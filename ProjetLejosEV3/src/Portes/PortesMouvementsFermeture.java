package Portes;

import lejos.hardware.motor.Motor;
import lejos.hardware.motor.NXTRegulatedMotor;

public class PortesMouvementsFermeture implements EtatPortes {
	private Portail portail;
	private NXTRegulatedMotor moteurG;
	private NXTRegulatedMotor moteurD;
	
	public PortesMouvementsFermeture(Portail portail, NXTRegulatedMotor moteur1, NXTRegulatedMotor moteur2) {
		this.portail = portail;
		moteurD = moteur1;
		moteurG = moteur2;
	}

	@Override
	public void ouvrirPortes() {
		moteurG.stop();
		moteurD.stop();
		portail.setEtatPortes(portail.getPortesBloquees());
		
	}

	@Override
	public void fermerPortes() {
		moteurG.stop();
		moteurD.stop();
		portail.setEtatPortes(portail.getPortesBloquees());
		
	}

	@Override
	public void ouvrirgauche() {
		moteurG.stop();
		moteurD.stop();
		portail.setEtatPortes(portail.getPortesBloquees());
		
	}

	@Override
	public void bloquer() {
		moteurG.stop();
		moteurD.stop();
		portail.setEtatPortes(portail.getPortesBloquees());
		
	}

	@Override
	public String getEtat() {
		// TODO Auto-generated method stub
		return "mouvferm";
	}

}
