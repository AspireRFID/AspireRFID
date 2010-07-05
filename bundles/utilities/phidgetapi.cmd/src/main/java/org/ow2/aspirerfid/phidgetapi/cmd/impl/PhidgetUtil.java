/*
   Copyright 2005-2008, OW2 Aspire RFID project 
   
   This library is free software; you can redistribute it and/or modify it 
   under the terms of the GNU Lesser General Public License as published by 
   the Free Software Foundation (the "LGPL"); either version 2.1 of the 
   License, or (at your option) any later version. If you do not alter this 
   notice, a recipient may use your version of this file under either the 
   LGPL version 2.1, or (at his option) any later version.
   
   You should have received a copy of the GNU Lesser General Public License 
   along with this library; if not, write to the Free Software Foundation, 
   Inc., 51 Franklin Street, Fifth Floor, Boston, MA 02110-1301 USA.
   
   This software is distributed on an "AS IS" basis, WITHOUT WARRANTY OF ANY 
   KIND, either express or implied. See the GNU Lesser General Public 
   License for the specific language governing rights and limitations.

   Contact: OW2 Aspire RFID project <X AT Y DOT org> (with X=aspirerfid and Y=ow2)

   LGPL version 2.1 full text http://www.gnu.org/licenses/lgpl-2.1.txt    
 */
package org.ow2.aspirerfid.phidgetapi.cmd.impl;

import com.phidgets.Phidget;
import com.phidgets.PhidgetException;

/**
 * This class provides utilities for Phidgets development
 * 
 * @author Didier Donsez
 */
public class PhidgetUtil  {

	public static String toString(Phidget p) throws PhidgetException {
		return   "id="+p.getDeviceID()
				+";sn="+p.getSerialNumber()
				+";label="+p.getDeviceLabel()
				+";name="+p.getDeviceName()
				+";type="+p.getDeviceType()
				+";version="+p.getDeviceVersion()
				+";class="+getClassStr(p.getDeviceClass())
				;
	}

	public static String toJSON(Phidget p) throws PhidgetException {
		return   "{"+toString(p)+"}";
	}
	
	public static String getClassStr(int deviceClass){
		switch(deviceClass){
			case Phidget.PHIDCLASS_ACCELEROMETER: return "accelormeter";
			case Phidget.PHIDCLASS_ADVANCEDSERVO: return "advancedservo";
			case Phidget.PHIDCLASS_ENCODER: return "encoder";
			case Phidget.PHIDCLASS_INTERFACEKIT: return "interfacekit";
			case Phidget.PHIDCLASS_LED: return "led";
			case Phidget.PHIDCLASS_MOTORCONTROL: return "motorcontrol";
			case Phidget.PHIDCLASS_NOTHING: return "nothing";
			case Phidget.PHIDCLASS_PHSENSOR: return "phsensor";
			case Phidget.PHIDCLASS_RFID: return "rfid";
			case Phidget.PHIDCLASS_SERVO: return "servo";
			case Phidget.PHIDCLASS_STEPPER: return "stepper";
			case Phidget.PHIDCLASS_TEMPERATURESENSOR: return "temperaturesensor";
			case Phidget.PHIDCLASS_TEXTLCD: return "textled";
			case Phidget.PHIDCLASS_TEXTLED: return "textled";
			case Phidget.PHIDCLASS_WEIGHTSENSOR: return "weightsensor";
			default: return "unknown";
		}		
	}
	
	public static String getIDStr(int deviceID){
		switch(deviceID){
		case Phidget.PHIDID_ACCELEROMETER_2AXIS 	: return "ACCELEROMETER_2AXIS";
		case Phidget.PHIDID_INTERFACEKIT_0_0_4 		: return "INTERFACEKIT_0_0_4";
		case Phidget.PHIDID_WEIGHTSENSOR 			: return "WEIGHTSENSOR";
		/*
		case Phidget.PHIDID_ACCELEROMETER_3AXIS 	126
		case Phidget.PHIDID_ADVANCEDSERVO_1MOTOR 	130
		case Phidget.PHIDID_ADVANCEDSERVO_8MOTOR 	58
		case Phidget.PHIDID_BIPOLAR_STEPPER_1MOTOR 	123
		case Phidget.PHIDID_ENCODER_1ENCODER_1INPUT 	75
		case Phidget.PHIDID_ENCODER_HS_1ENCODER 	128
		case Phidget.PHIDID_ENCODER_HS_4ENCODER_4INPUT 	79
		case Phidget.PHIDID_INTERFACEKIT_0_0_8 	129
		case Phidget.PHIDID_INTERFACEKIT_0_16_16 	68
		case Phidget.PHIDID_INTERFACEKIT_0_8_8_w_LCD 	83
		case Phidget.PHIDID_INTERFACEKIT_4_8_8 	4
		case Phidget.PHIDID_INTERFACEKIT_8_8_8 	69
		case Phidget.PHIDID_INTERFACEKIT_8_8_8_w_LCD 	125
		case Phidget.PHIDID_IR 	77
		case Phidget.PHIDID_LED_64 	74
		case Phidget.PHIDID_LED_64_ADV 	76
		case Phidget.PHIDID_LINEAR_TOUCH 	118
		case Phidget.PHIDID_MOTORCONTROL_HC_2MOTOR 	89
		case Phidget.PHIDID_MOTORCONTROL_LV_2MOTOR_4INPUT 	88
		case Phidget.PHIDID_NOTHING 	1
		case Phidget.PHIDID_PHSENSOR 	116
		case Phidget.PHIDID_RFID 	48
		case Phidget.PHIDID_RFID_2OUTPUT 	49
		case Phidget.PHIDID_ROTARY_TOUCH 	119
		case Phidget.PHIDID_SERVO_1MOTOR 	57
		case Phidget.PHIDID_SERVO_1MOTOR_OLD 	2
		case Phidget.PHIDID_SERVO_4MOTOR 	56
		case Phidget.PHIDID_SERVO_4MOTOR_OLD 	3
		case Phidget.PHIDID_SPATIAL_ACCEL_3AXIS 	127
		case Phidget.PHIDID_SPATIAL_ACCEL_GYRO_COMPASS 	51
		case Phidget.PHIDID_TEMPERATURESENSOR 	112
		case Phidget.PHIDID_TEMPERATURESENSOR_4 	50
		case Phidget.PHIDID_TEXTLCD_2x20 	82
		case Phidget.PHIDID_TEXTLCD_2x20_w_0_8_8 	339
		case Phidget.PHIDID_TEXTLCD_2x20_w_8_8_8 	381
		case Phidget.PHIDID_TEXTLED_1x8 	73
		case Phidget.PHIDID_TEXTLED_4x8 	72
		case Phidget.PHIDID_UNIPOLAR_STEPPER_4MOTOR 	122
		*/
		default: return "unknown";
	}		
		
	}
}