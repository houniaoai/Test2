package com.haier.smarthomesdk.login.manager;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.accounts.AccountManagerCallback;
import android.accounts.AccountManagerFuture;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import com.google.gson.Gson;
import com.haier.smarthomesdk.login.bean.AccessTokenBean;
import com.haier.smarthomesdk.login.callback.AddAccountCallback;
import com.haier.smarthomesdk.login.callback.CheckAndAddAccountCallBack;
import com.haier.smarthomesdk.login.callback.DeleteAccountCallBack;
import com.haier.smarthomesdk.login.callback.GetAccessTokenBeanCallBack;
import com.haier.smarthomesdk.login.callback.GetAllAccountCallBack;
import com.haier.smarthomesdk.login.callback.GetAuthTokenByJWTCallBack;
import com.haier.smarthomesdk.login.callback.GetAuthTokenByUserIdCallBack;
import com.haier.smarthomesdk.login.constant.AccountGeneral;

import java.io.IOException;
import java.util.List;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Response;

/**
 * Created on 2018/4/24.
 */

public class GEAAccountManager {

    private Context mContext = null;
    private  AccountManager accountManager = null;
    private static GEAAccountManager geaAccountManager = null ;
    public static final String TAG = "GEAAccountManager";

    /**
     * Constructor
     * @param context
     */
    private GEAAccountManager(Context context) {
        this.mContext = context;
        accountManager = AccountManager.get(mContext);
    }

    public static GEAAccountManager getSingletonGEAAccountManager(Context mContext){
        if (geaAccountManager == null){
            synchronized (GEAAccountManager.class){
                geaAccountManager = new GEAAccountManager(mContext);
            }
        }
        return geaAccountManager;
    }

    /**
     * add a GEA Account
     * @param activity This parameter must be of type Activity.
     * @param callback
     */
    public void addAccount(Activity activity,Bundle bundle, final AddAccountCallback callback){
        if(hasInstallApplication(mContext, AccountGeneral.REMOTE_LOGIN_APP_PACKAGE_NAME)){
            //GEALoginService.apk installed
            accountManager.addAccount(AccountGeneral.ACCOUNT_TYPE, "", null,
                    bundle, activity, new AccountManagerCallback<Bundle>() {
                @Override
                public void run(AccountManagerFuture<Bundle> future) {
                    Bundle bnd = null;
                    try {
                        bnd = future.getResult();
                        if (bnd != null){
                            String userBehavior = bnd.getString(AccountGeneral.USER_BEHAVIOR);
                            String userMDT = bnd.getString("userMDT");
                            String accountType = bnd.getString("ACCOUNT_TYPE");
                            if (!TextUtils.isEmpty(accountType) && accountType.contains(AccountGeneral.ACCOUNT_TYPE) &&!TextUtils.isEmpty(userMDT)){
                                Log.d(TAG, "Account was created,NewAccount Bundle is -> " + bnd);
                                callback.onAddAccountSucceed(userMDT);
                            }else if (!TextUtils.isEmpty(userBehavior) && userBehavior.contains("cancel")){
                                callback.onUserCancelLogin();
                            }else if (!TextUtils.isEmpty(userBehavior) && userBehavior.contains("skip")){
                                callback.onUserSkipLogin();
                            }else {
                                Log.d(TAG, "Account was'not created, Bundle is -> " + bnd);
                                callback.onAddAccountFailed("Account was'not created, Bundle is -> " + bnd,false);
                            }

                        }else {
                            Log.d(TAG, "Account was'not created, Bundle is null -> " + bnd);
                            callback.onAddAccountFailed("Account was'not created, Bundle is null -> " + bnd,false);
                        }
                    }catch (Exception e) {
                        //Add Account Error/Failure
                        e.printStackTrace();
                        Log.e(TAG, "AddAccount failed,Exception in method -> " + e.toString());
                        callback.onAddAccountFailed("bnd is null.",false);
                    }
                }
            },null);
        }else {
            //GEALoginService.apk not yet installed
            callback.onLoginAppNotInstall();
        }

    }

    public boolean hasInstallApplication(Context context, String packageName){
        PackageManager packageManager = context.getPackageManager();
        //Get information about application packages installed on the system
        List<PackageInfo> listPackageInfo = packageManager.getInstalledPackages(0);
        for (int i = 0; i < listPackageInfo.size(); i++) {
            if(listPackageInfo.get(i).packageName.equalsIgnoreCase(packageName)){
                return true;
            }
        }
        return false;

    }

