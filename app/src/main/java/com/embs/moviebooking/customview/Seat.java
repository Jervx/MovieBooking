package com.embs.moviebooking.customview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;

import com.google.android.material.button.MaterialButton;

import com.embs.moviebooking.R;

public class Seat extends MaterialButton {
    int seatnumber;
    boolean selected = false;
    boolean taken = true;

    public int getSeatnumber() {
        return seatnumber;
    }

    public void setSeatnumber(int seatnumber) {
        this.seatnumber = seatnumber;
        int width= 80;
        int heigth= 80;
        ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(width, heigth);
        this.setLayoutParams(layoutParams);
    }

    public void setTaken(boolean taken){
        this.taken = taken;
        setClickable(taken);
    }

    public void flipSelect() {
        selected = !selected;
        if(selected)
            this.setBackgroundColor(getContext().getResources().getColor(R.color.yellow));
        else
            this.setBackgroundColor(getContext().getResources().getColor(R.color.unselected));
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
