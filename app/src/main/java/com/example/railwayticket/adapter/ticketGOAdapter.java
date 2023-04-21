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

public class ticketGOAdapter extends RecyclerView.Adapter<ticketGOAdapter.ticketGOViewHolder> {

    Context context;
    ArrayList<ticketGO> lstTicket;
    ticketGOAdapter tickCallback;

    public ticketGOAdapter(ArrayList<ticketGO> lstTicket) {
        this.lstTicket = lstTicket;
    }



    public void settickGOCallback(ticketGOAdapter tickCallback) {
        this.tickCallback = tickCallback;
    }

    @NonNull
    @Override
    public ticketGOViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.ticket_item, parent, false);
        return new ticketGOViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ticketGOViewHolder holder, int position) {
        ticketGO item = lstTicket.get(position);
        holder.trainid.setText(String.valueOf(item.getId()));
        holder.timego.setText(item.getDate());
        holder.statego.setText(String.valueOf(item.getStateName()));
//        holder.stateend.setText(item.getStateEnd());
        holder.price.setText(String.valueOf(item.getPrice()));
    }

    @Override
    public int getItemCount() {
        return lstTicket.size();
    }

    public class ticketGOViewHolder extends RecyclerView.ViewHolder {
        TextView trainid, timego, timeend, statego, stateend, price;
        public ticketGOViewHolder(@NonNull View itemView) {
            super(itemView);
            trainid = itemView.findViewById(R.id.tvTicketID);
            timego = itemView.findViewById(R.id.tvTimeGo);
            statego = itemView.findViewById(R.id.tvStateGo);
//            stateend = itemView.findViewById(R.id.tvStateEnd);
            price = itemView.findViewById(R.id.tvPrice);
        }
    }
}
