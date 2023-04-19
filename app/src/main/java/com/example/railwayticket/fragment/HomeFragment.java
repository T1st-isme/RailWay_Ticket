package com.example.railwayticket.fragment;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.railwayticket.DBHelper;
import com.example.railwayticket.R;
import com.example.railwayticket.model.ticketGO;
import com.example.railwayticket.ui.ChonTauDiActivity;

import java.util.ArrayList;
import java.util.Calendar;

public class HomeFragment extends Fragment {
    TextView tvdateGO, tvdateEnd;
    Spinner snGO, snEND;
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
        snGO.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view,
                                       int position, long id) {
                Object item = adapterView.getItemAtPosition(position);
                if (item != null) {
                    Toast.makeText(getContext(), item.toString(),
                            Toast.LENGTH_SHORT).show();
                }
                Toast.makeText(getContext(), "Selected",
                        Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                // TODO Auto-generated method stub

            }
        });

        loadSpinner();

        // Inflate the layout for this fragment
        return v;
    }

    private void loadSpinner() {
        ArrayList<ticketGO> lstDepart = DBHelper.getAll(getContext());
        lstDepart.add(0, new ticketGO(0, "Chọn địa điểm"));
        ArrayAdapter<ticketGO> ticketGOArrayAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item, lstDepart);
        ticketGOArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        snGO.setAdapter(ticketGOArrayAdapter);
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
