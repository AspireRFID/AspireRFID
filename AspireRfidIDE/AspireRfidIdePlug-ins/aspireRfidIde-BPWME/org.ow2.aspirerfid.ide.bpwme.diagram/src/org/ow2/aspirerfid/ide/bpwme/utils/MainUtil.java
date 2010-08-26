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

package org.ow2.aspirerfid.ide.bpwme.utils;

import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IEditorReference;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PlatformUI;
import org.ow2.aspirerfid.commons.apdl.model.OLCBProc;
import org.ow2.aspirerfid.ide.bpwme.BpwmeFactory;
import org.ow2.aspirerfid.ide.bpwme.WorkflowMap;

/**
 * Utils for the whole project and workspace
 * 
 * @author Yongming Luo <yluo@ait.edu.gr>
 *
 */
public class MainUtil {
	
	/**
	 * Return whether the editor is opened already
	 * @param editorID
	 * @return
	 */
	public static boolean isEditorOpened(String editorID) {
		IWorkbenchPage page = PlatformUI.getWorkbench().
		getActiveWorkbenchWindow().getActivePage();
		IEditorReference[] refs = page.getEditorReferences();
		for(IEditorReference ref:refs) {
			if(ref.getId().equals(editorID)) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Activate the editor if it is in the workspace
	 * @param editorID
	 */
	public static void activateEditor(String editorID) {
		IWorkbenchPage page = PlatformUI.getWorkbench().
		getActiveWorkbenchWindow().getActivePage();
		IEditorReference[] refs = page.getEditorReferences();
		for(IEditorReference ref:refs) {
			if(ref.getId().equals(editorID)) {
				IEditorPart editor = ref.getEditor(false);
				page.activate(editor);
			}
		}
	}
	
	/**
	 * Get the editor instance if it is in the workspace
	 * @param editorID
	 * @return
	 */
	public static IEditorPart getEditor(String editorID) {
		IWorkbenchPage page = PlatformUI.getWorkbench().
		getActiveWorkbenchWindow().getActivePage();
		IEditorReference[] refs = page.getEditorReferences();
		for(IEditorReference ref:refs) {
			if(ref.getId().equals(editorID)) {
				IEditorPart editor = ref.getEditor(false);
				return editor;
			}
		}
		return null;
	}
	
	/**
	 * Copy and transfer the model in APDL file to the model in GMF Diagram
	 * @param olcb
	 * @param workflowMap
	 */
	public static void copyToDiagramModel(OLCBProc olcbModel, WorkflowMap workflowMap) {
		org.ow2.aspirerfid.ide.bpwme.OLCBProc olcbDiagram = BpwmeFactory.eINSTANCE.createOLCBProc();		
		workflowMap.getNodes().add(olcbDiagram);
		//olcb level
		olcbDiagram.setId(olcbModel.getId());
		olcbDiagram.setName(olcbModel.getName());
	}
}
