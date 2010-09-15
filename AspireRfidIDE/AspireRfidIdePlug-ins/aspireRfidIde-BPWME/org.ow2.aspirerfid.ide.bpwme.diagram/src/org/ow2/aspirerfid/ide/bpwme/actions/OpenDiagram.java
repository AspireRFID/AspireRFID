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

import java.io.File;
import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.emf.common.ui.URIEditorInput;
import org.eclipse.emf.common.util.URI;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.handlers.HandlerUtil;
import org.ow2.aspirerfid.ide.bpwme.diagram.part.BpwmeDiagramEditor;
import org.ow2.aspirerfid.ide.bpwme.utils.MainControl;
import org.ow2.aspirerfid.ide.bpwme.utils.MainUtil;
import org.ow2.aspirerfid.ide.bpwme.utils.MainControl.FileAction;


/**
 * Open an existing bpwme diagram, do the model mapping
 * @author Yongming Luo <yluo@ait.edu.gr>
 *
 */
public class OpenDiagram extends AbstractHandler{

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		MainControl mc = MainControl.getMainControl();
		mc.setFileAction(FileAction.OpenAction);
		IWorkbenchWindow window = HandlerUtil.getActiveWorkbenchWindow(event);
		IWorkbenchPage page = window.getActivePage();
		FileDialog fileDialog = new FileDialog(window.getShell(),
				SWT.OPEN);
		fileDialog.open();
		if (fileDialog.getFileName() != null
				&& fileDialog.getFileName().length() > 0) {
			URI fileURI = URI.createFileURI(fileDialog.getFilterPath()
					+ File.separator + fileDialog.getFileName());
			System.out.println(fileURI);
			try {
				page.openEditor(new URIEditorInput(fileURI), BpwmeDiagramEditor.ID);
			} catch (PartInitException e) {
				e.printStackTrace();
			}
			mc.setAPDLFileName(fileURI);	
			mc.rebuild();
			mc.mapModels();
			
			MainUtil.executeCommand("org.ow2.aspirerfid.ide.bpwme.diagram.showXmlEditor");
			
		}
		return null;
	}
}
