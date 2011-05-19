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

package org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.part;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.gef.Tool;
import org.eclipse.gef.palette.PaletteContainer;
import org.eclipse.gef.palette.PaletteDrawer;
import org.eclipse.gef.palette.PaletteGroup;
import org.eclipse.gef.palette.PaletteRoot;
import org.eclipse.gef.palette.ToolEntry;
import org.eclipse.gmf.runtime.diagram.ui.tools.UnspecifiedTypeCreationTool;

/**
 * @generated
 */
public class MasterDataEditorGMFPaletteFactory {

	/**
	 * @generated
	 */
	public void fillPalette(PaletteRoot paletteRoot) {
		paletteRoot.add(createMasterDataEditorGMF1Group());
	}

	/**
	 * Creates "MasterDataEditorGMF" palette tool group
	 * @generated
	 */
	private PaletteContainer createMasterDataEditorGMF1Group() {
		PaletteGroup paletteContainer = new PaletteGroup(
				org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.part.Messages.MasterDataEditorGMF1Group_title);
		paletteContainer.add(createWarehouses1Group());
		paletteContainer.add(createContainers2Group());
		return paletteContainer;
	}

	/**
	 * Creates "Warehouses" palette tool group
	 * @generated
	 */
	private PaletteContainer createWarehouses1Group() {
		PaletteDrawer paletteContainer = new PaletteDrawer(
				org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.part.Messages.Warehouses1Group_title);
		paletteContainer
				.setDescription(org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.part.Messages.Warehouses1Group_desc);
		paletteContainer.add(createBizLoc1CreationTool());
		paletteContainer.add(createWarehouse2CreationTool());
		paletteContainer.add(createRoom3CreationTool());
		paletteContainer.add(createSection4CreationTool());
		return paletteContainer;
	}

	/**
	 * Creates "Containers" palette tool group
	 * @generated
	 */
	private PaletteContainer createContainers2Group() {
		PaletteDrawer paletteContainer = new PaletteDrawer(
				"ReadPoints/Items");
		paletteContainer.add(createReadPoint1CreationTool());
		paletteContainer.add(createContainer2CreationTool());
		paletteContainer.add(createConveyor3CreationTool());
		paletteContainer.add(createShelf4CreationTool());
		paletteContainer.add(createGate5CreationTool());
		paletteContainer.add(createPalletTruck6CreationTool());
		paletteContainer.add(createPushArm7CreationTool());
		paletteContainer.add(createHandHeldReader8CreationTool());
		return paletteContainer;
	}

	/**
	 * @generated
	 */
	private ToolEntry createBizLoc1CreationTool() {
		List/*<IElementType>*/types = new ArrayList/*<IElementType>*/(2);
		types
				.add(org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.providers.MasterDataEditorGMFElementTypes.BizLoc_1001);
		types
				.add(org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.providers.MasterDataEditorGMFElementTypes.BizLoc_2001);
		NodeToolEntry entry = new NodeToolEntry(
				org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.part.Messages.BizLoc1CreationTool_title,
				org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.part.Messages.BizLoc1CreationTool_desc,
				types);
		entry
				.setSmallIcon(org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.providers.MasterDataEditorGMFElementTypes
						.getImageDescriptor(org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.providers.MasterDataEditorGMFElementTypes.BizLoc_1001));
		entry.setLargeIcon(entry.getSmallIcon());
		return entry;
	}

	/**
	 * @generated
	 */
	private ToolEntry createWarehouse2CreationTool() {
		List/*<IElementType>*/types = new ArrayList/*<IElementType>*/(2);
		types
				.add(org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.providers.MasterDataEditorGMFElementTypes.Warehouse_2002);
		types
				.add(org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.providers.MasterDataEditorGMFElementTypes.Warehouse_1002);
		NodeToolEntry entry = new NodeToolEntry(
				org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.part.Messages.Warehouse2CreationTool_title,
				org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.part.Messages.Warehouse2CreationTool_desc,
				types);
		entry
				.setSmallIcon(org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.providers.MasterDataEditorGMFElementTypes
						.getImageDescriptor(org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.providers.MasterDataEditorGMFElementTypes.Warehouse_2002));
		entry.setLargeIcon(entry.getSmallIcon());
		return entry;
	}

