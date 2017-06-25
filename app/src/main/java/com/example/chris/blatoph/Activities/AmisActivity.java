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

import org.json.JSONObject;

/**
 * Created by Sarah Pierson on 02/06/2017.
 */

public class AmisActivity extends AppCompatActivity {


    Resources res;
    LesObjets obj;

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.amis);
        Log.d("Lancement", "OK");

        //Ne pas ouvrir le clavier à l'ouverture
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        //Permet de récupérer les données stockées dans le dossier des ressources
        res = this.getResources();

        obj = (LesObjets) getApplicationContext();

        final EditText email = (EditText) findViewById(R.id.editText);
        email.setHint(res.getString(R.string.email));

        final String utilisateurId = obj.getUtilisateur().getId();




        final Button button = (Button) findViewById(R.id.ajt_ami);
        button.setOnClickListener(new View.OnClickListener() {
                                      public void onClick(View v) {

                                          String adresseEmail = email.getText().toString();

                                          if (!adresseEmail.isEmpty()){

                                              JSONObject amis = new JSONObject();

                                              amis.put("uti_id", utilisateurId);



                                          }



                                          Intent intent = new Intent(getApplicationContext(), UtilisateurActivity.class);
                                          startActivity(intent);
                                      }


                                  }
        );
    }
}
