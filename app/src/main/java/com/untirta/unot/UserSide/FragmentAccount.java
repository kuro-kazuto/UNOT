package com.untirta.unot.UserSide;


import android.Manifest;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.mikhaellopez.circularimageview.CircularImageView;
import com.untirta.unot.R;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentAccount extends Fragment {


    public FragmentAccount() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_account, container, false);
    }

    @Override
    public void onResume() {
        super.onResume();
        getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Akun Saya");
        getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        String namaFile = getActivity().getIntent().getStringExtra("Uname");

        //INI BAGIAN TARIK GAMBAR DARI FIREBASE STORAGE

        // Reference to an image file in Cloud Storage
        // Create a storage reference from our app
        CircularImageView imageView = view.findViewById(R.id.userimg);
        FirebaseStorage storage = FirebaseStorage.getInstance();
        StorageReference storageReference = storage.getReference().child("images/").child(namaFile);
        storageReference.getDownloadUrl().addOnCompleteListener(new OnCompleteListener<Uri>() {
            @Override
            public void onComplete(@NonNull Task<Uri> task) {
                if (task.isSuccessful()) {
                    Glide.with(getActivity())
                            .load(task.getResult())
                            .apply(RequestOptions.circleCropTransform())
                            .into(imageView);

                } else {
                }
            }
        });

        //nama user
        TextView userName = view.findViewById(R.id.namauser);
        userName.setText(getActivity().getIntent().getStringExtra("Uname"));

        //email user
        TextView emailUser = view.findViewById(R.id.emailuser);
        emailUser.setText(getActivity().getIntent().getStringExtra("Uname") + "@untirta.ac.id");

        //tanggal akses terkini
        Date date = new Date();
        DateFormat dateFormat = new SimpleDateFormat("dd MMMM yyyy HH:mm:ss");
        String strDate = dateFormat.format(date);
        TextView tanggal = view.findViewById(R.id.date);
        tanggal.setText(strDate);

        // GET CURRENT LOCATION
        TextView koordinat = view.findViewById(R.id.gps);
        FusedLocationProviderClient mFusedLocation = LocationServices.getFusedLocationProviderClient(getActivity());
        if (ActivityCompat.checkSelfPermission(getActivity(),
                Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getActivity(),
                Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        mFusedLocation.getLastLocation().addOnSuccessListener(getActivity(), new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
                if (location != null) {
                    // Do it all with location
                    Log.d("My Current location", "Lat : " + location.getLatitude() + " Long : " + location.getLongitude());

                    //http://www.google.com/maps/place/lat,lng
                    String linkmaps = "https://www.google.com/maps/place/"+location.getLatitude()+","+location.getLongitude();
                    String langlong = "[Lat : "+location.getLatitude()+"],[Long : "+location.getLongitude()+"]";
                    String value = "<html><a href=\""+linkmaps+"\">"+langlong+"</a></html>";
                    koordinat.setText(Html.fromHtml(value));
                    koordinat.setMovementMethod(LinkMovementMethod.getInstance());

                }
            }
        });


    }
}
