package com.embs.moviebooking._home;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.embs.moviebooking.R;


public class Home extends AppCompatActivity {
    ImageView home,book,cinema;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        home = findViewById(R.id.homeIcon);
        book = findViewById(R.id.bookIcon);
        cinema = findViewById(R.id.cinemaIcon);

        route();
    }
    public void route(){
        getSupportFragmentManager().beginTransaction().setReorderingAllowed(true).replace(R.id.fragmentContainer, home_fragment.class, null).commit();
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                home.startAnimation(AnimationUtils.loadAnimation(Home.this, R.anim.anim_item));
                getSupportFragmentManager().beginTransaction().setReorderingAllowed(true).replace(R.id.fragmentContainer, home_fragment.class, null).commit();
            }
        });
        book.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                book.startAnimation(AnimationUtils.loadAnimation(Home.this, R.anim.anim_item));
                getSupportFragmentManager().beginTransaction().setReorderingAllowed(true).replace(R.id.fragmentContainer, book_fragment.class, null).commit();
            }
        });
        cinema.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cinema.startAnimation(AnimationUtils.loadAnimation(Home.this, R.anim.anim_item));
                getSupportFragmentManager().beginTransaction().setReorderingAllowed(true).replace(R.id.fragmentContainer, cinema_fragment.class, null).commit();
            }
        });
    }


}
