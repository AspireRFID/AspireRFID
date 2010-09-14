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

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.RectangleFigure;
import org.eclipse.draw2d.StackLayout;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.LayoutEditPolicy;
import org.eclipse.gef.editpolicies.NonResizableEditPolicy;
import org.eclipse.gef.requests.CreateRequest;
import org.eclipse.gmf.runtime.diagram.ui.editparts.GraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ShapeNodeEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.EditPolicyRoles;
import org.eclipse.gmf.runtime.draw2d.ui.figures.ConstrainedToolbarLayout;
import org.eclipse.gmf.runtime.draw2d.ui.figures.WrappingLabel;
import org.eclipse.gmf.runtime.gef.ui.figures.DefaultSizeNodeFigure;
import org.eclipse.gmf.runtime.gef.ui.figures.NodeFigure;
import org.eclipse.gmf.runtime.notation.NotationPackage;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.widgets.Display;
import org.ow2.aspirerfid.ide.MasterDataEditorGMF.Warehouse;
import org.ow2.aspirerfid.ide.MasterDataEditorGMF.impl.WarehouseImpl;

/**
 * @generated
 */
public class WarehouseEditPart extends ShapeNodeEditPart {

	/**
	 * @generated
	 */
	public static final int VISUAL_ID = 1002;

	/**
	 * @generated
	 */
	protected IFigure contentPane;

	/**
	 * @generated
	 */
	protected IFigure primaryShape;

	/**
	 * @generated
	 */
	public WarehouseEditPart(View view) {
		super(view);
	}

	/**
	 * @generated
	 */
	protected void createDefaultEditPolicies() {
		super.createDefaultEditPolicies();
		installEditPolicy(
				EditPolicyRoles.SEMANTIC_ROLE,
				new org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.policies.WarehouseItemSemanticEditPolicy());
		installEditPolicy(EditPolicy.LAYOUT_ROLE, createLayoutEditPolicy());
		// XXX need an SCR to runtime to have another abstract superclass that would let children add reasonable editpolicies
		// removeEditPolicy(org.eclipse.gmf.runtime.diagram.ui.editpolicies.EditPolicyRoles.CONNECTION_HANDLES_ROLE);
	}

	/**
	 * @generated
	 */
	protected LayoutEditPolicy createLayoutEditPolicy() {
		LayoutEditPolicy lep = new LayoutEditPolicy() {

			protected EditPolicy createChildEditPolicy(EditPart child) {
				EditPolicy result = child
						.getEditPolicy(EditPolicy.PRIMARY_DRAG_ROLE);
				if (result == null) {
					result = new NonResizableEditPolicy();
				}
				return result;
			}

			protected Command getMoveChildrenCommand(Request request) {
				return null;
			}

			protected Command getCreateCommand(CreateRequest request) {
				return null;
			}
		};
		return lep;
	}

	/**
	 * @generated
	 */
	protected IFigure createNodeShape() {
		WarehouseFigure figure = new WarehouseFigure();
		return primaryShape = figure;
	}

	/**
	 * @generated
	 */
	public WarehouseFigure getPrimaryShape() {
		return (WarehouseFigure) primaryShape;
	}

	/**
	 * @generated
	 */
	protected boolean addFixedChild(EditPart childEditPart) {
		if (childEditPart instanceof org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.WarehouseNameEditPart) {
			((org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.WarehouseNameEditPart) childEditPart)
					.setLabel(getPrimaryShape().getFigureWarehouseNameFigure());
			return true;
		}
		return false;
	}

	/**
	 * @generated
	 */
	protected boolean removeFixedChild(EditPart childEditPart) {

		return false;
	}

	/**
	 * @generated
	 */
	protected void addChildVisual(EditPart childEditPart, int index) {
		if (addFixedChild(childEditPart)) {
			return;
		}
		super.addChildVisual(childEditPart, -1);
	}

	/**
	 * @generated
	 */
	protected void removeChildVisual(EditPart childEditPart) {
		if (removeFixedChild(childEditPart)) {
			return;
		}
		super.removeChildVisual(childEditPart);
	}

	/**
	 * @generated
	 */
	protected IFigure getContentPaneFor(IGraphicalEditPart editPart) {

		return super.getContentPaneFor(editPart);
	}

	/**
	 * @generated
	 */
	protected NodeFigure createNodePlate() {
		DefaultSizeNodeFigure result = new DefaultSizeNodeFigure(getMapMode()
				.DPtoLP(40), getMapMode().DPtoLP(40));
		return result;
	}

