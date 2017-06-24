package com.example.chris.blatoph.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.example.chris.blatoph.Classes.Album;
import com.example.chris.blatoph.Classes.Utilisateur;
import com.example.chris.blatoph.AlbumAdapter;
import com.example.chris.blatoph.Http.RequeteServeur;
import com.example.chris.blatoph.LesObjets;
import com.example.chris.blatoph.R;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import static android.provider.AlarmClock.EXTRA_MESSAGE;

/**
 * Created by Sarah Pierson on 08/06/2017.
 */

public class ListeAlbumActivity extends AppCompatActivity {

    ListView mListView;
    String url,url2;
    RequeteServeur requete = new RequeteServeur();
    LesObjets obj;



        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.liste_album);


            final Button nouveau = (Button) findViewById(R.id.nvx);
            nouveau.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    Intent intent = new Intent(getApplicationContext(), CreationAlbumActivity.class);
                    startActivity(intent);
                }


            });

            obj = (LesObjets)getApplicationContext();

            url = obj.getUrl()+"utilisateurs/"+obj.getUtilisateur().getId()+"albums";
            url2 = obj.getUrl()+"utilisateurs/"+obj.getUtilisateur().getId()+"albumPartages";

            mListView = (ListView) findViewById(R.id.listView_album);
            afficherListeAlbum();


            mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                public void onItemClick(AdapterView<?> parent, View view,int position, long id)
                {
                    Intent intent = new Intent(getApplicationContext(), ListePhotoActivity.class);
                    intent.putExtra("ALB_ID",position);
                    startActivity(intent);
                }});
        }

        private List<Album> genererAlbum(){
            Utilisateur moi = obj.getUtilisateur();
            /*JSONArray reponseAlbums;
            JSONArray reponseAlbumsPartages;

            try {
                reponseAlbums = requete.execute("GET", url).get();
                reponseAlbumsPartages = requete.execute("GET", url2).get();

                Log.d("Requete", reponseAlbums.getJSONObject(0).get("message").toString());

            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }

            Log.d("PATH", Environment.getExternalStorageDirectory()+"/Blatoph/blatoph-2017-06-03-15-40-17.jpg");
*/

            List<Album> albums = new ArrayList<Album>(obj.getUtilisateur().getAlbums().values());
            obj.setAlbums(albums);

            return albums;
        }

        private void afficherListeAlbum(){
            List<Album> albums = genererAlbum();

            AlbumAdapter adapter = new AlbumAdapter(ListeAlbumActivity.this, albums);
            mListView.setAdapter(adapter);
        }
    }



