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

package org.ow2.aspirerfid.ale.engine.input;

import org.ow2.aspirerfid.ale.engine.lr.IcomLRPropSetter;


public class RPChannelConfig {
	
	public final static int POWER = 0 , INITIAL_Q = 1, SESSION = 2 ;

	public static final int NUM_ATTRIBUTES = 3;	
	
	protected int[] config ;
	
	protected int[] readerPowerMapping;
	
	protected boolean[] update;		

	/**
	 * Constructor
	 */
	public RPChannelConfig() {
		config = new int[NUM_ATTRIBUTES]; 
		update = new boolean[NUM_ATTRIBUTES]; 
	}
	
	/**
	 * Constructor
	 * @param power
	 * @param initialQ
	 * @param session
	 * @param readerPowerMapping
	 */
	public RPChannelConfig(int power, int initialQ, int session, int[] readerPowerMapping) {
		super();
		config[POWER] = power;
		config[INITIAL_Q] = initialQ;
		config[SESSION] = session;
		this.readerPowerMapping = readerPowerMapping;
	}

	/**
	 * Getter for power
	 * @return the power
	 */
	public int getPower() {
		return config[POWER];
	}

	/**
	 * Getter for initialQ
	 * @return the initialQ
	 */
	public int getInitialQ() {
		return config[INITIAL_Q];
	}

	/**
	 * Getter for session
	 * @return the session
	 */
	public int getSession() {
		return config[SESSION];
	}

	/**
	 * Getter for readerPowerMapping
	 * @return the readerPowerMapping
	 */
	public int[] getReaderPowerMapping() {
		return readerPowerMapping;
	}

	/**
	 * Setter for readerPowerMapping
	 * @param readerPowerMapping the value to set
	 */
	public void setReaderPowerMapping(int[] readerPowerMapping) {
		this.readerPowerMapping = readerPowerMapping;
	}

	/**
	 * Setter for power
	 * @param power the value to set
	 */
	public void setPower(int power) {
		config[POWER] = power;
		update[POWER] = true;
	}

	/**
	 * Setter for initialQ
	 * @param initialQ the value to set
	 */
	public void setInitialQ(int initialQ) {
		config[INITIAL_Q] = initialQ;
		update[INITIAL_Q] = true;
	}

	/**
	 * Setter for session
	 * @param session the value to set
	 */
	public void setSession(int session) {
		config[SESSION] = session;
		update[SESSION] = true;
	}
	
	/**
	 * Reconfiguration Utility.
	 * @param setter
	 */
	public void reconfigure(IcomLRPropSetter setter) {
		// Update Power
		if (update[POWER]) {
			setter.setPower(config[POWER], readerPowerMapping);
			update[POWER] = false;
		}
		
		// Update initialQ
		if (update[INITIAL_Q]) {
			setter.setInitialQ(config[INITIAL_Q]);
			update[INITIAL_Q] = false;
		}
	
		// Update Session
		if (update[SESSION]) {
			setter.setSession(config[SESSION]);
			update[SESSION] = false;
		}
	}
	
}
