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
 * define the frame to send for a reader diagnostic command
 * 
 * @author Anne Robert
 * @version 2006
 */
public class ReaderDiagnosticRequestFrame extends RequestFrame {

	private final static byte[] bs = { (byte) 0xD5, (byte) 0x00, (byte) 0x06,
			(byte) 0x04, (byte) 0x00, (byte) 0x12, (byte) 0x01, (byte) 0x83,
			(byte) 0xAB };

	/**
	 * TODO Javadoc
	 */
	public ReaderDiagnosticRequestFrame() {
		super(bs);
	}
}
