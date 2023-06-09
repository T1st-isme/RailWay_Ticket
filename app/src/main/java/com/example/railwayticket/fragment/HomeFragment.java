package com.example.railwayticket.fragment;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import androidx.fragment.app.Fragment;
import com.example.railwayticket.DBHelper;
import com.example.railwayticket.R;
import com.example.railwayticket.ui.ChonTauDiActivity;
import com.example.railwayticket.ui.LoginActivity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class HomeFragment extends Fragment {
    TextView tvdateGO, tvdateEnd;
    Spinner snGO, snEND;
    Button btSearchC;
    ArrayList<String> ticketEnd;
    ArrayList<String> ticketGo;
    Intent i;
    SharedPreferences sp;
    private SharedPreferences.Editor editor;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_home, container, false);
        sp = requireActivity().getSharedPreferences("MyApp", Context.MODE_PRIVATE);
        editor = sp.edit();
        boolean login = sp.getBoolean("IsLoggedin", false);

        anhxa(v);

        tvdateGO.setOnClickListener(view -> DateGoPicker());
        tvdateEnd.setOnClickListener(view -> DateEndPicker());
        btSearchC.setOnClickListener(view -> {
            String txtGO = snGO.getSelectedItem().toString();
            String txtEnd = snEND.getSelectedItem().toString();
            String txtDateGo = tvdateGO.getText().toString();
            String txtDateEnd = tvdateEnd.getText().toString();

            if (!login) {
                startActivity(new Intent(getActivity(), LoginActivity.class));
                Toast.makeText(getContext(), "Vui lòng đăng nhập", Toast.LENGTH_SHORT).show();
                requireActivity().finish();
            }else if (!compareDate(txtDateGo, txtDateEnd)){
                Toast.makeText(getContext(), "Ngày đi phải trước ngày đến!", Toast.LENGTH_SHORT).show();
            } else if (txtGO.equals(txtEnd)) {
                Toast.makeText(getContext(), "Nơi đi và nơi đến không được trùng!", Toast.LENGTH_SHORT).show();
            } else if (txtDateGo.equals("Ngày đi") || txtDateEnd.equals("Ngày đến")) {
                Toast.makeText(getContext(), "Vui lòng chọn ngày đi và ngày đến!", Toast.LENGTH_SHORT).show();
            } else if (txtDateGo.equals(txtDateEnd)) {
                Toast.makeText(getContext(), "Ngày đi và ngày đến không được trùng!", Toast.LENGTH_SHORT).show();
            } else {
                i = new Intent(getContext(), ChonTauDiActivity.class);
                i.putExtra("stateG", txtGO);
                i.putExtra("stateE", txtEnd);
                i.putExtra("dateGo", txtDateGo);
                i.putExtra("dateEnd", txtDateEnd);
                startActivity(i);
            }
        });
        spinnerGO();
        spinnerEnd();
        // Inflate the layout for this fragment
        return v;
    }


    //validate datepicker
    @SuppressLint("SimpleDateFormat")
    private Boolean compareDate(String Date1, String Date2) {
      String pattern =  "dd/MM/yyyy";
      SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        try{
            Date date1 = sdf.parse(Date1);
            Date date2 = sdf.parse(Date2);
            if (date1.before(date2)) System.out.println("Ngày 1 trước ngày 2");
            else {
                System.out.println("Ngày 1 sau ngày 2");
                return false;
            }
        } catch (ParseException e) {
        }
        return true;
    }

    private void spinnerEnd() {
        ticketEnd = getAllState(getContext());
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this.getActivity(), R.layout.my_spinner, ticketEnd);
        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        snEND.setAdapter(adapter);
    }

    private void spinnerGO() {
        ticketGo = getAllState(getContext());
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this.getActivity(), R.layout.my_spinner, ticketGo);
        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        snGO.setAdapter(adapter);
    }

    public ArrayList<String> getAllState(Context context) {
        ArrayList<String> lstTicket = new ArrayList<>();
        DBHelper db = new DBHelper(context);
        SQLiteDatabase sqlite = db.getReadableDatabase();
        Cursor cursor = sqlite.rawQuery("select stateGO from ticketGO group by stateGO", null);
        cursor.moveToFirst();
        if (cursor.getCount() == 0) {
            Toast.makeText(context, "Không có dữ liệu", Toast.LENGTH_LONG).show();
        } else {
            while (!cursor.isAfterLast()) {
                String state = cursor.getString(0);
                lstTicket.add(state);
                cursor.moveToNext();
            }
        }
        cursor.close();
        db.closeDB();
        return lstTicket;
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
