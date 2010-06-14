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

package org.ow2.aspirerfid.ale.epc.reports;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.ow2.aspirerfid.ale.epc.spec.ECSpec;
import org.ow2.aspirerfid.ale.epc.spec.ECTrigger;



/**
 * ECReports is the output from an event cycle. <br>
 * The "meat" of an ECReports instance is the ordered {@link #reports list} of ECReport instances, 
 * each corresponding to an ECReportSpec instance in the event cycle's ECSpec, and 
 * appearing in the order corresponding to the ECSpec. In addition to the reports 
 * themselves, ECReports contains a number of "header" fields that provide useful 
 * information about the event cycle.
 * 
 * @author pops
 * @author R.DAGHER
 */
public class ECReports {
	/**
	 * The name of the ECSpec that controlled this event cycle.<br>
	 * In the case of an ECSpec that was requested using the immediate method ,
	 * this name is one chosen by the ALE implementation.
	 */
	private String specName ;
	
	/**
	 * A representation of the date and time when the event cycle ended.<br>
	 * For bindings in which this field is represented textually, an ISO-8601 
	 * compliant representation SHOULD be used.
	 */
	private Date date ;
	
	/**
	 * An identifier for the deployed instance of the ALE implementation. <br>
	 * The meaning of this identifier is outside the scope of this specification.
	 */
	public static final String ALEID  = "ICOM_LIGHT_ALE" ;
	
	/**
	 * The total time, in milliseconds, from the start of the event cycle to the 
	 * end of the event cycle.
	 */
	private long totalMilliseconds ;
	
	/**
	 * Indicates what kind of event caused the event cycle to initiate.
	 * <ul>
	 * 	<li> The receipt of an explicit start trigger </li>
	 * 	<li> the expiration of the repeat period </li>
	 * 	<li> or a transition to the requested state when no start triggers were specified in the ECSpec </li>
	 * </ul>
	 */
	private ECInitiationCondition initiationCondition ;
	
	/**
	 * If initiationCondition is TRIGGER, the ECTrigger instance corresponding 
	 * to the trigger that initiated the event cycle; omitted otherwise.
	 */
	private ECTrigger initiationTrigger ;
	
	/**
	 * Indicates what kind of event caused the event cycle to terminate.
	 * <ul>
	 * 	<li> the receipt of an explicit stop trigger </li>
	 * 	<li> the expiration of the event cycle duration </li>
	 * 	<li> the read field being stable for the prescribed amount of time </li>
	 * 	<li> or the "when data available" condition becoming true. </li>
	 * </ul>
	 */
	private ECTerminationCondition terminationCondition ;
	
	/**
	 * If terminationCondition is TRIGGER, the ECTrigger instance corresponding to 
	 * the trigger that terminated the event cycle; omitted otherwise.
	 */
	private ECTrigger terminationTrigger;
	
	/**
	 * A copy of the ECSpec that generated this ECReports instance. <br>
	 * Only included if the ECSpec has includeSpecInReports set to true.
	 */
	private ECSpec ECSpec ;
	
	/**
	 * List of reports generated during the event cycle.
	 */
	private ECReport[] reports;

	//
	// <<extension point>>
	//	---
	/**
	 * ISO-8601 format for string representation of the {@link #date} field
	 */
	public static final SimpleDateFormat ISO_8601_SDF = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss z");
	
	/**
	 * Constructor
	 * @param specName
	 * @param date
	 * @param aLEID
	 * @param totalMilliseconds
	 * @param initiationCondition
	 * @param initiationTrigger
	 * @param terminationCondition
	 * @param terminationTrigger
	 * @param eCSpec
	 * @param reports
	 */
	public ECReports(String specName, Date date,
			long totalMilliseconds, ECInitiationCondition initiationCondition,
			ECTrigger initiationTrigger,
			ECTerminationCondition terminationCondition,
			ECTrigger terminationTrigger, ECSpec eCSpec, ECReport[] reports) {
		this.specName = specName;
		this.date = date;
		this.totalMilliseconds = totalMilliseconds;
		this.initiationCondition = initiationCondition;
		this.initiationTrigger = initiationTrigger;
		this.terminationCondition = terminationCondition;
		this.terminationTrigger = terminationTrigger;
		ECSpec = eCSpec;
		this.reports = reports;
	}

	/**
	 * 
	 * Constructor
	 */
	public ECReports() {
	}

	public void setDate(Date date) {
		this.date = date;
	}
	
	public Date getDate(Date date) {
		return date;
	}
	
	public String getDate() {
		return ISO_8601_SDF.format(date);
	}
	
	/**
	 * Getter for specName
	 * @return the specName
	 */
	public String getSpecName() {
		return specName;
	}

	/**
	 * Getter for totalMilliseconds
	 * @return the totalMilliseconds
	 */
	public long getTotalMilliseconds() {
		return totalMilliseconds;
	}

	/**
	 * Getter for initiationCondition
	 * @return the initiationCondition
	 */
	public ECInitiationCondition getInitiationCondition() {
		return initiationCondition;
	}

	/**
	 * Getter for initiationTrigger
	 * @return the initiationTrigger
	 */
	public ECTrigger getInitiationTrigger() {
		return initiationTrigger;
	}

	/**
	 * Getter for terminationCondition
	 * @return the terminationCondition
	 */
	public ECTerminationCondition getTerminationCondition() {
		return terminationCondition;
	}

	/**
	 * Getter for terminationTrigger
	 * @return the terminationTrigger
	 */
	public ECTrigger getTerminationTrigger() {
		return terminationTrigger;
	}

	/**
	 * Getter for ECSpec
	 * @return the eCSpec
	 */
	public ECSpec getECSpec() {
		return ECSpec;
	}

	/**
	 * Getter for reports
	 * @return the reports
	 */
	public ECReport[] getReports() {
		return reports;
	}

	/**
	 * Setter for specName
	 * @param specName the value to set
	 */
	public void setSpecName(String specName) {
		this.specName = specName;
	}


	/**
	 * Setter for totalMilliseconds
	 * @param totalMilliseconds the value to set
	 */
	public void setTotalMilliseconds(long totalMilliseconds) {
		this.totalMilliseconds = totalMilliseconds;
	}

	/**
	 * Setter for initiationCondition
	 * @param initiationCondition the value to set
	 */
	public void setInitiationCondition(ECInitiationCondition initiationCondition) {
		this.initiationCondition = initiationCondition;
	}

	/**
	 * Setter for initiationTrigger
	 * @param initiationTrigger the value to set
	 */
	public void setInitiationTrigger(ECTrigger initiationTrigger) {
		this.initiationTrigger = initiationTrigger;
	}

	/**
	 * Setter for terminationCondition
	 * @param terminationCondition the value to set
	 */
	public void setTerminationCondition(ECTerminationCondition terminationCondition) {
		this.terminationCondition = terminationCondition;
	}

	/**
	 * Setter for terminationTrigger
	 * @param terminationTrigger the value to set
	 */
	public void setTerminationTrigger(ECTrigger terminationTrigger) {
		this.terminationTrigger = terminationTrigger;
	}

	/**
	 * Setter for ECSpec
	 * @param eCSpec the value to set
	 */
	public void setECSpec(ECSpec eCSpec) {
		ECSpec = eCSpec;
	}

	/**
	 * Setter for reports
	 * @param reports the value to set
	 */
	public void setReports(ECReport[] reports) {
		this.reports = reports;
	}

	public static void main(String[] args) throws Exception {
		ECReports report = new ECReports();
		
		report.setDate(new Date());
	    System.out.println(report.getDate());
	}
}
