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

import java.util.List;
import org.accada.ale.xsd.ale.epcglobal.ECFilterSpec;
import org.accada.ale.xsd.ale.epcglobal.ECGroupSpec;
import org.accada.ale.xsd.ale.epcglobal.ECGroupSpecExtension;
import org.accada.ale.xsd.ale.epcglobal.ECReportOutputSpec;
import org.accada.ale.xsd.ale.epcglobal.ECReportSetSpec;
import org.accada.ale.xsd.ale.epcglobal.ECReportSpec;
import org.accada.ale.xsd.ale.epcglobal.ECReportSpecExtension;
import org.accada.ale.xsd.ale.epcglobal.ECReportSpecExtension.StatProfileNames;

/**
 * @author Vasso Koletti e-mail: vkol@ait.edu.gr
 * @author Nektarios Leontiadis
 * 
 */
public class ECReportSpecBuilder {

	ECReportSpec spec;

	public static final String DEFAULT_STAT_PROFILE_NAME = "TagTimestamps";

	public static enum ReportSet {
		CURRENT("CURRENT"), ADDITIONS("ADDITIONS"), DELETIONS("DELETIONS");

		private String setValue;

		ReportSet(String value) {
			setValue = value;
		}

		public String toString() {
			return setValue;
		}
	}

	public static enum FilterType {
		INCLUDE("INCLUDE"), EXCLUDE("EXCLUDE");

		private String filterType;

		FilterType(String filterType) {
			this.filterType = filterType;
		}

		public String toString() {
			return filterType;
		}
	}

	public ECReportSpecBuilder() {
		this("temp");
	}

	public ECReportSpecBuilder(String name) {
		ECReportSpecExtension extension = new ECReportSpecExtension();

		spec = new ECReportSpec();
		spec.setReportName(name);
		spec.setFilterSpec(new ECFilterSpec());
		spec.setGroupSpec(new ECGroupSpec());
		spec.setOutput(new ECReportOutputSpec());
		spec.setReportSet(new ECReportSetSpec());

		extension.setStatProfileNames(new StatProfileNames());
		spec.setExtension(extension);
	}

	public static ECReportSpec validateAndReturn(ECReportSpecBuilder builder)
			throws Exception {
		if (builder.spec.getExtension().getStatProfileNames()
				.getStatProfileName().size() == 0)
			builder.addStatProfileName(DEFAULT_STAT_PROFILE_NAME);
		try {
			return builder.spec;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static boolean isNullOrEmpty(String str) {
		return str == null || str.equals("");
	}

	public void setReportName(String name) {
		spec.setReportName(name);
	}

	public String getReportName() {
		return spec.getReportName();
	}

	/********* ECFilterSpec ********/
	public void setECReportSet(ReportSet set) {
		ECReportSetSpec setSpec = new ECReportSetSpec();
		setSpec.setSet(set.toString());
		spec.setReportSet(setSpec);
	}

	public ReportSet getECReportSet() throws Exception {
		try {
			return ReportSet.valueOf(spec.getReportSet().getSet());
		} catch (Exception e) {
			return null;
		}
	}

	/********* ECFilterSpec ********/
	public void addECFilter(String filter, FilterType filterType) {
		ECFilterSpec filterSpec = spec.getFilterSpec();
		if (filterType == FilterType.INCLUDE) {
			filterSpec.getIncludePatterns().getIncludePattern().add(filter);
		} else {
			filterSpec.getExcludePatterns().getExcludePattern().add(filter);
		}
	}

	public void setECFilter(List<String> list, FilterType filterType) {

		if (filterType == FilterType.INCLUDE) {
			ECFilterSpec.IncludePatterns include = new ECFilterSpec.IncludePatterns();
			include.getIncludePattern().clear();
			include.getIncludePattern().addAll(list);

			spec.getFilterSpec().setIncludePatterns(include);
		} else {
			ECFilterSpec.ExcludePatterns exclude = new ECFilterSpec.ExcludePatterns();
			exclude.getExcludePattern().clear();
			exclude.getExcludePattern().addAll(list);

			spec.getFilterSpec().setExcludePatterns(exclude);
		}
	}

	public List<String> getECFilter(FilterType filterType) throws Exception {
		if (filterType == FilterType.INCLUDE) {
			return spec.getFilterSpec().getIncludePatterns()
					.getIncludePattern();
		} else {
			return spec.getFilterSpec().getExcludePatterns()
					.getExcludePattern();
		}
	}

	/********* ECGroupSpec *********/

	public void setECGroupSpec(String fieldName, String dataType,
			String format, List<String> patternList) {
		ECGroupSpec groupSpec = new ECGroupSpec();
		if (fieldName != null) {
			ECGroupSpecExtension extension = new ECGroupSpecExtension();
			extension.setFieldspec(new FieldSpecBuilder(fieldName, dataType,
					format).getECFieldSpec());
			groupSpec.setExtension(extension);
		}

		groupSpec.getPattern().clear();
		groupSpec.getPattern().addAll(patternList);
	}

	/****** ECReportOutputSpec *****/
	public void setECReportOutputSpec(ReportOutputSpecBuilder specBuilder) {
		spec.setOutput(specBuilder.getECReportOutputSpec());
	}

	public void setReportIfEmpty(boolean doReport) {
		spec.setReportIfEmpty(doReport);
	}

	public void setReportOnlyOnChange(boolean doReport) {
		spec.setReportOnlyOnChange(doReport);
	}

	public void addStatProfileName(String profile) {
		ECReportSpecExtension extension = spec.getExtension();
		if (extension == null) {
			extension = new ECReportSpecExtension();
		}
		if (extension.getStatProfileNames() == null) {
			extension.setStatProfileNames(new StatProfileNames());
		}
		if (!extension.getStatProfileNames().getStatProfileName().contains(
				profile))
			extension.getStatProfileNames().getStatProfileName().add(profile);

		spec.setExtension(extension);
	}
}
