package com.example.railwayticket.fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.railwayticket.DBHelper;
import com.example.railwayticket.R;
import com.example.railwayticket.adapter.ticketMAdapter;
import com.example.railwayticket.model.User;
import com.example.railwayticket.model.ticketGO;
import com.example.railwayticket.ui.DetailTicketActivity;

import java.util.ArrayList;

public class TicketManageFragment extends Fragment implements ticketMAdapter.tickMCallback {
    LinearLayout lnDta, lnNDta;
    RecyclerView rcvTicket;
    ArrayList<ticketGO> lstTicket;
    ticketMAdapter adapter;
    private int userID = 0;
    SharedPreferences sp;
    String TAG =  "ABC";
    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_ticket_manage, container, false);
        rcvTicket = v.findViewById(R.id.rcvMticket);
        lnDta = v.findViewById(R.id.haveDta);
        lnNDta = v.findViewById(R.id.NoDta);
        lnNDta.setVisibility(View.GONE);
        lnNDta.setVisibility(View.GONE);

//        ticketGO t = new ticketGO(0, "", "", "", "", "", "", "", "");
        sp = requireActivity().getSharedPreferences("MyApp", Context.MODE_PRIVATE);

        System.out.println(userID);
        try{
            userID = Integer.parseInt(sp.getString("id", null));
        }catch (Exception e){
            Log.e(TAG,"userID = 0");
        }
        if (userID == 0){
            Toast.makeText(getContext(), "Bạn chưa có vé nào. Vui lòng đặt vé",Toast.LENGTH_SHORT).show();
            lnNDta.setVisibility(View.VISIBLE);
        }else {
            lnNDta.setVisibility(View.GONE);
            lnDta.setVisibility(View.VISIBLE);
            User user = new User(userID, "", "", "");
            lstTicket = DBHelper.getAllTicketM(getContext(), user);
            adapter = new ticketMAdapter(lstTicket);
            adapter.setTickMCallback(this);
            LinearLayoutManager lm = new LinearLayoutManager(getContext());
            rcvTicket.setAdapter(adapter);
            rcvTicket.setLayoutManager(lm);
            if (lstTicket.size() == 0){
                lnNDta.setVisibility(View.VISIBLE);
            }
        }
        // Inflate the layout for this fragment
        return v;
    }

    @Override
    public void onItemClick(ticketGO t) {
        Intent i = new Intent(getActivity(), DetailTicketActivity.class);
        i.putExtra("tickID", t.tickID);
        i.putExtra("ghe", t.seat);
        i.putExtra("stateG", t.stateGO);
        i.putExtra("stateE", t.stateEnd);
        i.putExtra("timeGo", t.timeGO);
        i.putExtra("DateGo", t.dateGo);
        i.putExtra("DateEnd", t.dateEnd);
        i.putExtra("price", t.price);
        i.putExtra("name", t.name);
        i.putExtra("phone", t.phone);
        i.putExtra("cccd", t.cccd);
//        i.putExtra("name", t.name);
        System.out.println("ghe:" +t.seat +"so ve: "+ t.tickID+"nơi di: "+t.stateGO+"nơi đến: "+t.stateEnd+"thời gian đi: "+t.timeGO+"ngày đi: "+t.dateGo+"giá: "+t.price+"tên: "+t.name);
        startActivity(i);
    }

}