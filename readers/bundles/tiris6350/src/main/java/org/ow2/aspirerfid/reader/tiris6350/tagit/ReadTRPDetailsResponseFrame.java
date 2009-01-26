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
public class ReadTRPDetailsResponseFrame extends Frame {

	/*
	 * Example ReadTRPDetails Tx: 01 09 00000000 05 0DF2 Rx: 01 12 00000000 05
	 * 8D401C01 01 0500 08 04 CE31 Rx: 01 0A 00000010 05 01 1FE0 if error With
	 * 01 1C 40 8D TRP=8D401C01 01 Man (TI) 05 00 Tag Version=0005 08 No of
	 * Block 04 Byte per block CE31 BCC ?
	 */
	protected final static int POS_TAGADDRESS = 6;
	protected final static int POS_MANUFACTURER = POS_TAGADDRESS + 4;
	protected final static int POS_VERSION = POS_MANUFACTURER + 1;
	protected final static int POS_NUMBER_OF_BLOCK = POS_VERSION + 2;
	protected final static int POS_BYTE_PER_BLOCK = POS_NUMBER_OF_BLOCK + 1;

	/**
	 * constructor with the reader response
	 * 
	 * @param bytes
	 *            reader reaponse
	 */
	public ReadTRPDetailsResponseFrame(byte[] bytes) {
		super(bytes);
	}

	/**
	 * get the tag adresse in byte array format
	 * 
	 * @return 4 Bytes address
	 */
	public byte[] getTRP() {
		return new byte[] { bytes[POS_TAGADDRESS], bytes[POS_TAGADDRESS + 1],
				bytes[POS_TAGADDRESS + 2], bytes[POS_TAGADDRESS + 3] };
	}

	/**
	 * get the tag adresse in int format
	 * 
	 * @return tag address
	 */
	public int getTagAddress() {
		return bytes[POS_TAGADDRESS] + bytes[POS_TAGADDRESS + 1] * 8
				+ bytes[POS_TAGADDRESS + 2] * 8 * 8 + bytes[POS_TAGADDRESS + 3]
				* 8 * 8 * 8;
	}

	/**
	 * get the tag adresse in long format
	 * 
	 * @return tag address
	 */
	public long getTagId() {
		return (long) getTagAddress() + ((long) getVersion()) * 8 * 8 * 8 * 8
				+ ((long) getManufacturer()) * 8 * 8 * 8 * 8 * 8 * 8;
	}

	/**
	 * extract the manufacturer
	 * 
	 * @return the manufacturer id
	 */
	public byte getManufacturer() {
		return bytes[POS_MANUFACTURER];
	}

	/**
	 * extract the tag version in int format
	 * 
	 * @return tag vesion
	 */
	public int getVersion() {
		return bytes[POS_VERSION] + bytes[POS_VERSION + 1] * 8;
	}

	/**
	 * extract the tag version in byte array format
	 * 
	 * @return tag vesion
	 */
	public byte[] getTagVersion() {
		return new byte[] { bytes[POS_VERSION], bytes[POS_VERSION + 1] };
	}

	/**
	 * extract the number of block stored on the tag
	 * 
	 * @return the number of block of the tag
	 */
	public byte getNumberOfBlocks() {
		return bytes[POS_NUMBER_OF_BLOCK];
	}

	/**
	 * extract the block size (of data on tag)
	 * 
	 * @return the tag block size
	 */
	public byte getBytesPerBlock() {
		return bytes[POS_BYTE_PER_BLOCK];
	}

	/**
	 * control the correctness of the response
	 * 
	 * @return true if there is no error
	 */
	public boolean noErrorReturned() {
		// TODO check also BCC
		return bytes[POS_DATALENGTH] == 0x12;
	}
}
