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
import android.widget.Toast;

import com.example.chris.blatoph.Http.RequeteServeur;
import com.example.chris.blatoph.LesObjets;
import com.example.chris.blatoph.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.ExecutionException;

/**
 * Created by Sarah Pierson on 15/06/2017.
 */

public class CreationCompteActivity extends AppCompatActivity {

    Resources res;
    LesObjets obj;

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.creation_compte);
        Log.d("Lancement", "OK");

        //Ne pas ouvrir le clavier à l'ouverture
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        //Permet de récupérer les données stockées dans le dossier des ressources
        res = this.getResources();

        obj = (LesObjets) getApplicationContext();

        final EditText nom = (EditText) findViewById(R.id.editText_nom);
        nom.setHint(res.getString(R.string.nom));
        final EditText email = (EditText) findViewById(R.id.editText_email);
        email.setHint(res.getString(R.string.email));
        final EditText mdp = (EditText) findViewById(R.id.editText_mdp);
        mdp.setHint(res.getString(R.string.mdp));
        final EditText confirmerMdp = (EditText) findViewById(R.id.editText_mdp2);
        confirmerMdp.setHint(res.getString(R.string.confmdn));


        final Button button = (Button) findViewById(R.id.button_creation);
        button.setOnClickListener(new View.OnClickListener() {
                                      public void onClick(View v) {

                                          String prenom = nom.getText().toString();
                                          String adresseEmail = email.getText().toString();
                                          String motDePasse = mdp.getText().toString();
                                          String motDePasse2 = confirmerMdp.getText().toString();

                                          if (!prenom.isEmpty() && !adresseEmail.isEmpty() && !motDePasse.isEmpty() && !motDePasse2.isEmpty()) {

                                              if (motDePasse.equals(motDePasse2)) {

                                                  JSONObject utilisateur = new JSONObject();
                                                  JSONObject id = null;
                                                  JSONObject code = null;
                                                  try {
                                                      utilisateur.put("nom", prenom);
                                                      utilisateur.put("prenom", prenom);
                                                      utilisateur.put("email", adresseEmail);
                                                      utilisateur.put("mot_de_pass", motDePasse);

                                                      RequeteServeur requete = new RequeteServeur();
                                                      JSONArray reponse = null;
                                                      reponse = requete.execute("POST", obj.getUrl() + "utilisateurs", utilisateur.toString()).get();

                                                      code = reponse.getJSONObject(reponse.length()-1);

                                                      /*Si l'update se fait bien */
                                                      if (code.getString("code").equals("201")) {
                                                          Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                                                          startActivity(intent);
                                                      }
                                                      else{
                                                          if (code.getString("code").equals("400")) {
                                                              Toast.makeText(CreationCompteActivity.this, "Cette adresse email est déjà utilisée", Toast.LENGTH_SHORT).show();
                                                          }
                                                      }

                                                      Log.d("ReponseZer", reponse.toString());
                                                  } catch (InterruptedException e) {
                                                      e.printStackTrace();
                                                  } catch (ExecutionException e) {
                                                      e.printStackTrace();
                                                  } catch (JSONException e) {
                                                      e.printStackTrace();
                                                  }
                                              } else {
                                                  Toast.makeText(CreationCompteActivity.this, "Veuillez renseigner des mots de passe identiques", Toast.LENGTH_SHORT).show();
                                              }
                                          }
                                          else{
                                              Toast.makeText(CreationCompteActivity.this, "Merci de remplir tous les champs", Toast.LENGTH_SHORT).show();
                                          }
                                      }


                                  }
        );
    }
}

