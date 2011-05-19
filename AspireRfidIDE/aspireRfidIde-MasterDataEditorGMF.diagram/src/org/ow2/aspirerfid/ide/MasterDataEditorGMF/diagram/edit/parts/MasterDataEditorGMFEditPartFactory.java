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

package org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts;

import org.eclipse.draw2d.FigureUtilities;
import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPartFactory;
import org.eclipse.gef.tools.CellEditorLocator;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ITextAwareEditPart;
import org.eclipse.gmf.runtime.draw2d.ui.figures.WrappingLabel;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Text;

/**
 * @generated
 */
public class MasterDataEditorGMFEditPartFactory implements EditPartFactory {

	/**
	 * @generated
	 */
	public EditPart createEditPart(EditPart context, Object model) {
		if (model instanceof View) {
			View view = (View) model;
			switch (org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.part.MasterDataEditorGMFVisualIDRegistry
					.getVisualID(view)) {

			case org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.CompanyEditPart.VISUAL_ID:
				return new org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.CompanyEditPart(
						view);

			case org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.BizLocEditPart.VISUAL_ID:
				return new org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.BizLocEditPart(
						view);

			case org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.BizLocNameEditPart.VISUAL_ID:
				return new org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.BizLocNameEditPart(
						view);

			case org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.WarehouseEditPart.VISUAL_ID:
				return new org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.WarehouseEditPart(
						view);

			case org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.WarehouseNameEditPart.VISUAL_ID:
				return new org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.WarehouseNameEditPart(
						view);

			case org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.RoomEditPart.VISUAL_ID:
				return new org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.RoomEditPart(
						view);

			case org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.RoomNameEditPart.VISUAL_ID:
				return new org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.RoomNameEditPart(
						view);

			case org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.SectionEditPart.VISUAL_ID:
				return new org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.SectionEditPart(
						view);

			case org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.SectionNameEditPart.VISUAL_ID:
				return new org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.SectionNameEditPart(
						view);

			case org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.BizLoc2EditPart.VISUAL_ID:
				return new org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.BizLoc2EditPart(
						view);

			case org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.BizLocName2EditPart.VISUAL_ID:
				return new org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.BizLocName2EditPart(
						view);

			case org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.Warehouse2EditPart.VISUAL_ID:
				return new org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.Warehouse2EditPart(
						view);

			case org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.WarehouseName2EditPart.VISUAL_ID:
				return new org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.WarehouseName2EditPart(
						view);

			case org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.Room2EditPart.VISUAL_ID:
				return new org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.Room2EditPart(
						view);

			case org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.RoomName2EditPart.VISUAL_ID:
				return new org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.RoomName2EditPart(
						view);

			case org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.Section2EditPart.VISUAL_ID:
				return new org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.Section2EditPart(
						view);

			case org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.SectionName2EditPart.VISUAL_ID:
				return new org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.SectionName2EditPart(
						view);

			case org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.ReadPointEditPart.VISUAL_ID:
				return new org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.ReadPointEditPart(
						view);

			case org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.ContainerEditPart.VISUAL_ID:
				return new org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.ContainerEditPart(
						view);

			case org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.ConveyorEditPart.VISUAL_ID:
				return new org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.ConveyorEditPart(
						view);

			case org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.ShelfEditPart.VISUAL_ID:
				return new org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.ShelfEditPart(
						view);

			case org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.GateEditPart.VISUAL_ID:
				return new org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.GateEditPart(
						view);

			case org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.PalletTruckEditPart.VISUAL_ID:
				return new org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.PalletTruckEditPart(
						view);

			case org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.PushArmEditPart.VISUAL_ID:
				return new org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.PushArmEditPart(
						view);

			case org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.HandHeldReaderEditPart.VISUAL_ID:
				return new org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.HandHeldReaderEditPart(
						view);

			case org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.BizLocWarehousesCompartmentEditPart.VISUAL_ID:
				return new org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.BizLocWarehousesCompartmentEditPart(
						view);

			case org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.BizLocContainersCompartmentEditPart.VISUAL_ID:
				return new org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.BizLocContainersCompartmentEditPart(
						view);

			case org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.BizLocWarehousesCompartment2EditPart.VISUAL_ID:
				return new org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.BizLocWarehousesCompartment2EditPart(
						view);

			case org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.BizLocContainersCompartment2EditPart.VISUAL_ID:
				return new org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.BizLocContainersCompartment2EditPart(
						view);

			case org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.WarehouseWarehousesCompartmentEditPart.VISUAL_ID:
				return new org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.WarehouseWarehousesCompartmentEditPart(
						view);

			case org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.WarehouseContainersCompartmentEditPart.VISUAL_ID:
				return new org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.WarehouseContainersCompartmentEditPart(
						view);

			case org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.RoomWarehousesCompartmentEditPart.VISUAL_ID:
				return new org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.RoomWarehousesCompartmentEditPart(
						view);

			case org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.RoomContainersCompartmentEditPart.VISUAL_ID:
				return new org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.RoomContainersCompartmentEditPart(
						view);

			case org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.SectionWarehousesCompartmentEditPart.VISUAL_ID:
				return new org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.SectionWarehousesCompartmentEditPart(
						view);

			case org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.SectionContainersCompartmentEditPart.VISUAL_ID:
				return new org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.SectionContainersCompartmentEditPart(
						view);

			case org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.WarehouseWarehousesCompartment2EditPart.VISUAL_ID:
				return new org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.WarehouseWarehousesCompartment2EditPart(
						view);

			case org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.WarehouseContainersCompartment2EditPart.VISUAL_ID:
				return new org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.WarehouseContainersCompartment2EditPart(
						view);

			case org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.RoomWarehousesCompartment2EditPart.VISUAL_ID:
				return new org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.RoomWarehousesCompartment2EditPart(
						view);

			case org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.RoomContainersCompartment2EditPart.VISUAL_ID:
				return new org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.RoomContainersCompartment2EditPart(
						view);

			case org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.SectionWarehousesCompartment2EditPart.VISUAL_ID:
				return new org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.SectionWarehousesCompartment2EditPart(
						view);

			case org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.SectionContainersCompartment2EditPart.VISUAL_ID:
				return new org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.SectionContainersCompartment2EditPart(
						view);
			}
		}
		return createUnrecognizedEditPart(context, model);
	}

