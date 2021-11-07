package com.untirta.unot;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class Adapter_getData extends RecyclerView.Adapter {

    List<Score> fetchDataList;

    public Adapter_getData(List<Score> fetchDataList) {
        this.fetchDataList = fetchDataList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout, parent, false);
        ViewHolderClass viewHolderClass =new ViewHolderClass(view);
        return viewHolderClass;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        ViewHolderClass viewHolderClass = (ViewHolderClass)holder;
        Score score = fetchDataList.get(position);
        viewHolderClass.tvName.setText(score.getUserName().toString());
        viewHolderClass.tvNilai.setText(score.getUserScoreT());

    }

    @Override
    public int getItemCount() {
        return fetchDataList.size();
    }

    public class ViewHolderClass extends RecyclerView.ViewHolder{
        TextView tvName, tvNilai;

        public ViewHolderClass(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.getUserName);
            tvNilai = itemView.findViewById(R.id.nilai);
        }
    }
}
