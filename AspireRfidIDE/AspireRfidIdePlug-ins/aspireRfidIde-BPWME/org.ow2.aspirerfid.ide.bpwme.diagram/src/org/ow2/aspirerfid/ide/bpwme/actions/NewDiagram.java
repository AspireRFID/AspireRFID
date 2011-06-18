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

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.common.util.URI;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.window.Window;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.ui.IPartListener2;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPartReference;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.handlers.HandlerUtil;
import org.ow2.aspirerfid.ide.bpwme.diagram.comboeditor.ComboEditor;
import org.ow2.aspirerfid.ide.bpwme.diagram.part.BpwmeCreationWizard;
import org.ow2.aspirerfid.ide.bpwme.diagram.part.BpwmeDiagramEditor;
import org.ow2.aspirerfid.ide.bpwme.diagram.simpleditor.PathEditorInput;
import org.ow2.aspirerfid.ide.bpwme.diagram.xmleditor.XMLEditor;
import org.ow2.aspirerfid.ide.bpwme.ecspec.views.ECLRInput;
import org.ow2.aspirerfid.ide.bpwme.ecspec.views.ECSpecEditor;
import org.ow2.aspirerfid.ide.bpwme.master.views.MasterEditor;
import org.ow2.aspirerfid.ide.bpwme.navigator.BpwmeContentProvider;
import org.ow2.aspirerfid.ide.bpwme.utils.MainControl;
import org.ow2.aspirerfid.ide.bpwme.utils.MainUtil;
import org.ow2.aspirerfid.ide.bpwme.utils.MainControl.FileAction;

/**
 * Create new bpwme diagram, use the generated wizard
 * @author Yongming Luo <yluo@ait.edu.gr>
 *
 */
public class NewDiagram extends AbstractHandler {

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		//at this time the url is not initiallized yet		
		MainControl mc = MainControl.getMainControl();
		mc.setFileAction(FileAction.NewAction);
		BpwmeCreationWizard wizard = new BpwmeCreationWizard();
		IWorkbenchWindow window = HandlerUtil.getActiveWorkbenchWindow(event);
		wizard.init(window.getWorkbench(), StructuredSelection.EMPTY);
		WizardDialog wizardDialog = new WizardDialog(
				window.getShell(), wizard);
		int result = wizardDialog.open();
		
		if(result == Window.OK) {
			MainUtil.setPerspective("bpwme.diagram.BpwmePerspective");
			
			IWorkbenchPage page = PlatformUI.getWorkbench().
			getActiveWorkbenchWindow().getActivePage();
			ECLRInput eli = new ECLRInput();
			
			try {
				ECSpecEditor ese = (ECSpecEditor)page.openEditor(eli, ECSpecEditor.ID);
				ese.setDirty(eli.getECSpecBuilder().isDirty());
			}catch (PartInitException e) {
				e.printStackTrace();
			}
			
			HashSet<String> editorIDs = new HashSet<String>();
			editorIDs.add(ECSpecEditor.ID);
			editorIDs.add(MasterEditor.ID);
			
			MainUtil.splitEditorArea(ComboEditor.ID,editorIDs);
		}
		
		//Modified by elka
		//In order to fix known issue with common navigator where metadata are not reset
		IWorkbenchPage page = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
		
		BpwmeContentProvider.viewer.refresh();
		
		page.addPartListener(new IPartListener2(){
	        public void partActivated( IWorkbenchPartReference partRef ) {
	        	if (partRef.getId().equals("org.ow2.aspirerfid.ide.bpwme.navigator.view")) {
	        		BpwmeContentProvider.viewer.refresh();
	        	}
	        }
	
	        public void partBroughtToTop( IWorkbenchPartReference partRef ) {
	        }
	
	        public void partClosed( IWorkbenchPartReference partRef ) {
	        	BpwmeContentProvider.viewer.refresh();
	        }
	
	        public void partDeactivated( IWorkbenchPartReference partRef ) {
	        }
	
	        public void partOpened( IWorkbenchPartReference partRef ) {
	        	BpwmeContentProvider.viewer.refresh();
	        }
	        
	        public void partHidden( IWorkbenchPartReference partRef ) {
	        }
	
	        public void partVisible( IWorkbenchPartReference partRef ) {
	        }
	
	        public void partInputChanged( IWorkbenchPartReference partRef ) {
	        	BpwmeContentProvider.viewer.refresh();
	        }
	    });
		
		return null;
	}
}
