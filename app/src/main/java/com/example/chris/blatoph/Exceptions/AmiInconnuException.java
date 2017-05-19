package com.example.chris.blatoph.Exceptions;

/**
 * Created by Sarah Pierson on 23/03/2017.
 */

@SuppressWarnings("serial")

public class AmiInconnuException extends Exception {

    /*
     * Constructeur AmiInconnuException
     * Constructeur qui fait appel au constructeur sans parametres de Exception
     */
    public AmiInconnuException(){
        super();
    }

	/*
	 * Constructeur AmiInconnuException
	 * Constructeur qui fait appel au constructeur qui prend un String en parametre
	 * @params s
	 */

    public AmiInconnuException(String s){
        super(s);
    }
}
