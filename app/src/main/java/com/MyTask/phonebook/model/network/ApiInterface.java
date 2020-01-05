package com.MyTask.phonebook.model.network;


import com.google.gson.JsonArray;

import retrofit2.Call;
import retrofit2.http.GET;


public interface ApiInterface {

    String BaseURl="https://restcountries.eu/rest/v2/";

    @GET("all")
    Call<JsonArray> getCodes();

}
