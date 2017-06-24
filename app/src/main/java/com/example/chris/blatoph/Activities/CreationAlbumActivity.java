package com.example.chris.blatoph.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.chris.blatoph.R;

/**
 * Created by Sarah Pierson on 24/06/2017.
 */

public class CreationAlbumActivity extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.creation_album);
        Log.d("Lancement", "OK");


        final Button amis = (Button) findViewById(R.id.amis);
        amis.setOnClickListener(new View.OnClickListener() {
                                      public void onClick(View v) {
                                          Intent intent = new Intent(getApplicationContext(), AmisPatageActivity.class);
                                          startActivity(intent);
                                      }


                                  }
        );



        final Button creation = (Button) findViewById(R.id.ajouter);
        creation.setOnClickListener(new View.OnClickListener() {
                                      public void onClick(View v) {
                                          Intent intent = new Intent(getApplicationContext(), ListeAlbumActivity.class);
                                          startActivity(intent);
                                      }


                                  }
        );
    }
}

