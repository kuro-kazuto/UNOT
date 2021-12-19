//AKTIVITY SPLASH SCREEN
package com.untirta.unot;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

import com.untirta.unot.AccountSide.Login;
import com.untirta.unot.FeedRss.News;

public class Splash extends AppCompatActivity {

    //1000=1 detik

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);


        setContentView(R.layout.activity_splash);
        int waktu_loading = 3000;
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                //setelah loading maka akan langsung berpindah ke home activity
                Intent login=new Intent(Splash.this, Login.class);
                startActivity(login);
                finish();

            }
        }, waktu_loading);
    }
}