package com.embs.moviebooking._home;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.embs.moviebooking.R;
import com.embs.moviebooking._models.Movie;


public class movie_details extends Fragment {

    TextView title,desc,genre,cost,duration;
    ImageView cover;
    Button get;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =  inflater.inflate(R.layout.fragment_movie_details, container, false);
        title = v.findViewById(R.id.title);
        desc = v.findViewById(R.id.desc);
        genre = v.findViewById(R.id.genre);
        cost = v.findViewById(R.id.cost);
        duration = v.findViewById(R.id.duration);
        cover = v.findViewById(R.id.cover);
        get = v.findViewById(R.id.get);

        Bundle bundolf = this.getArguments();
        Movie currentMovie = (Movie) bundolf.getSerializable("currentMovie");

        get.setOnClickListener(JohnySinsei -> {
            // TODO PANO TO?
            getChildFragmentManager().beginTransaction().setReorderingAllowed(true).replace(R.id.movieFrag, book_fragment.class, bundolf).commit();
        });

        title.setText( currentMovie.getTitle() );
        desc.setText( currentMovie.getDescription() );
        cost.setText( currentMovie.getCost()+"" );
        genre.setText( currentMovie.getGenre() );
        duration.setText( currentMovie.getDuration() );
        cover.setImageResource( currentMovie.getMovieCoverResID(getContext()));

        return v;
    }
}