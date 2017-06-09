package com.example.chris.blatoph.Activities;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.util.JsonReader;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.chris.blatoph.Http.*;
import com.example.chris.blatoph.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Array;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        /*Creation du dossier de stockage des fichiers de l'application*/
        File mydir = new File(Environment.getExternalStorageDirectory() + "/Blatoph/");
        if(!mydir.exists()) {
            mydir.mkdirs();
            Log.d("Dossier", "Creation du dossier Blatoph dans le dossier parent :"+ Environment.getExternalStorageDirectory().toString());
        }
        else {
            Log.d("error", "Le dossier existe deja");
        }

// On charge la page d'accueil
        setContentView(R.layout.connexion);
        Log.d("Lancement", "OK");

        /* Structure de requete HTTP

        JSONObject album = new JSONObject();
        try {
            album.put("titre","Album 30");
            album.put("date_creation","08-89");
            album.put("uti_id",1);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        RequeteServeur requete = new RequeteServeur();
        String reponse = null;
        try {
            reponse = requete.execute("POST","http://192.168.0.34/blatoph-server/web/albums",album.toString()).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        Log.d("Requete", "La reponse : "+reponse);

        */

     /*   RequeteServeurFile requeteFile = new RequeteServeurFile();

        Bitmap myBitmap = null;
        try {
            myBitmap = requeteFile.execute("GET","e9e7422c5a11f6baa9d25e424f782b0c.png").get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        final ImageView tv1 = (ImageView) findViewById(R.id.connexion_logo);
        tv1.setImageBitmap(myBitmap);

        */
        final EditText email = (EditText)findViewById(R.id.editText_email);
        final EditText mdp = (EditText)findViewById(R.id.editText_mdp);
        final Button button = (Button) findViewById(R.id.bouton_connexion);
        final int count = 0;
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                RequeteServeur requete = new RequeteServeur();
                JSONArray reponse = null;
                JSONObject id = null;
                JSONObject code = null;
                String username = email.getText().toString();
                String password = mdp.getText().toString();

                String url = "http://192.168.43.53/blatoph-server/web/utilisateurs/"+
                        username+"/"+password;

                try {
                    reponse = requete.execute("GET",url,"").get();
                    Log.d("Requete", "La reponse : "+reponse.toString());

                    id = reponse.getJSONObject(0);
                    code = reponse.getJSONObject(1);

                   if(code.getString("code").equals("200") ){
                       importationDonneesUtilisateur(id.getString("id"));
                        if(appareilPhotoExiste(MainActivity.this)) {
                            openCamera();
                        }
                        else{
                            button.setBackgroundColor(3);
                        }
                    }
                    else{
                        /*finish();
                        startActivity(getIntent());*/
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }


            }
        });

    }

    /** Fonction qui vérifie que l'appareil contient bien un appareil photo */
    private boolean appareilPhotoExiste(Context context) {
        if (context.getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA)){
            // au moins un appreil photo a été trouvé
            return true;
        } else {
            // aucun appareil photo n'a été trouvé
            return false;
        }
    }

    private void openCamera(){

        Intent intent = new Intent(this, AppareilPhotoActivity.class);
        startActivity(intent);
    }


    protected void importationDonneesUtilisateur(String id){

        Log.d("Requete","The id is "+id);
    }


}
