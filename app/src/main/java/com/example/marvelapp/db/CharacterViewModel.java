package com.example.marvelapp.db;

import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.marvelapp.api.Character;
import com.example.marvelapp.db.myRepository;

import java.util.List;

public class CharacterViewModel extends ViewModel {
    private myRepository repo;
    private LiveData<List<Character>> allCharacters;

    public CharacterViewModel(Application application){
        repo = new myRepository(application);
        allCharacters = repo.getAllCharacters();
    }

    public LiveData<List<Character>> getAllCharacters() {
        return getAllCharacters();
    }

    public void insert(Character character){
        repo.insertCharacters(character);
    }
}
