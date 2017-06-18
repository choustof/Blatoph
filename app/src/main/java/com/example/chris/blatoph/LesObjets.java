package com.example.chris.blatoph;


import android.app.Application;

import com.example.chris.blatoph.Classes.Album;
import com.example.chris.blatoph.Classes.Photo;
import com.example.chris.blatoph.Classes.Utilisateur;

import java.util.ArrayList;

/**
 * Created by chris on 15/06/2017.
 */

public class LesObjets extends Application {

    private ArrayList<Photo> photos;
    private ArrayList<Album> albums;
    private Utilisateur utilisateur;
    private String path;

    public void setPath(String path) {
        this.path = path;
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

    public void setAlbums(ArrayList<Album> albums) {
        this.albums = albums;
    }

    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }

    public ArrayList<Album> getAlbums() {
        return albums;
    }

    public Utilisateur getUtilisateur() {
        return utilisateur;
    }


}