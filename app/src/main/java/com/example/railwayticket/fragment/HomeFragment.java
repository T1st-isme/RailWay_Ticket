package com.example.railwayticket.fragment;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.railwayticket.R;
import com.example.railwayticket.ui.ChonTauDiActivity;

import java.util.Calendar;

public class HomeFragment extends Fragment {
    TextView tvdateGO, tvdateEnd, tvplaceGO, tvplaceEnd;
    Button btSearchC;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_home, container, false);
        anhxa(v);
        tvdateGO.setOnClickListener(view -> DateGoPicker());
        tvdateEnd.setOnClickListener(view -> DateEndPicker());
        btSearchC.setOnClickListener(view -> {
            Intent intent = new Intent(getContext(), ChonTauDiActivity.class);
            startActivity(intent);
        });
        // Inflate the layout for this fragment
        return v;
    }
    Calendar cal = Calendar.getInstance();
    final int ngay = cal.get(Calendar.DATE);
    final int thang = cal.get(Calendar.MONTH);
    final int nam = cal.get(Calendar.YEAR);


    private void DateEndPicker() {

        DatePickerDialog dialog = new DatePickerDialog(getContext(), (datePicker, i, i1, i2) -> {
            i1= i1+1;
            String date = i2 + "/" + i1 + "/" + i;
            tvdateEnd.setText(date);
        }, nam, thang, ngay);
        dialog.show();
    }

    private void DateGoPicker() {

        DatePickerDialog dialog = new DatePickerDialog(getContext(), (datePicker, i, i1, i2) -> {
            i1= i1+1;
            String date = i2 + "/" + i1 + "/" + i;
            tvdateGO.setText(date);
        }, nam, thang, ngay);
        dialog.show();
    }

    void anhxa(View v) {
        tvdateGO = v.findViewById(R.id.tvDayStart);
        tvdateEnd = v.findViewById(R.id.tvDayEnd);
        tvplaceGO = v.findViewById(R.id.tvplaceGo);
        tvplaceEnd = v.findViewById(R.id.tvplaceEnd);
        btSearchC = v.findViewById(R.id.btSearch);
    }
}
