package org.ow2.aspirerfid.ide.bpwme.diagram.part;

import org.eclipse.gmf.runtime.diagram.ui.parts.DiagramActionBarContributor;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.IWorkbenchActionConstants;
import org.eclipse.ui.IWorkbenchPage;

/**
 * @generated
 */
public class BpwmeDiagramActionBarContributor extends
		DiagramActionBarContributor {

	/**
	 * @generated
	 */
	protected Class getEditorClass() {
		return BpwmeDiagramEditor.class;
	}

	/**
	 * @generated
	 */
	protected String getEditorId() {
		return BpwmeDiagramEditor.ID;
	}

	/**
	 * @generated
	 */
	public void init(IActionBars bars, IWorkbenchPage page) {
		super.init(bars, page);
		// print preview
//		IMenuManager fileMenu = bars.getMenuManager().findMenuUsingPath(
//				IWorkbenchActionConstants.M_FILE);
//		assert fileMenu != null;
//		fileMenu.remove("pageSetupAction"); //$NON-NLS-1$
	}
}
