/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.ow2.aspirerfid.sensor.sunspot.controller;

import com.sun.spot.io.j2me.radiogram.RadiogramConnection;
import com.sun.spot.sensorboard.EDemoBoard;
import com.sun.spot.sensorboard.peripheral.ITemperatureInput;
import com.sun.spot.util.Utils;
import java.io.IOException;
import javax.microedition.io.Connector;
import javax.microedition.io.Datagram;

/**
 *
 * @author lionel
 */
class TemperatureProducer implements Runnable {

    private long sampleDelay;
    
    private String targetAddress;
    
    private int communicationPort;
    
    private boolean end;
    
    private RadiogramConnection rCon;
    
    private ITemperatureInput tempSensor;
    
    
    TemperatureProducer(String spotAddress, int comPort, long sampleDelay) {
        targetAddress = spotAddress;
        communicationPort = comPort;
        this.sampleDelay = sampleDelay;
        tempSensor =  EDemoBoard.getInstance().getADCTemperature();
    }

    public void run() {
        end = false;
        long now = 0L;
        
        Datagram dg = null;
        
        try {
            // open a connection to communicate with the remote base station
            rCon = (RadiogramConnection) Connector.open("radiogram://"+targetAddress+":"+communicationPort);
            dg = rCon.newDatagram(PacketTypes.DEFAULT_DATAGRAM_SIZE);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        
        while (!end) {
            try {
                now = System.currentTimeMillis();
                // update the temperature
                dg.reset();
                dg.writeByte(PacketTypes.TEMPERATURE);
                double tempC = tempSensor.getCelsius();
                dg.writeDouble(tempC);
                rCon.send(dg);
                
                // sleep
                Utils.sleep(sampleDelay - (System.currentTimeMillis() - now));
                
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            
        }

    }
    
    public void stop(){
        try {
            end = true;
            rCon.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
    
}
