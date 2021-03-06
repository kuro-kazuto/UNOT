package com.untirta.unot.UserSide;

import android.Manifest;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.mikhaellopez.circularimageview.CircularImageView;
import com.untirta.unot.AccountSide.Login;
import com.untirta.unot.AdminSide.Admin_Remote;
import com.untirta.unot.AdminSide.Admin_control;
import com.untirta.unot.R;
import com.untirta.unot.Splash;

import java.io.ByteArrayOutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

public class UserPhoto extends AppCompatActivity {

    private StorageReference storageReference = null;
    private DatabaseReference databaseReference = null;
    private ProgressBar progressBar;
    private CircularImageView imagePreviw;
    private Uri filePath = null;

    private final int PICK_IMAGE_GALLERY_CODE = 78;
    private final int CAMERA_PERMISSION_REQUEST_CODE = 12345;
    private final int CAMERA_PICTURE_REQUEST_CODE = 56789;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        FirebaseStorage firebaseStorage = FirebaseStorage.getInstance();
        databaseReference =  database.getReference().child("user_images");
        storageReference = firebaseStorage.getReference();

        CircularImageView selectButton = findViewById(R.id.imagePreview);
        Button uploadButton = findViewById(R.id.uploadButton);
        Button showUPloadedImages = findViewById(R.id.showUPloadedImages);

        imagePreviw = findViewById(R.id.imagePreview);
        progressBar = findViewById(R.id.progressBar);

        TextView NIM = findViewById(R.id.greeting);
        NIM.setText(getIntent().getStringExtra("USname"));

        selectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showImageSelectedDialog();

            }
        });


        uploadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (imagePreviw.getDrawable() == null){
                    Toast.makeText(UserPhoto.this, "Tidak Ada Foto!, Harap Tambah Foto Untuk Melanjutkan", Toast.LENGTH_SHORT).show();
                } else{
                    uploadImage();
                    Dialog dialog = new Dialog(UserPhoto.this);
                    //Memasang Desain Layout untuk Custom Dialog
                    dialog.setContentView(R.layout.dialog_progress);
                    dialog.setCanceledOnTouchOutside(false);
                    dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
                    int waktu_loading = 8000;
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {

                            //setelah loading maka akan langsung berpindah activity
                            String Uname = NIM.getText().toString();
                            Intent pindahKeDashboard = new Intent(UserPhoto.this, MainActivity.class);
                            pindahKeDashboard.putExtra("Uname", Uname);
                            startActivity(pindahKeDashboard);
                            finish();

                        }
                    }, waktu_loading);
                    dialog.show();

                }
            }
        });

        //OPSIONAL UNTUK MELIHAT SELURUH GAMBAR
        showUPloadedImages.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(UserPhoto.this, DownloadUploadedImages.class));
            }
        });

    }

    private void uploadImage() {
        if(filePath != null) {
            progressBar.setVisibility(View.VISIBLE);
            Date date = new Date();
            DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
            String strDate = dateFormat.format(date);


            TextView Nama = findViewById(R.id.greeting);
            Nama.setText(getIntent().getStringExtra("USname"));
            StorageReference ref = storageReference.child("images/"+ Nama.getText().toString());
            StorageReference ref1 = storageReference.child("history/" + Nama.getText().toString() +" - "+ strDate);
            ref.putFile(filePath).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    ref.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {
                            databaseReference.push().setValue(uri.toString());
                            Toast.makeText(UserPhoto.this, "Image uploaded successfully", Toast.LENGTH_SHORT).show();
                            progressBar.setVisibility(View.GONE);
                        }
                    });
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    progressBar.setVisibility(View.GONE);
                    Toast.makeText(UserPhoto.this, "Image uploaded failed", Toast.LENGTH_SHORT).show();
                }
            });

            ref1.putFile(filePath).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    ref1.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {
                            databaseReference.push().setValue(uri.toString());
                        }
                    });
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {}
            });
        }
    }
    private void showImageSelectedDialog() {
        checkCameraPermission();
    }

    private void checkCameraPermission() {
        if(ContextCompat.checkSelfPermission(UserPhoto.this, Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED
                || ContextCompat.checkSelfPermission(UserPhoto.this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED ) {
            ActivityCompat.requestPermissions(UserPhoto.this, new String[] {
                    Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE
            }, CAMERA_PERMISSION_REQUEST_CODE);
        } else {
            openCamera();
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode == CAMERA_PERMISSION_REQUEST_CODE && grantResults[1] ==PackageManager.PERMISSION_GRANTED){
            openCamera();
        }
    }

    private void openCamera() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if(intent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(intent, CAMERA_PICTURE_REQUEST_CODE);
        }
    }

    private void selectFromGallery() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Image"), PICK_IMAGE_GALLERY_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode  ==  PICK_IMAGE_GALLERY_CODE && resultCode == Activity.RESULT_OK) {
            if(data == null || data.getData() == null)
                return;

            try {
                filePath = data.getData();
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);
                imagePreviw.setImageBitmap(bitmap);
            }catch (Exception e) {

            }
        } else if(requestCode == CAMERA_PICTURE_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap bitmap  = (Bitmap)extras.get("data");
            imagePreviw.setImageBitmap(bitmap);
            filePath =getImageUri(getApplicationContext(), bitmap);
        }

    }

    private Uri getImageUri(Context context, Bitmap bitmap) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
        String path = MediaStore.Images.Media.insertImage(getContentResolver(), bitmap, "title", null);
        return Uri.parse(path);
    }
}