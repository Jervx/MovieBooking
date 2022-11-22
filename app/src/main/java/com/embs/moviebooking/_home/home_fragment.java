package com.embs.moviebooking._home;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.embs.moviebooking.R;

public class home_fragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.home_fragment, container, false);
        TextView desc = v.findViewById(R.id.desc);
        desc.setText("       Triple AM booking app is an online booking system that allows potential customers/guests to self-book and reserve their desired and comfortable seats via applications.");








        return v;
    }
}