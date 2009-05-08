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

import java.io.Serializable;
import java.util.GregorianCalendar;

/**
 * @author Nektarios Leontiadis (nele@ait.edu.gr)
 * 
 */
public class SubscriptionParameters implements Serializable{

    private static final long serialVersionUID = -5579424976976797614L;
    private String replyEndpoint;
    private String transactionId;
    private String transactionType;
    private String subscriptionId;
    private String querySec;
    private String queryMin;
    private String queryHour;
    private String queryDayOfMonth;
    private String queryMonth;
    private String queryDayOfWeek;
    private GregorianCalendar initialTime;
    private boolean doPoll;
    private boolean reportIfEmpty;
    
    private final String defaultValue = "-1";

    public SubscriptionParameters() {
	querySec = defaultValue;
	queryMin = defaultValue;
	queryHour = defaultValue;
	queryDayOfMonth = defaultValue;
	queryMonth = defaultValue;
	queryDayOfWeek = defaultValue;
	initialTime = null;
	doPoll = false;
	transactionId = defaultValue;
	transactionType = defaultValue;
	subscriptionId = defaultValue;
	reportIfEmpty = false;
    }

    /**
     * @return the transactionId
     */
    public final String getTransactionId() {
	return transactionId;
    }

    /**
     * @param transactionId
     *            the transactionId to set
     */
    public final void setTransactionId(String transactionId) {
	this.transactionId = transactionId;
    }

    /**
     * @return the transactionType
     */
    public final String getTransactionType() {
	return transactionType;
    }

    /**
     * @param transactionType
     *            the transactionType to set
     */
    public final void setTransactionType(String transactionType) {
	this.transactionType = transactionType;
    }

    /**
     * @return the subscriptionId
     */
    public final String getSubscriptionId() {
	return subscriptionId;
    }

    /**
     * @param subscriptionId
     *            the subscriptionId to set
     */
    public final void setSubscriptionId(String subscriptionId) {
	this.subscriptionId = subscriptionId;
    }

    /**
     * @return the querySec
     */
    public final String getQuerySec() {
	return querySec;
    }

    /**
     * @param querySec
     *            the querySec to set
     */
    public final void setQuerySec(String querySec) {
	this.querySec = querySec;
    }

    /**
     * @return the queryMin
     */
    public final String getQueryMin() {
	return queryMin;
    }

    /**
     * @param queryMin
     *            the queryMin to set
     */
    public final void setQueryMin(String queryMin) {
	this.queryMin = queryMin;
    }

    /**
     * @return the queryHour
     */
    public final String getQueryHour() {
	return queryHour;
    }

    /**
     * @param queryHour
     *            the queryHour to set
     */
    public final void setQueryHour(String queryHour) {
	this.queryHour = queryHour;
    }

    /**
     * @return the queryDayOfMonth
     */
    public final String getQueryDayOfMonth() {
	return queryDayOfMonth;
    }

    /**
     * @param queryDayOfMonth
     *            the queryDayOfMonth to set
     */
    public final void setQueryDayOfMonth(String queryDayOfMonth) {
	this.queryDayOfMonth = queryDayOfMonth;
    }

    /**
     * @return the queryMonth
     */
    public final String getQueryMonth() {
	return queryMonth;
    }

    /**
     * @param queryMonth
     *            the queryMonth to set
     */
    public final void setQueryMonth(String queryMonth) {
	this.queryMonth = queryMonth;
    }

    /**
     * @return the queryDayOfWeek
     */
    public final String getQueryDayOfWeek() {
	return queryDayOfWeek;
    }

    /**
     * @param queryDayOfWeek
     *            the queryDayOfWeek to set
     */
    public final void setQueryDayOfWeek(String queryDayOfWeek) {
	this.queryDayOfWeek = queryDayOfWeek;
    }

    /**
     * @return the initialTime
     */
    public final GregorianCalendar getInitialTime() {
	return initialTime;
    }

    /**
     * @param initialTime
     *            the initialTime to set
     */
    public final void setInitialTime(GregorianCalendar initialTime) {
	this.initialTime = initialTime;
    }

    /**
     * @return the doPoll
     */
    public final boolean isDoPoll() {
	return doPoll;
    }

    /**
     * @param doPoll
     *            the doPoll to set
     */
    public final void setDoPoll(boolean doPoll) {
	this.doPoll = doPoll;
    }

    /**
     * @param replyEndpoint the replyEndpoint to set
     */
    public void setReplyEndpoint(String replyEndpoint) {
	this.replyEndpoint = replyEndpoint;
    }

    /**
     * @return the replyEndpoint
     */
    public String getReplyEndpoint() {
	return replyEndpoint;
    }

    /**
     * @param reportIfEmpty the reportIfEmpty to set
     */
    public void setReportIfEmpty(boolean reportIfEmpty) {
	this.reportIfEmpty = reportIfEmpty;
    }

    /**
     * @return the reportIfEmpty
     */
    public boolean isReportIfEmpty() {
	return reportIfEmpty;
    }

}
