package com.example.chris.blatoph;


import android.app.Application;

import com.example.chris.blatoph.Classes.Album;
import com.example.chris.blatoph.Classes.Photo;
import com.example.chris.blatoph.Classes.Utilisateur;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chris on 15/06/2017.
 */

public class LesObjets extends Application {

    private ArrayList<Photo> photos;
    private ArrayList<Album> albums;
    private ArrayList<Utilisateur> amis;
    private Utilisateur utilisateur;
    private String path, url;

    public void setPath(String path) {
        this.path = path;
    }

    public void setUrl(String url){
        this.url = url;
    }

    public String getUrl(){
        return url;
    }

    public String getPath() {

        return path;
    }

    private ArrayList<Photo> getPhotos(){
        return photos;

    }

    public void setPhotos(ArrayList<Photo> photos) {
        this.photos = photos;
    }

    public void setAlbums(List<Album> albums) {
        this.albums = (ArrayList<Album>)albums;
    }

    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }

    public void setAmis(List<Utilisateur> amis){
        this.amis = (ArrayList<Utilisateur>)amis;
    }

    public ArrayList<Album> getAlbums() {
        return albums;
    }

    public Utilisateur getUtilisateur() {
        return utilisateur;
    }


}
