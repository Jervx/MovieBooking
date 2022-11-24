package com.embs.moviebooking._home;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.embs.moviebooking.R;
import com.embs.moviebooking._models.Movie;
import com.embs.moviebooking._models.Ticket;
import com.embs.moviebooking._models.User;
import com.embs.moviebooking._utils.DatabaseHelper;
import com.embs.moviebooking.adapter.movieAdapter;
import com.embs.moviebooking.adapter.myTicketsAdapter;

public class myTickets extends Fragment {
    ListView tickets;
    myTicketsAdapter m;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.my_tickets_fragment, container, false);
        DatabaseHelper d = new DatabaseHelper(v.getContext());
//        m = new movieAdapter(v.getContext(), Ticket.getAllUserTickets(d, User));
//        tickets.setAdapter(m);



        return  v;
    }
}