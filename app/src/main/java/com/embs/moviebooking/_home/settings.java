package com.embs.moviebooking._home;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.embs.moviebooking.MainActivity;
import com.embs.moviebooking.R;
import com.embs.moviebooking._models.User;
import com.embs.moviebooking._utils.DatabaseHelper;
import com.embs.moviebooking._utils.Helper;
import com.embs.moviebooking.login.signup;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Date;

public class settings extends Fragment {

    private Bitmap bitmap;
    private Boolean hasNewImage = false;
    private ImageView cover;
    private User currentUser;
    private DatabaseHelper dbHelper;
    private TextView  staticemail, staticusername, sw;
    private EditText usernameeditable, passwordeditable;
    private Button savebtn;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_settings, container, false);
        dbHelper = new DatabaseHelper(getContext());

        currentUser = (User) getArguments().get("currentUser");

        System.out.println("CURR " + currentUser.toString());


        staticemail = v.findViewById(R.id.staticemail);
        staticusername = v.findViewById(R.id.staticusername);
        usernameeditable = v.findViewById(R.id.usernameeditable);
        passwordeditable = v.findViewById(R.id.passwordeditable);
        sw = v.findViewById(R.id.sw);
        sw.setClickable(true);
        cover = v.findViewById(R.id.cover);
        cover.setOnClickListener(JohnySensei -> {
            Intent pickGal = new Intent(Intent.ACTION_PICK);
            pickGal.setType("image/*");
            pickGal.setData(MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            startActivityForResult(pickGal, 1000);
        });

        usernameeditable = v.findViewById(R.id.usernameeditable);
        passwordeditable = v.findViewById(R.id.passwordeditable);
        savebtn = v.findViewById(R.id.savebtn);

        savebtn.setOnClickListener(JohnySensei -> {
            saveChange();
        });

        sw.setOnClickListener(JohnySinsei2 -> {
            Intent goToSignin = new Intent(getContext(), signup.class);
            startActivity(goToSignin);
        });

        ((TextView) v.findViewById(R.id.logout)).setOnClickListener(JohnySinsei->{
            Dialog logoutconf = new Dialog(getContext());
            logoutconf.setContentView(R.layout.logout_dialog);
            logoutconf.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            logoutconf.getWindow().getAttributes().windowAnimations = R.style.diagAnim;
            logoutconf.show();

            ((Button) logoutconf.findViewById(R.id.yes)).setOnClickListener(JohnySinsei2 -> {
                currentUser.setState(0);
                currentUser.saveState(getContext(), dbHelper, false);
                Intent MAIN = new Intent(getContext(), MainActivity.class);
                startActivity(MAIN);
                getActivity().finish();
                System.exit(0);
                logoutconf.dismiss();
            });

            ((Button) logoutconf.findViewById(R.id.No)).setOnClickListener(JohnySinsei2 -> {
                logoutconf.dismiss();
            });
        });
        render();
        return v;
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        Home parent = (Home) getActivity();
        try {
            if (resultCode == parent.RESULT_OK && requestCode == 1000) {
                Uri targetUri = data.getData();
                bitmap = BitmapFactory.decodeStream(parent.getContentResolver().openInputStream(targetUri));
                hasNewImage = true;
                cover.setImageBitmap(bitmap);
//              handleType();
            } else if (resultCode == Activity.RESULT_CANCELED) {
                System.out.println("CANCELLED ");
            }
        } catch (Exception e) {
            System.out.println("Fire ERR " + e);
        }
    }

    // TODO ONCE xml layout done
    void render() {

        staticemail.setText(currentUser.getEmail());
        staticusername.setText(currentUser.getUsername());

        usernameeditable.setText(currentUser.getUsername());
        passwordeditable.setText("");


        if (currentUser.getImage() != null) {
            try {
                File imgFile = new File(currentUser.getImage());
                if (imgFile.exists()) {
                    cover.setImageBitmap(Helper.getImage(new File(currentUser.getImage())));
                }
            } catch (Exception e) {
                System.out.println("ERR IMAGE CUZ " + e);
            }
        }
    }

    void saveChange() {
        // TODO AFTER settings xml completed
        currentUser.setUsername(usernameeditable.getText().toString());

        if (passwordeditable.getText().toString().length() > 0)
            currentUser.setPassword(Helper.hashPassword(passwordeditable.getText().toString()));

        if (hasNewImage) {
            try {
                ContextWrapper cw = new ContextWrapper(getContext());
                File directory = cw.getDir("profiles", Context.MODE_PRIVATE);
                File file = new File(directory, Helper.toISODateString(new Date()) + "_PROFILE_"
                        + Helper.randomKey(8) + ".jpg");
                System.out.println("WORK A");
                Helper.saveImage(file, bitmap);
                String abspath = file.toString();

                if(currentUser.getImage() != null){
                    String prevImg = currentUser.getImage();
                    System.out.println("WORK D");
                    Helper.deleteFile(prevImg);
                }
                currentUser.setImage(abspath);
            } catch (Exception e) {
                System.out.println("SETTED ERR " + e);
            }
        }
        currentUser.saveState(getContext(), dbHelper, false);
        currentUser.fetchSelf(dbHelper);
        System.out.println(currentUser.toString());

        render();

        Toast.makeText(getContext(), "Changes Saved", Toast.LENGTH_LONG).show();
    }

}