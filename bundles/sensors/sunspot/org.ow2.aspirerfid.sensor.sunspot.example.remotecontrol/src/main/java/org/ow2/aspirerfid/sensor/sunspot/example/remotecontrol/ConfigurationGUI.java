package org.ow2.aspirerfid.sensor.sunspot.example.remotecontrol;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;

import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;

public class ConfigurationGUI  extends JPanel implements ItemListener, ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6759865062457768456L;
	
	private JCheckBox mouseButton;
	private JComboBox sw1;
	private JComboBox sw2;
	
	private String[] actionList = {"Left mouse button", "Right mouse button", "Left arrow", "Right arrow"};
	
	private RemoteController rc;
	
	public ConfigurationGUI(RemoteController rc) {
		super(new BorderLayout());
		
		this.rc = rc;
		
		// create checkbox
		mouseButton = new JCheckBox("Enable mouse cursor");
		mouseButton.setSelected(rc.isMouseEnabled());
		mouseButton.addItemListener(this);
		
		// create lists
		sw1 = new JComboBox(actionList);
		sw2 = new JComboBox(actionList);
		sw1.addActionListener(this);
		sw2.addActionListener(this);
		
		switch (rc.getSw1Action()) {
			case InputEvent.BUTTON1_MASK:
				sw1.setSelectedIndex(0);
				break;
			case InputEvent.BUTTON2_MASK:
				sw1.setSelectedIndex(1);
				break;	
			case KeyEvent.VK_LEFT:
				sw1.setSelectedIndex(2);
				break;
			case KeyEvent.VK_RIGHT:
				sw1.setSelectedIndex(3);
				break;
		}
		
		switch (rc.getSw2Action()) {
			case InputEvent.BUTTON1_MASK:
				sw2.setSelectedIndex(0);
				break;
			case InputEvent.BUTTON3_MASK:
				sw2.setSelectedIndex(1);
				break;	
			case KeyEvent.VK_LEFT:
				sw2.setSelectedIndex(2);
				break;
			case KeyEvent.VK_RIGHT:
				sw2.setSelectedIndex(3);
				break;	
		}
		JPanel comboBoxes = new JPanel(new GridLayout(2,2));
		JLabel lab1 = new JLabel();
		lab1.setText("SW 1 :");
		JLabel lab2 = new JLabel();
		lab2.setText("SW 2 :");
		
		comboBoxes.add(lab1);
		comboBoxes.add(lab2);
		comboBoxes.add(sw1);
		comboBoxes.add(sw2);


		this.add(mouseButton, BorderLayout.NORTH);
		this.add(new JSeparator(), BorderLayout.CENTER);
		this.add(comboBoxes, BorderLayout.SOUTH);
		
	}

	public void itemStateChanged(ItemEvent evt) {
		Object source = evt.getItemSelectable();

        if (source == mouseButton) {
        	if (evt.getStateChange() == ItemEvent.SELECTED) {
        		rc.setMouseEnabled(true);
        	} else {
        		rc.setMouseEnabled(false);
        	}
        }
	}

	public void actionPerformed(ActionEvent evt) {
		Object source = evt.getSource();
        if (source instanceof JComboBox) {
			JComboBox swBox = (JComboBox) source;
			int action = swBox.getSelectedIndex();
			if (swBox.equals(sw1)){
		        switch(action){
		        	case 0:
		        		rc.setSw1Mode(rc.MOUSE_MODE);
		        		rc.setSw1Action(InputEvent.BUTTON1_MASK);
		        		break;
		        	case 1:
		        		rc.setSw1Mode(rc.MOUSE_MODE);
		        		rc.setSw1Action(InputEvent.BUTTON3_MASK);
		        		break;
		        	case 2:
		        		rc.setSw1Action(KeyEvent.VK_LEFT);
		        		rc.setSw1Mode(rc.KEYBOARD_MODE);
		        		break;
		        	case 3:
		        		rc.setSw1Action(KeyEvent.VK_RIGHT);
		        		rc.setSw1Mode(rc.KEYBOARD_MODE);
		        		break;
		        }
			} else if (swBox.equals(sw2)){
		        switch(action){
	        	case 0:
	        		rc.setSw2Mode(rc.MOUSE_MODE);
	        		rc.setSw2Action(InputEvent.BUTTON1_MASK);
	        		break;
	        	case 1:
	        		rc.setSw2Mode(rc.MOUSE_MODE);
	        		rc.setSw2Action(InputEvent.BUTTON3_MASK);
	        		break;
	        	case 2:
	        		rc.setSw2Action(KeyEvent.VK_LEFT);
	        		rc.setSw2Mode(rc.KEYBOARD_MODE);
	        		break;
	        	case 3:
	        		rc.setSw2Action(KeyEvent.VK_RIGHT);
	        		rc.setSw2Mode(rc.KEYBOARD_MODE);
	        		break;
		        }
			}
		}

	}
	
}
