package Default;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import Controlleur.CapteurPresence;
import Controlleur.Controlleur;
import Controlleur.Lumiere;
import Portes.Portail;
import lejos.hardware.Bluetooth;
import lejos.hardware.Brick;
import lejos.hardware.BrickFinder;
import lejos.hardware.Button;
import lejos.hardware.ev3.LocalEV3;
import lejos.hardware.motor.Motor;
import lejos.hardware.motor.NXTRegulatedMotor;
import lejos.hardware.port.AnalogPort;
import lejos.hardware.port.MotorPort;
import lejos.hardware.port.Port;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3TouchSensor;
import lejos.hardware.sensor.NXTColorSensor;
import lejos.hardware.sensor.NXTLightSensor;
import lejos.hardware.sensor.NXTTouchSensor;
import lejos.remote.nxt.BTConnection;
import lejos.remote.nxt.BTConnector;
import lejos.remote.nxt.NXTConnection;
import lejos.robotics.EncoderMotor;


public class main {
	
	private static DataOutputStream dataOut; 
	private static DataInputStream dataIn;
	private static BTConnection BTLink;
	private static byte transmitReceived=0;
	private static int power = 50;
	private static boolean app_alive;
	
	private static EncoderMotor motorLeft;
	private static EncoderMotor motorRight;
	
	private static boolean is_running = false;

	public static void main(String[] args) throws Exception {
			
		
		connect();

	
		Brick brick = BrickFinder.getDefault();
		Port port1 = brick.getPort("S1");
		Port port2 = brick.getPort("S2");
		EV3TouchSensor touchSens1 =  new  EV3TouchSensor(port1) ;
		EV3TouchSensor touchSens2 = new EV3TouchSensor(port2);
		NXTRegulatedMotor moteurG = Motor.B;
		NXTRegulatedMotor moteurD = Motor.A;
	
		
	    Portail portail1 = new Portail(moteurG, moteurD);
	    Lumiere lumiere = new Lumiere();
	    
	 
	    
	    CapteurPresence capteurOuverture = new CapteurPresence(touchSens1); // port S1
	    CapteurPresence capteurFermeture = new CapteurPresence(touchSens2); // port S2
	    
	    Controlleur control = new Controlleur(portail1, lumiere);
//	    
	    while(true){
			  try {
			       transmitReceived = dataIn.readByte();
			      
			       switch(transmitReceived){
			       
			       case 3: // ouverture total
			    	   
			    	  is_running = true;
			    	  while (is_running) 
			    	  {		    		
			    		// on detecte que la porte est fermer  
			    		// on ouvre la porte
			    		  if (capteurFermeture.alerteCollision()  &&  (control.getPortes().getEtatPortes().getEtat().equals("fermer")||
			    				  									   control.getPortes().getEtatPortes().getEtat().equals("bloque")||
			    				  									   control.getPortes().getEtatPortes().getEtat().equals("mouvferm"))) 
			    		  {
			    					is_running = false;
			    			  		System.out.println("ouverture");
				    			  	control.ouvertureP();		 
				    	  }
			    		   
			    		  // on detecte que la porte est ouverte
			    		  // on ferme la porte
			    		  else if (capteurOuverture.alerteCollision() &&  (control.getPortes().getEtatPortes().getEtat().equals("ouvert")||
			    				  										   control.getPortes().getEtatPortes().getEtat().equals("bloque")||
			    				  										   control.getPortes().getEtatPortes().getEtat().equals("mouvouv"))) 
			    		  {
			    			  is_running = false;
			    			  System.out.println("fermeture");
			    			  control.fermeture();
			    			  	
			    		  }	
			    		  
			    		  // si le portail n'est ni en fermeture ou en ouverture
			    		  // et qu'il est en mouvement
			    		  // on bloque le portail
			    		  else if (!capteurOuverture.alerteCollision() && control.getPortes().getEtatPortes().getEtat().equals("mouvferm")
			    				  || (!capteurFermeture.alerteCollision() && control.getPortes().getEtatPortes().getEtat().equals("mouvouv"))) 
			    		  
			    		  {
			    			  is_running = false;
			    			  control.bloquer();
			    		  }
			    	  }
			    	  
			    	 
			    	 
			    	   break;
			    	   
			      // ouverture partiel
			       case 2:  
			    	   is_running = true;
			    	   while (is_running)
			    	   {
			    		   if( control.getPortes().getEtatPortes().getEtat().equals("ferme")) 
			    		   {
			    			   System.out.println("ouverture partiel");
			    			   control.ouvertureT();
			    			   is_running = false;
			    			 		   
			    		   }
			    		   
			    		   else if(capteurOuverture.alerteCollision()  && (control.getPortes().getEtatPortes().getEtat().equals("ouvert")||
			    				   											control.getPortes().getEtatPortes().getEtat().equals("bloque")||
			    				   											control.getPortes().getEtatPortes().getEtat().equals("mouvouv"))){
			    			   System.out.println("fermer");
			    			   control.fermeture();
			    			   is_running = false;
			    		   }
			    		   
			    		      // si le portail n'est ni en fermeture ou en ouverture
				    		  // et qu'il est en mouvement
				    		  // on bloque le portail
				    		  else if (!capteurOuverture.alerteCollision() && control.getPortes().getEtatPortes().getEtat().equals("mouvferm")
				    				  || (!capteurFermeture.alerteCollision() && control.getPortes().getEtatPortes().getEtat().equals("mouvouv"))) 
				    		  
				    		  {
				    			  is_running = false;
				    			  control.bloquer();
				    		  }

			    	   }
		    		   
			    	   break;
			    	   
			       // quitte l'application
			       case 4 : 
			    	   return;
			    
		    	   }
			   }
			    
			       catch (IOException ioe) {
			       System.out.println("IO Exception readInt");
			   }
		}
	    

}
	
	// methode pour connecté le robot 
	public static void connect()
	 {  
	    System.out.println("Listening");
	    BTConnector ncc = (BTConnector) Bluetooth.getNXTCommConnector();
	    BTLink = (BTConnection) ncc.waitForConnection(30000, NXTConnection.RAW);
	    dataOut = BTLink.openDataOutputStream();
	    dataIn = BTLink.openDataInputStream();
	 }
}

	