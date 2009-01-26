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
package org.ow2.aspirerfid.app.epcis.client.widget.gmap;

import org.ow2.aspirerfid.app.epcis.client.widget.tags.ReportGroupListMemberGWT;

import java.util.Date;
import java.util.List;

import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.mapitz.gwt.googleMaps.client.GInfoWindowTab;

/**
 * TODO Javadoc
 * 
 * @author Unknown
 */
public class InfoWindowTab {

	private List /* <ReportGroupListMemberGWT> */reports;

	private GInfoWindowTab[] tabs;

	private static final int MAX_TAG_PER_TAB = 3;

	/**
	 * 
	 * List<ReportGroupListMemberGWT> reports
	 */
	public InfoWindowTab(List reports) {
		this.reports = reports;
		tabs = new GInfoWindowTab[getNbTab()];
		buildTab();
	}

	private int getNbTab() {
		double exactNbTab = ((double) reports.size())
				/ ((double) MAX_TAG_PER_TAB);
		return (int) Math.ceil(exactNbTab);
	}

	private void buildTab() {
		int size = reports.size();
		int nbTab = tabs.length;
		for (int t = 0; t < nbTab; t++) {
			Panel vp = new VerticalPanel();

			if (nbTab > 2) {
				vp.setWidth("100%");
			}

			boolean lastElement = false;
			for (int i = t * MAX_TAG_PER_TAB; i < (t + 1) * MAX_TAG_PER_TAB; i++) {
				if (i < size) {
					ReportGroupListMemberGWT report = (ReportGroupListMemberGWT) reports
							.get(i);

					vp.add(new HTML("<font size=1><b>Temperature</b> : "
							+ report.getTemperature() + " K&#176;</font>"));
					vp.add(new HTML("<font size=1><b>GPS</b> : "
							+ report.getGpsCoordinate()));
					vp.add(new HTML("<font size=1><b>Gateway</b> : "
							+ report.getGatewayName() + "</font>"));
					vp.add(new HTML("<font size=1><b>Reader</b> : "
							+ report.getReaderName() + "</font>"));
					Date d = report.getMemberDate();
					vp.add(new HTML("<font size=1><b>Date</b> : "
							+ d.toLocaleString() + "</font>"));

					lastElement = ((t + 1) * MAX_TAG_PER_TAB == i + 1);
					if (!lastElement) {
						vp
								.add(new HTML(
										"<hr noshade height=\"1px\" width=\"45px\" align=\"left\"/>"));
					}
				}
			}

			tabs[t] = new GInfoWindowTab(new Integer(t).toString(), vp);
		}
	}

	/**
	 * TODO Javadoc
	 * 
	 * @return
	 */
	public GInfoWindowTab[] getTabs() {
		return tabs;
	}
}
