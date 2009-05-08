/*
 * Copyright © 2008-2010, Aspire
 * 
 * Aspire is free software; you can redistribute it and/or modify it under the
 * terms of the GNU Lesser General Public License version 2.1 as published by
 * the Free Software Foundation (the "LGPL").
 * 
 * You should have received a copy of the GNU Lesser General Public License
 * along with this library in the file COPYING-LGPL-2.1; if not, write to the
 * Free Software Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA
 * 02110-1301 USA.
 * 
 * This software is distributed on an "AS IS" basis, WITHOUT WARRANTY OF ANY
 * KIND, either express or implied. See the GNU Lesser General Public License
 * for the specific language governing rights and limitations.
 */


package org.ow2.aspirerfid.ide.aleconfig.dialogs;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.ArrayList;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.swt.*;
import org.eclipse.swt.events.*;
import org.eclipse.swt.layout.*;
import org.eclipse.swt.widgets.*;
import org.ow2.aspirerfid.ide.aleconfig.preferences.*;
import org.ow2.aspirerfid.ide.aleconfig.utils.swtdesigner.ResourceManager;
import org.ow2.aspirerfid.ide.aleconfig.utils.swtdesigner.SWTResourceManager;
import org.ow2.aspirerfid.ide.aleconfig.Activator;
import org.w3c.dom.NodeList;


/**
 * This class demonstrates how to create your own dialog classes. It allows
 * users to input a String
 *
 * @author Vasso Koletti e-mail: vkol@ait.edu.gr
 *
 */
public class rpReaderSetPropertiesInputDialog extends Dialog {
	private Combo RPReaderTypeText;
	private Text RPDescriptionText;
	private Combo RPConnectionPointCombo;
	private Combo RPNotificationPointCombo;
	private Text RPReadTimeIntervalText;
	private Text RPAdaptorClassText;
	private Text RPImplClassText;
	private Text RPPhysicalReaderNameText;
	private Text RPPhysicalReaderSourceText;

	private ArrayList<String> inputTemp;
	private ArrayList<String> input;
	private NodeList propertiesNamesNodesList;
	private NodeList propertiesValuesNodesList;

	/** Returns the preference store for this UI plug-in */
	IPreferenceStore preferencesConnectionPoint = Activator.getDefault()
			.getPreferenceStore();
	IPreferenceStore preferencesNotificationPoint = Activator.getDefault()
			.getPreferenceStore();

	/**
	 * InputDialog constructor
	 * 
	 * @param parent
	 *            the parent
	 */
	public rpReaderSetPropertiesInputDialog(Shell parent,
			NodeList propertiesNamesNodesList, NodeList propertiesValuesNodeList) {
		// Pass the default styles here
		this(parent, SWT.DIALOG_TRIM | SWT.APPLICATION_MODAL,
				propertiesNamesNodesList, propertiesValuesNodeList);
	}

	/**
	 * InputDialog constructor
	 * 
	 * @param parent
	 *            the parent
	 * @param style
	 *            the style
	 */
	public rpReaderSetPropertiesInputDialog(Shell parent, int style,
			NodeList propertiesNamesNodesList,
			NodeList propertiesValuesNodesList) {
		// Let users override the default styles
		super(parent, style);
		setText("RP Reader - Set Properties");
		this.propertiesNamesNodesList = propertiesNamesNodesList;
		this.propertiesValuesNodesList = propertiesValuesNodesList;
		this.inputTemp = new ArrayList<String>();
		this.input = new ArrayList<String>();
	}

	/**
	 * Gets the input
	 * 
	 * @return String
	 */
	public ArrayList<String> getInput() {
		return input;
	}

	/**
	 * Sets the input
	 * 
	 * @param input
	 *            the new input
	 */
	public void setInput(ArrayList<String> input) {
		this.input = input;
	}

