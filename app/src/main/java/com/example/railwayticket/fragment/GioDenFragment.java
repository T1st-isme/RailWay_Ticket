package com.example.railwayticket.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.fragment.app.Fragment;

import com.example.railwayticket.R;

import java.util.ArrayList;


public class GioDenFragment extends Fragment {
    ListView lvgiodenc;
    ArrayList<String> danhsach1;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_gio_den,container,false);
        // Inflate the layout for this fragment
        lvgiodenc = view.findViewById(R.id.lvgioden);
        danhsach1 = new ArrayList<String>();
        danhsach1.add("Thông tin vé");
        danhsach1.add("Thông tin vé");
        danhsach1.add("Thông tin vé");
        danhsach1.add("Thông tin vé");
        danhsach1.add("Thông tin vé");
        danhsach1.add("Thông tin vé");

        ArrayAdapter<String> adapter=new ArrayAdapter<String> (getContext(), android.R.layout.simple_list_item_1,danhsach1);
        lvgiodenc.setAdapter(adapter);
        return view;
    }
}