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
import org.ow2.aspirerfid.rp.api.NotificationChannel;
import org.ow2.aspirerfid.rp.api.RPException;
import org.ow2.aspirerfid.rp.api.ReadPoint;
import org.ow2.aspirerfid.rp.api.ReaderDevice;
import org.ow2.aspirerfid.rp.api.Source;
import org.ow2.aspirerfid.rp.api.TagField;
import org.ow2.aspirerfid.rp.api.TagSelector;
import org.ow2.aspirerfid.rp.api.Trigger;
import org.ow2.aspirerfid.rp.api.enumerations.TriggerType;
import org.ow2.aspirerfid.rp.api.factories.Manageable;
import org.ow2.aspirerfid.rp.imp.factory.Configurable;
import org.ow2.aspirerfid.rp.imp.factory.Connectable;

import gnu.javax.comm.wce.WCECommDriver;

import com.caen.RFIDLibrary.CAENRFIDException;
import com.caen.RFIDLibrary.CAENRFIDPort;
import com.caen.RFIDLibrary.CAENRFIDProtocol;
import com.caen.RFIDLibrary.CAENRFIDReader;

public class CAENReaderDevice implements ReaderDevice, Connectable, Configurable, Manageable {
	
	/**
	 * Basic Caen Reader instance wrapper. 
	 */
	protected CAENRFIDReader myReader;
	
	private String name = "CAENReaderDevice";
	private String role = "MobileReader";
	
	private CAENSource currentSource;
	private DataSelector currentDataSelector;

	private long instanceTime;
	
	
	/**
	 * Constructor
	 */
	public CAENReaderDevice(String name) {
		// CAEN wrapper
		myReader = new CAENRFIDReader();
		this.name = name;
		this.role = "MobileReader";
		
		// Time the instance was created
		instanceTime = System.currentTimeMillis();
		// Default supported data selector
		currentDataSelector = new CAENDataSelector("ReaderProtocol:Default");
		currentSource = new CAENSource("Unique Source", this.myReader);
		
		// Laod Javaxcom library for communicating with device
		System.loadLibrary("javaxcomm");
	}


	public DataSelector[] getAllDataSelectors() throws RPException {
		throw RPException.NOT_SUPPORTED_OPERATION;
	}


	public NotificationChannel[] getAllNotificationChannels()
			throws RPException {
		throw RPException.NOT_SUPPORTED_OPERATION;
	}


	public ReadPoint[] getAllReadPoints() throws RPException {
		throw RPException.NOT_SUPPORTED_OPERATION;
	}


	public Source[] getAllSources() throws RPException {
		throw RPException.NOT_SUPPORTED_OPERATION;
	}


	public TagField[] getAllTagFields() throws RPException {
		throw RPException.NOT_SUPPORTED_OPERATION;
	}


	public TagSelector[] getAllTagSelectors() throws RPException {
		throw RPException.NOT_SUPPORTED_OPERATION;
	}


	public Trigger[] getAllTriggers() throws RPException {
		throw RPException.NOT_SUPPORTED_OPERATION;
	}


	public DataSelector getCurrentDataSelector() throws RPException {
		return this.currentDataSelector;
	}


	public Source getCurrentSource() throws RPException {
		return this.currentSource;
	}


	public DataSelector getDataSelector(String name) throws RPException {
		throw RPException.NOT_SUPPORTED_OPERATION;
	}


	public String getEPC() throws RPException {
		return "";
	}


	public int getHandle() throws RPException {
		throw RPException.NOT_SUPPORTED_OPERATION;
	}


	public String getManufacturer() throws RPException {
		throw RPException.NOT_SUPPORTED_OPERATION;
	}


	public String getManufacturerDescription() throws RPException {
		throw RPException.NOT_SUPPORTED_OPERATION;
	}


	public String getModel() throws RPException {
		throw RPException.NOT_SUPPORTED_OPERATION;
	}


	public String getName() throws RPException {
		return this.name;
	}


	public NotificationChannel getNotificationChannel(String name)
			throws RPException {
		throw RPException.NOT_SUPPORTED_OPERATION;
	}


	public ReadPoint getReadPoint(String name) throws RPException {
		throw RPException.NOT_SUPPORTED_OPERATION;
	}


