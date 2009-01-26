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

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * The user role bean.
 * 
 * @author Guillaume Vaudaux-Ruth
 * @version 2007
 */
@Entity
@Table(name = "ROLES")
public class RoleBean implements Serializable {
    
    private static final long serialVersionUID = -58283494768908721L;
    
    private String name;
    private int id;
    private Set<PrivilegeBean> privileges;
    private Set<UserBean> users;
    
    /**
     * Constructor
     * 
     * @param name
     *            name
     */
    public RoleBean(String name) {
        this.name = name;
        this.privileges = new HashSet<PrivilegeBean>();
        this.users = new HashSet<UserBean>();
    }
    
    /**
     * TODO Javadoc
     * 
     * @param user
     */
    public void addUser(UserBean user) {
        this.users.add(user);
    }
    
    /**
     * TODO Javadoc
     * 
     * @param user
     */
    public void removeUser(UserBean user) {
        this.users.remove(user);
    }
    
    /**
     * TODO Javadoc
     * 
     * @return
     */
    @OneToMany(mappedBy = "role", fetch = FetchType.EAGER)
    public Set<UserBean> getUsers() {
        return users;
    }
    
    /**
     * TODO Javadoc
     * 
     * @param users
     */
    public void setUsers(Set<UserBean> users) {
        this.users = users;
    }
    
    /**
     * TODO Javadoc
     */
    public RoleBean() {
    }
    
    /**
     * Get the primary key
     * 
     * @return The primary key
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public int getId() {
        return id;
    }
    
    /**
     * Set the primary key
     * 
     * @param id
     *            The primary key
     */
    public void setId(int id) {
        this.id = id;
    }
    
    /**
     * Gets functions name
     * 
     * @return name
     */
    public String getName() {
        return name;
    }
    
    /**
     * Sets functions name
     * 
     * @param name
     *            name
     */
    public void setName(String name) {
        this.name = name;
    }
    
    /**
     * TODO Javadoc
     * 
     * @return
     */
    @ManyToMany(fetch = FetchType.EAGER, cascade = {
            CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH
    })
    @JoinTable(joinColumns = @JoinColumn(name = "ROLES_ID", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "PRIVILEGES_ID", referencedColumnName = "name"))
    public Set<PrivilegeBean> getPrivileges() {
        return privileges;
    }
    
    /**
     * TODO Javadoc
     * 
     * @param privileges
     */
    public void setPrivileges(Set<PrivilegeBean> privileges) {
        this.privileges = privileges;
    }
    
}
