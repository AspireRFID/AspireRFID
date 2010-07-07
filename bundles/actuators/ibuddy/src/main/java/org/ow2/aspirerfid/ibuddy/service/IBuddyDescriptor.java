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

package org.ow2.aspirerfid.ibuddy.service;

import org.ow2.aspirerfid.ibuddy.service.IIBuddy.HeadColor;
import org.ow2.aspirerfid.ibuddy.service.IIBuddy.HeartState;
import org.ow2.aspirerfid.ibuddy.service.IIBuddy.Orientation;
import org.ow2.aspirerfid.ibuddy.service.IIBuddy.WingsState;
import org.ow2.aspirerfid.libusbjava.service.ProxyLibUsbJavaService;
import ch.ntb.usb.Usb_Device;

/**
 * TODO
 * @author Daniel Lovera and Clément Deschamps and Mehdi Damou
 **/

public class IBuddyDescriptor {


	private static final byte[] INIT = {
			0x22, 0x09, 0x00, 0x02, 0x01, 0x00, 0x00, 0x00 };
	private static final byte[] COMMAND_HEADER = {
			0x55, 0x53, 0x42, 0x43, 0x00, 0x40, 0x02, 0x00 };

	private byte currentCommand = (byte) 0xFF;
	private boolean debug = true;
	private boolean opened = false;
	private Long id;
	private long handle;
	private Usb_Device usbdevice = null;
	private ProxyLibUsbJavaService usblib;

	public IBuddyDescriptor(Long long1, Usb_Device usb_Device,
			ProxyLibUsbJavaService usblib) {
		this.id = long1;
		this.usbdevice = usb_Device;
		this.usblib = usblib;
	}

	public void open() {
		if ((opened == false) && (usbdevice != null)) {
			handle = usblib.open(usbdevice);
			System.out
					.println("DEBUG_JBuddy open -> usb_open : " + handle);

			int lDebug;
			if (System.getProperty("os.name").equals("Linux")) {
				printDebug("os -> Running on Linux");
			} else {
				printDebug("os -> Running on " + System.getProperty("os.name"));
				lDebug = usblib.set_configuration(handle, 1);
				printDebug("open -> usb_set_configuration : " + lDebug);
			}

			// PATCH: under Linux : interface 1; default : interface 0
			lDebug = usblib.claim_interface(handle, 1);
			printDebug("open -> usb_claim_interface : " + lDebug);

			lDebug = usblib.set_altinterface(handle, 0);
			printDebug("open -> usb_set_altinterface : " + lDebug);

			opened=true;
		}
	}

	public void close() {
		if (opened == true) {
			usblib.close(handle);
			opened = false;
		}
	}

	private void sendCurrentCommand() {


			if (opened == true) {

				usblib.control_msg(handle, 0x21, 0x09, 0x02, 0x01, INIT,
					INIT.length, 250);
			byte[] commandMessage = COMMAND_HEADER.clone();
			commandMessage[7] = currentCommand;
			usblib.control_msg(handle, 0x21, 0x09, 0x02, 0x01,
					commandMessage, commandMessage.length, 250);
			}

	}

	public void sendReset() {
		if (opened == true) {
			currentCommand = (byte) 0xFF;
			sendCurrentCommand();
		}
	}

	public void sendHeadColor(HeadColor pColor) {
		currentCommand = (byte) (currentCommand | 0x70);
		switch (pColor) {
		case NONE:
			currentCommand = (byte) (currentCommand ^ (0 << 4));
			break;
		case RED:
			currentCommand = (byte) (currentCommand ^ (1 << 4));
			break;
		case GREEN:
			currentCommand = (byte) (currentCommand ^ (2 << 4));
			break;
		case YELLOW:
			currentCommand = (byte) (currentCommand ^ (3 << 4));
			break;
		case BLUE:
			currentCommand = (byte) (currentCommand ^ (4 << 4));
			break;
		case VIOLET:
			currentCommand = (byte) (currentCommand ^ (5 << 4));
			break;
		case CYAN:
			currentCommand = (byte) (currentCommand ^ (6 << 4));
			break;
		case WHITE:
			currentCommand = (byte) (currentCommand ^ (7 << 4));
			break;
		}
		sendCurrentCommand();
	}

	public void sendOrientation(Orientation pOrientation) {
		currentCommand = (byte) (currentCommand | 0x03);
		switch (pOrientation) {
		case LEFT:
			currentCommand = (byte) (currentCommand ^ (1 << 1));
			break;
		case RIGHT:
			currentCommand = (byte) (currentCommand ^ (1 << 0));
			break;
		}
		sendCurrentCommand();
	}

	public void sendHeartState(HeartState pHeartState) {
		switch (pHeartState) {
		case OFF:
			currentCommand = (byte) (currentCommand | (1 << 7));
			break;
		case ON:
			currentCommand = (byte) (currentCommand ^ (1 << 7));
			break;
		}
		sendCurrentCommand();
	}

	public void sendWingsState(WingsState pState) {
		currentCommand = (byte) (currentCommand | 0x0C);
		switch (pState) {
		case UP:
			currentCommand = (byte) (currentCommand ^ (1 << 3));
			break;
		case DOWN:
			currentCommand = (byte) (currentCommand ^ (1 << 2));
			break;
		}
		sendCurrentCommand();
	}

	public void setDebug(boolean pState) {
		debug = pState;
	}

	public void printDebug(String pStr) {
		if (debug == true)
			System.out.println("DEBUG_JBuddy " + pStr);
	}


	/**
	 * @return the opened
	 */
	public boolean isOpened() {
		return opened;
	}

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

}
