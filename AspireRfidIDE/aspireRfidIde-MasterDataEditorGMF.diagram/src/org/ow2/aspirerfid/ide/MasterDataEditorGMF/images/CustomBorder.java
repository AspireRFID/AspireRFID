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

import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.LineBorder;
import org.eclipse.draw2d.geometry.Insets;
import org.eclipse.gmf.runtime.draw2d.ui.mapmode.MapModeUtil;
import org.eclipse.swt.graphics.Color;

/**
 * @author Eleftherios Karageorgiou (elka) e-mail: elka@ait.edu.gr
 *
 */
public class CustomBorder extends LineBorder {

	public CustomBorder() {
		super();
	}

	public CustomBorder(int width) {
		super(width);
	}

	public CustomBorder(Color color) {
		super(color);
	}

	public CustomBorder(Color color, int width) {
		super(color, width);
	}

	public void paint(IFigure figure, Graphics graphics, Insets insets) {
		tempRect.setBounds(getPaintRectangle(figure, insets));
		if (getWidth() % 2 == 1) {
			tempRect.width -= MapModeUtil.getMapMode(figure).DPtoLP(1);
			tempRect.height -= MapModeUtil.getMapMode(figure).DPtoLP(1);
		}
		int shrinkWidth = MapModeUtil.getMapMode(figure).DPtoLP(getWidth() / 2);
		tempRect.shrink(shrinkWidth, shrinkWidth);
		graphics.setLineWidth(getWidth());
		if (getColor() != null) {
			graphics.setForegroundColor(getColor());
		}
		graphics.drawRoundRectangle(tempRect, 12, 12);
	}
}
