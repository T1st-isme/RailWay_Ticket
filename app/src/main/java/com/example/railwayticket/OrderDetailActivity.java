package com.example.railwayticket;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class OrderDetailActivity extends AppCompatActivity {

    TextView tvpayMethod, Ghe, trainID, ngayDi, timeGo, stateGo, stateEnd, price, tongtien;
    EditText edname, edcmnd, edphone;
    Button btPay;
    ImageView ivPayMethod;
    private
    int id = 0;
    SharedPreferences sp;
    SharedPreferences.Editor editor;
    String x ="ghe";
    String y ="DiemDi";
    String z ="DiemDen";
    String trainId = "tickID";
    String dGo = "NgayDi";
    String p  = "gia";

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
//        ticketGO t = new ticketGO(x, y, z);
        Ghe.setText(ghe);
        stateGo.setText(di);
        stateEnd.setText(den);
        price.setText(gia);
        trainID.setText(trainid);
        ngayDi.setText(dateGo);
        tongtien.setText(gia);
        System.out.println(ghe + di + den + gia);
        tvpayMethod = findViewById(R.id.tvpay);
        ivPayMethod = findViewById(R.id.ivpay);
        ivPayMethod.setOnClickListener(view -> {
            Intent intent = new Intent(OrderDetailActivity.this, PaymentMethodActivity.class);
            startActivity(intent);
        });
        tvpayMethod.setOnClickListener(view -> {
            Intent intent = new Intent(OrderDetailActivity.this, PaymentMethodActivity.class);
            startActivity(intent);
        });
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
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle("Xác nhận");
        alert.setMessage("Xác nhận thanh toán");
        alert.setPositiveButton("Có", (dialogInterface, i) -> {
            String x = edname.getText().toString().trim();
            String y = edphone.getText().toString().trim();
            String z = edcmnd.getText().toString().trim();
            sp = getSharedPreferences("MyApp", Context.MODE_PRIVATE);
            id = Integer.parseInt(sp.getString("id",null));
            Toast.makeText(OrderDetailActivity.this, "Thanh toán thành công", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, HomeActivity.class);
            insertOrderTicket(id, trainID.getText().toString());
            InsertUserInfo(id,x,y,z);
            System.out.println(x+y+z);
            startActivity(intent);
            finish();
        });
        alert.setNegativeButton("Không", (dialogInterface, i) -> {
        });
        alert.show();
    }

    public void insertOrderTicket(int id, String tickId) {
        DBHelper db = new DBHelper(this);
        SQLiteDatabase MyDB = db.getWritableDatabase();
        MyDB.execSQL("INSERT INTO orderTick (user_id, tickID) " +
                "VALUES((SELECT user_id FROM user WHERE user_id = ?)," +
                " (SELECT tickID FROM ticketGO WHERE tickID = ?))", new Object[]{id, tickId});
    }
    public void InsertUserInfo(int id,String name, String phone, String cmnd){
        DBHelper db = new DBHelper(this);
        SQLiteDatabase MyDB = db.getWritableDatabase();
        MyDB.execSQL("INSERT INTO customer (user_id, name, phone, cmnd)" +
                    "VALUES ((SELECT user_id FROM user WHERE user_id = ?),?,?,?)", new Object[]{id,name, phone, cmnd});
    }
}