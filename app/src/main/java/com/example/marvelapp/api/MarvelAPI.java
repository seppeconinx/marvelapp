package com.example.marvelapp.api;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface MarvelAPI {

    @GET
    Call<CharacterData> getCharacters(@Url String url);
}
