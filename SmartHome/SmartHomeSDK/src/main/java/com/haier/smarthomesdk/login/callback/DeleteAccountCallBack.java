package com.haier.smarthomesdk.login.callback;

/**
 * Created on 2018/4/24.
 */

public interface DeleteAccountCallBack {
    public void onDeleteAccountFailed(String errMsg);
    public void onDeleteAccountSucceed(String msg);
    public void onLoginAppNotInstall();
}