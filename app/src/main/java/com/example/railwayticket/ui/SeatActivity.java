package com.example.railwayticket.ui;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.railwayticket.OrderDetailActivity;
import com.example.railwayticket.R;

import java.util.ArrayList;
import java.util.List;

public class SeatActivity extends AppCompatActivity implements View.OnClickListener {
    ViewGroup layout;

    String seats =
            "___/"
                    + "U_A/"
                    + "R_A/"
                    + "U_U/"
                    + "R_A/"
                    + "R_A/"
                    + "R_A/"
                    + "___/"
                    + "R_A/"
                    + "R_A/"
                    + "R_A/"
                    + "R_A/"
                    + "R_A/"
                    + "R_A/"
                    + "___/"
                    + "R_A/"
                    + "R_A/"
                    + "R_A/"
                    + "R_A/"
                    + "R_A/"
                    + "R_A/"
                    + "___/"
                    + "R_A/"
                    + "R_A/"
                    + "R_A/"
                    + "R_A/"
                    + "R_A/"
                    + "R_A/"
                    + "___/"
                    + "R_A/"
                    + "R_A/"
                    + "R_A/"
                    + "R_A/"
                    + "R_A/"
                    + "R_A/"
                    + "___/"
                    + "R_A/"
                    + "R_A/"
                    + "R_A/"
                    + "R_A/"
                    + "R_A/"
                    + "R_A/"
                    + "___/"
                    + "R_A/"
                    + "R_A/"
                    + "R_A/"
                    + "R_A/"
                    + "R_A/"
                    + "R_A/"
                    + "___/"
                    + "R_A/"
                    + "R_A/"
                    + "R_A/"
                    + "R_A/"
                    + "R_A/"
                    + "R_A/"
                    + "___/"
                    + "R_A/"
                    + "R_A/"
                    + "R_A/"
                    + "R_A/"
                    + "R_A/"
                    + "R_A/"
                    + "___/"
                    + "R_A/"
                    + "R_A/"
                    + "R_A/"
                    + "R_A/"
                    + "R_A/"
                    + "R_A/"
                    + "___/"
                    + "R_A/"
                    + "R_A/"
                    + "R_A/"
                    + "R_A/"
                    + "R_A/"
                    + "R_A/"
                    + "___/"
                    + "R_A/"
                    + "R_A/"
                    + "R_A/"
                    + "R_A/"
                    + "R_A/"
                    + "R_A/"
                    + "___/"
                    + "R_A/"
                    + "R_A/"
                    + "R_A/"
                    + "R_A/"
                    + "R_A/"
                    + "R_A/"
                    + "___/";
    Button btContinue;

    List<TextView> seatViewList = new ArrayList<>();
    int seatSize = 100;
    int seatGaping = 10;

    int STATUS_AVAILABLE = 1;
    int STATUS_BOOKED = 2;
    int STATUS_RESERVED = 3;
    String selectedIds = "";

