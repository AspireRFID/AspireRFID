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


package org.ow2.aspirerfid.ide.ecspec.preferences;

/**
 * Constant definitions for plug-in preferences
 *
 * @author Nikos Kefalakis (nkef) e-mail: nkef@ait.edu.gr
 * @author Vasso Koletti e-mail: vkol@ait.edu.gr
 *
 */
public class PreferenceConstants {

	private static String fileSeparator = System.getProperty("file.separator");
	private static String userHome = System.getProperty("user.home");
	
	public static final String P_ECSpecsPATH = userHome+fileSeparator+"AspireRFID"+fileSeparator+"IDE"+fileSeparator+"ECSpecs"+fileSeparator; //"C:\\ASPIRE_APPLICATION_FILES\\ECSpecs\\";
	public static final String P_HAL_LRSpecsPATH = userHome+fileSeparator+"AspireRFID"+fileSeparator+"IDE"+fileSeparator+"LRSpecs"+fileSeparator+"HAL"+fileSeparator; //"C:\\ASPIRE_APPLICATION_FILES\\LRSpecs\\HAL\\";
	public static final String P_RP_LRSpecsPATH = userHome+fileSeparator+"AspireRFID"+fileSeparator+"IDE"+fileSeparator+"LRSpecs"+fileSeparator+"RP"+fileSeparator; //"C:\\ASPIRE_APPLICATION_FILES\\LRSpecs\\RP\\";
	public static final String P_LLRP_LRSpecsPATH = userHome+fileSeparator+"AspireRFID"+fileSeparator+"IDE"+fileSeparator+"LRSpecs"+fileSeparator+"LLRP"+fileSeparator; //"C:\\ASPIRE_APPLICATION_FILES\\LRSpecs\\LLRP\\";

}
