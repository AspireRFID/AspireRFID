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

package org.ow2.aspirerfid.ide.bpwme.ecspec.provider;

import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.ColumnViewer;
import org.eclipse.jface.viewers.EditingSupport;
import org.eclipse.jface.viewers.ICellEditorValidator;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TextCellEditor;
import org.eclipse.ui.PlatformUI;
import org.ow2.aspirerfid.ide.bpwme.ecspec.model.BoundaryContent;
import org.ow2.aspirerfid.ide.bpwme.utils.MainUtil;

/**
 * 
 * @author Yongming Luo <yluo@ait.edu.gr>
 *
 */
public class BoundaryEditingSupport extends EditingSupport{
	private CellEditor editor;

	
	public BoundaryEditingSupport(ColumnViewer viewer) {
		super(viewer);
		editor = new TextCellEditor(((TableViewer) viewer).getTable());
//		editor.setValidator(new ICellEditorValidator(){
//
//			@Override
//			public String isValid(Object value) {
//				if (value instanceof String) {
//					if(MainUtil.isInteger((String)value)) {
//						MainUtil.showInStatus(null);
//						return (String)value;
//					}
//				}
//				MainUtil.showInStatus("Should be an integer");
//				return null;
//			}});
	}

	@Override
	protected boolean canEdit(Object element) {
		return true;
	}

	@Override
	protected CellEditor getCellEditor(Object element) {
		return editor;
	}

	@Override
	protected Object getValue(Object element) {
		BoundaryContent bc = (BoundaryContent)element;
		return bc.getValue();
	}

	@Override
	protected void setValue(Object element, Object value) {		
		BoundaryContent bc = (BoundaryContent)element;
		bc.setValue((String)value);
		getViewer().update(element, null);
	}
	
}
