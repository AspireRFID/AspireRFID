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

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.ow2.aspirerfid.ide.masterdata.swtdesigner.SWTResourceManager;
import org.ow2.aspirerfid.ide.masterdata.views.*;
import java.util.*;

/**
 * @author Eystathios Mertikas e-mail: efme@ait.edu.gr
 * 
 */
public class Edit_Attributes extends Dialog {

	private Combo oldValue;
	private Combo newValue;
	private List list_biz_attrs;
	protected Object result;
	protected Shell shell;


	private MasterDataEditorView mdi;
	private java.util.List<org.ow2.aspirerfid.ide.masterdata.classes.Attribute>Attributes;
	private boolean remove = false;
	private int current_position = -1;
	Label mdfLabel;
	/**
	 * Create the dialog
	 * @param parent
	 * @param style
	 */
	public Edit_Attributes(Shell parent, MasterDataEditorView mdi) {
		super(parent, 1);
		this.mdi = mdi;
	}
	public Edit_Attributes(Shell parent, int style) {
		super(parent, style);
	}

	/**
	 * Create the dialog
	 * @param parent
	 */
	public Edit_Attributes(Shell parent) {
		this(parent, SWT.NONE);
	}

	public void showData()
	{
		this.Attributes  = mdi.underEdit.getAttributes();
		java.util.Iterator<org.ow2.aspirerfid.ide.masterdata.classes.Attribute>it = Attributes.iterator();
		
		
		while(it.hasNext())
		{
		
			org.ow2.aspirerfid.ide.masterdata.classes.Attribute temp = it.next();
			System.out.println("Attr = "+temp.getAttribute());
			if(temp.getAttribute().endsWith("Read Point") || temp.getAttribute().endsWith("Name") || temp.getAttribute().endsWith("Address") || temp.getAttribute().endsWith("Description") || temp.getAttribute().endsWith("City") || temp.getAttribute().endsWith("Country"))
				continue;
			//list_biz_attrs.add(temp.getAttribute().substring("urn:epcglobal:epcis:mda:".length(),temp.getAttribute().length()));
			list_biz_attrs.add(temp.getAttribute());
//			list_biz_attrs.setItem(i, temp.getAttribute());
//			i++;
			
		}
		
		
		
	}
	
	
	
	public void showValue()
	{
		String attr_selected = list_biz_attrs.getSelection()[0];
		
		java.util.Iterator<org.ow2.aspirerfid.ide.masterdata.classes.Attribute>it = Attributes.iterator();
		int idx = 0;
		String value = "";
	/*	if(attr_selected.equals("Read Point"))
		{
			System.out.println("&&&&&&&&&&&&&&IN READ POINTS ");
			oldValue.setEnabled(true);
			if(mdi == null)
				System.out.println("!!!!!!!!!!MDI NULL!!!!!!!!!!!!!");
			if(mdi.underEdit == null)
				System.out.println("UNDER EDIT NULL");
			if(mdi.underEdit.getReadPoints()  == null)
				System.out.println("READ POINTS NULL");
			java.util.List<String> rps = mdi.underEdit.getReadPoints();
			System.out.println("$$$$$$$$$$$$$$$$READ POINTS TO SHOW ");//+mdi.underEdit.getReadpoints().toString());
			if(rps == null)
				System.out.println("rps null");
			Iterator<String>its = rps.iterator();
			while(its.hasNext())
			{

				String val = its.next();
				System.out.println("RPS   "+val);
				oldValue.setText(val);
			}
			//offer options
			org.ow2.aspirerfid.ide.masterdata.classes.ReadPointsReader reader_RP = new org.ow2.aspirerfid.ide.masterdata.classes.ReadPointsReader(mdi.getQueryClient());
			mdi.info = reader_RP.getTags_Names();
			java.util.Set<java.util.Map.Entry<Key_Map,String>> se = mdi.info.entrySet();
					
			for(Iterator<java.util.Map.Entry<Key_Map,String>> i = se.iterator();i.hasNext();)
			{
				java.util.Map.Entry<Key_Map,String> temp = i.next();
				Key_Map k = temp.getKey();
				String v = temp.getValue();
				System.out.println("Key = <"+k.getIdx()+","+k.getName()+"> - Value = <"+v+">");
				
				newValue.add(k.getName());
				
						
						
			}
					
			
			
			
			
			
			
			
		}*/
		//else
		//{	
			oldValue.setEnabled(false);
			while(it.hasNext())
			{
				org.ow2.aspirerfid.ide.masterdata.classes.Attribute temp = it.next();
				if(temp.getAttribute().endsWith(attr_selected))
				{				
					value = temp.getValue();
					break;
				}
				idx++;
				
			}
			current_position = idx;
			oldValue.setText(value);
		//}
		
		
		
		
		
	}
	public void doModify()
	{
		
			ModifiedUnit unit = new ModifiedUnit();
			unit.setOldEPC(this.mdi.underEdit.getFQTag());
			unit.setOldAttribute(list_biz_attrs.getSelection()[0]);
			unit.setOldValue(oldValue.getText());
			
			if(remove)
			{
				unit.setRemove(true);
				unit.setNewValue(oldValue.getText());//for remove the new value field is not important. As long as it is not empty
			}
			else
				unit.setNewValue(newValue.getText());
			
			this.mdi.modifications_pool.addUnit(unit);
			this.getParent().close();
			
		
		
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
		
		return result;
	}

