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

package org.ow2.aspirerfid.commons.connector.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * An event encapsulator class
 * @author Nektarios Leontiadis (nele@ait.edu.gr)
 * 
 */

public class Event implements Serializable {

    private static final long serialVersionUID = -6912773245563439525L;

    private long eventTime;
    private List<String> epcList;
    private String parentId;
    private List<String> childEpcs;
    private int quantity;
    private String action;
    private String bizStepId;
    private String dispositionId;
    private String readPointId;
    private String bizLocationId;
    private List<String> bizTransactionList;
    private String epcClass;
    private String subscriptionId;

    public Event() {
	epcList = new ArrayList<String>();
    }

    public String toString() {
	final String nl = ";";
	StringBuilder str = new StringBuilder();

	str.append("Subscription id: ");
	str.append(subscriptionId);
	str.append(nl);
	
	str.append("Time: ");
	str.append(eventTime);
	str.append(nl);

	if (parentId != null) {
	    str.append("Parent: ");
	    str.append(parentId);
	    str.append(nl);
	}

	if (epcList != null) {
	    str.append("EPC list: ");
	    for (String epc : epcList) {
		str.append("\t");
		str.append(epc);
		str.append(nl);
	    }
	}

	if (childEpcs != null) {
	    str.append("Child EPC list: ");
	    for (String epc : childEpcs) {
		str.append("\t");
		str.append(epc);
		str.append(nl);
	    }
	}

	if (bizTransactionList != null) {
	    str.append("Biz transaction list: ");
	    for (String epc : bizTransactionList) {
		str.append("\t");
		str.append(epc);
		str.append(nl);
	    }
	}

	if (quantity != 0) {
	    str.append("Quantity: ");
	    str.append(quantity);
	    str.append(nl);
	}

	if (action != null) {
	    str.append("Action: ");
	    str.append(action);
	    str.append(nl);
	}

	if (bizStepId != null) {
	    str.append("Buziness step: ");
	    str.append(bizStepId);
	    str.append(nl);
	}

	if (dispositionId != null) {
	    str.append("Disposition: ");
	    str.append(dispositionId);
	    str.append(nl);
	}

	if (readPointId != null) {
	    str.append("Readpoint id: ");
	    str.append(readPointId);
	    str.append(nl);
	}

	if (bizLocationId != null) {
	    str.append("Biz location id: ");
	    str.append(bizLocationId);
	    str.append(nl);
	}

	if (epcClass != null) {
	    str.append("Epc class: ");
	    str.append(epcClass);
	    str.append(nl);
	}

	return str.toString();
    }

    public void addToEpcList(String epc) {
	epcList.add(epc);
    }

    /**
     * @return the eventTime
     */
    public final long getEventTime() {
	return eventTime;
    }

    /**
     * @param eventTime
     *            the eventTime to set
     */
    public final void setEventTime(long eventTime) {
	this.eventTime = eventTime;
    }

    /**
     * @return the epcList
     */
    public final List<String> getEpcList() {
	return epcList;
    }

    /**
     * @param epcList
     *            the epcList to set
     */
    public final void setEpcList(List<String> epcList) {
	this.epcList = epcList;
    }

    /**
     * @return the parentId
     */
    public final String getParentId() {
	return parentId;
    }

    /**
     * @param parentId
     *            the parentId to set
     */
    public final void setParentId(String parentId) {
	this.parentId = parentId;
    }

    /**
     * @return the childEpcs
     */
    public final List<String> getChildEpcs() {
	return childEpcs;
    }

    /**
     * @param childEpcs
     *            the childEpcs to set
     */
    public final void setChildEpcs(List<String> childEpcs) {
	this.childEpcs = childEpcs;
    }

    /**
     * @return the quantity
     */
    public final int getQuantity() {
	return quantity;
    }

    /**
     * @param quantity
     *            the quantity to set
     */
    public final void setQuantity(int quantity) {
	this.quantity = quantity;
    }

    /**
     * @return the action
     */
    public final String getAction() {
	return action;
    }

    /**
     * @param action
     *            the action to set
     */
    public final void setAction(String action) {
	this.action = action;
    }

    /**
     * @return the bizStepId
     */
    public final String getBizStepId() {
	return bizStepId;
    }

    /**
     * @param bizStepId
     *            the bizStepId to set
     */
    public final void setBizStepId(String bizStepId) {
	this.bizStepId = bizStepId;
    }

    /**
     * @return the dispositionId
     */
    public final String getDispositionId() {
	return dispositionId;
    }

    /**
     * @param dispositionId
     *            the dispositionId to set
     */
    public final void setDispositionId(String dispositionId) {
	this.dispositionId = dispositionId;
    }

    /**
     * @return the readPointId
     */
    public final String getReadPointId() {
	return readPointId;
    }

    /**
     * @param readPointId
     *            the readPointId to set
     */
    public final void setReadPointId(String readPointId) {
	this.readPointId = readPointId;
    }

    /**
     * @return the bizLocationId
     */
    public final String getBizLocationId() {
	return bizLocationId;
    }

    /**
     * @param bizLocationId
     *            the bizLocationId to set
     */
    public final void setBizLocationId(String bizLocationId) {
	this.bizLocationId = bizLocationId;
    }

    /**
     * @return the bizTransactionList
     */
    public final List<String> getBizTransactionList() {
	return bizTransactionList;
    }

    /**
     * @param bizTransactionList
     *            the bizTransactionList to set
     */
    public final void setBizTransactionList(List<String> bizTransactionList) {
	this.bizTransactionList = bizTransactionList;
    }

    /**
     * @param epcClass
     *            the epcClass to set
     */
    public void setEpcClass(String epcClass) {
	this.epcClass = epcClass;
    }

    /**
     * @return the epcClass
     */
    public String getEpcClass() {
	return epcClass;
    }

    /**
     * @param subscriptionId the subscriptionId to set
     */
    public void setSubscriptionId(String subscriptionId) {
	this.subscriptionId = subscriptionId;
    }

    /**
     * @return the subscriptionId
     */
    public String getSubscriptionId() {
	return subscriptionId;
    }

}
