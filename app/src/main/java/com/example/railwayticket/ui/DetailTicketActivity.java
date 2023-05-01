package com.example.railwayticket.ui;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.railwayticket.DBHelper;
import com.example.railwayticket.R;
import com.example.railwayticket.adapter.DetailAdapter;
import com.example.railwayticket.model.User;
import com.example.railwayticket.model.ticketGO;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;

import java.util.ArrayList;

public class DetailTicketActivity extends AppCompatActivity {
    private int userID = 0;
    SharedPreferences sp;
    ImageView qrImage;
    RecyclerView rcvDetails;
    DetailAdapter adapter;
    ArrayList<ticketGO> lstDetails;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_ticket);
        qrImage = findViewById(R.id.QRCode);
        sp = getSharedPreferences("MyApp", Context.MODE_PRIVATE);
        try {
            userID = Integer.parseInt(sp.getString("id", null));
        } catch (Exception e) {
        }
        rcvDetails = findViewById(R.id.rcvDetail);
        String tickID = getIntent().getStringExtra("tickID");
        String stateG = getIntent().getStringExtra("stateG");
        String stateE = getIntent().getStringExtra("stateE");
        String timeGo = getIntent().getStringExtra("timeGo");
        String DateGo = getIntent().getStringExtra("DateGo");
        int ghe = getIntent().getIntExtra("ghe",0);
        String price = getIntent().getStringExtra("price");
        ticketGO t = new ticketGO(0, tickID, "", "", stateG, stateE, DateGo, "", timeGo, price, ghe);
        User user = new User(userID, "", "", "");
        lstDetails = DBHelper.getDetailTicket(this, user, t);
        adapter = new DetailAdapter(lstDetails);
        LinearLayoutManager lm = new LinearLayoutManager(this);
        rcvDetails.setAdapter(adapter);
        rcvDetails.setLayoutManager(lm);


        MultiFormatWriter writer = new MultiFormatWriter();
        try {
            BitMatrix matrix = writer.encode("Ticket ID: " +tickID + "\n" +
                                                        "Trip: " +stateG  + " - " +stateE + "\n" +
                                                        "Time: " +timeGo + "\n" +
                                                        "Day: "+DateGo + "\n" +
                                                        "Seat ID: "+ghe + "\n" +
                                                        "Price: "+price, BarcodeFormat.QR_CODE, 250,250);
            BarcodeEncoder encoder = new BarcodeEncoder();
            Bitmap bitmap = encoder.createBitmap(matrix);
            qrImage.setImageBitmap(bitmap);
        } catch (WriterException e) {
            throw new RuntimeException(e);
        }

    }

}