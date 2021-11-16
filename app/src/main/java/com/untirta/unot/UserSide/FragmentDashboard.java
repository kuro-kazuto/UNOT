package com.untirta.unot.UserSide;


import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.mikhaellopez.circularimageview.CircularImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.untirta.unot.AdminSide.Admin_control;
import com.untirta.unot.Blank404;
import com.untirta.unot.R;
import com.untirta.unot.UserSoal.Soal_A;
import com.untirta.unot.testfoto;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentDashboard extends Fragment {


    public FragmentDashboard() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_dashboard, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Beranda");
        TextView NIM = view.findViewById(R.id.greeting);
        String namaFile = getActivity().getIntent().getStringExtra("Uname");
        ImageView TOEFL = view.findViewById(R.id.TOEFL);
        ImageView Button1 = view.findViewById(R.id.button1);
        ImageView Button2 = view.findViewById(R.id.button2);
        NIM.setText("Hai " + getActivity().getIntent().getStringExtra("Uname") + ",");

        //INI BAGIAN TARIK GAMBAR DARI FIREBASE STORAGE

        // Reference to an image file in Cloud Storage
        // Create a storage reference from our app
        CircularImageView imageView = view.findViewById(R.id.userimg);
        FirebaseStorage storage = FirebaseStorage.getInstance();
        StorageReference storageReference = storage.getReference().child("images/").child(namaFile);
        storageReference.getDownloadUrl().addOnCompleteListener(new OnCompleteListener<Uri>() {
            @Override
            public void onComplete(@NonNull Task<Uri> task) {
                if(task.isSuccessful())
                {
                    Glide.with(getActivity())
                            .load(task.getResult())
                            .apply(RequestOptions.circleCropTransform())
                            .into(imageView);

                }
                else {
                }
            }
        });




        TOEFL.setOnClickListener(v -> TOEFLDialog());
        Button1.setOnClickListener(v -> Button1Dialog());
        Button2.setOnClickListener(v -> Button2Dialog());
    }

    private void TOEFLDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Apakah Kamu Siap?");
        builder.setMessage("Jangan Lupa Berdoa Sebelum Mengerjakan Soal");
        builder.setPositiveButton("Saya Siap!", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("AdminAktifasi");

                databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        String On = "On";
                        String Off = "Off";
                        String AktifasiAdmin = "Admin";
                        if(snapshot.child(AktifasiAdmin).exists()){
                            Admin_control value = snapshot.child(AktifasiAdmin).getValue(Admin_control.class);
                            String ValueA = value.getValueA();
                            if(ValueA.equals(On)){
                                Intent intent = new Intent(getActivity(), Soal_A.class);
                                startActivity(intent);
                                dialog.dismiss();
                            }
                            if (ValueA.equals(Off)){
                                Intent intent = new Intent(getActivity(), Blank404.class);
                                startActivity(intent);
                                dialog.dismiss();
                            }
                        }
                        else {
                            Toast.makeText(getActivity(), "Child Dosent Exist", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });


            }
        });
        builder.setNeutralButton("Saya Belum Siap", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void Button1Dialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Apakah Kamu Siap?");
        builder.setMessage("Jangan Lupa Berdoa Sebelum Mengerjakan Soal");
        builder.setPositiveButton("Saya Siap!", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("AdminAktifasi");

                databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        String On = "On";
                        String Off = "Off";
                        String AktifasiAdmin = "Admin";
                        if(snapshot.child(AktifasiAdmin).exists()){
                            Admin_control value = snapshot.child(AktifasiAdmin).getValue(Admin_control.class);
                            String ValueA = value.getValueA();
                            if(ValueA.equals(On)){
                                Intent intent = new Intent(getActivity(), Soal_A.class);
                                startActivity(intent);
                                dialog.dismiss();
                            }
                            if (ValueA.equals(Off)){
                                Intent intent = new Intent(getActivity(), Blank404.class);
                                startActivity(intent);
                                dialog.dismiss();
                            }
                        }
                        else {
                            Toast.makeText(getActivity(), "Child Dosent Exist", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });
        builder.setNeutralButton("Saya Belum Siap", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }
    private void Button2Dialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Apakah Kamu Siap?");
        builder.setMessage("Jangan Lupa Berdoa Sebelum Mengerjakan Soal");
        builder.setPositiveButton("Saya Siap!", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(getActivity(), testfoto.class);
                startActivity(intent);
                dialog.dismiss();
            }
        });
        builder.setNeutralButton("Saya Belum Siap", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }
}
