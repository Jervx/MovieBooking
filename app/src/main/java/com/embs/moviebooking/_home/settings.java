package com.embs.moviebooking._home;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.embs.moviebooking.R;
import com.embs.moviebooking._models.User;
import com.embs.moviebooking._utils.DatabaseHelper;
import com.embs.moviebooking._utils.Helper;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Date;

public class settings extends Fragment {

    private Bitmap bitmap;
    private Boolean hasNewImage = false;
    private ImageView profile_image;
    private User currentUser;
    private DatabaseHelper dbHelper;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_settings, container, false);
        dbHelper = new DatabaseHelper(getContext());

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
                profile_image.setImageBitmap(bitmap);
//                handleType();
            } else if (resultCode == Activity.RESULT_CANCELED) { System.out.println("CANCELLED "); }
        } catch (Exception e) { System.out.println("Fire ERR " + e); }
    }

    // TODO ONCE xml layout done
    void render(){

    }

    void saveChange() {
        // TODO AFTER settings xml completed
//        currentUser.setUsername(newName);

//        if (password.getText().toString().length() > 0)
//            currentUser.setPassword(Helper.hashPassword(newPassword));

        try{

            ContextWrapper cw = new ContextWrapper(getContext());
            File directory = cw.getDir("profiles", Context.MODE_PRIVATE);
            File file = new File(directory, Helper.toISODateString(new Date()) + "_PROFILE_"
                    + Helper.randomKey(8) + ".jpg");

            Helper.saveImage(file, bitmap);

            String abspath = file.toString();
            String prevImg = currentUser.getImage();
            Helper.deleteFile(prevImg);
            currentUser.setImage(abspath);
        }catch (Exception e){}

        currentUser.saveState(getContext(), dbHelper, false);
        currentUser.fetchSelf(dbHelper);

//        reInit();

        Toast.makeText(getContext(), "Changes Saved", Toast.LENGTH_LONG).show();
    }

}