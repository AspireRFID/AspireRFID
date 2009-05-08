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
import org.eclipse.swt.*;
import org.eclipse.swt.events.*;
import org.eclipse.swt.layout.*;
import org.eclipse.swt.widgets.*;
import org.ow2.aspirerfid.ide.aleconfig.Activator;
import org.ow2.aspirerfid.ide.aleconfig.utils.swtdesigner.ResourceManager;
import org.ow2.aspirerfid.ide.aleconfig.utils.swtdesigner.SWTResourceManager;
import org.w3c.dom.NodeList;


/**
 * This class demonstrates how to create your own dialog classes. It allows
 * users to input a String
 *
 * @author Vasso Koletti e-mail: vkol@ait.edu.gr
 *
 */
public class halReaderSetPropertiesInputDialog extends Dialog {
	private Combo HALReaderTypeText;
	private Text HALDescriptionText;
	private Text HALAdaptorClassText;
	private Text HALPhysicalReaderNameText;
	private Text HALReadTimeIntervalText;
	private Text HALReadPointsText;
	private Text HALPropertiesFileText;

	private ArrayList<String> inputTemp;
	private ArrayList<String> input;
	private NodeList propertiesNamesNodesList;
	private NodeList propertiesValuesNodesList;

	/**
	 * InputDialog constructor
	 * 
	 * @param parent
	 *            the parent
	 */
	public halReaderSetPropertiesInputDialog(Shell parent,
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
	public halReaderSetPropertiesInputDialog(Shell parent, int style,
			NodeList propertiesNamesNodesList,
			NodeList propertiesValuesNodesList) {
		// Let users override the default styles
		super(parent, style);
		setText("HAL Reader - Set Properties");
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

		HALReaderTypeText = new Combo(shell, SWT.READ_ONLY);
		HALReaderTypeText
				.setItems(new String[] { "org.ow2.aspirerfid.ale.server.readers.hal.HALAdaptor" });
		HALReaderTypeText.setBackground(SWTResourceManager.getColor(230, 230,
				230));
		data = new GridData(GridData.FILL_HORIZONTAL);
		data.horizontalSpan = 5;
		HALReaderTypeText.setLayoutData(data);

		// Description
		Label HALDescriptionLabel = new Label(shell, SWT.NONE);
		HALDescriptionLabel.setText("Description:");
		HALDescriptionLabel.setLayoutData(data);

		HALDescriptionText = new Text(shell, SWT.BORDER);
		HALDescriptionText.setBackground(SWTResourceManager.getColor(230, 230,
				230));
		HALDescriptionText.setLayoutData(data);

		// Adaptor Class
		Label HALAdaptorClassLabel = new Label(shell, SWT.NONE);
		HALAdaptorClassLabel.setText("Adaptor Class:");
		HALAdaptorClassLabel.setLayoutData(data);

		HALAdaptorClassText = new Text(shell, SWT.BORDER);
		HALAdaptorClassText.setBackground(SWTResourceManager.getColor(230, 230,
				230));
		HALAdaptorClassText.setLayoutData(data);

		// Physical Reader Name
		Label HALPhysicalReaderNameLabel = new Label(shell, SWT.NONE);
		HALPhysicalReaderNameLabel.setText("Physical Reader Name:");
		HALPhysicalReaderNameLabel.setLayoutData(data);

		HALPhysicalReaderNameText = new Text(shell, SWT.BORDER);
		HALPhysicalReaderNameText.setBackground(SWTResourceManager.getColor(
				230, 230, 230));
		HALPhysicalReaderNameText.setLayoutData(data);

		// Read Time Interval
		Label HALReadTimeIntervalLabel = new Label(shell, SWT.NONE);
		HALReadTimeIntervalLabel.setText("Read Time Interval:");
		HALReadTimeIntervalLabel.setLayoutData(data);

		HALReadTimeIntervalText = new Text(shell, SWT.BORDER);
		HALReadTimeIntervalText.setBackground(SWTResourceManager.getColor(230,
				230, 230));
		HALReadTimeIntervalText
				.addVerifyListener(new HALReadTimeIntervalTextVerifyListener());
		HALReadTimeIntervalText.setLayoutData(data);

		// Read Points
		Label HALReadPointsLabel = new Label(shell, SWT.NONE);
		HALReadPointsLabel.setText("Read Points:");
		HALReadPointsLabel.setLayoutData(data);

		HALReadPointsText = new Text(shell, SWT.BORDER);
		HALReadPointsText.setBackground(SWTResourceManager.getColor(230, 230,
				230));
		HALReadPointsText.setLayoutData(data);

		// Properties File
		Label HALPropertiesFileLabel = new Label(shell, SWT.NONE);
		HALPropertiesFileLabel.setText("Properties File:");
		HALPropertiesFileLabel.setLayoutData(data);

		HALPropertiesFileText = new Text(shell, SWT.BORDER);
		HALPropertiesFileText.setBackground(SWTResourceManager.getColor(230,
				230, 230));
		HALPropertiesFileText.setLayoutData(data);

		// Fill in the fields appropriately
		for (int i = 0; i < propertiesNamesNodesList.getLength(); i++) {
			if (propertiesNamesNodesList.item(i).getTextContent().equals(
					"ReaderType")) {
				HALReaderTypeText.setText(propertiesValuesNodesList.item(i)
						.getTextContent());
			} else if (propertiesNamesNodesList.item(i).getTextContent()
					.equals("Description")) {
				HALDescriptionText.setText(propertiesValuesNodesList.item(i)
						.getTextContent());
			} else if (propertiesNamesNodesList.item(i).getTextContent()
					.equals("AdaptorClass")) {
				HALAdaptorClassText.setText(propertiesValuesNodesList.item(i)
						.getTextContent());
			} else if (propertiesNamesNodesList.item(i).getTextContent()
					.equals("PhysicalReaderName")) {
				HALPhysicalReaderNameText.setText(propertiesValuesNodesList
						.item(i).getTextContent());
			} else if (propertiesNamesNodesList.item(i).getTextContent()
					.equals("ReadTimeInterval")) {
				HALReadTimeIntervalText.setText(propertiesValuesNodesList.item(
						i).getTextContent());
			} else if (propertiesNamesNodesList.item(i).getTextContent()
					.equals("ReadPoints")) {
				HALReadPointsText.setText(propertiesValuesNodesList.item(i)
						.getTextContent());
			} else if (propertiesNamesNodesList.item(i).getTextContent()
					.equals("PropertiesFile")) {
				HALPropertiesFileText.setText(propertiesValuesNodesList.item(i)
						.getTextContent());
			}
		}

		// Create the OK button and add a handler
		// so that pressing it will set input
		// to the entered value
		Button ok = new Button(shell, SWT.PUSH);
		ok.setText("OK");
		ok.setImage(ResourceManager.getPluginImage(Activator.getDefault(),
				"icons/ok.png"));
		data = new GridData(GridData.FILL_HORIZONTAL);
		ok.setLayoutData(data);
		ok.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent event) {
				if (HALReaderTypeText.getText().equals("")
						|| HALDescriptionText.getText().equals("")
						|| HALAdaptorClassText.getText().equals("")
						|| HALPhysicalReaderNameText.getText().equals("")
						|| HALReadTimeIntervalText.getText().equals("")
						|| HALReadPointsText.getText().equals("")
						|| HALPropertiesFileText.getText().equals("")) {
					MessageDialog.openError(shell, "Error",
							"All property fields are mandatory!");
				} else {
					// Fill in input array appropriately
					inputTemp.add(HALReaderTypeText.getText());
					inputTemp.add(HALDescriptionText.getText());
					inputTemp.add(HALAdaptorClassText.getText());
					inputTemp.add(HALPhysicalReaderNameText.getText());
					inputTemp.add(HALReadTimeIntervalText.getText());
					inputTemp.add(HALReadPointsText.getText());
					inputTemp.add(HALPropertiesFileText.getText());

					input = inputTemp;

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

	private class HALReadTimeIntervalTextVerifyListener implements
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
}
