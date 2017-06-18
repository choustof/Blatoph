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

import com.example.chris.blatoph.Classes.Photo;
import com.example.chris.blatoph.LesObjets;
import com.example.chris.blatoph.R;

/**
 * Created by Sarah Pierson on 08/06/2017.
 */

public class ParamPhotoActivity extends AppCompatActivity {

    Resources res;

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.param_photo);
        //Ne pas ouvrir le clavier à l'ouverture
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        //Permet de récupérer les données stockées dans le dossier des ressources
        res = this.getResources();


        final EditText titre = (EditText)findViewById(R.id.titre_param_photo);
        titre.setHint(res.getString(R.string.titre_param_photo));
        final EditText legende = (EditText)findViewById(R.id.legende_param_photo);
        legende.setHint(res.getString(R.string.legende_param_photo));

        LesObjets obj = (LesObjets)getApplicationContext();
        Log.d("LE PATH", obj.getPath());



        final Button button = (Button) findViewById(R.id.ajouter);
        button.setOnClickListener(new View.OnClickListener() {
                                      public void onClick(View v) {
                                          Intent intent = new Intent(getApplicationContext(), AppareilPhotoActivity.class);
                                          startActivity(intent);
                                      }


                                  }
        );
    }
}
