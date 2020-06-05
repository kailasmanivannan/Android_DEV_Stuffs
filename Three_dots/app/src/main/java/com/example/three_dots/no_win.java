package com.example.three_dots;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;

public class no_win extends AppCompatActivity {

    MediaPlayer bs;

    public void playagain(View v){
        bs.start();
        Intent a = new Intent(getApplicationContext(),gameactivity.class);
        startActivity(a);
    }

    public void exitgame(View v){
        bs.start();
        finishAffinity();
        System.exit(0);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_no_win);
        MediaPlayer sound = MediaPlayer.create(this,R.raw.sodhani);
        sound.start();
        bs = MediaPlayer.create(this,R.raw.button);
    }
}
