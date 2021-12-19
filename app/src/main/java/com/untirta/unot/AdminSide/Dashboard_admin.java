package com.untirta.unot.AdminSide;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.untirta.unot.R;

public class Dashboard_admin extends AppCompatActivity {

    ImageView btnRemote, btnNilai, btnBuatSoal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard_admin);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        btnNilai = findViewById(R.id.btn_nilai);
        btnRemote = findViewById(R.id.btn_remote);
        btnBuatSoal = findViewById(R.id.btn_soal);
        BtnNilai();
        BtnRemote();
        BtnBuatSoal();
    }

    private void BtnBuatSoal() {
        btnBuatSoal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Dashboard_admin.this, Admin_CreateQuestion.class);
                startActivity(intent);
            }
        });
    }

    private void BtnNilai(){
        btnNilai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Dashboard_admin.this, Admin_nilai.class);
                startActivity(intent);
            }
        });
    }

    private void BtnRemote(){
        btnRemote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Dashboard_admin.this, Admin_Remote.class);
                startActivity(intent);
            }
        });
    }
}