package com.example.marvelapp.api;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CharacterResults {

    @SerializedName("results")
    private List<Character> characters;

    public List<Character> getCharacters() {
        return characters;
    }

    public void setCharacters(List<Character> characters) {
        this.characters = characters;
    }
}
