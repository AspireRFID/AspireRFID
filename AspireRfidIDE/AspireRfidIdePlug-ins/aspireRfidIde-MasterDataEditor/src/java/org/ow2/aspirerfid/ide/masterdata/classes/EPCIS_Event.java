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
public class EPCIS_Event extends Transaction {
	
	String EventType;
	String BizLoc;
	String BizStep;
	String Disposition;
	String ReadPoint;
	String ECSpec;
	private String Action;
	private String Name;
	private String ECReportName;
	private String TransactionType;
	
	public EPCIS_Event()
	{
		super();
	}
	public EPCIS_Event(String EPC_Tag, String Name)
	{
		super(EPC_Tag, Name);
	}
	public String getEventType()
	{
		return this.EventType;
	}
	public void setEventType(String type)
	{
		this.EventType = type;
	}
	
	public String getBizLoc()
	{
		return this.BizLoc;
	}
	public void setBizLoc(String loc)
	{
		this.BizLoc = loc;
	}
	public String getBizStep()
	{
		return this.BizStep;
	}
	public void setBizStep(String Bstep)
	{
		this.BizStep = Bstep;
	}
	
	public void setDisposition(String dis)
	{
		this.Disposition = dis;
	}
	public String getDisposition()
	{
		return this.Disposition;
	}
	public String getECSpec()
	{
		return this.ECSpec;
	}

	public void setECSpec(String ecs)
	{
		this.ECSpec = ecs;
	}
	public void setReadPoint(String rp)
	{
		this.ReadPoint = rp;
		
	}
	public String getReadPoint()
	{
		return this.ReadPoint;
	}
	public void setAction(String action)
	{
		this.Action = action;
	}
	public String getAction()
	{
		return this.Action;
	}
	public void setName(String Name)
	{
		this.Name = Name;
		
	}
	public String getName()
	{
		return this.Name;
	}
	
	public void setECReportName(List<String> ECReportNames)
	{
		Iterator<String>it = ECReportNames.iterator();
		StringBuffer reports = new StringBuffer();
		while(it.hasNext())
		{
			reports.append(it.next()+",");
		}
		this.ECReportName = reports.toString().substring(0, reports.toString().lastIndexOf(","));
		
	}
	
	public void setECReportName(String ECReportName)
	{
		this.ECReportName = ECReportName;
	}
	
	public String getECReportName()
	{
		return this.ECReportName;
	}
	public void setTransactionType(String type)
	{
		this.TransactionType = type;
	}
	public String getTransactionType()
	{
		return this.TransactionType;
	}
	
}



