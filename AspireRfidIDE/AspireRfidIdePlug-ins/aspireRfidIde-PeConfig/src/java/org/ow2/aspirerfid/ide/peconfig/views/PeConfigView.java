/*
 * Copyright (C) 2008-2010, Aspire
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


package org.ow2.aspirerfid.ide.peconfig.views;

import java.io.File;
import java.io.FileNotFoundException;

import org.apache.log4j.Logger;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.ui.part.*;
import org.ow2.aspirerfid.commons.apdl.model.OLCBProc;
import org.ow2.aspirerfid.commons.apdl.utils.DeserializerUtil;
import org.ow2.aspirerfid.ide.peconfig.Activator;
import com.swtdesigner.ResourceManager;

import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.viewers.*;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.jface.action.*;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.ui.*;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;

import org.ow2.aspirerfid.ide.peconfig.preferences.PreferenceConstants;
import org.ow2.aspirerfid.ide.peconfig.client.ProgrammableEngineClient;


/**
 * @author Nikos Kefalakis (nkef) e-mail: nkef@ait.edu.gr
 *
 */
public class PeConfigView extends ViewPart {
	private Combo availableApdlsCombo;
	private Action action1;
	private Action action2;

	/** Returns the preference store for this UI plug-in */
	IPreferenceStore peConfigPreferences = Activator.getDefault().getPreferenceStore();

	/** logger. */
	public static final Logger LOG = Logger.getLogger(PeConfigView.class);

	private ProgrammableEngineClient peClient = null;

	public static final String ID = "org.ow2.aspirerfid.ide.peconfig.views.PeConfigView"; //$NON-NLS-1$

	Composite composite;

	/*
	 * The content provider class is responsible for providing objects to the
	 * view. It can wrap existing objects in adapters or simply return objects
	 * as-is. These objects may be sensitive to the current input of the view,
	 * or ignore it and always show the same content (like Task List, for
	 * example).
	 */

	/**
	 * The constructor.
	 */
	public PeConfigView() {
	}

	/**
	 * This is a callback that will allow us to create the viewer and initialize
	 * it.
	 */
	public void createPartControl(Composite parent) {
		final ScrolledComposite scrolledComposite = new ScrolledComposite(parent, SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL);

		composite = new Composite(scrolledComposite, SWT.NONE);

		final Group peEncoderGroup = new Group(composite, SWT.NONE);
		peEncoderGroup.setText("PE Encoder");
		peEncoderGroup.setBounds(10, 10, 409, 61);

		availableApdlsCombo = new Combo(peEncoderGroup, SWT.NONE);
		availableApdlsCombo.addMouseListener(new AvailableApdlsComboMouseListener());
		availableApdlsCombo.setBounds(10, 20, 304, 21);

		final Button encodeButton = new Button(peEncoderGroup, SWT.NONE);
		encodeButton.addMouseListener(new EncodeButtonMouseListener());
		encodeButton.setImage(ResourceManager.getPluginImage(Activator.getDefault(), "icons/fromAgilo/menu_showactivity.png"));
		encodeButton.setText("Encode");
		encodeButton.setBounds(320, 20, 75, 23);
		composite.setSize(494, 346);
		scrolledComposite.setContent(composite);
		makeActions();
		hookContextMenu();
		hookDoubleClickAction();
		contributeToActionBars();
		if (peClient==null
				|| !(peConfigPreferences.getString(PreferenceConstants.P_PE_PeEngineEndPoint).equals(peConfigPreferences
						.getString(PreferenceConstants.P_PE_PeEngineEndCurrentPoint)))) {
			LOG.debug("PE Engine EndPoint  has changed PE Client reinitialized...");
			peClient = new ProgrammableEngineClient(peConfigPreferences.getString(PreferenceConstants.P_PE_PeEngineEndPoint));
			peConfigPreferences.setValue(PreferenceConstants.P_PE_PeEngineEndCurrentPoint, peConfigPreferences
					.getString(PreferenceConstants.P_PE_PeEngineEndPoint));
		}

	}

