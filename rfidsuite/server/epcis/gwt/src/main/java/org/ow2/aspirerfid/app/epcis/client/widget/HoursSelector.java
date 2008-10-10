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
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.ListBox;

/**
 * TODO Javadoc
 * 
 * @author Guillaume Vaudaux-Ruth
 * @version 2007
 */
public class HoursSelector extends Composite {

	private FlexTable panel;

	private ListBox lbHours;

	private ListBox lbMin;

	private ListBox lbSec;

	/**
	 * TODO Javadoc
	 */
	public HoursSelector() {
		panel = new FlexTable();
		initWidget(panel);
		init();
		display();
	}

	/**
	 * TODO Javadoc
	 * 
	 * @param hours
	 */
	public HoursSelector(int hours) {
		panel = new FlexTable();
		initWidget(panel);
		init();
		lbHours.setSelectedIndex(hours);
		display();
	}

	/**
	 * TODO Javadoc
	 */
	public void init() {
		lbHours = new ListBox();
		for (int i = 0; i < 24; i++) {
			lbHours.addItem(Integer.toString(i));
		}

		lbMin = new ListBox();
		for (int i = 0; i < 60; i++) {
			lbMin.addItem(Integer.toString(i));
		}

		lbSec = new ListBox();
		for (int i = 0; i < 60; i++) {
			lbSec.addItem(Integer.toString(i));
		}
	}

	/**
	 * TODO Javadoc
	 */
	public void display() {
		panel.setWidget(1, 1, lbHours);
		panel.setText(1, 2, "h ");

		panel.setWidget(1, 3, lbMin);
		panel.setText(1, 4, "m ");

		panel.setWidget(1, 5, lbSec);
		panel.setText(1, 6, "s");
	}

	public String getSelectedTime() {
		String s;
		s = lbHours.getItemText(lbHours.getSelectedIndex()) + ":"
				+ lbMin.getItemText(lbMin.getSelectedIndex()) + ":"
				+ lbSec.getItemText(lbSec.getSelectedIndex());

		return s;
	}

	/**
	 * TODO Javadoc
	 * 
	 * @param b
	 */
	public void setEnabled(boolean b) {
		lbHours.setEnabled(b);
		lbMin.setEnabled(b);
		lbSec.setEnabled(b);
	}
}
