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
package org.ow2.aspirerfid.nfc.midlet.sendersReceivers.bluetooth;

import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Vector;

import org.ow2.aspirerfid.nfc.midlet.generic.GenericMidlet;
import org.ow2.aspirerfid.nfc.midlet.generic.SenderReceiver;
import org.ow2.aspirerfid.nfc.midlet.generic.SenderReceiverFactory;
import org.ow2.aspirerfid.nfc.midlet.generic.ui.AlertScreen;
import org.ow2.aspirerfid.nfc.midlet.generic.ui.Screen;
import org.ow2.aspirerfid.nfc.midlet.sendersReceivers.bluetooth.ui.BluetoothToUIInterface;
import org.ow2.aspirerfid.nfc.midlet.sendersReceivers.bluetooth.ui.FoundDevicesListScreen;
import org.ow2.aspirerfid.nfc.midlet.sendersReceivers.bluetooth.ui.SearchingDevicesScreen;
import org.ow2.aspirerfid.nfc.midlet.sendersReceivers.bluetooth.ui.UIToBluetoothInterface;

/**
 * This class manages the connection and communicates bia bluetooth. That
 * permits to the midlet to delegate these responsabilities. Also, this object
 * an be used for several applications that wants to use bluetooth
 * communication. The controller calls the SenderReceiverFactory to ask for the
 * bluetooth communication to send and to receive messages to and from a server.
 * The available servers are in a given list, so the bluetooth does not have to
 * discover them.<br/>This class communicates with the UI layer and the
 * communication layer, permitting and abstractness of the midlet. TODO when
 * connecting, the controller starts another thread that waits for operations.
 * The run method waits watching the value of a variable, and after that, it
 * calls another method. To make an operation, all the external method, change
 * the current operation, ands sets in local variables the necessary objects to
 * perform the operation. TODO use an inbox with two vectors (in and out) to
 * process them asynchronously. Each operation (send or receive) are in other
 * vector of operations. Probably, notifies the caller when one operation is
 * finished.
 * 
 * @author <a href="mailto:angoca@yahoo.com">Andres Gomez</a>
 * @version 1.0.0 31/07/2008
 */
