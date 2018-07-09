package com.unistrong.requestlibs.request;

import android.util.Log;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.Request;

/**
 * 请求策略
 */
public class BasePolicy {
    private static final String TAG = "BasePolicy";
    protected static final int DEF_TIMEOUT = 6 * 1000;

    private static OkHttpClient.Builder builder;

    private static String appInfoString;
    private static String token;

    static {
        builder = new OkHttpClient.Builder();
    }

    protected void setConnectTimeout(int timeout) {
        builder.connectTimeout(timeout, TimeUnit.MILLISECONDS);
        builder.readTimeout(10 * 1000, TimeUnit.MILLISECONDS);
        builder.writeTimeout(10 * 1000, TimeUnit.MILLISECONDS);
    }

    protected OkHttpClient newClient() {
        return builder.build();
    }

    protected void richAppInfo(Request.Builder builder) {
        Log.d(TAG, appInfoString);
        builder.addHeader("appInfo", appInfoString);
    }

    protected void richToken(Request.Builder builder) {
        builder.addHeader("token", token);
    }

    public static void setAppInfoString(String appInfoString) {
        BasePolicy.appInfoString = appInfoString;
    }

    public static void setToken(String token) {
        BasePolicy.token = token;
    }

}
