package com.example.railwayticket.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.railwayticket.R;
import com.example.railwayticket.model.ticket;

import java.util.ArrayList;

public class trainTicketAdapter extends RecyclerView.Adapter<trainTicketAdapter.TrainTicketViewHolder> {
    Context context;
    ArrayList<ticket> lstTicket;
    TrainTickCallback tickCallback;

    public trainTicketAdapter(ArrayList<ticket> lstTicket) {
        this.lstTicket = lstTicket;
    }

    public void setTrainCallback(TrainTickCallback tickCallback) {
        this.tickCallback = tickCallback;
    }


    @NonNull
    @Override
    public TrainTicketViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.train_item, parent, false);
        return new TrainTicketViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TrainTicketViewHolder holder, int position) {
        ticket item = lstTicket.get(position);
        holder.trainid.setText(item.getTrainID());
        holder.timego.setText(item.getTimego());
        holder.timeend.setText(item.getTimeend());
        holder.statego.setText(item.getStateGo());
        holder.stateend.setText(item.getStateEnd());
        holder.price.setText(item.getPrice());
        holder.delete.setOnClickListener(v -> tickCallback.onItemDeleteClicked(item, position));
        holder.update.setOnClickListener(v -> tickCallback.onItemEditClicked(item, position));
    }

    @Override
    public int getItemCount() {
        return lstTicket.size();
    }


    static class TrainTicketViewHolder extends RecyclerView.ViewHolder {
        TextView trainid, timego, timeend, statego, stateend, price;
        ImageView delete, update;

        public TrainTicketViewHolder(@NonNull View itemView) {
            super(itemView);
            trainid = itemView.findViewById(R.id.tvName);
            timego = itemView.findViewById(R.id.tvTimeGo);
            timeend = itemView.findViewById(R.id.tvTimeEnd);
            statego = itemView.findViewById(R.id.tvStateGo);
            stateend = itemView.findViewById(R.id.tvStateEnd);
            price = itemView.findViewById(R.id.tvPrice);
            delete = itemView.findViewById(R.id.ivDelete);
            update = itemView.findViewById(R.id.ivEdit);
        }
    }

    public interface TrainTickCallback {
        void onItemDeleteClicked(ticket ticket, int position);

        void onItemEditClicked(ticket ticket, int position);
    }
}
