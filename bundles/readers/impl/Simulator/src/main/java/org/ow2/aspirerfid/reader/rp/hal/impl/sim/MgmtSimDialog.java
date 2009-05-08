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

package org.ow2.aspirerfid.reader.rp.hal.impl.sim;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import org.apache.log4j.xml.DOMConfigurator;


public class MgmtSimDialog extends JDialog {
	
	/**
	 * The serial version UID.
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * The name of the read point this dialog belongs to.
	 */
	private String readPointName;
	
	/**
	 * The simulator controller.
	 */
	private SimulatorController controller;
	
	/*---------------------*
	 | The GUI components. |
	 *---------------------*/
	private JPanel bottomPanel;
	private JPanel buttonPanel;
	private JLabel readLabel;
	private JLabel lockLabel;
	private JPanel labelPanel;
	private JPanel lockPanel;
	private JPanel erasePanel;
	private JPanel killPanel;
	private JPanel writePanel;
	private JPanel readPanel;
	private JPanel identifyPanel;
	private JLabel ContinuousLabel;
	private JLabel onceLabel;
	private JCheckBox lockContinuousCheckBox;
	private JCheckBox lockOnceCheckBox;
	private JCheckBox eraseContinuousCheckBox;
	private JCheckBox eraseOnceCheckBox;
	private JCheckBox killContinuousCheckBox;
	private JCheckBox killOnceCheckBox;
	private JCheckBox writeContinuousCheckBox;
	private JCheckBox writeOnceCheckBox;
	private JCheckBox readContinuousCheckBox;
	private JCheckBox readOnceCheckBox;
	private JLabel eraseLabel;
	private JLabel killLabel;
	private JLabel writeLabel;
	private JLabel errorTypeLabel;
	private JCheckBox identifyContinuousCheckBox;
	private JCheckBox identifyOnceCheckBox;
	private JLabel identifyLabel;
	private JPanel errorPanel;
	private JButton cancelButton;
	private JButton okButton;

	/**
	 * For debugging.
	 * 
	 * @param args
	 *            No arguments
	 */
	public static void main(String[] args) {
		DOMConfigurator.configure("./props/log4j.xml");
		MgmtSimDialog mgmtSimDialog = new MgmtSimDialog("TestReadPoint",
		      new SimulatorController("SimulatorController",
		            "/props/SimulatorController.xml"));
		mgmtSimDialog.setVisible(true);
	}
	
	/**
	 * The constructor.
	 * 
	 * @param readPointName
	 *            The name of the read point this dialog belongs to
	 * @param controller
	 *            The simulator controller
	 */
	public MgmtSimDialog(String readPointName, SimulatorController controller) {
		this.readPointName = readPointName;
		this.controller = controller;
		
		initGUI();
		
		setTitle("Error generation for read point \"" + readPointName + "\"");
		updateGUI();
		
		// disable unsupported parts
		eraseLabel.setEnabled(false);
		eraseContinuousCheckBox.setEnabled(false);
		eraseOnceCheckBox.setEnabled(false);
		lockLabel.setEnabled(false);
		lockContinuousCheckBox.setEnabled(false);
		lockOnceCheckBox.setEnabled(false);
	}
	
	/* (non-Javadoc)
	 * @see java.awt.Component#setVisible(boolean)
	 */
	@Override
	public void setVisible(boolean b) {
		updateGUI();
		super.setVisible(b);
	}

	/**
	 * Updates the GUI to the state of the controller.
	 */
	private void updateGUI() {
		identifyOnceCheckBox.setSelected(controller.identifyError.contains(readPointName));
		readOnceCheckBox.setSelected(controller.readError.contains(readPointName));
		writeOnceCheckBox.setSelected(controller.writeError.contains(readPointName));
		killOnceCheckBox.setSelected(controller.killError.contains(readPointName));
		
		identifyContinuousCheckBox.setSelected(controller.continuousIdentifyErrors.contains(readPointName));
		readContinuousCheckBox.setSelected(controller.continuousReadErrors.contains(readPointName));
		writeContinuousCheckBox.setSelected(controller.continuousWriteErrors.contains(readPointName));
		killContinuousCheckBox.setSelected(controller.continuousKillErrors.contains(readPointName));
	}
	
