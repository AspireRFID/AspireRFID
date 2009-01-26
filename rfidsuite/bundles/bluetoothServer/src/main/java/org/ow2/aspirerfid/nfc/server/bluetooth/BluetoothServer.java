/*
 *  Copyright (C) Aspire
 *
 *  This library is free software; you can redistribute it and/or
 *  modify it under the terms of the GNU Lesser General Public
 *  License as published by the Free Software Foundation; either
 *  version 2.1 of the License, or (at your option) any later version.
 *
 *  This library is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 *  Lesser General Public License for more details.
 *
 *  You should have received a copy of the GNU Lesser General Public
 *  License along with this library; if not, write to the Free Software
 *  Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301  USA
 */package org.ow2.aspirerfid.nfc.server.bluetooth;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UTFDataFormatException;
import java.util.Date;
import java.util.Enumeration;
import java.util.Hashtable;

import javax.bluetooth.BluetoothStateException;
import javax.bluetooth.LocalDevice;
import javax.bluetooth.UUID;
import javax.microedition.io.Connector;
import javax.microedition.io.StreamConnection;
import javax.microedition.io.StreamConnectionNotifier;

import org.ow2.aspirerfid.nfc.server.message.request.AbstractRequestMessage;
import org.ow2.aspirerfid.nfc.server.message.request.DefaultMessage;
import org.ow2.aspirerfid.nfc.server.message.response.AbstractResponseMessage;

/**
 * This is the bluetooth server that hears text messages from a client, and
 * sends back and answer. This class waits for clients to be connected
 * (AcceptAndOpen in the run method), and process the messages he sends.<br/>This
 * works with a set of bluetooth properties that are defined in a >ML file, and
 * loaded with the corresponding class (BluetoothProperties.) After that, there
 * is a message process register step, that indicates to bluetooth server which
 * messages will be process. After that, the bluetooth server can be activated.
 * Process a message consist in receive a message from a connected client,
 * analyze the first XML tag of it, and compare it with a list of messages
 * processors according to the that first tag. <br/>The messages have to be XML
 * to be processed in a different way (this can be overridden to process other
 * types of messages, not only text messages.)
 * 
 * @author <a href="mailto:angoca@yahoo.com">Andres Gomez</a>
 * @version 2.0 19/08/2008
 */
public class BluetoothServer implements Runnable {

	/**
	 * Bluetooth properties.
	 */
	private final BluetoothProperties m_bluetoothProperties;
	/**
	 * Class that calls the bluetooth server (the caller of this class to notify
	 * him when a communication has finished.)
	 */
	private BluetoothServerCaller m_caller;
	/**
	 * Message processors.
	 */
	private Hashtable<String, AbstractRequestMessage> m_messageProcessors;
	/**
	 * Flag to stop to process messages. Exit the while.
	 */
	private boolean m_stop = false;
	/**
	 * Bluetooth Connection.
	 */
	private StreamConnection m_conn;
	/**
	 * This is the notifier when a bluetooth client connects to the server.
	 */
	private StreamConnectionNotifier m_connectionNotifier;
	/**
	 * Stream to receive messages.
	 */
	private DataInputStream m_din;
	/**
	 * Stream to send messages.
	 */
	private DataOutputStream m_dout;

	/**
	 * Starts the bluetooth server with the default properties file.
	 * 
	 * @param caller
	 *            Class that call the bluetooth server.
	 * @throws Exception
	 *             If an exception was caught.
	 */
	public BluetoothServer(final BluetoothServerCaller caller) throws Exception {
		try {
			this.m_bluetoothProperties = new BluetoothProperties();

			this.startBluetooth(caller);
		} catch (Exception e) {
			System.out
					.println("Exception Occured at starting: " + e.toString());
			throw e;
		}
	}

	/**
	 * Builds a bluetooth server with a specific properties file..
	 * 
	 * @param caller
	 *            Class that calls the bluetooth server.
	 * @param bluetoothPropertiesFile
	 *            Name of the file where the properties are found.
	 * @throws Exception
	 *             If an exception was caught.
	 */
	public BluetoothServer(final BluetoothServerCaller caller,
			final String bluetoothPropertiesFile) throws Exception {
		try {
			if ((bluetoothPropertiesFile == null)
					|| (bluetoothPropertiesFile.compareTo("") == 0)) {
				throw new RuntimeException(
						"Invalid bluetooth properties file name");
			}
			this.m_bluetoothProperties = new BluetoothProperties(
					bluetoothPropertiesFile);

			this.startBluetooth(caller);
		} catch (Exception e) {
			System.out
					.println("Exception Occured at starting: " + e.toString());
			throw e;
		}
	}

