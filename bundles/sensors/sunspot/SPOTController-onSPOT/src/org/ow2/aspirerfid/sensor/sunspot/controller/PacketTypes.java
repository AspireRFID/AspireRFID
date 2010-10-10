/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.ow2.aspirerfid.sensor.sunspot.controller;

/**
 *
 * @author lionel
 */
public interface PacketTypes {

    public static final int DEFAULT_DATAGRAM_SIZE = 60;
    
    // Command & reply codes for data packets
    
    public static final byte NEW_CONNECTION_REQ = 1;
    
    public static final byte ADMIN_SVC_CONNECTION = 2;
    
    public static final byte TEMP_PROD_CONNECTION = 3;
    public static final byte ACCEL_PROD_CONNECTION = 4;
    
    public static final byte HEARTBEAT = 10;
    
    public static final byte TEMPERATURE = 11;
    public static final byte ACCELERATION = 12;
    
    public static final byte BUTTON = 20;
    public static final byte BUTTON_SW1 = 21;
    public static final byte BUTTON_SW2 = 22;
    public static final byte BUTTON_RELEASED = 0;
    public static final byte BUTTON_PRESSED = 1;
    
    public static final int GET_BATTERY_REQ = 30;
    
    public static final int SET_LED_REQ = 40;
    public static final int SET_LED_DURATION_REQ = 41;
    public static final int SET_LEDS_REQ = 42;
    public static final int SET_LEDS_DURATION_REQ = 43;
    public static final int LEDS_OFF = 44;
    
    public static final int SET_ACCEL_SAMPLE_DELAY = 3;
   
    
    // temporary
    public static final byte BLINK_LEDS_REQ = 10;
    
    public static final byte SWITCH = 2;
    
    public static final byte ACCEL = 3;
    
}
