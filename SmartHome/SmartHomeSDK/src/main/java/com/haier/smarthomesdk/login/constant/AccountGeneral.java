package com.haier.smarthomesdk.login.constant;

/**
 * Created on 2018/4/3.
 */

public class AccountGeneral {

    /**
     * Account type id
     */
    public static final String ACCOUNT_TYPE = "com.haier.accountauthenticator";

    /**
     * Remote login app package name
     */
    public static final String REMOTE_LOGIN_APP_PACKAGE_NAME = "com.haier.gealoginservice";

    /**
     * Account name
     */
    public static final String ACCOUNT_NAME = "GE Appliances, a Haier Company";
    public static final String TAG = "GEAccount";

    /**
     *user behavior
     */
    public static final String USER_BEHAVIOR = "user_behavior";
    public static final String USER_BEHAVIOR_CANCEL_LOGIN = "user_behavior_cancel_login";
    public static final String USER_BEHAVIOR_SKIP_LOGIN = "user_behavior_skip_login";

    /**
     * Auth token types
     */
    public static final String AUTHTOKEN_TYPE_USER_ID = "USER_ID";
    public static final String AUTHTOKEN_TYPE_JWT = "JWT";
    public static final String TYPE_MDT = "MDT";

    /**
     * Url get jwt token
     */
    public static final String URL_GET_JWT_TOKEN = "https://accounts.brillion.geappliances.com/oauth2/getoken";

    /**
     * Tag : The key of the bundle data
     */
    public static final String KEY_OF_THE_BUNDLE_DATA = "KEY_OF_THE_BUNDLE_DATA";

    /**
     * Tag : The key of the data
     */
    public static final String KEY_OF_THE_DATA = "KEY_OF_THE_DATA";

    /**
     * Tag : Added an account function triggered
     */
    public static final String TRIGGERED_FROM_WHERE = "FROM_WHERE";

    /**
     * Tag : triggered  from which app
     */
    public static final String TRIGGERED_FROM_WHICH_APP = "FROM_WHICH_APP";

    /**
     * Tag : triggered  from which app
     */
    public static final String NEED_LOAD_WHICH_URL = "NEED_LOAD_WHICH_URL";

    /**
     * Tag : Added an account function triggered in kitchen-hub
     */
    public static final String TRIGGERED_FROM_KITCHEN_HUB_APP = "FROM_KITCHEN_HUB";

    /**
     * Tag : Added an account function triggered in Smart-Home
     */
    public static final String TRIGGERED_FROM_SMART_HOME_APP = "FROM_SMART_HOME";

    public static final String TRIGGERED_BEFORE_LOGGIN_GE_PROCESS = "BEFORE";

    public static final String TRIGGERED_AFTER_LOGGIN_GE_PROCESS = "AFTER";


    /**
     *  测试创建新账号(看起来这个创建账号适用于 GE Hub Setup 和 Smart Home )
     *  HTTPS GET:
     *  a.Field Testing  : https://accounts-fld.brillion.geappliances.com
     *  b.   Production  : https://accounts.brillion.geappliances.com
     *
     */
    public static String url_creat_new_field = "https://accounts-fld.brillion.geappliances.com";
    public static String url_creat_new_Production = "https://accounts.brillion.geappliances.com";



    /** GE Hub Setup -> field
     *
     */
    public static String hub_setup_redirect_id_field = "brillion.624e4a7632535555674b306872446b704e2f3833://oauth/redirect";
    public static String hub_setup_client_secret_field = "3138726f5946663779396632507833325844713956713139364979642b69773d";
    public static String hub_setup_client_id_field = "68634d494852585233514a2f6943345048704478";
    public static String hub_setup_url_logging_in_existing_field = "https://accounts-fld.brillion.geappliances.com/oauth2/auth?" +
            "redirect_uri="+hub_setup_redirect_id_field +
            "&response_type=code" +
            "&client_id="+hub_setup_client_id_field +
            "&access_type=offline&" +
            "scope=openid+addons";




