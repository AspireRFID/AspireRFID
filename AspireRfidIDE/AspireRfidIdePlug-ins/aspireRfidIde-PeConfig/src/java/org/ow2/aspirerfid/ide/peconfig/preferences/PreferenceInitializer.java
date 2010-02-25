/*
 * Copyright (C) 2008-2010, Aspire
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


package org.ow2.aspirerfid.ide.peconfig.preferences;

import org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer;
import org.eclipse.jface.preference.IPreferenceStore;

import org.ow2.aspirerfid.ide.peconfig.Activator;
/**
 * @author Nikos Kefalakis (nkef) e-mail: nkef@ait.edu.gr
 *
 * Class used to initialize default preference values.
 */

public class PreferenceInitializer extends AbstractPreferenceInitializer {


	private static String fileSeparator = System.getProperty("file.separator");
	private static String userHome = System.getProperty("user.home");
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer#initializeDefaultPreferences()
	 */
	public void initializeDefaultPreferences() {
		IPreferenceStore store = Activator.getDefault().getPreferenceStore();

		store.setDefault(PreferenceConstants.P_PE_ApdlFilesPath, userHome+fileSeparator+"AspireRFID"+fileSeparator+"IDE"+fileSeparator+"APDLs"+fileSeparator);

		
		store.setDefault(PreferenceConstants.P_PE_PeEngineEndPoint, "http://localhost:8080/aspireRfidProgrammableEngine/");

	}

}
