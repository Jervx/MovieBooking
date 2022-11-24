package com.embs.moviebooking._home;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.embs.moviebooking.R;
import com.embs.moviebooking._models.Movie;
import com.embs.moviebooking._models.Ticket;
import com.embs.moviebooking._models.User;
import com.embs.moviebooking._utils.DatabaseHelper;
import com.embs.moviebooking._utils.Helper;
import com.embs.moviebooking.customview.Seat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;


public class book_fragment extends Fragment {

    private GridLayout left, right;
    private TextView titletxtv, genretxtv, cinemaNo, availableSeat, tekenSeat, total;
    private ImageView moviebanner;
    private Button bookbtn;

    private int [] seatsleft = {1,2,3,4,9,10,11,12,17,18,19,20,25,26,27,28};
    private int [] seatsright = {5,6,7,8,13,14,15,16,21,22,23,24,29,30,31,32};
    private float totalCost = 0;

    private ArrayList<Seat> seatLeft, seatRight;
    private ArrayList<Seat> Chosen = new ArrayList<>();
    private DatabaseHelper dbHelper;
    private Movie currentMovie;
    private User currentUser;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =  inflater.inflate(R.layout.book_fragment, container, false);

        dbHelper = new DatabaseHelper(getContext());

        Bundle bundolf = this.getArguments();
        try{
            currentMovie = (Movie) bundolf.getSerializable("currentMovie");
        }catch (Exception e){  }

        seatLeft = new ArrayList<>();
        seatRight = new ArrayList<>();

        left = v.findViewById(R.id.left);
        right = v.findViewById(R.id.right);
        titletxtv = v.findViewById(R.id.titletxtv);
        genretxtv = v.findViewById(R.id.generetxtv);
        cinemaNo = v.findViewById(R.id.cinemaNo);
        availableSeat = v.findViewById(R.id.availableSeat);
        tekenSeat = v.findViewById(R.id.takenSeat);
        total = v.findViewById(R.id.total);
        moviebanner = v.findViewById(R.id.moviebanner);
        bookbtn = v.findViewById(R.id.bookbtn);

        bookbtn.setOnClickListener(ev->{
            placeBook();
        });

        if(currentMovie == null){
            Toast.makeText(getContext(), "You haven't selected a movie", Toast.LENGTH_LONG).show();
            bookbtn.setTextSize(12);
            total.setTextSize(12);
            bookbtn.setText("No Chosen Movie");
            total.setText("No Chosen Movie");
            bookbtn.setClickable(false);
        }else{
            renderMovieInfo();
            genSeat();
            render();
        }

        return v;
    }

    void placeBook(){
        for(Seat st : Chosen) {
            Ticket tkinstance = new Ticket(0, currentMovie.getUid(), st.getSeatnumber(), currentMovie.getDay(), currentMovie.getTime(), currentMovie.getCinema(), Helper.toISODateString(new Date()), "");
            currentMovie.takeSeat(st.getSeatnumber());
            tkinstance.saveState(getContext(), dbHelper, true);
        }

        Toast.makeText(getContext(), "Generated " + Chosen.size() + " tickets to your account", Toast.LENGTH_LONG).show();

        totalCost = 0;
        Chosen = new ArrayList<>();
        seatLeft = new ArrayList<>();
        seatRight = new ArrayList<>();
        currentMovie.saveState(getContext(), dbHelper, false);
        genSeat();
        render();
        renderMovieInfo();
    }

    void genSeat(){
        for(int st : seatsleft) {
            Seat seatInstance = (Seat)  getLayoutInflater().inflate(R.layout.custom_view_button_seat, null);
            seatInstance.setOnClickListener(e->{
                if(seatInstance.isSelectedSeat()){
                    totalCost -= currentMovie.getCost();
                    Chosen.remove(seatInstance);
                }else{
                    totalCost += currentMovie.getCost();
                    Chosen.add(seatInstance);
                }
                seatInstance.toggleSelect();
                seatInstance.renderState();
                renderMovieInfo();
            });
            seatLeft.add(seatInstance);
            seatInstance.setSeatnumber(st);
            seatInstance.setSelected(false);

            if(Helper.checkIfExist(currentMovie.getTaken(), st+""))
                seatInstance.setTaken(true);
            else if(!Helper.checkIfExist(currentMovie.getSeats(), st+""))
                seatInstance.setTaken(true);

            seatInstance.renderState();
        }

        for(int st : seatsright) {
            Seat seatInstance = (Seat)  getLayoutInflater().inflate(R.layout.custom_view_button_seat, null);
            seatInstance.setOnClickListener(e->{
                if(seatInstance.isSelectedSeat()){
                    totalCost -= currentMovie.getCost();
                    Chosen.remove(seatInstance);
                }else{
                    totalCost += currentMovie.getCost();
                    Chosen.add(seatInstance);
                }
                seatInstance.toggleSelect();
                seatInstance.renderState();
                renderMovieInfo();
            });
            seatRight.add(seatInstance);
            seatInstance.setSeatnumber(st);
            seatInstance.setSelected(false);

            if(Helper.checkIfExist(currentMovie.getTaken(), st+""))
                seatInstance.setTaken(true);
            else if(!Helper.checkIfExist(currentMovie.getSeats(), st+""))
                seatInstance.setTaken(true);

            seatInstance.renderState();
        }
    }

    void renderMovieInfo(){
        titletxtv.setText(currentMovie.getTitle());
        genretxtv.setText(currentMovie.getGenre());
        cinemaNo.setText(currentMovie.getCinema());
        availableSeat.setText(currentMovie.getSeats().length() > 0 ? currentMovie.getSeats().split(",").length+"" : "0");
        tekenSeat.setText((seatsleft.length + seatsright.length - (currentMovie.getSeats().length() > 0 ? currentMovie.getSeats().split(",").length  : 0)) + "");
        total.setText(""+totalCost + "");
        moviebanner.setImageResource(currentMovie.getMovieCoverResID(getContext()));
        bookbtn.setClickable(totalCost > 0 );
        if(totalCost > 0) {
            bookbtn.setBackgroundColor(getContext().getResources().getColor(R.color.yellow));
        }
        else {
            bookbtn.setBackgroundColor(getContext().getResources().getColor(R.color.unavailable));
            if(currentMovie.getSeats().length() == 0) total.setText("Seats Unavailable");
            else total.setText("Choose Seat");
        }
    }

    void render(){
        left.removeAllViews();
        right.removeAllViews();

        for(Seat st : seatLeft){
            left.addView(st);
        }

        for(Seat st : seatRight){
            right.addView(st);
        }
    }
}