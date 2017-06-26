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
import android.widget.ListView;

import com.example.chris.blatoph.AmiAdapter;
import com.example.chris.blatoph.Classes.Utilisateur;
import com.example.chris.blatoph.Http.RequeteServeur;
import com.example.chris.blatoph.LesObjets;
import com.example.chris.blatoph.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * Created by Sarah Pierson on 02/06/2017.
 */

public class AmisActivity extends AppCompatActivity {


    Resources res;
    LesObjets obj;
    String url;
    private ListView mListView;
    RequeteServeur requete = new RequeteServeur();
    JSONArray reponse;
    ArrayList<String> amisId;
    int mSelectedItem;
    AmiAdapter adapter;

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

                                          if (!adresseEmail.isEmpty() && !adresseEmail.equals(obj.getUtilisateur().getAdresse())) {

                                              JSONObject ami = new JSONObject();
                                              JSONArray reponse = null;
                                              JSONObject id = null;

                                              try {
                                                  url = obj.getUrl() + "utilisateurs/ami/" + adresseEmail;
                                                  reponse = new RequeteServeur().execute("GET", url).get();
                                                  id = reponse.getJSONObject(0);


                                                  ami.put("uti_id", utilisateurId);
                                                  ami.put("ami_id", id.getString("id"));

                                                  url = obj.getUrl() + "amis";
                                                  reponse = new RequeteServeur().execute("POST", url, ami.toString()).get();

                                                  finish();
                                                  startActivity(getIntent());

                                              } catch (JSONException e) {
                                                  e.printStackTrace();
                                              } catch (InterruptedException e) {
                                                  e.printStackTrace();
                                              } catch (ExecutionException e) {
                                                  e.printStackTrace();
                                              }


                                          }


                                      }


                                  }
        );

        mListView = (ListView) findViewById(R.id.listView_amis);

        afficherListeAmis();
    }

    private List<Utilisateur> genererAmis(){
        List<Utilisateur> amis = new ArrayList<Utilisateur>();

        try {

            String url = obj.getUrl() + "utilisateurs/" + obj.getUtilisateur().getId() + "/amis";
            reponse = new RequeteServeur().execute("GET", url).get();
            reponse.remove(reponse.length()-1);

            for (int i = 0;i < reponse.length(); i++) {
                JSONObject ami = reponse.getJSONObject(i);
                amis.add(new Utilisateur(
                        ami.get("id").toString(),
                        ami.get("nom").toString(),
                        ami.get("email").toString()
                ));
            }

            obj.setAmis(amis);

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return amis;
    }

    private void afficherListeAmis(){
        List<Utilisateur> amis = genererAmis();

        adapter = new AmiAdapter(AmisActivity.this, amis);
        mListView.setAdapter(adapter);
    }
}
