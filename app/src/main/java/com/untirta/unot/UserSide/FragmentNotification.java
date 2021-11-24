package com.untirta.unot.UserSide;


import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.untirta.unot.FeedRss.API.APILiga;
import com.untirta.unot.FeedRss.Models.RSSItem;
import com.untirta.unot.FeedRss.News;
import com.untirta.unot.FeedRss.Support.InternetTools;
import com.untirta.unot.R;
import com.untirta.unot.Splash;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentNotification extends Fragment {
    private Context thisContext = getActivity();
    private String CLASS_NAME = "News";

    private int ITEMS_IN_LIST = 3;
    private int ITEMS_COUNT;
    private int PAGES_COUNT;

    private APILiga api;
    private InternetTools internetTools = new InternetTools(thisContext);
    private Button[] paginationButtons;
    private ArrayList<RSSItem> items;
    private ArrayList<RSSItem> sortedItems;

    ListView FeedList;
    LinearLayout paginationLayout;


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
