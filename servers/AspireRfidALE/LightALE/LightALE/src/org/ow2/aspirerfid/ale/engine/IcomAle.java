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

package org.ow2.aspirerfid.ale.engine;

import org.ow2.aspirerfid.ale.engine.com.IhmCom;
import org.ow2.aspirerfid.ale.engine.input.RPChannel;
import org.ow2.aspirerfid.ale.engine.lr.IcomLRFactory;
import org.ow2.aspirerfid.ale.epc.exceptions.ECSpecValidationException;
import org.ow2.aspirerfid.ale.epc.exceptions.ImplementationException;
import org.ow2.aspirerfid.ale.epc.lr.ALELR;
import org.ow2.aspirerfid.ale.epc.lr.LRProperty;
import org.ow2.aspirerfid.ale.epc.lr.LRSpec;
import org.ow2.aspirerfid.ale.epc.reports.ECReports;
import org.ow2.aspirerfid.ale.epc.spec.ALE;
import org.ow2.aspirerfid.ale.epc.spec.ECReportSetSpec;
import org.ow2.aspirerfid.ale.epc.spec.ECReportSpec;
import org.ow2.aspirerfid.ale.epc.spec.ECSpec;


/**
 * Icom Implementation for the Decathlon demonstrator.
 * <br>
 * Implementation notes :
 * <ul>
 * 	<li>
 * 	Only the immediate mode is supported.
 * 	</li>
 * 	<li>
 * 	Regarding the {@link ECSpec} parameter of {@link IcomAle#immediate(ECSpec)}, 
 *   <br>Only one {@link ECReportSpec} is supported with {@link ECReportSetSpec} set to {@link ECReportSetSpec#CURRENT}.
 * 	</li>
 * 	<li>
 * 	The ALE is linked to two communication channel : ({@link RPChannel}) and IHM observer ({@link IhmCom})
 *  </li>
 * </ul>
 * 
 * @author rdagher
 *
 */
public class IcomAle implements ALE, ALELR {
	
	/**
	 * ALE engine containing core functions.
	 */
	private IcomAleEngine aleEngine;
	
	/**
	 * Communication Channel with a connected RP component.
	 */
	private RPChannel RpLink;
	
	/**
	 * IHM aggregation and communication component.
	 */
	private IhmCom ihmLink;
	
	/**
	 * Specification version.
	 */
	public final static String specVersion = "1.1";
	
	/**
	 * Vendor Version. The pops team.
	 */
	public final static String vendorVersion = "http://www2.lifl.fr/POPS/Eng/Index";
			
	/**
	 * Constructor.
	 * TODO Handle interface (RP and IHM) management
	 */
	public IcomAle(RPChannel RpLink, IhmCom ihmLink) {
		this.RpLink = RpLink;
		this.ihmLink = ihmLink;
		
		// Register RPLink as a property setter
		IcomLRFactory.setPropSetter(RpLink.getReaderName(), RpLink);
		
		// get engine singleton
		this.aleEngine = IcomAleEngine.getEngine();
		this.aleEngine.init(ihmLink, RpLink);
	}
	
	/*------------------------------------------------------------
	 *  Common methods for ALE READ and ALE LR APIs.
	 *------------------------------------------------------------
	 */
	public String getStandardVersion() {
		return IcomAle.specVersion;
	}
	
	public String getVendorVersion() {
		return IcomAle.vendorVersion;
	}
	
	/*------------------------------------------------------------
	 *  ALE READ API implementation.
	 *------------------------------------------------------------
	 */
	
	public ECReports immediate(ECSpec spec) throws ECSpecValidationException, ImplementationException {
		
		// Validate Spec : TODO
		if (!SpecValidationEngine.validateSpec(spec))
			throw new ECSpecValidationException();
		
		// Call handler from engine : blocking call
		try {
			return aleEngine.handleECSpec(spec, this.RpLink, this.ihmLink);
		}
		catch (Exception e) {
			throw new ImplementationException(e);
		}
	}

	/**
	 * Not implemented method.
	 */
	public void define(String specName, ECSpec spec) {
		throw new UnsupportedOperationException("Icom, decathlon demonstrator");
		
	}
	
	/**
	 * Not implemented method.
	 */
	public ECSpec getECSpec(String specName) {
		throw new UnsupportedOperationException("Icom, decathlon demonstrator");
	}

