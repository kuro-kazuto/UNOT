package com.untirta.unot;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class StartQuiz extends AppCompatActivity {
    TextView tvdepartment;
    ImageButton btnAbout, btnstart, btnHelp;
    EditText editText;
    Button JTE, JTM, JTS, JTMET, JTK, JTI;
    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("NIM");
    DatabaseReference dbAdmin = FirebaseDatabase.getInstance().getReference().child("AdminAktifasi");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_startquiz);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        btnstart = findViewById(R.id.btnstart);
        btnAbout = findViewById(R.id.about);
        btnHelp = findViewById(R.id.help);
        editText = findViewById(R.id.edit);
        tvdepartment = findViewById(R.id.pilihan);
        tvdepartment.setText(getIntent().getStringExtra("pilihan"));

        //int varInteger = Integer.parseInt(String.valueOf(editText));

        btnAbout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent about = new Intent(StartQuiz.this, Help.class);
                startActivity(about);
            }
        });

        btnHelp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent help = new Intent(StartQuiz.this, Help.class);
                startActivity(help);
            }
        });

        btnstart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog dialog = new Dialog(StartQuiz.this);
                //Memasang Title / Judul pada Custom Dialog
                dialog.setTitle("Reading Text");
                //Memasang Desain Layout untuk Custom Dialog
                dialog.setContentView(R.layout.dialog_jurusan);
                Button JTE = dialog.findViewById(R.id.TE);
                Button JTM = dialog.findViewById(R.id.TM);
                Button JTS = dialog.findViewById(R.id.TS);
                Button JTK = dialog.findViewById(R.id.TK);
                Button JTMET = dialog.findViewById(R.id.TMetal);
                Button JTI = dialog.findViewById(R.id.TI);

                JTE.setOnClickListener(v1-> {
                    dialog.dismiss();
                    String NIM = editText.getText().toString();
                    if(NIM.length()==10){
                        nimJTE();
                    }else {
                        Toast.makeText(StartQuiz.this, "NIM Must Be 10 Digit", Toast.LENGTH_SHORT).show();
                    }
                });
                JTM.setOnClickListener(v2 -> {
                    dialog.dismiss();
                    String NIM = editText.getText().toString();
                    if(NIM.length()==10){
                        nimJTM();
                    }else {
                        Toast.makeText(StartQuiz.this, "NIM Must Be 10 Digit", Toast.LENGTH_SHORT).show();
                    }
                });
                JTS.setOnClickListener(v3 -> {
                    dialog.dismiss();
                    String NIM = editText.getText().toString();
                    if(NIM.length()==10){
                        nimJTS();
                    }else {
                        Toast.makeText(StartQuiz.this, "NIM Must Be 10 Digit", Toast.LENGTH_SHORT).show();
                    }
                });
                JTK.setOnClickListener(v4 -> {
                    dialog.dismiss();
                    String NIM = editText.getText().toString();
                    if(NIM.length()==10){
                        nimJTK();
                    }else {
                        Toast.makeText(StartQuiz.this, "NIM Must Be 10 Digit", Toast.LENGTH_SHORT).show();
                    }
                });
                JTMET.setOnClickListener(v5 -> {
                    dialog.dismiss();
                    String NIM = editText.getText().toString();
                    if(NIM.length()==10){
                        nimJTMET();
                    }else {
                        Toast.makeText(StartQuiz.this, "NIM Must Be 10 Digit", Toast.LENGTH_SHORT).show();
                    }
                });
                JTI.setOnClickListener(v6 -> {
                    dialog.dismiss();
                    String NIM = editText.getText().toString();
                    if(NIM.length()==10){
                        nimJTI();
                    }else {
                        Toast.makeText(StartQuiz.this, "NIM Must Be 10 Digit", Toast.LENGTH_SHORT).show();
                    }
                });

                dialog.show();
            }
        });
    }

    @Override
    public void onBackPressed() {

        return;
    }

