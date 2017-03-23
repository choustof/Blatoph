package com.example.chris.blatoph.TestsAppareilsPhoto;

import android.content.Context;
import android.content.pm.PackageManager;
import android.hardware.Camera;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

import com.example.chris.blatoph.R;

import java.io.File;


public class MainActivity3 extends AppCompatActivity {

    private Camera appareilPhoto;
    private PrevisualisationAppareilPhoto previsualisation;

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


        setContentView(R.layout.connexion);
        Log.d("Lancement", "OK");

        final Button button = (Button) findViewById(R.id.bouton_connexion);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(appareilPhotoExiste(MainActivity3.this)) {

                    //On cré une instance de l'appareil photo
                   appareilPhoto = ouvrirAppareilPhoto();

                    // On cré la prévisualisation et on l'ajoute à la vue
                    previsualisation = new PrevisualisationAppareilPhoto(MainActivity3.this, appareilPhoto);
                    FrameLayout preview = (FrameLayout) findViewById(R.id.camera_preview);
                    preview.addView(previsualisation);
                }
                else{
                    button.setBackgroundColor(3);
                }
            }
        });

    }

    /** Fonction qui vérifie que l'appareil contient bien un appareil photo */
    private boolean appareilPhotoExiste(Context context) {
        if (context.getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA)){
            // L'appareil a bien un appareil photo
            return true;
        } else {
            // Aucun appareil photo n'a été trouvé
            return false;
        }
    }

    private static Camera ouvrirAppareilPhoto(){

        Camera c = null;
        try {
            c = Camera.open();
        }
        catch (Exception e){
            Log.d("ERREUR", "Erreur lors de l'ouverture de l'appareil photo: " + e.getMessage());
        }
        return c;
    }

}
