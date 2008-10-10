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
package org.ow2.aspirerfid.app.epcis.client.widget.inputhelper;

import org.ow2.aspirerfid.app.epcis.client.IndexModule;
import org.ow2.aspirerfid.app.epcis.client.util.Util;
import org.ow2.aspirerfid.app.epcis.client.widget.tags.TagInput;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import asquare.gwt.tk.client.ui.ModalDialog;

import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.ChangeListener;
import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

/**
 * TODO Javadoc
 * 
 * @author Guillaume Vaudaux-Ruth
 * @version 2007
 */
public class InputHelper extends Composite {

	private static final String FIELD_WIDTH = "300px";

	private static final String COUNT_GENERALMANAGER = "editor.inputhelper.generalmanager.count";

	private static final String KEYVALUE_GENERALMANAGER = "editor.inputhelper.generalmanager";

	private static final String COUNT_OBJECTCLASS = "editor.inputhelper.objectclass.count";

	private static final String KEYVALUE_OBJECTCLASS = "editor.inputhelper.objectclass";

	private VerticalPanel panel;

	private List customGeneralManager;

	private List customObjectClass;

	private ModalDialog dialog;

	private String epc;

	private TextBox tbEpc;

	private ListBox lbTagFormat;

	private ListBox lbGeneralManager;

	private ListBox lbObjectClass;

	private TextBox tbSerialNumber;

	private TagInput tagInput;

	private class KeyValue {
		public KeyValue(String k, String v) {
			key = k;
			value = v;
		}

		public String key;
		public String value;
	}

	/**
	 * TODO Javadoc
	 * 
	 * @param dialog
	 * @param tagInput
	 */
	public InputHelper(ModalDialog dialog, TagInput tagInput) {
		this.tagInput = tagInput;
		this.dialog = dialog;
		panel = new VerticalPanel();
		initWidget(panel);
		loadingMessage();

		IndexModule.getSession().getEditorConf(
				new InputHelperGetConfCallback(this));
	}

	/**
	 * TODO Javadoc
	 */
	public void loadingMessage() {
		panel.add(Util.defaultLoadingMessage());
		dialog.center();
	}

	/**
	 * TODO Javadoc
	 * 
	 * @param props
	 */
	public void display(HashMap props) {
		// load custom value set in 'editor.properties'
		setConfig(props);

		panel.clear();

		FlexTable t = new FlexTable();

		// epc preview
		tbEpc = new TextBox();
		tbEpc.setText("loading ...");
		tbEpc.setEnabled(false);
		tbEpc.setWidth(FIELD_WIDTH);
		t.setText(0, 0, "EPC : ");
		t.setWidget(0, 1, tbEpc);

		// tag format
		lbTagFormat = new ListBox();
		lbTagFormat.addItem("GID-96", "gid");
		lbTagFormat.setWidth(FIELD_WIDTH);
		lbTagFormat.addChangeListener(new MyChangeListener());
		t.setText(1, 0, "Tag format : ");
		t.setWidget(1, 1, lbTagFormat);

		// general manager
		lbGeneralManager = new ListBox();
		for (int i = 0; i < customGeneralManager.size(); i++) {
			KeyValue kv = (KeyValue) customGeneralManager.get(i);
			lbGeneralManager.addItem(kv.key, kv.value);
		}
		lbGeneralManager.addChangeListener(new MyChangeListener());
		lbGeneralManager.setWidth(FIELD_WIDTH);
		t.setText(2, 0, "General manager : ");
		t.setWidget(2, 1, lbGeneralManager);

		// object class
		lbObjectClass = new ListBox();
		for (int i = 0; i < customObjectClass.size(); i++) {
			KeyValue kv = (KeyValue) customObjectClass.get(i);
			lbObjectClass.addItem(kv.key, kv.value);
		}
		lbObjectClass.setWidth(FIELD_WIDTH);
		lbObjectClass.addChangeListener(new MyChangeListener());
		t.setText(3, 0, "Object class : ");
		t.setWidget(3, 1, lbObjectClass);

		// serial number
		tbSerialNumber = new TextBox();
		tbSerialNumber.setWidth(FIELD_WIDTH);
		tbSerialNumber.addChangeListener(new MyChangeListener());
		t.setText(4, 0, "Serial number : ");
		t.setWidget(4, 1, tbSerialNumber);

		Button btOK = new Button("Select", new ClickOK());
		Button btCancel = new Button("Cancel", new ClickCancel());

		t.setWidget(5, 0, btOK);
		t.setWidget(5, 1, btCancel);

		t.setWidth("450px");

		updateEpc();
		panel.add(t);
		dialog.center();
	}

	private void updateEpc() {
		epc = "urn:epc:id:"
				+ lbTagFormat.getValue(lbTagFormat.getSelectedIndex())
				+ ":"
				+ lbGeneralManager
						.getValue(lbGeneralManager.getSelectedIndex()) + "."
				+ lbObjectClass.getValue(lbObjectClass.getSelectedIndex())
				+ "." + tbSerialNumber.getText();

		tbEpc.setText(epc);
	}

	private void setConfig(HashMap conf) {
		customGeneralManager = new ArrayList();
		customObjectClass = new ArrayList();

		if (conf != null) {
			int n = Integer.parseInt((String) conf.get(COUNT_GENERALMANAGER));
			for (int i = 0; i < n; i++) {
				String s = (String) conf.get(i + "." + KEYVALUE_GENERALMANAGER);
				String[] splitted = s.split("-");
				customGeneralManager
						.add(new KeyValue(splitted[0], splitted[1]));
			}

			n = Integer.parseInt((String) conf.get(COUNT_OBJECTCLASS));
			for (int i = 0; i < n; i++) {
				String s = (String) conf.get(i + "." + KEYVALUE_OBJECTCLASS);
				String[] splitted = s.split("-");
				customObjectClass.add(new KeyValue(splitted[0], splitted[1]));
			}
		} else {
			customGeneralManager.add(new KeyValue(
					"please config your 'editor.properties'", "000000"));
			customObjectClass.add(new KeyValue(
					"please config your 'editor.properties'", "000000"));
		}
	}

	private class MyChangeListener implements ChangeListener {

		public void onChange(Widget sender) {
			updateEpc();
		}
	}

	private class ClickOK implements ClickListener {

		public void onClick(Widget sender) {
			dialog.hide();
			tagInput.setTag(epc);
		}

	}

	private class ClickCancel implements ClickListener {

		public void onClick(Widget sender) {
			dialog.hide();
		}

	}

}
