/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.ow2.aspirerfid.sensor.sunspot.controller;

import com.sun.spot.sensorboard.peripheral.LEDColor;

/**
 *
 * @author lionel
 */
public interface SPOTLEDService {
    
    public void setLED(int ledNumber, LEDColor color, long duration);
    
    public void setLEDs(int[] ledsNumber, LEDColor color, long duration);
    
    public void turnLEDoff(int ledNumber);
    
    public void turnLEDsoff();
    
    public void getBatteryLevel();
    
}
