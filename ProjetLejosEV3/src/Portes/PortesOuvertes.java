package Portes;

import lejos.hardware.motor.Motor;
import lejos.hardware.motor.NXTRegulatedMotor;

public class PortesOuvertes implements EtatPortes{

	Portail portail;
	private NXTRegulatedMotor moteurG;
	private NXTRegulatedMotor moteurD;
	
	public PortesOuvertes(Portail newPortail, NXTRegulatedMotor moteur1, NXTRegulatedMotor moteur2) {
		portail = newPortail;
		moteurD = moteur1;
		moteurG = moteur2;
	}
	

	@Override
	public void ouvrirPortes() {
		//porte gauche
		moteurD.setAcceleration(10);
		moteurD.backward();
				
		//porte droite
		moteurG.setAcceleration(10);
		moteurG.backward();
		portail.setEtatPortes(portail.getPortesMouvementsFermetures());
		
	}

	@Override
	public void fermerPortes() {
		//porte gauche
		moteurD.setAcceleration(10);
		moteurD.backward();
				
		//porte droite
		moteurG.setAcceleration(10);
		moteurG.backward();
		portail.setEtatPortes(portail.getPortesMouvementsFermetures());
	}

	@Override
	public void ouvrirgauche() {		
		//porte gauche
		moteurD.setAcceleration(10);
		moteurD.backward();
				
		//porte droite
		moteurG.setAcceleration(10);
		moteurG.backward();
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
		return "ouvert";
	}

}
