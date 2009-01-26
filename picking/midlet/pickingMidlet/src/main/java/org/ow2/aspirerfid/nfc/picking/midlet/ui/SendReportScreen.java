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

import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Vector;

import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Form;

import org.ow2.aspirerfid.nfc.midlet.generic.GenericMidlet;
import org.ow2.aspirerfid.nfc.midlet.generic.ui.Screen;

/**
 * This is the screen that shows the report sent to the pc.
 * 
 * @author Perisanidi Maroula
 * 
 */
public class SendReportScreen extends Screen {

	/**
	 * Command that goes back to the main menu.
	 */
	private Command m_back = null;

	/**
	 * Form where the Send Report is presented.
	 */
	private Form m_report = null;
	/**
	 * Vector containing the uid's patterns of the items that have not been
	 * collected.
	 */
	private final Vector vector;

	/**
	 * @param midlet
	 *            Caller midlet.
	 * @param missingItems
	 *            List containing the names of the items that were not
	 *            collected.
	 * @param collectedItems
	 *            Set containing the names of the items that were collected.
	 */
	public SendReportScreen(GenericMidlet midlet, Vector missingItems,
			Hashtable collectedItems) {
		super(midlet);
		this.vector = missingItems;
		this.m_report = new Form("Report:");

		this.m_report.append("Missing Items:\n");
		// create the list from the vector
		for (int i = 0; i < this.vector.size(); i++) {
			this.m_report.append((String) this.vector.elementAt(i));
			this.m_report.append("\n");
		}

		System.out.println("collectedItems " + collectedItems.size());
		Enumeration e = collectedItems.keys();

		this.m_report.append("Collected Items:\n");
		int i = 0;
		while (e.hasMoreElements()) {
			String key = (String) e.nextElement();
			String value = (String) collectedItems.get(key);
			this.m_report.append(key + "(" + value + ")\n");
			i++;
		}

		// Command to go back to the main menu.
		this.m_back = new Command("Back", Command.BACK, 1);
		this.m_report.addCommand(this.m_back);

		// Establishes the listener to this screen.
		this.m_report.setCommandListener(this);

		this.setDiplayable(this.m_report);
	}

	public void commandAction(Command command, Displayable displayable) {
		if (displayable != this.m_report) {
			// Invalid displayable.
			throw new RuntimeException("Error: Invalid 'report' displayable");
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
