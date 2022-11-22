package com.embs.moviebooking._models;

public class Movie {
    private int uid;
    private String movieCover, title, description, cinema, day, time, tickets, totalseats, taken;

    public Movie(int uid, String movieCover, String title, String description, String cinema, String day, String time, String tickets, String totalseats, String taken) {
        this.uid = uid;
        this.movieCover = movieCover;
        this.title = title;
        this.description = description;
        this.cinema = cinema;
        this.day = day;
        this.time = time;
        this.tickets = tickets;
        this.totalseats = totalseats;
        this.taken = taken;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getMovieCover() {
        return movieCover;
    }

    public void setMovieCover(String movieCover) {
        this.movieCover = movieCover;
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

    public String getTickets() {
        return tickets;
    }

    public void setTickets(String tickets) {
        this.tickets = tickets;
    }

    public String getTotalseats() {
        return totalseats;
    }

    public void setTotalseats(String totalseats) {
        this.totalseats = totalseats;
    }

    public String getTaken() {
        return taken;
    }

    public void setTaken(String taken) {
        this.taken = taken;
    }
}
