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

package org.ow2.aspirerfid.beg.mbean;

/**
 * provides the interface of the MBean to add/remove master data
 * @author Didier Donsez, Kiev Gama, Gabriel Pedraza-Ferreira
 */

public interface EPCISMBean {

	/**
	 * the MBean object name
	 */
	public static final String OBJECTNAME = Common.OBJECTNAME_PREFIX+",name=epcis";

	public void addMasterData(String masterData) throws Exception;
	public void removeMasterData(String masterDataId) throws Exception;
	public String[] getmasterDataIds() throws Exception;
	
}
