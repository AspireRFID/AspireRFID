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

import static javax.persistence.CascadeType.ALL;
import static javax.persistence.FetchType.EAGER;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * The User Bean
 * 
 * @author François Fornaciari, Guillaume Vaudaux-Ruth, Guillaume Surrel
 * @version 0.1.0 2007
 */
@Entity
@Table(name = "USERS")
@NamedQuery(name = "allUsers", query = "select o from UserBean o")
public class UserBean implements Serializable {
    
    private static final long serialVersionUID = -4493534703173985730L;
    
    /**
     * The user's mail
     */
    private String mail = null;
    
    /**
     * Login, the user's Id
     */
    private String login;
    
    /**
     * Pass
     */
    private String pass;
    
    /**
     * User privilege
     */
    private RoleBean role;
    
    /**
     * The list of lost warning requests made by the user
     */
    private Set<WarningLostBean> warningLostSet;
    
    /**
     * The list of temperature exceeding requests made by the user
     */
    private Set<WarningTempExceedingBean> warningTempSet;
    
    /**
     * Constructor by default
     */
    public UserBean() {
        warningLostSet = new HashSet<WarningLostBean>();
        warningTempSet = new HashSet<WarningTempExceedingBean>();
    }
    
    /**
     * Create an user
     * 
     * @param mail
     *            The user's mail
     */
    public UserBean(String login, String pass, String mail, RoleBean role) {
        this.mail = mail;
        this.login = login;
        this.pass = pass;
        this.role = role;
    }
    
    /**
     * Get the user's mail
     * 
     * @return The user's mail
     */
    public String getMail() {
        return mail;
    }
    
    /**
     * Set the user's mail
     * 
     * @param mail
     *            The user's mail
     */
    public void setMail(String mail) {
        this.mail = mail;
    }
    
    @Override
    public boolean equals(Object o) {
        if (!(o instanceof UserBean)) {
            return false;
        }
        UserBean user = (UserBean) o;
        return getLogin() == user.getLogin();
    }
    
    /**
     * Gets login
     * 
     * @return login
     */
    @Id
    public String getLogin() {
        return login;
    }
    
    /**
     * Sets login
     * 
     * @param login
     *            login
     */
    public void setLogin(String login) {
        this.login = login;
    }
    
    /**
     * Gets pass
     * 
     * @return pass
     */
    public String getPass() {
        return pass;
    }
    
    /**
     * Sets password
     * 
     * @param pass
     *            password
     */
    public void setPass(String pass) {
        this.pass = pass;
    }
    
    /**
     * Gets role
     * 
     * @return role role
     */
    @ManyToOne
    @JoinColumn(name = "ROLES_ID")
    public RoleBean getRole() {
        return role;
    }
    
    /**
     * Sets role
     * 
     * @param role
     *            role
     */
    public void setRole(RoleBean role) {
        this.role = role;
    }
    
    /**
     * TODO Javadoc
     * 
     * @return
     */
    @OneToMany(fetch = EAGER, cascade = {
        ALL
    })
    public Set<WarningLostBean> getWarningLostSet() {
        return warningLostSet;
    }
    
    /**
     * TODO Javadoc
     * 
     * @param warningLostSet
     */
    public void setWarningLostSet(Set<WarningLostBean> warningLostSet) {
        this.warningLostSet = warningLostSet;
    }
    
    /**
     * TODO Javadoc
     * 
     * @return
     */
    @OneToMany(fetch = EAGER, cascade = {
        ALL
    })
    public Set<WarningTempExceedingBean> getWarningTempSet() {
        return warningTempSet;
    }
    
    /**
     * TODO Javadoc
     * 
     * @param warningTempSet
     */
    public void setWarningTempSet(Set<WarningTempExceedingBean> warningTempSet) {
        this.warningTempSet = warningTempSet;
    }
}
