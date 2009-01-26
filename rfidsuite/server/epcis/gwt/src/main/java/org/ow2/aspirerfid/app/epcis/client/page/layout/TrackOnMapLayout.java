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
import org.ow2.aspirerfid.app.epcis.client.widget.gmap.GMapLegend;
import org.ow2.aspirerfid.app.epcis.client.widget.gmap.GMapMenu;
import org.ow2.aspirerfid.app.epcis.client.widget.table.ReportGroupListMemberTable;
import org.ow2.aspirerfid.app.epcis.client.widget.tags.TagInput;
import org.ow2.aspirerfid.app.epcis.client.widget.tags.TemperatureChart;

import com.google.gwt.user.client.ui.ChangeListener;
import com.google.gwt.user.client.ui.DockPanel;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TabPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.mapitz.gwt.googleMaps.client.GControl;
import com.mapitz.gwt.googleMaps.client.GLatLng;
import com.mapitz.gwt.googleMaps.client.GMap2;
import com.mapitz.gwt.googleMaps.client.GMap2Widget;
import com.mapitz.gwt.googleMaps.client.GMapType;

/**
 * Layout for the page : Track on map.
 * 
 * @author Guillaume Vaudaux-Ruth
 * @version 2007
 */
public class TrackOnMapLayout implements PageLayout {
	private static double PARIS_LAT = 48.898581;

	private static double PARIS_LNG = 2.438965;

	/**
	 * TODO Javadoc
	 */
	public static TemperatureChart chart;

	private ListBox lbSelectUnit;

	private TagInput tagInput;

	public static ReportGroupListMemberTable tableResult;

	/**
	 * TODO Javadoc
	 */
	public TrackOnMapLayout() {
		tableResult = new ReportGroupListMemberTable();
		tableResult.addField(ReportGroupListMemberTable.FIELD_LIST_POSITION,
				"Pos");
		tableResult.addField(ReportGroupListMemberTable.FIELD_GATEWAYNAME,
				"Gateway");
		tableResult.addField(ReportGroupListMemberTable.FIELD_READERNAME,
				"Reader");
		tableResult.addField(ReportGroupListMemberTable.FIELD_DATE, "Date");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.ow2.aspirerfid.app.epcis.client.page.layout.PageLayout#display()
	 */
	public void display() {

		Panel mainPanel = new VerticalPanel();
		mainPanel.setWidth("100%");

		TabPanel tabPanel = new TabPanel();
		tabPanel.setWidth("100%");

		// TRACKING
		// ///////////////////////////////////////////////////////////////
		DockPanel gmapAndOption = new DockPanel();

		// Google map
		int w = RootPanel.get().getOffsetWidth() - 360; // substract menu and
														// marging
		if (w < 600) {
			w = 600;
		}
		GMap2Widget gmapWidget = new GMap2Widget("350px", w + "px");
		gmapWidget.setStyleName("widget-GMap");

		GMap2 gmap = gmapWidget.getGmap();
		gmap.addMapType(GMapType.G_HYBRID_MAP());
		gmap.addMapType(GMapType.G_NORMAL_MAP());
		gmap.addMapType(GMapType.G_SATELLITE_MAP());
		gmap.addControl(GControl.GMapTypeControl());
		gmap.addControl(GControl.GOverviewMapControl());
		gmap.addControl(GControl.GSmallZoomControl());
		gmap.enableContinuousZoom();
		gmap.enableDoubleClickZoom();
		gmap.setCenter(new GLatLng(PARIS_LAT, PARIS_LNG), 3, GMapType
				.G_NORMAL_MAP());
		gmapAndOption.add(gmapWidget, DockPanel.CENTER);

		VerticalPanel vp = new VerticalPanel();
		GMapMenu menu = new GMapMenu(gmapWidget);
		vp.add(menu);
		vp.add(new GMapLegend());
		gmapAndOption.add(vp, DockPanel.EAST);

		tabPanel.add(gmapAndOption, "Tracking");

		// COMPLEMTARY INFORMATIONS
		// /////////////////////////////////////////////////////////////
		Panel pChart = new VerticalPanel();

		FlexTable flTable = new FlexTable();

		ListBox lbInfo = new ListBox(false);
		lbInfo.addItem("Temperature");

		flTable.setText(1, 1, "Informations : ");
		flTable.setWidget(1, 2, lbInfo);

		lbSelectUnit = new ListBox();
		lbSelectUnit.addItem("Celcius", "c");
		lbSelectUnit.addItem("Kelvin", "k");
		lbSelectUnit.setSelectedIndex(0);
		lbSelectUnit.addChangeListener(new UnitSelectorChangeListener());
		flTable.setText(2, 1, "Temperature unit : ");
		flTable.setWidget(2, 2, lbSelectUnit);

		// epc tag input
		tagInput = new TagInput(tableResult, gmapWidget, menu);

		chart = new TemperatureChart(TagInput.getTag(), lbSelectUnit
				.getValue(lbSelectUnit.getSelectedIndex()));

		pChart.add(flTable);
		pChart.add(chart);

		tabPanel.add(pChart, "Complementary information");
		tabPanel.selectTab(0);
		mainPanel.add(tagInput);
		mainPanel.add(tabPanel);
		mainPanel.add(tableResult);

		IndexModule.setCentralWidget(mainPanel);
	}

	private class UnitSelectorChangeListener implements ChangeListener {
		public void onChange(Widget sender) {
			TagInput.setUnit(lbSelectUnit.getValue(lbSelectUnit
					.getSelectedIndex()));
			chart.setTag(TagInput.getTag(), TagInput.getUnit());
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.ow2.aspirerfid.app.epcis.client.page.layout.PageLayout#getTitle()
	 */
	public String getTitle() {
		return "Track on map";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.ow2.aspirerfid.app.epcis.client.page.layout.PageLayout#getCategorie()
	 */
	public String getCategorie() {
		return "Tag";
	}
}
