/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.ow2.aspirerfid.sensor.sunspot.controller;

import com.sun.spot.io.j2me.radiogram.RadiogramConnection;
import com.sun.spot.sensorboard.EDemoBoard;
import com.sun.spot.sensorboard.peripheral.IAccelerometer3D;
import com.sun.spot.util.Utils;
import java.io.IOException;
import javax.microedition.io.Connector;
import javax.microedition.io.Datagram;

/**
 *
 * @author lionel
 */
class AccelerationProducer implements Runnable {

    private long sampleDelay;
    
    private String targetAddress;
    
    private int communicationPort;
    
    private boolean end;
    
    private RadiogramConnection rCon;
    
    private IAccelerometer3D accelerometer;
    
    
    AccelerationProducer(String spotAddress, int comPort, long sampleDelay) {
        targetAddress = spotAddress;
        communicationPort = comPort;
        this.sampleDelay = sampleDelay;
        accelerometer =  EDemoBoard.getInstance().getAccelerometer();
    }

    public void run() {
        end = false;
        long now = 0L;
        
        Datagram dg = null;
        
        try {
            // open a connection to communicate with the remote base station
            rCon = (RadiogramConnection) Connector.open("radiogram://"+targetAddress+":"+communicationPort);
            dg = rCon.newDatagram(rCon.getMaximumLength());
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        
        while (!end) {
            try {
                now = System.currentTimeMillis();
                // update the acceleration
                dg.reset();
                dg.writeByte(PacketTypes.ACCELERATION);
                double x = accelerometer.getAccelX();
                double y = accelerometer.getAccelY();
                double z = accelerometer.getAccelZ();
                dg.writeDouble(x);
                dg.writeDouble(y);
                dg.writeDouble(z);
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
