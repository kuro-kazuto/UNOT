package com.untirta.unot.About;


import android.content.pm.ActivityInfo;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.untirta.unot.R;


public class Help extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);




    }


}
