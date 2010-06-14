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

import org.ow2.aspirerfid.rp.api.readreport.ReadReport;

/**
 * A NotificationChannel is used to report notifications to a single host. It has a list
 * of Sources associated with it. Only events coming from these Sources SHALL be
 * reported. It also has a DataSelector that determines what data should be reported to the
 * host. If no DataSelector is explicitly associated, then the Reader’s current
 * DataSelector SHALL be used.
 * Notification SHALL be sent whenever the associated Trigger fires. Alternatively, a
 * host MAY also query the NotificationChannel for its contents, using the
 * command NotificationChannel.readQueuedData().
 * 
 * @author rdagher
 *
 */
public interface NotificationChannel {
		
	public String getName() throws RPException;

	public String getAddress() throws RPException;

	public String getEffectiveAddress() throws RPException;

	public int setAddress(String addr) throws RPException;

	public DataSelector getDataSelector() throws RPException;

	public void setDataSelector(DataSelector dataSelector) throws RPException;

	public void addSources(Source[] sourceList) throws RPException;
	
	public void removeSources(Source[] sources) throws RPException;
	
	public void removeAllSources() throws RPException;

	public Source getSource(String name) throws RPException;
	
	public Source[] getAllSources() throws RPException;
	
	public void addNotificationTriggers(Trigger[] triggers)
			throws RPException;

	public void removeNotificationTriggers(Trigger[] triggers) throws RPException;

	public void removeAllNotificationTriggers() throws RPException;

	public Trigger getNotificationTrigger(String name) throws RPException;

	public Trigger[] getAllNotificationTriggers() throws RPException;

	public ReadReport readQueuedData(boolean clearBuffer) throws RPException;
	
}