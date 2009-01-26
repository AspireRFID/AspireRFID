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
package org.ow2.aspirerfid.common.obr.impl;

import java.net.MalformedURLException;
import java.net.URL;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.BundleException;
import org.osgi.framework.ServiceReference;
import org.osgi.framework.Version;
import org.osgi.service.obr.Repository;
import org.osgi.service.obr.RepositoryAdmin;
import org.osgi.service.obr.Resolver;
import org.osgi.service.obr.Resource;
import org.ow2.aspirerfid.common.obr.ObrMBean;

/**
 * The class provides a service for configuring the instance with a JMX bundle
 * 
 * @author François Fornaciari
 * @version 2007
 */
public class ObrImpl implements ObrMBean, BundleActivator {

	private BundleContext bundleContext;

	/**
	 * @param bundleContext
	 *            TODO Javadoc
	 */
	public ObrImpl(BundleContext bundleContext) {
		this.bundleContext = bundleContext;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.ow2.aspirerfid.common.obr.ObrMBean#obrAddRepository(java.lang.String)
	 */
	public void obrAddRepository(String repositoryUrl) {
		ServiceReference serviceReference = bundleContext
				.getServiceReference("org.osgi.service.obr.RepositoryAdmin");
		RepositoryAdmin repository = (RepositoryAdmin) bundleContext
				.getService(serviceReference);
		try {
			repository.addRepository(new URL(repositoryUrl));
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.ow2.aspirerfid.common.obr.ObrMBean#obrDeployBundle(java.lang.String,
	 *      java.lang.String)
	 */
	public void obrDeployBundle(String name, String version)
			throws BundleException {
		deploy(name, version, false);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.ow2.aspirerfid.common.obr.ObrMBean#obrStartBundle(java.lang.String,
	 *      java.lang.String)
	 */
	public void obrStartBundle(String name, String version)
			throws BundleException {
		deploy(name, version, true);
	}

	/**
	 * Deploy a bundle (also the dependencies) and start it if the param start
	 * is true If the version is not null, deploys the bundle with the specific
	 * version If the version is null, deploys the bundle which has the higher
	 * version
	 * 
	 * @param name
	 * @param version
	 * @param start
	 * 
	 */
	public void deploy(String name, String version, boolean start) {
		ServiceReference serviceReference = bundleContext
				.getServiceReference("org.osgi.service.obr.RepositoryAdmin");

		if (serviceReference != null) {
			// Minimal version for comparation
			Version foundedVersion = new Version("0.0.0");
			// URL to return
			Resource foundedResource = null;

			RepositoryAdmin repository = (RepositoryAdmin) bundleContext
					.getService(serviceReference);
			Resolver resolver = repository.resolver();

			Repository[] repositories = repository.listRepositories();

			// List the repositories
			for (int i = 0; i < repositories.length; i++) {
				Resource[] resources = repositories[i].getResources();

				// List the bundles
				for (int j = 0; j < resources.length; j++) {
					// A bundle with the name bundleName is present
					if (resources[j].getPresentationName().compareTo(name) == 0) {

						// Verify that the versions are equal
						if (version != null
								&& resources[j].getVersion().toString()
										.compareTo(version) == 0) {
							resolver.add(resources[j]);
						}

						// The BundleURL value corresponds to the URL of the
						// bundle which has the higher version
						if (version == null
								&& resources[j].getVersion().compareTo(
										foundedVersion) > 0) {
							foundedResource = resources[j];
							foundedVersion = resources[j].getVersion();
						}
					}
				}
			}

			if (foundedResource != null)
				resolver.add(foundedResource);

			resolver.deploy(start);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.osgi.framework.BundleActivator#start(org.osgi.framework.BundleContext)
	 */
	public void start(BundleContext bundleContext) throws Exception {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.osgi.framework.BundleActivator#stop(org.osgi.framework.BundleContext)
	 */
	public void stop(BundleContext bundleContext) throws Exception {
	}
}
