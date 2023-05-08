package com.example.railwayticket;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.textfield.TextInputEditText;

import java.util.Objects;

public class OrderDetailActivity extends AppCompatActivity {

    TextView Ghe, trainID, ngayDi, timeGo, stateGo, stateEnd, price, tongtien;
    TextInputEditText edname, edcmnd, edphone;
    Button btPay;
    private
    int id = 0;
    SharedPreferences sp1;
    String x = "ghe";
    String y = "DiemDi";
    String z = "DiemDen";
    String trainId = "tickID";
    String dGo = "NgayDi";
    String p = "gia";
    String tckId = "id";

    @SuppressLint({"MissingInflatedId", "SetTextI18n"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_detail);
        anhxa();
        String ghe = getIntent().getStringExtra(x);
        String di = getIntent().getStringExtra(y);
        String den = getIntent().getStringExtra(z);
        String gia = getIntent().getStringExtra(p);
        String dateGo = getIntent().getStringExtra(dGo);
        String trainid = getIntent().getStringExtra(trainId);
        String giodi = getIntent().getStringExtra("GioDi");
        Ghe.setText(ghe);
        stateGo.setText(di);
        stateEnd.setText(den);
        price.setText(gia);
        trainID.setText(trainid);
        ngayDi.setText(dateGo);
        timeGo.setText(giodi);
        tongtien.setText(gia);

        btPay.setOnClickListener(view -> XacnhanThanhtoan());
    }


    public void anhxa() {
        btPay = findViewById(R.id.btPay);
        Ghe = findViewById(R.id.SoCho);
        trainID = findViewById(R.id.trainID);
        ngayDi = findViewById(R.id.tvDayGo);
        timeGo = findViewById(R.id.tvGioDi);
        stateGo = findViewById(R.id.tvNoiDi);
        stateEnd = findViewById(R.id.tvNoiDen);
        edname = findViewById(R.id.edNameOrder);
        edphone = findViewById(R.id.edPhone);
        edcmnd = findViewById(R.id.edCCCD);
        price = findViewById(R.id.Gia);
        tongtien = findViewById(R.id.TongTien);
    }

    private void XacnhanThanhtoan() {
        String x = Objects.requireNonNull(edname.getText()).toString().trim();
        String y = Objects.requireNonNull(edphone.getText()).toString().trim();
        String z = Objects.requireNonNull(edcmnd.getText()).toString().trim();
        if (TextUtils.isEmpty(x) || TextUtils.isEmpty(y) || TextUtils.isEmpty(z)) {
            Toast.makeText(this, "Vui lòng điền đẩy đủ thông tin!!!", Toast.LENGTH_SHORT).show();
        } else {
            AlertDialog.Builder alert = new AlertDialog.Builder(this);
            alert.setTitle("Xác nhận");
            alert.setMessage("Xác nhận thanh toán");
            alert.setPositiveButton("Có", (dialogInterface, i) -> {

                sp1 = getSharedPreferences("MyApp", Context.MODE_PRIVATE);
                id = Integer.parseInt(sp1.getString("id", null));
                Toast.makeText(OrderDetailActivity.this, "Thanh toán thành công", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(this, HomeActivity.class);
                insertOrderTicket(id, trainID.getText().toString(), Integer.parseInt(Ghe.getText().toString()), x, y, z);
                System.out.println(x + y + z);
                startActivity(intent);
                finish();
            });
            alert.setNegativeButton("Không", (dialogInterface, i) -> {
            });
            alert.show();
        }
    }

    public void insertOrderTicket(int id, String tickId, int seat, String name, String phone, String cmnd) {
        DBHelper db = new DBHelper(this);
        SQLiteDatabase MyDB = db.getWritableDatabase();
        MyDB.execSQL("INSERT INTO orderTick (user_id, tickID, seat, name, phone, cmnd) " +
                "VALUES((SELECT user_id FROM user WHERE user_id = ?)," +
                " (SELECT id FROM ticketGO WHERE tickID = ?)," +
                "?,?,?,?)", new Object[]{id, tickId, seat, name, phone, cmnd});
    }
}