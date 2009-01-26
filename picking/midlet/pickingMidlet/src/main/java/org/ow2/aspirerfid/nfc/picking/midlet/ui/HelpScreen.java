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

import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Form;
import javax.microedition.lcdui.Item;
import javax.microedition.lcdui.StringItem;

import org.ow2.aspirerfid.nfc.midlet.generic.GenericMidlet;
import org.ow2.aspirerfid.nfc.midlet.generic.ui.Screen;

/**
 * This class explains the function of the application.
 * 
 * @author Perisanidi Maroula
 */
public class HelpScreen extends Screen {

	/**
	 * Command that goes back to the main menu.
	 */
	private Command m_back = null;

	/**
	 * Form where the help is presented.
	 */
	private Form m_help = null;

	/**
	 * Constructor of the help interface.
	 * 
	 * @param midlet
	 *            Caller midlet.
	 */
	public HelpScreen(GenericMidlet midlet) {
		super(midlet);
		this.m_help = new Form("Help");

		// Application's description.
		StringItem description = new StringItem(
				"",
				"This is a picking application. A list of items"
						+ " to be collected can be downloaded to the mobile."
						+ " Each time a tag is read, a message is displayed, stating whether"
						+ " it is in the list or not. At the end a report is sent to the"
						+ " computer with the results.");
		description.setLayout(Item.LAYOUT_LEFT);
		this.m_help.append(description);

		// Commands
		StringItem commandsDescription = new StringItem("Commands",
				"This is the Help command."
						+ " The Receive List command, connects to the pc"
						+ " and receives the list of items to be collected."
						+ " The Send Report command, sends a report to the pc"
						+ " with the results of the Picking operation.");
		commandsDescription.setLayout(Item.LAYOUT_LEFT);
		this.m_help.append(commandsDescription);

		// Commands
		StringItem license = new StringItem("License",
				"GNU Lesser General Public License (LGPL) version 2.1");
		license.setLayout(Item.LAYOUT_LEFT);
		this.m_help.append(license);
		// Command to go back to the main menu.
		this.m_back = new Command("Back", Command.BACK, 1);
		this.m_help.addCommand(this.m_back);

		// Establishes the listener to this screen.
		this.m_help.setCommandListener(this);

		this.setDiplayable(this.m_help);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.microedition.lcdui.CommandListener#commandAction(javax.microedition.lcdui.Command,
	 *      javax.microedition.lcdui.Displayable)
	 */
	public void commandAction(Command command, Displayable displayable) {
		if (displayable != this.m_help) {
			// Invalid displayable.
			throw new RuntimeException(
					"Error: Invalid 'instructions' displayable");
		}

		// Go back command.
		if (command == this.m_back) {
			this.getMidlet().setActiveScreen(new MenuScreen(this.getMidlet()));
		} else {
			// Unknown command.
			throw new RuntimeException("Error: Invalid command in 'help'");
		}
	}
}
