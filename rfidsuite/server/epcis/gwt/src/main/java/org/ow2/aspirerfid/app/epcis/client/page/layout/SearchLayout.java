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
import org.ow2.aspirerfid.app.epcis.client.widget.table.CustomTable;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

/**
 * Layout for the page : Search.
 * 
 * @author Guillaume Vaudaux-Ruth
 * @version 2007
 */
public class SearchLayout implements PageLayout {

	private final ListBox lbType;

	private final TextBox tbName;

	private final CustomTable resultTable;

	private final VerticalPanel vp;

	/**
	 * TODO Javadoc
	 */
	public SearchLayout() {
		FlexTable flexTable = new FlexTable();
		resultTable = new CustomTable();
		vp = new VerticalPanel();
		vp.setSpacing(5);

		flexTable.setText(1, 0, "Search a : ");
		lbType = new ListBox();
		lbType.addItem("loading...");
		lbType.setEnabled(false);

		flexTable.setWidget(1, 1, lbType);

		flexTable.setText(2, 0, "Name : ");
		tbName = new TextBox();
		flexTable.setWidget(2, 1, tbName);
		flexTable.setWidget(3, 1, new Button("Search",
				new SearchClickListener()));

		Fieldset f = new Fieldset("Search by element", flexTable);

		vp.add(f);
		vp.add(resultTable);
	}

	/**
	 * Display the layout
	 */
	public void display() {
		IndexModule.getSession().getUserPrivilege(
				new GetUserPrivilegeCallback());
		IndexModule.setCentralWidget(vp);
	}

	/**
	 * Set privilege, what kind of thing can you search
	 * 
	 * @param privileges
	 *            List<String>
	 */
	public void setPrivilege(List privileges) {
		lbType.clear();

		if (privileges.contains(EditRole.SEARCH_TAG)) {
			lbType.addItem("Tag");
		}

		if (privileges.contains(EditRole.SEARCH_READER)) {
			lbType.addItem("Reader");
		}

		if (privileges.contains(EditRole.SEARCH_GATEWAY)) {
			lbType.addItem("Gateway");
		}

		lbType.setEnabled(true);
	}

	//
	// Internal Callback class
	//
	private class GetUserPrivilegeCallback implements AsyncCallback {
		public void onFailure(Throwable caught) {
			IndexModule.getKikooBox().showError("Get user privilege failed",
					2000);
		}

		public void onSuccess(Object result) {
			setPrivilege((List) result);
		}
	}

	private class SearchClickListener implements ClickListener {
		public void onClick(Widget sender) {
			String type = lbType.getItemText(lbType.getSelectedIndex());
			if (type.compareTo("Tag") == 0) {
				IndexModule.getSession().searchTag(tbName.getText(),
						new SearchCallback());
			}

			if (type.compareTo("Reader") == 0) {
				IndexModule.getSession().searchReader(tbName.getText(),
						new SearchCallback());
			}

			if (type.compareTo("Gateway") == 0) {
				IndexModule.getSession().searchGateway(tbName.getText(),
						new SearchCallback());
			}
		}
	}

	private class SearchCallback implements AsyncCallback {
		public void onFailure(Throwable caught) {
			IndexModule.getKikooBox().showError("Search failed", 2000);
		}

		public void onSuccess(Object result) {
			List resultList = (List) result;
			IndexModule.getKikooBox().showOk("Search success", 1000);
			List list = new ArrayList();
			CustomTable.ListAndName listAndName = resultTable.new ListAndName(
					resultList, lbType.getItemText(lbType.getSelectedIndex()));
			list.add(listAndName);
			resultTable.setContent(list);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.ow2.aspirerfid.app.epcis.client.page.layout.PageLayout#getTitle()
	 */
	public String getTitle() {
		return "Search";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.ow2.aspirerfid.app.epcis.client.page.layout.PageLayout#getCategorie()
	 */
	public String getCategorie() {
		return "Search";
	}

}
