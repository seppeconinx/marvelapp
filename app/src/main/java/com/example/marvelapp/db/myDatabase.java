package com.example.marvelapp.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {dbCharacter.class}, version = 1)
public abstract class myDatabase extends RoomDatabase {
    public abstract CharacterDao characterDao();

    public static myDatabase getDatabase(final Context context){
        return Room.databaseBuilder(context.getApplicationContext(),
                myDatabase.class,
                "character_database")
                .build();
    }
}
