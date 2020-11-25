package com.example.picdemo.network

import com.example.picdemo.Config
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetroFitFactory {

    val homeService: HomeService by lazy { restAdapter.create(
        HomeService::class.java
    ) }


    val restAdapter: Retrofit
        get() = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .baseUrl(Config.baseUrl)
            .client(diyClient)
            .build()

    val diyClient: OkHttpClient
        get() {
            val builder = OkHttpClient.Builder()
            return builder
                .connectTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS)
                .sslSocketFactory(HttpsUtils.initSSLSocketFactory(), HttpsUtils.initTrustManager())
                .build()
        }
}