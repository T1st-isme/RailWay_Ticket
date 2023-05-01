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
import com.example.railwayticket.model.ticketGO;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class crudTickActivity extends AppCompatActivity implements trainTicketAdapter.TrainTickCallback {
    RecyclerView rcvTrain;
    ArrayList<ticketGO> lstTrain;
    trainTicketAdapter adapter;
    FloatingActionButton flb;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crud_tick);
        rcvTrain = findViewById(R.id.rcvTick);
        flb = findViewById(R.id.fbAdd);

        flb.setOnClickListener(v -> addDialog());
        lstTrain = DBHelper.getAllTicket(this);
        adapter = new trainTicketAdapter(lstTrain);
        adapter.setTrainCallback(this);
        LinearLayoutManager lm = new LinearLayoutManager(this);
        rcvTrain.setLayoutManager(lm);
        rcvTrain.setAdapter(adapter);
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
        EditText edDateGo = dialogView.findViewById(R.id.eddatego);
        EditText edDateEnd = dialogView.findViewById(R.id.eddateend);
        EditText edNoiden = dialogView.findViewById(R.id.edNoiden);
        EditText edNoidi = dialogView.findViewById(R.id.edNoidi);
        EditText edGia = dialogView.findViewById(R.id.edPrice);
        alertDialog.setPositiveButton("Đồng ý", (dialog, which) -> {
            String ticketID = edTicketID.getText().toString();
            String gioden = edTimego.getText().toString();
            String giodi = edTimeend.getText().toString();
            String ngaydi = edDateGo.getText().toString();
            String ngayden = edDateEnd.getText().toString();
            String noiden = edNoiden.getText().toString();
            String noidi = edNoidi.getText().toString();
            String gia = edGia.getText().toString();
            if (ticketID.isEmpty() || giodi.isEmpty() || gioden.isEmpty() ||
                    ngaydi.isEmpty() || ngayden.isEmpty()
                    || noidi.isEmpty() || noiden.isEmpty() || gia.isEmpty()) {
                Toast.makeText(this,
                        "Nhập dữ liệu không hợp lệ",
                        Toast.LENGTH_LONG).show();
            } else {
                DBHelper DB = new DBHelper(this);
                ticketGO tck = new ticketGO(0, ticketID, gioden, giodi, noidi, noiden, ngaydi, ngayden, gia);
                long id = DB.insertTicket(tck);
                if (id > 0) {
                    Toast.makeText(this,
                            "Thêm vé thành công",
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

    @SuppressLint("MissingInflatedId")
    private void updateUserDialog(ticketGO ticket) {
        //khởi tạo dialog cập nhật người dùng
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
        alertDialog.setTitle("Cập nhật");
        LayoutInflater inflater = this.getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialog_add_item, null);
        alertDialog.setView(dialogView);

        EditText edTicketID = dialogView.findViewById(R.id.edTckID);
        EditText edTimego = dialogView.findViewById(R.id.edTimego);
        EditText edTimeend = dialogView.findViewById(R.id.edTimeend);
        EditText edDateGo = dialogView.findViewById(R.id.eddatego);
        EditText edDateEnd = dialogView.findViewById(R.id.eddateend);
        EditText edNoiden = dialogView.findViewById(R.id.edNoiden);
        EditText edNoidi = dialogView.findViewById(R.id.edNoidi);
        EditText edGia = dialogView.findViewById(R.id.edPrice);

        edTicketID.setText(ticket.getTickID());
        edTimego.setText(ticket.getTimeGO());
        edTimeend.setText(ticket.getTimeEnd());
        edDateGo.setText(ticket.getDateGo());
        edDateEnd.setText(ticket.getDateEnd());
        edNoiden.setText(ticket.getStateEnd());
        edNoidi.setText(ticket.getStateGO());
        edGia.setText(ticket.getPrice());
        //gán dữ liệu
        alertDialog.setPositiveButton("Đồng ý", (dialog, which) -> {
            ticket.setTickID(edTicketID.getText().toString().trim());
            ticket.setTimeGO(edTimego.getText().toString().trim());
            ticket.setTimeEnd(edTimeend.getText().toString().trim());
            ticket.setDateGo(edDateGo.getText().toString().trim());
            ticket.setDateEnd(edDateEnd.getText().toString().trim());
            ticket.setStateGO(edNoidi.getText().toString().trim());
            ticket.setStateEnd(edNoiden.getText().toString().trim());
            ticket.setPrice(edGia.getText().toString().trim());

            if (ticket.tickID.isEmpty() || ticket.timeGO.isEmpty() || ticket.timeEnd.isEmpty()
                    || ticket.stateGO.isEmpty() || ticket.stateEnd.isEmpty() ||
                    ticket.dateGo.isEmpty() || ticket.dateEnd.isEmpty() || ticket.price.isEmpty()) {
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
    public void onItemDeleteClicked(ticketGO ticket, int position) {
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
    public void onItemEditClicked(ticketGO ticket, int position) {
        updateUserDialog(ticket);
    }


}