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

package org.ow2.aspirerfid.ide.MasterDataEditorGMF.images;

import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.GridData;
import org.eclipse.draw2d.GridLayout;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.ImageFigure;
import org.eclipse.draw2d.LayoutManager;
import org.eclipse.gmf.runtime.diagram.ui.figures.ResizableCompartmentFigure;
import org.eclipse.gmf.runtime.diagram.ui.figures.ShapeCompartmentFigure;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.plugin.AbstractUIPlugin;

/**
 * @author Eleftherios Karageorgiou (elka) e-mail: elka@ait.edu.gr
 *
 */
public class ImageNodeShape extends Figure {

	public ImageNodeShape(String img) {
		GridLayout layout = new GridLayout(2, false);
		super.setLayoutManager(layout);
		add(new ImageFigure(getImageNodeImage(img)), new GridData(SWT.BEGINNING, SWT.BEGINNING, false, false, 1, 2));
	}

	@Override
	public void setLayoutManager(LayoutManager manager) {
	}

	@Override
	public void add(IFigure figure, Object constraint, int index) {
		if (figure instanceof ShapeCompartmentFigure) {
			constraint = new GridData(SWT.FILL, SWT.FILL, true, true, 2, 1);
			figure.setBorder(new CustomBorder());
		} else if (figure instanceof ResizableCompartmentFigure) {
			constraint = new GridData(SWT.FILL, SWT.BEGINNING, true, false, 1, 1);
		}
		super.add(figure, constraint, index);
	}

	protected Image getImageNodeImage(String img) { 
		return getImageImage(img);
	}

	protected Image getImageImage(String path) {
		Image image = JFaceResources.getImageRegistry().get(path);
		if (image == null) {
			ImageDescriptor descriptor = AbstractUIPlugin.imageDescriptorFromPlugin("org.ow2.aspirerfid.ide.MasterDataEditorGMF", path);
			if (descriptor == null) {
				descriptor = ImageDescriptor.getMissingImageDescriptor();
			}
			JFaceResources.getImageRegistry().put(path, image = descriptor.createImage());
		}
		return image;
	}
	
}
