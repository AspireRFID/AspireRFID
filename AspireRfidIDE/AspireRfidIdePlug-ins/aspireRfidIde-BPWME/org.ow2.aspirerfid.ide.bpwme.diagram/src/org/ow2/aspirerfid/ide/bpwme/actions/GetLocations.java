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

package org.ow2.aspirerfid.ide.bpwme.actions;

import java.util.HashMap;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.gmf.runtime.notation.View;
import org.ow2.aspirerfid.commons.apdl.model.CLCBProc;
import org.ow2.aspirerfid.ide.MasterDataEditorGMF.bpwmeintegration.MasterDataContentsProvider;
import org.ow2.aspirerfid.ide.bpwme.diagram.edit.parts.CLCBProcEditPart;
import org.ow2.aspirerfid.ide.bpwme.impl.CLCBProcImpl;
import org.ow2.aspirerfid.ide.bpwme.master.utils.MasterDataBuilder;
import org.ow2.aspirerfid.ide.bpwme.utils.MainControl;
import org.ow2.aspirerfid.ide.bpwme.utils.MainUtil;

public class GetLocations extends AbstractHandler{
	
	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		//get editpart from workspace
		
		CLCBProcEditPart clcbe = MainUtil.getCLCBPartSelection();
		if(clcbe == null) {
			return null;
		}
		CLCBProcImpl clcbi = (CLCBProcImpl)((View)clcbe.getModel()).getElement();
		
		
		MainControl mc = MainControl.getMainControl();
		CLCBProc clcb = (CLCBProc) mc.getMapObject(clcbi.hashCode());
		
		if(clcb == null) {
			return null;
		}
		
		MasterDataBuilder mdb = MasterDataBuilder.getInstance();
		
		mdb.setCLCBProc(clcb);
		
		HashMap<String, HashMap<String, String>> companyMap = 
			MasterDataContentsProvider.getCompanyUriAttributesValues();
		HashMap<String, HashMap<String, String>> warehouseMap = 
			MasterDataContentsProvider.getWarehouseUriAttributesValues();
		HashMap<String, HashMap<String, String>> readpointMap = 
			MasterDataContentsProvider.getReadPointUriAttributesValues();
		
		mdb.setBusinessStepReadPoint(companyMap, warehouseMap, readpointMap);		
		return null;
	}
}