	private void hookContextMenu() {
		MenuManager menuMgr = new MenuManager("#PopupMenu");
		menuMgr.setRemoveAllWhenShown(true);
		menuMgr.addMenuListener(new IMenuListener() {
			public void menuAboutToShow(IMenuManager manager) {
				PeConfigView.this.fillContextMenu(manager);
			}
		});
	}

	private void contributeToActionBars() {
		IActionBars bars = getViewSite().getActionBars();
		fillLocalPullDown(bars.getMenuManager());
		fillLocalToolBar(bars.getToolBarManager());
	}

	private void fillLocalPullDown(IMenuManager manager) {
		manager.add(action1);
		manager.add(new Separator());
		manager.add(action2);
	}

	private void fillContextMenu(IMenuManager manager) {
		manager.add(action1);
		manager.add(action2);
		// Other plug-ins can contribute there actions here
		manager.add(new Separator(IWorkbenchActionConstants.MB_ADDITIONS));
	}

	private void fillLocalToolBar(IToolBarManager manager) {
		manager.add(action1);
		manager.add(action2);
	}

	private void makeActions() {
		action1 = new Action() {
			public void run() {
				showMessage("Action 1 executed");
			}
		};
		action1.setText("Action 1");
		action1.setToolTipText("Action 1 tooltip");
		action1.setImageDescriptor(PlatformUI.getWorkbench().getSharedImages().getImageDescriptor(ISharedImages.IMG_OBJS_INFO_TSK));

		action2 = new Action() {
			public void run() {
				showMessage("Action 2 executed");
			}
		};
		action2.setText("Action 2");
		action2.setToolTipText("Action 2 tooltip");
		action2.setImageDescriptor(PlatformUI.getWorkbench().getSharedImages().getImageDescriptor(ISharedImages.IMG_OBJS_INFO_TSK));
	}

	private void hookDoubleClickAction() {
	}

	private void showMessage(String message) {
	}

	/**
	 * Passing the focus request to the viewer's control.
	 */
	public void setFocus() {
	}

	private void fillAvailableApdlsCombo() {

		availableApdlsCombo.removeAll();
		
		String folderPath = peConfigPreferences.getString(PreferenceConstants.P_PE_ApdlFilesPath);
		File f = new File(folderPath);
		if (f.exists() == false) {
			f.mkdirs();
		}
		File[] contents = f.listFiles();

		for (int i = 0; i < contents.length; i++) {
			if (contents[i].getName().endsWith(".xml")) {
				availableApdlsCombo.add(contents[i].getName());
			}
		}

	}

	private class AvailableApdlsComboMouseListener extends MouseAdapter {
		public void mouseDown(final MouseEvent e) {
			fillAvailableApdlsCombo();
		}
	}

	private class EncodeButtonMouseListener extends MouseAdapter {
		public void mouseDown(final MouseEvent arg0) {

			OLCBProc openLoopCBProc = new OLCBProc();
			String filePath = peConfigPreferences.getString(PreferenceConstants.P_PE_ApdlFilesPath)+ availableApdlsCombo.getText();
			int responceID;

			try {
				openLoopCBProc = DeserializerUtil.deserializeOLCBProcFile(filePath);
			}
			catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			// openLoopCBProc.setId("urn:epc:id:send1");
			// openLoopCBProc.setName("TheFirstOpenLoopCompositeBusinessProcess");

			LOG.debug("******************Start to Encode*******************");
			LOG.debug("Encoding File: " + filePath);
			responceID = peClient.encodeAspireRFID(openLoopCBProc);

			LOG.debug("The Responce ID is: " + responceID);

			if (responceID == 400) {
				MessageDialog.openInformation(composite.getShell(), "Success", "The APDL file has been Successfully encoded!");
			}
			else if (responceID == 425) {
				MessageDialog.openError(composite.getShell(), "Error", "The APDL file has not been encoded!");

			}

		}
	}

}
