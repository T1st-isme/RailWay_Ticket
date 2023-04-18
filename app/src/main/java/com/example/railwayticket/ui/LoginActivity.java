package com.example.railwayticket.ui;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.railwayticket.DBHelper;
import com.example.railwayticket.HomeActivity;
import com.example.railwayticket.R;
import com.example.railwayticket.model.User;

public class LoginActivity extends AppCompatActivity {
    Button btLog;
    TextView reg;
    EditText edUsername, edPassword;
    DBHelper DB;
    SharedPreferences sp;
    private SharedPreferences.Editor editor;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        anhxa();

        sp = getSharedPreferences("MyApp", Context.MODE_PRIVATE);
        editor = sp.edit();
        boolean login = sp.getBoolean("IsLoggedin", false);
        if  (login) {
            startActivity(new Intent(this, HomeActivity.class));
            finish();
        }
        DB = new DBHelper(this);

        btLog.setOnClickListener(view -> Login());

        reg.setOnClickListener(view -> {
            Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
            startActivity(intent);
        });

    }

    private void anhxa() {
        btLog = findViewById(R.id.btLogin);
        reg = findViewById(R.id.tvReg);
        edUsername = findViewById(R.id.edUsername);
        edPassword = findViewById(R.id.edPassword);
    }

    private void Login() {
        DBHelper DB = new DBHelper(this);

        String name = edUsername.getText().toString().trim();
        String password = edPassword.getText().toString().trim();
        User user = DB.checkUserPass(name, password);
        if (TextUtils.isEmpty(name) || TextUtils.isEmpty(password)) {
            Toast.makeText(this, "Vui lòng điền đầy đủ thông tin", Toast.LENGTH_SHORT).show();
        } else if (name.equals("admin") && password.equals("admin")) {
            Intent intent = new Intent(this, AdminActivity.class);
            startActivity(intent);
        } else {
            if (user != null) {
                Toast.makeText(this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                sp = getSharedPreferences("MyApp", MODE_PRIVATE);
                editor.putString("username", name);
                editor.putString("password", password);
                editor.putBoolean("IsLoggedin", true);
                editor.apply();
                Intent i = new Intent(LoginActivity.this, HomeActivity.class);
                startActivity(i);
            } else {
                Toast.makeText(this, "Tài khoản hoặc mật khẩu không đúng!", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
