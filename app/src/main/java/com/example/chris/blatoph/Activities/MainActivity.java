package com.example.chris.blatoph.Activities;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.chris.blatoph.Classes.Album;
import com.example.chris.blatoph.Classes.Utilisateur;
import com.example.chris.blatoph.Http.*;
import com.example.chris.blatoph.LesObjets;
import com.example.chris.blatoph.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {

    Resources res;
    LesObjets obj;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        //Ne pas ouvrir le clavier à l'ouverture
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        //Permet de récupérer les données stockées dans le dossier des ressources
        res = this.getResources();

        obj = (LesObjets)getApplicationContext();

        //A remplacer selon le serveur
        obj.setUrl("http://192.168.0.25/blatoph-server/web/");

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
            reponse = requete.execute("POST",obj.getUrl()+"albums",album.toString()).get();
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
        email.setHint(res.getString(R.string.email));
        final EditText mdp = (EditText)findViewById(R.id.editText_mdp);
        mdp.setHint(res.getString(R.string.mdp));
        final Button button = (Button) findViewById(R.id.bouton_connexion);

        final int count = 0;
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                JSONArray reponse = null;
                JSONObject id = null;
                JSONObject code = null;
                String username = email.getText().toString();
                String password = mdp.getText().toString();

                if(!username.isEmpty() && !password.isEmpty()) {
                    RequeteServeur requete = new RequeteServeur();
                    //RequeteServeurFile requeteFile = new RequeteServeurFile();

                    String url = obj.getUrl() + "utilisateurs/" +
                            username + "/" + password;
                    try {
                        //requeteFile.execute("POST", url, "");
                        reponse = requete.execute("GET", url).get();
                        Log.d("Requete",reponse.toString());

                        id = reponse.getJSONObject(0);
                        code = reponse.getJSONObject(1);
                        Log.d("Requete", "The id is " + code.get("code").toString());

                        if (code.getString("code").equals("200")) {
                            importationDonneesUtilisateur(id.getString("id"));
                            if (appareilPhotoExiste(MainActivity.this)) {
                                openCamera();
                            } else {
                                button.setBackgroundColor(3);
                            }
                        } else {
                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    }
                }
                else{
                    if(username.isEmpty()){
                        Toast.makeText(MainActivity.this, "Veuillez renseigner un email", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        Toast.makeText(MainActivity.this, "Veuillez renseigner un mot de passe", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });

        final Button button2 = (Button) findViewById(R.id.nouvel_utilisateur);
        button2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Log.d("Nouveau", "On passe");
                Intent intent = new Intent(getApplicationContext(), CreationCompteActivity.class);
                startActivity(intent);
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
        RequeteServeur requete = new RequeteServeur();
        JSONArray reponse = null;
        JSONObject nom = null;

        String url = obj.getUrl() + "utilisateurs/" +id;

        try {

            //Recupération des données utilisateur
            reponse = requete.execute("GET", url).get();
            Log.d("Requete lol",reponse.toString());

            obj.setUtilisateur(new Utilisateur(
                    reponse.getJSONObject(0).get("id").toString(),
                    reponse.getJSONObject(0).get("nom").toString(),
                    reponse.getJSONObject(0).get("email").toString(),
                    reponse.getJSONObject(0).get("motDePass").toString(),
                    reponse.getJSONObject(0).get("albumCourantId").toString()
            ));

            //Recupération des albums
            url = obj.getUrl() + "utilisateurs/" +id+"/albums";
            reponse = new RequeteServeur().execute("GET", url).get();
            reponse.remove(reponse.length()-1);

            for (int i = 0; i < reponse.length(); i++) {
                JSONObject album = reponse.getJSONObject(i);
                obj.getUtilisateur().addAlbum(album.get("id").toString(),new Album(
                    album.get("id").toString(),
                    album.get("titre").toString(),
                    album.get("date_creation").toString(),
                    album.get("uti_id").toString()
                    ));
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

}
