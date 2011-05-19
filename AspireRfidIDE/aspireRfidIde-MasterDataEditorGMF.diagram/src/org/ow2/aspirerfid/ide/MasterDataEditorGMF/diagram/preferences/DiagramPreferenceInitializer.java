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

import org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer;
import org.eclipse.jface.preference.IPreferenceStore;

/**
 * @generated
 */
public class DiagramPreferenceInitializer extends AbstractPreferenceInitializer {

	/**
	 * @generated NOT
	 */
	public void initializeDefaultPreferences() {
		IPreferenceStore store = getPreferenceStore();
		org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.preferences.DiagramPrintingPreferencePage
				.initDefaults(store);
		org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.preferences.DiagramGeneralPreferencePage
				.initDefaults(store);
		org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.preferences.DiagramAppearancePreferencePage
				.initDefaults(store);
		org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.preferences.DiagramConnectionsPreferencePage
				.initDefaults(store);
		org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.preferences.DiagramRulersAndGridPreferencePage
				.initDefaults(store);
		org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.preferences.DiagramConfiguratorPreferencePage
				.initDefaults(store);
	}

	/**
	 * @generated
	 */
	protected IPreferenceStore getPreferenceStore() {
		return org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.part.MasterDataEditorGMFDiagramEditorPlugin
				.getInstance().getPreferenceStore();
	}
}
