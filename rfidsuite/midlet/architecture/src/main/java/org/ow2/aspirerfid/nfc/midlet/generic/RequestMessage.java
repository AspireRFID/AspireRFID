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

import org.ow2.aspirerfid.nfc.midlet.reader.TagDetector;

/**
 * Structure of a message that contains a request.
 * 
 * @author <a href="mailto:angoca@yahoo.com">Andres Gomez</a>
 * @version 1.0.0 31/07/2008
 */
public abstract class RequestMessage extends Message {
	/**
	 * Midlet that call this message.
	 */
	protected final TagDetector m_midlet;

	/**
	 * The constructor that obtains the midlet that calls him.
	 * 
	 * @param midlet
	 *            Midlet that calls him.
	 */
	public RequestMessage(TagDetector midlet) {
		if (midlet == null) {
			throw new IllegalArgumentException(
					"Error #: The midlet param is null.");
		}
		this.m_midlet = midlet;
	}

	/**
	 * Returns all the message.
	 * 
	 * @return Message to sent.
	 */
	public abstract String getAllMessage();
}
