/*
 * Copyright (C) 2007 ETH Zurich
 *
 * This file is part of Accada (www.accada.org).
 *
 * Accada is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License version 2.1, as published by the Free Software Foundation.
 *
 * Accada is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with Accada; if not, write to the Free
 * Software Foundation, Inc., 51 Franklin Street, Fifth Floor,
 * Boston, MA  02110-1301  USA
 */

package org.ow2.aspirerfid.reader.rp.impl.core.mgmt.simulator;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTabbedPane;
import javax.swing.WindowConstants;
import javax.swing.border.BevelBorder;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;

import org.ow2.aspirerfid.reader.rp.impl.core.AntennaReadPoint;
import org.ow2.aspirerfid.reader.rp.impl.core.ReadPoint;
import org.ow2.aspirerfid.reader.rp.impl.core.ReaderDevice;
import org.ow2.aspirerfid.reader.rp.impl.core.ReaderProtocolException;
import org.ow2.aspirerfid.reader.rp.impl.core.mgmt.OperationalStatus;
import org.apache.log4j.Logger;


/**
* This code was edited or generated using CloudGarden's Jigloo
* SWT/Swing GUI Builder, which is free for non-commercial
* use. If Jigloo is being used commercially (ie, by a corporation,
* company or business for any purpose whatever) then you
* should purchase a license for each developer using Jigloo.
* Please visit www.cloudgarden.com for details.
* Use of Jigloo implies acceptance of these licensing terms.
* A COMMERCIAL LICENSE HAS NOT BEEN PURCHASED FOR
* THIS MACHINE, SO JIGLOO OR THIS CODE CANNOT BE USED
* LEGALLY FOR ANY CORPORATE OR COMMERCIAL PURPOSE.
*/
public class MgmtSimulator extends javax.swing.JFrame {
	
	/**
	 * The logger.
	 */
	private static Logger log = Logger.getLogger(MgmtSimulator.class);
	
	private JPanel readPointOperStatusLabelPanel;
	private JPanel readPointErrorButtonPanel;
	private JPanel readPointErrorLabelPanel;
	private JPanel readPointSelectionLabelPanel;
	private JLabel readPointOperStatusLabel;
	private JRadioButton readPointUnknownRadioButton;
	private JRadioButton readPointOtherRadioButton;
	private JRadioButton readPointDownRadioButton;
	private JRadioButton readPointUpRadioButton;
	private JPanel readPointRadioButtonPanel;
	private JPanel readPointOperStatusPanel;
	private JLabel readPointSelectionLabel;
	private JLabel readPointErrorLabel;
	private JPanel readPointPanel;
	private JButton lockErrorButton;
	private JButton eraseErrorButton;
	private JButton killErrorButton;
	private JButton writeErrorButton;
	private JButton readErrorButton;
	private JPanel readPointErrorPanel;
	private JPanel readPointActionPanel;
	private JPanel readPointSelectorPanel;
	private JComboBox readPointSelector;
	private JTabbedPane deviceTabs;
	private ButtonGroup readPointRadioButtonGroup;

	/**
	 * The reader device.
	 */
	private ReaderDevice readerDevice;

	/**
	 * Main method to display this JFrame
	 */
	public static void main(String[] args) {
		ReaderDevice readerDevice = null;
		try {
			readerDevice = ReaderDevice.getInstance();
		} catch (ReaderProtocolException rpe) {
			log.error("Unable to obtain ReaderDevice instance. -> Abort");
			return;
		}
		MgmtSimulator inst = new MgmtSimulator(readerDevice);
		inst.setVisible(true);
	}
	
	/**
	 * The constructor.
	 * 
	 * @param readerDevice
	 *            The reader device
	 */
	public MgmtSimulator(ReaderDevice readerDevice) {
		super();
		this.readerDevice = readerDevice;
		initGUI();
		updateReadPointSelector();
		updateReadPointActionPanel();
	}
	
