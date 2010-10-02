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

import java.util.ArrayList;
import java.util.List;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.RectangleFigure;
import org.eclipse.draw2d.StackLayout;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.LayoutEditPolicy;
import org.eclipse.gef.editpolicies.NonResizableEditPolicy;
import org.eclipse.gef.requests.CreateRequest;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ShapeNodeEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.EditPolicyRoles;
import org.eclipse.gmf.runtime.draw2d.ui.figures.ConstrainedToolbarLayout;
import org.eclipse.gmf.runtime.draw2d.ui.figures.WrappingLabel;
import org.eclipse.gmf.runtime.gef.ui.figures.DefaultSizeNodeFigure;
import org.eclipse.gmf.runtime.gef.ui.figures.NodeFigure;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.views.properties.IPropertySource;
import org.ow2.aspirerfid.ide.MasterDataEditorGMF.AbstractWarehouse;
import org.ow2.aspirerfid.ide.MasterDataEditorGMF.Warehouse;
import org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.sheet.WarehouseProperties;
import org.ow2.aspirerfid.ide.MasterDataEditorGMF.impl.AbstractWarehouseImpl;
import org.ow2.aspirerfid.ide.MasterDataEditorGMF.impl.WarehouseImpl;
import org.ow2.aspirerfid.ide.MasterDataEditorGMF.querycapture.MasterDataEditParts;

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
	
//	@Override
//	public Object getAdapter(Class adapter) {
//		Warehouse warehouse = (WarehouseImpl) ((View) this.getModel()).getElement();
////		System.out.println("adapter: " + adapter);
//		
//		if (adapter == IPropertySource.class) {
//			System.out.println("adapter: " + adapter);
//			//ISelection sel = new StructuredSelection(BpwmeContentProvider.viewer.getSelection());
//			//a.setInput(getIWorkbenchPart(), sel);
//			return new WarehouseProperties(warehouse).getPropertyDescriptors();
//		}
//		return null;
//	}

//	/**
//	 * @generated NOT
//	 */
//	protected void handleNotificationEvent(Notification event) {
//		super.handleNotificationEvent(event);
//		
//		if (event.getNotifier() instanceof AbstractWarehouseImpl) {
//			resetProperties(element);
//		}
//	}
//	
//	/**
//	 * Element
//	 */
//	AbstractWarehouse element = (AbstractWarehouseImpl) ((View) this.getModel()).getElement();
//	
//	/**
//	 * Element attr
//	 */
//	private String[] elementAttr = {element.getAttr1(), element.getAttr2(), element.getAttr3(), element.getAttr4(), element.getAttr5(), 
//									element.getAttr6(), element.getAttr7(), element.getAttr8(), element.getAttr9(), element.getAttr10(), 
//									element.getAttr11(), element.getAttr12(), element.getAttr13(), element.getAttr14(), element.getAttr15(), 
//									element.getAttr16(), element.getAttr17(), element.getAttr18(), element.getAttr19(), element.getAttr20()};
//	
//	/**
//	 * Resets the values of non existing properties
//	 */
//	private void resetProperties(AbstractWarehouse element) {
//		List<Integer> countAttr = new ArrayList<Integer>();
//		boolean found = false;
//		
//		for (int i = 0; i <  elementAttr.length; i++) {
//			if (!(elementAttr[i].isEmpty() && elementAttr[i] == "")) {
//				found = false;
//				
//				for (int j = 0; j < MasterDataEditParts.getNewWarehouseAttr().length; j++) {
//					if (elementAttr[i].startsWith(MasterDataEditParts.getNewWarehouseAttr()[j] + "_")) {
//						found = true;
//					}
//				}
//				if (!found)
//					countAttr.add(i);
//			}
//		}
//	
//		for (int i = 0; i < countAttr.size(); i++) {
//			switch(countAttr.get(i)) {
//				case 0:element.setAttr1("");break;
//				case 1:element.setAttr2("");break;
//				case 2:element.setAttr3("");break;
//				case 3:element.setAttr4("");break;
//				case 4:element.setAttr5("");break;
//				case 5:element.setAttr6("");break;
//				case 6:element.setAttr7("");break;
//				case 7:element.setAttr8("");break;
//				case 8:element.setAttr9("");break;
//				case 9:element.setAttr10("");break;
//				case 10:element.setAttr11("");break;
//				case 11:element.setAttr12("");break;
//				case 12:element.setAttr13("");break;
//				case 13:element.setAttr14("");break;
//				case 14:element.setAttr15("");break;
//				case 15:element.setAttr16("");break;
//				case 16:element.setAttr17("");break;
//				case 17:element.setAttr18("");break;
//				case 18:element.setAttr19("");break;
//				case 19:element.setAttr20("");break;
//			}
//		}
//	}
		
}
