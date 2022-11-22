package com.embs.moviebooking;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Handler;

import com.embs.moviebooking._home.Home;
import com.embs.moviebooking._models.User;
import com.embs.moviebooking._utils.DatabaseHelper;
import com.embs.moviebooking._utils.Helper;
import com.embs.moviebooking.front.front;

public class MainActivity extends AppCompatActivity {

    private User dummyUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DatabaseHelper dbHelper = new DatabaseHelper(this);
        dbHelper.dropDbs(new String[] {"user"});
//        dbHelper.truncateDbs(new String[] {"user"});
        dbHelper.checkTableExist();
//        dummyUser = new User("louellagracechua@gmail.com", "Jervx", Helper.hashPassword("helloworld"));
//        dummyUser.setState(0);
//        dummyUser.saveState(getApplicationContext(), dbHelper, true);

        Cursor hasLoggedIn = dbHelper.execRawQuery("SELECT * FROM user where state=1", null);

        if(hasLoggedIn == null || hasLoggedIn.getCount() == 0){
            hasLoggedIn.close();
            viewFront();
        }else{
            hasLoggedIn.moveToNext();
            dummyUser = new User(hasLoggedIn.getString(2));
            System.out.println("USER DESU");
            System.out.println(dummyUser.toString());
            dummyUser.fetchSelf(dbHelper);

            Intent homeIntent = new Intent(getApplicationContext(), Home.class);
            homeIntent.putExtra("usr", dummyUser);

            startActivity(homeIntent);
        }
    }

    public void viewFront(){
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent front = new Intent(getApplicationContext(), front.class);
                startActivity(front);
            }
        },2000);
    }

}