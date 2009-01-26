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
import org.ow2.aspirerfid.app.epcis.client.widget.warnings.ListWarnings;

import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

/**
 * TODO Javadoc
 * 
 * @author Unknown
 */
public class ManageWarningsLayout implements PageLayout {

	private final Fieldset f;
	private final ListWarnings listWarnings;

	/**
	 * TODO Javadoc
	 */
	public ManageWarningsLayout() {
		VerticalPanel vp = new VerticalPanel();

		// Content
		listWarnings = new ListWarnings(IndexModule.getSession());
		vp.add(listWarnings);

		Button addButton = new Button("Create temperature warning");
		addButton.addClickListener(new ClickListener() {
			public void onClick(Widget sender) {
				new TemperatureWarningLayout().display();
			}
		});
		addButton.setWidth("80%");

		Button addButton2 = new Button("Create lost warning");
		addButton2.addClickListener(new ClickListener() {
			public void onClick(Widget sender) {
				new LostObjectsLayout().display();
			}
		});
		addButton2.setWidth("80%");

		vp.add(new HTML("<hr>"));
		vp.add(addButton);
		vp.add(addButton2);
		vp.setCellHorizontalAlignment(addButton,
				HasHorizontalAlignment.ALIGN_CENTER);
		vp.setCellHorizontalAlignment(addButton2,
				HasHorizontalAlignment.ALIGN_CENTER);
		vp.setSpacing(5);
		f = new Fieldset("Warnings management", vp);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.ow2.aspirerfid.app.epcis.client.page.layout.PageLayout#display()
	 */
	public void display() {
		listWarnings.update();
		IndexModule.setCentralWidget(f);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.ow2.aspirerfid.app.epcis.client.page.layout.PageLayout#getTitle()
	 */
	public String getTitle() {
		return "Manage warnings";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.ow2.aspirerfid.app.epcis.client.page.layout.PageLayout#getCategorie()
	 */
	public String getCategorie() {
		return "Warning";
	}

}
