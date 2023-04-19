package com.example.railwayticket.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.railwayticket.model.ticketGO;

import java.util.ArrayList;

public class ticketGOAdapter extends RecyclerView.Adapter<ticketGOAdapter.ticketGOViewHolder> {

    Context context;
    ArrayList<ticketGO> lstTicket;
    trainTicketAdapter tickCallback;

    public ticketGOAdapter(Context context, ArrayList<ticketGO> lstTicket) {
        this.context = context;
        this.lstTicket = lstTicket;
    }

    public void settickGOCallback(trainTicketAdapter tickCallback) {
        this.tickCallback = tickCallback;
    }

    @NonNull
    @Override
    public ticketGOViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull ticketGOViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return lstTicket.size();
    }

    public class ticketGOViewHolder extends RecyclerView.ViewHolder {
        public ticketGOViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
