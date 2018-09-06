package com.haier.smarthomesdk.login.data;

import android.os.Bundle;

/**
 * Created by 01438511 on 2018/8/20.
 */

public class SmartHomeBundleData {

    public static Bundle bundleData = new Bundle();

    public static synchronized Bundle initBundleData(){
        if ( null == bundleData ){
            bundleData = new Bundle();
        }else {
            bundleData.putString(SmartHomeParameters.URL_WEBVIEW_NEEDS_TO_LOAD_IN_FIRST_STEP_KEY, SmartHomeParameters.URL_WEBVIEW_NEEDS_TO_LOAD_IN_FIRST_STEP);
            bundleData.putString(SmartHomeParameters.THE_FIRST_HALF_OF_THE_SECOND_URL_KEY, SmartHomeParameters.THE_FIRST_HALF_OF_THE_SECOND_URL);
            bundleData.putString(SmartHomeParameters.THE_SECOND_HALF_OF_THE_SECOND_URL_KEY, SmartHomeParameters.THE_SECOND_HALF_OF_THE_SECOND_URL);
            bundleData.putString(SmartHomeParameters.HOST_PARAMETER_OF_THE_SECOND_REQUEST_KEY, SmartHomeParameters.HOST_PARAMETER_OF_THE_SECOND_REQUEST);
            bundleData.putString(SmartHomeParameters.POST_PARAMETER_OF_THE_SECOND_REQUEST_KEY, SmartHomeParameters.POST_PARAMETER_OF_THE_SECOND_REQUEST);
            bundleData.putString(SmartHomeParameters.CONTENT_TYPE_PARAMETER_OF_THE_SECOND_REQUEST_KEY, SmartHomeParameters.CONTENT_TYPE_PARAMETER_OF_THE_SECOND_REQUEST);
            bundleData.putString(SmartHomeParameters.THE_URL_THIRD_REQUEST_KEY, SmartHomeParameters.THE_URL_THIRD_REQUEST);
            bundleData.putString(SmartHomeParameters.BODYSTRING_OF_THE_THIRD_REQUEST_KEY, SmartHomeParameters.BODYSTRING_OF_THE_THIRD_REQUEST);
            bundleData.putString(SmartHomeParameters.HOST_PARAMETER_OF_THE_THIRD_REQUEST_KEY, SmartHomeParameters.HOST_PARAMETER_OF_THE_THIRD_REQUEST);
            bundleData.putString(SmartHomeParameters.POST_PARAMETER_OF_THE_THIRD_REQUEST_KEY, SmartHomeParameters.POST_PARAMETER_OF_THE_THIRD_REQUEST);
            bundleData.putString(SmartHomeParameters.AUTHORIZATION_PARAMETER_OF_THE_THIRD_REQUEST_KEY, SmartHomeParameters.AUTHORIZATION_PARAMETER_OF_THE_THIRD_REQUEST);
            bundleData.putString(SmartHomeParameters.CONTENT_TYPE_PARAMETER_OF_THE_THIRD_REQUEST_KEY, SmartHomeParameters.CONTENT_TYPE_PARAMETER_OF_THE_THIRD_REQUEST);
        }
        return bundleData;
    }

}
