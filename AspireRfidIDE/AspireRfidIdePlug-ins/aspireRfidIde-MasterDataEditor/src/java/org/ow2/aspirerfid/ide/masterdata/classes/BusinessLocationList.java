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
/**
 * @author Eystathios Mertikas e-mail: efme@ait.edu.gr
 * 
 */
public class BusinessLocationList {
	
	private List<BusinessLocation> bizLocList;
	private HashMap<String,List<String>> Children;
	
	public BusinessLocationList()
	{
		this.bizLocList = new ArrayList<BusinessLocation>();
		this.Children = new HashMap<String,List<String>>();
		
		
	}
	
	public void addAll(List<BusinessLocation> list)
	{
		this.bizLocList.addAll(list);
		
	}
	
	public void addBusinessLocation(BusinessLocation bizLoc)
	{
		this.bizLocList.add(bizLoc);
	}
	
	public void removeBusinessLocation(int index)
	{
		this.bizLocList.remove(index);
	}
	
	public void addBusinessLocation(BusinessLocation bizLoc,int index)
	{
		this.bizLocList.add(index,bizLoc);
	}
	
	private String getEPCTag_byName(String ParentName)
	{
		Iterator<BusinessLocation>it = this.bizLocList.iterator();
		while(it.hasNext())
		{
			BusinessLocation temp = it.next();
			if(temp.getName().equals(ParentName))
				return temp.getEPC();
		}
		return null;
	}
	
	private int getParentIndex(String ParentName)
	{
		int index = 0;
		System.out.println("In getParentIndex");
		if(this.bizLocList == null)
			System.out.println("Loc list null");
			
		Iterator<BusinessLocation>it = this.bizLocList.iterator();
		if(it == null)
			System.out.println("NULL");
		while(it.hasNext())
		{
			System.out.println("in while");
			BusinessLocation temp = it.next();
			System.out.println("name = "+temp.getName());
			if(temp.getName().equals(ParentName))
				return index;
			index++;
		}
		return -1;//Should NEVER reach here
		
	}
	
	public String insertAsChild(String ParentName,String ChildEPCTag)//was void
	{
		System.out.println(1+" "+ParentName);
		int index = this.getParentIndex(ParentName);
		System.out.println(2);
		if(index<0)
			return null;//No child to add ????
		BusinessLocation temp = this.bizLocList.get(index);
		System.out.println(3);
		this.removeBusinessLocation(index);
		System.out.println(4);
		temp.addChildren(ChildEPCTag);
		System.out.println(5);
		this.addBusinessLocation(temp, index);
		System.out.println(6);
		return temp.getEPC();
	}
	
	public List<BusinessLocation> getBusinessLocations()
	{
		return this.bizLocList;
	}
	
	public void addChildren(String parent_uri,List<String>children)
	{
		this.Children.put(parent_uri, children);
	}
	public List<String> fetchChildren(String parent_uri)
	{
		return this.Children.get(parent_uri);
		
	}
	
	

}
