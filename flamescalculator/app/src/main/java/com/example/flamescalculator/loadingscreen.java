package com.example.flamescalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import java.lang.Math;
import java.util.Objects;

public class loadingscreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loadingscreen);
        Intent data = getIntent();
        String name1 = Objects.requireNonNull(data.getExtras()).getString("firstname");
        String name2 = Objects.requireNonNull(data.getExtras()).getString("crushname");
        assert name1 != null;
        assert name2 != null;
        name1=name1.toLowerCase();
        name2=name2.toLowerCase();
        char result = flames.calcsum(name1,name2);
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                Intent anscreen;
                switch (result){
                    case 'f':
                              anscreen = new Intent(getApplicationContext(),friends.class);
                              startActivity(anscreen);
                              break;
                    case 'l': anscreen = new Intent(getApplicationContext(),lover.class);
                              startActivity(anscreen);
                              break;
                    case 'a': anscreen = new Intent(getApplicationContext(),affection.class);
                              startActivity(anscreen);
                              break;
                    case 'm': anscreen = new Intent(getApplicationContext(),marriage.class);
                              startActivity(anscreen);
                              break;
                    case 'e': anscreen = new Intent(getApplicationContext(),enemy.class);
                              startActivity(anscreen);
                              break;
                    case 's': anscreen = new Intent(getApplicationContext(),brother.class);
                              startActivity(anscreen);
                              break;
                }
            }
        }, 3000);
    }
}
class flames extends MainActivity{
    public static char calcsum(String a,String b){
        int i,sum=0;
        char answer = 'a';
        String res = "flames",res2;
        int [] ar1 = new int[25];
        int [] ar2 = new int[25];
        char[] per1 =a.toCharArray();
        char[] per2 =b.toCharArray();
        for(i=0;i<a.length();i++)
        {
            ar1[per1[i]-'a']++;

        }
        for(i=0;i<b.length();i++)
        {
            ar2[per2[i]-'a']++;
        }
        for(i=0;i<25;i++)
        {
            ar1[i]=Math.abs(ar1[i]-ar2[i]);
            sum+=ar1[i];
        }
        if(sum<=6){
            switch(sum){
                case 1: answer = 's';
                        break;
                case 2 :
                case 4 :
                    answer = 'e';
                        break;
                case 3 :
                case 5 :
                    answer = 'f';
                        break;
                case 6: answer = 'm';
                        break;
            }
        }
        else {
            int rem,start,end;
            for(int j = 6; j>=2; j--){
                rem=sum%j;
                if(sum%j==0){
                    int len = res.length();
                    res2=res.substring(0,len-1);
                }
                else{
                    res2=res.substring(rem);
                    start=0;
                    end=rem-1;
                    char ch;
                    for(int k =start;k<end;k++)
                    {
                        ch=res.charAt(k);
                        res2=res2+ch;

                    }
                }
                res=res2;
                res2="";
            }
            answer = res.charAt(0);

        }
        return answer;
    }
}