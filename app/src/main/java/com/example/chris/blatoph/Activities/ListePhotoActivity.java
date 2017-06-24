package com.example.chris.blatoph.Activities;

import android.app.ListActivity;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.chris.blatoph.Classes.Album;
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

/**
 * Created by Sarah Pierson on 09/06/2017.
 */

public class ListePhotoActivity extends AppCompatActivity {

    private ListView mListView;
    LesObjets obj;
    int albId;
    RequeteServeur requete = new RequeteServeur();
    JSONArray reponse;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.liste_photo);

        requete = new RequeteServeur();
        reponse = null;
        obj = (LesObjets)getApplicationContext();

        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if(extras == null) {
                albId= 0;
            } else {
                albId= extras.getInt("ALB_ID");
            }
        } else {
            albId = (int) savedInstanceState.getSerializable("ALB_ID");
        }

        mListView = (ListView) findViewById(R.id.listView_photo);

        afficherListePhotos();
    }

    private List<Photo> genererPhotos(){
        List<Photo> photos = new ArrayList<Photo>();

        try {

            //recuperation des photos

            String url = obj.getUrl() + "albums/" + obj.getAlbums().get(albId).getId() + "/photos";
            reponse = new RequeteServeur().execute("GET", url).get();
            reponse.remove(reponse.length()-1);

            for (int i = 0; i < reponse.length(); i++) {
                JSONObject photo = reponse.getJSONObject(i);
                RequeteServeurFile requeteFile = new RequeteServeurFile();
                photos.add(new Photo(
                        photo.get("id").toString(),
                        photo.get("titre").toString(),
                        photo.get("legende").toString(),
                        requeteFile.execute("GET", obj.getUrl()+"images/"+photo.get("path"), "").get()
                ));
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        Log.d("PATH",Environment.getExternalStorageDirectory()+"/Blatoph/blatoph-2017-06-03-15-40-17.jpg");

       /* photos.add(new Photo("Photo 1","",Environment.getExternalStorageDirectory()+"/Blatoph/blatoph-2017-06-03-15-40-17.jpg"));
        photos.add(new Photo("Photo 2","",Environment.getExternalStorageDirectory()+"/Blatoph/blatoph-2017-06-03-15-40-17.jpg"));
        photos.add(new Photo("Photo 3","",Environment.getExternalStorageDirectory()+"/Blatoph/blatoph-2017-06-03-15-40-17.jpg"));
        photos.add(new Photo("Photo 4","",Environment.getExternalStorageDirectory()+"/Blatoph/blatoph-2017-06-03-15-40-17.jpg"));
        photos.add(new Photo("Photo 1","",Environment.getExternalStorageDirectory()+"/Blatoph/blatoph-2017-06-03-15-40-17.jpg"));
        photos.add(new Photo("Photo 2","",Environment.getExternalStorageDirectory()+"/Blatoph/blatoph-2017-06-03-15-40-17.jpg"));
        photos.add(new Photo("Photo 3","",Environment.getExternalStorageDirectory()+"/Blatoph/blatoph-2017-06-03-15-40-17.jpg"));
        photos.add(new Photo("Photo 4","",Environment.getExternalStorageDirectory()+"/Blatoph/blatoph-2017-06-03-15-40-17.jpg"));
        photos.add(new Photo("Photo 1","",Environment.getExternalStorageDirectory()+"/Blatoph/blatoph-2017-06-03-15-40-17.jpg"));
        photos.add(new Photo("Photo 2","",Environment.getExternalStorageDirectory()+"/Blatoph/blatoph-2017-06-03-15-40-17.jpg"));
        photos.add(new Photo("Photo 3","",Environment.getExternalStorageDirectory()+"/Blatoph/blatoph-2017-06-03-15-40-17.jpg"));
        photos.add(new Photo("Photo 4","",Environment.getExternalStorageDirectory()+"/Blatoph/blatoph-2017-06-03-15-40-17.jpg"));
*/
        return photos;
    }

    private void afficherListePhotos(){
        List<Photo> photos = genererPhotos();

        PhotoAdapter adapter = new PhotoAdapter(ListePhotoActivity.this, photos);
        mListView.setAdapter(adapter);
    }
}
