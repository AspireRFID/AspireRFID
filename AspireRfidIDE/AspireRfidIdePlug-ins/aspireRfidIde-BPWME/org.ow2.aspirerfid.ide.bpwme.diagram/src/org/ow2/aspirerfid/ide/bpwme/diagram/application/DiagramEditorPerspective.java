package org.ow2.aspirerfid.ide.bpwme.diagram.application;

import org.eclipse.ui.IFolderLayout;
import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IPerspectiveFactory;
import org.eclipse.ui.IPlaceholderFolderLayout;
import org.ow2.aspirerfid.ide.bpwme.master.views.MasterEditor;

public class DiagramEditorPerspective implements IPerspectiveFactory {
	/**
	 * Modified by yluo
	 */
	public void createInitialLayout(IPageLayout layout) {
		layout.setEditorAreaVisible(true);
		layout
				.addPerspectiveShortcut(DiagramEditorWorkbenchAdvisor.PERSPECTIVE_ID);
		
		
		IFolderLayout buttomLeft = layout.createFolder(
				"bottomLeft", IPageLayout.BOTTOM, 0.6f, layout.getEditorArea()); //$NON-NLS-1$	 //$NON-NLS-2$
		buttomLeft.addView(IPageLayout.ID_PROP_SHEET);
		
		IFolderLayout buttomMiddle = layout.createFolder(
				"bottomMiddle", IPageLayout.RIGHT, 0.6f, IPageLayout.ID_PROP_SHEET); //$NON-NLS-1$	 //$NON-NLS-2$
		buttomMiddle.addView("org.ow2.aspirerfid.ide.bpwme.navigator.view");		
		
		IFolderLayout bottomRight = layout.createFolder(
		"bottomRight", IPageLayout.RIGHT, 0.5f, "org.ow2.aspirerfid.ide.bpwme.navigator.view"); //$NON-NLS-1$	 //$NON-NLS-2$
		bottomRight.addView(IPageLayout.ID_OUTLINE);		

	}
}