	/**
	 * Not implemented method.
	 */
	public String[] getECSpecNames() {
		throw new UnsupportedOperationException("Icom, decathlon demonstrator");
	}
	
	/**
	 * Not implemented method.
	 */
	public String[] getSubscribers(String specName) {
		throw new UnsupportedOperationException("Icom, decathlon demonstrator");
	}

	/**
	 * Not implemented method.
	 */
	public ECReports poll(String specName) {
		throw new UnsupportedOperationException("Icom, decathlon demonstrator");
	}

	/**
	 * Not implemented method.
	 */
	public void subscribe(String specName, String notificationURI) {
		throw new UnsupportedOperationException("Icom, decathlon demonstrator");
	}

	/**
	 * Not implemented method.
	 */
	public void undefine(String specName) {
		throw new UnsupportedOperationException("Icom, decathlon demonstrator");		
	}

	/**
	 * Not implemented method.
	 */
	public void unsubscribe(String specName, String notificationURI) {
		throw new UnsupportedOperationException("Icom, decathlon demonstrator");		
	}

	/*------------------------------------------------------------
	 *  ALE LR API implementation.
	 *------------------------------------------------------------
	 */
	
	/**
	 * Returns an LRSpec that describes the logical reader named name.
	 * @param name the Logical reader's name.
	 * @return the {@link LRSpec} of the reader.
	 */
	public LRSpec getLRSpec(String name) {
		return IcomLRFactory.getLRSpec(name);
	}

	/**
	 * Returns an unordered list of the names of all logical readers that are visible 
	 * to the caller.
	 * 
	 * @return an array of the logical readers names.
	 */
	public String[] getLogicalReaderNames() {
		return IcomLRFactory.READER_NAMES;
	}
	
	/**
	 * Returns the current value of the specified property for the specified reader, 
	 * or null if the specified reader does not have a property with the specified name.
	 *
	 * @param name Reader's name.
	 * @param propertyName property name.
	 * @return The property value.
	 */
	public String getPropertyValue(String name, String propertyName) {
		LRProperty[] props = IcomLRFactory.getLRSpec(name).getProperties();
		boolean found = false;
		String value = null;
		
		for (int i = 0 ; ((i < props.length) && !found); i++) {
			if (propertyName.equals(props[i].getName())) {
				found = true;
				value = props[i].getValue();
			}
		}
		
		return value;
	}

	/**
	 * Changes properties for the logical reader named name to the specified list. 
	 * This is equivalent to calling {@link IcomAle#getLRSpec getLRSpec }, modifying the 
	 * properties in the {@link LRSpec} according to the table below, and then calling update with 
	 * the modified {@link LRSpec} .
	 * 
	 * @param name Logical Reader Name.
	 * @param properties
	 */
	public void setProperties(String name, LRProperty[] properties) {
		LRSpec spec ;
		LRProperty[] props ;
		
		// get spec
		spec = IcomLRFactory.getLRSpec(name);
		
		// set properties
		props = spec.getProperties();
		for (int i = 0 ; i < props.length; i++) {
			props[i].setName(properties[i].getName());
			props[i].setValue(properties[i].getValue());
		}
		
		// update
		IcomLRFactory.updateLRSpec(name, spec);
	}

	/**
	 * Changes the definition of the logical reader named name to match the specification 
	 * in the spec parameter.
	 * 
	 * @param name Logical reader name.
	 * @param spec LRSpec object.
	 */
	public void update(String name, LRSpec spec) {
		IcomLRFactory.updateLRSpec(name, spec);
	}
	
	// Unimplemented methods //
	
	/**
	 * Not implemented method.
	 */
	public void removeReaders(String name, String[] readers) {
		throw new UnsupportedOperationException("Icom, decathlon demonstrator");		
	}
	
	/**
	 * Not implemented method.
	 */
	public void setReaders(String name, String[] readers) {
		throw new UnsupportedOperationException("Icom, decathlon demonstrator");		
	}
	
	/**
	 * Not implemented method.
	 */
	public void addReaders(String name, String[] readers) {
		throw new UnsupportedOperationException("Icom, decathlon demonstrator");		
	}

	/**
	 * Not implemented method.
	 */
	public void define(String name, LRSpec spec) {
		throw new UnsupportedOperationException("Icom, decathlon demonstrator");
	}
}
