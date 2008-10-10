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
package org.ow2.aspirerfid.app.epcis.client.widget.menu;

import org.ow2.aspirerfid.app.epcis.client.RemoteAdminViewAsync;
import org.ow2.aspirerfid.app.epcis.client.page.layout.ListTagLayout;
import org.ow2.aspirerfid.app.epcis.client.page.layout.LostObjectsLayout;
import org.ow2.aspirerfid.app.epcis.client.page.layout.ManageRolesLayout;
import org.ow2.aspirerfid.app.epcis.client.page.layout.ManageUsersLayout;
import org.ow2.aspirerfid.app.epcis.client.page.layout.ManageWarningsLayout;
import org.ow2.aspirerfid.app.epcis.client.page.layout.MonitorWarningsLayout;
import org.ow2.aspirerfid.app.epcis.client.page.layout.NetworkTopologyLayout;
import org.ow2.aspirerfid.app.epcis.client.page.layout.SearchLayout;
import org.ow2.aspirerfid.app.epcis.client.page.layout.SearchTemperatureLayout;
import org.ow2.aspirerfid.app.epcis.client.page.layout.TemperatureWarningLayout;
import org.ow2.aspirerfid.app.epcis.client.page.layout.TrackOnMapLayout;
import org.ow2.aspirerfid.app.epcis.client.util.Util;
import org.ow2.aspirerfid.app.epcis.client.widget.menu.commands.MenuEntryCommandChangeLayout;
import org.ow2.aspirerfid.app.epcis.client.widget.roles.EditRole;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.VerticalPanel;

/**
 * Menu Widget.
 * 
 * @author Guillaume Vaudaux-Ruth
 * @version 2007
 */
public class Menu extends Composite implements AsyncCallback {

	private static final String READERS_TOPIC_IMG = "img/reader.png";
	private static final String TAGS_TOPIC_IMG = "img/tag.png";
	private static final String SEARCH_TOPIC_IMG = "img/search.png";
	private static final String ADMIN_TOPIC_IMG = "img/admin.png";
	private static final String ARCHI_TOPIC_IMG = "img/network.gif";
	private static final String WARNINGS_TOPIC_IMG = "img/warning.png";

	/**
	 * Widget main panel
	 */
	private VerticalPanel panel;

	/**
	 * Constructor
	 * 
	 * @param r
	 *            remote session
	 */
	public Menu(RemoteAdminViewAsync r) {
		panel = new VerticalPanel();
		panel.setStyleName("menu");
		panel.setSpacing(3);
		initWidget(panel);
		LoadingMessage();
		r.getUserPrivilege(this);
	}

	/**
	 * Call to the remote session success
	 */
	public void onSuccess(Object result) {
		Display((List) result);
	}

	/**
	 * Call to the remote session failed
	 */
	public void onFailure(Throwable caught) {
		Util.defaultOnFailure(caught);
	}

	/**
	 * Define loading message
	 * 
	 */
	private void LoadingMessage() {
		panel.add(Util.defaultLoadingMessage());
	}

