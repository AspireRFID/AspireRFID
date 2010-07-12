/*
 *  Copyright (C) Aspire
 *
 *  This library is free software; you can redistribute it and/or
 *  modify it under the terms of the GNU Lesser General Public
 *  License as published by the Free Software Foundation; either
 *  version 2.1 of the License, or (at your option) any later version.
 *
 *  This library is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 *  Lesser General Public License for more details.
 *
 *  You should have received a copy of the GNU Lesser General Public
 *  License along with this library; if not, write to the Free Software
 *  Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301  USA
 */
package org.ow2.aspirerfid.rocket.impl;

import org.osgi.framework.BundleContext;
import org.osgi.framework.BundleException;
import org.ow2.aspirerfid.libusbjava.service.ProxyLibUsbJavaService;
import org.ow2.aspirerfid.rocket.service.RocketService;

import ch.ntb.usb.LibusbJava;
import ch.ntb.usb.Usb_Device;

/**
 * Rocket launcher implementation. Can move tower and fire rockets
 * 
 * @author Jean-François Marquet
 * @author Wahiba Gabli
 * @author Lionel Touseau
 * @author Thomas Calmant (Linux Patch, refactoring)
 * 
 */
public class Rocket implements RocketService {

	// This Class uses a bundled version of libusb.
	// The non bundled code necessary to work is still available but commented.

	// This is where the bundle libusbjava is used
	ProxyLibUsbJavaService m_libusb;

	static final short VENDOR_ID = 0x1130;
	static final short PRODUCT_ID = 0x0202;

	static byte[] PACKET_INIT_A = null;
	static byte[] PACKET_INIT_B = null;
	static byte[] PACKET_COMMAND = null;

	static final int WRITE_TIMEOUT = 100;
	static final int USB_REQUEST_TYPE_HID = 0x21;
	static final int REQUEST_SET_CONFIGURATION = 0x09;
	static final int REQUESTTYPE_RECIPIENT_ENDPOINT = 0x02;
	static final int CONTROL_MSG_VALUE = 1;

	static long DeviceId;

	public void start() throws BundleException {

		// Create and initialize the different packets to be sent

		PACKET_INIT_A = new byte[] { 'U', 'S', 'B', 'C', 0, 0, 4, 0 };
		PACKET_INIT_B = new byte[] { 'U', 'S', 'B', 'C', 0, 0x40, 2, 0 };

		PACKET_COMMAND = new byte[64];
		PACKET_COMMAND[6] = 8;
		PACKET_COMMAND[7] = 8;

		// /* USB INIT */
		// LibusbJava.usb_init();
		// LibusbJava.usb_find_busses();
		// LibusbJava.usb_find_devices();

		// /* FIND MISSILE LAUNCHER */
		// Usb_Device lMissileLauncher = getMissileLauncher();

		Usb_Device lMissileLauncher = m_libusb.findUsbDevice(VENDOR_ID,
				PRODUCT_ID);
		if (lMissileLauncher == null) {
			System.out.println("No Rocket Launcher Found !");
			throw new BundleException("No Rocket Launcher Found !");
		}
		System.out.println("Rocket Launcher Found !");

		/* CONFIGURE MISSILE LAUNCHER */
		long lMissileLauncherID = LibusbJava.usb_open(lMissileLauncher);

		// By default, claim the interface 0
		int usb_interface = 0;

		if (System.getProperty("os.name").equals("Linux")) {
			// Under Linux, we need to claim the interface 1
			usb_interface = 1;
		}

		m_libusb.set_configuration(lMissileLauncherID, 1);

		// Under Linux : interface 1, default : 0
		m_libusb.claim_interface(lMissileLauncherID, usb_interface);
		m_libusb.set_altinterface(lMissileLauncherID, 0);

		DeviceId = lMissileLauncherID;
	}

	/**
	 * Implements BundleActivator.stop(). Does nothing since the framework will
	 * automatically unregister any registered services.
	 * 
	 * @param context
	 *            the framework context for the bundle.
	 */
	public void stop(BundleContext context) {
		// NOTE: The org.ow2.aspirerfid.rocket.service is automatically
		// unregistered.
	}

	// public methods to communicate with the org.ow2.aspirerfid.rocket.impl
	// launcher

	public void fire() {
		sendCommand(DeviceId, Command.FIRE);
	}

	// the movement will stop after 'time' milliseconds
	public void moveDown(long time) {
		sendCommand(DeviceId, Command.DOWN);
		try {
			Thread.sleep(time);
		} catch (InterruptedException e) {
			e.printStackTrace();
			System.out.println("sleep exception in moveDown");
		}
		sendCommand(DeviceId, Command.STOP);
	}

	public void moveDownLeft(long time) {
		sendCommand(DeviceId, Command.DOWNLEFT);
		try {
			Thread.sleep(time);
		} catch (InterruptedException e) {
			e.printStackTrace();
			System.out.println("sleep exception in moveDownLeft");
		}
		sendCommand(DeviceId, Command.STOP);
	}

