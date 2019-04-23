package Portes;

import lejos.hardware.motor.Motor;
import lejos.hardware.motor.NXTRegulatedMotor;

public class PortesMouvementOuverture implements EtatPortes {
	Portail portail;
	private NXTRegulatedMotor moteurG;
	private NXTRegulatedMotor moteurD;
	
	public PortesMouvementOuverture(Portail portail, NXTRegulatedMotor moteur1, NXTRegulatedMotor moteur2) {
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
		moteurD.stop();
		moteurD.stop();
		portail.setEtatPortes(portail.getPortesBloquees());
		
	}

	@Override
	public String getEtat() {
		// TODO Auto-generated method stub
		return "mouvouv";
	}

}
