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

package org.ow2.aspirerfid.connectors.api;

/**
 * This interface should be implemented by the applications that require Events to be received
 * @author Nektarios Leontiadis (nele@ait.edu.gr)
 *
 */

public interface ClientEventHandler {

    /**
     * This operation should be implemented in an application (WMS) specific manner. The connector will forward 
     * the events to the application using this operations and then the application is responsible to handle it in a way 
     * that fits to the specific application. 
     * @param event The event to be processed
     */
    public void handleEvent(Event event);
}
