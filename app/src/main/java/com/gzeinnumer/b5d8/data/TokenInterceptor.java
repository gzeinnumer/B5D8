package com.gzeinnumer.b5d8.data;

import android.content.Context;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;

public class TokenInterceptor {

    public TokenInterceptor() {
    }

    public OkHttpClient getClient(Context applicationContext){
        //Session = new contex
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        return new OkHttpClient
                .Builder()
                .addInterceptor(interceptor)
                .addInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        Request request = chain.request().newBuilder()
                                .addHeader("Accept", "application/json")
                                .addHeader("Authorization", "Bearer Token")
                                .build();
                        return chain.proceed(request);
                    }
                })
                .readTimeout(90, TimeUnit.SECONDS)
                .writeTimeout(90, TimeUnit.SECONDS)
                .connectTimeout(90, TimeUnit.SECONDS)
                .retryOnConnectionFailure(false)
                .cache(null)
                .build();
    }
}