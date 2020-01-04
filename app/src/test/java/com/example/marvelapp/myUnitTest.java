package com.example.marvelapp;

import com.example.marvelapp.api.API;
import com.example.marvelapp.api.Character;
import com.example.marvelapp.api.CharacterData;


import org.junit.Test;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static org.junit.Assert.assertFalse;

public class myUnitTest {

    /** Test if API call response list is not empty*/
    @Test
    public void testReturnedApiList(){
        Call<CharacterData> call = API.apiInterfance().getCharacters("characters?ts=1577638249838&apikey=6e1818d5f229df319d2c672e89a21e67&hash=3dc32c375328875f95351f0b73d2a334&limit=50&offset=300");

        call.enqueue(new Callback<CharacterData>() {
            @Override
            public void onResponse(Call<CharacterData> call, Response<CharacterData> response) {

                List<Character> characters = response.body().getCharacterResults().getCharacters();

                /** Test if empty */
                assertFalse(characters.isEmpty());
            }

            @Override
            public void onFailure(Call<CharacterData> call, Throwable t) {
                //
            }
        });
    }
}
