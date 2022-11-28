package com.embs.moviebooking._home;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.embs.moviebooking.R;
import com.embs.moviebooking._models.User;
import com.embs.moviebooking._utils.DatabaseHelper;


public class Home extends AppCompatActivity {
    ImageView home,book,ticket,setting;
    ImageButton back;
    User currentUser;
    DatabaseHelper dbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        home = findViewById(R.id.homeIcon);
        book = findViewById(R.id.bookIcon);
        ticket = findViewById(R.id.cinemaIcon);
        back = findViewById(R.id.back);
        setting = findViewById(R.id.settings);
        dbHelper = new DatabaseHelper(getApplicationContext());
        currentUser = (User) getIntent().getSerializableExtra("currentUser");
        route();
    }
    public void route(){
        swtchRoute(0, null);
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                home.startAnimation(AnimationUtils.loadAnimation(Home.this, R.anim.anim_item));
                swtchRoute(0, null);
            }
        });
        book.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                book.startAnimation(AnimationUtils.loadAnimation(Home.this, R.anim.anim_item));
                swtchRoute(2, null);
            }
        });
        ticket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ticket.startAnimation(AnimationUtils.loadAnimation(Home.this, R.anim.anim_item));
                swtchRoute(5, null);
            }
        });
        setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setting.startAnimation(AnimationUtils.loadAnimation(Home.this, R.anim.anim_item));
                swtchRoute(4, null);
            }
        });
        ImageButton back = findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                home.startAnimation(AnimationUtils.loadAnimation(Home.this, R.anim.anim_item));
                swtchRoute(0, null);
            }
        });

    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        try {
            if (resultCode == RESULT_OK && requestCode == 1000) {
                Toast.makeText(this, "Photo Selected", Toast.LENGTH_SHORT).show();
            } else if (resultCode == Activity.RESULT_CANCELED) {
                Toast.makeText(this, "Select Photo Cancelled", Toast.LENGTH_SHORT).show();
                System.out.println("CANCELLED ");
            }
        } catch (Exception e) {
            System.out.println("Fire ERR " + e);
        }
    }

    public void swtchRoute(int route, Bundle bundolf){
         try {
             if(bundolf.containsKey("currentUser")) bundolf.remove("currentUser");
             currentUser.fetchSelf(dbHelper);
             bundolf.putSerializable("currentUser", currentUser);
         }catch (Exception e){
             System.out.println("Fire ERR " + e);
             bundolf = new Bundle();
             bundolf.putSerializable("currentUser", currentUser);
         }
        if(route == 0) getSupportFragmentManager().beginTransaction().setReorderingAllowed(true).replace(R.id.fragmentContainer, home_fragment.class, bundolf).commit();
        if(route == 1) getSupportFragmentManager().beginTransaction().setReorderingAllowed(true).replace(R.id.fragmentContainer, movie_details.class, bundolf).commit();
        if(route == 2) getSupportFragmentManager().beginTransaction().setReorderingAllowed(true).replace(R.id.fragmentContainer, book_fragment.class, bundolf).commit();
        if(route == 3) getSupportFragmentManager().beginTransaction().setReorderingAllowed(true).replace(R.id.fragmentContainer, cinema_fragment.class, bundolf).commit();
        if(route == 4) getSupportFragmentManager().beginTransaction().setReorderingAllowed(true).replace(R.id.fragmentContainer, settings.class, bundolf).commit();
        if(route == 5) getSupportFragmentManager().beginTransaction().setReorderingAllowed(true).replace(R.id.fragmentContainer, myTickets.class, bundolf).commit();
    }

}
