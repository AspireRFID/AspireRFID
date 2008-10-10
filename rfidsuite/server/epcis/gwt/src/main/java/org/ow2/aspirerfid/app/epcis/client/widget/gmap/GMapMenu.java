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

import org.ow2.aspirerfid.app.epcis.client.widget.TweakedCheckBox;
import org.ow2.aspirerfid.app.epcis.client.widget.tags.callback.CenterClickListener;
import org.ow2.aspirerfid.app.epcis.client.widget.tags.callback.ClearMapClickListener;
import org.ow2.aspirerfid.app.epcis.client.widget.tags.callback.TrackClickListener;

import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.mapitz.gwt.googleMaps.client.GMap2Widget;

/**
 * TODO Javadoc
 * 
 * @author Unknown
 * 
 */
public class GMapMenu extends Composite {

	private Panel panel;

	private static String CLEAR_IMG = "img/broom.png";

	private static String ZOOM_IMG = "img/zoom2.png";

	private static String DISPLAY_IMG = "img/display.png";

	private GMap2Widget gmapWidget;

	private TweakedCheckBox cbPath;

	private TweakedCheckBox cbMarker;

	private TweakedCheckBox cbPartner;

	/**
	 * TODO Javadoc
	 * 
	 * @param gmapWidget
	 */
	public GMapMenu(GMap2Widget gmapWidget) {
		this.gmapWidget = gmapWidget;
		panel = new VerticalPanel();
		panel.setWidth("100px");
		initWidget(panel);
		setStyleName("widget-GMapMenu");
		display();
	}

	/**
	 * TODO Javadoc
	 */
	public void display() {
		Label lInfo = new Label("Information :");
		lInfo.setStyleName("widget-GMapMenu-title");

		cbPath = new TweakedCheckBox();
		cbPath.setText("Path");
		cbPath.setChecked(true);
		cbPath.addLabelStyleName("widget-TweakedCheckBox-Label");
		cbMarker = new TweakedCheckBox();
		cbMarker.setText("Marker");
		cbMarker.setChecked(true);
		cbMarker.addLabelStyleName("widget-TweakedCheckBox-Label");
		cbPartner = new TweakedCheckBox();
		cbPartner.setText("Partner information");
		cbPartner.setChecked(false);
		cbPartner.addLabelStyleName("widget-TweakedCheckBox-Label");

		Button btDisplay = new Button("<img src='" + DISPLAY_IMG
				+ "' /> Display");
		btDisplay.addClickListener(new TrackClickListener(gmapWidget, cbPath,
				cbMarker, cbPartner));

		Button btZoom = new Button("<img src='" + ZOOM_IMG + "' /> Focus");
		btZoom.addClickListener(new CenterClickListener(gmapWidget));

		Button btClear = new Button("<img src='" + CLEAR_IMG + "' /> Clear map");
		btClear.addClickListener(new ClearMapClickListener(gmapWidget));

		panel.clear();
		panel.add(lInfo);
		panel.add(cbPath);
		panel.add(cbMarker);
		panel.add(cbPartner);
		panel.add(btDisplay);
		panel.add(btZoom);
		panel.add(btClear);
	}

	/**
	 * TODO Javadoc
	 * 
	 * @return
	 */
	public boolean PartnerIsSelected() {
		return cbPartner.isChecked();
	}

	/**
	 * TODO Javadoc
	 * 
	 * @return
	 */
	public boolean PathIsSelected() {
		return cbPath.isChecked();
	}

	/**
	 * TODO Javadoc
	 * 
	 * @return
	 */
	public boolean MarkerIsSelected() {
		return cbMarker.isChecked();
	}

}
