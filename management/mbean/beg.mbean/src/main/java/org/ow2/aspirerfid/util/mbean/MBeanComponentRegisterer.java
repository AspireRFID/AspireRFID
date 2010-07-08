/*
 * Copyright 2009 OW2 Chameleon
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *    http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.ow2.aspirerfid.util.mbean;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.management.MBeanServer;
import javax.management.MBeanServerFactory;


/**
 * registers MBeans to the MBeanServer
 * @author Didier Donsez
 */
public abstract class MBeanComponentRegisterer {

	private static final String LOGGER_NAME = "org.ow2.aspirerfid.beg";
	
	private List<MBeanComponent> mmBeanComponents=new ArrayList<MBeanComponent>();

	public MBeanComponentRegisterer() {
		// add MBeanComponent instances in the list
	}
	
	protected void addComponent(MBeanComponent mBeanComponent) {
		mmBeanComponents.add(mBeanComponent);
	}
	
	protected void removeComponent(MBeanComponent mBeanComponent) {
		mmBeanComponents.remove(mBeanComponent);
	}
	
	final public void start() {
		MBeanServer mbeanserver=getServer();
		for (Iterator iterator = mmBeanComponents.iterator(); iterator.hasNext();) {
			MBeanComponent mBeanComponent = (MBeanComponent) iterator.next();
			mBeanComponent.start();
			register(mbeanserver, mBeanComponent);
		}
	}

	final public void stop() {
		MBeanServer mbeanserver=getServer();
		// TODO iterate in reverse order
		for (Iterator iterator = mmBeanComponents.iterator(); iterator.hasNext();) {
			MBeanComponent mBeanComponent = (MBeanComponent) iterator.next();
			unregister(mbeanserver, mBeanComponent);
			mBeanComponent.stop();
		}
	}
		
	private boolean trace=true;
	private Logger logger=Logger.getLogger(LOGGER_NAME);
	
	
	protected Logger getLogger() {
		return logger;
	}
		
	private void print(String msg){
		if(trace) System.out.println(msg);
		if(logger!=null) logger.log(Level.INFO,msg);
	}

	private void print(String msg, Throwable t){
		if(trace) {
			System.err.println(msg + " : " + t.getMessage());
			t.printStackTrace();
		}
		if(logger!=null) {
			logger.log(Level.WARNING, msg + " : " + t.getMessage());
		}
	}

	private void register(MBeanServer server, MBeanComponent mbean) {
			try {
				server.registerMBean(mbean, mbean.getObjectName());
				print(mbean.getObjectName()+" registered");
			} catch (Exception e) {
				print("Exception while registering",e);
			}
	}

	private void unregister(MBeanServer server, MBeanComponent mbean) {
		try {
			server.unregisterMBean(mbean.getObjectName());
			print(mbean.getObjectName()+" unregistered");
		} catch (Exception e) {
			print("Exception while unregistering",e);
		}
	}
	
	public void listServers() {
		ArrayList mbservers = MBeanServerFactory.findMBeanServer(null);
		if (mbservers == null || mbservers.size()==0) {
			print("No MBeanServer found");
		} else {
			for (Iterator iterator = mbservers.iterator(); iterator.hasNext();) {
				MBeanServer mBeanServer = (MBeanServer) iterator.next();
				print("MBeanServer:"+mBeanServer.toString());
			}
		}
		MBeanServer platformMBeanServer = java.lang.management.ManagementFactory.getPlatformMBeanServer();
		print("Platform MBeanServer:"+platformMBeanServer.toString());	
	}
	
	private MBeanServer getServer() {
		MBeanServer mbeanserver = null;

		ArrayList mbservers = MBeanServerFactory.findMBeanServer(null);

		if (mbservers.size() > 0) {
			mbeanserver = (MBeanServer) mbservers.get(0);
		}

		if (mbeanserver != null) {
			print("Found MBeanServer:"+mbeanserver.toString());
		} else {
			mbeanserver = MBeanServerFactory.createMBeanServer();
			print("Create MBeanServer:"+mbeanserver.toString());
		}

		return mbeanserver;
	}

	
	
}
