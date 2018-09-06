package com.haier.smarthomesdk.login;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Toast;
import com.haier.smarthomesdk.BuildConfig;
import com.haier.smarthomesdk.SmartHomeSdk;
import com.haier.smarthomesdk.login.bean.AccessTokenBean;
import com.haier.smarthomesdk.login.callback.AddAccountCallback;
import com.haier.smarthomesdk.login.callback.GetAccessTokenBeanCallBack;
import com.haier.smarthomesdk.login.constant.AccountGeneral;
import com.haier.smarthomesdk.login.data.SmartHomeBundleData;
import com.haier.smarthomesdk.login.data.SmartHomeParameters;
import com.haier.smarthomesdk.login.manager.GEAAccountManager;
import com.haier.smarthomesdk.utils.Constants;
import com.haier.smarthomesdk.utils.LogUtil;
import com.haier.smarthomesdk.utils.SharedPreferencesUtil;

import okhttp3.Response;

public class LoginUtils {

    private static String TAG = "LoginUtils";
    private static LoginUtils instance;
    private Context context;

    public static LoginUtils getInstance() {
        if (instance == null) {
            instance = new LoginUtils();
        }
        return instance;
    }

    public void init(Context context) {
        this.context = context.getApplicationContext();
    }

    public String getLocalMDTToken() {
        String mdt = (String) SharedPreferencesUtil.getInstance().get(Constants.MDT, "");
        return mdt;
    }


    public void getMDTToken(final Activity activity, final LoginCallBack mloginCallBack) {
        String mdt = LoginUtils.getInstance().getLocalMDTToken();
        if (!TextUtils.isEmpty(mdt)) {
            mloginCallBack.onAddAccountSucceed(mdt);
            return;
        }

        GEAAccountManager geaAccountManager = GEAAccountManager.getSingletonGEAAccountManager(activity);
        Bundle bundle = new Bundle();
        bundle.putString(AccountGeneral.TRIGGERED_FROM_WHICH_APP, AccountGeneral.TRIGGERED_FROM_SMART_HOME_APP);
        bundle.putString(AccountGeneral.TRIGGERED_FROM_WHERE, AccountGeneral.TRIGGERED_BEFORE_LOGGIN_GE_PROCESS);
        bundle.putBundle(SmartHomeParameters.BUNDLEDATA_KEY, SmartHomeBundleData.initBundleData());

        LogUtil.d(TAG, "Start to open the GEA account login Activity  ----- -----");
        geaAccountManager.addAccount(activity, bundle, new AddAccountCallback() {
            @Override
            public void onAddAccountFailed(String errMsg, boolean isUserManuallyCancel) {
                //Add AccountFailed
                LogUtil.d(TAG, "Err in addAccount method , errMsg -> " + errMsg);
                mloginCallBack.onAddAccountFailed(errMsg, isUserManuallyCancel);
            }

            @Override
            public void onAddAccountSucceed(String mdt) {
                //Add AccountSucceed
                LogUtil.d(TAG, "Add AccountSucceed in LoginUtils,mdt is -->" + mdt);
                SharedPreferencesUtil.getInstance().put(Constants.MDT, mdt);
                mloginCallBack.onAddAccountSucceed(mdt);

            }

            @Override
            public void onUserCancelLogin() {
                mloginCallBack.onUserCancelLogin();
                //User clicked the return button in the login account activity.
                LogUtil.d(TAG, "User clicked the return button in the login account activity.");
            }

            @Override
            public void onUserSkipLogin() {
                mloginCallBack.onUserSkipLogin();
                //User clicked the skip button in the login account activity.
                LogUtil.d(TAG, "User clicked the skip button in the login account activity," +
                        "will jump to lock screen.");
            }

            @Override
            public void onLoginAppNotInstall() {
                mloginCallBack.onLoginAppNotInstall();
                Toast.makeText(context, "Check you have install Gea account application", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void retrieveNewAccessToken(boolean isProductionEnvironment, String client_id, String client_secret, String mdtToken,
                                        final RetrieveNewAccessTokenCallBack callback) {
        GEAAccountManager geaAccountManager = GEAAccountManager.getSingletonGEAAccountManager(context);
        geaAccountManager.retrieveNewAccessToken(isProductionEnvironment, client_id, client_secret, mdtToken,
                new GetAccessTokenBeanCallBack() {
                    @Override
                    public void onGetControlDevicesTokenFailed(String s) {
                        LogUtil.d(TAG, "onGetControlDevicesTokenFailed ,error is -->" + s);
                        if (callback != null) {
                            callback.onRetrieveNewAccessTokenFailed(s, false);
                        }
                    }

                    @Override
                    public void onGetControlDevicesTokenSucceed(AccessTokenBean devicesControlBean) {
                        LogUtil.d(TAG, "onGetControlDevicesTokenSucceed ,devicesControlBean is -->" + devicesControlBean);
                        SharedPreferencesUtil.getInstance().put(Constants.ACCESS_TOKEN_LOCATION, devicesControlBean.getAccess_token());

                        if (callback != null) {
                            callback.onRetrieveNewAccessTokenSucceed(devicesControlBean);
                        }
                    }
                });
    }

    public void goToRetrieveNewAccessToken() {
        if (!TextUtils.isEmpty(getLocalMDTToken())) {
            retrieveNewAccessToken(SmartHomeSdk.isProduction,
                    BuildConfig.SMART_HOME_CLIENT_ID, BuildConfig.SMART_HOME_CLIENT_SECRET, getLocalMDTToken(),
                    new RetrieveNewAccessTokenCallBack() {
                        @Override
                        public void onRetrieveNewAccessTokenFailed(String errMsg, boolean isUserManuallyCancel) {

                        }

                        @Override
                        public void onRetrieveNewAccessTokenSucceed(AccessTokenBean bean) {
                            String access_token = bean.getAccess_token();
                            SharedPreferencesUtil.getInstance().put(Constants.ACCESS_TOKEN_LOCATION, access_token);
                        }
                    });
        }
    }

}
