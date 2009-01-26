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
import java.io.DataOutputStream;
import java.io.IOException;

import javax.bluetooth.RemoteDevice;
import javax.microedition.io.Connector;
import javax.microedition.io.StreamConnection;

import org.ow2.aspirerfid.nfc.midlet.sendersReceivers.bluetooth.BluetoothException;
import org.ow2.aspirerfid.nfc.midlet.sendersReceivers.bluetooth.BluetoothSenderReceiver;
import org.ow2.aspirerfid.nfc.midlet.sendersReceivers.bluetooth.BluetoothToBusinessInterface;

/**
 * This class represents the state when a bluetooth connection has been
 * established and is currently connected, but there is not operation with it.<br/>
 * The valid operations are disconnect, receive and send.<br/>This is the
 * implementation of the state pattern, and this class is a concrete state
 * participant.
 * 
 * @author <a href="mailto:angoca@yahoo.com">Andres Gomez</a>
 * @version 1.0.0 31/07/2008
 */
public class ConnectedState extends BluetoothState {
	/**
	 * Stream to the server.
	 */
	private StreamConnection m_connection = null;
	/**
	 * Stream to receive the message.
	 */
	private DataInputStream m_dataIn = null;
	/**
	 * Stream to send the message.
	 */
	private DataOutputStream m_dataOut = null;

	/**
	 * Constructor that associates the bluetooth context to the state. A context
	 * is the class that uses the states and it only has a state attribute to
	 * know the current state. The connection can be directly called if the
	 * rigth url connection if known, including the PMS (Protocol/service
	 * multiplexer) or the CN (channel number) for each type of connection,
	 * L2CAP or RFCOMM respectively. However, this implementation is RFCOMM
	 * oriented because the user doesn't have to deal with message properties,
	 * such as MTU, breaking up and reassembling messages.
	 * 
	 * @param senderReceiver
	 *            Bluetooth context.
	 * @param urlConnection
	 *            This is the url to connect via bluetooth.
	 * @throws BluetoothException
	 *             If there is a problem creating the connection.
	 */
	public ConnectedState(BluetoothSenderReceiver senderReceiver,
			String urlConnection) throws BluetoothException {
		super(senderReceiver);
		try {
			this.createStreams(urlConnection);
		} catch (BluetoothException e) {
			throw e;
		} catch (Exception e) {
			throw new RuntimeException("Error BT1 creating connected state: "
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
				"Error: Invalid call to cancel in connected state");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see sendersReceivers.bluetooth.states.BluetoothState#connect()
	 */
	public void connect() {
		// Nothing must call this method.
		throw new RuntimeException(
				"Error: Invalid call to connect in connected state");
	}

	/**
	 * Creates a stream to send messages.
	 * 
	 * @param urlConnection
	 *            This is the url to connect via bluetooth.
	 * @throws BluetoothException
	 *             When opening or creating an stream.
	 */
	private void createStreams(String urlConnection) throws BluetoothException {
		try {
			// Try to establish the connection.
			this.m_connection = (StreamConnection) Connector
					.open(urlConnection);
			RemoteDevice remoteDevice = RemoteDevice
					.getRemoteDevice(this.m_connection);
			// Performs authentication.
			if (this.getParent().getAuthenticateRemoteDevice()) {
				boolean authenticationResult = remoteDevice.authenticate();
				if (!authenticationResult) {
					this
							.getParent()
							.report(
									BluetoothToBusinessInterface.ERROR_AUTHENTIFYING_REMOTE_DEVICE);
				}
			}
			// Performs authorization.
			if (this.getParent().getAuthorizeRemoteDevice()) {
				boolean authorizationResult = remoteDevice
						.authorize(this.m_connection);
				if (!authorizationResult) {
					this
							.getParent()
							.report(
									BluetoothToBusinessInterface.ERROR_AUTHORIZING_REMOTE_DEVICE);
				}
			}
			// Performs encryption.
			if (this.getParent().getEncryptConnection()) {
				boolean encryptionResult = remoteDevice.encrypt(
						this.m_connection, true);
				if (!encryptionResult) {
					this
							.getParent()
							.report(
									BluetoothToBusinessInterface.ERROR_ENCRYPTING_CONNECTION);
				}
			}
		} catch (IOException e) {
			throw new BluetoothException("Error openning the URL");
		} finally {
			try { // Get the output stream.
				this.m_dataOut = new DataOutputStream(this.m_connection
						.openOutputStream());
			} catch (IOException e) {
				throw new BluetoothException("Error creating the output stream");
			} finally {
				try {
					// Get the input stream.
					this.m_dataIn = new DataInputStream(this.m_connection
							.openInputStream());
				} catch (IOException e) {
					throw new BluetoothException(
							"Error creating the input stream");
				}
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see sendersReceivers.bluetooth.states.BluetoothState#disconnect()
	 */
	public void disconnect() {
		try {
			this.changeState(new DisconnectedState(this.getParent()));
			// There is a try for each element, because we have to close all of
			// them. Even though if there is an exception.
			try {
				this.m_dataOut.close();
			} catch (IOException e) {
				// Not necessary, disconnecting anyway.
			}
			try {
				this.m_dataIn.close();
			} catch (IOException e) {
				// Not necessary, disconnecting anyway.
			}
			try {
				this.m_connection.close();
			} catch (IOException e) {
				// Not necessary, disconnecting anyway.
			}
		} catch (Exception e) {
			throw new RuntimeException(
					"Error BT2 disconnecing from connected state: "
							+ e.getMessage());
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see sendersReceivers.bluetooth.states.BluetoothState#receive()
	 */
	public String receive() {
		String message = null;
		try {
			ReceivingState state = new ReceivingState(this.getParent(),
					this.m_dataIn, this);
			this.changeState(state);
			message = state.doReceive();
		} catch (Exception e) {
			throw new RuntimeException("Error BT3 receiving connected state: "
					+ e.getMessage());
		}
		return message;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see sendersReceivers.bluetooth.states.BluetoothState#send(java.lang.String)
	 */
	public void send(String message) {
		try {
			SendingState state = new SendingState(this.getParent(),
					this.m_dataOut, this);
			this.changeState(state);
			state.doSend(message);
		} catch (Exception e) {
			throw new RuntimeException("Error BT4 sending connected state: "
					+ e.getMessage());
		}
	}
}
