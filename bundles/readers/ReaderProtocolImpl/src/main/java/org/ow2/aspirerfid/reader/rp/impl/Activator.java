/*
 * Copyright (c) 2008-2010, Aspire 
 * 
 * This library is free software; you can redistribute it and/or modify it 
 * under the terms of the GNU Lesser General Public License as published by 
 * the Free Software Foundation (the "LGPL"); either version 2.1 of the 
 * License, or (at your option) any later version. If you do not alter this 
 * notice, a recipient may use your version of this file under either the 
 * LGPL version 2.1, or (at his option) any later version.
 * 
 * You should have received a copy of the GNU Lesser General Public License 
 * along with this library; if not, write to the Free Software Foundation, 
 * Inc., 51 Franklin Street, Fifth Floor, Boston, MA 02110-1301 USA.
 * 
 * This software is distributed on an "AS IS" basis, WITHOUT WARRANTY OF ANY 
 * KIND, either express or implied. See the GNU Lesser General Public 
 * License for the specific language governing rights and limitations.
 */

package org.ow2.aspirerfid.reader.rp.impl;

import java.util.Dictionary;
import java.util.Hashtable;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.osgi.framework.Bundle;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;
import org.osgi.util.tracker.ServiceTracker;

import org.ow2.aspirerfid.common.tdt.TDT;

/**
 * @author Nektarios Leontiadis
 *
 */
public class Activator implements BundleActivator{

	
	public static Bundle bundle;
	private static ServiceTracker sTracker = null;
	private static Logger log;
	private ReaderProtocolMBean rp;
	private ServiceRegistration rpServiceRegistration;
	
	static  {
		BasicConfigurator.resetConfiguration();
		BasicConfigurator.configure();
		log = Logger.getLogger(Activator.class);
	}
	
	public void start(BundleContext context) throws Exception {
		bundle = context.getBundle();
		Dictionary props = new Hashtable();
		if(sTracker == null)
		{
			sTracker = new ServiceTracker(context,TDT.class.getName(),null);
		}
		sTracker.open();
		rp = new ReaderProtocol();
		props.put("jmxagent.objectname", "rfid:type=service,SymbolicName=RPreader");
		rpServiceRegistration = context.registerService(ReaderProtocolMBean.class.getName(), rp, props);
	}

	public void stop(BundleContext context) throws Exception {
		log.info("Stoping RP...");
		try{
			rp.stop();
			rp = null;
		}catch(Exception e)
		{
			log.warn(e.getMessage());
		}
		sTracker.close();
		sTracker = null;
		rpServiceRegistration.unregister();
		log.info("RP stopped");
	}

	public static TDT getTDTService()
	{
		TDT tdt;
		log.info("Requesting a TDT service");
		tdt = (TDT)sTracker.getService();
		if(tdt != null)
		{
			log.info("Got a TDT service");
		}
			return tdt;
	}
	
}
