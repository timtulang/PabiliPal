package com.example.uibasics;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

class DatabaseHelperLogin extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "Signup.db";
    private static final int DATABASE_VERSION = 1;

    public DatabaseHelperLogin(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createUserTableQuery = "CREATE TABLE allusers (user TEXT PRIMARY KEY, password TEXT, role TEXT)";
        db.execSQL(createUserTableQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS allusers");
        onCreate(db);
    }

    public boolean insertData(String user, String password, String role) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("user", user);
        values.put("password", password);
        values.put("role", role);
        long result = db.insert("allusers", null, values);
        return result != -1;
    }

    public boolean checkUser(String user) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query("allusers", null, "user=?", new String[]{user}, null, null, null);
        int count = cursor.getCount();
        cursor.close();
        return count > 0;
    }

    public boolean checkUserPassword(String user, String password) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query("allusers", null, "user=? AND password=?", new String[]{user, password}, null, null, null);
        int count = cursor.getCount();
        cursor.close();
        return count > 0;
    }

    public boolean isAdmin(String username) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query("allusers", null, "user=? AND role=?", new String[]{username, "Admin"}, null, null, null);
        int count = cursor.getCount();
        cursor.close();
        return count > 0;
    }
}
