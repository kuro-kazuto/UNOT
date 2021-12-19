package com.untirta.unot;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;

import com.untirta.unot.UserSide.MainActivity;
import com.untirta.unot.UserSoal.FinalScore;

public class UnderConstruction extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_under_construction);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(UnderConstruction.this, MainActivity.class);
        String NIM = getIntent().getStringExtra("Uname");
        intent.putExtra("Uname", NIM);
        startActivity(intent);
        finish();
    }
}