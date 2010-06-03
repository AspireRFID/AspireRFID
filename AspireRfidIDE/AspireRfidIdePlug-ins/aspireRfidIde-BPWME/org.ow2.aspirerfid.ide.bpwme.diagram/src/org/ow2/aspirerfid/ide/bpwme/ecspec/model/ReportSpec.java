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

package org.ow2.aspirerfid.ide.bpwme.ecspec.model;

import java.util.List;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.model.IWorkbenchAdapter;
import org.eclipse.ui.views.properties.IPropertySource;
import org.ow2.aspirerfid.commons.ale.model.ale.ECReportSpec;
import org.ow2.aspirerfid.ide.bpwme.ecspec.utils.ECSpecBuilder;
/**
 * 
 * @author Yongming Luo <yluo@ait.edu.gr>
 *
 */
public class ReportSpec implements IWorkbenchAdapter, IAdaptable{
	public ECReportSpec ecrspec;
	ECSpecBuilder ecsb;
	
	public ReportSpec(ECReportSpec ecrspec, ECSpecBuilder ecsb) {
		this.ecrspec = ecrspec;
		this.ecsb = ecsb;
	}
	
	@Override
	public Object[] getChildren(Object o) {
		if(this == o) {
			return ecrspec.getFilterSpec().getIncludePatterns().getIncludePattern().toArray();
		}
		return null;
	}

	public List<String> getChildren() {
		return ecrspec.getFilterSpec().getIncludePatterns().getIncludePattern();
	}
	
	@Override
	public ImageDescriptor getImageDescriptor(Object object) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getLabel(Object o) {
		// TODO Auto-generated method stub
		return ecrspec.getReportName();
	}

	@Override
	public Object getParent(Object o) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object getAdapter(Class adapter) {
        if (adapter == IWorkbenchAdapter.class)
            return this;
        if (adapter == IPropertySource.class)
            return new ReportSpecProperties(this);
        return null;
	}
	
	public String getName() {
		return ecrspec.getReportName();
	}
	
	public boolean addFilter(String filter) {
		return ecsb.addFilter(filter, ecrspec);
	}
	
	public boolean changeFilter(String oldF, String newF) {
		return ecsb.changeFilter(oldF, newF, ecrspec);
	}
	
	public boolean removeFilter(String filter) {
		return ecsb.removeFilter(filter, ecrspec);
	}
	
	public boolean isReportOnChange() {
		return ecrspec.isReportOnlyOnChange();
	}
	public boolean isReportIfEmpty() {
		return ecrspec.isReportIfEmpty();
	}
	
	public void setReportOnChange(boolean value) {
		ecsb.setReportOnChange(value, ecrspec);
	}
	
	public void setReportIfEmpty(boolean value) {
		ecsb.setReportIfEmpty(value, ecrspec);
	}
	
}
