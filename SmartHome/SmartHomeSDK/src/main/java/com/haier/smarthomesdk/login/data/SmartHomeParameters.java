package com.haier.smarthomesdk.login.data;

import com.haier.smarthomesdk.BuildConfig;

/**
 * Created by 01438511 on 2018/8/14.
 */

public class SmartHomeParameters {

    public static final String BUNDLEDATA_KEY = "BUNDLEDATA";
    public static final String URL_WEBVIEW_NEEDS_TO_LOAD_IN_FIRST_STEP_KEY = "URL_WEBVIEW_NEEDS_TO_LOAD_IN_FIRST_STEP";
    public static final String THE_FIRST_HALF_OF_THE_SECOND_URL_KEY = "THE_FIRST_HALF_OF_THE_SECOND_URL";
    public static final String THE_SECOND_HALF_OF_THE_SECOND_URL_KEY = "THE_SECOND_HALF_OF_THE_SECOND_URL";
    public static final String HOST_PARAMETER_OF_THE_SECOND_REQUEST_KEY = "HOST_PARAMETER_OF_THE_SECOND_REQUEST";
    public static final String POST_PARAMETER_OF_THE_SECOND_REQUEST_KEY = "POST_PARAMETER_OF_THE_SECOND_REQUEST";
    public static final String CONTENT_TYPE_PARAMETER_OF_THE_SECOND_REQUEST_KEY = "CONTENT_TYPE_PARAMETER_OF_THE_SECOND_REQUEST";
    public static final String THE_URL_THIRD_REQUEST_KEY = "THE_URL_THIRD_REQUEST";
    public static final String BODYSTRING_OF_THE_THIRD_REQUEST_KEY = "BODYSTRING_OF_THE_THIRD_REQUEST";
    public static final String HOST_PARAMETER_OF_THE_THIRD_REQUEST_KEY = "HOST_PARAMETER_OF_THE_THIRD_REQUEST";
    public static final String POST_PARAMETER_OF_THE_THIRD_REQUEST_KEY = "POST_PARAMETER_OF_THE_THIRD_REQUEST";
    public static final String AUTHORIZATION_PARAMETER_OF_THE_THIRD_REQUEST_KEY = "AUTHORIZATION_PARAMETER_OF_THE_THIRD_REQUEST";
    public static final String CONTENT_TYPE_PARAMETER_OF_THE_THIRD_REQUEST_KEY = "CONTENT_TYPE_PARAMETER_OF_THE_THIRD_REQUEST";

    /**
     * Login account  SMART_HOME parameters.
     */
    public static String URL_WEBVIEW_NEEDS_TO_LOAD_IN_FIRST_STEP = BuildConfig.SMART_HOME_URL_LOGGING_IN_FIRST;
    public static String THE_FIRST_HALF_OF_THE_SECOND_URL = BuildConfig.THE_FIRST_PART_OF_THE_SECOND_URL;
    public static String THE_SECOND_HALF_OF_THE_SECOND_URL = "&client_id=" +
            BuildConfig.SMART_HOME_CLIENT_ID + "&client_secret=" + BuildConfig.SMART_HOME_CLIENT_SECRET +
            "&redirect_uri=" + BuildConfig.SMART_HOME_REDIRECT_ID + "&grant_type=authorization_code";
    public static String HOST_PARAMETER_OF_THE_SECOND_REQUEST = BuildConfig.HOST_PARAMETER_OF_THE_SECOND_REQUEST;
    public static String POST_PARAMETER_OF_THE_SECOND_REQUEST = "/oauth2/getoken HTTP/1.1";
    public static String CONTENT_TYPE_PARAMETER_OF_THE_SECOND_REQUEST = "application/x-www-form-urlencoded";
    public static String THE_URL_THIRD_REQUEST = BuildConfig.THE_URL_THIRD_REQUEST;
    public static String BODYSTRING_OF_THE_THIRD_REQUEST = "{\"kind\":\"" + "mdt#login" + "\",\"app\":\"" + "com.gea.smarthome.wca.dev.android" + "\",\"os\":\"google_android\"}";
    public static String HOST_PARAMETER_OF_THE_THIRD_REQUEST = BuildConfig.HOST_PARAMETER_OF_THE_THIRD_REQUEST;
    public static String POST_PARAMETER_OF_THE_THIRD_REQUEST = "";
    public static String AUTHORIZATION_PARAMETER_OF_THE_THIRD_REQUEST = "";
    public static String CONTENT_TYPE_PARAMETER_OF_THE_THIRD_REQUEST = "application/json";

}
