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
import org.ow2.aspirerfid.app.epcis.client.widget.roles.EditRole;
import org.ow2.aspirerfid.app.epcis.client.widget.roles.ListRoles;

import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

/**
 * Layout for the page : Change role.
 * 
 * @author Guillaume Vaudaux-Ruth
 * @version 2007
 */
public class ManageRolesLayout implements PageLayout {

	private final Fieldset f;
	private final ListRoles listRoles;

	/**
	 * TODO Javadoc
	 */
	public ManageRolesLayout() {
		VerticalPanel vp = new VerticalPanel();

		// Content
		listRoles = new ListRoles(IndexModule.getSession());
		vp.add(listRoles);
		vp.add(new HTML("<hr>"));

		Button addButton = new Button("New role");
		addButton.addClickListener(new ClickListener() {
			public void onClick(Widget sender) {
				EditRole editRole = new EditRole(listRoles);
				editRole.show();
				editRole.center();
			}
		});
		vp.add(addButton);
		vp.setSpacing(5);
		f = new Fieldset("Roles management", vp);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.ow2.aspirerfid.app.epcis.client.page.layout.PageLayout#display()
	 */
	public void display() {
		listRoles.update();
		IndexModule.setCentralWidget(f);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.ow2.aspirerfid.app.epcis.client.page.layout.PageLayout#getTitle()
	 */
	public String getTitle() {
		return "Role manager";
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
