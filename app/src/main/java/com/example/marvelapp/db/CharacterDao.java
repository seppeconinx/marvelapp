/*package com.example.marvelapp.db;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.marvelapp.api.Character;

import java.util.List;

@Dao
public interface CharacterDao {

    @Insert
    void insert(Character... character);

    @Insert
    int insert(Character character);

    @Insert
    void insertList(List<Character> characterList);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void upsert(Character... characters);

    @Query("SELECT * FROM character_table")
    LiveData<List<Character>> getAll();

    @Query("DELETE FROM character_table")
    void deleteAll();

    @Query("SELECT * FROM character_table ORDER BY name ASC")
    LiveData<List<Character>> getAllOrderedyName();
}*/
