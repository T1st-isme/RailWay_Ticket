package com.example.railwayticket;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class OrderDetailActivity extends AppCompatActivity {

    TextView tvpayMethod, Ghe, trainID, ngayDi, timeGo, stateGo, stateEnd;
    Button btPay;
    ImageView ivPayMethod;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_detail);
        anhxa();
        String x = getIntent().getStringExtra("ghe");
        String y =  getIntent().getStringExtra("DiemDi");
        String z =  getIntent().getStringExtra("DiemDen");
        Ghe.setText(x);
        stateGo.setText(y);
        stateEnd.setText(z);
        System.out.println(x + y + z);
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
        btPay.setOnClickListener(view -> {
            XacnhanThanhtoan();
        });
    }

    public void anhxa() {
        btPay = findViewById(R.id.btPay);
        Ghe = findViewById(R.id.SoCho);
        trainID = findViewById(R.id.trainID);
        ngayDi = findViewById(R.id.tvDayGo);
        timeGo = findViewById(R.id.tvGioDi);
        stateGo = findViewById(R.id.tvNoiDi);
        stateEnd = findViewById(R.id.tvNoiDen);
    }

    private void XacnhanThanhtoan() {
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle("Xác nhận");
        alert.setMessage("Xác nhận thanh toán");
        alert.setPositiveButton("Có", (dialogInterface, i) -> {
            Toast.makeText(OrderDetailActivity.this, "Thanh toán thành công", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, HomeActivity.class);
            startActivity(intent);
            finish();
        });
        alert.setNegativeButton("Không", (dialogInterface, i) -> {
        });
        alert.show();
    }
}