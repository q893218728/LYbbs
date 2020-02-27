package com.ly.bbs.okhttpclient;

import okhttp3.OkHttpClient;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;
import java.util.concurrent.TimeUnit;

public enum Client{
    INSTANCE;
    private OkHttpClient client;
    private Client(){
        client = new OkHttpClient().newBuilder().hostnameVerifier(new HostnameVerifier() {
            @Override
            public boolean verify(String hostname, SSLSession session) {
                return true;
            }
        }).readTimeout(20, TimeUnit.SECONDS).build();
    }
    public OkHttpClient getInstance() {
        return client;
    }
}
