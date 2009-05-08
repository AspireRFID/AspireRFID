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
public class ModifiedUnit {
	
	private String oldEpc;
	private String newEpc;//if applicable
	private String oldAttribute;
	private String newAttribute;
	private String oldValue;
	private String newValue;
	private boolean justRemove;
	private boolean newAddition;
	
	public ModifiedUnit()
	{
		oldEpc = null;
		newEpc = null;
		oldAttribute = null;
		oldValue = null;
		newValue = null;
		justRemove = false;
		newAddition = false;
	}
	public void setNewAddition(boolean b)
	{
		newAddition = b;
	}
	public boolean isNewAddition()
	{
		return newAddition;
	}
	
	public void setRemove(boolean b)
	{
		this.justRemove = b;
	}
	public boolean toRemove()
	{
		return this.justRemove;
	}
	
	public void setOldEPC(String epc)
	{
		this.oldEpc = epc;
	}
	public String getOldEpc()
	{
		return this.oldEpc;
	}
	public void setNewEPC(String epc)
	{
		this.newEpc = epc;
	}
	public void setOldAttribute(String attr)
	{
		this.oldAttribute = attr;
	}
	public String getOldAttribute()
	{
		return this.oldAttribute;
	}
	
	public void setNewAttribute(String attr)
	{
		this.newAttribute = attr;
	}
	public String getNewAttribute()
	{
		return this.newAttribute;
	}
	
	public void setOldValue(String val)
	{
		this.oldValue = val;
	}
	public String getOldValue()
	{
		return this.oldValue;
	}
	public void setNewValue(String val)
	{
		this.newValue = val;
	}
	public String getNewValue()
	{
		return this.newValue;
	}
	

}
