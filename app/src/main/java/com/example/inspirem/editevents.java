package com.example.inspirem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

public class editevents extends AppCompatActivity {
    TextView email;
    Spinner spinner;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editevents);
        email=findViewById(R.id.email);

        SharedPreferences bb = getSharedPreferences("my_prefs", 0);
        String m = bb.getString("email", "");

        email.setText(m);


    }
}
