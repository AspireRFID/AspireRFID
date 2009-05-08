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
import java.util.*;

import org.eclipse.jface.preference.IPreferenceStore;
import org.ow2.aspirerfid.ide.masterdata.Activator;
import org.ow2.aspirerfid.ide.masterdata.preferences.PreferenceConstants;
import org.ow2.aspirerfid.ide.masterdata.tools.*;

/**
 * @author Eystathios Mertikas e-mail: efme@ait.edu.gr
 * @author Nikos Kefalakis (nkef) e-mail: nkef@ait.edu.gr
 *
 */
public class UpdateDB {
	
	/** Returns the preference store for this UI plug-in */
	IPreferenceStore preferences = Activator.getDefault().getPreferenceStore();
	
	private List<ModifiedUnit> units;
	
	public UpdateDB()
	{
		units = new ArrayList<ModifiedUnit>();
		
	}
	
	public void addUnit(ModifiedUnit u)
	{
		units.add(u);
	}
	
	public List<ModifiedUnit> getAll()
	{
		return units;
	}
	
	public void flush(String queryUrl)
	{
		String mode = "2";
		System.out.println("Query url is "+queryUrl);
		MasterDataCaptureClient cedit = new MasterDataCaptureClient(preferences.getString(PreferenceConstants.P_MdeEpcisRepositoryCaptureURL));
		Iterator<ModifiedUnit>it = this.units.iterator();
		while(it.hasNext())
		{
			ModifiedUnit temp = it.next();
			System.out.println("Modify: "+temp.getOldAttribute()+" attribute "+temp.getOldAttribute()+" value"+temp.getNewValue());
			//if(temp.getOldAttribute().endsWith("Read Point"))
			//{
			//	temp.setNewValue(temp.getOldValue()+"_"+temp.getNewValue());
			//}
			if(temp.toRemove())
				mode = "3";
			cedit.simpleMasterDataAndAttributeEdit("urn:epcglobal:epcis:vtype:BusinessLocation", temp.getOldEpc(), temp.getOldAttribute(), temp.getNewValue(), mode);//"2");
			
			
			
			
			
		}
		
		
	}
	
	public void flush2(String queryUrl,String vocabulary)
	{
		String mode = "2";
		System.out.println("Query url is "+queryUrl);
		MasterDataCaptureClient cedit = new MasterDataCaptureClient(preferences.getString(PreferenceConstants.P_MdeEpcisRepositoryCaptureURL));
		Iterator<ModifiedUnit>it = this.units.iterator();
		while(it.hasNext())
		{
			ModifiedUnit temp = it.next();
			System.out.println("Modify: "+temp.getOldAttribute()+" attribute "+temp.getOldAttribute()+" value"+temp.getNewValue());
			
			if(temp.toRemove())
				mode = "3";
			cedit.simpleMasterDataAndAttributeEdit(vocabulary, temp.getOldEpc(), temp.getOldAttribute(), temp.getNewValue(), mode);//"2");
			
			
			
			
			
		}
		
		
	}
	
	public void clear()
	{
		this.units.clear();
	}
	
	public void updateMemory(EPCIS_Class element)
	{
		Iterator<ModifiedUnit>it = units.iterator();
		
		while(it.hasNext())
		{
			ModifiedUnit mod_attr = it.next();
			if(mod_attr.isNewAddition())
				element.getAttributes().add(new Attribute(mod_attr.getOldAttribute(),mod_attr.getNewValue()));
			else
			{
				Iterator<Attribute> attributesIt = element.getAttributes().iterator();
				while(attributesIt.hasNext())
				{
					Attribute temp = attributesIt.next();
					if(temp.getAttribute().endsWith(mod_attr.getOldAttribute()))
					{
						if(mod_attr.toRemove())//has been removed
						{
							element.getAttributes().remove(temp);
							break;
						}						
						else
						{					
							if(element instanceof BusinessLocation && mod_attr.getOldAttribute().endsWith("ReadPoint"))
							{
								((BusinessLocation)element).clearReadPoints();
								((BusinessLocation)element).setReadPoint(mod_attr.getNewValue());
							}
							else if(mod_attr.getOldAttribute().endsWith("Name"))
							{
								element.setName(mod_attr.getNewValue());
							}
							else
								temp.setValue(mod_attr.getNewValue());
						}
					}
					
				}
				
			}
		}
			
		
	}
	
