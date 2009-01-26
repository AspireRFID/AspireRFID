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
package org.ow2.aspirerfid.reader.fictive;

import org.ow2.aspirerfid.reader.RfidReaderMBean;
import org.ow2.aspirerfid.wires.RFIDTagRead;

/**
 * Define the configuration interface for a fictive Rfid reader
 * 
 * @author Anne Robert
 * @author Guillaume Surrel
 * @version 2.0 2007
 */
public interface RfidReaderSimulatorMBean extends RfidReaderMBean {

	/**
	 * setter for the tag list used to randomly choose read tags.
	 * 
	 * @param tagList
	 *            a string array containing the Rfid guid
	 */
	public void setTagList(String[] tagList);

	/**
	 * getter for the tag list used to randomly choose read tags.
	 * 
	 * @return the list of tag fictitiously read
	 */
	public String[] getTagList();

	/**
	 * setter for the maximum of simultaneous read tag the reader will read a
	 * random number between 0 and the max
	 * 
	 * @param max
	 *            the maximum tag read in a command
	 */
	public void setMaxTagRead(int max);

	/**
	 * getter for the maximum of simultaneous read tag the reader will read a
	 * random number between 0 and the max
	 * 
	 * @return the max number of tag read in a command
	 */
	public int getMaxTagRead();

	/**
	 * setter for the guid reader for real reader this value is not writable
	 * 
	 * @param readerGuid
	 *            the fictive value of the reader Guid
	 */
	public void setReaderGUId(String readerGuid);

	/**
	 * Tells the fictitious reader to read a tag
	 * 
	 * @param tag
	 *            the tag to read
	 * @return TODO Javadoc
	 */
	public RFIDTagRead readThisTag(String tag);

	/**
	 * Tells the fictitious reader to read a tag list
	 * 
	 * @param tags
	 *            the list of tags
	 */
	public void readTheseTags(String[] tags);

}
