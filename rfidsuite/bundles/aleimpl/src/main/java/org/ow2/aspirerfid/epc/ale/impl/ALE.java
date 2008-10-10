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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import java.net.URI;
import java.net.URISyntaxException;

import org.osgi.framework.BundleContext;
import org.ow2.aspirerfid.epc.ale.api.DuplicateNameException;
import org.ow2.aspirerfid.epc.ale.api.DuplicateSubscriptionException;
import org.ow2.aspirerfid.epc.ale.api.ECSpec;
import org.ow2.aspirerfid.epc.ale.api.ECSpecValidationException;
import org.ow2.aspirerfid.epc.ale.api.ImplementationException;
import org.ow2.aspirerfid.epc.ale.api.InvalidURIException;
import org.ow2.aspirerfid.epc.ale.api.NoSuchNameException;
import org.ow2.aspirerfid.epc.ale.api.NoSuchSubscriberException;
import org.ow2.aspirerfid.util.Logger;

/**
 * Implementation of the ALE application programming interface.<br/> Currently
 * unsupported parameters in the ALE specification : - ReportOnlyOnChange -
 * StartTrigger - StopTrigger - StableSetInterval - IncludeSpecInReports -
 * Include extensions in reports
 * 
 * @author François Fornaciari
 * @author Guillaume Surrel
 * @version 2007
 */
public class ALE implements ALEMBean, org.ow2.aspirerfid.epc.ale.api.ALE {

	private final Map specs = new HashMap();

	private final List subscriptions = new LinkedList();

	private final BundleContext bundleContext;

	private final Logger logger;

	private final Map rfidListeners = new HashMap();

