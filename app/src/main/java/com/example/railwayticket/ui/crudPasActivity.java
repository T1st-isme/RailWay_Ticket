package com.example.railwayticket.ui;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.railwayticket.DBHelper;
import com.example.railwayticket.R;
import com.example.railwayticket.adapter.UserAdapter;
import com.example.railwayticket.model.User;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.IOException;
import java.util.ArrayList;

public class crudPasActivity extends AppCompatActivity implements UserAdapter.UserCallback {
    RecyclerView rcvUser;
    ArrayList<User> lstUser;
    UserAdapter adapter;
    FloatingActionButton flb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crud_pas);
        rcvUser = findViewById(R.id.rcvPas);
        flb = findViewById(R.id.fbAdd);
        flb.setOnClickListener(v -> addUserDialog());
        //Lấy dữ liệu
        lstUser = DBHelper.getAllUsers(this);
        adapter = new UserAdapter(lstUser);
        adapter.setCallback(this);
        //gán adapter
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        rcvUser.setAdapter(adapter);
        rcvUser.setLayoutManager(linearLayoutManager);
    }

    @SuppressLint("MissingInflatedId")
    private void addUserDialog() {
        DBHelper DB = new DBHelper(this);
        try {
            DB.createDB();
            DB.OpenDatabase();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        DB.OpenDatabase();
        //khởi tạo dialog thêm người dùng
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
        alertDialog.setTitle("Thêm mới");
        LayoutInflater inflater = this.getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialog_add_user, null);
        alertDialog.setView(dialogView);

        EditText edNameC = dialogView.findViewById(R.id.edName);
        EditText edPassC = dialogView.findViewById(R.id.edPasswordAd);
        EditText edAvatarC = dialogView.findViewById(R.id.edAvatar);

        alertDialog.setPositiveButton("Đồng ý", (dialog, which) -> {
            String name = edNameC.getText().toString();
            String pass = edPassC.getText().toString();
            String avatar = edAvatarC.getText().toString();
            if (name.isEmpty() || pass.isEmpty()) {
                Toast.makeText(this,
                        "Nhập dữ liệu không hợp lệ",
                        Toast.LENGTH_LONG).show();
            } else {
                User user = new User(0, name, pass, avatar);
                long id = DBHelper.insertDataAd(crudPasActivity.this, user);
                if (id > 0) {
                    Toast.makeText(this,
                            "Thêm người dùng thành công",
                            Toast.LENGTH_LONG).show();
                    resetData();
                    dialog.dismiss();
                }
            }
        });
        alertDialog.setNegativeButton
                ("Hủy", (dialog, which) -> dialog.dismiss());
        alertDialog.create();
        alertDialog.show();
        DB.close();
    }

    @SuppressLint("MissingInflatedId")
    private void updateUserDialog(User user) {
        DBHelper DB = new DBHelper(this);
        DB.OpenDatabase();
        //khởi tạo dialog cập nhật người dùng
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
        alertDialog.setTitle("Cập nhật");
        LayoutInflater inflater = this.getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialog_add_user, null);
        alertDialog.setView(dialogView);

        EditText edNameB = dialogView.findViewById(R.id.edName);
        EditText edPassB = dialogView.findViewById(R.id.edPasswordAd);
        EditText edAvatarB = dialogView.findViewById(R.id.edAvatar);

        edNameB.setText(user.getName());
        edPassB.setText(user.getPassword());
        edNameB.setText(user.getAvatar());
        //gán dữ liệu
        alertDialog.setPositiveButton("Đồng ý", (dialog, which) -> {
            user.setName(edNameB.getText().toString().trim());
            user.setPassword(edPassB.getText().toString().trim());
            user.setAvatar(edAvatarB.getText().toString().trim());

            if (user.name.isEmpty() || user.password.isEmpty()) {
                Toast.makeText(this, "Nhập dữ liệu không hợp lệ", Toast.LENGTH_LONG).show();
            } else {
                int id = DBHelper.updateUser(this, user);
                if (id > 0) {
                    Toast.makeText(this, "Cập nhật người dùng thành công", Toast.LENGTH_LONG).show();
                    resetData();
                    dialog.dismiss();
                }
            }
        });
        alertDialog.setNegativeButton
                ("Hủy", (dialog, which) -> dialog.dismiss());
        alertDialog.create();
        alertDialog.show();
        DB.close();
    }

    @SuppressLint("NotifyDataSetChanged")
    private void resetData() {
        lstUser.clear();
        lstUser.addAll(DBHelper.getAllUsers(this));
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onItemDeleteClicked(User user, int position) {
        boolean result = DBHelper.deleteUser(this, user.id);
        if (result) {
            Toast.makeText(this,
                    "Xóa thành công",
                    Toast.LENGTH_LONG).show();
            resetData();
        } else {
            Toast.makeText(this,
                    "Xóa thất bại",
                    Toast.LENGTH_LONG).show();
        }
    }
    @Override
    public void onItemEditClicked(User user, int position) {
        updateUserDialog(user);
    }
}