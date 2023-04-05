package com.example.railwayticket.ui;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.IOException;
import java.io.InputStream;

public class Utils {
    public static final String SHARE_PREFERENCES_APP = "share_preferences_app";
    public static final String KEY_USER = "Key_user";
    public static final String KEY_USER_PROFILE = "Key_user_profile";

    //db
    public static final String DB_NAME = "traino.db";
    public static final int DB_VERSION = 1 ;
    public static final String TABLE_USER = "user" ;
    public static final String COL_USER_ID = "user_id" ;
    public static final String COL_USER_NAME = "username" ;
    public static final String COL_USER_PASSWORD = "password" ;
    private static final String COL_USER_AVATAR = "avatar";


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
