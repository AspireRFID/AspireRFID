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

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.gmf.runtime.common.ui.action.AbstractActionDelegate;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.ow2.aspirerfid.commons.apdl.model.ApdlDataField;
import org.ow2.aspirerfid.commons.apdl.model.EBProc;
import org.ow2.aspirerfid.ide.bpwme.diagram.edit.parts.EBProcEditPart;
import org.ow2.aspirerfid.ide.bpwme.ecspec.utils.ECSpecBuilder;
import org.ow2.aspirerfid.ide.bpwme.ecspec.utils.LRSpecBuilder;
import org.ow2.aspirerfid.ide.bpwme.ecspec.views.ECSpecEditor;
import org.ow2.aspirerfid.ide.bpwme.ecspec.views.ECSpecEditorInput;
import org.ow2.aspirerfid.ide.bpwme.impl.EBProcImpl;
import org.ow2.aspirerfid.ide.bpwme.utils.MainControl;
import org.ow2.aspirerfid.ide.bpwme.utils.MainUtil;


/**
 * Context menu command for editing ECSpec
 * @author Yongming Luo <yluo@ait.edu.gr>
 *
 */

public class EditECSpec extends AbstractActionDelegate 
implements IObjectActionDelegate {

	@Override
	protected void doRun(IProgressMonitor progressMonitor) {
		if(MainUtil.isEditorOpened(ECSpecEditor.ID)) {
			MessageBox messageBox = new MessageBox(
					PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell());
			messageBox.setMessage("Editor is already opened.\nPlease close it and try again.");
			messageBox.open();
			return;
		}
		

		
		//get the selected EBProc part
		EBProcEditPart epe = (EBProcEditPart)getStructuredSelection().getFirstElement();		
		EBProcImpl ebi = (EBProcImpl)((View)epe.getModel()).getElement();
		
		//get the relevant EBProc in Apdl
		MainControl mc = MainControl.getMainControl();
		EBProc ebp = (EBProc)mc.getMapObject(ebi.hashCode());
		
		mc.lrsb = new LRSpecBuilder(ebp);
		
		
		boolean newECSB = true;
		for(ApdlDataField adf:ebp.getApdlDataFields().getApdlDataField()) {
			//if there exists one ECSpec definition
			if(adf.getECSpec() != null) {
				mc.ecsb = new ECSpecBuilder(ebp, adf.getECSpec());
				newECSB = false;
				break;
			}
		}
		if(newECSB) {
			mc.ecsb = new ECSpecBuilder(ebp);
		}

		ECSpecEditorInput eei = new ECSpecEditorInput(mc.lrsb, mc.ecsb);
		
		IWorkbenchPage page = PlatformUI.getWorkbench().
		getActiveWorkbenchWindow().getActivePage();
		try {
			ECSpecEditor ese = (ECSpecEditor)page.openEditor(eei, ECSpecEditor.ID);
			if(newECSB) {
				ese.setDirty(true);
			}			
			mc.ecEditor = ese;
		} catch (PartInitException e) {
			e.printStackTrace();
		}
		
		MainUtil.splitEditorArea();
		
	}
}
