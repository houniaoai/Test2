package com.haier.smarthomesdk.http;

import com.haier.smarthomesdk.SmartHomeSdk;
import com.haier.smarthomesdk.login.LoginUtils;
import com.haier.smarthomesdk.utils.Constants;
import com.haier.smarthomesdk.utils.LogUtil;
import com.haier.smarthomesdk.utils.SharedPreferencesUtil;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitBaseInfo {
    public final static String toke_type = "Bearer";
    public static String access_token;
    private static RetrofitHttpApi api;

    public synchronized static RetrofitHttpApi getApi() {
        access_token = (String) SharedPreferencesUtil.getInstance().get(Constants.ACCESS_TOKEN_LOCATION, "");
        if (api == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(SmartHomeSdk.isProduction ? ApiUrl.BASE_URL : ApiUrl.BASE_API_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .client(getClient())
                    .build();
            api = retrofit.create(RetrofitHttpApi.class);
        }
        return api;
    }

    private static OkHttpClient getClient() {
        Interceptor interceptor = new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request()
                        .newBuilder()
                        .addHeader("Authorization", toke_type + " " + access_token)
                        .addHeader("Content-Type", "application/json")
                        .build();
                return chain.proceed(request);
            }
        };
        Interceptor login_interceptor = new Interceptor() {
            @Override
            public Response intercept(Interceptor.Chain chain) throws IOException {
                Response originalResponse = chain.proceed(chain.request());
                if (originalResponse.code() == 401 || originalResponse.code() == 403) {
                    LoginUtils.getInstance().goToRetrieveNewAccessToken();
                }
                return originalResponse;
            }
        };

        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                LogUtil.d("body", message);
            }
        });

        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.addInterceptor(interceptor);
        builder.addInterceptor(loggingInterceptor);
        builder.addNetworkInterceptor(login_interceptor);

        builder.connectTimeout(15, TimeUnit.SECONDS);
        builder.readTimeout(15, TimeUnit.SECONDS);
        builder.writeTimeout(15, TimeUnit.SECONDS);
        builder.retryOnConnectionFailure(true);
        OkHttpClient client = builder.build();
        return client;
    }

}
