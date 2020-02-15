package com.ECatering.e_catering.Server;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
public class InitRetrofit {

    public static final String BASE_URL = "http://192.168.21.190/E-Catering/Api_E-Catering/";
    private static InitRetrofit mInstance;
    private Retrofit retrofit;

    private InitRetrofit(){
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static synchronized InitRetrofit getInstance(){
        if (mInstance == null ){
            mInstance = new InitRetrofit();
        }
        return mInstance;
    }

    public ApiServices getApi(){
        return retrofit.create(ApiServices.class);
    }
}