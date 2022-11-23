package com.embs.moviebooking._models;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.widget.Toast;

import com.embs.moviebooking._utils.DatabaseHelper;

import java.util.ArrayList;

public class Ticket {
    int uid, userid, movieid, seatnumber;
    String day, time, cinema, purchaseddate ;

    public Ticket(int uid, int userid, int movieid, int seatnumber, String day, String time, String cinema, String purchaseddate) {
        this.uid = uid;
        this.userid = userid;
        this.movieid = movieid;
        this.seatnumber = seatnumber;
        this.day = day;
        this.time = time;
        this.cinema = cinema;
        this.purchaseddate = purchaseddate;
    }

    public Ticket(int userid, int movieid, int seatnumber, String day, String time, String cinema, String purchaseddate) {
        this.userid = userid;
        this.movieid = movieid;
        this.seatnumber = seatnumber;
        this.day = day;
        this.time = time;
        this.cinema = cinema;
        this.purchaseddate = purchaseddate;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public int getMovieid() {
        return movieid;
    }

    public void setMovieid(int movieid) {
        this.movieid = movieid;
    }

    public int getSeatnumber() {
        return seatnumber;
    }

    public void setSeatnumber(int seatnumber) {
        this.seatnumber = seatnumber;
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

    public String getCinema() {
        return cinema;
    }

    public void setCinema(String cinema) {
        this.cinema = cinema;
    }

    public String getPurchaseddate() {
        return purchaseddate;
    }

    public void setPurchaseddate(String purchaseddate) {
        this.purchaseddate = purchaseddate;
    }

    private ContentValues getSelfContentValues(){
        ContentValues vals = new ContentValues();
        vals.put("userid", this.userid);
        vals.put("movieid", this.movieid);
        vals.put("day", this.day);
        vals.put("time", this.time);
        vals.put("cinema", this.cinema);
        vals.put("seatnumber", this.seatnumber);
        vals.put("purchaseddate", this.purchaseddate);

        return vals;
    }

    public boolean saveState(Context context, DatabaseHelper dbHelper, boolean isNew){
        if(isNew){
            if(dbHelper.insert(getSelfContentValues(), "ticket")){
                System.out.println("Ticket : Ticket Saved Self");
                return true;
            }else{
                Toast.makeText(null, "Failed to create ticket", Toast.LENGTH_LONG);
                return false;
            }
        }else{
            if( !dbHelper.update(getSelfContentValues(), "uid="+this.uid, "ticket") ){
                Toast.makeText(context, "Failed to save ticket state", Toast.LENGTH_LONG);
                System.out.println("Ticket : Updated Self");
                return false;
            }else{
                fetchSelf(dbHelper);
                return true;
            }
        }
    }

    public void fetchSelf(DatabaseHelper dbHelper){
        try{
            Cursor cur = dbHelper.execRawQuery(String.format("SELECT * FROM ticket WHERE uid=%d;", uid), null);
            if (cur == null || cur.getCount() == 0 || !cur.moveToNext()) return;
            setUid(cur.getInt(0));
            setUserid(cur.getInt(1));
            setMovieid(cur.getInt(2));
            setDay(cur.getString(3));
            setTime(cur.getString(4));
            setCinema(cur.getString(5));
            setSeatnumber(cur.getInt(6));
            setPurchaseddate(cur.getString(7));
        }catch(Exception e){
            System.out.println("ERR ON FETCH " + e);
        }
    }

    /**Retrieve ang mga all ticket desu - jervx
     *
     * @param dbHelper need ko ng instance of DatabaseHelper desu
     * @return ArrayList of <Ticket>
     */
    public static ArrayList<Ticket> getAllTickets(DatabaseHelper dbHelper){
        ArrayList <Ticket> all = new ArrayList<>();

        Cursor tkts = dbHelper.execRawQuery("SELECT * FROM ticket", null);
        while(tkts.moveToNext()) all.add(new Ticket(
                tkts.getInt(0),
                tkts.getInt(1),
                tkts.getInt(2),
                tkts.getString(3),
                tkts.getString(4),
                tkts.getString(5),
                tkts.getString(6)
        ));

        return all;
    }

    /**Retrieve ang mga all ticket of the specified user desu - jervx
     *
     * @param dbHelper need ko ng instance of DatabaseHelper desu
     * @param uid need ko din ng unique id ng user
     * @return ArrayList of <Movie>
     */
    public static ArrayList<Ticket> getAllUserTickets(DatabaseHelper dbHelper, int uid){
        ArrayList <Ticket> all = new ArrayList<>();

        Cursor tkts = dbHelper.execRawQuery("SELECT * FROM ticket where userid="+uid, null);
        while(tkts.moveToNext()) all.add(new Ticket(
                tkts.getInt(0),
                tkts.getInt(1),
                tkts.getInt(2),
                tkts.getString(3),
                tkts.getString(4),
                tkts.getString(5),
                tkts.getString(6)
        ));

        return all;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "uid=" + uid +
                ", userid=" + userid +
                ", movieid=" + movieid +
                ", seatnumber=" + seatnumber +
                ", day='" + day + '\'' +
                ", time='" + time + '\'' +
                ", cinema='" + cinema + '\'' +
                ", purchaseddate='" + purchaseddate + '\'' +
                '}';
    }
}
