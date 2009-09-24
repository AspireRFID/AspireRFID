/**
 * Copyright © 2008-2010, Aspire 
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

package org.ow2.aspirerfid.connectors.engine;

import java.util.HashMap;

/**
 * @author Nektarios Leontiadis (nele@ait.edu.gr)
 *
 */
public class SubscriptionManager {

    private static HashMap<String,String> subscriptionMap;
    
    static {
	subscriptionMap = new HashMap<String, String>();
    }
    
    private SubscriptionManager() {}
    
    public static boolean add(String subscriptionId, String endpoint)
    {
	if (subscriptionMap.containsKey(subscriptionId))
		return false;
	else 
	    subscriptionMap.put(subscriptionId, endpoint);
	return true;
    }
    
    public static String get(String subscriptionId)
    {
	return subscriptionMap.get(subscriptionId);
    }
    
    public static void remove(String subscriptionId)
    {
	subscriptionMap.remove(subscriptionId);
    }
}
