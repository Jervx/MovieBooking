package com.embs.moviebooking._home;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.embs.moviebooking.R;


public class Home extends AppCompatActivity {
    ImageView home,book,cinema,setting;
    ImageButton back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        home = findViewById(R.id.homeIcon);
        book = findViewById(R.id.bookIcon);
        cinema = findViewById(R.id.cinemaIcon);
        back = findViewById(R.id.back);
        setting = findViewById(R.id.settings);
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
        cinema.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cinema.startAnimation(AnimationUtils.loadAnimation(Home.this, R.anim.anim_item));
                swtchRoute(3, null);
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

    public void swtchRoute(int route, Bundle bundolf){
        if(route == 0) getSupportFragmentManager().beginTransaction().setReorderingAllowed(true).replace(R.id.fragmentContainer, home_fragment.class, bundolf).commit();
        if(route == 1) getSupportFragmentManager().beginTransaction().setReorderingAllowed(true).replace(R.id.fragmentContainer, movie_details.class, bundolf).commit();
        if(route == 2) getSupportFragmentManager().beginTransaction().setReorderingAllowed(true).replace(R.id.fragmentContainer, book_fragment.class, bundolf).commit();
        if(route == 3) getSupportFragmentManager().beginTransaction().setReorderingAllowed(true).replace(R.id.fragmentContainer, cinema_fragment.class, bundolf).commit();
        if(route == 4) getSupportFragmentManager().beginTransaction().setReorderingAllowed(true).replace(R.id.fragmentContainer, settings.class, bundolf).commit();
    }

}
