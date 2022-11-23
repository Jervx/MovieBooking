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
                Movie foc = Movie.getAllMovies(d).get(i);
                bundle.putSerializable("currentMovie", foc);
                getChildFragmentManager().beginTransaction().setReorderingAllowed(true).replace(R.id.movieFrag, movie_details.class, bundle).commit();
                scroll.setVisibility(View.GONE);
            }
        });


        return v;
    }
}