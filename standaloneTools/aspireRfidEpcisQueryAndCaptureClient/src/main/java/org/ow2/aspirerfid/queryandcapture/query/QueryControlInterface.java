/*
 * Copyright (C) 2007 ETH Zurich
 *
 * This file is part of Fosstrak (www.fosstrak.org).
 *
 * Fosstrak is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License version 2.1, as published by the Free Software Foundation.
 *
 * Fosstrak is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with Fosstrak; if not, write to the Free
 * Software Foundation, Inc., 51 Franklin Street, Fifth Floor,
 * Boston, MA  02110-1301  USA
 */

package org.ow2.aspirerfid.queryandcapture.query;

import java.util.List;

import org.ow2.aspirerfid.commons.epcis.model.Poll;
import org.ow2.aspirerfid.commons.epcis.model.QueryParams;
import org.ow2.aspirerfid.commons.epcis.model.QueryResults;
import org.ow2.aspirerfid.commons.epcis.model.SubscriptionControls;
import org.ow2.aspirerfid.commons.epcis.wsdl.query.DuplicateSubscriptionExceptionResponse;
import org.ow2.aspirerfid.commons.epcis.wsdl.query.ImplementationExceptionResponse;
import org.ow2.aspirerfid.commons.epcis.wsdl.query.InvalidURIExceptionResponse;
import org.ow2.aspirerfid.commons.epcis.wsdl.query.NoSuchNameExceptionResponse;
import org.ow2.aspirerfid.commons.epcis.wsdl.query.NoSuchSubscriptionExceptionResponse;
import org.ow2.aspirerfid.commons.epcis.wsdl.query.QueryParameterExceptionResponse;
import org.ow2.aspirerfid.commons.epcis.wsdl.query.QueryTooComplexExceptionResponse;
import org.ow2.aspirerfid.commons.epcis.wsdl.query.QueryTooLargeExceptionResponse;
import org.ow2.aspirerfid.commons.epcis.wsdl.query.SecurityExceptionResponse;
import org.ow2.aspirerfid.commons.epcis.wsdl.query.SubscribeNotPermittedExceptionResponse;
import org.ow2.aspirerfid.commons.epcis.wsdl.query.SubscriptionControlsExceptionResponse;
import org.ow2.aspirerfid.commons.epcis.wsdl.query.ValidationExceptionResponse;

/**
 * @author Marco Steybe
 */
public interface QueryControlInterface {

    /**
     * Performs a poll operation at the repository's Query Controls Module.
     * 
     * @param poll
     *            The Poll object including the query name and parameters to be
     *            executed.
     * @return The QueryResults.
     * @throws QueryParameterExceptionResponse
     * @throws NoSuchNameExceptionResponse
     * @throws ValidationExceptionResponse
     * @throws SecurityExceptionResponse
     * @throws QueryTooLargeExceptionResponse
     * @throws QueryTooComplexExceptionResponse
     * @throws ImplementationExceptionResponse
     */
    QueryResults poll(final Poll poll) throws ImplementationExceptionResponse, QueryTooComplexExceptionResponse,
            QueryTooLargeExceptionResponse, SecurityExceptionResponse, ValidationExceptionResponse,
            NoSuchNameExceptionResponse, QueryParameterExceptionResponse;

    /**
     * Performs a subscribe operation at the repository's Query Controls Module,
     * i.e. subscribes a query for later execution.
     * 
     * @param queryName
     *            The name of the query to be executed.
     * @param params
     *            The parameters of the query to be executed.
     * @param dest
     *            The destination address where QueryResults will be sent once
     *            the subscribed query gets executed.
     * @param controls
     *            The SubscriptionControls.
     * @param subscriptionId
     *            The ID of the subscription.
     * @throws QueryParameterExceptionResponse
     * @throws SubscriptionControlsExceptionResponse
     * @throws NoSuchNameExceptionResponse
     * @throws SubscribeNotPermittedExceptionResponse
     * @throws ValidationExceptionResponse
     * @throws InvalidURIExceptionResponse
     * @throws SecurityExceptionResponse
     * @throws QueryTooComplexExceptionResponse
     * @throws ImplementationExceptionResponse
     * @throws DuplicateSubscriptionExceptionResponse
     */
    void subscribe(final String queryName, final QueryParams params, final String dest,
            final SubscriptionControls controls, final String subscriptionId)
            throws DuplicateSubscriptionExceptionResponse, ImplementationExceptionResponse,
            QueryTooComplexExceptionResponse, SecurityExceptionResponse, InvalidURIExceptionResponse,
            ValidationExceptionResponse, SubscribeNotPermittedExceptionResponse, NoSuchNameExceptionResponse,
            SubscriptionControlsExceptionResponse, QueryParameterExceptionResponse;

    /**
     * Perform an unsubscribe operation at the repository's Query Controls
     * Module, i.e. unsubscribes a previously subscribed query.
     * 
     * @param subscriptionId
     *            The ID of the query to be unsubscribed.
     * @throws NoSuchSubscriptionExceptionResponse
     * @throws ValidationExceptionResponse
     * @throws SecurityExceptionResponse
     * @throws ImplementationExceptionResponse
     */
    void unsubscribe(final String subscriptionId) throws ImplementationExceptionResponse, SecurityExceptionResponse,
            ValidationExceptionResponse, NoSuchSubscriptionExceptionResponse;

    /**
     * Retrieves the names of queries that can be coped with.
     * 
     * @return A List of query names.
     * @throws ValidationExceptionResponse
     * @throws SecurityExceptionResponse
     * @throws ImplementationExceptionResponse
     */
    List<String> getQueryNames() throws ImplementationExceptionResponse, SecurityExceptionResponse,
            ValidationExceptionResponse;

    /**
     * Retrieves the ID of a subscribed query.
     * 
     * @param queryName
     *            The name of the query.
     * @return A List of IDs.
     * @throws NoSuchNameExceptionResponse
     * @throws ValidationExceptionResponse
     * @throws SecurityExceptionResponse
     * @throws ImplementationExceptionResponse
     */
    List<String> getSubscriptionIds(final String queryName) throws ImplementationExceptionResponse,
            SecurityExceptionResponse, ValidationExceptionResponse, NoSuchNameExceptionResponse;

    /**
     * Retrieves the standard version implemented by this implementation.
     * 
     * @return The implemented standard version.
     * @throws ValidationExceptionResponse
     * @throws SecurityExceptionResponse
     * @throws ImplementationExceptionResponse
     */
    String getStandardVersion() throws ImplementationExceptionResponse, SecurityExceptionResponse,
            ValidationExceptionResponse;

    /**
     * Retrieves the vendor version.
     * 
     * @return The vendor version.
     * @throws ValidationExceptionResponse
     * @throws SecurityExceptionResponse
     * @throws ImplementationExceptionResponse
     */
    String getVendorVersion() throws ImplementationExceptionResponse, SecurityExceptionResponse,
            ValidationExceptionResponse;
}