    public boolean checkIfAccountAlreadyLoggedIn(String accountName){
        boolean IsAccountAlreadyLoggedIn = false;
        if (hasInstallApplication(mContext,AccountGeneral.REMOTE_LOGIN_APP_PACKAGE_NAME)){
            //GEALoginService.apk installed,find all accounts under this account system
            Account[] accounts = accountManager.getAccountsByType(AccountGeneral.ACCOUNT_TYPE);
            if (accounts.length != 0){
                Log.d(AccountGeneral.TAG,"Check if the account : " + accountName + " exists." );
                for (int j = 0 ; j < accounts.length ; j++){
                    if (accountName.equals(accounts[j].name)){
                        Log.d(AccountGeneral.TAG,"The account : " + accounts[j].name +
                                " already exists, number is -> " + j);
                        //Explain that the target account has been previously logged in and saved
                        IsAccountAlreadyLoggedIn = true ;
                    }
                }
                return IsAccountAlreadyLoggedIn;

            }else {
                //No account has been logged in before.
                Log.d(AccountGeneral.TAG,"No account has been logged in before,so return false.");
                return IsAccountAlreadyLoggedIn;
            }

        }else {
            //GEALoginService.apk not yet installed
            Log.e(AccountGeneral.TAG,"GEALoginService.apk not yet installed,so return false.");
            return IsAccountAlreadyLoggedIn;

        }

    }

    public void checkAndAddAccount(String userName,String userId, String userMDT,
                                   CheckAndAddAccountCallBack callBack){
        /**
         * Use addAccountExplicitly method to add Account to save userId & userMDT，
         * Attention :The userName for the account created is the client_id for the application.
         */

        boolean alreadyGraded = false;
        if (hasInstallApplication(mContext,AccountGeneral.REMOTE_LOGIN_APP_PACKAGE_NAME)){
            //Find all accounts under this account system
            Account[] accounts = accountManager.getAccountsByType(AccountGeneral.ACCOUNT_TYPE);
            if (accounts.length != 0){
                Log.d(AccountGeneral.TAG,"Check if the account : " + userName + " exists." );
                for (int j = 0 ; j < accounts.length ; j++){
                    if (userName.equals(accounts[j].name)){
                        Log.d(AccountGeneral.TAG,"The account : " + accounts[j].name +
                                " already exists, number is -> " + j);
                        //Explain that the target account has been previously logged in and saved
                        alreadyGraded = true ;
                    }
                }

                if (alreadyGraded){
                    alreadyGraded = false ;
                    //Delete the account and add new account information.
                    Log.e(AccountGeneral.TAG,"Delete the account and add new account information..");
                    boolean isRemoveAccountSuccess =  accountManager.removeAccountExplicitly(
                            new Account(userName, AccountGeneral.ACCOUNT_TYPE));
                    if (isRemoveAccountSuccess){
                        Log.d(AccountGeneral.TAG,"The old information about account has been " +
                                "deleted and new information about account is saving");
                        methodAddAccount(accountManager,userName,userId,userMDT,callBack);
                    }else {
                        //Failed to delete existing account information
                        Log.e(AccountGeneral.TAG,"Delete existing account information failed.");
                        callBack.onAddAccountFailed("Delete existing account information failed.");
                    }

                }else {
                    //Save the account directly
                    Log.d(AccountGeneral.TAG,"Save the account directly.");
                    methodAddAccount(accountManager,userName,userId,userMDT,callBack);
                }

            }else {
                //Save the account directly
                Log.d(AccountGeneral.TAG,"Save account : " + userName + " for the first time.");
                methodAddAccount(accountManager,userName,userId,userMDT,callBack);
            }

        }else {
            //GEALoginService.apk not yet installed
            callBack.onLoginAppNotInstall();
        }

    }

