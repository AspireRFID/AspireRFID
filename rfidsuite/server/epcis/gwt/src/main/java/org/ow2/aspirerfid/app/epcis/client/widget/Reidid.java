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

import asquare.gwt.tk.client.ui.ModalDialog;

import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FocusPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.KeyboardListener;
import com.google.gwt.user.client.ui.Widget;

/**
 * TODO Javadoc
 * 
 * @author Guillaume Vaudaux-Ruth
 * @version 2007
 */
public class Reidid extends Composite {

	private Image IMG_REIDID = new Image("img/reidid.jpg");

	private FocusPanel panel = new FocusPanel();

	/**
	 * TODO Javadoc
	 */
	public Reidid() {
		panel.clear();
		panel.add(new Image("img/transparent.png"));
		panel.setWidth("100%");
		panel.setStyleName("clickable");
		panel.addKeyboardListener(new ReididListener());
		initWidget(panel);
	}

	private class CloseClickListener implements ClickListener {

		private ModalDialog dialog;

		public CloseClickListener(ModalDialog dialog) {
			this.dialog = dialog;
		}

		public void onClick(Widget sender) {
			dialog.hide();
		}
	}

	private class ReididListener implements KeyboardListener {

		private String buffer = "";

		public void onKeyDown(Widget sender, char keyCode, int modifiers) {
		}

		public void onKeyPress(Widget sender, char keyCode, int modifiers) {
			buffer += keyCode;
			if (buffer.length() == 6) {
				buffer = buffer.toLowerCase();
				if (buffer.compareTo("didier") == 0) {
					ModalDialog didibox = new ModalDialog();
					didibox.add(IMG_REIDID);
					didibox.add(new Button("Close", new CloseClickListener(
							didibox)));
					didibox.show();
					didibox.center();
					buffer = "";
				} else {
					buffer = buffer.substring(1);
				}
			}

		}

		public void onKeyUp(Widget sender, char keyCode, int modifiers) {
		}
	}
}
