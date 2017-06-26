package com.example.chris.blatoph.Activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

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




    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.une_image);

        /*requete = new RequeteServeur();
        reponse = null;
        obj = (LesObjets)getApplicationContext();

        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if(extras == null) {
                Id= 0;
            } else {
                Id= extras.getInt("PHO_ID");
            }
        } else {
            Id = (int) savedInstanceState.getSerializable("PHO_ID");
        }

    }*/
    }


}
