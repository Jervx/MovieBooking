package com.embs.moviebooking._models;

import android.content.ContentValues;
import android.database.Cursor;
import android.widget.Toast;

import com.embs.moviebooking._utils.DatabaseHelper;

public class User {

    private int uid, state;
    private String email, username, password;

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    /*
    * Sets user password hash
    *
    * @args password a hash string
    * */
    public void setPassword(String password) {
        this.password = password;
    }

    public User(String email){ this.email = email; }

    public User(int uid, String email, String username, String password, int state) {
        this.uid = uid;
        this.email = email;
        this.username = username;
        this.password = password;
        this.state = state;
    }

    /* returns boolean if a user with the email already exist
    *
     * @args DatabaseHelper an instance of DatabaseHelper
     * @args isNew indicastes if this user is a new user, if user already exist this will return false
     *
     * */
    public boolean checkIfAlreadyExist(DatabaseHelper dbHelper){
        Cursor curs = dbHelper.execRawQuery("SELECT * FROM user WHERE email ='"+this.email+"'", null);
        if(curs != null && curs.getCount() > 0) return true;
        return false;
    }


        /* Saves current object state to user table
        *
         * @args DatabaseHelper an instance of DatabaseHelper
         * @args isNew indicastes if this user is a new user, if user already exist this will return false
         * */
    public boolean saveState(DatabaseHelper dbHelper, boolean isNew){
        if(isNew){
            if(checkIfAlreadyExist(dbHelper)){
                Toast.makeText(null, "User already exist", Toast.LENGTH_SHORT).show();
                return false;
            }
            ContentValues vals = new ContentValues();
            vals.put("email", this.email);
            vals.put("username", this.username);
            vals.put("password", this.password);
            if(dbHelper.insert(vals, "user")){
                return true;
            }else{
                Toast.makeText(null, "Failed to create", Toast.LENGTH_LONG);
                return false;
            }
        }else{
            ContentValues vals = new ContentValues();
            vals.put("email", this.email);
            vals.put("username", this.username);
            vals.put("password", this.password);
            if( dbHelper.update(vals, "email='"+this.email+"'", "user") ){
                Toast.makeText(null, "Failed to save state", Toast.LENGTH_LONG);
                return true;
            }else{
                return false;
            }
        }
    }

    @Override
    public String toString() {
        return "User{" +
                "uid=" + uid +
                ", email='" + email + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