	/**
	 * Updates the controller to the state of the GUI.
	 */
	private void updateController() {
		if (identifyOnceCheckBox.isSelected() && !controller.identifyError.contains(readPointName)) {
			controller.identifyError.add(readPointName);
		} else if (!identifyOnceCheckBox.isSelected() && controller.identifyError.contains(readPointName)) {
			controller.identifyError.remove(readPointName);
		}
		
		if (readOnceCheckBox.isSelected() && !controller.readError.contains(readPointName)) {
			controller.readError.add(readPointName);
		} else if (!readOnceCheckBox.isSelected() && controller.readError.contains(readPointName)) {
			controller.readError.remove(readPointName);
		}
		
		if (writeOnceCheckBox.isSelected() && !controller.writeError.contains(readPointName)) {
			controller.writeError.add(readPointName);
		} else if (!writeOnceCheckBox.isSelected() && controller.writeError.contains(readPointName)) {
			controller.writeError.remove(readPointName);
		}
		
		if (killOnceCheckBox.isSelected() && !controller.killError.contains(readPointName)) {
			controller.killError.add(readPointName);
		} else if (!killOnceCheckBox.isSelected() && controller.killError.contains(readPointName)) {
			controller.killError.remove(readPointName);
		}
		
		if (identifyContinuousCheckBox.isSelected() && !controller.continuousIdentifyErrors.contains(readPointName)) {
			controller.continuousIdentifyErrors.add(readPointName);
		} else if (!identifyContinuousCheckBox.isSelected() && controller.continuousIdentifyErrors.contains(readPointName)) {
			controller.continuousIdentifyErrors.remove(readPointName);
		}
		
		if (readContinuousCheckBox.isSelected() && !controller.continuousReadErrors.contains(readPointName)) {
			controller.continuousReadErrors.add(readPointName);
		} else if (!readContinuousCheckBox.isSelected() && controller.continuousReadErrors.contains(readPointName)) {
			controller.continuousReadErrors.remove(readPointName);
		}
		
		if (writeContinuousCheckBox.isSelected() && !controller.continuousWriteErrors.contains(readPointName)) {
			controller.continuousWriteErrors.add(readPointName);
		} else if (!writeContinuousCheckBox.isSelected() && controller.continuousWriteErrors.contains(readPointName)) {
			controller.continuousWriteErrors.remove(readPointName);
		}
		
		if (killContinuousCheckBox.isSelected() && !controller.continuousKillErrors.contains(readPointName)) {
			controller.continuousKillErrors.add(readPointName);
		} else if (!killContinuousCheckBox.isSelected() && controller.continuousKillErrors.contains(readPointName)) {
			controller.continuousKillErrors.remove(readPointName);
		}
	}
	
