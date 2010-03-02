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
 * @author Nektarios Leontiadis
 * 
 */
public class DeliveryItem {

	private String company;
	private String itemCode;
	private String description;
	private BigInteger quantityDelivered;
	private BigInteger expectedQuantity;
	private BigInteger quantityRemain;
	private String deliveryDate;
	private String measurementID;
	private BigInteger quantity;

	public DeliveryItem() {
		quantityDelivered = BigInteger.ZERO;
	}

	/**
	 * @return the company
	 */
	public String getCompany() {
		return company;
	}

	/**
	 * @param company
	 *            the company to set
	 */
	public void setCompany(String company) {
		this.company = company;
	}

	/**
	 * @return the itemCode
	 */
	public String getItemCode() {
		return itemCode;
	}

	/**
	 * @param itemCode
	 *            the itemCode to set
	 */
	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description
	 *            the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the quantityDelivered
	 */
	public BigInteger getQuantityDelivered() {
		return quantityDelivered;
	}

	/**
	 * @param quantityDelivered
	 *            the quantityDelivered to set
	 */
	public void setQuantityDelivered(BigInteger quantityDelivered) {
		this.quantityDelivered = quantityDelivered;
	}

	/**
	 * @return the expectedQuantity
	 */
	public BigInteger getExpectedQuantity() {
		return expectedQuantity;
	}

	/**
	 * @param expectedQuantity
	 *            the expectedQuantity to set
	 */
	public void setExpectedQuantity(BigInteger expectedQuantity) {
		this.expectedQuantity = expectedQuantity;
	}

	/**
	 * @return the quantityRemain
	 */
	public BigInteger getQuantityRemain() {
		return quantityRemain;
	}

	/**
	 * @param quantityRemain
	 *            the quantityRemain to set
	 */
	public void setQuantityRemain(BigInteger quantityRemain) {
		this.quantityRemain = quantityRemain;
	}

	/**
	 * @return the deliveryDate
	 */
	public String getDeliveryDate() {
		return deliveryDate;
	}

	/**
	 * @param deliveryDate
	 *            the deliveryDate to set
	 */
	public void setDeliveryDate(String deliveryDate) {
		this.deliveryDate = deliveryDate;
	}

	/**
	 * @return the measurementID
	 */
	public String getMeasurementID() {
		return measurementID;
	}

	/**
	 * @param measurementID
	 *            the measurementID to set
	 */
	public void setMeasurementID(String measurementID) {
		this.measurementID = measurementID;
	}

	/**
	 * @return the quantity
	 */
	public BigInteger getQuantity() {
		return quantity;
	}

	/**
	 * @param quantity
	 *            the quantity to set
	 */
	public void setQuantity(BigInteger quantity) {
		this.quantity = quantity;
	}

}
