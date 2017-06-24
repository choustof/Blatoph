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

public class AmisPatageActivity extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.amis_partage);
        Log.d("Lancement", "OK");


        final Button ajouter = (Button) findViewById(R.id.ajt_ami);
        ajouter.setOnClickListener(new View.OnClickListener() {
                                      public void onClick(View v) {
                                          Intent intent = new Intent(getApplicationContext(), AmisPatageActivity.class);
                                          startActivity(intent);
                                      }


                                  }
        );


        final Button valider = (Button) findViewById(R.id.valider);
        valider.setOnClickListener(new View.OnClickListener() {
                                      public void onClick(View v) {
                                          Intent intent = new Intent(getApplicationContext(), CreationAlbumActivity.class);
                                          startActivity(intent);
                                      }


                                  }
        );




    }
}