public class BluetoothController implements BusinessToBluetoothCommInterface,
		UIToBluetoothInterface {
	/**
	 * This is the same caller midlet.
	 */
	private BluetoothControlerUser m_bluetoothControllerUser = null;
	/**
	 * Screen that is presenting the found devices and waiting to finish the
	 * discovery.
	 */
	private BluetoothToUIInterface m_devicesScreen = null;
	/**
	 * Midlet that call the controller.
	 */
	private GenericMidlet m_midlet = null;
	/**
	 * Screen that waits while bluetooth inquiry. This screen is active after
	 * the connection is established, or disconnection is finished.
	 */
	private Screen m_nextScreen = null;
	/**
	 * Screen that waits while bluetooth inquiry. This screen is active after
	 * the connection is established, or disconnection is finished.
	 */
	private Screen m_previousScreen = null;
	/**
	 * Selected device of the list of found services.
	 */
	private int m_selectedDevice = -1;
	/**
	 * Bluetooth sender receiver.
	 */
	private BluetoothToBusinessInterface m_senderReceiver = null;

	/**
	 * Constructor that associates the caller midlet and starts the bluetooth
	 * sender receiver.
	 * 
	 * @param midlet
	 *            caller midlet.
	 */
	public BluetoothController(GenericMidlet midlet) {
		this.m_midlet = midlet;
		try {
			try {
				this.m_bluetoothControllerUser = (BluetoothControlerUser) midlet;
			} catch (ClassCastException e) {
				throw new RuntimeException(
						"The midlet does not implement the BluetoothControllerUser interface.");
			}
			// Getting the sender receiver
			this.m_senderReceiver = (BluetoothSenderReceiver) SenderReceiverFactory
					.getNewInstance(SenderReceiverFactory.BLUETOOTH, this);
		} catch (Exception e) {
			throw new RuntimeException("Error BTC1 Creating the controller: "
					+ e.getMessage());
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see appl.museum.stable.UIToBluetoothInterface#cancel()
	 */
	public void cancel() {
		try {
			this.m_senderReceiver.cancel();
			this.m_midlet.setActiveScreen(this.m_previousScreen);
		} catch (Exception e) {
			throw new RuntimeException("Error BTC2 cancelling: "
					+ e.getMessage());
		}
	}

	/**
	 * Loads all the bluetooth configuration.
	 */
	private void configureBluetoothSenderReceiver() {
		try { // Gets the uuid of the service
			String uuid = this.m_midlet.getAppProperty("Server-UUID");
			this.m_senderReceiver.setUUID(uuid);
			// Gets the access code to discover
			String accessCode = this.m_midlet
					.getAppProperty("Local-BTAccessCode");
			if (accessCode != null) {
				this.m_senderReceiver.setAccessCode(Integer
						.parseInt(accessCode));
			}
			// Gets the action of to authorize or not the remote device.
			String authorizeStr = this.m_midlet.getAppProperty("BT-Authorize");
			if (authorizeStr != null) {
				boolean authorize = false;
				if (authorizeStr.compareTo("true") == 0) {
					authorize = true;
				}
				this.m_senderReceiver.setAuthorizeRemoteDevice(authorize);
			}
			// Gets the action of to authenticate or not the remote device.
			String authenticateStr = this.m_midlet
					.getAppProperty("BT-Authenticate");
			if (authenticateStr != null) {
				boolean authenticate = false;
				if (authenticateStr.compareTo("true") == 0) {
					authenticate = true;
				}
				this.m_senderReceiver.setAuthenticateRemoteDevice(authenticate);
			}
			// Gets the action of to encrypt or not the connection.
			String encryptStr = this.m_midlet.getAppProperty("BT-Authorize");
			if (encryptStr != null) {
				boolean encrypt = false;
				if (encryptStr.compareTo("true") == 0) {
					encrypt = true;
				}
				this.m_senderReceiver.setEncryptConnection(encrypt);
			}
			// Gets the action of automatic connection when just one valid found
			// device.
			String automaticStr = this.m_midlet
					.getAppProperty("BT-OneAutomatic");
			if (automaticStr != null) {
				boolean automatic = false;
				if (automaticStr.compareTo("true") == 0) {
					automatic = true;
				}
				this.m_senderReceiver
						.setAutomaticConnectionWhenJustOne(automatic);
			}
			// Adding servers.
			boolean validServer = true;
			int i = 1;
			while (validServer) {
				String serverAddress = this.m_midlet
						.getAppProperty("RemoteServer-" + i++);
				if (serverAddress != null) {
					this.m_senderReceiver
							.addBluetoothRemoteAddress(serverAddress);
				} else {
					validServer = false;
				}
			}
		} catch (Exception e) {
			throw new RuntimeException("Error BTC3 Loading the configuration: "
					+ e.getMessage());
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.ow2.aspire.rfid.nfc.midlet.sendersReceivers.bluetooth.ui.UIToBluetoothInterface#connectBluetooth(org.ow2.aspire.rfid.nfc.midlet.generic.ui.Screen,
	 *      org.ow2.aspire.rfid.nfc.midlet.generic.ui.Screen)
	 */
	public void connectBluetooth(Screen previousScreen, Screen nextScreen) {
		try {
			// Shows the waiting screen.
			// TODO change the title and not create several time the same
			// object.
			SearchingDevicesScreen connection = new SearchingDevicesScreen(
					this.m_midlet, this, "Connecting");
			this.m_midlet.setActiveScreen(connection);

			this.m_previousScreen = previousScreen;
			this.m_nextScreen = nextScreen;

			// Bluetooth not connected.
			if (!this.isBluetoothConnected()) {
				// Configuring the sender receiver
				this.configureBluetoothSenderReceiver();

				// Connecting
				// Starting the sender receiver.
				((SenderReceiver) this.m_senderReceiver).startSender();
				if (this.m_senderReceiver.isConnectionWithDiscovery()) {
					connection.changeTitle("Searching Devices");
				} else {
					connection.changeTitle("Connecting...");
				}
			} else {
				// Bluetooth disconnected.
				this.m_midlet.setActiveScreen(new AlertScreen(this.m_midlet,
						"Already connected!", this.m_nextScreen));
			}
		} catch (Exception e) {
			throw new RuntimeException("Error BTC4 Connecting: "
					+ e.getMessage());
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see sendersReceivers.bluetooth.states.BusinessToBluetoothCommInterface#deviceDiscovered(java.lang.String,
	 *      java.lang.String)
	 */
	public void deviceDiscovered(String friendlyName, String bluetoothAddress) {
		try {
			// Adds the name of the found devices to the screen.
			this.m_devicesScreen.addDiscoveredDevice(friendlyName);
		} catch (Exception e) {
			throw new RuntimeException("Error BTC5 Adding device: "
					+ e.getMessage());
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.ow2.aspire.rfid.nfc.midlet.sendersReceivers.bluetooth.ui.UIToBluetoothInterface#disconnectBluetooth(org.ow2.aspire.rfid.nfc.midlet.generic.ui.Screen,
	 *      org.ow2.aspire.rfid.nfc.midlet.generic.ui.Screen)
	 */
	public void disconnectBluetooth(Screen previousScreen, Screen nextScreen) {
		try {
			SearchingDevicesScreen disconnecting = new SearchingDevicesScreen(
					this.m_midlet, this, "Disconnecting");
			this.m_midlet.setActiveScreen(disconnecting);
			this.m_nextScreen = nextScreen;
			this.m_previousScreen = previousScreen;
			if (this.isBluetoothConnected()) {
				((SenderReceiver) this.m_senderReceiver).stopSender();
			} else {
				// Bluetooth disconnected.
				this.m_midlet.setActiveScreen(new AlertScreen(this.m_midlet,
						"Not connected!", this.m_previousScreen));
			}
		} catch (Exception e) {
			throw new RuntimeException("Error BTC6 disconnecting: "
					+ e.getMessage());
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see sendersReceivers.bluetooth.states.BusinessToBluetoothCommInterface#informConnected(boolean)
	 */
	public void informConnected(boolean connection) {
		try {
			if (connection) {
				this.m_midlet.setActiveScreen(new AlertScreen(this.m_midlet,
						"Connected!", this.m_nextScreen));
				this.m_bluetoothControllerUser.informConnected();
			} else {
				this.m_midlet.setActiveScreen(new AlertScreen(this.m_midlet,
						"Disconnected!", this.m_nextScreen));
				this.m_bluetoothControllerUser.informDisonnected();
			}
		} catch (Exception e) {
			throw new RuntimeException(
					"Error BTC7 informing connection state: " + e.getMessage());
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see sendersReceivers.bluetooth.states.BusinessToBluetoothCommInterface#inquiryCompleted(int)
	 */
	public void inquiryCompleted(int errorCode) {
		try {
			if (errorCode == BluetoothToBusinessInterface.SEARCH_DEVICES_COMPLETED) {
				// Do nothing. Redirected to service discovery.
			} else if (errorCode == BluetoothToBusinessInterface.SEARCH_DEVICES_ERROR) {
				this.m_midlet.setActiveScreen(new AlertScreen(this.m_midlet,
						"Error in search devices.", this.m_previousScreen));
			} else if (errorCode == BluetoothToBusinessInterface.SEARCH_DEVICES_CANCELED) {
				this.m_midlet.setActiveScreen(new AlertScreen(this.m_midlet,
						"Search devices canceled.", this.m_previousScreen));
			}
		} catch (Exception e) {
			throw new RuntimeException("Error BTC8 inquiry completed: "
					+ e.getMessage());
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see appl.museum.stable.UIToBluetoothInterface#isBluetoothConnected()
	 */
	public boolean isBluetoothConnected() {
		boolean value = false;
		try {
			int state = this.m_senderReceiver.getState();
			if ((state == BluetoothToBusinessInterface.STATE_CONNECTED)
					|| (state == BluetoothToBusinessInterface.STATE_SENDING)
					|| (state == BluetoothToBusinessInterface.STATE_RECEIVING)) {
				value = true;
			}
		} catch (Exception e) {
			throw new RuntimeException(
					"Error BTC9 Requesting the bluetooth state: "
							+ e.getMessage());
		}
		return value;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see sendersReceivers.bluetooth.states.BusinessToBluetoothCommInterface#notFoundServices()
	 */
	public void notFoundServices() {
		try {
			this.m_midlet.setActiveScreen(new AlertScreen(this.m_midlet,
					"Bluetooth server not found.", this.m_previousScreen));
		} catch (Exception e) {
			throw new RuntimeException("Error BTC10 Not found services: "
					+ e.getMessage());
		}

	}

	/**
	 * Receives a message via bluetooth. The communication has to be established
	 * before call this method.
	 * 
	 * @return Message to receive.
	 */
	public String receiveMessage() {
		String messRec = null;
		try {
			if (this.m_senderReceiver.getState() == BluetoothToBusinessInterface.STATE_CONNECTED) {
				// Receiving the message
				messRec = ((SenderReceiver) this.m_senderReceiver)
						.receiveMessage();
			} else {
				throw new RuntimeException(
						"Wait for connection state before receive.");
			}
		} catch (Exception e) {
			throw new RuntimeException("Error BTC11 Receiving message: "
					+ e.getMessage());
		}
		return messRec;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see sendersReceivers.bluetooth.states.BusinessToBluetoothCommInterface#reportProblemCode(int)
	 */
	public void reportProblemCode(int reason) {
		String message = "";
		try {
			if (reason == BluetoothToBusinessInterface.ERROR_GETTING_FRIENDLY_NAME) {
				message = "The name of the bluetooth device couldn't be obtained";
			} else if (reason == BluetoothToBusinessInterface.ERROR_IN_SERVICE_DISCOVERY) {
				message = "Error in service discovery";
				this.m_midlet.setActiveScreen(new AlertScreen(this.m_midlet,
						message, this.m_previousScreen));
			} else if (reason == BluetoothToBusinessInterface.ERROR_ON_INQUIRY_COMPLETE) {
				message = "Error in inquiry completed";
				this.m_midlet.setActiveScreen(new AlertScreen(this.m_midlet,
						message, this.m_previousScreen));
			} else if (reason == BluetoothToBusinessInterface.ERROR_RECEIVING_MESSAGE) {
				message = "Error receiving message";
				this.m_midlet.setActiveScreen(new AlertScreen(this.m_midlet,
						message, this.m_previousScreen));
			} else if (reason == BluetoothToBusinessInterface.ERROR_RUNNING_SERVICE_DISCOVERY) {
				message = "The application is not allowed to use connectivity applications";
				this.m_midlet.setActiveScreen(new AlertScreen(this.m_midlet,
						message, this.m_previousScreen));
			} else if (reason == BluetoothToBusinessInterface.ERROR_SENDING_MESSAGE) {
				message = "Error sending message";
				this.m_midlet.setActiveScreen(new AlertScreen(this.m_midlet,
						message, this.m_previousScreen));
			} else if (reason == BluetoothToBusinessInterface.ERROR_AUTHENTIFYING_REMOTE_DEVICE) {
				message = "Error authentifying the remote device.";
				this.m_midlet.setActiveScreen(new AlertScreen(this.m_midlet,
						message, this.m_previousScreen));
			} else if (reason == BluetoothToBusinessInterface.ERROR_AUTHORIZING_REMOTE_DEVICE) {
				message = "Error authorizing the remote device.";
				this.m_midlet.setActiveScreen(new AlertScreen(this.m_midlet,
						message, this.m_previousScreen));
			} else if (reason == BluetoothToBusinessInterface.ERROR_ENCRYPTING_CONNECTION) {
				message = "Error encrypting the connection.";
				this.m_midlet.setActiveScreen(new AlertScreen(this.m_midlet,
						message, this.m_previousScreen));
			} else if (reason == BluetoothToBusinessInterface.ERROR_BLUETOOTH_NOT_AVAILABLE) {
				message = "The bluetooth device is not active or not available.";
				this.m_midlet.setActiveScreen(new AlertScreen(this.m_midlet,
						message, this.m_previousScreen));
			} else {
				throw new RuntimeException("Invalid report problem code "
						+ reason);
			}
		} catch (Exception e) {
			throw new RuntimeException("Error BTC12 Reporting problem: "
					+ e.getMessage());
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see sendersReceivers.bluetooth.states.BusinessToBluetoothCommInterface#selectService(java.util.Hashtable)
	 */
	public synchronized String selectService(Hashtable bluetoothNames)
			throws InterruptedException {
		String selected = null;
		try {
			// Transform the hashtable in a vector, saving the positions in
			// another
			// hashtable.
			Vector names = new Vector();
			Hashtable positions = new Hashtable();
			Enumeration enumNames = bluetoothNames.elements();
			Enumeration enumAddress = bluetoothNames.keys();
			int i = 0;
			while (enumNames.hasMoreElements()) {
				names.addElement(enumNames.nextElement());
				positions.put(new Integer(i), enumAddress.nextElement());
				i++;
			}

			// Shows the list of devices.
			this.m_midlet.setActiveScreen(new FoundDevicesListScreen(
					this.m_midlet, this, names, this.m_nextScreen));

			// Waits the response.
			this.wait();

			// Process the response.
			selected = (String) positions
					.get(new Integer(this.m_selectedDevice));
		} catch (Exception e) {
			throw new RuntimeException("Error BTC13 Selecting device: "
					+ e.getMessage());
		}
		return selected;
	}

	/**
	 * Sends the given message via bluetooth. The communication has to be
	 * established before calling this method.
	 * 
	 * @param message
	 *            Message to send.
	 */
	public void sendMessage(String message) {
		try {
			if (this.m_senderReceiver.getState() == BluetoothToBusinessInterface.STATE_CONNECTED) {
				// Sending the message
				((SenderReceiver) this.m_senderReceiver).sendMessage(message);
			} else {
				throw new RuntimeException(
						"Wait for connection state before send.");
			}
		} catch (Exception e) {
			throw new RuntimeException("Error BTC14 Seding message: "
					+ e.getMessage());
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see sendersReceivers.bluetooth.states.BusinessToBluetoothCommInterface#serviceSearchCompleted(int)
	 */
	public void serviceSearchCompleted(int errorCode) {
		try {
			String message = "";
			if (errorCode == BluetoothToBusinessInterface.SEARCH_SERVICES_COMPLETED) {
				// Do nothing, the list of devices is being build at the same
				// time.
				message = "Ready to select a service.";
			} else if (errorCode == BluetoothToBusinessInterface.SEARCH_SERVICES_ERROR) {
				// Do nothing, the device does not have the required service.
				message = "Service search error.";
			} else if (errorCode == BluetoothToBusinessInterface.SEARCH_SERVICES_CANCELED) {
				message = "Service search canceled.";
				this.m_midlet.setActiveScreen(new AlertScreen(this.m_midlet,
						message, this.m_previousScreen));
			}
		} catch (Exception e) {
			throw new RuntimeException("Error BTC15 Search completed: "
					+ e.getMessage());
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see appl.museum.stable.UIToBluetoothInterface#setSelectedDeviceAndContinue(int)
	 */
	public synchronized void setSelectedDeviceAndContinue(int selectedIndex) {
		try {
			this.m_selectedDevice = selectedIndex;
			this.m_midlet.setActiveScreen(new SearchingDevicesScreen(
					this.m_midlet, this, "Connecting to device"));
			// Activates the selectService method to permit it to return the
			// selected id.
			this.notify();
		} catch (Exception e) {
			throw new RuntimeException("Error BTC16 selecting device: "
					+ e.getMessage());
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see appl.museum.stable.UIToBluetoothInterface#waitingServices(appl.museum.stable.BluetoothToUIInterface)
	 */
	public void waitingServices(BluetoothToUIInterface deviceScreen) {
		this.m_devicesScreen = deviceScreen;
	}
}
