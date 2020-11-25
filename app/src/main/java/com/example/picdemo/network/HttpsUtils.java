package com.example.picdemo.network;


import android.annotation.SuppressLint;

import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

/**
 * Created by 逢朋 on 2017/9/8.
 */

public class HttpsUtils {



    private static X509TrustManager chooseTrustManager(TrustManager[] trustManagers) {
        for (TrustManager trustManager : trustManagers) {
            if (trustManager instanceof X509TrustManager) {
                return (X509TrustManager) trustManager;
            }
        }
        return null;
    }



    /**
     * 适配一切证书
     *
     * @return
     */
    public static SSLSocketFactory initSSLSocketFactory() {
        try {
            SSLContext sslContext = SSLContext.getInstance("TLSv1.2");
            X509TrustManager[] xTrustArray = new X509TrustManager[]
                    {initTrustManager()};
            sslContext.init(null,
                    xTrustArray, new SecureRandom());
            return sslContext.getSocketFactory();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static X509TrustManager initTrustManager() {
        X509TrustManager mTrustManager = new X509TrustManager() {
            @Override
            public X509Certificate[] getAcceptedIssuers() {
                //此处这句话的作用，是返回一个根证书，因为所有的可靠的证书，必然是根证书机构颁发的
                return new X509Certificate[]{};
            }

            //检查服务器证书
            @Override
            public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
                //如果不是测试环境， 检查服务器返回的证书的可靠性，

            }

            @SuppressLint("TrustAllX509TrustManager")
            @Override
            public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
            }
        };
        return mTrustManager;
    }

}