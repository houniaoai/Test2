package com.haier.smarthomesdk.login.callback;

/**
 * Created on 2018/4/24.
 */

public interface GetAuthTokenByUserIdCallBack extends GetAuthTokenCallBack{
    public void onGetTokenFailed(String errMsg);
    public void onGetTokenSucceed(String user_id);
}
