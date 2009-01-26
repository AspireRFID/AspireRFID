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
 * Class containing method to interpret the reader response for the
 * corresponding request
 * 
 * @author Anne Robert
 * @version 2006
 */
public class ResponseFrame extends Frame {

	// 2.2.5 Status Flag
	/*
	 * 00; // Reserved 05; // Reserved 06; // Reserved 07; // Reserved 08; //
	 * Reserved 09; // Reserved 0A; // Reserved 0B; // Reserved 0C; // Reserved
	 * 0D; // Reserved 0E; // Reserved
	 */
	/**
	 * Request data corrupted, not executed
	 */
	public final static byte ERROR_CODE_Request_data_corrupted = 0x01;
	/**
	 * Application not supported
	 */
	public final static byte ERROR_CODE_Application_not_supported = 0x02;
	/**
	 * Data format error, request aborted
	 */
	public final static byte ERROR_CODE_Data_format_error = 0x03;
	/**
	 * Continuous mode not available for this request
	 */
	public final static byte ERROR_CODE_Continuous_mode_not_available = 0x04;
	/**
	 * Undefined system error, request aborted
	 */
	public final static byte ERROR_CODE_Undefined_system_error = 0x0F;

	/**
	 * TODO Javadoc
	 */
	protected final static int POS_CONTROLFLAG = 4;

	/**
	 * constructor with the reader response
	 * 
	 * @param bytes
	 *            reader response
	 */
	public ResponseFrame(byte[] bytes) {
		super(bytes);
	}

	/**
	 * extract the control flag from the response
	 * 
	 * @return the response control flag
	 */
	public byte getControlFlag() {
		return bytes[POS_CONTROLFLAG];
	}

}
