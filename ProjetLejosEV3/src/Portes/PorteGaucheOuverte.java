package Portes;

import lejos.hardware.motor.Motor;
import lejos.hardware.motor.NXTRegulatedMotor;

public class PorteGaucheOuverte implements EtatPortes{
	Portail portail;
	private NXTRegulatedMotor moteurG;
	private NXTRegulatedMotor moteurD;
	
	public PorteGaucheOuverte(Portail portail, NXTRegulatedMotor moteur1, NXTRegulatedMotor moteur2) {
		this.portail = portail;
		moteurD = moteur1;
		moteurG = moteur2;
	}

	@Override
	public void ouvrirPortes() {
		moteurG.setAcceleration(10);
		moteurG.forward();
		portail.setEtatPortes(portail.getPortesMouvementsOuverture());
		
	}

	@Override
	public void fermerPortes() {
		//porte gauche
		moteurD.setAcceleration(10);
		moteurD.forward();	
		portail.setEtatPortes(portail.getPortesMouvementsFermetures());
		
	}

	@Override
	public void ouvrirgauche() {
		moteurD.setAcceleration(10);
		moteurD.backward();
		portail.setEtatPortes(portail.getPortesMouvementsFermetures());
		
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
		return "gauchouv";
	}

}
