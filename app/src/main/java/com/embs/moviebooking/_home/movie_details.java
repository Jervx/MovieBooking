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
        Bundle bundle = this.getArguments();
        title.setText( bundle.getString("title"));
        desc.setText(bundle.getString("desc"));
        cost.setText(bundle.getString("cost"));
        genre.setText(bundle.getString("genre"));
        duration.setText(bundle.getString("duration"));
        cover.setImageResource(bundle.getInt("cover"));

        return v;
    }
}