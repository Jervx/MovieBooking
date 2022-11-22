package com.embs.moviebooking;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.WindowManager;

import com.embs.moviebooking._models.User;
import com.embs.moviebooking._utils.DatabaseHelper;
import com.embs.moviebooking._utils.Helper;
import com.embs.moviebooking.front.front;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,  WindowManager.LayoutParams.FLAG_FULLSCREEN);
<<<<<<< HEAD
        setContentView(R.layout.activity_main);

        DatabaseHelper dbHelper = new DatabaseHelper(this);
//        dbHelper.dropDbs(new String[] {"user"});
//        dbHelper.truncateDbs(new String[] {"user"});
        dbHelper.checkTableExist();

<<<<<<< HEAD
        User dummyUser = new User("louellagracechua@gmail.com", "Jervx", Helper.hashPassword("helloworld"));
        dummyUser.saveState(getApplicationContext(), dbHelper, true);

=======
=======
        setContentView(R.layout.loading);
>>>>>>> b095eb9 (commit)
>>>>>>> fc01821 (commit)
        viewFront();
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