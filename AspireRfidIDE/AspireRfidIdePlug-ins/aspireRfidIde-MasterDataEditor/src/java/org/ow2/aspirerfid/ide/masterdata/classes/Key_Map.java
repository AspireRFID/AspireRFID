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

import javax.swing.text.Keymap;


/**
 * @author Eystathios Mertikas e-mail: efme@ait.edu.gr
 * 
 */
public class Key_Map implements Comparable<Key_Map>{
	
	private String Logical_Name;
	int index;//this is for the index in the combo box, to allow for non-unique names
	
	public Key_Map(String Logical_Name, int index)
	{
		this.Logical_Name = Logical_Name;
		this.index = index;
	}
	public Key_Map()
	{
		Logical_Name = "";
		index = -1;
	}
	
	public void setName(String Name)
	{
		this.Logical_Name = Name;
	}

	public void setIdx(int index)
	{
		this.index = index;
	}
	
	public String getName()
	{
		return this.Logical_Name;
	}

	public int getIdx()
	{
		return this.index;
	}
	
	public int compareTo(Key_Map key)
	{
		//if(this.Logical_Name.equals(key.getName()) && this.index == key.getIdx())
		//if(this.Logical_Name.equals(key.getName()) && this.index == key.getIdx())
			return this.getIdx() - key.getIdx();//this.getName().compareTo(key.getName());
		//return false;
	}
	
	public boolean same(Key_Map key)
	{
		if(this.Logical_Name.equals(key.getName()) && this.index == key.getIdx())
			return true;
		return false;
		
	}
	
}
