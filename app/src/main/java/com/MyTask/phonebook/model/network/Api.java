package com.MyTask.phonebook.model.network;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class Api {


    static Gson gson = new GsonBuilder()
            .setLenient()
            .create();
    public static ApiInterface getClient() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiInterface.BaseURl)
                .addConverterFactory(GsonConverterFactory.create(gson))

                .build();
        ApiInterface  api = retrofit.create(ApiInterface.class);

        return api;
    }


}
