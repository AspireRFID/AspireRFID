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
package org.ow2.aspirerfid.nfc.midlet.sendersReceivers.bluetooth.states;

import java.io.IOException;
import java.util.Hashtable;
import java.util.Vector;

import javax.bluetooth.BluetoothStateException;
import javax.bluetooth.DeviceClass;
import javax.bluetooth.DiscoveryAgent;
import javax.bluetooth.DiscoveryListener;
import javax.bluetooth.LocalDevice;
import javax.bluetooth.RemoteDevice;
import javax.bluetooth.ServiceRecord;

import org.ow2.aspirerfid.nfc.midlet.sendersReceivers.bluetooth.BluetoothSenderReceiver;
import org.ow2.aspirerfid.nfc.midlet.sendersReceivers.bluetooth.BluetoothToBusinessInterface;

/**
 * This class represents the state of discovering near devices near. This is the
 * implementation of the state pattern, and this class is a concrete state
 * participant.<br/>There is only one valid operation for this class: cancel.<br/>The
 * example is taken from <a
 * href="http://mobile.liveshere.net/articles/viewarticle.php?id=22">http://mobile.liveshere.net/articles/viewarticle.php?id=22</a>
 * To make it works, the server side has to be executed and waiting for the
 * messages.<br/> The implementation of the DiscoveryListener interface permits
 * the callbacks to the deviceDiscovered and inquiryCompleted methods. Based in
 * the Card Exchanger example of Nokia.<br/> Also based on <a
 * href="http://developers.sun.com/mobility/apis/articles/bluetoothcore/index.html">http://developers.sun.com/mobility/apis/articles/bluetoothcore/index.html</a>
 * TODO To use a attribute that indicates if the device discovery have to be
 * quickly and to search any device that offers the required service.
 * Automatically jump to the connect state and not to the service discovery
 * state.
 * 
 * @author <a href="mailto:angoca@yahoo.com">Andres Gomez</a>
 * @version 1.0.0 31/07/2008
 */
