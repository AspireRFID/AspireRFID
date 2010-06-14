/**
 * Copyright (c) 2008-2010, AspireRFID
 *
 * This library is free software; you can redistribute it and/or
 * modify it either under the terms of the GNU Lesser General Public
 * License version 2.1 as published by the Free Software Foundation
 * (the "LGPL"). If you do not alter this
 * notice, a recipient may use your version of this file under the LGPL.
 *
 * You should have received a copy of the LGPL along with this library
 * in the file COPYING-LGPL-2.1; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA
 *
 * This software is distributed on an "AS IS" basis, WITHOUT WARRANTY
 * OF ANY KIND, either express or implied. See the LGPL  for
 * the specific language governing rights and limitations.
 *
 * Contact: AspireRFID mailto:aspirerfid@ow2.org
 */

package org.ow2.aspirerfid.ale.epc.spec;

import java.util.StringTokenizer;

/**
 * Trigger type : No enumeration in JDK 1.4
 * 
 * @author pops
 * @author R.DAGHER
 */
public class TriggerType {
	/**
	 * Trigger Header definition
	 */
	public static final String URI_HEADER = new String("urn:icom:ale:trigger:");
	
	/**
	 * TAG Trigger : indicates a trigger bound to a specific TAG code (SGLN) 
	 */
	public static final byte TAG_TRIGGER = 0;
	
	
	/**
	 * PAUSE Trigger : indicates a trigger bound to the PAUSE event
	 */
	public static final byte PAUSE_TRIGGER = 10;
	
	/**
	 * IHM END Trigger : indicates a trigger bound to the END IHM event
	 */
	public static final byte END_TRIGGER = 20;
	
	/**
	 * Threshold Trigger : indicates a trigger that is fired when a maximum threshold 
	 * 					   is reached
	 */
	public static final byte THRESHOLD_TRIGGER = 30;
	
	public static void validateHeader (String uri, TriggerType triggerInfo ) 
		throws IllegalArgumentException
		{
		StringTokenizer tokenizer ; 	/* For parsing URI */
		String payload_token ;			/* extract of the payload (uri - header) */
		String type;
		byte _type;
	
		// Cut in tokens delimited by ':'
		tokenizer = new StringTokenizer(uri,":");
		// Validate and decode fields 
		if ((tokenizer.nextToken().equals("urn"))
			&& (tokenizer.nextToken().equals("icom"))
			&& (tokenizer.nextToken().equals("ale"))
			&& (tokenizer.nextToken().equals("trigger")))
		{
			type = tokenizer.nextToken();
			// Validate Type
			if (type.equals("tag")) {
				_type = TriggerType.TAG_TRIGGER ;
			} else if (type.equals("pause")) {
				_type = TriggerType.PAUSE_TRIGGER ;
			} else if (type.equals("end")) {
				_type = TriggerType.END_TRIGGER ;
			} else if (type.equals("threshold")) {
				_type = TriggerType.THRESHOLD_TRIGGER ;
			} else {
				// Invalid Type : throw exception
				throw new IllegalArgumentException("Invalid Type" + type);
			}
			if ((_type == TriggerType.TAG_TRIGGER) || (_type == TriggerType.THRESHOLD_TRIGGER)){
				// extract payload : header - type
				payload_token = uri.substring(uri.indexOf(type)+type.length()+1);
				// TODO : validate data
			} else {
				// No data
				payload_token = "";
			}
		}
		else {
			// Invalid header
			throw new IllegalArgumentException("Invalid header");
		}
		/* Set triggerInfo fields*/
		triggerInfo.triggerType = _type;
		triggerInfo.triggerData = payload_token;
	}
	
	
	public static String buildTriggerUR(StringBuffer sBuffer, TriggerType triggerInfo) {
		return buildTriggerURI(sBuffer, triggerInfo.triggerType, triggerInfo.triggerData);
	}
	
	
	public static String buildTriggerURI (StringBuffer sBuffer, byte triggerType, String triggerData) {
		String ret ;
		
		sBuffer.setLength(0); 
		
		// set URI header
		sBuffer.append(TriggerType.URI_HEADER);
		
		// set payload
		switch (triggerType) {
			case TriggerType.PAUSE_TRIGGER :
				sBuffer.append("pause");
				break;
			case TriggerType.END_TRIGGER :
				sBuffer.append("end");
				break;
			case TriggerType.THRESHOLD_TRIGGER :
				sBuffer.append("threshold:").append(triggerData);
				break;
			case TriggerType.TAG_TRIGGER :
				sBuffer.append("tag:").append(triggerData);
				break;
			default :
				sBuffer.setLength(0); 
				throw new IllegalArgumentException("Invalid trigger type : " + triggerType);
		}
		ret = sBuffer.toString();
		
		sBuffer.setLength(0);
		return ret;
	}
	/*
	 * Fields
	 **/
	
	protected byte triggerType;    /* Trigger Type */
	protected String triggerData;  /* Trigger Data */

};