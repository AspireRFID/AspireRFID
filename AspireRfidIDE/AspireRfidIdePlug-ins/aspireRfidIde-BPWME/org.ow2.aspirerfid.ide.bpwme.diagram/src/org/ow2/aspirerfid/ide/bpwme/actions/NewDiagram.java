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

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.handlers.HandlerUtil;
import org.ow2.aspirerfid.ide.bpwme.diagram.part.BpwmeCreationWizard;
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
		wizardDialog.open();
		
		MainUtil.executeCommand("org.ow2.aspirerfid.ide.bpwme.diagram.showXmlEditor");
		
		return null;
	}
}
