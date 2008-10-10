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

import java.util.Hashtable;

import javax.bluetooth.BluetoothStateException;
import javax.bluetooth.DiscoveryAgent;
import javax.bluetooth.LocalDevice;
import javax.bluetooth.RemoteDevice;

import org.ow2.aspirerfid.nfc.midlet.generic.SenderReceiver;
import org.ow2.aspirerfid.nfc.midlet.sendersReceivers.bluetooth.states.BluetoothState;
import org.ow2.aspirerfid.nfc.midlet.sendersReceivers.bluetooth.states.ConnectedState;
import org.ow2.aspirerfid.nfc.midlet.sendersReceivers.bluetooth.states.DeviceDiscoveryState;
import org.ow2.aspirerfid.nfc.midlet.sendersReceivers.bluetooth.states.DisconnectedState;
import org.ow2.aspirerfid.nfc.midlet.sendersReceivers.bluetooth.states.DummyRemoteDevice;
import org.ow2.aspirerfid.nfc.midlet.sendersReceivers.bluetooth.states.ReceivingState;
import org.ow2.aspirerfid.nfc.midlet.sendersReceivers.bluetooth.states.SendingState;
import org.ow2.aspirerfid.nfc.midlet.sendersReceivers.bluetooth.states.ServiceDiscoveryState;

/**
 * This is the sender receiver that communicates with a remote server via
 * Bluetooth. The different states of the bluetooth connection are defined as
 * "states" based on the State Pattern (Gamma.) This class is the pattern or
 * context of all the states. The abstract state class is BluetoothState. <br/>
 * To connect to a bluetooth device the application has to wait, because the
 * connection is not immediate (This is done in other Thread.) If another method
 * is called before the connection is finished, an RuntimeException will be
 * raise. TODO to create another thread and to manage all the operations in the
 * other thread. The thread can be in wait(), and a variable indicates the
 * operation that has to perform (switch) after a notify. Analyze the cost? is
 * it expensive and necessary?
 * 
 * @author <a href="mailto:angoca@yahoo.com">Andres Gomez</a>
 * @version 1.0.0 31/07/2008
 */
