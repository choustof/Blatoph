package com.example.chris.blatoph.Http;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Scanner;

import static java.lang.Thread.sleep;


/**
 * Created by chris on 19/05/2017.
 */

public class RequeteServeur extends AsyncTask<String, Void, String> {

    protected String doInBackground(String ...url) {
       return  requeteGet(url[0]);

    }

    protected void onPostExecute(Long result) {
        Log.d("Requete", "PostExecute");
    }

    public String requeteGet(String url){

        InputStream response = null;
        String reponse = null;
        URLConnection connection = null;
        try {
            connection = new URL(url).openConnection();
        } catch (IOException e) {
            e.printStackTrace();
        }
        connection.setRequestProperty("Accept-Charset", "UTF-8");
        try {
            response = new URL(url).openStream();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (Scanner scanner = new Scanner(response)) {
            reponse = scanner.useDelimiter("\\A").next();
        }

        Log.d("Requete", reponse);
        return reponse;
    }
}
