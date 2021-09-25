package com.untirta.unot;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Admin_Remote extends AppCompatActivity {

    DatabaseReference databaseReference;
    Button btnAdminOn, btnAdminOff;
    TextView textAdmin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_remote);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        databaseReference = FirebaseDatabase.getInstance().getReference("AdminAktifasi");
        btnAdminOn = findViewById(R.id.tmbl);
        btnAdminOff = findViewById(R.id.tmbl2);
        textAdmin = findViewById(R.id.teks);
        String AktifasiAdmin = "Admin";

        btnAdminOn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Aktifasi = "Admin";
                String ValueA = "On";
                String id = databaseReference.push().getKey();

                Admin_control user = new Admin_control(id, Aktifasi, ValueA);

                databaseReference.child(user.getAktifasi()).setValue(user);

                Intent intent = new Intent(Admin_Remote.this, Activation.class);
                startActivity(intent);
            }
        });

        btnAdminOff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Aktifasi = "Admin";
                String ValueA = "Off";
                String id = databaseReference.push().getKey();

                Admin_control user = new Admin_control(id, Aktifasi, ValueA);

                databaseReference.child(user.getAktifasi()).setValue(user);

                Intent intent = new Intent(Admin_Remote.this, Activation.class);
                startActivity(intent);
            }
        });


        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.child(AktifasiAdmin).exists()) {
                    Admin_control value = snapshot.child(AktifasiAdmin).getValue(Admin_control.class);
                    textAdmin.setText(value.getValueA());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });




    }
}