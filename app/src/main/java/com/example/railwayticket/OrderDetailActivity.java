package com.example.railwayticket;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class OrderDetailActivity extends AppCompatActivity {

    TextView tvpayMethod;
    Button btPay;
    ImageView ivPayMethod;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_detail);
        btPay = findViewById(R.id.btPay);
        tvpayMethod = (TextView) findViewById(R.id.tvpay);
        ivPayMethod = (ImageView) findViewById(R.id.ivpay);
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
    private void XacnhanThanhtoan(){
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle("Xác nhận");
        alert.setMessage("Xác nhận thanh toán");
        alert.setPositiveButton("Có", (dialogInterface, i) -> {
            Toast.makeText(OrderDetailActivity.this,"Thanh toán thành công", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, HomeActivity.class);
            startActivity(intent);
            finish();
        });
        alert.setNegativeButton("Không", (dialogInterface, i) -> {

        });
        alert.show();
    }
}