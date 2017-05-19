package com.example.chris.blatoph.Http;

import org.json.JSONObject;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by chris on 19/05/2017.
 */

public class RequeteServeur {

    private URL url;

    public RequeteServeur(String url){

        //"http://192.168.43.53/blatoph-server/web/"
        try {
            this.url = new URL(url);
        }

        catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    public JSONObject requete(int type, JSONObject requete){

        JSONObject reponse = new JSONObject();

        return reponse;
    }
}
