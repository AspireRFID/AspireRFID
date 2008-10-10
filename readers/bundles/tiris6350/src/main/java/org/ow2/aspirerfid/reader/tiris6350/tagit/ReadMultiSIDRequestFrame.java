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

package org.ow2.aspirerfid.reader.tiris6350.tagit;

/**
 * define the frame to send to read all present tags ID on the reader
 * 
 * @author Anne Robert
 * @version 2006
 */
public class ReadMultiSIDRequestFrame extends RequestFrame {

	private final static byte[] bs = {
			// (byte)0xD5, (byte)0x00, (byte)0x0A, (byte)0x03,
			// (byte)0x00, (byte)0xFD, (byte)0x00, (byte)0x00,
			// (byte)0x00, (byte)0x03, (byte)0x01, (byte)0xA3,
			// (byte)0xA6
			(byte) 0x01, (byte) 0x0E, (byte) 0x00, (byte) 0x00, (byte) 0x00,
			(byte) 0x00, (byte) 0x60, (byte) 0x11, (byte) 0x00, (byte) 0x01,
			(byte) 0x00, (byte) 0x00, (byte) 0x6E, (byte) 0x91

	};

	/**
	 * Constructor: build the command
	 */
	public ReadMultiSIDRequestFrame() {
		super(bs);
	}
}
