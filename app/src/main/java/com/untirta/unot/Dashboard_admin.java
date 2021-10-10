package com.untirta.unot;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class Dashboard_admin extends AppCompatActivity {

    Button btnRemote, btnNilai;

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
                Intent intent = new Intent(Dashboard_admin.this, Listening_direction.class);
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