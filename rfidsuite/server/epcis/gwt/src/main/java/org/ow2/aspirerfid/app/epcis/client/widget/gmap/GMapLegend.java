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

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;

/**
 * TODO Javadoc
 * 
 * @author Unknown
 */
public class GMapLegend extends Composite {

	private FlexTable panel;

	private static final String IMG_INTER = "img/marker.png";

	private static final String IMG_START = "img/marker_green.png";

	private static final String IMG_FINISH = "img/marker-end.png";

	/**
	 * TODO Javadoc
	 */
	public GMapLegend() {
		panel = new FlexTable();
		initWidget(panel);
		display();
	}

	/**
	 * TODO Javadoc
	 */
	public void display() {
		panel.setStyleName("widget-GMapLegend");

		Label l = new Label("Legend :");
		l.setStyleName("widget-GMapLegend-title");
		panel.setWidget(1, 0, l);

		panel.setWidget(2, 0, new Label("Start"));
		panel.setWidget(2, 1, new Image(IMG_START));

		panel.setWidget(3, 0, new Label("Intermediate"));
		panel.setWidget(3, 1, new Image(IMG_INTER));

		panel.setWidget(4, 0, new Label("Finish"));
		panel.setWidget(4, 1, new Image(IMG_FINISH));
	}
}
