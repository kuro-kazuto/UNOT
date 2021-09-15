package com.untirta.unot;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Dashboard extends AppCompatActivity {
    Button btnTE, btnM, btnK, btnI, btnMetal, btnS;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        btnI = findViewById(R.id.TI);
        btnK = findViewById(R.id.TK);
        btnM = findViewById(R.id.TM);
        btnMetal = findViewById(R.id.TMetal);
        btnTE = findViewById(R.id.TE);
        btnS = findViewById(R.id.TS);

        String NIM = getIntent().getStringExtra("name");

        btnI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String jurusan = "Teknik Industri";
                Intent intent =new Intent(Dashboard.this, AdminActivity.class);
                intent.putExtra("name", NIM);
                intent.putExtra("pilihan", jurusan);
                startActivity(intent);
            }
        });

        btnK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String jurusan = "Teknik Kimia";
                Intent intent =new Intent(Dashboard.this, AdminActivity.class);
                intent.putExtra("name", NIM);
                intent.putExtra("pilihan", jurusan);
                startActivity(intent);
            }
        });

        btnM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String jurusan = "Teknik Mesin";
                Intent intent =new Intent(Dashboard.this, AdminActivity.class);
                intent.putExtra("name", NIM);
                intent.putExtra("pilihan", jurusan);
                startActivity(intent);
            }
        });

        btnMetal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String jurusan = "Teknik Metalurgi";
                Intent intent =new Intent(Dashboard.this, AdminActivity.class);
                intent.putExtra("name", NIM);
                intent.putExtra("pilihan", jurusan);
                startActivity(intent);
            }
        });

        btnTE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String jurusan = "Teknik Elektro";
                Intent intent =new Intent(Dashboard.this, AdminActivity.class);
                intent.putExtra("name", NIM);
                intent.putExtra("pilihan", jurusan);
                startActivity(intent);
            }
        });

        btnS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String jurusan = "Teknik Sipil";
                Intent intent =new Intent(Dashboard.this, AdminActivity.class);
                intent.putExtra("name", NIM);
                intent.putExtra("pilihan", jurusan);
                startActivity(intent);
            }
        });

    }

    @Override
    public void onBackPressed() {

        return;
    }}