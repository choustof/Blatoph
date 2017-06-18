package com.example.chris.blatoph.Activities;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ListView;

import com.example.chris.blatoph.R;

/**
 * Created by Sarah Pierson on 08/06/2017.
 */

public class ListeAlbumActivitiy extends AppCompatActivity {

    ListView mListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.liste_album);

        mListView = (ListView) findViewById(R.id.listView);

        //Ne pas ouvrir le clavier à l'ouverture
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

    }


}
