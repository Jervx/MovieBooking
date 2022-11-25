package com.embs.moviebooking._home;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

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
    User currentUser;
    TextView label;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.my_tickets_fragment, container, false);
        System.out.println("INFLATED : ");
        DatabaseHelper d = new DatabaseHelper(v.getContext());
        currentUser = (User) getArguments().getSerializable("currentUser");
        System.out.println("CURRE USER TICKET: " + currentUser.toString());
        tickets = v.findViewById(R.id.ticketList);
        label = v.findViewById(R.id.ticketLable);

        m = new myTicketsAdapter(v.getContext(), Ticket.getAllUserTickets(d, currentUser.getUid()));
        tickets.setAdapter(m);

        return  v;
    }
}