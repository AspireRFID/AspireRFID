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
package org.ow2.aspirerfid.nfc.midlet.generic;

import org.ow2.aspirerfid.nfc.midlet.sendersReceivers.BusinessToCommInterface;
import org.ow2.aspirerfid.nfc.midlet.sendersReceivers.FileSenderReceiver;
import org.ow2.aspirerfid.nfc.midlet.sendersReceivers.HTTPSenderReceiver;
import org.ow2.aspirerfid.nfc.midlet.sendersReceivers.SMSSenderReceiver;
import org.ow2.aspirerfid.nfc.midlet.sendersReceivers.bluetooth.BluetoothSenderReceiver;
import org.ow2.aspirerfid.nfc.midlet.sendersReceivers.bluetooth.BusinessToBluetoothCommInterface;
import org.ow2.aspirerfid.nfc.midlet.sendersReceivers.fictive.FictiveSenderReceiver;

/**
 * Returns the instance of the selected sender / receiver.
 * 
 * @author <a href="mailto:angoca@yahoo.com">Andres Gomez</a>
 * @version 1.0.0 31/07/2008
 */
public class SenderReceiverFactory {
	/**
	 * Bluetooth type.
	 */
	public static final int BLUETOOTH = 1;
	/**
	 * Fictive type.
	 */
	public static final int FICTIVE = 2;
	/**
	 * File implementation type.
	 */
	public static final int FILE = 3;
	/**
	 * HTTP type.
	 */
	public static final int HTTP = 4;
	/**
	 * SMS type.
	 */
	public static final int SMS = 5;

	/**
	 * This method implements the factory. Returns the instance of the selected
	 * sender / receiver.
	 * 
	 * @param type
	 *            This is the type of the sender receiver that will be created.
	 * @param caller
	 *            Caller class.
	 * @return Instance of the selected type.
	 */
	public static SenderReceiver getNewInstance(int type,
			BusinessToCommInterface caller) {
		SenderReceiver senderReceiver = null;
		switch (type) {

		case BLUETOOTH:
			if (caller instanceof BusinessToBluetoothCommInterface) {
				senderReceiver = BluetoothSenderReceiver
						.getInstance((BusinessToBluetoothCommInterface) caller);
			} else {
				throw new RuntimeException(
						"Invalid caller: It is not BluetoothSenderReceiver");
			}
			break;

		case FICTIVE:
			senderReceiver = new FictiveSenderReceiver(caller);
			break;

		case FILE:
			senderReceiver = new FileSenderReceiver(caller);
			break;

		case HTTP:
			senderReceiver = new HTTPSenderReceiver(caller);
			break;

		case SMS:
			senderReceiver = new SMSSenderReceiver(caller);
			break;

		default:
			senderReceiver = new FictiveSenderReceiver(caller);
			break;
		}
		return senderReceiver;
	}
}
