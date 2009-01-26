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
 * define the frame to read details of a transponder
 * 
 * @author Anne Robert
 * @version 2006
 */
public class ReadTRPDetailsRequestFrame extends Frame {

	/*
	 * Example ReadTRPDetails Tx: 01 09 00000000 05 0DF2 Rx: 01 12 00000000 05
	 * 8D401C01 01 0500 08 04 CE31 With 01 1C 40 8D TRP=8D401C01 01 Man (TI) 05
	 * 00 Tag Version=0005 08 No of Block 04 Byte per block
	 */

	private final static byte[] bs = { (byte) 0x01, (byte) 0x09, (byte) 0x00,
			(byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x05, (byte) 0x0D,
			(byte) 0xF2 };

	/**
	 * Constructor: build the command
	 */
	public ReadTRPDetailsRequestFrame() {
		super(bs);
	}

}
