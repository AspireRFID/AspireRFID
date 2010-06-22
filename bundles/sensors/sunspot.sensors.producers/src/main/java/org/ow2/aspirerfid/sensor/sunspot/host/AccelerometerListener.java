/*
 * Copyright 2005-2008, Aspire
 * 
 * This library is free software; you can redistribute it and/or modify it 
 * under the terms of the GNU Lesser General Public License as published by 
 * the Free Software Foundation (the "LGPL"); either version 2.1 of the 
 * License, or (at your option) any later version. If you do not alter this 
 * notice, a recipient may use your version of this file under either the 
 * LGPL version 2.1, or (at his option) any later version.
 * 
 * You should have received a copy of the GNU Lesser General Public License 
 * along with this library; if not, write to the Free Software Foundation, 
 * Inc., 51 Franklin Street, Fifth Floor, Boston, MA 02110-1301 USA.
 * 
 * This software is distributed on an "AS IS" basis, WITHOUT WARRANTY OF ANY 
 * KIND, either express or implied. See the GNU Lesser General Public 
 * License for the specific language governing rights and limitations.
 */

package org.ow2.aspirerfid.sensor.sunspot.host;

import com.sun.spot.io.j2me.radiogram.*;
import com.sun.spot.peripheral.NoAckException;
import com.sun.spot.peripheral.TimeoutException;
import com.sun.spot.util.IEEEAddress;

import java.io.*;
import javax.microedition.io.*;


/**
 * Simple example class to locate a remote service (on a SPOT), to connect to it
 * and send it a variety of commands. In this case to set or calibrate the SPOT's
 * accelerometer and to return a stream of accelerometer telemetry information. 
 *
 * @author Ron Goldman<br>
 * Date: May 2, 2006 
 */
public class AccelerometerListener extends Thread implements PacketTypes {
        
    private double zeroOffsets[][] = { { 465, 465, 465 }, { 465, 465, 465 } };
    private double gains[][]       = { { 186.2, 186.2, 186.2 }, { 62.07, 62.07, 62.07 } };
    private double restOffsets[][] = { { 465, 465, 465 + 186 }, { 465, 465, 465 + 62 } };  // w/SPOT sitting flat Z up
    
    private boolean baseStationPresent = false;
    private RadiogramConnection conn = null;
    private Radiogram xdg = null;
    private boolean running = true;
    private boolean connected = false;
    private long spotAddress = 0;
    private String stringedSpotAddress;
    private long timeStampOffset = -1;
    private int index = 0;
    private int scaleInUse = 2;

    /**
     * The server where to store the latest acceleration measurements
     */
    private HostServer server;
    
    /**
     * Create a new AccelerometerListener to connect to the remote SPOT over the radio.
     * @param server 
     */
    public AccelerometerListener () {
    	server = HostServer.getSingleton();
        init();
    }

    /**
     * Connect to base station & other initialization.
     */
    private void init () {
        RadiogramConnection rcvConn = null;
        try {
            rcvConn = (RadiogramConnection)Connector.open("radiogram://:" + BROADCAST_PORT);
            baseStationPresent = true;
        } catch (Exception ex) {
            baseStationPresent = false;
            System.out.println("Problem connecting to base station: " + ex);
        } finally {
            try {
                if (rcvConn != null) {
                    rcvConn.close();
                }
            } catch (IOException ex) { /* ignore */ }
        }
    }
    
    /**
     * Report which scale is the accelerometer is using.
     *
     * @return true if using 2G scale, false for 6G scale
     */
    public boolean is2GScale () {
        return scaleInUse == 2;
    }


