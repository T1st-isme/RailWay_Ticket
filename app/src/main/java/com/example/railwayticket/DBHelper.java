package com.example.railwayticket;

import static com.example.railwayticket.Utils.Utils.TABLE_TICKET;
import static com.example.railwayticket.Utils.Utils.TABLE_USER;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.example.railwayticket.Utils.Utils;
import com.example.railwayticket.model.User;
import com.example.railwayticket.model.ticket;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {


    private final Context context;

    public DBHelper(@Nullable Context context) {
        super(context, Utils.DB_NAME, null, Utils.DB_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase MyDB) {
        MyDB.execSQL("CREATE TABLE " + TABLE_USER + " ( " +
                Utils.COL_USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT , " +
                Utils.COL_USER_NAME + " TEXT, " +
                Utils.COL_USER_PASSWORD + " TEXT, " +
                Utils.COL_USER_AVATAR + " TEXT ) ");
        MyDB.execSQL("CREATE TABLE " + TABLE_TICKET + " ( " +
                Utils.COL_TICK_ID + " INTEGER PRIMARY KEY AUTOINCREMENT , " +
                Utils.COL_TICKET_ID + "  TEXT, " +
                Utils.COL_TRAIN_TIMEGO + " DATETIME, " +
                Utils.COL_TRAIN_TIMEEND + " DATETIME, " +
                Utils.COL_TRAIN_STATEGO + "  TEXT, " +
                Utils.COL_TRAIN_STATEEND + "  TEXT, " +
                Utils.COL_TICKET_PRICE + " FLOAT ) ");
    }

    @Override
    public void onUpgrade(SQLiteDatabase MyDB, int i, int i1) {
        MyDB.execSQL("drop Table if exists " + TABLE_USER);
        MyDB.execSQL("drop Table if exists " + TABLE_TICKET);
        onCreate(MyDB);
    }

    public static long insertDataAd(Context context, User user) {
        DBHelper db = new DBHelper(context);
        SQLiteDatabase sqlite = db.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Utils.COL_USER_NAME, user.name);
        values.put(Utils.COL_USER_PASSWORD, user.password);
        values.put(Utils.COL_USER_AVATAR, user.avatar);
        return sqlite.insert(Utils.TABLE_USER, null, values);
    }

    public Boolean insertDataReg(User user) {
        boolean result;
        try {
            SQLiteDatabase MyDB = this.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put(Utils.COL_USER_NAME, user.getName());
            values.put(Utils.COL_USER_PASSWORD, user.getPassword());
            result = MyDB.insert(TABLE_USER, null, values) > 0;
        } catch (Exception e) {
            result = false;
        }
        return result;
    }

    public User checkUsername(String username) {
        User user = null;
        try {
            SQLiteDatabase MyDB = this.getWritableDatabase();
            Cursor cursor = MyDB.rawQuery("select * from " + TABLE_USER
                    + " where username = ? ", new String[]{username});
            if (cursor.moveToFirst()) {
                user = new User();
                user.setId(cursor.getInt(0));
                user.setName(cursor.getString(1));
            }
        } catch (Exception e) {
            user = null;
        }
        return user;
    }

    public User checkUserPass(String username, String password) {
        User user = null;
        try {
            SQLiteDatabase MyDB = this.getWritableDatabase();
            Cursor cursor = MyDB.rawQuery("select * from " + TABLE_USER
                    + " where username = ? and password = ? ", new String[]{username, password});
            if (cursor.moveToFirst()) {
                user = new User();
                user.setId(cursor.getInt(0));
                user.setName(cursor.getString(1));
                user.setPassword(cursor.getString(2));
            }
        } catch (Exception e) {
            user = null;
        }
        return user;
    }


    public static ArrayList<User> getAllUsers(Context context) {
        ArrayList<User> lstUsers = new ArrayList<>();
        DBHelper db = new DBHelper(context);
        SQLiteDatabase sqlite = db.getReadableDatabase();
        Cursor cursor = sqlite.rawQuery("select * from " + Utils.TABLE_USER, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            int id = cursor.getInt(0);
            String name = cursor.getString(1);
            String pass = cursor.getString(2);
            String avatar = cursor.getString(3);
            lstUsers.add(new User(id, name,pass, avatar));
            cursor.moveToNext();
        }
        cursor.close();
        sqlite.close();
        return lstUsers;
    }


    public static boolean deleteUser(Context context, int id) {
        DBHelper db = new DBHelper(context);
        SQLiteDatabase sqlite = db.getWritableDatabase();
        int result = sqlite.delete(Utils.TABLE_USER, Utils.COL_USER_ID + " =? ", new String[]{String.valueOf(id)});
        return (result > 0);
    }

    public static int updateUser(Context context, User user) {
        DBHelper db = new DBHelper(context);
        SQLiteDatabase sqlite = db.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Utils.COL_USER_NAME, user.getName());
        return sqlite.update(TABLE_USER, values, Utils.COL_USER_ID + " =? ", new String[]{String.valueOf(user.id)});
    }

    //Ticket
    public static ArrayList<ticket> getAllTicket(Context context) {
        ArrayList<ticket> lstTicket = new ArrayList<>();
        DBHelper db = new DBHelper(context);
        SQLiteDatabase sqlite = db.getReadableDatabase();
        Cursor cursor = sqlite.rawQuery("select * from " + Utils.TABLE_TICKET, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            int id = cursor.getInt(0);
            String tckId = cursor.getString(1);
            String timego = cursor.getString(2);
            String timeend = cursor.getString(3);
            String statego = cursor.getString(4);
            String stateend = cursor.getString(5);
            String price = cursor.getString(6);
            lstTicket.add(new ticket(id, tckId, timego, timeend, statego, stateend, price));
            cursor.moveToNext();
        }
        cursor.close();
        sqlite.close();
        return lstTicket;

    }

    public long insertTicket(ticket ticket) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Utils.COL_TICKET_ID, ticket.getTicketId());
        values.put(Utils.COL_TRAIN_TIMEGO, ticket.getTimego());
        values.put(Utils.COL_TRAIN_TIMEEND, ticket.getTimeend());
        values.put(Utils.COL_TRAIN_STATEGO, ticket.getStateGo());
        values.put(Utils.COL_TRAIN_STATEEND, ticket.getStateEnd());
        values.put(Utils.COL_TICKET_PRICE, ticket.getPrice());
        long result = MyDB.insert(TABLE_TICKET, null, values);
        if (result == -1){
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(context, "Add successfully", Toast.LENGTH_SHORT).show();
        }
        return result;
    }

    public static boolean deleteTicket(Context context ,int id) {
        DBHelper db = new DBHelper(context);
        SQLiteDatabase sqlite = db.getWritableDatabase();
        long result = sqlite.delete(TABLE_TICKET, Utils.COL_TICK_ID + " =? ", new String[]{String.valueOf(id)});
        return (result > 0);
    }

    public static int updateTicket( Context context ,ticket ticket) {
        DBHelper db = new DBHelper(context);
        SQLiteDatabase sqlite = db.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Utils.COL_TICKET_ID, ticket.getTicketId());
        values.put(Utils.COL_TRAIN_TIMEGO, ticket.getTimego());
        values.put(Utils.COL_TRAIN_TIMEEND, ticket.getTimeend());
        values.put(Utils.COL_TRAIN_STATEGO, ticket.getStateGo());
        values.put(Utils.COL_TRAIN_STATEEND, ticket.getStateEnd());
        values.put(Utils.COL_TICKET_PRICE, ticket.getPrice());
        return sqlite.update(TABLE_TICKET, values, Utils.COL_TICK_ID + " =? ", new String[]{String.valueOf(ticket.id)});
    }
}
