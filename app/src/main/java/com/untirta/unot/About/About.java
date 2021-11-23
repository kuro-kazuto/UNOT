package com.untirta.unot.About;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.widget.ImageView;


import androidx.appcompat.app.AppCompatActivity;

import com.untirta.unot.R;

public class About extends AppCompatActivity {
    ImageView dev1, dev2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        dev1 = findViewById(R.id.dev1);
        dev2 = findViewById(R.id.dev2);
        dev1.setOnClickListener(v -> {
            Intent dev1 = new Intent(About.this, Dev1.class);
            startActivity(dev1);
        });
        dev2.setOnClickListener(v -> {
            Intent dev2 = new Intent(About.this, Dev2.class);
            startActivity(dev2);
        });

    }

}