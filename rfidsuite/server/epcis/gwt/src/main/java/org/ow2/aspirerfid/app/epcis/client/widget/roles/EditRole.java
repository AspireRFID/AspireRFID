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

import org.ow2.aspirerfid.app.epcis.client.IndexModule;
import org.ow2.aspirerfid.app.epcis.client.widget.Fieldset;
import org.ow2.aspirerfid.app.epcis.client.widget.TweakedCheckBox;
import org.ow2.aspirerfid.app.epcis.client.widget.roles.callback.EditRoleCallbackCheckBox;
import org.ow2.aspirerfid.app.epcis.client.widget.roles.callback.EditRoleCallbackSaveButton;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import asquare.gwt.tk.client.ui.ModalDialog;

import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.HorizontalSplitPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

/**
 * Edit role widget.
 * 
 * @author Guillaume Vaudaux-Ruth
 * @version 2007
 */
public class EditRole extends ModalDialog {
	//
	// Constants
	//
	/**
	 * TODO Javadoc
	 */
	public static final String READERS_CAT = "Readers";
	/**
	 * TODO Javadoc
	 */
	public static final String TAG_CAT = "Tags";
	/**
	 * TODO Javadoc
	 */
	public static final String SEARCH_CAT = "Search";
	/**
	 * TODO Javadoc
	 */
	public static final String ADMIN_CAT = "Administration";
	/**
	 * TODO Javadoc
	 */
	public static final String ARCHI_CAT = "Architecture";
	/**
	 * TODO Javadoc
	 */
	public static final String WARN_CAT = "Warnings";
	/**
	 * TODO Javadoc
	 */
	public static final String READERS_NUMBER_OF_TAGS = "READERS_NUMBER_OF_TAGS";
	/**
	 * TODO Javadoc
	 */
	public static final String READERS_LIST_TAGS = "READERS_LIST_TAGS";
	/**
	 * TODO Javadoc
	 */
	public static final String TAGS_TRACK_ON_MAP = "TAGS_TRACK_ON_MAP";
	/**
	 * TODO Javadoc
	 */
	public static final String SEARCH_TAG = "SEARCH_TAG";
	/**
	 * TODO Javadoc
	 */
	public static final String SEARCH_READER = "SEARCH_READER";
	/**
	 * TODO Javadoc
	 */
	public static final String SEARCH_GATEWAY = "SEARCH_GATEWAY";
	/**
	 * TODO Javadoc
	 */
	public static final String ADMIN_MANAGE_USERS = "ADMIN_MANAGE_USERS";
	/**
	 * TODO Javadoc
	 */
	public static final String ADMIN_LIST_USERS = "ADMIN_LIST_USERS";
	/**
	 * TODO Javadoc
	 */
	public static final String ADMIN_MANAGE_ROLES = "ADMIN_MANAGE_ROLES";
	/**
	 * TODO Javadoc
	 */
	public static final String ARCHI_SEE_NETWORK_TOPOLOGY = "ARCHI_SEE_NETWORK_TOPOLOGY";
	/**
	 * TODO Javadoc
	 */
	public static final String WARN_LIST_WARNINGS = "WARN_LIST_WARNINGS";
	/**
	 * TODO Javadoc
	 */
	public static final String WARN_LIST_OF_LOST_OBJECTS = "WARN_LIST_OF_LOST_OBJECTS";
	/**
	 * TODO Javadoc
	 */
	public static final String WARN_TEMPERATURE = "WARN_TEMPERATURE";
	/**
	 * TODO Javadoc
	 */
	public static final String WARN_MONITORING = "WARN_MONITORING";
	/**
	 * TODO Javadoc
	 */
	public static final String SEARCH_TEMPERATURE = "SEARCH_TEMPERATURE";

	//
	// Internals variables
	//
	private RoleGWT role;

	private VerticalPanel vp;

	private List nameToInfo;

	private TextBox tbUserRole;

	private ListRoles listRoles;

	//
	// Internal class
	//
	public class Info {
		public boolean privilege;
		/**
		 * TODO Javadoc
		 */
		public String menuName;
		/**
		 * TODO Javadoc
		 */
		public String categorie;
		/**
		 * TODO Javadoc
		 */
		public String name;
		/**
		 * TODO Javadoc
		 */
		public TweakedCheckBox checkBox;

		/**
		 * TODO Javadoc
		 * 
		 * @param r
		 * @param mn
		 * @param cat
		 * @param n
		 */
		public Info(RoleGWT r, String mn, String cat, String n) {
			checkBox = new TweakedCheckBox();
			menuName = mn;
			categorie = cat;
			name = n;

			if (r != null) {
				privilege = r.privileges.contains(n);
			}
		}
	}

	//
	// Methods
	//
	/**
	 * TODO Javadoc
	 * 
	 * @param listRoles
	 */
	public EditRole(ListRoles listRoles) {
		this.listRoles = listRoles;
		this.role = new RoleGWT(-1, "", new ArrayList(), true);
		init();
	}

	/**
	 * TODO Javadoc
	 * 
	 * @param role
	 * @param listRoles
	 */
	public EditRole(RoleGWT role, ListRoles listRoles) {
		this.listRoles = listRoles;
		this.role = role;
		init();
	}

