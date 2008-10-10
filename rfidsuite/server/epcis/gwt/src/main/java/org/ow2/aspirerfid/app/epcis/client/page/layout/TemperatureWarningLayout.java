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
import org.ow2.aspirerfid.app.epcis.client.widget.IntervalSelector;
import org.ow2.aspirerfid.app.epcis.client.widget.table.ReportGroupListMemberTable;
import org.ow2.aspirerfid.app.epcis.client.widget.warnings.WarningTemp;

import asquare.gwt.tk.client.util.DomUtil;

import com.google.gwt.user.client.ui.VerticalPanel;

/**
 * TODO Javadoc
 * 
 * @author Guillaume Surrel
 * @version 2007
 */
public class TemperatureWarningLayout implements PageLayout {

	private VerticalPanel mainVp;

	private IntervalSelector intervalSelector;

	private WarningTemp wl;

	/**
	 * TODO Javadoc
	 */
	public TemperatureWarningLayout() {
		ReportGroupListMemberTable table = new ReportGroupListMemberTable();
		table.addField(ReportGroupListMemberTable.FIELD_TAG, "Tag");
		table.addField(ReportGroupListMemberTable.FIELD_TEMPERATURE, "Temp");
		table.addField(ReportGroupListMemberTable.FIELD_DATE, "Date");

		// Content
		intervalSelector = new IntervalSelector();
		wl = new WarningTemp(table, intervalSelector, true);
		intervalSelector.setLiveResultListener(wl);

		VerticalPanel vp = new VerticalPanel();
		DomUtil.setAttribute(vp, "id", "dropDown-left");
		vp.setSpacing(5);
		vp.add(wl);

		Fieldset f = new Fieldset("Temperature warning", vp);

		mainVp = new VerticalPanel();
		mainVp.setWidth("100%");
		mainVp.setSpacing(10);
		mainVp.add(f);
		mainVp.add(table);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.ow2.aspirerfid.app.epcis.client.page.layout.PageLayout#display()
	 */
	public void display() {
		wl.update();
		IndexModule.setCentralWidget(mainVp);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.ow2.aspirerfid.app.epcis.client.page.layout.PageLayout#getTitle()
	 */
	public String getTitle() {
		return "Temperature";
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
