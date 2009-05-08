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


package org.ow2.aspirerfid.ide.ecspec.utils;


import org.accada.ale.xsd.ale.epcglobal.ECFieldSpec;

/**
 *  @author Vasso Koletti e-mail: vkol@ait.edu.gr
 *
 */
public class FieldSpecBuilder {

	ECFieldSpec fieldSpec;
	
	public FieldSpecBuilder(String fieldName)
	{
		fieldSpec = new ECFieldSpec();
		fieldSpec.setFieldname(fieldName);
	}
	
	public FieldSpecBuilder(String fieldName, String dataType, String format)
	{
		this(fieldName);
		if (dataType != null && dataType.trim()!="")
			fieldSpec.setDatatype(dataType);
		if(format != null && format.trim()!="")
			fieldSpec.setFormat(format);
	}
	
	public ECFieldSpec getECFieldSpec()
	{
		return fieldSpec;
	}
}
