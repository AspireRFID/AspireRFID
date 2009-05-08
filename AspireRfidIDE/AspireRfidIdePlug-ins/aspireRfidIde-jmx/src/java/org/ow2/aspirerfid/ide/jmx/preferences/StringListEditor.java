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


package org.ow2.aspirerfid.ide.jmx.preferences;

import java.io.File;
import java.util.ArrayList;
import java.util.StringTokenizer;

import org.eclipse.jface.dialogs.InputDialog;
import org.eclipse.jface.preference.ListEditor;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.DirectoryDialog;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;


/**
 * @author Nikos Kefalakis (nkef) e-mail: nkef@ait.edu.gr
 *
 */
public class StringListEditor extends ListEditor {


    /**
     * The last path, or <code>null</code> if none.
     */
    private String lastPath;

    /**
     * The special label text for directory chooser, 
     * or <code>null</code> if none.
     */
    private String dirChooserLabelText;

    public static final String SEPARATOR = ",";
    /**
     * Creates a new path field editor 
     */
    protected StringListEditor() {
    }

    /**
     * Creates a path field editor.
     * 
     * @param name the name of the preference this field editor works on
     * @param labelText the label text of the field editor
     * @param dirChooserLabelText the label text displayed for the directory chooser
     * @param parent the parent of the field editor's control
     */
    public StringListEditor(String name, String labelText, String dirChooserLabelText, Composite parent) {
        init(name, labelText);
        this.dirChooserLabelText = dirChooserLabelText;
        createControl(parent);
    }

    /* (non-Javadoc)
     * Method declared on ListEditor.
     * Creates a single string from the given array by separating each
     * string with the appropriate OS-specific path separator.
     */
    protected String createList(String[] items) {
        StringBuffer path = new StringBuffer("");//$NON-NLS-1$

        for (int i = 0; i < items.length; i++) {
            path.append(items[i]);
            path.append(SEPARATOR);
        }
        return path.toString();
    }

    /* (non-Javadoc)
     * Method declared on ListEditor.
     * Creates a new path element by means of a directory dialog.
     */
    protected String getNewInputObject() {

    	InputDialog inputDialog = new InputDialog(getShell(), "Reader classes", "Insert fully qualified RP proxy implementation class names", "", null);
    	inputDialog.open();
    	String input = inputDialog.getValue();
    	return input;
    }

    /* (non-Javadoc)
     * Method declared on ListEditor.
     */
    protected String[] parseString(String stringList) {
        StringTokenizer st = new StringTokenizer(stringList, SEPARATOR);//$NON-NLS-1$
        ArrayList v = new ArrayList();
        while (st.hasMoreElements()) {
            v.add(st.nextElement());
        }
        return (String[]) v.toArray(new String[v.size()]);
    }
	
	
}
