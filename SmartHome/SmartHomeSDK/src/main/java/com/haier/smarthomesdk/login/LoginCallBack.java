package com.haier.smarthomesdk.login;

public interface LoginCallBack {
    void onAddAccountFailed(String errMsg, boolean isUserManuallyCancel);
    void onAddAccountSucceed(String mdt);
    void onUserCancelLogin();
    void onUserSkipLogin();
    void onLoginAppNotInstall();
}