	/**
	 * @generated
	 */
	private ToolEntry createRoom3CreationTool() {
		List/*<IElementType>*/types = new ArrayList/*<IElementType>*/(2);
		types
				.add(org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.providers.MasterDataEditorGMFElementTypes.Room_2003);
		types
				.add(org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.providers.MasterDataEditorGMFElementTypes.Room_1003);
		NodeToolEntry entry = new NodeToolEntry(
				org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.part.Messages.Room3CreationTool_title,
				org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.part.Messages.Room3CreationTool_desc,
				types);
		entry
				.setSmallIcon(org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.providers.MasterDataEditorGMFElementTypes
						.getImageDescriptor(org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.providers.MasterDataEditorGMFElementTypes.Room_2003));
		entry.setLargeIcon(entry.getSmallIcon());
		return entry;
	}

	/**
	 * @generated
	 */
	private ToolEntry createSection4CreationTool() {
		List/*<IElementType>*/types = new ArrayList/*<IElementType>*/(2);
		types
				.add(org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.providers.MasterDataEditorGMFElementTypes.Section_2004);
		types
				.add(org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.providers.MasterDataEditorGMFElementTypes.Section_1004);
		NodeToolEntry entry = new NodeToolEntry(
				org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.part.Messages.Section4CreationTool_title,
				org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.part.Messages.Section4CreationTool_desc,
				types);
		entry
				.setSmallIcon(org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.providers.MasterDataEditorGMFElementTypes
						.getImageDescriptor(org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.providers.MasterDataEditorGMFElementTypes.Section_2004));
		entry.setLargeIcon(entry.getSmallIcon());
		return entry;
	}

	/**
	 * @generated
	 */
	private ToolEntry createReadPoint1CreationTool() {
		List/*<IElementType>*/types = new ArrayList/*<IElementType>*/(1);
		types
				.add(org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.providers.MasterDataEditorGMFElementTypes.ReadPoint_2005);
		NodeToolEntry entry = new NodeToolEntry(
				org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.part.Messages.ReadPoint1CreationTool_title,
				org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.part.Messages.ReadPoint1CreationTool_desc,
				types);
		entry
				.setSmallIcon(org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.providers.MasterDataEditorGMFElementTypes
						.getImageDescriptor(org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.providers.MasterDataEditorGMFElementTypes.ReadPoint_2005));
		entry.setLargeIcon(entry.getSmallIcon());
		return entry;
	}

	/**
	 * @generated
	 */
	private ToolEntry createContainer2CreationTool() {
		List/*<IElementType>*/types = new ArrayList/*<IElementType>*/(1);
		types
				.add(org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.providers.MasterDataEditorGMFElementTypes.Container_2006);
		NodeToolEntry entry = new NodeToolEntry(
				org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.part.Messages.Container2CreationTool_title,
				org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.part.Messages.Container2CreationTool_desc,
				types);
		entry
				.setSmallIcon(org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.providers.MasterDataEditorGMFElementTypes
						.getImageDescriptor(org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.providers.MasterDataEditorGMFElementTypes.Container_2006));
		entry.setLargeIcon(entry.getSmallIcon());
		return entry;
	}

	/**
	 * @generated
	 */
	private ToolEntry createConveyor3CreationTool() {
		List/*<IElementType>*/types = new ArrayList/*<IElementType>*/(1);
		types
				.add(org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.providers.MasterDataEditorGMFElementTypes.Conveyor_2007);
		NodeToolEntry entry = new NodeToolEntry(
				org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.part.Messages.Conveyor3CreationTool_title,
				org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.part.Messages.Conveyor3CreationTool_desc,
				types);
		entry
				.setSmallIcon(org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.providers.MasterDataEditorGMFElementTypes
						.getImageDescriptor(org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.providers.MasterDataEditorGMFElementTypes.Conveyor_2007));
		entry.setLargeIcon(entry.getSmallIcon());
		return entry;
	}

