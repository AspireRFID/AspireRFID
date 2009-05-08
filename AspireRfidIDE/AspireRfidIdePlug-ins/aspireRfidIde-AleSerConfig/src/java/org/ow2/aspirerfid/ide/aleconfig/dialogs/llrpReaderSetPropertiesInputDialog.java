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
public class llrpReaderSetPropertiesInputDialog extends Dialog {
	private Combo LLRPReaderTypeText;
	private Text LLRPDescriptionText;
	private Text LLRPConnectionPointAddressText;
	private Text LLRPConnectionPointPortText;
	private Text LLRPEncryptedConnectionPointAddressText;
	private Text LLRPEncryptedConnectionPointPortText;
	private Text LLRPReadTimeIntervalText;
	private Text LLRPAdaptorClassText;
	private Text LLRPImplClassText;
	private Text LLRPPhysicalReaderSourceText;
	private Text LLRPDescriptiveReaderSourceText;
	private Text LLRPReaderOperationSpecIDText;

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
	public llrpReaderSetPropertiesInputDialog(Shell parent,
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
	public llrpReaderSetPropertiesInputDialog(Shell parent, int style,
			NodeList propertiesNamesNodesList,
			NodeList propertiesValuesNodesList) {
		// Let users override the default styles
		super(parent, style);
		setText("LLRP Reader - Set Properties");
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

		LLRPReaderTypeText = new Combo(shell, SWT.READ_ONLY);
		LLRPReaderTypeText
				.setItems(new String[] { "org.ow2.aspirerfid.ale.server.readers.llrp.LLRPAdaptor" });
		LLRPReaderTypeText.setBackground(SWTResourceManager.getColor(230, 230,
				230));
		data = new GridData(GridData.FILL_HORIZONTAL);
		data.horizontalSpan = 5;
		LLRPReaderTypeText.setLayoutData(data);

		// Description
		Label LLRPDescriptionLabel = new Label(shell, SWT.NONE);
		LLRPDescriptionLabel.setText("Description:");
		LLRPDescriptionLabel.setLayoutData(data);

		LLRPDescriptionText = new Text(shell, SWT.BORDER);
		LLRPDescriptionText.setBackground(SWTResourceManager.getColor(230, 230,
				230));
		LLRPDescriptionText.setLayoutData(data);

		// Connection Point Address
		Label LLRPConnectionPointAddressLabel = new Label(shell, SWT.NONE);
		LLRPConnectionPointAddressLabel.setText("Connection Point Address:");
		LLRPConnectionPointAddressLabel.setLayoutData(data);

		LLRPConnectionPointAddressText = new Text(shell, SWT.BORDER);
		LLRPConnectionPointAddressText.setBackground(SWTResourceManager
				.getColor(230, 230, 230));
		LLRPConnectionPointAddressText.setLayoutData(data);

		// Connection Point Port
		Label LLRPConnectionPointPortLabel = new Label(shell, SWT.NONE);
		LLRPConnectionPointPortLabel.setText("Connection Point Port:");
		LLRPConnectionPointPortLabel.setLayoutData(data);

		LLRPConnectionPointPortText = new Text(shell, SWT.BORDER);
		LLRPConnectionPointPortText
				.addVerifyListener(new LLRPConnectionPointPortTextVerifyListener());
		LLRPConnectionPointPortText.setBackground(SWTResourceManager.getColor(
				230, 230, 230));
		LLRPConnectionPointPortText.setLayoutData(data);

		// Encrypted Connection Point Address
		Label LLRPEncryptedConnectionPointAddressLabel = new Label(shell,
				SWT.NONE);
		LLRPEncryptedConnectionPointAddressLabel
				.setText("Encrypted Connection Point Address:");
		LLRPEncryptedConnectionPointAddressLabel.setLayoutData(data);

		LLRPEncryptedConnectionPointAddressText = new Text(shell, SWT.BORDER);
		LLRPEncryptedConnectionPointAddressText
				.setBackground(SWTResourceManager.getColor(230, 230, 230));
		LLRPEncryptedConnectionPointAddressText.setLayoutData(data);

		// Encrypted Connection Point Port
		Label LLRPEncryptedConnectionPointPortLabel = new Label(shell, SWT.NONE);
		LLRPEncryptedConnectionPointPortLabel
				.setText("Encrypted Connection Point Port:");
		LLRPEncryptedConnectionPointPortLabel.setLayoutData(data);

		LLRPEncryptedConnectionPointPortText = new Text(shell, SWT.BORDER);
		LLRPEncryptedConnectionPointPortText
				.addVerifyListener(new LLRPEncryptedConnectionPointPortTextVerifyListener());
		LLRPEncryptedConnectionPointPortText.setBackground(SWTResourceManager
				.getColor(230, 230, 230));
		LLRPEncryptedConnectionPointPortText.setLayoutData(data);

		// Read Time Interval
		Label LLRPReadTimeIntervalLabel = new Label(shell, SWT.NONE);
		LLRPReadTimeIntervalLabel.setText("Read Time Interval:");
		LLRPReadTimeIntervalLabel.setLayoutData(data);

		LLRPReadTimeIntervalText = new Text(shell, SWT.BORDER);
		LLRPReadTimeIntervalText.setBackground(SWTResourceManager.getColor(230,
				230, 230));
		LLRPReadTimeIntervalText
				.addVerifyListener(new LLRPReadTimeIntervalTextVerifyListener());
		LLRPReadTimeIntervalText.setLayoutData(data);

		// Adaptor Class
		Label LLRPAdaptorClassLabel = new Label(shell, SWT.NONE);
		LLRPAdaptorClassLabel.setText("Adaptor Class:");
		LLRPAdaptorClassLabel.setLayoutData(data);

		LLRPAdaptorClassText = new Text(shell, SWT.BORDER);
		LLRPAdaptorClassText.setBackground(SWTResourceManager.getColor(230,
				230, 230));
		LLRPAdaptorClassText.setLayoutData(data);

		// Implementation Class
		Label LLRPImplClassLabel = new Label(shell, SWT.NONE);
		LLRPImplClassLabel.setText("Implementation Class:");
		LLRPImplClassLabel.setLayoutData(data);

		LLRPImplClassText = new Text(shell, SWT.BORDER);
		LLRPImplClassText.setBackground(SWTResourceManager.getColor(230, 230,
				230));
		LLRPImplClassText.setLayoutData(data);

		// Physical Reader Source
		Label LLRPPhysicalReaderSourceLabel = new Label(shell, SWT.NONE);
		LLRPPhysicalReaderSourceLabel.setText("Physical Reader Source:");
		LLRPPhysicalReaderSourceLabel.setLayoutData(data);

		LLRPPhysicalReaderSourceText = new Text(shell, SWT.BORDER);
		LLRPPhysicalReaderSourceText
				.addVerifyListener(new LLRPPhysicalReaderSourceTextVerifyListener());
		LLRPPhysicalReaderSourceText.setBackground(SWTResourceManager.getColor(
				230, 230, 230));
		LLRPPhysicalReaderSourceText.setLayoutData(data);

		// Descriptive Reader Source
		Label LLRPDescriptiveReaderSourceLabel = new Label(shell, SWT.NONE);
		LLRPDescriptiveReaderSourceLabel.setText("Descriptive Reader Source:");
		LLRPDescriptiveReaderSourceLabel.setLayoutData(data);

		LLRPDescriptiveReaderSourceText = new Text(shell, SWT.BORDER);
		LLRPDescriptiveReaderSourceText.setBackground(SWTResourceManager
				.getColor(230, 230, 230));
		LLRPDescriptiveReaderSourceText.setLayoutData(data);

		// Reader Operation Spec ID
		Label LLRPReaderOperationSpecIDLabel = new Label(shell, SWT.NONE);
		LLRPReaderOperationSpecIDLabel.setText("Reader Operation Spec ID:");
		LLRPReaderOperationSpecIDLabel.setLayoutData(data);

		LLRPReaderOperationSpecIDText = new Text(shell, SWT.BORDER);
		LLRPReaderOperationSpecIDText
				.addVerifyListener(new LLRPReaderOperationSpecIDTextVerifyListener());
		LLRPReaderOperationSpecIDText.setBackground(SWTResourceManager
				.getColor(230, 230, 230));
		LLRPReaderOperationSpecIDText.setLayoutData(data);

		// Fill in the fields appropriately
		for (int i = 0; i < propertiesNamesNodesList.getLength(); i++) {
			if (propertiesNamesNodesList.item(i).getTextContent().equals(
					"ReaderType")) {
				LLRPReaderTypeText.setText(propertiesValuesNodesList.item(i)
						.getTextContent());
			} else if (propertiesNamesNodesList.item(i).getTextContent()
					.equals("Description")) {
				LLRPDescriptionText.setText(propertiesValuesNodesList.item(i)
						.getTextContent());
			} else if (propertiesNamesNodesList.item(i).getTextContent()
					.equals("ConnectionPointAddress")) {
				LLRPConnectionPointAddressText
						.setText(propertiesValuesNodesList.item(i)
								.getTextContent());
			} else if (propertiesNamesNodesList.item(i).getTextContent()
					.equals("ConnectionPointPort")) {
				LLRPConnectionPointPortText.setText(propertiesValuesNodesList
						.item(i).getTextContent());
			} else if (propertiesNamesNodesList.item(i).getTextContent()
					.equals("EncryptedConnectionPointAddress")) {
				LLRPEncryptedConnectionPointAddressText
						.setText(propertiesValuesNodesList.item(i)
								.getTextContent());
			} else if (propertiesNamesNodesList.item(i).getTextContent()
					.equals("EncryptedConnectionPointPort")) {
				LLRPEncryptedConnectionPointPortText
						.setText(propertiesValuesNodesList.item(i)
								.getTextContent());
			} else if (propertiesNamesNodesList.item(i).getTextContent()
					.equals("ReadTimeInterval")) {
				LLRPReadTimeIntervalText.setText(propertiesValuesNodesList
						.item(i).getTextContent());
			} else if (propertiesNamesNodesList.item(i).getTextContent()
					.equals("AdaptorClass")) {
				LLRPAdaptorClassText.setText(propertiesValuesNodesList.item(i)
						.getTextContent());
			} else if (propertiesNamesNodesList.item(i).getTextContent()
					.equals("ImplementationClass")) {
				LLRPImplClassText.setText(propertiesValuesNodesList.item(i)
						.getTextContent());
			} else if (propertiesNamesNodesList.item(i).getTextContent()
					.equals("PhysicalReaderSource")) {
				LLRPPhysicalReaderSourceText.setText(propertiesValuesNodesList
						.item(i).getTextContent());
			} else if (propertiesNamesNodesList.item(i).getTextContent()
					.equals("DescriptiveReaderSource")) {
				LLRPDescriptiveReaderSourceText
						.setText(propertiesValuesNodesList.item(i)
								.getTextContent());
			} else if (propertiesNamesNodesList.item(i).getTextContent()
					.equals("RoSpecID")) {
				LLRPReaderOperationSpecIDText.setText(propertiesValuesNodesList
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
				if (LLRPReaderTypeText.getText().equals("")
						|| LLRPDescriptionText.getText().equals("")
						|| LLRPConnectionPointAddressText.getText().equals("")
						|| LLRPConnectionPointPortText.getText().equals("")
						|| LLRPEncryptedConnectionPointAddressText.getText()
								.equals("")
						|| LLRPEncryptedConnectionPointPortText.getText()
								.equals("")
						|| LLRPReadTimeIntervalText.getText().equals("")
						|| LLRPAdaptorClassText.getText().equals("")
						|| LLRPImplClassText.getText().equals("")
						|| LLRPPhysicalReaderSourceText.getText().equals("")
						|| LLRPDescriptiveReaderSourceText.getText().equals("")
						|| LLRPReaderOperationSpecIDText.getText().equals("")) {
					MessageDialog.openError(shell, "Error",
							"All property fields are mandatory!");

				} else {
					// Fill in input array appropriately
					inputTemp.add(LLRPReaderTypeText.getText());
					inputTemp.add(LLRPDescriptionText.getText());
					inputTemp.add(LLRPConnectionPointAddressText.getText());
					inputTemp.add(LLRPConnectionPointPortText.getText());
					inputTemp.add(LLRPEncryptedConnectionPointAddressText
							.getText());
					inputTemp.add(LLRPEncryptedConnectionPointPortText
							.getText());
					inputTemp.add(LLRPReadTimeIntervalText.getText());
					inputTemp.add(LLRPAdaptorClassText.getText());
					inputTemp.add(LLRPImplClassText.getText());
					inputTemp.add(LLRPPhysicalReaderSourceText.getText());
					inputTemp.add(LLRPDescriptiveReaderSourceText.getText());
					inputTemp.add(LLRPReaderOperationSpecIDText.getText());

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

	private class LLRPReadTimeIntervalTextVerifyListener implements
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

	private class LLRPConnectionPointPortTextVerifyListener implements
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

	private class LLRPEncryptedConnectionPointPortTextVerifyListener implements
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

	private class LLRPPhysicalReaderSourceTextVerifyListener implements
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

	private class LLRPReaderOperationSpecIDTextVerifyListener implements
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
