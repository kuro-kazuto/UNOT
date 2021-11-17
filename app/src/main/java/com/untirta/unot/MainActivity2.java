package com.untirta.unot;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity2 extends AppCompatActivity {
    Button TA, siakad, spada, wisuda, eadmin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        TA = findViewById(R.id.tugasakhir);
        siakad = findViewById(R.id.siakad);
        spada = findViewById(R.id.spada);
        eadmin = findViewById(R.id.eadmin);
        wisuda = findViewById(R.id.wisuda);

        TA.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity2.this, TugasAkhirActivity.class);
            startActivity(intent);
        });
        eadmin.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity2.this, EadministrasiActivity.class);
            startActivity(intent);
        });
        spada.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity2.this, SpadaActivity.class);
            startActivity(intent);
        });
        siakad.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity2.this, SiakadActivity.class);
            startActivity(intent);
        });
        wisuda.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity2.this, WisudaActivity.class);
            startActivity(intent);
        });
    }
}