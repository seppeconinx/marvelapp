package com.example.marvelapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.marvelapp.api.API;
import com.example.marvelapp.api.Character;
import com.example.marvelapp.api.CharacterData;
import com.example.marvelapp.api.CharacterResults;
import com.example.marvelapp.api.Thumbnail;
import com.example.marvelapp.db.dbCharacter;
import com.example.marvelapp.db.myDatabase;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class CharactersActivity extends AppCompatActivity {

    private GridView gridView;
    private CustomAdapter customAdapter;
    public static List<Character> characters;
    public static myDatabase db;

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_characters);
        gridView = findViewById(R.id.gridView);

        db = Room.databaseBuilder(this.getApplicationContext(),
                myDatabase.class,
                "character_database")
                .allowMainThreadQueries()
                .build();

        makeApiCall();
    }

    public void makeApiCall(){
        characters = new ArrayList<>();
        Call<CharacterData> call = API.apiInterfance().getCharacters("characters?ts=1577638249838&apikey=6e1818d5f229df319d2c672e89a21e67&hash=3dc32c375328875f95351f0b73d2a334&limit=50&offset=300");

        call.enqueue(new Callback<CharacterData>() {
            @Override
            public void onResponse(Call<CharacterData> call, Response<CharacterData> response) {
                if(response.isSuccessful()) {
                    CharacterData characterData = response.body();
                    CharacterResults characterResults = characterData.getCharacterResults();
                    List<Character> characterList = characterResults.getCharacters();


                    //Add data to local database
                    for(Character c : characterList){
                        dbCharacter dbChar = new dbCharacter();
                        dbChar.setName(c.getName());
                        dbChar.setDescription(c.getDescription());
                        dbChar.setModified(c.getModified());
                        db.characterDao().insert(dbChar);
                    }

                    characters = characterList;
                    customAdapter = new CustomAdapter(characterList, CharactersActivity.this);
                    gridView.setAdapter(customAdapter);

                    gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            Intent intent = new Intent();

                            //intent.putExtra("name", characters.get(position).getName());
                            //intent.putExtra("modified", characters.get(position).getModified());
                            //intent.putExtra("description", characters.get(position).getDescription());

                            Thumbnail charThumbnail = characters.get(position).getThumbnail();
                            String thumbnail = charThumbnail.getPath() + "." + charThumbnail.getExtension();
                            //intent.putExtra("thumbnail", thumbnail);

                            startActivity(new Intent(getApplicationContext(), ItemActivity.class).putExtra("name", characters.get(position).getName()).putExtra("modified", characters.get(position).getModified()).putExtra("description", characters.get(position).getDescription()).putExtra("thumbnail", thumbnail));
                        }
                    });
                }
                else{
                    Toast.makeText(getApplicationContext(), "An error occured", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<CharacterData> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "An error occured:" + t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public class CustomAdapter extends BaseAdapter {

        public List<Character> characterList;
        public Context context;

        public CustomAdapter(List<Character> characterList, Context context) {
            this.characterList = characterList;
            this.context = context;
        }

        @Override
        public int getCount() {
            return characterList.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            View view = LayoutInflater.from(context).inflate(R.layout.fragment_character, null);

            TextView name = view.findViewById(R.id.textView);
            ImageView image = view.findViewById(R.id.imageView);

            name.setText(characterList.get(position).getName());
            Thumbnail charthumbnail = characterList.get(position).getThumbnail();
            String charImage = charthumbnail.getPath() + "." + charthumbnail.getExtension();

            Glide.with(context)
                    .load(charImage)
                    .into(image);

            return view;
        }
    }

    public static void main(String[] args) {

    }
}