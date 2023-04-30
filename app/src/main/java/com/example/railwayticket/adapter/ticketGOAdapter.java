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
    tickCallback tickCallback;

    public ticketGOAdapter(ArrayList<ticketGO> lstTicket, tickCallback tickCallback) {
        this.lstTicket = lstTicket;
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
        holder.trainid.getId();
        holder.trainid.setText(String.valueOf(item.getTickID()));
        holder.timego.setText(item.getTimeGO());
        holder.timeend.setText(item.getTimeEnd());
        holder.dateGo.setText(item.getDateGo());
        holder.dateEnd.setText(item.getDateEnd());
        holder.statego.setText(String.valueOf(item.getStateGO()));
        holder.stateend.setText(String.valueOf(item.getStateEnd()));
        holder.price.setText(String.valueOf(item.getPrice()));
        holder.itemView.setOnClickListener(v -> tickCallback.onItemClick(item.getId(), item.getTickID(), item.getPrice()));

    }

    @Override
    public int getItemCount() {
        return lstTicket.size();
    }

    public static class ticketGOViewHolder extends RecyclerView.ViewHolder {
        TextView trainid, timego, timeend, statego, stateend, dateGo, dateEnd, price;

        public ticketGOViewHolder(@NonNull View itemView) {
            super(itemView);
            trainid = itemView.findViewById(R.id.tvTrainID);
            dateGo = itemView.findViewById(R.id.tvDayGo);
            dateEnd = itemView.findViewById(R.id.tvDayEnd);
            timego = itemView.findViewById(R.id.tvtimeGo);
            timeend = itemView.findViewById(R.id.tvtimeEnd);
            statego = itemView.findViewById(R.id.tvStateGo);
            stateend = itemView.findViewById(R.id.tvStateEnd);
            price = itemView.findViewById(R.id.tvPrice);
        }
    }

    public interface tickCallback {
        void onItemClick(int id, String tckid, String price);
    }
}
