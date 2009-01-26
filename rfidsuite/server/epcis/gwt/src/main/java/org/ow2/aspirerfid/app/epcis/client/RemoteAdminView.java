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
import java.util.Map;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.ServiceDefTarget;

import org.ow2.aspirerfid.app.epcis.client.widget.roles.RoleGWT;
import org.ow2.aspirerfid.app.epcis.client.widget.tags.TagGeneralInformation;
import org.ow2.aspirerfid.app.epcis.client.widget.topology.ArchiElement;

/**
 * RemoteAdminView session, see by client.
 * 
 * @author Guillaume Vaudaux-Ruth
 * @version 2007
 */
public interface RemoteAdminView extends RemoteService {
	/**
	 * Utility class for simplifing access to the instance of async service.
	 */
	public static class Util {
		private static RemoteAdminViewAsync instance;

		public static RemoteAdminViewAsync getInstance() {
			if (instance == null) {
				instance = (RemoteAdminViewAsync) GWT
						.create(RemoteAdminView.class);
				ServiceDefTarget target = (ServiceDefTarget) instance;
				target.setServiceEntryPoint(GWT.getModuleBaseURL()
						+ "/RemoteAdminView");
			}
			return instance;
		}
	}

	/**
	 * Authentication
	 * 
	 * @param login
	 *            login
	 * @param pass
	 *            password
	 * @return true if authentication succeeds
	 */
	public Boolean authentication(String login, String pass);

	/**
	 * Gets privileges
	 * 
	 * @return list of privileges
	 * @gwt.typeArgs <java.lang.String>
	 */
	public List getPrivileges();

	/**
	 * Gets list of roles
	 * 
	 * @return List of roles
	 * @gwt.typeArgs <org.ow2.aspirerfid.app.epcis.client.widget.roles.RoleGWT>
	 */
	public List getRoles();

	/**
	 * Initialize DB
	 * 
	 */
	public void initDB();

	/**
	 * Add role
	 * 
	 * @param r
	 *            role
	 */
	public void addRole(RoleGWT r);

	/**
	 * Remove role
	 * 
	 * @param roleId
	 *            role id
	 */
	public void removeRole(int roleId);

	/**
	 * Modify role
	 * 
	 * @param roleId
	 *            role id
	 * @param newName
	 *            role name
	 * @param newPrivilegeNames
	 *            list of privileges
	 * @gwt.typeArgs newPrivilegeNames <java.lang.String>           
	 */
	public void modifyRole(int roleId, String newName, List newPrivilegeNames);

	/**
	 * Get list of users
	 * 
	 * @return list of user
	 * @gwt.typeArgs <org.ow2.aspirerfid.app.epcis.client.widget.users.UserGWT>
	 */
	public List getUsers();

	/**
	 * TODO Javadoc
	 * 
	 * @param login
	 */
	public void removeUser(String login);

	/**
	 * TODO Javadoc
	 * 
	 * @param login
	 * @param password
	 * @param mail
	 * @param roleId
	 */
	public void addUser(String login, String password, String mail, int roleId);

	/**
	 * TODO Javadoc
	 * 
	 * @param login
	 * @param newLogin
	 * @param newPassword
	 * @param newMail
	 * @param newRoleId
	 */
	public void modifyUser(String login, String newLogin, String newPassword,
			String newMail, int newRoleId);

	/**
	 * TODO Javadoc
	 * 
	 * @return
	 * @gwt.typeArgs <java.lang.String,java.lang.String>
	 */
	public Map getEditorConf();

	/**
	 * TODO Javadoc
	 * 
	 * @param tag
	 * @return
	 * @gwt.typeArgs <org.ow2.aspirerfid.app.epcis.client.widget.tags.ReportGroupListMemberGWT>
	 */
	public List getEPCHistory(String tag);

	/**
	 * TODO Javadoc
	 * 
	 * @param tag
	 * @param unit
	 * @param w
	 * @param h
	 * @return
	 */
	public String getTempatureChart(String tag, String unit, int w, int h);

	/**
	 * TODO Javadoc
	 * 
	 * @return
	 * @gwt.typeArgs <java.lang.String,java.lang.String>
	 */
	public List getUserPrivilege();

