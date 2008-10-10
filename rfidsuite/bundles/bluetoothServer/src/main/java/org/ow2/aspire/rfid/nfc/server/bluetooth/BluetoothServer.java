package org.ow2.aspire.rfid.nfc.server.bluetooth;

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

import org.ow2.aspire.rfid.nfc.server.message.request.AbstractRequestMessage;
import org.ow2.aspire.rfid.nfc.server.message.request.DefaultMessage;
import org.ow2.aspire.rfid.nfc.server.message.response.AbstractResponseMessage;

/**
 * This is an example of the bluetooth server that hears messages from a client.
 *
 * @author <a href="mailto:angoca@yahoo.com">Andres Gomez</a>
 */
public class BluetoothServer implements Runnable {

    /**
     * Bluetooth properties.
     */
    private final BluetoothProperties m_bluetoothProperties;
    /**
     * Class that calls the bluetooth server.
     */
    private BluetoothServerCaller m_caller;
    /**
     * Message processors.
     */
    private Hashtable<String, AbstractRequestMessage> m_messageProcessors;

    /**
     * Starts the bluetooth server with the default properties file.
     *
     * @param caller
     *                Class that call the bluetooth server.
     * @throws Exception
     *                 If an exception was caught.
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
     * Default constructor.
     *
     * @param caller
     *                Class that calls the bluetooth server.
     * @param bluetoothPropertiesFile
     *                Name of the file where the properties are found.
     * @throws Exception
     *                 If an exception was caught.
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

    public BluetoothServer(final BluetoothServerCaller caller,
    	    final InputStream bluetoothPropertiesFile) throws Exception {
    	try {
    	    if ((bluetoothPropertiesFile == null)
    		    ) {
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
     * configures the server and returns the url to connect.
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
     * @param conn
     *                Connection.
     * @param connectionNotifier
     *                Notifier.
     * @param din
     *                input stream.
     * @param dout
     *                output stream.
     * @param e
     *                exception to analyze.
     */
    private void processException(final StreamConnection conn,
	    final StreamConnectionNotifier connectionNotifier,
	    final DataInputStream din, final DataOutputStream dout,
	    final Exception e) {
	System.out
		.println("Exception Occured while executing: " + e.toString());
	if (din != null) {
	    try {
		din.close();
	    } catch (Exception e1) {
		System.out.println("Exception Occured while closing din: "
			+ e1.toString());
	    }
	}
	if (dout != null) {
	    try {
		dout.close();
	    } catch (Exception e1) {
		System.out.println("Exception Occured while closing dout: "
			+ e1.toString());
	    }
	}
	if (conn != null) {
	    try {
		conn.close();
	    } catch (Exception e1) {
		System.out.println("Exception Occured while closing conn: "
			+ e1.toString());
	    }
	}
	if (connectionNotifier != null) {
	    try {
		connectionNotifier.close();
	    } catch (Exception e1) {
		System.out.println("Exception Occured while closing notifier: "
			+ e1.toString());
	    }
	}
    }

    /**
     * Process message: receive and return.
     *
     * @param din
     *                Input stream
     * @param dout
     *                Output stream
     * @throws IOException
     *                 If there is a problem
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
	    // block the client.
	    dout.writeUTF("");
	    System.out.println("Problem sending the string: " + e.getMessage());
	}
    }

    /**
     * Process the received message.
     *
     * @param receivedMess
     *                Received message.
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
     *                Message processor.
     * @param tag
     *                Type of tag.
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
	if (this.m_messageProcessors.size() == 0) {
	    throw new RuntimeException("No message processors defined.");
	}
	StreamConnection conn = null;
	StreamConnectionNotifier connectionNotifier = null;
	DataInputStream din = null;
	DataOutputStream dout = null;
	try {
	    String url = this.configureBluetooth();

	    System.out.println("");
	    // Publish the service and waits for connections.
	    System.out.println("Start advertising service...");
	    // L2CAP returns L2CAPConnectionNotifier, RFCOMM returns
	    // StreamConnectionNotifier and OBEX returns ClientSession.
	    connectionNotifier = (StreamConnectionNotifier) Connector.open(url);
	    System.out.println("Waiting for incoming connection...");
	    // Inserts the service record into the SDDB and wait for incoming
	    // connections.
	    conn = connectionNotifier.acceptAndOpen();

	    // Client connected. Receives the data (can send data too.)
	    System.out.println("Client Connected...");
	    din = conn.openDataInputStream();
	    dout = conn.openDataOutputStream();
	    while (true) {
		this.processMessage(din, dout);
	    }

	} catch (Exception e) {
	    this.processException(conn, connectionNotifier, din, dout, e);
	} finally {
	    this.m_caller.setFinished();
	}
    }

    /**
     * Starts the bluetooth server and set it discoverable.
     *
     * @param caller
     *                Class that calls the bluetooth server.
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
