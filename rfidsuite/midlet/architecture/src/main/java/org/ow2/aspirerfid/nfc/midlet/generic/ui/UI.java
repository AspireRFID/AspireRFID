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

import org.ow2.aspirerfid.nfc.midlet.generic.GenericMidlet;

/**
 * Graphic interface of a program. It starts the interface and permits show
 * messages like exceptions, warnings, etc. TODO delete this interface
 * 
 * @author <a href="mailto:angoca@yahoo.com">Andres Gomez</a>
 * @version 1.0.0 31/07/2008
 */
public interface UI {

	/**
	 * Show a message in a window to indicate something to the user.
	 * 
	 * @param message
	 *            Message to show.
	 */
	public void alert(String message);

	/**
	 * Show a message in a window to indicate something to the user for a given
	 * time.
	 * 
	 * @param message
	 *            Message to show.
	 * @param time
	 *            Time to show the message (in millis)
	 */
	public void alert(String message, int time);

	/**
	 * Appends a message.
	 * 
	 * @param message
	 *            Message to append.
	 */
	public void append(String message);

	/**
	 * Starts the program graphic interface.
	 * 
	 * @param rfidMidlet
	 *            Midlet that call the interface.
	 */
	public void start(GenericMidlet rfidMidlet);
}
