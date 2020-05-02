package com.example.bmi_app_v10;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public void button(View V) {
        EditText w = (EditText) findViewById(R.id.weight);
        EditText h = (EditText) findViewById(R.id.height);
        String ws = w.getText().toString();
        String hs = h.getText().toString();
        double wd = Double.parseDouble(ws);
        double hd = Double.parseDouble(hs);
        double res;
        hd = hd * hd;
        res = wd / hd;
        String res_string = String.format("%.2f", res);
        w.onEditorAction(EditorInfo.IME_ACTION_DONE);
        TextView ans = (TextView) findViewById(R.id.result);
        ans.setText(res_string);
        SystemClock.sleep(1000);
        if (res < 18.5) {
            Toast.makeText(this, "YOU ARE UNDERWEIGHT", Toast.LENGTH_LONG).show();
        } else if (res >= 18.5 && res <= 24.9) {
            Toast.makeText(this, "YOU ARE NORMAL", Toast.LENGTH_LONG).show();
        } else if (res >= 25 && res <= 29.9) {
            Toast.makeText(this, "YOU ARE OVERWEIGHT", Toast.LENGTH_LONG).show();
        } else if (res >= 30 && res <= 34.9) {
            Toast.makeText(this, "YOU ARE OBESE(TOO MUCH WEIGHT)", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(this, "YOU ARE HIGHLY OBESE (TOOOO MUCH)", Toast.LENGTH_LONG).show();
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
