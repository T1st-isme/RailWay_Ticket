package com.example.railwayticket.ui;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.railwayticket.DBHelper;
import com.example.railwayticket.R;
import com.example.railwayticket.adapter.OrderAdapter;
import com.example.railwayticket.model.ticketGO;

import java.util.ArrayList;

public class ManageOrderActivity extends AppCompatActivity {
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
        adapter = new OrderAdapter(lstOrders);
        //gán adapter
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        rcvOrder.setAdapter(adapter);
        rcvOrder.setLayoutManager(linearLayoutManager);
    }
}