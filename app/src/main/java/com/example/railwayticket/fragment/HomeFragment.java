package com.example.railwayticket.fragment;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.railwayticket.R;
import com.example.railwayticket.ui.ChonTauDiActivity;

import java.text.SimpleDateFormat;
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

    private void DateEndPicker() {
        Calendar cal = Calendar.getInstance();
        int ngay = cal.get(Calendar.DATE);
        int thang = cal.get(Calendar.MONTH);
        int nam = cal.get(Calendar.YEAR);
        DatePickerDialog dialog = new DatePickerDialog(getContext(), (datePicker, i, i1, i2) -> {
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            tvdateEnd.setText(dateFormat.format(cal.getTime()));
        }, nam, thang,ngay);
        dialog.show();
    }

    private void DateGoPicker() {
        Calendar cal = Calendar.getInstance();
        int ngay = cal.get(Calendar.DATE);
        int thang = cal.get(Calendar.MONTH);
        int nam = cal.get(Calendar.YEAR);
        DatePickerDialog dialog = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                tvdateGO.setText(dateFormat.format(cal.getTime()));
            }
        }, nam, thang,ngay);
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
