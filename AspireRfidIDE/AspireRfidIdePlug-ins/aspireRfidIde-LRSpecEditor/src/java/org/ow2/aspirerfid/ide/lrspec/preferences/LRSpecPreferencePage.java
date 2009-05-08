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

package org.ow2.aspirerfid.ide.lrspec.preferences;

import org.eclipse.jface.preference.*;
import org.eclipse.ui.IWorkbenchPreferencePage;
import org.eclipse.ui.IWorkbench;
import org.ow2.aspirerfid.ide.lrspec.preferences.StringListEditor;
import org.ow2.aspirerfid.ide.lrspec.preferences.PreferenceConstants;
import org.ow2.aspirerfid.ide.lrspec.Activator;

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
 * @author Nikos Kefalakis (nkef) e-mail: nkef@ait.edu.gr
 * @author Vasso Koletti e-mail: vkol@ait.edu.gr
 */
public class LRSpecPreferencePage extends FieldEditorPreferencePage implements IWorkbenchPreferencePage {

	public LRSpecPreferencePage() {
		super(GRID);
		setPreferenceStore(Activator.getDefault().getPreferenceStore());
		setDescription("LRSpec Editor Preferences");
	}

	/**
	 * Creates the field editors. Field editors are abstractions of the common
	 * GUI blocks needed to manipulate various types of preferences. Each field
	 * editor knows how to save and restore itself.
	 */

	public void createFieldEditors() {
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

		final StringListEditor listOfConnectionPoints = new StringListEditor(PreferenceConstants.P_ConnectionPoints,
				"List of RP Readers Connection points:", "RP Reader Connection point Dialog", "Insert RP Reader Connection point:",
				getFieldEditorParent());
		addField(listOfConnectionPoints);

		final StringListEditor listOfNotificationPoints = new StringListEditor(PreferenceConstants.P_NotificationPoints,
				"List of RP Readers Notification points:", "RP Reader Notification point Dialog", "Insert RP Reader Notification point:",getFieldEditorParent());
		addField(listOfNotificationPoints);

		final StringListEditor listOfDynamicReaders = new StringListEditor(PreferenceConstants.P_DynamicReaders,
				"List of Available Dynamic Readers:", "Available Dynamic Reader Dialog","Insert Available Dynamic Reader:", getFieldEditorParent());
		addField(listOfDynamicReaders);
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