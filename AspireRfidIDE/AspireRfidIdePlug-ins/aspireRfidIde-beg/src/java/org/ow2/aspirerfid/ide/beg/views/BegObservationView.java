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

import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.ViewPart;
import org.ow2.aspirerfid.ide.beg.Activator;
import org.ow2.aspirerfid.commons.beg.model.EventStatus;
import org.ow2.aspirerfid.ide.beg.observer.ObserveViewRefresher;
import org.ow2.aspirerfid.ide.beg.preferences.PreferenceConstants;
import org.ow2.aspirerfid.ide.beg.utils.BegEngineClient;

import com.swtdesigner.ResourceManager;

import com.swtdesigner.SWTResourceManager;

/**
 * @author Nikos Kefalakis (nkef) e-mail: nkef@ait.edu.gr
 *
 */
public class BegObservationView extends ViewPart {

	private Combo eventsToObserveCombo;
	private Table tagIdsObservedTable;
	private Text transactionIdObservedText;
	public static final String ID = "org.ow2.aspirerfid.ide.beg.views.BegObservationView"; //$NON-NLS-1$
	private ObserveViewRefresher observeViewRefresher = null;

	/** Returns the preference store for this UI plug-in */
	IPreferenceStore begConfigPreferences = Activator.getDefault().getPreferenceStore();
	
	final IWorkbench workbench = PlatformUI.getWorkbench();
	Display display = workbench.getDisplay();

	/**
	 * Create contents of the view part
	 * 
	 * @param parent
	 */
	@Override
	public void createPartControl(Composite parent) {
		Composite container = new Composite(parent, SWT.NONE);
		container.setLayout(new FillLayout());

		final ScrolledComposite begScrolledComposite = new ScrolledComposite(container, SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL);

		final Composite begComposite = new Composite(begScrolledComposite, SWT.NONE);
		begComposite.setLocation(0, 0);

		final Group buGroup = new Group(begComposite, SWT.NONE);
		buGroup.setText("Business Step Observation");
		buGroup.setBounds(15, 75, 447, 477);

		tagIdsObservedTable = new Table(buGroup, SWT.MULTI | SWT.FULL_SELECTION | SWT.BORDER);// |
		tagIdsObservedTable.setBounds(15, 51, 422, 416);
		tagIdsObservedTable.setLinesVisible(true);
		tagIdsObservedTable.setHeaderVisible(true);

		final TableColumn tagIdColumn = new TableColumn(tagIdsObservedTable, SWT.CENTER);
		tagIdColumn.setWidth(418);
		tagIdColumn.setText("Tag ID");

		transactionIdObservedText = new Text(buGroup, SWT.BORDER);
		transactionIdObservedText.setBounds(122, 20, 315, 25);

		final Label transactionIdLabel = new Label(buGroup, SWT.NONE);
		transactionIdLabel.setBounds(15, 25, 101, 16);
		transactionIdLabel.setFont(SWTResourceManager.getFont("", 11, SWT.NONE));
		transactionIdLabel.setText("Transaction ID:");

		final Group eventObservedGroup = new Group(begComposite, SWT.NONE);
		eventObservedGroup.setText("Event Observed");
		eventObservedGroup.setBounds(15, 20, 447, 46);

		final Label transactionIdLabel_1 = new Label(eventObservedGroup, SWT.NONE);
		transactionIdLabel_1.setBounds(10, 16, 172, 16);
		transactionIdLabel_1.setFont(SWTResourceManager.getFont("", 11, SWT.NONE));
		transactionIdLabel_1.setText("Choose Event to Observe:");

		eventsToObserveCombo = new Combo(eventObservedGroup, SWT.NONE);
		eventsToObserveCombo.addSelectionListener(new EventsToObserveComboSelectionListener());
		eventsToObserveCombo.setBounds(185, 15, 218, 21);

		final Button refreshEventsToObserveButton = new Button(eventObservedGroup, SWT.NONE);
		refreshEventsToObserveButton.addMouseListener(new RefreshEventsToObserveButtonMouseListener());
		refreshEventsToObserveButton.setImage(ResourceManager.getPluginImage(Activator.getDefault(), "icons/img/s_reload.png"));
		refreshEventsToObserveButton.setBounds(409, 14, 28, 23);
		begComposite.setSize(494, 571);
		begScrolledComposite.setContent(begComposite);

		createActions();
		initializeToolBar();
		initializeMenu();

		//Initialize BegConfiguration once
		if (PreferenceConstants.begEngineClient==null){
			PreferenceConstants.begEngineClient = new BegEngineClient(begConfigPreferences.getString(PreferenceConstants.P_BegEngineURL));
		}

	}

	public void fillObservation(final EventStatus eventStatus) {

		display.asyncExec(new Runnable() {
			public void run() {
				tagIdsObservedTable.removeAll();
				transactionIdObservedText.setText("");
			}
		});

		if (!eventStatus.getTransactionID().equals("")) {
			display.asyncExec(new Runnable() {
				public void run() {
					transactionIdObservedText.setText(eventStatus.getTransactionID());
				}
			});
		}

		if (!(eventStatus.getEpcList().size() == 0)) {
			display.asyncExec(new Runnable() {
				public void run() {
					for (String epc : eventStatus.getEpcList()) {
						TableItem tableItem = new TableItem(tagIdsObservedTable, SWT.NONE);
						tableItem.setText(epc);
					}
				}
			});
		}
	}

	private void fillStartedBusinessEventsCombo() {

		ArrayList<String> startedBusinessEvents = (ArrayList<String>) PreferenceConstants.begEngineClient.getStartedEvents();
		eventsToObserveCombo.removeAll();
		tagIdsObservedTable.removeAll();
		transactionIdObservedText.setText("");

		if (startedBusinessEvents != null && startedBusinessEvents.size() > 0) {
			for (String startedBusinessEventName : startedBusinessEvents) {
				eventsToObserveCombo.add(startedBusinessEventName);
			}
			eventsToObserveCombo.select(0);
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

	private class EventsToObserveComboSelectionListener extends SelectionAdapter {
		public void widgetSelected(final SelectionEvent e) {

			if (observeViewRefresher == null && !eventsToObserveCombo.getText().equals("")) {
				observeViewRefresher = new ObserveViewRefresher(eventsToObserveCombo.getText());
			}
			else if (!observeViewRefresher.equals(null) && !eventsToObserveCombo.getText().equals("")) {
				observeViewRefresher.setEventID(eventsToObserveCombo.getText());
			}
			System.out.print(":)");
		}
	}

	private class RefreshEventsToObserveButtonMouseListener extends MouseAdapter {
		public void mouseDown(final MouseEvent e) {
System.out.print(":):):):)");
			fillStartedBusinessEventsCombo();
		}
	}

}
