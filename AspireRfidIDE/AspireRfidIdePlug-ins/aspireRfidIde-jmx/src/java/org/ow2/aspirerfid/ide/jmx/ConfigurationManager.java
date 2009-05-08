/**
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

package org.ow2.aspirerfid.ide.jmx;

import java.io.IOException;

import javax.management.MBeanServerConnection;
import javax.management.MBeanServerInvocationHandler;
import javax.management.ObjectName;
import javax.management.remote.JMXConnector;
import javax.management.remote.JMXConnectorFactory;
import javax.management.remote.JMXServiceURL;
import org.ow2.aspirerfid.ide.jmx.preferences.PreferenceConstants;
import org.ow2.aspirerfid.reader.rp.RmRpMBean;

/**
 * @author Nektarios Leontiadis (nele@ait.edu.gr)
 * 
 */
public class ConfigurationManager implements RmRpMBean {

    private static MBeanServerConnection connection;
    private static String mBeanName = "rfid:type=service,SymbolicName=RPreader";
    private static ObjectName bean;
    private static boolean connected;
    private static RmRpMBean handler;
    private static ConfigurationManager instance;
    static {
	connected = false;
	instance = null;
    }

    private static final boolean connect() {
	if(connected)
	{
	    try {
		connection.getDefaultDomain();
	    } catch (IOException e) {
		//The connection is broken
		connected = false;
	    }
	}
	if (!connected) {
	    try {
		String url = Activator.getDefault().getPreferenceStore().getString(PreferenceConstants.JMX_CONNECTION_URL);
		JMXServiceURL u = new JMXServiceURL(url);
		JMXConnector c = JMXConnectorFactory.connect(u);

		connection = c.getMBeanServerConnection();
		bean = new ObjectName(mBeanName);
		handler = MBeanServerInvocationHandler.newProxyInstance(connection, bean, RmRpMBean.class, false);
		connected = true;
	    } catch (Exception e) {
		System.out.println(e.getLocalizedMessage());
	    }
	}
	return connected;
    }

    public static ConfigurationManager getInstance() throws Exception {
	if (instance == null)
	    instance = new ConfigurationManager();

	if (!connect())
	    throw new Exception("Not connected");

	return instance;
    }
    

    public void loadConfigurationFile(String serializedFile)
    {
	connect();
	handler.loadConfigurationFile(serializedFile);
    }
    