    private void methodAddAccount(AccountManager accountManager, String userName, String userId,
                                         String userMDT,CheckAndAddAccountCallBack callBack){
        if (hasInstallApplication(mContext,AccountGeneral.REMOTE_LOGIN_APP_PACKAGE_NAME)){
            final Account account = new Account(userName, AccountGeneral.ACCOUNT_TYPE);
            Bundle userData = new Bundle();
            userData.putString(AccountGeneral.TYPE_MDT,userMDT);
            userData.putString(AccountGeneral.AUTHTOKEN_TYPE_USER_ID,userId);

            Log.d(AccountGeneral.TAG,"Start saving account -> " + account +
                    "\nStart saving userData -> " + userData);
            //Add Account first , then setAuthToken
            boolean isAddSuccess =  accountManager.addAccountExplicitly(account, null, userData);
            if (isAddSuccess){
                Log.d(AccountGeneral.TAG, "The account : " + userName + " was successfully added, " +
                        "then setAuthToken.");
                accountManager.notifyAccountAuthenticated(account);//V23
                accountManager.setAuthToken(account,AccountGeneral.AUTHTOKEN_TYPE_USER_ID,userId);
                accountManager.setUserData(account,"userData",userMDT);
                //Verify after saving successfully
                String authToken = accountManager.peekAuthToken(account,
                        AccountGeneral.AUTHTOKEN_TYPE_USER_ID);
                Log.d(AccountGeneral.TAG,"Verify whether the userId can be retrieved from the account:" +
                        "\nuserId -> " + authToken +" . \nmdt -> " + accountManager.getUserData(account,"userData") );
                callBack.onAddAccountSucceed(userMDT);
            }else {
                //Prompt "Account information saved failed"
                callBack.onAddAccountFailed("Failed to save target account information to AccountManager, " +
                        "account already exists, or other error.");
                Log.e(AccountGeneral.TAG, "Failed to save target account information to AccountManager, " +
                        "account already exists, or other error.");
            }

        }else {
            //GEALoginService.apk not yet installed
            callBack.onLoginAppNotInstall();
        }

    }

    public void deleteAccount(String userName ,DeleteAccountCallBack callBack){

        if (hasInstallApplication(mContext,AccountGeneral.REMOTE_LOGIN_APP_PACKAGE_NAME)){

            boolean alreadyGraded = false;
            //Find all accounts
            Account[] accounts = accountManager.getAccountsByType(AccountGeneral.ACCOUNT_TYPE);
            if (accounts.length != 0){
                Log.d(AccountGeneral.TAG,"Check if the account : " + userName + " exists." );
                for (int j = 0 ; j < accounts.length ; j++){
                    if (userName.equals(accounts[j].name)){
                        Log.d(AccountGeneral.TAG,"The account : " + accounts[j].name +
                                " already exists, number is -> " + j);
                        //Explain that the target account has been previously logged in and saved
                        alreadyGraded = true ;
                    }
                }

                if (alreadyGraded){
                    alreadyGraded = false ;
                    //Delete the account explicitly
                    boolean isRemoveAccountSuccess =  accountManager.removeAccountExplicitly(
                            new Account(userName, AccountGeneral.ACCOUNT_TYPE));
                    if (isRemoveAccountSuccess){
                        Log.d(AccountGeneral.TAG,"Account information has been deleted " +
                                "from AccountManager.");
                        callBack.onDeleteAccountSucceed("Account information has been deleted " +
                                "from AccountManager.");
                    }else {
                        //Delete the account failed
                        Log.e(AccountGeneral.TAG,"Failed to delete the account information " +
                                "from AccountManager.");
                        callBack.onDeleteAccountFailed("Failed to delete the account information " +
                                "from AccountManager.");
                    }
                }else {
                    //The account has not been logged in and saved before.
                    Log.e(AccountGeneral.TAG,"This account was not previously " +
                            "saved in Account Manager.");
                    callBack.onDeleteAccountFailed("This account was not previously " +
                            "saved in Account Manager.");
                }
            }else {
                //The current account system does not hold any account information.
                Log.e(AccountGeneral.TAG,"The AccountManager does not " +
                        "hold any Account information.");
                callBack.onDeleteAccountFailed("The AccountManager does not " +
                        "hold any Account information.");
            }

        }else {
            //GEALoginService.apk not yet installed
            callBack.onLoginAppNotInstall();
        }

    }

