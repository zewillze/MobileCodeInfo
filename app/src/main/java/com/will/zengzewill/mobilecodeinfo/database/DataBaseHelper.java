package com.will.zengzewill.mobilecodeinfo.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by zengzewei on 16/4/21.
 */
public class DataBaseHelper extends SQLiteOpenHelper{

    public static final String TABLE_COMMENTS = "mobileInfo";
    public static final String COLUMN_ID = "id";

    public static final String JSON_STRING = "jsonString";

    private static final String DATABASE_NAME = "mobile.db";
    private static final int DATABASE_VERSION = 1;

    private static final String DATABASE_CREATE = "create table "
            + TABLE_COMMENTS + "("+COLUMN_ID+" integer primary key autoincrement,"+ JSON_STRING+" text not null);";



    public DataBaseHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(DATABASE_CREATE);
    }


    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        Log.w(DataBaseHelper.class.getName(), "Upgrading database from version "+ i + "to" + i1 + ",which will destroy old data");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS" + TABLE_COMMENTS);
        onCreate(sqLiteDatabase);
    }
}
