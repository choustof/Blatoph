package com.example.chris.blatoph.Exceptions;

/**
 * Created by Sarah Pierson on 23/03/2017.
 */

@SuppressWarnings("serial")
public class AjoutAlbumException extends Exception{

	/*
	 * Constructeur AjoutAlbumException
	 * Constructeur qui fait appel au constructeur sans parametres de Exception
	 */

    public AjoutAlbumException(){
        super();
    }

    /*
     * Constructeur AjoutAlbumException
     * Constructeur qui fait appel au constructeur qui prend un String en parametre
     * @params s
     */
    public AjoutAlbumException(String s){
        super(s);
    }
}

