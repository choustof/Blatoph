package com.example.chris.blatoph.Http;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;


/**
 * Created by chris on 19/05/2017.
 */

public class RequeteServeurFile extends AsyncTask<String, Void, Bitmap> {

    protected Bitmap doInBackground(String ...params) {

        Bitmap myBitmap = getBitmapFromURL("http://192.168.43.53/blatoph-server/web/images/"+params[1]);

       return  myBitmap;

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

    public static Bitmap getBitmapFromURL(String src) {
        try {
            URL url = new URL(src);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);
            connection.connect();
            InputStream input = connection.getInputStream();
            Bitmap myBitmap = BitmapFactory.decodeStream(input);
            return myBitmap;
        } catch (IOException e) {
            // Log exception
            return null;
        }
    }
}
