package Portes;
//import lejos.hardware.moteurGaseRegulatedMotor;
import lejos.hardware.motor.Motor;
import lejos.hardware.motor.NXTRegulatedMotor;


public class PorteGaucheMouvementOuverture implements EtatPortes {
	Portail portail;
	private NXTRegulatedMotor moteurG;
	private NXTRegulatedMotor moteurD;
	public PorteGaucheMouvementOuverture(Portail portail, NXTRegulatedMotor moteur1, NXTRegulatedMotor moteur2) {
		this.portail = portail;
		moteurD = moteur1;
		moteurG = moteur2;
	}

	@Override
	public void ouvrirPortes() {
		moteurD.stop();
		moteurG.stop();
		
		portail.setEtatPortes(portail.getPortesBloquees());
		
	}

	@Override
	public void fermerPortes() {
		moteurD.stop();
		moteurG.stop();
		portail.setEtatPortes(portail.getPortesBloquees());
		
	}

	@Override
	public void ouvrirgauche() {
		
		moteurD.stop();
		moteurG.stop();
		portail.setEtatPortes(portail.getPortesBloquees());
	}

	@Override
	public void bloquer() {
		moteurD.stop();
		moteurG.stop();
		portail.setEtatPortes(portail.getPortesBloquees());
		
	}

	@Override
	public String getEtat() {
		return "mouvouvgau";
	}

}