	/**
	 * Opens the dialog and returns the input
	 * 
	 * @return String
	 */
	public ArrayList<String> open() {
		// Create the dialog window
		Shell shell = new Shell(getParent(), getStyle());
		shell.setText(getText());
		createContents(shell);
		shell.pack();
		shell.open();

		// Get the screen size
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Dimension screenSize = toolkit.getScreenSize();

		// Calculate the frame location
		int x = (screenSize.width - shell.getBounds().width) / 2;
		int y = (screenSize.height - shell.getBounds().height) / 2;

		// Set the new frame location
		shell.setLocation(x, y);

		// load values to RPConnectionPointCombo
		String connectionPointArray[] = {};

		connectionPointArray = preferencesConnectionPoint.getString(
				PreferenceConstants.P_ConnectionPoints).split(",");

		RPConnectionPointCombo.removeAll();
		for (int i = 0; i < connectionPointArray.length; i++) {
			if(!(connectionPointArray[i].equals(""))){
			RPConnectionPointCombo.add(connectionPointArray[i]);
			}
		}

		// load values to RPNotificationPointCombo
		String notificationPointArray[] = {};

		notificationPointArray = preferencesNotificationPoint.getString(
				PreferenceConstants.P_NotificationPoints).split(",");

		RPNotificationPointCombo.removeAll();
		for (int i = 0; i < notificationPointArray.length; i++) {
			if(!(notificationPointArray[i].equals(""))){
			RPNotificationPointCombo.add(notificationPointArray[i]);
			}
		}

		Display display = getParent().getDisplay();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
		// Return the entered value, or null
		return input;
	}

