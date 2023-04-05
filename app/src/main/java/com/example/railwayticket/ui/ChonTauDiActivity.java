package com.example.railwayticket.ui;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.railwayticket.R;
import com.example.railwayticket.fragment.ChoConFragment;
import com.example.railwayticket.fragment.GioDenFragment;
import com.example.railwayticket.fragment.GioDiFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class ChonTauDiActivity extends AppCompatActivity {
    BottomNavigationView mnBottom;
    Button btnnext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chon_tau_di);
        mnBottom = findViewById(R.id.navMenu);
        btnnext = findViewById(R.id.btnext);
        //button de bam
        mnBottom.setOnItemSelectedListener(getListener());
        btnnext.setOnClickListener(v -> {
                //tao duong dan toi Activity moi
                Intent i = new Intent(ChonTauDiActivity.this, ChonTauVeActivity.class);
                startActivity(i);
            }
        );
        getSupportFragmentManager().beginTransaction().add(R.id.main_fragment, new ChoConFragment()).commit();

    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return true;
    }

    @SuppressLint("NonConstantResourceId")
    @NonNull
    private NavigationBarView.OnItemSelectedListener getListener() {
        return item -> {

            switch (item.getItemId()) {
                case R.id.mnchocon:
                    loadFragment(new ChoConFragment());
                    break;
                case R.id.mnthoigiandi:
                    loadFragment(new GioDiFragment());
                    break;
                case R.id.mnthoigianden:
                    loadFragment(new GioDenFragment());
                    break;
               /* case R.id.mnthoigianchay:
                    loadFragment(new ThoiGianChayFragment());
                    break;*/
            }
            return true;
        };
    }




    //ham de load fragment
    void loadFragment(Fragment fmNew) {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction fmTran = fm.beginTransaction();
        fmTran.replace(R.id.main_fragment, fmNew);
        fmTran.addToBackStack(null);
        fmTran.commit();
    }
}

