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

package org.ow2.aspirerfid.demos.warehouse.management.tools;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * @author Nektarios Leontiadis (nele@ait.edu.gr)
 *
 */
public class Shipment {

    private String invoiceId;

    private HashMap<String, DeliveryItem> items;
    
    public Shipment(String invoiceId) {
	this.invoiceId = invoiceId;
	items = new HashMap<String, DeliveryItem>();
    }
    
    public Set<String> getItems()
    {
	return items.keySet();
    }
    public final void addItem(DeliveryItem item)
    {
	items.put(item.getItemCode(), item);
    }
    
    public final DeliveryItem getItemInfo(String itemCode)
    {
	for( Iterator<Map.Entry<String, DeliveryItem>> i = items.entrySet().iterator(); i.hasNext();)
	{
	    Map.Entry<String, DeliveryItem> me = i.next();
	    if(itemCode.startsWith(me.getKey()))
		return me.getValue();
	}
	return null;
    }
    
    public final void updateItemDeliveredQuantity(String itemCode, BigInteger quantity)
    {
	for( Iterator<Map.Entry<String, DeliveryItem>> i = items.entrySet().iterator(); i.hasNext();)
	{
	    Map.Entry<String, DeliveryItem> me = i.next();
	    if(itemCode.startsWith(me.getKey()))
		me.getValue().setQuantityDelivered(quantity);
	}
    }

    public boolean isComplete()
    {
	DeliveryItem item;
	for( Iterator<Map.Entry<String, DeliveryItem>> i = items.entrySet().iterator(); i.hasNext();)
	{
	    Map.Entry<String, DeliveryItem> me = i.next();
    	    item = me.getValue();
    	    if(item.getExpectedQuantity().compareTo(item.getQuantityDelivered()) != 0)
    		return false;
	}
	
	return true;
	
    }
    
    /**
     * @return the invoiceId
     */
    public final String getInvoiceId() {
        return invoiceId;
    }
    
}
