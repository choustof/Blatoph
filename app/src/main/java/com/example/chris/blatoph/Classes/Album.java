package com.example.chris.blatoph.Classes;

/**
 * Created by Sarah Pierson on 23/03/2017.
 */

import android.graphics.Bitmap;

import com.example.chris.blatoph.Interfaces.Edition;
import com.example.chris.blatoph.Exceptions.ObservateurException;
import com.example.chris.blatoph.Classes.Photo;

import java.util.*;


public class Album extends Observable implements Edition {

    private String titre;


    private ArrayList<Photo> listePhotos;
    private ArrayList<Utilisateur> listeObservateur;
    private Utilisateur createur;
    private Date dateCreation;
    public Boolean albumCourant;

	/*
	 * Le constructeur prend en parametre le nom du createur de l'album car
	 * ceux-ci sont voues a etre partages
	 */

    public Album(String titre, Utilisateur createur){

        this.titre = titre;
        this.createur = createur;
        dateCreation = new Date();
        listePhotos = new ArrayList<Photo>();
        listeObservateur = new ArrayList<Utilisateur>();
        this.albumCourant=false;
    }

	/*
	 * Methode renommer
	 * Cette methode permet de changer le nom de l'album
	 * @params renommer
	 */

    public boolean renommer(String renommer){

        titre = renommer;
        return true;
    }

    public boolean supprimer(){ return true; }

    /*
     * Methode getNom
     * Methode qui retourne le nom de l'albuù
     */
    public String getTitre(){
        return titre;
    }


    public Date getDateCreation(){
        return dateCreation;
    }

    public Boolean getAlbumCourant(){
        return albumCourant;
    }

    public String dateCreation(){
        return dateCreation.toString();
    }

    /*
     * Methode ajouterPhoto
     * Methode qui permet d'ajouter une nouvelle photo a l'album
     * @params photo
     */
    public boolean ajouterPhoto(Photo photo){

       /* setChanged();
        notifyObservers(photo); */
        listePhotos.add(photo);
        return true;
    }



    /*
     * Methode getCreateur
     * Methode qui permet de retourner les infos concernant le createur de l'album
     * Ici on a decide de retourner l'utilisateur entierement, pour avoir le plus d'information possible
     * Cependant il n'est peut-etre pas necessaire de le retourner entierement dans tous les cas
     */
    public Utilisateur getCreateur(){
        return this.createur;
    }

    /*
     * Methode supprimerPhoto
     * Methode qui permet de supprimer une photo dans un album
     */
    public void supprimerPhoto(String photo) throws Exception{

        if(listePhotos.contains(photo)){
            listePhotos.remove(photo);
        }
        else{
            throw new Exception("Cette photo ne fait pas partie de l'album");
        }
    }

	/*
	 * Methode getInfos()
	 * Methode qui retourner les informations sur l'album
	 * @return Informations utiles sur l'album
	 */

    public String getInfos(){

        String infos;
        infos = titre+ " - Cet album a été créé le "+dateCreation+" par "+createur.getPrenom()+"\n";
        for(int i = 0; i<listePhotos.size();i++){
            infos += listePhotos.get(i)+"\n";
        }
        return infos;
    }

    /*
     * Methode toString()
     * Retourne le minimum d'informations sur l'album, tout en permettant de savoir de quel album il s'agit
     * @return Une chaine de caractere regroupant des informations sur l'albuù
     */
    public String toString(){
        return titre;
    }

    public boolean setAlbumCourant(Boolean c){
        albumCourant=c;
        return true;
    }

    public Photo afficherPhoto(){
         return listePhotos.get(0);
    }


}
