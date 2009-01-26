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
package org.ow2.aspirerfid.reader.tiris6350;

import java.io.Serializable;
import java.util.Date;

/**
 * Represents a sensor event
 * <p>
 * This class will change for JSR 257 (Contactless Communications API [RFID])
 * available on 2005Q1.<br/> <a
 * href="http://jcp.org/en/jsr/detail?id=256">http://jcp.org/en/jsr/detail?id=256</a>
 * <a
 * href="http://jcp.org/en/jsr/detail?id=257">http://jcp.org/en/jsr/detail?id=257</a>
 * 
 * @author Didier Donsez (Didier.Donsez@ieee.org)
 * @version 0.1.0 03 Dec. 2004
 */
public class Event2 implements Serializable {

	private static final long serialVersionUID = 7796146330427753978L;

	/**
	 * the GUID (Global Unique IDentifier) of the reader that communicate with
	 * the tag
	 * <p>
	 * example: uuid:090000019F69F709@ibutton.com for a one-wire adapter
	 * <p>
	 * see also chapter 23 in OSGi R3 specification Should respect the chapter
	 * 23 "Name-space Specification Version 1.0" of the release 3
	 */
	private String readerGUId;

	/**
	 * the GUID (Global Unique IDentifier) of the tag
	 * <p>
	 * example: uuid:5A34C0000219A121@ibutton.com for a one-wire iButton
	 * microcan
	 * <p>
	 * example: uuid:12345687@tiris.com for a TagIt RFIDs
	 * <p>
	 * see also chapter 23 in OSGi R3 specification Should respect the chapter
	 * 23 "Name-space Specification Version 1.0" of the release 3
	 */
	private String tagGUId;

	/**
	 * the type of the event
	 */
	private int type;

	/**
	 * the timestamp when the event occurs
	 */
	private long timestamp;

	/**
	 * represents sensor detection
	 */
	public final static int DETECTION = 1;

	/**
	 * represents sensor activation and calibration
	 */
	public final static int ACTIVATION = 2;

	/**
	 * represents sensor capture
	 */
	public final static int CAPTURE = 4;

	/**
	 * represents sensor deactivation
	 */
	public final static int DESACTIVATION = 8;

	/**
	 * TODO Javadoc
	 * 
	 * @param readerGUId
	 * @param tagGUId
	 * @param type
	 * @param timestamp
	 */
	public Event2(String readerGUId, String tagGUId, int type, long timestamp) {
		this.readerGUId = readerGUId;
		this.tagGUId = tagGUId;
		this.type = type;
		this.timestamp = timestamp;
	}

	/**
	 * @return Returns the reader GUId.
	 */
	public String getReaderGUId() {
		return readerGUId;
	}

	/**
	 * @param readerGUId
	 *            The reader GUId to set.
	 */
	public void setReaderGUId(String readerGUId) {
		this.readerGUId = readerGUId;
	}

	/**
	 * @return Returns the tag GUId.
	 */
	public String getTagGUId() {
		return tagGUId;
	}

	/**
	 * @param tagGUId
	 *            The tag GUId to set.
	 */
	public void setTagGUId(String tagGUId) {
		this.tagGUId = tagGUId;
	}

	/**
	 * @return Returns the event type.
	 */
	public int getType() {
		return type;
	}

	/**
	 * @param type
	 *            The event type to set.
	 */
	public void setType(int type) {
		this.type = type;
	}

	/**
	 * @return Returns the timestamp when the event occurred.
	 */
	public long getTimestamp() {
		return timestamp;
	}

	/**
	 * @param timestamp
	 *            The timestamp to set.
	 */
	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}

	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("[readerGUId=").append(readerGUId);
		sb.append(",tagGUId=").append(tagGUId);
		sb.append(",timestamp=").append((new Date(timestamp)).toString());
		sb.append(",type=");
		switch (type) {
		case DETECTION:
			sb.append("DETECTION");
			break;
		case ACTIVATION:
			sb.append("ACTIVATION");
			break;
		case CAPTURE:
			sb.append("CAPTURE");
			break;
		case DESACTIVATION:
			sb.append("DESACTIVATION");
			break;
		default:
			sb.append("UNKNOWN");
		}
		sb.append(']');
		return sb.toString();
	}
}
