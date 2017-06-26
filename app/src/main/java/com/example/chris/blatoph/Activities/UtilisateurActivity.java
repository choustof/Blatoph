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

import com.example.chris.blatoph.Http.RequeteServeur;
import com.example.chris.blatoph.LesObjets;
import com.example.chris.blatoph.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.ExecutionException;


/**
 * Created by Sarah Pierson on 11/05/2017.
 */

public class UtilisateurActivity extends AppCompatActivity {

    Resources res;
    LesObjets obj;

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.parametre_compte);
        //Ne pas ouvrir le clavier à l'ouverture
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        obj = (LesObjets) getApplicationContext();

        //Permet de récupérer les données stockées dans le dossier des ressources
        res = this.getResources();

        final EditText nom = (EditText)findViewById(R.id.utilisateur_nom);
        nom.setText(obj.getUtilisateur().getPrenom());
        final EditText email = (EditText)findViewById(R.id.email);
        email.setText(obj.getUtilisateur().getAdresse());
        final EditText mdp = (EditText)findViewById(R.id.mdp);
        mdp.setText(obj.getUtilisateur().getMotDePasse());
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

                String nomUtilisateur = nom.getText().toString();
                String emailUtilisateur = email.getText().toString();
                String mdpUtilisateur = mdp.getText().toString();


                if (!nomUtilisateur.isEmpty() && !emailUtilisateur.isEmpty() && !mdpUtilisateur.isEmpty()) {
                    JSONObject utilisateur = new JSONObject();

                    try {
                        utilisateur.put("nom", nomUtilisateur);
                        utilisateur.put("email", emailUtilisateur);
                        utilisateur.put("mot_de_pass", mdpUtilisateur);

                        RequeteServeur requete = new RequeteServeur();
                        JSONArray reponse = null;
                        reponse = requete.execute("PUT", obj.getUrl() + "utilisateurs/"+obj.getUtilisateur().getId(), utilisateur.toString()).get();
                        Log.d("Reponse", reponse.toString());

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }


                    Intent intent = new Intent(getApplicationContext(), ListeAlbumActivity.class);
                    startActivity(intent);
                }


                finish();
                startActivity(getIntent());
            }


        }
    );
}}
