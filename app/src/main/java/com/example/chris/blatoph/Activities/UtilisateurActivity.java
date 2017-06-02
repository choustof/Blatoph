package com.example.chris.blatoph.Activities;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.chris.blatoph.R;


/**
 * Created by Sarah Pierson on 11/05/2017.
 */

public class UtilisateurActivity extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.parametre_compte);
        Log.d("Lancement", "OK");


        final Button buttonAmis = (Button) findViewById(R.id.Amis);
        buttonAmis.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), AmisActivity.class);
                startActivity(intent);
            }
        });





        final Button buttonEnregistrer = (Button) findViewById(R.id.enregistrer);
        buttonEnregistrer.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), UtilisateurActivity.class);
                startActivity(intent);
            }


        }
    );
}}
