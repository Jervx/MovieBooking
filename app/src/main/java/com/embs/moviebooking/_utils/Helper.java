package com.embs.moviebooking._utils;

import org.mindrot.jbcrypt.BCrypt;

public class Helper {

    public static String hashPassword(String toHash) {
        String hashed = BCrypt.hashpw(toHash, BCrypt.gensalt());
        return hashed;
    }

    public static boolean comparePassword(String word, String hash){
        if(BCrypt.checkpw(word, hash)) return true;
        return false;
    }

}
