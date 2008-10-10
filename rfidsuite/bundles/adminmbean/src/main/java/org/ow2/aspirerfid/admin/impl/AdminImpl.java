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

package org.ow2.aspirerfid.admin.impl;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.management.AttributeChangeNotification;
import javax.management.MBeanNotificationInfo;
import javax.management.Notification;
import javax.management.NotificationBroadcasterSupport;
import javax.management.openmbean.ArrayType;
import javax.management.openmbean.CompositeData;
import javax.management.openmbean.CompositeDataSupport;
import javax.management.openmbean.CompositeType;
import javax.management.openmbean.OpenDataException;
import javax.management.openmbean.OpenType;
import javax.management.openmbean.SimpleType;
import javax.management.openmbean.TabularData;
import javax.management.openmbean.TabularDataSupport;
import javax.management.openmbean.TabularType;

import org.osgi.framework.Bundle;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.BundleEvent;
import org.osgi.framework.BundleException;
import org.osgi.framework.BundleListener;
import org.osgi.framework.Constants;
import org.osgi.framework.FrameworkEvent;
import org.osgi.framework.FrameworkListener;
import org.osgi.framework.ServiceEvent;
import org.osgi.framework.ServiceListener;
import org.osgi.framework.ServiceReference;
import org.ow2.aspirerfid.admin.AdminMBean;
import org.ow2.aspirerfid.admin.mbean.util.Util;

/**
 * The class provides a service for configuring the instance with a JMX bundle
 * 
 * @author Didier Donsez (didier.donsez@ieee.org)
 * @version 2007
 */
public class AdminImpl implements AdminMBean, BundleActivator {

	private BundleContext bundleContext;

	/**
	 * TODO Javadoc
	 * 
	 * @param bundleContext
	 */
	public AdminImpl(BundleContext bundleContext) {
		this.bundleContext = bundleContext;
	}

	/**
	 * @return TODO Javadoc
	 */
	public MBeanNotificationInfo[] getNotificationInfo() {
		String[] types = new String[] { AttributeChangeNotification.ATTRIBUTE_CHANGE };
		String name = AttributeChangeNotification.class.getName();
		String description = "An attribute of this MBean has changed";
		MBeanNotificationInfo info = new MBeanNotificationInfo(types, name,
				description);
		return new MBeanNotificationInfo[] { info };
	}

	private Bundle findBundleById(long bundleId) {
		Bundle[] bundles = bundleContext.getBundles();
		for (int i = 0; i < bundles.length; i++)
			if (bundleId == bundles[i].getBundleId())
				return bundles[i];
		return null;
	}

	private Bundle findBundleByLocation(String location) {
		Bundle[] bundles = bundleContext.getBundles();
		for (int i = 0; i < bundles.length; i++)
			if (location.equals(bundles[i].getLocation()))
				return bundles[i];
		return null;
	}

