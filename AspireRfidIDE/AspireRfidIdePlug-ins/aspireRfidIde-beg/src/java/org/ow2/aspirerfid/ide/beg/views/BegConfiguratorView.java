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

package org.ow2.aspirerfid.ide.beg.views;

import java.util.ArrayList;
import java.util.List;

import javax.xml.namespace.QName;

import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.handlers.IHandlerService;
import org.eclipse.ui.part.ViewPart;
import org.ow2.aspirerfid.commons.epcis.model.AttributeType;
import org.ow2.aspirerfid.commons.epcis.model.VocabularyElementType;
import org.ow2.aspirerfid.ide.beg.Activator;
import com.swtdesigner.ResourceManager;
import com.swtdesigner.SWTResourceManager;

// import org.ow2.aspirerfid.ide.beg.capture.BegConfiguration;
import org.ow2.aspirerfid.ide.beg.preferences.PreferenceConstants;
import org.ow2.aspirerfid.ide.beg.utils.BegEngineClient;

/**
 * @author Nikos Kefalakis (nkef) e-mail: nkef@ait.edu.gr
 * 
 */
public class BegConfiguratorView extends ViewPart {

	private Combo startedBusinessEventsCombo;
	private Combo availableBusinessEventsCombo;
	private Text bindingPortText;
	private Composite container;

	/** Returns the preference store for this UI plug-in */
	IPreferenceStore begConfigPreferences = Activator.getDefault().getPreferenceStore();

	private ArrayList<VocabularyElementType> recievedVocabularyElementList = new ArrayList<VocabularyElementType>();

	public static final String ID = "org.ow2.aspirerfid.ide.beg.views.BegConfiguratorView"; //$NON-NLS-1$

