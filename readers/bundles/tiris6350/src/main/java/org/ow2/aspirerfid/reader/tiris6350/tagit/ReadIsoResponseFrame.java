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

// TAG-IT COMMANDS FROM http://www.ti.com/rfid/docs/manuals/refmanuals/s6000hp.pdf
package org.ow2.aspirerfid.reader.tiris6350.tagit;

/**
 * Class containing method to interpret the reader response for the
 * corresponding request
 * 
 * @author Anne Robert
 * @version 2006
 */
public class ReadIsoResponseFrame extends Frame {

	/**
	 * constructor with the reader response
	 * 
	 * @param bytes
	 *            reader response
	 */
	public ReadIsoResponseFrame(byte[] bytes) {
		super(bytes);
	}

	/*
	 * Example Read single block Tx: 01 0D00 000000 60110320005EA1 Rx:
	 * 010E000000006000000000006F90 01 : header 0E00 : length (msb ending) 0000 :
	 * node address 00 : response flag 60 : command executed 0000000000 : data
	 */
	protected final static int POS_ID = 7;
	private static final int POS_FLAGS = 5;
	private static final int ID_LENGTH = 5;

	/**
	 * get the read tag id
	 * 
	 * @return id as read on the tag
	 */
	public byte[] getTagId() {
		byte[] res = new byte[5];
		for (int i = 0; i < ID_LENGTH; i++) {
			res[i] = bytes[POS_ID + i];
		}
		return res;
	}

	/**
	 * control the correctness of the response
	 * 
	 * @return true if there is no error
	 */
	public boolean noErrorReturned() {
		// TODO check also BCC
		return (bytes[POS_DATALENGTH] == 0x0E)
				&& ((bytes[POS_FLAGS] & 0x10) == 0);
	}
}
