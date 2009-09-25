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

package org.ow2.aspirerfid.beg.simmulator;

import java.util.ArrayList;
import java.util.List;

import org.fosstrak.epcis.model.BusinessLocationType;
import org.fosstrak.epcis.model.ActionType;
import org.fosstrak.epcis.model.ReadPointType;


/**
 * @author Nikos Kefalakis (nkef) e-mail: nkef@ait.edu.gr
 * @author Efstathios Mertikas
 *
 */
public class BusinessCtx {
	
	private String event_type;
	private BusinessLocationType bizLocation;
	private String bizStep;
	private String disposition;
	private String ecspecName;
	private ReadPointType readPoint;
	private ActionType action;
	private ArrayList<String> ecReportNames = new ArrayList<String>();
	private String businessTransactionTypeID;
	
	public String getBusinessTransactionTypeID() {
		return businessTransactionTypeID;
	}



	public void setBusinessTransactionTypeID(String businessTransactionTypeID) {
		this.businessTransactionTypeID = businessTransactionTypeID;
	}



	public ArrayList<String> getEcReportNames() {
		return ecReportNames;
	}

	
	
	public void setEcReportNames(String stringOfEcReportNames) {
		String[] arrayOfReportNames = stringOfEcReportNames.split(",");
		
		for(int i = 0 ; i<arrayOfReportNames.length; i++){
			ecReportNames.add(arrayOfReportNames[i].toString());
		}
	}

	public BusinessCtx(){
		
	}
	
	public void setEventType(String et)
	{
		this.event_type = et;
	}
	public String getEventType()
	{
		return this.event_type;
	}
	
	public void setBizLocation(String bizLoc)
	{
		this.bizLocation  = new BusinessLocationType();
		bizLocation.setId(bizLoc);
		
	}
	public BusinessLocationType getBizLocation()
	{
		return this.bizLocation;
	}
	public void setDisposition(String disposition)
	{
		this.disposition = disposition;
	}
	public String getDisposition()
	{
		return this.disposition;
	}

	public void setBizStep(String bizStep)
	{
		this.bizStep = bizStep;
	}
	public String getBizStep()
	{
		return this.bizStep;
	}
	
	public void setECSpecName(String ecspecName)
	{
		this.ecspecName = ecspecName;
	}
	public String getECSpecName()
	{
		return this.ecspecName;
	}

	public void setReadPoint(String readPoint)
	{
		this.readPoint = new ReadPointType();
		this.readPoint.setId(readPoint);
		
	}
	public ReadPointType getReadPoint()
	{
		return this.readPoint;
	}
	public void setAction(String action)
	{
		if(action.equalsIgnoreCase("ADD"))
			this.action = ActionType.ADD;
		else if(action.equalsIgnoreCase("DELETE"))
			this.action = ActionType.DELETE;
		else if(action.equalsIgnoreCase("OBSERVE"))
			this.action = ActionType.OBSERVE;
		else
			this.action = ActionType.OBSERVE;
		
		
		
	}
	public ActionType getAction()
	{
		return this.action;
	}
	

}
