package com.example.railwayticket.ui;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.railwayticket.DBHelper;
import com.example.railwayticket.R;
import com.example.railwayticket.Utils.Utils;
import com.example.railwayticket.model.User;
import com.google.gson.Gson;


public class RegisterActivity extends AppCompatActivity {
    private SharedPreferences.Editor editor;
    SharedPreferences sharedPref;
    private final Gson gson = new Gson();
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
        sharedPref = getSharedPreferences(Utils.SHARE_PREFERENCES_APP, MODE_PRIVATE);
        editor = sharedPref.edit();

        btnRegister.setOnClickListener(view -> Register());
    }

    private void anhxa() {
        btnRegister = findViewById(R.id.btRegister);
        edUsername = findViewById(R.id.edUsername);
        edPassword = findViewById(R.id.edPassword);
        edEmail = findViewById(R.id.edEmail);
        edCPassword = findViewById(R.id.edConfirmPassword);
    }

    private boolean checkPassword(String password, String confirmPassword) {
        if (password.isEmpty()) {
            edPassword.setError("Vui lòng nhập mật khẩu");
            return false;
        }
        if (password.length() <= 5) {
            edPassword.setError("Mật khẩu phải có ít nhất 6 ký tự");
            return false;
        }
        if (!password.equals(confirmPassword)) {
            edPassword.setError("Mật khẩu không khớp");
            return false;
        }
        return true;
    }

    private boolean checkUsername(String username) {
        if (username.isEmpty()) {
            edUsername.setError("Vui lòng nhập tên đăng nhập");
            return false;
        }
        if (username.length() <= 5) {
            edUsername.setError("Tên đăng nhập phải có 6 ký tự");
            return false;
        }
        return true;
    }

    private void Register() {
        DBHelper DB = new DBHelper(this);
        User user = new User();
        user.setName(edUsername.getText().toString().trim());
        user.setPassword(edPassword.getText().toString().trim());
        user.setPassword(edCPassword.getText().toString().trim());
        user.setAvatar(edEmail.getText().toString().trim());
        User temp = DB.checkUsername(edUsername.getText().toString());
        if (temp == null) {
            Toast.makeText(this, "Vui lòng điền đầy đủ thông tin", Toast.LENGTH_SHORT).show();

            if (DB.insertDataReg(user)) {
                Toast.makeText(this, "Đăng ký thành công", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);
            } else {
                Toast.makeText(this, "Đăng ký không thành công", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "Tài khoản đã tồn tại", Toast.LENGTH_SHORT).show();
        }
//        if (name.equals("") || password.equals("") || cpassword.equals("") || email.equals("")) {
        //            Toast.makeText(this, "Vui lòng điền đầy đủ thông tin", Toast.LENGTH_SHORT).show();
        //        } else {
//            if (password.equals(cpassword)) {
//                Boolean checkUser = DB.checkUsername(name);
//                if (!checkUser) {


//                        Toast.makeText(this, "Đăng ký không thành công", Toast.LENGTH_SHORT).show();
//                    }
//                }
//                else {
//                    Toast.makeText(this, "Tài khoản đã được đăng ký! Vui lòng đăng nhập.", Toast.LENGTH_SHORT).show();
//                }
//            }
//            else {
//                Toast.makeText(this, "Mật khẩu không khớp!", Toast.LENGTH_SHORT).show();
//            }

    }
}

//        String username = edUsername.getText().toString().trim();
//        String password = edPassword.getText().toString().trim();
//        String confirmPassword = edCPassword.getText().toString().trim();
//        boolean isValid = checkUsername(username) && checkPassword(password, confirmPassword);
//        //nếu dữ liệu hợp lệ, tạo đối tượng lưu vô sharedPreference
//        if (isValid) {
//            User user = new User();
//            user.setName(username);
//            user.setPassword(password);
//            //convert to json
//            String userStr = gson.toJson(user);
//            editor.putString(Utils.KEY_USER, userStr);
//            editor.commit();
//            //Toast thông báo đk thành công
//            Toast.makeText(RegisterActivity.this, "Đăng ký thành công", Toast.LENGTH_LONG).show();
//            //finish
//            finish();
//        }






