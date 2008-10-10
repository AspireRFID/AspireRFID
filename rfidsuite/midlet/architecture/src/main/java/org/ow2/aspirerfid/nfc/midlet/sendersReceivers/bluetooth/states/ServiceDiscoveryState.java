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
import java.util.Enumeration;
import java.util.Hashtable;

import javax.bluetooth.BluetoothStateException;
import javax.bluetooth.DeviceClass;
import javax.bluetooth.DiscoveryAgent;
import javax.bluetooth.DiscoveryListener;
import javax.bluetooth.LocalDevice;
import javax.bluetooth.RemoteDevice;
import javax.bluetooth.ServiceRecord;
import javax.bluetooth.UUID;

import org.ow2.aspirerfid.nfc.midlet.sendersReceivers.bluetooth.BluetoothException;
import org.ow2.aspirerfid.nfc.midlet.sendersReceivers.bluetooth.BluetoothSenderReceiver;
import org.ow2.aspirerfid.nfc.midlet.sendersReceivers.bluetooth.BluetoothToBusinessInterface;

/**
 * This class represents the state of discovering services over the found
 * devices. <br/>There is just one valid action to call: cancel.<br/>This is
 * the implementation of the state pattern, and this class is a concrete state
 * participant.<br/>The example is taken from <a
 * href="http://mobile.liveshere.net/articles/viewarticle.php?id=22">http://mobile.liveshere.net/articles/viewarticle.php?id=22</a>
 * To make it works, the server side has to be executed and waiting for the
 * messages.<br/> The implementation of the DiscoveryListener interface permits
 * the callbacks to the servicesDiscovered and serviceSearchCompleted methods.
 * Based in the Card Exchanger example of Nokia.<br/> Improvements from <a
 * href="http://developers.sun.com/mobility/apis/articles/bluetoothcore/index.html">http://developers.sun.com/mobility/apis/articles/bluetoothcore/index.html</a>
 * 
 * @author <a href="mailto:angoca@yahoo.com">Andres Gomez</a>
 * @version 1.0.0 31/07/2008
 */
