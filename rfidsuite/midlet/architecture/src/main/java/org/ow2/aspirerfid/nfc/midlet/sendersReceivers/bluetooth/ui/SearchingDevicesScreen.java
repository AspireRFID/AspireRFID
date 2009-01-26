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
package org.ow2.aspirerfid.nfc.midlet.sendersReceivers.bluetooth.ui;

import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Form;
import javax.microedition.lcdui.Gauge;

import org.ow2.aspirerfid.nfc.midlet.generic.GenericMidlet;
import org.ow2.aspirerfid.nfc.midlet.generic.ui.Screen;

/**
 * This is the class that is show when the application is detecting the devices.
 * 
 * @author <a href="mailto:angoca@yahoo.com">Andres Gomez</a>
 * @version 1.0.0 31/07/2008
 */
public class SearchingDevicesScreen extends Screen implements
		BluetoothToUIInterface {

	/**
	 * Class that controls the bluetooth communication in the business layer.
	 */
	private UIToBluetoothInterface m_bluetoothController = null;
	/**
	 * Command that cancel the search.
	 */
	private Command m_cancelCmd = null;
	/**
	 * List of discovered devices.
	 */
	private Form m_foundDevices = null;

	/**
	 * Constructor that establishes the caller midlet and creates all the
	 * elements to show.
	 * 
	 * @param midlet
	 *            Caller midlet.
	 * @param bluetoothController
	 *            Class that controls the bluetooth communications in the
	 *            business layer.
	 * @param title
	 *            Title to show in the screen.
	 */
	public SearchingDevicesScreen(GenericMidlet midlet,
			UIToBluetoothInterface bluetoothController, String title) {
		super(midlet);
		this.m_bluetoothController = bluetoothController;

		this.m_foundDevices = new Form(title);

		Gauge searching = new Gauge("", false, Gauge.INDEFINITE,
				Gauge.CONTINUOUS_RUNNING);
		this.m_foundDevices.append(searching);

		// Cancel command.
		this.m_cancelCmd = new Command("Cancel", Command.EXIT, 1);
		this.m_foundDevices.addCommand(this.m_cancelCmd);

		// Establishes the listener to this screen.
		this.m_foundDevices.setCommandListener(this);

		this.setDiplayable(this.m_foundDevices);

		// Reports to the business layer that the interface is waiting.
		this.m_bluetoothController.waitingServices(this);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see appl.museum.stable.BluetoothToUIInterface#addDiscoveredDevice(java.lang.String)
	 */
	public void addDiscoveredDevice(String deviceName) {
		this.m_foundDevices.append(deviceName + "\n");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.microedition.lcdui.CommandListener#commandAction(javax.microedition.lcdui.Command,
	 *      javax.microedition.lcdui.Displayable)
	 */
	public void commandAction(Command command, Displayable displayable) {
		if (displayable != this.m_foundDevices) {
			// Invalid displayable.
			throw new RuntimeException(
					"Error: Invalid 'searching devices' displayable");
		}

		if (command == this.m_cancelCmd) {
			// Cancel command.
			this.m_bluetoothController.cancel();
		} else {
			// Unknown command.
			throw new RuntimeException(
					"Error: Invalid command in 'searching devices'");
		}
	}

	/**
	 * Changes the title of the current form.
	 * 
	 * @param title
	 *            Title to set.
	 */
	public void changeTitle(String title) {
		this.m_foundDevices.setTitle(title);
	}
}
