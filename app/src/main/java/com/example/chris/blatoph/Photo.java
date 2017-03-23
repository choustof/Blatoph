/* Classe obsolète ?
Utilisation d'une imageview suffisant ?
 */

/**
 * Created by Sarah Pierson on 23/03/2017.
 */

package com.example.chris.blatoph;
import android.media.Image;

import java.util.Date;


public class Photo  implements Edition,Comparable<Photo>{

    @SuppressWarnings("unused")
    private String nom, legende;
    private Date dateCreation;

    /*
     * Constructeur d'une photo, avec un parametrage au niveau des dimensions, du type
     * et du nom de la photo
     * @params width, height, imageType, nom
     */
    public Photo(int width, int height, int imageType,String nom){
        //super(width,height,imageType);
        this.nom = nom;
        dateCreation = new Date();
    }

	/*
	 * (non-Javadoc)
	 * @see Edition#renommer(java.lang.String)
	 */

    public boolean renommer(String renommer){

        nom = renommer;
        return true;

    }

    /*
     * (non-Javadoc)
     * @see Edition#supprimer()
     */
    public boolean supprimer(){
        return true;
    }

    /*
     * Methode dateCreation
     * return La date de creation de l'image sous forme de chaine de caractere
     */
    public String dateCreation(){
        return dateCreation.toString();
    }

	/*
	 * Methode getDate
	 * return La date de creation de la photo
	 */

    public Date getDate(){
        return dateCreation;
    }

	/*
	 * Methode getNom
	 * return Le nom de la photo sous forme de chaine de caractere
	 */

    public String getNom(){
        return nom;
    }

    /*
     *
     * (non-Javadoc)
     * @see java.lang.Comparable#compareTo(java.lang.Object)
     * A utiliser pour le trie des photos par date de creation dans une liste
     */
    public int compareTo(Photo photo){

        return dateCreation.compareTo(photo.getDate());
    }
}
