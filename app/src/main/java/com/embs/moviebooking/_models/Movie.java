package com.embs.moviebooking._models;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.widget.Toast;

import com.embs.moviebooking._utils.DatabaseHelper;

public class Movie {
    private int uid;
    private String moviecover, title, description, cinema, day, time, seats, taken;

    public Movie(int uid, String moviecover, String title, String description, String cinema, String day, String time, String seats, String taken) {
        this.uid = uid;
        this.moviecover = moviecover;
        this.title = title;
        this.description = description;
        this.cinema = cinema;
        this.day = day;
        this.time = time;
        this.seats = seats;
        this.taken = taken;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getMoviecover() {
        return moviecover;
    }

    public void setMoviecover(String moviecover) {
        this.moviecover = moviecover;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCinema() {
        return cinema;
    }

    public void setCinema(String cinema) {
        this.cinema = cinema;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getSeats() {
        return seats;
    }

    public void setSeats(String seats) {
        this.seats = seats;
    }

    public String getTaken() {
        return taken;
    }

    public void setTaken(String taken) {
        this.taken = taken;
    }

    private ContentValues getSelfContentValues(){
        ContentValues vals = new ContentValues();

        vals.put("moviecover", moviecover);
        vals.put("title", title);
        vals.put("description", description);
        vals.put("cinema", cinema);
        vals.put("day", day);
        vals.put("time", time);
        vals.put("seats", seats);
        vals.put("taken", taken);

        return vals;
    }

    public boolean saveState(Context context, DatabaseHelper dbHelper, boolean isNew){
        if(isNew){

            if(dbHelper.insert(getSelfContentValues(), "movie")){
                System.out.println("Movie : New movie Saved Self");
                return true;
            }else{
                Toast.makeText(null, "Failed to create movie", Toast.LENGTH_LONG);
                return false;
            }
        }else{
            if( !dbHelper.update(getSelfContentValues(), "uid="+this.uid+"", "movie") ){
                Toast.makeText(context, "Failed to save state", Toast.LENGTH_LONG);
                System.out.println("Movie : Updated Self");
                return false;
            }else{
                fetchSelf(dbHelper);
                return true;
            }
        }
    }

    public void fetchSelf(DatabaseHelper dbHelper){
        try{
            Cursor cur = dbHelper.execRawQuery(String.format("SELECT * FROM movie WHERE uid=%d;", uid), null);
            if (cur == null || cur.getCount() == 0 || !cur.moveToNext()) return;
            setUid(cur.getInt(0));
            setTitle(cur.getString(1));
            setDescription(cur.getString(2));
            setCinema(cur.getString(3));
            setDay(cur.getString(4));
            setTime(cur.getString(5));
            setSeats(cur.getString(6));
            setTaken(cur.getString(7));
        }catch(Exception e){
            System.out.println("ERR ON FETCH " + e);
        }
    }

}
