/*
 * Copyright 2005-2008, Aspire This library is free software; you can
 * redistribute it and/or modify it under the terms of the GNU Lesser General
 * Public License as published by the Free Software Foundation (the "LGPL");
 * either version 2.1 of the License, or (at your option) any later version. If
 * you do not alter this notice, a recipient may use your version of this file
 * under either the LGPL version 2.1, or (at his option) any later version. You
 * should have received a copy of the GNU Lesser General Public License along
 * with this library; if not, write to the Free Software Foundation, Inc., 51
 * Franklin Street, Fifth Floor, Boston, MA 02110-1301 USA. This software is
 * distributed on an "AS IS" basis, WITHOUT WARRANTY OF ANY KIND, either express
 * or implied. See the GNU Lesser General Public License for the specific
 * language governing rights and limitations.
 */
package org.ow2.aspirerfid.epcis.server;

import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * Remote interface for the session bean ViewAdminBean
 * 
 * @author François Fornaciari, Guillaume Surrel, Guillaume Vaudaux-Ruth
 * @version 0.1.0
 */
public interface UserManagementRemote {
    
    /**
     * Init DB : only for developpement
     * 
     * @deprecated
     */
    public void initDB();
    
    /**
     * Gets list of users
     * 
     * @return list of users
     */
    public List<UserBean> getUsers();
    
    /**
     * Adds a new user
     * 
     * @param login
     *            the user's login
     * @param password
     *            the user's password
     * @param roleId
     */
    public UserBean addUser(String login, String password, String email,
            int roleId);
    
    /**
     * Removes a user
     * 
     * @param login
     */
    public void removeUser(String login);
    
    /**
     * Modifies a user
     * 
     * @param login
     * @param newLogin
     * @param newPassword
     * @param roleId
     */
    public UserBean modifyUser(String login, String newLogin,
            String newPassword, String newEmail, int roleId);
    
    /**
     * Gets a user by its name
     * 
     * @return the requested user
     */
    public UserBean getUser(String login);
    
    /**
     * Removes the role
     * 
     * @param roleId
     *            the role id
     */
    public void removeRole(int roleId);
    
    /**
     * Adds a new role
     * 
     * @param roleName
     *            the role name
     * @param privilegeNames
     *            the privileges of the role
     * @return the role identifier
     */
    public RoleBean addRole(String roleName, Set<String> privilegeNames);
    
    /**
     * Modifies an existing role
     * 
     * @param roleId
     *            the role id
     * @param newName
     *            the new name
     * @param newPrivilegeNames
     *            the privileges of the role
     */
    public RoleBean modifyRole(int roleId, String newName,
            Set<String> newPrivilegeNames);
    
    /**
     * Gets a role by its name
     * 
     * @return the requested role
     */
    public RoleBean getRole(String name);
    
    /**
     * Lists all the roles
     * 
     * @return a list containing all roles
     */
    public List<RoleBean> getAllRoles();
    
    /**
     * Lists the roles of the connected user
     * 
     * @return a list containing all roles
     */
    public RoleBean getUserRole();
    
    /**
     * Lists all the privileges
     * 
     * @return a list containing all privileges
     */
    public List<String> getPrivileges();
    
    /**
     * @param login
     *            the user's login
     * @param pass
     *            the user's password
     * @return true if login and password are correct
     */
    public Boolean authentication(String login, String pass);
    
    /**
     * Saves the parameters of a lost warning
     * 
     * @param reader
     *            the reader logical name
     * @param duration
     *            the duration
     * @param warningTitle
     *            the title of the warning
     */
    public void addWarningLost(String reader, long duration, String warningTitle);
    
    /**
     * Saves the parameters of a temperature warning
     * 
     * @param dateBegin
     *            the beginning date
     * @param operator
     *            the operator
     * @param value
     *            the boundary value
     * @param warningTitle
     *            the title of the warning
     */
    public void addWarningTemp(Date dateBegin, String operator, double value,
            String readerName, String warningTitle);
    
    /**
     * Lists the lost warnings saved by the connected user
     * 
     * @return the list of all lost warnings saved by the user
     */
    public Set<WarningLostBean> getUserWarningLost();
    
    /**
     * Lists the temperature warnings saved by the connected user
     * 
     * @return the list of all temperature warnings saved by the user
     */
    public Set<WarningTempExceedingBean> getUserWarningTemp();
    
    /**
     * TODO Javadoc
     * 
     * @param id
     */
    public void removeWarningTemp(int id);
    
    /**
     * TODO Javadoc
     * 
     * @param id
     */
    public void removeWarningLost(int id);
    
}
