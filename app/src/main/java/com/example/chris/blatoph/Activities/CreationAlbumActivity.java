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

import com.example.chris.blatoph.Classes.Utilisateur;
import com.example.chris.blatoph.Http.RequeteServeur;
import com.example.chris.blatoph.LesObjets;
import com.example.chris.blatoph.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.ExecutionException;

/**
 * Created by Sarah Pierson on 24/06/2017.
 */

public class CreationAlbumActivity extends AppCompatActivity {

    Resources res;
    LesObjets obj;

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);


        //Ne pas ouvrir le clavier à l'ouverture
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);


//Permet de récupérer les données stockées dans le dossier des ressources
        setContentView(R.layout.creation_album);

        res = this.getResources();

        obj = (LesObjets) getApplicationContext();

        final EditText titre = (EditText) findViewById(R.id.titre_param_album);
        titre.setHint(res.getString(R.string.titre_album));

        final String utilisateurId = obj.getUtilisateur().getId();


        final Button amis = (Button) findViewById(R.id.amis);
        amis.setOnClickListener(new View.OnClickListener() {
                                    public void onClick(View v) {


                                        Intent intent = new Intent(getApplicationContext(), AmisPatageActivity.class);
                                        startActivity(intent);
                                    }


                                }
        );


        final Button creation = (Button) findViewById(R.id.ajouter);
        creation.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                String titreAlbum = titre.getText().toString();
                String dateAlbum = new Date().toString();


                if (!titreAlbum.isEmpty()) {
                    JSONObject album = new JSONObject();
                    JSONObject utiId = new JSONObject();
                    JSONObject albumPartage = new JSONObject();

                    try {

                        album.put("titre", titreAlbum);
                        album.put("date_creation", dateAlbum);
                        album.put("uti_id", utilisateurId);

                        RequeteServeur requete = new RequeteServeur();
                        JSONArray reponse = null;
                        reponse = requete.execute("POST", obj.getUrl() + "albums", album.toString()).get();
                        Log.d("Reponse", reponse.getJSONObject(reponse.length() - 2).toString());

                        String albId = reponse.getJSONObject(reponse.length() - 2).get("id").toString();

                        utiId.put("album_courant_id", reponse.getJSONObject(reponse.length() - 2).get("id"));
                        obj.getUtilisateur().setAlbumCourantId(albId);
                        Log.d("ALLLLLLL ", obj.getUtilisateur().getAlbumCourantId());

                        reponse = new RequeteServeur().execute("PUT", obj.getUrl() + "utilisateurs/" + utilisateurId, utiId.toString()).get();

                        ArrayList<Utilisateur> amisSelectionnes = new ArrayList<Utilisateur>(obj.getAmisSelectionnes());

                        for (int i =  0;i<amisSelectionnes.size(); i++){

                            albumPartage.put("uti_id",amisSelectionnes.get(i).getId());
                            albumPartage.put("alb_id",albId);
                            reponse = new RequeteServeur().execute("POST", obj.getUrl() + "albumPartages", albumPartage.toString()).get();
                        }


                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }


                    obj.clearAmisSelectionnes();
                    Intent intent = new Intent(getApplicationContext(), ListeAlbumActivity.class);
                    startActivity(intent);
                }


            }
        });
    }
}

