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
package org.ow2.aspirerfid.common.cron;

import java.io.Serializable;

/**
 * Interface CronService provides description of all methods exported by the
 * schedule bundle like registering and unregistering timed objects
 * 
 * @author Unknown
 * @version 2006
 */
public interface CronService {

	/**
	 * This method register a timed object for a schedule described in the
	 * pattern
	 * 
	 * @param timedObject
	 *            the timed object to register
	 * @param serializable
	 *            the serializable object to pass as parameter
	 * @param cronPattern
	 *            the time parameter corresponding to the action
	 */
	public void add(TimedObject timedObject, Serializable serializable,
			String cronPattern);

	/**
	 * This method unregister the timed object
	 * 
	 * @param timedObject
	 *            the timed object to register
	 * @param serializable
	 *            the serializable object to pass as parameter
	 */
	public void remove(TimedObject timedObject, Serializable serializable);

	/**
	 * Translate a period in millisecond to a string used by the, cron service
	 * to specify a periodical task
	 * 
	 * @param period
	 *            the wanted period in millisecond
	 * @return the string specifying a periodical task
	 */
	public String cronString(long period);

}
