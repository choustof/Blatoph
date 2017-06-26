package com.example.chris.blatoph;


import android.app.Application;

import com.example.chris.blatoph.Classes.Album;
import com.example.chris.blatoph.Classes.Photo;
import com.example.chris.blatoph.Classes.Utilisateur;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by chris on 15/06/2017.
 */

public class LesObjets extends Application {

    private ArrayList<Photo> photos;
    private ArrayList<Album> albums;
    private ArrayList<Utilisateur> amis;
    private HashMap<String,Utilisateur> amisSelectionnes = new HashMap<String,Utilisateur>();
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

    public ArrayList<Photo> getPhotos(){
        return photos;

    }

    public void setPhotos(List<Photo> photos) {
        this.photos = (ArrayList<Photo>)photos;
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

    public ArrayList<Utilisateur> getAmis(){
        return amis;
    }

    public ArrayList<Album> getAlbums() {
        return albums;
    }

    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    public void selectionnerAmi(String id, Utilisateur ami){
        amisSelectionnes.put(id,ami);
    }

    public void deselectionnerAmi(String id){
        amisSelectionnes.remove(id);
    }

    public boolean estSelectionne(String id){
        return amisSelectionnes.containsKey(id);
    }

    public void clearAmisSelectionnes(){
        amisSelectionnes.clear();
    }

    public ArrayList<Utilisateur> getAmisSelectionnes(){
        return new ArrayList<Utilisateur>(amisSelectionnes.values());
    }

    public void clearAlbums(){
        albums = new ArrayList<Album>();
    }


}
