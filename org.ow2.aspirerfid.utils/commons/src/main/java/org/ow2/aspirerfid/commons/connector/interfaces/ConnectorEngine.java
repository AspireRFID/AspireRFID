/**
 * Copyright (c) 2008-2010, Aspire 
 * 
 * Aspire is free software; you can redistribute it and/or 
 * modify it under  the terms of the GNU Lesser General Public 
 * License version 2.1 as published by the Free Software Foundation (the 
 * "LGPL"). 
 * 
 * You should have received a copy of the GNU Lesser General Public 
 * License along with this library in the file COPYING-LGPL-2.1; if not, write to the Free Software 
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301 USA. 
 * 
 * This software is distributed on an "AS IS" basis, WITHOUT WARRANTY 
 * OF ANY KIND, either express or implied. See the GNU Lesser General Public 
 * License for the specific language governing rights and limitations. 
 * 
 */

package org.ow2.aspirerfid.commons.connector.interfaces;

import javax.jws.WebService;
import org.ow2.aspirerfid.commons.connector.model.SubscriptionParameters;

/**
 * This interface define the endpoints that the connector client should invoke
 * when it wants to register or unregister for a specific transaction.
 * @author Nektarios Leontiadis (nele@ait.edu.gr)
 *
 */
@WebService
public interface ConnectorEngine {

    /**
     * Register an observator for a transaction
     * @param parameters The subscription parameters
     * @return True if the operations is completed without errors; false otherwise
     */
    public boolean startObservingTransaction(SubscriptionParameters parameters);
    
    /**
     * Unsubscribe an observator for an already registered transaction 
     * @param parameters The subscription that should be unsubscribed
     * @param isComplete Indicates whether we stop observing a transaction because it has been completed or for other reasons. If
     * it is complete we need to signal the EPCIS repository with a new TransactionEvent to delete the transaction.
     * @return True if the operation completes withour errors; false otherwise (e.g. there is no such subscription)
     */
    public boolean stopObservingTransaction(SubscriptionParameters parameters, boolean isComplete);
}
