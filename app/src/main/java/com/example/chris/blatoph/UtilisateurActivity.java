package com.example.chris.blatoph;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;




/**
 * Created by Sarah Pierson on 11/05/2017.
 */

public class UtilisateurActivity extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.parametre_compte);
        Log.d("Lancement", "OK");



        final Button button = (Button) findViewById(R.id.enregistrer);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), UtilisateurActivity.class);
                startActivity(intent);
            }


        }
    );
}}
