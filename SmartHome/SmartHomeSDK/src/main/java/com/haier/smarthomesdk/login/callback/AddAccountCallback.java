package com.haier.smarthomesdk.login.callback;

/**
 * Created on 2018/5/21.
 */

public interface AddAccountCallback {

    public void onAddAccountFailed(String errMsg, boolean isUserManuallyCancel);
    public void onAddAccountSucceed(String userMDT);
    public void onUserCancelLogin();
    public void onUserSkipLogin();
    public void onLoginAppNotInstall();
}
