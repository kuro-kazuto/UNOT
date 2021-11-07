package com.untirta.unot.UserAdmin;

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
import com.untirta.unot.Adapter_getData;
import com.untirta.unot.R;
import com.untirta.unot.Score;

import java.util.ArrayList;
import java.util.List;

public class MainActivity2 extends AppCompatActivity {

    List<Score> fetchData;
    RecyclerView recyclerView;
    Adapter_getData adapter_getData;
    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Score");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        recyclerView = findViewById(R.id.recview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        fetchData = new ArrayList<>();

        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot:snapshot.getChildren()){
                    Score data = dataSnapshot.getValue(Score.class);
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