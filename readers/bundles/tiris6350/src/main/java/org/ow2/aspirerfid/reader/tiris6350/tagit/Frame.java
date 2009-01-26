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
 * Define a frame to send to the reader
 * 
 * @author Anne Robert
 * @version 2006
 */
public class Frame {

	protected final static int POS_STARTCODE = 0;
	protected final static int POS_DATALENGTH = 1;
	protected final static int POS_SERVICECODE = 3;
	protected final static int POS_MESSAGE = 5;

	protected byte[] bytes;

	/**
	 * Constructor with the frame contents
	 * 
	 * @param bytes
	 *            command to send
	 */
	public Frame(byte[] bytes) {
		this.bytes = bytes;
	}

	/**
	 * @return the contents of the command
	 */
	public byte[] getBytes() {
		return bytes;
	}

	/**
	 * @return get the service code included in the frame
	 */
	public byte getServiceCode() {
		return bytes[POS_SERVICECODE];
	}

	/*
	 * public short getDataLength() { return new
	 * Short(bytes[POS_DATALENGTH]+bytes[POS_DATALENGTH+1]*8); }
	 */
	/**
	 * check the BCC (CRC or LRC)
	 * <p>
	 * see 3.5 Error Detection in
	 * http://www.ti.com/rfid/docs/manuals/refmanuals/s6000hp.pdf
	 * <p>
	 * see Appendix A BCC Error Detection Methods in
	 * http://www.ti.com/rfid/docs/manuals/refmanuals/s6000hp.pdf
	 * 
	 * @return true if the BCC is correct
	 */
	public boolean checkCode() {
		// TODO
		return false;
	}

	/**
	 * compute the BCC (CRC or LRC) and set it at the tail of the message
	 */
	public void setBCC() {
		// TODO
		return;
	}
}
