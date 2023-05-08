package com.example.railwayticket.fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import com.example.railwayticket.HomeActivity;
import com.example.railwayticket.R;
import com.example.railwayticket.Utils.Utils;
import com.example.railwayticket.ui.*;


public class AccManagerFragment extends Fragment {
    TextView Account;
    ImageView avt;
    Button btLogout, btHuongdan, btlienhe, btquydinh;
    private String username = "";
    private String avtar = "";
    SharedPreferences sp;
    SharedPreferences.Editor editor;

    @SuppressLint({"MissingInflatedId", "SetTextI18n"})
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_acc_manager, container, false);

        anhxa(v);

        sp = requireActivity().getSharedPreferences("MyApp", Context.MODE_PRIVATE);
        username = sp.getString("username", null);
        avtar = sp.getString("avt", null);
        Account.setText(username);


        if (avtar != null) {
            avt.setImageBitmap(Utils.convertToBitmapFromAssets(getContext(), avtar));
        } else {
            avt.setImageBitmap(Utils.convertToBitmapFromAssets(getContext(), "male.png"));
        }

        btHuongdan.setOnClickListener(view -> {
            Intent i = new Intent(getActivity(), HuongDan.class);
            startActivity(i);
        });
        btlienhe.setOnClickListener(view -> {
            Intent i = new Intent(getActivity(), LienHe.class);
            startActivity(i);
        });
        btquydinh.setOnClickListener(view -> {
            Intent i = new Intent(getActivity(), QuyDinh.class);
            startActivity(i);
        });

        Account.setOnClickListener(view -> {
            if (username == null) {
                Intent i = new Intent(getActivity(), LoginActivity.class);
                startActivity(i);
            } else {
                Intent i2 = new Intent(getActivity(), AccInfoActivity.class);
                startActivity(i2);
            }
        });

        btLogout = v.findViewById(R.id.btnLogout);
        sp = requireActivity().getSharedPreferences("MyApp", Context.MODE_PRIVATE);
        editor = sp.edit();
        if (username == null) {
            btLogout.setVisibility(View.GONE);
        } else {
            btLogout.setVisibility(View.VISIBLE);
            btLogout.setOnClickListener(view -> logout());
        }
        return v;
    }

    private void anhxa(View v) {
        Account = v.findViewById(R.id.Account);
        avt = v.findViewById(R.id.IvAvt);
        btquydinh = v.findViewById(R.id.btnquydinh);
        btHuongdan = v.findViewById(R.id.btnHuongdan);
        btlienhe = v.findViewById(R.id.btnLienhe);

    }

    private void logout() {
        editor.clear();
        editor.apply();
        startActivity(new Intent(getActivity(), HomeActivity.class));
        getActivity().finish();
    }

    private boolean shouldRefreshOnResume = false;

    // Reload current fragment
    @Override
    public void onResume() {
        super.onResume();
        // Check should we need to refresh the fragment
        if (shouldRefreshOnResume) {
            // refresh fragment
            getActivity().recreate();
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        shouldRefreshOnResume = true;
    }

}