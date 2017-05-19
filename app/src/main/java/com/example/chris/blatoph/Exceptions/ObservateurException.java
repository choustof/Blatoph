package com.example.chris.blatoph.Exceptions;

/**
 * Created by Sarah Pierson on 23/03/2017.
 */

@SuppressWarnings("serial")

public class ObservateurException extends Exception {

	/*
	 * Constructeur ObservateurException
	 * Constructeur qui fait appel au constructeur sans parametres de Exception
	 */

    public ObservateurException(){
        super();
    }

	/*
	 * Constructeur ObservateurException
	 * Constructeur qui fait appel au constructeur qui prend un String en parametre
	 * @params s
	 */

    public ObservateurException(String s){
        super(s);
    }
}

