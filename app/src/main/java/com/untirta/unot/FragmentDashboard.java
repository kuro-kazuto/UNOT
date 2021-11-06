package com.untirta.unot;


import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
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
        ImageView TOEFL = view.findViewById(R.id.TOEFL);
        ImageView Button1 = view.findViewById(R.id.button1);
        NIM.setText("Hai " + getActivity().getIntent().getStringExtra("Uname") + ",");


        TOEFL.setOnClickListener(v -> TOEFLDialog());
        Button1.setOnClickListener(v -> Button1Dialog());
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
                                Intent intent = new Intent(getActivity(), Listening_direction.class);
                                startActivity(intent);
                                dialog.dismiss();
                            }
                            if (ValueA.equals(Off)){
                                Intent intent = new Intent(getActivity(), UnderConstruction.class);
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
                                Intent intent = new Intent(getActivity(), Listening_direction.class);
                                startActivity(intent);
                                dialog.dismiss();
                            }
                            if (ValueA.equals(Off)){
                                Intent intent = new Intent(getActivity(), UnderConstruction.class);
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
                Intent intent = new Intent(getActivity(), UnderConstruction.class);
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
