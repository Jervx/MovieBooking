package com.embs.moviebooking._home;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.GridLayout;
import android.widget.ImageButton;

import com.embs.moviebooking.R;
import com.embs.moviebooking.customview.Seat;

import java.util.ArrayList;


public class book_fragment extends Fragment {

    private GridLayout left, right;

    private int [] seatsleft = {1,2,3,4,9,10,11,12,17,18,19,20,25,26,27,28};
    private int [] seatsright = {5,6,7,8,13,14,15,16,21,22,23,24,29,30,31,32};

    private ArrayList<Seat> seatLeft, seatRight;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =  inflater.inflate(R.layout.book_fragment, container, false);

        seatLeft = new ArrayList<>();
        seatRight = new ArrayList<>();

        left = v.findViewById(R.id.left);
        right = v.findViewById(R.id.right);

        for(int st : seatsleft) {
            Seat seatInstance = (Seat)  getLayoutInflater().inflate(R.layout.custom_view_button_seat, null);
            seatInstance.setOnClickListener(e->{
                seatInstance.flipSelect();
            });
            seatLeft.add(seatInstance);
            seatInstance.setSeatnumber(st);
            seatInstance.setSelected(false);
        }

        for(int st : seatsright) {
            Seat seatInstance = (Seat)  getLayoutInflater().inflate(R.layout.custom_view_button_seat, null);
            seatRight.add(seatInstance);
            seatInstance.setSeatnumber(st);
            seatInstance.setSelected(false);
        }

        render();
        return v;
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