package com.example.chris.blatoph.Classes;


import com.example.chris.blatoph.Exceptions.AjoutAlbumException;
import com.example.chris.blatoph.Exceptions.AlbumInconnuException;
import com.example.chris.blatoph.Exceptions.AmiInconnuException;
import com.example.chris.blatoph.Classes.Album;
import com.example.chris.blatoph.Exceptions.ObservateurException;

import java.util.*;


public class Utilisateur{

    private String prenom,adresseMail,motDePasse;
    private TreeMap <String,Album> listeAlbums;
    private TreeMap <String,Utilisateur> listeAmis;


	/*
	 * Constructeur d'un utilisateur
	 * @params  prenom, adresseMail, motdePasse
	 */

    public Utilisateur(String prenom  ,String adresseMail,String motDePasse){
        this.prenom = prenom;
        this.adresseMail = adresseMail;
        this.motDePasse = motDePasse;
        listeAlbums = new TreeMap <String, Album>();
        listeAmis = new TreeMap <String,Utilisateur>();
    }
	/*
	 * Methode getPrenom
	 * @return Le prenom sous forme de chaine de caractere
	 */

    public String getPrenom(){
        return prenom;
    }

	/*
	 * Methode getAdresse
	 * @return L'adresse sous forme de chaine de caractere
	 */

    public String getAdresse(){
        return adresseMail;
    }

	/*
	 * Methode getMotDePasse
	 * @return Le mot de passe sous forme de chaine de caractere
	 */

    public String getMotDePasse(){
        return motDePasse;
    }



    /*
     * Methode getAlbums
     * @return Une liste des albums d'un utilisateur, avec pour clef le nom de l'album
     */
    public TreeMap<String,Album> getAlbums(){

        return listeAlbums;
    }

    /*
     * Methode listerAlbums
     * @return Une chaine de caractere listant les albums de l'utilisateur, mais aussi
     * les albums qui lui ont été partagés
     */
    public String listerAlbums(){
        String infos = "Mes Albums: \n";
        Collection<Album> elements= listeAlbums.values();
        for(Album album : elements){
            infos += album.getTitre()+"\n";
        }

        return infos;
    }

    /*
     * Methode getAlbumByName
     * @params nom
     * @return L'album dont le nom a ete passé en parametre
     */
    public Album getAlbumByName(String nom) throws AlbumInconnuException {

        if(listeAlbums.get(nom) == null){
            throw new AlbumInconnuException("Cet album n'existe pas");
        }
        return listeAlbums.get(nom);
    }

    /*
     * Mathode nouvelAlbum
     * Cette méthode est la méthode d'ajout d'un nouvel album dans la liste de l'utilisateur
     * @params album
     * @exception AjoutAlbumException
     */
    public void nouvelAlbum(String id, Album album) throws AjoutAlbumException {

            if(listeAlbums.put(id,album) == null){
                throw new AjoutAlbumException("Probleme lors de l'ajout de l'album");
            }
        }


    /*
     * Methode supprimerAlbum
     * Cette méthode permet de supprimer l'album dont le nom est passé en parametre
     * @params nom
     * @exception AlbumInconnuException
     */
    public void supprimerAlbum(String nom) throws AlbumInconnuException{
        if(listeAlbums.containsKey(nom)){
            listeAlbums.remove(nom);
        }
        else{
            throw new AlbumInconnuException("L'album n'appartient pas à la liste");
        }
    }

    /*
     * Methode supprimerAmi
     * Cette methode permet de supprimer l'ami dont l'adresse mail a ete passe en paramete
     * @params nom
     * @exception AmisInconnuException
     */
    public void supprimerAmi(String nom) throws AmiInconnuException {
        if(listeAmis.containsKey(nom)){
            listeAmis.remove(nom);
        }
        else{
            throw new AmiInconnuException(nom+" ne fait pas parti de vos amis");
        }
    }
    public boolean supprimer(){
        return true;
    }

    /*
     * Methode ajouterAmi
     * Cette methode permet d'ajouter un nouvel ami a partir d'un utilisateur passé en parametre
     * @param ami
     *
     */
    public boolean ajouterAmi(Utilisateur ami){

        listeAmis.put(ami.getAdresse(), ami);
        return true;
    }

	/*
	 * Methode getListeAmis
	 * Cette merthode retourne la liste des amis de l'utilisateurs, repérés par leur nom
	 * @return Une liste d'utilisateurs
	 */

    public TreeMap<String,Utilisateur> getListeAmis(){
        return listeAmis;
    }

	/*
	 * Methode afficherListeAmis
	 * A la difference de getListeAmis, cette methode retourne une chaine de caracteres
	 * @return La liste des amis sous forme de chaine de caracteres
	 */

    public String afficherListeAmis(){
        String infos = "";
        Collection<Utilisateur> elements= listeAmis.values();
        for(Utilisateur utilisateur : elements){
            infos += utilisateur.getPrenom()+" - "+utilisateur.getAdresse()+"\n";
        }
        return infos;
    }

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */

    public String toString(){
        String infos;
        infos = prenom+" "+prenom+" "+adresseMail+" "+motDePasse;
        return infos;
    }

    public void setPrenom(String prenom){
        this.prenom = prenom;
    }

    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
    }

    public void prendrePhoto(String image,String titre, String legende, String album){
        Photo photo = new Photo(titre,legende,image);
        listeAlbums.get(album).ajouterPhoto(photo);
        //Requete
    }

    public void partagerAlbum(ArrayList<Utilisateur> amis, Album album) throws AmiInconnuException {
        for (Utilisateur s : amis){
            if (listeAmis.containsKey(s.prenom)) {
                    //Requete

            }
            else {
                throw new AmiInconnuException(" ne fait pas parti de vos amis");
            }
        };
    }

    public void addAlbum(String id,Album albumpartage){
        listeAlbums.put(id, albumpartage);
    }
}
