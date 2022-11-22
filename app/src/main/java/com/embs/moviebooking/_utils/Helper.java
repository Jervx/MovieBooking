package com.embs.moviebooking._utils;

import org.mindrot.jbcrypt.BCrypt;

import java.util.Random;

public class Helper {

    final static String EMAIL = "securesally@gmail.com", PASS = "htrnfenswszubgmv";

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

}
