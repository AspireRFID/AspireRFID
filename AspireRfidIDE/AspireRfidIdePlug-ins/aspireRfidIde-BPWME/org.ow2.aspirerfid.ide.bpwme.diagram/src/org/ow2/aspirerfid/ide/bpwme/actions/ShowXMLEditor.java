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
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.common.util.URI;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.handlers.HandlerUtil;
import org.ow2.aspirerfid.ide.bpwme.diagram.simpleditor.PathEditorInput;
import org.ow2.aspirerfid.ide.bpwme.diagram.xmleditor.XMLEditor;
import org.ow2.aspirerfid.ide.bpwme.utils.MainControl;


/**
 * Open apdl file in an xml editor.
 * TODO in the future the model can be updated also by
 * updating the xml file
 * @author Yongming Luo <yluo@ait.edu.gr>
 *
 */
public class ShowXMLEditor extends AbstractHandler {

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		MainControl mc = MainControl.getMainControl();
		URI apdlURI = mc.getApdlURI();
		if(apdlURI == null) {
			System.out.println("No Path For Opening XML Editor");
			return null;
		}
		IPath location= new Path(apdlURI.toFileString());
		PathEditorInput input= new PathEditorInput(location);
		IWorkbenchPage page = HandlerUtil.getActiveWorkbenchWindow(event).getActivePage();
		try {
			page.openEditor(input, XMLEditor.ID);
		} catch (PartInitException e) {
			e.printStackTrace();
		}
		return null;
	}
}
