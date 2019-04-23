package Telecommande;

import Controlleur.Controlleur;

public class Telecommande {
	private Controlleur controlleur;
	
	public Telecommande(Controlleur controlleur) {
		this.controlleur = controlleur;
	}
	
	public void ouvrirT() {
		controlleur.ouvertureT();
	}
	
	public void ouvrirP() {
		controlleur.ouvertureP();
	}
}
