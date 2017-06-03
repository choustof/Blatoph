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

public class RequeteServeur extends AsyncTask<String, Void, String> {

    protected String doInBackground(String ...params) {

        String reponse = null;

        switch(params[0]){
            case "GET": reponse = requeteGet(params[1]);
                break;
            case "POST": reponse = requetePost(params[1], params[2]);
                break;
            case "PUT": reponse = requetePut(params[1], params[2]);
                break;
            case "DELETE": reponse = requeteDelete(params[1]);
                break;
            default: reponse = "Mauvais type de requete";
                break;
        }

       return  reponse;

    }

    protected void onPostExecute(String result) {
        Log.d("Requete", "PostExecute");
    }

    public String requeteGet(String url){

        InputStream response = null;
        String reponse = null;
        HttpURLConnection connection = null;
        URL obj = null;
        JSONObject reponseJson = new JSONObject();

        int codeReponse = 0;

        try {
            obj = new URL(url);
            connection = (HttpURLConnection) obj.openConnection();
            connection.setRequestProperty("Accept-Charset", "UTF-8");
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
            reponseJson.put("code",codeReponse);
            reponseJson.put("reponse",reponse);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return reponseJson.toString();
    }

    public String requetePost(String url, String body){

        InputStream response = null;
        String reponse = null;
        HttpURLConnection connection = null;
        URL obj = null;
        int codeReponse = 0;

        JSONObject album = new JSONObject();
        JSONObject reponseJson = new JSONObject();
        try {
            album.put("titre","Album 9");
            album.put("date_creation","08-89");
            album.put("uti_id",1);

            obj = new URL(url);
            connection = (HttpURLConnection) obj.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Accept-Charset", "UTF-8");
            connection.setRequestProperty("Content-Type", "application/json");

            OutputStreamWriter wr = new OutputStreamWriter(connection.getOutputStream());
            wr.write(album.toString());
            wr.flush();

            codeReponse = connection.getResponseCode();
            response = new URL(url).openStream();

        } catch (JSONException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();

        } catch (IOException e) {
            e.printStackTrace();
        }

        try (Scanner scanner = new Scanner(response)) {
            reponse = scanner.useDelimiter("\\A").next();
        }

        try {
            reponseJson.put("code",codeReponse);
            reponseJson.put("reponse",reponse);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return reponseJson.toString();
    }

    public String requetePut(String url, String body){

        InputStream response = null;
        String reponse = null;
        HttpURLConnection connection = null;
        URL obj = null;
        int codeReponse = 0;

        JSONObject album = new JSONObject();
        JSONObject reponseJson = new JSONObject();
        try {
            album.put("titre","Album 20");
            album.put("date_creation","77777 Marne-la-Vallée");
            album.put("uti_id",1);

            obj = new URL(url);
            connection = (HttpURLConnection) obj.openConnection();
            connection.setRequestMethod("PUT");
            connection.setRequestProperty("Accept-Charset", "UTF-8");
            connection.setRequestProperty("Content-Type", "application/json");

            OutputStreamWriter wr = new OutputStreamWriter(connection.getOutputStream());
            wr.write(album.toString());
            wr.flush();

            codeReponse = connection.getResponseCode();
            response = new URL(url).openStream();

        } catch (JSONException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();

        } catch (IOException e) {
            e.printStackTrace();
        }

        try (Scanner scanner = new Scanner(response)) {
            reponse = scanner.useDelimiter("\\A").next();
        }

        try {
            reponseJson.put("code",codeReponse);
            reponseJson.put("reponse",reponse);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return reponseJson.toString();
    }

    public String requeteDelete(String url){

        InputStream response = null;
        String reponse = null;
        HttpURLConnection connection = null;
        URL obj = null;
        int codeReponse = 0;

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
            reponseJson.put("code",codeReponse);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return reponseJson.toString();
    }
}
