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
public class GioDIFragment extends Fragment {
    ListView lvgiodi;
    ArrayList<String> danhsach1;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_gio_di,container,false);
        // Inflate the layout for this fragment
        lvgiodi = view.findViewById(R.id.lvgiodi);
        danhsach1 = new ArrayList<>();
        danhsach1.add("Thông tin vé");
        danhsach1.add("Thông tin vé");
        danhsach1.add("Thông tin vé");
        danhsach1.add("Thông tin vé");
        danhsach1.add("Thông tin vé");
        danhsach1.add("Thông tin vé");
        ArrayAdapter<String> adapter= new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, danhsach1);
        lvgiodi.setAdapter(adapter);
        return view;



    }
}