/*
 * Copyright © 2008-2010, Aspire
 * 
 * Aspire is free software; you can redistribute it and/or modify it under the
 * terms of the GNU Lesser General Public License version 2.1 as published by
 * the Free Software Foundation (the "LGPL").
 * 
 * You should have received a copy of the GNU Lesser General Public License
 * along with this library in the file COPYING-LGPL-2.1; if not, write to the
 * Free Software Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA
 * 02110-1301 USA.
 * 
 * This software is distributed on an "AS IS" basis, WITHOUT WARRANTY OF ANY
 * KIND, either express or implied. See the GNU Lesser General Public License
 * for the specific language governing rights and limitations.
 */


package org.ow2.aspirerfid.ide.masterdata.classes;

import java.util.ArrayList;
import java.util.List;


/**
 * @author Eystathios Mertikas e-mail: efme@ait.edu.gr
 * 
 */
public class EpcisEvent {
	
	private String uri;
	private List<Attribute> attributes;
	
	public EpcisEvent(String id)
	{
		uri = id;
		attributes = new ArrayList<Attribute>();
	}
	
	public List<Attribute> getAttributes()
	{
		return attributes;
	}
	public String getUri()
	{
		return uri;
	}
	
	public void setUri(String uri)
	{
		this.uri = uri;
	
	}
	
	
	
	

}
