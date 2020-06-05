package com.example.three_dots;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class gameactivity extends AppCompatActivity {
    MediaPlayer bs;
    int[] gamestate = {2,2,2,2,2,2,2,2,2};
    int[][] winingpositions = {{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};
    // 0 : yellow  ; 1 : red
    boolean gameactive=true;
    int activeplayer = 0 ;
    int count =0;
    public void dropin(View v) {
        bs.start();
        ImageView counter = (ImageView) v;
        int tappedcounter = Integer.parseInt(counter.getTag().toString());
        if (gamestate[tappedcounter] == 2 && gameactive ) {
            gamestate[tappedcounter] = activeplayer;
            counter.setTranslationY(-2500);
            if (activeplayer == 0) {
                counter.setImageResource(R.drawable.yellow);
                activeplayer = 1;
                count++;
            } else {
                counter.setImageResource(R.drawable.red);
                activeplayer = 0;
                count++;
            }
            counter.animate().translationYBy(2500).rotation(3000).setDuration(300);
            for(int[] winningpositions : winingpositions){
                if(gamestate[winningpositions[0]] == gamestate[winningpositions[1]] && gamestate[winningpositions[1]] == gamestate[winningpositions[2]] && gamestate[winningpositions[0]]!= 2)
                {
                    if(activeplayer==1){
                        // call yellow intent
                        Intent a = new Intent(getApplicationContext(),yellow_win.class);
                        startActivity(a);
                    }
                    else{
                        // call red intent
                        Intent b = new Intent(getApplicationContext(),red_win.class);
                        startActivity(b);
                    }

                }
            }
            if(count==9){
                //no win call
                Intent n = new Intent(getApplicationContext(),no_win.class);
                startActivity(n);
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gameactivity);
        bs = MediaPlayer.create(this,R.raw.button);
    }
}
