package com.example.marvelapp.db;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.marvelapp.api.Character;

import java.util.List;

public class myRepository {
    private CharacterDao characterDao;
    private LiveData<List<Character>> allCharacters;

    public myRepository(Application application) {
        myDatabase db = myDatabase.getDatabase(application);
        this.characterDao = db.characterDao();
        this.allCharacters = characterDao.getAll();
    }

    public void insertCharacters(Character... characters){
        new insertAsyncTask(characterDao).execute(characters);
    }

    public LiveData<List<Character>> getAllCharacters() {
        return this.allCharacters;
    }

}


