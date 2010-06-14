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

package org.ow2.aspirerfid.rp.imp.caen;

import org.ow2.aspirerfid.rp.api.DataSelector;
import org.ow2.aspirerfid.rp.api.RPException;
import org.ow2.aspirerfid.rp.api.ReadPoint;
import org.ow2.aspirerfid.rp.api.Source;
import org.ow2.aspirerfid.rp.api.TagFieldValue;
import org.ow2.aspirerfid.rp.api.TagSelector;
import org.ow2.aspirerfid.rp.api.Trigger;
import org.ow2.aspirerfid.rp.api.readreport.ReadReport;


import com.caen.RFIDLibrary.CAENRFIDException;
import com.caen.RFIDLibrary.CAENRFIDLogicalSource;
import com.caen.RFIDLibrary.CAENRFIDReader;
import com.caen.RFIDLibrary.CAENRFIDTag;

public class CAENSource implements Source {
	
	private String name;
	
	private ReadPoint myReadPoint;
	
	private ReadPoint[] myReadPoints;
	

	private CAENRFIDReader myReader;
	private CAENRFIDLogicalSource mySource;
		
	private int readTimeout;

	
	/**
	 * Constructor
	 * @param name
	 * @param myReader
	 */
	public CAENSource(String name, CAENRFIDReader myReader) {
		// Init fields
		this.name = name;
		this.myReader = myReader;
		this.mySource = this.myReader.GetSources()[0];
		this.myReadPoint = CAENReadpoint.ANT1;
		this.myReadPoints = new CAENReadpoint[1];
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
		System.err.println(this.getClass() + " : Unsupported operation");
		return 0;
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
	 * TagSelectors are ignored because not implemented for CAEN.
	 */
	public void kill(String[] passwords, TagSelector[] selectors)
			throws RPException {
		throw RPException.NOT_SUPPORTED_OPERATION;
		// TODO
	}

	public ReadReport rawReadIDs(DataSelector dataselector) throws RPException {
		CAENReadReport report = CAENReadReport.EMPTY_CAEN_READ_REPORT;  ;
		CAENRFIDTag[] myRFIDTags = null;
		long time = 0, t_0 = System.currentTimeMillis();
		
		do {
			// Inventory of observed tags (only ids)
			try {			
				myRFIDTags = mySource.InventoryTag();
			} catch (CAENRFIDException e) {
				e.printStackTrace();
			}
			if (myRFIDTags != null) {
				// glue to fit contract : append tags to report
				report = decodeReadResponse(myRFIDTags, report);
			
			// check for end condition
			time = (System.currentTimeMillis() - t_0);				
			}
		} while (time < readTimeout);
		
		return report;
	}
	
	private final static StringBuffer sBuffer = new StringBuffer();
	
	private static CAENReadReport decodeReadResponse(CAENRFIDTag[] myRFIDTags, CAENReadReport report) {
		byte[] tagId;
		
		for (int i = 0; i < myRFIDTags.length; i++) {
			tagId = myRFIDTags[i].GetId();
			// Convert to Hex String in Big endian mode : Header is first
			sBuffer.setLength(0);
			for (int j = 0; j < tagId.length; j++) {
				short aByte = (short) (tagId[j]&0xFF); // convert to short for bit sign workaround
				// Add zero if necessary
				if (aByte < 16)
					sBuffer.append("0");
				// Convert to hex
				sBuffer.append(Integer.toHexString(aByte));
			}
			// append to report
			report.addTag(sBuffer.toString());
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
		}
		else {
			// invalid readTimeout.
			throw new IllegalArgumentException("readTimeout <=0 : " + timeout);
		}
	}

	public void setSession(int session) throws RPException {
		System.err.println(this.getClass() + "\n setSession operation not supported.");	
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
	public void initDaqStage(int readTimeout) throws RPException {		
		setReadTimeout(readTimeout);
	}
	
}