    /**GE Hub Setup -> prod
     *
     */
    public static String hub_setup_redirect_id_prod = "brillion.704c772f334e73766d7439483441547746706448://oauth/redirect";
    public static String hub_setup_client_secret_prod = "415051324f65727457415541777a526a71655a2f542f69526570674f57654d3d";
    public static String hub_setup_client_id_prod = "5a44786a4a754e59664466734f685538356a6373";
    public static String hub_setup_url_logging_in_existing_prod = "https://accounts.brillion.geappliances.com/oauth2/auth?" +
            "redirect_uri="+hub_setup_redirect_id_prod +
            "&response_type=code&" +
            "client_id="+hub_setup_client_id_prod +
            "&access_type=offline&" +
            "scope=openid+addons";


    //-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
    /** Smart Home -> field
     *
     */
    public static String smart_home_redirect_id_field = "brillion.4f416a656774474e4d2b6f70674a4c4a33574832://oauth/redirect";
    public static String smart_home_client_secret_field = "5479646271464c413056744541446662564a496544514a4e337262756c42413d";
    public static String smart_home_client_id_field = "477065395341664b334d4e346a41366b79694333";
    public static String smart_home_url_logging_in_existing_testing = "https://accounts-fld.brillion.geappliances.com/oauth2/auth?" +
            "redirect_uri="+smart_home_redirect_id_field +
            "&response_type=code" +
            "&client_id="+smart_home_client_id_field +
            "&access_type=offline&" +
            "scope=openid+addons";




    /**Smart Home -> prod
     *
     */
    public static String smart_home_redirect_id_prod = "brillion.476c5469314771304b72524d736775646a744a46://oauth/redirect";
    public static String smart_home_client_secret_prod = "58627a785451596b4357334856484e6342355370575565596c6148507948383d";
    public static String smart_home_client_id_prod = "723877764976524c344d6b43583765774a6a7664";
    public static String smart_home_url_logging_in_existing_prod = "https://accounts.brillion.geappliances.com/oauth2/auth?" +
            "redirect_uri="+smart_home_redirect_id_prod +
            "&response_type=code&" +
            "client_id="+smart_home_client_id_prod +
            "&access_type=offline&" +
            "scope=openid+addons";

    //-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=




    /**
     * 重定向之后的 url 处理，
     */
    public static String common_Redirectsurl_Constant_field = "https://accounts-fld.brillion.geappliances.com/oauth2/token?code=" ;
    public static String common_Redirectsurl_Constant_prod = "https://accounts.brillion.geappliances.com/oauth2/token?code=" ;




    /**
     * GE Hub Setup -> 首次登陆 (prod)
     *
     */
    public static String hub_setup_url_logging_in_first_prod = "https://accounts.brillion.geappliances.com/oauth2/auth?" +
            "redirect_uri="+hub_setup_redirect_id_prod +
            "&response_type=code&" +
            "client_id="+hub_setup_client_id_prod;


    /**
     * GE Hub Setup -> 首次登陆 (field)
     *
     */
    public static String hub_setup_url_logging_in_first_field = "https://accounts-fld.brillion.geappliances.com/oauth2/auth?" +
            "redirect_uri="+hub_setup_redirect_id_field +
            "&response_type=code&" +
            "client_id="+hub_setup_client_id_field;

    /**
     *  Smart Home ->首次登陆 (prod)
     *
     */
    public static String smart_home_url_logging_in_first_prod = "https://accounts.brillion.geappliances.com/oauth2/auth?" +
            "redirect_uri="+smart_home_redirect_id_prod +
            "&response_type=code&" +
            "client_id="+smart_home_client_id_prod;

    /**
     *  Smart Home ->首次登陆 (field)
     *
     */
    public static String smart_home_url_logging_in_first_field = "https://accounts-fld.brillion.geappliances.com/oauth2/auth?" +
            "redirect_uri="+smart_home_redirect_id_field +
            "&response_type=code&" +
            "client_id="+smart_home_client_id_field;
}
