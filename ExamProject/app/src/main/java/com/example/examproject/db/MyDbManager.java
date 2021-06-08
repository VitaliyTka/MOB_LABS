package com.example.examproject.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class MyDbManager {
    private Context context;
    private MyDbHelper myDbHelper;
    private SQLiteDatabase db;

    public MyDbManager(Context context) {
        this.context = context;
        myDbHelper = new MyDbHelper(context);
    }
    public void openDb(){
        db = myDbHelper.getWritableDatabase();
    }
    public void insertToDb(String brand,
                           String body_type,
                           String color,
                           double engine_capacity,
                           double price){
        ContentValues cv = new ContentValues();
        cv.put(MyConstants.BRAND, brand);
        cv.put(MyConstants.BODY_TYPE, body_type);
        cv.put(MyConstants.COLOR, color);
        cv.put(MyConstants.ENGINE_CAPACITY, engine_capacity);
        cv.put(MyConstants.PRICE, price);
        db.insert(MyConstants.TABLE_NAME, null, cv);
    }
    public List<String> readBrandFromDb(){
        List<String> brandList = new ArrayList<>();
        Cursor cursor = db.query(MyConstants.TABLE_NAME,
                null,
                null,
                null,
                null,
                null,
                null);
        while (cursor.moveToNext()){
            brandList.add(cursor.getString(cursor.getColumnIndex(MyConstants.BRAND)));
        }
        cursor.close();
        return brandList;
    }
    public List<String> readBodyTypeFromDb(){
        List<String> brandList = new ArrayList<>();
        Cursor cursor = db.query(MyConstants.TABLE_NAME,
                null,
                null,
                null,
                null,
                null,
                null);
        while (cursor.moveToNext()){
            brandList.add(cursor.getString(cursor.getColumnIndex(MyConstants.BODY_TYPE)));
        }
        cursor.close();
        return brandList;
    }
    public List<String> readColorFromDb(){
        List<String> brandList = new ArrayList<>();
        Cursor cursor = db.query(MyConstants.TABLE_NAME,
                null,
                null,
                null,
                null,
                null,
                null);
        while (cursor.moveToNext()){
            brandList.add(cursor.getString(cursor.getColumnIndex(MyConstants.COLOR)));
        }
        cursor.close();
        return brandList;
    }
    public List<String> readEngineCapacityFromDb(){
        List<String> brandList = new ArrayList<>();
        Cursor cursor = db.query(MyConstants.TABLE_NAME,
                null,
                null,
                null,
                null,
                null,
                null);
        while (cursor.moveToNext()){
            brandList.add(cursor.getString(cursor.getColumnIndex(MyConstants.ENGINE_CAPACITY)));
        }
        cursor.close();
        return brandList;
    }
    public List<String> readPriceFromDb(){
        List<String> brandList = new ArrayList<>();
        Cursor cursor = db.query(MyConstants.TABLE_NAME,
                null,
                null,
                null,
                null,
                null,
                null);
        while (cursor.moveToNext()){
            brandList.add(cursor.getString(cursor.getColumnIndex(MyConstants.PRICE)));
        }
        cursor.close();
        return brandList;
    }
    public List<String> readIDFromDb(){
        List<String> idList = new ArrayList<>();
        Cursor cursor = db.query(MyConstants.TABLE_NAME,
                null,
                null,
                null,
                null,
                null,
                null);
        while (cursor.moveToNext()){
            idList.add(cursor.getString(cursor.getColumnIndex(MyConstants._ID)));
        }
        cursor.close();
        return idList;
    }
    public void deleteByID(String id) {
        db.delete(MyConstants.TABLE_NAME, "_id = " + id, null);
    }
    public void closeDb(){
        myDbHelper.close();
    }
}
