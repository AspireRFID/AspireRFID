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
public class BusinessLocation extends EPCIS_Class{

	private String parentID;
	//private List<String> children;
	private StringBuffer children;//to be removed
	private java.util.List<String> children2;
	private StringBuffer description;
	boolean hasReadPointsAssigned;
	boolean displayAsChild;
	private String fq_epc;//has mygrandFather_myFather_myTag
	private StringBuffer ReadPoints;
	public boolean justAdded = false;
	//List of locations????? i.e. List<BusinessLocation> children???????
	public BusinessLocation()
	{
		super("urn:epcglobal:epcis:vtype:BusinessLocation","bizLocationAttribute");
		parentID = null;
		children = new StringBuffer();//new ArrayList<String>();
		description = new StringBuffer();
		hasReadPointsAssigned =false;
		displayAsChild = false;
		ReadPoints = new StringBuffer("");
	}
	
	public void setDisplayed(boolean b)
	{
		this.displayAsChild = b;
	}
	public boolean isDisplayed()
	{
		return this.displayAsChild;
	}
	
	public void setDescription(String des)
	{
		this.description.append(des);
	}
	
	
	public String getDescription()
	{
		return this.description.toString();
	}
	
	
	public void setChildren2(List<String> children)
	{
		this.children2 = children;
	}
	public java.util.List<String> getChildren2()
	{
		return this.children2;
	}
	//public String getChildren2()
	//{
//		return this.children2;
	//}
	
	public void setfqTag(String tag)
	{
		this.fq_epc = tag;
	}
	
	public String getFQTag()
	{
		return this.fq_epc;
	}
	
	public BusinessLocation(String EPC_Tag, String Name)
	{
		super(EPC_Tag, Name);
		parentID = null;
		children =  new StringBuffer();// new ArrayList<String>();
	}
	public void setParentID(String pid)
	{
		parentID = pid;
	}
	
	public String getParentID()
	{
		return parentID;
	}
	
	
	public void addChildren(String childTag)
	{
		//this.children.add(childTag);
		children.append(childTag+" ");
	}
	
	public String getChildren()
	{
		return this.children.toString();
	}
	
	
	public void insertChildren_DB()
	{		
		String temp = this.getEPC();
		this.setEPC(this.fq_epc);
		this.insert_Attribute_Value(new Attribute("Children",children.toString()));
		this.setEPC(temp);
		
	}
	public void setHasReadPoints()
	{
		this.hasReadPointsAssigned = true;
	}
	
	public void unsetHasReadPoints()
	{
		this.hasReadPointsAssigned = false;
	}
	public boolean hasReadPoints()
	{
		return this.hasReadPointsAssigned;
	}
	
	public void setEPC(String epc)
	{
		super.setEPC(epc.substring(epc.lastIndexOf(",")+1));
	}
	public void clearReadPoints()
	{
		this.ReadPoints = null;
		ReadPoints = new StringBuffer();
		
		
	}
	public void setReadPoint(String readpoints)
	{
		this.ReadPoints.append(readpoints);
	}
	public StringBuffer getReadpoints()
	{
		return this.ReadPoints;
	}
	public List<String> getReadPoints()
	{
		List<String> read_points = new ArrayList<String>();
		StringTokenizer rps = new StringTokenizer(ReadPoints.toString(),"_");
		int tkn = rps.countTokens();
		for(int i = 0; i < tkn; i++)
		{
		
			read_points.add(rps.nextToken());
		}
		return read_points;
		/*System.out.println("IN GET READ POINTS");
		List<String> read_points = new ArrayList<String>();
		String rp = ReadPoints.toString();
		System.out.println("READ POINTS = "+rp);
		int last = rp.lastIndexOf("_");
		System.out.println("last="+last);
		int pre_index = rp.indexOf("_");
		System.out.println("pre="+pre_index);
		int post_index = rp.indexOf("_", pre_index+1);
		System.out.println("post="+post_index);
		if(last < 0 || (pre_index == last))// && post_index < 0))
		{
			System.out.println(4);
			read_points.add(rp);
			System.out.println(5);
		}
		else
		{
			
			while(pre_index!=last)
			{
				System.out.println(4);
				
				String temp = rp.substring(pre_index+1, post_index-1);
				read_points.add(temp);
				pre_index = post_index+1;
				post_index = rp.indexOf("_", pre_index+1);
			}
		}
		System.out.println(6);
		return read_points;	
		*/
		
		
	}
	
	public String getValueOfAttribute(String attribute)
	{
		Iterator<Attribute>it = this.getAttributes().iterator();
		while(it.hasNext())
		{
			Attribute temp = it.next();
			if(temp.getAttribute().equalsIgnoreCase(attribute))
				return temp.getValue();
		}
		return "";
		
	}
	
}