public class BluetoothSenderReceiver extends SenderReceiver implements
		BluetoothToBusinessInterface {

	/**
	 * Instance (Solitary)
	 */
	private static BluetoothSenderReceiver instance = null;

	/**
	 * Returns the unique instance of the bluetooth sender.
	 * 
	 * @param caller
	 *            Caller class of bluetooth that uses this type of sender
	 *            receiver.
	 * @return Instance.
	 */
	public static BluetoothSenderReceiver getInstance(
			BusinessToBluetoothCommInterface caller) {
		try {
			if (BluetoothSenderReceiver.instance == null) {
				BluetoothSenderReceiver.instance = new BluetoothSenderReceiver(
						caller);
			}
		} catch (Exception e) {
			throw new RuntimeException("Error BT16 getting instance: "
					+ e.getMessage());
		}
		return BluetoothSenderReceiver.instance;
	}

	/**
	 * Access code to make the inquiry.
	 */
	private int m_accessCode = 0;
	/**
	 * Authenticate remote device.
	 */
	private boolean m_authenticate = false;
	/**
	 * Authorize remote device.
	 */
	private boolean m_authorize = false;
	/**
	 * Automatic connection when just one valid found device.
	 */
	private boolean m_automaticConnection = false;
	/**
	 * Set of bluetooth server to connect.
	 */
	private Hashtable m_bluetoothRemoteDevices = null;
	/**
	 * Current bluetooth state.
	 */
	private BluetoothState m_bluetoothState = null;
	/**
	 * Encrypt connection.
	 */
	private boolean m_encrypt = false;
	/**
	 * Name of the remote device.
	 */
	private String m_remoteName = null;
	/**
	 * This is the service identification. It was generated automatically. This
	 * can be generated with the uuidgen of http://jug.safehaus.org/
	 */
	private String m_uuid = null;

	/**
	 * Constructor that associates the caller class with this sender receiver.
	 * 
	 * @param caller
	 *            Caller class of bluetooth that uses this type of sender
	 *            receiver.
	 */
	private BluetoothSenderReceiver(BusinessToBluetoothCommInterface caller) {
		super(caller);
		try {
			this.changeState(new DisconnectedState(this));
			this.m_bluetoothRemoteDevices = new Hashtable();
			this.m_accessCode = DiscoveryAgent.GIAC;
		} catch (Exception e) {
			throw new RuntimeException("Error BT17 creating sender: "
					+ e.getMessage());
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see sendersReceivers.bluetooth.states.BluetoothToBusinessInterface#addBluetoothRemoteAddress(java.lang.String)
	 */
	public void addBluetoothRemoteAddress(String bluetoothRemoteAddress) {
		try {
			RemoteDevice device = DummyRemoteDevice
					.getOneInstance(bluetoothRemoteAddress);
			this.m_bluetoothRemoteDevices.put(bluetoothRemoteAddress, device);
		} catch (Exception e) {
			throw new RuntimeException("Error BT18 adding BT address: "
					+ e.getMessage());
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see sendersReceivers.bluetooth.states.BluetoothToBusinessInterface#cancel()
	 */
	public void cancel() {
		try {
			this.m_bluetoothState.cancel();
		} catch (Exception e) {
			throw new RuntimeException("Error BT19 canceling "
					+ this.m_bluetoothState + "; " + e.getMessage());
		}
	}

	/**
	 * Change the current bluetooth state.
	 * 
	 * @param state
	 *            New bluetooth state.
	 */
	public void changeState(BluetoothState state) {
		this.m_bluetoothState = state;
	}

	/**
	 * This method notifies the business layer that a device has been found in
	 * the device discovery.
	 * 
	 * @param friendlyName
	 *            Name of the found device.
	 * @param bluetoothAddress
	 *            Bluetooth address of the found device.
	 */
	public void deviceDiscovered(String friendlyName, String bluetoothAddress) {
		try {
			if (this.getCallerClass() != null) {
				((BusinessToBluetoothCommInterface) this.getCallerClass())
						.deviceDiscovered(friendlyName, bluetoothAddress);
			} else {
				throw new RuntimeException("There is not a caller class.");
			}
		} catch (Exception e) {
			throw new RuntimeException("Error BT20 device discovered: "
					+ e.getMessage());
		}
	}

	/**
	 * Returns the access code for the inquiry.
	 * 
	 * @return Access code. DiscoveryAgent.GIAC or DiscoveryAgent.LIAC.
	 */
	public int getAccessCode() {
		return this.m_accessCode;
	}

	/**
	 * @return If authentication has to be performed.
	 */
	public boolean getAuthenticateRemoteDevice() {
		return this.m_authenticate;
	}

	/**
	 * @return If authorization has to be performed.
	 */
	public boolean getAuthorizeRemoteDevice() {
		return this.m_authorize;
	}

	/**
	 * @return If the sender receiver has to connect to bluetooth directly when
	 *         just one valid device found.
	 */
	public boolean getAutomaticConnection() {
		return this.m_automaticConnection;
	}

	/**
	 * Returns all the defined remote devices.
	 * 
	 * @return Hashtable with all the RemoteDevices.
	 */
	public Hashtable getBluetoothRemoteAddress() {
		return this.m_bluetoothRemoteDevices;
	}

	/**
	 * @return If the connection has to be encrypted.
	 */
	public boolean getEncryptConnection() {
		return this.m_encrypt;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see sendersReceivers.bluetooth.states.BluetoothToBusinessInterface#getLocalAddress()
	 */
	public String getLocalAddress() throws BluetoothException {
		String address = null;
		try {
			address = LocalDevice.getLocalDevice().getBluetoothAddress();
		} catch (BluetoothStateException e) {
			throw new BluetoothException(
					"Problem getting the bluetooth address.");
		}
		return address;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see sendersReceivers.bluetooth.states.BluetoothToBusinessInterface#getName()
	 */
	public String getName() throws BluetoothException {
		String name = null;
		try {
			name = LocalDevice.getLocalDevice().getFriendlyName();
		} catch (BluetoothStateException e) {
			throw new BluetoothException("Problem getting the bluetooth name.");
		} catch (Exception e) {
			throw new RuntimeException("Error BT30 getting local name: "
					+ e.getMessage());
		}
		return name;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see sendersReceivers.bluetooth.states.BluetoothToBusinessInterface#getRemoteName()
	 */
	public String getRemoteName() {
		return this.m_remoteName;
	}

	/**
	 * Returns the selected device by the user. The method receives a Hashtable
	 * with the bluetooth address as key, and the bluetooth friendly name as
	 * content.
	 * 
	 * @param bluetoothNames
	 *            Set of names of the found services. The key is the bluetooth
	 *            address and the content is the bluetooth friendly name.
	 * @return bluetooth address of the selected device by the user.
	 */
	public String getSelectedDevice(Hashtable bluetoothNames) {
		String bluetoothAddress = null;
		try {
			bluetoothAddress = ((BusinessToBluetoothCommInterface) this
					.getCallerClass()).selectService(bluetoothNames);
		} catch (Exception e) {
			throw new RuntimeException("Error BT21 calling caller class: "
					+ e.getMessage());
		}
		if (!bluetoothNames.containsKey(bluetoothAddress)
				&& (bluetoothAddress
						.compareTo(BluetoothToBusinessInterface.DISCOVER_AGAIN) != 0)) {
			throw new RuntimeException(
					"Invalid the selected bluetooth device ("
							+ bluetoothAddress + ")");
		}
		return bluetoothAddress;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see sendersReceivers.bluetooth.states.BluetoothCommServiceInterface#getState()
	 */
	public int getState() {
		int state = 0;
		if (this.m_bluetoothState instanceof DisconnectedState) {
			state = BluetoothToBusinessInterface.STATE_DISCONNECTED;
		} else if (this.m_bluetoothState instanceof DeviceDiscoveryState) {
			state = BluetoothToBusinessInterface.STATE_DEVICE_DISCOVERY;
		} else if (this.m_bluetoothState instanceof ServiceDiscoveryState) {
			state = BluetoothToBusinessInterface.STATE_SERVICE_DISCOVERY;
		} else if (this.m_bluetoothState instanceof ConnectedState) {
			state = BluetoothToBusinessInterface.STATE_CONNECTED;
		} else if (this.m_bluetoothState instanceof SendingState) {
			state = BluetoothToBusinessInterface.STATE_SENDING;
		} else if (this.m_bluetoothState instanceof ReceivingState) {
			state = BluetoothToBusinessInterface.STATE_RECEIVING;
		}
		if (state == 0) {
			throw new RuntimeException("Invalid bluetooth state");
		}
		return state;
	}

	/**
	 * Returns the UUID of the service to connect via bluetooth.
	 * 
	 * @return UUID of the service.
	 */
	public String getUUID() {
		return this.m_uuid;
	}

	/**
	 * Inform to the caller that the connection has been successful.
	 */
	public void informConnected() {
		try {
			((BusinessToBluetoothCommInterface) this.getCallerClass())
					.informConnected(true);
		} catch (Exception e) {
			throw new RuntimeException("Error BT22 informing connection: "
					+ e.getMessage());
		}
	}

	/**
	 * This method notifies that the inquiry has been completed.
	 * 
	 * @param errorCode
	 *            Error code of the inquiry.
	 */
	public void inquiryCompleted(int errorCode) {
		if ((errorCode != BluetoothToBusinessInterface.SEARCH_DEVICES_COMPLETED)
				&& (errorCode != BluetoothToBusinessInterface.SEARCH_DEVICES_ERROR)
				&& (errorCode != BluetoothToBusinessInterface.SEARCH_DEVICES_CANCELED)) {
			throw new RuntimeException(
					"Error invalid error code for inquiry completed");
		}
		try {
			((BusinessToBluetoothCommInterface) this.getCallerClass())
					.inquiryCompleted(errorCode);
		} catch (Exception e) {
			throw new RuntimeException("Error BT23 inqury completed: "
					+ e.getMessage());
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see sendersReceivers.bluetooth.states.BluetoothToBusinessInterface#isConnectionWithDiscovery()
	 */
	public boolean isConnectionWithDiscovery() {
		boolean value = false;
		try {
			if (this.m_bluetoothRemoteDevices.isEmpty()) {
				value = true;
			}
		} catch (Exception e) {
			throw new RuntimeException(
					"Error BT24 demanding connection state: " + e.getMessage());
		}

		return value;
	}

	/**
	 * Notifies the interface when not found devices.
	 */
	public void notFoundServices() {
		try {
			((BusinessToBluetoothCommInterface) this.getCallerClass())
					.notFoundServices();
		} catch (Exception e) {
			throw new RuntimeException(
					"Error BT25 notifying not found devices: " + e.getMessage());
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see core.SenderReceiver#receiveMessage()
	 */
	public String receiveMessage() {
		try {
			return this.m_bluetoothState.receive();
		} catch (Exception e) {
			throw new RuntimeException("Error BT26 receiving message: "
					+ e.getMessage());
		}

	}

	/**
	 * Reports a message to the screen.
	 * 
	 * @param reason
	 *            Message to show.
	 */
	public void report(int reason) {
		if ((reason != BluetoothToBusinessInterface.ERROR_GETTING_FRIENDLY_NAME)
				&& (reason != BluetoothToBusinessInterface.ERROR_RECEIVING_MESSAGE)
				&& (reason != BluetoothToBusinessInterface.ERROR_SENDING_MESSAGE)
				&& (reason != BluetoothToBusinessInterface.ERROR_ON_INQUIRY_COMPLETE)
				&& (reason != BluetoothToBusinessInterface.ERROR_RUNNING_SERVICE_DISCOVERY)
				&& (reason != BluetoothToBusinessInterface.ERROR_IN_SERVICE_DISCOVERY)
				&& (reason != BluetoothToBusinessInterface.ERROR_AUTHENTIFYING_REMOTE_DEVICE)
				&& (reason != BluetoothToBusinessInterface.ERROR_AUTHORIZING_REMOTE_DEVICE)
				&& (reason != BluetoothToBusinessInterface.ERROR_ENCRYPTING_CONNECTION)
				&& (reason != BluetoothToBusinessInterface.ERROR_BLUETOOTH_NOT_AVAILABLE)) {
			throw new RuntimeException("Invalid error reason: " + reason);
		}
		try {
			((BusinessToBluetoothCommInterface) this.getCallerClass())
					.reportProblemCode(reason);
		} catch (Exception e) {
			throw new RuntimeException("Error BT27 reporting problem " + reason
					+ ": " + e.getMessage());
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see core.SenderReceiver#sendMessage(java.lang.String)
	 */
	public void sendMessage(String message) {
		try {
			this.m_bluetoothState.send(message);
		} catch (Exception e) {
			throw new RuntimeException("Error BT28 sending message: "
					+ e.getMessage());
		}

	}

	/**
	 * Notifies the interface that the service search has been completed.
	 * 
	 * @param errorCode
	 *            Error code of the search services.
	 */
	public void serviceSearchCompleted(int errorCode) {
		if ((errorCode != BluetoothToBusinessInterface.SEARCH_SERVICES_COMPLETED)
				&& (errorCode != BluetoothToBusinessInterface.SEARCH_SERVICES_ERROR)
				&& (errorCode != BluetoothToBusinessInterface.SEARCH_SERVICES_CANCELED)) {
			throw new RuntimeException("Invalid errorCode for search completed");
		}
		try {
			((BusinessToBluetoothCommInterface) this.getCallerClass())
					.serviceSearchCompleted(errorCode);
		} catch (Exception e) {
			throw new RuntimeException(
					"Error BT29 notifying search completed: " + e.getMessage());
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see sendersReceivers.bluetooth.states.BluetoothCommServiceInterface#setAccessCode(int)
	 */
	public void setAccessCode(int accessCode) {
		if ((accessCode != DiscoveryAgent.GIAC)
				&& (accessCode != DiscoveryAgent.LIAC)) {
			throw new RuntimeException(
					"Invalid access code for the discovery manger");
		}
		this.m_accessCode = accessCode;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see sendersReceivers.bluetooth.states.BluetoothToBusinessInterface#setAuthenticaRemoteDevice(boolean)
	 */
	public void setAuthenticateRemoteDevice(boolean authenticate) {
		this.m_authenticate = authenticate;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see sendersReceivers.bluetooth.states.BluetoothToBusinessInterface#setAuthorizeRemoteDevice(boolean)
	 */
	public void setAuthorizeRemoteDevice(boolean authorize) {
		this.m_authorize = authorize;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see sendersReceivers.bluetooth.states.BluetoothToBusinessInterface#setAutomaticConnectionWhenJustOne(boolean)
	 */
	public void setAutomaticConnectionWhenJustOne(boolean automatic) {
		this.m_automaticConnection = automatic;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see sendersReceivers.bluetooth.states.BluetoothToBusinessInterface#setEncrypConnection(boolean)
	 */
	public void setEncryptConnection(boolean encrypt) {
		this.m_encrypt = encrypt;
	}

	/**
	 * Establishes the name of the remote device.
	 * 
	 * @param name
	 *            Remote device's name.
	 */
	public void setHostName(String name) {
		this.m_remoteName = name;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see sendersReceivers.bluetooth.states.BluetoothToBusinessInterface#setUUID(java.lang.String)
	 */
	public void setUUID(String uuid) {
		this.m_uuid = uuid;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see core.SenderReceiver#startSender()
	 */
	public void startSender() {
		if (this.getUUID() == null) {
			throw new RuntimeException("Not UUID defined.");
		}
		try {
			this.m_bluetoothState.connect();
		} catch (Exception e) {
			throw new RuntimeException("Error BT30 starting sender: "
					+ e.getMessage());
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see core.SenderReceiver#stopSender()
	 */
	public void stopSender() {
		try {
			this.m_bluetoothState.disconnect();
			((BusinessToBluetoothCommInterface) this.getCallerClass())
					.informConnected(false);
		} catch (Exception e) {
			throw new RuntimeException("Error BT31 stopping sender: "
					+ e.getMessage());
		}
	}
}