    /**
     * Send a request to the remote SPOT to report on which accelerometer scale it is using.
     */
    public void doGetScale () {
        sendCmd(GET_ACCEL_INFO_REQ);
    }

    
    /**
     * Send a request to the remote SPOT to set which accelerometer scale it will use.
     *
     * @param val the scale to use: 2 or 6
     */
    public void doSetScale (int val) {
        if (conn != null) {
            try {
                xdg.reset();
                xdg.writeByte(SET_ACCEL_SCALE_REQ);
                xdg.writeByte(val);
                conn.send(xdg);
            } catch (NoAckException nex) {
                connected = false;
            } catch (IOException ex) {
                // ignore any other problems
            }
        }
    }                                               

    /**
     * Send a request to the remote SPOT to calibrate the accelerometer.
     */
    public void doCalibrate () {
        doGetScale();
        sendCmd(CALIBRATE_ACCEL_REQ);
    }

    /**
     * Send a request to the remote SPOT to start or stop sending accelerometer readings.
     *
     * @param sendIt true to start sending, false to stop
     * @param gView the GraphView display to pass the data to
     */
    public void doSendData (boolean sendIt) {
        sendCmd(sendIt ? SEND_ACCEL_DATA_REQ : STOP_ACCEL_DATA_REQ);
    }

    /**
     * Send a request to the remote SPOT to report on radio signal strength.
     */
    public void doPing() {
        sendCmd(PING_REQ);
    }

    /**
     * Send a request to the remote SPOT to blink its LEDs.
     */
    public void doBlink() {
        sendCmd(BLINK_LEDS_REQ);
    }

    /**
     * Stop running. Also notify the remote SPOT that we are no longer listening to it.
     */
    public void doQuit () {
        sendCmd(DISPLAY_SERVER_QUITTING);
        running = false;
        // TODO ADDED
        connected = false;
    }

    /**
     * Send a request to the remote SPOT to report on radio signal strength.
     */
    public void reconnect() {
        connected = false;
        announceStarting();
    }

    /**
     * Send a simple command request to the remote SPOT.
     *
     * @param cmd the command requested
     */
    private void sendCmd (byte cmd) {
        if (conn != null) {
            try {
                xdg.reset();
                xdg.writeByte(cmd);
                conn.send(xdg);
            } catch (NoAckException nex) {
                connected = false;
            } catch (IOException ex) {
                // ignore any other problems
            }
        }
    }

    /**
     * Routine to reset after old data has been cleared from the GUI display.
     */
    public void clear () {
        index = 0;
        timeStampOffset = -1;
    }

    /**
     * Main runtime loop to connect to a remote SPOT.
     * Do not call directly. Call start() instead.
     */
    public void run () {
        if (baseStationPresent) {
            System.out.println("Accelerometer Reader Thread Started ...");
            hostLoop();
        }
    }

