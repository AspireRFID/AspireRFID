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

import javax.microedition.contactless.TargetProperties;

import org.ow2.aspirerfid.nfc.midlet.reader.TagDetector;

/**
 * Process the data of a tag. The concrete classes make the message that is
 * going to be sent (by the senders) or read (by the UI.)
 * 
 * @author <a href="mailto:angoca@yahoo.com">Andres Gomez</a>
 * @version 1.0.0 31/07/2008
 */
public abstract class ReaderThread extends Thread {

	/**
	 * Midlet that calls the tag detector.
	 */
	protected TagDetector m_midlet = null;

	/**
	 * Set of properties from the read tag.
	 */
	private TargetProperties[] m_properties = null;

	/**
	 * Graphic interface of the current Midlet.
	 * 
	 * @param properties
	 *            Set of properties.
	 * @param midlet
	 *            Midlet that call the RFIDtector.
	 */
	public ReaderThread(TargetProperties[] properties, TagDetector midlet) {
		this.m_properties = properties;
		this.m_midlet = midlet;
	}

	/**
	 * Builds the message to be sent or processed with the properties of the
	 * tag.
	 * 
	 * @param targetProp
	 *            Properties of the tag.
	 * @return Message.
	 */
	protected abstract RequestMessage buildMessage(TargetProperties targetProp);

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Thread#run()
	 */
	public void run() {
		for (int i = 0; i < this.m_properties.length; i++) {
			TargetProperties targetProp = this.m_properties[i];
			RequestMessage message = null;
			message = this.buildMessage(targetProp);
			this.m_midlet.tagRead(message);
		}
	}
}
