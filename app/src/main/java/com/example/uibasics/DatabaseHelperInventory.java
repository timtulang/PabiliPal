package com.example.uibasics;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelperInventory extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "inventory.db";
    private static final int DATABASE_VERSION = 1;

    public static final String TABLE_NAME = "inventory";
    public static final String COLUMN_ID = "id"; // Primary Key
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_PRICE = "price";
    public static final String COLUMN_STOCK = "stock";
    public static final String COLUMN_IMAGE = "image";

    private static final String TABLE_CREATE =
            "CREATE TABLE " + TABLE_NAME + " (" +
                    COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COLUMN_NAME + " TEXT, " +
                    COLUMN_PRICE + " REAL, " +
                    COLUMN_STOCK + " INTEGER, " +
                    COLUMN_IMAGE + " BLOB);";
//    private static final String createTriggerQuery = "CREATE TRIGGER IF NOT EXISTS sort_data_trigger " +
//            "AFTER INSERT ON " + TABLE_NAME +
//            " BEGIN " +
//            "    UPDATE " + TABLE_NAME + " SET " + COLUMN_NAME + " = (SELECT " + COLUMN_NAME + " FROM " + TABLE_NAME +
//            " ORDER BY " + COLUMN_NAME + ") WHERE " + COLUMN_ID + " = new." + COLUMN_ID + ";" +
//            " END;";

    public DatabaseHelperInventory(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_CREATE);
//        db.execSQL(createTriggerQuery);
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
}
