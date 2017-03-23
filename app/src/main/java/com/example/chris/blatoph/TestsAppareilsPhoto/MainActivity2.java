package com.example.chris.blatoph.TestsAppareilsPhoto;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.hardware.Camera;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

import com.example.chris.blatoph.R;

import java.io.File;

public class MainActivity2 extends AppCompatActivity {

    static final int REQUEST_IMAGE_CAPTURE = 1;
    private Camera mCamera = null;
    private AppareilPhotoActivity2 mCameraView = null;


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
                if(appareilPhotoExiste(MainActivity2.this)) {
                   openCamera();
                    //dispatchTakePictureIntent();

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

        setContentView(R.layout.appareil_photo2);

        try{
            mCamera = Camera.open(0);//you can use open(int) to use different cameras
        } catch (Exception e){
            Log.d("ERROR", "Failed to get camera: " + e.getMessage());
        }

        if(mCamera != null) {
            mCameraView = new AppareilPhotoActivity2(this, mCamera);//create a SurfaceView to show camera data
            FrameLayout camera_view = (FrameLayout)findViewById(R.id.camera_view2);
            camera_view.addView(mCameraView);//add the SurfaceView to the layout
        }


    }

    /* FOnction qui fait appel à l'application appareil photo */
    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
    }

}
