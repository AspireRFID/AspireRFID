/**
 * Copyright (c) 2008-2010, AspireRFID
 *
 * This library is free software; you can redistribute it and/or
 * modify it either under the terms of the GNU Lesser General Public
 * License version 2.1 as published by the Free Software Foundation
 * (the "LGPL"). If you do not alter this
 * notice, a recipient may use your version of this file under the LGPL.
 *
 * You should have received a copy of the LGPL along with this library
 * in the file COPYING-LGPL-2.1; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA
 *
 * This software is distributed on an "AS IS" basis, WITHOUT WARRANTY
 * OF ANY KIND, either express or implied. See the LGPL  for
 * the specific language governing rights and limitations.
 *
 * Contact: AspireRFID mailto:aspirerfid@ow2.org
 */

package org.ow2.aspirerfid.rp.api;

/**
 * The ReaderDevice object represents the Reader. It is a singleton object that SHALL
 * exist by default. There are therefore no commands to create or delete it.
 * The ReaderDevice object keeps lists of all Sources, DataSelectors,
 * NotificationChannels, Triggers and TagSelectors known. The objects
 * SHALL be added to these lists implicitly when created.
 * 
 * @author rdagher
 *
 */
public interface ReaderDevice {
	
	
	public String getEPC() throws RPException;
	
	public String getManufacturer() throws RPException;

	public String getModel() throws RPException;

	public int getHandle() throws RPException;
	
	public void setHandle(int handle) throws RPException;
	
	public String getName() throws RPException;

	public void setName(String name) throws RPException;

	public String getRole() throws RPException;
	
	public void setRole(String role) throws RPException;
	
	public int getTimeTicks() throws RPException;

	public String getTimeUTC() throws RPException;
	
	public void setTimeUTC(String time) throws RPException;
	
	public String getManufacturerDescription() throws RPException;
	
	public Source getCurrentSource() throws RPException;

	public void setCurrentSource(Source source) throws RPException;
	
	public DataSelector getCurrentDataSelector() throws RPException;

	public void setCurrentDataSelector(DataSelector dataSelector) throws RPException;

	public void removeSources(Source[] sources) throws RPException;
	
	public void removeAllSources() throws RPException;
	
	public Source getSource(String name) throws RPException;
	
	public Source[] getAllSources() throws RPException;

	public void removeDataSelectors(DataSelector[] dataSelectors) throws RPException;
	
	public void removeAllDataSelectors() throws RPException;
	
	public DataSelector getDataSelector(String name) throws RPException;

	public DataSelector[] getAllDataSelectors() throws RPException;

	public void removeNotificationChannels(NotificationChannel[] notificationChannels) throws RPException;
	
	public void removeAllNotificationChannels() throws RPException;
	
	public NotificationChannel getNotificationChannel(String name) throws RPException;

	public NotificationChannel[] getAllNotificationChannels() throws RPException;

	public void removeTriggers(Trigger[] triggers) throws RPException;
	
	public void removeAllTriggers() throws RPException;
	
	public Trigger getTrigger(String name) throws RPException;

	public Trigger[] getAllTriggers() throws RPException;

	public void removeTagSelectors(TagSelector[] tagSelectors) throws RPException;
	
	public void removeAllTagSelectors() throws RPException;
	
	public TagSelector getTagSelector(String name) throws RPException;

	public TagSelector[] getAllTagSelectors() throws RPException;

	public void removeTagFields(TagField[] tagFields) throws RPException;
	
	public void removeAllTagFields() throws RPException;
	
	public TagField getTagField(String name) throws RPException;

	public TagField[] getAllTagFields() throws RPException;

	public void resetToDefaultSettings() throws RPException;

	public void reboot() throws RPException;

	public void goodbye() throws RPException;

	public ReadPoint getReadPoint(String name) throws RPException;

	public ReadPoint[] getAllReadPoints() throws RPException;
}