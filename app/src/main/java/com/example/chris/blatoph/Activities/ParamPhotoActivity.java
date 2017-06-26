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
import com.example.chris.blatoph.Http.RequeteServeurFile;
import com.example.chris.blatoph.LesObjets;
import com.example.chris.blatoph.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Date;
import java.util.concurrent.ExecutionException;

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


        final String url = obj.getUrl() + "photos";
        final String path = obj.getPath();
        final String albumCourantId = obj.getUtilisateur().getAlbumCourantId();
        Log.d("AlbumCourantIdPhoto", albumCourantId);
        final String utilisateurId = obj.getUtilisateur().getId();

        final Button button = (Button) findViewById(R.id.ajouter);
        button.setOnClickListener(new View.OnClickListener() {
                                      public void onClick(View v) {



                                          /*Photo p = new Photo(titre.getText().toString(),legende.getText().toString(),"");
                                          JSONObject photoJson = new JSONObject();
                                          try {
                                              photoJson.put("titre",p.getTitre());
                                              album.put("date_creation","08-89");
                                              album.put("uti_id",1);
                                          } catch (JSONException e) {
                                              e.printStackTrace();
                                          }*/

                                          RequeteServeurFile requeteFile = new RequeteServeurFile();
                                          try {
                                              requeteFile.execute("POST", url,path, titre.getText().toString(), legende.getText().toString(), new Date().toString(), albumCourantId, utilisateurId ).get();
                                          } catch (InterruptedException e) {
                                              e.printStackTrace();
                                          } catch (ExecutionException e) {
                                              e.printStackTrace();
                                          }

                                          Intent intent = new Intent(getApplicationContext(), AppareilPhotoActivity.class);
                                          startActivity(intent);
                                      }


                                  }
        );
    }
}
