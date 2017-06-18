package com.example.chris.blatoph.Http;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Environment;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
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

        Bitmap myBitmap = null;
        Log.d("Reponse POST", params[0]);
        switch (params[0]){
            case "GET": myBitmap = getBitmapFromURL("http://192.168.43.53/blatoph-server/web/images/"+params[1]);
                break;
            case "POST": requetePost("http://192.168.0.34/blatoph-server/web/photos","");
                break;
            case "PUT": requetePut(params[1], params[2]);
                break;
            case "DELETE": requeteDelete(params[1]);
                break;
            default:
                break;
        }

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

        Log.d("Reponse POST", "On est dans le post");
        InputStream response = null;
        String reponse = null;
        HttpURLConnection connection = null;
        URL obj = null;
        int codeReponse = 0;
        int bytesRead, bytesAvailable, bufferSize;
        byte[] buffer;
        int maxBufferSize = 1*1024*1024;
        DataOutputStream dos = null;

        String existingFileName = Environment.getExternalStorageDirectory()+"/Blatoph/blatoph-2017-06-03-15-40-17.jpg";
        String lineEnd = "\r\n";
        String twoHyphens = "--";
        String boundary =  "*****";

        JSONObject reponseJson = new JSONObject();
        JSONArray lejson = new JSONArray();
        try {
            FileInputStream fileInputStream = new FileInputStream(new File(existingFileName) );

            obj = new URL(url);
            connection = (HttpURLConnection) obj.openConnection();
            connection.setDoInput(true);
            connection.setDoOutput(true);
            connection.setUseCaches(false);
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Connection","Keep-Alive");
            connection.setRequestProperty("Content-Type", "multipart/form-data;boundary="+boundary);
            connection.setRequestProperty("postman-token", "578b9894-723f-743b-d469-ac41a73de2f4");

            Log.d("Reponse POST", lineEnd);

            dos = new DataOutputStream(connection.getOutputStream());
            Log.d("Reponse POST", "On est la");
            dos.writeBytes(twoHyphens+boundary+lineEnd);
            dos.writeBytes("Content-Disposition: form-data; name=\"image\";filename=\""+ Environment.getExternalStorageDirectory()+"/Blatoph/blatoph-2017-06-03-15-40-17.jpg"+"\""+lineEnd);
            dos.writeBytes(twoHyphens+boundary+lineEnd);
            dos.writeBytes("Content-Disposition: form-data; name=\\\"titre\\\"\\r\\n\\r\\nMDR"+lineEnd);
            dos.writeBytes(twoHyphens+boundary+lineEnd);
            dos.writeBytes("Content-Disposition: form-data; name=\\\"legende\\\"\\r\\n\\r\\nLOL"+lineEnd);
            dos.writeBytes(twoHyphens+boundary+lineEnd);
            dos.writeBytes("Content-Disposition: form-data; name=\\\"date_creation\\\"\\r\\n\\r\\nhier"+lineEnd);
            dos.writeBytes(twoHyphens+boundary+lineEnd);
            dos.writeBytes("Content-Disposition: form-data; name=\\\"alb_id\\\"\\r\\n\\r\\n1"+lineEnd);
            dos.writeBytes(twoHyphens+boundary+lineEnd);
            dos.writeBytes("Content-Disposition: form-data; name=\\\"uti_id\\\"\\r\\n\\r\\n1"+lineEnd);
            dos.writeBytes(lineEnd);


            bytesAvailable = fileInputStream.available();
            bufferSize = Math.min(bytesAvailable,maxBufferSize);
            buffer = new byte[bufferSize];
            bytesRead = fileInputStream.read(buffer,0,bufferSize);
            while(bytesRead > 0){
                dos.write(buffer, 0, bufferSize);
                bytesAvailable = fileInputStream.available();
                bufferSize = Math.min(bytesAvailable, maxBufferSize);
                bytesRead = fileInputStream.read(buffer, 0, bufferSize);
                Log.d("Reponse POST", "+1");

            }
            dos.writeBytes(lineEnd);
            dos.writeBytes(twoHyphens+boundary+twoHyphens+lineEnd);
            fileInputStream.close();
            dos.flush();
            dos.close();

            codeReponse = connection.getResponseCode();

            Log.d("Reponse POST", ""+codeReponse);
            response = new URL(url).openStream();

           // Log.d("Reponse POST", reponse);

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
