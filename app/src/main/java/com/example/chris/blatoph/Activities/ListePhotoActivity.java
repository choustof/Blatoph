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

import com.example.chris.blatoph.Classes.Photo;
import com.example.chris.blatoph.PhotoAdapter;
import com.example.chris.blatoph.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sarah Pierson on 09/06/2017.
 */

public class ListePhotoActivity extends AppCompatActivity {

    private ListView mListView;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.liste_photo);

        mListView = (ListView) findViewById(R.id.listView_photo);

        afficherListePhotos();
    }

    private List<Photo> genererPhotos(){
        List<Photo> photos = new ArrayList<Photo>();

        Log.d("PATH",Environment.getExternalStorageDirectory()+"/Blatoph/blatoph-2017-06-03-15-40-17.jpg");

        photos.add(new Photo("Photo 1","",Environment.getExternalStorageDirectory()+"/Blatoph/blatoph-2017-06-03-15-40-17.jpg"));
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

        return photos;
    }

    private void afficherListePhotos(){
        List<Photo> photos = genererPhotos();

        PhotoAdapter adapter = new PhotoAdapter(ListePhotoActivity.this, photos);
        mListView.setAdapter(adapter);
    }
}
