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


package org.ow2.aspirerfid.ide.masterdata.classes;



import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;
import org.ow2.aspirerfid.ide.masterdata.classes.Attribute;
import org.ow2.aspirerfid.ide.masterdata.swtdesigner.SWTResourceManager;


/**
 * @author Eystathios Mertikas e-mail: efme@ait.edu.gr
 * 
 */
public class AttributesCollector extends Dialog {

	private Text Value_txt;
	private Text Attribute_txt;
	private Table AttrVal_Table;
	private Label errorLabel;
	protected Object result;
	protected Shell shell;
	private Menu remove;
	private List<Attribute>theAttributes;
	private List<ModifiedUnit> units; 
	private List<String>exclude;
	private String epcID;
	private boolean newAttributeAddition;
	
	private boolean modification;
	private String attribute_selected;
	private String value_selected;

	/**
	 * Create the dialog
	 * @param parent
	 * @param style
	 */
	public AttributesCollector(Shell parent, int style) {
		super(parent, style);
		modification = false;
		newAttributeAddition = false;
	}
	public AttributesCollector(Shell parent, int style, java.util.List<Attribute> attributes,String epc,List<String>ex) {
		super(parent, style);
		this.theAttributes = attributes;
		modification = true;
		newAttributeAddition = false;
		this.epcID = epc;
		exclude = new ArrayList<String>();
		this.exclude.addAll(ex);
	}

	
	public AttributesCollector(Shell parent, int style, java.util.List<Attribute> attributes,String epc) {
		super(parent, style);
		this.theAttributes = attributes;
		modification = true;
		newAttributeAddition = false;
		this.epcID = epc;
	}

	/**
	 * Create the dialog
	 * @param parent
	 */
	public AttributesCollector(Shell parent) {
		this(parent, SWT.NONE);
	}

