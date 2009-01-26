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
 */
package org.ow2.aspirerfid.nfc.picking.server;

import org.ow2.aspirerfid.nfc.picking.server.message.request.ReceiveListMessage;
import org.ow2.aspirerfid.nfc.picking.server.message.request.ReceiveReportMessage;
import org.ow2.aspirerfid.nfc.picking.util.XmlTags;
import org.ow2.aspirerfid.nfc.server.bluetooth.BluetoothServer;
import org.ow2.aspirerfid.nfc.server.bluetooth.BluetoothServerCaller;

/**
 * This is an example of the bluetooth server that hears messages from a client.
 * 
 * @author Maroula Perisanidi
 * @version 1.0 01/05/2008
 */
public class PickingServer implements BluetoothServerCaller {
	/**
	 * Thread bluetooth finished.
	 */
	private boolean finished = true;
	/**
	 * Thread Picking.
	 */
	private boolean stop = false;
	/**
	 * Bluetooth server that process messages from the devices.
	 */
	private BluetoothServer server;

	/**
	 * Starts the Bluetooth server and receives connection indefinitely.
	 */
	public PickingServer(MessageProcessorCallback callback) {

		try {
			server = new BluetoothServer(this, this.getClass()
					.getResourceAsStream("/pickingConf.xml"));
			server.registerMessage(new ReceiveReportMessage(callback), "<"
					+ XmlTags.PROD_LIST + ">");
			server.registerMessage(new ReceiveListMessage(), "<"
					+ XmlTags.SEND_LIST + ">");

		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}

	/**
	 * Starts the bluetooth server to process messages.
	 */
	public void start() {
		Thread t = new Thread(new Runnable() {
			public void run() {
				stop = false;
				while (!stop) {
					// Verifies if the previous thread has finished.
					if (finished) {
						Thread thread = new Thread(server);
						thread.setName("AspireRFID PickingServer process messages");
						thread.start();
						finished = false;
					}
					// Waits to check again.
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		});
		t.setName("AspireRFID PickingServer Bluetooth");
		t.start();
	}

	/**
	 * @see org.ow2.aspire.rfid.nfc.server.bluetooth.BluetoothServerCaller#setFinished()
	 */
	public void setFinished() {
		this.finished = true;
	}

	/**
	 * Stops the thread.
	 */
	public void stop() {
		// First, the outer cycle to not create another thread (messages.)
		this.stop = true;
		// Second, the inner cycle to finish the current thread (messages.)
		server.stop();
	}
}
