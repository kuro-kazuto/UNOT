package com.untirta.unot;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Objects;

@SuppressLint("Registered")
public class Login extends AppCompatActivity {
    private EditText username,password;
    private FirebaseAuth auth;
    private Button btnlogin, btnAdmin;
    private TextView show, createaccount;
    ImageButton btnAbout;
    ImageButton btnHelp;


    public void onCreate(Bundle SaveInstanceState)
    {
        super.onCreate(SaveInstanceState);
        setContentView(R.layout.activity_login);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        initView();
        showPassword();
        login();
        aboutHelp();




    }

    //deklarasi activity help dan about

    private void aboutHelp() {
        btnAbout = findViewById(R.id.about2);
        btnHelp = findViewById(R.id.help2);
        btnHelp.setOnClickListener(v -> {
            Intent help = new Intent(Login.this, Help.class);
            startActivity(help);
        });
        btnAbout.setOnClickListener(v -> {
            Intent about = new Intent(Login.this, Help.class);
            startActivity(about);
        });
    }


    //DEKLARASI FIND VIEW BY ID
    private void initView() {
        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        show = (TextView) findViewById(R.id.show);

        createaccount = (TextView) findViewById(R.id.buat);
        btnlogin = findViewById(R.id.Btnlogin);
        btnAdmin = findViewById(R.id.admin);
        btnAdmin.setOnClickListener(v -> {
            Intent mimin = new Intent(Login.this, Login_admin.class);
            startActivity(mimin);
        });
        auth = FirebaseAuth.getInstance();

    }


    //------------------------------------------------------


    //KODINGAN UNTUK PLUGIN SHOW PASSWORD DAN HIDDEN PASSWORD
    private void showPassword() {
        password.setOnFocusChangeListener(new View.OnFocusChangeListener(){

            @Override
            public void onFocusChange (View view, boolean b)
            {
                if (hasWindowFocus())
                {
                    show.setVisibility(View.VISIBLE);
                    show.setOnClickListener(new View.OnClickListener()
                    {
                        @Override
                        public void onClick (View view)
                        {
                            if (show.getText() .toString() .equals("show"))
                            {
                                password.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                                show.setText("hide");
                            }
                            else
                            {
                                password.setTransformationMethod(PasswordTransformationMethod.getInstance());
                                show.setText("show");
                            }
                        }
                    });
                }
            }
        });
    }
    //-------------------------------------------------------------------------


    //ON CLICK LISTENER UNTUK LOGIN DAN PINDAH ACTIVITY
    private void login() {
        createaccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Login.this, Create_account.class));
            }
        });
        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //menampung imputan user
                final String emailUser = username.getText().toString().trim();
                final String passwordUser = password.getText().toString().trim();

                //validasi email dan password
                // jika email kosong
                if (emailUser.isEmpty()) {
                    username.setError("Email tidak boleh kosong");
                }
                // jika email not valid
                else if (!Patterns.EMAIL_ADDRESS.matcher(emailUser).matches()) {
                    username.setError("Email tidak valid");
                }
                // jika password kosong
                else if (passwordUser.isEmpty()) {
                    password.setError("Password tidak boleh kosong");
                }
                //jika password kurang dari 6 karakter
                else if (passwordUser.length() < 8) {
                    password.setError("Password minimal terdiri dari 6 karakter");
                }
                else {
                    auth.signInWithEmailAndPassword(emailUser, passwordUser)
                            .addOnCompleteListener(Login.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    // ketika gagal login maka akan do something
                                    if (!task.isSuccessful()) {
                                        Toast.makeText(Login.this,
                                                "Gagal login karena " + Objects.requireNonNull(task.getException()).getMessage()
                                                , Toast.LENGTH_LONG).show();
                                    }
                                    // ketika berhasil maka pindah ACTIVITY
                                    else {
                                        Bundle bundle = new Bundle();
                                        bundle.putString("email", emailUser);
                                        bundle.putString("pass", passwordUser);
                                        Intent intent = new Intent(new Intent(Login.this, Dashboard.class).putExtra("emailpass", bundle));
                                        startActivity(intent);

                                        finish();
                                    }
                                }
                            });
                }
            }
        });
    }


}


