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

import java.io.File;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.emf.common.util.URI;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.handlers.HandlerUtil;

/**
 * @author Eleftherios Karageorgiou (elka) e-mail: elka@ait.edu.gr
 *
 */
public class OpenMasterDataEditorGMFFromApdlViewHandler extends AbstractHandler{

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		IWorkbenchWindow window = HandlerUtil.getActiveWorkbenchWindow(event);
		
		//open the apdl file
		FileDialog fileDialog = new FileDialog(window.getShell(),
				SWT.OPEN);
		//set the file type
		String[] filterExt = {"*.xml"};
		fileDialog.setFilterExtensions(filterExt);
		fileDialog.open();
		if (fileDialog.getFileName() != null && fileDialog.getFileName().length() > 0) {
			URI fileURI = URI.createFileURI(fileDialog.getFilterPath()
					+ File.separator + fileDialog.getFileName());

			if (!(fileURI.fileExtension().endsWith("xml"))) {
				MessageDialog.openError(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell(), "Error", 
				"A MasterDataEditorGMF can be created from APDL files with the extension \"xml\".");
				return null;
			}
			
			//create the MasterDataEditorGMF
			OpenMasterDataEditorGMFFromApdlWizard wizard = new OpenMasterDataEditorGMFFromApdlWizard();
			//set the file
			wizard.setFileURI(fileURI);
			wizard.init(window.getWorkbench(), StructuredSelection.EMPTY);
			WizardDialog wizardDialog = new WizardDialog(
					window.getShell(), wizard);
			wizardDialog.open();
		}
		return null;
	}
}