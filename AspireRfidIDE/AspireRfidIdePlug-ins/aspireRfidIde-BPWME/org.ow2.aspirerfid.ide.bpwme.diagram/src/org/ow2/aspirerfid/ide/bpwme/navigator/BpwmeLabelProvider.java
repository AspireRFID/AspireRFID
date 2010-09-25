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

package org.ow2.aspirerfid.ide.bpwme.navigator;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.navigator.IDescriptionProvider;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.ow2.aspirerfid.ide.MasterDataEditorGMF.AbstractContainer;
import org.ow2.aspirerfid.ide.MasterDataEditorGMF.AbstractWarehouse;
import org.ow2.aspirerfid.ide.MasterDataEditorGMF.Company;
import org.ow2.aspirerfid.ide.bpwme.CLCBProc;
import org.ow2.aspirerfid.ide.bpwme.EBProc;
import org.ow2.aspirerfid.ide.bpwme.OLCBProc;

/**
 * Label provider for the navigator
 * @author Eleftherios Karageorgiou (elka) e-mail: elka@ait.edu.gr
 *
 */
public class BpwmeLabelProvider extends LabelProvider implements ILabelProvider, IDescriptionProvider {

	/**
	 * Returns the label of the elements for the navigator
	 */
	public String getText(Object element) {
		if (element instanceof URI)
			return ((URI) element).toFileString();
		else if (element instanceof OLCBProc)
    		return getElementName(((OLCBProc) element).getName());
		else if (element instanceof CLCBProc)
    		return getElementName(((CLCBProc) element).getName());
		else if (element instanceof EBProc) 
    		return getElementName(((EBProc) element).getName());
		else if (element instanceof Company) 
    		return getElementName(((Company) element).getName());
    	else if (element instanceof AbstractWarehouse) 
    		return getElementName(((AbstractWarehouse) element).getName());
    	else if (element instanceof AbstractContainer) 
    		return getElementName(((AbstractContainer) element).getName());
    	return null;
    }
	
	/**
	 * Returns the element name
	 */
	private String getElementName(String name) {
		if (!(name.isEmpty() && name == ""))
			return name;
		else 
			return "Error: no name provided for element";
	}

	/**
	 * Returns the description for the navigator
	 */
    public String getDescription(Object element) {
        String text = getText(element);
        return "This is a description of " + text;
    }
    
	/**
	 * Returns the image of the elements for the navigator
	 */
    public Image getImage(Object element) {
    	if (element instanceof URI)
    		return getImageImage("icons/obj16/BpwmeDiagramFile.gif", "org.ow2.aspirerfid.ide.bpwme.diagram");
    	else if (element instanceof OLCBProc)
    		return getImageImage(getImagePath((EObject) element), "org.ow2.aspirerfid.ide.bpwme.diagram");
    	else if (element instanceof CLCBProc)
    		return getImageImage(getImagePath((EObject) element), "org.ow2.aspirerfid.ide.bpwme.diagram");
    	else if (element instanceof EBProc)
    		return getImageImage(getImagePath((EObject) element), "org.ow2.aspirerfid.ide.bpwme.diagram");
    	else if (element instanceof Company)
            return getImageImage(getImagePath((EObject) element), "org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram");
    	else if (element instanceof AbstractWarehouse)
            return getImageImage(getImagePath((EObject) element), "org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram");
    	else if (element instanceof AbstractContainer)
            return getImageImage(getImagePath((EObject) element), "org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram");
        return null;
    }
    
	/**
	 * Returns the path of the image
	 */
    private String getImagePath(EObject element) {
    	String path = "icons/navigator/" + element.getClass().getSimpleName() + ".gif";
    	return path;
    }
    
	/**
	 * Returns the image for each element
	 */
	private Image getImageImage(String path, String pluginPath) {
		Image image = JFaceResources.getImageRegistry().get(path);
		if (image == null) {
			ImageDescriptor descriptor = AbstractUIPlugin.imageDescriptorFromPlugin(pluginPath, path);
			if (descriptor == null) {
				descriptor = ImageDescriptor.getMissingImageDescriptor();
			}
			JFaceResources.getImageRegistry().put(path, image = descriptor.createImage());
		}
		return image;
	}
}
