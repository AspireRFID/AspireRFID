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

package org.ow2.aspirerfid.ide.MasterDataEditorGMF.handler;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.handlers.HandlerUtil;
import org.ow2.aspirerfid.ide.bpwme.CLCBProc;

/**
 * @author Eleftherios Karageorgiou (elka) e-mail: elka@ait.edu.gr
 *
 */
public class ImportMasterDataEditorGMFViewHandler extends AbstractHandler {
	
	private static CLCBProc clcbProc;

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		IWorkbenchWindow window = HandlerUtil.getActiveWorkbenchWindow(event);
		
		SelectCLCBProcForMasterDataEditorGMFWizard selectCLCBwizard = new SelectCLCBProcForMasterDataEditorGMFWizard();
		selectCLCBwizard.init(window.getWorkbench(), StructuredSelection.EMPTY);
		WizardDialog wizardDialog = new WizardDialog(
				window.getShell(), selectCLCBwizard);
		wizardDialog.open();
		clcbProc = selectCLCBwizard.getClcbProc();
		ImportMasterDataEditorGMFWizard importMasterDatawizard = new ImportMasterDataEditorGMFWizard();
		importMasterDatawizard.setClcbProc(clcbProc);
		importMasterDatawizard.init(window.getWorkbench(), StructuredSelection.EMPTY);
		WizardDialog wizardDialog1 = new WizardDialog(
				window.getShell(), importMasterDatawizard);
		wizardDialog1.open();

		return null;
	}

	/**
	 * @param clcbProc the clcbProc to set
	 */
	public static void setClcbProc(CLCBProc clcbProc) {
		ImportMasterDataEditorGMFViewHandler.clcbProc = clcbProc;
	}
	
}
