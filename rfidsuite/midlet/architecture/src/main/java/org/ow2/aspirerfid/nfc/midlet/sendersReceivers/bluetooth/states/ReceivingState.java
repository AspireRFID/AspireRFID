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

import java.io.DataInputStream;
import java.io.IOException;

import org.ow2.aspirerfid.nfc.midlet.sendersReceivers.bluetooth.BluetoothSenderReceiver;
import org.ow2.aspirerfid.nfc.midlet.sendersReceivers.bluetooth.BluetoothToBusinessInterface;

/**
 * This class represents the state when the device is receiving something via
 * bluetooth. <br/>There are not valid operations to call from this class.<br/>This
 * is the implementation of the state pattern, and this class is a concrete
 * state participant.
 * 
 * @author <a href="mailto:angoca@yahoo.com">Andres Gomez</a>
 * @version 1.0.0 31/07/2008
 */
public class ReceivingState extends BluetoothState implements Runnable {

	/**
	 * Caller state.
	 */
	private ConnectedState m_connectedState = null;
	/**
	 * Data input stream to receive the message.
	 */
	private DataInputStream m_inputStream = null;
	/**
	 * Received message.
	 */
	private String m_receivedMessage = null;
	/**
	 * Thread to receive the data.
	 */
	private Thread m_thread = null;

	/**
	 * Constructor that associates the bluetooth context to the state. A context
	 * is the class that uses the states and it only has a state attribute to
	 * know the current state. The input stream is already open.
	 * 
	 * @param senderReceiver
	 *            Bluetooth context.
	 * @param inputStream
	 *            Input stream to receive the message.
	 * @param connectedState
	 *            Connected state. Caller of this state.
	 */
	public ReceivingState(BluetoothSenderReceiver senderReceiver,
			DataInputStream inputStream, ConnectedState connectedState) {
		super(senderReceiver);
		try {
			this.m_inputStream = inputStream;
			this.m_connectedState = connectedState;
			this.m_thread = new Thread(this);
		} catch (Exception e) {
			throw new RuntimeException("Error BT10 creating receiving state: "
					+ e.getMessage());
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see sendersReceivers.bluetooth.states.BluetoothState#cancel()
	 */
	public void cancel() {
		// Nothing must call this method.
		throw new RuntimeException(
				"Error: Invalid call to cancel in receiving state");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see sendersReceivers.bluetooth.states.BluetoothState#connect()
	 */
	public void connect() {
		// Nothing must call this method.
		throw new RuntimeException(
				"Error: Invalid call to connect in receiving state");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see sendersReceivers.bluetooth.states.BluetoothState#disconnect()
	 */
	public void disconnect() {
		// Nothing must call this method.
		throw new RuntimeException(
				"Error: Invalid call to disconnect in receiving state");
	}

	/**
	 * Receives a string message.
	 * 
	 * @return Message received.
	 */
	public synchronized String doReceive() {
		// TODO verify connection before try to receive.
		try {
			this.m_thread.start();
			try {
				this.wait();
			} catch (InterruptedException e) {
				// Does not matter.
			}
		} catch (Exception e) {
			this.getParent().report(
					BluetoothToBusinessInterface.ERROR_RECEIVING_MESSAGE);
		}
		return this.m_receivedMessage;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see sendersReceivers.bluetooth.states.BluetoothState#receive()
	 */
	public String receive() {
		// Nothing must call this method.
		throw new RuntimeException(
				"Error: Invalid call to receive in receiving state");
	}

	/**
	 * Receives the data in other thread.
	 */
	public synchronized void run() {
		try {
			this.m_receivedMessage = this.m_inputStream.readUTF();
		} catch (IOException e) {
			this.getParent().report(
					BluetoothToBusinessInterface.ERROR_RECEIVING_MESSAGE);
		} catch (Exception e) {
			this.getParent().report(
					BluetoothToBusinessInterface.ERROR_RECEIVING_MESSAGE);
		} finally {
			try {
				this.changeState(this.m_connectedState);
				this.notify();
			} catch (Exception e) {
				throw new RuntimeException(
						"Error BT11 notifying receiving state: "
								+ e.getMessage());
			}

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
				"Error: Invalid call to send in receiving state");
	}
}
