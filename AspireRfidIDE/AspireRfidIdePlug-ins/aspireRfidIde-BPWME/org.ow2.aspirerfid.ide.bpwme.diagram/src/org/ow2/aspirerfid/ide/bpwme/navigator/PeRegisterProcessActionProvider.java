package org.ow2.aspirerfid.ide.bpwme.navigator;

import java.io.FileNotFoundException;
import java.util.Collection;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.preference.IPreferenceStore;
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
import org.ow2.aspirerfid.commons.apdl.utils.DeserializerUtil;
import org.ow2.aspirerfid.commons.pe.exceptions.NotCompletedExecutionException;
import org.ow2.aspirerfid.commons.pe.exceptions.OLCBProcValidationException;
import org.ow2.aspirerfid.ide.bpwme.CLCBProc;
import org.ow2.aspirerfid.ide.bpwme.EBProc;
import org.ow2.aspirerfid.ide.bpwme.OLCBProc;
import org.ow2.aspirerfid.ide.bpwme.diagram.part.BpwmeDiagramEditorPlugin;
import org.ow2.aspirerfid.ide.bpwme.diagram.preferences.PreferenceConstants;
import org.ow2.aspirerfid.ide.bpwme.peclient.PEOLCBProcControlClient;
import org.ow2.aspirerfid.ide.bpwme.utils.ConsoleWriter;

//import org.ow2.aspirerfid.commons.apdl.model.OLCBProc;

public class PeRegisterProcessActionProvider extends CommonActionProvider {

	private PeRegisterProcessAction peRegisterProcessAction;

	ConsoleWriter consoleWriter = null;

	PEOLCBProcControlClient peOLCBProcControlClient = null;

	/** Returns the preference store for this UI plug-in */
	IPreferenceStore bpwmeConfigPreferences = BpwmeDiagramEditorPlugin.getInstance().getPreferenceStore();

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

		private String fileSeparator = System.getProperty("file.separator");
		private ISelectionProvider provider;
		private Object data;
		private EBProc ebProcGmfModel = null;
		private OLCBProc olcbProcGmfModel = null;
		private CLCBProc clcbProcGmfModel = null;
		private String apdlFilePath = "";

		public PeRegisterProcessAction(IWorkbenchPage workbenchPage, ISelectionProvider selectionProvider) {
			super("Register");
			provider = selectionProvider;
		}

		@Override
		public void run() {

			if (data != null) {

				if (data instanceof EBProc) {
					ebProcGmfModel = (EBProc) data;

					olcbProcGmfModel = (OLCBProc) ((CLCBProc) ebProcGmfModel.eContainer()).eContainer();
					System.out.println("Father of All Containing Object Name:" + olcbProcGmfModel.getName());

					System.out.println("EBProc With ID:" + ebProcGmfModel.getId());

				} else if (data instanceof CLCBProc) {

					clcbProcGmfModel = (CLCBProc) data;
					System.out.println("CLCBProc With ID:" + clcbProcGmfModel.getId());

				} else if (data instanceof OLCBProc) {

					olcbProcGmfModel = (OLCBProc) data;

					org.ow2.aspirerfid.commons.apdl.model.OLCBProc olcbProcForPE = new org.ow2.aspirerfid.commons.apdl.model.OLCBProc();

					if (peOLCBProcControlClient == null) {
						consoleWriter.writeToConsoleln("Initializing the OLCBProcControlClient...");

						peOLCBProcControlClient = new PEOLCBProcControlClient();
						consoleWriter.writeToConsoleln("OLCBProcControlClient Successfuly initialized.");
					}

					apdlFilePath = bpwmeConfigPreferences.getString(PreferenceConstants.P_BPWME_DIR) + fileSeparator
							+ olcbProcGmfModel.getName() + fileSeparator + olcbProcGmfModel.getName() + ".xml";
					consoleWriter.writeToConsoleln("APDL filepath: \"" + apdlFilePath + "\"");

					try {
						olcbProcForPE = DeserializerUtil.deserializeOLCBProcFile(apdlFilePath);
					} catch (FileNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						consoleWriter.writeErrorToConsoleln("FileNotFound Exception");
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						consoleWriter.writeErrorToConsoleln("Exception");
					}

					try {
						peOLCBProcControlClient.register(olcbProcForPE);
					} catch (OLCBProcValidationException e) {
						consoleWriter.writeErrorToConsoleln("OLCBProc Validation Exception");
						e.printStackTrace();
					} catch (NotCompletedExecutionException e) {
						consoleWriter.writeErrorToConsoleln("Not Completed Execution Exception");
						e.printStackTrace();
					}

					consoleWriter.writeToConsoleln("OLCBProc With ID:" + olcbProcGmfModel.getId()
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
