package Controlleur;

import lejos.hardware.sensor.BaseSensor;
import lejos.hardware.sensor.NXTTouchSensor;
import lejos.hardware.sensor.SensorMode;

public class CapteurPresence {

	private BaseSensor sensor;
	
	public CapteurPresence(BaseSensor sensor) {
		//controlleur = null;
		this.sensor = sensor;
			
	}
	
	//get a sample provider that returns an indication of the button being up(0) or down(1)
	public boolean alerteCollision() {

		boolean res = false;
		int size = this.sensor.sampleSize();
		float[] sample = new float[size];
		sensor.fetchSample(sample,0);
		if( sample[0] == 1.0) {
			res = true;
			System.out.println("stop");
		}
		return res;
	}

//	public void setControlleur(Controlleur controlleur) {
//		this.controlleur = controlleur;
//		
//	}

	
	
		
		
			
	
	
}
