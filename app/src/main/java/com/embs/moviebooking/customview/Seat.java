package com.embs.moviebooking.customview;

import android.content.Context;
import android.util.AttributeSet;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;

public class Seat extends AppCompatButton {
    int seatnumber;
    boolean selected;

    public Seat(@NonNull Context context, int Seat) {
        super(context);
    }

    public Seat(@NonNull Context context) {
        super(context);
    }

    public Seat(@NonNull Context context, int Seat, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public Seat(@NonNull Context context, int Seat, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
}
