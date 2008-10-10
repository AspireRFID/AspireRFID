/*
 * Copyright 2005-2008, Aspire
 * 
 * This library is free software; you can redistribute it and/or modify it 
 * under the terms of the GNU Lesser General Public License as published by 
 * the Free Software Foundation (the "LGPL"); either version 2.1 of the 
 * License, or (at your option) any later version. If you do not alter this 
 * notice, a recipient may use your version of this file under either the 
 * LGPL version 2.1, or (at his option) any later version.
 * 
 * You should have received a copy of the GNU Lesser General Public License 
 * along with this library; if not, write to the Free Software Foundation, 
 * Inc., 51 Franklin Street, Fifth Floor, Boston, MA 02110-1301 USA.
 * 
 * This software is distributed on an "AS IS" basis, WITHOUT WARRANTY OF ANY 
 * KIND, either express or implied. See the GNU Lesser General Public 
 * License for the specific language governing rights and limitations.
 */
package org.ow2.aspirerfid.reader;

/**
 * Interface of a RFID Driver. Each driver may control many readers. When the
 * driver service is active, readers can be installed with the newReader
 * function.
 * 
 * @author Anne Robert
 * @version 2006
 */
public interface RfidDriverMBean {

	/**
	 * add a reader controlled by the driver. The parameters must have be
	 * specified by call to setParameter function before this call
	 */
	public void newReader();

	/**
	 * @return Returns the logicalName.
	 */
	public String getLogicalName();

	/**
	 * @param logicalName
	 *            The logicalName to set.
	 */
	public void setLogicalName(String logicalName);

	/**
	 * @return Returns the period.
	 */
	public int getRepeatPeriod();

	/**
	 * @param period
	 *            The period to set.
	 */
	public void setRepeatPeriod(int period);

	/**
	 * @return Returns the readerId.
	 */
	public String getReaderGUId();

	/**
	 * @param readerId
	 *            The readerId to set.
	 */
	public void setReaderGUId(String readerId);

}
