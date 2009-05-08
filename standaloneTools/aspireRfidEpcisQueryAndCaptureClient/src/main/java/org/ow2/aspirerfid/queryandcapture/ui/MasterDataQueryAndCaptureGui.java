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



package org.ow2.aspirerfid.queryandcapture.ui;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.TimeZone;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import org.fosstrak.epcis.model.ArrayOfString;
import org.fosstrak.epcis.model.QueryParam;
import org.ow2.aspirerfid.queryandcapture.capture.EpcisConstants;
import org.ow2.aspirerfid.queryandcapture.capture.MasterDataCaptureClient;
import org.ow2.aspirerfid.queryandcapture.query.MasterDataQueryClient;
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
public class MasterDataQueryAndCaptureGui {

	private Label vLabel_4;
	private Combo captureVocabularyTypeCombo;
	private Combo vocabularyTypeCombo;
	private Button querySubmitButton;
	private Label vLabel_2_1;
	private Text eq_nameText;
	private Label vLabel_2;
	private Group queryInterfaceTestGroup;
	private Group captureInterfaceTestGroup;
	private Text queryServiceUrlText;
	private Label vLabel;
	private Label label;
	private Text buissnessStepValueText;
	private Text buissnessStepUriText;
	private Label vLabel_3;
	private Text buissnessStepAttributeText;
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
	private Button includeAttributesButton;
	private Button includeChildrenButton;

	private Text captureServiceUrlText;
	protected Shell shell;

	/**
	 * Miscellaneous numeric formats used in formatting.
	 */
	private static final DecimalFormat XX_FORMAT = new DecimalFormat("00");
	private static final DecimalFormat XXX_FORMAT = new DecimalFormat("000");
	private static final DecimalFormat XXXX_FORMAT = new DecimalFormat("0000");

	// ******************************************
	private MasterDataCaptureClient captureClient = null;
	// ******************************************

	private MasterDataQueryClient queryClient = null;

	/**
	 * The logger.
	 */
	private static Log log = LogFactory.getLog(MasterDataQueryAndCaptureGui.class);

	/**
	 * The endpoint URL for the query service.
	 */
	private String queryUrl = "http://localhost:8080/aspireRfidEpcisRepository/query";
	
	
	/**
	 * The endpoint URL for the capture service.
	 */
	private String captureUrl = "http://localhost:8080/aspireRfidEpcisRepository/capture";

	
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
		shell.setSize(596, 611);
		shell.setText("Master Data Editor Application");

		label = new Label(shell, SWT.SEPARATOR | SWT.HORIZONTAL);
		label.setBounds(0, 70, 588, 30);
		//

		vLabel_1 = new Label(shell, SWT.NONE);
		vLabel_1.setBounds(10, 20, 152, 15);
		vLabel_1.setText("EPCIS Repository Capture URL:");

		captureServiceUrlText = new Text(shell, SWT.BORDER);
		captureServiceUrlText.setBounds(164, 15, 389, 25);
		captureServiceUrlText.setText(captureUrl);

		vLabel = new Label(shell, SWT.NONE);
		vLabel.setBounds(10, 50, 152, 15);
		vLabel.setText("EPCIS Repository Query URL:");

		queryServiceUrlText = new Text(shell, SWT.BORDER);
		queryServiceUrlText.setBounds(164, 45, 389, 25);
		queryServiceUrlText.setText(queryUrl);

		captureInterfaceTestGroup = new Group(shell, SWT.NONE);
		captureInterfaceTestGroup.setText("Capture Interface Test");
		captureInterfaceTestGroup.setBounds(10, 105, 563, 248);

		captureSubmitButton = new Button(captureInterfaceTestGroup, SWT.NONE);
		captureSubmitButton.setBounds(10, 124, 94, 81);
		captureSubmitButton.addMouseListener(new CaptureSubmitButtonMouseListener());

		captureSubmitButton.setText("Submit");

		vLabel_3 = new Label(captureInterfaceTestGroup, SWT.NONE);
		vLabel_3.setBounds(10, 60, 107, 15);
		vLabel_3.setText("Voc Element URI:");

		vLabel_2_2 = new Label(captureInterfaceTestGroup, SWT.NONE);
		vLabel_2_2.setBounds(10, 90, 124, 15);
		vLabel_2_2.setText("Voc Element Attr/Value:");

		buissnessStepUriText = new Text(captureInterfaceTestGroup, SWT.BORDER);
		buissnessStepUriText.setBounds(140, 55, 413, 25);
		buissnessStepUriText.setText("Some_Test_BuissnessStep_URI");

