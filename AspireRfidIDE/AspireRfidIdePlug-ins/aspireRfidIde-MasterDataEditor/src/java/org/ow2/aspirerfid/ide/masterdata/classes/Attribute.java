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

/**
 * @author Eystathios Mertikas e-mail: efme@ait.edu.gr
 * 
 */

public class Attribute {

	private String attribute;
	private String value;
	
	
	public Attribute(){}
	public Attribute(String attr, String val)
	{
		this.attribute = attr;
		this.value = val;
	}

	public String getAttribute()
	{
		return this.attribute;
	}
	public void setAttribute(String attr)
	{
		this.attribute = attr;
	}
	public String getValue()
	{
		return this.value;
	}
	public void setValue(String val)
	{
		this.value = val;
	}
	@Override public boolean equals(Object o)
	{
		System.out.println("MY EQUALS IS CALLED");
		if(o instanceof String)
		{
			if(this == o)
				return true;
			return ((String)o).equals(attribute);
		}
		return false;
		
	}
	
}
