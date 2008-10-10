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
 * Message request that has all the information about the tag.
 * 
 * @author <a href="mailto:angoca@yahoo.com">Andres Gomez</a>
 * @version 1.0.0 31/07/2008
 */
public class CompleteRequestMessage extends RequestMessage {

	/**
	 * Message content.
	 */
	private String m_content = null;

	/**
	 * Tag's type.
	 */
	private String m_type = null;

	/**
	 * Tag's URI.
	 */
	private String m_uid = null;

	/**
	 * Tag's URL.
	 */
	private String m_url = null;

	/**
	 * Default constructor.
	 * 
	 * @param midlet
	 *            Midlet that calls the message.
	 */
	public CompleteRequestMessage(TagDetector midlet) {
		super(midlet);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see core.RequestMessage#getAllMessage()
	 */
	public String getAllMessage() {
		String message = "TYPE: " + this.getTagType() + ",UID: "
				+ this.getTagUID() + ",URL: " + this.getURL() + ",CONTENT: "
				+ this.getContent();
		return message;
	}

	/**
	 * Return the message's content.
	 * 
	 * @return Message's content.
	 */
	public String getContent() {
		return this.m_content;
	}

	/**
	 * Return the tag's type.
	 * 
	 * @return Tag's type.
	 */
	public String getTagType() {
		return this.m_type;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see core.Message#getUID()
	 */
	public String getTagUID() {
		return this.m_uid;
	}

	/**
	 * Return the tag's URL.
	 * 
	 * @return tag's URL.
	 */
	public String getURL() {
		return this.m_url;
	}

	/**
	 * Establishes the content of the message.
	 * 
	 * @param content
	 *            Message's content.
	 */
	public void setContent(String content) {
		this.m_content = content;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see core.Message#setUID(java.lang.String)
	 */
	public void setTagUID(String uid) {
		this.m_uid = uid;
	}

	/**
	 * Established the tag's type.
	 * 
	 * @param type
	 *            Tag's type.
	 */
	public void setType(String type) {
		this.m_type = type;
	}

	/**
	 * Established the Tag's URL.
	 * 
	 * @param url
	 *            Tag's URL.
	 */
	public void setURL(String url) {
		this.m_url = url;
	}
}
