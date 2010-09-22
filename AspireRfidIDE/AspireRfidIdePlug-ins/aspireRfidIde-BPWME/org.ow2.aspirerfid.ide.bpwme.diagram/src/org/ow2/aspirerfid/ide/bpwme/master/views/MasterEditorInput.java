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

package org.ow2.aspirerfid.ide.bpwme.master.views;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IPersistableElement;
import org.ow2.aspirerfid.commons.apdl.model.EBProc;
import org.ow2.aspirerfid.ide.bpwme.master.utils.MasterDataBuilder;

/**
 * Input for MasterEditor. Nothing interesting.
 * 
 * @author Yongming Luo <yluo@ait.edu.gr>
 *
 */
public class MasterEditorInput implements IEditorInput{

//	private MasterDataBuilder mdb;
	
	public MasterEditorInput() {
		
	}
	
//	public MasterEditorInput(MasterDataBuilder mdb) {
//		this.mdb = mdb;
//	}
	
	public MasterEditorInput(EBProc ebp) {
		MasterDataBuilder mdb = MasterDataBuilder.getInstance();
		mdb.setEBProc(ebp);
	}
		
	@Override
	public boolean exists() {
		return false;
	}

	@Override
	public ImageDescriptor getImageDescriptor() {
		return null;
	}

	@Override
	public String getName() {
		return "Master Data Editor";
	}

	@Override
	public IPersistableElement getPersistable() {
		return null;
	}

	@Override
	public String getToolTipText() {
		return "";
	}

	@Override
	public Object getAdapter(Class adapter) {
		return null;
	}

}
