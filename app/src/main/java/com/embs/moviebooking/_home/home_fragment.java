package com.embs.moviebooking._home;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.embs.moviebooking.R;
import com.embs.moviebooking._models.Movie;
import com.embs.moviebooking.adapter.movieAdapter;

import java.util.List;

public class home_fragment extends Fragment {
    ListView movies;
    static movieAdapter m;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.home_fragment, container, false);

//        m = new movieAdapter(v.getContext(), img);
        movies.setAdapter(m);
        return v;
    }
}