package com.example.railwayticket;

import static com.example.railwayticket.Utils.Utils.DB_NAME;
import static com.example.railwayticket.Utils.Utils.DB_VERSION;
import static com.example.railwayticket.Utils.Utils.TABLE_TICKET;
import static com.example.railwayticket.Utils.Utils.TABLE_USER;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.example.railwayticket.Utils.Utils;
import com.example.railwayticket.model.User;
import com.example.railwayticket.model.ticketGO;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {
    String DB_PATH;

    private final Context context;

    private SQLiteDatabase sqlite;

    @SuppressLint("SdCardPath")
    public DBHelper(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        this.context = context;
        assert context != null;
        DB_PATH = "/data/data/" + context.getPackageName() + "/databases/";
    }


    public void checkDB() {
        //check if the database exists
        boolean databaseExist = checkDataBase();
        if (databaseExist) {
            // Do Nothing.
        } else {
            this.getWritableDatabase();
            copyDatabase();
        }// end if else dbExist
    }

    public boolean checkDataBase() {
        File databaseFile = new File(DB_PATH + DB_NAME);
        return databaseFile.exists();
    }

    public void copyDatabase() {
        try {
            //Open ur local db as the input stream
            InputStream myInput = context.getAssets().open(DB_NAME);
            //Path to just created emty db
            String outFileName = DB_PATH + DB_NAME;
            //Open the emty db as the output stream
            OutputStream myOutput = Files.newOutputStream(Paths.get(outFileName));
            //transfer bytes from the inputfile to the outputfile
            byte[] bytes = new byte[1024];
            int length;
            while ((length = myInput.read(bytes)) > 0) {
                myOutput.write(bytes, 0, length);
            }
            //close the stream
            myInput.close();
            myOutput.flush();
            myOutput.close();
        } catch (IOException e) {
        }
    }

    public void openDB() {
        String path = DB_PATH + DB_NAME;
        if (sqlite != null && sqlite.isOpen()) {
            return;
        }
        SQLiteDatabase.openDatabase(path, null, SQLiteDatabase.OPEN_READWRITE);
    }

    public void closeDB() {
        if (sqlite != null) {
            sqlite.close();
        }
    }

    @Override
    public void onCreate(SQLiteDatabase MyDB) {
//        MyDB.execSQL("CREATE TABLE " + TABLE_USER + " ( " +
//                Utils.COL_USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT , " +
//                Utils.COL_USER_NAME + " TEXT, " +
//                Utils.COL_USER_PASSWORD + " TEXT, " +
//                Utils.COL_USER_AVATAR + " TEXT ) ");
//        MyDB.execSQL("CREATE TABLE " + TABLE_TICKET + " ( " +
//                Utils.COL_TICK_ID + " INTEGER PRIMARY KEY AUTOINCREMENT , " +
//                Utils.COL_TICKET_ID + "  TEXT, " +
//                Utils.COL_TRAIN_TIMEGO + " DATETIME, " +
//                Utils.COL_TRAIN_TIMEEND + " DATETIME, " +
//                Utils.COL_TRAIN_STATEGO + "  TEXT, " +
//                Utils.COL_TRAIN_STATEEND + "  TEXT, " +
//                Utils.COL_TICKET_PRICE + " FLOAT ) ");
    }

    @Override
    public void onUpgrade(SQLiteDatabase MyDB, int i, int i1) {
//        MyDB.execSQL("drop Table if exists " + TABLE_USER);
//        MyDB.execSQL("drop Table if exists " + TABLE_TICKET);
//        onCreate(MyDB);
        copyDatabase();
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
        if (cursor.getCount() == 0) {
            Toast.makeText(context, "Không có dữ liệu", Toast.LENGTH_LONG).show();
        } else {
            while (!cursor.isAfterLast()) {
                int id = cursor.getInt(0);
                String name = cursor.getString(1);
                String pass = cursor.getString(2);
                String avatar = cursor.getString(3);
                lstUsers.add(new User(id, name, pass, avatar));
                cursor.moveToNext();
            }
        }
        cursor.close();
        db.closeDB();
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
    public static ArrayList<ticketGO> getAllTicket(Context context) {
        ArrayList<ticketGO> lstTicket = new ArrayList<>();
        DBHelper db = new DBHelper(context);
        db.openDB();
        SQLiteDatabase sqlite = db.getReadableDatabase();
        Cursor cursor = sqlite.rawQuery("SELECT * " +
                "from " + Utils.TABLE_TICKET, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            int id = cursor.getInt(0);
            String tckId = cursor.getString(1);
            String timego = cursor.getString(2);
            String timeend = cursor.getString(3);
            String stateGo = cursor.getString(4);
            String stateEnd = cursor.getString(5);
            String dateGo = cursor.getString(6);
            String dateEnd = cursor.getString(7);
            String price = cursor.getString(8);
            lstTicket.add(new ticketGO(id, tckId, timego, timeend, stateGo, stateEnd, dateGo, dateEnd, price));
            cursor.moveToNext();
        }
        cursor.close();
        db.closeDB();
        return lstTicket;
    }

    public long insertTicket(ticketGO ticket) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Utils.COL_TICKET_ID, ticket.getTickID());
        values.put(Utils.COL_TRAIN_TIMEGO, ticket.getTimeGO());
        values.put(Utils.COL_TRAIN_TIMEEND, ticket.getTimeEnd());
        values.put(Utils.COL_TRAIN_STATEGO, ticket.getStateGO());
        values.put(Utils.COL_TRAIN_STATEEND, ticket.getStateEnd());
        values.put("dateGO", ticket.getDateGo());
        values.put("dateEnd", ticket.getDateEnd());
        values.put(Utils.COL_TICKET_PRICE, ticket.getPrice());
        long result = MyDB.insert(TABLE_TICKET, null, values);
        if (result == -1) {
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Add successfully", Toast.LENGTH_SHORT).show();
        }
        return result;
    }

    public static boolean deleteTicket(Context context, int id) {
        DBHelper db = new DBHelper(context);
        SQLiteDatabase sqlite = db.getWritableDatabase();
        long result = sqlite.delete(TABLE_TICKET, Utils.COL_TICK_ID + " =? ", new String[]{String.valueOf(id)});
        return (result > 0);
    }

    public static int updateTicket(Context context, ticketGO ticket) {
        DBHelper db = new DBHelper(context);
        SQLiteDatabase sqlite = db.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Utils.COL_TICKET_ID, ticket.getTickID());
        values.put(Utils.COL_TRAIN_TIMEGO, ticket.getTimeGO());
        values.put(Utils.COL_TRAIN_TIMEEND, ticket.getTimeEnd());
        values.put("dateGO", ticket.getDateGo());
        values.put("dateEnd", ticket.getDateEnd());
        values.put(Utils.COL_TRAIN_STATEGO, ticket.getStateGO());
        values.put(Utils.COL_TRAIN_STATEEND, ticket.getStateEnd());
        values.put(Utils.COL_TICKET_PRICE, ticket.getPrice());
        return sqlite.update(TABLE_TICKET, values, Utils.COL_TICK_ID + " =? ", new String[]{String.valueOf(ticket.id)});
    }


    public static ArrayList<ticketGO> getAllTicketGO(Context context, ticketGO ticket) {
        ArrayList<ticketGO> lstTicket = new ArrayList<>();
        DBHelper db = new DBHelper(context);
        db.openDB();
        SQLiteDatabase sqlite = db.getReadableDatabase();
        Cursor cursor = sqlite.rawQuery("select ticketGO.id,tickID, stateGO, stateEnd , dateGO, dateEnd,timeGO,timeEnd,price " +
                        "from ticketGO " +
                        "where stateGO =? and stateEnd =? and dateGO =? order by tickID"
                , new String[]{String.valueOf(ticket.getStateGO()),
                        String.valueOf(ticket.getStateEnd()),
                        String.valueOf(ticket.getDateGo())});
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            int id = cursor.getInt(0);
            String tckid = cursor.getString(1);
            String stateG = cursor.getString(2);
            String stateE = cursor.getString(3);
            String dateGo = cursor.getString(4);
            String dateEnd = cursor.getString(5);
            String timeGO = cursor.getString(6);
            String timeEnd = cursor.getString(7);
            String price = cursor.getString(8);
            lstTicket.add(new ticketGO(id, tckid, stateG, stateE, dateGo, dateEnd, timeGO, timeEnd, price));
            cursor.moveToNext();
        }
        cursor.close();
        db.closeDB();
        return lstTicket;
    }

    public static ArrayList<ticketGO> getAllTicketM(Context context, User u) {
        ArrayList<ticketGO> lstTicket = new ArrayList<>();
        DBHelper db = new DBHelper(context);
        SQLiteDatabase sqlite = db.getReadableDatabase();
        Cursor cursor = sqlite.rawQuery("SELECT DISTINCT  ticketGO.tickID,stateGO, stateEnd, dateGO,dateEnd,timeGO, price, seat , name,phone,cmnd " +
                        "FROM ticketGO, orderTick " +
                        "WHERE orderTick.tickID = ticketGO.id " +
                        "and orderTick.user_id = ? "
                , new String[]{String.valueOf(u.id)});
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            String tckid = cursor.getString(0);
            String stateG = cursor.getString(1);
            String stateE = cursor.getString(2);
            String dateGo = cursor.getString(3);
            String dateEnd = cursor.getString(4);
            String timeGO = cursor.getString(5);
            String price = cursor.getString(6);
            int seat = cursor.getInt(7);
            String name = cursor.getString(8);
            String phone = cursor.getString(9);
            String cccd = cursor.getString(10);
            lstTicket.add(new ticketGO(tckid, stateG, stateE, dateGo,dateEnd, timeGO, price, seat, name, phone, cccd));
            cursor.moveToNext();
        }
        cursor.close();
        db.closeDB();
        return lstTicket;
    }

    public static ArrayList<ticketGO> getDetailTicket(Context context, User u, ticketGO t) {
        ArrayList<ticketGO> lstTicket = new ArrayList<>();
        DBHelper db = new DBHelper(context);
        SQLiteDatabase sqlite = db.getReadableDatabase();
        Cursor cursor = sqlite.rawQuery("SELECT DISTINCT ticketGO.id, ticketGO.tickID, name, phone, stateGO, stateEnd, dateGO,dateEnd, timeGO, price, seat " +
                        "FROM ticketGO, orderTick, user " +
                        "WHERE orderTick.tickID = ticketGO.id  " +
                        "and ticketGO.tickID = ? " +
                        "and orderTick.user_id = ? " +
                        "GROUP by orderTick.tickID "
                , new String[]{t.getTickID(), String.valueOf(u.id)});
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            int id = cursor.getInt(0);
            String tckid = cursor.getString(1);
            String name = cursor.getString(2);
            String phone = cursor.getString(3);
            String stateG = cursor.getString(4);
            String stateE = cursor.getString(5);
            String dateGo = cursor.getString(6);
            String dateEnd = cursor.getString(7);
            String timeGO = cursor.getString(8);
            String price = cursor.getString(9);
            int seat = cursor.getInt(10);
            lstTicket.add(new ticketGO(id, tckid, name, phone, stateG, stateE, dateGo, dateEnd, timeGO, price, seat));
            cursor.moveToNext();
        }
        cursor.close();
        db.closeDB();
        return lstTicket;
    }

    public static ArrayList<ticketGO> getAllOrder(Context context) {
        ArrayList<ticketGO> lstTicket = new ArrayList<>();
        DBHelper db = new DBHelper(context);
        SQLiteDatabase sqlite = db.getReadableDatabase();
        Cursor cursor = sqlite.rawQuery("SELECT DISTINCT  orderID,ticketGO.tickID,stateGO, stateEnd, dateGO,dateEnd,timeGO, price, seat , name,phone,cmnd " +
                        "FROM ticketGO, orderTick, user " +
                        "WHERE orderTick.tickID = ticketGO.id "
                , null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            int orderID = cursor.getInt(0);
            String tckid = cursor.getString(1);
            String stateG = cursor.getString(2);
            String stateE = cursor.getString(3);
            String dateGo = cursor.getString(4);
            String dateEnd = cursor.getString(5);
            String timeGO = cursor.getString(6);
            String price = cursor.getString(7);
            int seat = cursor.getInt(8);
            String name = cursor.getString(9);
            String phone = cursor.getString(10);
            String cccd = cursor.getString(11);
            lstTicket.add(new ticketGO(orderID, tckid, stateG, stateE, dateGo, dateEnd, timeGO, price, seat, name, phone, cccd));
            cursor.moveToNext();
        }
        cursor.close();
        db.closeDB();
        return lstTicket;
    }

}