	/**
	 * Create contents of the dialog
	 */
	protected void createContents() {
		System.out.println("create");
		shell = new Shell(getParent(), SWT.DIALOG_TRIM | SWT.APPLICATION_MODAL);
		shell.setSize(500, 375);
		shell.setText("Modify Attributes");

		final Composite composite = new Composite(shell, SWT.NONE);
		composite.setBounds(0, 0, 484, 349);

		list_biz_attrs = new List(composite, SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL);
		list_biz_attrs.addMouseListener(new MouseAdapter() {
			public void mouseDown(final MouseEvent e) {
				showValue();
			}
		});
		list_biz_attrs.setBounds(24, 57, 100, 100);

		final Label selectAttributeLabel = new Label(composite, SWT.NONE);
		selectAttributeLabel.setText("Select Attribute");
		selectAttributeLabel.setBounds(24, 23, 216, 15);

		final Group modifyGroup = new Group(composite, SWT.NONE);
		modifyGroup.setText("Modify");
		modifyGroup.setBounds(153, 44, 297, 133);

		final Label oldLabel = new Label(modifyGroup, SWT.NONE);
		oldLabel.setText("Old");
		oldLabel.setBounds(10, 28, 47, 15);

		final Label newLabel = new Label(modifyGroup, SWT.NONE);
		newLabel.setText("New");
		newLabel.setBounds(10, 88, 28, 15);

		newValue = new Combo(modifyGroup, SWT.NONE);
		newValue.setBounds(73, 85, 193, 23);

		oldValue = new Combo(modifyGroup, SWT.NONE);
		oldValue.setFont(SWTResourceManager.getFont("", 11, SWT.BOLD));
		oldValue.setEnabled(false);
		oldValue.setBounds(73, 25, 193, 23);

		final Button removeButton = new Button(composite, SWT.CHECK);
		removeButton.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(final SelectionEvent e) {
				if(removeButton.getSelection())
				{
					remove = true;
					mdfLabel.setVisible(true);
				}
				else
				{
					remove = false;
					mdfLabel.setVisible(false);
				}
				
			}
		});
		removeButton.setText("Remove");
		removeButton.setBounds(24, 270, 93, 16);

		final Button modifyButton = new Button(composite, SWT.NONE);
		modifyButton.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(final SelectionEvent e) {
				doModify();
			}
		});
		modifyButton.setText("Modify");
		modifyButton.setBounds(371, 270, 48, 25);

		mdfLabel = new Label(composite, SWT.NONE);
		mdfLabel.setVisible(false);
		mdfLabel.setForeground(SWTResourceManager.getColor(255, 0, 0));
		mdfLabel.setText("Press modify to permanently remove the attribute");
		mdfLabel.setBounds(106, 206, 313, 41);
		list_biz_attrs.removeAll();
		showData();
		//
	}

}
