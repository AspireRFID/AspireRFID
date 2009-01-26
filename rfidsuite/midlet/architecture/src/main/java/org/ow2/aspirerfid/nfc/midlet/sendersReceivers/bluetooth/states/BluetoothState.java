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

import org.ow2.aspirerfid.nfc.midlet.sendersReceivers.bluetooth.BluetoothSenderReceiver;

/**
 * This abstract class represents a bluetooth state. This is the implementation
 * of the state pattern, and this class is the State participant.
 * 
 * @author <a href="mailto:angoca@yahoo.com">Andres Gomez</a>
 * @version 1.0.0 31/07/2008
 */
public abstract class BluetoothState {

	/**
	 * Context of the state.
	 */
	private final BluetoothSenderReceiver m_senderReceiver;

	/**
	 * Constructor that associates the bluetooth context to the state. A context
	 * is the class that uses the states and it only has a state attribute to
	 * know the current state.
	 * 
	 * @param senderReceiver
	 *            Bluetooth context.
	 */
	public BluetoothState(BluetoothSenderReceiver senderReceiver) {
		this.m_senderReceiver = senderReceiver;
	}

	/**
	 * Cancels the current operation (discovering devices, discovering services,
	 * sending data or receiving data.)
	 */
	public abstract void cancel();

	/**
	 * Calls the context to change the current state.
	 * 
	 * @param state
	 *            New bluetooth state.
	 */
	protected void changeState(BluetoothState state) {
		this.m_senderReceiver.changeState(state);
	}

	/**
	 * Connects to a remote device via bluetooth.
	 */
	public abstract void connect();

	/**
	 * Disconnects from a remote bluetooth device.
	 */
	public abstract void disconnect();

	/**
	 * Returns the context of the state.
	 * 
	 * @return Context that use the states.
	 */
	protected BluetoothSenderReceiver getParent() {
		return this.m_senderReceiver;
	}

	/**
	 * Receives a message from the remote device.
	 * 
	 * @return Received message.
	 */
	public abstract String receive();

	/**
	 * Sends a message to the remote device.
	 * 
	 * @param message
	 *            Message to send.
	 */
	public abstract void send(String message);
}
