package com.example.chris.blatoph.Http;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import org.json.*;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;



/**
 * Created by chris on 19/05/2017.
 */

public class RequeteServeur extends AsyncTask<String, Void, JSONArray> {

    protected JSONArray doInBackground(String ...params) {

        String reponse = null;
        JSONArray reponseJSON = null;

        switch(params[0]){
            case "GET": reponseJSON = requeteGet(params[1]);
                break;
            case "POST": reponseJSON = requetePost(params[1], params[2]);
                break;
            case "PUT": reponseJSON = requetePut(params[1], params[2]);
                break;
            case "DELETE": reponseJSON = requeteDelete(params[1]);
                break;
            default: reponseJSON = null;
                break;
        }

       return  reponseJSON;

    }

    protected void onPostExecute(String result) {
        Log.d("Requete", "PostExecute");
    }

    public JSONArray requeteGet(String url){

        InputStream response = null;
        String reponse = null;
        HttpURLConnection connection = null;
        URL obj = null;
        JSONObject reponseJson = new JSONObject();
        JSONArray lejson = new JSONArray();
        int codeReponse = 0;

        try {
            obj = new URL(url);
            connection = (HttpURLConnection) obj.openConnection();
            connection.setRequestProperty("Accept-Charset", "UTF-8");
            connection.setRequestProperty("Content-Type", "application/json");
            codeReponse = connection.getResponseCode();
            response = new URL(url).openStream();

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (Scanner scanner = new Scanner(response)) {
            reponse = scanner.useDelimiter("\\A").next();
        }
        catch(NullPointerException e){
            e.printStackTrace();
        }

        try {

            lejson = new JSONArray(reponse);
            reponseJson.put("code",codeReponse);

            lejson.put(reponseJson);
        } catch (JSONException e) {
            Log.d("Requete", e.getMessage());
        }
        return lejson;
    }

    public JSONArray requetePost(String url, String body){

        InputStream response = null;
        String reponse = null;
        HttpURLConnection connection = null;
        URL obj = null;
        int codeReponse = 0;

        JSONObject reponseJson = new JSONObject();
        JSONArray lejson = new JSONArray();
        try {

            obj = new URL(url);
            connection = (HttpURLConnection) obj.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Accept-Charset", "UTF-8");
            connection.setRequestProperty("Content-Type", "application/json");

            OutputStreamWriter wr = new OutputStreamWriter(connection.getOutputStream());
            wr.write(body);
            wr.flush();

            codeReponse = connection.getResponseCode();
            response = new URL(url).openStream();

        } catch (MalformedURLException e) {
            e.printStackTrace();

        } catch (IOException e) {
            e.printStackTrace();
        }

        try (Scanner scanner = new Scanner(response)) {
            reponse = scanner.useDelimiter("\\A").next();
        }

        try {
            lejson = new JSONArray(reponse);
            reponseJson.put("code",codeReponse);

            lejson.put(reponseJson);
        } catch (JSONException e) {
            Log.d("Requete", e.getMessage());
        }
        return lejson;
    }

    public JSONArray requetePut(String url, String body){

        InputStream response = null;
        String reponse = null;
        HttpURLConnection connection = null;
        URL obj = null;
        int codeReponse = 0;
        JSONArray lejson = new JSONArray();

        JSONObject reponseJson = new JSONObject();
        try {
            obj = new URL(url);
            connection = (HttpURLConnection) obj.openConnection();
            connection.setRequestMethod("PUT");
            connection.setRequestProperty("Accept-Charset", "UTF-8");
            connection.setRequestProperty("Content-Type", "application/json");

            OutputStreamWriter wr = new OutputStreamWriter(connection.getOutputStream());
            wr.write(body);
            wr.flush();

            codeReponse = connection.getResponseCode();
            response = new URL(url).openStream();

        } catch (MalformedURLException e) {
            e.printStackTrace();

        } catch (IOException e) {
            e.printStackTrace();
        }

        try (Scanner scanner = new Scanner(response)) {
            reponse = scanner.useDelimiter("\\A").next();
        }

        try {
            lejson = new JSONArray(reponse);
            reponseJson.put("code",codeReponse);

            lejson.put(reponseJson);
        } catch (JSONException e) {
            Log.d("Requete", e.getMessage());
        }
        return lejson;
    }

    public JSONArray requeteDelete(String url){

        InputStream response = null;
        String reponse = null;
        HttpURLConnection connection = null;
        URL obj = null;
        int codeReponse = 0;
        JSONArray lejson = new JSONArray();

        JSONObject reponseJson = new JSONObject();
        try {
            obj = new URL(url);
            connection = (HttpURLConnection) obj.openConnection();
            connection.setRequestMethod("DELETE");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.connect();
            codeReponse = connection.getResponseCode();

        } catch (MalformedURLException e) {
            e.printStackTrace();

        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            lejson = new JSONArray();
            reponseJson.put("code",codeReponse);

            lejson.put(reponseJson);
        } catch (JSONException e) {
            Log.d("Requete", e.getMessage());
        }
        return lejson;
    }
}
