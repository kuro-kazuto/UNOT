package com.untirta.unot;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.untirta.unot.UserAdmin.Admin_nilai;

public class Dashboard_admin extends AppCompatActivity {

    ImageView btnRemote, btnNilai;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard_admin);

        btnNilai = findViewById(R.id.btn_nilai);
        btnRemote = findViewById(R.id.btn_remote);
        BtnNilai();
        BtnRemote();
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