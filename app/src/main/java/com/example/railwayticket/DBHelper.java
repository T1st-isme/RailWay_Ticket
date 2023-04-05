package com.example.railwayticket;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.railwayticket.model.User;
import com.example.railwayticket.ui.Utils;

public class DBHelper extends SQLiteOpenHelper {



    public DBHelper(@Nullable Context context) {
        super(context, Utils.DB_NAME, null, Utils.DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase MyDB) {
        MyDB.execSQL("CREATE TABLE " + Utils.TABLE_USER + " ( " +
                Utils.COL_USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT , " +
                Utils.COL_USER_NAME + " TEXT, " +
                Utils.COL_USER_PASSWORD +" TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase MyDB, int i, int i1) {
        MyDB.execSQL("drop Table if exists " + Utils.TABLE_USER);
        onCreate(MyDB);
    }

    public Boolean insertData(User user) {
        boolean result;
        try {
            SQLiteDatabase MyDB = this.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put(Utils.COL_USER_NAME, user.getName());
            values.put(Utils.COL_USER_PASSWORD, user.getPassword());
            result = MyDB.insert(Utils.TABLE_USER, null, values) > 0;
        }catch (Exception e){
            result = false;
        }
        return result;
    }

    public User checkUsername(String username) {
        User user = null;
        try {
            SQLiteDatabase MyDB = this.getWritableDatabase();
            Cursor cursor = MyDB.rawQuery("select * from " + Utils.TABLE_USER
                    + " where username = ? ", new String[]{username});
            if (cursor.moveToFirst()){
                user = new User();
                user.setId(cursor.getInt(0));
                user.setName(cursor.getString(1));
            }
        }catch (Exception e) {
            user = null;
        }
        return user;
    }

    public User checkUserPass(String username, String password) {
        User user = null;
        try {
            SQLiteDatabase MyDB = this.getWritableDatabase();
            Cursor cursor = MyDB.rawQuery("select * from " + Utils.TABLE_USER
                    + " where username = ? and password = ? ", new String[]{username, password});
            if (cursor.moveToFirst()){
                user = new User();
                user.setId(cursor.getInt(0));
                user.setName(cursor.getString(1));
                user.setPassword(cursor.getString(2));
            }
        }catch (Exception e) {
            user = null;
        }
        return user;
    }

    public int checkUserId(String username, String password){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor getID = MyDB.rawQuery("select "+Utils.COL_USER_ID+" from " + Utils.TABLE_USER
                + " where username = ? and password = ? ", new String[]{username, password});
        if (getID == null && getID.moveToFirst()){
            return getID.getInt(0);
        }
        else {
            return 0;
        }
    }

    //FUNC DELETE
//    public static boolean delete(Context context, int id) {
//        DBHelper db = new DBHelper(context);
//        SQLiteDatabase sqlite = db.getWritableDatabase();
//        int result = sqlite.delete(Utils.TABLE_USER, Utils.COLUMN_USER_ID + " =? ", new String[] {String.valueOf(id)});
//        return (result > 0);
//    }

    //update
//    public static int update(Context context, User user) {
//        DBHelper db = new DBHelper(context);
//        SQLiteDatabase sqlite = db.getWritableDatabase();
//        ContentValues values = new ContentValues();
//        values.put(COL_USER_NAME, user.getName());
//        values.put(COL_USER_AVATAR, user.getAvatar());
//        return sqlite.update(TABLE_USER, values, COL_USER_ID + " =? ", new String[] {String.valueOf(user.id)});
//    }
}
