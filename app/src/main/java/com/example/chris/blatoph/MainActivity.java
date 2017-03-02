package com.example.chris.blatoph;

import android.content.Context;
import android.content.pm.PackageManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.view.*;
import android.hardware.camera2.*;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.connexion);
        Log.d("Lancement", "OK");

        final Button button = (Button) findViewById(R.id.bouton_connexion);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(appareilPhotoExiste(MainActivity.this)) {
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
        /** A safe way to get an instance of the Camera object.
        public static Camera getCameraInstance(){
            Camera c = null;
            try {
                c = Camera.open(); // attempt to get a Camera instance
            }
            catch (Exception e){
                // Camera is not available (in use or does not exist)
            }
            return c; // returns null if camera is unavailable
        }*/

        setContentView(R.layout.appareil_photo);
    }

}
