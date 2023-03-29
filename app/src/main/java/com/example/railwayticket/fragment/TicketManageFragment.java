package com.example.railwayticket.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.railwayticket.R;

import java.util.ArrayList;

public class TicketManageFragment extends Fragment {
    ListView lvTicket;
    ArrayList<String> arr;
    ArrayAdapter adapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_ticket_manage, container, false);
        lvTicket = (ListView) v.findViewById(R.id.lvallTicket);
        arr = new ArrayList<>();
        arr.add("Mã vé: ABC");
        arr.add("Mã vé: XYZ");
        arr.add("Mã vé: GHA");
        arr.add("Mã vé: ADM");
        adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, arr);
        lvTicket.setAdapter(adapter);
        // Inflate the layout for this fragment
        return v;
    }
}