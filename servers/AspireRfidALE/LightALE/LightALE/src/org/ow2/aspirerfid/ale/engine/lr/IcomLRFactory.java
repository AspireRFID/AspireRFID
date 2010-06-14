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

package org.ow2.aspirerfid.ale.engine.lr;

import org.ow2.aspirerfid.ale.epc.lr.LRProperty;
import org.ow2.aspirerfid.ale.epc.lr.LRSpec;


/**
 * Logical Reader Properties and LRSpec definitions for the Decathlon demonstrator.
 * This implementation defines two Externally-defined Base Readers :
 *  <ul>
 *  	<li> CAEN reader :  used on the PSION PDA</li>
 *  	<li> Intermec reader : IP30 reader used on the CN3 PDA </li>
 *  </ul>
 *  For each reader, an LRSpec is already defined with default values, and may be updated.
 * @author rdagher.
 *
 */
public class IcomLRFactory {
	
	/**
	 * Physical Readers used on the demonstrator. 
	 * CAEN or Intermec.
	 */	
	public final static String[] READER_NAMES = {"CAENReaderDevice", "IntermecReaderDevice", "Simulator"};

	/**
	 * The list of configurable properties.
	 */
	public final static String[] DECATHLON_PROP_NAMES = {"NameofPeripheral", "Power", "InitialQ", "Session"};
	
	/**
	 * CAEN LR Spec name.
	 */
	public final static String CaenLRSpecName = "CAEN_LRSpec";
	
	/**
	 * Intermec LR Spec name.
	 */
	public final static String IntermecLRSpecName = "Intermec_LRSpec";
	
	/**
	 * Simulator LR Spec name.
	 */
	public final static String SimulatorLRSpecName = "Simulator_LRSpec";

	/**
	 * CAEN power mapping.
	 */
	private static final int[] CAEN_POWER_MAPPING = {0 /* padding*/ , 50 /*mw*/, 150 /*mw*/, 200 /*mw*/, 500/*mw*/, 500/*mw*/};
	
	/**
	 * Intermec Power Mapping.
	 */
	private static final int[] INTERMEC_POWER_MAPPING = {0 /* padding*/ , 35/* 50 mw*/, 60/*150 mw*/, 65/*200 mw*/, 85/*500mw*/, 100/*1W*/};
	
	/**
	 * Setters to be notified on each update of LRSpec.
	 * CAEN is at first position, Intermec is at second.
	 */
	private final static IcomLRPropSetter[] propSetters = {null /*CAEN*/, null /*Intermec*/, null /* Simulator*/};
	
	/**
	 * Set the properties setter to update the reader.
	 * 
	 * @param readerName the reader to be updated.
	 * @param propSetter the setter handling the reader's update.
	 * 
	 * @throws IllegalArgumentException if reader name is invalid.
	 */
	public static void setPropSetter(String readerName, IcomLRPropSetter propSetter) {
		if (READER_NAMES[0].equals(readerName)) {
			// Set Caen reader properties setter
			propSetters[0] = propSetter;
		} else if (READER_NAMES[1].equals(readerName)) {
			// Set Intermec reader properties setter
			propSetters[1] = propSetter;
		} else if (READER_NAMES[2].equals(readerName)) {
			// Set Simulator reader properties setter
			propSetters[2] = propSetter;
		} else
			throw new IllegalArgumentException("Invalid Reader Name : " + readerName);
	}
	
	/**
	 * USet the properties setter to update the reader.
	 * @param readerName the concerned reader.
	 * 
	 * @throws IllegalArgumentException if reader name is invalid.
	 */
	public static void removePropSetter(String readerName) {		
		if (READER_NAMES[0].equals(readerName)) {
			// Unset Caen reader properties setter
			propSetters[0] = null;
		} else if (READER_NAMES[1].equals(readerName)) {
			// Unset Intermec reader properties setter
			propSetters[1] = null;
		} else if (READER_NAMES[2].equals(readerName)) {
			// Unset Simulator reader properties setter
			propSetters[2] = null;
		} else
			throw new IllegalArgumentException("Invalid Reader Name : " + readerName);
	}
	
	
	/**
	 * Gives the LRSpec instance by its name. 
	 * @param specName Logical reader name, see {@link IcomLRFactory#READER_NAMES READER_NAMES}.
	 * @return the corresponding LR Spec instance.
	 */
	public static LRSpec getLRSpec(String specName) {
		LRSpec spec;
		
		if (READER_NAMES[0].equals(specName))
			spec = CaenLRSpec;
		else if (READER_NAMES[1].equals(specName))
			spec = IntermecLRSpec;
		else if (READER_NAMES[2].equals(specName))
			spec = SimulatorLRSpec;
		else	
			throw new IllegalArgumentException("Invalid LRSpec name " + specName);
		
		return spec;
	}
		
