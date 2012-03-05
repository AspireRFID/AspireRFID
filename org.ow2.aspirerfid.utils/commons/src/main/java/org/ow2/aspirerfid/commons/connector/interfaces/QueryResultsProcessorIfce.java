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

/**
 * This defines an interface that should be implemented by a class that will handle
 * incoming EPCIS events
 * 
 * @author Nektarios Leontiadis (nele@ait.edu.gr)
 *
 */
public interface QueryResultsProcessorIfce {

    /**
     * This method should be called when a new EPCIS event is generated
     * @param result The EPCISEvent in XML representation
     */
    public void fetchQueryResult(String result);

}