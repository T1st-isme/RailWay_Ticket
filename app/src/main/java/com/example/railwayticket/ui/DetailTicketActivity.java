package com.example.railwayticket.ui;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.railwayticket.DBHelper;
import com.example.railwayticket.R;
import com.example.railwayticket.adapter.DetailAdapter;
import com.example.railwayticket.model.User;
import com.example.railwayticket.model.ticketGO;

import java.util.ArrayList;

public class DetailTicketActivity extends AppCompatActivity {
    private int userID = 0;
    SharedPreferences sp;
    RecyclerView rcvDetails;
    DetailAdapter adapter;
    ArrayList<ticketGO> lstDetails;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_ticket);
        sp = getSharedPreferences("MyApp", Context.MODE_PRIVATE);
        try{
            userID = Integer.parseInt(sp.getString("id", null));
        }catch (Exception e){
        }
        rcvDetails = findViewById(R.id.rcvDetail);
        String tickID = getIntent().getStringExtra("tickID");
        ticketGO t = new ticketGO(0,tickID, "", "", "", "", "", "", "", "", 0);
//        ticketGO t = new ticketGO();
        User user = new User(userID, "", "", "");
        lstDetails = DBHelper.getDetailTicket(this,  user, t);
        adapter = new DetailAdapter(lstDetails);
        LinearLayoutManager lm = new LinearLayoutManager(this);
        rcvDetails.setAdapter(adapter);
        rcvDetails.setLayoutManager(lm);

    }
}