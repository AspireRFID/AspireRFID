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
package org.ow2.aspirerfid.app.epcis.client;

import java.util.List;

import org.ow2.aspirerfid.app.epcis.client.widget.roles.RoleGWT;

import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * RemoteAdminView session, see by client.
 * 
 * @author Guillaume Vaudaux-Ruth
 * @version 2007
 */
public interface RemoteAdminViewAsync {
	/**
	 * Authentication
	 * 
	 * @param login
	 *            login
	 * @param pass
	 *            password
	 * @return true if authentication succeeds
	 */
	public void authentication(String login, String pass, AsyncCallback callback);

	/**
	 * Gets privileges
	 * 
	 * @return list of privileges
	 */
	public void getPrivileges(AsyncCallback callback);

	/**
	 * Gets list of roles
	 * 
	 * @return List of roles
	 */
	public void getRoles(AsyncCallback callback);

	/**
	 * Initialize DB
	 * 
	 */
	public void initDB(AsyncCallback callback);

	/**
	 * Add role
	 * 
	 * @param r
	 *            role
	 */
	public void addRole(RoleGWT r, AsyncCallback callback);

	/**
	 * Remove role
	 * 
	 * @param roleId
	 *            role id
	 */
	public void removeRole(int roleId, AsyncCallback callback);

	/**
	 * Modify role
	 * 
	 * @param roleId
	 *            role id
	 * @param newName
	 *            role name
	 * @param newPrivilegeNames
	 *            list of privileges
	 */
	public void modifyRole(int roleId, String newName, List newPrivilegeNames,
			AsyncCallback callback);

	/**
	 * Get list of users
	 * 
	 * @return list of user
	 */
	public void getUsers(AsyncCallback callback);

	/**
	 * TODO Javadoc
	 * 
	 * @param login
	 * @param callback
	 */
	public void removeUser(String login, AsyncCallback callback);

	/**
	 * TODO Javadoc
	 * 
	 * @param login
	 * @param password
	 * @param mail
	 * @param roleId
	 * @param callback
	 */
	public void addUser(String login, String password, String mail, int roleId,
			AsyncCallback callback);

	/**
	 * TODO Javadoc
	 * 
	 * @param login
	 * @param newLogin
	 * @param newPassword
	 * @param newMail
	 * @param newRoleId
	 * @param callback
	 */
	public void modifyUser(String login, String newLogin, String newPassword,
			String newMail, int newRoleId, AsyncCallback callback);

	/**
	 * TODO Javadoc
	 * 
	 * @param callback
	 */
	public void getEditorConf(AsyncCallback callback);

	/**
	 * TODO Javadoc
	 * 
	 * @param tag
	 * @param callback
	 */
	public void getEPCHistory(String tag, AsyncCallback callback);

	/**
	 * TODO Javadoc
	 * 
	 * @param tag
	 * @param unit
	 * @param w
	 * @param h
	 * @param callback
	 */
	public void getTempatureChart(String tag, String unit, int w, int h,
			AsyncCallback callback);

	/**
	 * TODO Javadoc
	 * 
	 * @param callback
	 */
	public void getUserPrivilege(AsyncCallback callback);

	/**
	 * TODO Javadoc
	 * 
	 * @param begin
	 * @param end
	 * @return
	 */
	public void getFiltrateDate(String beginDate, String beginHours,
			String endDate, String endHours, AsyncCallback callback);

	/**
	 * TODO Javadoc
	 * 
	 * @param reader
	 * @param duration
	 * @return
	 */
	public void getLostTags(String reader, long duration, AsyncCallback callback);

	/**
	 * TODO Javadoc
	 * 
	 * @param tag
	 * @return
	 */
	public void searchTag(String tagPattern, AsyncCallback callback);

	/**
	 * TODO Javadoc
	 * 
	 * @param gatewayPattern
	 * @return
	 */
	public void searchGateway(String gatewayPattern, AsyncCallback callback);

	/**
	 * TODO Javadoc
	 * 
	 * @param readerPattern
	 * @return
	 */
	public void searchReader(String readerPattern, AsyncCallback callback);

	/**
	 * TODO Javadoc
	 * 
	 * @return
	 */
	public void getAllReaders(AsyncCallback callback);

	/**
	 * TODO Javadoc
	 * 
	 * @return
	 */
	public void getAllGateways(AsyncCallback callback);

	/**
	 * Saves the parameters of a lost warning
	 * 
	 * @param reader
	 *            the first reader logical name
	 * @param duration
	 *            the duration
	 * @param warningTitle
	 *            the title of the warning
	 */
	public void addWarningLost(String reader, long duration,
			String warningTitle, AsyncCallback callback);

	/**
	 * @param dateBegin
	 *            the beginning date
	 * @param dateEnd
	 *            the stopping date
	 * @param operator
	 *            the operator
	 * @param value
	 *            the boundary value
	 * @return the list of epc exceeding the boundary value respecting the given
	 *         operator
	 */
	public void getEPCTemperatureAlert(String beginDate, String beginHours,
			String endDate, String endHours, String operator, double value,
			String readerName, AsyncCallback callback);

	/**
	 * Saves the parameters of a temperature warning
	 * 
	 * @param dateBegin
	 *            the beginning date
	 * @param dateEnd
	 *            the stopping date
	 * @param operator
	 *            the operator
	 * @param value
	 *            the boundary value
	 * @param continuous
	 *            true if dateEnd should be the actual date
	 * @param warningTitle
	 *            the title of the warning
	 */
	public void addWarningTemp(String beginDate, String beginHours,
			String operator, double value, String readerName,
			String warningTitle, AsyncCallback callback);

	/**
	 * TODO Javadoc
	 * 
	 * @param callback
	 */
	public void getSavedWarningsLostResult(AsyncCallback callback);

	/**
	 * TODO Javadoc
	 * 
	 * @param callback
	 */
	public void getSavedWarningsTemperatureResult(AsyncCallback callback);

	/**
	 * TODO Javadoc
	 * 
	 * @return
	 */
	public void getArchitectureStruct(AsyncCallback callback);

	/**
	 * TODO Javadoc
	 * 
	 * @param id
	 * @param callback
	 */
	public void removeWarningLost(int id, AsyncCallback callback);

	/**
	 * TODO Javadoc
	 * 
	 * @param id
	 * @param callback
	 */
	public void removeWarningTemp(int id, AsyncCallback callback);

	/**
	 * TODO Javadoc
	 * 
	 * @param callback
	 */
	public void getWarnings(AsyncCallback callback);

	/**
	 * TODO Javadoc
	 * 
	 * @param tag
	 * @param callback
	 */
	public void getGeneralTagInformation(String tag, AsyncCallback callback);

	/**
	 * TODO Javadoc
	 * 
	 * @param tag
	 * @param callback
	 */
	public void getFullHistories(String tag, AsyncCallback callback);

	/**
	 * TODO Javadoc
	 * 
	 * @param beginDate
	 * @param beginHours
	 * @param endDate
	 * @param endHours
	 * @param callback
	 */
	public void getCountTagEntry(String beginDate, String beginHours,
			String endDate, String endHours, AsyncCallback callback);

	/**
	 * TODO Javadoc
	 * 
	 * @param beginDate
	 * @param beginHours
	 * @param endDate
	 * @param endHours
	 * @param offsetBegin
	 * @param maxResult
	 * @param sortBy
	 * @param desc
	 * @param callback
	 */
	public void getPartOfTagEntry(String beginDate, String beginHours,
			String endDate, String endHours, int offsetBegin, int maxResult,
			int sortBy, boolean desc, AsyncCallback callback);

}
