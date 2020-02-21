package com.ly.bbs.okhttpclient;

import okhttp3.OkHttpClient;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;

public enum Client{
    INSTANCE;
    private OkHttpClient client;
    private Client(){
        client = new OkHttpClient().newBuilder().hostnameVerifier(new HostnameVerifier() {
            @Override
            public boolean verify(String hostname, SSLSession session) {
                return true;
            }
        }).build();
    }
    public OkHttpClient getInstance() {
        return client;
    }
}
