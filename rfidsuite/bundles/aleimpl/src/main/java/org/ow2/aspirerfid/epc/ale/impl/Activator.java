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
package org.ow2.aspirerfid.epc.ale.impl;

import java.util.Dictionary;
import java.util.Hashtable;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;
import org.ow2.aspirerfid.util.Logger;

/**
 * This class creates and registers ALE and ECSpecFactory services managed
 * through JMX.
 * 
 * @author François Fornaciari
 * @author Guillaume Surrel
 * @vector 2007
 */
public class Activator implements BundleActivator {

	private ServiceRegistration ecSpecFactoryServiceRegistration;

	private ServiceRegistration aleServiceRegistration;

	private Logger logger;

	private ALE ale;

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.osgi.framework.BundleActivator#start(org.osgi.framework.BundleContext)
	 */
	public void start(final BundleContext context) throws Exception {
		this.logger = new Logger("ALE", Logger.INFO);

		this.ale = new ALE(context, this.logger);
		final Dictionary props = new Hashtable();
		props.put("jmxagent.objectname", "rfid:type=service,SymbolicName=ALE");
		// register a service implementation
		this.aleServiceRegistration = context.registerService(ALEMBean.class
				.getName(), this.ale, props);

		final ECSpecFactoryMBean ecSpec = new ECSpecFactory(this.ale,
				this.logger);
		props.put("jmxagent.objectname",
				"rfid:type=service,SymbolicName=ECSpecFactory");
		// register a service implementation
		this.ecSpecFactoryServiceRegistration = context.registerService(
				ECSpecFactoryMBean.class.getName(), ecSpec, props);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.osgi.framework.BundleActivator#stop(org.osgi.framework.BundleContext)
	 */
	public void stop(final BundleContext arg0) throws Exception {
		this.ale.stop();
		this.ecSpecFactoryServiceRegistration.unregister();
		this.aleServiceRegistration.unregister();
	}

}
