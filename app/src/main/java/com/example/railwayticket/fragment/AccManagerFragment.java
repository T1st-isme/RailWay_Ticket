package com.example.railwayticket.fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.railwayticket.R;
import com.example.railwayticket.model.User;
import com.example.railwayticket.ui.LoginActivity;

import java.util.Objects;


public class AccManagerFragment extends Fragment {
    TextView Account;

    //    private final Gson gson = new Gson();
    @SuppressLint({"MissingInflatedId", "SetTextI18n"})
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_acc_manager, container, false);
        Account = v.findViewById(R.id.Account);

        Intent i = requireActivity().getIntent();

        User name = (User) i.getSerializableExtra("name");
        if(name == null){
            Account.setText("Hello");
        }else {
            Account.setText("Hello"+ name.getName());
        }
        Account.setOnClickListener( view ->{
            Intent e = new Intent(getContext(), LoginActivity.class);
            startActivity(e);
        });

        return v;
    }
}