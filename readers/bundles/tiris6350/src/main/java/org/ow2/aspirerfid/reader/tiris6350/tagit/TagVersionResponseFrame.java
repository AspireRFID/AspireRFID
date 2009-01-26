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
 * Start 1 Start frame delimiter D516<br/> Data length 2 16 bytes follow this
 * block. 00 1016<br/> Service code 1 Single 0216<br/> Status flag 1 No
 * exception 0016<br/> Command code 1 Tag version 0316<br/> Format code 1
 * Non-addressed, no error 0016<br/> Synch code 1 No synchronization 0016<br/>
 * Address 4 00 12 AB 8116<br/> Manufacturer code 1 Texas Instruments 0116<br/>
 * Chip version 2 Version 5 00 0516<br/> Block size 1 3+1 bytes x 8 = 32 bits
 * 0316<br/> Number of blocks 1 7+1 = 8 blocks 0716<br/> BCC 2 CRC –calculated
 * on previous 16 bytes E1 85
 * 
 * @author Unknown
 * @version 2006
 */
public class TagVersionResponseFrame extends ResponseFrame {

	/**
	 * @param bytes
	 */
	public TagVersionResponseFrame(byte[] bytes) {
		super(bytes);
	}

}