	/**
	 * Creates figure for this edit part.
	 * 
	 * Body of this method does not depend on settings in generation model
	 * so you may safely remove <i>generated</i> tag and modify it.
	 * 
	 * @generated
	 */
	protected NodeFigure createNodeFigure() {
		NodeFigure figure = createNodePlate();
		figure.setLayoutManager(new StackLayout());
		IFigure shape = createNodeShape();
		figure.add(shape);
		contentPane = setupContentPane(shape);
		return figure;
	}

	/**
	 * Default implementation treats passed figure as content pane.
	 * Respects layout one may have set for generated figure.
	 * @param nodeShape instance of generated figure class
	 * @generated
	 */
	protected IFigure setupContentPane(IFigure nodeShape) {
		if (nodeShape.getLayoutManager() == null) {
			ConstrainedToolbarLayout layout = new ConstrainedToolbarLayout();
			layout.setSpacing(getMapMode().DPtoLP(5));
			nodeShape.setLayoutManager(layout);
		}
		return nodeShape; // use nodeShape itself as contentPane
	}

	/**
	 * @generated
	 */
	public IFigure getContentPane() {
		if (contentPane != null) {
			return contentPane;
		}
		return super.getContentPane();
	}

	/**
	 * @generated
	 */
	public EditPart getPrimaryChildEditPart() {
		return getChildBySemanticHint(org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.part.MasterDataEditorGMFVisualIDRegistry
				.getType(org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.WarehouseNameEditPart.VISUAL_ID));
	}

	/**
	 * @generated
	 */
	public class WarehouseFigure extends RectangleFigure {

		/**
		 * @generated
		 */
		private WrappingLabel fFigureWarehouseNameFigure;

		/**
		 * @generated
		 */
		public WarehouseFigure() {
			createContents();
		}

		/**
		 * @generated
		 */
		private void createContents() {

			fFigureWarehouseNameFigure = new WrappingLabel();
			fFigureWarehouseNameFigure.setText("<...>");

			fFigureWarehouseNameFigure.setFont(FFIGUREWAREHOUSENAMEFIGURE_FONT);

			fFigureWarehouseNameFigure.setMaximumSize(new Dimension(
					getMapMode().DPtoLP(1000), getMapMode().DPtoLP(20)));

			this.add(fFigureWarehouseNameFigure);

		}

		/**
		 * @generated
		 */
		private boolean myUseLocalCoordinates = false;

		/**
		 * @generated
		 */
		protected boolean useLocalCoordinates() {
			return myUseLocalCoordinates;
		}

		/**
		 * @generated
		 */
		protected void setUseLocalCoordinates(boolean useLocalCoordinates) {
			myUseLocalCoordinates = useLocalCoordinates;
		}

		/**
		 * @generated
		 */
		public WrappingLabel getFigureWarehouseNameFigure() {
			return fFigureWarehouseNameFigure;
		}
		
//		@Override
//		public void paintFigure(Graphics arg0) { 
//			this.setPreferredSize(100, 150);	
//			super.paintFigure(arg0);
//		}

	}

	/**
	 * @generated
	 */
	static final Font FFIGUREWAREHOUSENAMEFIGURE_FONT = new Font(Display
			.getCurrent(),
			Display.getDefault().getSystemFont().getFontData()[0].getName(),
			10, SWT.BOLD);

//	/**
//	 * @generated NOT
//	 */
//	protected void handleNotificationEvent(Notification event) {
//		System.out.println("handled");
//		super.handleNotificationEvent(event);
//		int type = event.getEventType();
//		Object feature = event.getFeature();
////		System.out.println("type: " + type);
////
////		//if (event.getNotifier() instanceof AbstractWarehouseImpl) {
////			System.out.println("in");
////			Dimension size = new Dimension(100, 150);
////		    int x = ((Integer) getStructuralFeatureValue(NotationPackage.eINSTANCE.getLocation_X())).intValue();
////		    int y = ((Integer) getStructuralFeatureValue(NotationPackage.eINSTANCE.getLocation_Y())).intValue();
////		    Point loc = new Point(x + 100, y + 100);
////		    ((GraphicalEditPart) getParent()).setLayoutConstraint(
////		        this,
////		        getFigure(),
////		        new Rectangle(loc, size));
////		    System.out.println("x: " + x + ", y: "+ y);
//		Warehouse warehouse = (WarehouseImpl) ((View) this.getModel()).getElement();
//		warehouse.setID("asd");
//	
//	}
		
	
	
}
