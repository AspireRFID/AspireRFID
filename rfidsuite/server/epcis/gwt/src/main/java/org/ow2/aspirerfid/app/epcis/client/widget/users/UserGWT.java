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
package org.ow2.aspirerfid.app.epcis.client.widget.users;

import java.io.Serializable;

import org.ow2.aspirerfid.app.epcis.client.widget.roles.RoleGWT;

/**
 * TODO Javadoc
 * 
 * @author Guillaume Vaudaux-Ruth
 * @version 2007
 */
public class UserGWT implements Serializable {
    
    private static final long serialVersionUID = -1365788837579549301L;
    
    /**
     * Mail
     */
    private String mail;
    
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
    private RoleGWT role;
    
    private String oldLogin;
    
    /**
     * TODO Javadoc
     */
    public UserGWT() {
    }
    
    /**
     * TODO Javadoc
     * 
     * @param login
     * @param pass
     * @param mail
     * @param role
     */
    public UserGWT(String login, String pass, String mail, RoleGWT role) {
        this.mail = mail;
        this.login = login;
        this.pass = pass;
        this.role = role;
    }
    
    /**
     * TODO Javadoc
     * 
     * @return
     */
    public String getLogin() {
        return login;
    }
    
    /**
     * TODO Javadoc
     * 
     * @param login
     */
    public void setLogin(String login) {
        this.oldLogin = this.login;
        this.login = login;
    }
    
    /**
     * TODO Javadoc
     * 
     * @return
     */
    public String getMail() {
        return mail;
    }
    
    /**
     * TODO Javadoc
     * 
     * @param mail
     */
    public void setMail(String mail) {
        this.mail = mail;
    }
    
    /**
     * TODO Javadoc
     * 
     * @return
     */
    public String getPass() {
        return pass;
    }
    
    /**
     * TODO Javadoc
     * 
     * @param pass
     */
    public void setPass(String pass) {
        this.pass = pass;
    }
    
    /**
     * TODO Javadoc
     * 
     * @return
     */
    public RoleGWT getRole() {
        return role;
    }
    
    /**
     * TODO Javadoc
     * 
     * @param role
     */
    public void setRole(RoleGWT role) {
        this.role = role;
    }
    
    /**
     * TODO Javadoc
     * 
     * @return
     */
    public String getOldLogin() {
        return oldLogin;
    }
}
