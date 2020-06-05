package com.example.three_dots;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    MediaPlayer bs ;
    public void end(View v)
    {
        bs.start();
        finish();
        System.exit(0);
    }
    public void next(View v)
    {
        bs.start();
        Intent a = new Intent(getApplicationContext(),gameactivity.class);
        startActivity(a);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
         bs = MediaPlayer.create(this,R.raw.button);
    }
}
