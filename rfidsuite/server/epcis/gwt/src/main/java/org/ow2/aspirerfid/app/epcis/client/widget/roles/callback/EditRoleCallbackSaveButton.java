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
import org.ow2.aspirerfid.app.epcis.client.RemoteAdminViewAsync;
import org.ow2.aspirerfid.app.epcis.client.widget.roles.EditRole;
import org.ow2.aspirerfid.app.epcis.client.widget.roles.ListRoles;
import org.ow2.aspirerfid.app.epcis.client.widget.roles.RoleGWT;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.Widget;

/**
 * Save button callback.
 * 
 * @author Guillaume Vaudaux-Ruth
 * @version 2007
 */
public class EditRoleCallbackSaveButton implements ClickListener, AsyncCallback {

	private EditRole editRole;

	private RemoteAdminViewAsync remoteView;

	private ListRoles listRoles;

	/**
	 * TODO Javadoc
	 * 
	 * @param editRole
	 * @param remoteView
	 * @param listRoles
	 */
	public EditRoleCallbackSaveButton(EditRole editRole,
			RemoteAdminViewAsync remoteView, ListRoles listRoles) {
		this.listRoles = listRoles;
		this.editRole = editRole;
		this.remoteView = remoteView;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.google.gwt.user.client.ui.ClickListener#onClick(com.google.gwt.user.client.ui.Widget)
	 */
	public void onClick(Widget sender) {
		RoleGWT role = editRole.getRole();

		// Add a new role ?
		if (role.getNewUser()) {
			remoteView.addRole(role, this);
		}
		// Change a existent role
		else {
			remoteView.modifyRole(role.getId(), role.name, role.privileges,
					this);
		}
		editRole.hide();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.google.gwt.user.client.rpc.AsyncCallback#onFailure(java.lang.Throwable)
	 */
	public void onFailure(Throwable caught) {
		IndexModule.getKikooBox().showError("Save role failed", 2000);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.google.gwt.user.client.rpc.AsyncCallback#onSuccess(java.lang.Object)
	 */
	public void onSuccess(Object result) {
		IndexModule.getKikooBox().showOk("Save role sucess", 1000);
		listRoles.update();
	}
}