	/**
	 * Initializes the GUI.
	 */
	private void initGUI() {
		try {
			bottomPanel = new JPanel();
			BorderLayout bottomPanelLayout = new BorderLayout();
			bottomPanel.setLayout(bottomPanelLayout);
			getContentPane().add(bottomPanel, BorderLayout.SOUTH);
			
			buttonPanel = new JPanel();
			bottomPanel.add(buttonPanel, BorderLayout.EAST);
			okButton = new JButton();
			buttonPanel.add(okButton);
			okButton.setText("OK");
			okButton.setPreferredSize(new java.awt.Dimension(80, 22));
			okButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent event) {
					updateController();
					setVisible(false);
				}
			});
			cancelButton = new JButton();
			buttonPanel.add(cancelButton);
			cancelButton.setText("Cancel");
			cancelButton.setPreferredSize(new java.awt.Dimension(80, 22));
			cancelButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent event) {
					updateGUI();
					setVisible(false);
				}
			});
			
			errorPanel = new JPanel();
			errorPanel.setLayout(new BoxLayout(errorPanel, javax.swing.BoxLayout.Y_AXIS));
			getContentPane().add(errorPanel, BorderLayout.CENTER);
			
			labelPanel = new JPanel();
			GridLayout labelPanelLayout = new GridLayout(1, 3);
			labelPanelLayout.setColumns(3);
			labelPanelLayout.setHgap(5);
			labelPanelLayout.setVgap(5);
			labelPanel.setLayout(labelPanelLayout);
			errorPanel.add(labelPanel);
			labelPanel.setBackground(new java.awt.Color(255,128,128));
			errorTypeLabel = new JLabel();
			labelPanel.add(errorTypeLabel);
			errorTypeLabel.setText("Error Type");
			errorTypeLabel.setFont(new java.awt.Font("Tahoma",1,14));
			onceLabel = new JLabel();
			labelPanel.add(onceLabel);
			onceLabel.setText("Once");
			onceLabel.setFont(new java.awt.Font("Tahoma",1,14));
			ContinuousLabel = new JLabel();
			labelPanel.add(ContinuousLabel);
			ContinuousLabel.setText("Continuous");
			ContinuousLabel.setFont(new java.awt.Font("Tahoma",1,14));
			
			identifyPanel = new JPanel();
			GridLayout identifyPanelLayout = new GridLayout(1, 3);
			identifyPanelLayout.setColumns(3);
			identifyPanelLayout.setHgap(5);
			identifyPanelLayout.setVgap(5);
			identifyPanel.setLayout(identifyPanelLayout);
			errorPanel.add(identifyPanel);
			identifyPanel.setBorder(BorderFactory.createEtchedBorder(BevelBorder.LOWERED));
			identifyLabel = new JLabel();
			identifyPanel.add(identifyLabel);
			identifyLabel.setText("Identify");
			identifyOnceCheckBox = new JCheckBox();
			identifyPanel.add(identifyOnceCheckBox);
			identifyContinuousCheckBox = new JCheckBox();
			identifyPanel.add(identifyContinuousCheckBox);
			identifyContinuousCheckBox.addChangeListener(new ChangeListener() {
				public void stateChanged(ChangeEvent evt) {
					if (identifyContinuousCheckBox.isSelected()) {
						identifyOnceCheckBox.setEnabled(false);
					} else {
						identifyOnceCheckBox.setEnabled(true);
					}
				}
			});
			
			readPanel = new JPanel();
			GridLayout readPanelLayout = new GridLayout(1, 3);
			readPanelLayout.setColumns(3);
			readPanelLayout.setHgap(5);
			readPanelLayout.setVgap(5);
			readPanel.setLayout(readPanelLayout);
			errorPanel.add(readPanel);
			readPanel.setBorder(BorderFactory.createEtchedBorder(BevelBorder.LOWERED));
			readLabel = new JLabel();
			readPanel.add(readLabel);
			readLabel.setText("Read");
			readOnceCheckBox = new JCheckBox();
			readPanel.add(readOnceCheckBox);
			readContinuousCheckBox = new JCheckBox();
			readPanel.add(readContinuousCheckBox);
			readContinuousCheckBox.addChangeListener(new ChangeListener() {
				public void stateChanged(ChangeEvent evt) {
					if (readContinuousCheckBox.isSelected()) {
						readOnceCheckBox.setEnabled(false);
					} else {
						readOnceCheckBox.setEnabled(true);
					}
				}
			});
			
			writePanel = new JPanel();
			GridLayout writePanelLayout = new GridLayout(1, 3);
			writePanelLayout.setColumns(3);
			writePanelLayout.setHgap(5);
			writePanelLayout.setVgap(5);
			writePanel.setLayout(writePanelLayout);
			errorPanel.add(writePanel);
			writePanel.setBorder(BorderFactory.createEtchedBorder(BevelBorder.LOWERED));
			writeLabel = new JLabel();
			writePanel.add(writeLabel);
			writeLabel.setText("Write");
			writeOnceCheckBox = new JCheckBox();
			writePanel.add(writeOnceCheckBox);
			writeContinuousCheckBox = new JCheckBox();
			writePanel.add(writeContinuousCheckBox);
			writeContinuousCheckBox.addChangeListener(new ChangeListener() {
				public void stateChanged(ChangeEvent evt) {
					if (writeContinuousCheckBox.isSelected()) {
						writeOnceCheckBox.setEnabled(false);
					} else {
						writeOnceCheckBox.setEnabled(true);
					}
				}
			});
			
			killPanel = new JPanel();
			GridLayout killPanelLayout = new GridLayout(1, 3);
			killPanelLayout.setColumns(3);
			killPanelLayout.setHgap(5);
			killPanelLayout.setVgap(5);
			killPanel.setLayout(killPanelLayout);
			errorPanel.add(killPanel);
			killPanel.setBorder(BorderFactory.createEtchedBorder(BevelBorder.LOWERED));
			killLabel = new JLabel();
			killPanel.add(killLabel);
			killLabel.setText("Kill");
			killOnceCheckBox = new JCheckBox();
			killPanel.add(killOnceCheckBox);
			killContinuousCheckBox = new JCheckBox();
			killPanel.add(killContinuousCheckBox);
			killContinuousCheckBox.addChangeListener(new ChangeListener() {
				public void stateChanged(ChangeEvent evt) {
					if (killContinuousCheckBox.isSelected()) {
						killOnceCheckBox.setEnabled(false);
					} else {
						killOnceCheckBox.setEnabled(true);
					}
				}
			});
			
			erasePanel = new JPanel();
			GridLayout erasePanelLayout = new GridLayout(1, 3);
			erasePanelLayout.setColumns(3);
			erasePanelLayout.setHgap(5);
			erasePanelLayout.setVgap(5);
			erasePanel.setLayout(erasePanelLayout);
			errorPanel.add(erasePanel);
			erasePanel.setBorder(BorderFactory.createEtchedBorder(BevelBorder.LOWERED));
			eraseLabel = new JLabel();
			erasePanel.add(eraseLabel);
			eraseLabel.setText("Erase");
			eraseOnceCheckBox = new JCheckBox();
			erasePanel.add(eraseOnceCheckBox);
			eraseContinuousCheckBox = new JCheckBox();
			erasePanel.add(eraseContinuousCheckBox);
			eraseContinuousCheckBox.addChangeListener(new ChangeListener() {
				public void stateChanged(ChangeEvent evt) {
					if (eraseContinuousCheckBox.isSelected()) {
						eraseOnceCheckBox.setEnabled(false);
					} else {
						eraseOnceCheckBox.setEnabled(true);
					}
				}
			});
			
			lockPanel = new JPanel();
			GridLayout lockPanelLayout = new GridLayout(1, 3);
			lockPanelLayout.setColumns(3);
			lockPanelLayout.setHgap(5);
			lockPanelLayout.setVgap(5);
			lockPanel.setLayout(lockPanelLayout);
			errorPanel.add(lockPanel);
			lockPanel.setBorder(BorderFactory.createEtchedBorder(BevelBorder.LOWERED));
			lockLabel = new JLabel();
			lockPanel.add(lockLabel);
			lockLabel.setText("Lock");
			lockOnceCheckBox = new JCheckBox();
			lockPanel.add(lockOnceCheckBox);
			lockContinuousCheckBox = new JCheckBox();
			lockPanel.add(lockContinuousCheckBox);
			lockContinuousCheckBox.addChangeListener(new ChangeListener() {
				public void stateChanged(ChangeEvent evt) {
					if (lockContinuousCheckBox.isSelected()) {
						lockOnceCheckBox.setEnabled(false);
					} else {
						lockOnceCheckBox.setEnabled(true);
					}
				}
			});
			
			setSize(400, 300);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
