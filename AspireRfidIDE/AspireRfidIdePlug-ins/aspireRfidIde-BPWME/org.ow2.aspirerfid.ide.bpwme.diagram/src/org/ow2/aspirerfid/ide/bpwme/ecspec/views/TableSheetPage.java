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

import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.ui.views.properties.PropertySheetEntry;
import org.eclipse.ui.views.properties.PropertySheetPage;
/**
 * 
 * @author Yongming Luo <yluo@ait.edu.gr>
 *
 */
public class TableSheetPage extends PropertySheetPage{
	
	private PropertySheetEntry selection = null;
	
	public String getSelectProperty() {
		if(selection != null){
			return selection.getDisplayName();
		}
		return null;
	}
	
	public void handleEntrySelection(ISelection selection) {
		if(selection != null) {
			StructuredSelection structuredSelection = (StructuredSelection)selection;
			if(!structuredSelection.isEmpty()) {
				this.selection = (PropertySheetEntry)structuredSelection.getFirstElement();
			}
		}
		super.handleEntrySelection(selection);
	}
}
