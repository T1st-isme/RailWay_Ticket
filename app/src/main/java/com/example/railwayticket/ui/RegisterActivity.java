package com.example.railwayticket.ui;

import static android.R.id.home;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.railwayticket.DBHelper;
import com.example.railwayticket.R;

public class RegisterActivity extends AppCompatActivity {
    Button btnRegister;
    EditText edUsername, edPassword, edCPassword, edEmail;
    DBHelper DB;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        anhxa();

        DB = new DBHelper(this);

        btnRegister.setOnClickListener(view -> Register());
    }

    private void Register() {
        String name = edUsername.getText().toString().trim();
        String password = edPassword.getText().toString().trim();
        String cpassword = edCPassword.getText().toString().trim();
        String email = edEmail.getText().toString().trim();
        if (name.equals("") || password.equals("") || cpassword.equals("") || email.equals("")) {
            Toast.makeText(this, "Vui lòng điền đầy đủ thông tin", Toast.LENGTH_SHORT).show();
        } else {
            if (password.equals(cpassword)) {
                Boolean checkUser = DB.checkUsername(name);
                if (!checkUser) {
                    Boolean insert = DB.insertData(name, password);
                    if (insert) {
                        Toast.makeText(this, "Đăng ký thành công", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                        startActivity(intent);
                    }
                    else {
                        Toast.makeText(this, "Đăng ký không thành công", Toast.LENGTH_SHORT).show();
                    }
                }
                else {
                    Toast.makeText(this, "Tài khoản đã được đăng ký! Vui lòng đăng nhập.", Toast.LENGTH_SHORT).show();
                }
            }
            else {
                Toast.makeText(this, "Mật khẩu không khớp!", Toast.LENGTH_SHORT).show();
            }
        }
    }


    private void anhxa() {
        btnRegister = (Button) findViewById(R.id.btRegister);
        edUsername = (EditText) findViewById(R.id.edUsername);
        edPassword = (EditText) findViewById(R.id.edPassword);
        edEmail = (EditText) findViewById(R.id.edEmail);
        edCPassword = (EditText) findViewById(R.id.edConfirmPassword);
    }
}