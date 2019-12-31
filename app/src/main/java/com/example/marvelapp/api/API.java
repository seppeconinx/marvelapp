package com.example.marvelapp.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class API {

    public static String BASE_URL = "http://gateway.marvel.com/v1/public/";


    public static Retrofit getClient(){

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit;
    }

    public static MarvelAPI apiInterfance(){
        return getClient().create(MarvelAPI.class);
    }
}
