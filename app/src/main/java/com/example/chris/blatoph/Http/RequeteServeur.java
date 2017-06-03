package com.example.chris.blatoph.Http;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.ArrayList;

import static java.lang.Thread.sleep;


/**
 * Created by chris on 19/05/2017.
 */

public class RequeteServeur {

    private String url;
    private RequestQueue queue;

    public RequeteServeur(Context context, String url){

        this.url = url;
    }

    public ArrayList<String> requeteGet(){

        final ArrayList<String> reponse = new ArrayList<String>();

        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    public void onResponse(String response) {

                        // Display the first 500 characters of the response string.
                        reponse.add(response);
                        Log.d("Requete",reponse.toString());
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("Requete", "Error");
                reponse.add("That didn't work!");

            }
        });

        return reponse;
    }
}