	/**
	 * @generated
	 */
	private EditPart createUnrecognizedEditPart(EditPart context, Object model) {
		// Handle creation of unrecognized child node EditParts here
		return null;
	}

	/**
	 * @generated
	 */
	public static CellEditorLocator getTextCellEditorLocator(
			ITextAwareEditPart source) {
		if (source.getFigure() instanceof WrappingLabel)
			return new TextCellEditorLocator((WrappingLabel) source.getFigure());
		else {
			return new LabelCellEditorLocator((Label) source.getFigure());
		}
	}

	/**
	 * @generated
	 */
	static private class TextCellEditorLocator implements CellEditorLocator {

		/**
		 * @generated
		 */
		private WrappingLabel wrapLabel;

		/**
		 * @generated
		 */
		public TextCellEditorLocator(WrappingLabel wrapLabel) {
			this.wrapLabel = wrapLabel;
		}

		/**
		 * @generated
		 */
		public WrappingLabel getWrapLabel() {
			return wrapLabel;
		}

		/**
		 * @generated
		 */
		public void relocate(CellEditor celleditor) {
			Text text = (Text) celleditor.getControl();
			Rectangle rect = getWrapLabel().getTextBounds().getCopy();
			getWrapLabel().translateToAbsolute(rect);
			if (getWrapLabel().isTextWrapOn()
					&& getWrapLabel().getText().length() > 0) {
				rect.setSize(new Dimension(text.computeSize(rect.width,
						SWT.DEFAULT)));
			} else {
				int avr = FigureUtilities.getFontMetrics(text.getFont())
						.getAverageCharWidth();
				rect.setSize(new Dimension(text.computeSize(SWT.DEFAULT,
						SWT.DEFAULT)).expand(avr * 2, 0));
			}
			if (!rect.equals(new Rectangle(text.getBounds()))) {
				text.setBounds(rect.x, rect.y, rect.width, rect.height);
			}
		}
	}

	/**
	 * @generated
	 */
	private static class LabelCellEditorLocator implements CellEditorLocator {

		/**
		 * @generated
		 */
		private Label label;

		/**
		 * @generated
		 */
		public LabelCellEditorLocator(Label label) {
			this.label = label;
		}

		/**
		 * @generated
		 */
		public Label getLabel() {
			return label;
		}

		/**
		 * @generated
		 */
		public void relocate(CellEditor celleditor) {
			Text text = (Text) celleditor.getControl();
			Rectangle rect = getLabel().getTextBounds().getCopy();
			getLabel().translateToAbsolute(rect);
			int avr = FigureUtilities.getFontMetrics(text.getFont())
					.getAverageCharWidth();
			rect.setSize(new Dimension(text.computeSize(SWT.DEFAULT,
					SWT.DEFAULT)).expand(avr * 2, 0));
			if (!rect.equals(new Rectangle(text.getBounds()))) {
				text.setBounds(rect.x, rect.y, rect.width, rect.height);
			}
		}
	}
}
