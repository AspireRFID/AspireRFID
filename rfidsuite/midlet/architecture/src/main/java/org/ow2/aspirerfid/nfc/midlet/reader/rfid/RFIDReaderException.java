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
package org.ow2.aspirerfid.nfc.midlet.reader.rfid;

import org.ow2.aspirerfid.nfc.midlet.generic.NFCMidletException;

/**
 * The exception is thrown when there is problem reading the tags.
 * 
 * @author <a href="mailto:angoca@yahoo.com">Andres Gomez</a>
 * @version 1.0.0 31/07/2008
 */
public class RFIDReaderException extends NFCMidletException {

	/**
	 * Exceptions's ID
	 */
	private static final long serialVersionUID = -3227897362014429288L;

	/**
	 * Builds the exception just with a message.
	 * 
	 * @param message
	 *            Reason of the exception.
	 */
	public RFIDReaderException(String message) {
		super(message);
	}

	/**
	 * Builds the exception with a message an a masked exception.
	 * 
	 * @param message
	 *            Reason of the exception.
	 * @param e
	 *            Masked exception.
	 */
	public RFIDReaderException(String message, Exception e) {
		super(message, e);
	}
}
