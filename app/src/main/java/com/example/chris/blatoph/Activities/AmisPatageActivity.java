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

import com.example.chris.blatoph.LesObjets;
import com.example.chris.blatoph.R;

/**
 * Created by Sarah Pierson on 24/06/2017.
 */

public class AmisPatageActivity extends AppCompatActivity {


    Resources res;
    LesObjets obj;


    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.amis_partage);
        Log.d("Lancement", "OK");

        //Ne pas ouvrir le clavier à l'ouverture
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        //Permet de récupérer les données stockées dans le dossier des ressources
        res = this.getResources();

        obj = (LesObjets) getApplicationContext();

        final EditText email = (EditText) findViewById(R.id.editText);
        email.setHint(res.getString(R.string.email));


        final Button ajouter = (Button) findViewById(R.id.ajt_ami);
        ajouter.setOnClickListener(new View.OnClickListener() {
                                      public void onClick(View v) {

                                          String adresseEmail = email.getText().toString();

                                          Intent intent = new Intent(getApplicationContext(), AmisPatageActivity.class);
                                          startActivity(intent);
                                      }


                                  }
        );


        final Button valider = (Button) findViewById(R.id.valider);
        valider.setOnClickListener(new View.OnClickListener() {
                                      public void onClick(View v) {
                                          Intent intent = new Intent(getApplicationContext(), CreationAlbumActivity.class);
                                          startActivity(intent);
                                      }


                                  }
        );




    }
}

