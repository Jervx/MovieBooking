package com.embs.moviebooking.front;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.embs.moviebooking.R;
import com.embs.moviebooking.login.login;
import com.embs.moviebooking.login.signup;

public class front extends AppCompatActivity {
    Button log,sign;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_front);
        button();
    }

    public void button(){
        log = findViewById(R.id.log);
        sign = findViewById(R.id.sign);
        log.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent gotToLogin = new Intent(getApplicationContext(), login.class);
                startActivity(gotToLogin);
            }
        });
        sign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent gotToSignup = new Intent(getApplicationContext(), signup.class);
                startActivity(gotToSignup);
            }
        });
    }
}