	/**
	 * TODO Javadoc
	 * 
	 * @param begin
	 * @param end
	 * @return
	 * @gwt.typeArgs <org.ow2.aspirerfid.app.epcis.client.widget.tags.ReportGroupListMemberGWT>
	 */
	public List getFiltrateDate(String beginDate, String beginHours,
			String endDate, String endHours);

	/**
	 * TODO Javadoc
	 * 
	 * @param reader
	 * @param duration
	 * @return
	 * @gwt.typeArgs <org.ow2.aspirerfid.app.epcis.client.widget.tags.ReportGroupListMemberGWT>
	 */
	public List getLostTags(String reader, long duration);

	/**
	 * TODO Javadoc
	 * 
	 * @param tag
	 * @return
	 * @gwt.typeArgs <java.lang.String,java.lang.String>
	 */
	public List searchTag(String tagPattern);

	/**
	 * TODO Javadoc
	 * 
	 * @param gatewayPattern
	 * @return
	 * @gwt.typeArgs <java.lang.String,java.lang.String>
	 */
	public List searchGateway(String gatewayPattern);

	/**
	 * TODO Javadoc
	 * 
	 * @param readerPattern
	 * @return
	 * @gwt.typeArgs <java.lang.String,java.lang.String>
	 */
	public List searchReader(String readerPattern);

	/**
	 * TODO Javadoc
	 * 
	 * @return
	 * @gwt.typeArgs <java.lang.String,java.lang.String>
	 */
	public List getAllReaders();

	/**
	 * TODO Javadoc
	 * 
	 * @return
	 * @gwt.typeArgs <java.lang.String,java.lang.String>
	 */
	public List getAllGateways();

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
	public void addWarningLost(String reader, long duration, String warningTitle);

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
	 * @gwt.typeArgs <org.ow2.aspirerfid.app.epcis.client.widget.tags.ReportGroupListMemberGWT>
	 */
	public List getEPCTemperatureAlert(String beginDate, String beginHours,
			String endDate, String endHours, String operator, double value,
			String readerName);

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
			String warningTitle);

	/**
	 * TODO Javadoc
	 * 
	 * @return
	 * @gwt.typeArgs <org.ow2.aspirerfid.app.epcis.client.widget.tags.ReportGroupListMemberGWT>
	 */
	public List getSavedWarningsLostResult();

	/**
	 * TODO Javadoc
	 * 
	 * @return
	 * @gwt.typeArgs <org.ow2.aspirerfid.app.epcis.client.widget.tags.ReportGroupListMemberGWT>
	 */
	public List getSavedWarningsTemperatureResult();

	/**
	 * TODO Javadoc
	 * 
	 * @return
	 * @gwt.typeArgs <org.ow2.aspirerfid.app.epcis.client.widget.topology.ArchiElement>
	 */
	public List getArchitectureStruct();

	/**
	 * TODO Javadoc
	 * 
	 * @param id
	 */
	public void removeWarningLost(int id);

	/**
	 * TODO Javadoc
	 * 
	 * @param id
	 */
	public void removeWarningTemp(int id);

	/**
	 * TODO Javadoc
	 * 
	 * @return
	 * @gwt.typeArgs <org.ow2.aspirerfid.app.epcis.client.widget.warnings.WarningGWT>
	 */
	public List getWarnings();

	/**
	 * TODO Javadoc
	 * 
	 * @param tag
	 * @return
	 */
	public TagGeneralInformation getGeneralTagInformation(String tag);

	/**
	 * TODO Javadoc
	 * 
	 * @param tag
	 * @return
	 * @gwt.typeArgs <org.ow2.aspirerfid.app.epcis.client.widget.tags.ReportGroupListMemberGWT>
	 */
	public List getFullHistories(String tag);

	/**
	 * TODO Javadoc
	 * 
	 * @param beginDate
	 * @param beginHours
	 * @param endDate
	 * @param endHours
	 * @return
	 */
	public Long getCountTagEntry(String beginDate, String beginHours,
			String endDate, String endHours);

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
	 * @return
	 * @gwt.typeArgs <org.ow2.aspirerfid.app.epcis.client.widget.tags.ReportGroupListMemberGWT>
	 */
	public List getPartOfTagEntry(String beginDate, String beginHours,
			String endDate, String endHours, int offsetBegin, int maxResult,
			int sortBy, boolean desc);

}