	private ServiceReference findServiceReferenceById(long serviceId) {
		Bundle[] bundles = bundleContext.getBundles();
		for (int b = 0; b < bundles.length; b++) {
			ServiceReference[] serviceReferences = bundles[b]
					.getRegisteredServices();
			for (int s = 0; s < serviceReferences.length; s++)
				if (serviceId == ((Long) serviceReferences[s]
						.getProperty(Constants.SERVICE_ID)).longValue())
					return serviceReferences[s];
		}
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.ow2.aspirerfid.admin.AdminMBean#getBundleIds()
	 */
	public long[] getBundleIds() {
		Bundle[] bundles = bundleContext.getBundles();
		long[] bundleIds = new long[bundles.length];
		for (int i = 0; i < bundles.length; i++) {
			bundleIds[i] = bundles[i].getBundleId();
		}
		return bundleIds;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.ow2.aspirerfid.admin.AdminMBean#getBundleLocations()
	 */
	public String[] getBundleLocations() {
		Bundle[] bundles = bundleContext.getBundles();
		String[] bundleLocations = new String[bundles.length];
		for (int i = 0; i < bundles.length; i++) {
			bundleLocations[i] = bundles[i].getLocation();
		}
		return bundleLocations;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.ow2.aspirerfid.admin.AdminMBean#getServiceIds()
	 */
	public long[] getServiceIds() {
		Bundle[] bundles = bundleContext.getBundles();
		List list = new LinkedList();
		for (int b = 0; b < bundles.length; b++) {
			ServiceReference[] serviceReferences = bundles[b]
					.getRegisteredServices();
			if (serviceReferences == null)
				continue;
			for (int s = 0; s < serviceReferences.length; s++)
				list
						.add(serviceReferences[s]
								.getProperty(Constants.SERVICE_ID));
		}
		long[] serviceIds = new long[list.size()];
		Iterator iterator = list.iterator();
		int i = 0;
		while (iterator.hasNext())
			serviceIds[i++] = ((Long) iterator.next()).longValue();
		return serviceIds;
	}

	/**
	 * @see org.osgi.framework.Bundle#getState()
	 * @see org.ow2.aspirerfid.admin.AdminMBean#getBundleState(long)
	 */
	public int getBundleState(long bundleId) {
		Bundle bundle = findBundleById(bundleId);
		if (bundle == null)
			return -1;
		else
			return bundle.getState();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.ow2.aspirerfid.admin.AdminMBean#getBundleState(java.lang.String)
	 */
	public int getBundleState(String location) {
		Bundle bundle = findBundleByLocation(location);
		if (bundle == null)
			return -1;
		else
			return bundle.getState();
	}

	/**
	 * @see org.osgi.framework.BundleContext#installBundle(String location)
	 * @see org.ow2.aspirerfid.admin.AdminMBean#installBundle(java.lang.String)
	 */
	public void installBundle(String location) throws BundleException {
		bundleContext.installBundle(location);
	}

	/**
	 * @see org.osgi.framework.Bundle#start()
	 * @see org.ow2.aspirerfid.admin.AdminMBean#startBundle(long)
	 */
	public void startBundle(long bundleId) throws BundleException {
		Bundle bundle = findBundleById(bundleId);
		if (bundle == null)
			throw new BundleException("Not a existing bundle");
		else
			bundle.start();
	}

	/**
	 * @throws BundleException
	 * @see org.osgi.framework.Bundle#start()
	 * @see org.ow2.aspirerfid.admin.AdminMBean#startBundle(java.lang.String)
	 */
	public void startBundle(String location) throws BundleException {
		Bundle bundle = findBundleByLocation(location);
		if (bundle == null) {
			bundle = bundleContext.installBundle(location);
		}
		bundle.start();
	}

	/**
	 * @throws BundleException
	 * @see org.osgi.framework.Bundle#stop(long bundleId)
	 * @see org.ow2.aspirerfid.admin.AdminMBean#stopBundle(long)
	 */
	public void stopBundle(long bundleId) throws BundleException {
		Bundle bundle = findBundleById(bundleId);
		if (bundle == null)
			throw new BundleException("Not a existing bundle");
		else
			bundle.stop();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.ow2.aspirerfid.admin.AdminMBean#stopBundle(java.lang.String)
	 */
	public void stopBundle(String location) throws BundleException {
		Bundle bundle = findBundleByLocation(location);
		if (bundle == null) {
			throw new BundleException("Not a existing bundle");
		}
		bundle.stop();
	}

	/**
	 * @throws BundleException
	 * @see org.osgi.framework.Bundle#update(long bundleId)
	 * @see org.ow2.aspirerfid.admin.AdminMBean#updateBundle(long)
	 */
	public void updateBundle(long bundleId) throws BundleException {
		Bundle bundle = findBundleById(bundleId);
		if (bundle == null)
			throw new BundleException("Not a existing bundle");
		else
			bundle.update();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.ow2.aspirerfid.admin.AdminMBean#updateBundle(java.lang.String)
	 */
	public void updateBundle(String location) throws BundleException {
		Bundle bundle = findBundleByLocation(location);
		if (bundle == null) {
			throw new BundleException("Not a existing bundle");
		}
		bundle.update();
	}

	/**
	 * TODO Javadoc
	 * 
	 * @param bundleId
	 * @see org.osgi.framework.Bundle#update(long bundleId, String location)
	 * @see org.ow2.aspirerfid.admin.AdminMBean#updateBundle(long,
	 *      java.lang.String)
	 */
	public void updateBundle(long bundleId, String locationstr)
			throws BundleException {
		Bundle bundle = findBundleById(bundleId);
		updateBundle(bundle, locationstr);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.ow2.aspirerfid.admin.AdminMBean#updateBundle(java.lang.String,
	 *      java.lang.String)
	 */
	public void updateBundle(String location, String locationstr)
			throws BundleException {
		Bundle bundle = findBundleByLocation(location);
		updateBundle(bundle, locationstr);
	}

	private void updateBundle(Bundle bundle, String locationstr)
			throws BundleException {
		if (bundle == null)
			throw new BundleException("Not a existing bundle");
		else {
			URL url = null;
			try {
				url = new URL(locationstr);
			} catch (MalformedURLException e) {
				throw new BundleException(null, e);
			}
			InputStream inputStream = null;
			try {
				inputStream = url.openStream();
			} catch (IOException e) {
				throw new BundleException(null, e);
			}
			bundle.update(inputStream);
		}
	}

	/**
	 * @see org.osgi.framework.Bundle#uninstall(long bundleId)
	 * @see org.ow2.aspirerfid.admin.AdminMBean#uninstallBundle(long)
	 */
	public void uninstallBundle(long bundleId) throws BundleException {
		Bundle bundle = findBundleById(bundleId);
		if (bundle == null)
			throw new BundleException("Not a existing bundle");
		else
			bundle.uninstall();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.ow2.aspirerfid.admin.AdminMBean#uninstallBundle(java.lang.String)
	 */
	public void uninstallBundle(String location) throws BundleException {
		Bundle bundle = findBundleByLocation(location);
		if (bundle == null) {
			throw new BundleException("Not a existing bundle");
		}
		bundle.uninstall();
	}

	/**
	 * @see org.osgi.framework.Bundle#getHeaders(long bundleId)
	 * @see org.ow2.aspirerfid.admin.AdminMBean#getBundleHeaders(long)
	 */
	public Dictionary getBundleHeaders(long bundleId) {
		Bundle bundle = findBundleById(bundleId);
		if (bundle == null)
			return null;
		else
			return bundle.getHeaders();
	}

	/**
	 * @see org.osgi.framework.Bundle#getLocation(long bundleId)
	 * @see org.ow2.aspirerfid.admin.AdminMBean#getBundleLocation(long)
	 */
	public String getBundleLocation(long bundleId) {
		Bundle bundle = findBundleById(bundleId);
		if (bundle == null)
			return null;
		else
			return bundle.getLocation();
	}

	/**
	 * @see org.osgi.framework.Bundle#getRegisteredServices(long bundleId)
	 * @see org.ow2.aspirerfid.admin.AdminMBean#getBundleRegisteredServices(long)
	 */
	public long[] getBundleRegisteredServices(long bundleId) {
		Bundle bundle = findBundleById(bundleId);
		if (bundle == null)
			return null;
		ServiceReference[] serviceReferences = bundle.getRegisteredServices();
		long[] serviceIds = new long[serviceReferences.length];
		for (int s = 0; s < serviceReferences.length; s++)
			serviceIds[s] = ((Long) serviceReferences[s]
					.getProperty(Constants.SERVICE_ID)).longValue();
		return serviceIds;
	}

	/**
	 * @see org.osgi.framework.Bundle#getServicesInUse(long bundleId)
	 * @see org.ow2.aspirerfid.admin.AdminMBean#getBundleServicesInUse(long)
	 */
	public long[] getBundleServicesInUse(long bundleId) {
		Bundle bundle = findBundleById(bundleId);
		if (bundle == null)
			return null;
		ServiceReference[] serviceReferences = bundle.getServicesInUse();
		long[] serviceIds = new long[serviceReferences.length];
		for (int s = 0; s < serviceReferences.length; s++)
			serviceIds[s] = ((Long) serviceReferences[s]
					.getProperty(Constants.SERVICE_ID)).longValue();
		return serviceIds;
	}

	/**
	 * @see org.osgi.framework.ServiceReference#getProperty(java.lang.String)
	 * @see org.ow2.aspirerfid.admin.AdminMBean#getServiceProperties(long)
	 */
	public TabularData getServiceProperties(long serviceId) {
		ServiceReference serviceReference = findServiceReferenceById(serviceId);
		if (serviceReference == null)
			return null;
		String keys[] = serviceReference.getPropertyKeys();

		String[] itemNames = new String[keys.length];
		String[] itemDescriptions = null;
		OpenType[] itemTypes = new OpenType[keys.length];
		for (int i = 0; i < keys.length; i++) {
			itemNames[i] = keys[i];
			// itemDescriptions[i]=null;
			itemTypes[i] = SimpleType.STRING; // TODO
		}
		String[] indexNames = { Constants.SERVICE_ID };

		CompositeType tPropertiesType = null;
		try {
			tPropertiesType = new CompositeType("tShirt",
					"a ServiceReference property", itemNames, itemDescriptions,
					itemTypes);

		} catch (OpenDataException e) {
			e.printStackTrace();
			return null;
		}

		TabularType tabularType = null;
		try {
			tabularType = new TabularType("tProperties",
					"List of ServiceReference properties", tPropertiesType, // row
					// type
					indexNames);
		} catch (OpenDataException e1) {
			e1.printStackTrace();
			return null;
		}
		TabularDataSupport tabularDataSupport = new TabularDataSupport(
				tabularType, keys.length, 0.75f);
		for (int k = 0; k < keys.length; k++) {
			tabularDataSupport.put(keys[k], serviceReference
					.getProperty(keys[k]));
		}

		return tabularDataSupport;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.ow2.aspirerfid.admin.AdminMBean#getBundlesInfos()
	 */
	public TabularData getBundlesInfos() {
		TabularData bundlesTabularData = null;
		try {

			String[] serviceItemNames = { "objectClass", "id" };
			String[] serviceIndexNames = { "objectClass", "id" };
			String[] serviceItemDescriptions = {
					"the list of the service interfaces", "the service id" };
			OpenType[] serviceItemTypes = { SimpleType.STRING, SimpleType.LONG };
			// TODO add the other service properties

			CompositeType serviceRowType = new CompositeType("services",
					"a ServiceReference property", serviceItemNames,
					serviceItemDescriptions, serviceItemTypes);

			ArrayType serviceArrayType = new ArrayType(1, serviceRowType);

			// TODO Add services in use
			String[] bundleItemNames = { "id", "name", "version", "state",
					"location", "services" };
			String[] bundleIndexNames = { "id", "name", "version", "state",
					"location", "services" };
			String[] bundleItemDescriptions = { "the bundle id",
					"the bundle name", "the bundle version",
					"the bundle state", "the bundle location",
					"the services registered by the bundle" };
			OpenType[] bundleItemTypes = { SimpleType.LONG, SimpleType.STRING,
					SimpleType.STRING, SimpleType.INTEGER, SimpleType.STRING,
					serviceArrayType };
			CompositeType bundleRowType = new CompositeType("bundle infos",
					"description", bundleItemNames, bundleItemDescriptions,
					bundleItemTypes);
			TabularType tabularType = new TabularType("tabular type",
					"description", bundleRowType, bundleIndexNames);

			bundlesTabularData = new TabularDataSupport(tabularType);
			Bundle[] bundles = bundleContext.getBundles();
			for (int b = 0; b < bundles.length; b++) {
				Bundle bundle = bundles[b];

				ServiceReference[] serviceReferences = bundle
						.getRegisteredServices();
				CompositeData[] serviceCompositeDatas;
				if (serviceReferences != null) {
					serviceCompositeDatas = new CompositeData[serviceReferences.length];
					for (int s = 0; s < serviceReferences.length; s++) {
						ServiceReference serviceReference = serviceReferences[s];
						Map serviceMap = new HashMap();
						serviceMap.put(serviceIndexNames[0], Util
								.getValueString(serviceReference
										.getProperty(Constants.OBJECTCLASS)));
						serviceMap.put(serviceIndexNames[1], serviceReference
								.getProperty(Constants.SERVICE_ID));
						serviceCompositeDatas[s] = new CompositeDataSupport(
								serviceRowType, serviceMap);
					}
				} else {
					serviceCompositeDatas = new CompositeData[0];
				}

				Dictionary headers = bundle.getHeaders();
				Map map = new HashMap();
				map.put(bundleIndexNames[0], new Long(bundle.getBundleId()));
				map.put(bundleIndexNames[1], headers.get(Constants.BUNDLE_NAME)
						.toString());
				map.put(bundleIndexNames[2], headers.get(
						Constants.BUNDLE_VERSION).toString());
				map.put(bundleIndexNames[3], new Integer(bundle.getState()));
				map.put(bundleIndexNames[4], bundle.getLocation());
				map.put(bundleIndexNames[5], serviceCompositeDatas);

				CompositeData bundleCompositeData = new CompositeDataSupport(
						bundleRowType, map);

				bundlesTabularData.put(bundleCompositeData);

			}
		} catch (OpenDataException e) {
			e.printStackTrace();
		}
		return bundlesTabularData;
	}

	// public List tabularDataToList(TabularData tabularData) {
	// LinkedList bundleList = new LinkedList();
	// BundleInfo bundleInfos;
	// ServiceInfo serviceInfo;
	//
	// Iterator keyIter = tabularData.keySet().iterator();
	// for (int i = 0; keyIter.hasNext(); ++i) {
	// Object[] key = ((List) keyIter.next()).toArray();
	// CompositeData data = tabularData.get(key);
	// long bundleID = Long.parseLong(data.get("id").toString());
	// String bundleName = data.get("name").toString();
	// String bundleVersion = data.get("version").toString();
	// int bundleState = Integer.parseInt(data.get("state").toString());
	// String bundleLocation = data.get("location").toString();
	// List serviceList = new LinkedList();
	// CompositeData[] datas = (CompositeData[]) data.get("services");
	//
	// for (int j = 0; j < datas.length; j++) {
	// String objectName = datas[j].get("objectClass").toString();
	// String id = datas[j].get("id").toString();
	// serviceInfo = new ServiceInfo(objectName, id);
	// serviceList.add(serviceInfo);
	// }
	//
	// bundleInfos = new BundleInfo(bundleID, bundleName, bundleVersion,
	// bundleState, bundleLocation, serviceList);
	// bundleList.add(bundleInfos);
	// }
	//
	// return bundleList;
	// }

	/**
	 * @see org.osgi.framework.ServiceReference#getProperty(java.lang.String)
	 * @see org.ow2.aspirerfid.admin.AdminMBean#getServiceProperty(long,
	 *      java.lang.String)
	 */
	public Object getServiceProperty(long serviceId, String key) {
		ServiceReference serviceReference = findServiceReferenceById(serviceId);
		if (serviceReference == null)
			return null;
		return serviceReference.getProperty(key);
	}

	/**
	 * @see org.osgi.framework.ServiceReference#getPropertyKeys()
	 * @see org.ow2.aspirerfid.admin.AdminMBean#getServicePropertyKeys(long)
	 */
	public String[] getServicePropertyKeys(long serviceId) {
		ServiceReference serviceReference = findServiceReferenceById(serviceId);
		if (serviceReference == null)
			return null;
		return serviceReference.getPropertyKeys();
	}

	/**
	 * @see org.osgi.framework.BundleContext#getProperty()
	 * @see org.ow2.aspirerfid.admin.AdminMBean#getFrameworkProperty(java.lang.String)
	 */
	public String getFrameworkProperty(String key) {
		return bundleContext.getProperty(key);
	}

	private final static String[] frameworkPropertyKeys = {
			Constants.FRAMEWORK_EXECUTIONENVIRONMENT,
			Constants.FRAMEWORK_LANGUAGE, Constants.FRAMEWORK_OS_NAME,
			Constants.FRAMEWORK_OS_VERSION, Constants.FRAMEWORK_PROCESSOR,
			Constants.FRAMEWORK_VENDOR, Constants.FRAMEWORK_VERSION
	// TODO extends this array with constants introduced in the R4 spec
	};

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.ow2.aspirerfid.admin.AdminMBean#getFrameworkPropertyKeys()
	 */
	public String[] getFrameworkPropertyKeys() {
		return frameworkPropertyKeys;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.ow2.aspirerfid.admin.AdminMBean#getFrameworkProperties()
	 */
	public TabularData getFrameworkProperties() {
		// TODO
		return null;
	}

	/**
	 * TODO Javadoc
	 * 
	 * @see org.osgi.framework.ServiceListener#serviceChanged(org.osgi.framework.ServiceEvent)
	 * @param serviceEvent
	 */
	public void serviceChanged(ServiceEvent serviceEvent) {
		sendNotification(sequenceNumber++, System.currentTimeMillis(),
				"ServicesIds changed", "ServicesIds", long[].class
						.getCanonicalName(), null, // getServiceIds(), // TODO
				// add, remove the ids
				getServiceIds(), serviceEvent.getServiceReference()
						.getProperty(Constants.SERVICE_ID));
	}

	/**
	 * @see org.osgi.framework.BundleListener#bundleChanged(org.osgi.framework.BundleEvent)
	 * @param bundleEvent
	 */
	public void bundleChanged(BundleEvent bundleEvent) {
		sendNotification(sequenceNumber++, System.currentTimeMillis(),
				"BundleIds changed", "BundleIds", long[].class
						.getCanonicalName(), null, // getBundleIds(), // TODO
				// add, remove the ids
				getBundleIds(), new Long(bundleEvent.getBundle().getBundleId()));
	}

	/**
	 * TODO Javadoc
	 * 
	 * @see org.osgi.framework.FrameworkListener#frameworkEvent(org.osgi.framework.FrameworkEvent)
	 * @param frameworkEvent
	 */
	public void frameworkEvent(FrameworkEvent frameworkEvent) {

	}

	// --- notification ---
	private int sequenceNumber = 1;
	private List notificationBroadcasterSupports = new ArrayList();

	/**
	 * TODO Javadoc
	 * 
	 * @param notificationBroadcasterSupport
	 */
	public void addNotificationBroadcasterSupport(
			NotificationBroadcasterSupport notificationBroadcasterSupport) {
		notificationBroadcasterSupports.add(notificationBroadcasterSupport);
	}

	/**
	 * TODO Javadoc
	 * 
	 * @param notificationBroadcasterSupport
	 */
	public void removeNotificationBroadcasterSupport(
			NotificationBroadcasterSupport notificationBroadcasterSupport) {
		notificationBroadcasterSupports.remove(notificationBroadcasterSupport);
	}

	private void sendNotification(int sequenceNumber, long timeStamp,
			String msg, String attributeName, String attributeType,
			Object oldValue, Object newValue, Object userData) {

		Iterator iterator = notificationBroadcasterSupports.iterator();
		while (iterator.hasNext()) {
			NotificationBroadcasterSupport notificationBroadcasterSupport = (NotificationBroadcasterSupport) iterator
					.next();
			Notification notification = new AttributeChangeNotification(
					notificationBroadcasterSupport, sequenceNumber, timeStamp,
					msg, attributeName, attributeType, oldValue, newValue);
			notification.setUserData(userData);
			notificationBroadcasterSupport.sendNotification(notification);
		}
	}

	// --- BundleActivator
	private ServiceListener serviceListener;
	private BundleListener bundleListener;
	private FrameworkListener frameworkListener;
	private CompositeType bundleRowType;

	/**
	 * TODO Javadoc
	 * 
	 * @author Didier Donsez
	 * @version 2007
	 */
	class MyFrameworkListener implements FrameworkListener {
		private AdminImpl adminImpl;

		/**
		 * TODO Javadoc
		 * 
		 * @param adminImpl
		 */
		public MyFrameworkListener(AdminImpl adminImpl) {
			this.adminImpl = adminImpl;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.osgi.framework.FrameworkListener#frameworkEvent(org.osgi.framework.FrameworkEvent)
		 */
		public void frameworkEvent(FrameworkEvent frameworkEvent) {
			adminImpl.frameworkEvent(frameworkEvent);
		}
	};

	/**
	 * TODO Javadoc
	 * 
	 * @author Didier Donsez
	 * @version 2007
	 */
	class MyBundleListener implements BundleListener {
		private AdminImpl adminImpl;

		/**
		 * TODO Javadoc
		 * 
		 * @param adminImpl
		 */
		public MyBundleListener(AdminImpl adminImpl) {
			this.adminImpl = adminImpl;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.osgi.framework.BundleListener#bundleChanged(org.osgi.framework.BundleEvent)
		 */
		public void bundleChanged(BundleEvent bundleEvent) {
			adminImpl.bundleChanged(bundleEvent);
		}
	};

	/**
	 * TODO Javadoc
	 * 
	 * @author Didier Donsez
	 * @version 2007
	 */
	class MyServiceListener implements ServiceListener {
		private AdminImpl adminImpl;

		/**
		 * TODO Javadoc
		 * 
		 * @param adminImpl
		 */
		public MyServiceListener(AdminImpl adminImpl) {
			this.adminImpl = adminImpl;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.osgi.framework.ServiceListener#serviceChanged(org.osgi.framework.ServiceEvent)
		 */
		public void serviceChanged(ServiceEvent serviceEvent) {
			adminImpl.serviceChanged(serviceEvent);
		}
	};

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.osgi.framework.BundleActivator#start(org.osgi.framework.BundleContext)
	 */
	public void start(BundleContext bundleContext) throws Exception {

		frameworkListener = new MyFrameworkListener(this);
		bundleListener = new MyBundleListener(this);
		serviceListener = new MyServiceListener(this);

		bundleContext.addFrameworkListener(frameworkListener);
		bundleContext.addBundleListener(bundleListener);
		bundleContext.addServiceListener(serviceListener);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.osgi.framework.BundleActivator#stop(org.osgi.framework.BundleContext)
	 */
	public void stop(BundleContext bundleContext) throws Exception {
		bundleContext.removeServiceListener(serviceListener);
		bundleContext.removeBundleListener(bundleListener);
		bundleContext.removeFrameworkListener(frameworkListener);
	}

}
