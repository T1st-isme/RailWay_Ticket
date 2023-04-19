package com.example.railwayticket.fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.railwayticket.HomeActivity;
import com.example.railwayticket.R;
import com.example.railwayticket.ui.AccInfoActivity;
import com.example.railwayticket.ui.LoginActivity;


public class AccManagerFragment extends Fragment {
    TextView Account;
    Button btLogout;
    private String username = "";
    SharedPreferences sp;
    SharedPreferences.Editor editor;

    //    private final Gson gson = new Gson();
    @SuppressLint({"MissingInflatedId", "SetTextI18n"})
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_acc_manager, container, false);
        Account = v.findViewById(R.id.Account);
        sp = requireActivity().getSharedPreferences("MyApp", Context.MODE_PRIVATE);
        username = sp.getString("username", null);
        Account.setText(username);




        Account.setOnClickListener(view -> {
            if (username == null) {
                Intent i = new Intent(getActivity(), LoginActivity.class);
                startActivity(i);
            } else {
                Intent i2 = new Intent(getActivity(), AccInfoActivity.class);
                startActivity(i2);
            }
        });

        btLogout = v.findViewById(R.id.btnLogout);
        sp = requireActivity().getSharedPreferences("MyApp", Context.MODE_PRIVATE);
        editor = sp.edit();
        if (username == null){
            btLogout.setVisibility(View.GONE);
        } else {
            btLogout.setVisibility(View.VISIBLE);
            btLogout.setOnClickListener(view -> {
                logout();
            });
        }
        return v;
    }

    private void logout() {
        editor.clear();
        editor.apply();
        startActivity(new Intent(getActivity(), HomeActivity.class));
        getActivity().finish();
    }
}