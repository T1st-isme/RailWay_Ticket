package com.example.railwayticket.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.railwayticket.R;

public class ChonTauDiActivity extends AppCompatActivity {
    Button btnnext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chon_tau_di);
        btnnext = findViewById(R.id.btnext);
        //button de bam
        btnnext.setOnClickListener(v -> {
                //tao duong dan toi Activity moi
                Intent i = new Intent(ChonTauDiActivity.this, ChonTauVeActivity.class);
                startActivity(i);
            }
        );

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

