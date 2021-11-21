package com.untirta.unot.UserSoal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.untirta.unot.R;
import com.untirta.unot.UserSide.MainActivity;
import com.untirta.unot.UserSoal.Model.ModelNilai;

public class FinalScore extends AppCompatActivity {
    TextView tvnim, tvscore;
    Button btnFinish;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final_score);

        tvnim = findViewById(R.id.nim);
        tvscore = findViewById(R.id.score);
        btnFinish = findViewById(R.id.btn_finish);

        tvnim.setText(getIntent().getStringExtra("Uname"));
        tvscore.setText(getIntent().getStringExtra("nilai"));

        btnFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String NIM = tvnim.getText().toString();
                String nilai = tvscore.getText().toString().trim();
                Intent intent = new Intent(FinalScore.this, MainActivity.class);
                intent.putExtra("nilai", nilai);
                intent.putExtra("Uname", NIM);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onBackPressed() {
        // super.onBackPressed();
        //Toast.makeText(Listening.this,"You Click Back Button !, Are You Want To Cheating ?",Toast.LENGTH_SHORT).show();
        return;
    }
}