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

import org.ow2.aspirerfid.app.epcis.client.IndexModule;

import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Widget;

public class Disconnect extends Composite {

	private Panel panel;
	private Image close;

	/**
	 * TODO Javadoc
	 */
	public Disconnect() {
		panel = new SimplePanel();
		close = new Image("img/close2.png");
		close.addStyleName("clickable");
		close.addClickListener(new CloseClickListener());
		panel.add(close);
		initWidget(panel);

	}

	private class CloseClickListener implements ClickListener {
		public void onClick(Widget arg0) {
			// IndexModule.getSession();

			IndexModule index = new IndexModule();
			index.reset();
			index.onModuleLoad();
		}
	}
}
