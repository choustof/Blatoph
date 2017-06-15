package com.example.chris.blatoph.Activities;

import android.app.ListActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;

import com.example.chris.blatoph.R;

/**
 * Created by Sarah Pierson on 09/06/2017.
 */

public class PhotoActivity extends ListActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        String[] values = new String[] { "Device",
                "Géo localisation", "Accéléromètre",
                "Navigateur internet", "Dialogues", "Album photos",
                "Connexion réseau", "Gestion des fichiers",
                "Carnet de contacts" };

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                R.layout.une_photo , values);
        setListAdapter(adapter);
    }}
