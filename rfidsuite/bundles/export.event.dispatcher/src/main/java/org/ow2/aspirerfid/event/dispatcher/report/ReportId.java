/*
 * Copyright 2005-2008, Aspire
 * 
 * This library is free software; you can redistribute it and/or modify it 
 * under the terms of the GNU Lesser General Public License as published by 
 * the Free Software Foundation (the "LGPL"); either version 2.1 of the 
 * License, or (at your option) any later version. If you do not alter this 
 * notice, a recipient may use your version of this file under either the 
 * LGPL version 2.1, or (at his option) any later version.
 * 
 * You should have received a copy of the GNU Lesser General Public License 
 * along with this library; if not, write to the Free Software Foundation, 
 * Inc., 51 Franklin Street, Fifth Floor, Boston, MA 02110-1301 USA.
 * 
 * This software is distributed on an "AS IS" basis, WITHOUT WARRANTY OF ANY 
 * KIND, either express or implied. See the GNU Lesser General Public 
 * License for the specific language governing rights and limitations.
 */
package org.ow2.aspirerfid.event.dispatcher.report;

/**
 * This class represents a unique report identifier.
 * 
 * @author François Fornaciari
 * @version 2007
 */
public class ReportId {
	/**
	 * The report gateway name
	 */
	private String gatewayName = null;

	/**
	 * The report message identifier
	 */
	private String messageId = null;

	/**
	 * The report date of the reception
	 */
	private long date = 0;

	/**
	 * Build a report identifier.
	 * 
	 * @param gatewayName
	 *            The report gateway name
	 * @param messageId
	 *            The report message identifier
	 * @param date
	 *            The report date of the reception
	 */
	public ReportId(String gatewayName, String messageId, long date) {
		this.gatewayName = gatewayName;
		this.messageId = messageId;
		this.date = date;
	}

	/**
	 * Returns the report gateway name
	 * 
	 * @return The report gateway name
	 */
	public String getGatewayName() {
		return gatewayName;
	}

	/**
	 * Sets the report gateway name
	 * 
	 * @param gatewayName
	 *            The report gateway name
	 */
	public void setGatewayName(String gatewayName) {
		this.gatewayName = gatewayName;
	}

	/**
	 * Returns the report message identifier
	 * 
	 * @return The report message identifier
	 */
	public String getMessageId() {
		return messageId;
	}

	/**
	 * Sets the report message identifier
	 * 
	 * @param messageId
	 *            The report message identifier
	 */
	public void setMessageId(String messageId) {
		this.messageId = messageId;
	}

	/**
	 * Returns the report date of the reception
	 * 
	 * @return The report date of the reception
	 */
	public long getDate() {
		return date;
	}

	/**
	 * Sets the report date of the reception
	 * 
	 * @param date
	 *            The report date of the reception
	 */
	public void setDate(long date) {
		this.date = date;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	public boolean equals(Object o) {
		if (!(o instanceof ReportId)) {
			return false;
		}

		ReportId reportId = (ReportId) o;
		return getGatewayName().compareTo(reportId.getGatewayName()) == 0
				&& getMessageId().compareTo(reportId.getMessageId()) == 0;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return getGatewayName() + ":" + getMessageId();
	}
}
