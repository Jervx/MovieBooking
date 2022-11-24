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
import com.embs.moviebooking._models.Ticket;

import java.util.ArrayList;
import java.util.List;

public class myTicketsAdapter extends ArrayAdapter<Ticket> {
    static Ticket ticket;
    private List<Ticket> itemList;

//    int uid, userid, movieid, seatnumber;
//    String day, time, cinema, purchaseddate, brcode ;

    public myTicketsAdapter(Context context, ArrayList<Ticket> mList){
        super(context, R.layout.ticket_list,mList);
    }
    public View getView(int position, @Nullable View c, @NonNull ViewGroup parent) {
        ticket = getItem(position);
        if(c == null){
            c = LayoutInflater.from(getContext()).inflate(R.layout.movie_list,parent,false);
        }
        TextView mID = c.findViewById(R.id.movieId);
        TextView time = c.findViewById(R.id.time);
        TextView day = c.findViewById(R.id.day);
        TextView cinemaNo = c.findViewById(R.id.cinemaNo);
//        int resId = getContext().getResources().getIdentifier(String.format("drawable/%s", movie.getMoviecover()), null, getContext().getPackageName());
//        TextView title = c.findViewById(R.id.title);
////        TextView desc = c.findViewById(R.id.desc);
//        //      TextView cinema = c.findViewById(R.id.cinema);
//        TextView day = c.findViewById(R.id.day);
//        TextView time = c.findViewById(R.id.time);
////        TextView seat = c.findViewById(R.id.seat);
////        TextView taken = c.findViewById(R.id.taken);
//        //  TextView genre = c.findViewById(R.id.genre);
////        TextView duration = c.findViewById(R.id.duration);
////        TextView cost = c.findViewById(R.id.cost);
//        img.setImageResource(resId);

         mID.setText(ticket.getMovieid());
         time.setText(ticket.getTime());
         day.setText(ticket.getDay());
         cinemaNo.setText(ticket.getCinema());
//        title.setText(movie.getTitle());
////        desc.setText(movie.getDescription());
//        //   cinema.setText(movie.getCinema());
//        day.setText(movie.getDay());
//        time.setText(movie.getTime());
////        seat.setText(movie.getSeats());
////        taken.setText(movie.getTaken());
//        //     genre.setText(movie.getGenre());
////        duration.setText(movie.getDuration());
////        String cCost =String.valueOf(movie.getCost());
////        cost.setText(cCost);
        return c;
    }
}