	public void updateMemory_BL(BusinessLocation bl_mem)
	{
		Iterator<ModifiedUnit>it = units.iterator();
		
		while(it.hasNext())
		{
			ModifiedUnit mod_attr = it.next();
			if(mod_attr.isNewAddition())
				bl_mem.getAttributes().add(new Attribute(mod_attr.getOldAttribute(),mod_attr.getNewValue()));
			else
			{
				Iterator<Attribute> attributesIt = bl_mem.getAttributes().iterator();
				while(attributesIt.hasNext())
				{
					Attribute temp = attributesIt.next();
					if(temp.getAttribute().endsWith(mod_attr.getOldAttribute()))
					{
						if(mod_attr.toRemove())//has been removed
						{
							bl_mem.getAttributes().remove(temp);
							break;
						}						
						else
						{					
							if(mod_attr.getOldAttribute().endsWith("ReadPoint"))
							{
								bl_mem.clearReadPoints();
								bl_mem.setReadPoint(mod_attr.getNewValue());
							}
							else if(mod_attr.getOldAttribute().endsWith("Name"))
							{
								bl_mem.setName(mod_attr.getNewValue());
							}
							else
								temp.setValue(mod_attr.getNewValue());
						}
					}
					
				}
				
			}
			
		}
		
		
		
		
	/*	while(attributesIt.hasNext())
		{
			Attribute temp = attributesIt.next();
			while(it.hasNext())
			{
				ModifiedUnit mod_attr = it.next();
				
				if(temp.getAttribute().endsWith(mod_attr.getOldAttribute()))
				{
					if(mod_attr.getOldAttribute().endsWith("ReadPoint"))
					{
						bl_mem.clearReadPoints();
						bl_mem.setReadPoint(mod_attr.getNewValue());
					}
					else if(mod_attr.getOldAttribute().endsWith("Name"))
					{
						bl_mem.setName(mod_attr.getNewValue());
					}
					else
						temp.setValue(mod_attr.getNewValue());
				}
			}
			it = units.iterator();
			
		}*/
	
		
		
		
	}
	public BusinessLocation getNewUnit(BusinessLocation oldUnit)
	{
		BusinessLocation newUnit = new BusinessLocation();
		Iterator<ModifiedUnit>it = units.iterator();
		List<Attribute> attrs = oldUnit.getAttributes();
		List<Attribute> newAttrs = new ArrayList<Attribute>();
		boolean done = false;
		newUnit.setfqTag(oldUnit.getFQTag());
		newUnit.setEPC(oldUnit.getEPC());
		newUnit.setName(oldUnit.getName());
		Iterator<Attribute> old_it = attrs.iterator();
		while(old_it.hasNext())//iterate though list
		{
			Attribute temp = old_it.next();
			System.out.println("In new unit attr = "+temp.getAttribute()+" Value = "+temp.getValue());
			while(it.hasNext())
			{
				ModifiedUnit u = it.next();
				
				if(u.getOldAttribute().equals("Read Point"))
				{
					newUnit.clearReadPoints();
					if(!u.toRemove())
						newUnit.setReadPoint(u.getNewValue());
				}
				if(u.getOldAttribute().equals("Name"))
					newUnit.setName(u.getNewValue());
				
				if(u.getOldAttribute().equalsIgnoreCase(temp.getAttribute()))
				{
					System.out.println("Found diff"+u.getOldAttribute()+"  Val="+u.getNewValue());
					Attribute pair = new Attribute();
					pair.setAttribute(u.getOldAttribute());
					pair.setValue(u.getNewValue());
					newAttrs.add(pair);
					done = true;
					break;
				}
			}
			if(!done)
			{
				newAttrs.add(temp);//unchanged
			}
			it = units.iterator();
		}
		newUnit.addAll(newAttrs);
		
		return newUnit;
		
			
				
				
				
		
		
		
		
	}
	
	
	

}
