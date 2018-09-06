package com.haier.smarthomesdk.login.callback;

/**
 * Created on 2018/4/24.
 */

public interface GetAuthTokenByJWTCallBack extends GetAuthTokenCallBack{
    public void onGetTokenByJWTFailed(String errMsg);
    public void onGetTokenByJWTSucceed(String accessToken, String idToken);

}