package com.example.chris.blatoph.Activities;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.example.chris.blatoph.AmiAdapter;
import com.example.chris.blatoph.Classes.Photo;
import com.example.chris.blatoph.Classes.Utilisateur;
import com.example.chris.blatoph.Http.RequeteServeur;
import com.example.chris.blatoph.Http.RequeteServeurFile;
import com.example.chris.blatoph.LesObjets;
import com.example.chris.blatoph.PhotoAdapter;
import com.example.chris.blatoph.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import okhttp3.internal.Util;

/**
 * Created by Sarah Pierson on 24/06/2017.
 */

public class AmisPatageActivity extends AppCompatActivity {

    Resources res;
    LesObjets obj;
    private ListView mListView;
    RequeteServeur requete = new RequeteServeur();
    JSONArray reponse;
    ArrayList<String> amisId;
    int mSelectedItem;
    AmiAdapter adapter;
    int nombreAmis;

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.amis_partage);
        Log.d("Lancement", "OK");

        //Ne pas ouvrir le clavier à l'ouverture
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        //Permet de récupérer les données stockées dans le dossier des ressources
        res = this.getResources();

        obj = (LesObjets) getApplicationContext();

        amisId = new ArrayList<String>();

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

        mListView = (ListView) findViewById(R.id.listView_amisPartage);

        afficherListeAmis();


        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                String positionString = "" + position;


                if (obj.estSelectionne(positionString)) {
                    mListView.getChildAt(position).setBackgroundColor(Color.WHITE);
                    obj.deselectionnerAmi(positionString);
                } else {
                    mListView.getChildAt(position).setBackgroundColor(Color.CYAN);
                    obj.selectionnerAmi(positionString, obj.getAmis().get(position));
                }
            }
        });

    }

    private List<Utilisateur> genererAmis() {
        List<Utilisateur> amis = new ArrayList<Utilisateur>();

        try {

            String url = obj.getUrl() + "utilisateurs/" + obj.getUtilisateur().getId() + "/amis";
            reponse = new RequeteServeur().execute("GET", url).get();
            reponse.remove(reponse.length() - 1);

            for (int i = 0; i < reponse.length(); i++) {
                JSONObject ami = reponse.getJSONObject(i);
                amis.add(new Utilisateur(
                        ami.get("id").toString(),
                        ami.get("nom").toString(),
                        ami.get("email").toString()
                ));
            }
            nombreAmis = amis.size();

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

    private void afficherListeAmis() {
        List<Utilisateur> amis = genererAmis();

        adapter = new AmiAdapter(AmisPatageActivity.this, amis);
        mListView.setAdapter(adapter);

        mListView.post(new Runnable(){

            @Override
            public void run() {
                for (int i = 0; i < nombreAmis; i++) {
                    if (obj.estSelectionne("" + i)) {
                        mListView.getChildAt(i).setBackgroundColor(Color.CYAN);
                    }
                }
            }
        });



    }
}

