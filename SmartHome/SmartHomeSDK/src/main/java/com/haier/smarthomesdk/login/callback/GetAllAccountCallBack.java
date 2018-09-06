package com.haier.smarthomesdk.login.callback;

import android.accounts.Account;

/**
 * Created on 2018/4/24.
 */

public interface GetAllAccountCallBack {
    public void onGetAccountFailed(String errMsg);
    public void onGetAccountSucceed(Account[] accounts);
    public void onLoginAppNotInstall();
}
