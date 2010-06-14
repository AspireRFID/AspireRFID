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

package org.ow2.aspirerfid.rp.api.enumerations;

public interface FieldName {
	
	public final static String ALL                  = "all";
	public final static String ALL_EVENT            = "allEvent";
	public final static String ALL_NOTIFY           = "allNotify";
	public final static String ALL_READER           = "allReader";
	public final static String ALL_SOURCE           = "allSource";
	public final static String ALL_SUPPORTED        = "allSupported";
	public final static String ALL_TAG              = "allTag";
	public final static String EVENT_TIME_TICK      = "eventTimeTick";
	public final static String EVENT_TIME_UTC       = "eventTimeUTC";
	public final static String EVENT_TRIGGERS       = "eventTriggers";
	public final static String EVENT_TYPE           = "eventType";
	public final static String NOTIFY_CHANNEL_NAME  = "notifyChannelName";
	public final static String NOTIFY_TRIGGER_NAME  = "notifyTriggerName";
	public final static String READER_EPC           = "readerEPC";
	public final static String READER_HANDLE        = "readerHandle";
	public final static String READER_NAME          = "readerName";
	public final static String READER_NOW_TICK      = "readerNowTick";
	public final static String READER_NOW_UTC       = "readerNowUTC";
	public final static String READER_ROLE          = "readerRole";
	public final static String SOURCE_FREQUENCY     = "sourceFrequency";
	public final static String SOURCE_NAME          = "sourceName";
	public final static String SOURCE_PROTOCOL      = "sourceProtocol";
	public final static String TAGID                = "tagID";
	public final static String TAGID_AS_PURE_URI    = "tagIDasPureURI";
	public final static String TAGID_AS_TAG_URI     = "tagIDasTagURI";
	public final static String TAG_TYPE             = "tagType";
	
	public final String[] MANDATORY_FIELDS = {READER_EPC, READER_NAME, READER_ROLE, TAGID, ALL_SUPPORTED};
	
	public String[] getSupportedNames();
}
