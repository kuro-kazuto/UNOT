package com.untirta.unot;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class DataDiri extends AppCompatActivity {
    Button jumper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datadiri);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        String NIM = getIntent().getStringExtra("name");

        jumper = findViewById(R.id.tombol_sementara);

        jumper.setOnClickListener(v -> {
            Intent intent = new Intent(DataDiri.this, StartQuiz.class);
            startActivity(intent);
        });

    }

    @Override
    public void onBackPressed() {

        return;
    }}