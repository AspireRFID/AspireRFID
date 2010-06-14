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

package org.ow2.aspirerfid.rp.api.factories;

import org.ow2.aspirerfid.rp.api.DataSelector;
import org.ow2.aspirerfid.rp.api.NotificationChannel;
import org.ow2.aspirerfid.rp.api.RPException;
import org.ow2.aspirerfid.rp.api.Source;
import org.ow2.aspirerfid.rp.api.TagField;
import org.ow2.aspirerfid.rp.api.TagSelector;
import org.ow2.aspirerfid.rp.api.Trigger;
import org.ow2.aspirerfid.rp.api.enumerations.TriggerType;


/**
 * This interface specifies a Manageable Reader. For creation and maximum 
 * number support for each model component : Source, NotificationChannel...etc.
 * 
 * @author rdagher
 *
 */
public interface Manageable {
	
	/*
	 * Object Source.
	 */
	public Source Source_create(String name) throws RPException;
	
	public int Source_getMaxNumberSupported() throws RPException;
	
	/*
	 * Object Trigger.
	 */
	public Trigger Trigger_create (String name, TriggerType type, String value)throws RPException;
	
	public int Trigger_getMaxNumberSupported() throws RPException;
	
	/*
	 * Object TagSelector.
	 */
	public TagSelector TagSelector_create(String name,
										  TagField field,
										  String value /* hex */,
										  String mask /* hex */,
										  boolean inclusiveFlag)throws RPException;
	
	public int TagSelector_getMaxNumberSupported() throws RPException;
	
	/*
	 * Object NotificationChannel.
	 */
	public NotificationChannel NotificationChannel_create (String name, String address)throws RPException;
	
	public int NotificationChannel_getMaxNumberSupported() throws RPException;
	
	/*
	 * Object DataSelector.
	 */
	public DataSelector DataSelector_create (String name)throws RPException;
	
	public int DataSelector_getMaxNumberSupported() throws RPException;
	
	
	/*
	 * Object TagField.
	 */
	public TagField TagField_create (String name)throws RPException;
	
	public int TagField_getMaxNumberSupported() throws RPException;	
	
}
