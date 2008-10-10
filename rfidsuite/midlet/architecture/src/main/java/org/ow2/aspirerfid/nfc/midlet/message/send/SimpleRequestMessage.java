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
package org.ow2.aspirerfid.nfc.midlet.message.send;

import org.ow2.aspirerfid.nfc.midlet.generic.RequestMessage;
import org.ow2.aspirerfid.nfc.midlet.reader.TagDetector;

/**
 * Simple message request that has only the tag's UID.
 * 
 * @author <a href="mailto:angoca@yahoo.com">Andres Gomez</a>
 * @version 1.0.0 31/07/2008
 */
public class SimpleRequestMessage extends RequestMessage {

	/**
	 * Tag's UID.
	 */
	private String m_uid = null;

	/**
	 * Default constructor
	 * 
	 * @param midlet
	 *            Midlet that called the message.
	 */
	public SimpleRequestMessage(TagDetector midlet) {
		super(midlet);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see core.RequestMessage#getAllMessage()
	 */
	public String getAllMessage() {
		String message = this.getTagUID();
		return message;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see core.Message#getUID()
	 */
	public String getTagUID() {
		return this.m_uid;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see core.Message#setUID(java.lang.String)
	 */
	public void setTagUID(String uid) {
		this.m_uid = uid;
	}
}
