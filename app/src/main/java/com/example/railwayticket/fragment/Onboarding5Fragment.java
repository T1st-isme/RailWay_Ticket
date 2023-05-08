package com.example.railwayticket.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.railwayticket.HomeActivity;
import com.example.railwayticket.R;


public class Onboarding5Fragment extends Fragment {
    private Button btngetstart;
    private View view;


    public Onboarding5Fragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view =  inflater.inflate(R.layout.fragment_onboarding5, container, false);
        btngetstart = view.findViewById(R.id.bt_start);
        btngetstart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), HomeActivity.class);
                getActivity().startActivity(i);
            }
        });
        return view;
    }
}