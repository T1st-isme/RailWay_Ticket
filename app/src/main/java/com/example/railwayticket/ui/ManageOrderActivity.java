package com.example.railwayticket.ui;

import android.annotation.SuppressLint;
import android.os.Bundle;

import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.railwayticket.DBHelper;
import com.example.railwayticket.R;
import com.example.railwayticket.adapter.OrderAdapter;
import com.example.railwayticket.model.ticketGO;

import java.util.ArrayList;

public class ManageOrderActivity extends AppCompatActivity implements OrderAdapter.OrderCallback {
    RecyclerView rcvOrder;
    ArrayList<ticketGO> lstOrders;
    OrderAdapter adapter;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_order);
        rcvOrder = findViewById(R.id.rcvOrder);
        //Lấy dữ liệu
        lstOrders = DBHelper.getAllOrder(this);
        adapter = new OrderAdapter(lstOrders, this);
        //gán adapter
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        rcvOrder.setAdapter(adapter);
        rcvOrder.setLayoutManager(linearLayoutManager);
    }
    @SuppressLint("NotifyDataSetChanged")
    private void resetData() {
        lstOrders.clear();
        lstOrders.addAll(DBHelper.getAllOrder(this));
        adapter.notifyDataSetChanged();
    }
    @Override
    public void onItemDeleteClicked(ticketGO ticket, int position) {
        boolean result = DBHelper.deleteOrder(this, ticket.orderID);
        if (result) {
            Toast.makeText(this,
                    "Xóa thành công",
                    Toast.LENGTH_LONG).show();
            resetData();
        } else {
            Toast.makeText(this,
                    "Xóa thất bại",
                    Toast.LENGTH_LONG).show();
        }
    }
}