	/**
	 * Updates an LRSpec and notifies a setter to update reader's properties.
	 * @param readerName the name of the reader to update.
	 * @param spec LRspec instance containing data.
	 * @param props new updated properties.
	 * @param setter setter to be notified to update property.
	 * 
	 * @throws IllegalArgumentException if reader name is invalid.
	 */
	public static void updateLRSpec(String readerName, LRSpec spec) {
		if (READER_NAMES[0].equals(readerName)) {
			// Update Caen reader spec
			updateLRSpec(CaenLRSpec, spec.getProperties(), propSetters[0]);
		} else if (READER_NAMES[1].equals(readerName)) {
			// Update Intermec reader spec
			updateLRSpec(IntermecLRSpec, spec.getProperties(), propSetters[1]);
		} else if (READER_NAMES[2].equals(readerName)) {
			// Update Simulator reader spec
			updateLRSpec(SimulatorLRSpec, spec.getProperties(), propSetters[2]);
		} else
			throw new IllegalArgumentException("Invalid Reader Name");
	}
	
	/**
	 * Updates an LRSpec and notifies a setter to update reader's properties.
	 * @param spec LRspec instance.
	 * @param props new updated properties.
	 * @param setter setter to be notified to update property.
	 */
	private static void updateLRSpec(LRSpec spec, LRProperty[] props, IcomLRPropSetter setter) {
		String newValue;
			
		// Ignore reader name change
		
		// Power		
		newValue = props[1].getValue();
		if (spec == CaenLRSpec) {
			// CAEN Reader power setup
			if (setter != null)
				setter.setPower(Integer.parseInt(newValue)/*power*/, CAEN_POWER_MAPPING /* power mapping */);
		}
		else if (spec == IntermecLRSpec) {
			// Intermec Reader power setup
			if (setter != null)
				setter.setPower(Integer.parseInt(newValue)/*power*/, INTERMEC_POWER_MAPPING /* power mapping */);
		}
		else if (spec == SimulatorLRSpec){
			// Simulator Reader power setup
			if (setter != null)
				setter.setPower(Integer.parseInt(newValue)/*power*/, INTERMEC_POWER_MAPPING /* power mapping */);
		}
		
		// InitialQ
		newValue = props[2].getValue();
		if (setter != null)
			setter.setInitialQ(Integer.parseInt(newValue)/*initialQ*/);
		
		// Session
		newValue = props[3].getValue();
		if (setter != null)
			setter.setSession(Integer.parseInt(newValue)/*session*/);
	}
	
//	/**
//	 * Updates an LRSpec and notifies a setter to update reader's properties.
//	 * @param spec LRspec instance.
//	 * @param props new updated properties.
//	 * @param setter setter to be notified to update property.
//	 */
//	private static void updateLRSpec(LRSpec spec, LRProperty[] props, IcomLRPropSetter setter) {
//		LRProperty[] current = spec.getProperties();
//		String oldValue, newValue;
//			
//		// Ignore reader name change
//		
//		// Power
//		oldValue = current[1].getValue();
//		newValue = props[1].getValue();
//		if (!oldValue.equals(newValue)) {
//			if (spec == CaenLRSpec) {
//				// CAEN Reader power setup
//				current[1].setValue(newValue);
//				if (setter != null)
//					setter.setPower(Integer.parseInt(newValue)/*power*/, CAEN_POWER_MAPPING /* power mapping */);
//			}
//			else if (spec == IntermecLRSpec) {
//				// Intermec Reader power setup
//				current[1].setValue(newValue);
//				if (setter != null)
//					setter.setPower(Integer.parseInt(newValue)/*power*/, INTERMEC_POWER_MAPPING /* power mapping */);
//			}
//			else if (spec == SimulatorLRSpec){
//				// Simulator Reader power setup
//				current[1].setValue(newValue);
//				if (setter != null)
//					setter.setPower(Integer.parseInt(newValue)/*power*/, INTERMEC_POWER_MAPPING /* power mapping */);
//			}
//		}
//		
//		// InitialQ
//		oldValue = current[2].getValue();
//		newValue = props[2].getValue();
//		if (!oldValue.equals(newValue)) {
//			current[2].setValue(newValue);
//			if (setter != null)
//				setter.setInitialQ(Integer.parseInt(newValue)/*initialQ*/);
//		}
//		
//		// Session
//		oldValue = current[3].getValue();
//		newValue = props[3].getValue();
//		if (!oldValue.equals(newValue)) {
//			current[3].setValue(newValue);
//			if (setter != null)
//				setter.setSession(Integer.parseInt(newValue)/*session*/);
//		}
//	}
	
