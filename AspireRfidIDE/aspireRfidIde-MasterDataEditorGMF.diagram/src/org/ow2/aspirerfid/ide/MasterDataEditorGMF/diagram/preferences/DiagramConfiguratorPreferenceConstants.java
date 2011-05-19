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

/**
 * @author Eleftherios Karageorgiou (elka) e-mail: elka@ait.edu.gr
 *
 */
public class DiagramConfiguratorPreferenceConstants {
	
	private static String fileSeparator = System.getProperty("file.separator");
	private static String userHome = System.getProperty("user.home");
	
	public static final String P_CaptureURL = "http://localhost:8080/aspireRfidEpcisRepository/capture";
	public static final String P_QueryURL = "http://localhost:8080/aspireRfidEpcisRepository/query";
	public static final String P_Size = "size";
	public static final String P_Size_Height = "height";
	public static final String P_Size_Width = "width";
	
	public static final String newCompanyAttributes = ",1";
	public static final String newWarehouseAttributes = ",2";
	public static final String newReadPointAttributes = ",3";

}
