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
package org.ow2.aspirerfid.app.epcis.client.widget.roles;

import java.io.Serializable;
import java.util.List;

/**
 * RoleBean isn't visible by client side, so it need to be cast in Role.
 * 
 * @author Guillaume Vaudaux-Ruth
 * @version 2007
 */
public class RoleGWT implements Serializable {

	private static final long serialVersionUID = 6440737491102929043L;

	/**
	 * id
	 */
	private int id;

	/**
	 * Role name
	 */
	public String name;

	/**
	 * List of privileges
	 * 
	 * @gwt.typeArgs <java.lang.String>
	 */
	public List privileges;

	private boolean newUser;

	/**
	 * Default constructor
	 */
	public RoleGWT() {
	}

	/**
	 * Constructor
	 * 
	 * @param name
	 *            name
	 * @param privileges
	 *            list of privileges
	 * @param newUser
	 *            New user
	 * 
	 * @gwt.typeArgs privileges <java.lang.String>
	 */
	public RoleGWT(int id, String name, List privileges, boolean newUser) {
		this.id = id;
		this.name = name;
		this.privileges = privileges;
		this.newUser = newUser;
		if (id == -1 && !this.newUser) {
			System.out.println("Warning : id for a new Role must be -1");
		}
	}

	/**
	 * TODO Javadoc
	 * 
	 * @return
	 */
	public boolean getNewUser() {
		return newUser;
	}

	/**
	 * TODO Javadoc
	 * 
	 * @return
	 */
	public int getId() {
		return id;
	}
}
