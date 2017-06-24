/* Classe obsolète ?
Utilisation d'une imageview suffisant ?
 */

/**
 * Created by Sarah Pierson on 23/03/2017.
 */

package com.example.chris.blatoph.Classes;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;

import com.example.chris.blatoph.Interfaces.Edition;

import java.io.File;
import java.util.Date;


public class Photo  implements Edition,Comparable<Photo>{

    private String titre, legende;
    private Date dateCreation;
    private Bitmap image;
    private String path;

    private String id;

    /*
     * Constructeur d'une photo, avec un parametrage au niveau des dimensions, du type
     * et du titre de la photo
     * @params width, height, imageType, titre
     */
    public Photo(String titre, String legende, String path){
        this.titre = titre;
        this.legende=legende;
        this.path = path;
        dateCreation = new Date();
    }

    public Photo(String id, String titre, String legende, Bitmap image){
        this.titre = titre;
        this.legende=legende;
        this.image = image;
        dateCreation = new Date();
    }

	/*
	 * (non-Javadoc)
	 * @see Edition#renommer(java.lang.String)
	 */

    public boolean renommer(String renommer){

        titre = renommer;
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
	 * Methode getTitre
	 * return Le titre de la photo sous forme de chaine de caractere
	 */

    public String getTitre(){
        return titre;
    }

    public String getLegende(){
        return legende;
    }

    public Bitmap getImage(){

        /*
        File image = new File(this.image);
        BitmapFactory.Options bmOptions = new BitmapFactory.Options();
        Bitmap bitmap = BitmapFactory.decodeFile(image.getAbsolutePath(),bmOptions);
        bitmap = Bitmap.createScaledBitmap(bitmap,bitmap.getWidth(),bitmap.getHeight(),true);
*/

        return image;
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

    public void setLegende(String legende){
        this.legende=legende;
    }
}
