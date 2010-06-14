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

/**
 * ECTrigger denotes a URI that is used to specify a start or stop trigger for 
 * an event cycle or command cycle. The interpretation of this URI is determined by 
 * the ALE implementation; the kinds and means of triggers supported is intended 
 * to be a point of extensibility. 
 *
 * @author pops
 * @author R.DAGHER
 */
public class ECTrigger {

	/**
	 * Trigger URI. Using the following syntax :<br>
	 * <b> <pre> urn:epcglobal:ale:trigger:'triggerType':'triggerData' </pre> </b>
	 *  with :<br>
	 *  <pre>
	 *   _______________________________________________________________________ 
	 *  |              |                   |                                    |
	 *  | 'triggerType'|   'triggerData'   |       Description                  |
	 *  |______________|___________________|____________________________________|
	 *  | "tag"        | "'SGLN_code'"     | SGLN code for triggering tags scan |
	 *  |______________|___________________|____________________________________|
	 *  | "pause"      | N/A               | PAUSE assertion/de-assertion IHM   |
	 *  |              |                   | event (toggle)                     |
     *  |______________|___________________|____________________________________|
	 *  | "end"        | N/A               | END assertion IHM event            |
	 *  |______________|___________________|____________________________________|
	 *  | "threshold"  | "'VAL'"           | Maximum number of tags to scan in  |
     *  |              |                   | an event cycle (long)              |
	 *  |______________|___________________|____________________________________|
	 *  </pre>
	 *  
	 */
	private String triggerURI;	/* Complete String URI */
	
	// Extensions //
	private TriggerType TriggerInfo;    /* Trigger Type and data */
	
	/**
	 * Default Constructor.
	 */
	
	public ECTrigger() {
		TriggerInfo = new TriggerType();
	}
	
	/**
	 * Constructor
	 * @param triggerURI
	 */
	public ECTrigger(String triggerURI) throws IllegalArgumentException {
		this();
		setTriggerURI(triggerURI);
	}

	/**
	 * Getter for triggerURI
	 * @return the triggerURI
	 */
	public String getTriggerURI() {
		return triggerURI;
	}

	/**
	 * Setter for triggerURI
	 * @param triggerURI the value to set
	 */
	public void setTriggerURI(String triggerURI) throws IllegalArgumentException{
		
		// Validate UDI, and retrieve Trigger information
		TriggerType.validateHeader(triggerURI, this.TriggerInfo);
		
		// set Trigger URI
		this.triggerURI = triggerURI;
	}
	
	
	/**
	 * Getter for trigger type 
	 * @return the trigger type 
	 */
	public byte getTriggerType() {
		return this.TriggerInfo.triggerType;
	}
	
	/**
	 * Getter for triggerURI
	 * @return the trigger data
	 */
	public String getTriggerData() {
		return this.TriggerInfo.triggerData;
	}
	
	/* 
	 * Debug utility
	 */
	public String toString() {
		return "URI : '" + this.getTriggerURI() + "'" +
			   "\n -> Type : '" + this.getTriggerType() + "'" +
			   "\n -> Data :'" + this.getTriggerData() + "'";
	}
	
	/* 
	 * Test case
	 */
	public static void main(String[] args) throws Exception {
		ECTrigger aTrigger;
		StringBuffer sBuffer = new StringBuffer();

		//aTrigger = new ECTrigger("urn:icom:ale:trigger:pause");
		aTrigger = new ECTrigger(TriggerType.buildTriggerURI(sBuffer, TriggerType.PAUSE_TRIGGER, null));
		System.out.println(aTrigger);

		//aTrigger = new ECTrigger("urn:icom:ale:trigger:end");
		aTrigger = new ECTrigger(TriggerType.buildTriggerURI(sBuffer, TriggerType.END_TRIGGER, null));
		System.out.println(aTrigger);
		
		//aTrigger = new ECTrigger("urn:icom:ale:trigger:threshold:10");
		aTrigger = new ECTrigger(TriggerType.buildTriggerURI(sBuffer, TriggerType.THRESHOLD_TRIGGER, "10"));
		System.out.println(aTrigger);
		
		//aTrigger = new ECTrigger("urn:icom:ale:trigger:tag:"+"urn:epc:tag:sgln-96:2.0012345.00003");
		aTrigger = new ECTrigger(TriggerType.buildTriggerURI(sBuffer, TriggerType.TAG_TRIGGER, "urn:epc:tag:sgln-96:2.0012345.00003"));

		System.out.println(aTrigger);
		
		// Exception test
		// aTrigger = new ECTrigger();
	}
}
