package com.untirta.unot;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Activation extends AppCompatActivity {

    DatabaseReference databaseReference;
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activation);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        databaseReference = FirebaseDatabase.getInstance().getReference("AdminAktifasi");

        tv = findViewById(R.id.tvActivation);

        String AktifasiAdmin = "Admin";

        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.child(AktifasiAdmin).exists()) {
                    Admin_control value = snapshot.child(AktifasiAdmin).getValue(Admin_control.class);
                    if (value.getValueA().equals("On")){
                        tv.setText("The Aplication Is ON Now");
                    }else {
                        tv.setText("The Aplication Is OFF Now");
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
}