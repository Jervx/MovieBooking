package com.embs.moviebooking._models;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import com.embs.moviebooking._utils.DatabaseHelper;

import java.io.Serializable;

public class User implements Serializable {

    private int uid, state;
    private String image, email, username, password;

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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
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

    public User(String image, String email, String username, String password) {
        this.uid = uid;
        this.email = email;
        this.username = username;
        this.password = password;
    }

    public User(String email, String username, String password) {
        this.uid = uid;
        this.email = email;
        this.username = username;
        this.password = password;
    }

    public User(int uid, String email, String username, String password) {
        this.uid = uid;
        this.email = email;
        this.username = username;
        this.password = password;
    }

    /* returns boolean if a user with the email already exist
    *
     * @args DatabaseHelper an instance of DatabaseHelper
     * @args isNew indicastes if this user is a new user, if user already exist this will return false
     *
     * */
    public boolean checkIfAlreadyExist(DatabaseHelper dbHelper){
        Cursor curs = dbHelper.execRawQuery("SELECT * FROM user WHERE email='"+this.email+"'", null);
        if(curs != null && curs.getCount() > 0) return true;
        return false;
    }

        /* Saves current object state to user table
        *
         * @args DatabaseHelper an instance of DatabaseHelper
         * @args isNew indicastes if this user is a new user, if user already exist this will return false
         * */
    public boolean saveState(Context context, DatabaseHelper dbHelper, boolean isNew){
        if(isNew){
            if(checkIfAlreadyExist(dbHelper)){
                System.out.println("USER : Failed New User, Duplics Desu");
                Toast.makeText(context, "User already exist", Toast.LENGTH_SHORT).show();
                return false;
            }
            ContentValues vals = new ContentValues();
            vals.put("image", this.image);
            vals.put("email", this.email);
            vals.put("username", this.username);
            vals.put("password", this.password);
            if(dbHelper.insert(vals, "user")){
                System.out.println("USER : New User Saved Self");
                return true;
            }else{
                Toast.makeText(null, "Failed to create", Toast.LENGTH_LONG);
                return false;
            }
        }else{
            ContentValues vals = new ContentValues();
            vals.put("image", this.image);
            vals.put("email", this.email);
            vals.put("username", this.username);
            vals.put("password", this.password);
            if( dbHelper.update(vals, "email='"+this.email+"'", "user") ){
                Toast.makeText(null, "Failed to save state", Toast.LENGTH_LONG);
                System.out.println("USER : Updated Self");
                return true;
            }else{
                return false;
            }
        }
    }

    public void fetchSelf(DatabaseHelper dbHelper){
        try{
            Cursor findUser = dbHelper.execRawQuery(String.format("SELECT * FROM user WHERE email = '%s';", email), null);
            if (findUser == null || findUser.getCount() == 0 || !findUser.moveToNext()) return;
            this.setUid(findUser.getInt(0));
            this.setImage(findUser.getString(1));
            this.setEmail(findUser.getString(2));
            this.setUsername(findUser.getString(3));
            this.setPassword(findUser.getString(4));
            System.out.println("DATA : "+findUser.getInt(0)+" "+findUser.getString(1) + " "+findUser.getString(2) + " "+findUser.getString(3)+" "+findUser.getString(4));
        }catch(Exception e){
            System.out.println("ERR ON FETCH " + e);
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
