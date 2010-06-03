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

import org.eclipse.jface.layout.TableColumnLayout;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.ColumnViewer;
import org.eclipse.jface.viewers.ColumnWeightData;
import org.eclipse.jface.viewers.EditingSupport;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.jface.viewers.TextCellEditor;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.RowData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.ui.part.ViewPart;
/**
 * 
 * @author Yongming Luo <yluo@ait.edu.gr>
 *
 */
public class LLRPExtraView extends ViewPart{
	Vector<Property> propertyTable;
	TableViewer v;
	Property lastElement;
	Composite table;
	
	
	public LLRPExtraView(Composite parent, Vector<Property> LLRPPropertyTable) {
		propertyTable = LLRPPropertyTable;
		createPartControl(parent);	
	}
	
	public void setSize(int width, int height) {
		table.setLayoutData(new RowData(width, height));
		v.refresh();
	}
	
	/*
	 * set the cursor to the new blank row
	 */
	public void toNewRow() {
		v.editElement(lastElement, 0);
	}
	
	public void removeSelectRow() {
		ISelection is = v.getSelection();
		if(is == null)
			return;
		Property selectProperty = (Property)((IStructuredSelection)is).getFirstElement();
		if(selectProperty == null)
			return;
		propertyTable.remove(selectProperty);
		v.refresh();
	}
	
	
	@Override
	public void createPartControl(Composite parent) {
		
		table = new Composite(parent, SWT.NONE);
		TableColumnLayout tableColumnLayout = new TableColumnLayout();
		table.setLayout(tableColumnLayout);
		
		
		v = new TableViewer(table,SWT.BORDER | SWT.FULL_SELECTION);
		
		
		//TableColumn single = new TableColumn(v.getTable(), SWT.NONE);
		//TableColumnLayout tcl;
		
		
		
		
		TableViewerColumn column = new TableViewerColumn(v,SWT.NONE);
		column.setLabelProvider(new PropertyLabelProvider());
		column.setEditingSupport(new PropertyEditing(v));
		
		column.getColumn().setWidth(200);
		//column.getColumn().setText("MyModel");
		column.getColumn().setMoveable(true);
		
		tableColumnLayout.setColumnData(column.getColumn(), new ColumnWeightData(100));
		
		v.setContentProvider(new PropertyContentProvider());
		//propertyTable = new Vector<Property>();
		propertyTable.add(new Property("aaa"));
		propertyTable.add(new Property("bbb"));
		lastElement = new Property("");
		propertyTable.add(lastElement);
		
		v.setInput(propertyTable);
		v.getTable().setLinesVisible(true);		

	}

	@Override
	public void setFocus() {
		// TODO Auto-generated method stub
		v.getControl().setFocus();
	}
	
	public class Property{
		private String name;
		public Property(String name) {
			this.name = name;
		}		
		public String toString() {
			return name;
		}
	}
	
	private class PropertyLabelProvider extends ColumnLabelProvider {
		@Override
		public String getText(Object element) {
			// TODO Auto-generated method stub
			return ((Property)element).toString();
		}
	}
	
	private class PropertyEditing extends EditingSupport {
		private TextCellEditor cellEditor;
		
		public PropertyEditing(ColumnViewer viewer) {
			super(viewer);
			cellEditor = new TextCellEditor(((TableViewer)viewer).getTable());
		}

		@Override
		protected boolean canEdit(Object element) {
			return true;
		}

		@Override
		protected CellEditor getCellEditor(Object element) {
			return cellEditor;
		}

		@Override
		protected Object getValue(Object element) {
			// TODO Auto-generated method stub
			return ((Property)element).toString();
		}

		@Override
		protected void setValue(Object element, Object value) {
			// TODO Auto-generated method stub
			//((MyModel)element).counter = 1;
			if(value.equals("")) {
				return;
			}
			((Property)element).name = (String)value;
			if(propertyTable.lastElement().equals(element)) {
				lastElement = new Property("");
				propertyTable.add(lastElement);
				v.refresh();
			}
			getViewer().update(element, null);
		}
	}
	
	private class PropertyContentProvider implements IStructuredContentProvider {
		/* (non-Javadoc)
		 * @see org.eclipse.jface.viewers.IStructuredContentProvider#getElements(java.lang.Object)
		 */
		public Object[] getElements(Object inputElement) {
			return ((Vector<Property>)inputElement).toArray();
		}
		/* (non-Javadoc)
		 * @see org.eclipse.jface.viewers.IContentProvider#dispose()
		 */
		public void dispose() {
			
		}
		@Override
		public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {	
		}		
	}


}