	/**
	 * Creates the dialog's contents
	 * 
	 * @param shell
	 *            the dialog window
	 */
	private void createContents(final Shell shell) {

		shell.setLayout(new GridLayout(5, true));

		// Reader Type
		Label readerTypeLabel = new Label(shell, SWT.NONE);
		readerTypeLabel.setText("Reader Type:");
		GridData data = new GridData();
		data.horizontalSpan = 5;
		readerTypeLabel.setLayoutData(data);

		RPReaderTypeText = new Combo(shell, SWT.READ_ONLY);
		RPReaderTypeText
				.setItems(new String[] { "org.ow2.aspirerfid.ale.server.readers.rp.RPAdaptor" });
		RPReaderTypeText.setBackground(SWTResourceManager.getColor(230, 230,
				230));
		data = new GridData(GridData.FILL_HORIZONTAL);
		data.horizontalSpan = 5;
		RPReaderTypeText.setLayoutData(data);

		// Description
		Label RPDescriptionLabel = new Label(shell, SWT.NONE);
		RPDescriptionLabel.setText("Description:");
		RPDescriptionLabel.setLayoutData(data);

		RPDescriptionText = new Text(shell, SWT.BORDER);
		RPDescriptionText.setBackground(SWTResourceManager.getColor(230, 230,
				230));
		RPDescriptionText.setLayoutData(data);

		// Connection Point
		Label RPConnectionPointLabel = new Label(shell, SWT.NONE);
		RPConnectionPointLabel.setText("Connection Point:");
		RPConnectionPointLabel.setLayoutData(data);

		RPConnectionPointCombo = new Combo(shell, SWT.BORDER);
		RPConnectionPointCombo.setBackground(SWTResourceManager.getColor(230,
				230, 230));
		RPConnectionPointCombo.setLayoutData(data);

		// Notification Point
		Label RPNotificationPointLabel = new Label(shell, SWT.NONE);
		RPNotificationPointLabel.setText("Notification Point:");
		RPNotificationPointLabel.setLayoutData(data);

		RPNotificationPointCombo = new Combo(shell, SWT.BORDER);
		RPNotificationPointCombo.setBackground(SWTResourceManager.getColor(230,
				230, 230));
		RPNotificationPointCombo.setLayoutData(data);

		// Read Time Interval
		Label RPReadTimeIntervalLabel = new Label(shell, SWT.NONE);
		RPReadTimeIntervalLabel.setText("Read Time Interval:");
		RPReadTimeIntervalLabel.setLayoutData(data);

		RPReadTimeIntervalText = new Text(shell, SWT.BORDER);
		RPReadTimeIntervalText.setBackground(SWTResourceManager.getColor(230,
				230, 230));
		RPReadTimeIntervalText
				.addVerifyListener(new RPReadTimeIntervalTextVerifyListener());
		RPReadTimeIntervalText.setLayoutData(data);

		// Adaptor Class
		Label RPAdaptorClassLabel = new Label(shell, SWT.NONE);
		RPAdaptorClassLabel.setText("Adaptor Class:");
		RPAdaptorClassLabel.setLayoutData(data);

		RPAdaptorClassText = new Text(shell, SWT.BORDER);
		RPAdaptorClassText.setBackground(SWTResourceManager.getColor(230, 230,
				230));
		RPAdaptorClassText.setLayoutData(data);

		// Implementation Class
		Label RPImplClassLabel = new Label(shell, SWT.NONE);
		RPImplClassLabel.setText("Implementation Class:");
		RPImplClassLabel.setLayoutData(data);

		RPImplClassText = new Text(shell, SWT.BORDER);
		RPImplClassText.setBackground(SWTResourceManager
				.getColor(230, 230, 230));
		RPImplClassText.setLayoutData(data);

		// Physical Reader Name
		Label RPPhysicalReaderNameLabel = new Label(shell, SWT.NONE);
		RPPhysicalReaderNameLabel.setText("Physical Reader Name:");
		RPPhysicalReaderNameLabel.setLayoutData(data);

		RPPhysicalReaderNameText = new Text(shell, SWT.BORDER);
		RPPhysicalReaderNameText.setBackground(SWTResourceManager.getColor(230,
				230, 230));
		RPPhysicalReaderNameText.setLayoutData(data);

		// Physical Reader Source
		Label RPPhysicalReaderSourceLabel = new Label(shell, SWT.NONE);
		RPPhysicalReaderSourceLabel.setText("Physical Reader Source:");
		RPPhysicalReaderSourceLabel.setLayoutData(data);

		RPPhysicalReaderSourceText = new Text(shell, SWT.BORDER);
		RPPhysicalReaderSourceText.setBackground(SWTResourceManager.getColor(
				230, 230, 230));
		RPPhysicalReaderSourceText.setLayoutData(data);

		// Fill in the fields appropriately
		for (int i = 0; i < propertiesNamesNodesList.getLength(); i++) {
			if (propertiesNamesNodesList.item(i).getTextContent().equals(
					"ReaderType")) {
				RPReaderTypeText.setText(propertiesValuesNodesList.item(i)
						.getTextContent());
			} else if (propertiesNamesNodesList.item(i).getTextContent()
					.equals("Description")) {
				RPDescriptionText.setText(propertiesValuesNodesList.item(i)
						.getTextContent());
			} else if (propertiesNamesNodesList.item(i).getTextContent()
					.equals("ConnectionPoint")) {
				RPConnectionPointCombo.setText(propertiesValuesNodesList
						.item(i).getTextContent());
			} else if (propertiesNamesNodesList.item(i).getTextContent()
					.equals("NotificationPoint")) {
				RPNotificationPointCombo.setText(propertiesValuesNodesList
						.item(i).getTextContent());
			} else if (propertiesNamesNodesList.item(i).getTextContent()
					.equals("ReadTimeInterval")) {
				RPReadTimeIntervalText.setText(propertiesValuesNodesList
						.item(i).getTextContent());
			} else if (propertiesNamesNodesList.item(i).getTextContent()
					.equals("AdaptorClass")) {
				RPAdaptorClassText.setText(propertiesValuesNodesList.item(i)
						.getTextContent());
			} else if (propertiesNamesNodesList.item(i).getTextContent()
					.equals("ImplementationClass")) {
				RPImplClassText.setText(propertiesValuesNodesList.item(i)
						.getTextContent());
			} else if (propertiesNamesNodesList.item(i).getTextContent()
					.equals("PhysicalReaderName")) {
				RPPhysicalReaderNameText.setText(propertiesValuesNodesList
						.item(i).getTextContent());
			} else if (propertiesNamesNodesList.item(i).getTextContent()
					.equals("PhysicalReaderSource")) {
				RPPhysicalReaderSourceText.setText(propertiesValuesNodesList
						.item(i).getTextContent());
			}
		}

		// Create the OK button and add a handler
		// so that pressing it will set input
		// to the entered value
		Button ok = new Button(shell, SWT.PUSH);
		ok.setImage(ResourceManager.getPluginImage(Activator.getDefault(),
				"icons/ok.png"));
		ok.setText("OK");
		data = new GridData(GridData.FILL_HORIZONTAL);
		ok.setLayoutData(data);
		ok.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent event) {
				if (RPReaderTypeText.getText().equals("")
						|| RPDescriptionText.getText().equals("")
						|| RPConnectionPointCombo.getText().equals("")
						|| RPNotificationPointCombo.getText().equals("")
						|| RPReadTimeIntervalText.getText().equals("")
						|| RPAdaptorClassText.getText().equals("")
						|| RPImplClassText.getText().equals("")
						|| RPPhysicalReaderNameText.getText().equals("")
						|| RPPhysicalReaderSourceText.getText().equals("")) {
					MessageDialog.openError(shell, "Error",
							"All property fields are mandatory!");

				} else {
					// Fill in input array appropriately
					inputTemp.add(RPReaderTypeText.getText());
					inputTemp.add(RPDescriptionText.getText());
					inputTemp.add(RPConnectionPointCombo.getText());
					inputTemp.add(RPNotificationPointCombo.getText());
					inputTemp.add(RPReadTimeIntervalText.getText());
					inputTemp.add(RPAdaptorClassText.getText());
					inputTemp.add(RPImplClassText.getText());
					inputTemp.add(RPPhysicalReaderNameText.getText());
					inputTemp.add(RPPhysicalReaderSourceText.getText());

					input = inputTemp;

					addNewConnectionPointValueIfNotPresent();
					addNewNotificationPointValueIfNotPresent();

					shell.close();
				}
			}
		});

		// Create the cancel button and add a handler
		// so that pressing it will set input to null
		Button cancel = new Button(shell, SWT.PUSH);
		cancel.setImage(ResourceManager.getPluginImage(Activator.getDefault(),
				"icons/cancel.jpg"));
		cancel.setText("Cancel");
		data = new GridData(GridData.FILL_HORIZONTAL);
		cancel.setLayoutData(data);
		cancel.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent event) {
				input = null;
				shell.close();
			}
		});

		// Set the OK button as the default, so
		// user can type input and press Enter
		// to dismiss
		shell.setDefaultButton(ok);
	}

	private class RPReadTimeIntervalTextVerifyListener implements
			VerifyListener {
		public void verifyText(final VerifyEvent arg0) {
			String string = arg0.text;
			char[] chars = new char[string.length()];
			string.getChars(0, chars.length, chars, 0);
			for (int i = 0; i < chars.length; i++) {
				if (!('0' <= chars[i] && chars[i] <= '9')) {
					arg0.doit = false;
					return;
				}
			}
		}
	}

	private void addNewConnectionPointValueIfNotPresent() {
		// add a new value to PreferenceConstants.P_ConnectionPoints
		String connectionPointArray[] = {};
		String connectionPoints = "";
		boolean found = false;

		connectionPointArray = preferencesConnectionPoint.getString(
				PreferenceConstants.P_ConnectionPoints).split(",");

		for (int i = 0; i < connectionPointArray.length; i++) {
			if (RPConnectionPointCombo.getText()
					.equals(connectionPointArray[i])) {
				found = true;
			}
		}

		if (found == false) {

			connectionPoints = preferencesConnectionPoint
					.getString(PreferenceConstants.P_ConnectionPoints);

			connectionPoints = connectionPoints + ","
					+ RPConnectionPointCombo.getText();

			preferencesConnectionPoint.setValue(
					PreferenceConstants.P_ConnectionPoints, connectionPoints);

			connectionPointArray = preferencesConnectionPoint.getString(
					PreferenceConstants.P_ConnectionPoints).split(",");

			RPConnectionPointCombo.removeAll();
			for (int i = 0; i < connectionPointArray.length; i++) {
				if(!(connectionPointArray[i].equals(""))){
				RPConnectionPointCombo.add(connectionPointArray[i]);
				}
			}
		}
	}

	private void addNewNotificationPointValueIfNotPresent() {
		// add a new value to PreferenceConstants.P_NotificationPoints
		String notificationPointArray[] = {};
		String notificationPoints = "";
		boolean found = false;

		notificationPointArray = preferencesNotificationPoint.getString(
				PreferenceConstants.P_NotificationPoints).split(",");

		for (int i = 0; i < notificationPointArray.length; i++) {
			if (RPNotificationPointCombo.getText().equals(
					notificationPointArray[i])) {
				found = true;
			}
		}

		if (found == false) {

			notificationPoints = preferencesNotificationPoint
					.getString(PreferenceConstants.P_NotificationPoints);

			notificationPoints = notificationPoints + ","
					+ RPNotificationPointCombo.getText();

			preferencesNotificationPoint.setValue(
					PreferenceConstants.P_NotificationPoints,
					notificationPoints);

			notificationPointArray = preferencesNotificationPoint.getString(
					PreferenceConstants.P_NotificationPoints).split(",");

			RPNotificationPointCombo.removeAll();
			for (int i = 0; i < notificationPointArray.length; i++) {
				if(!(notificationPointArray[i].equals(""))){
				RPNotificationPointCombo.add(notificationPointArray[i]);
				}
			}
		}
	}
}