    /**
     * Process telemetry data sent by remote SPOT.
     *
     * @param dg the packet containing the accelerometer data
     * @param twoG the scale that was used to collect the data (true = 2G, false = 6G)
     */
    private void receive (Datagram dg, boolean twoG) {
        boolean skipZeros = (index == 0);
        int scale = twoG ? 0 : 1;
        try {
            String address = dg.getAddress();
            long rcvdTimeStamp = dg.readLong();
            long timeStamp = rcvdTimeStamp;
            if (timeStampOffset <= 0) {
                timeStampOffset = timeStamp;
                timeStamp = 0;
            } else {
                timeStamp -= timeStampOffset;
            }
            int sampleSize = dg.readByte();         // Number of SensorData contained in the datagram
            for (int i = 0; i < sampleSize; i++) {
                int deltaT = dg.readShort();
                long sampleTime = timeStamp + (deltaT & 0x0ffffL);
                int xValue = dg.readShort();
                int yValue = dg.readShort();
                int zValue = dg.readShort();

                if (skipZeros &&                    // Ignore leading values until they become non-zero
                    ((Math.abs(xValue - (int)restOffsets[scale][0]) > 20) || 
                     (Math.abs(yValue - (int)restOffsets[scale][1]) > 20) || 
                     (Math.abs(zValue - (int)restOffsets[scale][2]) > 20))) {
                    skipZeros = false;
                    timeStampOffset += sampleTime;
                    timeStamp = -deltaT;
                    sampleTime = 0;
                }

                if (!skipZeros) {
                    double x  = (xValue - zeroOffsets[scale][0]) / gains[scale][0];        // Convert to G's
                    double y  = (yValue - zeroOffsets[scale][1]) / gains[scale][1];
                    double z  = (zValue - zeroOffsets[scale][2]) / gains[scale][2];

                    double g = Math.sqrt(x*x + y*y + z*z);      // Square vector of the total Gs

                    server.updateAcceleration(new double[]{x,y,z}, address, rcvdTimeStamp);
                    
                    index++;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Broadcast that the host display server is (re)starting.
     */
    private void announceStarting () {
        DatagramConnection txConn = null;
        try {
            txConn = (DatagramConnection)Connector.open("radiogram://broadcast:" + CONNECTED_PORT);
            Datagram dg = txConn.newDatagram(txConn.getMaximumLength());
            dg.writeByte(DISPLAY_SERVER_RESTART);        // packet type
            txConn.send(dg);                             // broadcast it
        } catch (Exception ex) {
            System.out.println("Error sending display server startup message: " + ex.toString());
            ex.printStackTrace();
        } finally {
            try {
                if (txConn != null) { 
                    txConn.close();
                }
            } catch (IOException ex) { /* ignore */ }
        }
    }
    
    /**
     * Wait for a remote SPOT to request a connection.
     */
    private void waitForSpot () {
        RadiogramConnection rcvConn = null;
        DatagramConnection txConn = null;
        spotAddress = 0;
        try {
            rcvConn = (RadiogramConnection)Connector.open("radiogram://:" + BROADCAST_PORT);
            rcvConn.setTimeout(10000);             // timeout in 10 seconds
            Datagram dg = rcvConn.newDatagram(rcvConn.getMaximumLength());
            while (true) {
                try {
                    dg.reset();
                    rcvConn.receive(dg);            // wait until we receive a request
                    if (dg.readByte() == LOCATE_DISPLAY_SERVER_REQ) {       // type of packet
                        String addr = dg.getAddress();
                        IEEEAddress ieeeAddr = new IEEEAddress(addr);
                        long macAddress = ieeeAddr.asLong();
                        System.out.println("Received request from: " + ieeeAddr.asDottedHex());
                        Datagram rdg = rcvConn.newDatagram(10);
                        rdg.reset();
                        rdg.setAddress(dg);
                        rdg.writeByte(DISPLAY_SERVER_AVAIL_REPLY);        // packet type
                        rdg.writeLong(macAddress);                        // requestor's ID
                        rcvConn.send(rdg);                                // broadcast it
                        spotAddress = macAddress;
                        stringedSpotAddress = addr;
                        
                        // TODO added Start a wireadmin acceleration producer
                        server.startAccelerationProducer(stringedSpotAddress);
                        
                        break;
                    }
                } catch (TimeoutException ex) {
                    announceStarting();
                }
            }
        } catch (Exception ex) {
            System.out.println("Error waiting for remote Spot: " + ex.toString());
            ex.printStackTrace();
        } finally {
            try {
                if (rcvConn != null) { 
                    rcvConn.close();
                }
                if (txConn != null) { 
                    txConn.close();
                }
            } catch (IOException ex) { /* ignore */ }
        }
    }

    /**
     * Main receive loop. Receive a packet sent by remote SPOT and handle it.
     */
    private void hostLoop() {
        running = true;
        announceStarting();  // announce we are starting up - in case a Spot thinks it's connected to us
        while (running) {
            waitForSpot();   // connect to a Spot with accelerometer telemetry to display
            if (spotAddress != 0) {
                try {
                    conn = (RadiogramConnection)Connector.open("radiogram://" + spotAddress + ":" + CONNECTED_PORT);
                    conn.setTimeout(1000);             // timeout every second
                    Radiogram rdg = (Radiogram)conn.newDatagram(conn.getMaximumLength());
                    xdg = (Radiogram)conn.newDatagram(10); // we never send more than 1 or 2 bytes
                    connected = true;
                    index = 0;
                    timeStampOffset = -1;
                    while (connected) {
                        try {
                            conn.receive(rdg);            // wait until we receive a reply
                        } catch (TimeoutException ex) {
                            continue;
                        }
                        byte packetType = rdg.readByte();
                        switch (packetType) {
                            case GET_ACCEL_INFO_REPLY:
                                scaleInUse = rdg.readByte();
                                System.out.println("Accelerometer scale is set to " + scaleInUse + "G");
                                System.out.println("Accelerometer zero offsets:");
                                for (int i = 0; i < 2; i++) {
                                    System.out.print((i == 0 ? "  2G: " : "  6G: "));
                                    for (int j = 0; j < 3; j++) {
                                        zeroOffsets[i][j] = (int)rdg.readDouble();
                                        System.out.print(zeroOffsets[i][j] + (j < 2 ? ", " : ""));
                                    }
                                    System.out.println();
                                }
                                break;
                            case GET_ACCEL_INFO2_REPLY:
                                System.out.println("Accelerometer gains:");
                                for (int i = 0; i < 2; i++) {
                                    System.out.print((i == 0 ? "  2G: " : "  6G: "));
                                    for (int j = 0; j < 3; j++) {
                                        gains[i][j] = (int)rdg.readDouble();
                                        System.out.print(gains[i][j] + (j < 2 ? ", " : ""));
                                    }
                                    System.out.println();
                                }
                                break;
                            case SET_ACCEL_SCALE_REPLY:
                                int newScale = rdg.readByte();
                                if (newScale > 0) {
                                    scaleInUse = newScale;
                                    System.out.println("Accelerometer scale now set to " + newScale + "G");
                                } else {
                                    System.out.println("Invalid Accelerometer scale requested!");
                                }
                                break;
                            case CALIBRATE_ACCEL_REPLY:
                                System.out.println("Accelerometer rest offsets:");
                                for (int i = 0; i < 2; i++) {
                                    System.out.print((i == 0 ? "  2G: " : "  6G: "));
                                    for (int j = 0; j < 3; j++) {
                                        restOffsets[i][j] = (int)rdg.readDouble();
                                        System.out.print(restOffsets[i][j] + (j < 2 ? ", " : ""));
                                    }
                                    System.out.println();
                                    
                                    //TODO ADDED to start sensor values reading
                                    doSendData(true);                                 
                                }
                                break;
                            case ACCEL_2G_DATA_REPLY:
                            case ACCEL_6G_DATA_REPLY:
                                receive(rdg, packetType == ACCEL_2G_DATA_REPLY);
                                break;
                            case PING_REPLY:
                                System.out.println("Ping reply:  (linkQuality : corr : rssi)");
                                System.out.println("   host->spot: " + rdg.readInt() + " : " + rdg.readInt() + " : " + rdg.readInt());
                                System.out.println("   spot->host: " + rdg.getLinkQuality() + " : " + rdg.getCorr() + " : " + rdg.getRssi());
                                System.out.println("   spot battery voltage: " + rdg.readInt() + " mv");
                                break;
                            case MESSAGE_REPLY:
                                String str = rdg.readUTF();
                                System.out.println("Message from sensor: " + str);
                                break;
                        }
                    }
                } catch (Exception ex) {
                    System.out.println("Error communicating with remote Spot: " + ex.toString());
                } finally {
                    try {
                        connected = false;
                        if (conn != null) { 
                            xdg.reset();
                            xdg.writeByte(DISPLAY_SERVER_QUITTING);        // packet type
                            conn.send(xdg);                                // broadcast it
                            conn.close();
                            conn = null;
                        }
                        // TODO Added
                        if (running)
                        	server.stopProducer(stringedSpotAddress);                        
                    } catch (IOException ex) { /* ignore */ }
                }
            }
        }
    }

}
