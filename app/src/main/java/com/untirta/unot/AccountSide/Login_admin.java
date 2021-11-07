package com.untirta.unot.AccountSide;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.untirta.unot.AdminSide.AkunAdmin;
import com.untirta.unot.AdminSide.Dashboard_admin;
import com.untirta.unot.R;

public class Login_admin extends AppCompatActivity {

    EditText tvUser, tvPassword;
    TextView tvUserdb, tvPassdb, pindah;
    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("AkunMimin");
    Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_admin);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        //showPassword();



        tvUser = findViewById(R.id.usernameA);
        tvPassword = findViewById(R.id.passwordA);
        btnLogin = findViewById(R.id.BtnloginA);
        pindah = findViewById(R.id.pindah);

        pindah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent Pindah = new Intent(Login_admin.this, Login.class);
                startActivity(Pindah);

            }
        });


        String EUser = tvUser.getText().toString().trim();
        String EPass = tvPassword.getText().toString().trim();

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                akunDB();
            }
        });




    }
    private void peringatan(){
        String EUser = tvUser.getText().toString().trim();
        String EPass = tvPassword.getText().toString().trim();
        if(EUser.isEmpty()){
            tvUser.setError("UserName Tidak Boleh Kosong");
        }
        if (EPass.isEmpty()){
            tvPassword.setError("Password tidak boleh kosong");
        }
    }
    private void create_db(){
        String EUser = tvUser.getText().toString().trim();
        String EPass = tvPassword.getText().toString().trim();
        String id = databaseReference.push().getKey();
        AkunAdmin user = new AkunAdmin(id, EUser, EPass);

        databaseReference.child(user.getEmailA()).setValue(user);
    }

    private void akunDB(){

        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String akun = "UNOT";
                String EUser = tvUser.getText().toString().trim();
                String EPass = tvPassword.getText().toString().trim();
                if (snapshot.child(akun).exists()){
                    AkunAdmin user = snapshot.child(akun).getValue(AkunAdmin.class);
                    String userName = user.getEmailA();
                    if (userName.equals(EUser)){
                        String passWord = user.getPassA();
                        if (passWord.equals(EPass)){
                            Intent intent = new Intent(Login_admin.this, Dashboard_admin.class);
                            startActivity(intent);
                        }else {
                            Toast.makeText(Login_admin.this, "Wrong Password", Toast.LENGTH_SHORT).show();
                        }
                    }else {
                        Toast.makeText(Login_admin.this, "Wrong UserName", Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}