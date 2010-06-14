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
 * DataSelector objects are used to define what data SHALL be reported in notification
 * messages or by commands that take a DataSelector object as parameter. Only events
 * and data fields that are added to the respective lists SHALL be reported.
 *
 * @author rdagher
 *
 */
public interface DataSelector {
	
	/**
	 * Returns the name of the given DataSelector object.
	 * @return The name of the DataSelector object.
	 */
	public String getName() throws RPException;

	/**
	 * Adds the specified field names to the list of field names currently associated with this
	 * DataSelector. If one or more of the FieldNames given are not supported, an error
	 * SHALL be raised and no FieldNames SHALL be added. If some of the FieldNames
	 * to be added are already associated with this DataSelector, only the not yet associated
	 * FieldNames shall be added and the command SHALL complete successfully.
	 * 
	 * @param fields The list of FieldNames to be added.
	 * These data fields SHALL from now on be reported.
	 */
	public void addFieldNames(String[] fields) throws RPException;

	/**
	 * Removes the specified field names from the list of field names currently associated with
	 * this DataSelector. If one or more of the FieldNames given are not supported,
	 * these are ignored and all other FieldNames SHALL be removed and the command
	 * SHALL complete successfully.
	 * Removing a 'default' object changes the 'default' to null. Using a 'null' object is an error
	 * (ERROR_*_NOT_FOUND).
	 * 
	 * @param fields The list of FieldNames to be removed. 
	 * These data fields SHALL NOT be reported anymore.
	 */
	public void removeFieldNames(String[] fields) throws RPException;

	/**
	 * Removes all field names currently associated with this DataSelector.
	 * Removing a 'default' object changes the 'default' to null. Using a 'null' object is an error
	 * (ERROR_*_NOT_FOUND).
	 */
	public void removeAllFieldNames() throws RPException;

	/**
	 * Returns all field names currently associated with this DataSelector.
	 * 
	 * @return The list of all FieldNames currently being reported.
	 */
	public String[] getAllFieldNames() throws RPException;

	/**
	 * Adds the specified EventTypes to the list of EventTypes currently associated
	 * this DataSelector. If one or more of the EventTypes given are not supported,
	 * error SHALL be raised and no EventTypes SHALL be added. If some of the
	 * EventTypes to be added are already associated with this DataSelector, only
	 * not yet associated EventTypes shall be added and the command SHALL complete
	 * successfully.
	 * Only events of the types that have been added SHALL be reported. That is, the
	 * EventTypes added are to be used as inclusive filters.
	 * 
	 * @param events The list of EventTypes to be added. 
	 * Events of these types SHALL from now on be reported.
	 */
	public void addEventFilters(String[] events) throws RPException;

	/**
	 * Removes the specified EventTypes from the list of (inclusive) event filters currently 
	 * associated with this DataSelector. If one or more of the EventTypes given are 
	 * supported, or if some of the EventTypes to be removed are currently not associated
	 * with this DataSelector, these are ignored and all other EventTypes SHALL be
	 * removed and the command SHALL complete successfully.
	 * Removing a 'default' object changes the 'default' to null. Using a 'null' object is an error
	 * (ERROR_*_NOT_FOUND).
	 * 
	 * @param events The list of EventTypes to be removed.
	 * Events of these types SHALL NOT be reported anymore.
	 * @throws RPException
	 */
	public void removeEventFilters(String[] events) throws RPException;

	/**
	 * Removes all event filters currently associated with this DataSelector. 
	 * Removing a 'default' object changes the 'default' to null. Using a 'null' object is an error
	 * (ERROR_*_NOT_FOUND).
	 */
	public void removeAllEventFilters() throws RPException;

	/**
	 * Returns all event filters currently associated with this DataSelector.
	 * @return A list of all EventTypes currently being reported.
	 */
	public String[] getAllEventFilters() throws RPException;

	/**
	 * Adds the specified tag field names to the list of tag field names currently associated with
	 * this DataSelector. If some of the given names to be added are already associated
	 * with this DataSelector, only the not yet associated names shall be added and the
	 * command SHALL complete successfully.
	 * 
	 * @param fieldNames The list of TagField names to be added.
	 * These data fields SHALL from now on be reported.
	 */
	public void addTagFieldNames(String[] fieldNames) throws RPException;

	/**
	 * Removes the specified tag field names from the list of tag field names currently
	 * associated with this DataSelector. If one or more of the names given are not
	 * currently associated with this DataSelector, these are ignored and all other names
	 * SHALL be removed and the command SHALL complete successfully.
	 * Removing a 'default' object changes the 'default' to null. Using a 'null' object is an error
	 * (ERROR_*_NOT_FOUND).
	 * 
	 * @param fieldNames The list of TagField names to be removed.
	 *  These tag data fields SHALL NOT be reported anymore.
	 */
	public void removeTagFieldNames(String[] fieldNames) throws RPException;

	/**
	 * Removes all tag field names currently associated with this DataSelector.
	 * Removing a 'default' object changes the 'default' to null. Using a 'null' object is an error
	 * (ERROR_*_NOT_FOUND).
	 */
	public void removeAllTagFieldNames() throws RPException;

	/**
	 * Returns all tag field names currently associated with this DataSelector.
	 * 
	 * @return The list of all TagField names currently being reported.
	 */
	public String[] getAllTagFieldNames() throws RPException;
	
}