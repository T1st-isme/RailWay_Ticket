package com.example.railwayticket.ui;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.railwayticket.DBHelper;
import com.example.railwayticket.R;
import com.example.railwayticket.adapter.ticketGOAdapter;
import com.example.railwayticket.model.ticketGO;

import java.util.ArrayList;

public class ChonTauDiActivity extends AppCompatActivity implements ticketGOAdapter.tickCallback {
    Button btnnext;
    RecyclerView rcvTicket;
    ArrayList<ticketGO> lstTicket;
    ticketGOAdapter adapter;
    Intent i;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chon_tau_di);
        rcvTicket = findViewById(R.id.rcvTaudi);
        String x =  getIntent().getStringExtra("stateG");
        String y =  getIntent().getStringExtra("stateE");
        String z = getIntent().getStringExtra("dateGo");
        ticketGO t = new ticketGO("0",x,y,z,"", "");
        lstTicket = DBHelper.getAllTicketGO(this, t);
        adapter = new ticketGOAdapter(lstTicket,this);
        LinearLayoutManager lm = new LinearLayoutManager(this);
        rcvTicket.setLayoutManager(lm);
        rcvTicket.setAdapter(adapter);

//        //button de bam
//        btnnext.setOnClickListener(v -> {
//                    //tao duong dan toi Activity moi
//                    startActivity(i);
//                }
//        );
    }
    @Override
    public void onItemClick(String id, String gia) {
        String x =  getIntent().getStringExtra("stateG");
        String y =  getIntent().getStringExtra("stateE");
        String z = getIntent().getStringExtra("dateGo");
        i =  new Intent(ChonTauDiActivity.this, SeatActivity.class);
        i.putExtra("tickID", id);
        i.putExtra("NoiDi", x);
        i.putExtra("NoiDen", y);
        i.putExtra("NgayDi", z);
        i.putExtra("gia", String.valueOf(gia));
        System.out.println(gia);
        startActivity(i);
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