	public String getRole() throws RPException {
		return this.role;
	}


	public Source getSource(String name) throws RPException {
		throw RPException.NOT_SUPPORTED_OPERATION;
	}


	public TagField getTagField(String name) throws RPException {
		throw RPException.NOT_SUPPORTED_OPERATION;
	}


	public TagSelector getTagSelector(String name) throws RPException {
		throw RPException.NOT_SUPPORTED_OPERATION;
	}


	public int getTimeTicks() throws RPException {
		// Reader does not support this function, emulate it		
		return (int) (System.currentTimeMillis() - instanceTime);
	}


	public String getTimeUTC() throws RPException {
		throw RPException.NOT_SUPPORTED_OPERATION;
	}


	public Trigger getTrigger(String name) throws RPException {
		throw RPException.NOT_SUPPORTED_OPERATION;
	}


	public void goodbye() throws RPException {
		try {
			// disconnect
			this.myReader.Disconnect();
		} catch (CAENRFIDException e) {
			throw new RPException(e);
		} finally {
			// power down CAEN reader device
			WCECommDriver.powerDownCaenReader();
		}
	}


	public void reboot() throws RPException {
		// reser connection
		goodbye();
		connect(DEFAULT_COM_ADDR);
	}


	public void removeAllDataSelectors() throws RPException {
		throw RPException.NOT_SUPPORTED_OPERATION;
		
	}


	public void removeAllNotificationChannels() throws RPException {
		throw RPException.NOT_SUPPORTED_OPERATION;
		
	}


	public void removeAllSources() throws RPException {
		throw RPException.NOT_SUPPORTED_OPERATION;
		
	}


	public void removeAllTagFields() throws RPException {
		throw RPException.NOT_SUPPORTED_OPERATION;
		
	}


	public void removeAllTagSelectors() throws RPException {
		throw RPException.NOT_SUPPORTED_OPERATION;
		
	}


	public void removeAllTriggers() throws RPException {
		throw RPException.NOT_SUPPORTED_OPERATION;
		
	}


	public void removeDataSelectors(DataSelector[] dataSelectors)
			throws RPException {
		throw RPException.NOT_SUPPORTED_OPERATION;
		
	}


	public void removeNotificationChannels(
			NotificationChannel[] notificationChannels) throws RPException {
		throw RPException.NOT_SUPPORTED_OPERATION;
		
	}


	public void removeSources(Source[] sources) throws RPException {
		throw RPException.NOT_SUPPORTED_OPERATION;
		
	}


	public void removeTagFields(TagField[] tagFields) throws RPException {
		throw RPException.NOT_SUPPORTED_OPERATION;
		
	}


	public void removeTagSelectors(TagSelector[] tagSelectors)
			throws RPException {
		throw RPException.NOT_SUPPORTED_OPERATION;
		
	}


	public void removeTriggers(Trigger[] triggers) throws RPException {
		throw RPException.NOT_SUPPORTED_OPERATION;
		
	}


	public void resetToDefaultSettings() throws RPException {
		// Unsupported by CAEN
		throw new RPException(" Unsupported by CAEN reader");
		
	}


	public void setCurrentDataSelector(DataSelector dataSelector)
			throws RPException {
		throw RPException.NOT_SUPPORTED_OPERATION;
		
	}


	public void setCurrentSource(Source source) throws RPException {
		throw RPException.NOT_SUPPORTED_OPERATION;
		
	}


	public void setHandle(int handle) throws RPException {
		throw RPException.NOT_SUPPORTED_OPERATION;
		
	}


	public void setName(String name) throws RPException {
		this.name = name;		
	}


	public void setRole(String role) throws RPException {
		this.role = role;		
	}


	public void setTimeUTC(String time) throws RPException {
		throw RPException.NOT_SUPPORTED_OPERATION;
		
	}

	/////////////////////////////////
	// Connectable Implementation  //
	/////////////////////////////////
	
	private final static String DEFAULT_COM_ADDR = "serial://com1";
	public String getDefaultAddress() {
		return DEFAULT_COM_ADDR;
	}
	
