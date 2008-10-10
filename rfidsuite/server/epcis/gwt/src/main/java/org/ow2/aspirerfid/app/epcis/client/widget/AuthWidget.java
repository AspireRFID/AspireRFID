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

import org.ow2.aspirerfid.app.epcis.client.IndexModule;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DockPanel;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.KeyboardListener;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

/**
 * TODO Javadoc
 * 
 * @author Unknown
 */
public class AuthWidget extends Composite {

	private DockPanel centredPanel;

	private DockPanel panel;

	private TextBox tbLogin;

	private PasswordTextBox tbPass;

	private Button btOK;

	private AsyncCallback callback;

	/**
	 * TODO Javadoc
	 * 
	 * @param callback
	 */
	public AuthWidget(AsyncCallback callback) {
		panel = new DockPanel();
		centredPanel = new DockPanel();
		this.callback = callback;
		initWidget(centredPanel);
		panel.setStyleName("widget-AuthWidget");
		display();
	}

	/**
	 * TODO Javadoc
	 */
	public void display() {
		panel.clear();
		Label authLabel = new Label("Authentication");
		panel.add(authLabel, DockPanel.NORTH);
		panel.setCellHorizontalAlignment(authLabel, DockPanel.ALIGN_CENTER);
		panel.add(new Image("img/bolt.png"), DockPanel.WEST);
		panel.setSpacing(15);

		FlexTable flex = new FlexTable();

		tbLogin = new TextBox();
		tbLogin.addKeyboardListener(new onEnter());
		tbLogin.setText("admin");
		flex.setText(0, 0, "Login");
		flex.getCellFormatter().setHorizontalAlignment(0, 0,
				HasHorizontalAlignment.ALIGN_RIGHT);
		flex.setWidget(0, 1, tbLogin);

		tbPass = new PasswordTextBox();
		tbPass.addKeyboardListener(new onEnter());
		tbPass.setText("admin");
		flex.setText(1, 0, "Password");
		flex.getCellFormatter().setHorizontalAlignment(1, 0,
				HasHorizontalAlignment.ALIGN_RIGHT);
		flex.setWidget(1, 1, tbPass);

		panel.add(flex, DockPanel.CENTER);
		panel.setCellHorizontalAlignment(flex, DockPanel.ALIGN_CENTER);

		btOK = new Button("Connect", new ClickListener() {
			public void onClick(Widget arg0) {
				IndexModule.getSession().authentication(tbLogin.getText(),
						tbPass.getText(), callback);
			}
		});
		panel.add(btOK, DockPanel.SOUTH);
		panel.setCellHorizontalAlignment(btOK, DockPanel.ALIGN_RIGHT);

		centredPanel.add(panel, DockPanel.CENTER);
		centredPanel.setWidth("100%");
		centredPanel.setCellHorizontalAlignment(panel, DockPanel.ALIGN_CENTER);
	}

	private class onEnter implements KeyboardListener {

		public void onKeyDown(Widget sender, char keyCode, int modifiers) {
		}

		public void onKeyPress(Widget sender, char keyCode, int modifiers) {
			if (keyCode == KEY_ENTER) {
				IndexModule.getSession().authentication(tbLogin.getText(),
						tbPass.getText(), callback);
			}
		}

		public void onKeyUp(Widget sender, char keyCode, int modifiers) {
		}
	}

	/**
	 * TODO Javadoc
	 * 
	 * @return
	 */
	public String getLogin() {
		return tbLogin.getText();
	}

}
