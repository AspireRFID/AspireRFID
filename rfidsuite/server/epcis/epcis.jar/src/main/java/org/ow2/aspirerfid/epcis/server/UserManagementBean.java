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
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.ejb.Remote;
import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 * Stateful Bean to manage the application
 * 
 * @author François Fornaciari, Guillaume Surrel, Guillaume Vaudaux-Ruth
 * @version 0.1.0 2007
 */
@Stateful
@Remote(UserManagementRemote.class)
@SuppressWarnings("unchecked")
public class UserManagementBean implements UserManagementRemote {
    
    /**
     * Entity manager used by this bean.
     */
    @PersistenceContext
    private EntityManager entityManager = null;
    
    /**
     * The authenticated user
     */
    private UserBean user;
    
    /*
     * (non-Javadoc)
     * 
     * @see org.ow2.aspirerfid.epcis.server.UserManagementRemote#removeUser(java.lang.String)
     */
    public void removeUser(String login) {
        UserBean user = entityManager.find(UserBean.class, login);
        if (user != null) {
            entityManager.remove(user);
        }
    }
    
    /*
     * (non-Javadoc)
     * 
     * @see org.ow2.aspirerfid.epcis.server.UserManagementRemote#modifyUser(java.lang.String,
     *      java.lang.String, java.lang.String, java.lang.String, int)
     */
    public UserBean modifyUser(String login, String newLogin,
            String newPassword, String newEmail, int roleId) {
        removeUser(login);
        return addUser(newLogin, newPassword, newEmail, roleId);
    }
    
    /*
     * (non-Javadoc)
     * 
     * @see org.ow2.aspirerfid.epcis.server.UserManagementRemote#addUser(java.lang.String,
     *      java.lang.String, java.lang.String, int)
     */
    public UserBean addUser(String login, String password, String email,
            int roleId) {
        RoleBean role = (RoleBean) entityManager.find(RoleBean.class, roleId);
        UserBean user = new UserBean(login, password, email, role);
        entityManager.persist(user);
        return user;
    }
    
    /*
     * (non-Javadoc)
     * 
     * @see org.ow2.aspirerfid.epcis.server.UserManagementRemote#getUser(java.lang.String)
     */
    public UserBean getUser(String login) {
        return (UserBean) entityManager.find(UserBean.class, login);
    }
    
    /*
     * (non-Javadoc)
     * 
     * @see org.ow2.aspirerfid.epcis.server.UserManagementRemote#getUsers()
     */
    public List<UserBean> getUsers() {
        Query q = entityManager.createQuery("SELECT u FROM UserBean u");
        return q.getResultList();
    }
    
    /*
     * (non-Javadoc)
     * 
     * @see org.ow2.aspirerfid.epcis.server.UserManagementRemote#removeRole(int)
     */
    public void removeRole(int roleId) {
        RoleBean role = entityManager.find(RoleBean.class, roleId);
        if (role != null) {
            entityManager.remove(role);
        }
    }
    
    /*
     * (non-Javadoc)
     * 
     * @see org.ow2.aspirerfid.epcis.server.UserManagementRemote#addRole(java.lang.String,
     *      java.util.Set)
     */
    public RoleBean addRole(String roleName, Set<String> privilegeNames) {
        RoleBean role = new RoleBean(roleName);
        Set<PrivilegeBean> privileges = new HashSet<PrivilegeBean>();
        for (String name : privilegeNames) {
            PrivilegeBean privilege = entityManager.find(PrivilegeBean.class,
                    name);
            if (privilege == null) {
                privilege = new PrivilegeBean(name);
            }
            privileges.add(privilege);
        }
        role.setPrivileges(privileges);
        entityManager.persist(role);
        return role;
    }
    
    /*
     * (non-Javadoc)
     * 
     * @see org.ow2.aspirerfid.epcis.server.UserManagementRemote#modifyRole(int,
     *      java.lang.String, java.util.Set)
     */
    public RoleBean modifyRole(int roleId, String newName,
            Set<String> newPrivilegeNames) {
        RoleBean role = entityManager.find(RoleBean.class, roleId);
        if (role != null) {
            role.setName(newName);
            Set<PrivilegeBean> privileges = new HashSet<PrivilegeBean>();
            for (String name : newPrivilegeNames) {
                PrivilegeBean privilege = entityManager.find(
                        PrivilegeBean.class, name);
                if (privilege == null) {
                    privilege = new PrivilegeBean(name);
                }
                privileges.add(privilege);
            }
            role.setPrivileges(privileges);
            entityManager.flush();
        }
        return role;
    }
    
    /*
     * (non-Javadoc)
     * 
     * @see org.ow2.aspirerfid.epcis.server.UserManagementRemote#getRole(java.lang.String)
     */
    public RoleBean getRole(String name) {
        Query q = entityManager
                .createQuery("SELECT r FROM RoleBean r where r.name = :name");
        q.setParameter("name", name);
        return (RoleBean) q.getSingleResult();
    }
    
    /*
     * (non-Javadoc)
     * 
     * @see org.ow2.aspirerfid.epcis.server.UserManagementRemote#getAllRoles()
     */
    public List<RoleBean> getAllRoles() {
        Query q = entityManager.createQuery("SELECT r FROM RoleBean r");
        return q.getResultList();
    }
    
    /*
     * (non-Javadoc)
     * 
     * @see org.ow2.aspirerfid.epcis.server.UserManagementRemote#getUserRole()
     */
    public RoleBean getUserRole() {
        if (user == null) {
            return null;
        } else {
            return user.getRole();
        }
    }
    
