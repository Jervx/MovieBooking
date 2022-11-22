package com.embs.moviebooking._models;

public class Ticket {
    int uid, userid, movieid, day, time, cinema, seatnumber, purchaseddate ;

    public Ticket(int uid, int userid, int movieid, int day, int time, int cinema, int seatnumber, int purchaseddate) {
        this.uid = uid;
        this.userid = userid;
        this.movieid = movieid;
        this.day = day;
        this.time = time;
        this.cinema = cinema;
        this.seatnumber = seatnumber;
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

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public int getCinema() {
        return cinema;
    }

    public void setCinema(int cinema) {
        this.cinema = cinema;
    }

    public int getSeatnumber() {
        return seatnumber;
    }

    public void setSeatnumber(int seatnumber) {
        this.seatnumber = seatnumber;
    }

    public int getPurchaseddate() {
        return purchaseddate;
    }

    public void setPurchaseddate(int purchaseddate) {
        this.purchaseddate = purchaseddate;
    }
}
