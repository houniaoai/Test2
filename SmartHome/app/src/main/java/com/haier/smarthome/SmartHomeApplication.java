package com.haier.smarthome;

import android.app.Application;

import com.haier.smarthomesdk.SmartHomeSdk;

public class SmartHomeApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        SmartHomeSdk.getInstance().init(this, true, false);
    }
}
