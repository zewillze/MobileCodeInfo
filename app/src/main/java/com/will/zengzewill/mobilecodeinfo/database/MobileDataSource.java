package com.will.zengzewill.mobilecodeinfo.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.will.zengzewill.mobilecodeinfo.model.InfoModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zengzewei on 16/4/22.
 */
public class MobileDataSource {
    private SQLiteDatabase database;
    private DataBaseHelper dataBaseHelper;
    private String[] allColumn = {DataBaseHelper.COLUMN_ID, DataBaseHelper.JSON_STRING, DataBaseHelper.PHONE_NUM};

    public MobileDataSource(Context context){
        dataBaseHelper = new DataBaseHelper(context);
    }

    public void open() throws SQLException{
        database = dataBaseHelper.getWritableDatabase();
    }

    public void close(){
        dataBaseHelper.close();
    }


    public InfoModel createInfo(String jsoninfo, String phone){
        ContentValues values = new ContentValues();
        values.put(DataBaseHelper.JSON_STRING, jsoninfo);
        values.put(DataBaseHelper.PHONE_NUM, phone);
        long insertId = database.insert(DataBaseHelper.TABLE_COMMENTS, null, values);
        Cursor cursor = database.query(DataBaseHelper.TABLE_COMMENTS, allColumn, DataBaseHelper.COLUMN_ID + "=" + insertId, null, null, null, null);
        cursor.moveToFirst();
        InfoModel infoModel = cursorToInfoModel(cursor);
        return infoModel;
    }

    public void deleteInfo(InfoModel model){
        String jsonString = model.getJsonString();
        database.delete(DataBaseHelper.TABLE_COMMENTS, DataBaseHelper.JSON_STRING + "=" + jsonString, null);
        Log.w("Delete info", jsonString);
    }

    public List<InfoModel> getAllInfos(){
        List<InfoModel> models = new ArrayList<InfoModel>();
        Cursor cursor = database.query(DataBaseHelper.TABLE_COMMENTS, allColumn, null, null, null, null, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            InfoModel model = cursorToInfoModel(cursor);
            models.add(model);
            cursor.moveToNext();
        }
        cursor.close();
        return models;
    }

    private InfoModel cursorToInfoModel(Cursor cursor){
        InfoModel model = new InfoModel();

        model.setJsonString(cursor.getString(1));
        model.setPhone(cursor.getString(2));
        return model;
    }
}
