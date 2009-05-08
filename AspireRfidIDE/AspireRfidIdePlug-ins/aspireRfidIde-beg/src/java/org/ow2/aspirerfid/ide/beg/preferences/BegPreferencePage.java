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


package org.ow2.aspirerfid.ide.beg.preferences;

import org.eclipse.jface.preference.*;
import org.eclipse.jface.preference.IntegerFieldEditor;
import org.eclipse.jface.preference.StringFieldEditor;
import org.eclipse.ui.IWorkbenchPreferencePage;
import org.eclipse.ui.IWorkbench;
import org.ow2.aspirerfid.ide.beg.Activator;
import org.ow2.aspirerfid.ide.beg.preferences.PreferenceConstants;

/**
 * This class represents a preference page that
 * is contributed to the Preferences dialog. By 
 * subclassing <samp>FieldEditorPreferencePage</samp>, we
 * can use the field support built into JFace that allows
 * us to create a page that is small and knows how to 
 * save, restore and apply itself.
 * <p>
 * This page is used to modify preferences only. They
 * are stored in the preference store that belongs to
 * the main plug-in class. That way, preferences can
 * be accessed directly via the preference store.
 * 
 * @author Nikos Kefalakis (nkef) e-mail: nkef@ait.edu.gr
 *
 */
public class BegPreferencePage
	extends FieldEditorPreferencePage
	implements IWorkbenchPreferencePage {

	public BegPreferencePage() {
		super(GRID);
		setPreferenceStore(Activator.getDefault().getPreferenceStore());
		setTitle("Business Event Generator");
	}
	
	/**
	 * Creates the field editors. Field editors are abstractions of
	 * the common GUI blocks needed to manipulate various types
	 * of preferences. Each field editor knows how to save and
	 * restore itself.
	 */
	public void createFieldEditors() {
		final StringFieldEditor stringEpcisRepositoryCaptureURL = new StringFieldEditor(PreferenceConstants.P_EpcisRepositoryCaptureURL, "EPCIS Repository URL:", getFieldEditorParent());
		final StringFieldEditor stringEpcisRepositoryQueryURL = new StringFieldEditor(PreferenceConstants.P_EpcisRepositoryQueryURL, "EPCIS Repository URL:", getFieldEditorParent());	
		final StringListEditor listOfEventPortsBindings = new StringListEditor(PreferenceConstants.P_EventPortsBindings, "Events/Ports Bindings:",
				"Event/Port Binding:","Insert in \"Event@Port\" Format", getFieldEditorParent());
		
		

		addField(stringEpcisRepositoryCaptureURL);
		addField(stringEpcisRepositoryQueryURL);
		addField(listOfEventPortsBindings);

		{
			final IntegerFieldEditor observatorRefreshRate = new IntegerFieldEditor(PreferenceConstants.P_ObservatorRefreshRate, "Observator Refresh Rate (Sec):", getFieldEditorParent());
			observatorRefreshRate.setValidRange(1, Integer.MAX_VALUE);
			addField(observatorRefreshRate);
		}


		
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ui.IWorkbenchPreferencePage#init(org.eclipse.ui.IWorkbench)
	 */
	public void init(IWorkbench workbench) {
	}
	
}