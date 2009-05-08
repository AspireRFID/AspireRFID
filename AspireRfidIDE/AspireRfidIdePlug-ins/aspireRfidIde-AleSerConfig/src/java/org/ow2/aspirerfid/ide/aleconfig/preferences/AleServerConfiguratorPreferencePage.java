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

package org.ow2.aspirerfid.ide.aleconfig.preferences;

import org.eclipse.jface.preference.*;
import org.eclipse.ui.IWorkbenchPreferencePage;
import org.eclipse.ui.IWorkbench;
import org.ow2.aspirerfid.ide.aleconfig.Activator;
import org.ow2.aspirerfid.ide.aleconfig.preferences.PreferenceConstants;

/**
 * This class represents a preference page that is contributed to the
 * Preferences dialog. By subclassing <samp>FieldEditorPreferencePage</samp>, we
 * can use the field support built into JFace that allows us to create a page
 * that is small and knows how to save, restore and apply itself.
 * <p>
 * This page is used to modify preferences only. They are stored in the
 * preference store that belongs to the main plug-in class. That way,
 * preferences can be accessed directly via the preference store.
 * 
 * @author Vasso Koletti e-mail: vkol@ait.edu.gr
 * @author Nikos Kefalakis (nkef) e-mail: nkef@ait.edu.gr
 * 
 */
public class AleServerConfiguratorPreferencePage extends FieldEditorPreferencePage implements IWorkbenchPreferencePage {

	public AleServerConfiguratorPreferencePage() {
		super(GRID);
		setPreferenceStore(Activator.getDefault().getPreferenceStore());
		setDescription("ALE Server Configurator Preferences");
	}

	/**
	 * Creates the field editors. Field editors are abstractions of the common
	 * GUI blocks needed to manipulate various types of preferences. Each field
	 * editor knows how to save and restore itself.
	 */
	public void createFieldEditors() {

		{
			addField(new DirectoryFieldEditor(PreferenceConstants.P_ECSpecsPATH, "EC Specs Directory:", getFieldEditorParent()));
		}

		{
			addField(new StringFieldEditor(PreferenceConstants.P_ALEClientEndPointSTRING, "ALE Client End Point:", getFieldEditorParent()));
		}

		{
			addField(new DirectoryFieldEditor(PreferenceConstants.P_HAL_LRSpecsPATH, "HAL LR Specs Directory:", getFieldEditorParent()));
		}

		{
			addField(new DirectoryFieldEditor(PreferenceConstants.P_RP_LRSpecsPATH, "RP LR Specs Directory:", getFieldEditorParent()));
		}

		{
			addField(new DirectoryFieldEditor(PreferenceConstants.P_LLRP_LRSpecsPATH, "LLRP LR Specs Directory:", getFieldEditorParent()));
		}

		{
			addField(new DirectoryFieldEditor(PreferenceConstants.P_Composite_LRSpecsPATH, "Composite LR Specs Directory:", getFieldEditorParent()));
		}

		{
			addField(new StringFieldEditor(PreferenceConstants.P_ALELRClientEndPointSTRING, "ALE LR Client End Point", getFieldEditorParent()));
		}

		{
			final StringListEditor listOfNotificationURIs = new StringListEditor(PreferenceConstants.P_NotificationURI, "List of Notification URIs:",
					"Notification URI Dialog", "Insert Notification URI:", getFieldEditorParent());
			addField(listOfNotificationURIs);
		}

		{
			final StringListEditor listOfReaderNames = new StringListEditor(PreferenceConstants.P_ReaderNames, "List of Reader Names:",
					"Reader Name Dialog", "Insert Reader Name:", getFieldEditorParent());
			addField(listOfReaderNames);
		}

		{
			final StringListEditor listOfConnectionPoints = new StringListEditor(PreferenceConstants.P_ConnectionPoints,
					"List of Connection points:", "Connection point Dialog", "Insert Connection point:", getFieldEditorParent());
			addField(listOfConnectionPoints);
		}

		{
			final StringListEditor listOfNotificationPoints = new StringListEditor(PreferenceConstants.P_NotificationPoints,
					"Notification point Dialog", "Insert Notification point:", "List of Notification points:", getFieldEditorParent());
			addField(listOfNotificationPoints);
		}

		{
			final StringListEditor listOfECSpecNames = new StringListEditor(PreferenceConstants.P_ECSpecNames, "List of ECSpec Names:",
					"ECSpec Name Dialog", "Insert ECSpec Name:", getFieldEditorParent());
			addField(listOfECSpecNames);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.ui.IWorkbenchPreferencePage#init(org.eclipse.ui.IWorkbench)
	 */
	public void init(IWorkbench workbench) {
	}

}