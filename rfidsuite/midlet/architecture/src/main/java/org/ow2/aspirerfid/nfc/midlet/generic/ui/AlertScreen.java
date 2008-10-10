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

import javax.microedition.lcdui.Alert;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Displayable;

import org.ow2.aspirerfid.nfc.midlet.generic.GenericMidlet;

/**
 * Presents an alert. After presenting the after there can be another screen or
 * to exit from the application.
 * 
 * @author <a href="mailto:angoca@yahoo.com">Andres Gomez</a>
 * @version 1.0.0 31/07/2008
 */
public class AlertScreen extends Screen {

	/**
	 * Alert to show.
	 */
	private Alert m_alert = null;
	/**
	 * Screen to show after the alert.
	 */
	private Screen m_nextScreen = null;
	/**
	 * End execution after the alert.
	 */
	private boolean m_stopExecution = false;

	/**
	 * Constructor that associates the caller midlet and the message to show.
	 * The execution of the application continue.
	 * 
	 * @param midlet
	 *            Caller midlet.
	 * @param message
	 *            Message to show.
	 */
	public AlertScreen(GenericMidlet midlet, String message) {
		super(midlet);
		this.m_nextScreen = this.getMidlet().getActiveScreen();
		this.createAlert(message);
	}

	/**
	 * Constructor that associates the caller midlet and the message to show. To
	 * continue the execution depends on the stop value.
	 * 
	 * @param midlet
	 *            Caller midlet.
	 * @param message
	 *            Message to show.
	 * @param stop
	 *            Determine if the execution has to contiue.
	 */
	public AlertScreen(GenericMidlet midlet, String message, boolean stop) {
		super(midlet);
		this.m_stopExecution = stop;
		this.createAlert(message);
	}

	/**
	 * Constructor that associate the caller midlet and the message to show.
	 * This alert permits to continue the execution, and after the alert there
	 * is another screen. TODO delete this constructor and use the method
	 * Display.setCurrent(Alert, Displayable) for this it is necessary to change
	 * the setActive method.
	 * 
	 * @param midlet
	 *            Caller midlet.
	 * @param message
	 *            Message to show.
	 * @param nextScreen
	 *            Screen to show after the alert.
	 */
	public AlertScreen(GenericMidlet midlet, String message, Screen nextScreen) {
		super(midlet);
		this.m_nextScreen = nextScreen;
		this.createAlert(message);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.microedition.lcdui.CommandListener#commandAction(javax.microedition.lcdui.Command,
	 *      javax.microedition.lcdui.Displayable)
	 */
	public void commandAction(Command command, Displayable displayable) {
		if (displayable != this.m_alert) {
			// Invalid displayable.
			throw new RuntimeException("Error: Invalid 'alert' displayable");
		}

		// Dismiss command
		if (command == Alert.DISMISS_COMMAND) {
			// The application stops after the alert.
			if (this.m_stopExecution) {
				this.getMidlet().stopApplication(true, true);
			} else if (this.m_nextScreen != null) {
				// There is another screen after the alert.
				this.getMidlet().setActiveScreen(this.m_nextScreen);
			} else {
				// Timeout
				// TODO review the timeout.
			}
		} else {
			// Unknown command.
			throw new RuntimeException("Error: Invalid command in 'alert'");
		}
	}

	/**
	 * Creates an alert to show.
	 * 
	 * @param message
	 *            Message that has to have the alert.
	 */
	private void createAlert(String message) {
		// The display is already an alert.
		if (Display.getDisplay(this.getMidlet()).getCurrent() instanceof Alert) {
			((Alert) Display.getDisplay(this.getMidlet()).getCurrent())
					.setString(message);
			((Alert) Display.getDisplay(this.getMidlet()).getCurrent())
					.setTimeout(1500);
		} else {
			this.m_alert = new Alert("", message, null, null);
			this.m_alert.setTimeout(2000);

			// Establishes the listener to this screen.
			this.m_alert.setCommandListener(this);

			this.setDiplayable(this.m_alert);
		}
	}

}
