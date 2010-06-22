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

package org.ow2.aspirerfid.libusbjava.service;

import java.util.Hashtable;
import java.util.Map;

import ch.ntb.usb.Usb_Device;

/**
 * TODO comment the methods and the fields
 * TODO set private the fields
 * @author El Mehdi DAMOU
 */
public class UsbDevice {
	
	public short vendorId;
	
	public short productId;
	
	public long lenght = 0;
	
	public Map<Long,Long> handles;
	
	public Map<Long,Boolean> onUse;
	
	public Map<Long,Boolean> present;
	
	public Map<Long,Usb_Device> devices;
	
	public Map<Long,String> filenames;
	
	public UsbDevice(short vendor, short product){
		this.vendorId = vendor;
		this.productId = product;
		devices = new Hashtable<Long, Usb_Device>();
		present = new Hashtable<Long, Boolean>();
		handles =  new Hashtable<Long, Long>();
		onUse = new Hashtable<Long, Boolean>();
		filenames = new Hashtable<Long, String>();
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + productId;
		result = prime * result + vendorId;
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UsbDevice other = (UsbDevice) obj;
		if (productId != other.productId)
			return false;
		if (vendorId != other.vendorId)
			return false;
		return true;
	}

	/**
	 * @return the vendorId
	 */
	public short getVendorId() {
		return vendorId;
	}

	/**
	 * @param vendorId the vendorId to set
	 */
	public void setVendorId(short vendorId) {
		this.vendorId = vendorId;
	}

	/**
	 * @return the productId
	 */
	public short getProductId() {
		return productId;
	}

	/**
	 * @param productId the productId to set
	 */
	public void setProductId(short productId) {
		this.productId = productId;
	}


	/**
	 * @return the lenght
	 */
	public long getLenght() {
		return lenght;
	}

	/**
	 * @param lenght the lenght to set
	 */
	public void setLenght(long lenght) {
		this.lenght = lenght;
	}

	/**
	 * @return the handles
	 */
	public Map<Long, Long> getHandles() {
		return handles;
	}

	/**
	 * @param handles the handles to set
	 */
	public void setHandles(Map<Long, Long> handles) {
		this.handles = handles;
	}


	/**
	 * @return the onUse
	 */
	public Map<Long, Boolean> getOnUse() {
		return onUse;
	}

	/**
	 * @param onUse the onUse to set
	 */
	public void setOnUse(Map<Long, Boolean> onUse) {
		this.onUse = onUse;
	}

	/**
	 * @return the present
	 */
	public Map<Long, Boolean> getPresent() {
		return present;
	}

	/**
	 * @param present the present to set
	 */
	public void setPresent(Map<Long, Boolean> present) {
		this.present = present;
	}

	/**
	 * @return the devices
	 */
	public Map<Long, Usb_Device> getDevices() {
		return devices;
	}

	/**
	 * @param devices the devices to set
	 */
	public void setDevices(Map<Long, Usb_Device> devices) {
		this.devices = devices;
	}

	/**
	 * @return the filenames
	 */
	public Map<Long,String> getFilenames() {
		return filenames;
	}

	/**
	 * @param filenames the filenames to set
	 */
	public void setFilenames(Map<Long,String> filenames) {
		this.filenames = filenames;
	}
}
