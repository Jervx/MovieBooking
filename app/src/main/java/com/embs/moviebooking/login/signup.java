package com.embs.moviebooking.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.embs.moviebooking.R;

public class signup extends AppCompatActivity {
    TextView signin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       setContentView(R.layout.activity_signup);

        signin = findViewById(R.id.signin);
        signin.setClickable(true);
        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent goToSignin = new Intent(getApplicationContext(), login.class);
                startActivity(goToSignin);
            }
        });
    }
}