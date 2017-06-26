package com.example.chris.blatoph.Activities;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.chris.blatoph.Classes.Photo;
import com.example.chris.blatoph.Http.RequeteServeur;
import com.example.chris.blatoph.Http.RequeteServeurFile;
import com.example.chris.blatoph.LesObjets;
import com.example.chris.blatoph.PhotoAdapter;
import com.example.chris.blatoph.R;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Created by Sarah Pierson on 26/06/2017.
 */

public class UneImageActivity extends AppCompatActivity {

    int phoId;
    LesObjets obj;
    Resources res;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.une_image);

        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if (extras == null) {
                phoId = 0;
            } else {
                phoId = extras.getInt("PHO_ID");
            }
        } else {
            phoId = (int) savedInstanceState.getSerializable("PHO_ID");
        }

        res = this.getResources();
        obj = (LesObjets) getApplicationContext();
        Bitmap image = obj.getPhotos().get(phoId).getImage();

        final ImageView imageView = (ImageView) findViewById(R.id.imageView3);
        imageView.setImageBitmap(image);



    }
}


