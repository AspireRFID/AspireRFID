/*
 * Copyright (C) 2008-2010, Aspire
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


package org.ow2.aspirerfid.beg.simmulator;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBException;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.*;
import org.fosstrak.epcis.model.AttributeType;
import org.fosstrak.epcis.model.VocabularyElementType;

import org.eclipse.swt.widgets.MessageBox;

/**
 * @author Nikos Kefalakis (nkef) e-mail: nkef@ait.edu.gr
 *
 */
public class BegSimmAppl {

	private static String CAPTURE_URL = "http://localhost:8080/aspireRfidEpcisRepository/capture";
	private static String QUERY_URL = "http://localhost:8080/aspireRfidEpcisRepository/query";
	private ArrayList<VocabularyElementType> recievedVocabularyElementList = new ArrayList<VocabularyElementType>();
	private BegSimmCore begSimmCore = new BegSimmCore();
	
	private MessageBox errorMessageBox;
	private MessageBox infoMessageBox;

	private Text epcisCaptureUrlText;
	private Text epcisQueryUrlText;
	private Text actionTypeText;
	private Text eventTypeText;
	private Combo availableBusinessEventsCombo;
	protected Shell shell;

	/**
	 * Launch the application
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			BegSimmAppl window = new BegSimmAppl();
			window.open();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

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
		shell = new Shell();
		shell.setLayout(new FillLayout());
		shell.setSize(672, 536);
		shell.setText("BEG Simmulator");

		final Composite composite = new Composite(shell, SWT.NONE);
		composite.setLayout(new FillLayout());

		final ScrolledComposite scrolledComposite = new ScrolledComposite(composite, SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL);

		final Composite begSimmComposite = new Composite(scrolledComposite, SWT.NONE);
		begSimmComposite.setLocation(0, 0);

		final Group startBusinessEventGroup = new Group(begSimmComposite, SWT.NONE);
		startBusinessEventGroup.setBounds(10, 113, 520, 221);
		startBusinessEventGroup.setText("Business Event Generation");

		final Label availableEventsLabel = new Label(startBusinessEventGroup, SWT.NONE);
		availableEventsLabel.setFont(SWTResourceManager.getFont("", 11, SWT.NONE));
		availableEventsLabel.setBounds(10, 22, 152, 23);
		availableEventsLabel.setText("Avail. Business Events:");

		availableBusinessEventsCombo = new Combo(startBusinessEventGroup, SWT.NONE);
		availableBusinessEventsCombo.addSelectionListener(new AvailableBusinessEventsComboSelectionListener());
		availableBusinessEventsCombo.setBounds(168, 22, 243, 21);

		final Button startCaptureButton = new Button(startBusinessEventGroup, SWT.NONE);
		startCaptureButton.addMouseListener(new StartCaptureButtonMouseListener());
		startCaptureButton.setBounds(177, 177, 140, 23);
		startCaptureButton.setText("Event Generation");

		final Button refreshAvailableBusinessEventsButton = new Button(startBusinessEventGroup, SWT.NONE);
		refreshAvailableBusinessEventsButton.addMouseListener(new RefreshAvailableBusinessEventsButtonMouseListener());
		refreshAvailableBusinessEventsButton.setImage(SWTResourceManager.getImage(BegSimmAppl.class, "/icons/img/s_reload.png"));
		refreshAvailableBusinessEventsButton.setBounds(417, 22, 28, 23);
		refreshAvailableBusinessEventsButton.setText("button");

		eventTypeText = new Text(startBusinessEventGroup, SWT.BORDER);
		eventTypeText.setBounds(168, 79, 219, 25);

		final Label availableEventsLabel_1 = new Label(startBusinessEventGroup, SWT.NONE);
		availableEventsLabel_1.setBounds(10, 79, 146, 23);
		availableEventsLabel_1.setFont(SWTResourceManager.getFont("", 11, SWT.NONE));
		availableEventsLabel_1.setText("Event Type:");

		actionTypeText = new Text(startBusinessEventGroup, SWT.BORDER);
		actionTypeText.setBounds(168, 110, 219, 25);

		final Label availableEventsLabel_1_1 = new Label(startBusinessEventGroup, SWT.NONE);
		availableEventsLabel_1_1.setBounds(10, 110, 140, 23);
		availableEventsLabel_1_1.setFont(SWTResourceManager.getFont("", 11, SWT.NONE));
		availableEventsLabel_1_1.setText("Action:");

		final Group connectionUrlsGroup = new Group(begSimmComposite, SWT.NONE);
		connectionUrlsGroup.setText("Connection URLs");
		connectionUrlsGroup.setBounds(10, 10, 520, 97);

		final Label availableEventsLabel_1_2 = new Label(connectionUrlsGroup, SWT.NONE);
		availableEventsLabel_1_2.setBounds(10, 25, 125, 23);
		availableEventsLabel_1_2.setFont(SWTResourceManager.getFont("", 11, SWT.NONE));
		availableEventsLabel_1_2.setText("EPCIS Query URL:");

		final Label availableEventsLabel_1_1_1 = new Label(connectionUrlsGroup, SWT.NONE);
		availableEventsLabel_1_1_1.setBounds(10, 56, 137, 23);
		availableEventsLabel_1_1_1.setFont(SWTResourceManager.getFont("", 11, SWT.NONE));
		availableEventsLabel_1_1_1.setText("EPCIS Capture URL:");

		epcisQueryUrlText = new Text(connectionUrlsGroup, SWT.BORDER);
		epcisQueryUrlText.setBounds(153, 25, 344, 25);
		epcisQueryUrlText.setText(QUERY_URL);

		epcisCaptureUrlText = new Text(connectionUrlsGroup, SWT.BORDER);
		epcisCaptureUrlText.setBounds(153, 56, 344, 25);
		epcisCaptureUrlText.setText(CAPTURE_URL);
		begSimmComposite.setSize(552, 356);
		scrolledComposite.setContent(begSimmComposite);

		//
		errorMessageBox = new MessageBox(shell, SWT.ICON_ERROR | SWT.OK);
		errorMessageBox.setText("Error");

		
		infoMessageBox = new MessageBox(shell, SWT.ICON_INFORMATION | SWT.OK);
		infoMessageBox.setText("Information");
	}

	private void fillAvailableBusinessEventsCombo() {
		List<VocabularyElementType> vocabularyElementList = null;

		recievedVocabularyElementList.clear();

		try {

			vocabularyElementList = begSimmCore.getEventList(epcisQueryUrlText.getText());
			// vocabularyElementList =
			// service_port.getEventList(preferences.getString(PreferenceConstants.P_EpcisRepositoryQueryURL));
			if (vocabularyElementList != null && vocabularyElementList.size() > 0) {
				for (VocabularyElementType vocabularyElement : vocabularyElementList) {

					for (AttributeType vocElementAttribute : vocabularyElement.getAttribute()) {
						if (vocElementAttribute.getId().endsWith("event_name")) {

							availableBusinessEventsCombo.add(vocElementAttribute.getContent().get(0).toString());
							recievedVocabularyElementList.add(vocabularyElement);

						}
					}
				}
				if (availableBusinessEventsCombo.getItemCount() > 0) {
					availableBusinessEventsCombo.select(0);
				}
			}

		}
		catch (Exception e) {
			e.printStackTrace();
			errorMessageBox.setMessage("Query Exception! \nThe Events could not be retrieved from \nthe Information Shairing Repository!");
			errorMessageBox.open();
//			MessageDialog.openError(shell, "Query Exception", "The Events could not be retrieved from \nthe Information Shairing Repository!");
		}

	}

	private class StartCaptureButtonMouseListener extends MouseAdapter {
		public void mouseDown(final MouseEvent e) {

			int vocabularyElementSelected = availableBusinessEventsCombo.getSelectionIndex();

			if (vocabularyElementSelected == -1) {
				errorMessageBox.setMessage("Please Select a Buisness Event.");
				errorMessageBox.open();
//				MessageDialog.openError(shell, "Error", "Please Select a Buisness Event.");
				return;
			}

			boolean saccesful = false;
			try {
				saccesful = begSimmCore.generateDataForTransEvent(recievedVocabularyElementList.get(vocabularyElementSelected), epcisCaptureUrlText
						.getText());
			}
			catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				errorMessageBox.setMessage(e1.getMessage());
				errorMessageBox.open();
//				MessageDialog.openError(shell, "Error!", e1.getMessage());
			}
			catch (JAXBException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				errorMessageBox.setMessage(e1.getMessage());
				errorMessageBox.open();
//				MessageDialog.openError(shell, "Error!", e1.getMessage());
			}

			infoMessageBox.setMessage("Event Generated Successfully!");
			infoMessageBox.open();
//			MessageDialog.openInformation(shell, "Success!", "Event Generated Successfully!");

		}
	}

	private class RefreshAvailableBusinessEventsButtonMouseListener extends MouseAdapter {
		public void mouseDown(final MouseEvent e) {

			availableBusinessEventsCombo.removeAll();
			fillAvailableBusinessEventsCombo();

		}
	}

	private class AvailableBusinessEventsComboSelectionListener extends SelectionAdapter {
		public void widgetSelected(final SelectionEvent e) {

			int vocabularyElementSelected = availableBusinessEventsCombo.getSelectionIndex();

			if (vocabularyElementSelected == -1) {
				return;
			}

			VocabularyElementType selectedVocabularyElementType = recievedVocabularyElementList.get(vocabularyElementSelected);
			BusinessCtx selectedVocabularyBusinessCtx = begSimmCore.getBusinessCtx(selectedVocabularyElementType);
			eventTypeText.setText(selectedVocabularyBusinessCtx.getEventType());
			actionTypeText.setText(selectedVocabularyBusinessCtx.getAction().value());

		}
	}

}
