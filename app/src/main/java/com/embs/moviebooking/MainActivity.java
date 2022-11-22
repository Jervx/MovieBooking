package com.embs.moviebooking;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.WindowManager;

import com.embs.moviebooking._home.Home;
import com.embs.moviebooking._models.User;
import com.embs.moviebooking._utils.DatabaseHelper;
import com.embs.moviebooking._utils.Helper;
import com.embs.moviebooking.front.front;
import com.embs.moviebooking.home.HomeActivity;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DatabaseHelper dbHelper = new DatabaseHelper(this);
//        dbHelper.dropDbs(new String[] {"user"});
//        dbHelper.truncateDbs(new String[] {"user"});
        dbHelper.checkTableExist();
        User dummyUser = new User("louellagracechua@gmail.com", "Jervx", Helper.hashPassword("helloworld"));
        dummyUser.saveState(getApplicationContext(), dbHelper, true);

        Cursor hasLoggedIn = dbHelper.execRawQuery("SELECT * FROM user where state=1", null);

        if(hasLoggedIn == null || hasLoggedIn.getCount() == 0){
            hasLoggedIn.close();
            viewFront();
        }else{
            hasLoggedIn.moveToNext();
            dummyUser = new User(hasLoggedIn.getString(2));
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