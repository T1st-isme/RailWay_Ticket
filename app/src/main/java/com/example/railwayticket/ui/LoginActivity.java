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
import com.example.railwayticket.Sessions.SessionManager;
import com.example.railwayticket.model.User;

public class LoginActivity extends AppCompatActivity {
    Button btLog;
    TextView reg;
    EditText edUsername, edPassword;
    DBHelper DB;
    SessionManager session;
    SharedPreferences sp;
    int userId;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        anhxa();

        session = new SessionManager(getApplicationContext());
        sp = getSharedPreferences(Utils.SHARE_PREFERENCES_APP, Context.MODE_PRIVATE);
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
        userId = DB.checkUserId(name, password);
        if (TextUtils.isEmpty(name) || TextUtils.isEmpty(password)) {
            Toast.makeText(this, "Vui lòng điền đầy đủ thông tin", Toast.LENGTH_SHORT).show();
        } else {
            if (user != null) {
                Toast.makeText(this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(this, HomeActivity.class);
                session.saveSession(userId);
                i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                i.putExtra("name", user);
                startActivity(i);
            } else {
                Toast.makeText(this, "Tài khoản hoặc mật khẩu không đúng!", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
