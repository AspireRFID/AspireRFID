/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.ow2.aspirerfid.sensor.sunspot.controller;

import com.sun.spot.io.j2me.radiogram.Radiogram;
import com.sun.spot.io.j2me.radiogram.RadiogramConnection;
import com.sun.spot.peripheral.IBattery;
import com.sun.spot.peripheral.Spot;
import com.sun.spot.sensorboard.EDemoBoard;
import com.sun.spot.sensorboard.peripheral.ITriColorLED;
import com.sun.spot.sensorboard.peripheral.LEDColor;
import com.sun.spot.util.Utils;
import java.io.IOException;
import javax.microedition.io.Connector;
import javax.microedition.io.Datagram;



/**
 *
 * @author lionel
 */
public class SPOTControlHandler implements Runnable {

    private String targetAddress;
    
    private int communicationPort;
    private RadiogramConnection rCon;
    
    private boolean end;
    
    private ITriColorLED leds[] = EDemoBoard.getInstance ().getLEDs();
    
    private IBattery battery;
    
    private SPOTLEDService ledService;
    
    public SPOTControlHandler(String address, int port) {
        targetAddress = address;
        communicationPort = port;
        try {
            // open a connection to communicate with the remote base station
            rCon = (RadiogramConnection) Connector.open("radiogram://"+targetAddress+":"+communicationPort);
            // get Battery
            battery = Spot.getInstance().getPowerController().getBattery();
            
            ledService = new SPOTLEDServiceImpl();
            
        } catch (IOException ex) {
            ex.printStackTrace();
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
    
    public void run() {
        end = false;
        Datagram dg = null;
        try {
            dg = rCon.newDatagram(PacketTypes.DEFAULT_DATAGRAM_SIZE);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        while (!end) {
            try {
                dg.reset();
                // listen for incomming commands
                rCon.receive(dg);
                byte cmd = dg.readByte();
                LEDColor standardColor = LEDColor.WHITE;
                int ledNumber;
                String ledColor;
                
                switch (cmd) {
                    case PacketTypes.BLINK_LEDS_REQ :
                        int times = dg.readInt();
                        blinkLEDs(times);
                        break;
                    case PacketTypes.LEDS_OFF:
                        ledService.turnLEDsoff();
                        break;
                    case PacketTypes.SET_LED_DURATION_REQ :
                        ledNumber = dg.readInt();
                        ledColor = dg.readUTF();
                        long duration = dg.readLong();
                        
                        if (ledColor.equals("GREEN")) {
                            standardColor = LEDColor.GREEN;
                        } else if (ledColor.equals("RED")) {
                            standardColor = LEDColor.RED;
                        } else if (ledColor.equals("BLUE")) {
                            standardColor = LEDColor.BLUE;
                        }
                        ledService.setLED(ledNumber, standardColor, duration);
                        break;
                    case PacketTypes.SET_LEDS_DURATION_REQ :
                        int arraySize = dg.readInt();
                        int[] ledList = new int[arraySize]; 
                        for (int i=0; i < arraySize; i++){
                            ledList[i] = dg.readInt();
                        }
                        ledColor = dg.readUTF();
                        duration = dg.readLong();
                        
                        if (ledColor.equals("GREEN")) {
                            standardColor = LEDColor.GREEN;
                        } else if (ledColor.equals("RED")) {
                            standardColor = LEDColor.RED;
                        } else if (ledColor.equals("BLUE")) {
                            standardColor = LEDColor.BLUE;
                        }
                        ledService.setLEDs(ledList, standardColor, duration);
                        break;
                    case PacketTypes.GET_BATTERY_REQ :
                        Datagram reply = rCon.newDatagram(PacketTypes.DEFAULT_DATAGRAM_SIZE);
                        reply.writeByte(PacketTypes.GET_BATTERY_REQ);
                        reply.writeInt(battery.getBatteryLevel());
                        rCon.send(reply);
                        break;
                }
                
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            
        }

    }
    
    // handle Blink LEDs command
    private void blinkLEDs(int times) {
        for (int i = 0; i < times; i++) {          // blink LEDs N times for N seconds
            for (int j = 0; j < 8; j++) {
                leds[j].setColor(LEDColor.MAGENTA);
                leds[j].setOn();
            }
            Utils.sleep(250);
            for (int j = 0; j < 8; j++) {
                leds[j].setOff();
            }
            Utils.sleep(750);
        }
    }
    
}
