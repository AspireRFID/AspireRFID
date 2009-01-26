/*
 *  Copyright (C) Aspire
 *
 *  This library is free software; you can redistribute it and/or
 *  modify it under the terms of the GNU Lesser General Public
 *  License as published by the Free Software Foundation; either
 *  version 2.1 of the License, or (at your option) any later version.
 *
 *  This library is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 *  Lesser General Public License for more details.
 *
 *  You should have received a copy of the GNU Lesser General Public
 *  License along with this library; if not, write to the Free Software
 *  Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301  USA
 */
package org.ow2.aspirerfid.nfc.picking.server.message.response;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.ow2.aspirerfid.nfc.server.message.response.AbstractResponseMessage;

/**
 * TODO Javadoc
 * 
 * @author Maroula Perisanidi
 * @version 1.0 01/05/2008
 */
public class ListMessage extends AbstractResponseMessage {

	/**
	 * @param contents
	 *            String to clean.
	 * @return The string without tabs nor spaces.
	 */
	private StringBuilder deleteSpaces(StringBuilder contents) {
		// Deletes the spaces and tabs.
		String contents2 = contents.toString().replace('\t', ' ').trim();
		StringBuilder contentRet = new StringBuilder();
		for (int i = 0, length = contents2.length(); i < length; i++) {
			if (contents2.charAt(i) != ' ') {
				contentRet.append(contents2.charAt(i));
			}
		}
		return contentRet;
	}

	/**
	 * Reads a text file (given the filename) and return its content in a
	 * String. Deletes all the tabs, spaces and new lines.
	 * 
	 * @param fileName
	 *            Absolute location of the file with filename.
	 * @return File's content.
	 */
	private String getFileContents(String fileName) {
		StringBuilder contents = new StringBuilder();

		try {
//			FileReader file = new FileReader(fileName);
			InputStream input = this.getClass().getResourceAsStream(fileName);
			InputStreamReader isr = new InputStreamReader(input);
			BufferedReader buffer = new BufferedReader(isr);
			try {
				String line = null;
				while ((line = buffer.readLine()) != null) {
					contents.append(line);
					// Here, it deletes the new line separators.
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				buffer.close();
			}
		} catch (IOException ex) {
			ex.printStackTrace();
		}

		StringBuilder contentRet = this.deleteSpaces(contents);

		return contentRet.toString();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.ow2.aspire.rfid.nfc.server.message.response.ResponseMessage#getMessage()
	 */
	public String getMessage() {
		String respMessage = this.getFileContents("/list.xml");
		return respMessage;
	}
}
