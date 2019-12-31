package com.example.marvelapp.api;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CharacterData {

    @SerializedName("data")
    private CharacterResults characterResults;

    public CharacterResults getCharacterResults() {
        return characterResults;
    }

    public void setCharacterResults(CharacterResults characterResults) {
        this.characterResults = characterResults;
    }
}