    /*
     * (non-Javadoc)
     * 
     * @see org.ow2.aspirerfid.epcis.server.UserManagementRemote#getPrivileges()
     */
    public List<String> getPrivileges() {
        Query q = entityManager
                .createQuery("SELECT p.name FROM PrivilegeBean p");
        return q.getResultList();
    }
    
    /*
     * (non-Javadoc)
     * 
     * @see org.ow2.aspirerfid.epcis.server.UserManagementRemote#authentication(java.lang.String,
     *      java.lang.String)
     */
    public Boolean authentication(String login, String pass) {
        // prevent SQL injection hack
        login.replace("'", "''");
        user = entityManager.find(UserBean.class, login);
        if (user != null && user.getPass().equals(pass)) {
            return new Boolean(true);
        }
        return new Boolean(false);
    }
    
    /*
     * (non-Javadoc)
     * 
     * @see org.ow2.aspirerfid.epcis.server.UserManagementRemote#initDB()
     */
    public void initDB() {
        RoleBean role = new RoleBean("ROOT");
        Set<PrivilegeBean> privileges = new HashSet<PrivilegeBean>();
        privileges.add(new PrivilegeBean("READERS_NUMBER_OF_TAGS"));
        privileges.add(new PrivilegeBean("READERS_LIST_TAGS"));
        privileges.add(new PrivilegeBean("TAGS_TRACK_ON_MAP"));
        privileges.add(new PrivilegeBean("SEARCH_TAG"));
        privileges.add(new PrivilegeBean("SEARCH_READER"));
        privileges.add(new PrivilegeBean("SEARCH_GATEWAY"));
        privileges.add(new PrivilegeBean("ADMIN_LIST_USERS"));
        privileges.add(new PrivilegeBean("ADMIN_MANAGE_USERS"));
        privileges.add(new PrivilegeBean("ADMIN_MANAGE_ROLES"));
        privileges.add(new PrivilegeBean("ARCHI_SEE_NETWORK_TOPOLOGY"));
        privileges.add(new PrivilegeBean("WARN_LIST_WARNINGS"));
        privileges.add(new PrivilegeBean("WARN_LIST_OF_LOST_OBJECTS"));
        privileges.add(new PrivilegeBean("WARN_TEMPERATURE"));
        privileges.add(new PrivilegeBean("WARN_MONITORING"));
        privileges.add(new PrivilegeBean("SEARCH_TEMPERATURE"));
        
        role.setPrivileges(privileges);
        
        UserBean admin = new UserBean("admin", "admin", "mail@mail.com", role);
        entityManager.persist(role);
        entityManager.persist(admin);
    }
    
    /*
     * (non-Javadoc)
     * 
     * @see org.ow2.aspirerfid.epcis.server.UserManagementRemote#addWarningLost(java.lang.String,
     *      long, java.lang.String)
     */
    public void addWarningLost(String reader, long duration, String warningTitle) {
        UserBean user = entityManager
                .find(UserBean.class, this.user.getLogin());
        if (user != null) {
            WarningLostBean warn = new WarningLostBean(reader, duration,
                    warningTitle);
            entityManager.persist(warn);
            user.getWarningLostSet().add(warn);
            entityManager.flush();
            this.user = user;
        }
    }
    
    /*
     * (non-Javadoc)
     * 
     * @see org.ow2.aspirerfid.epcis.server.UserManagementRemote#addWarningTemp(java.util.Date,
     *      java.lang.String, double, java.lang.String, java.lang.String)
     */
    public void addWarningTemp(Date dateBegin, String operator, double value,
            String readerName, String warningTitle) {
        UserBean user = entityManager
                .find(UserBean.class, this.user.getLogin());
        if (user != null) {
            WarningTempExceedingBean warn = new WarningTempExceedingBean(
                    dateBegin, operator, value, readerName, warningTitle);
            entityManager.persist(warn);
            user.getWarningTempSet().add(warn);
            entityManager.flush();
            this.user = user;
        }
    }
    
    /*
     * (non-Javadoc)
     * 
     * @see org.ow2.aspirerfid.epcis.server.UserManagementRemote#getUserWarningLost()
     */
    public Set<WarningLostBean> getUserWarningLost() {
        if (user != null) {
            return user.getWarningLostSet();
        } else {
            return null;
        }
    }
    
    /*
     * (non-Javadoc)
     * 
     * @see org.ow2.aspirerfid.epcis.server.UserManagementRemote#getUserWarningTemp()
     */
    public Set<WarningTempExceedingBean> getUserWarningTemp() {
        if (user != null) {
            return user.getWarningTempSet();
        } else {
            return null;
        }
    }
    
    /*
     * (non-Javadoc)
     * 
     * @see org.ow2.aspirerfid.epcis.server.UserManagementRemote#removeWarningLost(int)
     */
    public void removeWarningLost(int id) {
        UserBean user = entityManager
                .find(UserBean.class, this.user.getLogin());
        WarningLostBean wLost = entityManager.find(WarningLostBean.class, id);
        if (wLost != null && user != null) {
            user.getWarningLostSet().remove(wLost);
            entityManager.remove(wLost);
            entityManager.flush();
            this.user = user;
        }
    }
    
    /*
     * (non-Javadoc)
     * 
     * @see org.ow2.aspirerfid.epcis.server.UserManagementRemote#removeWarningTemp(int)
     */
    public void removeWarningTemp(int id) {
        UserBean user = entityManager
                .find(UserBean.class, this.user.getLogin());
        WarningTempExceedingBean wTemp = entityManager.find(
                WarningTempExceedingBean.class, id);
        if (wTemp != null && user != null) {
            user.getWarningTempSet().remove(wTemp);
            entityManager.remove(wTemp);
            entityManager.flush();
            this.user = user;
        }
    }
    
}
