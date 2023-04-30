package com.example.railwayticket.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.railwayticket.R;
import com.example.railwayticket.model.ticketGO;

import java.util.ArrayList;

public class DetailAdapter extends RecyclerView.Adapter<DetailAdapter.DetailViewHolder> {
    Context context;
    ArrayList<ticketGO> lstDetails;

    public DetailAdapter(ArrayList<ticketGO> lstDetails ) {
        this.lstDetails = lstDetails;
    }


    @NonNull
    @Override
    public DetailViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.detail_item, parent, false);
        return new DetailViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DetailViewHolder holder, int position) {
        ticketGO item = lstDetails.get(position);
        holder.name.setText(item.getName());
        holder.phone.setText(item.getPhone());
        holder.trainid.setText(String.valueOf(item.getTickID()));
        holder.dateGo.setText(item.getDateGo());
        holder.dateEnd.setText(item.getDateEnd());
        holder.statego.setText(String.valueOf(item.getStateGO()));
        holder.stateend.setText(String.valueOf(item.getStateEnd()));
        holder.timego.setText(item.getTimeGO());
        holder.price.setText(String.valueOf(item.getPrice()));
        holder.ghe.setText(String.valueOf(item.getSeat()));
    }

    @Override
    public int getItemCount() {
        return lstDetails.size();
    }
    public static class DetailViewHolder extends RecyclerView.ViewHolder {
        TextView name, phone, trainid, timego, statego, stateend, dateGo,dateEnd, price, ghe;

        @SuppressLint("CutPasteId")
        public DetailViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.tvNameKH);
            phone = itemView.findViewById(R.id.tvsdt);
            trainid = itemView.findViewById(R.id.tvTickID);
            ghe = itemView.findViewById(R.id.tvGhe);
            dateGo = itemView.findViewById(R.id.tvNgayDi);
            dateEnd = itemView.findViewById(R.id.tvNgayDen);
            timego = itemView.findViewById(R.id.tvTimeDi);
            statego = itemView.findViewById(R.id.tvNoiDi);
            stateend = itemView.findViewById(R.id.tvNoiDen);
            price = itemView.findViewById(R.id.tvGia);
        }
    }
}