//LONG CODE, REPEAT SAME CODE, WE DOESN'T HAVE ANY SOLUTION FOR DECREASE THE CODE

    private void nimJTE(){
        String name = editText.getText().toString();
        String pilihan = tvdepartment.getText().toString();
        Intent intent = new Intent(StartQuiz.this, Listening_direction.class);
        intent.putExtra("pilihan", pilihan);
        intent.putExtra("name", name);
        String jurusan = "Teknik Elektro";
        intent.putExtra("jurusan", jurusan);
        Intent intent1 = new Intent(StartQuiz.this, Blank404.class);

        if(!TextUtils.isEmpty(name)){
            String AktifasiAdmin = "Admin";
            databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if(snapshot.child(name).exists()) {
                        NIM value = snapshot.child(name).getValue(NIM.class);
                        if (value.getNIM().equals(name)) {
                            Toast.makeText(StartQuiz.this, "NIM Alredy Exist", Toast.LENGTH_SHORT).show();
                        }
                    }else {
                        dbAdmin.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                String On = "On";
                                String Off = "Off";
                                if (snapshot.child(AktifasiAdmin).exists()){
                                    Admin_control value = snapshot.child(AktifasiAdmin).getValue(Admin_control.class);
                                    String ValueA = value.getValueA();
                                    if(ValueA.equals(On)){
                                        startActivity(intent);
                                    }
                                    if(ValueA.equals(Off)){
                                        startActivity(intent1);
                                    }
                                }
                            }
                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {
                            }
                        });
                    }
                }
                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                }
            });

        }else{
            Toast.makeText(this, "You Should Enter NIM", Toast.LENGTH_SHORT).show();
        }
    }

    private void nimJTM(){
        String name = editText.getText().toString();
        String pilihan = tvdepartment.getText().toString();
        Intent intent = new Intent(StartQuiz.this, Listening_direction.class);
        intent.putExtra("pilihan", pilihan);
        intent.putExtra("name", name);
        String jurusan = "Teknik Mesin";
        intent.putExtra("jurusan", jurusan);
        Intent intent1 = new Intent(StartQuiz.this, Blank404.class);

        if(!TextUtils.isEmpty(name)){
            String AktifasiAdmin = "Admin";
            databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if(snapshot.child(name).exists()) {
                        NIM value = snapshot.child(name).getValue(NIM.class);
                        if (value.getNIM().equals(name)) {
                            Toast.makeText(StartQuiz.this, "NIM Alredy Exist", Toast.LENGTH_SHORT).show();
                        }
                    }else {
                        dbAdmin.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                String On = "On";
                                String Off = "Off";
                                if (snapshot.child(AktifasiAdmin).exists()){
                                    Admin_control value = snapshot.child(AktifasiAdmin).getValue(Admin_control.class);
                                    String ValueA = value.getValueA();
                                    if(ValueA.equals(On)){
                                        startActivity(intent);
                                    }
                                    if(ValueA.equals(Off)){
                                        startActivity(intent1);
                                    }
                                }
                            }
                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {
                            }
                        });
                    }
                }
                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                }
            });

        }else{
            Toast.makeText(this, "You Should Enter NIM", Toast.LENGTH_SHORT).show();
        }
    }

    private void nimJTK(){
        String name = editText.getText().toString();
        String pilihan = tvdepartment.getText().toString();
        Intent intent = new Intent(StartQuiz.this, Listening_direction.class);
        intent.putExtra("pilihan", pilihan);
        intent.putExtra("name", name);
        String jurusan = "Teknik Kimia";
        intent.putExtra("jurusan", jurusan);
        Intent intent1 = new Intent(StartQuiz.this, Blank404.class);

        if(!TextUtils.isEmpty(name)){
            String AktifasiAdmin = "Admin";
            databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if(snapshot.child(name).exists()) {
                        NIM value = snapshot.child(name).getValue(NIM.class);
                        if (value.getNIM().equals(name)) {
                            Toast.makeText(StartQuiz.this, "NIM Alredy Exist", Toast.LENGTH_SHORT).show();
                        }
                    }else {
                        dbAdmin.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                String On = "On";
                                String Off = "Off";
                                if (snapshot.child(AktifasiAdmin).exists()){
                                    Admin_control value = snapshot.child(AktifasiAdmin).getValue(Admin_control.class);
                                    String ValueA = value.getValueA();
                                    if(ValueA.equals(On)){
                                        startActivity(intent);
                                    }
                                    if(ValueA.equals(Off)){
                                        startActivity(intent1);
                                    }
                                }
                            }
                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {
                            }
                        });
                    }
                }
                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                }
            });

        }else{
            Toast.makeText(this, "You Should Enter NIM", Toast.LENGTH_SHORT).show();
        }
    }

    private void nimJTS(){
        String name = editText.getText().toString();
        String pilihan = tvdepartment.getText().toString();
        Intent intent = new Intent(StartQuiz.this, Listening_direction.class);
        intent.putExtra("pilihan", pilihan);
        intent.putExtra("name", name);
        String jurusan = "Teknik Sipil";
        intent.putExtra("jurusan", jurusan);
        Intent intent1 = new Intent(StartQuiz.this, Blank404.class);

        if(!TextUtils.isEmpty(name)){
            String AktifasiAdmin = "Admin";
            databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if(snapshot.child(name).exists()) {
                        NIM value = snapshot.child(name).getValue(NIM.class);
                        if (value.getNIM().equals(name)) {
                            Toast.makeText(StartQuiz.this, "NIM Alredy Exist", Toast.LENGTH_SHORT).show();
                        }
                    }else {
                        dbAdmin.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                String On = "On";
                                String Off = "Off";
                                if (snapshot.child(AktifasiAdmin).exists()){
                                    Admin_control value = snapshot.child(AktifasiAdmin).getValue(Admin_control.class);
                                    String ValueA = value.getValueA();
                                    if(ValueA.equals(On)){
                                        startActivity(intent);
                                    }
                                    if(ValueA.equals(Off)){
                                        startActivity(intent1);
                                    }
                                }
                            }
                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {
                            }
                        });
                    }
                }
                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                }
            });

        }else{
            Toast.makeText(this, "You Should Enter NIM", Toast.LENGTH_SHORT).show();
        }
    }

    private void nimJTI(){
        String name = editText.getText().toString();
        String pilihan = tvdepartment.getText().toString();
        Intent intent = new Intent(StartQuiz.this, Listening_direction.class);
        intent.putExtra("pilihan", pilihan);
        intent.putExtra("name", name);
        String jurusan = "Teknik Industri";
        intent.putExtra("jurusan", jurusan);
        Intent intent1 = new Intent(StartQuiz.this, Blank404.class);

        if(!TextUtils.isEmpty(name)){
            String AktifasiAdmin = "Admin";
            databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if(snapshot.child(name).exists()) {
                        NIM value = snapshot.child(name).getValue(NIM.class);
                        if (value.getNIM().equals(name)) {
                            Toast.makeText(StartQuiz.this, "NIM Alredy Exist", Toast.LENGTH_SHORT).show();
                        }
                    }else {
                        dbAdmin.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                String On = "On";
                                String Off = "Off";
                                if (snapshot.child(AktifasiAdmin).exists()){
                                    Admin_control value = snapshot.child(AktifasiAdmin).getValue(Admin_control.class);
                                    String ValueA = value.getValueA();
                                    if(ValueA.equals(On)){
                                        startActivity(intent);
                                    }
                                    if(ValueA.equals(Off)){
                                        startActivity(intent1);
                                    }
                                }
                            }
                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {
                            }
                        });
                    }
                }
                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                }
            });

        }else{
            Toast.makeText(this, "You Should Enter NIM", Toast.LENGTH_SHORT).show();
        }
    }

    private void nimJTMET(){
        String name = editText.getText().toString();
        String pilihan = tvdepartment.getText().toString();
        Intent intent = new Intent(StartQuiz.this, Listening_direction.class);
        intent.putExtra("pilihan", pilihan);
        intent.putExtra("name", name);
        String jurusan = "Teknik Metalurgi";
        intent.putExtra("jurusan", jurusan);
        Intent intent1 = new Intent(StartQuiz.this, Blank404.class);

        if(!TextUtils.isEmpty(name)){
            String AktifasiAdmin = "Admin";
            databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if(snapshot.child(name).exists()) {
                        NIM value = snapshot.child(name).getValue(NIM.class);
                        if (value.getNIM().equals(name)) {
                            Toast.makeText(StartQuiz.this, "NIM Alredy Exist", Toast.LENGTH_SHORT).show();
                        }
                    }else {
                        dbAdmin.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                String On = "On";
                                String Off = "Off";
                                if (snapshot.child(AktifasiAdmin).exists()){
                                    Admin_control value = snapshot.child(AktifasiAdmin).getValue(Admin_control.class);
                                    String ValueA = value.getValueA();
                                    if(ValueA.equals(On)){
                                        startActivity(intent);
                                    }
                                    if(ValueA.equals(Off)){
                                        startActivity(intent1);
                                    }
                                }
                            }
                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {
                            }
                        });
                    }
                }
                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                }
            });

        }else{
            Toast.makeText(this, "You Should Enter NIM", Toast.LENGTH_SHORT).show();
        }
    }
}