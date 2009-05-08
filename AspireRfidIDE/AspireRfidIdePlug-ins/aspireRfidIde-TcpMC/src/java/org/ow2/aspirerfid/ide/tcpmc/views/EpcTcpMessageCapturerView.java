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


package org.ow2.aspirerfid.ide.tcpmc.views;

import java.util.List;

import org.accada.ale.xsd.epcglobal.EPC;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ScrolledComposite;
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
import org.ow2.aspirerfid.ide.tcpmc.Activator;
import org.ow2.aspirerfid.ide.tcpmc.preferences.PreferenceConstants;

import com.swtdesigner.ResourceManager;
import com.swtdesigner.SWTResourceManager;

/**
 * @author Nikos Kefalakis (nkef) e-mail: nkef@ait.edu.gr
 *
 */
public class EpcTcpMessageCapturerView extends ViewPart {

	private Table tagIdsObservedTable;
	public static final String ID = "org.ow2.aspirerfid.ide.tcpmc.views.EpcTcpMessageCapturerView"; //$NON-NLS-1$

	private final IWorkbench workbench = PlatformUI.getWorkbench();
	private Display display = workbench.getDisplay();

	private Group epcTagsGroup;

	public EpcTcpMessageCapturerView() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void createPartControl(Composite parent) {
		final Composite container = new Composite(parent, SWT.NONE);
		container.setLayout(new FillLayout());

		epcTagsGroup = new Group(container, SWT.NO_RADIO_GROUP | SWT.BORDER);
		epcTagsGroup.setLayout(new FillLayout());
		if (PreferenceConstants.P_EpcTcpMessageCaptureThreads != null) {
			if (PreferenceConstants.P_EpcTcpMessageCaptureThreads.containsKey("EpcTcpMessageCaptureThread")) {
				epcTagsGroup.setText("Capturing Started");
			}
			else {
				epcTagsGroup.setText("Capturing Stoped");
			}
		}
		else {
			epcTagsGroup.setText("Capturing Stoped");
		}

		tagIdsObservedTable = new Table(epcTagsGroup, SWT.FULL_SELECTION | SWT.MULTI | SWT.BORDER);
		tagIdsObservedTable.setLinesVisible(true);
		tagIdsObservedTable.setHeaderVisible(true);

		final TableColumn tagIdColumn = new TableColumn(tagIdsObservedTable, SWT.CENTER);
		tagIdColumn.setWidth(500);
		tagIdColumn.setText("Tag ID");

		initializeToolBar();
		// TODO Auto-generated method stub

	}

	public void writeStopMassageToEpcTagsGroup() {

		display.asyncExec(new Runnable() {
			public void run() {
				epcTagsGroup.setText("Capturing Stoped");
			}
		});

	}

	public void writeStartMassageToEpcTagsGroup() {

		display.asyncExec(new Runnable() {
			public void run() {
				epcTagsGroup.setText("Capturing Started");
				tagIdsObservedTable.removeAll();
			}
		});

	}

	public void fillObservationTable(final List<EPC> epcs) {
		display.asyncExec(new Runnable() {
			public void run() {
				tagIdsObservedTable.removeAll();
				for (EPC epc : epcs) {
					TableItem tableItem = new TableItem(tagIdsObservedTable, SWT.NONE);
					tableItem.setText(epc.getValue());
				}
			}
		});
	}

	@Override
	public void setFocus() {
		// TODO Auto-generated method stub

	}

	private void initializeToolBar() {
		IToolBarManager toolBarManager = getViewSite().getActionBars().getToolBarManager();
	}

	public void clearObservationTable() {

		display.asyncExec(new Runnable() {
			public void run() {
				tagIdsObservedTable.removeAll();
			}
		});

	}

}