    public void getAllAccount(GetAllAccountCallBack callBack){
        if (hasInstallApplication(mContext,AccountGeneral.REMOTE_LOGIN_APP_PACKAGE_NAME)){
            Account[] accounts = accountManager.getAccountsByType(AccountGeneral.ACCOUNT_TYPE);
            if (accounts.length != 0){
                callBack.onGetAccountSucceed(accounts);
            }else {
                //The current account system does not hold any account information.
                Log.e(AccountGeneral.TAG,"The AccountManager does not " +
                        "hold any Account information.");
                callBack.onGetAccountFailed("The AccountManager does not " +
                        "hold any Account information.");
            }

        }else {
            //GEALoginService.apk not yet installed
            callBack.onLoginAppNotInstall();
        }
    }

    public void getAuthToken(String authtokenType, String clientId , String clientSsecret,
                             GetAuthTokenByUserIdCallBack callBackUserId,
                             GetAuthTokenByJWTCallBack callBackJWT) {
        if (hasInstallApplication(mContext,AccountGeneral.REMOTE_LOGIN_APP_PACKAGE_NAME)){
            if (AccountGeneral.AUTHTOKEN_TYPE_USER_ID.equals(authtokenType)){
                getAuthTokenByTypeUserId(accountManager,clientId,callBackUserId);
            }else if (AccountGeneral.AUTHTOKEN_TYPE_JWT.equals(authtokenType)){
                getAuthTokenByTypeJwt(accountManager,clientId,clientSsecret,callBackJWT);
            }
        }else {
            //GEALoginService.apk not yet installed
            if (callBackUserId != null){
                callBackUserId.onLoginAppNotInstall();
            }else if (callBackJWT != null){
                callBackJWT.onLoginAppNotInstall();
            }
        }
    }

    private void getAuthTokenByTypeJwt(AccountManager accountManager, String clientId,
                                              String clientSsecret,final GetAuthTokenByJWTCallBack callBack) {

        Account account_jwt = new Account(clientId, AccountGeneral.ACCOUNT_TYPE);

        Bundle options = new Bundle();
        options.putString("client_id",clientId);
        options.putString("client_secret",clientSsecret);

        accountManager.getAuthToken(account_jwt, AccountGeneral.AUTHTOKEN_TYPE_JWT,
                options, null, new AccountManagerCallback<Bundle>() {
                    @Override
                    public void run(AccountManagerFuture<Bundle> future) {
                        try {
                            Bundle bnd = future.getResult();
                            Log.d(AccountGeneral.TAG, "accountManager -> getAuthTokenByTypeJwt() -> " +
                                    "AccountManagerCallback() is called,\naccount Bundle is -> " + bnd);
                            if (bnd.getString("errorMessage") != null){
                                Log.e(AccountGeneral.TAG, "Error getAuthTokenByTypeJwt(),detail -> "+
                                        bnd.getString("errorMessage"));
                                callBack.onGetTokenByJWTFailed(bnd.getString("errorMessage"));
                            }else {
                                String access_token = bnd.getString("ACCESS_TOKEN", "");
                                String id_token = bnd.getString("ID_TOKEN", "");
                                Log.d(AccountGeneral.TAG, "根据已知的存储的账号类型 \"com.haier.xxxx\"，" +
                                        "\n用户名 client_id ，和存取类型：JWT，通过联网请求，得到了要找的\n" +
                                        "这个账户的 id_token 是 ->" + id_token + ".\naccess_token 是 -> " + access_token + ".");
                                if ((!TextUtils.isEmpty(access_token)) && (!TextUtils.isEmpty(id_token))){
                                    callBack.onGetTokenByJWTSucceed(access_token,id_token);
                                }else {
                                    callBack.onGetTokenByJWTFailed("Token value is null.");
                                }

                            }

                        }catch (Exception e1) {
                            e1.printStackTrace();
                            Log.e(AccountGeneral.TAG,"Exception in getAuthTokenByTypeJwt(), detail ->\n" +
                                    e1.getMessage());
                            callBack.onGetTokenByJWTFailed(e1.getMessage());
                        }
                    }
                }, null);

    }

