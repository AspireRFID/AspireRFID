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

/**
 * This is the interface that permits objects outside the communication layer to
 * access to the bluetooth services. The class that manage the states of the
 * communication has to implement this interface.
 * 
 * @author <a href="mailto:angoca@yahoo.com">Andres Gomez</a>
 * @version 1.0.0 31/07/2008
 */
public interface BluetoothToBusinessInterface {
	/**
	 * The device and service discovery has to be done again. Not desired device
	 * has been found.
	 */
	public static final String DISCOVER_AGAIN = "DISCOVER_AGAIN";
	/**
	 * The bluetooth device is not active or not available.
	 */
	public static final int ERROR_BLUETOOTH_NOT_AVAILABLE = 40;
	/**
	 * Represents the connected state. To device and valid service.
	 */
	public static final int STATE_CONNECTED = 4;
	/**
	 * Represents the device discovery state. When the application is looking
	 * for bluetooth devices.
	 */
	public static final int STATE_DEVICE_DISCOVERY = 2;
	/**
	 * Represents the disconnected state.
	 */
	public static final int STATE_DISCONNECTED = 1;
	/**
	 * Represents the receiving state. Receiving a message from the connected
	 * device.
	 */
	public static final int STATE_RECEIVING = 6;
	/**
	 * Represents the sending state. Sending a message to the connected device.
	 */
	public static final int STATE_SENDING = 5;
	/**
	 * Represents the service discovery state. When the application is searching
	 * in the found devices for valid services.
	 */
	public static final int STATE_SERVICE_DISCOVERY = 3;
	/**
	 * Error authentifying the remote device.
	 */
	public final int ERROR_AUTHENTIFYING_REMOTE_DEVICE = 37;
	/**
	 * Error authorizing the remote device.
	 */
	public final int ERROR_AUTHORIZING_REMOTE_DEVICE = 38;
	/**
	 * Error encrypting connection.
	 */
	public final int ERROR_ENCRYPTING_CONNECTION = 39;
	/**
	 * Error getting the friendly name.
	 */
	public final int ERROR_GETTING_FRIENDLY_NAME = 31;
	/**
	 * Bluetooth exception in service discovery
	 */
	public final int ERROR_IN_SERVICE_DISCOVERY = 35;
	/**
	 * Error when inquiring completed.
	 */
	public final int ERROR_ON_INQUIRY_COMPLETE = 32;
	/**
	 * Error receiving message
	 */
	public final int ERROR_RECEIVING_MESSAGE = 33;
	/**
	 * Error trying to establish communication with the selected service.
	 */
	public final int ERROR_RUNNING_SERVICE_DISCOVERY = 36;
	/**
	 * Error sending message
	 */
	public final int ERROR_SENDING_MESSAGE = 34;
	/**
	 * Represents that the search devices has been canceled by the user.
	 */
	public final int SEARCH_DEVICES_CANCELED = 13;
	/**
	 * Represents that the search devices has been completed successfully.
	 */
	public final int SEARCH_DEVICES_COMPLETED = 11;
	/**
	 * Represents that the search devices has been finished because of an error.
	 */
	public final int SEARCH_DEVICES_ERROR = 12;
	/**
	 * Represents that the search services has been canceled by the user.
	 */
	public final int SEARCH_SERVICES_CANCELED = 22;
	/**
	 * Represents that the search services has been completed successfully.
	 */
	public final int SEARCH_SERVICES_COMPLETED = 21;
	/**
	 * Represents that the search services has been finished due an error.
	 */
	public final int SEARCH_SERVICES_ERROR = 23;

	/**
	 * Adds a bluetooth address of a remote device to connect to it. <br/> The
	 * retrieveDevices method of the DiscoveryAgent class returns null when
	 * asking for cached or pre-known devices.
	 * 
	 * @param bluetoothRemoteAddress
	 *            Bluetooth address of the remote device.
	 */
	public void addBluetoothRemoteAddress(String bluetoothRemoteAddress);

	/**
	 * Cancel the current operation.
	 */
	public void cancel();

	/**
	 * Returns the address of the local device to identify it.
	 * 
	 * @return Bluetooth address. When is Null, that means the address couldn't
	 *         be obtained.
	 * @throws BluetoothException
	 *             Error asking the local bluetooth address.
	 */
	public String getLocalAddress() throws BluetoothException;

	/**
	 * Returns the name of the local device.
	 * 
	 * @return Friendly name of the local device.
	 * @throws BluetoothException
	 *             Error asking the local friendly name.
	 */
	public String getName() throws BluetoothException;

	/**
	 * Returns the name of the remote device.
	 * 
	 * @return Remote device's name.
	 */
	public String getRemoteName();

	/**
	 * Returns the current state of the bluetooth. The values are
	 * DISCONNECTED_STATE, DEVICE_DISCOVERY_STATE, SERVICE_DISCOVERY_STATE,
	 * CONNECTED_STATE, SENDING_STATE and RECEIVING_STATE.
	 * 
	 * @return Current bluetooth state.
	 */
	public int getState();

	/**
	 * Returns true if the connection will be with discovery. With discovery is
	 * when there are not remote devices defined.
	 * 
	 * @return True if the connection will be with discovery, otherwise false.
	 */
	public boolean isConnectionWithDiscovery();

	/**
	 * Sets the access code for the local discovery agent.
	 * 
	 * @param accessCode
	 *            The access code. DiscoveryAgent.GIAC or DiscoveryAgent.LIAC.
	 *            Other number is invalid and throws and RuntimeException.
	 */
	public void setAccessCode(int accessCode);

	/**
	 * Establishes if the remote device has to authenticated. By default is
	 * false. This operation is applicable before try to connect.
	 * 
	 * @param authenticate
	 *            True if authentication has to be performed.
	 */
	public void setAuthenticateRemoteDevice(boolean authenticate);

	/**
	 * Establishes if the remote device has to be authorize. By default is
	 * false. This operation is applicable before try to connect.
	 * 
	 * @param authorize
	 *            True if authorization has to be performed.
	 */
	public void setAuthorizeRemoteDevice(boolean authorize);

	/**
	 * Sets the param that permits to automatically connects when the there is
	 * only one valid found device.
	 * 
	 * @param automatic
	 *            True to connects automatically.
	 */
	public void setAutomaticConnectionWhenJustOne(boolean automatic);

	/**
	 * Establishes if the connection has be to encrypted. By default is false.
	 * This operation is applicable before try to connect.
	 * 
	 * @param encrypt
	 *            True if the connection has to be performed.
	 */
	public void setEncryptConnection(boolean encrypt);

	/**
	 * Establishes the uuid to connect.
	 * 
	 * @param uuid
	 *            UUID to connect via bluetooth.
	 */
	public void setUUID(String uuid);
}
