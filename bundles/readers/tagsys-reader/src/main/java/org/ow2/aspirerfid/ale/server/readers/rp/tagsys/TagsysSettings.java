/*
   Copyright 2005-2010, OW2 Aspire RFID project 
   
   This library is free software; you can redistribute it and/or modify it 
   under the terms of the GNU Lesser General Public License as published by 
   the Free Software Foundation (the "LGPL"); either version 2.1 of the 
   License, or (at your option) any later version. If you do not alter this 
   notice, a recipient may use your version of this file under either the 
   LGPL version 2.1, or (at his option) any later version.
   
   You should have received a copy of the GNU Lesser General Public License 
   along with this library; if not, write to the Free Software Foundation, 
   Inc., 51 Franklin Street, Fifth Floor, Boston, MA 02110-1301 USA.
   
   This software is distributed on an "AS IS" basis, WITHOUT WARRANTY OF ANY 
   KIND, either express or implied. See the GNU Lesser General Public 
   License for the specific language governing rights and limitations.

   Contact: OW2 Aspire RFID project <X AT Y DOT org> (with X=aspirerfid and Y=ow2)

   LGPL version 2.1 full text http://www.gnu.org/licenses/lgpl-2.1.txt    
 */
package org.ow2.aspirerfid.ale.server.readers.rp.tagsys;

import java.util.Map;

import com.tagsys.reader.mediolx00.TMedioLx00BufferEntry;

//FIXME Use getters/setters for properties
public class TagsysSettings {
	private static final String PROPERTY_PREFIX = "tagsys.mediolx00.settings";

	public static final int MEDIA_SERIAL = 0;

	public static final int MEDIA_ETHERNET = 1;

	int iTimer;
	
	int iMediaType;

	String strSerialName;

	int iSerialSpeed;

	String strIPAddress;

	int iTCPPort;

	int iPower;

	int iChannel;

	int iTagType;

	boolean boCont;

	boolean boSort;

	public void set(Map<String,String> properties) {

		this.iTimer = Integer.parseInt(properties.get(PROPERTY_PREFIX + ".iTimer"));
		
		String iMediaType_ = properties.get(PROPERTY_PREFIX	+ ".iMediaType");
		if (iMediaType_ != null) {
			if (iMediaType_.equals("serial")) {
				this.iMediaType = MEDIA_SERIAL;
				this.strSerialName = properties.get(PROPERTY_PREFIX	+ ".strSerialName");
				this.iSerialSpeed = Integer.parseInt(properties.get(PROPERTY_PREFIX + ".iSerialSpeed"));
			} else if (iMediaType_.equals("ip")) {
				this.iMediaType = MEDIA_ETHERNET;
				this.strIPAddress = properties.get(PROPERTY_PREFIX + ".strIPAddress");
				this.iTCPPort = Integer.parseInt(properties.get(PROPERTY_PREFIX + ".iTCPPort"));
			} else {
				return;
			}

			this.iChannel = Integer.parseInt(properties.get(PROPERTY_PREFIX + ".iChannel"));
			
			// multiple of 500 mW
			this.iPower = Integer.parseInt(properties.get(PROPERTY_PREFIX + ".iPower"));
			

			// Set tag type
			// values are TAG_C210, TAG_C220, TAG_C240, TAG_C270, TAG_EPC,
			// TAG_ISO15693
			String strTagType = properties.get(PROPERTY_PREFIX+ ".strTagType");
			
			if(strTagType!=null){
				if (strTagType.equals("TAG_ISO15693")) {
					this.iTagType = TMedioLx00BufferEntry.TAG_ISO15693;
				} else if (strTagType.equals("TAG_C210")) {
					this.iTagType = TMedioLx00BufferEntry.TAG_C210;
				} else if (strTagType.equals("TAG_C220")) {
					this.iTagType = TMedioLx00BufferEntry.TAG_C220;
				} else if (strTagType.equals("TAG_C240")) {
					this.iTagType = TMedioLx00BufferEntry.TAG_C240;
				} else if (strTagType.equals("TAG_C270")) {
					this.iTagType = TMedioLx00BufferEntry.TAG_C270;
				} else if (strTagType.equals("TAG_EPC")) {
					this.iTagType = TMedioLx00BufferEntry.TAG_EPC;
				} else {
					// unknown tag type
					this.iTagType = -1;
				}
			} else {
				// unknown tag type
				this.iTagType = -1;
			}

			// Set continuous
			this.boCont = Boolean.parseBoolean(properties.get(PROPERTY_PREFIX + ".boCont"));

			// Set sorting
			this.boSort = Boolean.parseBoolean(properties.get(PROPERTY_PREFIX + ".boSort"));

		} else {
			return;
		}
	}
	
	protected Object clone() {
		TagsysSettings clonedSettings = new TagsysSettings();
		clonedSettings.boCont = this.boCont;
		clonedSettings.boSort = this.boSort;
		clonedSettings.iChannel = this.iChannel;
		clonedSettings.iMediaType = this.iMediaType;
		clonedSettings.iPower = this.iPower;
		clonedSettings.iSerialSpeed = this.iSerialSpeed;
		clonedSettings.iTagType = this.iTagType;
		clonedSettings.iTCPPort = this.iTCPPort;
		clonedSettings.iTimer = this.iTimer;
		clonedSettings.strIPAddress = this.strIPAddress;
		clonedSettings.strSerialName = this.strSerialName;
		return clonedSettings;
	}
};
