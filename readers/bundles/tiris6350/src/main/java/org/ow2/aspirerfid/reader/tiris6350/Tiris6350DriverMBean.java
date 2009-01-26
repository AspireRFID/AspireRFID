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
package org.ow2.aspirerfid.reader.tiris6350;

import org.ow2.aspirerfid.reader.RfidDriverMBean;

/**
 * TODO Javadoc
 * @author Unknown
 * @version 2006
 */
public interface Tiris6350DriverMBean extends RfidDriverMBean {

	/**
	 * @return Returns the baudRate.
	 */
	public int getBaudRate();

	/**
	 * @param baudRate The baudRate to set.
	 */
	public void setBaudRate(int baudRate);

	/**
	 * @return Returns the dataBits.
	 */
	public int getDataBits();

	/**
	 * @param dataBits The dataBits to set.
	 */
	public void setDataBits(int dataBits);

	/**
	 * @return Returns the flowControlIn.
	 */
	public String getFlowControlIn();

	/**
	 * @param flowControlIn The flowControlIn to set.
	 */
	public void setFlowControlIn(String flowControlIn);

	/**
	 * @return Returns the flowControlOut.
	 */
	public String getFlowControlOut();

	/**
	 * @param flowControlOut The flowControlOut to set.
	 */
	public void setFlowControlOut(String flowControlOut);

	/**
	 * @return Returns the parity.
	 */
	public String getParity();

	/**
	 * @param parity The parity to set.
	 */
	public void setParity(String parity);

	/**
	 * @return Returns the portName.
	 */
	public String getPortName();

	/**
	 * @param portName The portName to set.
	 */
	public void setPortName(String portName);

	/**
	 * @return Returns the stopBits.
	 */
	public int getStopBits();

	/**
	 * @param stopBits The stopBits to set.
	 */
	public void setStopBits(int stopBits);

}
