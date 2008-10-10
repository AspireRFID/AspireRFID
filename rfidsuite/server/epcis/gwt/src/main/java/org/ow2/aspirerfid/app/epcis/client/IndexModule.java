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
package org.ow2.aspirerfid.app.epcis.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.ServiceDefTarget;
import com.google.gwt.user.client.ui.DockPanel;
import com.google.gwt.user.client.ui.HorizontalSplitPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Widget;

import org.ow2.aspirerfid.app.epcis.client.util.Util;
import org.ow2.aspirerfid.app.epcis.client.widget.AuthWidget;
import org.ow2.aspirerfid.app.epcis.client.widget.Banner;
import org.ow2.aspirerfid.app.epcis.client.widget.kikoobox.KikooBox;
import org.ow2.aspirerfid.app.epcis.client.widget.menu.Menu;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 * 
 * @author Guillaume Vaudaux-Ruth
 * @version 2007
 */
public class IndexModule implements EntryPoint {
	private static RemoteAdminViewAsync session;

	private static KikooBox kikooBox;

	private static Panel mainPanel;

	private AuthWidget auth;

	private static Banner banner;

	/**
	 * TODO Javadoc
	 * 
	 * @return
	 */
	public static KikooBox getKikooBox() {
		return kikooBox;
	}

	/**
	 * TODO Javadoc
	 * 
	 * @return
	 */
	public static RemoteAdminViewAsync getSession() {
		return session;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.google.gwt.core.client.EntryPoint#onModuleLoad()
	 */
	public void onModuleLoad() {
		session = (RemoteAdminViewAsync) GWT.create(RemoteAdminView.class);

		ServiceDefTarget endpoint = (ServiceDefTarget) session;
		String moduleRelativeURL = GWT.getModuleBaseURL() + "/RemoteAdminView";
		endpoint.setServiceEntryPoint(moduleRelativeURL);

		AsyncCallback callback = new AsyncCallback() {
			public void onSuccess(Object result) {
				// do some UI stuff to show success
				if (((Boolean) result).booleanValue()) {
					System.out.println("auth ok");
					Display();
				} else {
					System.out.println("auth failed");
					RootPanel.get("content").clear();
					RootPanel.get("content").add(auth);
					kikooBox.showError("Authentification failed", 3000);
					getSession().initDB(null);
				}
			}

			public void onFailure(Throwable caught) {
				Util.defaultOnFailure(caught);
			}
		};

		// Kikoo box
		kikooBox = new KikooBox();
		RootPanel.get("kikoo").setHeight("0px");
		RootPanel.get("kikoo").add(kikooBox);

		auth = new AuthWidget(callback);
		RootPanel.get("content").add(auth);
	}

	/**
	 * Display the index page
	 * 
	 */
	private void Display() {
		// Header
		banner = new Banner("first page", auth.getLogin(), "Welcome");
		RootPanel.get("header").add(banner);

		// Menu
		Menu menu = new Menu(session);

		// Content
		DockPanel p = new DockPanel();
		mainPanel = new SimplePanel();
		mainPanel.setWidth("100%");

		// welcome message
		Image img = new Image("img/logo.png");
		p.add(img, DockPanel.CENTER);
		p.setCellHorizontalAlignment(img, DockPanel.ALIGN_CENTER);
		Label l = new Label("Select a function in side menu.");
		p.add(l, DockPanel.SOUTH);
		p.setCellHorizontalAlignment(l, DockPanel.ALIGN_CENTER);
		p.setWidth("100%");
		RootPanel.get("content").clear();

		mainPanel.add(p);
		HorizontalSplitPanel hsp = new HorizontalSplitPanel();
		hsp.setLeftWidget(menu);
		hsp.setRightWidget(mainPanel);
		hsp.setSplitPosition("180px");

		RootPanel.get("content").add(hsp);
	}

	/**
	 * Get banner
	 * 
	 * @return
	 */
	public static Banner getBanner() {
		return banner;
	}

	/**
	 * TODO Javadoc
	 * 
	 * @return
	 */
	public static Panel getMainPanel() {
		return mainPanel;
	}

	/**
	 * TODO Javadoc
	 * 
	 * @param newCentralWidget
	 */
	public static void setCentralWidget(Widget newCentralWidget) {
		mainPanel.clear();
		mainPanel.add(newCentralWidget);
	}

	/**
	 * TODO Javadoc
	 */
	public void reset() {
		session = null;
		kikooBox = null;
		mainPanel = null;
		banner = null;

		RootPanel.get("header").clear();
		RootPanel.get("content").clear();
		RootPanel.get("kikoo").clear();
		RootPanel.get().clear();
	}
}
