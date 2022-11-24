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

//    int uid, userid, movieid, seatnumber;
//    String day, time, cinema, purchaseddate, brcode ;

    public myTicketsAdapter(Context context, ArrayList<Ticket> mList){
        super(context, R.layout.ticket_list,mList);
    }
    public View getView(int position, @Nullable View c, @NonNull ViewGroup parent) {
        ticket = getItem(position);
        if(c == null){
            c = LayoutInflater.from(getContext()).inflate(R.layout.ticket_list,parent,false);
        }
        TextView mID = c.findViewById(R.id.movieId);
        TextView time = c.findViewById(R.id.time);
        TextView day = c.findViewById(R.id.day);
        TextView cinemaNo = c.findViewById(R.id.cinemaNo);

         mID.setText(ticket.getMovieid());
         time.setText(ticket.getTime());
         day.setText(ticket.getDay());
         cinemaNo.setText(ticket.getCinema());

        return c;
    }
}
