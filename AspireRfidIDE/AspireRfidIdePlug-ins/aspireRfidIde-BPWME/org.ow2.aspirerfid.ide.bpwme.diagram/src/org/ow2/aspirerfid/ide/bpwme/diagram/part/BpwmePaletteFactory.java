package org.ow2.aspirerfid.ide.bpwme.diagram.part;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.gef.Tool;
import org.eclipse.gef.palette.PaletteContainer;
import org.eclipse.gef.palette.PaletteGroup;
import org.eclipse.gef.palette.PaletteRoot;
import org.eclipse.gef.palette.PaletteSeparator;
import org.eclipse.gef.palette.ToolEntry;
import org.eclipse.gmf.runtime.diagram.ui.tools.UnspecifiedTypeConnectionTool;
import org.eclipse.gmf.runtime.diagram.ui.tools.UnspecifiedTypeCreationTool;
import org.ow2.aspirerfid.ide.bpwme.diagram.providers.BpwmeElementTypes;


/**
 * @generated
 */
public class BpwmePaletteFactory {

	/**
	 * @generated
	 */
	public void fillPalette(PaletteRoot paletteRoot) {
		paletteRoot.add(createBpwme1Group());
	}

	/**
	 * Creates "bpwme" palette tool group
	 * @generated
	 */
	private PaletteContainer createBpwme1Group() {
		PaletteGroup paletteContainer = new PaletteGroup(
				Messages.Bpwme1Group_title);
		paletteContainer.setId("createBpwme1Group"); //$NON-NLS-1$
		paletteContainer.add(createOLCBProc1CreationTool());
		paletteContainer.add(createCLCBProc2CreationTool());
		paletteContainer.add(createEBProc3CreationTool());
		paletteContainer.add(new PaletteSeparator());
		paletteContainer.add(createConnection5CreationTool());
		return paletteContainer;
	}

	/**
	 * @generated
	 */
	private ToolEntry createOLCBProc1CreationTool() {
		List/*<IElementType>*/types = new ArrayList/*<IElementType>*/(1);
		types.add(BpwmeElementTypes.OLCBProc_2001);
		NodeToolEntry entry = new NodeToolEntry(
				Messages.OLCBProc1CreationTool_title,
				Messages.OLCBProc1CreationTool_desc, types);
		entry.setId("createOLCBProc1CreationTool"); //$NON-NLS-1$
		entry.setSmallIcon(BpwmeElementTypes
				.getImageDescriptor(BpwmeElementTypes.OLCBProc_2001));
		entry.setLargeIcon(entry.getSmallIcon());
		return entry;
	}

	/**
	 * @generated
	 */
	private ToolEntry createCLCBProc2CreationTool() {
		List/*<IElementType>*/types = new ArrayList/*<IElementType>*/(1);
		types.add(BpwmeElementTypes.CLCBProc_3001);
		NodeToolEntry entry = new NodeToolEntry(
				Messages.CLCBProc2CreationTool_title,
				Messages.CLCBProc2CreationTool_desc, types);
		entry.setId("createCLCBProc2CreationTool"); //$NON-NLS-1$
		entry.setSmallIcon(BpwmeElementTypes
				.getImageDescriptor(BpwmeElementTypes.CLCBProc_3001));
		entry.setLargeIcon(entry.getSmallIcon());
		return entry;
	}

	/**
	 * @generated
	 */
	private ToolEntry createEBProc3CreationTool() {
		List/*<IElementType>*/types = new ArrayList/*<IElementType>*/(1);
		types.add(BpwmeElementTypes.EBProc_3002);
		NodeToolEntry entry = new NodeToolEntry(
				Messages.EBProc3CreationTool_title,
				Messages.EBProc3CreationTool_desc, types);
		entry.setId("createEBProc3CreationTool"); //$NON-NLS-1$
		entry.setSmallIcon(BpwmeElementTypes
				.getImageDescriptor(BpwmeElementTypes.EBProc_3002));
		entry.setLargeIcon(entry.getSmallIcon());
		return entry;
	}

	/**
	 * @generated
	 */
	private ToolEntry createConnection5CreationTool() {
		List/*<IElementType>*/types = new ArrayList/*<IElementType>*/(1);
		types.add(BpwmeElementTypes.Connection_4001);
		LinkToolEntry entry = new LinkToolEntry(
				Messages.Connection5CreationTool_title,
				Messages.Connection5CreationTool_desc, types);
		entry.setId("createConnection5CreationTool"); //$NON-NLS-1$
		entry.setSmallIcon(BpwmeElementTypes
				.getImageDescriptor(BpwmeElementTypes.Connection_4001));
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

	/**
	 * @generated
	 */
	private static class LinkToolEntry extends ToolEntry {

		/**
		 * @generated
		 */
		private final List relationshipTypes;

		/**
		 * @generated
		 */
		private LinkToolEntry(String title, String description,
				List relationshipTypes) {
			super(title, description, null, null);
			this.relationshipTypes = relationshipTypes;
		}

		/**
		 * @generated
		 */
		public Tool createTool() {
			Tool tool = new UnspecifiedTypeConnectionTool(relationshipTypes);
			tool.setProperties(getToolProperties());
			return tool;
		}
	}
}
