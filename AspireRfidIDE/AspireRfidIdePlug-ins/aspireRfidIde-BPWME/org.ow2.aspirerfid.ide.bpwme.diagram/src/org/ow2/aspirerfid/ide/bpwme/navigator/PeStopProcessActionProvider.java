package org.ow2.aspirerfid.ide.bpwme.navigator;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.IMemento;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.navigator.CommonActionProvider;
import org.eclipse.ui.navigator.ICommonActionConstants;
import org.eclipse.ui.navigator.ICommonActionExtensionSite;
import org.eclipse.ui.navigator.ICommonMenuConstants;
import org.eclipse.ui.navigator.ICommonViewerSite;
import org.eclipse.ui.navigator.ICommonViewerWorkbenchSite;
import org.ow2.aspirerfid.ide.bpwme.CLCBProc;
import org.ow2.aspirerfid.ide.bpwme.EBProc;
import org.ow2.aspirerfid.ide.bpwme.OLCBProc;

public class PeStopProcessActionProvider extends CommonActionProvider {

	private PeStopProcessAction peStopProcessAction;

	public PeStopProcessActionProvider() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void init(ICommonActionExtensionSite site) {
		ICommonViewerSite viewSite = site.getViewSite();
		if (viewSite instanceof ICommonViewerWorkbenchSite) {
			ICommonViewerWorkbenchSite workbenchSite = (ICommonViewerWorkbenchSite) viewSite;
			peStopProcessAction = new PeStopProcessAction(workbenchSite.getPage(), workbenchSite
					.getSelectionProvider());
		}
	}

	@Override
	public void restoreState(IMemento memento) {
		super.restoreState(memento);
	}

	@Override
	public void saveState(IMemento memento) {
		super.saveState(memento);
	}

	@Override
	public void fillActionBars(IActionBars actionBars) {
		if (peStopProcessAction.isEnabled()) {
			actionBars.setGlobalActionHandler(ICommonActionConstants.OPEN, peStopProcessAction);
		}
	}

	@Override
	public void fillContextMenu(IMenuManager menu) {
		if (peStopProcessAction.isEnabled()) {
			menu.appendToGroup(ICommonMenuConstants.GROUP_OPEN, peStopProcessAction);
		}
	}

	/**
	 * Open Action
	 */
	class PeStopProcessAction extends Action {
		private ISelectionProvider provider;
		private Object data;
		private EBProc ebProcForPE = null;
		private OLCBProc olcbProcForPE = null;
		private CLCBProc clcbProcForPE = null;

		public PeStopProcessAction(IWorkbenchPage workbenchPage, ISelectionProvider selectionProvider) {
			super("Stop");
			provider = selectionProvider;
		}

		@Override
		public void run() {

			if (data != null) {

				if (data instanceof EBProc) {
					ebProcForPE = (EBProc) data;

					System.out.println("EBProc With ID:" + ebProcForPE.getId());
					
				} else if (data instanceof CLCBProc){
					
					clcbProcForPE = (CLCBProc) data;
					System.out.println("CLCBProc With ID:" + clcbProcForPE.getId());
					
				} else if (data instanceof OLCBProc){
					
					olcbProcForPE = (OLCBProc) data;
					System.out.println("OLCBProc With ID:" + olcbProcForPE.getId());
					
				}



			}
			super.run();
		}

		@Override
		public boolean isEnabled() {
			ISelection selection = provider.getSelection();
			if (!selection.isEmpty()) {
				IStructuredSelection sSelection = (IStructuredSelection) selection;
				if (sSelection.size() == 1 && sSelection.getFirstElement() instanceof Object) {
					data = (Object) sSelection.getFirstElement();
					return true;
				}
			}
			return false;
		}
	}
}