	/**
	 * TODO Javadoc
	 * 
	 * @param bundleContext
	 * @param logger
	 */
	public ALE(final BundleContext bundleContext, final Logger logger) {
		this.bundleContext = bundleContext;
		this.logger = logger;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.ow2.aspirerfid.epc.ale.api.ALE#define(java.lang.String,
	 *      org.ow2.aspirerfid.epc.ale.api.ECSpec)
	 */
	public void define(final String ecSpecName, final ECSpec ecSpec)
			throws DuplicateNameException, ECSpecValidationException,
			SecurityException, ImplementationException {

		if (this.specs.containsKey(ecSpecName)) {
			throw new DuplicateNameException();
		} else {
			final RFIDListener listener = new RFIDListener(this.bundleContext,
					this.logger, this, ecSpecName, ecSpec);

			this.specs.put(ecSpecName, ecSpec);
			this.rfidListeners.put(ecSpecName, listener);

			this.logger.log(Logger.INFO, "Add listener for ECSpec "
					+ ecSpecName);

			// Start listening and sending
			listener.start();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.ow2.aspirerfid.epc.ale.api.ALE#getECSpec(java.lang.String)
	 */
	public ECSpec getECSpec(final String specName)
			throws ImplementationException, SecurityException,
			NoSuchNameException {
		final ECSpec spec = (ECSpec) this.specs.get(specName);
		if (spec == null) {
			throw new NoSuchNameException();
		} else {
			return spec;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.ow2.aspirerfid.epc.ale.impl.ALEMBean#getECSpecNames()
	 */
	public List getECSpecNames() throws SecurityException,
			ImplementationException {
		return new ArrayList(this.specs.keySet());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.ow2.aspirerfid.epc.ale.impl.ALEMBean#getStandardVersion()
	 */
	public String getStandardVersion() {
		return "1.0";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.ow2.aspirerfid.epc.ale.impl.ALEMBean#getSubscribers(java.lang.String)
	 */
	public List getSubscribers(final String specName)
			throws NoSuchNameException, SecurityException,
			ImplementationException {

		final List subscribers = new LinkedList();

		final Iterator it = this.subscriptions.iterator();
		while (it.hasNext()) {
			final ECSubscription subscription = (ECSubscription) it.next();

			if (subscription.getEcSpecName().compareTo(specName) == 0) {
				subscribers.add(subscription.getUri().toString());
			}
		}

		return subscribers;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.ow2.aspirerfid.epc.ale.impl.ALEMBean#getVendorVersion()
	 */
	public String getVendorVersion() {
		return "";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.ow2.aspirerfid.epc.ale.api.ALE#immediate(org.ow2.aspirerfid.epc.ale.api.ECSpec)
	 */
	public String immediate(final ECSpec ecSpec)
			throws ECSpecValidationException, SecurityException,
			ImplementationException {

		// TODO : allow multiple ecSpecName
		final String ecSpecName = "immediateReport";
		String reports = null;

		try {
			this.define(ecSpecName, ecSpec);
			reports = this.poll(ecSpecName);
			this.undefine(ecSpecName);
		} catch (final DuplicateNameException e) {
			e.printStackTrace();
		} catch (final NoSuchNameException e) {
			e.printStackTrace();
		}

		return reports;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.ow2.aspirerfid.epc.ale.impl.ALEMBean#poll(java.lang.String)
	 */
	public String poll(final String ecSpecName) throws NoSuchNameException,
			SecurityException, ImplementationException {
		final PollResult pollResult = new PollResult();
		return pollResult.getPollResult((RFIDListener) this.rfidListeners
				.get(ecSpecName));
	}

	/**
	 * Remove all subscriptions registered on this ecSpecName
	 * 
	 * @param ecSpecName
	 *            the name of the ECSpec
	 */
	private void removeSubscriptions(final String ecSpecName) {
		Iterator it = this.subscriptions.iterator();
		final List subscribionsToRemove = new LinkedList();
		while (it.hasNext()) {
			final ECSubscription subscription = (ECSubscription) it.next();
			if (subscription.getEcSpecName().compareTo(ecSpecName) == 0) {
				subscribionsToRemove.add(subscription);
			}
		}

		it = subscribionsToRemove.iterator();
		while (it.hasNext()) {
			final ECSubscription subscription = (ECSubscription) it.next();
			this.subscriptions.remove(subscription);
		}
	}

	/**
	 * TODO Javadoc
	 * 
	 * @throws SecurityException
	 * @throws NoSuchNameException
	 * @throws ImplementationException
	 */
	public void stop() throws SecurityException, NoSuchNameException,
			ImplementationException {
		final Iterator it = this.specs.keySet().iterator();
		while (it.hasNext()) {
			this.undefine((String) it.next());
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.ow2.aspirerfid.epc.ale.impl.ALEMBean#subscribe(java.lang.String,
	 *      java.lang.String)
	 */
	public void subscribe(final String ecSpecName, final String uri)
			throws NoSuchNameException, InvalidURIException,
			DuplicateSubscriptionException, SecurityException,
			ImplementationException {
		try {
			this.subscribe(ecSpecName, new URI(uri.trim()));
		} catch (final URISyntaxException e) {
			e.printStackTrace();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.ow2.aspirerfid.epc.ale.api.ALE#subscribe(java.lang.String,
	 *      java.net.URI)
	 */
	public void subscribe(final String ecSpecName, final URI uri)
			throws NoSuchNameException, InvalidURIException,
			DuplicateSubscriptionException, SecurityException,
			ImplementationException {

		final ECSubscription subscription = new ECSubscription(ecSpecName, uri);

		if (!this.specs.containsKey(ecSpecName)) {
			throw new NoSuchNameException();
		} else if (this.subscriptions.contains(subscription)) {
			throw new DuplicateSubscriptionException();
		} else {
			this.subscriptions.add(subscription);
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.ow2.aspirerfid.epc.ale.impl.ALEMBean#undefine(java.lang.String)
	 */
	public void undefine(final String ecSpecName) throws NoSuchNameException,
			SecurityException, ImplementationException {

		if (!this.specs.containsKey(ecSpecName)) {
			throw new NoSuchNameException();
		} else {
			// Stop RFID listener for this ecSpecName
			final RFIDListener listener = (RFIDListener) this.rfidListeners
					.get(ecSpecName);
			listener.remove();
			// Remove rfid listeners from the list
			this.rfidListeners.remove(ecSpecName);

			// Remove subscriptions
			this.removeSubscriptions(ecSpecName);

			this.specs.remove(ecSpecName);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.ow2.aspirerfid.epc.ale.impl.ALEMBean#unsubscribe(java.lang.String,
	 *      java.lang.String)
	 */
	public void unsubscribe(final String ecSpecName, final String uri)
			throws NoSuchNameException, NoSuchSubscriberException,
			InvalidURIException, SecurityException, ImplementationException {
		try {
			this.unsubscribe(ecSpecName, new URI(uri.trim()));
		} catch (final URISyntaxException e) {
			e.printStackTrace();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.ow2.aspirerfid.epc.ale.api.ALE#unsubscribe(java.lang.String,
	 *      java.net.URI)
	 */
	public void unsubscribe(final String ecSpecName, final URI uri)
			throws NoSuchNameException, NoSuchSubscriberException,
			InvalidURIException, SecurityException, ImplementationException {

		final ECSubscription subscription = new ECSubscription(ecSpecName, uri);

		if (!this.specs.containsKey(ecSpecName)) {
			throw new NoSuchNameException();
		} else if (!this.subscriptions.contains(subscription)) {
			throw new NoSuchSubscriberException();
		} else {
			this.subscriptions.remove(subscription);
		}

	}

}
