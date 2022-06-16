package com.gzeinnumer.b5d8.data;

import android.content.Context;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetroServerDemo {
    //private static final String base_url = "http://10.0.2.2/retrofit/";
    private static final String base_url = "https://demo-laravel.gzeinnumer.com/api/";

    private static Retrofit setInit(Context applicationContext){
        OkHttpClient client = new TokenInterceptor().getClient(applicationContext);
        return new Retrofit.Builder()
                .baseUrl(base_url)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();
    }

    public static ApiRequestDemo getInstance(Context applicationContext){
        return setInit(applicationContext).create(ApiRequestDemo.class);
    }
}
