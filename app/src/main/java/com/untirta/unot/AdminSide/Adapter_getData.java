package com.untirta.unot.AdminSide;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.untirta.unot.R;
import com.untirta.unot.Score;
import com.untirta.unot.UserSoal.Model.ModelNilai;

import java.util.List;

public class Adapter_getData extends RecyclerView.Adapter {

    List<ModelNilai> fetchDataList;

    public Adapter_getData(List<ModelNilai> fetchDataList) {
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
        ModelNilai score = fetchDataList.get(position);
        viewHolderClass.tvName.setText(score.getIdentitas());
        viewHolderClass.tvNilai.setText(score.getNilai());

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
