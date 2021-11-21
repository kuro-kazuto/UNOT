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
import com.untirta.unot.WebView.EadministrasiActivity;
import com.untirta.unot.WebView.SiakadActivity;
import com.untirta.unot.WebView.SpadaActivity;
import com.untirta.unot.WebView.TugasAkhirActivity;
import com.untirta.unot.WebView.WisudaActivity;


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
        ImageView Button0 = view.findViewById(R.id.button0);
        ImageView Button1 = view.findViewById(R.id.button1);
        ImageView Button2 = view.findViewById(R.id.button2);
        ImageView Button3 = view.findViewById(R.id.button3);
        ImageView Button4 = view.findViewById(R.id.button4);
        ImageView Button5 = view.findViewById(R.id.button5);
        ImageView Button6 = view.findViewById(R.id.button6);
        ImageView Button7 = view.findViewById(R.id.button7);
        ImageView Button8 = view.findViewById(R.id.button8);
        ImageView Button9 = view.findViewById(R.id.button9);
        ImageView TA = view.findViewById(R.id.tugasakhir);
        ImageView siakad = view.findViewById(R.id.siakad);
        ImageView spada = view.findViewById(R.id.spada);
        ImageView eadmin = view.findViewById(R.id.eadmin);
        ImageView wisuda = view.findViewById(R.id.wisuda);
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


        Button0.setOnClickListener(v -> Button0Dialog());
        Button1.setOnClickListener(v -> Button1Dialog());
        Button2.setOnClickListener(v -> Button2Dialog());
        Button3.setOnClickListener(v -> Button3Dialog());
        Button4.setOnClickListener(v -> Button4Dialog());
        Button5.setOnClickListener(v -> Button5Dialog());
        Button6.setOnClickListener(v -> Button6Dialog());
        Button7.setOnClickListener(v -> Button7Dialog());
        Button8.setOnClickListener(v -> Button8Dialog());
        Button9.setOnClickListener(v -> Button9Dialog());

        TA.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), TugasAkhirActivity.class);
            startActivity(intent);
        });
        eadmin.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), EadministrasiActivity.class);
            startActivity(intent);
        });
        spada.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), SpadaActivity.class);
            startActivity(intent);
        });
        siakad.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), SiakadActivity.class);
            startActivity(intent);
        });
        wisuda.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), WisudaActivity.class);
            startActivity(intent);
        });
    }

    private void Button0Dialog() {
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
                                String name = getActivity().getIntent().getStringExtra("Uname");
                                Intent intent = new Intent(getActivity(), Soal_A.class);
                                intent.putExtra("Uname", name);
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
                                String name = getActivity().getIntent().getStringExtra("Uname");
                                Intent intent = new Intent(getActivity(), Soal_A.class);
                                intent.putExtra("Uname", name);
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
                                String name = getActivity().getIntent().getStringExtra("Uname");
                                Intent intent = new Intent(getActivity(), Soal_A.class);
                                intent.putExtra("Uname", name);
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

    private void Button3Dialog() {
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
                                String name = getActivity().getIntent().getStringExtra("Uname");
                                Intent intent = new Intent(getActivity(), Soal_A.class);
                                intent.putExtra("Uname", name);
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

    private void Button4Dialog() {
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
                                String name = getActivity().getIntent().getStringExtra("Uname");
                                Intent intent = new Intent(getActivity(), Soal_A.class);
                                intent.putExtra("Uname", name);
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

    private void Button5Dialog() {
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
                                String name = getActivity().getIntent().getStringExtra("Uname");
                                Intent intent = new Intent(getActivity(), Soal_A.class);
                                intent.putExtra("Uname", name);
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

    private void Button6Dialog() {
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
                                String name = getActivity().getIntent().getStringExtra("Uname");
                                Intent intent = new Intent(getActivity(), Soal_A.class);
                                intent.putExtra("Uname", name);
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

    private void Button7Dialog() {
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
                                String name = getActivity().getIntent().getStringExtra("Uname");
                                Intent intent = new Intent(getActivity(), Soal_A.class);
                                intent.putExtra("Uname", name);
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

    private void Button8Dialog() {
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
                                String name = getActivity().getIntent().getStringExtra("Uname");
                                Intent intent = new Intent(getActivity(), Soal_A.class);
                                intent.putExtra("Uname", name);
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

    private void Button9Dialog() {
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
                                String name = getActivity().getIntent().getStringExtra("Uname");
                                Intent intent = new Intent(getActivity(), Soal_A.class);
                                intent.putExtra("Uname", name);
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
}
