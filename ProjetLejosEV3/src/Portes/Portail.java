package Portes;

import lejos.hardware.Device;
import lejos.hardware.motor.Motor;
import lejos.hardware.motor.NXTRegulatedMotor;
// conecter les bon moteurs au bon port
// on suppose que le moteur gauche est connecté au port A
// on suppose que le moteur droit est connecté au port B
public class Portail {
 
	private NXTRegulatedMotor moteur1;
	private NXTRegulatedMotor moteur2;
	
	private EtatPortes etatPortes;
	private EtatPortes porteOuvertes;
	private EtatPortes portesFermees;
	private EtatPortes portesGaucheOuverte;
	private EtatPortes portesBloquees;
	private EtatPortes portesMouvementsOuverture;
	private EtatPortes portesGaucheMouvementOuvertes;
	private EtatPortes portesMouvementsFermetures;
	
	/**
	 * 
	 */
	public Portail(NXTRegulatedMotor moteurG, NXTRegulatedMotor moteurD) {
		
		moteur1 = moteurG;
		moteur2 = moteurD;
		porteOuvertes = new PortesOuvertes(this, moteur1, moteur2);                
		portesFermees = new PortesFermees(this, moteur1, moteur2);                
		portesGaucheOuverte = new PorteGaucheOuverte(this, moteur1, moteur2);          
		portesBloquees = new PortesBloquees(this, moteur1, moteur2);               
		portesMouvementsOuverture = new PortesMouvementOuverture(this, moteur1, moteur2);    
		portesGaucheMouvementOuvertes = new PorteGaucheMouvementOuverture(this, moteur1, moteur2);
		portesMouvementsFermetures = new PortesMouvementsFermeture(this, moteur1, moteur2);  
	
		etatPortes = portesFermees;
	}
	
	/**
	 * @param etatPortes
	 */
	public void setEtatPortes(EtatPortes etatPortes){
		this.etatPortes = etatPortes;
	}
	
	/**
	 * 
	 */
	public void ouvrirPortes() {
		etatPortes.ouvrirPortes();
	}
	
	/**
	 * 
	 */
	public void fermerPortes() {
		etatPortes.fermerPortes();
	}
	
	/**
	 * 
	 */
	public void ouvrirGauche() {
		etatPortes.ouvrirgauche();
	}
	
	/**
	 * 
	 */
	public void bloquer() {
		etatPortes.bloquer();
	}

	/**
	 * @return
	 */
	public EtatPortes getEtatPortes() {
		return etatPortes;
	}

	/**
	 * @return
	 */
	public EtatPortes getPorteOuvertes() {
		return porteOuvertes;
	}

	/**
	 * @return
	 */
	public EtatPortes getPortesFermees() {
		return portesFermees;
	}

	/**
	 * @return
	 */
	public EtatPortes getPortesGaucheOuverte() {
		return portesGaucheOuverte;
	}

	/**
	 * @return
	 */
	public EtatPortes getPortesBloquees() {
		return portesBloquees;
	}

	/**
	 * @return
	 */
	public EtatPortes getPortesMouvementsOuverture() {
		return portesMouvementsOuverture;
	}

	/**
	 * @return
	 */
	public EtatPortes getPortesGaucheMouvementOuvertes() {
		return portesGaucheMouvementOuvertes;
	}

	/**
	 * @return
	 */
	public EtatPortes getPortesMouvementsFermetures() {
		return portesMouvementsFermetures;
	}
	
}