    @SuppressLint({"MissingInflatedId", "SetTextI18n"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.seat_layout);
        btContinue = findViewById(R.id.btTiep);
        btContinue.setOnClickListener(v -> continu());

        layout = findViewById(R.id.layoutSeat);

        seats = "/" + seats;

        LinearLayout layoutSeat = new LinearLayout(this);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutSeat.setOrientation(LinearLayout.VERTICAL);
        layoutSeat.setLayoutParams(params);
        layoutSeat.setPadding(8 * seatGaping, 8 * seatGaping, 8 * seatGaping, 8 * seatGaping);
        layout.addView(layoutSeat);

        LinearLayout layout = null;

        int count = 0;

        for (int index = 0; index < seats.length(); index++) {
            if (seats.charAt(index) == '/') {
                layout = new LinearLayout(this);
                layout.setOrientation(LinearLayout.HORIZONTAL);
                layoutSeat.addView(layout);
            } else if (seats.charAt(index) == 'U') {
                count++;
                TextView view = new TextView(this);
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(seatSize, seatSize);
                layoutParams.setMargins(seatGaping, seatGaping, seatGaping, seatGaping);
                view.setLayoutParams(layoutParams);
                view.setPadding(0, 0, 0, 2 * seatGaping);
                view.setId(count);
                view.setGravity(Gravity.CENTER);
                view.setBackgroundResource(R.drawable.ic_seats_booked);
                view.setTextColor(Color.WHITE);
                view.setTag(STATUS_BOOKED);
                view.setText(count + "");
                view.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 9);
                assert layout != null;
                layout.addView(view);
                seatViewList.add(view);
                view.setOnClickListener(this);
            } else if (seats.charAt(index) == 'A') {
                count++;
                TextView view = new TextView(this);
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(seatSize, seatSize);
                layoutParams.setMargins(seatGaping, seatGaping, seatGaping, seatGaping);
                view.setLayoutParams(layoutParams);
                view.setPadding(0, 0, 0, 2 * seatGaping);
                view.setId(count);
                view.setGravity(Gravity.CENTER);
                view.setBackgroundResource(R.drawable.ic_seats_book);
                view.setText(count + "");
                view.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 9);
                view.setTextColor(Color.BLACK);
                view.setTag(STATUS_AVAILABLE);
                assert layout != null;
                layout.addView(view);
                seatViewList.add(view);
                view.setOnClickListener(this);
            } else if (seats.charAt(index) == 'R') {
                count++;
                TextView view = new TextView(this);
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(seatSize, seatSize);
                layoutParams.setMargins(seatGaping, seatGaping, seatGaping, seatGaping);
                view.setLayoutParams(layoutParams);
                view.setPadding(0, 0, 0, 2 * seatGaping);
                view.setId(count);
                view.setGravity(Gravity.CENTER);
                view.setBackgroundResource(R.drawable.ic_seats_reserved);
                view.setText(count + "");
                view.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 9);
                view.setTextColor(Color.WHITE);
                view.setTag(STATUS_RESERVED);
                assert layout != null;
                layout.addView(view);
                seatViewList.add(view);
                view.setOnClickListener(this);
            } else if (seats.charAt(index) == '_') {
                TextView view = new TextView(this);
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(seatSize, seatSize);
                layoutParams.setMargins(seatGaping, seatGaping, seatGaping, seatGaping);
                view.setLayoutParams(layoutParams);
                view.setBackgroundColor(Color.TRANSPARENT);
                view.setText("");
                assert layout != null;
                layout.addView(view);
            }
        }
    }


    Intent i;

    @Override
    public void onClick(View view) {
        if ((int) view.getTag() == STATUS_AVAILABLE) {
            if (selectedIds.contains(view.getId() + ",")) {
                selectedIds = selectedIds.replace(view.getId() + ",", "");
                view.setBackgroundResource(R.drawable.ic_seats_book);
            } else {
                selectedIds = selectedIds + view.getId() + ",";
                view.setBackgroundResource(R.drawable.ic_seats_selected);
                String x =  getIntent().getStringExtra("NoiDi");
                String y =  getIntent().getStringExtra("NoiDen");
                i = new Intent(this, OrderDetailActivity.class);
                i.putExtra("ghe", String.valueOf(view.getId()));
                i.putExtra("DiemDi", String.valueOf(x));
                i.putExtra("DiemDen", String.valueOf(y));
            }
        } else if ((int) view.getTag() == STATUS_BOOKED) {
            Toast.makeText(this, "Seat " + view.getId() + " is Booked", Toast.LENGTH_SHORT).show();
        } else if ((int) view.getTag() == STATUS_RESERVED) {
            Toast.makeText(this, "Seat " + view.getId() + " is Reserved", Toast.LENGTH_SHORT).show();
        }
    }

    public void continu() {
        startActivity(i);
    }
}