		buissnessStepAttributeText = new Text(captureInterfaceTestGroup, SWT.BORDER);
		buissnessStepAttributeText.setBounds(140, 85, 208, 25);
		buissnessStepAttributeText.setText("Some_Test_BuissnessStep_Attribute");

		buissnessStepValueText = new Text(captureInterfaceTestGroup, SWT.BORDER);
		buissnessStepValueText.setBounds(354, 85, 199, 25);
		buissnessStepValueText.setText("Some_Test_BuissnessStep_Value");

		alterelementButton = new Button(captureInterfaceTestGroup, SWT.RADIO);
		alterelementButton.setText("AlterElement");
		alterelementButton.setBounds(270, 130, 83, 16);

		alterElementsAttributeButton = new Button(captureInterfaceTestGroup, SWT.RADIO);
		alterElementsAttributeButton.setText("Alter Element's Attribute");
		alterElementsAttributeButton.setBounds(395, 130, 138, 16);

		final Group group = new Group(captureInterfaceTestGroup, SWT.NONE);
		group.setBounds(280, 145, 100, 99);

		singleDeleteButton = new Button(group, SWT.RADIO);
		singleDeleteButton.setBounds(10, 30, 85, 16);
		singleDeleteButton.setText("Single Delete");

		alterUriButton = new Button(group, SWT.RADIO);
		alterUriButton.setBounds(10, 52, 85, 16);
		alterUriButton.setText("Alter URI");

		deleteWdButton = new Button(group, SWT.RADIO);
		deleteWdButton.setBounds(10, 74, 85, 16);
		deleteWdButton.setText("Delete WD");

		final Button saveNewElButton = new Button(group, SWT.RADIO);
		saveNewElButton.setText("Save new El");
		saveNewElButton.setBounds(10, 10, 83, 16);

		final Group group_1 = new Group(captureInterfaceTestGroup, SWT.NONE);
		group_1.setBounds(410, 145, 107, 80);

		deleteAttributeButton = new Button(group_1, SWT.RADIO);
		deleteAttributeButton.setBounds(10, 35, 124, 16);
		deleteAttributeButton.setText("Delete Attribute");

		alterValueButton = new Button(group_1, SWT.RADIO);
		alterValueButton.setBounds(10, 57, 85, 16);
		alterValueButton.setText("Alter Value");

		final Button saveNewAttrButton = new Button(group_1, SWT.RADIO);
		saveNewAttrButton.setText("Save new Attr");
		saveNewAttrButton.setBounds(10, 15, 83, 16);

		captureVocabularyTypeCombo = new Combo(captureInterfaceTestGroup, SWT.NONE);
		captureVocabularyTypeCombo.setBounds(140, 24, 413, 25);
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

		queryInterfaceTestGroup = new Group(shell, SWT.NONE);
		queryInterfaceTestGroup.setText("Query Interface Test");
		queryInterfaceTestGroup.setBounds(10, 365, 563, 184);

		vLabel_2 = new Label(queryInterfaceTestGroup, SWT.NONE);
		vLabel_2.setBounds(10, 36, 79, 15);
		vLabel_2.setText("EQ_name");

		eq_nameText = new Text(queryInterfaceTestGroup, SWT.BORDER);
		eq_nameText.setBounds(95, 30, 315, 25);

		vLabel_2_1 = new Label(queryInterfaceTestGroup, SWT.NONE);
		vLabel_2_1.setBounds(10, 66, 84, 15);
		vLabel_2_1.setText("Vocabulary Type:");

		querySubmitButton = new Button(queryInterfaceTestGroup, SWT.NONE);
		querySubmitButton.addMouseListener(new QuerySubmitButtonMouseListener());
		querySubmitButton.setBounds(255, 150, 48, 25);
		querySubmitButton.setText("Submit");
		
		vocabularyTypeCombo = new Combo(queryInterfaceTestGroup, SWT.NONE);
		vocabularyTypeCombo.setBounds(95, 63, 315, 25);
		vocabularyTypeCombo.add(EpcisConstants.READ_POINT_ID);
		vocabularyTypeCombo.add(EpcisConstants.BUSINESS_LOCATION_ID);
		vocabularyTypeCombo.add(EpcisConstants.BUSINESS_STEP_ID);
		vocabularyTypeCombo.add(EpcisConstants.DISPOSITION_ID);
		vocabularyTypeCombo.add(EpcisConstants.BUSINESS_TRANSACTION_ID);
		vocabularyTypeCombo.add(EpcisConstants.BUSINESS_TRANSACTION_TYPE_ID);
		vocabularyTypeCombo.add(EpcisConstants.EPC_CLASS_ID);

