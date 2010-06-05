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

package org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.providers;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmf.runtime.common.core.service.AbstractProvider;
import org.eclipse.gmf.runtime.common.core.service.IOperation;
import org.eclipse.gmf.runtime.common.ui.services.parser.GetParserOperation;
import org.eclipse.gmf.runtime.common.ui.services.parser.IParser;
import org.eclipse.gmf.runtime.common.ui.services.parser.IParserProvider;
import org.eclipse.gmf.runtime.emf.type.core.IElementType;
import org.eclipse.gmf.runtime.emf.ui.services.parser.ParserHintAdapter;
import org.eclipse.gmf.runtime.notation.View;

/**
 * @generated
 */
public class MasterDataEditorGMFParserProvider extends AbstractProvider
		implements IParserProvider {

	/**
	 * @generated
	 */
	private IParser bizLocName_4005Parser;

	/**
	 * @generated
	 */
	private IParser getBizLocName_4005Parser() {
		if (bizLocName_4005Parser == null) {
			bizLocName_4005Parser = createBizLocName_4005Parser();
		}
		return bizLocName_4005Parser;
	}

	/**
	 * @generated
	 */
	protected IParser createBizLocName_4005Parser() {
		EAttribute[] features = new EAttribute[] { org.ow2.aspirerfid.ide.MasterDataEditorGMF.MasterDataEditorGMFPackage.eINSTANCE
				.getAbstractWarehouse_Name(), };
		org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.parsers.MessageFormatParser parser = new org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.parsers.MessageFormatParser(
				features);
		return parser;
	}

	/**
	 * @generated
	 */
	private IParser warehouseName_4006Parser;

	/**
	 * @generated
	 */
	private IParser getWarehouseName_4006Parser() {
		if (warehouseName_4006Parser == null) {
			warehouseName_4006Parser = createWarehouseName_4006Parser();
		}
		return warehouseName_4006Parser;
	}

	/**
	 * @generated
	 */
	protected IParser createWarehouseName_4006Parser() {
		EAttribute[] features = new EAttribute[] { org.ow2.aspirerfid.ide.MasterDataEditorGMF.MasterDataEditorGMFPackage.eINSTANCE
				.getAbstractWarehouse_Name(), };
		org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.parsers.MessageFormatParser parser = new org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.parsers.MessageFormatParser(
				features);
		return parser;
	}

	/**
	 * @generated
	 */
	private IParser roomName_4007Parser;

	/**
	 * @generated
	 */
	private IParser getRoomName_4007Parser() {
		if (roomName_4007Parser == null) {
			roomName_4007Parser = createRoomName_4007Parser();
		}
		return roomName_4007Parser;
	}

	/**
	 * @generated
	 */
	protected IParser createRoomName_4007Parser() {
		EAttribute[] features = new EAttribute[] { org.ow2.aspirerfid.ide.MasterDataEditorGMF.MasterDataEditorGMFPackage.eINSTANCE
				.getAbstractWarehouse_Name(), };
		org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.parsers.MessageFormatParser parser = new org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.parsers.MessageFormatParser(
				features);
		return parser;
	}

	/**
	 * @generated
	 */
	private IParser sectionName_4008Parser;

	/**
	 * @generated
	 */
	private IParser getSectionName_4008Parser() {
		if (sectionName_4008Parser == null) {
			sectionName_4008Parser = createSectionName_4008Parser();
		}
		return sectionName_4008Parser;
	}

	/**
	 * @generated
	 */
	protected IParser createSectionName_4008Parser() {
		EAttribute[] features = new EAttribute[] { org.ow2.aspirerfid.ide.MasterDataEditorGMF.MasterDataEditorGMFPackage.eINSTANCE
				.getAbstractWarehouse_Name(), };
		org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.parsers.MessageFormatParser parser = new org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.parsers.MessageFormatParser(
				features);
		return parser;
	}

	/**
	 * @generated
	 */
	private IParser bizLocName_4004Parser;

	/**
	 * @generated
	 */
	private IParser getBizLocName_4004Parser() {
		if (bizLocName_4004Parser == null) {
			bizLocName_4004Parser = createBizLocName_4004Parser();
		}
		return bizLocName_4004Parser;
	}

	/**
	 * @generated
	 */
	protected IParser createBizLocName_4004Parser() {
		EAttribute[] features = new EAttribute[] { org.ow2.aspirerfid.ide.MasterDataEditorGMF.MasterDataEditorGMFPackage.eINSTANCE
				.getAbstractWarehouse_Name(), };
		org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.parsers.MessageFormatParser parser = new org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.parsers.MessageFormatParser(
				features);
		return parser;
	}

	/**
	 * @generated
	 */
	private IParser warehouseName_4003Parser;

	/**
	 * @generated
	 */
	private IParser getWarehouseName_4003Parser() {
		if (warehouseName_4003Parser == null) {
			warehouseName_4003Parser = createWarehouseName_4003Parser();
		}
		return warehouseName_4003Parser;
	}

	/**
	 * @generated
	 */
	protected IParser createWarehouseName_4003Parser() {
		EAttribute[] features = new EAttribute[] { org.ow2.aspirerfid.ide.MasterDataEditorGMF.MasterDataEditorGMFPackage.eINSTANCE
				.getAbstractWarehouse_Name(), };
		org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.parsers.MessageFormatParser parser = new org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.parsers.MessageFormatParser(
				features);
		return parser;
	}

	/**
	 * @generated
	 */
	private IParser roomName_4002Parser;

	/**
	 * @generated
	 */
	private IParser getRoomName_4002Parser() {
		if (roomName_4002Parser == null) {
			roomName_4002Parser = createRoomName_4002Parser();
		}
		return roomName_4002Parser;
	}

	/**
	 * @generated
	 */
	protected IParser createRoomName_4002Parser() {
		EAttribute[] features = new EAttribute[] { org.ow2.aspirerfid.ide.MasterDataEditorGMF.MasterDataEditorGMFPackage.eINSTANCE
				.getAbstractWarehouse_Name(), };
		org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.parsers.MessageFormatParser parser = new org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.parsers.MessageFormatParser(
				features);
		return parser;
	}

	/**
	 * @generated
	 */
	private IParser sectionName_4001Parser;

	/**
	 * @generated
	 */
	private IParser getSectionName_4001Parser() {
		if (sectionName_4001Parser == null) {
			sectionName_4001Parser = createSectionName_4001Parser();
		}
		return sectionName_4001Parser;
	}

	/**
	 * @generated
	 */
	protected IParser createSectionName_4001Parser() {
		EAttribute[] features = new EAttribute[] { org.ow2.aspirerfid.ide.MasterDataEditorGMF.MasterDataEditorGMFPackage.eINSTANCE
				.getAbstractWarehouse_Name(), };
		org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.parsers.MessageFormatParser parser = new org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.parsers.MessageFormatParser(
				features);
		return parser;
	}

	/**
	 * @generated
	 */
	protected IParser getParser(int visualID) {
		switch (visualID) {
		case org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.BizLocNameEditPart.VISUAL_ID:
			return getBizLocName_4005Parser();
		case org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.WarehouseNameEditPart.VISUAL_ID:
			return getWarehouseName_4006Parser();
		case org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.RoomNameEditPart.VISUAL_ID:
			return getRoomName_4007Parser();
		case org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.SectionNameEditPart.VISUAL_ID:
			return getSectionName_4008Parser();
		case org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.BizLocName2EditPart.VISUAL_ID:
			return getBizLocName_4004Parser();
		case org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.WarehouseName2EditPart.VISUAL_ID:
			return getWarehouseName_4003Parser();
		case org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.RoomName2EditPart.VISUAL_ID:
			return getRoomName_4002Parser();
		case org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.SectionName2EditPart.VISUAL_ID:
			return getSectionName_4001Parser();
		}
		return null;
	}

	/**
	 * @generated
	 */
	public IParser getParser(IAdaptable hint) {
		String vid = (String) hint.getAdapter(String.class);
		if (vid != null) {
			return getParser(org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.part.MasterDataEditorGMFVisualIDRegistry
					.getVisualID(vid));
		}
		View view = (View) hint.getAdapter(View.class);
		if (view != null) {
			return getParser(org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.part.MasterDataEditorGMFVisualIDRegistry
					.getVisualID(view));
		}
		return null;
	}

	/**
	 * @generated
	 */
	public boolean provides(IOperation operation) {
		if (operation instanceof GetParserOperation) {
			IAdaptable hint = ((GetParserOperation) operation).getHint();
			if (org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.providers.MasterDataEditorGMFElementTypes
					.getElement(hint) == null) {
				return false;
			}
			return getParser(hint) != null;
		}
		return false;
	}

	/**
	 * @generated
	 */
	public static class HintAdapter extends ParserHintAdapter {

		/**
		 * @generated
		 */
		private final IElementType elementType;

		/**
		 * @generated
		 */
		public HintAdapter(IElementType type, EObject object, String parserHint) {
			super(object, parserHint);
			assert type != null;
			elementType = type;
		}

		/**
		 * @generated
		 */
		public Object getAdapter(Class adapter) {
			if (IElementType.class.equals(adapter)) {
				return elementType;
			}
			return super.getAdapter(adapter);
		}
	}

}
