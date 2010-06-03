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

package org.ow2.aspirerfid.ide.bpwme.ecspec.provider;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;
import org.ow2.aspirerfid.commons.ale.model.ale.ECFilterSpec;
import org.ow2.aspirerfid.commons.ale.model.ale.ECGroupSpec;
import org.ow2.aspirerfid.commons.ale.model.ale.ECReportOutputSpec;
import org.ow2.aspirerfid.commons.ale.model.ale.ECReportSpec;
import org.ow2.aspirerfid.ide.bpwme.ecspec.model.ReportSpec;
import org.ow2.aspirerfid.ide.bpwme.ecspec.utils.ECSpecBuilder;
/**
 * 
 * @author Yongming Luo <yluo@ait.edu.gr>
 *
 */
public class ReportContentProvider implements ITreeContentProvider {
	
	List<ECReportSpec> listECReportSpecs;
	List<ReportSpec> listReportSpec;
	ECSpecBuilder ecsb;
	
	public ReportContentProvider(List<ECReportSpec> ecReportSpecs, ECSpecBuilder ecsb) {
		listECReportSpecs = ecReportSpecs;
		this.ecsb = ecsb;
	}
	
	@Override
	public Object[] getChildren(Object parentElement) {
		if(parentElement instanceof ReportSpec) {
			return ((ReportSpec)parentElement).getChildren().toArray();
		}
		return null;
		/*
		if(parentElement instanceof List<?>) {
			return ((List)parentElement).toArray();
		} else if(parentElement instanceof ECReportSpec) {
			return new Object[]{((ECReportSpec)parentElement).getOutput(), 
			        ((ECReportSpec)parentElement).getFilterSpec(),
			        ((ECReportSpec)parentElement).getGroupSpec()};
		}
		return null;*/
	}
	
	@Override
	public Object getParent(Object element) {
		return null;
		/*
		if(element instanceof ECReportSpec) {
			return listReportSpecs;
		}else if(element instanceof ECReportOutputSpec ||
				element instanceof ECFilterSpec ||
				element instanceof ECGroupSpec) {
			return  listReportSpecs.get(0);
		}
		return null;*/
	}

	@Override
	public boolean hasChildren(Object element) {
		if(element instanceof ReportSpec) {
			return true;
		}else {
			return false;
		}
		//return true;
		/*
		if(element instanceof List<?>) {
			return true;
		}else {
			return false;
		} */
			
			/*
			if(element instanceof ECReportOutputSpec ||
				element instanceof ECFilterSpec ||
				element instanceof ECGroupSpec) {
			return true;
		}
		return false;*/
	}

	@Override
	public Object[] getElements(Object inputElement) {
		return ((List)inputElement).toArray();
		/*
		listReportSpec = new ArrayList<ReportSpec>();
		for(ECReportSpec item:(List<ECReportSpec>)inputElement) {
			listReportSpec.add(new ReportSpec(item, ecsb));
		}
		return listReportSpec.toArray();
		*/
		//return ((List)inputElement).toArray();
		// TODO Auto-generated method stub
		//return null;
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}

	@Override
	public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
		// TODO Auto-generated method stub

	}
}