	/**
	 * Initializes the GUI.
	 */
	private void initGUI() {
		try {
			setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			this.setTitle("Management Simulator");
			{
				deviceTabs = new JTabbedPane();
				getContentPane().add(deviceTabs, BorderLayout.CENTER);
				{
					readPointPanel = new JPanel();
					GridLayout readPointPanelLayout = new GridLayout(1, 1);
					readPointPanelLayout.setHgap(5);
					readPointPanelLayout.setVgap(5);
					readPointPanelLayout.setColumns(1);
					readPointPanel.setLayout(readPointPanelLayout);
					deviceTabs
						.addTab("Read Points", null, readPointPanel, null);
					{
						readPointSelectorPanel = new JPanel();
						BoxLayout readPointSelectorPanelLayout = new BoxLayout(
							readPointSelectorPanel,
							javax.swing.BoxLayout.Y_AXIS);
						readPointSelectorPanel.setLayout(readPointSelectorPanelLayout);
						readPointPanel.add(readPointSelectorPanel);
						readPointSelectorPanel.setBorder(BorderFactory.createEtchedBorder(BevelBorder.LOWERED));
						{
							readPointSelectionLabelPanel = new JPanel();
							FlowLayout readPointSelectionLabelPanelLayout = new FlowLayout();
							readPointSelectionLabelPanelLayout.setAlignment(FlowLayout.LEFT);
							readPointSelectorPanel.add(readPointSelectionLabelPanel);
							readPointSelectionLabelPanel.setMaximumSize(new java.awt.Dimension(32767, 26));
							readPointSelectionLabelPanel.setLayout(readPointSelectionLabelPanelLayout);
							{
								readPointSelectionLabel = new JLabel();
								readPointSelectionLabelPanel.add(readPointSelectionLabel);
								readPointSelectionLabel
									.setText("Choose a read point:");
							}
						}

						{
							ComboBoxModel readPointSelectorModel = new DefaultComboBoxModel();
							readPointSelector = new JComboBox();
							readPointSelectorPanel.add(readPointSelector);
							readPointSelector.setMaximumSize(new java.awt.Dimension(32767, 26));
							readPointSelector
								.addPopupMenuListener(new PopupMenuListener() {
								public void popupMenuWillBecomeVisible(
									PopupMenuEvent evt) {
									readPointSelectorPopupMenuWillBecomeVisible(evt);
								}
								public void popupMenuWillBecomeInvisible(
									PopupMenuEvent evt) { }
								public void popupMenuCanceled(PopupMenuEvent evt) { }
								});
							readPointSelector
								.addItemListener(new ItemListener() {
									public void itemStateChanged(ItemEvent evt) {
										readPointSelectorItemStateChanged(evt);
									}
								});
							readPointSelector.setModel(readPointSelectorModel);
						}
					}
					{
						readPointActionPanel = new JPanel();
						BoxLayout readPointActionPanelLayout = new BoxLayout(
							readPointActionPanel,
							javax.swing.BoxLayout.Y_AXIS);
						readPointActionPanel.setLayout(readPointActionPanelLayout);
						readPointPanel.add(readPointActionPanel);
						{
							readPointErrorPanel = new JPanel();
							BorderLayout readPointErrorPanelLayout = new BorderLayout();
							readPointErrorPanel.setLayout(readPointErrorPanelLayout);
							readPointActionPanel.add(readPointErrorPanel);
							readPointErrorPanel.setPreferredSize(new java.awt.Dimension(216, 170));
							readPointErrorPanel.setBorder(BorderFactory.createEtchedBorder(BevelBorder.LOWERED));
							{
								readPointErrorLabelPanel = new JPanel();
								FlowLayout readPointErrorLabelPanelLayout = new FlowLayout();
								readPointErrorLabelPanelLayout.setAlignment(FlowLayout.LEFT);
								readPointErrorLabelPanel.setLayout(readPointErrorLabelPanelLayout);
								readPointErrorPanel.add(readPointErrorLabelPanel, BorderLayout.NORTH);
								readPointErrorLabelPanel.setMaximumSize(new java.awt.Dimension(32767, 26));
								{
									readPointErrorLabel = new JLabel();
									readPointErrorLabelPanel.add(readPointErrorLabel);
									readPointErrorLabel.setText("Generate an error:");
								}
							}
							{
								readPointErrorButtonPanel = new JPanel();
								BoxLayout readPointErrorButtonPanelLayout = new BoxLayout(
									readPointErrorButtonPanel,
									javax.swing.BoxLayout.Y_AXIS);
								readPointErrorButtonPanel.setLayout(readPointErrorButtonPanelLayout);
								readPointErrorPanel.add(readPointErrorButtonPanel, BorderLayout.WEST);
								{
									readErrorButton = new JButton();
									readPointErrorButtonPanel.add(readErrorButton);
									readErrorButton.setText("Read");
									readErrorButton
										.setMaximumSize(new java.awt.Dimension(
											70,
											26));
									readErrorButton
										.addActionListener(new ActionListener() {
										public void actionPerformed(
											ActionEvent evt) {
											readErrorButtonActionPerformed(evt);
										}
										});
								}
								{
									writeErrorButton = new JButton();
									readPointErrorButtonPanel.add(writeErrorButton);
									writeErrorButton.setText("Write");
									writeErrorButton
										.setMaximumSize(new java.awt.Dimension(
											70,
											26));
									writeErrorButton
										.addActionListener(new ActionListener() {
										public void actionPerformed(
											ActionEvent evt) {
											writeErrorButtonActionPerformed(evt);
										}
										});
								}
								{
									killErrorButton = new JButton();
									readPointErrorButtonPanel.add(killErrorButton);
									killErrorButton.setText("Kill");
									killErrorButton
										.setMaximumSize(new java.awt.Dimension(
											70,
											26));
									killErrorButton
										.addActionListener(new ActionListener() {
										public void actionPerformed(
											ActionEvent evt) {
											killErrorButtonActionPerformed(evt);
										}
										});
								}
								{
									eraseErrorButton = new JButton();
									readPointErrorButtonPanel.add(eraseErrorButton);
									eraseErrorButton.setText("Erase");
									eraseErrorButton
										.setMaximumSize(new java.awt.Dimension(
											70,
											26));
									eraseErrorButton
										.addActionListener(new ActionListener() {
										public void actionPerformed(
											ActionEvent evt) {
											eraseErrorButtonActionPerformed(evt);
										}
										});
								}
								{
									lockErrorButton = new JButton();
									readPointErrorButtonPanel.add(lockErrorButton);
									lockErrorButton.setText("Lock");
									lockErrorButton
										.setMaximumSize(new java.awt.Dimension(
											70,
											26));
									lockErrorButton
										.addActionListener(new ActionListener() {
										public void actionPerformed(
											ActionEvent evt) {
											lockErrorButtonActionPerformed(evt);
										}
										});
								}
							}
						}
						{
							readPointOperStatusPanel = new JPanel();
							BorderLayout readPointOperStatusPanelLayout = new BorderLayout();
							readPointOperStatusPanel.setLayout(readPointOperStatusPanelLayout);
							readPointActionPanel.add(readPointOperStatusPanel);
							readPointOperStatusPanel.setBorder(BorderFactory.createEtchedBorder(BevelBorder.LOWERED));
							readPointOperStatusPanel.setPreferredSize(new java.awt.Dimension(216, 168));
							{
								readPointOperStatusLabelPanel = new JPanel();
								FlowLayout readPointOperStatusLabelPanelLayout = new FlowLayout();
								readPointOperStatusLabelPanelLayout.setAlignment(FlowLayout.LEFT);
								readPointOperStatusPanel.add(readPointOperStatusLabelPanel, BorderLayout.NORTH);
								readPointOperStatusLabelPanel.setLayout(readPointOperStatusLabelPanelLayout);
								{
									readPointOperStatusLabel = new JLabel();
									readPointOperStatusLabelPanel.add(readPointOperStatusLabel);
									readPointOperStatusLabel
										.setText("Choose an operational status:");
								}
							}
							{
								readPointRadioButtonPanel = new JPanel();
								BoxLayout readPointRadioButtonPanelLayout = new BoxLayout(
									readPointRadioButtonPanel,
									javax.swing.BoxLayout.Y_AXIS);
								readPointRadioButtonPanel.setLayout(readPointRadioButtonPanelLayout);
								readPointOperStatusPanel.add(readPointRadioButtonPanel, BorderLayout.CENTER);
								readPointRadioButtonPanel.setPreferredSize(new java.awt.Dimension(212, 85));
								{
									readPointUpRadioButton = new JRadioButton();
									readPointRadioButtonPanel.add(readPointUpRadioButton);
									readPointUpRadioButton.setText("UP");
								}
								{
									readPointDownRadioButton = new JRadioButton();
									readPointRadioButtonPanel.add(readPointDownRadioButton);
									readPointDownRadioButton.setText("DOWN");
								}
								{
									readPointOtherRadioButton = new JRadioButton();
									readPointRadioButtonPanel.add(readPointOtherRadioButton);
									readPointOtherRadioButton.setText("OTHER");
								}
								{
									readPointUnknownRadioButton = new JRadioButton();
									readPointRadioButtonPanel.add(readPointUnknownRadioButton);
									readPointUnknownRadioButton
										.setText("UNKNOWN");
								}
								{
									readPointRadioButtonGroup = new ButtonGroup();
									readPointRadioButtonGroup.add(readPointUpRadioButton);
									readPointUpRadioButton
										.addItemListener(new ItemListener() {
										public void itemStateChanged(
											ItemEvent evt) {
											readPointRadioButtonItemStateChanged(evt);
										}
										});
									readPointRadioButtonGroup.add(readPointDownRadioButton);
									readPointDownRadioButton
										.addItemListener(new ItemListener() {
										public void itemStateChanged(
											ItemEvent evt) {
											readPointRadioButtonItemStateChanged(evt);
										}
										});
									readPointRadioButtonGroup.add(readPointOtherRadioButton);
									readPointOtherRadioButton
										.addItemListener(new ItemListener() {
										public void itemStateChanged(
											ItemEvent evt) {
											readPointRadioButtonItemStateChanged(evt);
										}
										});
									readPointRadioButtonGroup.add(readPointUnknownRadioButton);
									readPointUnknownRadioButton
										.addItemListener(new ItemListener() {
										public void itemStateChanged(
											ItemEvent evt) {
											readPointRadioButtonItemStateChanged(evt);
										}
										});
								}
							}
						}
					}
				}
			}
			pack();
			this.setSize(450, 400);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Updates the items of the <code>readPointSelector</code> by requesting
	 * the read points from the reader device.
	 */
	private void updateReadPointSelector() {
		String selectedName = null;
		if (readPointSelector.getItemCount() > 0) {
			selectedName = ((ReadPointItem) (readPointSelector
					.getSelectedItem())).getReadPoint().getName();
		}
		ReadPoint[] readPoints = readerDevice.getAllReadPoints();
		ReadPointItem[] items = new ReadPointItem[readPoints.length];
		ReadPointItem selectedItem = null;
		for (int i = 0; i < readPoints.length; i++) {
			items[i] = new ReadPointItem(readPoints[i]);
			if (readPoints[i].getName().equals(selectedName))
				selectedItem = items[i];
		}
		readPointSelector.setModel(new DefaultComboBoxModel(items));
		if (selectedItem != null)
			readPointSelector.setSelectedItem(selectedItem);
	}
	
	/**
	 * Updates the <code>readPointActionPanel</code> and its content accordingly
	 * to the selected read point.
	 */
	private void updateReadPointActionPanel() {
		ReadPoint selectedReadPoint = ((ReadPointItem)(readPointSelector.getSelectedItem())).getReadPoint();
		if (selectedReadPoint instanceof AntennaReadPoint) {
			readErrorButton.setEnabled(true);
			writeErrorButton.setEnabled(true);
			killErrorButton.setEnabled(true);
			eraseErrorButton.setEnabled(true);
			lockErrorButton.setEnabled(true);
		} else {
			readErrorButton.setEnabled(false);
			writeErrorButton.setEnabled(false);
			killErrorButton.setEnabled(false);
			eraseErrorButton.setEnabled(false);
			lockErrorButton.setEnabled(false);
		}
		
		switch (selectedReadPoint.getOperStatus()) {
			case UP:
				readPointUpRadioButton.setSelected(true);
				break;
			case DOWN:
				readPointDownRadioButton.setSelected(true);
				break;
			case OTHER:
				readPointOtherRadioButton.setSelected(true);
				break;
			case UNKNOWN:
				readPointUnknownRadioButton.setSelected(true);
				break;
		}
	}
	
	/**
	 * Called whenever the item states of the read point radio buttons have been
	 * changed.
	 * 
	 * @param evt
	 *            The item event
	 */
	private void readPointRadioButtonItemStateChanged(ItemEvent evt) {
		JRadioButton radioButton = (JRadioButton)evt.getSource();
		if (evt.getStateChange() == ItemEvent.SELECTED) {
			ReadPoint selectedReadPoint = ((ReadPointItem)(readPointSelector.getSelectedItem())).getReadPoint();
			if (radioButton == readPointUpRadioButton) {
				selectedReadPoint.setOperStatus(OperationalStatus.UP);
			} else if (radioButton == readPointDownRadioButton) {
				selectedReadPoint.setOperStatus(OperationalStatus.DOWN);
			} else if (radioButton == readPointOtherRadioButton) {
				selectedReadPoint.setOperStatus(OperationalStatus.OTHER);
			} else if (radioButton == readPointUnknownRadioButton) {
				selectedReadPoint.setOperStatus(OperationalStatus.UNKNOWN);
			}
		}
	}
	
	/**
	 * Called whenever the item state of the <code>readPointSelector</code>
	 * has been changed.
	 * 
	 * @param evt
	 *            The item event
	 */
	private void readPointSelectorItemStateChanged(ItemEvent evt) {
		updateReadPointActionPanel();
	}
	
	/**
	 * Called on an action event on the <code>readErrorButton</code>.
	 * 
	 * @param evt
	 *            The action event
	 */
	private void readErrorButtonActionPerformed(ActionEvent evt) {
		updateReadPointSelector();
		AntennaReadPoint selectedReadPoint = (AntennaReadPoint) ((ReadPointItem) (readPointSelector
				.getSelectedItem())).getReadPoint();
		selectedReadPoint.memReadFailureOccurred();
	}
	
	/**
	 * Called on an action event on the <code>writeErrorButton</code>.
	 * 
	 * @param evt
	 *            The action event
	 */
	private void writeErrorButtonActionPerformed(ActionEvent evt) {
		updateReadPointSelector();
		AntennaReadPoint selectedReadPoint = (AntennaReadPoint) ((ReadPointItem) (readPointSelector
				.getSelectedItem())).getReadPoint();
		selectedReadPoint.writeFailureOccurred();
	}
	
	/**
	 * Called on an action event on the <code>killErrorButton</code>.
	 * 
	 * @param evt
	 *            The action event
	 */
	private void killErrorButtonActionPerformed(ActionEvent evt) {
		updateReadPointSelector();
		AntennaReadPoint selectedReadPoint = (AntennaReadPoint) ((ReadPointItem) (readPointSelector
				.getSelectedItem())).getReadPoint();
		selectedReadPoint.killFailureOccurred();
	}
	
	/**
	 * Called on an action event on the <code>eraseErrorButton</code>.
	 * 
	 * @param evt
	 *            The action event
	 */
	private void eraseErrorButtonActionPerformed(ActionEvent evt) {
		updateReadPointSelector();
		AntennaReadPoint selectedReadPoint = (AntennaReadPoint) ((ReadPointItem) (readPointSelector
				.getSelectedItem())).getReadPoint();
		selectedReadPoint.eraseFailureOccurred();
	}
	
	/**
	 * Called on an action event on the <code>lockErrorButton</code>.
	 * 
	 * @param evt
	 *            The action event
	 */
	private void lockErrorButtonActionPerformed(ActionEvent evt) {
		updateReadPointSelector();
		AntennaReadPoint selectedReadPoint = (AntennaReadPoint) ((ReadPointItem) (readPointSelector
				.getSelectedItem())).getReadPoint();
		selectedReadPoint.lockFailureOccurred();
	}
	
	/**
	 * Called whenever the <code>readPointSelector</code>'s popup menu
	 * becomes visible.
	 * 
	 * @param evt
	 *            The popup menu event
	 */
	private void readPointSelectorPopupMenuWillBecomeVisible(PopupMenuEvent evt) {
		updateReadPointSelector();
		updateReadPointActionPanel();
	}
	
	/**
	 * This class representates a list item used by the
	 * <code>readPointSelector</code>.
	 */
	private class ReadPointItem {
		
		/**
		 * The read point.
		 */
		private ReadPoint readPoint;
		
		/**
		 * The constructor.
		 * 
		 * @param readPoint
		 *            The read point
		 */
		public ReadPointItem(ReadPoint readPoint) {
			this.readPoint = readPoint;
		}
		
		/**
		 * Returns the read point.
		 * 
		 * @return The read point
		 */
		public ReadPoint getReadPoint() {
			return readPoint;
		}

		/* (non-Javadoc)
		 * @see java.lang.Object#toString()
		 */
		@Override
		public String toString() {
			return readPoint.getName() + " ("
					+ readPoint.getClass().getSimpleName() + ")";
		}
	}

}
