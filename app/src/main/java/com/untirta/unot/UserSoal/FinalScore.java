package com.untirta.unot.UserSoal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.mikhaellopez.circularimageview.CircularImageView;
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
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        photoUser();

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

    private void photoUser() {
        String namaFile = getIntent().getStringExtra("Uname");

        //INI BAGIAN TARIK GAMBAR DARI FIREBASE STORAGE

        // Reference to an image file in Cloud Storage
        // Create a storage reference from our app
        CircularImageView imageView = findViewById(R.id.imgscore);
        FirebaseStorage storage = FirebaseStorage.getInstance();
        StorageReference storageReference = storage.getReference().child("images/").child(namaFile);
        storageReference.getDownloadUrl().addOnCompleteListener(new OnCompleteListener<Uri>() {
            @Override
            public void onComplete(@NonNull Task<Uri> task) {
                if (task.isSuccessful()) {
                    Glide.with(FinalScore.this)
                            .load(task.getResult())
                            .apply(RequestOptions.circleCropTransform())
                            .into(imageView);

                } else {
                }
            }
        });
    }
}