package com.embs.moviebooking._utils;

import org.mindrot.jbcrypt.BCrypt;

import java.text.SimpleDateFormat;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.Random;
import java.util.TimeZone;

public class Helper {

    final static String EMAIL = "tripleam2022@gmail.com", PASS = "htrnfenswszubgmv";

    public static String hashPassword(String toHash) {
        String hashed = BCrypt.hashpw(toHash, BCrypt.gensalt());
        return hashed;
    }

    public static boolean comparePassword(String word, String hash){
        if(BCrypt.checkpw(word, hash)) return true;
        return false;
    }

    public static String randomKey(int length) {
        String genCars = "";

        int nums[] = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};

        while (length > 0) {
            genCars += nums[randomNum()];
            length--;
        }

        return genCars;
    }

    public static int randomNum() {
        return new Random().nextInt(9);
    }

    public static boolean checkIfExist(String from, String what){
        String strry [] = from.split(",");
        for(String st : strry) if(st.equals(what)) return true;
        return false;
    }

    public static String seatGenerator(int startseat, int endseat){
        String seat = "";
        for(; startseat <= endseat; startseat++)
            seat += startseat + (startseat == endseat ? "" : ",");
        return seat;
    }

    public static String toISODateString(Date date) {
        SimpleDateFormat sdf;
        sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
        sdf.setTimeZone(TimeZone.getTimeZone("CET"));
        String ISO = sdf.format(date);
        return ISO;
    }

    public static Date fromIoDateStringToDate(String ISODateString) {
        return Date.from(ZonedDateTime.parse(ISODateString).toInstant());
    }

}
