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

import java.io.IOException;
import java.io.InputStream;

import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Form;
import javax.microedition.lcdui.Image;
import javax.microedition.lcdui.ImageItem;
import javax.microedition.lcdui.Item;
import javax.microedition.lcdui.StringItem;

import org.ow2.aspirerfid.nfc.midlet.generic.GenericMidlet;
import org.ow2.aspirerfid.nfc.midlet.generic.ui.AlertScreen;
import org.ow2.aspirerfid.nfc.midlet.generic.ui.Screen;

/**
 * This is the screen that presents the application.
 * 
 * @author Perisanidi Maroula
 */
public class PresentationScreen extends Screen {

	/**
	 * Command that permits to access the main menu.
	 */
	private Command m_enter = null;

	/**
	 * Form where the application's presentation is showed.
	 */
	private Form m_presentation = null;

	/**
	 * @param midlet
	 *            Midlet that calls the application.
	 */
	public PresentationScreen(GenericMidlet midlet) {
		super(midlet);

		// Form's title.
		this.m_presentation = new Form("Picking Application");

		// Command to pass to the main menu.
		this.m_enter = new Command("Enter", Command.OK, 1);
		this.m_presentation.addCommand(this.m_enter);

		// Loads an image and adds it to the current form.
		try {
			InputStream inputStream = this.getClass().getResourceAsStream(
					"/intro.png");
			Image image = Image.createImage(inputStream);
			this.m_presentation.append(new ImageItem(null, image,
					ImageItem.LAYOUT_CENTER, null));
		} catch (IOException e) {
			this.getMidlet().setActiveScreen(
					new AlertScreen(this.getMidlet(),
							"Error: Presentation image not found."));
		} catch (NullPointerException e) {
			this
					.getMidlet()
					.setActiveScreen(
							new AlertScreen(this.getMidlet(),
									"Error: The image is not available for the presentation."));
		}

		// Application's description.
		StringItem description = new StringItem("",
				"AspireRFID\nAndrés Gómez\nMaroula Perisanidi\nLGPL 2.1\n2008");
		description.setLayout(Item.LAYOUT_CENTER);
		this.m_presentation.append(description);

		// Establishes the listener to this screen.
		this.m_presentation.setCommandListener(this);

		this.setDiplayable(this.m_presentation);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.microedition.lcdui.CommandListener#commandAction(javax.microedition.lcdui.Command,
	 *      javax.microedition.lcdui.Displayable)
	 */
	public void commandAction(Command command, Displayable displayable) {
		if (displayable != this.m_presentation) {
			// Invalid displayable.
			throw new RuntimeException(
					"Error: Invalid 'presentation' displayable");
		}

		// Skip command.
		if (command == this.m_enter) {
			this.getMidlet().setActiveScreen(new MenuScreen(this.getMidlet()));
		} else {
			// Unknown command.
			throw new RuntimeException(
					"Error: Invalid command in 'presentation'");
		}
	}
}
