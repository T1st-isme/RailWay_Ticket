package com.example.railwayticket.ui;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.railwayticket.DBHelper;
import com.example.railwayticket.R;
import com.example.railwayticket.model.User;

public class AccInfoActivity extends AppCompatActivity {
    EditText edUname, edPassword, edCpassword, edEmail, edAvatar;
    Button btnSubmit;
    SharedPreferences sp;
    SharedPreferences.Editor editor;
    private int id = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        anhxa();

        sp = getSharedPreferences("MyApp", MODE_PRIVATE);
        id = Integer.parseInt(sp.getString("id", null));

        btnSubmit.setOnClickListener(v -> updateUserInfo());

    }

    private void updateUserInfo() {
        DBHelper DB = new DBHelper(this);
        String avt = edAvatar.getText().toString().trim();
        String username = edUname.getText().toString().trim();
        String password = edPassword.getText().toString().trim();
        String Cpassword = edCpassword.getText().toString().trim();
        String email = edEmail.getText().toString().trim();
        User temp = DB.checkUsername(username);
        if (username.isEmpty() || password.isEmpty() ||
                Cpassword.isEmpty() || email.isEmpty() || avt.isEmpty()) {
            Toast.makeText(this,
                    "Vui lòng nhập đầy đủ thông tin",
                    Toast.LENGTH_LONG).show();
        } else if (!password.equals(Cpassword)) {
            Toast.makeText(this,
                    "Mật khẩu không khớp",
                    Toast.LENGTH_LONG).show();
        } else if (temp != null) {
            Toast.makeText(this, "Tài khoản đã tồn tại", Toast.LENGTH_SHORT).show();
        } else {
            User user = new User(id, "", "", "");
            user.setName(username);
            user.setPassword(password);
            user.setAvatar(avt);
            DBHelper.updateUser(this, user);
            sp = getSharedPreferences("MyApp", MODE_PRIVATE);
            editor = sp.edit();
            editor.putString("avt", avt);
            editor.putString("username", username);
            editor.putString("password", password);
            editor.apply();
            Toast.makeText(this,
                    "Thay đổi thành công",
                    Toast.LENGTH_LONG).show();
            finish();
        }
    }

    private void anhxa() {
        edUname = findViewById(R.id.edUname);
        edPassword = findViewById(R.id.edPass);
        edCpassword = findViewById(R.id.edCpass);
        edEmail = findViewById(R.id.edMail);
        edAvatar = findViewById(R.id.avt);
        btnSubmit = findViewById(R.id.btSubmit);
    }
}