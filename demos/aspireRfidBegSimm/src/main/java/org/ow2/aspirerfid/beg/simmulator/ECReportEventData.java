/*
 * Copyright (C) 2008-2010, Aspire
 * 
 * Aspire is free software; you can redistribute it and/or modify it under the
 * terms of the GNU Lesser General Public License version 2.1 as published by
 * the Free Software Foundation (the "LGPL").
 * 
 * You should have received a copy of the GNU Lesser General Public License
 * along with this library in the file COPYING-LGPL-2.1; if not, write to the
 * Free Software Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA
 * 02110-1301 USA.
 * 
 * This software is distributed on an "AS IS" basis, WITHOUT WARRANTY OF ANY
 * KIND, either express or implied. See the GNU Lesser General Public License
 * for the specific language governing rights and limitations.
 */


package org.ow2.aspirerfid.beg.simmulator;

import java.util.ArrayList;

/**
 * @author Nikos Kefalakis (nkef) e-mail: nkef@ait.edu.gr
 *
 */
public class ECReportEventData {
	
	private String bizTransactionParentID = "";
	private String bizTransactionID = "";
	private String parentObject = "";
	private ArrayList<String> transactionItems = new ArrayList<String>();

	public ECReportEventData(String bizTransactionID) {
		super();
		this.bizTransactionID = bizTransactionID;
	}

	public ECReportEventData() {
		super();
	}
	
	public ECReportEventData(String bizTransactionParentID, String bizTransactionID, String parentObject) {
		super();
		this.bizTransactionParentID = bizTransactionParentID;
		this.bizTransactionID = bizTransactionID;
		this.parentObject = parentObject;
	}
	public ECReportEventData(String bizTransactionParentID, String bizTransactionID, String parentObject, ArrayList<String> transactionItems) {
		super();
		this.bizTransactionParentID = bizTransactionParentID;
		this.bizTransactionID = bizTransactionID;
		this.parentObject = parentObject;
		this.transactionItems = transactionItems;
	}

	public String getBizTransactionParentID() {
		return bizTransactionParentID;
	}

	public void setBizTransactionParentID(String bizTransactionParentID) {
		this.bizTransactionParentID = bizTransactionParentID;
	}

	public String getBizTransactionID() {
		return bizTransactionID;
	}

	public void setBizTransactionID(String bizTransactionID) {
		this.bizTransactionID = bizTransactionID;
	}

	public String getParentObject() {
		return parentObject;
	}

	public void setParentObject(String parentObject) {
		this.parentObject = parentObject;
	}

	public ArrayList<String> getTransactionItems() {
		return transactionItems;
	}

	public void setTransactionItems(ArrayList<String> transactionItems) {
		this.transactionItems = transactionItems;
	}
	

	
	
	
}
