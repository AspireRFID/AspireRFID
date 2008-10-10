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
package org.ow2.aspirerfid.app.epcis.client.page.layout;

import org.ow2.aspirerfid.app.epcis.client.IndexModule;
import org.ow2.aspirerfid.app.epcis.client.widget.Fieldset;
import org.ow2.aspirerfid.app.epcis.client.widget.users.EditUser;
import org.ow2.aspirerfid.app.epcis.client.widget.users.ListUsers;

import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

/**
 * Layout for the page : List user.
 * 
 * @author Guillaume Vaudaux-Ruth
 * @version 2007
 */
public class ManageUsersLayout implements PageLayout {

	private final ListUsers listUsers;
	private final Fieldset f;

	/**
	 * TODO Javadoc
	 * 
	 * @param isManagementEnabled
	 */
	public ManageUsersLayout(boolean isManagementEnabled) {
		// Content
		if (isManagementEnabled) {
			listUsers = new ListUsers(IndexModule.getSession());
			VerticalPanel vp = new VerticalPanel();
			vp.add(listUsers);
			vp.add(new HTML("<hr>"));
			vp.add(new Button("New user", new ClickListener() {
				public void onClick(Widget sender) {
					EditUser editUser = new EditUser(true, listUsers);
					editUser.show();
					editUser.center();
				}
			}));
			vp.setSpacing(5);
			f = new Fieldset("List of users", vp);

		} else {
			listUsers = new ListUsers(IndexModule.getSession(), false);
			f = new Fieldset("List of users", listUsers);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.ow2.aspirerfid.app.epcis.client.page.layout.PageLayout#display()
	 */
	public void display() {
		listUsers.update();
		IndexModule.setCentralWidget(f);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.ow2.aspirerfid.app.epcis.client.page.layout.PageLayout#getTitle()
	 */
	public String getTitle() {
		return "User manager";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.ow2.aspirerfid.app.epcis.client.page.layout.PageLayout#getCategorie()
	 */
	public String getCategorie() {
		return "Administration";
	}
}