public class DeviceDiscoveryState extends BluetoothState implements
		DiscoveryListener {

	/**
	 * Set of discovered devices.
	 */
	private Vector m_discoveredDevices = null;
	/**
	 * Local device agent.
	 */
	private DiscoveryAgent m_discoveryAgent = null;
	/**
	 * Set of remote devices.
	 */
	private Hashtable m_remoteDevices = null;

	/**
	 * Constructor that associates the bluetooth context to the state. A context
	 * is the class that uses the states and it only has a state attribute to
	 * know the current state.
	 * 
	 * @param senderReceiver
	 *            Bluetooth context.
	 */
	public DeviceDiscoveryState(BluetoothSenderReceiver senderReceiver) {
		super(senderReceiver);
		try {
			// List of remote devices.
			this.m_remoteDevices = new Hashtable();
			// List of discovered devices.
			this.m_discoveredDevices = new Vector();

			// Get the Discovery agent to search devices.
			LocalDevice localDevice = LocalDevice.getLocalDevice();
			this.m_discoveryAgent = localDevice.getDiscoveryAgent();

			// Retrieves all the already known devices.
			// Cached devices.
			RemoteDevice[] cachedDevices = this.m_discoveryAgent
					.retrieveDevices(DiscoveryAgent.CACHED);
			if (cachedDevices != null) {
				for (int i = 0, length = cachedDevices.length; i < length; i++) {
					this.m_remoteDevices.put(cachedDevices[i]
							.getBluetoothAddress(), cachedDevices[i]);
				}
			}
			// Preknown devices.
			RemoteDevice[] preknownDevices = this.m_discoveryAgent
					.retrieveDevices(DiscoveryAgent.PREKNOWN);
			if (preknownDevices != null) {
				for (int i = 0, lenght = preknownDevices.length; i < lenght; i++) {
					this.m_remoteDevices.put(preknownDevices[i]
							.getBluetoothAddress(), preknownDevices[i]);
				}
			}

			// The search will be GIAC and not LIAC. When this method is called,
			// the inquiry uses the deviceDiscovery and inquiryCompleted
			// methods (via callback thanks to DiscoveryListener interface.)
			this.m_discoveryAgent.startInquiry(
					this.getParent().getAccessCode(), this);
		} catch (BluetoothStateException e) {
			this.changeState(new DisconnectedState(this.getParent()));
			this.getParent().report(
					BluetoothToBusinessInterface.ERROR_BLUETOOTH_NOT_AVAILABLE);
		} catch (Exception e) {
			throw new RuntimeException("Error BT5 creating device discovery: "
					+ e.getMessage());
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see sendersReceivers.bluetooth.states.BluetoothState#cancel()
	 */
	public void cancel() {
		try {
			this.m_discoveryAgent.cancelInquiry(this);
		} catch (Exception e) {
			throw new RuntimeException("Error BT7 cancel device discovery: "
					+ e.getMessage());
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see sendersReceivers.bluetooth.states.BluetoothState#connect()
	 */
	public void connect() {
		// Nothing must call this method.
		throw new RuntimeException(
				"Error: Invalid call to connect in device discovery state");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.bluetooth.DiscoveryListener#deviceDiscovered(javax.bluetooth.RemoteDevice,
	 *      javax.bluetooth.DeviceClass)
	 */
	public void deviceDiscovered(RemoteDevice remoteDevice, DeviceClass exclude) {
		try {
			String friendlyName = "No friendly name";
			try {
				friendlyName = remoteDevice.getFriendlyName(false);
			} catch (IOException e) {
				this
						.getParent()
						.report(
								BluetoothToBusinessInterface.ERROR_GETTING_FRIENDLY_NAME);
			}
			String address = remoteDevice.getBluetoothAddress();
			this.getParent().deviceDiscovered(friendlyName, address);
			// DeviceClass is not used because there is not filtering when
			// searching
			// devices. It just adds the found device in the corresponding list.

			this.m_discoveredDevices.addElement(remoteDevice);
		} catch (Exception e) {
			throw new RuntimeException("Error BT6 device discovered: "
					+ e.getMessage());
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see sendersReceivers.bluetooth.states.BluetoothState#disconnect()
	 */
	public void disconnect() {
		// Nothing must call this method.
		throw new RuntimeException(
				"Error: Invalid call to disconnect in device discovery state");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.bluetooth.DiscoveryListener#inquiryCompleted(int)
	 */
	public void inquiryCompleted(int param) {
		try {
			switch (param) {

			// Inquiry completed normally
			case DiscoveryListener.INQUIRY_COMPLETED:
				// Adds the discovered devices.
				for (int i = 0, size = this.m_discoveredDevices.size(); i < size; i++) {
					RemoteDevice device = (RemoteDevice) this.m_discoveredDevices
							.elementAt(i);
					// Adds the device only if it not already known or cached.
					RemoteDevice existentDevice = (RemoteDevice) this.m_remoteDevices
							.get(device.getBluetoothAddress());
					if (existentDevice == null) {
						this.m_remoteDevices.put(device.getBluetoothAddress(),
								device);
					}
				}

				this.getParent().inquiryCompleted(
						BluetoothToBusinessInterface.SEARCH_DEVICES_COMPLETED);
				this.changeState(new ServiceDiscoveryState(this.getParent(),
						this.m_remoteDevices));
				break;

			// Error during inquiry
			case DiscoveryListener.INQUIRY_ERROR:
				this.getParent().inquiryCompleted(
						BluetoothToBusinessInterface.SEARCH_DEVICES_ERROR);
				this.changeState(new DisconnectedState(this.getParent()));
				break;

			// Inquiry terminated by agent.cancelInquiry()
			case DiscoveryListener.INQUIRY_TERMINATED:
				this.getParent().inquiryCompleted(
						BluetoothToBusinessInterface.SEARCH_DEVICES_CANCELED);
				this.changeState(new DisconnectedState(this.getParent()));
				break;
			}
		} catch (Exception e) {
			this.changeState(new DisconnectedState(this.getParent()));
			this.getParent().report(
					BluetoothToBusinessInterface.ERROR_ON_INQUIRY_COMPLETE);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see sendersReceivers.bluetooth.states.BluetoothState#receive()
	 */
	public String receive() {
		// Nothing must call this method.
		throw new RuntimeException(
				"Error: Invalid call to receive in device discovery state");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see sendersReceivers.bluetooth.states.BluetoothState#send(java.lang.String)
	 */
	public void send(String message) {
		// Nothing must call this method.
		throw new RuntimeException(
				"Error: Invalid call to send in device discovery state");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.bluetooth.DiscoveryListener#servicesDiscovered(int,
	 *      javax.bluetooth.ServiceRecord[])
	 */
	public void servicesDiscovered(int arg0, ServiceRecord[] arg1) {
		// Nothing must call this method.
		throw new RuntimeException("Error: Invalid call servicesDiscovered");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.bluetooth.DiscoveryListener#serviceSearchCompleted(int, int)
	 */
	public void serviceSearchCompleted(int arg0, int arg1) {
		// Nothing must call this method.
		throw new RuntimeException("Error: Invalid call serviceSearchCompleted");
	}
}
