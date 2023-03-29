package com.example.railwayticket;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

import com.example.railwayticket.adapter.PayMethodAdapter;
import com.example.railwayticket.model.PaymentMethod;

public class PaymentMethodActivity extends AppCompatActivity {
ListView lsPay;
ArrayList<PaymentMethod> arrPayMethods;
PayMethodAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_method);
        anhxa();
        adapter = new PayMethodAdapter(this , R.layout.list_paymethod,arrPayMethods);
        lsPay.setAdapter(adapter);
    }

    void anhxa(){
        lsPay = (ListView)findViewById(R.id.lvPaymethod);
        arrPayMethods = new ArrayList<>();
        arrPayMethods.add(new PaymentMethod("Visa/Master Card", R.drawable.card));
        arrPayMethods.add(new PaymentMethod("MoMo", R.drawable.momo));
        arrPayMethods.add(new PaymentMethod("PayPal", R.drawable.pay_pal));

    }
}