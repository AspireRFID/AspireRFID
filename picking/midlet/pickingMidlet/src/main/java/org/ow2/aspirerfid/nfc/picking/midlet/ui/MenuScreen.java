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

import javax.microedition.lcdui.Choice;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.List;

import org.ow2.aspirerfid.nfc.midlet.generic.GenericMidlet;
import org.ow2.aspirerfid.nfc.midlet.generic.ui.Screen;
import org.ow2.aspirerfid.nfc.picking.midlet.PickingMidlet;

/**
 * This is the screen of the main menu that shows the possible options of the
 * application.
 * 
 * @author Perisanidi Maroula
 */
public class MenuScreen extends Screen {

	/**
	 * Name of the connection option.
	 */
	private final String m_connectionName = "Receive List";

	/**
	 * Exit command.
	 */
	private Command m_exitCmd = null;

	/**
	 * Name of the exit option.
	 */

	private final String m_exitName = "Exit";

	/**
	 * Name of the help option.
	 */

	private final String m_helpName = "Help";
	/**
	 * List of options of the menu.
	 */
	private List m_menuOptions = null;
	/**
	 * List of options
	 */
	private final String[] m_nameOptions = { this.m_connectionName,
			this.m_showlistName, this.m_reportName, this.m_helpName,
			this.m_exitName };

	/**
	 * Name of the report option.
	 */

	private final String m_reportName = "Send Report";

	/**
	 * Select option command.
	 */
	private Command m_selectCmd = null;

	/**
	 * Name of the option that shows the list of products.
	 */
	private final String m_showlistName = "Show List";

	/**
	 * @param midlet
	 *            Midlet that calls the screen.
	 */
	public MenuScreen(GenericMidlet midlet) {
		super(midlet);
		this.m_menuOptions = new List("Main menu", Choice.IMPLICIT,
				this.m_nameOptions, null);

		// Select command.
		this.m_selectCmd = new Command("Select", Command.ITEM, 1);
		this.m_menuOptions.setSelectCommand(this.m_selectCmd);

		// Exit command.
		this.m_exitCmd = new Command("Exit", Command.EXIT, 1);
		this.m_menuOptions.addCommand(this.m_exitCmd);

		// Establishes the listener to this screen.
		this.m_menuOptions.setCommandListener(this);

		this.setDiplayable(this.m_menuOptions);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.microedition.lcdui.CommandListener#commandAction(javax.microedition.lcdui.Command,
	 *      javax.microedition.lcdui.Displayable)
	 */
	public void commandAction(Command command, Displayable displayable) {
		if (displayable != this.m_menuOptions) {
			// Invalid displayable.
			throw new RuntimeException("Error: Invalid 'main menu' displayable");
		}
		if (command == this.m_selectCmd) {
			String option = this.m_nameOptions[this.m_menuOptions
					.getSelectedIndex()];
			// Instructions command.
			if (option.compareTo(this.m_helpName) == 0) {
				this.getMidlet().setActiveScreen(
						new HelpScreen(this.getMidlet()));
			} else if (option.compareTo(this.m_connectionName) == 0) {
				// Connection command.
				((PickingMidlet) this.getMidlet()).receiveList();
			} else if (option.compareTo(this.m_exitName) == 0) {
				// Exit command.
				this.getMidlet().stopApplication(true, true);
			} else if (option.compareTo(this.m_showlistName) == 0) {
				// Show List command
				((PickingMidlet) this.getMidlet()).showList();
			} else if (option.compareTo(this.m_reportName) == 0) {
				// Send Report command
				((PickingMidlet) this.getMidlet()).createReport();
			} else {
				throw new RuntimeException("Error: Invalid selected option");
			}
		} else if (command == this.m_exitCmd) {
			// Exit command.
			this.getMidlet().stopApplication(true, true);
		} else {
			// Unknown command.
			throw new RuntimeException("Error: Invalid command in 'main menu'");
		}

	}

}
