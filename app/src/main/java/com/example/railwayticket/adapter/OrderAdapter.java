package com.example.railwayticket.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.railwayticket.R;
import com.example.railwayticket.model.ticketGO;

import java.util.ArrayList;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.OrderViewHolder> {
    Context context;
    ArrayList<ticketGO> lstOrders;
    OrderCallback OrderCallback;

    public OrderAdapter(ArrayList<ticketGO> lstOrders, OrderCallback OrderCallback) {
        this.lstOrders = lstOrders;
        this.OrderCallback = OrderCallback;
    }

    @NonNull
    @Override
    public OrderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.order_item, parent, false);
        return new OrderViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull OrderViewHolder holder, int position) {
        ticketGO item = lstOrders.get(position);
        holder.orderID.setText(String.valueOf(item.getOrderID()));
        holder.name.setText(item.getName());
        holder.phone.setText(item.getPhone());
        holder.cccd.setText(item.getCccd());
        holder.tickid.setText(String.valueOf(item.getTickID()));
        holder.dateGo.setText(String.valueOf(item.getDateGo()));
        holder.dateEnd.setText(String.valueOf(item.getDateEnd()));
        holder.statego.setText(String.valueOf(item.getStateGO()));
        holder.stateend.setText(String.valueOf(item.getStateEnd()));
        holder.timego.setText(item.getTimeGO());
        holder.timeend.setText(item.getTimeEnd());
        holder.price.setText(String.valueOf(item.getPrice()));
        holder.ghe.setText(String.valueOf(item.getSeat()));
        holder.delete.setOnClickListener(v -> OrderCallback.onItemDeleteClicked(item, position));
    }

    @Override
    public int getItemCount() {
        return lstOrders.size();
    }

    public static class OrderViewHolder extends RecyclerView.ViewHolder {
        TextView orderID, name, phone, cccd, tickid, timego, timeend, statego, stateend, dateGo, dateEnd, price, ghe;
        ImageView delete;

        @SuppressLint("CutPasteId")
        public OrderViewHolder(@NonNull View itemView) {
            super(itemView);
            orderID = itemView.findViewById(R.id.tvOrderID);
            name = itemView.findViewById(R.id.tvNameOrder);
            phone = itemView.findViewById(R.id.tvPhoneKH);
            cccd = itemView.findViewById(R.id.tvCCCD);
            tickid = itemView.findViewById(R.id.tvTickID);
            ghe = itemView.findViewById(R.id.tvSeat);
            dateGo = itemView.findViewById(R.id.tvDateGo);
            dateEnd = itemView.findViewById(R.id.tvDateEnd);
            timego = itemView.findViewById(R.id.tvTimeGo);
            timeend = itemView.findViewById(R.id.tvTimeEnd);
            statego = itemView.findViewById(R.id.tvStateGo);
            stateend = itemView.findViewById(R.id.tvStateEnd);
            price = itemView.findViewById(R.id.tvPrice);
            delete = itemView.findViewById(R.id.ivDelete);
        }
    }

    public interface OrderCallback {
        void onItemDeleteClicked(ticketGO ticket, int position);
    }
}
