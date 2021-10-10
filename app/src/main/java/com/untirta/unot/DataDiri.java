package com.untirta.unot;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class DataDiri extends AppCompatActivity {
    Button btnTE, btnM, btnK, btnI, btnMetal, btnS, dj;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datadiri);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        dj = findViewById(R.id.dialogjurusan);
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
                Intent intent =new Intent(DataDiri.this, AdminActivity.class);
                intent.putExtra("name", NIM);
                intent.putExtra("pilihan", jurusan);
                startActivity(intent);
            }
        });

        btnK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String jurusan = "Teknik Kimia";
                Intent intent =new Intent(DataDiri.this, AdminActivity.class);
                intent.putExtra("name", NIM);
                intent.putExtra("pilihan", jurusan);
                startActivity(intent);
            }
        });

        btnM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String jurusan = "Teknik Mesin";
                Intent intent =new Intent(DataDiri.this, AdminActivity.class);
                intent.putExtra("name", NIM);
                intent.putExtra("pilihan", jurusan);
                startActivity(intent);
            }
        });

        btnMetal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String jurusan = "Teknik Metalurgi";
                Intent intent =new Intent(DataDiri.this, AdminActivity.class);
                intent.putExtra("name", NIM);
                intent.putExtra("pilihan", jurusan);
                startActivity(intent);
            }
        });

        btnTE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String jurusan = "Teknik Elektro";
                Intent intent =new Intent(DataDiri.this, AdminActivity.class);
                intent.putExtra("name", NIM);
                intent.putExtra("pilihan", jurusan);
                startActivity(intent);
            }
        });

        btnS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String jurusan = "Teknik Sipil";
                Intent intent =new Intent(DataDiri.this, AdminActivity.class);
                intent.putExtra("name", NIM);
                intent.putExtra("pilihan", jurusan);
                startActivity(intent);
            }
        });

        dj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog dialog = new Dialog(DataDiri.this);

                //Memasang Title / Judul pada Custom Dialog
                dialog.setTitle("Reading Text");

                //Memasang Desain Layout untuk Custom Dialog
                dialog.setContentView(R.layout.dialog_jurusan);

                Button JTE = dialog.findViewById(R.id.TE);
                Button JTM = dialog.findViewById(R.id.TM);
                Button JTS = dialog.findViewById(R.id.TS);
                Button JTK = dialog.findViewById(R.id.TK);
                Button JTMET = dialog.findViewById(R.id.TMetal);
                Button JTI = dialog.findViewById(R.id.TI);

                JTE.setOnClickListener(v1 -> {
                    dialog.dismiss();
                    String jurusan = "Teknik Elektro";
                    Intent intent =new Intent(DataDiri.this, AdminActivity.class);
                    intent.putExtra("name", NIM);
                    intent.putExtra("pilihan", jurusan);
                    startActivity(intent);
                });
                JTM.setOnClickListener(v12 -> {
                    dialog.dismiss();
                    String jurusan = "Teknik Mesin";
                    Intent intent =new Intent(DataDiri.this, AdminActivity.class);
                    intent.putExtra("name", NIM);
                    intent.putExtra("pilihan", jurusan);
                    startActivity(intent);
                });
                JTS.setOnClickListener(v13 -> {
                    dialog.dismiss();
                    String jurusan = "Teknik Sipil";
                    Intent intent =new Intent(DataDiri.this, AdminActivity.class);
                    intent.putExtra("name", NIM);
                    intent.putExtra("pilihan", jurusan);
                    startActivity(intent);
                });
                JTK.setOnClickListener(v14 -> {
                    dialog.dismiss();
                    String jurusan = "Teknik Kimia";
                    Intent intent =new Intent(DataDiri.this, AdminActivity.class);
                    intent.putExtra("name", NIM);
                    intent.putExtra("pilihan", jurusan);
                    startActivity(intent);
                });
                JTMET.setOnClickListener(v15 -> {
                    dialog.dismiss();
                    String jurusan = "Teknik Metalurgi";
                    Intent intent =new Intent(DataDiri.this, AdminActivity.class);
                    intent.putExtra("name", NIM);
                    intent.putExtra("pilihan", jurusan);
                    startActivity(intent);
                });
                JTI.setOnClickListener(v15 -> {
                    dialog.dismiss();
                    String jurusan = "Teknik Industri";
                    Intent intent =new Intent(DataDiri.this, AdminActivity.class);
                    intent.putExtra("name", NIM);
                    intent.putExtra("pilihan", jurusan);
                    startActivity(intent);
                });



                dialog.show();
            }
        });

    }

    @Override
    public void onBackPressed() {

        return;
    }}