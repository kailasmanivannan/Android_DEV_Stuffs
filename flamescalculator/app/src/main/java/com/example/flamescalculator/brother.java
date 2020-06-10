package com.example.flamescalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;

public class brother extends AppCompatActivity {

    MediaPlayer sound;

    public void startagain(View v){
        sound.start();
        Intent var = new Intent(getApplicationContext(),MainActivity.class);
        startActivity(var);
    }
    public void goaway(View v){
        sound.start();
        finishAffinity();
        System.exit(0);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_brother);
        sound = MediaPlayer.create(this,R.raw.button);
    }
}