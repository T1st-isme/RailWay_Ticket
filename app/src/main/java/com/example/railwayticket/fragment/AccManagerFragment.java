package com.example.railwayticket.fragment;

import android.accounts.Account;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.railwayticket.R;
import com.example.railwayticket.ui.LoginActivity;


public class AccManagerFragment extends Fragment {
    Button Account;
    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_acc_manager, container, false);
        Account = (Button) v.findViewById(R.id.Account);
        Account.setOnClickListener(view -> {
            Intent intent = new Intent(getContext(), LoginActivity.class);
            startActivity(intent);
        });
        // Inflate the layout for this fragment
        return v;
    }
}