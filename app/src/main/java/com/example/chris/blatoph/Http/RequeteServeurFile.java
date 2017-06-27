package com.example.chris.blatoph.Http;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.os.AsyncTask;
import android.os.Environment;
import android.util.Log;

import com.example.chris.blatoph.LesObjets;

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

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;


/**
 * Created by chris on 19/05/2017.
 */

public class RequeteServeurFile extends AsyncTask<String, Void, Bitmap> {


    LesObjets obj;

    protected Bitmap doInBackground(String ...params) {

        Bitmap myBitmap = null;

        switch (params[0]){
            case "GET": myBitmap = getBitmapFromURL(params[1]);
                break;
            case "POST": requetePost(params[1],params[2],params[3],params[4],params[5],params[6],params[7]);
                Log.d("Reponse POST", params[0]);
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

    public void requetePost(String url, String path, String titre, String legende, String date, String albumCourantId, String utilisateurId){

        if(legende.equals("")){
            Log.d("PHOTO","NULL");
            legende = " ";
        }
        OkHttpClient client = new OkHttpClient();

        MediaType MEDIA_TYPE_PNG = MediaType.parse("image/png");

        File image = new File(path);
        Log.d("Valeurs",image.getName() );


        RequestBody requestBody = new MultipartBody.Builder().setType(MultipartBody.FORM)
                .addFormDataPart("image", image.getName(), RequestBody.create(MEDIA_TYPE_PNG, image))
                .addFormDataPart("titre",titre)
                .addFormDataPart("date_creation",date)
                .addFormDataPart("legende",legende)
                .addFormDataPart("alb_id",albumCourantId)
                .addFormDataPart("uti_id",utilisateurId)
                .build();

        Request request = new Request.Builder().url(url)
                .post(requestBody).build();

        Response response = null;
        try {
            response = client.newCall(request).execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (!response.isSuccessful()) {
            try {
                throw new IOException("Unexpected code " + response);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static Bitmap getBitmapFromURL(String src) {
        try {
            URL url = new URL(src);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);
            connection.connect();
            InputStream input = connection.getInputStream();
            Bitmap myBitmap = BitmapFactory.decodeStream(input);
            Log.d("SOurce","src");

            Matrix matrix = new Matrix();
            matrix.postRotate(90);

            Bitmap avantRotation = Bitmap.createScaledBitmap(myBitmap,myBitmap.getWidth(),myBitmap.getHeight(),true);
            Bitmap apresRotation = Bitmap.createBitmap(avantRotation,0,0,avantRotation.getWidth(),avantRotation.getHeight(),matrix,true);
            return apresRotation;
        } catch (IOException e) {
            // Log exception
            return null;
        }
    }


}
