package com.embs.moviebooking.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
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
import com.google.android.material.textfield.TextInputEditText;

public class signup extends AppCompatActivity {
    TextView signin;
    private TextInputEditText email, username, pass, confirm_pass;
    private Button btnlogin;
    private String key;
    private User newUsr;
    private DatabaseHelper dbHelper;

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

            if(_email.length() == 0 || _username.length() == 0 || _password.length() == 0 || _confirm_pass.length() == 0 ){
                Toast.makeText(this, "Please Fill Up All Fields", Toast.LENGTH_LONG).show();
                return;
            }

            if(!_password.equals(_confirm_pass)){
                Toast.makeText(this, "Confirm Password Doesn't Match", Toast.LENGTH_LONG).show();
                return;
            }

            newUsr = new User(_email, _username, Helper.hashPassword(_password));

            if(newUsr.checkIfAlreadyExist(dbHelper)){
                Toast.makeText(this, "Email is already taken", Toast.LENGTH_LONG).show();
                return;
            }

            key = Helper.randomKey(6);

            JavaMailAPI mail = new JavaMailAPI(this, _email, "Your verification code", key+"");
            mail.execute();

            // TODO DIALOG ROJAN & CALL onSuccess() function if key & input from dialog matches

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

    public void onSuccess(){
        newUsr.setState(1);
        if(newUsr.saveState(this, dbHelper, true)){
            Intent homeIntent = new Intent(getApplicationContext(), Home.class);
            homeIntent.putExtra("usr", newUsr);
            startActivity(homeIntent);
        }
    }

}