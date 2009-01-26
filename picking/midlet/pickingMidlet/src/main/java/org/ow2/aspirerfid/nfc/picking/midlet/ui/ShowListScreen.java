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
package org.ow2.aspirerfid.nfc.picking.midlet.ui;

import java.util.Vector;

import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Form;

import org.ow2.aspirerfid.nfc.midlet.generic.GenericMidlet;
import org.ow2.aspirerfid.nfc.midlet.generic.ui.Screen;

/**
 * Screen that shows the list of items to be collected.
 * 
 * @author Perisanidi Maroula
 */
public class ShowListScreen extends Screen {

	/**
	 * Command that goes back to the main menu.
	 */
	private Command m_back = null;

	/**
	 * Form where the help is presented.
	 */
	private Form m_showList = null;

	/**
	 * Vector containing the uid's pattern.
	 */
	private final Vector vector;

	/**
	 * @param midlet
	 *            Midlet that calls the screen.
	 * @param vector
	 *            Vector containing the names of the items to be collected.
	 */
	public ShowListScreen(GenericMidlet midlet, Vector vector) {
		super(midlet);
		this.vector = vector;

		this.m_showList = new Form("Uid List");

		// create the list from the vector
		for (int i = 0; i < this.vector.size(); i++) {
			this.m_showList.append((String) this.vector.elementAt(i));
			this.m_showList.append("\n");
		}

		// Command to go back to the main menu.
		this.m_back = new Command("Back", Command.BACK, 1);
		this.m_showList.addCommand(this.m_back);

		// Establishes the listener to this screen.
		this.m_showList.setCommandListener(this);

		this.setDiplayable(this.m_showList);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.microedition.lcdui.CommandListener#commandAction(javax.microedition.lcdui.Command,
	 *      javax.microedition.lcdui.Displayable)
	 */
	public void commandAction(Command command, Displayable displayable) {
		if (displayable != this.m_showList) {
			// Invalid displayable.
			throw new RuntimeException("Error: Invalid 'showList' displayable");
		}
		// Go back command.
		if (command == this.m_back) {
			this.getMidlet().setActiveScreen(new MenuScreen(this.getMidlet()));
		} else {
			// Unknown command.
			throw new RuntimeException("Error: Invalid command in 'showList'");
		}
	}
}