	/**
	 * Create contents of the view part
	 * 
	 * @param parent
	 */
	@Override
	public void createPartControl(Composite parent) {
		container = new Composite(parent, SWT.NONE);
		container.setLayout(new FillLayout());

		final ScrolledComposite begScrolledComposite = new ScrolledComposite(container, SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL);

		final Composite begConfiguratorComposite = new Composite(begScrolledComposite, SWT.NONE);
		begConfiguratorComposite.setLocation(0, 0);

		final Group startBusinessEventGroup = new Group(begConfiguratorComposite, SWT.NONE);
		startBusinessEventGroup.setText("Start Business Event Generation");
		startBusinessEventGroup.setBounds(10, 0, 456, 89);

		final Label availableEventsLabel = new Label(startBusinessEventGroup, SWT.NONE);
		availableEventsLabel.setBounds(10, 22, 175, 23);
		availableEventsLabel.setFont(SWTResourceManager.getFont("", 11, SWT.NONE));
		availableEventsLabel.setText("Available Business Events:");

		availableBusinessEventsCombo = new Combo(startBusinessEventGroup, SWT.NONE);
		availableBusinessEventsCombo.setBounds(191, 22, 220, 21);

		bindingPortText = new Text(startBusinessEventGroup, SWT.BORDER);
		bindingPortText.setBounds(191, 57, 83, 23);

		final Label aleSubscriptionPortLabel = new Label(startBusinessEventGroup, SWT.NONE);
		aleSubscriptionPortLabel.setBounds(10, 57, 147, 22);
		aleSubscriptionPortLabel.setFont(SWTResourceManager.getFont("", 11, SWT.NONE));
		aleSubscriptionPortLabel.setText("ALE Subscription Port:");

		final Button startCaptureButton = new Button(startBusinessEventGroup, SWT.NONE);
		startCaptureButton.setBounds(305, 55, 140, 23);
		startCaptureButton.addMouseListener(new StartCaptureButtonMouseListener());
		startCaptureButton.setImage(ResourceManager.getPluginImage(Activator.getDefault(), "icons/img/b_sqldoc.png"));
		startCaptureButton.setText("Start Event Generation");
		startCaptureButton.setAlignment(SWT.CENTER);

		final Button refreshAvailableBusinessEventsButton = new Button(startBusinessEventGroup, SWT.NONE);
		refreshAvailableBusinessEventsButton.addMouseListener(new RefreshAvailableBusinessEventsButtonMouseListener());
		refreshAvailableBusinessEventsButton.setImage(ResourceManager.getPluginImage(Activator.getDefault(), "icons/img/s_reload.png"));
		refreshAvailableBusinessEventsButton.setBounds(417, 22, 28, 23);

		final Group stopBusinessEventGroup = new Group(begConfiguratorComposite, SWT.NONE);
		stopBusinessEventGroup.setText("Stop Business Event Generation");
		stopBusinessEventGroup.setBounds(10, 95, 456, 89);

		final Label availableEventsLabel_1 = new Label(stopBusinessEventGroup, SWT.NONE);
		availableEventsLabel_1.setBounds(10, 20, 175, 23);
		availableEventsLabel_1.setFont(SWTResourceManager.getFont("", 11, SWT.NONE));
		availableEventsLabel_1.setText("Started Business Events:");

		startedBusinessEventsCombo = new Combo(stopBusinessEventGroup, SWT.NONE);
		startedBusinessEventsCombo.setBounds(191, 20, 221, 21);

		final Button stopCaptureButton = new Button(stopBusinessEventGroup, SWT.NONE);
		stopCaptureButton.addMouseListener(new StopCaptureButtonMouseListener());
		stopCaptureButton.setImage(ResourceManager.getPluginImage(Activator.getDefault(), "icons/img/bd_deltbl.png"));
		stopCaptureButton.setBounds(190, 50, 140, 23);
		stopCaptureButton.setText("Stop Event Generation");

		final Button refreshStartedBusinessEventsButton = new Button(stopBusinessEventGroup, SWT.NONE);
		refreshStartedBusinessEventsButton.addMouseListener(new RefreshStartedBusinessEventsButtonMouseListener());
		refreshStartedBusinessEventsButton.setImage(ResourceManager.getPluginImage(Activator.getDefault(), "icons/img/s_reload.png"));
		refreshStartedBusinessEventsButton.setBounds(418, 18, 28, 23);
		begConfiguratorComposite.setSize(479, 216);
		begScrolledComposite.setContent(begConfiguratorComposite);
		//
		createActions();
		initializeToolBar();
		initializeMenu();

		// Initialize BegConfiguration once
		if (PreferenceConstants.begEngineClient == null) {
			PreferenceConstants.begEngineClient = new BegEngineClient(begConfigPreferences.getString(PreferenceConstants.P_BegEngineURL));
		}

		// fillStartedBusinessEventsCombo();

	}

	private void fillStartedBusinessEventsCombo() {

		ArrayList<String> startedBusinessEvents = (ArrayList<String>) PreferenceConstants.begEngineClient.getStartedEvents();
		startedBusinessEventsCombo.removeAll();

		if (startedBusinessEvents != null && startedBusinessEvents.size() > 0) {
			for (String startedBusinessEventName : startedBusinessEvents) {
				startedBusinessEventsCombo.add(startedBusinessEventName);
			}
			startedBusinessEventsCombo.select(0);
		}
	}

	private void fillAvailableBusinessEventsCombo() {
		List<VocabularyElementType> vocabularyElementList = null;

		recievedVocabularyElementList.clear();
		availableBusinessEventsCombo.removeAll();

		try {
			vocabularyElementList = PreferenceConstants.begEngineClient.getEventList(begConfigPreferences
					.getString(PreferenceConstants.P_EpcisRepositoryQueryURL));

			if (vocabularyElementList != null && vocabularyElementList.size() > 0) {
				for (VocabularyElementType vocabularyElement : vocabularyElementList) {

					if (vocabularyElement.getId().split(",").length == 3) {
						for (AttributeType vocElementAttribute : vocabularyElement.getAttribute()) {
							if (vocElementAttribute.getId().endsWith("event_name")) {
								// availableBusinessEventsCombo.add(vocElementAttribute.getAny().get(0).getAttribute("attribute"));
								availableBusinessEventsCombo.add(vocElementAttribute.getOtherAttributes().get(new QName("value")));

								recievedVocabularyElementList.add(vocabularyElement);

							}
						}
					}
				}
				if (availableBusinessEventsCombo.getItemCount() > 0) {
					availableBusinessEventsCombo.select(0);
				}
			}

		}
		catch (Exception e) {
			e.printStackTrace();
			MessageDialog.openError(container.getShell(), "Query Exception",
					"The Events could not be retrieved from \nthe Information Shairing Repository!");
		}

	}

