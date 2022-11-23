package com.embs.moviebooking.customview;

import android.content.Context;
import android.util.AttributeSet;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;

import com.google.android.material.button.MaterialButton;

public class Seat extends MaterialButton {
    int seatnumber;
    boolean selected;

    public int getSeatnumber() {
        return seatnumber;
    }

    public void setSeatnumber(int seatnumber) {
        this.seatnumber = seatnumber;
    }

    @Override
    public boolean isSelected() {
        return selected;
    }

    @Override
    public void setSelected(boolean selected) {
        this.selected = selected;
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
