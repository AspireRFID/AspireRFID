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

package org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.preferences;

import java.util.ArrayList;
import java.util.StringTokenizer;

import org.eclipse.jface.dialogs.IInputValidator;
import org.eclipse.jface.dialogs.InputDialog;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.preference.ListEditor;
import org.eclipse.swt.widgets.Composite;
import org.ow2.aspirerfid.ide.MasterDataEditorGMF.querycapture.MasterDataEditParts;

/**
 * @author Eleftherios Karageorgiou (elka) e-mail: elka@ait.edu.gr
 * 
 */
public class ReadPointStringListEditor extends ListEditor {

	/**
	 * The last path, or <code>null</code> if none.
	 */
	@SuppressWarnings("unused")
	private String lastPath;
	

	/**
	 * The special label text for directory chooser, or <code>null</code> if
	 * none.
	 */

	private String dialogtitle;
	private String inputStringtitle;

	/**
	 * Creates a new path field editor
	 */
	protected ReadPointStringListEditor() {
	}

	/**
	 * Creates a path field editor.
	 * 
	 * @param name
	 *            the name of the preference this field editor works on
	 * @param labelText
	 *            the label text of the field editor
	 * @param dialogtitle
	 *            the label text displayed for the input dialog
	 * @param inputStringtitle
	 *            the label text displayed for the input string
	 * @param parent
	 *            the parent of the field editor's control
	 */
	public ReadPointStringListEditor(String name, String labelText, String dialogtitle,String inputStringtitle, Composite parent) {
		init(name, labelText);
		this.dialogtitle = dialogtitle;
		this.inputStringtitle = inputStringtitle;
		createControl(parent);
	}

	/**
	 * Method declared on ListEditor. Creates a single string from
	 * the given array by separating each string with the appropriate
	 * OS-specific path separator.
	 */
	protected String createList(String[] items) {
		StringBuffer path = new StringBuffer("");//$NON-NLS-1$
		String newAttr[] = new String[items.length];
				
		for (int i = 0; i < items.length; i++) {
			newAttr[i] = items[i];
//			path.append(items[i]);
//			path.append(",");
		}
		
		String turnNewAttr[] = new String[newAttr.length];
		
		for (int i = turnNewAttr.length - 1, j = 0; i >= 0; i--, j++) {
			if (!(newAttr[i].isEmpty() && newAttr[i] == ""))
				turnNewAttr[j] = newAttr[i];
		}
		
		MasterDataEditParts.setNewReadPointAttr(newAttr);
		
		for (int i = 0; i < MasterDataEditParts.getNewReadPointAttr().length; i++) {
			path.append(MasterDataEditParts.getNewReadPointAttr()[i]);
			path.append(",");
		}

		return path.toString();
	}

	/**
	 * Method declared on ListEditor. Creates a new path element
	 * by means of a directory dialog.
	 */
	protected String getNewInputObject() {
		if (getList().getItemCount() == 7) {
			MessageDialog.openError(getShell(), "Error", "You can insert up to 20 custom attributes.");
			return null;
		}
		
		IInputValidator validator = new IInputValidator() {
	          public String isValid(String newText) {
	            if(newText.equals(""))
	              return "";
	            return null;
	          }
	        };
	
		InputDialog inputDialog = new InputDialog(getShell(), dialogtitle, inputStringtitle, "", validator);
		inputDialog.open();
		String input = inputDialog.getValue();
		
		if (input.isEmpty() || input == "")
			return null;
		
		for (int i = 0; i < getList().getItemCount(); i++) {
			if (input.equals(getList().getItem(i))) {
				MessageDialog.openError(getShell(), "Error", "The attribute with the name " + input + " already exists.");
				return null;
			}
		}
		
		return input;
	}

	/**
	 * Method declared on ListEditor.
	 */
	protected String[] parseString(String stringList) {
		StringTokenizer st = new StringTokenizer(stringList, "," + "\n\r");//$NON-NLS-1$ //File.pathSeparator
		ArrayList<String> v = new ArrayList<String>();
		while (st.hasMoreElements()) {
			v.add((String) st.nextElement());
		}
		return (String[]) v.toArray(new String[v.size()]);
	}

}