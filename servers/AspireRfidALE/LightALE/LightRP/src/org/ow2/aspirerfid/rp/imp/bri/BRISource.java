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

package org.ow2.aspirerfid.rp.imp.bri;

import java.util.StringTokenizer;

import org.ow2.aspirerfid.rp.api.DataSelector;
import org.ow2.aspirerfid.rp.api.RPException;
import org.ow2.aspirerfid.rp.api.ReadPoint;
import org.ow2.aspirerfid.rp.api.Source;
import org.ow2.aspirerfid.rp.api.TagFieldValue;
import org.ow2.aspirerfid.rp.api.TagSelector;
import org.ow2.aspirerfid.rp.api.Trigger;
import org.ow2.aspirerfid.rp.api.readreport.ReadReport;
import org.ow2.aspirerfid.rp.imp.factory.Configurable;


import com.intermec.datacollection.rfid.BasicBRIReader;
import com.intermec.datacollection.rfid.BasicReaderException;

public class BRISource implements Source {
	
	private String name;
	
	private ReadPoint myReadPoint;
	
	private ReadPoint[] myReadPoints;
	

	private BasicBRIReader myReader;
		
	private int readTimeout;
	
	/**
	 * Constructor
	 * @param name
	 * @param myReader
	 */
	public BRISource(String name, BasicBRIReader myReader) {
		// Init fields
		this.name = name;
		this.myReader = myReader;
		this.myReadPoint = BRIReadpoint.ANT1;
		this.myReadPoints = new BRIReadpoint[1];
		this.myReadPoints[0] = this.myReadPoint;		
	}

	public void addReadPoints(ReadPoint[] readPoints) throws RPException {
		throw RPException.NOT_SUPPORTED_OPERATION;

	}

	public void addReadTriggers(Trigger[] triggers) throws RPException {
		throw RPException.NOT_SUPPORTED_OPERATION;

	}

	public void addTagSelectors(TagSelector[] selectors) throws RPException {
		throw RPException.NOT_SUPPORTED_OPERATION;
	}

	public ReadPoint[] getAllReadPoints() throws RPException {
		return this.myReadPoints;
	}

	public Trigger[] getAllReadTriggers() throws RPException {
		throw RPException.NOT_SUPPORTED_OPERATION;
	}

	public TagSelector[] getAllTagSelectors() throws RPException {
		throw RPException.NOT_SUPPORTED_OPERATION;
	}

	public int getGlimpsedTimeout() throws RPException {
		throw RPException.NOT_SUPPORTED_OPERATION;
	}

	public int getLostTimeout() throws RPException {
		throw RPException.NOT_SUPPORTED_OPERATION;
	}

	public int getMaxReadDutyCycles() throws RPException {
		throw RPException.NOT_SUPPORTED_OPERATION;
	}

	public String getName() throws RPException {
		return this.name;
	}

	public int getObservedThreshold() throws RPException {
		throw RPException.NOT_SUPPORTED_OPERATION;
	}

	public int getObservedTimeout() throws RPException {
		throw RPException.NOT_SUPPORTED_OPERATION;
	}

	public int getReadCyclesPerTrigger() throws RPException {
		throw RPException.NOT_SUPPORTED_OPERATION;
	}

	public ReadPoint getReadPoint(String name) throws RPException {
		throw RPException.NOT_SUPPORTED_OPERATION;
	}

	public int getReadTimeout() throws RPException {
		return this.readTimeout;
	}

	public Trigger getReadTrigger(String name) throws RPException {
		throw RPException.NOT_SUPPORTED_OPERATION;
	}

	public int getSession() throws RPException {
		int session;
		String rsp, line;
		StringTokenizer tokenizer;
		
		// Send CMD
		try {
			rsp = this.myReader.execute("ATTRIB SESSION");
		} catch (BasicReaderException e) {
			throw new RPException(e);
		}
		
		// retrieve Session from response
		tokenizer = new StringTokenizer(rsp, "\n");
		line = tokenizer.nextToken();
		session = Integer.parseInt(line.substring(line.indexOf('=')+1));
				
		return session;
	}

	public TagSelector getTagSelector(String name) throws RPException {
		throw RPException.NOT_SUPPORTED_OPERATION;
	}

	/**
	 * The source is always fixed on the PDA.
	 */
	public boolean isFixed() throws RPException {
		return true;
	}

	/**
	 * TagSelectors are ignored because not implemented for BRI.
	 */
	public void kill(String[] passwords, TagSelector[] selectors)
			throws RPException {
		throw RPException.NOT_SUPPORTED_OPERATION;
		// TODO
	}

	public ReadReport rawReadIDs(DataSelector dataselector) throws RPException {
		BRIReadReport report = BRIReadReport.EMPTY_BRI_READ_REPORT;  ;
		String rsp = null;
		
		// Inventory of observed tags (only ids)
		try {			
			rsp = this.myReader.execute("RD EPCID");
			//System.out.println("inventory response : \n" + sResponse);
		} catch (BasicReaderException e) {
			throw new RPException(e);
		}
		
		// Decode and Fill report
		if ((rsp != null) && !rsp.startsWith("NOTAG")) {
			//report = decodeReadResponse(rsp, new BRIReadReport());
			report = decodeReadResponse(rsp, BRIReadReport.REUSABLE_REPORT);
		}
		
		return report;
	}

