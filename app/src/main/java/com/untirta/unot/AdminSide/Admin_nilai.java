package com.untirta.unot.AdminSide;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.pm.ActivityInfo;
import android.os.Bundle;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.untirta.unot.R;
import com.untirta.unot.Score;
import com.untirta.unot.UserSoal.Model.ModelNilai;

import java.util.ArrayList;
import java.util.List;

public class Admin_nilai extends AppCompatActivity {

    List<ModelNilai> fetchData;
    RecyclerView recyclerView;
    Adapter_getData adapter_getData;
    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Nilai");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adminnilai);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        recyclerView = findViewById(R.id.recview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        fetchData = new ArrayList<>();

        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot:snapshot.getChildren()){
                    ModelNilai data = dataSnapshot.getValue(ModelNilai.class);
                    fetchData.add(data);
                }
                adapter_getData = new Adapter_getData(fetchData);
                recyclerView.setAdapter(adapter_getData);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
}