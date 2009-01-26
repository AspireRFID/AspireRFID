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
package org.ow2.aspirerfid.epc.ale.api;

import java.net.URI;
import java.util.List;

/**
 * The Application Level Events (ALE) application programming interface. Support
 * EPCglobal ALE 1.0 specification.
 * 
 * @author Guillaume Surrel
 * @version 2007
 */
public interface ALE {

	/**
	 * Define a new event cycle specification for use with the poll and
	 * subscribe methods.
	 * 
	 * @param ecSpecName
	 *            The name of the specification.
	 * @param spec
	 *            The specification to define.
	 * @throws DuplicateNameException
	 *             if an ECSpec having the same name is already defined.
	 * @throws ECSpecValidationException
	 *             if the ECSpec is invalid.
	 * @throws ImplementationException
	 *             if there is an internal failure within the ALE implementation
	 * @throws SecurityException
	 *             if the operation was not permitted due to an access control
	 *             violation or other security concern
	 */
	public void define(String ecSpecName, ECSpec spec)
			throws DuplicateNameException, ECSpecValidationException,
			SecurityException, ImplementationException;

	/**
	 * Undefine an event cycle specification. All existing subscriptions will be
	 * removed.
	 * 
	 * @param ecSpecName
	 *            The name of the specification to undefine.
	 * @throws NoSuchNameException
	 *             if there is no ECSpec defined having the specified name.
	 * @throws SecurityException
	 *             if the operation was not permitted due to an access control
	 *             violation or other security concern
	 * @throws ImplementationException
	 *             if there is an internal failure within the ALE implementation
	 */
	public void undefine(String ecSpecName) throws NoSuchNameException,
			SecurityException, ImplementationException;

	/**
	 * Look up and return a previously defined event cycle specification by
	 * name.
	 * 
	 * @param specName
	 *            The name of the specification to look up.
	 * @return The ECSpec for the specified name, or null if there is no event
	 *         cycle specification defined with that name.
	 * @throws ImplementationException
	 *             if there is an internal failure within the ALE implementation
	 * @throws SecurityException
	 *             if the operation was not permitted due to an access control
	 *             violation or other security concern
	 * @throws NoSuchNameException
	 *             if there is no ECSpec defined having the specified name.
	 */
	public ECSpec getECSpec(String specName) throws ImplementationException,
			SecurityException, NoSuchNameException;

	/**
	 * Returns a list of the names of all event cycle specifications currently
	 * defined.
	 * 
	 * @return A list of strings naming the currently defined event cycle
	 *         specifications.
	 * @throws SecurityException
	 *             if the operation was not permitted due to an access control
	 *             violation or other security concern
	 * @throws ImplementationException
	 *             if there is an internal failure within the ALE implementation
	 */
	public List getECSpecNames() throws SecurityException,
			ImplementationException;

	/**
	 * Subscribe to asynchronous report delivery from an event cycle
	 * specification.
	 * 
	 * @param ecSpecName
	 *            The name of the specification to subscribe to.
	 * @param uri
	 *            The destination for asynchronously delivered reports.
	 * @throws NoSuchNameException
	 *             if there no ECSpec defined having the specified name.
	 * @throws InvalidURIException
	 *             if the specified URI cannot be interpreted, or has no driver
	 *             available.
	 * @throws DuplicateSubscriptionException
	 *             if the subscription already exists.
	 * @throws SecurityException
	 *             if the operation was not permitted due to an access control
	 *             violation or other security concern
	 * @throws ImplementationException
	 *             if there is an internal failure within the ALE implementation
	 */
	public void subscribe(String ecSpecName, URI uri)
			throws NoSuchNameException, InvalidURIException,
			DuplicateSubscriptionException, SecurityException,
			ImplementationException;

	/**
	 * Unsubscribe a specified destination from receiving asynchronous delivery
	 * of reports from a specified event cycle specification.
	 * 
	 * @param ecSpecName
	 *            The name of the specification to unsubscribe from.
	 * @param uri
	 *            The destination that will no longer receive reports.
	 * @throws NoSuchNameException
	 *             if there no ECSpec defined having the specified name.
	 * @throws NoSuchSubscriberException
	 *             if there is no subscription to the specified URI.
	 * @throws InvalidURIException
	 *             if the specified URI cannot be interpreted, or has no driver
	 *             available.
	 * @throws SecurityException
	 *             if the operation was not permitted due to an access control
	 *             violation or other security concern
	 * @throws ImplementationException
	 *             if there is an internal failure within the ALE implementation
	 */
	public void unsubscribe(String ecSpecName, URI uri)
			throws NoSuchNameException, NoSuchSubscriberException,
			InvalidURIException, SecurityException, ImplementationException;

	/**
	 * Activates a previously defined event cycle specification for one event
	 * cycle, synchronously returning a report.
	 * 
	 * @param ecSpecName
	 *            The name of the event cycle specification to activate.
	 * @return The reports generated from the event cycle.
	 * @throws NoSuchNameException
	 *             if there no ECSpec defined having the specified name.
	 * @throws SecurityException
	 *             if the operation was not permitted due to an access control
	 *             violation or other security concern
	 * @throws ImplementationException
	 *             if there is an internal failure within the ALE implementation
	 */
	public String poll(java.lang.String ecSpecName) throws NoSuchNameException,
			SecurityException, ImplementationException;

	/**
	 * Immediately define an event cycle specification and activate it for one
	 * event cycle, synchronously returning a report. This is similar to first
	 * defining a specification using define(ECSpec), activating it for one
	 * event cycle using poll(String), and undefining it using undefine(String),
	 * except that event cycle specifications created using this method will not
	 * be visible to listECSpecNames() or getECSpecInfo(String).
	 * 
	 * @param spec
	 *            The event cycle specification.
	 * @return The reports generated from one activation of the specification.
	 * @throws ECSpecValidationException
	 *             if the ECSpec is invalid.
	 * @throws SecurityException
	 *             if the operation was not permitted due to an access control
	 *             violation or other security concern
	 * @throws ImplementationException
	 *             if there is an internal failure within the ALE implementation
	 */
	public String immediate(ECSpec spec) throws ECSpecValidationException,
			SecurityException, ImplementationException;

	/**
	 * Returns a list of URIs which are subscribed to asynchronous reports for
	 * the specified ECSpec name.
	 * 
	 * @param specName
	 *            The name of the event cycle specification
	 * @return A List of URI instances, each a subscriber of the specified event
	 *         cycle specification.
	 * @throws NoSuchNameException
	 *             if there no ECSpec defined having the specified name.
	 * @throws SecurityException
	 *             if the operation was not permitted due to an access control
	 *             violation or other security concern
	 * @throws ImplementationException
	 *             if there is an internal failure within the ALE implementation
	 */
	public List getSubscribers(String specName) throws NoSuchNameException,
			SecurityException, ImplementationException;

	/**
	 * Returns a string that identifies what version of the EPCglobal ALE
	 * standard that this implementation complies with.
	 * 
	 * @return standard EPCGLobal version
	 */
	public String getStandardVersion();

	/**
	 * Returns a string that identifies the vendor and version of the
	 * implementation.
	 * 
	 * @return vendor specific version
	 */
	public String getVendorVersion();

}
