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


import ch.ntb.usb.Usb_Device;

/**
 * TODO comment the methods
 * @author El Mehdi DAMOU
 */
public interface ProxyLibUsbJavaService {

	public Usb_Device findUsbDevice(short vendorID, short productID);

	public int bulk_read(long dev_handle, int ep, byte[] bytes, int size,
			int timeout);

	public int bulk_write(long dev_handle, int ep, byte[] bytes, int size,
			int timeout);

	public int claim_interface(long dev_handle, int interface_);

	public int clear_halt(long dev_handle, int ep);

	public int close(long dev_handle);

	public int control_msg(long dev_handle, int requesttype, int request,
			int value, int index, byte[] bytes, int size, int timeout);

	public String get_descriptor_by_endpoint(long dev_handle, int ep,
			byte type, byte index, int size);

	public String get_descriptor(long dev_handle, byte type, byte index,
			int size);

	public String get_string_simple(long dev_handle, int index);

	public String get_string(long dev_handle, int index, int langid);

	public int interrupt_read(long dev_handle, int ep, byte[] bytes, int size,
			int timeout);

	public int interrupt_write(long dev_handle, int ep, byte[] bytes, int size,
			int timeout);

	public long open(Usb_Device dev);

	public int release_interface(long dev_handle, int interface_);

	public int usb_reset(long dev_handle);

	public int set_altinterface(long dev_handle, int alternate);

	public int set_configuration(long dev_handle, int configuration);

	public void set_debug(int level);

	public String strerror();
	
	public void updateListDevice();
	
	public Long findDevices(short vendorID, short productID) ;
	
	public UsbDevice getDevice(Long id);

}