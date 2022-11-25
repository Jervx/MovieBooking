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
import com.embs.moviebooking._utils.DatabaseHelper;

import java.util.ArrayList;
import java.util.List;

public class myTicketsAdapter extends ArrayAdapter<Ticket> {
    static Ticket ticket;


    public myTicketsAdapter(Context context, ArrayList<Ticket> mList){
        super(context, R.layout.ticket_list,mList);
    }
    public View getView(int position, @Nullable View c, @NonNull ViewGroup parent) {
        DatabaseHelper dbHelper = new DatabaseHelper(getContext());

        ticket = getItem(position);
        Movie tcktmv = ticket.getMatchedMovie(dbHelper);

        if(c == null){
            c = LayoutInflater.from(getContext()).inflate(R.layout.ticket_list,parent,false);
        }
        TextView userID = c.findViewById(R.id.userID);
        TextView seat = c.findViewById(R.id.seat);
        TextView purDate = c.findViewById(R.id.purDate);
        TextView movieID = c.findViewById(R.id.movieID);
        TextView title = c.findViewById(R.id.title);
        TextView time = c.findViewById(R.id.time);
        TextView day = c.findViewById(R.id.day);
        TextView cinemaNo = c.findViewById(R.id.cinemaNo);
        ImageView movieCover = c.findViewById(R.id.mCover);
        ImageView barcode = c.findViewById(R.id.barcode);

        userID.setText(Integer.toString(ticket.getUserid()));
         movieID.setText(Integer.toString(ticket.getMovieid()));
         seat.setText(Integer.toString(ticket.getSeatnumber()));
         purDate.setText(ticket.getPurchaseddate());
         title.setText(tcktmv.getTitle());
         time.setText(ticket.getTime());
         day.setText(ticket.getDay());
         cinemaNo.setText(ticket.getCinema());
        int mCover = getContext().getResources().getIdentifier(String.format("drawable/%s", tcktmv.getMoviecover()), null, getContext().getPackageName());
         movieCover.setImageResource(mCover);
//        int brcode = getContext().getResources().getIdentifier(String.format("drawable/%s", ticket.getBrcdBitmap()), null, getContext().getPackageName());
        barcode.setImageBitmap(ticket.getBrcdBitmap());

        return c;
    }
}