	/**
	 * @generated
	 */
	private ToolEntry createShelf4CreationTool() {
		List/*<IElementType>*/types = new ArrayList/*<IElementType>*/(1);
		types
				.add(org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.providers.MasterDataEditorGMFElementTypes.Shelf_2008);
		NodeToolEntry entry = new NodeToolEntry(
				org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.part.Messages.Shelf4CreationTool_title,
				org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.part.Messages.Shelf4CreationTool_desc,
				types);
		entry
				.setSmallIcon(org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.providers.MasterDataEditorGMFElementTypes
						.getImageDescriptor(org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.providers.MasterDataEditorGMFElementTypes.Shelf_2008));
		entry.setLargeIcon(entry.getSmallIcon());
		return entry;
	}

	/**
	 * @generated
	 */
	private ToolEntry createGate5CreationTool() {
		List/*<IElementType>*/types = new ArrayList/*<IElementType>*/(1);
		types
				.add(org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.providers.MasterDataEditorGMFElementTypes.Gate_2009);
		NodeToolEntry entry = new NodeToolEntry(
				org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.part.Messages.Gate5CreationTool_title,
				org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.part.Messages.Gate5CreationTool_desc,
				types);
		entry
				.setSmallIcon(org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.providers.MasterDataEditorGMFElementTypes
						.getImageDescriptor(org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.providers.MasterDataEditorGMFElementTypes.Gate_2009));
		entry.setLargeIcon(entry.getSmallIcon());
		return entry;
	}

	/**
	 * @generated
	 */
	private ToolEntry createPalletTruck6CreationTool() {
		List/*<IElementType>*/types = new ArrayList/*<IElementType>*/(1);
		types
				.add(org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.providers.MasterDataEditorGMFElementTypes.PalletTruck_2010);
		NodeToolEntry entry = new NodeToolEntry(
				org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.part.Messages.PalletTruck6CreationTool_title,
				org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.part.Messages.PalletTruck6CreationTool_desc,
				types);
		entry
				.setSmallIcon(org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.providers.MasterDataEditorGMFElementTypes
						.getImageDescriptor(org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.providers.MasterDataEditorGMFElementTypes.PalletTruck_2010));
		entry.setLargeIcon(entry.getSmallIcon());
		return entry;
	}

	/**
	 * @generated
	 */
	private ToolEntry createPushArm7CreationTool() {
		List/*<IElementType>*/types = new ArrayList/*<IElementType>*/(1);
		types
				.add(org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.providers.MasterDataEditorGMFElementTypes.PushArm_2011);
		NodeToolEntry entry = new NodeToolEntry(
				org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.part.Messages.PushArm7CreationTool_title,
				org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.part.Messages.PushArm7CreationTool_desc,
				types);
		entry
				.setSmallIcon(org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.providers.MasterDataEditorGMFElementTypes
						.getImageDescriptor(org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.providers.MasterDataEditorGMFElementTypes.PushArm_2011));
		entry.setLargeIcon(entry.getSmallIcon());
		return entry;
	}

	/**
	 * @generated
	 */
	private ToolEntry createHandHeldReader8CreationTool() {
		List/*<IElementType>*/types = new ArrayList/*<IElementType>*/(1);
		types
				.add(org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.providers.MasterDataEditorGMFElementTypes.HandHeldReader_2012);
		NodeToolEntry entry = new NodeToolEntry(
				org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.part.Messages.HandHeldReader8CreationTool_title,
				org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.part.Messages.HandHeldReader8CreationTool_desc,
				types);
		entry
				.setSmallIcon(org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.providers.MasterDataEditorGMFElementTypes
						.getImageDescriptor(org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.providers.MasterDataEditorGMFElementTypes.HandHeldReader_2012));
		entry.setLargeIcon(entry.getSmallIcon());
		return entry;
	}

	/**
	 * @generated
	 */
	private static class NodeToolEntry extends ToolEntry {

		/**
		 * @generated
		 */
		private final List elementTypes;

		/**
		 * @generated
		 */
		private NodeToolEntry(String title, String description,
				List elementTypes) {
			super(title, description, null, null);
			this.elementTypes = elementTypes;
		}

		/**
		 * @generated
		 */
		public Tool createTool() {
			Tool tool = new UnspecifiedTypeCreationTool(elementTypes);
			tool.setProperties(getToolProperties());
			return tool;
		}
	}
}
