package com.haier.smarthomesdk;

import android.app.Activity;
import android.content.Context;

import com.haier.smarthomesdk.http.RxApiManager;
import com.haier.smarthomesdk.login.LoginCallBack;
import com.haier.smarthomesdk.login.LoginUtils;
import com.haier.smarthomesdk.login.RetrieveNewAccessTokenCallBack;
import com.haier.smarthomesdk.utils.SharedPreferencesUtil;

public class SmartHomeSdk {
    public static boolean isShowLog;
    public static boolean isProduction;

    private static SmartHomeSdk instance;

    Context context;

    public static SmartHomeSdk getInstance() {
        if (instance == null) {
            instance = new SmartHomeSdk();
        }
        return instance;
    }

    /**
     * @param context
     * @param isShowLog    Do you show log?
     * @param isProduction Is it a production environment or a testing environment?
     */
    public void init(Context context, Boolean isShowLog, Boolean isProduction) {
        SharedPreferencesUtil.getInstance().init(context);
        LoginUtils.getInstance().init(context);
        this.context = context;
        this.isShowLog = isShowLog;
        this.isProduction = isProduction;

    }

    /**
     * get MDT
     *
     * @param activity
     * @param mloginCallBack
     */
    public void getMDT(final Activity activity, final LoginCallBack mloginCallBack) {
        LoginUtils.getInstance().getMDTToken(activity, mloginCallBack);
    }

    /**
     * Cancel the request
     */
    public void cancelRequest() {
        RxApiManager.get().cancelAll();
    }

}
