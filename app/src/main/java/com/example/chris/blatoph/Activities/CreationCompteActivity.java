package com.example.chris.blatoph.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.chris.blatoph.R;

/**
 * Created by Sarah Pierson on 15/06/2017.
 */

public class CreationCompteActivity extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.creation_compte);
        Log.d("Lancement", "OK");



        final Button button = (Button) findViewById(R.id.button_creation);
        button.setOnClickListener(new View.OnClickListener() {
                                      public void onClick(View v) {
                                          Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                                          startActivity(intent);
                                      }


                                  }
        );
    }
}

