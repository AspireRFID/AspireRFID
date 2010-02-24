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
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.math.BigInteger;
import java.net.URL;
import java.text.DateFormat;
import java.util.Calendar;
import java.util.HashMap;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import org.apache.commons.configuration.XMLConfiguration;
import org.ow2.aspirerfid.commons.utils.Configurator;
import org.ow2.aspirerfid.demos.warehouse.connector.ConnectorManager;
import org.ow2.aspirerfid.demos.warehouse.connector.WarehouseManager;
import org.ow2.aspirerfid.demos.warehouse.management.tools.DeliveryItem;
import org.ow2.aspirerfid.demos.warehouse.management.tools.Shipment;

/**
 * @author Nikos Kefalakis (nkef) e-mail: nkef@ait.edu.gr
 * @author Nektarios Leontiadis
 * 
 */
public class WMS {

	private JTable deliveryInfo;
	private JComboBox shipmentsCb;
	/**
	 * The logger.
	 */
	// private static Log log = LogFactory.getLog(WMS.class);
	private static JTextField offeringDateTextField;
	private static JTextField invoiceIDTextField;
	private static JFrame frame;
	private JTable deliveryTable;
	private static DefaultTableModel deliveryTableModel, deliveryInfoModel;
	int selectedRow;

	/**
	 * Launch the application
	 * 
	 * @param args
	 */
	public static void main(String args[]) {
		try {
			new WMS();
			frame.setVisible(true);
			Configurator.loadProperties("application.properties", WMS.class);
			connectorClientEventHandler = new WarehouseManager();
		}
		catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * Create the application
	 */
	public WMS() {
		initialize();
		loadShipmentInfo();

		shipmentsCb.setEnabled(false);
		for (String invoice : shipments.keySet()) {
			shipmentsCb.addItem(invoice);
		}
		shipmentsCb.setEnabled(true);
	}

	/**
	 * Initialize the contents of the frame
	 */
	private void initialize() {

		final JLabel entryDateLabel_2;
		final JLabel entryDateLabel_3_1_1;
		final JScrollPane scrollPane;
		final JButton printReportButton;
		final JButton saveReportButton;
		final JButton clearReportButton;
		frame = new JFrame();
		frame.setResizable(false);
		frame.addWindowListener(new FrameWindowListener());
		frame.getContentPane().setLayout(new BorderLayout());
		frame.setTitle("Warehouse Management");
		frame.setBounds(100, 100, 757, 625);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		tabbedPane = new JTabbedPane();
		frame.getContentPane().add(tabbedPane);

		deliveryTableModel = new DefaultTableModel();// All Clients Items
		deliveryTableModel.addColumn("Company");
		deliveryTableModel.addColumn("Item Code");
		deliveryTableModel.addColumn("Description");
		deliveryTableModel.addColumn("Quantity Delivered");
		deliveryTableModel.addColumn("Expected Quantity");
		deliveryTableModel.addColumn("Quantity Remain");
		deliveryTableModel.addColumn("Delivery Date");
		deliveryTableModel.addColumn("Measurement ID");

		deliveryInfoModel = new DefaultTableModel();// All Clients Items
		deliveryInfoModel.addColumn("Company");
		deliveryInfoModel.addColumn("Item Code");
		deliveryInfoModel.addColumn("Description");
		deliveryInfoModel.addColumn("Expected Quantity");
		deliveryInfoModel.addColumn("Measurement ID");

		shipmentPanel = new JPanel();
		shipmentPanel.setLayout(null);
		tabbedPane.addTab("Shipment", null, shipmentPanel, null);

		submitShipmentButton = new JButton();
		submitShipmentButton.addActionListener(new SubmitShipmentButtonActionListener());

		submitShipmentButton.setText("Submit");
		submitShipmentButton.setBounds(275, 89, 112, 25);
		shipmentPanel.add(submitShipmentButton);

		final JLabel selectAvaiableInvoiceLabel = new JLabel();
		selectAvaiableInvoiceLabel.setText("Select avaiable invoice to track");
		selectAvaiableInvoiceLabel.setBounds(58, 26, 195, 15);
		shipmentPanel.add(selectAvaiableInvoiceLabel);

		shipmentsCb = new JComboBox();
		shipmentsCb.setModel(new DefaultComboBoxModel(new String[] {}));
		shipmentsCb.setSelectedItem(null);
		shipmentsCb.addActionListener(new ShipmentsCbActionListener());
		shipmentsCb.setBounds(269, 21, 382, 24);
		shipmentPanel.add(shipmentsCb);

		final JPanel panel = new JPanel();
		panel.setLayout(null);
		panel
				.setBorder(new TitledBorder(null, "Shipment information", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null,
						null));
		panel.setBounds(10, 170, 722, 327);
		shipmentPanel.add(panel);

		final JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 27, 702, 300);
		panel.add(scrollPane_1);

		deliveryInfo = new JTable(deliveryInfoModel);
		deliveryInfo.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane_1.setViewportView(deliveryInfo);

		deliveryPanel = new JPanel();
		deliveryPanel.setLayout(null);
		tabbedPane.addTab("Delivery Counter", null, deliveryPanel, null);

		entryDateLabel_2 = new JLabel();
		entryDateLabel_2.setText("Invoice ID ");
		entryDateLabel_2.setBounds(10, 23, 117, 16);
		deliveryPanel.add(entryDateLabel_2);

		entryDateLabel_3_1_1 = new JLabel();
		entryDateLabel_3_1_1.setText("Offering Date");
		entryDateLabel_3_1_1.setBounds(391, 25, 117, 16);
		deliveryPanel.add(entryDateLabel_3_1_1);

		invoiceIDTextField = new JTextField();
		invoiceIDTextField.setEditable(false);
		invoiceIDTextField.setBounds(105, 21, 270, 20);
		deliveryPanel.add(invoiceIDTextField);

		offeringDateTextField = new JTextField();
		offeringDateTextField.setEditable(false);
		offeringDateTextField.setBounds(511, 23, 230, 20);
		deliveryPanel.add(offeringDateTextField);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 81, 731, 403);
		deliveryPanel.add(scrollPane);
		deliveryTable = new JTable(deliveryTableModel);
		deliveryTable.setFont(new Font("Arial Narrow", Font.PLAIN, 10));
		deliveryTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		scrollPane.setViewportView(deliveryTable);

