package com.haier.smarthomesdk.utils;

import android.util.Log;
import com.haier.smarthomesdk.SmartHomeSdk;

public class LogUtil {

    public static boolean isDebugModel = SmartHomeSdk.isShowLog;

    public static void e(String msg, String tag) {
        if (isDebugModel) {
            Log.e(tag, "--> " + msg);
        }
    }

    public static void v(final String tag, final String msg) {
        if (isDebugModel) {
            Log.v(tag, "--> " + msg);
        }
    }

    public static void d(final String tag, final String msg) {
        if (isDebugModel) {
            Log.d(tag, "--> " + msg);
        }
    }

    public static void i(final String tag, final String msg) {
        if (isDebugModel) {
            Log.i(tag, "--> " + msg);
        }
    }

    public static void w(final String tag, final String msg) {
        if (isDebugModel) {
            Log.w(tag, "--> " + msg);
        }
    }
}