	public void moveDownRight(long time) {
		sendCommand(DeviceId, Command.DOWNRIGHT);
		try {
			Thread.sleep(time);
		} catch (InterruptedException e) {
			e.printStackTrace();
			System.out.println("sleep exception in moveDownRight");
		}
		sendCommand(DeviceId, Command.STOP);
	}

	public void moveLeft(long time) {
		sendCommand(DeviceId, Command.LEFT);
		try {
			Thread.sleep(time);
		} catch (InterruptedException e) {
			e.printStackTrace();
			System.out.println("sleep exception in moveLeft");
		}
		sendCommand(DeviceId, Command.STOP);
	}

	public void moveRight(long time) {
		sendCommand(DeviceId, Command.RIGHT);
		try {
			Thread.sleep(time);
		} catch (InterruptedException e) {
			e.printStackTrace();
			System.out.println("sleep exception in moveRight");
		}
		sendCommand(DeviceId, Command.STOP);
	}

	public void moveUp(long time) {
		sendCommand(DeviceId, Command.UP);
		try {
			Thread.sleep(time);
		} catch (InterruptedException e) {
			e.printStackTrace();
			System.out.println("sleep exception in moveUp");
		}
		sendCommand(DeviceId, Command.STOP);
	}

	public void moveUpLeft(long time) {
		sendCommand(DeviceId, Command.UPLEFT);
		try {
			Thread.sleep(time);
		} catch (InterruptedException e) {
			e.printStackTrace();
			System.out.println("sleep exception in moveUpLeft");
		}
		sendCommand(DeviceId, Command.STOP);
	}

	public void moveUpRight(long time) {
		sendCommand(DeviceId, Command.UPRIGHT);
		try {
			Thread.sleep(time);
		} catch (InterruptedException e) {
			e.printStackTrace();
			System.out.println("sleep exception in moveUpRight");
		}
		sendCommand(DeviceId, Command.STOP);
	}

	// send a command to the org.ow2.aspirerfid.rocket.impl launcher
	private void sendCommand(long pDeviceID, Command pCommand) {
		byte[] lBytes = PACKET_COMMAND.clone();
		switch (pCommand) {
		case STOP:
			// Do nothing
			break;
		case LEFT:
			lBytes[1] = 1;
			break;
		case RIGHT:
			lBytes[2] = 1;
			break;
		case UP:
			lBytes[3] = 1;
			break;
		case DOWN:
			lBytes[4] = 1;
			break;
		case UPLEFT:
			lBytes[3] = 1;
			lBytes[1] = 1;
			break;
		case UPRIGHT:
			lBytes[3] = 1;
			lBytes[2] = 1;
			break;
		case DOWNLEFT:
			lBytes[4] = 1;
			lBytes[1] = 1;
			break;
		case DOWNRIGHT:
			lBytes[4] = 1;
			lBytes[2] = 1;
			break;
		case FIRE:
			lBytes[5] = 1;
			break;
		}

		// Send command to the device : two initialisation packets and the
		// actual command
		m_libusb.control_msg(pDeviceID, USB_REQUEST_TYPE_HID,
				REQUEST_SET_CONFIGURATION, REQUESTTYPE_RECIPIENT_ENDPOINT,
				CONTROL_MSG_VALUE, PACKET_INIT_A, PACKET_INIT_A.length,
				WRITE_TIMEOUT);

		m_libusb.control_msg(pDeviceID, USB_REQUEST_TYPE_HID,
				REQUEST_SET_CONFIGURATION, REQUESTTYPE_RECIPIENT_ENDPOINT,
				CONTROL_MSG_VALUE, PACKET_INIT_B, PACKET_INIT_B.length,
				WRITE_TIMEOUT);
		m_libusb.control_msg(pDeviceID, USB_REQUEST_TYPE_HID,
				REQUEST_SET_CONFIGURATION, REQUESTTYPE_RECIPIENT_ENDPOINT,
				CONTROL_MSG_VALUE, lBytes, lBytes.length, WRITE_TIMEOUT);

	}

	/*
	 * private static Usb_Device getMissileLauncher() { Usb_Device temp = null;
	 * Usb_Device device = null; boolean trouve = false; Usb_Bus tempbus =
	 * m_libusb.get_busses();;
	 * 
	 * 
	 * while (!trouve && (tempbus != null)) { temp = tempbus.getDevices(); while
	 * (!trouve && (temp != null)) { Usb_Device_Descriptor descriptor =
	 * temp.getDescriptor(); System.out.println(descriptor.getIdVendor() +
	 * " -/ - " + descriptor.getIdProduct()); if ((descriptor.getIdVendor() ==
	 * VENDOR_ID) && (descriptor.getIdProduct() == PRODUCT_ID)) {
	 * System.out.println("Device was found \n " + " - Vendor id : " +
	 * Short.toString(descriptor.getIdVendor()) + "\n - Product id : " +
	 * Short.toString(descriptor.getIdProduct())); trouve = true; device = temp;
	 * } temp = temp.getNext(); } tempbus = tempbus.getNext(); } return device;
	 * }
	 */
}
