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

import org.ow2.aspirerfid.ide.masterdata.tools.QueryClientGuiHelper;

/**
 * @author Eystathios Mertikas e-mail: efme@ait.edu.gr
 * 
 */
public class Transaction extends EPCIS_Class {
	
	private List<EPCIS_Event> events;
	private String event_items;
	int NumSteps;
	public Transaction()
	{
		super("urn:epcglobal:epcis:vtype:BusinessTransaction","bizTransactionAttribute");
		NumSteps = 0;
		events = new ArrayList<EPCIS_Event>();
	}
	
	public Transaction(String EPC_Tag, String Name)
	{
		super(EPC_Tag, Name,"bizTransaction","bizTransactionAttribute");
		NumSteps = 0;
		events = new ArrayList<EPCIS_Event>();
	}
	
	public List<EPCIS_Event> getEvents()
	{
		return this.events;
	}
	public void setEvent_items(String ch)
	{
		this.event_items = ch; 
	}
	public String getEvent_items()
	{
		return event_items;
	}
	public void addEvent(EPCIS_Event ev)
	{
		this.events.add(ev);
		
	}

	public void removeEvent(int index)
	{
		this.events.remove(index);
	}
	
	public void addEvent(EPCIS_Event ev, int index)
	{
		this.events.add(index,ev);
	}
	
	public EPCIS_Event getEvent(int index)
	{
		return this.events.get(index);
	}

	public void insertChildren_DB()
	{
		
		StringBuffer children = new StringBuffer();
		boolean hasChildren = false;
		
		Iterator<EPCIS_Event> it = this.getEvents().iterator();
		int total = this.getEvents().size();
		//while(it.hasNext())
		for(int i = 0; i < total; i++)
		{
			hasChildren = true;
			EPCIS_Event temp = it.next();
			children.append(temp.getEPC());
			if(i < total -1)
				children.append(",");//+" ");//separate children with empty space			
			
		}
		if(hasChildren)
			this.insert_Attribute_Value(new Attribute("Children",children.toString()));
		
	}
	public void insertEvents_DB()
	{
		Iterator<EPCIS_Event> it = this.getEvents().iterator();
		
		
		while(it.hasNext())
		{
			EPCIS_Event temp = it.next();
			temp.insertEPC_DB();
			temp.insert_Attribute_Value(new Attribute("event_type",temp.getEventType()));
			temp.insert_Attribute_Value(new Attribute("business_location",temp.getBizLoc()));
			temp.insert_Attribute_Value(new Attribute("business_step",temp.getBizStep()));
			temp.insert_Attribute_Value(new Attribute("disposition",temp.getDisposition()));
			temp.insert_Attribute_Value(new Attribute("ecspec_name",temp.getECSpec()));
			temp.insert_Attribute_Value(new Attribute("read_point", temp.getReadPoint()));
			temp.insert_Attribute_Value(new Attribute("action", temp.getAction()));
			temp.insert_Attribute_Value(new Attribute("event_name", temp.getName()));
			temp.insert_Attribute_Value(new Attribute("ecreport_names",temp.getECReportName()));
			System.out.println("Event Type:"+temp.getEventType());
			System.out.println("Business Location:"+temp.getBizLoc());
			System.out.println("Business Step:"+temp.getBizStep());
			System.out.println("Business Disposition:"+temp.getDisposition());
			System.out.println("ECSpec:"+temp.getECSpec());
			System.out.println("Read Point:"+temp.getReadPoint());
			System.out.println("Report Names"+temp.getECReportName());
		
			
		}
	}



}
