package com.haier.smarthomesdk.login;

import com.haier.smarthomesdk.login.bean.AccessTokenBean;

/**
 * Created by 01438511 on 2018/8/27.
 */

public interface RetrieveNewAccessTokenCallBack {
    void onRetrieveNewAccessTokenFailed(String errMsg, boolean isUserManuallyCancel);
    void onRetrieveNewAccessTokenSucceed(AccessTokenBean bean);
}