	/**
	 * Builds a bluetooth server with the properties are in a inputstream given.
	 * 
	 * @param caller
	 *            Class that calls the bluetooth server.
	 * @param inputStream
	 *            Stream where the properties are.
	 * @throws Exception
	 *             If an exception was caught.
	 */
	public BluetoothServer(final BluetoothServerCaller caller,
			final InputStream inputStream) throws Exception {
		try {
			if ((inputStream == null)) {
				throw new RuntimeException(
						"Invalid bluetooth properties input stream");
			}
			this.m_bluetoothProperties = new BluetoothProperties(inputStream);

			this.startBluetooth(caller);
		} catch (Exception e) {
			System.out
					.println("Exception Occured at starting: " + e.toString());
			throw e;
		}
	}

	/**
	 * Configures the server and returns the URL to connect.
	 * 
	 * @return connection URL
	 */
	private String configureBluetooth() {
		String uuidString = this.m_bluetoothProperties
				.getProperty(BluetoothProperties.UUID);
		String name = this.m_bluetoothProperties
				.getProperty(BluetoothProperties.SERVICE_NAME);
		String authenticate = this.m_bluetoothProperties
				.getProperty(BluetoothProperties.AUTHENTICATE);
		String encrypt = this.m_bluetoothProperties
				.getProperty(BluetoothProperties.ENCRYPT);

		// UUID
		UUID uuid = new UUID(uuidString, false);

		// Connection's parameters
		String params = ";name=" + name + ";authenticate=" + authenticate
				+ ";encrypt=" + encrypt + ";";

		// The service url. The protocol used is RFCOMM (btspp://). Other
		// protocols are L2CAP (btl2cap://) and OBEX
		// (btgoep://localhost:uuid)

		String url = "btspp://localhost:" + uuid + params;
		return url;
	}

	/**
	 * @param m_conn
	 *            Connection.
	 * @param m_connectionNotifier
	 *            Notifier.
	 * @param m_din
	 *            input stream.
	 * @param m_dout
	 *            output stream.
	 * @param e
	 *            exception to analyze.
	 */
	private void processException(final Exception e) {
		System.out.println("Exception occured while processing messages: "
				+ e.toString());
		closeAll();
	}

	/**
	 * Close all the connections and streams.
	 */
	private void closeAll() {
		if (this.m_din != null) {
			try {
				this.m_din.close();
			} catch (Exception e1) {
				System.out.println("Exception occured while closing din: "
						+ e1.toString());
			}
		}
		if (this.m_dout != null) {
			try {
				this.m_dout.close();
			} catch (Exception e1) {
				System.out.println("Exception occured while closing dout: "
						+ e1.toString());
			}
		}
		if (this.m_conn != null) {
			try {
				this.m_conn.close();
			} catch (Exception e1) {
				System.out.println("Exception occured while closing conn: "
						+ e1.toString());
			}
		}
		if (this.m_connectionNotifier != null) {
			try {
				this.m_connectionNotifier.close();
			} catch (Exception e1) {
				System.out.println("Exception occured while closing notifier: "
						+ e1.toString());
			}
		}
	}

	/**
	 * Process message: receive and return.
	 * 
	 * @param din
	 *            Input stream.
	 * @param dout
	 *            Output stream.
	 * @throws IOException
	 *             If there is a problem at reading or writing.
	 */
	private void processMessage(final DataInputStream din,
			final DataOutputStream dout) throws IOException {
		// Receives a message
		String cmd = "";
		char c = din.readChar();
		while ((c > 0) && (c != '\n')) {
			cmd += c;
			c = din.readChar();
		}
		System.out.println(new Date(System.currentTimeMillis()).toString());
		System.out.println("Received: " + cmd);

		String messRet = this.processMessage(cmd);
		System.out.println("Response: " + messRet);

		// Sends a message
		try {
			dout.writeUTF(messRet);
		} catch (UTFDataFormatException e) {
			// The message is too long but it writes something to not
			// block the client. The client always waits a response!
			dout.writeUTF("");
			System.out
					.println("Problem sending the message: " + e.getMessage());
		}
	}

