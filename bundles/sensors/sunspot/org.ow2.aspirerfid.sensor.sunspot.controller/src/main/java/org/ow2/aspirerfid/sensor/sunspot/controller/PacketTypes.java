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

package org.ow2.aspirerfid.sensor.sunspot.controller;

/**
 * Packet types for Telemetry app
 * 
 * @author Ron Goldman<br>
 * Date: January 15, 2007, revised: August 1, 2007
 */
public interface PacketTypes {

//    /** Port to use to locate the host application. */
//    public static final String BROADCAST_PORT = "42";
//    /** Port to use for sending commands and replies between the SPOT and the host application. */
//    public static final String CONNECTED_PORT = "43";
    
    public static final int DEFAULT_DATAGRAM_SIZE = 60;
    
	public static final int ADMIN_SVC_PORT = 60;
	
	public static final int TEMP_PROD_PORT = 61;
	
	public static final int LUM_PROD_PORT = 62;
	
	public static final int ACCEL_PROD_PORT = 63;
    
	
    /** Command & reply codes for data packets */
    
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
    
    
	// TODO : Delete following lines
    /** Client command to locate a display server. */
    public static final byte LOCATE_DISPLAY_SERVER_REQ  = 1;    // sent to display host (broadcast)
    /** Host command to indicate it is restarting. */
    public static final byte DISPLAY_SERVER_RESTART     = 2;    // sent to any clients (broadcast)
    
    /** Host command to indicate it is quiting. */
    public static final byte DISPLAY_SERVER_QUITTING    = 3;    // (direct p2p)
    /** Host command to request the current accelerometer scale being used. */
    public static final byte GET_ACCEL_INFO_REQ         = 4;
    /** Host command to specify the accelerometer scale to be used. */
    public static final byte SET_ACCEL_SCALE_REQ        = 5;
    /** Host command to request the accelerometer be calibrated. */
    public static final byte CALIBRATE_ACCEL_REQ        = 6;
    /** Host command to request accelerometer data be sent. */
    public static final byte SEND_ACCEL_DATA_REQ        = 7;
    /** Host command to request accelerometer data stop being sent. */
    public static final byte STOP_ACCEL_DATA_REQ        = 8;
    /** Host command to ping the remote SPOT and get the radio signal strength. */
    public static final byte PING_REQ                   = 9;
    /** Host command to blink the remote SPOT's LEDs. */
    public static final byte BLINK_LEDS_REQ             = 10;

    /** Host reply to indicate it is available. */
    public static final byte DISPLAY_SERVER_AVAIL_REPLY = 101;
    /** Client reply to indicate the current accelerometer scale & zero offsets. */
    public static final byte GET_ACCEL_INFO_REPLY       = 104;
    /** Client reply to return the accelerometer gains. */
    public static final byte GET_ACCEL_INFO2_REPLY      = 105;
    /** Client reply to indicate the current accelerometer scale being used. */
    public static final byte SET_ACCEL_SCALE_REPLY      = 106;
    /** Client reply to indicate the current accelerometer rest offsets. */
    public static final byte CALIBRATE_ACCEL_REPLY      = 107;
    /** Client reply with current accelerometer readings taken using the 2G scale. */
    public static final byte ACCEL_2G_DATA_REPLY        = 108;
    /** Client reply with current accelerometer readings taken using the 6G scale. */
    public static final byte ACCEL_6G_DATA_REPLY        = 109;
    /** Client reply to a ping includes the radio signal strength & battery level. */
    public static final byte PING_REPLY                 = 110;
    /** Client reply with any error message for the host to display. */
    public static final byte MESSAGE_REPLY              = 111;

	




}