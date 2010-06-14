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

public interface Source {
			
	public String getName() throws RPException;

	public boolean isFixed() throws RPException;

	public void addReadPoints(ReadPoint[] readPoints) throws RPException;

	public void removeReadPoints(ReadPoint[] readPoints) throws RPException;

	public void removeAllReadPoints() throws RPException;

	public ReadPoint getReadPoint(String name) throws RPException;

	public ReadPoint[] getAllReadPoints() throws RPException;

	public void addReadTriggers(Trigger[] triggers)
			throws RPException;

	public void removeReadTriggers(Trigger[] triggers) throws RPException;

	public void removeAllReadTriggers() throws RPException;

	public Trigger getReadTrigger(String name) throws RPException;

	public Trigger[] getAllReadTriggers() throws RPException;

	public void addTagSelectors(TagSelector[] selectors)
			throws RPException;

	public void removeTagSelectors(TagSelector[] selectors) throws RPException;

	public void removeAllTagSelectors() throws RPException;

	public TagSelector getTagSelector(String name)
			throws RPException;

	public TagSelector[] getAllTagSelectors() throws RPException;

	public int getGlimpsedTimeout() throws RPException;

	public void setGlimpsedTimeout(int timeout) throws RPException;

	public int getObservedThreshold() throws RPException;

	public void setObservedThreshold(int threshold)
			throws RPException;

	public int getObservedTimeout() throws RPException;

	public void setObservedTimeout(int timeout) throws RPException;

	public int getLostTimeout() throws RPException;

	public void setLostTimeout(int timeout) throws RPException;

	public ReadReport rawReadIDs(DataSelector dataselector) throws RPException;

	public ReadReport readIDs(DataSelector dataselector) throws RPException;

	public ReadReport read(DataSelector dataSelector, String[] passwords, 
			TagSelector[] selectors) throws RPException;

	public void writeID(String newID, String[] passwords,
			TagSelector[] selectors) throws RPException;

	public void write(TagFieldValue[] data,
			String[] passwords, TagSelector[] selectors)
			throws RPException;

	/**
	 * 
	 * Kills the specified tag or group of tags. An optional list of TagSelector objects can
	 * be specified with this command. If specified, the TagSelector list parameter overrides the
	 * Source object’s list of TagSelector objects that specify the selection of tags to be
	 * killed.<br>
	 * 
	 * A tag’s RF protocol may require one or more passwords (or lock code) to successfully
	 * complete this operation. Such use of passwords and their application to specific RF
	 * protocols SHALL be documented.<br>
	 * 
	 * Implementations MAY impose restrictions on the usage of this command if Read
	 * Triggers are active. Such restrictions SHALL be documented.
	 * Executing this command with no tags in the field is an error resulting in
	 * ERROR_NO_TAG.<br>
	 * 
	 * Executing this command on a Source with no ReadPoint is an error resulting in
	 * ERROR_READPOINT_NOT_FOUND.
	 * 
	 * @param passwords (Data Type: binary[]) Optional. A list of one or more passwords (or
	 * lock code). The use of passwords is dependent upon the tag’s RF protocol. For
	 * instance, the EPCglobal UHF Gen2 RF protocol requires both an access password and a
	 * kill password to kill a tag.
	 * 
	 * @param selectors Optional. If this list is provided then it MUST be applied as a filter specifying which tags MAY be killed.
	 * 
	 * @throws RPException
	 */
	public void kill(String[] passwords, TagSelector[] selectors)
			throws RPException;

	public int getReadCyclesPerTrigger() throws RPException;

	public void setReadCyclesPerTrigger(int cycles) throws RPException;

	public int getMaxReadDutyCycles() throws RPException;

	public void setMaxReadDutyCycles(int cycles) throws RPException;

	public int getReadTimeout() throws RPException;

	public void setReadTimeout(int timeout) throws RPException;

	public int getSession() throws RPException;

	public void setSession(int session) throws RPException;

}