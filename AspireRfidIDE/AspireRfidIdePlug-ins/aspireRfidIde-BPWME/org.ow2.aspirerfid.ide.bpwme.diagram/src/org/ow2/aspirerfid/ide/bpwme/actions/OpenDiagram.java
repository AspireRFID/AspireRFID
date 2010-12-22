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
import java.util.HashSet;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.common.ui.URIEditorInput;
import org.eclipse.emf.common.util.URI;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.handlers.HandlerUtil;
import org.ow2.aspirerfid.ide.bpwme.diagram.comboeditor.ComboEditor;
import org.ow2.aspirerfid.ide.bpwme.diagram.comboeditor.ComboInput;
import org.ow2.aspirerfid.ide.bpwme.diagram.part.BpwmeDiagramEditor;
import org.ow2.aspirerfid.ide.bpwme.diagram.part.BpwmeDiagramEditorPlugin;
import org.ow2.aspirerfid.ide.bpwme.diagram.preferences.PreferenceConstants;
import org.ow2.aspirerfid.ide.bpwme.diagram.simpleditor.PathEditorInput;
import org.ow2.aspirerfid.ide.bpwme.ecspec.views.ECLRInput;
import org.ow2.aspirerfid.ide.bpwme.ecspec.views.ECSpecEditor;
import org.ow2.aspirerfid.ide.bpwme.master.views.MasterEditor;
import org.ow2.aspirerfid.ide.bpwme.utils.EditorListener;
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
		IPreferenceStore store = BpwmeDiagramEditorPlugin.getInstance().getPreferenceStore();		
		String filterPath = store.getString(PreferenceConstants.P_BPWME_DIR);
		String[] extensions = new String[]{"*.bpwme_diagram"};
		fileDialog.setFilterExtensions(extensions);
		fileDialog.setFilterPath(filterPath);
		fileDialog.open();
		if (fileDialog.getFileName() != null
				&& fileDialog.getFileName().length() > 0) {
			URI diagramURI = URI.createFileURI(fileDialog.getFilterPath()
					+ File.separator + fileDialog.getFileName());
			URIEditorInput uei = new URIEditorInput(diagramURI);
			mc.setBpwmeURI(diagramURI);
			mc.setAPDLFileName(diagramURI);
			
			IPath location= new Path(mc.getApdlURI().toFileString());
			PathEditorInput pathInput= new PathEditorInput(location);

			ComboInput ni = new ComboInput();
			ni.setPei(pathInput);
			ni.setUei(uei);

			try {
				page.openEditor(ni, ComboEditor.ID);
			} catch (PartInitException e) {
				e.printStackTrace();
			}
			
			mc.rebuild();
			mc.mapModels();
			MainUtil.setPerspective("bpwme.diagram.BpwmePerspective");
			
			
			//open ecspec editor
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
			//open done

		}		
		
//		if (fileDialog.getFileName() != null
//				&& fileDialog.getFileName().length() > 0) {
//			URI fileURI = URI.createFileURI(fileDialog.getFilterPath()
//					+ File.separator + fileDialog.getFileName());
//			try {
//				page.openEditor(new URIEditorInput(fileURI), BpwmeDiagramEditor.ID);
//			} catch (PartInitException e) {
//				e.printStackTrace();
//			}
//			mc.setBpwmeURI(fileURI);
//			mc.setAPDLFileName(fileURI);	
//			mc.rebuild();
//			mc.mapModels();
//			
//			MainUtil.executeCommand("org.ow2.aspirerfid.ide.bpwme.diagram.showXmlEditor");
//			MainUtil.bringToTop(BpwmeDiagramEditor.ID);
//			MainUtil.setPerspective("bpwme.diagram.BpwmePerspective");
//		}
		return null;
	}
}
