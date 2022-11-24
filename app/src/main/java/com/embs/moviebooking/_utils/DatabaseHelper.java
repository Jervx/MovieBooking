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

        String checkUserTable = "CREATE TABLE IF NOT EXISTS user ( uid INTEGER PRIMARY KEY AUTOINCREMENT, image TEXT, email TEXT, username TEXT, password TEXT, state INTEGER );";
        String checkMovieTable = "CREATE TABLE IF NOT EXISTS movie ( uid INTEGER PRIMARY KEY AUTOINCREMENT, moviecover TEXT, title TEXT, description TEXT, cinema TEXT, day TEXT, time TEXT, seats TEXT, taken TEXT, genre TEXT, duration TEXT, cost REAL);";
        String checkTicketTable = "CREATE TABLE IF NOT EXISTS ticket ( uid INTEGER PRIMARY KEY AUTOINCREMENT, userid INTEGER, movieid INTEGER, day TEXT, time TEXT, cinema TEXT, seatnumber INTEGER, purchaseddate TEXT, brcode TEXT);";

        // TODO DB TBLS
        db.execSQL(checkUserTable);
        db.execSQL(checkMovieTable);
        db.execSQL(checkTicketTable);
    }

    public boolean dropDbs( String[] dbNames) {
        SQLiteDatabase db = getWritableDatabase();
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
        Cursor result;
        try {
            result = this.getWritableDatabase().rawQuery(query, args);
        } catch (Exception e) {
            return null;
        }
        return result;
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