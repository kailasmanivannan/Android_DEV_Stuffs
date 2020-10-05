package com.example.flamescalculator;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import java.io.IOException;


public class MainActivity extends AppCompatActivity {
    //local variables
    public boolean status,st1,st2 ;
    public String fname,cname,username;
    MediaPlayer sound;
    //firebase
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference ref;

    public void field1(View v){sound.start();}
    public void field2(View v){sound.start();}

    public void start(View v){
        sound.start();
        status = checkconnect.check(this);
        if (status) {
            TextView name1 = (TextView) findViewById(R.id.firstname);
            TextView name2 = (TextView) findViewById(R.id.crushname);
            fname = name1.getText().toString();
            cname = name2.getText().toString();
            st1=checkconnect.stringcheck(fname);
            st2=checkconnect.stringcheck(cname);
            username = Build.MODEL;
            if(st1&&st2){
                ref = database.getReference("users").push();
                ref.child("firstname").setValue(fname);
                ref.child("crushname").setValue(cname);
                ref.child("device name").setValue(username);
                Intent next = new Intent(getApplicationContext(),loadingscreen.class);
                next.putExtra("firstname",fname);
                next.putExtra("crushname",cname);
                startActivity(next);

            }
            else{
                new AlertDialog.Builder(this)
                        .setTitle("Input Error")
                        .setCancelable(false)
                        .setMessage("Only alphabets are allowed.Type name without spaces.")
                        .setNegativeButton("ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        })
                        .create().show();
            }

        } else {
            new AlertDialog.Builder(this)
                    .setTitle("Network Error")
                    .setCancelable(false)
                    .setMessage("Cannot Load Resources." + "Connect To Network And Try Again")
                    .setNegativeButton("ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    })
                    .create().show();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sound=MediaPlayer.create(this,R.raw.button);
    }


}
 class checkconnect extends AppCompatActivity{
    public static Boolean isConnected = false, isWiFi = false, isMobile = false, result = false;
     
    @NotNull
    public static boolean check(MainActivity main) {
        ConnectivityManager cm = (ConnectivityManager) main.getSystemService(Context.CONNECTIVITY_SERVICE);
        assert cm != null;
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        if (activeNetwork != null) {
            isWiFi = activeNetwork.getType() == ConnectivityManager.TYPE_WIFI;
            isMobile = activeNetwork.getType() == ConnectivityManager.TYPE_MOBILE;
            isConnected = activeNetwork.isConnectedOrConnecting();
        }
        if (isConnected) {
            if (isWiFi) {
                if (isConnectedToThisServer("www.google.com ")) {
                    result =true;
                } else {
                    result = false;
                }
            }
            if (isMobile) {
                if (isConnectedToThisServer("www.google.com")) {
                    result =true;
                } else {
                    result = false;
                }
            }
        } else {
            result = false;
        }
        return result;
    }
     
    @NotNull
    public static boolean isConnectedToThisServer(String host) {
        Runtime runtime = Runtime.getRuntime();
        try {
            Process ipProcess = runtime.exec("/system/bin/ping -c 1 " + host);
            int exitValue = ipProcess.waitFor();
            return (exitValue == 0);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return false;
    }
     
    @NotNull
    public static boolean stringcheck(String str){
        return ((str != null)
                && (!str.equals(""))
                && (str.matches("^[a-zA-Z]*$")));
    }

}


