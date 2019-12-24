package com.example.marvelapp;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /** Called after tapping the home button */
    public void goHome(View view){
        Intent intent = new Intent(this, CharactersActivity.class);
        startActivity(intent);

    }
}
