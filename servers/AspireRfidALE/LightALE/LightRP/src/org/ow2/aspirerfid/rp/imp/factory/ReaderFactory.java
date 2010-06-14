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

package org.ow2.aspirerfid.rp.imp.factory;

import org.ow2.aspirerfid.rp.api.RPException;
import org.ow2.aspirerfid.rp.api.ReaderDevice;
import org.ow2.aspirerfid.rp.imp.bri.BRIReaderDevice;
import org.ow2.aspirerfid.rp.imp.caen.CAENReaderDevice;


/**
 * This factory is used to create {@link ReaderDevice} implementations.
 * 
 * @see BRIReaderDevice
 * @see CAENReaderDevice
 * 
 * @author rdagher
 *
 */
public class ReaderFactory {
	
//	/**
//	 * CAEN power mapping.
//	 */
//	public final static int[] CAEN_POWER_MAPPING = {0 /* padding*/ , 50 /*mw*/, 150 /*mw*/, 200 /*mw*/, 500/*mw*/, 500/*mw*/};
//	
//	/**
//	 * BRI Power Mapping.
//	 */
//	public final static int[] BRI_POWER_MAPPING = {0 /* padding*/ , 35/* 50 mw*/, 60/*150 mw*/, 65/*200 mw*/, 85/*500mw*/, 100/*1W*/};

	
	/**
	 * BRI reader identifier.
	 */
	public final static int BRI_READER = 0;
	
	/**
	 * CAEN reader identifier.
	 */
	public final static int CAEN_READER = 1;
	
	/**
	 * Configurable attributes.
	 * @author rdagher
	 *
	 */
	public final static class ConfigAttributes {
		public final static int POWER = 0;
		public final static int INITIAL_Q = 1;
		public final static int SESSION = 2;
	}
	
	/**
	 * Currently active reader device.
	 */
	private static ReaderDevice currentReader = null;

	/**
	 * BRI Reader Device singleton.
	 */
	private static BRIReaderDevice briWrapper = null;
	
	/**
	 * CAEN Reader Device singleton.
	 */
	private static CAENReaderDevice CAENWrapper = null;
	
	/**
	 * Returns the reader singleton
	 * @param readerId the readers ID : {@link ReaderFactory#BRI_READER BRI_READER}, {@link ReaderFactory#CAEN_READER CAEN_READER}
	 * @param readerName the name of the eventual created reader.
	 * @return the created reader device.
	 */
	public static ReaderDevice getReader(int readerId, String readerName) {
		ReaderDevice reader;
		
		switch (readerId) {
			// BRI 
			case BRI_READER :
				if (briWrapper == null) {
					briWrapper = new BRIReaderDevice(readerName);
				}
				reader = briWrapper;
				break;
				
			// CAEN
			case CAEN_READER :
				if (briWrapper == null) {
					CAENWrapper = new CAENReaderDevice(readerName);
				}
				reader = CAENWrapper;
				break;
			default :
				reader = null;	
		}
		
		return reader;
	}
	
	/**
	 * Gets the current reader device.
	 * 
	 * @return the current reader device.
	 */
	public static ReaderDevice getCurrentReader() {
		return currentReader;
	}
	
	/**
	 * Establishes the connection with a {@link Connectable} reader using its default address.
	 * @param reader the {@link Connectable} reader.
	 * @param address connection address.
	 * 
	 * @throws RPException in case of connection error.
	 */
	public static void connectReader(ReaderDevice reader, String address) throws RPException {
		if ((reader == briWrapper) || (reader == CAENWrapper)) {
			if (reader instanceof Connectable) {
				((Connectable)reader).connect(address);
				currentReader = reader;
			}
			else 
				throw new IllegalArgumentException("Factory Error : The given reader is does not implement Connectable : " + reader);
		}
		else
			throw new IllegalArgumentException("Unknown reader singleton " + reader);
	}
	
