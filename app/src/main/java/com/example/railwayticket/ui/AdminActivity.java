package com.example.railwayticket.ui;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.railwayticket.R;

public class AdminActivity extends AppCompatActivity {
TextView train, pas, tick;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adminactivity);
        train = findViewById(R.id.order);
        pas = findViewById(R.id.crudPas);
        tick = findViewById(R.id.crudTicket);
        FuncIntent();
    }

    private void FuncIntent() {
        train.setOnClickListener(v -> {
            Intent intent = new Intent(this, ManageOrderActivity.class);
            startActivity(intent);
        });
        pas.setOnClickListener(v -> {
            Intent intent = new Intent(this, crudPasActivity.class);
            startActivity(intent);
        });
        tick.setOnClickListener(v -> {
            Intent intent = new Intent(this, crudTickActivity.class);
            startActivity(intent);
        });
    }
}