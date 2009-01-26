/*
 * Copyright 2005-2008, Aspire
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

package org.ow2.aspirerfid.admin;

import java.util.Dictionary;

import javax.management.AttributeChangeNotification;
import javax.management.MBeanNotificationInfo;
import javax.management.NotificationBroadcasterSupport;
import javax.management.openmbean.TabularData;

import org.osgi.framework.BundleException;

/**
 * The class is a delegate for MBean implementations
 * 
 * @author Didier Donsez (didier.donsez@ieee.org)
 * @version 2007
 */
public class Admin extends NotificationBroadcasterSupport implements AdminMBean {

	private AdminMBean adminMBean;

	/**
	 * TODO Javadoc
	 * 
	 * @param adminMBean
	 */
	public Admin(AdminMBean adminMBean) {
		this.adminMBean = adminMBean;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.ow2.aspirerfid.admin.AdminMBean#getBundleIds()
	 */
	public long[] getBundleIds() {
		return adminMBean.getBundleIds();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.ow2.aspirerfid.admin.AdminMBean#getBundleLocations()
	 */
	public String[] getBundleLocations() {
		return adminMBean.getBundleLocations();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.ow2.aspirerfid.admin.AdminMBean#getServiceIds()
	 */
	public long[] getServiceIds() {
		return adminMBean.getServiceIds();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.ow2.aspirerfid.admin.AdminMBean#getFrameworkProperty(java.lang.String)
	 */
	public String getFrameworkProperty(String key) {
		return adminMBean.getFrameworkProperty(key);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.ow2.aspirerfid.admin.AdminMBean#getFrameworkPropertyKeys()
	 */
	public String[] getFrameworkPropertyKeys() {
		return adminMBean.getFrameworkPropertyKeys();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.ow2.aspirerfid.admin.AdminMBean#getFrameworkProperties()
	 */
	public TabularData getFrameworkProperties() {
		return adminMBean.getFrameworkProperties();
	}

	/**
	 * @see org.osgi.framework.Bundle#getState()
	 * @see org.ow2.aspirerfid.admin.AdminMBean#getBundleState(long)
	 */
	public int getBundleState(long bundleId) {
		return adminMBean.getBundleState(bundleId);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.ow2.aspirerfid.admin.AdminMBean#getBundleState(java.lang.String)
	 */
	public int getBundleState(String url) {
		return adminMBean.getBundleState(url);
	}

	/**
	 * @see org.osgi.framework.BundleContext#installBundle(String url)
	 * @see org.ow2.aspirerfid.admin.AdminMBean#installBundle(java.lang.String)
	 */
	public void installBundle(String url) throws BundleException {
		adminMBean.installBundle(url);
	}

	/**
	 * @see org.osgi.framework.Bundle#start()
	 * @see org.ow2.aspirerfid.admin.AdminMBean#startBundle(long)
	 */
	public void startBundle(long bundleId) throws BundleException {
		adminMBean.startBundle(bundleId);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.ow2.aspirerfid.admin.AdminMBean#startBundle(java.lang.String)
	 */
	public void startBundle(String location) throws BundleException {
		adminMBean.startBundle(location);
	}

	/**
	 * @see org.osgi.framework.Bundle#stop(long bundleId)
	 * @see org.ow2.aspirerfid.admin.AdminMBean#stopBundle(long)
	 */
	public void stopBundle(long bundleId) throws BundleException {
		adminMBean.stopBundle(bundleId);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.ow2.aspirerfid.admin.AdminMBean#stopBundle(java.lang.String)
	 */
	public void stopBundle(String location) throws BundleException {
		adminMBean.stopBundle(location);
	}

	/**
	 * @see org.osgi.framework.Bundle#update(long)
	 * @see org.ow2.aspirerfid.admin.AdminMBean#updateBundle(long)
	 */
	public void updateBundle(long bundleId) throws BundleException {
		adminMBean.updateBundle(bundleId);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.ow2.aspirerfid.admin.AdminMBean#updateBundle(java.lang.String)
	 */
	public void updateBundle(String location) throws BundleException {
		adminMBean.updateBundle(location);
	}

	/**
	 * @see org.osgi.framework.Bundle#update(long bundleId, String url)
	 * @see org.ow2.aspirerfid.admin.AdminMBean#updateBundle(long,
	 *      java.lang.String)
	 */
	public void updateBundle(long bundleId, String url) throws BundleException {
		adminMBean.updateBundle(bundleId, url);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.ow2.aspirerfid.admin.AdminMBean#updateBundle(java.lang.String,
	 *      java.lang.String)
	 */
	public void updateBundle(String location, String url)
			throws BundleException {
		adminMBean.updateBundle(location, url);
	}

	/**
	 * @see org.osgi.framework.Bundle#uninstall(long bundleId)
	 * @see org.ow2.aspirerfid.admin.AdminMBean#uninstallBundle(long)
	 */
	public void uninstallBundle(long bundleId) throws BundleException {
		adminMBean.uninstallBundle(bundleId);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.ow2.aspirerfid.admin.AdminMBean#uninstallBundle(java.lang.String)
	 */
	public void uninstallBundle(String location) throws BundleException {
		adminMBean.uninstallBundle(location);
	}

	/**
	 * @see org.osgi.framework.Bundle#getHeaders(long bundleId)
	 * @see org.ow2.aspirerfid.admin.AdminMBean#getBundleHeaders(long)
	 */
	public Dictionary getBundleHeaders(long bundleId) {
		return adminMBean.getBundleHeaders(bundleId);
	}

	/**
	 * @see org.osgi.framework.Bundle#getLocation(long bundleId)
	 * @see org.ow2.aspirerfid.admin.AdminMBean#getBundleLocation(long)
	 */
	public String getBundleLocation(long bundleId) {
		return adminMBean.getBundleLocation(bundleId);
	}

	/**
	 * @see org.osgi.framework.Bundle#getRegisteredServices(long bundleId)
	 * @see org.ow2.aspirerfid.admin.AdminMBean#getBundleRegisteredServices(long)
	 */
	public long[] getBundleRegisteredServices(long bundleId) {
		return adminMBean.getBundleRegisteredServices(bundleId);
	}

	/**
	 * @see org.osgi.framework.Bundle#getServicesInUse(long bundleId)
	 * @see org.ow2.aspirerfid.admin.AdminMBean#getBundleServicesInUse(long)
	 */
	public long[] getBundleServicesInUse(long bundleId) {
		return adminMBean.getBundleServicesInUse(bundleId);
	}

	/**
	 * @see org.osgi.framework.ServiceReference#getProperty(java.lang.String)
	 * @see org.ow2.aspirerfid.admin.AdminMBean#getServiceProperties(long)
	 */
	public TabularData getServiceProperties(long serviceId) {
		return adminMBean.getServiceProperties(serviceId);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.ow2.aspirerfid.admin.AdminMBean#getBundlesInfos()
	 */
	public TabularData getBundlesInfos() {
		return adminMBean.getBundlesInfos();
	}

	/**
	 * @see org.osgi.framework.ServiceReference#getProperty(java.lang.String)
	 * @see org.ow2.aspirerfid.admin.AdminMBean#getServiceProperty(long,
	 *      java.lang.String)
	 */
	public Object getServiceProperty(long serviceId, String key) {
		return adminMBean.getServiceProperty(serviceId, key);
	}

	/**
	 * @see org.osgi.framework.ServiceReference#getPropertyKeys()
	 * @see org.ow2.aspirerfid.admin.AdminMBean#getServicePropertyKeys(long)
	 */
	public String[] getServicePropertyKeys(long serviceId) {
		return adminMBean.getServicePropertyKeys(serviceId);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.management.NotificationBroadcasterSupport#getNotificationInfo()
	 */
	public MBeanNotificationInfo[] getNotificationInfo() {
		String[] types = new String[] { AttributeChangeNotification.ATTRIBUTE_CHANGE };
		String name = AttributeChangeNotification.class.getName();
		String description = "An attribute of this MBean has changed";
		MBeanNotificationInfo info = new MBeanNotificationInfo(types, name,
				description);
		return new MBeanNotificationInfo[] { info };
	}

}
