package com.example.chris.blatoph.Activities;


import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

import com.example.chris.blatoph.R;


/**
 * Created by Sarah Pierson on 11/05/2017.
 */

public class UtilisateurActivity extends AppCompatActivity {

    Resources res;
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.parametre_compte);
        //Ne pas ouvrir le clavier à l'ouverture
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        //Permet de récupérer les données stockées dans le dossier des ressources
        res = this.getResources();

        final EditText email = (EditText)findViewById(R.id.email);
        email.setHint(res.getString(R.string.email));
        final EditText mdp = (EditText)findViewById(R.id.mdp);
        mdp.setHint(res.getString(R.string.mdp));
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
                finish();
                startActivity(getIntent());
            }


        }
    );
}}
