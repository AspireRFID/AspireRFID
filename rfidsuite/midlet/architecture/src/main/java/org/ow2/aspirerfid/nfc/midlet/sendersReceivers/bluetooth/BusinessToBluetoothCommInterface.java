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

import org.ow2.aspirerfid.nfc.midlet.sendersReceivers.BusinessToCommInterface;

/**
 * The business layer can exchange information via this interface. The
 * components at the communication layer that wants to publish this bluetooth
 * has to implement this interface.
 * 
 * @author <a href="mailto:angoca@yahoo.com">Andres Gomez</a>
 * @version 1.0.0 31/07/2008
 */
public interface BusinessToBluetoothCommInterface extends
		BusinessToCommInterface {

	/**
	 * This method notifies the business layer that a device has been found in
	 * the device discovery. This is used to show a list of found devices, but
	 * the user can't make a choice.
	 * 
	 * @param friendlyName
	 *            Name of the found device.
	 * @param bluetoothAddress
	 *            Bluetooth address of the found device.
	 */
	void deviceDiscovered(String friendlyName, String bluetoothAddress);

	/**
	 * Informs that the bluetooth connection has been successful or the
	 * disconnection has been finished.
	 * 
	 * @param connection
	 *            True if informing connection, false if informing
	 *            disconnection.
	 */
	void informConnected(boolean connection);

	/**
	 * This method notifies that the inquiry has been completed. The code are in
	 * the BluetoothToBusinessInterface interface, and the values are
	 * ERROR_GETTING_FRIENDLY_NAME, ERROR_IN_SERVICE_DISCOVERY,
	 * ERROR_ON_INQUIRY_COMPLETE, ERROR_RECEIVING_MESSAGE,
	 * ERROR_RUNNIING_SERVICE_DISCOVERY and ERROR_SENDING_MESSAGE. This is used
	 * to informs to the user that the device discovery has been finished.
	 * 
	 * @param errorCode
	 *            Error code of the inquiry.
	 */
	void inquiryCompleted(int errorCode);

	/**
	 * Notifies that there are not found devices after the inquiry.
	 */
	void notFoundServices();

	/**
	 * Reports a problem to the interface in the way of a code.
	 * 
	 * @param reason
	 *            Code of the error.
	 */
	public void reportProblemCode(int reason);

	/**
	 * Receives a set of names and returns the selected one. This method has to
	 * be implemented with a wait if there is an interaction with the ui. This
	 * method should be synchronized and waits the notify of other method in the
	 * same class.
	 * 
	 * @param bluetoothNames
	 *            Set of device names. The key of the hashtable is the
	 *            identifier of a bluetooth device. The content is the name of a
	 *            bluetooth device.
	 * @return Selected device.
	 * @throws InterruptedException
	 *             If there is an error waiting the answer.
	 */
	String selectService(Hashtable bluetoothNames) throws InterruptedException;

	/**
	 * Notifies the interface that the service search has been completed. The
	 * possibles codes are in BluetoothToBusinessInterface and the values are
	 * SEARCH_SERVICES_COMPLETED, SEARCH_SERVICES_ERROR and
	 * SEARCH_SERVICES_CANCELED. This is used to permit the user to select a
	 * found device.
	 * 
	 * @param errorCode
	 *            Error code of the search devices.
	 */
	void serviceSearchCompleted(int errorCode);

}