		includeAttributesButton = new Button(queryInterfaceTestGroup, SWT.CHECK);
		includeAttributesButton.setText("Include Attributes");
		includeAttributesButton.setBounds(440, 25, 113, 16);

		includeChildrenButton = new Button(queryInterfaceTestGroup, SWT.CHECK);
		includeChildrenButton.setText("Include Children");
		includeChildrenButton.setBounds(440, 47, 113, 16);
		
		shell.setTabList(new Control[] { captureServiceUrlText, vLabel_1, label, vLabel, queryServiceUrlText, captureInterfaceTestGroup,
				queryInterfaceTestGroup });

		// mwQueryArgumentTextFields = new LinkedList();
	}

	private void captureSubmitButtonPressed() {


		// SetUp the EPCIS Repository URL
		captureClient = new MasterDataCaptureClient(captureServiceUrlText.getText());

		boolean simpleMasterDataInsertionSucceeded = false;

		if (alterElementsAttributeButton.getSelection()) {

			String attrEditorMode = "1";
			if (alterValueButton.getSelection()) {
				attrEditorMode = "2";
			}
			else if (deleteAttributeButton.getSelection()) {
				attrEditorMode = "3";
			}

			simpleMasterDataInsertionSucceeded = captureClient.simpleMasterDataAndAttributeEdit(captureVocabularyTypeCombo.getText(), buissnessStepUriText
					.getText(), buissnessStepAttributeText.getText(), buissnessStepValueText.getText(), attrEditorMode);

		}
		else if (alterelementButton.getSelection()) {
			String elementEditorMode = "1";
			if (alterUriButton.getSelection()) {
				elementEditorMode = "2";
			}
			else if (singleDeleteButton.getSelection()) {
				elementEditorMode = "3";
			}
			else if (deleteWdButton.getSelection()) {
				elementEditorMode = "4";
			}
			simpleMasterDataInsertionSucceeded = captureClient.simpleMasterDataEdit(captureVocabularyTypeCombo.getText(),
					buissnessStepUriText.getText(), elementEditorMode);

		}

		if (simpleMasterDataInsertionSucceeded) {
			log.debug("Master Data saccesfuly saved!\n");

		}
		else {
			log.debug("The Master Data could NOT be captured!\n");
		}

	}

	/**
	 * Checks if the provided queryUrl is still a valid URL and updates the
	 * query client in case it has changed.
	 */
	private void checkQueryUrl(String url, MasterDataQueryClient queryClient) {
		if (url == null || "".equals(url)) {
			// query url has been deleted ... replace with previous url
			captureServiceUrlText.setText(queryUrl);
		}
		else if (!url.equals(queryUrl)) {
			// query url has changed
			System.out.println("Query service URL has changed, updating query client");
			queryUrl = queryClient.updateEndpointAddress(captureServiceUrlText.getText());
			captureServiceUrlText.setText(queryUrl);
		}
	}

	/**
	 * 
	 */
	private void querySubmitButtonPressed() {
		// SetUp the EPCIS Repository Query URL
		queryClient = new MasterDataQueryClient(queryServiceUrlText.getText());

		/**
		 * Contains the data for the result table.
		 */
		Object[][] data = {};

		try {
			checkQueryUrl(captureServiceUrlText.getText(), queryClient);
			queryClient.clearParameters();

			QueryParam queryParam = new QueryParam();
			queryParam.setName("eventType");
			queryParam.setValue("SimpleMasterDataQuery");
			queryClient.addParameter(queryParam);

			QueryParam param2 = new QueryParam();
			param2.setName("includeAttributes");
			param2.setValue(includeAttributesButton.getSelection());
			queryClient.addParameter(param2);
			QueryParam param3 = new QueryParam();
			param3.setName("includeChildren");
			param3.setValue(includeChildrenButton.getSelection());
			queryClient.addParameter(param3);

			ArrayOfString vocabularyNames = new ArrayOfString();
			vocabularyNames.getString().add(eq_nameText.getText());
			QueryParam param4 = new QueryParam();
			param4.setName("EQ_name");
			param4.setValue(vocabularyNames);
			queryClient.addParameter(param4);

			ArrayOfString vocabularyType = new ArrayOfString();
			
			vocabularyType.getString().add(vocabularyTypeCombo.getText());
			QueryParam param5 = new QueryParam();
			param5.setName("vocabularyName");
			param5.setValue(vocabularyType);
			queryClient.addParameter(param5);

			data = queryClient.runMasterDataQuery();
			
			if (!data.equals(null))
			{
				System.out.println("Data retrieved from the query:");
				for(int i =0 ; i<data.length; i++){
					for(int j =0 ; j<data[i].length; j++){
						if(data[i][j]!=null){
							System.out.println(data[i][j]);
						}
					}
				}
			}
			
			
		}
		catch (ParseException e) {
			String msg = "Unable to parse a Time value.";
			System.out.print("\n" + msg + "\n");
			StringWriter sw = new StringWriter();
			PrintWriter pw = new PrintWriter(sw);
			e.printStackTrace(pw);
			System.out.print(sw.toString());
			JFrame frame = new JFrame();
			JOptionPane.showMessageDialog(frame, msg + "\n" + e.getMessage(), "Service invocation error", JOptionPane.ERROR_MESSAGE);
		}
		catch (Exception e) {
			String msg = "Unexpected error while invoking EPCIS Query Interface.";
			System.out.print("\n" + msg + "\n");
			System.out.print(e.toString());
			StringWriter sw = new StringWriter();
			PrintWriter pw = new PrintWriter(sw);
			e.printStackTrace(pw);
			System.out.print(sw.toString());
			JFrame frame = new JFrame();
			JOptionPane.showMessageDialog(frame, msg + "\n" + e.toString(), "Service invocation error", JOptionPane.ERROR_MESSAGE);
		}
	}

	/**
	 * Formats a <code>Calendar</code> value into an ISO8601-compliant date/time
	 * string.
	 * 
	 * @param cal
	 *            The time value to be formatted into a date/time string.
	 * @return The formatted date/time string.
	 */
	private static String format(final Calendar cal) {
		if (cal == null) {
			throw new IllegalArgumentException("argument can not be null");
		}

		// determine era and adjust year if necessary
		int year = cal.get(Calendar.YEAR);
		if (cal.isSet(Calendar.ERA) && cal.get(Calendar.ERA) == GregorianCalendar.BC) {
			/**
			 * calculate year using astronomical system: year n BCE =>
			 * astronomical year -n + 1
			 */
			year = 0 - year + 1;
		}

		/**
		 * the format of the date/time string is: YYYY-MM-DDThh:mm:ss.SSSTZD
		 * note that we cannot use java.text.SimpleDateFormat for formatting
		 * because it can't handle years <= 0 and TZD's
		 */
		StringBuilder buf = new StringBuilder();
		// year ([-]YYYY)
		buf.append(XXXX_FORMAT.format(year));
		buf.append('-');
		// month (MM)
		buf.append(XX_FORMAT.format(cal.get(Calendar.MONTH) + 1));
		buf.append('-');
		// day (DD)
		buf.append(XX_FORMAT.format(cal.get(Calendar.DAY_OF_MONTH)));
		buf.append('T');
		// hour (hh)
		buf.append(XX_FORMAT.format(cal.get(Calendar.HOUR_OF_DAY)));
		buf.append(':');
		// minute (mm)
		buf.append(XX_FORMAT.format(cal.get(Calendar.MINUTE)));
		buf.append(':');
		// second (ss)
		buf.append(XX_FORMAT.format(cal.get(Calendar.SECOND)));
		buf.append('.');
		// millisecond (SSS)
		buf.append(XXX_FORMAT.format(cal.get(Calendar.MILLISECOND)));
		// time zone designator (+/-hh:mm)
		buf.append(getTimeZone(cal));
		return buf.toString();
	}

	/**
	 * Returns the time zone designator in a ISO6601-compliant format from the
	 * given <code>Calendar</code> value.
	 * 
	 * @param cal
	 *            The Calendar to be formatted.
	 * @return The time zone designator from the given Calendar.
	 */
	private static String getTimeZone(final Calendar cal) {
		StringBuilder buf = new StringBuilder();
		TimeZone tz = cal.getTimeZone();
		// determine offset of timezone from UTC (incl. daylight saving)
		int offset = tz.getOffset(cal.getTimeInMillis());
		int hours = Math.abs((offset / (60 * 1000)) / 60);
		int minutes = Math.abs((offset / (60 * 1000)) % 60);
		buf.append(offset < 0 ? '-' : '+');
		buf.append(XX_FORMAT.format(hours));
		buf.append(':');
		buf.append(XX_FORMAT.format(minutes));
		return buf.toString();
	}

	/**
	 * Launch the application
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			MasterDataQueryAndCaptureGui window = new MasterDataQueryAndCaptureGui();
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

	private class QuerySubmitButtonMouseListener extends MouseAdapter {
		public void mouseDown(final MouseEvent e) {

			querySubmitButtonPressed();

		}
	}

}
