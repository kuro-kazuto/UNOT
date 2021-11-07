package com.untirta.unot.AccountSide;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.untirta.unot.R;

public class Create_account extends AppCompatActivity implements View.OnClickListener{

    //deklarasi beberapa variabel kayak button editext, databaserefrence, firebaseauth
    private static final String TAG = "LoginActivity";

    private DatabaseReference mDatabase;
    private FirebaseAuth mAuth;
    private EditText edtEmail;
    private EditText edtPass;
    private EditText edtPassword;
    private TextView show,show2;
    //private Button btnMasuk;
    private Button btnDaftar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buatakun);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        //variabel tadi untuk memanggil fungsi
        mDatabase = FirebaseDatabase.getInstance().getReference();
        mAuth = FirebaseAuth.getInstance();

        // diatur sesuai id komponennya
        edtEmail = (EditText)findViewById(R.id.email);
        edtPass = (EditText)findViewById(R.id.pass);
        edtPassword = (EditText)findViewById(R.id.password);
        show = (TextView) findViewById(R.id.showpass);
        show2 =findViewById(R.id.showpass2);
        //btnMasuk = (Button) findViewById(R.id.btn_masuk);
        btnDaftar = (Button)findViewById(R.id.Btnbuatakun);

        //nambahin method onClick, biar tombolnya bisa diklik
       // btnMasuk.setOnClickListener(this);
        btnDaftar.setOnClickListener(this);

        showPassword();
        showPassword2();



    }

    //----

    //fungsi ini untuk mendaftarkan data pengguna ke Firebase
    private void signUp() {
        Log.d(TAG, "signUp");
        if (!validateForm()) {
            return;
        }

        //showProgressDialog();
        String email = edtEmail.getText().toString();
        String password = edtPass.getText().toString();
        String firstPass = edtPassword.getText().toString();

        if (email.contains("@untirta.ac.id")) {
            if (password.equals(firstPass)) {
                if (password.length() < 8) {
                    Toast.makeText(Create_account.this, "Length Password Must Be 8 Character !",
                            Toast.LENGTH_SHORT).show();
                }
                else {
                    mAuth.createUserWithEmailAndPassword(email, password)
                            .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    Log.d(TAG, "createUser:onComplete:" + task.isSuccessful());
                                    //hideProgressDialog();
                                    if (task.isSuccessful()) {
                                        onAuthSuccess(task.getResult().getUser());
                                    } else {
                                        Toast.makeText(Create_account.this, "Account Already Exist !",
                                                Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                }
            }
            else {
                Toast.makeText(Create_account.this, "Check Your Password Again !",
                        Toast.LENGTH_SHORT).show();
            }

        }

        else {
            Toast.makeText(Create_account.this, "Using Only Untirta Account !",
                    Toast.LENGTH_SHORT).show();
        }


    }

    //fungsi dipanggil ketika proses Authentikasi berhasil
    private void onAuthSuccess(FirebaseUser user) {
        String username = usernameFromEmail(user.getEmail());

        // membuat User admin baru
        writeNewAdmin(user.getUid(), username, user.getEmail());

        // Go to MainActivity
        startActivity(new Intent(Create_account.this, Login.class));
        finish();
    }

    /*
        ini fungsi buat bikin username dari email
            contoh email: abcdefg@mail.com
            maka username nya: abcdefg
     */
    private String usernameFromEmail(String email) {
        if (email.contains("@")) {
            return email.split("@")[0];
        } else {
            return email;
        }
    }

    //fungsi untuk memvalidasi EditText email dan password agar tak kosong dan sesuai format
    private boolean validateForm() {
        boolean result = true;
        if (TextUtils.isEmpty(edtEmail.getText().toString())) {
            edtEmail.setError("Required");
            result = false;
        } else {
            edtEmail.setError(null);
        }

        if (TextUtils.isEmpty(edtPass.getText().toString())) {
            edtPass.setError("Required");
            result = false;
        } else {
            edtPass.setError(null);
        }

        return result;
    }

    // menulis ke Database
    private void writeNewAdmin(String userId, String name, String email) {
        AdapterAccount admin = new AdapterAccount(name, email);

        mDatabase.child("Absen").child(name).setValue(admin);
    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.Btnbuatakun) {
            signUp();
        }
    }

    public void pindahloginactivity(View view) {
        Intent intent = new Intent(Create_account.this, Login.class);
        startActivity(intent);
    }



    //KODINGAN UNTUK PLUGIN SHOW PASSWORD DAN HIDDEN PASSWORD
    private void showPassword() {
        edtPassword.setOnFocusChangeListener(new View.OnFocusChangeListener(){

            @Override
            public void onFocusChange (View view, boolean b)
            {
                if (hasWindowFocus())
                {
                    show.setVisibility(View.VISIBLE);
                    show.setOnClickListener(view1 -> {
                        if (show.getText() .toString() .equals("show"))
                        {
                            edtPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                            show.setText("hide");
                        }
                        else
                        {
                            edtPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                            show.setText("show");
                        }
                    });
                }
            }
        });
    }
    private void showPassword2() {
        edtPass.setOnFocusChangeListener(new View.OnFocusChangeListener(){

            @Override
            public void onFocusChange (View view, boolean b)
            {
                if (hasWindowFocus())
                {
                    show2.setVisibility(View.VISIBLE);
                    show2.setOnClickListener(view1 -> {
                        if (show2.getText() .toString() .equals("show"))
                        {
                            edtPass.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                            show2.setText("hide");
                        }
                        else
                        {
                            edtPass.setTransformationMethod(PasswordTransformationMethod.getInstance());
                            show2.setText("show");
                        }
                    });
                }
            }
        });
    }
    //-------------------------------------------------------------------------

}