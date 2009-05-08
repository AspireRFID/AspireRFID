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




import org.accada.ale.xsd.ale.epcglobal.ECReportOutputFieldSpec;
import org.accada.ale.xsd.ale.epcglobal.ECReportOutputSpec;
import org.accada.ale.xsd.ale.epcglobal.ECReportOutputSpecExtension;

/**
 *  @author Vasso Koletti e-mail: vkol@ait.edu.gr
 *
 */
public class ReportOutputSpecBuilder {

	private ECReportOutputSpec spec;
	
	public ReportOutputSpecBuilder()
	{
		this (false, false, false, false, false);
	}
	
	public ReportOutputSpecBuilder(boolean includeEpc, boolean includeTag, boolean includeRawHex, boolean includeRawDecimal, boolean includeCount)
	{
		spec = new ECReportOutputSpec();
		
		spec.setIncludeEPC(includeEpc);
		spec.setIncludeTag(includeTag);
		spec.setIncludeRawHex(includeRawHex);
		spec.setIncludeRawDecimal(includeRawDecimal);
		spec.setIncludeCount(includeCount);
	}
	
	public void addECReportOutputFieldSpec(FieldSpecBuilder fieldSpec, String name, boolean includeFieldSpecInReport)
	{
		ECReportOutputSpecExtension extention;
		
		extention = spec.getExtension();
		if(extention == null)
			extention = new ECReportOutputSpecExtension();
		
		ECReportOutputFieldSpec outputFieldSpec = new ECReportOutputFieldSpec();
		outputFieldSpec.setFieldspec(fieldSpec.getECFieldSpec());
		outputFieldSpec.setName(name);
		outputFieldSpec.setIncludeFieldSpecInReport(includeFieldSpecInReport);
		
		extention.getFieldList().getField().add(outputFieldSpec);
		
		spec.setExtension(extention);
	}
	
	public ECReportOutputSpec getECReportOutputSpec()
	{
		return spec;
	}
}
