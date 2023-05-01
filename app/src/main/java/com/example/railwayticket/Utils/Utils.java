package com.example.railwayticket.Utils;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.IOException;
import java.io.InputStream;

public class Utils {
    //db
    public static final String DB_NAME = "traino.db";
    public static final int DB_VERSION = 1 ;
    public static final String TABLE_USER = "user" ;
    public static final String TABLE_TRAIN = "train" ;
    public static final String TABLE_TICKET = "ticketGO" ;
    public static final String COL_USER_ID = "user_id" ;
    public static final String COL_USER_NAME = "username" ;
    public static final String COL_USER_PASSWORD = "password" ;
    public static final String COL_TRAIN_ID = "train_id";

    public static final String COL_TICK_ID = "id";
    public static final String COL_TICKET_ID = "tickID";
    public static final String COL_TRAIN_TIMEGO = "timeGO";
    public static final String COL_TRAIN_TIMEEND = "timeEnd";
    public static final String COL_TRAIN_STATEGO = "stateGO";
    public static final String COL_TRAIN_STATEEND = "stateEnd";
    public static final String COL_TICKET_PRICE = "price";

    public static final String COL_USER_AVATAR = "avatar";


    public static Bitmap convertToBitmapFromAssets(Context context, String img) {
        AssetManager assetManager = context.getAssets();
        try {
            InputStream inputStream = assetManager.open("images/"+img);
            return BitmapFactory.decodeStream(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
