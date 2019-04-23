package Portes;

import lejos.hardware.motor.Motor;
import lejos.hardware.motor.NXTRegulatedMotor;

public class PortesFermees implements EtatPortes {
	Portail portail;
	private NXTRegulatedMotor moteurG;
	private NXTRegulatedMotor moteurD;
	
	public PortesFermees(Portail portail, NXTRegulatedMotor moteur1, NXTRegulatedMotor moteur2) {
		this.portail=portail;
		moteurD = moteur1;
		moteurG = moteur2;
	}

	@Override
	public void ouvrirPortes() {
		//porte gauche
		moteurD.setAcceleration(10);
		moteurD.forward();
				
		//porte droite
		moteurG.setAcceleration(10);
		moteurG.forward();
		portail.setEtatPortes(portail.getPortesMouvementsOuverture());
		
	}

	@Override
	public void fermerPortes() {
		moteurG.stop();
		moteurD.stop();
		portail.setEtatPortes(portail.getPortesFermees());
	}

	@Override
	public void ouvrirgauche() {
		moteurD.setAcceleration(10);
		moteurD.forward();
		portail.setEtatPortes(portail.getPortesGaucheMouvementOuvertes());
	}

	@Override
	public void bloquer() {
		moteurG.stop();
		moteurD.stop();
		portail.setEtatPortes(portail.getPortesBloquees());
	}

	@Override
	public String getEtat() {
		return "ferme";
	}

}
