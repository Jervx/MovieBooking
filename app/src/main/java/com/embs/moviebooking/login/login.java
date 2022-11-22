package com.embs.moviebooking.login;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.embs.moviebooking.R;
import com.embs.moviebooking._home.Home;
import com.embs.moviebooking._models.User;
import com.embs.moviebooking._utils.DatabaseHelper;
import com.embs.moviebooking._utils.Helper;
import com.google.android.material.textfield.TextInputEditText;

public class login extends AppCompatActivity {
    private TextView signup;
    private TextInputEditText email, pass;
    private Button btnlogin;
    private Button login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login);
        DatabaseHelper dbHelper = new DatabaseHelper(getApplicationContext());

        email = findViewById(R.id.email);
        pass = findViewById(R.id.pass);

        login = findViewById(R.id.login);
        signup = findViewById(R.id.signup);

        login.setOnClickListener( JohnySinsei -> {
            try{
                String _email = email.getText().toString(), _pass = pass.getText().toString();

                User usr = new User(_email);

                if(!usr.checkIfAlreadyExist(dbHelper)){
                    Toast.makeText(this, "User doesn't exist", Toast.LENGTH_LONG).show();
                    return;
                }

                usr.fetchSelf(dbHelper);

                if(!Helper.comparePassword(_pass, usr.getPassword())){
                    Toast.makeText(this, "Wrong Credentail", Toast.LENGTH_LONG).show();
                    return;
                }

                usr.setState(1);
                usr.saveState(getApplicationContext(), dbHelper, false);

                Intent homeIntent = new Intent(getApplicationContext(), Home.class);
                homeIntent.putExtra("usr", usr);

                startActivity(homeIntent);
            }catch (Exception e){ System.out.println("ERRR " + e); }
        });

        // find id
        signup = findViewById(R.id.signup);
        login = findViewById(R.id.login);

        signup.setClickable(true);
        clickMeBaby();
    }

    public void clickMeBaby(){
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent goToHome = new Intent(getApplicationContext(), Home.class);
                startActivity(goToHome);
            }
        });
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent goToSignup = new Intent(getApplicationContext(), signup.class);
                startActivity(goToSignup);
            }
        });
    }



}