package com.untirta.unot;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {
    TextView tvdepartment;

    Button btnstart;
    ImageButton btnAbout;
    ImageButton btnHelp;
    EditText editText;
    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("NIM");
    DatabaseReference dbAdmin = FirebaseDatabase.getInstance().getReference().child("AdminAktifasi");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);



        btnstart = findViewById(R.id.btnstart);
        btnAbout = findViewById(R.id.about);
        btnHelp = findViewById(R.id.help);
        editText = findViewById(R.id.edit);
        tvdepartment = findViewById(R.id.pilihan);

        tvdepartment.setText(getIntent().getStringExtra("pilihan"));

        //int varInteger = Integer.parseInt(String.valueOf(editText));

        btnAbout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent about = new Intent(MainActivity.this, Help.class);
                startActivity(about);
            }
        });

        btnHelp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent help = new Intent(MainActivity.this, Help.class);
                startActivity(help);
            }
        });

        btnstart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String NIM = editText.getText().toString();

                if(NIM.length()==10){
                    AddNIM();
                }else {
                    Toast.makeText(MainActivity.this, "NIM Must Be 10 Digit", Toast.LENGTH_SHORT).show();
                }



            }
        });

    }

    @Override
    public void onBackPressed() {

        return;
    }

    private void AddNIM(){
        String name = editText.getText().toString();
        String pilihan = tvdepartment.getText().toString();
        Intent intent = new Intent(MainActivity.this, Listening_direction.class);
        intent.putExtra("pilihan", pilihan);
        intent.putExtra("name", name);
        Intent intent1 = new Intent(MainActivity.this, Blank404.class);


        if(!TextUtils.isEmpty(name)){

            String AktifasiAdmin = "Admin";

            databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if(snapshot.child(name).exists()) {
                        NIM value = snapshot.child(name).getValue(NIM.class);

                        if (value.getNIM().equals(name)) {
                            Toast.makeText(MainActivity.this, "NIM Alredy Exist", Toast.LENGTH_SHORT).show();
                        }
                    }else {
                        dbAdmin.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                String On = "On";
                                String Off = "Off";
                                if (snapshot.child(AktifasiAdmin).exists()){
                                    Admin_control value = snapshot.child(AktifasiAdmin).getValue(Admin_control.class);
                                    String ValueA = value.getValueA();
                                    if(ValueA.equals(On)){
                                        startActivity(intent);
                                    }
                                    if(ValueA.equals(Off)){
                                        startActivity(intent1);
                                    }
                                }
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        });
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });

        }else{
            Toast.makeText(this, "You Should Enter NIM", Toast.LENGTH_SHORT).show();
        }
    }
}