	private static BRIReadReport decodeReadResponse(String rsp, BRIReadReport report) {
		StringTokenizer tokenizer;
		String line;
		int numTags ;
		
		// tokenize
		tokenizer = new StringTokenizer(rsp, "\r\n");
		
		// if not Error or no tags, there is one id per line
		numTags = tokenizer.countTokens() -1 ;
		if (numTags > 0) {
			for (int i = 0 ; i < numTags ; i++) {
				line = tokenizer.nextToken();
				// extract and add the EPCID to the report
				report.addTag(line.substring(1 /*skip the H*/));
			}
		}
		
		return report;
	}

	public ReadReport read(DataSelector dataSelector, String[] passwords,
			TagSelector[] selectors) throws RPException {
		throw RPException.NOT_SUPPORTED_OPERATION;
	}

	public ReadReport readIDs(DataSelector dataselector) throws RPException {
		// no tag selectors support.
		return rawReadIDs(dataselector);
	}

	public void removeAllReadPoints() throws RPException {
		throw RPException.NOT_SUPPORTED_OPERATION;

	}

	public void removeAllReadTriggers() throws RPException {
		throw RPException.NOT_SUPPORTED_OPERATION;

	}

	public void removeAllTagSelectors() throws RPException {
		throw RPException.NOT_SUPPORTED_OPERATION;

	}

	public void removeReadPoints(ReadPoint[] readPoints) throws RPException {
		throw RPException.NOT_SUPPORTED_OPERATION;

	}

	public void removeReadTriggers(Trigger[] triggers) throws RPException {
		throw RPException.NOT_SUPPORTED_OPERATION;

	}

	public void removeTagSelectors(TagSelector[] selectors) throws RPException {
		throw RPException.NOT_SUPPORTED_OPERATION;
	}

	public void setGlimpsedTimeout(int timeout) throws RPException {
		throw RPException.NOT_SUPPORTED_OPERATION;

	}

	public void setLostTimeout(int timeout) throws RPException {
		throw RPException.NOT_SUPPORTED_OPERATION;

	}

	public void setMaxReadDutyCycles(int cycles) throws RPException {
		throw RPException.NOT_SUPPORTED_OPERATION;

	}

	public void setObservedThreshold(int threshold) throws RPException {
		throw RPException.NOT_SUPPORTED_OPERATION;

	}

	public void setObservedTimeout(int timeout) throws RPException {
		throw RPException.NOT_SUPPORTED_OPERATION;

	}

	public void setReadCyclesPerTrigger(int cycles) throws RPException {
		throw RPException.NOT_SUPPORTED_OPERATION;
	}

	public void setReadTimeout(int timeout) throws RPException {
		
		if (timeout > 0) {
			this.readTimeout = timeout;
			try {
				setupDaqStage(timeout);
			} catch (BasicReaderException e) {
				throw new RPException(e);
			}
		}
		else {
			// invalid readTimeout.
			throw new IllegalArgumentException("readTimeout <=0 : " + timeout);
		}
	}

	public void setSession(int session) throws RPException {
		if (session >= Configurable.SESSION_MIN_VALUE && session <= Configurable.SESSION_MAX_VALUE) {
			try {
				this.myReader.execute("ATTRIB SESSION=" + session);
			} catch (BasicReaderException e) {
				throw new RPException(e);
			}
		}
	}

	public void write(TagFieldValue[] data, String[] passwords,
			TagSelector[] selectors) throws RPException {
		throw RPException.NOT_SUPPORTED_OPERATION;

	}

	public void writeID(String newID, String[] passwords,
			TagSelector[] selectors) throws RPException {
		throw RPException.NOT_SUPPORTED_OPERATION;

	}
	
	/*
	 * Factory methods for data acquisition stage setup
	 */

	
	/**
	 * 
	 * @param readTimeout
	 * @throws BasicReaderException
	 */
	public void initDaqStage(int readTimeout ) throws BasicReaderException {		
		// Enable Timeout Mode
		myReader.execute("ATTRIB TIMEOUTMODE=ON");
		
		// Set INITRIES to 1
		myReader.execute("ATTRIB INITTRIES  1");	
		
		// Set default values :
		setupDaqStage(readTimeout /*ms*/);
	}
	
	/**
	 * 
	 * @param readCyclesPerTrigger
	 * @param readTimeout
	 * @throws BasicReaderException
	 */
	private void setupDaqStage(int readTimeout) throws BasicReaderException {
		
		// Set ID Timeout
		myReader.execute("ATTRIB IDTIMEOUT=" + readTimeout);
		
		// Set ANT timeout : to garantee the condition IDTIMEOUT > ANTTIMEOUT
		myReader.execute("ATTRIB ANTTIMEOUT=" + (readTimeout/2));

		// Disable ID report in response
		myReader.execute("ATTRIB IDREPORT=OFF"); 
		
		// Enable No tag reporting
		//myReader.execute("ATTRIB NOTAGRPT=ON");
	}
	
}