    private void getAuthTokenByTypeUserId(AccountManager accountManager,
                                                 String clientId, final GetAuthTokenByUserIdCallBack callBack) {

        Account targetAccount = new Account(clientId, AccountGeneral.ACCOUNT_TYPE);
        accountManager.getAuthToken(targetAccount, AccountGeneral.AUTHTOKEN_TYPE_USER_ID,
                null, null, new AccountManagerCallback<Bundle>() {
                    @Override
                    public void run(AccountManagerFuture<Bundle> future) {
                        try {
                            Bundle bnd = future.getResult();
                            Log.d(AccountGeneral.TAG, "accountManager -> getAuthTokenByTypeUserId() -> " +
                                    "AccountManagerCallback() is called,\naccount Bundle is -> " + bnd);

                            if (bnd.getString("errorMessage") != null){
                                Log.e(AccountGeneral.TAG, "Error getAuthTokenByTypeUserId(),detail -> "+
                                        bnd.getString("errorMessage"));
                                callBack.onGetTokenFailed(bnd.getString("errorMessage"));

                            }else {
                                String targetUserId = bnd.getString("authtoken", "");
                                Log.d(AccountGeneral.TAG, "根据已知的存储的账号类型 \"com.haier.xxxx\"，" +
                                        "\n用户名 client_id ，和存取类型：USER_ID，得到了要找的\n" +
                                        "这个账户的 userId 是 ->" + targetUserId + ".");
                                if (!TextUtils.isEmpty(targetUserId)){

                                    callBack.onGetTokenSucceed(targetUserId);

                                }else {
                                    callBack.onGetTokenFailed("UserId is null.");
                                }

                            }

                        }/* catch (OperationCanceledException e) {
                            e.printStackTrace();
                            Log.e(AccountGeneral.TAG, "Error in getAuthTokenByTypeUserId(), detail ->\n" +
                                            e.getMessage());
                            callBack.onGetTokenFailed(e.getMessage());
                        } catch (IOException e) {
                            e.printStackTrace();
                            Log.e(AccountGeneral.TAG, "Error in getAuthTokenByTypeUserId(), detail ->\n" +
                                    e.getMessage());
                            callBack.onGetTokenFailed(e.getMessage());
                        } catch (AuthenticatorException e) {
                            e.printStackTrace();
                            Log.e(AccountGeneral.TAG, "Error in getAuthTokenByTypeUserId(), detail ->\n" +
                                    e.getMessage());
                            callBack.onGetTokenFailed(e.getMessage());
                        } */ catch (Exception e1) {
                            e1.printStackTrace();
                            Log.e(AccountGeneral.TAG,"Exception in getAuthTokenByTypeUserId(), detail ->\n" +
                                    e1.getMessage());
                            callBack.onGetTokenFailed(e1.getMessage());

                        }
                    }
                }, null);

    }

    public void retrieveNewAccessToken(boolean isProductionEnvironment, String client_id, String client_secret, String mdtToken,
                                        final GetAccessTokenBeanCallBack callback){
        String targetUrl = "" ;
        String hostParameter = "" ;
        if (isProductionEnvironment){
            targetUrl = "https://accounts.brillion.geappliances.com//oauth2/getoken?client_id=" + client_id +
                    "&client_secret=" + client_secret + "&mdt=" + mdtToken ;
            hostParameter = "accounts.brillion.geappliances.com";
        }else {
            targetUrl = "https://accounts-fld.brillion.geappliances.com//oauth2/getoken?client_id=" + client_id +
                    "&client_secret=" + client_secret + "&mdt=" + mdtToken ;
            hostParameter = "accounts-fld.brillion.geappliances.com";
        }

        FormBody.Builder params = new FormBody.Builder();
        params.add("POST","/oauth2/getoken?");
        params.add("Host",hostParameter);
        params.add("Content-Type","application/x-www-form-urlencoded");

        OkHttpClient okHttpClient = new OkHttpClient();

        okhttp3.Request request = new okhttp3.Request.Builder()
                .url(targetUrl)
                .post(params.build())
                .build();

        Call call = okHttpClient.newCall(request);

        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                //运行错误
                Log.e(AccountGeneral.TAG,"响应失败 -> onFailure -> " + e.toString() );
                callback.onGetControlDevicesTokenFailed(e.getMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                //正确响应
                if (response.code() == 200){
                    //Log.i(AccountGeneral.TAG, "响应成功 -> response.body() -> " + response.body().string());
                    String tempString = response.body().string();
                    AccessTokenBean bean = new Gson().fromJson(tempString, AccessTokenBean.class);
                    callback.onGetControlDevicesTokenSucceed(bean);

                }else {
                    Log.e(AccountGeneral.TAG, "response.code() != 200 ,response == " + response);
                    callback.onGetControlDevicesTokenFailed("response.code() != 200 ,response == " + response);
                }
            }
        });
    }
}
