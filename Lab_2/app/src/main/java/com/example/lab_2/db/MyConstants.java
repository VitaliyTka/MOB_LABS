package com.example.lab_2.db;

public class MyConstants {
    public static final String TABLE_NAME = "cars";
    public static final String _ID = "_id";
    public static final String BRAND = "brand";
    public static final String BODY_TYPE = "body_type";
    public static final String COLOR = "color";
    public static final String ENGINE_CAPACITY = "engine_capacity";
    public static final String PRICE = "price";
    public static final String DB_NAME = "db_car.db";
    public static final int DB_VERSION = 1;

    public static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " (" +
                    _ID + " INTEGER PRIMARY KEY," +
                    BRAND + " TEXT," +
                    BODY_TYPE + " TEXT," +
                    COLOR + " TEXT," +
                    ENGINE_CAPACITY + " REAL," +
                    PRICE + " REAL)";

    public static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + TABLE_NAME;
}
