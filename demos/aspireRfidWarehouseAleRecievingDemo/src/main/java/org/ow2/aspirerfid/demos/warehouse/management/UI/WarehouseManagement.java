/*
 * Copyright (c) 2008-2010, Aspire
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

package org.ow2.aspirerfid.demos.warehouse.management.UI;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.InputStream;
import java.net.*;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.*;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.event.*;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.xml.DOMConfigurator;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Vector;

import org.ow2.aspirerfid.demos.warehouse.management.beg.CaptureReport;
import org.ow2.aspirerfid.demos.warehouse.management.tools.*;

/**
 * @author nkef (Nikos Kefalakis)
 *
 */
public class WarehouseManagement {

	private JTextField epcisRepositoryURLTextField;
	private JTextField aleListeningPortTextField;
	/**
	 * The logger.
	 */
	private static Log log = LogFactory.getLog(WarehouseManagement.class);

	private static JTextField offeringHourTextField;
	private static JTextField offeringDateTextField;
	private static JTextField entryHourTextField;
	private static JTextField entryDateTextField;
	private static JTextField userIDTextField;
	private static JTextField zoneIDTextField;
	private static JTextField warehouseIDTextField;
	private static JTextField invoiceIDTextField;
	private JFrame frame;
	private JTable deliveryTable;
	private static DefaultTableModel deliveryTableModel;
	int selectedRow;
	CaptureReport captureReport = null;

