package com.haier.smarthomesdk.login.callback;

/**
 * Created on 2018/4/24.
 */

public interface CheckAndAddAccountCallBack {

    public void onAddAccountFailed(String msg);
    public void onAddAccountSucceed(String userMDT);
    public void onLoginAppNotInstall();

}