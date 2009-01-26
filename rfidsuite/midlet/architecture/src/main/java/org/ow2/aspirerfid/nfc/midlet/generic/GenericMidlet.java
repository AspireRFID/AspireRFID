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
package org.ow2.aspirerfid.nfc.midlet.generic;

import javax.microedition.midlet.MIDlet;

import org.ow2.aspirerfid.nfc.midlet.generic.ui.Screen;
import org.ow2.aspirerfid.nfc.midlet.generic.ui.UI;
import org.ow2.aspirerfid.nfc.midlet.reader.Reader;

/**
 * Midlet controller that starts all the components.
 * 
 * @author <a href="mailto:angoca@yahoo.com">Andres Gomez</a>
 * @version 1.0.0 31/07/2008
 */
public abstract class GenericMidlet extends MIDlet {

	/**
	 * Current screen displayed.
	 */
	private Screen m_currentScreen = null;

	/**
	 * RFID Tag detector.
	 */
	private Reader m_detector = null;
	/**
	 * State of the RFID detector.
	 */
	private boolean m_detectorStarted = false;

	/**
	 * Graphic interface of the program. TODO delete
	 */
	private UI m_ui = null;

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.microedition.midlet.MIDlet#destroyApp(boolean)
	 */
	protected void destroyApp(boolean arg0) {
		this.stopDetector();
	}

	/**
	 * Returns the current screen that is being displayed.
	 * 
	 * @return Current screen.
	 */
	public Screen getActiveScreen() {
		return this.m_currentScreen;
	}

	/**
	 * Returns the graphic interface that is defined for the current Midlet.
	 * TODO delete
	 * 
	 * @return Graphic interface for the current midlet.
	 */
	public UI getUI() {
		if (this.m_ui == null) {
			throw new IllegalStateException("The UI is not initialized");
		}
		return this.m_ui;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.microedition.midlet.MIDlet#pauseApp()
	 */
	protected void pauseApp() {
		// Nothing to do (probably in the future.)
	}

	/**
	 * Sets the given screen as active.
	 * 
	 * @param screen
	 *            Screen to establish as active.
	 */
	public synchronized void setActiveScreen(Screen screen) {
		this.m_currentScreen = screen;
		screen.setActive();
	}

	/**
	 * Establishes the graphic interface for the current midlet. TODO delete
	 * 
	 * @param ui
	 *            Graphic interface where the objects are placed.
	 */
	protected void setUI(UI ui) {
		this.m_ui = ui;
	}

	/**
	 * Starts the tag detector (add a listener for each selected tag type.)
	 * 
	 * @param reader
	 *            Reader to be started.
	 */
	protected void startDetector(Reader reader) {
		if (!this.m_detectorStarted) {
			this.m_detector = reader;
			this.m_detectorStarted = true;
		}
	}

	/**
	 * Establishes and starts the given user interface. TODO delete this method,
	 * with screen it does not have to exits.
	 * 
	 * @param ui
	 *            Interface graphic.
	 */
	public void startUI(UI ui) {
		this.setUI(ui);
		this.getUI().start(this);
	}

	/**
	 * Releases all the resources before finish the application.
	 * 
	 * @param value
	 *            Change to the destroy state. If true, it throws a
	 *            MidletStateChangeException.
	 * @param notifyDestroyed
	 *            If the application has to notify when destroyed.
	 */
	public void stopApplication(boolean value, boolean notifyDestroyed) {
		this.destroyApp(value);
		if (notifyDestroyed) {
			this.notifyDestroyed();
		}
	}

	/**
	 * Stops the tag detector.
	 */
	protected void stopDetector() {
		if (this.m_detector != null) {
			this.m_detector.stop();
			this.m_detector = null;
			this.m_detectorStarted = false;
		}
	}
}
