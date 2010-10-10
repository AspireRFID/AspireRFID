/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.ow2.aspirerfid.sensor.sunspot.controller;

import com.sun.spot.sensorboard.EDemoBoard;
import com.sun.spot.sensorboard.peripheral.ITriColorLED;
import com.sun.spot.sensorboard.peripheral.LEDColor;
import com.sun.spot.util.Utils;

/**
 *
 * @author lionel
 */
public class SPOTLEDServiceImpl implements SPOTLEDService {

    private ITriColorLED leds[] = EDemoBoard.getInstance ().getLEDs();
    
    public SPOTLEDServiceImpl() {
        
    }
    
    public void setLED(int ledNumber, LEDColor color, long duration) {
        if (ledNumber < leds.length && ledNumber >= 0) {
                leds[ledNumber].setColor(color);
                leds[ledNumber].setOn();
                if (duration > 0) {
                    Utils.sleep(duration);
                    leds[ledNumber].setOff();
                }
        }
        
    }

    public void setLEDs(int[] ledsNumber, LEDColor color, long duration) {
        for (int i = 0; i < ledsNumber.length; i++) {
            if (ledsNumber[i] < leds.length && ledsNumber[i] >= 0) {
                leds[ledsNumber[i]].setColor(color);
                leds[ledsNumber[i]].setOn();
            }
        }
        if (duration > 0) {
            Utils.sleep(duration);
            for (int i = 0; i < ledsNumber.length; i++) {
                if (ledsNumber[i] < leds.length && ledsNumber[i] >= 0) {
                    leds[ledsNumber[i]].setOff();
                }
            }
        }
        
    }

    public void turnLEDoff(int ledNumber) {
        if (ledNumber < leds.length && ledNumber >= 0 && leds[ledNumber].isOn()) {
            leds[ledNumber].setOff();
        }
    }

    public void turnLEDsoff() {
        for (int j = 0; j < 8; j++) {
            leds[j].setOff();
        }
    }

    public void getBatteryLevel() {
    }
    
}
