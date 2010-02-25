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
/**
 * @author Nikos Kefalakis (nkef) e-mail: nkef@ait.edu.gr
 *
 * Constant definitions for plug-in preferences
 */

public class PreferenceConstants {



	private static String fileSeparator = System.getProperty("file.separator");
	private static String userHome = System.getProperty("user.home");
	
	
	public static final String P_PE_ApdlFilesPath = userHome+fileSeparator+"AspireRFID"+fileSeparator+"IDE"+fileSeparator+"APDLs"+fileSeparator;

	public static final String P_PE_PeEngineEndPoint = "http://localhost:8080/aspireRfidProgrammableEngine/";
	public static final String P_PE_PeEngineEndCurrentPoint = "P_PE_PeEngineEndCurrentPoint";

	
}
