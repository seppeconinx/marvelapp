package com.example.marvelapp.db;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;

import java.util.List;

@Dao
public interface CharacterDao {
    @Insert
    long insert(dbCharacter character);
}
