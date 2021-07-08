package com.example.boredapi.remote;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitBuilder {

    private RetrofitBuilder(){}

    private static BoredApi instance;

    public static BoredApi getInstance(){
        if (instance == null){
            instance = creatRetrofit();
        }
        return instance;
    }

    private static BoredApi creatRetrofit() {
        return new Retrofit.Builder()
                .baseUrl("https://www.boredapi.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(BoredApi.class);
    }


}
