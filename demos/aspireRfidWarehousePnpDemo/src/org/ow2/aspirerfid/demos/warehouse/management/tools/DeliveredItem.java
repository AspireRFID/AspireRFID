/*
 * Copyright (c) 2008-2010, Aspire
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

package org.ow2.aspirerfid.demos.warehouse.management.tools;

import java.math.BigInteger;


/**
 * @author nkef (Nikos Kefalakis)
 * @author nkons (Nikolaos Konstantinou)
 *
 */
public class DeliveredItem {
	
	private String company;
	private String itemCode;
	private String description;
	private BigInteger quantityDelivered;
	private BigInteger expectedQuantity;
	private BigInteger quantityRemain;
	private String deliveryDate;
	private String measurementId;
	private BigInteger counter;
	private boolean needed;	
	

	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}

	public String getItemCode() {
		return itemCode;
	}
	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}

	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

	public BigInteger getQuantityDelivered() {
		return quantityDelivered;
	}
	public void setQuantityDelivered(BigInteger quantityDelivered) {
		this.quantityDelivered = quantityDelivered;
	}

	public BigInteger getExpectedQuantity() {
		return expectedQuantity;
	}
	public void setExpectedQuantity(BigInteger expectedQuantity) {
		this.expectedQuantity = expectedQuantity;
	}
	
	public BigInteger getQuantityRemain() {
		return quantityRemain;
	}
	public void setQuantityRemain(BigInteger quantityRemain) {
		this.quantityRemain = quantityRemain;
	}
	
	public String getDeliveryDate() {
		return deliveryDate;
	}
	public void setDeliveryDate(String deliveryDate) {
		this.deliveryDate = deliveryDate;
	}

	public String getMeasurementId() {
		return measurementId;
	}
	public void setMeasurementId(String measurementId) {
		this.measurementId = measurementId;
	}

	public BigInteger getCounter() {
		return counter;
	}
	public void setCounter(BigInteger counter) {
		this.counter = counter;
	}
	
	public boolean isNeeded() {
		return needed;
	}
	public void setNeeded(boolean needed) {
		this.needed = needed;
	}
}
