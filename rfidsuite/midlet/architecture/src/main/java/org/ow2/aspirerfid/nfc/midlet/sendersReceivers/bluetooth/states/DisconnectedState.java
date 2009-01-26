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

import java.util.Hashtable;

import org.ow2.aspirerfid.nfc.midlet.sendersReceivers.bluetooth.BluetoothSenderReceiver;

/**
 * This class represents the idle state of bluetooth when there is no
 * connections to any device. <br/>The valid operation for this class is
 * connect.<br/>This is the implementation of the state pattern, and this class
 * is a concrete state participant.
 * 
 * @author <a href="mailto:angoca@yahoo.com">Andres Gomez</a>
 * @version 1.0.0 31/07/2008
 */
public class DisconnectedState extends BluetoothState {

	/**
	 * Constructor that associates the bluetooth context to the state. A context
	 * is the class that uses the states and it only has a state attribute to
	 * know the current state.
	 * 
	 * @param senderReceiver
	 *            Bluetooth context.
	 */
	public DisconnectedState(BluetoothSenderReceiver senderReceiver) {
		super(senderReceiver);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.ow2.aspire.rfid.nfc.midlet.sendersReceivers.bluetooth.states.BluetoothState#cancel()
	 */
	public void cancel() {
		// Nothing must call this method.
		throw new RuntimeException(
				"Error: Invalid call to cancel in disconnected state");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see sendersReceivers.bluetooth.states.BluetoothState#connect()
	 */
	public void connect() {
		try {
			Hashtable bluetoothRemoteDevices = this.getParent()
					.getBluetoothRemoteAddress();
			if (bluetoothRemoteDevices.size() == 0) {
				// Starts the device discovery.
				this.changeState(new DeviceDiscoveryState(this.getParent()));
			} else {
				// Starts the service discovery with the given devices.
				this.changeState(new ServiceDiscoveryState(this.getParent(),
						bluetoothRemoteDevices));
			}
		} catch (Exception e) {
			throw new RuntimeException(
					"Error BT8 connecting disconnect state: " + e.getMessage());
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
				"Error: Invalid call to disconnect in disconnected state");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see sendersReceivers.bluetooth.states.BluetoothState#receive()
	 */
	public String receive() {
		// Nothing must call this method.
		throw new RuntimeException(
				"Error: Invalid call to receive in disconnected state");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see sendersReceivers.bluetooth.states.BluetoothState#send(java.lang.String)
	 */
	public void send(String message) {
		// Nothing must call this method.
		throw new RuntimeException(
				"Error: Invalid call to send in disconnected state");
	}
}
