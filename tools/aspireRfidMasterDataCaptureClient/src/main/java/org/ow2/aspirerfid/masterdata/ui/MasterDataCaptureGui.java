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

package org.ow2.aspirerfid.masterdata.ui;

import org.ow2.aspirerfid.masterdata.capture.EpcisConstants;
import org.ow2.aspirerfid.masterdata.capture.MasterDataCaptureClient;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.xml.DOMConfigurator;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

/**
 * @author Nikos Kefalakis (nkef) e-mail: nkef@ait.edu.gr
 * 
 */
public class MasterDataCaptureGui {

	private Text newVocElementUriText;
	private Label vLabel_2;
	private Label vLabel;
	private Label vLabel_4;
	private Combo captureVocabularyTypeCombo;
	private Group captureInterfaceTestGroup;
	private Label label;
	private Text vocElementAttributeValueText;
	private Text vocElementUriText;
	private Label vLabel_3;
	private Text vocElementAttributeNameText;
	private Label vLabel_2_2;
	private Label vLabel_1;
	private Button captureSubmitButton;
	private Button deleteAttributeButton;
	private Button alterValueButton;
	private Button singleDeleteButton;
	private Button alterUriButton;
	private Button deleteWdButton;
	private Button alterelementButton;
	private Button alterElementsAttributeButton;
	private Button saveNewElButton;
	private Button saveNewAttrButton;

	private Text captureServiceUrlText;
	protected Shell shell;


	// ******************************************
	private MasterDataCaptureClient captureClient = null;
	// ******************************************


	/**
	 * The logger.
	 */
	private static Log log = LogFactory.getLog(MasterDataCaptureGui.class);



	/**
	 * The endpoint URL for the capture service.
	 */
	private String captureUrl = "http://localhost:8080/epcis-repository-0.4.3-SNAPSHOT/capture";

