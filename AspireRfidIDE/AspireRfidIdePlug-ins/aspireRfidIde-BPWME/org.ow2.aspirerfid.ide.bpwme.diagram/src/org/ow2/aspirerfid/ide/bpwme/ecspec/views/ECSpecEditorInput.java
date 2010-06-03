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

package org.ow2.aspirerfid.ide.bpwme.ecspec.views;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IPersistableElement;
import org.ow2.aspirerfid.ide.bpwme.ecspec.utils.ECSpecBuilder;
import org.ow2.aspirerfid.ide.bpwme.ecspec.utils.LRSpecBuilder;

/**
 * Input for ECSpec Editor.
 * Bundled with lrspec and ecspec data.
 * @author Yongming Luo <yluo@ait.edu.gr>
 *
 */
public class ECSpecEditorInput implements IEditorInput{
	
	private LRSpecBuilder lrsb;
	private ECSpecBuilder ecsb;
	
	public ECSpecEditorInput(LRSpecBuilder lrsb, ECSpecBuilder ecsb) {
		this.lrsb = lrsb;
		this.ecsb = ecsb;
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
		return "EC Spec Editor";
	}

	@Override
	public IPersistableElement getPersistable() {
		return null;
	}

	@Override
	public String getToolTipText() {
		// TODO Auto-generated method stub
		return "";
	}

	@Override
	public Object getAdapter(Class adapter) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public LRSpecBuilder getLRSpecBuilder() {
		return lrsb;
	}
	
	public ECSpecBuilder getECSpecBuilder() {
		return ecsb;
	} 

}

