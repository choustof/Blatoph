package com.example.chris.blatoph.Exceptions;

/**
 * Created by Sarah Pierson on 23/03/2017.
 */

@SuppressWarnings("serial")
public class AlbumInconnuException extends Exception {


	/*
	 * Constructeur AlbumInconnuException
	 * Constructeur qui fait appel au constructeur sans parametres de Exception
	 */

    public AlbumInconnuException(){
        super();
    }

	/*
	 * Constructeur AlbumInconnuException
	 * Constructeur qui fait appel au constructeur qui prend un String en parametre
	 * @params s
	 */

    public AlbumInconnuException(String s){
        super(s);
    }
}
