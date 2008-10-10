package org.ow2.aspire.rfid.nfc.server.bluetooth;

/**
 * This is the interface that any application that wants to use the bluetooth
 * server has to implement.
 * 
 * @author <a href="mailto:angoca@yahoo.com">Andres Gomez</a>
 */
public interface BluetoothServerCaller {
	/**
	 * Notifies that the bluetooth server has been finished.
	 */
	void setFinished();
}
