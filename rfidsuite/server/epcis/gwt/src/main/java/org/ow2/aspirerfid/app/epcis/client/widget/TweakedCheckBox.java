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

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

/**
 * Custom CheckBox like http://www.chriserwin.com/scripts/crir/ but with GWT.
 * 
 * @author Guillaume Vaudaux-Ruth
 * @version 2007
 */
public class TweakedCheckBox extends Composite implements Checkable {

	private FlexTable panel;

	private Image imgChecked;

	private Image imgUnchecked;

	private boolean checked;

	private Label text;

	private List/* <ClickListener> */listeners;

	/**
	 * TODO Javadoc
	 */
	public TweakedCheckBox() {
		imgChecked = new Image("img/checkbox_checked.png");
		imgUnchecked = new Image("img/checkbox_unchecked.png");
		panel = new FlexTable();
		initWidget(panel);

		listeners = new ArrayList();
		text = new Label();
		text.addStyleName("clickable");
		panel.setWidget(1, 2, this.text);
		setChecked(false);

		ClickListener listener = new defaultCLickListener();
		text.addClickListener(listener);
		imgChecked.addClickListener(listener);
		imgChecked.addStyleName("clickable");
		imgUnchecked.addClickListener(listener);
		imgUnchecked.addStyleName("clickable");
	}

	/**
	 * TODO Javadoc
	 * 
	 * @param name
	 */
	public void addLabelStyleName(String name) {
		text.addStyleName(name);
	}

	/**
	 * TODO Javadoc
	 * 
	 * @param text
	 */
	public void setText(String text) {
		this.text.setText(text);
	}

	/**
	 * TODO Javadoc
	 * 
	 * @param checked
	 */
	public void setChecked(boolean checked) {
		this.checked = checked;
		if (this.checked) {
			panel.setWidget(1, 1, imgChecked);
		} else {
			panel.setWidget(1, 1, imgUnchecked);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.ow2.aspirerfid.app.epcis.client.widget.Checkable#isChecked()
	 */
	public boolean isChecked() {
		return this.checked;
	}

	/**
	 * TODO Javadoc
	 * 
	 * @param listener
	 */
	public void addClickListener(ClickListener listener) {
		this.listeners.add(listener);
	}

	private class defaultCLickListener implements ClickListener {
		public void onClick(Widget sender) {
			setChecked(!checked);
			for (int i = 0; i < listeners.size(); i++) {
				ClickListener listener = (ClickListener) listeners.get(i);
				listener.onClick(TweakedCheckBox.this);
			}
		}
	}
}
