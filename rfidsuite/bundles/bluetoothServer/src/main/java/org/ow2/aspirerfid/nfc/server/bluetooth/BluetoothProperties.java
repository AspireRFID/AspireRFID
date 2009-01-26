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

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.InvalidPropertiesFormatException;
import java.util.Properties;

import javax.bluetooth.DiscoveryAgent;

/**
 * These are the properties of the bluetooh connection.
 * 
 * @author <a href="mailto:angoca@yahoo.com">Andres Gomez</a>
 * @version 1.0 07/07/2008
 */
public class BluetoothProperties {

	/**
	 * Remote device authentication.
	 */
	public static final String AUTHENTICATE = "authenticate";
	/**
	 * Remote device authentication par default.
	 */
	private static final String AUTHENTICATE_VALUE = "false";
	/**
	 * Encrypt connection.
	 */
	public static final String ENCRYPT = "encrypt";
	/**
	 * Encrypt connection par default.
	 */
	private static final String ENCRYPT_VALUE = "false";
	/**
	 * Inquiry mode par default.
	 */
	public static final String INQUIRY_MODE = "inquiryMode";
	/**
	 * Inquiry mode par default.
	 */
	private static final String INQUIRY_MODE_VALUE = "" + DiscoveryAgent.GIAC;
	/**
	 * Is inquiry scanning allowed during a connection: "true" or "false".
	 */
	public static final String INQUIRY_SCAN = "bluetooth.connected.inquiry.scan";
	/**
	 * Is master/slave switch allowed: "true" or "false".
	 */
	public static final String MASTER_SLAVE = "bluetooth.master.switch";

	/**
	 * The maximum number of connected devices supported.
	 */
	public static final String MAX_DEVICES = "bluetooth.connected.devices.max";

	/**
	 * The L2CAP maximum transmission unit.
	 */
	public static final String MAX_MTU = "bluetooth.l2cap.receiveMTU.max";

	/**
	 * The maximum number of service-record attributes that can be retrieved at
	 * a time.
	 */
	public static final String MAX_RETRIEVABLE = "bluetooth.sd.attr.retrievable.max";

	/**
	 * The maximum number of concurrent service-discovery transactions.
	 */
	public static final String MAX_TRANS = "bluetooth.sd.trans.max";

	/**
	 * Is paging allowed during a connection: "true" or "false".
	 */
	public static final String PAGE = "bluetooth.connected.page";

	/**
	 * Is page scanning allowed during a connection: "true" or "false".
	 */
	public static final String PAGE_SCAN = "bluetooth.connected.page.scan";

	/**
	 * Properties file name.
	 */
	private static String propertiesFileName = "bluetooth.xml";

	/**
	 * Service name.
	 */
	public static final String SERVICE_NAME = "serviceName";

	/**
	 * Service name par default.
	 */
	private static final String SERVICE_NAME_VALUE = "Echo Server";

	/**
	 * Service uuid.
	 */
	public static final String UUID = "uuid";

	/**
	 * Servide uuid par default.
	 */
	private static final String UUID_VALUE = "9ee2b296da2448399658cbc0a7ee69e2";

	/**
	 * Bluetooth properties.
	 */
	private final Properties m_bluetoothProperties;

	/**
	 * Default constructor.
	 * 
	 * @throws BluetoothServerException
	 *             If the properties file is not found or malformed.
	 */
	public BluetoothProperties() throws BluetoothServerException {
		this(BluetoothProperties.propertiesFileName);
	}

	/**
	 * Loads the bluetooth properties from the given file name.
	 * 
	 * @param fileName
	 *            File name of the bluetooth properties.
	 * 
	 * @throws BluetoothServerException
	 *             If the properties file is not found or malformed.
	 */
	public BluetoothProperties(final String fileName)
			throws BluetoothServerException {
		File propertiesFile = new File(fileName);
		InputStream in;
		try {
			in = new FileInputStream(propertiesFile);
		} catch (FileNotFoundException e) {
			throw new BluetoothServerException(
					"Bluetooth properties file not found: " + fileName);
		}

		// It cannot be refactored because the attribut defaultProps is final.
		Properties defaultProps = setDefault();
		this.m_bluetoothProperties = new Properties(defaultProps);
		loadXML(in);
	}

	/**
	 * Loads the bluetooth properties from a given input stream
	 * 
	 * @param in
	 *            Input stream where the properties can be read.
	 * @throws BluetoothServerException
	 */
	public BluetoothProperties(final InputStream in)
			throws BluetoothServerException {
		Properties defaultProps = setDefault();
		this.m_bluetoothProperties = new Properties(defaultProps);
		loadXML(in);
	}

	/**
	 * @param in
	 * @throws BluetoothServerException
	 */
	private void loadXML(final InputStream in) throws BluetoothServerException {
		try {
			this.m_bluetoothProperties.loadFromXML(in);
		} catch (InvalidPropertiesFormatException e) {
			throw new BluetoothServerException(
					"Bluetooth properties file invalid.");
		} catch (IOException e) {
			throw new BluetoothServerException(
					"Error opening Bluetooth properties file.");
		}
	}

	/**
	 * @return Properties with the default settings.
	 */
	private Properties setDefault() {
		Properties defaultProps = new Properties();
		defaultProps.setProperty(BluetoothProperties.UUID,
				BluetoothProperties.UUID_VALUE);
		defaultProps.setProperty(BluetoothProperties.SERVICE_NAME,
				BluetoothProperties.SERVICE_NAME_VALUE);
		defaultProps.setProperty(BluetoothProperties.AUTHENTICATE,
				BluetoothProperties.AUTHENTICATE_VALUE);
		defaultProps.setProperty(BluetoothProperties.ENCRYPT,
				BluetoothProperties.ENCRYPT_VALUE);
		defaultProps.setProperty(BluetoothProperties.INQUIRY_MODE,
				BluetoothProperties.INQUIRY_MODE_VALUE);
		return defaultProps;
	}

	/**
	 * Returns a given string, according to the given key.
	 * 
	 * @param key
	 *            Object's key.
	 * @return String related to the given key.
	 */
	public final String getProperty(final String key) {
		String value = "";
		value = this.m_bluetoothProperties.getProperty(key);
		System.out.println("[PARAM] " + key + ": " + value);

		return value;
	}
}
