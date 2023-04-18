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
import com.example.railwayticket.adapter.trainTicketAdapter;
import com.example.railwayticket.model.ticket;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class crudTickActivity extends AppCompatActivity implements trainTicketAdapter.TrainTickCallback {
    RecyclerView rcvTrain;
    ArrayList<ticket> lstTrain;
    trainTicketAdapter adapter;
    FloatingActionButton flb;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crud_tick);
        rcvTrain = findViewById(R.id.rcvTick);
        flb = findViewById(R.id.fbAdd);
        DBHelper db = new DBHelper(this);
        try {
            db.checkDB();
        } catch (Exception e) {
        }
        flb.setOnClickListener(v -> addDialog());
        lstTrain = DBHelper.getAllTicket(this);
        adapter = new trainTicketAdapter(lstTrain);
        adapter.setTrainCallback(this);
        LinearLayoutManager lm = new LinearLayoutManager(this);
        rcvTrain.setLayoutManager(lm);
        rcvTrain.setAdapter(adapter);
        db.closeDB();
    }

    @SuppressLint("MissingInflatedId")
    private void addDialog() {
        //khởi tạo dialog thêm người dùng
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
        alertDialog.setTitle("Thêm mới");
        LayoutInflater inflater = this.getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialog_add_item, null);
        alertDialog.setView(dialogView);
        EditText edTicketID = dialogView.findViewById(R.id.edTckID);
        EditText edTimego = dialogView.findViewById(R.id.edTimego);
        EditText edTimeend = dialogView.findViewById(R.id.edTimeend);
        EditText edNoiden = dialogView.findViewById(R.id.edNoiden);
        EditText edNoidi = dialogView.findViewById(R.id.edNoidi);
        EditText edGia = dialogView.findViewById(R.id.edPrice);
        alertDialog.setPositiveButton("Đồng ý", (dialog, which) -> {
            String ticketID = edTicketID.getText().toString();
            String gioden = edTimego.getText().toString();
            String giodi = edTimeend.getText().toString();
            String noiden = edNoiden.getText().toString();
            String noidi = edNoidi.getText().toString();
            String gia = edGia.getText().toString();
            if (ticketID.isEmpty() || giodi.isEmpty() || gioden.isEmpty() || noidi.isEmpty()
                    || noiden.isEmpty() || gia.isEmpty()) {
                Toast.makeText(this,
                        "Nhập dữ liệu không hợp lệ",
                        Toast.LENGTH_LONG).show();
            } else {
                DBHelper DB = new DBHelper(this);
                ticket tck = new ticket(0, ticketID, gioden, giodi, noidi, noiden, gia);
                long id = DB.insertTicket(tck);
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
    }

    private void updateUserDialog(ticket ticket) {
        //khởi tạo dialog cập nhật người dùng
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
        alertDialog.setTitle("Cập nhật");
        LayoutInflater inflater = this.getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialog_add_item, null);
        alertDialog.setView(dialogView);

        EditText edTicketID = dialogView.findViewById(R.id.edTckID);
        EditText edTimego = dialogView.findViewById(R.id.edTimego);
        EditText edTimeend = dialogView.findViewById(R.id.edTimeend);
        EditText edNoiden = dialogView.findViewById(R.id.edNoiden);
        EditText edNoidi = dialogView.findViewById(R.id.edNoidi);
        EditText edGia = dialogView.findViewById(R.id.edPrice);

        edTicketID.setText(ticket.getTicketId());
        edTimego.setText(ticket.getTimego());
        edTimeend.setText(ticket.getTimeend());
        edNoiden.setText(ticket.getStateEnd());
        edNoidi.setText(ticket.getStateGo());
        edGia.setText(ticket.getPrice());
        //gán dữ liệu
        alertDialog.setPositiveButton("Đồng ý", (dialog, which) -> {
            ticket.setTicketId(edTicketID.getText().toString().trim());
            ticket.setTimego(edTimego.getText().toString().trim());
            ticket.setTimeend(edTimeend.getText().toString().trim());
            ticket.setStateGo(edNoidi.getText().toString().trim());
            ticket.setStateEnd(edNoiden.getText().toString().trim());
            ticket.setPrice(edGia.getText().toString().trim());

            if (ticket.TicketId.isEmpty() || ticket.timego.isEmpty() || ticket.timeend.isEmpty()
                    || ticket.stateGo.isEmpty() || ticket.stateEnd.isEmpty() || ticket.price.isEmpty()) {
                Toast.makeText(this, "Nhập dữ liệu không hợp lệ", Toast.LENGTH_LONG).show();
            } else {
                int id = DBHelper.updateTicket(this, ticket);
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
    }


    @SuppressLint("NotifyDataSetChanged")
    private void resetData() {
        lstTrain.clear();
        lstTrain.addAll(DBHelper.getAllTicket(this));
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onItemDeleteClicked(ticket ticket, int position) {
        DBHelper db = new DBHelper(this);
        db.openDB();
        boolean result = DBHelper.deleteTicket(this, ticket.id);
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
    public void onItemEditClicked(ticket ticket, int position) {
        updateUserDialog(ticket);
    }


}