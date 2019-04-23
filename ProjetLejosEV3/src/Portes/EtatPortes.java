package Portes;
import lejos.hardware.*;
public interface EtatPortes {

	public void ouvrirPortes();
	public void fermerPortes();
	public void ouvrirgauche();
	public void bloquer();
	public String getEtat();
}
