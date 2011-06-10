package org.ow2.aspirerfid.ide.bpwme.navigator;

import java.util.Collection;

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
import org.ow2.aspirerfid.commons.pe.exceptions.NotCompletedExecutionException;
import org.ow2.aspirerfid.commons.pe.exceptions.OLCBProcValidationException;
import org.ow2.aspirerfid.ide.bpwme.CLCBProc;
import org.ow2.aspirerfid.ide.bpwme.EBProc;
import org.ow2.aspirerfid.ide.bpwme.OLCBProc;
import org.ow2.aspirerfid.ide.bpwme.peclient.PEOLCBProcControlClient;
import org.ow2.aspirerfid.ide.bpwme.utils.ConsoleWriter;
//import org.ow2.aspirerfid.commons.apdl.model.OLCBProc;

public class PeRegisterProcessActionProvider extends CommonActionProvider {

	private PeRegisterProcessAction peRegisterProcessAction;

	ConsoleWriter consoleWriter = null;

	PEOLCBProcControlClient peOLCBProcControlClient = null;

	public PeRegisterProcessActionProvider() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void init(ICommonActionExtensionSite site) {
		ICommonViewerSite viewSite = site.getViewSite();
		if (viewSite instanceof ICommonViewerWorkbenchSite) {
			ICommonViewerWorkbenchSite workbenchSite = (ICommonViewerWorkbenchSite) viewSite;
			peRegisterProcessAction = new PeRegisterProcessAction(workbenchSite.getPage(), workbenchSite
					.getSelectionProvider());
		}

		consoleWriter = new ConsoleWriter();
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
		if (peRegisterProcessAction.isEnabled()) {
			actionBars.setGlobalActionHandler(ICommonActionConstants.OPEN, peRegisterProcessAction);
		}
	}

	@Override
	public void fillContextMenu(IMenuManager menu) {
		if (peRegisterProcessAction.isEnabled()) {
			menu.appendToGroup(ICommonMenuConstants.GROUP_OPEN, peRegisterProcessAction);
		}
	}

	/**
	 * Open Action
	 */
	class PeRegisterProcessAction extends Action {

		private ISelectionProvider provider;
		private Object data;
		private EBProc ebProcForPE = null;
		private OLCBProc olcbProcForPE = null;
		private CLCBProc clcbProcForPE = null;

		public PeRegisterProcessAction(IWorkbenchPage workbenchPage, ISelectionProvider selectionProvider) {
			super("Register");
			provider = selectionProvider;
		}

		@Override
		public void run() {

			if (data != null) {

				if (data instanceof EBProc) {
					ebProcForPE = (EBProc) data;

					
					olcbProcForPE = (OLCBProc)((CLCBProc)ebProcForPE.eContainer()).eContainer();
					System.out.println("Father of All Containing Object Name:" + olcbProcForPE.getName());
					
					
					System.out.println("EBProc With ID:" + ebProcForPE.getId());

				} else if (data instanceof CLCBProc) {

					clcbProcForPE = (CLCBProc) data;
					System.out.println("CLCBProc With ID:" + clcbProcForPE.getId());

				} else if (data instanceof OLCBProc) {

					olcbProcForPE = (OLCBProc) data;
					
					

					if (peOLCBProcControlClient == null) {
						consoleWriter.writeToConsole("Initializing the OLCBProcControlClient...");
						
						peOLCBProcControlClient = new PEOLCBProcControlClient();
						consoleWriter.writeToConsole("OLCBProcControlClient Successfuly initialized.");
					}

//					org.ow2.aspirerfid.commons.apdl.model.OLCBProc olCBProcOriginal= new org.ow2.aspirerfid.commons.apdl.model.OLCBProc();
//					
//					olCBProcOriginal.getCLCBProc().addAll((Collection<? extends org.ow2.aspirerfid.commons.apdl.model.CLCBProc>) olcbProcForPE.getCLCBProc());
//					olCBProcOriginal.setId(olcbProcForPE.getId());
					
					
					
//					try {
//						peOLCBProcControlClient.register(olcbProcForPE);
//					} catch (OLCBProcValidationException e) {
//						consoleWriter.writeToConsole("OLCBProc Validation Exception");
//						e.printStackTrace();
//					} catch (NotCompletedExecutionException e) {
//						consoleWriter.writeToConsole("Not Completed Execution Exception");
//						e.printStackTrace();
//					}
					
					
					
					
					consoleWriter.writeToConsole("OLCBProc With ID:" + olcbProcForPE.getId()
							+ "\nWas Sucessfully Registered");

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
