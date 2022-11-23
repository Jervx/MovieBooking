package com.embs.moviebooking._home;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.embs.moviebooking.R;
import com.embs.moviebooking._models.Movie;
import com.embs.moviebooking._utils.DatabaseHelper;
import com.embs.moviebooking.adapter.movieAdapter;

import java.util.List;

public class home_fragment extends Fragment {
    ListView movies;
    ScrollView scroll;
    static movieAdapter m;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.home_fragment, container, false);
//        m = new movieAdapter(v.getContext(), img);
        movies = v.findViewById(R.id.movieList);
        scroll = v.findViewById(R.id.scroll);
        DatabaseHelper d = new DatabaseHelper(v.getContext());
        m = new movieAdapter(v.getContext(),Movie.getAllMovies(d));
        movies.setAdapter(m);
        movies.setFastScrollEnabled(false);

        movies.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int i, long arg3) {
                Bundle bundle = new Bundle();
                String title = Movie.getAllMovies(d).get(i).getTitle();
                String cover = Movie.getAllMovies(d).get(i).getMoviecover();
                String desc = Movie.getAllMovies(d).get(i).getDescription();
                String cinema =Movie.getAllMovies(d).get(i).getCinema();
                String day = Movie.getAllMovies(d).get(i).getDay();
                String time =  Movie.getAllMovies(d).get(i).getTime();
                String seat = Movie.getAllMovies(d).get(i).getSeats();
                String taken = Movie.getAllMovies(d).get(i).getTaken();
                String cost = String.valueOf(Movie.getAllMovies(d).get(i).getCost());
                String duration = Movie.getAllMovies(d).get(i).getDuration();
                String genre = Movie.getAllMovies(d).get(i).getGenre();
                int resId = getContext().getResources().getIdentifier(String.format("drawable/%s", cover), null, getContext().getPackageName());
                bundle.putString("title", title);
                bundle.putString("desc", desc);
                bundle.putString("cinema", cinema);
                bundle.putString("day", day);
                bundle.putString("time", time);
                bundle.putString("seat",seat);
                bundle.putString("taken", taken);
                bundle.putString("cost", cost);
                bundle.putString("genre", genre);
                bundle.putString("duration", duration);
                bundle.putInt("cover",resId);
                getChildFragmentManager().beginTransaction().setReorderingAllowed(true).replace(R.id.movieFrag, movie_details.class, bundle).commit();
                scroll.setVisibility(View.GONE);
            }
        });


        return v;
    }
}