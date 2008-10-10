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
 * configuration interface of a Rfid reader. This interface will be used with
 * JMX
 * 
 * @author Anne Robert
 * @version 2006
 */
public interface RfidReaderMBean {

	/**
	 * (re)define the logical name of a RFID reader
	 * 
	 * @param logName
	 *            the new logical name
	 */
	public void setLogicalName(String logName);

	/**
	 * Get the defined logical name of RFID reader
	 * 
	 * @return the reader logical name
	 */
	public String getLogicalName();

	/**
	 * (re)define the read period
	 * 
	 * @param period
	 *            the read period in milliseconds
	 */
	public void setRepeatPeriod(int period);

	/**
	 * get the read period
	 * 
	 * @return the read period in milliseconds
	 */
	public int getRepeatPeriod();

	/**
	 * get the reader Guid
	 * 
	 * @return the reader guid
	 */
	public String getReaderGUId();

	/**
	 * Initialize the reader. The init function call is mandatory to call other
	 * operations
	 * 
	 */
	public void startReader();

	/**
	 * Stop the reader and the periodical task which periodically read
	 */
	public void stopReader();

	/**
	 * Stops the reader and unregisters the JMX MBean
	 */
	public void dispose();
}
