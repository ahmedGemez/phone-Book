package com.MyTask.phonebook.model.network;

import android.util.Log;

import com.MyTask.phonebook.Repository.CodeRepository;
import com.MyTask.phonebook.model.CodeModel;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.util.concurrent.ExecutionException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class downloader {



    public void downloadcodes(CodeRepository repository){


        ApiInterface api = Api.getClient();
        Call<JsonArray> call = api.getCodes();
        call.enqueue(new Callback<JsonArray>() {
            @Override public void onResponse(Call<JsonArray> call, Response<JsonArray> response) {
                JsonArray apiResponse = response.body();
                if(apiResponse != null) {
                    for (int i = 0; i <apiResponse.size() ; i++) {
                        JsonObject jsonObject=new JsonObject();
                        jsonObject= (JsonObject)apiResponse.get(i);
                        String name = jsonObject.get("name").getAsString();
                        String capital = jsonObject.get("capital").getAsString();

                        JsonArray codesArr=(JsonArray)jsonObject.get("callingCodes");
                        String code = codesArr.get(0).getAsString();;

                        Log.d("asacasc",name);
                        Log.d("codecode",code);


                        CodeModel codeModel=new CodeModel(code,name,capital);

                        repository.insertCode(codeModel);

                    }
                }

            }
            @Override public void onFailure(Call<JsonArray> call, Throwable t) {
                String errorMessage = t == null ? "unknown error" : t.getMessage();
            }
        });
    }




}
