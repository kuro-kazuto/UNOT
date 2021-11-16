package com.untirta.unot.AdminSide;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.untirta.unot.Preview_Create;
import com.untirta.unot.R;
import com.untirta.unot.UserSoal.Model.SoalAModel;

public class Set_Soal extends AppCompatActivity {
    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Soal");
    EditText Question, A, B, C, D, Correct_answer;
    Button btnCreate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_soal);

        Question = findViewById(R.id.soal);
        A = findViewById(R.id.optionA);
        B = findViewById(R.id.optionB);
        C = findViewById(R.id.optionC);
        D = findViewById(R.id.optionD);
        Correct_answer = findViewById(R.id.correctAnswer);
        btnCreate = findViewById(R.id.BuatSoal);


        Btn_Create();

    }

    private void Btn_Create() {
        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Quest = Question.getText().toString().trim();
                String OpA = A.getText().toString();
                String OpB = B.getText().toString();
                String OpC = C.getText().toString();
                String OpD = D.getText().toString();
                Create_Question();
                Intent intent = new Intent(Set_Soal.this, Preview_Create.class);
                intent.putExtra("Question", Quest);
                startActivity(intent);
            }
        });
    }

    private void Create_Question() {
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String Quest = Question.getText().toString().trim();
                String OpA = A.getText().toString().trim();
                String OpB = B.getText().toString().trim();
                String OpC = C.getText().toString().trim();
                String OpD = D.getText().toString().trim();
                String CAnswer = Correct_answer.getText().toString().trim();

                String id = databaseReference.push().getKey();

                SoalAModel user = new SoalAModel(id, Quest, CAnswer, OpA, OpB, OpC,OpD);

                databaseReference.child(user.getQuestion()).setValue(user);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}