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

/**
 * Exception thrown by the application.
 * 
 * @author <a href="mailto:angoca@yahoo.com">Andres Gomez</a>
 * @version 1.0.0 31/07/2008
 */
public abstract class NFCMidletException extends Exception {

	/**
	 * Masked exception.
	 */
	private Exception m_exception = null;

	/**
	 * Builds the exception just with a message.
	 * 
	 * @param message
	 *            Reason of the exception.
	 */
	public NFCMidletException(String message) {
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
	public NFCMidletException(String message, Exception e) {
		super(message);
		this.m_exception = e;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Throwable#getMessage()
	 */
	public String getMessage() {
		String message = super.getMessage();
		if (this.m_exception != null) {

			message += ":\n" + this.m_exception.getMessage();
		}
		return message;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Throwable#printStackTrace()
	 */
	public void printStackTrace() {
		super.printStackTrace();
		if (this.m_exception != null) {
			this.m_exception.printStackTrace();
		}
	}
}
