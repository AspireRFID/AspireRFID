package org.ow2.aspire.rfid.nfc.server.bluetooth;

/**
 * Exception ocurred in the bluetooth server.
 * 
 * @author <a href="mailto:angoca@yahoo.com">Andres Gomez</a>
 */
public class BluetoothServerException extends Exception {

	/**
	 * Generated serial ID.
	 */
	private static final long serialVersionUID = -4542098770935668230L;
	/**
	 * Exception message.
	 */
	private String m_message = null;

	/**
	 * Default constructor.
	 * 
	 * @param message
	 *            Exception message.
	 */
	public BluetoothServerException(final String message) {
		this.m_message = message;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Throwable#getMessage()
	 */
	@Override
	public final String getMessage() {
		return this.m_message;
	}

}
