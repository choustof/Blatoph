package com.example.chris.blatoph;

/*
 * L'interface Edition est utilis�e par les classes qui sont concern�es par les actions
 * de renommage et de suppression
 * 
 * @author Benjamin BATTAIS
 */
public interface Edition {

	public boolean renommer(String n);
	public boolean supprimer();
}
