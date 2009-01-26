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

import java.util.Enumeration;
import java.util.Vector;

import javax.microedition.lcdui.Choice;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.List;

import org.ow2.aspirerfid.nfc.midlet.generic.GenericMidlet;
import org.ow2.aspirerfid.nfc.midlet.generic.ui.Screen;

/**
 * This is the screen when the services of the found devices are being
 * discovered. TODO rename found devices
 * 
 * @author <a href="mailto:angoca@yahoo.com">Andres Gomez</a>
 * @version 1.0.0 31/07/2008
 */
public class FoundDevicesListScreen extends Screen implements
		BluetoothToUIInterface {

	/**
	 * Class that controls the bluetooth communication in the business layer.
	 */
	private UIToBluetoothInterface m_bluetoothController = null;
	/**
	 * Screen that calls the device discovery.
	 */
	private Screen m_callerScreen = null;
	/**
	 * Cancel command to return to the main screen.
	 */
	private Command m_cancelCmd = null;
	/**
	 * List of found devices
	 */
	private List m_foundDevices = null;

	/**
	 * Constructor that associates the caller midlet and creates all the
	 * elements to show.
	 * 
	 * @param midlet
	 *            Caller midlet.
	 * @param bluetoothController
	 *            Class that controls the bluetooth communications in the
	 *            business layer.
	 * @param foundDevices
	 *            Set of found devices.
	 * @param callerScreen
	 *            Screen to go after to be connected.
	 */
	public FoundDevicesListScreen(GenericMidlet midlet,
			UIToBluetoothInterface bluetoothController, Vector foundDevices,
			Screen callerScreen) {
		super(midlet);
		this.m_bluetoothController = bluetoothController;
		this.m_callerScreen = callerScreen;

		this.m_foundDevices = new List("Select a device:", Choice.IMPLICIT);

		// Creates the cancel command.
		this.m_cancelCmd = new Command("Cancel", Command.CANCEL, 1);
		this.m_foundDevices.addCommand(this.m_cancelCmd);

		// Shows the found devices.
		Enumeration enumDevices = foundDevices.elements();
		while (enumDevices.hasMoreElements()) {
			String name = (String) enumDevices.nextElement();
			this.m_foundDevices.append(name, null);
		}

		// Establishes the listener to this screen.
		this.m_foundDevices.setCommandListener(this);

		this.setDiplayable(this.m_foundDevices);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see appl.museum.stable.BluetoothToUIInterface#addDiscoveredDevice(java.lang.String)
	 */
	public void addDiscoveredDevice(String deviceName) {
		// Nothing has to call this method.
		throw new RuntimeException("Invalid call to add discovered device.");
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
					"Error: Invalid 'service discovery' displayable");
		}

		if (command == List.SELECT_COMMAND) {
			int index = this.m_foundDevices.getSelectedIndex();
			this.m_bluetoothController.setSelectedDeviceAndContinue(index);
		} else if (command == this.m_cancelCmd) {
			this.m_bluetoothController.cancel();
			this.getMidlet().setActiveScreen(this.m_callerScreen);
		} else {
			// This methods doesn't have command.
			// Unknown command.
			throw new RuntimeException(
					"Error: Invalid command in 'service discovery'");
		}
	}
}
