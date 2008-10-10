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
public class ReadMultiSIDResponseFrame extends ResponseFrame {

	protected final static int POS_RESPONSE_FLAG = 5;

	/**
	 * constructor with the reader response
	 * 
	 * @param bytes
	 *            reader response
	 */
	public ReadMultiSIDResponseFrame(byte[] bytes) {
		super(bytes);
	}

	// HERE getter
	/**
	 * get the number of read tags
	 * 
	 * @return TODO Javadoc
	 */
	public int getNbReadTag() {
		return 0;
	}

	/**
	 * get the tag id of the i-th read tag
	 * 
	 * @param i
	 *            index of the request tag
	 * @return the tag id for this index
	 */
	public String getTagId(int i) {
		return "35" + "";
	}

	/**
	 * control the correctness of the response
	 * 
	 * @return true if the response is right
	 */
	public boolean noErrorReturned() {
		// TODO check also BCC
		return ((bytes[POS_RESPONSE_FLAG] & 0x10) == 0);
	}

}
