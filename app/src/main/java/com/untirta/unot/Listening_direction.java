package com.untirta.unot;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class Listening_direction extends AppCompatActivity {
    TextView jajal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listening_direction);
        jajal = findViewById(R.id.test);
        jajal.setText("Ganyu Wangy");

    }
}