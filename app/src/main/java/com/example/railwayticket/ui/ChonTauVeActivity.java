package com.example.railwayticket.ui;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.railwayticket.R;

public class ChonTauVeActivity extends AppCompatActivity {
    Button btChonGhe;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chon_tau_ve);
        btChonGhe = findViewById(R.id.btnChonGhe);
        //button de bam
        btChonGhe.setOnClickListener(v -> {
            Intent i = new Intent(this, SeatActivity.class);
            startActivity(i);
        });

    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return true;
    }

}


