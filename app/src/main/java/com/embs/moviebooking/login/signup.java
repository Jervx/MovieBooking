package com.embs.moviebooking.login;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.embs.moviebooking.R;
import com.embs.moviebooking._home.Home;
import com.embs.moviebooking._models.User;
import com.embs.moviebooking._utils.DatabaseHelper;
import com.embs.moviebooking._utils.Helper;
import com.embs.moviebooking._utils.JavaMailAPI;
import com.embs.moviebooking.front.front;
import com.google.android.material.textfield.TextInputEditText;

public class signup extends AppCompatActivity {
    TextView signin;
    private TextInputEditText email, username, pass, confirm_pass;
    private Button btnlogin;
    private String key;
    private User newUsr;
    private DatabaseHelper dbHelper;
    private Dialog load;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        dbHelper = new DatabaseHelper(this);

        email = findViewById(R.id.email);
        username = findViewById(R.id.username);
        pass = findViewById(R.id.pass);
        confirm_pass = findViewById(R.id.confirm_pass);

        btnlogin = findViewById(R.id.btnlogin);

        btnlogin.setOnClickListener(JohnySensei -> {
            String _email = email.getText().toString(),
                    _username = username.getText().toString(),
                    _password = pass.getText().toString(),
                    _confirm_pass = confirm_pass.getText().toString();
            loading();
            if (_email.length() == 0 || _username.length() == 0 || _password.length() == 0 || _confirm_pass.length() == 0) {
                Toast.makeText(this, "Please Fill Up All Fields", Toast.LENGTH_LONG).show();
                return;
            }

            if (!_password.equals(_confirm_pass)) {
                Toast.makeText(this, "Confirm Password Doesn't Match", Toast.LENGTH_LONG).show();
                return;
            }

            newUsr = new User(_email, _username, Helper.hashPassword(_password));

            if (newUsr.checkIfAlreadyExist(dbHelper)) {
                Toast.makeText(this, "Email is already taken", Toast.LENGTH_LONG).show();
                return;
            }
            key = Helper.randomKey(6);

            JavaMailAPI mail = new JavaMailAPI(this, _email, "Your verification code", key+"");
            mail.execute();




        });
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





    public void verify() {
        Dialog verify = new Dialog(signup.this);
        verify.setContentView(R.layout.verification_dialog);
        verify.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        verify.getWindow().getAttributes().windowAnimations = R.style.diagAnim;
        verify.show();
    }

    public void loading() {
        load = new Dialog(signup.this);
        load.setContentView(R.layout.loading);
        load.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        load.getWindow().getAttributes().windowAnimations = R.style.diagAnim;
        load.show();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                load.dismiss();
                verify();
            }
        }, 2000);
    }

    public void onSuccess() {
        newUsr.setState(1);
        if (newUsr.saveState(this, dbHelper, true)) {
            Intent homeIntent = new Intent(getApplicationContext(), Home.class);
            homeIntent.putExtra("usr", newUsr);
            startActivity(homeIntent);
        }
    }

}