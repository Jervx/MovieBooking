package com.embs.moviebooking._utils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.Date;

public class DatabaseHelper extends SQLiteOpenHelper {

    public DatabaseHelper(Context context) {
        super(context, "MovieBooking.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

    }

    public void checkTableExist() {
        SQLiteDatabase db = this.getWritableDatabase();
        String checkUserTable = "CREATE TABLE IF NOT EXISTS user ( uid INTEGER PRIMARY KEY AUTOINCREMENT, email TEXT, username TEXT, password TEXT );";
        // TODO DB TBLS
        db.execSQL(checkUserTable);
    }

    public boolean dropDbs(SQLiteDatabase db, String[] dbNames) {
        for (String dbName : dbNames)
            db.execSQL(String.format("drop Table if exists %s", dbName));
        return true;
    }

    public boolean truncateDbs(String[] dbNames) {
        SQLiteDatabase db = getWritableDatabase();
        for (String dbName : dbNames)
            db.execSQL(String.format("DELETE FROM %s", dbName));
        db.close();
        return true;
    }

    public Cursor execRawQuery(String query, String[] args) {
        try (Cursor result = this.getWritableDatabase().rawQuery(query, args)){
            return result;
        } catch (Exception e) {
            return null;
        }
    }

    public boolean insert(ContentValues values, String table){
        SQLiteDatabase db = getWritableDatabase();
        long result = db.insert(table, null, values);
        return result != -1 ;
    }

    public boolean update(ContentValues values, String condition, String table){
        SQLiteDatabase db = getWritableDatabase();
        long result = db.update(table, values, condition , null);
        return result != -1 ;
    }

}