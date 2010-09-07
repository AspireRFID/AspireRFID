package org.ow2.aspirerfid.ide.bpwme.actions;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.gmf.runtime.common.ui.action.AbstractActionDelegate;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.ow2.aspirerfid.ide.bpwme.diagram.views.ExtendedAttr;

public class EditExtendedAttributes extends AbstractActionDelegate 
implements IObjectActionDelegate {

	public EditExtendedAttributes() {
	}

	@Override
	protected void doRun(IProgressMonitor progressMonitor) {
		//show extended attr view
		try {
			PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().showView(ExtendedAttr.ID);
		} catch (PartInitException e) {
			e.printStackTrace();
		}
	}

}
