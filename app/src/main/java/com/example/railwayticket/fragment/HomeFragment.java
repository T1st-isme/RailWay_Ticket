package com.example.railwayticket.fragment;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.railwayticket.DBHelper;
import com.example.railwayticket.R;
import com.example.railwayticket.ui.ChonTauDiActivity;

import java.util.ArrayList;
import java.util.Calendar;

public class HomeFragment extends Fragment {
    TextView tvdateGO, tvdateEnd;
    Spinner snGO, snEND;
    Button btSearchC;
    ArrayList<String> ticketEnd;
    ArrayList<String> ticketGo;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_home, container, false);
        anhxa(v);

        tvdateGO.setOnClickListener(view -> DateGoPicker());
        tvdateEnd.setOnClickListener(view -> DateEndPicker());
        btSearchC.setOnClickListener(view -> {
            String txtGO = snGO.getSelectedItem().toString();
            String txtEnd = snEND.getSelectedItem().toString();
            if (txtGO.equals(txtEnd)) {
                Toast.makeText(getContext(), "Nơi đi và nơi đến không được trùng!", Toast.LENGTH_SHORT).show();
            } else if (tvdateGO.getText().toString().equals("Ngày đi")) {
                Toast.makeText(getContext(), "Vui lòng chọn ngày đi", Toast.LENGTH_LONG).show();
            } else if (tvdateEnd.getText().toString().equals("Ngày về")) {
                Toast.makeText(getContext(), "Vui lòng chọn ngày về", Toast.LENGTH_LONG).show();
            } else {
                Intent intent = new Intent(getContext(), ChonTauDiActivity.class);
                intent.putExtra("state", txtGO);
                startActivity(intent);
            }
        });
        spinnerGO();
        spinnerEnd();
        // Inflate the layout for this fragment
        return v;
    }

    private void spinnerEnd() {
        DBHelper db = new DBHelper(getContext());
        ticketEnd = db.getAllTicketGO(getContext());
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this.getActivity(), R.layout.my_spinner, ticketEnd);
        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        snEND.setAdapter(adapter);
    }

    private void spinnerGO() {
        DBHelper db = new DBHelper(getContext());
        ticketGo = db.getAllTicketGO(getContext());
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this.getActivity(), R.layout.my_spinner, ticketGo);
        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        snGO.setAdapter(adapter);
    }


    Calendar cal = Calendar.getInstance();
    final int ngay = cal.get(Calendar.DATE);
    final int thang = cal.get(Calendar.MONTH);
    final int nam = cal.get(Calendar.YEAR);


    private void DateEndPicker() {

        DatePickerDialog dialog = new DatePickerDialog(getContext(), (datePicker, i, i1, i2) -> {
            i1 = i1 + 1;
            String date = i2 + "/" + i1 + "/" + i;
            tvdateEnd.setText(date);
        }, nam, thang, ngay);
        dialog.show();
    }

    private void DateGoPicker() {
        DatePickerDialog dialog = new DatePickerDialog(getContext(), (datePicker, i, i1, i2) -> {
            i1 = i1 + 1;
            String date = i2 + "/" + i1 + "/" + i;
            tvdateGO.setText(date);
        }, nam, thang, ngay);
        dialog.show();
    }

    void anhxa(View v) {
        tvdateGO = v.findViewById(R.id.tvDayStart);
        tvdateEnd = v.findViewById(R.id.tvDayEnd);
        snGO = v.findViewById(R.id.snplaceGo);
        snEND = v.findViewById(R.id.snplaceEnd);
        btSearchC = v.findViewById(R.id.btSearch);
    }


}
