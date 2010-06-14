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

import org.ow2.aspirerfid.ale.epc.exceptions.ECSpecValidationException;
import org.ow2.aspirerfid.ale.epc.exceptions.ImplementationException;
import org.ow2.aspirerfid.ale.epc.reports.ECReports;


/**
 * ALE reading API specification.
 * 
 * @author pops
 * @author R.DAGHER
 */
public interface ALE {
	
	void define(String specName, ECSpec spec);
	
	void undefine(String specName);
	
	ECSpec getECSpec(String specName) ; 
	
	String[] getECSpecNames();
	
	void subscribe(String specName, String notificationURI);
	
	void unsubscribe(String specName, String notificationURI);
	
	ECReports poll(String specName); 
	
	ECReports immediate(ECSpec spec) throws ECSpecValidationException, ImplementationException; 
	
	String[] getSubscribers(String specName);
	
	String getStandardVersion();
	
	String getVendorVersion();
	
	//
	// <<extension point>>
	//	---	
	
}