	/**
	 * Process the received message. Depending on the first tag of the XML
	 * message, it delegates the processing to the corresponding message
	 * processor, who has been previouly configured.
	 * 
	 * @param receivedMess
	 *            Received message.
	 * @return Message to send.
	 */
	@SuppressWarnings("unchecked")
	private String processMessage(final String receivedMess) {
		String messRet = "";
		AbstractRequestMessage processor = null;
		Enumeration<String> enumsKeys = this.m_messageProcessors.keys();
		Enumeration<AbstractRequestMessage> enumsElems = this.m_messageProcessors
				.elements();
		boolean found = false;
		// Analyze all the processors till found the right one.
		while (enumsKeys.hasMoreElements() && !found) {
			String tag = enumsKeys.nextElement();
			processor = enumsElems.nextElement();
			if (receivedMess.startsWith(tag)) {
				found = true;
			}
		}
		// There is not corresponding processor, then it uses the default one.
		if (processor == null) {
			processor = new DefaultMessage();
		}

		AbstractResponseMessage responseMessage = processor
				.getResponseMessage(receivedMess);
		messRet = responseMessage.getMessage();

		return messRet;
	}

	/**
	 * Adds a message processor to the set of processor. The tag param is the
	 * first tag of the XML file that describes the type of message, and the
	 * processor is the class that can process it.
	 * 
	 * @param processor
	 *            Message processor.
	 * @param tag
	 *            Type of tag.
	 */
	@SuppressWarnings("unchecked")
	public final void registerMessage(final AbstractRequestMessage processor,
			final String tag) {
		if (processor == null) {
			throw new RuntimeException("The message processor is null.");
		}
		this.m_messageProcessors.put(tag, processor);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Runnable#run()
	 */
	public final void run() {
		// This can be commented, but the server will do nothing, just answer OK
		// messages.
		if (this.m_messageProcessors.size() == 0) {
			throw new RuntimeException("No message processors defined.");
		}
		try {
			String url = this.configureBluetooth();

			System.out.println("");
			// Publish the service and waits for connections.
			System.out.println("Start advertising service...");
			// L2CAP returns L2CAPConnectionNotifier, RFCOMM returns
			// StreamConnectionNotifier and OBEX returns ClientSession.
			this.m_connectionNotifier = (StreamConnectionNotifier) Connector
					.open(url);
			System.out.println("Waiting for incoming connection...");
			// Inserts the service record into the SDDB and wait for incoming
			// connections.
			this.m_conn = this.m_connectionNotifier.acceptAndOpen();

			// Client connected. Receives the data (can send data too.)
			System.out.println("Client Connected...");
			this.m_din = this.m_conn.openDataInputStream();
			this.m_dout = this.m_conn.openDataOutputStream();
			this.m_stop = false;
			while (!this.m_stop) {
				this.processMessage(this.m_din, this.m_dout);
			}

		} catch (Exception e) {
			this.processException(e);
		} finally {
			this.m_caller.setFinished();
		}
	}

	/**
	 * Stops to process messages and closes all the connections. TODO There is a
	 * Thread, BluecoveShutdownHookThread, that does not stop at the same the
	 * bundles are stopped.
	 */
	public final void stop() {
		this.m_stop = true;
		closeAll();
	}

	/**
	 * Starts the bluetooth server and set it discoverable.
	 * 
	 * @param caller
	 *            Class that calls the bluetooth server.
	 */
	private void startBluetooth(final BluetoothServerCaller caller) {
		this.m_messageProcessors = new Hashtable<String, AbstractRequestMessage>();

		this.m_caller = caller;

		int mode = Integer.parseInt(this.m_bluetoothProperties
				.getProperty(BluetoothProperties.INQUIRY_MODE));
		try {

			LocalDevice localDevice = LocalDevice.getLocalDevice();
			System.out.println("Bluetooth address "
					+ localDevice.getBluetoothAddress());

			// Sets the local device visible to others devices.
			// The mode can be GIAC that is undefined in time. In the other
			// hand, the LIAC mode is limited in time, usually one minute.
			// TODO Andres can be there several devices consuming the same
			// service at the same time: two telephones requesting information.
			System.out.println("Setting device to be discoverable...");
			localDevice.setDiscoverable(mode);
		} catch (BluetoothStateException e) {
			throw new RuntimeException("Invalid bluetooth state: "
					+ e.getMessage());
		}
	}
}
