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

package org.ow2.aspirerfid.ibuddy.impl;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;


import org.ow2.aspirerfid.ibuddy.service.IBuddyDescriptor;
import org.ow2.aspirerfid.ibuddy.service.IIBuddy;
import org.ow2.aspirerfid.libusbjava.service.ProxyLibUsbJavaService;
import org.ow2.aspirerfid.libusbjava.service.UsbDevice;

import ch.ntb.usb.Usb_Device;

/**
 * TODO 
 * @author Daniel Lovera and Clément Deschamps and El Mehdi Damou
 */
public class IBuddy implements IIBuddy {

	ProxyLibUsbJavaService m_libusb;

	public static final short IBUDDY_VENDOR_ID = 0x1130;
	public static final short IBUDDY_PRODUCT_ID = 0x0004;

	private Map<Long,IBuddyDescriptor> listIbuddy=null;
	
	
	
	public void start(){
		System.out.println("Init IBuddy API");
		listIbuddy = getListIbuddy();
	}
	
	public void stop(){
		System.out.println("Stop IBuddy API");
		listIbuddy = null;
	}
	
	public Map<Long,IBuddyDescriptor> getListIbuddy(){
		IBuddyDescriptor ibuddy=null;
		long num = 0;
		Map<Long,IBuddyDescriptor> ibuddyList=new HashMap<Long, IBuddyDescriptor>();
		
		Long id = m_libusb.findDevices(IBUDDY_VENDOR_ID, IBUDDY_PRODUCT_ID);
		UsbDevice device = m_libusb.getDevice(id);
		Map<Long,Usb_Device> listusb = device.getDevices();
		Set<Long> total = listusb.keySet();
		for (Iterator<Long> iterator = total.iterator(); iterator.hasNext();) {
			Long long1 = (Long) iterator.next();
			ibuddy = new IBuddyDescriptor(long1,listusb.get(long1),m_libusb);
			ibuddyList.put(num, ibuddy);
			num++;
		}
		
		return ibuddyList;
	}

	public IBuddyDescriptor getIBuddyID(Long i) {
		if (listIbuddy == null){
			getListIbuddy();
		}
		return listIbuddy.get(i);
	}
}
