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
package org.ow2.aspirerfid.app.epcis.client.widget.roles.callback;

import org.ow2.aspirerfid.app.epcis.client.IndexModule;
import org.ow2.aspirerfid.app.epcis.client.widget.roles.ListRoles;
import org.ow2.aspirerfid.app.epcis.client.widget.roles.RoleGWT;

import asquare.gwt.tk.client.ui.AlertDialog;

import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.Widget;

/**
 * Delete callback.
 * 
 * @author Guillaume Vaudaux-Ruths
 * @version 2007
 */
public class ListRolesCallbackDelete
		implements
			ClickListener,
			AsyncCallback,
			Command {

	private RoleGWT role;
	private ListRoles listRoles;

	/**
	 * TODO Javadoc
	 * 
	 * @param r
	 * @param listRoles
	 */
	public ListRolesCallbackDelete(RoleGWT r, ListRoles listRoles) {
		this.role = r;
		this.listRoles = listRoles;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.google.gwt.user.client.ui.ClickListener#onClick(com.google.gwt.user.client.ui.Widget)
	 */
	public void onClick(Widget sender) {
		AlertDialog dialog = AlertDialog.createWarning(this, "Are you sure ?",
				"You are about to delete the role : '" + role.name + "'");
		dialog.show();
		dialog.center();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.google.gwt.user.client.rpc.AsyncCallback#onFailure(java.lang.Throwable)
	 */
	public void onFailure(Throwable caught) {
		IndexModule.getKikooBox().showError("Delete role failed", 2000);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.google.gwt.user.client.rpc.AsyncCallback#onSuccess(java.lang.Object)
	 */
	public void onSuccess(Object result) {
		listRoles.update();
		IndexModule.getKikooBox().showOk("Delete role success", 1000);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.google.gwt.user.client.Command#execute()
	 */
	public void execute() {
		IndexModule.getSession().removeRole(role.getId(), this);
	}

}
