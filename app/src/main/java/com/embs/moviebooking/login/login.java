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
import com.embs.moviebooking._models.User;
import com.embs.moviebooking._utils.DatabaseHelper;
import com.embs.moviebooking._utils.Helper;
import com.embs.moviebooking.home.HomeActivity;
import com.google.android.material.textfield.TextInputEditText;

public class login extends AppCompatActivity {
    private TextView signup;
    private TextInputEditText email, pass;
    private Button btnlogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login);

        DatabaseHelper dbHelper = new DatabaseHelper(getApplicationContext());

        email = findViewById(R.id.email);
        pass = findViewById(R.id.pass);

        btnlogin = findViewById(R.id.btnlogin);
        signup = findViewById(R.id.signup);

        btnlogin.setOnClickListener( JohnySinsei -> {
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

                Intent homeIntent = new Intent(getApplicationContext(), HomeActivity.class);
                homeIntent.putExtra("usr", usr);

                startActivity(homeIntent);
            }catch (Exception e){ System.out.println("ERRR " + e);}
        });

        signup.setClickable(true);
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent goToSignup = new Intent(getApplicationContext(), signup.class);
                startActivity(goToSignup);
            }
        });
<<<<<<< HEAD

=======
>>>>>>> fc01821 (commit)
    }
}