	/**
	 * Display the widget
	 * 
	 * @param privileges
	 *            list of String
	 * 
	 */
	private void Display(List privileges) {
		// remove loading msg
		panel.clear();
		List listMenuEntry = new ArrayList();

		//
		// Categorie : Tags
		//
		if (privileges.contains(EditRole.TAGS_TRACK_ON_MAP)) {
			listMenuEntry.add(new MenuEntry("Track on map",
					new MenuEntryCommandChangeLayout(new TrackOnMapLayout())));
		}

		if (listMenuEntry.size() > 0) {
			panel.add(new MenuTitle("Tags", new Image(TAGS_TOPIC_IMG)));
			for (Iterator iter = listMenuEntry.iterator(); iter.hasNext();) {
				MenuEntry entry = (MenuEntry) iter.next();
				panel.add(entry);
			}
		}

		//
		// Categorie : Readers
		//
		listMenuEntry.clear();
		if (privileges.contains(EditRole.READERS_NUMBER_OF_TAGS)
				|| privileges.contains(EditRole.READERS_LIST_TAGS)) {
			listMenuEntry.add(new MenuEntry("List of tags",
					new MenuEntryCommandChangeLayout(new ListTagLayout(
							privileges
									.contains(EditRole.READERS_NUMBER_OF_TAGS),
							privileges.contains("READERS_LIST_TAGS")))));
		}

		if (listMenuEntry.size() > 0) {
			panel.add(new MenuTitle("Readers", new Image(READERS_TOPIC_IMG)));
			for (Iterator iter = listMenuEntry.iterator(); iter.hasNext();) {
				MenuEntry entry = (MenuEntry) iter.next();
				panel.add(entry);
			}
		}

		//
		// Categorie : Search
		//
		listMenuEntry.clear();
		if (privileges.contains("SEARCH_TAG")
				|| privileges.contains("SEARCH_READER")
				|| privileges.contains("SEARCH_GATEWAY")
				|| privileges.contains("SEARCH_PREMISE")) {
			listMenuEntry.add(new MenuEntry("Search by element",
					new MenuEntryCommandChangeLayout(new SearchLayout())));
		}

		if (privileges.contains(EditRole.SEARCH_TEMPERATURE)) {
			listMenuEntry.add(new MenuEntry("Search by temperature",
					new MenuEntryCommandChangeLayout(
							new SearchTemperatureLayout())));
		}

		if (listMenuEntry.size() > 0) {
			panel.add(new MenuTitle("Search", new Image(SEARCH_TOPIC_IMG)));
			for (Iterator iter = listMenuEntry.iterator(); iter.hasNext();) {
				MenuEntry entry = (MenuEntry) iter.next();
				panel.add(entry);
			}
		}

		//
		// ARCHITECTURE
		//
		listMenuEntry.clear();
		if (privileges.contains(EditRole.ARCHI_SEE_NETWORK_TOPOLOGY)) {
			listMenuEntry.add(new MenuEntry("See network topology",
					new MenuEntryCommandChangeLayout(
							new NetworkTopologyLayout())));
		}

		if (listMenuEntry.size() > 0) {
			panel
					.add(new MenuTitle("Architecture", new Image(
							ARCHI_TOPIC_IMG)));
			for (Iterator iter = listMenuEntry.iterator(); iter.hasNext();) {
				MenuEntry entry = (MenuEntry) iter.next();
				panel.add(entry);
			}
		}

		//
		// WARNINGS
		//
		listMenuEntry.clear();

		if (privileges.contains(EditRole.WARN_LIST_OF_LOST_OBJECTS)) {
			listMenuEntry.add(new MenuEntry("Create lost warning",
					new MenuEntryCommandChangeLayout(new LostObjectsLayout())));
		}

		if (privileges.contains(EditRole.WARN_TEMPERATURE)) {
			listMenuEntry.add(new MenuEntry("Create temperature warning",
					new MenuEntryCommandChangeLayout(
							new TemperatureWarningLayout())));
		}

		if (privileges.contains(EditRole.WARN_LIST_WARNINGS)) {
			listMenuEntry
					.add(new MenuEntry("Manage saved warnings",
							new MenuEntryCommandChangeLayout(
									new ManageWarningsLayout())));
		}

		if (privileges.contains(EditRole.WARN_MONITORING)) {
			listMenuEntry.add(new MenuEntry("Monitor saved warnings",
					new MenuEntryCommandChangeLayout(
							new MonitorWarningsLayout())));
		}

		if (listMenuEntry.size() > 0) {
			panel.add(new MenuTitle("Warning", new Image(WARNINGS_TOPIC_IMG)));
			for (Iterator iter = listMenuEntry.iterator(); iter.hasNext();) {
				MenuEntry entry = (MenuEntry) iter.next();
				panel.add(entry);
			}
		}

		//
		// ADMIN
		//
		listMenuEntry.clear();
		if (privileges.contains(EditRole.ADMIN_LIST_USERS)) {
			if (privileges.contains(EditRole.ADMIN_MANAGE_USERS)) {
				listMenuEntry.add(new MenuEntry("Manage users",
						new MenuEntryCommandChangeLayout(new ManageUsersLayout(
								true))));
			} else {
				listMenuEntry.add(new MenuEntry("List users",
						new MenuEntryCommandChangeLayout(new ManageUsersLayout(
								false))));
			}
		} else if (privileges.contains(EditRole.ADMIN_MANAGE_USERS)) {
			listMenuEntry.add(new MenuEntry("Manage users",
					new MenuEntryCommandChangeLayout(
							new ManageUsersLayout(true))));
		}

		if (privileges.contains(EditRole.ADMIN_MANAGE_ROLES)) {
			listMenuEntry.add(new MenuEntry("Manage roles",
					new MenuEntryCommandChangeLayout(new ManageRolesLayout())));
		}

		if (listMenuEntry.size() > 0) {
			panel.add(new MenuTitle("Admin", new Image(ADMIN_TOPIC_IMG)));
			for (Iterator iter = listMenuEntry.iterator(); iter.hasNext();) {
				MenuEntry entry = (MenuEntry) iter.next();
				panel.add(entry);
			}
		}
	}
}
