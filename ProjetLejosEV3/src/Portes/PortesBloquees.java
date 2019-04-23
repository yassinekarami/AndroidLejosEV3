package Portes;

import lejos.hardware.motor.Motor;
import lejos.hardware.motor.NXTRegulatedMotor;

public class PortesBloquees implements EtatPortes {
	private Portail portail;
	private NXTRegulatedMotor moteurG;
	private NXTRegulatedMotor moteurD;
	
	public PortesBloquees(Portail portail, NXTRegulatedMotor moteur1, NXTRegulatedMotor moteur2) {
		this.portail = portail;
		moteurD = moteur1;
		moteurG = moteur2;
	}

	@Override
	public void ouvrirPortes() {
		moteurD.setAcceleration(10);
		moteurD.backward();
				
		//porte droite
		moteurG.setAcceleration(10);
		moteurG.backward();
		portail.setEtatPortes(portail.getPortesMouvementsFermetures());
	}

	@Override
	public void fermerPortes() {
		moteurD.setAcceleration(10);
		moteurD.backward();
				
		//porte droite
		moteurG.setAcceleration(10);
		moteurG.backward();
		portail.setEtatPortes(portail.getPortesMouvementsFermetures());
	}

	@Override
	public void ouvrirgauche() {
		moteurD.setAcceleration(10);
		moteurD.backward();
				
		//porte droite
		moteurG.setAcceleration(10);
		moteurG.backward();
		portail.setEtatPortes(portail.getPortesMouvementsFermetures());
	}

	@Override
	public void bloquer() {
		moteurD.stop();
		moteurG.stop();
		portail.setEtatPortes(portail.getPortesBloquees());
	}

	@Override
	public String getEtat() {
		return "bloque";
	}

}
