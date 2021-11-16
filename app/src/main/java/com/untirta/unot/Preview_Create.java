package com.untirta.unot;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class Preview_Create extends AppCompatActivity {
    TextView tvQuestion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preview_create);

        tvQuestion = findViewById(R.id.tvQuestion);

        tvQuestion.setText(getIntent().getStringExtra("Question"));
    }
}