	public void connect(String address) throws RPException {
		if (address.equals(DEFAULT_COM_ADDR)) {
			/* Set up Reader */
			// power up CAEN reader
			WCECommDriver.powerUpCaenReader();
			try {
				Thread.sleep(300); // sleep for reader to power up
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
			
			// connect and config
			try {
		        /* Connect to reader on COM1 */
		        System.out.println("Connecting to reader : " + "RS232 : COM1");
		        myReader.Connect(CAENRFIDPort.CAENRFID_RS232, "COM1:");
		        System.out.println("Connection Success");
		        
		        /* configure Reader */
		        // set protocol  : default is CAENRFID_EPC_C1G2
		        myReader.SetProtocol(CAENRFIDProtocol.CAENRFID_EPC_C1G2);
		        // set bitRate
		        //System.out.println("Setting BitRate");
		        //myReader.SetBitRate(CAENRFIDBitRate.TX10RX40);
		        //myReader.SetBitRate(CAENRFIDBitRate.TX40RX40);
		        //myReader.SetBitRate(CAENRFIDBitRate.TX40RX160);
		        //System.out.println("SetBitRate OK");
		        // set RF regulation
		        //myReader.SetRFRegulation(CAENRFIDRFRegulations.ETSI_302208);
		        myReader.Disconnect();
		        myReader.Connect(CAENRFIDPort.CAENRFID_RS232, "COM1:");
			} catch (CAENRFIDException e) {
				throw new RPException(e);
			}
		} else {
			throw new RPException("Invalid comm address " + address + " : use default address instead (" + DEFAULT_COM_ADDR + ")");
		}
	}


	public void discconnect() throws RPException {
		this.goodbye();		
	}
	
	/////////////////////////////////
	// Configurable Implementation //
	/////////////////////////////////

	public void setInitialQ(int initialQ) throws RPException {
		System.err.println(this.getClass() + "\n setInitialQ operation not supported.");		
	}


	public void setPower(int powerIndex, int[] mapping) throws RPException {
		if (powerIndex >= POWER_IDX_MIN_VALUE && powerIndex <= POWER_IDX_MAX_VALUE) {
			try {
				this.myReader.SetPower(mapping[powerIndex]);
				System.out.println("Expected Power = " + mapping[powerIndex]);
				System.out.println("Power = " + this.myReader.GetPower());
			} catch (CAENRFIDException e) {
				throw new RPException(e);
			}
		} else
			throw new IllegalArgumentException("powerIndex Out Of bounds");
	}


	public void setSession(int session) throws RPException {
		System.err.println(this.getClass() + "\n setSession operation not supported.");	
	}

	/////////////////////////////////
	// Manageable Implementation   //
	/////////////////////////////////
	
	public DataSelector DataSelector_create(String name) throws RPException {
		throw RPException.NOT_SUPPORTED_OPERATION;
	}


	/**
	 * Only the default Data Selector is supported.
	 */
	public int DataSelector_getMaxNumberSupported() throws RPException {
		return 1;
	}


	public NotificationChannel NotificationChannel_create(String name,
			String address) throws RPException {
		throw RPException.NOT_SUPPORTED_OPERATION;
	}


	/**
	 * No notification channel support.
	 */
	public int NotificationChannel_getMaxNumberSupported() throws RPException {
		return 0;
	}


	public Source Source_create(String name) throws RPException {
		throw RPException.NOT_SUPPORTED_OPERATION;
	}

	/**
	 * Only the default Pre-configured Source is supported.
	 */
	public int Source_getMaxNumberSupported() throws RPException {
		return 1;
	}


	public TagField TagField_create(String name) throws RPException {
		throw RPException.NOT_SUPPORTED_OPERATION;
	}


	public int TagField_getMaxNumberSupported() throws RPException {
		return 0;
	}


	public TagSelector TagSelector_create(String name, TagField field,
			String value, String mask, boolean inclusiveFlag)
			throws RPException {
		throw RPException.NOT_SUPPORTED_OPERATION;
	}


	public int TagSelector_getMaxNumberSupported() throws RPException {
		return 0;
	}


	public Trigger Trigger_create(String name, TriggerType type, String value)
			throws RPException {
		return null;
	}


	public int Trigger_getMaxNumberSupported() throws RPException {
		return 0;
	}
	
}
