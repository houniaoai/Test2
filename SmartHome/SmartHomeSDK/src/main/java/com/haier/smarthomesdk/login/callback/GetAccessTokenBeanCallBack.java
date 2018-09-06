package com.haier.smarthomesdk.login.callback;


import com.haier.smarthomesdk.login.bean.AccessTokenBean;

/**
 * Created by 01438511 on 2018/8/30.
 */

public interface GetAccessTokenBeanCallBack {
    void onGetControlDevicesTokenFailed(String errMsg);
    void onGetControlDevicesTokenSucceed(AccessTokenBean bean);
}