	/**
	 * Open the window
	 */
	public void open() {
		final Display display = Display.getDefault();
		createContents();
		shell.open();
		shell.layout();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch())
				display.sleep();
		}
	}

	/**
	 * Create contents of the window
	 */
	protected void createContents() {

		DOMConfigurator.configure("bin/log4j.xml");

		shell = new Shell();
		shell.setSize(565, 436);
		shell.setText("Master Data Editor Application");

		label = new Label(shell, SWT.SEPARATOR | SWT.HORIZONTAL);
		label.setBounds(0, 45, 557, 30);
		//

		vLabel_1 = new Label(shell, SWT.NONE);
		vLabel_1.setBounds(10, 20, 152, 15);
		vLabel_1.setText("EPCIS Repository Capture URL:");

		captureServiceUrlText = new Text(shell, SWT.BORDER);
		captureServiceUrlText.setBounds(164, 15, 389, 25);
		captureServiceUrlText.setText(captureUrl);

		captureInterfaceTestGroup = new Group(shell, SWT.NONE);
		captureInterfaceTestGroup.setText("Capture Interface Test");
		captureInterfaceTestGroup.setBounds(5, 70, 543, 326);

		captureSubmitButton = new Button(captureInterfaceTestGroup, SWT.NONE);
		captureSubmitButton.setBounds(25, 160, 153, 153);
		captureSubmitButton.addMouseListener(new CaptureSubmitButtonMouseListener());

		captureSubmitButton.setText("Submit");

		vLabel_3 = new Label(captureInterfaceTestGroup, SWT.NONE);
		vLabel_3.setBounds(10, 60, 94, 15);
		vLabel_3.setText("Voc Element URI:");

		vLabel_2_2 = new Label(captureInterfaceTestGroup, SWT.NONE);
		vLabel_2_2.setBounds(10, 90, 94, 15);
		vLabel_2_2.setText("Voc Element Attr:");

		vocElementUriText = new Text(captureInterfaceTestGroup, SWT.BORDER);
		vocElementUriText.setBounds(110, 56, 188, 25);
		vocElementUriText.setText("Some_Test_Vocabulary_URI");

		vocElementAttributeNameText = new Text(captureInterfaceTestGroup, SWT.BORDER);
		vocElementAttributeNameText.setBounds(110, 86, 188, 25);
		vocElementAttributeNameText.setText("Some_Test_Vocabulary_Attr_Name");

		vocElementAttributeValueText = new Text(captureInterfaceTestGroup, SWT.BORDER);
		vocElementAttributeValueText.setBounds(110, 116, 188, 25);
		vocElementAttributeValueText.setText("Some_Test_Vocabulary_Attr_Value");

		captureVocabularyTypeCombo = new Combo(captureInterfaceTestGroup, SWT.NONE);
		captureVocabularyTypeCombo.setBounds(110, 25, 393, 21);
		captureVocabularyTypeCombo.add(EpcisConstants.READ_POINT_ID);
		captureVocabularyTypeCombo.add(EpcisConstants.BUSINESS_LOCATION_ID);
		captureVocabularyTypeCombo.add(EpcisConstants.BUSINESS_STEP_ID);
		captureVocabularyTypeCombo.add(EpcisConstants.DISPOSITION_ID);
		captureVocabularyTypeCombo.add(EpcisConstants.BUSINESS_TRANSACTION_ID);
		captureVocabularyTypeCombo.add(EpcisConstants.BUSINESS_TRANSACTION_TYPE_ID);
		captureVocabularyTypeCombo.add(EpcisConstants.EPC_CLASS_ID);

		vLabel_4 = new Label(captureInterfaceTestGroup, SWT.NONE);
		vLabel_4.setBounds(10, 30, 94, 15);
		vLabel_4.setText("Vocabulary Type:");

		vLabel = new Label(captureInterfaceTestGroup, SWT.NONE);
		vLabel.setBounds(315, 55, 107, 15);
		vLabel.setText("New Voc Element URI:");

		vLabel_2 = new Label(captureInterfaceTestGroup, SWT.NONE);
		vLabel_2.setBounds(10, 120, 124, 15);
		vLabel_2.setText("Voc Element Value:");

		newVocElementUriText = new Text(captureInterfaceTestGroup, SWT.BORDER);
		newVocElementUriText.setBounds(315, 70, 188, 25);
		newVocElementUriText.setText("New_Test_Vocabulary_URI");

		final Group modeSelectionGroup = new Group(captureInterfaceTestGroup, SWT.NONE);
		modeSelectionGroup.setText("Mode Selection");
		modeSelectionGroup.setBounds(206, 160, 297, 153);

		alterelementButton = new Button(modeSelectionGroup, SWT.RADIO);
		alterelementButton.setBounds(10, 26,83, 16);
		alterelementButton.setText("AlterElement");
		alterelementButton.setSelection(true);

		final Group group = new Group(modeSelectionGroup, SWT.NONE);
		group.setBounds(20, 41,100, 99);

		singleDeleteButton = new Button(group, SWT.RADIO);
		singleDeleteButton.setBounds(10, 30, 85, 16);
		singleDeleteButton.setText("Single Delete");

		alterUriButton = new Button(group, SWT.RADIO);
		alterUriButton.setBounds(10, 52, 85, 16);
		alterUriButton.setText("Alter URI");

		deleteWdButton = new Button(group, SWT.RADIO);
		deleteWdButton.setBounds(10, 74, 85, 16);
		deleteWdButton.setText("Delete WD");

		saveNewElButton = new Button(group, SWT.RADIO);
		saveNewElButton.setText("Save new El");
		saveNewElButton.setBounds(10, 10, 83, 16);
		saveNewElButton.setSelection(true);

		alterElementsAttributeButton = new Button(modeSelectionGroup, SWT.RADIO);
		alterElementsAttributeButton.setBounds(135, 26,138, 16);
		alterElementsAttributeButton.setText("Alter Element's Attribute");

		final Group group_1 = new Group(modeSelectionGroup, SWT.NONE);
		group_1.setBounds(150, 41,107, 80);

		deleteAttributeButton = new Button(group_1, SWT.RADIO);
		deleteAttributeButton.setBounds(10, 35, 97, 16);
		deleteAttributeButton.setText("Delete Attribute");

		alterValueButton = new Button(group_1, SWT.RADIO);
		alterValueButton.setBounds(10, 57, 85, 16);
		alterValueButton.setText("Alter Value");

		saveNewAttrButton = new Button(group_1, SWT.RADIO);
		saveNewAttrButton.setText("Save new Attr");
		saveNewAttrButton.setBounds(10, 15, 83, 16);
		saveNewAttrButton.setSelection(true);

		shell.setTabList(new Control[] {captureServiceUrlText, vLabel_1, label, captureInterfaceTestGroup});

		// mwQueryArgumentTextFields = new LinkedList();
	}

	private void captureSubmitButtonPressed() {

		// SetUp the EPCIS Repository URL
		captureClient = new MasterDataCaptureClient(captureServiceUrlText.getText());

		boolean simpleMasterDataInsertionSucceeded = false;

		if (alterElementsAttributeButton.getSelection()) {

			if (saveNewAttrButton.getSelection()){
//				attrEditorMode = "1";		
				simpleMasterDataInsertionSucceeded = captureClient.insertOrAlterVocElemAttr (captureVocabularyTypeCombo.getText(), vocElementUriText.getText(), vocElementAttributeNameText.getText(),
						 vocElementAttributeValueText.getText());
			}
			else if (alterValueButton.getSelection()) {
//				attrEditorMode = "2";
				simpleMasterDataInsertionSucceeded = captureClient.insertOrAlterVocElemAttr (captureVocabularyTypeCombo.getText(), vocElementUriText.getText(), vocElementAttributeNameText.getText(),
						 vocElementAttributeValueText.getText());
			}
			else if (deleteAttributeButton.getSelection()) {
//				attrEditorMode = "3";
				simpleMasterDataInsertionSucceeded = captureClient.deleteVocElemAttr (captureVocabularyTypeCombo.getText(), vocElementUriText.getText(), vocElementAttributeNameText.getText());
			}

		}
		else if (alterelementButton.getSelection()) {
			
			if (saveNewElButton.getSelection()){
//			    elementEditorMode = "1";	
				simpleMasterDataInsertionSucceeded = captureClient.insertVocElem(captureVocabularyTypeCombo.getText(),vocElementUriText.getText());

			}
			else if (alterUriButton.getSelection()) {
//				elementEditorMode = "2";	
				simpleMasterDataInsertionSucceeded = captureClient.alterVocElem (captureVocabularyTypeCombo.getText(),vocElementUriText.getText(),newVocElementUriText.getText());

			}
			else if (singleDeleteButton.getSelection()) {
//				elementEditorMode = "3";
				simpleMasterDataInsertionSucceeded = captureClient.deleteVocElem (captureVocabularyTypeCombo.getText(),vocElementUriText.getText());

			}
			else if (deleteWdButton.getSelection()) {
//				elementEditorMode = "4";
				simpleMasterDataInsertionSucceeded = captureClient.deleteWithDescendantsVocElem(captureVocabularyTypeCombo.getText(),vocElementUriText.getText());

			}
		}

		if (simpleMasterDataInsertionSucceeded) {
			log.debug("Master Data saccesfuly saved!\n");

		}
		else {
			log.debug("The Master Data could NOT be captured!\n");
		}

	}


	/**
	 * Launch the application
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			MasterDataCaptureGui window = new MasterDataCaptureGui();
			window.open();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	private class CaptureSubmitButtonMouseListener extends MouseAdapter {
		public void mouseDown(final MouseEvent e) {
			captureSubmitButtonPressed();
		}
	}


}
