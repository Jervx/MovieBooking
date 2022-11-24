package com.embs.moviebooking.customview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;

import com.google.android.material.button.MaterialButton;

import com.embs.moviebooking.R;

public class Seat extends MaterialButton {
    int seatnumber;
    boolean selected = false;
    boolean taken = false;

    public int getSeatnumber() {
        return seatnumber;
    }

    public void setSeatnumber(int seatnumber) {
        this.seatnumber = seatnumber;
        int width= 80;
        int heigth= 80;
        ViewGroup.MarginLayoutParams params = new ViewGroup.MarginLayoutParams(width,heigth);
        params.setMargins(0, 0, 15, 0);
        this.setLayoutParams(params);

    }

    public boolean isSelectedSeat(){
        return selected;
    }

    public void setTaken(boolean taken){
        this.taken = taken;
        setClickable(false);
    }

    public void toggleSelect(){
        selected = !selected;
    }

    public void renderState() {
        if(taken)
            this.setBackgroundColor(getContext().getResources().getColor(R.color.unavailable));
        else if(selected)
            this.setBackgroundColor(getContext().getResources().getColor(R.color.yellow));
        if(!taken && !selected)
            this.setBackgroundColor(getContext().getResources().getColor(R.color.available));
    }

    public Seat(@NonNull Context context) {
        super(context);
    }

    public Seat(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public Seat(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
}
