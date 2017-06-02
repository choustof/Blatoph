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


/**
 * Created by chris on 19/05/2017.
 */

public class RequeteServeur {

    private String url;
    private RequestQueue queue;

    public RequeteServeur(Context context, String url){

        this.url = url;
        queue = Volley.newRequestQueue(context);
    }

    public ArrayList<String> requeteGet(){

        final ArrayList<String> reponse = new ArrayList<String>();

        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        // Display the first 500 characters of the response string.
                        reponse.add(response);
                        Log.d("Requete","Success");


                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("Requete", "Error");
                reponse.add("That didn't work!");

            }
        });


// Add the request to the RequestQueue.
        queue.add(stringRequest);

        return reponse;
    }


}
