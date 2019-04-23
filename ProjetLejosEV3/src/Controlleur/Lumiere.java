package Controlleur;

public class Lumiere {
	boolean etatLumiere;
	
	/**
	 * 
	 */
	public Lumiere() {
		etatLumiere = false;
	}

	/**
	 * @return
	 */
	public boolean getEtatLumiere() {

		return etatLumiere;
	}

	/**
	 * 
	 */
	public void eteindre() {
		etatLumiere = false;
		
	}

	/**
	 * 
	 */
	public void allumer() {
		etatLumiere = true;
	}

}