	/**
	 * Open the dialog
	 * @return the result
	 */
	public Object open() {
		createContents();
		shell.open();
		shell.layout();
		Display display = getParent().getDisplay();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch())
				display.sleep();
		}
		if(modification)
			result = units;
		else
			result = theAttributes;
		return result;
	}

	public boolean newValueAdded()
	{
		if(Attribute_txt.getEditable() && modification)
			return true;
		return false;
	}
	public void removeAttribute(String attribute,String value)
	{
		if(modification)
		{
			//was this attribute just added so it does not exist in the db?
			boolean done = false;
			int total = units.size();
			for(int i = 0 ; i < total; i++)
			{
				if(units.get(i).getOldAttribute().equals(AttrVal_Table.getSelection()[0].getText(1)))
				{
					units.remove(i);
					done = true;
					break;
				}
			}
			if(!done)//exists in db
			{
				ModifiedUnit unit = new ModifiedUnit();
				unit.setOldEPC(epcID);
				unit.setOldAttribute(AttrVal_Table.getSelection()[0].getText(0));
				unit.setOldValue(AttrVal_Table.getSelection()[0].getText(1));
				unit.setRemove(true);
				unit.setNewValue(AttrVal_Table.getSelection()[0].getText(1));//for remove the new value field is not important. As long as it is not empty
				units.add(unit);
				
			}
			
		}
		
		
		int size = theAttributes.size();
		for(int i = 0; i < size; i++)
		{
			if(theAttributes.get(i).getAttribute().equals(attribute))
			{
				theAttributes.remove(i);
				AttrVal_Table.getSelection()[0].dispose();
				return;
			}
			
				
		}
	}
	public void go()
	{
		System.out.println("Attribute"+AttrVal_Table.getSelection()[0].getText(0));
		System.out.println("Value"+AttrVal_Table.getSelection()[0].getText(1));
		
		
	}
	
	public void modifyAttribute()
	{
		int row;
		boolean newAddition = true;
		if(!newValueAdded())
		{
			if(Value_txt.getText().equals(""))
				return;
			row = AttrVal_Table.getSelectionIndex();
			AttrVal_Table.getItem(row).setText(1, Value_txt.getText());
			newAddition = false;
		}
		
		ModifiedUnit unit = new ModifiedUnit();
		unit.setOldEPC(this.epcID);
		unit.setOldAttribute(Attribute_txt.getText());
		unit.setNewAddition(newAddition);
		if(value_selected!= null)
			unit.setOldValue(value_selected);
		else
			unit.setOldValue(Value_txt.getText());
		unit.setNewValue(Value_txt.getText());
		units.add(unit);
		
	}
	
	public boolean isAttributeExistTable(String attribute, String value)
	{
	
		TableItem[] rows = AttrVal_Table.getItems();
		int size = rows.length;
		for(int i = 0; i < size; i++)
		{
			if(rows[i].getText(0).equalsIgnoreCase(attribute))
			{
				errorLabel.setText("You have already added this attribute!");
				errorLabel.setVisible(true);
				return true;				
			}
			
		}
		
		if(!modification)
			theAttributes.add(new Attribute(attribute,value));
		return false;
	}
	
	private boolean isexcluded(String attribute)
	{
		
		if(this.exclude == null || exclude.size() == 0)
			return false;
		int total = exclude.size();
		for(int i = 0; i < total; i++)
			if(exclude.get(i).equals(attribute))
				return true;
		return false;
	}
	
	/**
	 * Create contents of the dialog
	 */
	protected void createContents() {
		shell = new Shell(getParent(), SWT.DIALOG_TRIM | SWT.APPLICATION_MODAL);
		shell.setSize(500, 443);
		shell.setText("Attribute-Value window");

		AttrVal_Table = new Table(shell, SWT.BORDER | SWT.FULL_SELECTION);
		AttrVal_Table.addMouseListener(new MouseAdapter() {
			public void mouseDown(final MouseEvent e) {
				if(e.button == 3)
					remove.setVisible(true);
				else if(e.button == 1)
				{
					attribute_selected = AttrVal_Table.getSelection()[0].getText(0);
					value_selected = AttrVal_Table.getSelection()[0].getText(1);
					Attribute_txt.setText(attribute_selected);
					Attribute_txt.setEditable(false);
					Value_txt.setText(value_selected);
				}
					
			}
		});
		
		remove = new Menu(shell, SWT.POP_UP);
		MenuItem removeRow = new MenuItem(remove, SWT.CASCADE);
		removeRow.setText("Remove row");
		removeRow.addSelectionListener(new SelectionAdapter(){
			public void widgetSelected(final SelectionEvent e){
				
				if(AttrVal_Table.getItemCount() == 0)
					return;
			
				removeAttribute(AttrVal_Table.getSelection()[0].getText(0),AttrVal_Table.getSelection()[0].getText(1));
				
				
				
			}
		});
		AttrVal_Table.setLinesVisible(true);
		AttrVal_Table.setHeaderVisible(true);
		AttrVal_Table.setBounds(25, 145, 410, 217);

		final TableColumn newColumnTableColumn = new TableColumn(AttrVal_Table, SWT.NONE);
		newColumnTableColumn.setWidth(200);
		newColumnTableColumn.setText("Attribute");

		final TableColumn newColumnTableColumn_1 = new TableColumn(AttrVal_Table, SWT.NONE);
		newColumnTableColumn_1.setWidth(200);
		newColumnTableColumn_1.setText("Value");

		Attribute_txt = new Text(shell, SWT.BORDER);
		Attribute_txt.setBounds(101, 77, 108, 25);

		final Label attributeLabel = new Label(shell, SWT.NONE);
		attributeLabel.setText("Attribute:");
		attributeLabel.setBounds(35, 80, 50, 15);

		final Label valueLabel = new Label(shell, SWT.NONE);
		valueLabel.setText("Value:");
		valueLabel.setBounds(230, 80, 50, 15);

		Value_txt = new Text(shell, SWT.BORDER);
		Value_txt.setBounds(284, 77, 118, 25);

		errorLabel = new Label(shell, SWT.NONE);
		errorLabel.setVisible(false);
		errorLabel.setBounds(56, 110, 346, 15);
		
		final Button addButton = new Button(shell, SWT.NONE);
		addButton.addMouseListener(new MouseAdapter() {
			public void mouseDown(final MouseEvent e) {
				
				
				if(Attribute_txt.getText().equals(""))
				{
					errorLabel.setVisible(true);
					errorLabel.setText("You must give attribute!");
					return;
					
				}
				else if(Value_txt.getText().equals(""))
				{
					errorLabel.setVisible(true);
					errorLabel.setText("You must give value!");
					return;
					
				}
				errorLabel.setVisible(false);
				
				

				//if(modification)
			//	{
				//	modifyAttribute();
				//	return;
					
				//}
				if(!modification)
				{
					if(!isAttributeExistTable(Attribute_txt.getText(),Value_txt.getText()))
					{
						TableItem row = new TableItem(AttrVal_Table,0);
						row.setText(new String[]{Attribute_txt.getText(),Value_txt.getText()});
					
						if(!newValueAdded())
						{
							Attribute_txt.setText("");
							Value_txt.setText("");
						}
					}
					else
						return;
				}
				else
				{
					if(newAttributeAddition)
					{
						if(!isAttributeExistTable(Attribute_txt.getText(),Value_txt.getText()))
						{
							TableItem row = new TableItem(AttrVal_Table,0);
							row.setText(new String[]{Attribute_txt.getText(),Value_txt.getText()});
							
							
							ModifiedUnit unit = new ModifiedUnit();
							
							unit.setOldEPC(epcID);
							unit.setOldAttribute(Attribute_txt.getText());
							unit.setNewAddition(true);
							unit.setOldValue(Value_txt.getText());
							unit.setNewValue(Value_txt.getText());
							units.add(unit);
							
							
							
							
							
							
						}
						
					}
					else
						modifyAttribute();
				}
						
						
					
				
				Attribute_txt.setText("");
				Value_txt.setText("");
				
				
				
				
				
				/*if(newValueAdded())
				{
					
					
				}
				
				if(modification)
				{
					modifyAttribute();
					
				}
				
				if(Attribute_txt.getText().equals(""))
				{
					errorLabel.setVisible(true);
					errorLabel.setText("You must give attribute!");
					return;
					
				}
				else if(Value_txt.getText().equals(""))
				{
					errorLabel.setVisible(true);
					errorLabel.setText("You must give value!");
					return;
					
				}
				errorLabel.setVisible(false);
				
				if(addAttribute(Attribute_txt.getText(),Value_txt.getText()))
				{
					TableItem row = new TableItem(AttrVal_Table,0);
					row.setText(new String[]{Attribute_txt.getText(),Value_txt.getText()});
					Attribute_txt.setText("");
					Value_txt.setText("");
				}
				*/
			}
		});
		addButton.setText("Add/Modify");
		addButton.setBounds(79, 382, 76, 25);

		final Button okButton = new Button(shell, SWT.NONE);
		okButton.addMouseListener(new MouseAdapter() {
			public void mouseDown(final MouseEvent e) {
				getParent().close();
			}
		});
		okButton.setText("Close");
		okButton.setBounds(386, 382, 48, 25);

		final Label pleaseEnterTheLabel = new Label(shell, SWT.NONE);
		pleaseEnterTheLabel.setFont(SWTResourceManager.getFont("Palatino Linotype", 10, SWT.BOLD));
		pleaseEnterTheLabel.setText("Please enter the attributes and values");
		pleaseEnterTheLabel.setBounds(25, 40, 346, 25);

		if(this.modification)
		{
			int size = this.theAttributes.size();
			for(int i = 0; i < size; i++)
			{
				if(isexcluded(theAttributes.get(i).getAttribute()))
					continue;
				TableItem row = new TableItem(AttrVal_Table,0);
				row.setText(new String[]{theAttributes.get(i).getAttribute(),theAttributes.get(i).getValue()});
			}
			
			Attribute_txt.setEnabled(false);
			units = new ArrayList<ModifiedUnit>();
			
		}
		else
			theAttributes = new ArrayList<Attribute>();

		final Label epcLabel = new Label(shell, SWT.NONE);
		epcLabel.setFont(SWTResourceManager.getFont("Palatino Linotype", 11, SWT.BOLD, false, true));
		epcLabel.setText("ll");
		epcLabel.setBounds(25, 0, 322, 25);
	    if(modification)
	    	epcLabel.setText(this.epcID);

		final Button newButton = new Button(shell, SWT.NONE);
		newButton.addMouseListener(new MouseAdapter() {
			public void mouseDown(final MouseEvent e) {
				Attribute_txt.setText("");
				Attribute_txt.setEditable(true);
				Attribute_txt.setEnabled(true);
				Value_txt.setText("");
				newAttributeAddition = true;
			}
		});
		newButton.setText("New");
		newButton.setBounds(25, 382, 48, 25);
		

		
		
		//
	}

}
