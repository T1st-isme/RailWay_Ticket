package com.example.railwayticket.ui;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.railwayticket.DBHelper;
import com.example.railwayticket.R;
import com.example.railwayticket.adapter.ticketGOAdapter;
import com.example.railwayticket.model.ticketGO;

import java.util.ArrayList;

public class ChonTauDiActivity extends AppCompatActivity {
    Button btnnext;
    TextView test1;
    RecyclerView rcvTicket;
    ArrayList<ticketGO> lstTicket;
    ticketGOAdapter adapter;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chon_tau_di);
        btnnext = findViewById(R.id.btnext);
        rcvTicket = findViewById(R.id.rcvTaudi);
        String x =  getIntent().getStringExtra("state");
        System.out.println(x);
        lstTicket = DBHelper.getAllTicketGO(this, x);
        adapter = new ticketGOAdapter(lstTicket);
        LinearLayoutManager lm = new LinearLayoutManager(this);
        rcvTicket.setLayoutManager(lm);
        rcvTicket.setAdapter(adapter);


        //button de bam
        btnnext.setOnClickListener(v -> {
                    //tao duong dan toi Activity moi
                    Intent i = new Intent(ChonTauDiActivity.this, SeatActivity.class);
                    startActivity(i);
                }
        );

    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return true;
    }

}