	/**
	 * Launch the application
	 * 
	 * @param args
	 */
	public static void main(String args[]) {
		try {
			WarehouseManagement window = new WarehouseManagement();
			window.frame.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * Create the application
	 */
	public WarehouseManagement() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame
	 */
	private void initialize() {

		final JTabbedPane tabbedPane;
		final JPanel deliveryPanel;
		final JLabel entryDateLabel;
		final JLabel entryDateLabel_1;
		final JLabel entryDateLabel_2;
		final JLabel entryDateLabel_3;
		final JLabel entryDateLabel_3_1;
		final JLabel entryDateLabel_3_2;
		final JPanel shipmentPanel;
		final JLabel entryDateLabel_3_1_1;
		final JLabel entryDateLabel_3_1_2;
		final JScrollPane scrollPane;
		final JButton printReportButton;
		final JButton saveReportButton;
		final JButton activateDoorButton;
		final JButton deactivateDoorButton;
		final JButton clearReportButton;
		final JPanel panel;
		final JLabel entryDateLabel_3_3;
		final JLabel entryDateLabel_2_1;
		frame = new JFrame();
		frame.getContentPane().setLayout(new BorderLayout());
		frame.setTitle("Warehouse Management");
		frame.setBounds(100, 100, 1011, 625);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		tabbedPane = new JTabbedPane();
		frame.getContentPane().add(tabbedPane);

		deliveryPanel = new JPanel();
		deliveryPanel.setLayout(null);
		tabbedPane.addTab("Delivery Counter", null, deliveryPanel, null);

		entryDateLabel = new JLabel();
		entryDateLabel.setText("Entry Date .........");
		entryDateLabel.setBounds(533, 23, 117, 16);
		deliveryPanel.add(entryDateLabel);

		entryDateLabel_1 = new JLabel();
		entryDateLabel_1.setText("User ID ................");
		entryDateLabel_1.setBounds(10, 94, 117, 16);
		deliveryPanel.add(entryDateLabel_1);

		entryDateLabel_2 = new JLabel();
		entryDateLabel_2.setText("Invoice ID ............");
		entryDateLabel_2.setBounds(10, 23, 117, 16);
		deliveryPanel.add(entryDateLabel_2);

		entryDateLabel_3 = new JLabel();
		entryDateLabel_3.setText("Warehouse ID....");
		entryDateLabel_3.setBounds(10, 45, 117, 16);
		deliveryPanel.add(entryDateLabel_3);

		entryDateLabel_3_1 = new JLabel();
		entryDateLabel_3_1.setText("Zone ID ................");
		entryDateLabel_3_1.setBounds(10, 67, 117, 16);
		deliveryPanel.add(entryDateLabel_3_1);

		entryDateLabel_3_2 = new JLabel();
		entryDateLabel_3_2.setText("Entry Hour .........");
		entryDateLabel_3_2.setBounds(533, 45, 117, 16);
		deliveryPanel.add(entryDateLabel_3_2);

		entryDateLabel_3_1_1 = new JLabel();
		entryDateLabel_3_1_1.setText("Offering Date ....");
		entryDateLabel_3_1_1.setBounds(533, 70, 117, 16);
		deliveryPanel.add(entryDateLabel_3_1_1);

		entryDateLabel_3_1_2 = new JLabel();
		entryDateLabel_3_1_2.setText("Offering Hour ....");
		entryDateLabel_3_1_2.setBounds(533, 94, 117, 16);
		deliveryPanel.add(entryDateLabel_3_1_2);

		invoiceIDTextField = new JTextField();
		invoiceIDTextField.setBounds(105, 19, 365, 20);
		deliveryPanel.add(invoiceIDTextField);

		warehouseIDTextField = new JTextField();
		warehouseIDTextField.setBounds(105, 43, 365, 20);
		deliveryPanel.add(warehouseIDTextField);

		zoneIDTextField = new JTextField();
		zoneIDTextField.setBounds(105, 66, 365, 20);
		deliveryPanel.add(zoneIDTextField);

		userIDTextField = new JTextField();
		userIDTextField.setBounds(105, 90, 365, 20);
		deliveryPanel.add(userIDTextField);

		entryDateTextField = new JTextField();
		entryDateTextField.setBounds(623, 19, 365, 20);
		deliveryPanel.add(entryDateTextField);

		entryHourTextField = new JTextField();
		entryHourTextField.setBounds(623, 41, 365, 20);
		deliveryPanel.add(entryHourTextField);

		offeringDateTextField = new JTextField();
		offeringDateTextField.setBounds(623, 66, 365, 20);
		deliveryPanel.add(offeringDateTextField);

		offeringHourTextField = new JTextField();
		offeringHourTextField.setBounds(623, 90, 365, 20);
		deliveryPanel.add(offeringHourTextField);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 129, 978, 355);
		deliveryPanel.add(scrollPane);

		deliveryTableModel = new DefaultTableModel();// All Clients Items
		deliveryTableModel.addColumn("Company");
		deliveryTableModel.addColumn("Item Code");
		deliveryTableModel.addColumn("Description");
		deliveryTableModel.addColumn("Quantity Delivered");
		deliveryTableModel.addColumn("Expected Quantity");
		deliveryTableModel.addColumn("Quantity Remain");
		deliveryTableModel.addColumn("Delivery Date");
		deliveryTableModel.addColumn("Measurement ID");
		deliveryTableModel.addColumn("Quantity");
		deliveryTable = new JTable(deliveryTableModel);
		deliveryTable.setFont(new Font("Arial Narrow", Font.PLAIN, 10));
		deliveryTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		scrollPane.setViewportView(deliveryTable);

		ListSelectionModel rowSM = deliveryTable.getSelectionModel();

		printReportButton = new JButton();
		printReportButton.setText("Print Report");
		printReportButton.setBounds(860, 520, 117, 26);
		deliveryPanel.add(printReportButton);

		saveReportButton = new JButton();
		saveReportButton.setText("Save Report");
		saveReportButton.setBounds(614, 520, 117, 26);
		deliveryPanel.add(saveReportButton);

		activateDoorButton = new JButton();
		activateDoorButton
				.addMouseListener(new ActivateDoorButtonMouseListener());
		activateDoorButton.setText("Activate Door");
		activateDoorButton.setBounds(50, 520, 117, 26);
		deliveryPanel.add(activateDoorButton);

		deactivateDoorButton = new JButton();
		deactivateDoorButton
				.addMouseListener(new DeactivateDoorButtonMouseListener());
		deactivateDoorButton.setText("Dectivate Door");
		deactivateDoorButton.setBounds(173, 520, 117, 26);
		deliveryPanel.add(deactivateDoorButton);

		clearReportButton = new JButton();
		clearReportButton
				.addMouseListener(new ClearReportButtonMouseListener());
		clearReportButton.setText("Clear Report");
		clearReportButton.setBounds(737, 520, 117, 26);
		deliveryPanel.add(clearReportButton);
		rowSM.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				// Ignore extra messages.
				if (e.getValueIsAdjusting())
					return;

				ListSelectionModel lsm = (ListSelectionModel) e.getSource();
				if (lsm.isSelectionEmpty()) {
					// no rows are selected
				} else {
					selectedRow = lsm.getMinSelectionIndex();
					System.out.println("selectedRow = " + selectedRow);

				}
			}
		});

		shipmentPanel = new JPanel();
		tabbedPane.addTab("Shipment", null, shipmentPanel, null);

		panel = new JPanel();
		panel.setLayout(null);
		tabbedPane.addTab("Door Config", null, panel, null);

		aleListeningPortTextField = new JTextField();
		aleListeningPortTextField.setText("9999");
		aleListeningPortTextField.setBounds(172, 41, 330, 20);
		panel.add(aleListeningPortTextField);

		epcisRepositoryURLTextField = new JTextField();
		epcisRepositoryURLTextField
				.setText("http://localhost:8080/aspire0.3.0EpcisRepository/capture");
		epcisRepositoryURLTextField.setBounds(172, 65, 330, 20);
		panel.add(epcisRepositoryURLTextField);

		entryDateLabel_3_3 = new JLabel();
		entryDateLabel_3_3.setText("EPCIS Rep. URL .........");
		entryDateLabel_3_3.setBounds(51, 67, 117, 16);
		panel.add(entryDateLabel_3_3);

		entryDateLabel_2_1 = new JLabel();
		entryDateLabel_2_1.setText("ALE Listening Port ....");
		entryDateLabel_2_1.setBounds(51, 43, 117, 16);
		panel.add(entryDateLabel_2_1);

	}

	/**
	 * @param String
	 *            invoiceID
	 */
	public static void setInvoiceIDTextField(String invoiceID) {
		invoiceIDTextField.setText(invoiceID);
	}

	/**
	 * @param String
	 *            warehouseID
	 */
	public static void setWarehouseIDTextField(String warehouseID) {
		warehouseIDTextField.setText(warehouseID);
	}

	/**
	 * @param String
	 *            zoneID
	 */
	public static void setZoneIDTextField(String zoneID) {
		zoneIDTextField.setText(zoneID);
	}

	/**
	 * @param String
	 *            userID
	 */
	public static void setUserIDTextField(String userID) {
		userIDTextField.setText(userID);
	}

	/**
	 * @param String
	 *            entryDate
	 */
	public static void setEntryDateTextField(String entryDate) {
		entryDateTextField.setText(entryDate);
	}

	/**
	 * @param String
	 *            entryHour
	 */
	public static void setEntryHourTextField(String entryHour) {
		entryHourTextField.setText(entryHour);
	}

	/**
	 * @param String
	 *            offeringDate
	 */
	public static void setOfferingDateTextField(String offeringDate) {
		offeringDateTextField.setText(offeringDate);
	}

	/**
	 * @param String
	 *            offeringHour
	 */
	public static void setOfferingHourTextField(String offeringHour) {
		offeringHourTextField.setText(offeringHour);
	}

	/**
	 * @return entryDateTextField
	 */
	public static String getEntryDateTextField() {
		return entryDateTextField.getText();
	}

	/**
	 * @return entryHourTextField
	 */
	public static String getEntryHourTextField() {
		return entryHourTextField.getText();
	}

	/**
	 * @param DeliveredItem
	 *            deliveredItem
	 */
	public static void updateDeliveryTableModel(DeliveredItem deliveredItem) {

		// deliveryTableModel.setNumRows(0);
		if (deliveryTableModel.getRowCount()==0){
			deliveryTableModel.addRow(new Object[] {
					deliveredItem.getCompany(),
					deliveredItem.getItemCode(),
					deliveredItem.getDescription(),
					deliveredItem.getQuantityDelivered(),
					deliveredItem.getExpectedQuantity(),
					deliveredItem.getQuantityRemain(),
					deliveredItem.getDeliveryDate(),
					deliveredItem.getMeasurementID(),
					deliveredItem.getQuantity() });
		}
		boolean included = false;
		for (int i = 0; i < deliveryTableModel.getRowCount(); i++) {
			if (deliveryTableModel.getValueAt(i, 1).equals(deliveredItem.getItemCode())) {
				included = true;
			}
		}
			if(!included){
			deliveryTableModel.addRow(new Object[] {
					deliveredItem.getCompany(),
					deliveredItem.getItemCode(),
					deliveredItem.getDescription(),
					deliveredItem.getQuantityDelivered(),
					deliveredItem.getExpectedQuantity(),
					deliveredItem.getQuantityRemain(),
					deliveredItem.getDeliveryDate(),
					deliveredItem.getMeasurementID(),
					deliveredItem.getQuantity() });
			}
			
		
	}

	private class ActivateDoorButtonMouseListener extends MouseAdapter {
		public void mouseClicked(final MouseEvent arg0) {
			// ========Start Capture Application============

			URL log4jURL = this.getClass().getResource("/log4j.xml");
			DOMConfigurator.configure(log4jURL);
			log
					.debug("*******************Door Activated*********************\n");
			int port;
			String epcisRepository;
			if (!((aleListeningPortTextField.getText().equals("")) || (epcisRepositoryURLTextField
					.getText().equals("")))) {
				port = Integer.parseInt(aleListeningPortTextField.getText());
				epcisRepository = epcisRepositoryURLTextField.getText();
				log.debug("EPCIS REPOSITORY URL:"+ epcisRepositoryURLTextField.getText());
				log.debug("ALE Report Port:"+ aleListeningPortTextField.getText() + "\n");
				captureReport = new CaptureReport(port, epcisRepository);
				captureReport.setActivated(true);

			} else {
				return;
			}

			captureReport.start();

			// =============================================

		}
	}

	private class DeactivateDoorButtonMouseListener extends MouseAdapter {
		public void mouseClicked(final MouseEvent e) {
			captureReport.setActivated(false);

		}
	}

	private class ClearReportButtonMouseListener extends MouseAdapter {
		public void mouseClicked(final MouseEvent e) {
			deliveryTableModel.setNumRows(0);
			offeringHourTextField.setText("");
			offeringDateTextField.setText("");
			entryHourTextField.setText("");
			entryDateTextField.setText("");
			userIDTextField.setText("");
			zoneIDTextField.setText("");
			warehouseIDTextField.setText("");
			invoiceIDTextField.setText("");

		}
	}

}
