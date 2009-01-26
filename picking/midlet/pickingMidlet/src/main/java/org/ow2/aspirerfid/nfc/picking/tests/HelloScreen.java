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
package org.ow2.aspirerfid.nfc.picking.tests;

import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Form;
import javax.microedition.lcdui.Item;
import javax.microedition.lcdui.StringItem;

import org.ow2.aspirerfid.nfc.midlet.generic.GenericMidlet;
import org.ow2.aspirerfid.nfc.midlet.generic.ui.Screen;

/**
 * This is the screen that presents the hello world example.
 * 
 * @author
 * @author <a href="mailto:angoca@yahoo.com">Andres Gomez</a>
 * @version 1.0.0 27/08/2008
 */
public class HelloScreen extends Screen {

	/**
	 * Command that permits to exit.
	 */
	private Command m_exit = null;

	/**
	 * Form where the application's hello message is showed.
	 */
	private Form m_hello = null;

	/**
	 * @param midlet
	 *            Midlet that calls the application.
	 */
	public HelloScreen(GenericMidlet midlet) {
		super(midlet);

		// Form's title.
		this.m_hello = new Form("Hello world example");

		// Command to pass to the main menu.
		this.m_exit = new Command("Exit", Command.EXIT, 1);
		this.m_hello.addCommand(this.m_exit);

		// Application's description.
		StringItem description = new StringItem("",
				"LIG - Adele\nAndrés Gómez\nMaroula Perisanidi\n2008");
		description.setLayout(Item.LAYOUT_CENTER);
		this.m_hello.append(description);

		// Establishes the listener to this screen.
		this.m_hello.setCommandListener(this);

		this.setDiplayable(this.m_hello);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.microedition.lcdui.CommandListener#commandAction(javax.microedition.lcdui.Command,
	 *      javax.microedition.lcdui.Displayable)
	 */
	public void commandAction(Command command, Displayable displayable) {
		if (displayable != this.m_hello) {
			// Invalid displayable.
			throw new RuntimeException(
					"Error: Invalid 'hello' displayable");
		}

		// Skip command.
		if (command == this.m_exit) {
			this.getMidlet().stopApplication(true, true);
		} else {
			// Unknown command.
			throw new RuntimeException(
					"Error: Invalid command in 'hello'");
		}
	}
}
