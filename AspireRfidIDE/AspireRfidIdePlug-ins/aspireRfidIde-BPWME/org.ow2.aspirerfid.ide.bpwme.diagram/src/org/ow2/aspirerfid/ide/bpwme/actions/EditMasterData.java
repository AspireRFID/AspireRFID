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

import java.util.HashSet;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.gmf.runtime.common.ui.action.AbstractActionDelegate;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.ow2.aspirerfid.commons.apdl.model.EBProc;
import org.ow2.aspirerfid.ide.bpwme.diagram.edit.parts.EBProcEditPart;
import org.ow2.aspirerfid.ide.bpwme.diagram.part.BpwmeDiagramEditor;
import org.ow2.aspirerfid.ide.bpwme.ecspec.views.ECSpecEditor;
import org.ow2.aspirerfid.ide.bpwme.impl.EBProcImpl;
import org.ow2.aspirerfid.ide.bpwme.master.views.MasterEditor;
import org.ow2.aspirerfid.ide.bpwme.master.views.MasterEditorInput;
import org.ow2.aspirerfid.ide.bpwme.utils.MainControl;
import org.ow2.aspirerfid.ide.bpwme.utils.MainUtil;

/**
 * Context menu command for editing master data
 * @author Yongming Luo <yluo@ait.edu.gr>
 *
 */
public class EditMasterData extends AbstractActionDelegate 
implements IObjectActionDelegate {

	@Override
	protected void doRun(IProgressMonitor progressMonitor) {
		if(MainUtil.isEditorOpened(MasterEditor.ID)) {
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
		
		MasterEditorInput mei = new MasterEditorInput(ebp);
		
		IWorkbenchPage page = PlatformUI.getWorkbench().
		getActiveWorkbenchWindow().getActivePage();
		try {
			page.openEditor(mei, MasterEditor.ID);
			mc.saveObject();
		} catch (PartInitException e) {
			e.printStackTrace();
		}
		
		HashSet<String> editorIDs = new HashSet<String>();
		editorIDs.add(ECSpecEditor.ID);
		editorIDs.add(MasterEditor.ID);
		
		MainUtil.splitEditorArea(BpwmeDiagramEditor.ID,editorIDs);
	}
}
