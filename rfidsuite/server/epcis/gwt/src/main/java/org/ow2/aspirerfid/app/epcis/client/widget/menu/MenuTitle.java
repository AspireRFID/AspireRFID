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

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.Widget;

/**
 * Menu title.
 * 
 * @author Guillaume Vaudaux-Ruth
 * @version 2007
 */
public class MenuTitle extends Composite {

	private Label title;
	private Panel panel;

	/**
	 * TODO Javadoc
	 * 
	 * @param menuTitle
	 * @param img
	 */
	public MenuTitle(String menuTitle, Image img) {
		panel = new HorizontalPanel();
		initWidget(panel);
		title = new Label(menuTitle);
		panel.setStyleName("menu-title");
		panel.add(img);
		panel.add(title);
	}

	/**
	 * TODO Javadoc
	 * 
	 * @return
	 */
	public Widget getMenuTitle() {
		return panel;
	}
}
