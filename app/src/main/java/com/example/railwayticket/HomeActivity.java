package com.example.railwayticket;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.railwayticket.fragment.AccManagerFragment;
import com.example.railwayticket.fragment.HomeFragment;
import com.example.railwayticket.fragment.TicketManageFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class HomeActivity extends AppCompatActivity  {
    BottomNavigationView mnBottom;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        mnBottom = findViewById(R.id.bottomNavigationView);
        DBHelper db = new DBHelper(this);
        try {
            db.checkDB();
        } catch (Exception e) {
        }
        try {
            db.openDB();
        } catch (Exception e) {
        }
        //load lên fragment
        mnBottom.setOnItemSelectedListener(getListener());
        getSupportFragmentManager().beginTransaction().replace(R.id.main_fragment, new HomeFragment()).commit();
        db.closeDB();
    }

    @SuppressLint("NonConstantResourceId")
    @NonNull
    private NavigationBarView.OnItemSelectedListener getListener() {
        return item -> {
            switch (item.getItemId()) {
                case R.id.mnMuaVe:
                    loadFragment(new HomeFragment());
                    break;
                case R.id.mnDonMuaVe:
                    loadFragment(new TicketManageFragment());
                    break;
                case R.id.mnCaNhan:
                    loadFragment(new AccManagerFragment());
                    break;
            }
            return true;
        };
    }

    /*private View.OnClickListener IntentToPickTrain() {
        return view -> {
        Intent intent = new Intent(this, PickTrain.class);
        startActivity(intent);
        };
    }*/
    //Hàm load Fragment
    void loadFragment(Fragment fragment) {
        FragmentManager fmManager = getSupportFragmentManager();
        FragmentTransaction fm = fmManager.beginTransaction();
        fm.replace(R.id.main_fragment, fragment);
        fm.addToBackStack(null);
        fm.commit();
    }

}