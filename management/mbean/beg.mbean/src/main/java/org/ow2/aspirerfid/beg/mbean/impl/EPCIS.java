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
package org.ow2.aspirerfid.beg.mbean.impl;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.management.ObjectName;

import org.ow2.aspirerfid.beg.mbean.EPCISMBean;
import org.ow2.aspirerfid.util.mbean.MBeanComponent;

/**
 * provides the implementation of the MBean to start/stop business events
 * @author Didier Donsez, Kiev Gama, Gabriel Pedraza-Ferreira
 */
public class EPCIS implements EPCISMBean, MBeanComponent {
	private ObjectName name = null;

	public EPCIS(Logger logger) {
		try {
			name = new ObjectName(OBJECTNAME);
		} catch (Exception e) {
			logger.log(Level.SEVERE, "Could not instanciate ObjectName "
					+ OBJECTNAME, e);
		}
	}

	public void addMasterData(String masterData) throws Exception {
		// TODO Auto-generated method stub
		
	}

	public String[] getmasterDataIds() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	public void removeMasterData(String masterDataId) throws Exception {
		// TODO Auto-generated method stub
		
	}

	public ObjectName getObjectName() {
		return name;
	}

	public void setObjectName(ObjectName objectName) {
		name = objectName;
	}

	public void start() {
		// TODO Auto-generated method stub
		
	}

	public void stop() {
		// TODO Auto-generated method stub
		
	}

	
}
