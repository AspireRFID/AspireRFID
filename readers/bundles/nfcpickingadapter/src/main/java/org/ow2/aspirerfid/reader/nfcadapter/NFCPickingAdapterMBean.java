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
package org.ow2.aspirerfid.reader.nfcadapter;

import org.ow2.aspirerfid.reader.RfidReaderMBean;

/**
 *
 * Define the configuration interface for the NFC adapter
 * 
 */
public interface NFCPickingAdapterMBean extends RfidReaderMBean {
	/**
	 * setter for the guid reader for real reader this value is not writable
	 * 
	 * @param readerGuid
	 *            the fictive value of the reader Guid
	 */
	public void setReaderGUId(String readerGuid);
	
	public void setGpsCoordinates(String coordinates);
	
	public String getGpsCoordinates();

}
