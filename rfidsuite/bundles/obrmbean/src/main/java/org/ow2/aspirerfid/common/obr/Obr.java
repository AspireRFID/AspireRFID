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
package org.ow2.aspirerfid.common.obr;

import javax.management.NotificationBroadcasterSupport;
import org.osgi.framework.BundleException;

/**
 * The class is a delegate for MBean implementations
 * 
 * @author François Fornaciari
 * @version 2007
 */
public class Obr extends NotificationBroadcasterSupport implements ObrMBean {

	private ObrMBean obrMBean;

	/**
	 * @param adminMBean
	 *            TODO Javadoc
	 */
	public Obr(ObrMBean adminMBean) {
		this.obrMBean = adminMBean;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.ow2.aspirerfid.common.obr.ObrMBean#obrAddRepository(java.lang.String)
	 */
	public void obrAddRepository(String repositoryUrl) {
		obrMBean.obrAddRepository(repositoryUrl);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.ow2.aspirerfid.common.obr.ObrMBean#obrDeployBundle(java.lang.String,
	 *      java.lang.String)
	 */
	public void obrDeployBundle(String name, String version)
			throws BundleException {
		obrMBean.obrDeployBundle(name, version);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.ow2.aspirerfid.common.obr.ObrMBean#obrStartBundle(java.lang.String,
	 *      java.lang.String)
	 */
	public void obrStartBundle(String name, String version)
			throws BundleException {
		obrMBean.obrStartBundle(name, version);
	}
}
