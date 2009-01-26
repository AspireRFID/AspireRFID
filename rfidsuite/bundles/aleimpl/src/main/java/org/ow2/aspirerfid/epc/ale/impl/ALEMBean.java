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

import java.util.List;

import org.ow2.aspirerfid.epc.ale.api.DuplicateSubscriptionException;
import org.ow2.aspirerfid.epc.ale.api.ImplementationException;
import org.ow2.aspirerfid.epc.ale.api.InvalidURIException;
import org.ow2.aspirerfid.epc.ale.api.NoSuchNameException;
import org.ow2.aspirerfid.epc.ale.api.NoSuchSubscriberException;

/**
 * The part of the ALE implementation which is accessible through JMX
 * 
 * @author Guillaume Surrel
 * @version 2007
 */
public interface ALEMBean {

	/**
	 * @return TODO Javadoc
	 * @throws SecurityException
	 * @throws ImplementationException
	 */
	public List getECSpecNames() throws SecurityException,
			ImplementationException;

	/**
	 * @return TODO Javadoc
	 */
	public String getStandardVersion();

	/**
	 * @param specName
	 * @return TODO Javadoc
	 * @throws NoSuchNameException
	 * @throws SecurityException
	 * @throws ImplementationException
	 */
	public List getSubscribers(String specName) throws NoSuchNameException,
			SecurityException, ImplementationException;

	/**
	 * @return TODO Javadoc
	 */
	public String getVendorVersion();

	/**
	 * @param ecSpecName
	 * @return TODO Javadoc
	 * @throws NoSuchNameException
	 * @throws SecurityException
	 * @throws ImplementationException
	 */
	public String poll(java.lang.String ecSpecName) throws NoSuchNameException,
			SecurityException, ImplementationException;

	/**
	 * TODO Javadoc
	 * 
	 * @param ecSpecName
	 * @param uri
	 * @throws NoSuchNameException
	 * @throws InvalidURIException
	 * @throws DuplicateSubscriptionException
	 * @throws SecurityException
	 * @throws ImplementationException
	 */
	public void subscribe(String ecSpecName, String uri)
			throws NoSuchNameException, InvalidURIException,
			DuplicateSubscriptionException, SecurityException,
			ImplementationException;

	/**
	 * @param ecSpecName
	 *            TODO Javadoc
	 * @throws NoSuchNameException
	 * @throws SecurityException
	 * @throws ImplementationException
	 */
	public void undefine(String ecSpecName) throws NoSuchNameException,
			SecurityException, ImplementationException;

	/**
	 * TODO Javadoc
	 * 
	 * @param ecSpecName
	 * @param uri
	 * @throws NoSuchNameException
	 * @throws NoSuchSubscriberException
	 * @throws InvalidURIException
	 * @throws SecurityException
	 * @throws ImplementationException
	 */
	public void unsubscribe(String ecSpecName, String uri)
			throws NoSuchNameException, NoSuchSubscriberException,
			InvalidURIException, SecurityException, ImplementationException;

}
