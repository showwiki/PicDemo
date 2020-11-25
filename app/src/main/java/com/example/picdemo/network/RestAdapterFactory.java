package com.example.picdemo.network;


import android.text.TextUtils;

import com.example.picdemo.Config;
import java.util.concurrent.TimeUnit;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;


public class RestAdapterFactory {
    private static HomeService homeService;

    public static HomeService getHomeService() {
        if (homeService == null) {
            homeService = getRestAdapter().create(HomeService.class);
        }
        return homeService;
    }

    public static Retrofit getRestAdapter() {
        return new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(Config.baseUrl)
                .client(getDiyClient())
                .build();
    }

    public static OkHttpClient getDiyClient() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        return builder
                .connectTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS)
                .sslSocketFactory(HttpsUtils.initSSLSocketFactory(), HttpsUtils.initTrustManager())
                .build();
    }

}
