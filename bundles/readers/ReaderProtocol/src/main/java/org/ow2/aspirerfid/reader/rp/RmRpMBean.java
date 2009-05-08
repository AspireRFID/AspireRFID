/*
 * Copyright (c) 2008-2010, Aspire 
 * 
 * Aspire is free software; you can redistribute it and/or 
 * modify it under  the terms of the GNU Lesser General Public 
 * License version 2.1 as published by the Free Software Foundation (the 
 * "LGPL"). 
 * 
 * You should have received a copy of the GNU Lesser General Public 
 * License along with this library in the file COPYING-LGPL-2.1; if not, write to the Free Software 
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301 USA. 
 * 
 * This software is distributed on an "AS IS" basis, WITHOUT WARRANTY 
 * OF ANY KIND, either express or implied. See the GNU Lesser General Public 
 * License for the specific language governing rights and limitations.
 *  
 */


package org.ow2.aspirerfid.reader.rp;

/**
 * Provides a common interface for Reader Protocol implementations 
 * @author Nektarios Leontiadis (nele@ait.edu.gr)
 *
 */
public interface RmRpMBean {

	public boolean start();
	public boolean stop();
	public void loadConfig();
	public void resetConfig();
	public boolean isStarted();
	public void loadConfigurationFile(String serializedFile);
	public String saveConfigurationFileAs();
	
	public void setEPC(String epc);
	public String getEPC();
	public void setName(String name);
	public String getName();
	public void setManufacturer(String manufacturer);
	public String getManufacturer();
	public void setManufacturerDescription(String desc);
	public String getManufacturerDescription();
	public void setModel(String model);
	public String getModel();
	public void setHandle(int handle);
	public int getHandle();
	public void setRole(String role);
	public String getRole();
	
	public void setMaxSourceNumber(int number);
	public int getMaxSourceNumber();
	public void setMaxTagSelectorNumber(int number);
	public int getMaxTagSelectorNumber();
	public void setMaxTriggerNumber(int number);
	public int getMaxTriggerNumber();
	
	public void addReader(String name, String className, String propertiesFile);
	public void addReaderReadpoint(String readerName, String readpoint);
	public String [] getReaders();
	public String getReaderClassName(String readerName);
	public String getReaderPropertiesFile(String readerName);
	public String [] getReadPointNames(String readerName);
	
	public void setCurrentSource(String source);
	public String getCurrentSource();
	public void addSource(String name, boolean fixed, String readpoint);
	public String [] getSources();
	public boolean getIsSourceFixed(String sourceName);
	public String getSourceReadpoint(String sourceName);
	
	public void addIOEdgeTriggerPortManager(String port);
	public String [] getIOEdgeTriggerPortManager();
	public void addIOValueTriggerPortManager(String port);
	public String [] getIOValueTriggerPortManager();
	
	public void setTcpServerConnection(boolean isEnabled);
	public boolean getTcpServerConnection();
	public void setTcpPort(int port);
	public int getTcpPort();
	public void setHttpServerConnectionEnabled(boolean isEnabled);
	public boolean getHttpServerConnectionEnabled();
	public void setHttpPort(int port);
	public int getHttpPort();
	public void setNotificationListenTimeout(long timeout);
	public long getNotificationListenTimeout();
	public void setThreadPoolSize(int size);
	public int getThreadPoolSize();
	
	public void setIsSourceFized(boolean isFixed);
	public void setGlimpsedTimeout(long timeout);
	public long getGlimpsedTimeout();
	public void setObservedThreshold(long threshold);
	public long getObservedThreshold();
	public void setObservedTimeout(long timeout);
	public long getObservedTimeout();
	public void setLostTimeout(long timeout);
	public long getLostTimeout();
	public void setReadCyclesPerTrigger(int cycles);
	public int getReadCyclesPerTrigger();
	public void setMaxReadDutyCycles(int cycles);
	public int getMaxReadDutyCycles();
	public void setReadTimeout(long timeout);
	public long getReadTimeout();
	
	
}
