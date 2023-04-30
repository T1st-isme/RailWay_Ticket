package com.example.railwayticket.adapter;

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

public class ticketMAdapter extends RecyclerView.Adapter<ticketMAdapter.ticketMViewHolder> {
    Context context;
    ArrayList<ticketGO> lstTicket;
    tickMCallback tickMCallback;

    public ticketMAdapter(ArrayList<ticketGO> lstTicket) {
        this.lstTicket = lstTicket;
    }

    public void setTickMCallback(tickMCallback tickMCallback) {
        this.tickMCallback = tickMCallback;
    }


    @NonNull
    @Override
    public ticketMViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.fragment_ticket, parent, false);
        return new ticketMViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ticketMViewHolder holder, int position) {
        ticketGO item = lstTicket.get(position);
        holder.trainid.setText(String.valueOf(item.getTickID()));
        holder.timego.setText(item.getTimeGO());
        holder.dateGo.setText(item.getDateGo());
        holder.statego.setText(String.valueOf(item.getStateGO()));
        holder.stateend.setText(String.valueOf(item.getStateEnd()));
        holder.price.setText(String.valueOf(item.getPrice()));
        holder.ghe.setText(String.valueOf(item.getSeat()));
        holder.itemView.setOnClickListener(view -> tickMCallback.onItemClick(item));

    }

    @Override
    public int getItemCount() {
        return lstTicket.size();
    }

    public static class ticketMViewHolder extends RecyclerView.ViewHolder {
        TextView trainid, timego, statego, stateend, dateGo, price, ghe;

        public ticketMViewHolder(@NonNull View itemView) {
            super(itemView);
            trainid = itemView.findViewById(R.id.trainID);
            ghe = itemView.findViewById(R.id.SoCho);
            dateGo = itemView.findViewById(R.id.DayGo);
            timego = itemView.findViewById(R.id.GioDi);
            statego = itemView.findViewById(R.id.stateGO);
            stateend = itemView.findViewById(R.id.stateEnd);
            price = itemView.findViewById(R.id.Gia);
        }
    }

    public interface tickMCallback {
        void onItemClick(ticketGO t);

    }
}