		printReportButton = new JButton();
		printReportButton.setText("Print Report");
		printReportButton.setBounds(459, 520, 117, 26);
		deliveryPanel.add(printReportButton);

		saveReportButton = new JButton();
		saveReportButton.setText("Save Report");
		saveReportButton.setBounds(213, 520, 117, 26);
		deliveryPanel.add(saveReportButton);

		clearReportButton = new JButton();
		clearReportButton.addMouseListener(new ClearReportButtonMouseListener());
		clearReportButton.setText("Clear Report");
		clearReportButton.setBounds(336, 520, 117, 26);
		deliveryPanel.add(clearReportButton);

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
	}

	/**
	 * @param DeliveryItem
	 *            deliveredItem
	 */
	public static void updateDeliveryTableModel(DeliveryItem deliveredItem) {

		// deliveryTableModel.setNumRows(0);
		if (deliveryTableModel.getRowCount() == 0) {
			deliveryTableModel.addRow(new Object[] { deliveredItem.getCompany(), deliveredItem.getItemCode(), deliveredItem.getDescription(),
					deliveredItem.getQuantityDelivered(), deliveredItem.getExpectedQuantity(), deliveredItem.getQuantityRemain(),
					deliveredItem.getDeliveryDate(), deliveredItem.getMeasurementID(), deliveredItem.getQuantity() });
		}
		boolean included = false;
		for (int i = 0; i < deliveryTableModel.getRowCount(); i++) {
			if (deliveryTableModel.getValueAt(i, 1).equals(deliveredItem.getItemCode())) {
				included = true;
				deliveryTableModel.setValueAt(deliveredItem.getQuantityDelivered(), i, 3);
				deliveryTableModel.setValueAt(deliveredItem.getExpectedQuantity(), i, 4);
				deliveryTableModel.setValueAt(deliveredItem.getQuantityRemain(), i, 5);
				break;
			}
		}
		if (!included) {
			deliveryTableModel.addRow(new Object[] { deliveredItem.getCompany(), deliveredItem.getItemCode(), deliveredItem.getDescription(),
					deliveredItem.getQuantityDelivered(), deliveredItem.getExpectedQuantity(), deliveredItem.getQuantityRemain(),
					deliveredItem.getDeliveryDate(), deliveredItem.getMeasurementID() });
		}

	}

	public static void showDeliveredInfo(String invoiceId) {
		JOptionPane.showMessageDialog(frame, "Shipment with invoice " + invoiceId + " has been delivered", "Shipment delivery update",
				JOptionPane.INFORMATION_MESSAGE);
		submitShipmentButton.setEnabled(true);
	}

	private class ClearReportButtonMouseListener extends MouseAdapter {
		public void mouseClicked(final MouseEvent e) {
			deliveryTableModel.setNumRows(0);
			offeringDateTextField.setText("");
			invoiceIDTextField.setText("");

		}
	}

	private class SubmitShipmentButtonActionListener implements ActionListener {
		public void actionPerformed(final ActionEvent e) {
			Shipment s = shipments.get(shipmentsCb.getSelectedItem());
			if (s == null)
				return;

			if (connectorClientEventHandler.addShipmentInfo(s)) {
				setOfferingDateTextField((DateFormat.getInstance().format(Calendar.getInstance().getTime())));
				tabbedPane.setSelectedIndex(1);
				submitShipmentButton.setEnabled(false);
			}
		}
	}

	private class FrameWindowListener extends WindowAdapter {
		public void windowClosing(final WindowEvent arg0) {
			ConnectorManager.getInstance().cancelAllSubscriptions();
		}
	}

	private class ShipmentsCbActionListener implements ActionListener {
		public void actionPerformed(final ActionEvent arg0) {

			DeliveryItem item;
			deliveryInfoModel.setNumRows(0);

			Shipment s = shipments.get(shipmentsCb.getSelectedItem());
			for (String itemCode : s.getItems()) {
				item = s.getItemInfo(itemCode);
				deliveryInfoModel.addRow(new Object[] { item.getCompany(), item.getItemCode(), item.getDescription(), item.getExpectedQuantity(),
						item.getMeasurementID() });
			}
		}
	}

	/*
	 * private Shipment createShipment() { String empty = ""; String gids =
	 * tfGid.getText().trim(); String from = tfFrom.getText().trim(); String
	 * description = tfDescription.getText().trim(); String[] quantityStr; int[]
	 * quantity; String[] items; DeliveryItem item;
	 * 
	 * if (gids.equals(empty) || from.equals(empty) || description.equals(empty)
	 * || invoice.equals(empty)) { log.error("Shipment parameters not defined");
	 * return null; } try { quantityStr =
	 * tfQuantity.getText().trim().split(","); quantity = new
	 * int[quantityStr.length]; for (int i = 0; i < quantityStr.length; i++) {
	 * quantity[i] = Integer.parseInt(quantityStr[i]); } } catch (Exception e) {
	 * log
	 * .error("Quantities should be defined as comma separated numerical values"
	 * ); return null; } items = gids.split(",");
	 * 
	 * for (int i = 0; i < quantity.length; i++) { item = new DeliveryItem();
	 * item.setCompany(from); item.setDescription(description);
	 * item.setExpectedQuantity(BigInteger.valueOf(quantity[i]));
	 * item.setItemCode(items[i]); s.addItem(item); }
	 * 
	 * return s; }
	 */

	private void loadShipmentInfo() {
		XMLConfiguration config = new XMLConfiguration();
		config.setListDelimiter(',');
		URL fileurl2 = this.getClass().getClassLoader().getResource("/WarehouseParameters.xml");
		URL fileurl = this.getClass().getResource("/WarehouseParameters.xml");
		DeliveryItem item;
		Shipment shipment;
		String invoiceId;
		int items;
		try {

			config.load(fileurl);

		}
		catch (Exception e) {
			e.printStackTrace();
		}

		int invoicesNumber = config.getMaxIndex("invoices.invoice") + 1;

		shipments = new HashMap<String, Shipment>();

		for (int i = 0; i < invoicesNumber; i++) {

			invoiceId = config.getString("invoices.invoice(" + i + ").invoiceGroupName");
			shipment = new Shipment(invoiceId);
			items = config.getMaxIndex("invoices.invoice(" + i + ").merchandise.packetsContent") + 1;
			for (int j = 0; j < items; j++) {
				item = new DeliveryItem();
				item.setCompany(config.getString("invoices.invoice(" + i + ").merchandise.packetsContent(" + j + ").company"));
				item.setItemCode(config.getString("invoices.invoice(" + i + ").merchandise.packetsContent(" + j + ").packetsGroupName"));
				item.setDescription(config.getString("invoices.invoice(" + i + ").merchandise.packetsContent(" + j + ").description"));
				item.setMeasurementID(config.getString("invoices.invoice(" + i + ").merchandise.packetsContent(" + j + ").measurementID"));
				item.setExpectedQuantity(new BigInteger(config.getString("invoices.invoice(" + i + ").merchandise.packetsContent(" + j
						+ ").expectedQuantity")));
				shipment.addItem(item);
			}
			shipments.put(invoiceId, shipment);
		}

	}

	private static WarehouseManager connectorClientEventHandler;

	private HashMap<String, Shipment> shipments;
	private static JTabbedPane tabbedPane;
	private static JPanel deliveryPanel;
	private static JPanel shipmentPanel;
	private static JButton submitShipmentButton;
}
