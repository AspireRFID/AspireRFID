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
package org.ow2.aspirerfid.nfc.midlet.generic.ui;

import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Displayable;

import org.ow2.aspirerfid.nfc.midlet.generic.GenericMidlet;

/**
 * Defines a screen of an application. This screen shows something and process a
 * command.
 * 
 * @author <a href="mailto:angoca@yahoo.com">Andres Gomez</a>
 * @version 1.0.0 31/07/2008
 */
public abstract class Screen implements CommandListener {

	/**
	 * Object currenty display in the phone's screen.
	 */
	private Displayable m_displayable = null;

	/**
	 * Midlet that calls the screen.
	 */
	private GenericMidlet m_midlet = null;

	/**
	 * Default constructor that associates the caller midlet. TODO set an
	 * interface and not an object. Change the GenericMidlet, it has to be an
	 * interface, not an object.
	 * 
	 * @param midlet
	 *            Midlet that starts the application.
	 */
	public Screen(GenericMidlet midlet) {
		if (midlet == null) {
			throw new RuntimeException("Error: Caller midlet is null.");
		}
		this.m_midlet = midlet;
	}

	/**
	 * Returns the object that is currently displayed.
	 * 
	 * @return Current displayable.
	 */
	protected Displayable getDisplayable() {
		if (this.m_displayable == null) {
			throw new RuntimeException(
					"Trying to retrieve the displayable but it has not been defined yet.");
		}
		return this.m_displayable;
	}

	/**
	 * Returns the caller midlet. TODO Returns an interface and not an object.
	 * 
	 * @return Midlet that starts the application.
	 */
	protected GenericMidlet getMidlet() {
		return this.m_midlet;
	}

	/**
	 * Shows the current screen in the display.
	 */
	public void setActive() {
		try {
			Display display = Display.getDisplay(this.getMidlet());
			if (display.getCurrent() != this.getDisplayable()) {
				display.setCurrent(this.m_displayable);
			}
		} catch (Exception e) {
			throw new RuntimeException("Error C1 activating a screen: "
					+ e.getMessage());
		}
	}

	/**
	 * Sets the object to display.
	 * 
	 * @param displayable
	 *            Object to display.
	 */
	protected synchronized void setDiplayable(Displayable displayable) {
		if (displayable == null) {
			throw new RuntimeException("Assigning a null displayable.");
		}
		this.m_displayable = displayable;
	}
}