    public String saveConfigurationFileAs()
    {
	connect();
	return handler.saveConfigurationFileAs();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.ow2.aspirerfid.reader.rp.RmRpMBean#addReader(java.lang.String,
     * java.lang.String, java.lang.String)
     */
    @Override
    public void addReader(String name, String className, String propertiesFile) {
	connect();
	handler.addReader(name, className, propertiesFile);

    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.ow2.aspirerfid.reader.rp.RmRpMBean#addReaderReadpoint(java.lang.String
     * , java.lang.String)
     */
    @Override
    public void addReaderReadpoint(String readerName, String readpoint) {
	connect();
	handler.addReaderReadpoint(readerName, readpoint);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.ow2.aspirerfid.reader.rp.RmRpMBean#addSource(java.lang.String,
     * boolean, java.lang.String)
     */
    @Override
    public void addSource(String name, boolean fixed, String readpoint) {
	connect();
	handler.addSource(name, fixed, readpoint);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.ow2.aspirerfid.reader.rp.RmRpMBean#getCurrentSource()
     */
    @Override
    public String getCurrentSource() {
	connect();
	return handler.getCurrentSource();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.ow2.aspirerfid.reader.rp.RmRpMBean#getEPC()
     */
    @Override
    public String getEPC() {
	connect();
	return handler.getEPC();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.ow2.aspirerfid.reader.rp.RmRpMBean#getGlimpsedTimeout()
     */
    @Override
    public long getGlimpsedTimeout() {
	connect();
	return handler.getGlimpsedTimeout();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.ow2.aspirerfid.reader.rp.RmRpMBean#getHandle()
     */
    @Override
    public int getHandle() {
	connect();
	return handler.getHandle();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.ow2.aspirerfid.reader.rp.RmRpMBean#getHttpPort()
     */
    @Override
    public int getHttpPort() {
	connect();
	return handler.getHttpPort();
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.ow2.aspirerfid.reader.rp.RmRpMBean#getHttpServerConnectionEnabled()
     */
    @Override
    public boolean getHttpServerConnectionEnabled() {
	connect();
	return handler.getHttpServerConnectionEnabled();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.ow2.aspirerfid.reader.rp.RmRpMBean#getIOEdgeTriggerPortManager()
     */
    @Override
    public String[] getIOEdgeTriggerPortManager() {
	connect();
	return handler.getIOEdgeTriggerPortManager();
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.ow2.aspirerfid.reader.rp.RmRpMBean#getIOValueTriggerPortManager()
     */
    @Override
    public String[] getIOValueTriggerPortManager() {
	connect();
	return handler.getIOValueTriggerPortManager();
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.ow2.aspirerfid.reader.rp.RmRpMBean#getIsSourceFixed(java.lang.String)
     */
    @Override
    public boolean getIsSourceFixed(String source) {
	connect();
	return handler.getIsSourceFixed(source);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.ow2.aspirerfid.reader.rp.RmRpMBean#getLostTimeout()
     */
    @Override
    public long getLostTimeout() {
	connect();
	return handler.getLostTimeout();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.ow2.aspirerfid.reader.rp.RmRpMBean#getManufacturer()
     */
    @Override
    public String getManufacturer() {
	connect();
	return handler.getManufacturer();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.ow2.aspirerfid.reader.rp.RmRpMBean#getManufacturerDescription()
     */
    @Override
    public String getManufacturerDescription() {
	connect();
	return handler.getManufacturerDescription();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.ow2.aspirerfid.reader.rp.RmRpMBean#getMaxReadDutyCycles()
     */
    @Override
    public int getMaxReadDutyCycles() {
	connect();
	return handler.getMaxReadDutyCycles();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.ow2.aspirerfid.reader.rp.RmRpMBean#getMaxSourceNumber()
     */
    @Override
    public int getMaxSourceNumber() {
	connect();
	return handler.getMaxSourceNumber();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.ow2.aspirerfid.reader.rp.RmRpMBean#getMaxTagSelectorNumber()
     */
    @Override
    public int getMaxTagSelectorNumber() {
	connect();
	return handler.getMaxTagSelectorNumber();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.ow2.aspirerfid.reader.rp.RmRpMBean#getMaxTriggerNumber()
     */
    @Override
    public int getMaxTriggerNumber() {
	connect();
	return handler.getMaxTriggerNumber();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.ow2.aspirerfid.reader.rp.RmRpMBean#getModel()
     */
    @Override
    public String getModel() {
	connect();
	return handler.getModel();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.ow2.aspirerfid.reader.rp.RmRpMBean#getName()
     */
    @Override
    public String getName() {
	connect();
	return handler.getName();
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.ow2.aspirerfid.reader.rp.RmRpMBean#getNotificationListenTimeout()
     */
    @Override
    public long getNotificationListenTimeout() {
	connect();
	return handler.getNotificationListenTimeout();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.ow2.aspirerfid.reader.rp.RmRpMBean#getObservedThreshold()
     */
    @Override
    public long getObservedThreshold() {
	connect();
	return handler.getObservedThreshold();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.ow2.aspirerfid.reader.rp.RmRpMBean#getObservedTimeout()
     */
    @Override
    public long getObservedTimeout() {
	connect();
	return handler.getObservedTimeout();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.ow2.aspirerfid.reader.rp.RmRpMBean#getReadCyclesPerTrigger()
     */
    @Override
    public int getReadCyclesPerTrigger() {
	connect();
	return handler.getReadCyclesPerTrigger();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.ow2.aspirerfid.reader.rp.RmRpMBean#getReadTimeout()
     */
    @Override
    public long getReadTimeout() {
	connect();
	return handler.getReadTimeout();
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.ow2.aspirerfid.reader.rp.RmRpMBean#getReaderPropertiesFile(java.lang
     * .String)
     */
    @Override
    public String getReaderPropertiesFile(String readerName) {
	connect();
	return handler.getReaderPropertiesFile(readerName);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.ow2.aspirerfid.reader.rp.RmRpMBean#getReaders()
     */
    @Override
    public String[] getReaders() {
	connect();
	return handler.getReaders();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.ow2.aspirerfid.reader.rp.RmRpMBean#getRole()
     */
    @Override
    public String getRole() {
	connect();
	return handler.getRole();
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.ow2.aspirerfid.reader.rp.RmRpMBean#getSourceReadpoint(java.lang.String
     * )
     */
    @Override
    public String getSourceReadpoint(String source) {
	connect();
	return handler.getSourceReadpoint(source);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.ow2.aspirerfid.reader.rp.RmRpMBean#getSources()
     */
    @Override
    public String[] getSources() {
	connect();
	return handler.getSources();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.ow2.aspirerfid.reader.rp.RmRpMBean#getTcpPort()
     */
    @Override
    public int getTcpPort() {
	connect();
	return handler.getTcpPort();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.ow2.aspirerfid.reader.rp.RmRpMBean#getTcpServerConnection()
     */
    @Override
    public boolean getTcpServerConnection() {
	connect();
	return handler.getTcpServerConnection();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.ow2.aspirerfid.reader.rp.RmRpMBean#getThreadPoolSize()
     */
    @Override
    public int getThreadPoolSize() {
	connect();
	return handler.getThreadPoolSize();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.ow2.aspirerfid.reader.rp.RmRpMBean#start()
     */
    @Override
    public boolean start() {
	connect();
	return handler.start();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.ow2.aspirerfid.reader.rp.RmRpMBean#stop()
     */
    @Override
    public boolean stop() {
	connect();
	return handler.stop();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.ow2.aspirerfid.reader.rp.RmRpMBean#isStarted()
     */
    @Override
    public boolean isStarted() {
	connect();
	return handler.isStarted();
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.ow2.aspirerfid.reader.rp.RmRpMBean#addIOValueTriggerPortManager(java
     * .lang.String)
     */
    @Override
    public void addIOValueTriggerPortManager(String port) {
	connect();
	handler.addIOValueTriggerPortManager(port);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.ow2.aspirerfid.reader.rp.RmRpMBean#addIOEdgeTriggerPortManager(java
     * .lang.String)
     */
    @Override
    public void addIOEdgeTriggerPortManager(String port) {
	connect();
	handler.addIOEdgeTriggerPortManager(port);
    }
    
    /*
     * (non-Javadoc)
     * 
     * @see org.ow2.aspirerfid.reader.rp.RmRpMBean#loadConfig()
     */
    @Override
    public void loadConfig() {
	connect();
	handler.loadConfig();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.ow2.aspirerfid.reader.rp.RmRpMBean#resetConfig()
     */
    @Override
    public void resetConfig() {
	connect();
	handler.resetConfig();
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.ow2.aspirerfid.reader.rp.RmRpMBean#setCurrentSource(java.lang.String)
     */
    @Override
    public void setCurrentSource(String source) {
	connect();
	handler.setCurrentSource(source);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.ow2.aspirerfid.reader.rp.RmRpMBean#setEPC(java.lang.String)
     */
    @Override
    public void setEPC(String epc) {
	connect();
	handler.setEPC(epc);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.ow2.aspirerfid.reader.rp.RmRpMBean#setGlimpsedTimeout(long)
     */
    @Override
    public void setGlimpsedTimeout(long timeout) {
	connect();
	handler.setGlimpsedTimeout(timeout);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.ow2.aspirerfid.reader.rp.RmRpMBean#setHandle(int)
     */
    @Override
    public void setHandle(int handle) {
	connect();
	handler.setHandle(handle);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.ow2.aspirerfid.reader.rp.RmRpMBean#setHttpPort(int)
     */
    @Override
    public void setHttpPort(int port) {
	connect();
	handler.setHttpPort(port);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.ow2.aspirerfid.reader.rp.RmRpMBean#setHttpServerConnectionEnabled
     * (boolean)
     */
    @Override
    public void setHttpServerConnectionEnabled(boolean isEnabled) {
	connect();
	handler.setHttpServerConnectionEnabled(isEnabled);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.ow2.aspirerfid.reader.rp.RmRpMBean#setIsSourceFized(boolean)
     */
    @Override
    public void setIsSourceFized(boolean isFixed) {
	connect();
	handler.setIsSourceFized(isFixed);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.ow2.aspirerfid.reader.rp.RmRpMBean#setLostTimeout(long)
     */
    @Override
    public void setLostTimeout(long timeout) {
	connect();
	handler.setLostTimeout(timeout);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.ow2.aspirerfid.reader.rp.RmRpMBean#setManufacturer(java.lang.String)
     */
    @Override
    public void setManufacturer(String manufacturer) {
	connect();
	handler.setManufacturer(manufacturer);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.ow2.aspirerfid.reader.rp.RmRpMBean#setManufacturerDescription(java
     * .lang.String)
     */
    @Override
    public void setManufacturerDescription(String desc) {
	connect();
	handler.setManufacturerDescription(desc);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.ow2.aspirerfid.reader.rp.RmRpMBean#setMaxReadDutyCycles(int)
     */
    @Override
    public void setMaxReadDutyCycles(int cycles) {
	connect();
	handler.setMaxReadDutyCycles(cycles);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.ow2.aspirerfid.reader.rp.RmRpMBean#setMaxSourceNumber(int)
     */
    @Override
    public void setMaxSourceNumber(int number) {
	connect();
	handler.setMaxSourceNumber(number);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.ow2.aspirerfid.reader.rp.RmRpMBean#setMaxTagSelectorNumber(int)
     */
    @Override
    public void setMaxTagSelectorNumber(int number) {
	connect();
	handler.setMaxTagSelectorNumber(number);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.ow2.aspirerfid.reader.rp.RmRpMBean#setMaxTriggerNumber(int)
     */
    @Override
    public void setMaxTriggerNumber(int number) {
	connect();
	handler.setMaxTriggerNumber(number);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.ow2.aspirerfid.reader.rp.RmRpMBean#setModel(java.lang.String)
     */
    @Override
    public void setModel(String model) {
	connect();
	handler.setModel(model);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.ow2.aspirerfid.reader.rp.RmRpMBean#setName(java.lang.String)
     */
    @Override
    public void setName(String name) {
	connect();
	handler.setName(name);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.ow2.aspirerfid.reader.rp.RmRpMBean#setNotificationListenTimeout(long)
     */
    @Override
    public void setNotificationListenTimeout(long timeout) {
	connect();
	handler.setNotificationListenTimeout(timeout);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.ow2.aspirerfid.reader.rp.RmRpMBean#setObservedThreshold(long)
     */
    @Override
    public void setObservedThreshold(long threshold) {
	connect();
	handler.setObservedThreshold(threshold);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.ow2.aspirerfid.reader.rp.RmRpMBean#setObservedTimeout(long)
     */
    @Override
    public void setObservedTimeout(long timeout) {
	connect();
	handler.setObservedTimeout(timeout);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.ow2.aspirerfid.reader.rp.RmRpMBean#setReadCyclesPerTrigger(int)
     */
    @Override
    public void setReadCyclesPerTrigger(int cycles) {
	connect();
	handler.setReadCyclesPerTrigger(cycles);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.ow2.aspirerfid.reader.rp.RmRpMBean#setReadTimeout(long)
     */
    @Override
    public void setReadTimeout(long timeout) {
	connect();
	handler.setReadTimeout(timeout);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.ow2.aspirerfid.reader.rp.RmRpMBean#setRole(java.lang.String)
     */
    @Override
    public void setRole(String role) {
	connect();
	handler.setRole(role);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.ow2.aspirerfid.reader.rp.RmRpMBean#setTcpPort(int)
     */
    @Override
    public void setTcpPort(int port) {
	connect();
	handler.setTcpPort(port);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.ow2.aspirerfid.reader.rp.RmRpMBean#setTcpServerConnection(boolean)
     */
    @Override
    public void setTcpServerConnection(boolean isEnabled) {
	connect();
	handler.setTcpServerConnection(isEnabled);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.ow2.aspirerfid.reader.rp.RmRpMBean#setThreadPoolSize(int)
     */
    @Override
    public void setThreadPoolSize(int size) {
	connect();
	handler.setThreadPoolSize(size);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.ow2.aspirerfid.reader.rp.RmRpMBean#getReadPointNames(java.lang.String
     * )
     */
    @Override
    public String[] getReadPointNames(String readerName) {
	connect();
	return handler.getReadPointNames(readerName);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.ow2.aspirerfid.reader.rp.RmRpMBean#getReaderClassName(java.lang.String
     * )
     */
    @Override
    public String getReaderClassName(String readerName) {
	connect();
	return handler.getReaderClassName(readerName);
    }
}
