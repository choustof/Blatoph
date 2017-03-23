package com.example.chris.blatoph.TestsAppareilsPhoto;

import android.content.Context;
import android.content.pm.PackageManager;
import android.hardware.Camera;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.view.*;

import com.example.chris.blatoph.R;

import java.io.File;

public class MainActivity4 extends AppCompatActivity {

    private Camera mCamera = null;
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


        setContentView(R.layout.connexion);
        Log.d("Lancement", "OK");

        final Button button = (Button) findViewById(R.id.bouton_connexion);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(appareilPhotoExiste(MainActivity4.this)) {
                    openCamera();
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
            // au moins un appreil photo a été trouvé
            return true;
        } else {
            // aucun appareil photo n'a été trouvé
            return false;
        }
    }

    private void openCamera(){

        try{
            mCamera = Camera.open(0);//you can use open(int) to use different cameras
        } catch (Exception e){
            Log.d("ERROR", "Failed to get camera: " + e.getMessage());
        }
    }

}
