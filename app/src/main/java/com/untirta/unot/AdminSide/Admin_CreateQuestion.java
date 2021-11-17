package com.untirta.unot.AdminSide;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.untirta.unot.R;

public class Admin_CreateQuestion extends AppCompatActivity {

    EditText Q, O1, O2, O3, O4, CA;
    Button btnCreate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_create_question);

        Q = findViewById(R.id.inputSoal);
        O1 = findViewById(R.id.option1);
        O2 = findViewById(R.id.option2);
        O3 = findViewById(R.id.option3);
        O4 = findViewById(R.id.option4);
        CA = findViewById(R.id.jawabanBenar);

        btnCreate = findViewById(R.id.createQ);



        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Que = Q.getText().toString();
                String o1 = O1.getText().toString();
                String o2 = O2.getText().toString();
                String o3 = O3.getText().toString();
                String o4 = O4.getText().toString();
                String ca = CA.getText().toString();
                Intent intent = new Intent(Admin_CreateQuestion.this, PreviewCreate.class);
                intent.putExtra("que", Que);
                intent.putExtra("o1", o1);
                intent.putExtra("o2", o2);
                intent.putExtra("o3", o3);
                intent.putExtra("o4", o4);
                intent.putExtra("ca", ca);
                startActivity(intent);
            }
        });


    }
}