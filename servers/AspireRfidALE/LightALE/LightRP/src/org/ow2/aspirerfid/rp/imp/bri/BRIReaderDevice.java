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

package org.ow2.aspirerfid.rp.imp.bri;

import java.util.StringTokenizer;

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


import com.intermec.datacollection.rfid.BasicBRIReader;
import com.intermec.datacollection.rfid.BasicReaderException;

public class BRIReaderDevice implements ReaderDevice, Connectable, Configurable, Manageable {
	
	/**
	 * Basic Bri Reader instance wrapper. 
	 */
	protected BasicBRIReader myReader;
	
	private String name;
	private String role;
	
	private BRISource currentSource;
	private DataSelector currentDataSelector;
	
	
	/**
	 * Constructor
	 */
	public BRIReaderDevice(String name) {
		// BRI wrapper
		myReader = new BasicBRIReader();
		this.name = name;
		this.role = "MobileReader";
		// Default supported data selector
		currentDataSelector = new BRIDataSelector("ReaderProtocol:Default");
		currentSource = new BRISource("Unique Source", this.myReader);
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
		String rsp;
		StringTokenizer tokenizer;
		long time;

		try {
			rsp = this.myReader.execute("PING TIME");
		} catch (BasicReaderException e) {
			throw new RPException(e);
		}
		// retrieve time ticks from response
		tokenizer = new StringTokenizer(rsp, "\r\n");
		time = Long.parseLong(tokenizer.nextToken());
		
		return (int)time; // 32-bit signed integer
	}


	public String getTimeUTC() throws RPException {
		throw RPException.NOT_SUPPORTED_OPERATION;
	}


	public Trigger getTrigger(String name) throws RPException {
		throw RPException.NOT_SUPPORTED_OPERATION;
	}


	public void goodbye() throws RPException {
		try {
			this.myReader.close();
		} catch (BasicReaderException e) {
			throw new RPException(e);
		}
		
	}


	public void reboot() throws RPException {
		try {
			this.myReader.execute("RESET");
		} catch (BasicReaderException e) {
			throw new RPException(e);
		}
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
		try {
			// see BRI documentation 
			// §Resetting the Reader to the Factory Default Configuration
			this.myReader.execute("FACDFLT");
		} catch (BasicReaderException e) {
			throw new RPException(e);
		}
		
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
	
	public String getDefaultAddress() {
		return "tcp://127.0.0.1";
	}
	
	public void connect(String address) throws RPException {
		try {
			this.myReader.open(address);
			// Init Data Acquisition Timeout Profile to default values
			// 400 ms read cycle
			this.currentSource.initDaqStage(400 /*ms*/);
		} catch (BasicReaderException e) {
			throw new RPException(e);
		}
		
	}


	public void discconnect() throws RPException {
		try {
			this.myReader.close();
		} catch (BasicReaderException e) {
			throw new RPException(e);
		}
		
	}
	
	/////////////////////////////////
	// Configurable Implementation //
	/////////////////////////////////

	public void setInitialQ(int initialQ) throws RPException {
		if ((initialQ >= INITIALQ_MIN_VALUE) && (initialQ <= INITIALQ_MAX_VALUE)) {
			try {
				this.myReader.execute("ATTRIB INITIALQ=" + initialQ);
			} catch (BasicReaderException e) {
				throw new RPException(e);
			}
		}		
	}


	public void setPower(int powerIndex, int[] mapping) throws RPException {
		if (powerIndex >= POWER_IDX_MIN_VALUE && powerIndex <= POWER_IDX_MAX_VALUE) {
			try {
				this.myReader.execute("ATTRIB FIELDSTRENGTH=" + mapping[powerIndex]);
				// Log New value
				System.out.println("Power :\n " + this.myReader.execute("ATTRIB FIELDSTRENGTH"));
			} catch (BasicReaderException e) {
				throw new RPException(e);
			}
		}		
	}


	public void setSession(int session) throws RPException {
		if (session >= SESSION_MIN_VALUE && session <= SESSION_MAX_VALUE) {
			try {
				this.myReader.execute("ATTRIB SESSION=" + session);
			} catch (BasicReaderException e) {
				throw new RPException(e);
			}
		}		
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
