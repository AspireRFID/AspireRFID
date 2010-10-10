package org.ow2.aspirerfid.sensor.sunspot.service;

public interface SunSpotService {
	
	public enum LEDColor {RED, GREEN, BLUE};
	
	public void startAccelerometer();
	
	public void stopAccelerometer();
	
	public void startLuminosity();
	
	public void stopLuminosity();	
	
	public void startTemperature();
	
	public void stopTemperature();
	
	/**
	 * @return the battery level (in percent, between 0 and 100)
	 */
	public int getBatteryLevel();
	
	/**
	 * @return the spotAddress
	 */
	public String getAddress();
	
	public void blinkLEDs(int times);
	
	public void ledsOn();
	
	public void ledsOff();
	
	/**
	 * Lights a SPOT LED with the corresponding Color
	 * @param ledNumber
	 * @param color
	 */
	public void setLED(int ledNumber, LEDColor color);
	
	/**
	 * Lights a SPOT LED with the corresponding Color for a limited time
	 * @param ledNumber from 0 to 7
	 * @param color
	 * @param duration
	 */
	public void setLED(int ledNumber, LEDColor color, long duration);
	
	public void setLEDs(int[] ledNumbers, LEDColor color);
	public void setLEDs(int[] ledNumbers, LEDColor color, long duration);
	
}
