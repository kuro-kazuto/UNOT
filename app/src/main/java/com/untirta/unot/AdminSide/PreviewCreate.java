package com.untirta.unot.AdminSide;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.untirta.unot.R;
import com.untirta.unot.UserSoal.Model.SoalAModel;

public class PreviewCreate extends AppCompatActivity {

    TextView pque, po1, po2, po3, po4, pca;
    Button btnCreateQ;
    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Soal");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preview_create);

        pque = findViewById(R.id.previewSoal);
        po1 = findViewById(R.id.prevo1);
        po2 = findViewById(R.id.prevo2);
        po3 = findViewById(R.id.prevo3);
        po4 = findViewById(R.id.prevo4);
        pca = findViewById(R.id.prevCA);

        btnCreateQ = findViewById(R.id.storeDb);



        pque.setText(getIntent().getStringExtra("nosoal") + "." + getIntent().getStringExtra("que"));
        po1.setText(getIntent().getStringExtra("o1"));
        po2.setText(getIntent().getStringExtra("o2"));
        po3.setText(getIntent().getStringExtra("o3"));
        po4.setText(getIntent().getStringExtra("o4"));
        pca.setText(getIntent().getStringExtra("ca"));

        btnCreateQ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                String PNoSoal = getIntent().getStringExtra("nosoal");
                String q = pque.getText().toString().trim();
                String o1 = po1.getText().toString().trim();
                String o2 = po2.getText().toString().trim();
                String o3 = po3.getText().toString().trim();
                String o4 = po4.getText().toString().trim();
                String ca = pca.getText().toString().trim();

                String id = databaseReference.push().getKey();

                SoalAModel soalAModel = new SoalAModel(id, PNoSoal,q, ca, o1, o2, o3, o4);

                databaseReference.child(soalAModel.getNo()).setValue(soalAModel);

                Intent intent = new Intent(PreviewCreate.this, Dashboard_admin.class);
                startActivity(intent);


            }
        });
    }
}