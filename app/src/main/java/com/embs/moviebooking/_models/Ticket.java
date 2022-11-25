package com.embs.moviebooking._models;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.widget.Toast;

import com.embs.moviebooking._utils.DatabaseHelper;

import java.util.ArrayList;
import java.util.Arrays;

public class Ticket {
    int uid, userid, movieid, seatnumber;
    String day, time, cinema, purchaseddate, brcode ;

    public Ticket(int uid, int userid, int movieid, int seatnumber, String day, String time, String cinema, String purchaseddate, String brcode) {
        this.uid = uid;
        this.userid = userid;
        this.movieid = movieid;
        this.seatnumber = seatnumber;
        this.day = day;
        this.time = time;
        this.cinema = cinema;
        this.purchaseddate = purchaseddate;
        this.brcode = brcode;
    }

    public Ticket(int userid, int movieid, int seatnumber, String day, String time, String cinema, String purchaseddate, String brcode) {
        this.userid = userid;
        this.movieid = movieid;
        this.seatnumber = seatnumber;
        this.day = day;
        this.time = time;
        this.cinema = cinema;
        this.purchaseddate = purchaseddate;
        this.brcode = brcode;
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

    public String getBrcode() {
        return brcode;
    }

    public void setBrcode(String brcode) {
        this.brcode = brcode;
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
        vals.put("brcode", this.brcode);
        return vals;
    }

    public Movie getMatchedMovie(DatabaseHelper dbHelper){
        Movie mv = new Movie(movieid);
        mv.fetchSelf(dbHelper);
        return mv;
    }

    public boolean saveState(Context context, DatabaseHelper dbHelper, boolean isNew){
        if(isNew){
            if(dbHelper.insert(getSelfContentValues(), "ticket")){
                System.out.println("Ticket : Ticket Saved Self");
                return true;
            }else{
                Toast.makeText(context, "Failed to create ticket", Toast.LENGTH_LONG);
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
            System.out.println("Before " + toString());

            Cursor cur = dbHelper.execRawQuery(String.format("SELECT uid, userid, movieid, seatnumber, day, time, cinema, purchaseddate, brcode from ticket WHERE brcode='%s';", getBrcode()), null);
            String[] columnNames = cur.getColumnNames();
            System.out.println("DEEP | " + Arrays.deepToString(columnNames));
            if (cur == null || cur.getCount() == 0 || !cur.moveToNext()) return;
            setUid(cur.getInt(0));
            setUserid(cur.getInt(1));
            setMovieid(cur.getInt(2));
            setSeatnumber(cur.getInt(3));
            setDay(cur.getString(4));
            setTime(cur.getString(5));
            setCinema(cur.getString(6));
            setPurchaseddate(cur.getString(7));
            setBrcode(cur.getString(8));

            System.out.println("After " + toString());
        }catch(Exception e){
            System.out.println("Ticket ERR ON FETCH " + e);
        }
    }

    /**Retrieve ang mga all ticket desu - jervx
     *
     * @param dbHelper need ko ng instance of DatabaseHelper desu
     * @return ArrayList of <Ticket>
     */
    public static ArrayList<Ticket> getAllTickets(DatabaseHelper dbHelper){
        ArrayList <Ticket> all = new ArrayList<>();

        Cursor tkts = dbHelper.execRawQuery("SELECT uid, userid, movieid, seatnumber, day, time, cinema, purchaseddate, brcode FROM ticket", null);
        while(tkts.moveToNext()) all.add(new Ticket(
                tkts.getInt(0),
                tkts.getInt(1),
                tkts.getInt(2),
                tkts.getInt(3),
                tkts.getString(4),
                tkts.getString(5),
                tkts.getString(6),
                tkts.getString(7),
                tkts.getString(8)
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
        System.out.println("CURRENT USER ID "+uid);
        Cursor tkts = dbHelper.execRawQuery(String.format("SELECT uid, userid, movieid, seatnumber, day, time, cinema, purchaseddate, brcode FROM ticket where userid='%d'", uid), null);

        System.out.println("COUNT RETRIEVED  "+ tkts.getCount());
        while(tkts.moveToNext()) {
            Ticket newtk = new Ticket(
                    tkts.getInt(0),
                    tkts.getInt(1),
                    tkts.getInt(2),
                    tkts.getInt(3),
                    tkts.getString(4),
                    tkts.getString(5),
                    tkts.getString(6),
                    tkts.getString(7),
                    tkts.getString(8)
            );
            all.add(newtk);
        }
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
                ", brcode='" + brcode + '\'' +
                '}';
    }
}
