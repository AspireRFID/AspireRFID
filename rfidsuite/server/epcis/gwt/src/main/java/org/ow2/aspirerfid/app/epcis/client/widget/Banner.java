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
package org.ow2.aspirerfid.app.epcis.client.widget;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DockPanel;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;

/**
 * TODO Javadoc
 * 
 * @author Unknown
 */
public class Banner extends Composite {

	private Label cat;

	private Label title;

	private DockPanel panel;

	private Label login;

	private Label separator;

	/**
	 * TODO Javadoc
	 * 
	 * @param title
	 * @param login
	 * @param cat
	 */
	public Banner(String title, String login, String cat) {
		this.title = new Label(title);
		this.cat = new Label(cat);
		this.login = new Label(login);
		panel = new DockPanel();
		panel.setStyleName("widget-Banner");
		separator = new Label(" > ");
		separator.setStyleName("widget-Banner-Separator");
		initWidget(panel);
		display();

	}

	/**
	 * TODO Javadoc
	 */
	public void display() {
		panel.clear();

		Image img = new Image("img/logo_rfid.gif");
		VerticalPanel vp = new VerticalPanel();
		HorizontalPanel loginAndExit = new HorizontalPanel();
		loginAndExit.add(login);
		loginAndExit.add(new Disconnect());
		vp.add(loginAndExit);
		HorizontalPanel hp = new HorizontalPanel();
		hp.add(cat);
		hp.add(separator);
		hp.add(title);
		vp.add(hp);
		vp.setCellHorizontalAlignment(loginAndExit, VerticalPanel.ALIGN_RIGHT);
		vp.setCellHorizontalAlignment(title, VerticalPanel.ALIGN_RIGHT);
		panel.add(img, DockPanel.WEST);
		panel.add(new Reidid(), DockPanel.CENTER);
		panel.add(vp, DockPanel.EAST);
		panel.setCellHorizontalAlignment(img, DockPanel.ALIGN_LEFT);
		panel.setCellHorizontalAlignment(vp, DockPanel.ALIGN_RIGHT);
	}

	/**
	 * TODO Javadoc
	 * 
	 * @param title
	 * @param cat
	 */
	public void setTitle(String title, String cat) {
		this.title.setText(title);
		this.cat.setText(cat);
	}
}