	/**
	 * Establishes the connection with a {@link Connectable} reader using its default address.
	 * @param reader the {@link Connectable} reader.
	 * 
	 * @throws RPException in case of connection error.
	 */
	public static void connectReader(ReaderDevice reader) throws RPException {
		Connectable myReader;
		if ((reader == briWrapper) || (reader == CAENWrapper)) {
			if (reader instanceof Connectable) {
				myReader = (Connectable) reader;
				myReader.connect(myReader.getDefaultAddress());
				currentReader = reader;
			}
			else 
				throw new IllegalArgumentException("Factory Error : The given reader is does not implement Connectable : " + reader);
		}
		else
			throw new IllegalArgumentException("Unknown reader singleton " + reader);
	}
	
	/**
	 * Disconnect a {@link Connectable} reader.
	 * @param reader
	 * @throws RPException in case of disconnection error.
	 */
	public static void disconnectReader(ReaderDevice reader) throws RPException {
		if ((reader == briWrapper) || (reader == CAENWrapper)) {
			if (reader instanceof Connectable)
				((Connectable)reader).discconnect();
			else 
				throw new IllegalArgumentException("Factory Error : The given reader is does not implement Connectable : " + reader);
		}
		else
			throw new IllegalArgumentException("Unknown reader singleton " + reader);
	}
	
	/**
	 * Configures a reader device.
	 * @param reader the reader device that shall implement the {@link Configurable} interface.
	 * @param attributeIndex index in {@link ConfigAttributes} class.
	 * @param Value an Integer or PowerValue if power attribute.
	 * 
	 * @throws RPException in case of error during configuration process.
	 * @throws NumberFormatException if value parameter is not coherent with attribute.
	 */
	public static void configReader(ReaderDevice reader, int attributeIndex, Object value) throws NumberFormatException, RPException {
		Configurable theReader;
		Integer iVal = null;
		PowerValue pVal = null;
		
		
		if ((reader == briWrapper) || (reader == CAENWrapper)) {
			if (reader instanceof Configurable) {
				theReader = (Configurable)reader;
				
				if (value instanceof Integer) {
					if (attributeIndex != ConfigAttributes.POWER)
						iVal = (Integer)value ;
					else
						throw new IllegalArgumentException("Incompatible attributeIndex and value : value must instance of" + PowerValue.class);
				}
				else if (value instanceof PowerValue) {
					if (attributeIndex == ConfigAttributes.POWER)
						pVal = (PowerValue)value;
					else
						throw new IllegalArgumentException("Incompatible attributeIndex and value : index must be  equal to" + ConfigAttributes.POWER);
				}
				else
					throw new IllegalArgumentException("Invalid value object must be Integer or PowerValue object instead of " + value.getClass());
				
				// set attribute
				switch(attributeIndex) {
					case ConfigAttributes.POWER :
						theReader.setPower(pVal.powerIndex, pVal.mapping);
						break;
					
					case ConfigAttributes.INITIAL_Q :
						theReader.setInitialQ(iVal.intValue());
						break;
						
					case ConfigAttributes.SESSION :
						theReader.setSession(iVal.intValue());
						break;
						
					default :
						throw new IllegalArgumentException("Invalid attribute : " + attributeIndex);
				}
			}
			else 
				throw new IllegalArgumentException("Factory Error : The given reader is does not implement Configurable : " + reader);
		}
		else
			throw new IllegalArgumentException("Unknown reader singleton " + reader);		
	}
	
	/**
	 * Power Value class definition for Power attribute.
	 * @author rdagher
	 *
	 */
	public final static class PowerValue {
		private int powerIndex;
		private int[] mapping;
		
		/**
		 * Constructor
		 * @param powerIndex
		 * @param mapping
		 */
		public PowerValue(int powerIndex, int[] mapping) {
			this.powerIndex = powerIndex;
			this.mapping = mapping;
		}

		/**
		 * Getter for power
		 * @return the power
		 */
		public int getPower() {
			return powerIndex;
		}

		/**
		 * Getter for mapping
		 * @return the mapping
		 */
		public int[] getMapping() {
			return mapping;
		}

		/**
		 * Setter for power
		 * @param power the value to set
		 */
		public void setPower(int power) {
			this.powerIndex = power;
		}

		/**
		 * Setter for mapping
		 * @param mapping the value to set
		 */
		public void setMapping(int[] mapping) {
			this.mapping = mapping;
		}		
	}
	
}