public class ServiceDiscoveryState extends BluetoothState implements
		DiscoveryListener, Runnable {
	/**
	 * Cancel the service discovery.
	 */
	private boolean m_canceled = false;
	/**
	 * Agent to discover other bluetooth devices.
	 */
	private DiscoveryAgent m_discoveryAgent = null;
	/**
	 * Local Bluetooth device
	 */
	private LocalDevice m_localDevice = null;
	/**
	 * Set of remote found devices.
	 */
	private Hashtable m_remoteDevices = null;
	/**
	 * Id of the search.
	 */
	private int m_searchID = 0;
	/**
	 * This is the error to communicate that the search services completed
	 * reports.
	 */
	private int m_serviceErrorCode = -1;
	/**
	 * Set of found services.
	 */
	private Hashtable m_services = null;
	/**
	 * Set of UUID of services to search.
	 */
	private UUID[] m_uuids = null;

	/**
	 * Constructor that associates the bluetooth context to the state. A context
	 * is the class that uses the states and it only has a state attribute to
	 * know the current state.<br/>This method starts another thread to search
	 * the devices. This permits to cancel the search.
	 * 
	 * @param senderReceiver
	 *            Bluetooth context.
	 * @param remoteDevices
	 *            Found devices in the Device discovery state.
	 */
	public ServiceDiscoveryState(BluetoothSenderReceiver senderReceiver,
			Hashtable remoteDevices) {
		super(senderReceiver);
		try {
			this.m_remoteDevices = remoteDevices;
			this.m_services = new Hashtable();

			// Defines the services to search. In this case just one service.
			this.m_uuids = new UUID[1];
			this.m_uuids[0] = new UUID(this.getParent().getUUID(), false);

			// Gets the discovery agent to search a valid service in the found
			// devices.
			try {
				this.m_localDevice = LocalDevice.getLocalDevice();
				this.m_discoveryAgent = this.m_localDevice.getDiscoveryAgent();

				new Thread(this).start();
			} catch (BluetoothStateException e) {
				this.changeState(new DisconnectedState(this.getParent()));
				this
						.getParent()
						.report(
								BluetoothToBusinessInterface.ERROR_IN_SERVICE_DISCOVERY);
			}
		} catch (Exception e) {
			throw new RuntimeException(
					"Error BT14 creating service discovery: " + e.getMessage());
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see sendersReceivers.bluetooth.states.BluetoothState#cancel()
	 */
	public void cancel() {
		this.m_canceled = true;
		this.m_discoveryAgent.cancelServiceSearch(this.m_searchID);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see sendersReceivers.bluetooth.states.BluetoothState#connect()
	 */
	public void connect() {
		// Nothing must call this method.
		throw new RuntimeException(
				"Error: Invalid call to connect in service discovery state");
	}

	/**
	 * Connects to the given service.
	 * 
	 * @param serviceRecord
	 *            Service to whom the application has to connect.
	 */
	private void connect(ServiceRecord serviceRecord) {
		try {
			int props = ServiceRecord.NOAUTHENTICATE_NOENCRYPT;
			if (this.getParent().getAuthenticateRemoteDevice()
					&& this.getParent().getEncryptConnection()) {
				props = ServiceRecord.AUTHENTICATE_ENCRYPT;
			} else if (this.getParent().getAuthenticateRemoteDevice()) {
				props = ServiceRecord.AUTHENTICATE_NOENCRYPT;
			}
			String url = serviceRecord.getConnectionURL(props, false);
			this.changeState(new ConnectedState(this.getParent(), url));
			this.getParent().informConnected();
			// Sets the friendly name
			String name = null;
			try {
				name = serviceRecord.getHostDevice().getFriendlyName(false);
			} catch (IOException e) {
				name = serviceRecord.getHostDevice().getBluetoothAddress();
			}
			this.getParent().setHostName(name);
		} catch (BluetoothException e) {
			// There was an error establishing the connected
			// state.
			this.changeState(new DisconnectedState(this.getParent()));
		} catch (Exception e) {
			throw new RuntimeException(
					"Error BT15 connecting service discovery: "
							+ e.getMessage());
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.bluetooth.DiscoveryListener#deviceDiscovered(javax.bluetooth.RemoteDevice,
	 *      javax.bluetooth.DeviceClass)
	 */
	public void deviceDiscovered(RemoteDevice remote, DeviceClass deviceClass) {
		// Nothing must call this method.
		throw new RuntimeException("Error: Invalid call deviceDiscovered");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see sendersReceivers.bluetooth.states.BluetoothState#disconnect()
	 */
	public void disconnect() {
		// Nothing must call this method.
		throw new RuntimeException(
				"Error: Invalid call to disconnect in service discovery state");
	}

	/**
	 * Creates a hashtable of strings containing the identification of the
	 * bluetooth remote devices that are in the given vector of services. If the
	 * names could not be obtained, it returns the bluetooth address.
	 * 
	 * @param services
	 *            Services of the remote devices.
	 * @return Set of bluetooth identifications, with the bluetooth address as
	 *         key, and the name as value.
	 */
	private Hashtable getDevices(Hashtable services) {
		Hashtable names = new Hashtable();
		Enumeration enums = services.elements();
		while (enums.hasMoreElements()) {
			ServiceRecord serviceRecord = (ServiceRecord) enums.nextElement();
			RemoteDevice device = serviceRecord.getHostDevice();
			String address = device.getBluetoothAddress();
			String name = null;
			try {
				// Tries to get the name.
				name = device.getFriendlyName(false);
			} catch (Exception e) {
				// If there's a problem, it takes the address.
				name = address;
			}
			names.put(address, name);
		}
		return names;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.bluetooth.DiscoveryListener#inquiryCompleted(int)
	 */
	public void inquiryCompleted(int arg0) {
		// Nothing must call this method.
		throw new RuntimeException("Error: Invalid call inquiryCommandComplete");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see sendersReceivers.bluetooth.states.BluetoothState#receive()
	 */
	public String receive() {
		// Nothing must call this method.
		throw new RuntimeException(
				"Error: Invalid call to receive in service discovery state");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Runnable#run()
	 */
	public synchronized void run() {
		try {
			Enumeration enumDev = this.m_remoteDevices.elements();

			// Searches services in all found devices.
			while (enumDev.hasMoreElements() && !this.m_canceled) {
				RemoteDevice remoteDevice = (RemoteDevice) enumDev
						.nextElement();

				// Check if the device offers the service. The returned id is
				// for cancel the discovery.
				try {
					this.m_searchID = this.m_discoveryAgent.searchServices(
							null, this.m_uuids, remoteDevice, this);
				} catch (BluetoothStateException e) {
					// The search is canceled.
					this.changeState(new DisconnectedState(this.getParent()));
					this.m_canceled = true;
				} finally {
					try {
						// Wait till the service search is completed.
						this.wait();
					} catch (Exception e) {
						// TODO Exception not!, IllegalState or other more
						// specific.
						// Do nothing.
					}
				}
			}
			// Search not canceled
			if (!this.m_canceled) {
				// There is not valid services in the found devices.
				if (this.m_services.isEmpty()) {
					this.getParent().notFoundServices();
					this.changeState(new DisconnectedState(this.getParent()));
				} else if ((this.m_services.size() == 1)
						&& this.getParent().getAutomaticConnection()) {
					// One found valid services.
					ServiceRecord serviceRecord = (ServiceRecord) this.m_services
							.elements().nextElement();
					this.connect(serviceRecord);
				} else if (this.m_services.size() >= 1) {
					// Several valid found services -> Creates a list of their
					// devices
					Hashtable devices = this.getDevices(this.m_services);
					// Bluetooth address of the selected device.
					String addressSelDevice = this.getParent()
							.getSelectedDevice(devices);
					if (!this.m_canceled) {
						if (addressSelDevice
								.compareTo(BluetoothToBusinessInterface.DISCOVER_AGAIN) == 0) {
							this.changeState(new DeviceDiscoveryState(this
									.getParent()));
						} else {
							ServiceRecord serviceRecord = (ServiceRecord) this.m_services
									.get(addressSelDevice);
							this.connect(serviceRecord);
						}
					}
				}
			} else {
				// Canceled
				this.changeState(new DisconnectedState(this.getParent()));
			}
		} catch (Exception e) {
			// FIXME when the server addresses are given, there is a problem.
			this.changeState(new DisconnectedState(this.getParent()));
			this
					.getParent()
					.report(
							BluetoothToBusinessInterface.ERROR_RUNNING_SERVICE_DISCOVERY);
			throw new RuntimeException("Error BT31a executing second thread: "
					+ e.getMessage());
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see sendersReceivers.bluetooth.states.BluetoothState#send(java.lang.String)
	 */
	public void send(String message) {
		// Nothing must call this method.
		throw new RuntimeException(
				"Error: Invalid call to send in service discovery state");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.bluetooth.DiscoveryListener#servicesDiscovered(int,
	 *      javax.bluetooth.ServiceRecord[])
	 */
	public void servicesDiscovered(int id, ServiceRecord[] serviceRecord) {
		try {
			// The name of each found service is taken and put in a services
			// list.
			for (int x = 0; x < serviceRecord.length; x++) {
				ServiceRecord service = serviceRecord[x];
				String address = service.getHostDevice().getBluetoothAddress();
				this.m_services.put(address, service);
			}
		} catch (Exception e) {
			throw new RuntimeException("Error B14 service discovered: "
					+ e.getMessage());
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.bluetooth.DiscoveryListener#serviceSearchCompleted(int, int)
	 */
	public synchronized void serviceSearchCompleted(int transID, int respCode) {
		try {
			switch (respCode) {
			case DiscoveryListener.SERVICE_SEARCH_COMPLETED:
				this.m_serviceErrorCode = BluetoothToBusinessInterface.SEARCH_SERVICES_COMPLETED;
				break;
			case DiscoveryListener.SERVICE_SEARCH_DEVICE_NOT_REACHABLE:
				this.m_serviceErrorCode = BluetoothToBusinessInterface.SEARCH_SERVICES_ERROR;
				break;
			case DiscoveryListener.SERVICE_SEARCH_ERROR:
				this.m_serviceErrorCode = BluetoothToBusinessInterface.SEARCH_SERVICES_ERROR;
				break;
			case DiscoveryListener.SERVICE_SEARCH_NO_RECORDS:
				this.m_serviceErrorCode = BluetoothToBusinessInterface.SEARCH_SERVICES_ERROR;
				break;
			case DiscoveryListener.SERVICE_SEARCH_TERMINATED:
				this.m_serviceErrorCode = BluetoothToBusinessInterface.SEARCH_SERVICES_CANCELED;
				break;
			}

			// TODO cuándo y dónde debo reportar esto?
			this.getParent().serviceSearchCompleted(this.m_serviceErrorCode);

			this.notify();
		} catch (Exception e) {
			throw new RuntimeException("Error BT15 service search completed: "
					+ e.getMessage());
		}

	}
}
