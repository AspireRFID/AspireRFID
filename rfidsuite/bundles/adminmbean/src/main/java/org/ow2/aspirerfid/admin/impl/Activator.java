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

import java.util.Dictionary;
import java.util.Properties;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;
import org.ow2.aspirerfid.admin.Admin;
import org.ow2.aspirerfid.admin.AdminMBean;

/**
 * TODO Javadoc
 * 
 * @author Unknown
 * @version 2007
 */

public class Activator implements BundleActivator {
	private AdminImpl adminImpl;
	private Admin admin;
	private ServiceRegistration serviceRegistration;

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.osgi.framework.BundleActivator#start(org.osgi.framework.BundleContext)
	 */
	public void start(BundleContext bundleContext) throws Exception {
		adminImpl = new AdminImpl(bundleContext);
		admin = new Admin(adminImpl);
		adminImpl.addNotificationBroadcasterSupport(admin);

		Dictionary dictionary = new Properties();
		dictionary.put("jmxagent.objectname", "org.osgi:name=AdminMBean");
		serviceRegistration = bundleContext.registerService(AdminMBean.class
				.getName(), admin, dictionary);

		adminImpl.start(bundleContext);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.osgi.framework.BundleActivator#stop(org.osgi.framework.BundleContext)
	 */
	public void stop(BundleContext bundleContext) throws Exception {
		adminImpl.stop(bundleContext);

		serviceRegistration.unregister();

		adminImpl.removeNotificationBroadcasterSupport(admin);

	}
}
