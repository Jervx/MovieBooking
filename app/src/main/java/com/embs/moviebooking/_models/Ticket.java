package com.embs.moviebooking._models;

public class Ticket {
    int uid, userId, MovieId, day, time, cinema, seatnumber, purchaseddate ;

    public Ticket(int uid, int userId, int movieId, int day, int time, int cinema, int seatnumber, int purchaseddate) {
        this.uid = uid;
        this.userId = userId;
        MovieId = movieId;
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

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getMovieId() {
        return MovieId;
    }

    public void setMovieId(int movieId) {
        MovieId = movieId;
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
