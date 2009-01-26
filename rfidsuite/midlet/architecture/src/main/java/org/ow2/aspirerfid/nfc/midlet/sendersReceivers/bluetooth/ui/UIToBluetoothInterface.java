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

import org.ow2.aspirerfid.nfc.midlet.generic.ui.Screen;

/**
 * This interface permits the User Interface to communicate with the business
 * layer, and so on communicates via bluetooth.
 * 
 * @author <a href="mailto:angoca@yahoo.com">Andres Gomez</a>
 * @version 1.0.0 31/07/2008
 */
public interface UIToBluetoothInterface {
	/**
	 * Cancels the current operation.
	 */
	public void cancel();

	/**
	 * Connects to a device via bluetooth.
	 * 
	 * @param previousScreen
	 *            Previous screen to return after connect.
	 * @param nextScreen
	 *            After being connected this will be screen to show.
	 */
	public void connectBluetooth(Screen previousScreen, Screen nextScreen);

	/**
	 * Disconnect the bluetooth connection.
	 * 
	 * @param previousScreen
	 *            Previous screen to show if it could not disconnect.
	 * @param nextScreen
	 *            Next screen to show after disconnect.
	 */
	public void disconnectBluetooth(Screen previousScreen, Screen nextScreen);

	/**
	 * Returns the bluetooth connection state.
	 * 
	 * @return True means that the communication is established with a server.
	 *         False the contrary.
	 */
	public boolean isBluetoothConnected();

	/**
	 * Established the selected device by the user.<br/> When using a different
	 * thread to search devices, this method permits to continue the execution
	 * of that thread, because it waits the answer's response.<br/> Also, this
	 * method activates the screen that show the wait message while connecting.
	 * 
	 * @param selectedIndex
	 *            Device selected by the user.
	 */
	public void setSelectedDeviceAndContinue(int selectedIndex);

	/**
	 * Reports to the business layer that the user interface is waiting for the
	 * list of devices. TODO delete this method. From the business layer the
	 * screen has to be controlled.
	 * 
	 * @param deviceScreen
	 *            Screen that presents the found devices.
	 */
	public void waitingServices(BluetoothToUIInterface deviceScreen);
}