	/**
	 * CAEN Logical readers. Only one.
	 */
	private final static String[] CAEN_LOGICAL_READERS = {READER_NAMES[0]};
	
	/**
	 * Intermec Logical readers. Only one.
	 */
	private final static String[] INTERMEC_LOGICAL_READERS = {READER_NAMES[1]};
	
	/**
	 * Simulator Logical readers. Only one.
	 */
	private final static String[] SIMULATOR_LOGICAL_READERS = {READER_NAMES[2]};

	/**
	 * Default CAEN Reader Properties. Properties may be updated individually.
	 */
	private final static LRProperty[] DECATHLON_CAEN_LR_PROPS = {
		new LRProperty(DECATHLON_PROP_NAMES[0], READER_NAMES[0]),	// Reader Name
		new LRProperty(DECATHLON_PROP_NAMES[1], "4"), 				// 500mw max Power
		new LRProperty(DECATHLON_PROP_NAMES[2], "4"), 				// Initial Q
		new LRProperty(DECATHLON_PROP_NAMES[3], "2")  				// Session	
	};
	
	/**
	 * Default Intermec Reader Properties. Properties may be updated individually.
	 */
	private final static LRProperty[] DECATHLON_INTERMEC_LR_PROPS = {
		new LRProperty(DECATHLON_PROP_NAMES[0], READER_NAMES[1]),	// Reader Name
		new LRProperty(DECATHLON_PROP_NAMES[1], "4"), 				// 1 W max Power
		new LRProperty(DECATHLON_PROP_NAMES[2], "4"), 				// Initial Q
		new LRProperty(DECATHLON_PROP_NAMES[3], "2")  				// Session	
	};
	
	/**
	 * Default Intermec Reader Properties. Properties may be updated individually.
	 */
	private final static LRProperty[] SIMULATOR_LR_PROPS = {
		new LRProperty(DECATHLON_PROP_NAMES[0], READER_NAMES[2]),	// Reader Name
		new LRProperty(DECATHLON_PROP_NAMES[1], "4"), 				// 1 W max Power
		new LRProperty(DECATHLON_PROP_NAMES[2], "4"), 				// Initial Q
		new LRProperty(DECATHLON_PROP_NAMES[3], "2")  				// Session	
	};
	
	
	/**
	 * CAEN Reader definition.
	 */
	private final static LRSpec CaenLRSpec = new LRSpec(false /*isComposite*/, 
														CAEN_LOGICAL_READERS,
														DECATHLON_CAEN_LR_PROPS);
	
	/**
	 * Intermec Reader definition.
	 */
	private final static LRSpec IntermecLRSpec = new LRSpec(false /*isComposite*/, 
															INTERMEC_LOGICAL_READERS, 
															DECATHLON_INTERMEC_LR_PROPS);
	/**
	 * Simulator Reader definition.
	 */
	private final static LRSpec SimulatorLRSpec = new LRSpec(false /*isComposite*/, 
															SIMULATOR_LOGICAL_READERS, 
															SIMULATOR_LR_PROPS);
}
