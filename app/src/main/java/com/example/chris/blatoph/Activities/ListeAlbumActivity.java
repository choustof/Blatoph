package com.example.chris.blatoph.Activities;

import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ListView;

import com.example.chris.blatoph.Classes.Album;
import com.example.chris.blatoph.Classes.Utilisateur;
import com.example.chris.blatoph.AlbumAdapter;
import com.example.chris.blatoph.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sarah Pierson on 08/06/2017.
 */

public class ListeAlbumActivity extends AppCompatActivity {

    ListView mListView;


        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.liste_album);

            mListView = (ListView) findViewById(R.id.listView_album);

            afficherListeAlbum();
        }

        private List<Album> genererAlbum(){
            List<Album> albums = new ArrayList<Album>();
            Utilisateur moi = new Utilisateur("Blatoph","onestla@gmail.com","motdepasse");

            Log.d("PATH", Environment.getExternalStorageDirectory()+"/Blatoph/blatoph-2017-06-03-15-40-17.jpg");

            albums.add(new Album("Album 1",moi));
            albums.add(new Album("Album 2",moi));
            albums.add(new Album("Album 3",moi));
            albums.add(new Album("Album 4",moi));
            albums.add(new Album("Album 1",moi));
            albums.add(new Album("Album 2",moi));
            albums.add(new Album("Album 3",moi));
            albums.add(new Album("Album 4",moi));
            albums.add(new Album("Album 1",moi));
            albums.add(new Album("Album 2",moi));
            albums.add(new Album("Album 3",moi));
            albums.add(new Album("Album 4",moi));

            return albums;
        }

        private void afficherListeAlbum(){
            List<Album> albums = genererAlbum();

            AlbumAdapter adapter = new AlbumAdapter(ListeAlbumActivity.this, albums);
            mListView.setAdapter(adapter);
        }
    }



