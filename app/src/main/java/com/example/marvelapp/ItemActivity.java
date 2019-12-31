package com.example.marvelapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class ItemActivity extends AppCompatActivity {
    ImageView imageView;
    TextView characterName;
    TextView lastModified;
    TextView characterDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //Find views
        imageView = findViewById(R.id.characterImage);
        characterName = findViewById(R.id.characterName);
        lastModified = findViewById(R.id.lastModified);
        characterDescription = findViewById(R.id.characterDescription);

        //Get intent
        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        String description = intent.getStringExtra("description");
        String modified = intent.getStringExtra("modified");
        String thumbnail = intent.getStringExtra("thumbnail");

        //Set data on activity
        characterName.setText(name);
        lastModified.setText("Last modified: " + modified);
        if(description.isEmpty()){
            characterDescription.setText("No description available");
        }
        else{
            characterDescription.setText(description);
        }

        Glide.with(this)
                .load(thumbnail)
                .into(imageView);
    }
}
