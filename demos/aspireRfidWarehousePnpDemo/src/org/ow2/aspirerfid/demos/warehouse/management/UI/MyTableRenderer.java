package org.ow2.aspirerfid.demos.warehouse.management.UI;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

/**
 * @author nkons (Nikolaos Konstantinou)
 *
 */
public class MyTableRenderer extends DefaultTableCellRenderer {

	private static final long serialVersionUID = 1542304312L;

	public MyTableRenderer() {
		setOpaque(true);
	}

	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int col) {

		//column 9 is the "is Needed" column
		String s = table.getModel().getValueAt(row, 9).toString();

		if (s.equalsIgnoreCase("false")) {
			setBackground(Color.RED);
			setForeground(Color.WHITE);
		} else {
			setBackground(Color.GREEN);
			setForeground(Color.WHITE);
		}
		
		return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, col);
	}


}