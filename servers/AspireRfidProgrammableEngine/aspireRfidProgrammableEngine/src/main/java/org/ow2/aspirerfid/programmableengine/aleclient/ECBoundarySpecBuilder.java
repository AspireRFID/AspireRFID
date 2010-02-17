/*
 * Copyright (C) 2008-2010, Aspire
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


package org.ow2.aspirerfid.programmableengine.aleclient;


import java.util.List;

import org.ow2.aspirerfid.commons.ale.utils.ECTimeUnit;
//import org.ow2.aspirerfid.commons.apdl.model.ECBoundarySpecExtension;
//import org.ow2.aspirerfid.commons.apdl.model.ECBoundarySpec;
//import org.ow2.aspirerfid.commons.apdl.model.ECTime;
//
//import org.ow2.aspirerfid.commons.apdl.model.ECBoundarySpecExtension.StartTriggerList;
//import org.ow2.aspirerfid.commons.apdl.model.ECBoundarySpecExtension.StopTriggerList;


import org.ow2.aspirerfid.commons.ale.model.ale.ECBoundarySpecExtension;
import org.ow2.aspirerfid.commons.ale.model.ale.ECBoundarySpec;
import org.ow2.aspirerfid.commons.ale.model.ale.ECTime;

import org.ow2.aspirerfid.commons.ale.model.ale.ECBoundarySpecExtension.StartTriggerList;
import org.ow2.aspirerfid.commons.ale.model.ale.ECBoundarySpecExtension.StopTriggerList;


//org.ow2.aspirerfid.commons.ale.wsdl.alelr.

/**
 * @author Vasso Koletti e-mail: vkol@ait.edu.gr
 * @author Nektarios Leontiadis e-mail:nele@ait.edu.gr
 */
public class ECBoundarySpecBuilder {

	
	private ECBoundarySpec spec;
	
	public ECBoundarySpecBuilder()
	{
		spec = new ECBoundarySpec();
		initiallize();
	}
	
	public ECBoundarySpecBuilder(ECBoundarySpecBuilder builder)
	{
		spec = builder.spec;
	}
	
	public ECBoundarySpecBuilder(ECBoundarySpec ecspec)
	{
		spec = ecspec;
	}
	
	private void initiallize()
	{
		ECBoundarySpecExtension extention = new ECBoundarySpecExtension();
		extention.setStartTriggerList(new StartTriggerList());
		extention.setStopTriggerList(new StopTriggerList());

		spec.setExtension(extention);
	}
	
	public void addStartTrigger(String triggerURI)
	{
		spec.getExtension().getStartTriggerList().getStartTrigger().add(triggerURI);
	}
	
	public List<String> getStartTriggerList()
	{
		return spec.getExtension().getStartTriggerList().getStartTrigger();
	}
	
	/*public void setStartTriggerList(List<String> startTriggerList)
	{
		spec.getExtension().
	}*/
	
	public void addStopTrigger(String triggerURI)
	{
		spec.getExtension().getStopTriggerList().getStopTrigger().add(triggerURI);
	}
	
	public List<String> getStopTriggerList()
	{
		return spec.getExtension().getStopTriggerList().getStopTrigger();
	}
	
	public void setRepeatPeriod(long time)
	{
		setRepeatPeriod(time, ECTimeUnit.MS);
	}
	
	public void setRepeatPeriod(long time, String timeUnit)
	{
		ECTime ectime = new ECTime();
		ectime.setValue(time);
		ectime.setUnit(timeUnit);
		spec.setRepeatPeriod(ectime);
	}
	
	public long getRepeatPeriodInterval()
	{
		return spec.getRepeatPeriod().getValue();
	}
	
	public String getRepeatPeriodTimeUnit()
	{
		return spec.getRepeatPeriod().getUnit();
	}
	
	public void setDuration(long time)
	{
		setDuration(time, ECTimeUnit.MS);
	}
	
	public void setDuration(long time, String timeUnit)
	{
		ECTime ectime = new ECTime();
		ectime.setValue(time);
		ectime.setUnit(timeUnit);
		spec.setDuration(ectime);
	}
	
	public long getDuration()
	{
		return spec.getDuration().getValue();
	}
	
	public String getDurationTimeUnit()
	{
		return spec.getDuration().getUnit();
	}
	
	public void setStableSetInterval(long time)
	{
		setStableSetInterval(time, ECTimeUnit.MS);
	}
	
	public void setStableSetInterval(long time, String timeUnit)
	{
		ECTime ectime = new ECTime();
		ectime.setValue(time);
		ectime.setUnit(timeUnit);
		spec.setStableSetInterval(ectime);
	}
	
	public long getStableSetInterval()
	{
		return spec.getStableSetInterval().getValue();
	}
	
	public String getStableSetIntervalTimeUnit()
	{
		return spec.getStableSetInterval().getUnit();
	}
	
	public ECBoundarySpec getECBoundarySpec()
	{
		return spec;
	}
	
	public void setWhenDataAvailable(boolean set)
	{
		spec.getExtension().setWhenDataAvailable(set);
	}
	
	public boolean getWhenDataAvailable()
	{
		return spec.getExtension().isWhenDataAvailable();
	}
	
}
