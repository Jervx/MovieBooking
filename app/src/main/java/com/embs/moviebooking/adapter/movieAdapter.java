package com.embs.moviebooking.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.embs.moviebooking.R;
import com.embs.moviebooking._models.Movie;

import java.util.ArrayList;
import java.util.List;

public class movieAdapter  extends ArrayAdapter<Movie> {
    static Movie movie;
    public movieAdapter(Context context, ArrayList<Movie> mList){
        super(context, R.layout.movie_list,mList);
    }
    public View getView(int position, @Nullable View c, @NonNull ViewGroup parent) {
        movie = getItem(position);
        if(c == null){
            c = LayoutInflater.from(getContext()).inflate(R.layout.movie_list,parent,false);
        }
        ImageView img = c.findViewById(R.id.img);
        int resId = getContext().getResources().getIdentifier(String.format("drawable/%s", movie.getMoviecover()), null, getContext().getPackageName());
        TextView title = c.findViewById(R.id.title);
        TextView day = c.findViewById(R.id.day);
        TextView time = c.findViewById(R.id.time);
        img.setImageResource(resId);
        title.setText(movie.getTitle());
          day.setText(movie.getDay());
          time.setText(movie.getTime());
        return c;
    }
}
