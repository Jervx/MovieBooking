package com.embs.moviebooking._models;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.widget.Toast;

import com.embs.moviebooking._utils.DatabaseHelper;

import java.util.ArrayList;

public class Movie {
    private int uid = -1;
    private String moviecover, title, description, cinema, day, time, seats = "", taken = "", genre, duration;
    private float cost;

    public Movie(int uid, String moviecover, String title, String description, String cinema, String day, String time, String seats, String taken, String genre, String duration, float cost) {
        this.uid = uid;
        this.moviecover = moviecover;
        this.title = title;
        this.description = description;
        this.cinema = cinema;
        this.day = day;
        this.time = time;
        this.seats = seats;
        this.taken = taken;
        this.genre = genre;
        this.duration = duration;
        this.cost = cost;
    }

    public Movie(String moviecover, String title, String description, String cinema, String day, String time, String seats, String taken, String genre, String duration, float cost) {
        this.moviecover = moviecover;
        this.title = title;
        this.description = description;
        this.cinema = cinema;
        this.day = day;
        this.time = time;
        this.seats = seats;
        this.taken = taken;
        this.genre = genre;
        this.duration = duration;
        this.cost = cost;
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

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public float getCost() {
        return cost;
    }

    public void setCost(float cost) {
        this.cost = cost;
    }

    /**This converts the movie cover image into resource id (int)
     *
     * @param context
     * @return resId which correspond to the resource id of a resource file
     */
    public int getMovieCoverResID(Context context){
        return context.getResources().getIdentifier(String.format("drawable/%s", getMoviecover()), null, context.getPackageName());
    }

    public void takeSeat(int seat){
        setSeats(removeFrom(getSeats(), seat+""));
        setTaken(appendTo(getTaken(), seat+""));
    }

    public void removeTaken(int seat){
        setTaken(removeFrom(getTaken(), seat+""));
        setSeats(appendTo(getSeats(), seat+""));
    }

    public String removeFrom(String from, String what){
        String newval = "";
        for(String s : from.split(","))
            if(!s.equals(what)) newval += s + ",";
        int last = newval.length()-1;
        if( last != -1 && newval.charAt(last) == ',') newval = newval.substring(0, last);
        System.out.println("remove from "+from +" : "+what + " -> " + newval);
        return newval;
    }

    public String appendTo(String to, String what){
        String newval = to;
        if(to.length() == 0) newval += what;
        else newval += "," + what;
        System.out.println("Append to "+to +" : "+what + " -> " + newval);
        return newval;
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
        vals.put("genre", genre);
        vals.put("duration", duration);
        vals.put("cost", cost);
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
            setGenre(cur.getString(8));
            setDuration(cur.getString(9));
            setCost(cur.getFloat(10));
        }catch(Exception e){
            System.out.println("ERR ON FETCH " + e);
        }
    }

    /**Retrieve ang mga movei desu - jervx
     *
     * @param dbHelper need ko ng instance of DatabaseHelper desu
     * @return ArrayList of <Movie>
     */
    public static ArrayList <Movie> getAllMovies(DatabaseHelper dbHelper){
        ArrayList <Movie> alls = new ArrayList<>();
        Cursor all = dbHelper.execRawQuery("SELECT * FROM movie", null);

        while(all.moveToNext()){
            alls.add(new Movie(
                    all.getInt(0),
                    all.getString(1),
                    all.getString(2),
                    all.getString(3),
                    all.getString(4),
                    all.getString(5),
                    all.getString(6),
                    all.getString(7),
                    all.getString(8),
                    all.getString(9),
                    all.getString(10),
                    all.getFloat(11)
            ));
        }

        return alls;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "uid=" + uid +
                ", moviecover='" + moviecover + '\'' +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", cinema='" + cinema + '\'' +
                ", day='" + day + '\'' +
                ", time='" + time + '\'' +
                ", seats='" + seats + '\'' +
                ", taken='" + taken + '\'' +
                ", genre='" + genre + '\'' +
                ", duration='" + duration + '\'' +
                ", cost=" + cost +
                '}';
    }
}