package com.example.railwayticket.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.railwayticket.HomeActivity;
import com.example.railwayticket.R;

public class LoginActivity extends AppCompatActivity {
    Button btLog;
    TextView reg;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        btLog = (Button) findViewById(R.id.btLogin);
        reg =(TextView) findViewById(R.id.tvReg);
        btLog.setOnClickListener(view -> {
            Toast.makeText(this,"Đăng nhập thành công", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
            startActivity(intent);
            finish();
        });
        reg.setOnClickListener(view ->{
            Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
            startActivity(intent);
        });



    }
}