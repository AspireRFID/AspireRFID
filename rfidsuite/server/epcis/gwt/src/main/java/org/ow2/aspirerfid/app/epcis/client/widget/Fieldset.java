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
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

/**
 * Fieldset-like widget.
 * 
 * @author Guillaume Vaudaux-Ruth
 * @version 2007
 */
public class Fieldset extends Composite {

	private VerticalPanel panel;

	private String title;

	private Widget w;

	/**
	 * Constructor
	 * 
	 * @param title
	 *            title of fieldset
	 * @param w
	 *            widget contain into fieldset
	 */
	public Fieldset(String title, Widget w) {
		this.title = title;
		this.w = w;
		panel = new VerticalPanel();
		initWidget(panel);
		Display();
	}

	/**
	 * Display widget
	 * 
	 */
	public void Display() {
		Label labelTitle = new Label(title);
		labelTitle.setStyleName("fieldset-title");
		panel.add(labelTitle);
		VerticalPanel p = new VerticalPanel();
		p.setBorderWidth(0);
		p.setStyleName("fieldset-content");
		p.add(w);
		panel.add(p);
	}

}