	/**
	 * TODO Javadoc
	 */
	public void display() {

		// User role
		HorizontalPanel panelUserRole = new HorizontalPanel();
		Label userRoleLabel = new Label("User role name : ");
		vp.add(userRoleLabel);

		tbUserRole = new TextBox();
		if (role != null) {
			tbUserRole.setText(role.name);
		}

		panelUserRole.add(userRoleLabel);
		panelUserRole.add(tbUserRole);
		vp.add(panelUserRole);

		Map CatToPanel = new HashMap();
		for (Iterator iter = nameToInfo.iterator(); iter.hasNext();) {
			Info element = (Info) iter.next();
			add(element, CatToPanel);
		}

		HorizontalSplitPanel hsp = new HorizontalSplitPanel();
		VerticalPanel vpCol1 = new VerticalPanel();
		displayCat(READERS_CAT, CatToPanel, vpCol1);
		displayCat(TAG_CAT, CatToPanel, vpCol1);
		displayCat(ADMIN_CAT, CatToPanel, vpCol1);
		VerticalPanel vpCol2 = new VerticalPanel();
		displayCat(ARCHI_CAT, CatToPanel, vpCol2);
		displayCat(SEARCH_CAT, CatToPanel, vpCol2);
		displayCat(WARN_CAT, CatToPanel, vpCol2);

		hsp.setLeftWidget(vpCol1);
		hsp.setRightWidget(vpCol2);
		hsp.setSplitPosition("50%");

		Fieldset fieldset = new Fieldset("", hsp);

		vp.add(fieldset);

		HorizontalPanel hp = new HorizontalPanel();
		Button btSave = new Button("Save");
		btSave.addClickListener(new EditRoleCallbackSaveButton(this,
				IndexModule.getSession(), listRoles));
		hp.add(btSave);

		Button btCancel = new Button("Cancel");
		btCancel.addClickListener(new ClickListener() {
			public void onClick(Widget sender) {
				hide();
			}
		});
		hp.add(btCancel);
		vp.add(hp);
		vp.setCellHorizontalAlignment(hp, VerticalPanel.ALIGN_RIGHT);
		vp.setSpacing(5);

		this.setCaption("Modify role", false);
	}

	/**
	 * TODO Javadoc
	 */
	public void update() {
		defineNameToInfo(role);
		vp.clear();
		display();
	}

	/**
	 * TODO Javadoc
	 * 
	 * @return
	 */
	public RoleGWT getRole() {
		role.privileges.clear();
		for (int i = 0; i < nameToInfo.size(); i++) {
			Info info = (Info) nameToInfo.get(i);

			if (info.privilege) {
				role.privileges.add(info.name);
			}
		}

		role.name = tbUserRole.getText();

		return role;
	}

	/**
	 * TODO Javadoc
	 * 
	 * @param role
	 */
	public void setRole(RoleGWT role) {
		this.role = role;
		update();
	}

	/**
	 * TODO Javadoc
	 */
	private void init() {
		vp = new VerticalPanel();

		this.add(vp);

		nameToInfo = new ArrayList();
		defineNameToInfo(null);
		display();
	}

	/**
	 * TODO Javadoc
	 * 
	 * @param r
	 */
	private void defineNameToInfo(RoleGWT r) {
		nameToInfo.clear();
		nameToInfo.add(new Info(r, "Number of tags", READERS_CAT,
				READERS_NUMBER_OF_TAGS));
		nameToInfo
				.add(new Info(r, "List tags", READERS_CAT, READERS_LIST_TAGS));
		nameToInfo.add(new Info(r, "Track on map", TAG_CAT, TAGS_TRACK_ON_MAP));
		nameToInfo.add(new Info(r, "Search tag", SEARCH_CAT, SEARCH_TAG));
		nameToInfo.add(new Info(r, "Search reader", SEARCH_CAT, SEARCH_READER));
		nameToInfo
				.add(new Info(r, "Search gateway", SEARCH_CAT, SEARCH_GATEWAY));
		nameToInfo.add(new Info(r, "Search by temperature", SEARCH_CAT,
				SEARCH_TEMPERATURE));
		nameToInfo.add(new Info(r, "Manage users", ADMIN_CAT,
				ADMIN_MANAGE_USERS));
		nameToInfo.add(new Info(r, "List users", ADMIN_CAT, ADMIN_LIST_USERS));
		nameToInfo.add(new Info(r, "Manage roles", ADMIN_CAT,
				ADMIN_MANAGE_ROLES));
		nameToInfo.add(new Info(r, "See network topology", ARCHI_CAT,
				ARCHI_SEE_NETWORK_TOPOLOGY));
		nameToInfo.add(new Info(r, "Manage warnings", WARN_CAT,
				WARN_LIST_WARNINGS));
		nameToInfo.add(new Info(r, "Warning temperature", WARN_CAT,
				WARN_TEMPERATURE));
		nameToInfo.add(new Info(r, "List of lost objects", WARN_CAT,
				WARN_LIST_OF_LOST_OBJECTS));
		nameToInfo.add(new Info(r, "Monitor warnings", WARN_CAT,
				WARN_MONITORING));
	}

	/**
	 * TODO Javadoc
	 * 
	 * @param cat
	 * @param m
	 * @param p
	 */
	private void displayCat(String cat, Map m, Panel p) {
		Label title = new Label(cat);
		title.setStyleName("label-h2");
		p.add(title);
		p.add((Panel) m.get(cat));
		p.add(new HTML("<br />"));
	}

	/**
	 * TODO Javadoc
	 * 
	 * @param o
	 * @param m
	 */
	private void add(Info o, Map m) {
		o.checkBox.setText(o.menuName);
		o.checkBox.setChecked(o.privilege);
		o.checkBox.addClickListener(new EditRoleCallbackCheckBox(o));

		if (m.containsKey(o.categorie)) {
			((Panel) m.get(o.categorie)).add(o.checkBox);
		} else {
			Panel p = new VerticalPanel();
			p.add(o.checkBox);
			m.put(o.categorie, p);
		}
	}

}
