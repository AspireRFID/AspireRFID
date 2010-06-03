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
import org.ow2.aspirerfid.ide.bpwme.diagram.xmleditor.XMLEditor;
import org.ow2.aspirerfid.ide.bpwme.utils.MainUtil;

/**
 * Refresh the xml editor, although the editor should
 * be updated every time it get focused
 * @author Yongming Luo <yluo@ait.edu.gr>
 *
 */
public class RefreshXMLEditor extends AbstractHandler{
	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		XMLEditor editor = (XMLEditor)MainUtil.getEditor(XMLEditor.ID);
		if(editor != null) {
			editor.refresh();
		}
		return null;
	}
}
