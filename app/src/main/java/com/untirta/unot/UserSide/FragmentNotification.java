package com.untirta.unot.UserSide;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.untirta.unot.FeedRss.News;
import com.untirta.unot.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentNotification extends Fragment {

    public FragmentNotification() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_notification, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Berita");
        Dialog dialog = new Dialog(getActivity());
        //Memasang Desain Layout untuk Custom Dialog
        dialog.setContentView(R.layout.dialog_progress);
        dialog.setCanceledOnTouchOutside(false);
        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        int waktu_loading = 2000;
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                //setelah loading maka akan langsung berpindah ke home activity
                Intent news=new Intent(getActivity(), News.class);
                startActivity(news);
                dialog.dismiss();

            }
        }, waktu_loading);
        dialog.show();

    }

}
