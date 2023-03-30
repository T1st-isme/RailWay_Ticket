package com.example.railwayticket.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.railwayticket.DBHelper;
import com.example.railwayticket.HomeActivity;
import com.example.railwayticket.R;

public class LoginActivity extends AppCompatActivity {
    Button btLog;
    TextView reg;
    EditText edUsername, edPassword;
    DBHelper DB;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        anhxa();

        DB = new DBHelper(this);

        btLog.setOnClickListener(view -> {
            String name = edUsername.getText().toString().trim();
            String password = edPassword.getText().toString().trim();
            if (name.equals("") || password.equals("")) {
                Toast.makeText(this, "Vui lòng điền đầy đủ thông tin", Toast.LENGTH_SHORT).show();
            }
            else {
                Boolean checkuserpass = DB.checkUserPass(name, password);
                if (checkuserpass){
                    Toast.makeText(this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(this,HomeActivity.class);
                    startActivity(i);
                }else {
                    Toast.makeText(this, "Tài khoản hoặc mật khẩu không đúng!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        reg.setOnClickListener(view -> {
            Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
            startActivity(intent);
        });
    }

    private void anhxa() {
        btLog = (Button) findViewById(R.id.btLogin);
        reg = (TextView) findViewById(R.id.tvReg);
        edUsername = (EditText) findViewById(R.id.edUsername);
        edPassword = (EditText) findViewById(R.id.edPassword);
    }
}