	/**
	 * Create the actions
	 */
	private void createActions() {
		// Create the actions
	}

	/**
	 * Initialize the toolbar
	 */
	private void initializeToolBar() {
		IToolBarManager toolbarManager = getViewSite().getActionBars().getToolBarManager();
	}

	/**
	 * Initialize the menu
	 */
	private void initializeMenu() {
		IMenuManager menuManager = getViewSite().getActionBars().getMenuManager();
	}

	@Override
	public void setFocus() {
		// Set the focus
	}

	// ==================================open another
	// view=============================
	// IHandlerService handlerService = (IHandlerService)
	// getSite().getService(IHandlerService.class);
	// try {
	// handlerService.executeCommand("org.ow2.aspirerfid.ide.ecspec.openBegObservationView.command",
	// null);
	// }
	// catch (Exception ex) {
	// throw new
	// RuntimeException("org.ow2.aspirerfid.ide.ecspec.openBegObservationView.command not found");
	// }
	// =========================================================================

	private class OpenBegObservationViewButtonMouseListener extends MouseAdapter {
		public void mouseDown(final MouseEvent e) {
		}
	}

	private class StartCaptureButtonMouseListener extends MouseAdapter {
		public void mouseDown(final MouseEvent e) {

			int vocabularyElementSelected = availableBusinessEventsCombo.getSelectionIndex();

			if (vocabularyElementSelected == -1) {
				MessageDialog.openError(container.getShell(), "Error", "Please Select a Buisness Event.");
				return;
			}
			if (bindingPortText.getText().equals("")) {
				MessageDialog.openError(container.getShell(), "Error", "Please enter a Subscription Port Vallue.");
				return;
			}

			boolean saccesful = PreferenceConstants.begEngineClient.startBegForEvent(recievedVocabularyElementList.get(vocabularyElementSelected),
					begConfigPreferences.getString(PreferenceConstants.P_EpcisRepositoryCaptureURL), bindingPortText.getText());

			if (saccesful) {
				MessageDialog.openInformation(container.getShell(), "Success!", "Event Capturer Started Successfully!");
				bindingPortText.setText("");
			}
			else {
				MessageDialog.openError(container.getShell(), "Error!", "Event Capturer Could not be Started!");
			}
			fillStartedBusinessEventsCombo();
		}
	}

	private class StopCaptureButtonMouseListener extends MouseAdapter {
		public void mouseDown(final MouseEvent e) {

			int vocabularyElementSelected = startedBusinessEventsCombo.getSelectionIndex();

			if (vocabularyElementSelected == -1) {
				MessageDialog.openError(container.getShell(), "Error", "Please Select a Buisness Event.");
				return;
			}

			PreferenceConstants.begEngineClient.stopBegForEven(startedBusinessEventsCombo.getText());
			fillStartedBusinessEventsCombo();

		}
	}

	private class RefreshStartedBusinessEventsButtonMouseListener extends MouseAdapter {
		public void mouseDown(final MouseEvent e) {
			fillStartedBusinessEventsCombo();
		}
	}

	private class RefreshAvailableBusinessEventsButtonMouseListener extends MouseAdapter {
		public void mouseDown(final MouseEvent e) {
			fillAvailableBusinessEventsCombo();
		}
	}

}
