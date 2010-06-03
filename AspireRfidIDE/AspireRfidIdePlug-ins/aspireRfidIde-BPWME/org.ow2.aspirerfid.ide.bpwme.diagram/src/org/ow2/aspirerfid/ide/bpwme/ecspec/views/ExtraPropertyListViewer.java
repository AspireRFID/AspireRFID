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

package org.ow2.aspirerfid.ide.bpwme.ecspec.views;

import java.util.Vector;

import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.ListViewer;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.layout.RowData;
import org.eclipse.swt.widgets.Composite;
import org.ow2.aspirerfid.ide.bpwme.ecspec.model.ExtraProperty;

/**
 * View extra properties
 * 
 * @author Yongming Luo <yluo@ait.edu.gr>
 *
 */
public class ExtraPropertyListViewer extends ListViewer{
	Vector<ExtraProperty> extraPropertyTable;
	int type;
	
	public ExtraPropertyListViewer(Composite parent, Vector<ExtraProperty> extraPropertyTable, int type) {
		super(parent);
		this.extraPropertyTable = extraPropertyTable;
		this.type = type;
		
		getControl().setLayoutData(new RowData(100,100));
		setContentProvider(new IStructuredContentProvider() {
			@Override
			public Object[] getElements(Object inputElement) {
				// TODO Auto-generated method stub
				return ((Vector<ExtraProperty>)inputElement).toArray();
			}

			@Override
			public void dispose() {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void inputChanged(Viewer viewer, Object oldInput,
					Object newInput) {
				// TODO Auto-generated method stub
				
			}
		});
		setInput(extraPropertyTable);
		setLabelProvider(new LabelProvider(){
			@Override
			public String getText(Object element) {
				// TODO Auto-generated method stub
				return ((ExtraProperty)element).toString();

				//return super.getText(element);
			}
		});
		addSelectionChangedListener(new ISelectionChangedListener(){

			@Override
			public void selectionChanged(SelectionChangedEvent event) {
				// TODO Auto-generated method stub
		}}); 
		
	}

	public boolean remove(ExtraProperty item) {
		return extraPropertyTable.remove(item);
	}
	
	public boolean add(ExtraProperty item) {
		return extraPropertyTable.add(item);
	}
	
	public boolean add(String name) {
		return extraPropertyTable.add(new ExtraProperty(name, type));
	}

	
	public void change(ExtraProperty item, String newName) {
		if((newName == null)|(newName.equals(""))){
			return;
		}
		item.name = newName;
	}

}
