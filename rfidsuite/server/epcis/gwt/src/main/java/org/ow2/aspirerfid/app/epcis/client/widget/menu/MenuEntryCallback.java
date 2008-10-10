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

import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.MouseListener;
import com.google.gwt.user.client.ui.Widget;

/**
 * Menu Entry Callback.
 * 
 * @author Guillaume Vaudaux-Ruth
 * @version 2007
 */
public class MenuEntryCallback implements MouseListener, ClickListener {

	private MenuEntryCommand mc;

	private static Widget lastSelected;

	/**
	 * TODO Javadoc
	 * 
	 * @param mc
	 */
	public MenuEntryCallback(MenuEntryCommand mc) {
		this.mc = mc;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.google.gwt.user.client.ui.MouseListener#onMouseDown(com.google.gwt.user.client.ui.Widget,
	 *      int, int)
	 */
	public void onMouseDown(Widget sender, int x, int y) {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.google.gwt.user.client.ui.MouseListener#onMouseEnter(com.google.gwt.user.client.ui.Widget)
	 */
	public void onMouseEnter(Widget sender) {
		if (!sender.getStyleName().equals("menu-entry-selected")) {
			sender.setStyleName("menu-entry-over");
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.google.gwt.user.client.ui.MouseListener#onMouseLeave(com.google.gwt.user.client.ui.Widget)
	 */
	public void onMouseLeave(Widget sender) {
		if (!sender.getStyleName().equals("menu-entry-selected")) {
			sender.setStyleName("menu-entry");
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.google.gwt.user.client.ui.MouseListener#onMouseMove(com.google.gwt.user.client.ui.Widget,
	 *      int, int)
	 */
	public void onMouseMove(Widget sender, int x, int y) {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.google.gwt.user.client.ui.MouseListener#onMouseUp(com.google.gwt.user.client.ui.Widget,
	 *      int, int)
	 */
	public void onMouseUp(Widget sender, int x, int y) {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.google.gwt.user.client.ui.ClickListener#onClick(com.google.gwt.user.client.ui.Widget)
	 */
	public void onClick(Widget sender) {
		if (MenuEntryCallback.lastSelected != null) {
			MenuEntryCallback.lastSelected.setStyleName("menu-entry");
		}
		sender.setStyleName("menu-entry-selected");
		MenuEntryCallback.lastSelected = sender;
		if (mc != null)
			mc.command();
	}

}
