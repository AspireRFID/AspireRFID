/*
 * Copyright (c) 2008-2010, Aspire
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


package org.ow2.aspirerfid.ide.jmx.views;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;

import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.forms.widgets.TableWrapData;
import org.eclipse.ui.forms.widgets.TableWrapLayout;
import org.eclipse.ui.part.ViewPart;
import org.ow2.aspirerfid.ide.jmx.ConfigurationManager;


/**
 * @author Nektarios Leontiadis (nele@ait.edu.gr)
 *
 */
public class ManagementView extends ViewPart {

    public static final String ID = "org.ow2.aspirerfid.ide.jmx.views.ManagementView"; //$NON-NLS-1$
    private Label statusLabel;
    private Composite composite;

    public ManagementView() {
    }

    @Override
    public void createPartControl(Composite parent) {
	composite = parent;
	parent.setLayout(new FormLayout());
	final ScrolledComposite scrolledComposite = new ScrolledComposite(parent, SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL);
	final FormData fd_scrolledComposite = new FormData();
	fd_scrolledComposite.bottom = new FormAttachment(100, 0);
	fd_scrolledComposite.right = new FormAttachment(100, 0);
	fd_scrolledComposite.top = new FormAttachment(0, 0);
	fd_scrolledComposite.left = new FormAttachment(0, 0);
	scrolledComposite.setLayoutData(fd_scrolledComposite);

	final Composite composite = new Composite(scrolledComposite, SWT.NONE);
	composite.setLocation(0, 0);
	composite.setLayout(new FormLayout());

	final Group readerProxyGroup = new Group(composite, SWT.NONE);
	final FormData fd_readerProxyGroup = new FormData();
	fd_readerProxyGroup.bottom = new FormAttachment(0, 235);
	fd_readerProxyGroup.right = new FormAttachment(100, -5);
	fd_readerProxyGroup.top = new FormAttachment(0, 5);
	fd_readerProxyGroup.left = new FormAttachment(0, 10);
	readerProxyGroup.setLayoutData(fd_readerProxyGroup);
	final TableWrapLayout tableWrapLayout = new TableWrapLayout();
	tableWrapLayout.verticalSpacing = 10;
	tableWrapLayout.numColumns = 2;
	readerProxyGroup.setLayout(tableWrapLayout);
	readerProxyGroup.setText("Reader proxy");

	final Button startButton = new Button(readerProxyGroup, SWT.NONE);
	startButton.addSelectionListener(new StartButtonSelectionListener());
	startButton.setToolTipText("Start the reader proxy");
	startButton.setText("Start");

	final Button stopButton = new Button(readerProxyGroup, SWT.NONE);
	stopButton.addSelectionListener(new StopButtonSelectionListener());
	stopButton.setToolTipText("Stop the reader proxy");
	stopButton.setText("Stop");

	final Button loadConfigurationButton = new Button(readerProxyGroup, SWT.NONE);
	loadConfigurationButton.addSelectionListener(new LoadConfigurationButtonSelectionListener());
	loadConfigurationButton.setToolTipText("Reloads the configuration and restarts the proxy if required");
	loadConfigurationButton.setText("Save and load updated configuration");

	final Button resetConfigurationButton = new Button(readerProxyGroup, SWT.NONE);
	resetConfigurationButton.addSelectionListener(new ResetConfigurationButtonSelectionListener());
	resetConfigurationButton.setToolTipText("Resets the configuration to the default one and restart the proxy if required");
	resetConfigurationButton.setText("Reset configuration");

	final Label label1 = new Label(readerProxyGroup, SWT.NONE);
	label1.setText("Current status:");

	statusLabel = new Label(readerProxyGroup, SWT.NONE);
	final TableWrapData twd_statusLabel = new TableWrapData(TableWrapData.LEFT, TableWrapData.TOP);
	twd_statusLabel.maxWidth = 132;
	statusLabel.setLayoutData(twd_statusLabel);
	statusLabel.setText("                                    ");

	final Label label = new Label(readerProxyGroup, SWT.NONE);

	final Button refreshStatusButton = new Button(readerProxyGroup, SWT.NONE);
	refreshStatusButton.addSelectionListener(new RefreshStatusButtonSelectionListener());
	refreshStatusButton.setText("Refresh status");

	final Button saveConfigurationFileButton = new Button(readerProxyGroup, SWT.NONE);
	saveConfigurationFileButton.addSelectionListener(new SaveConfigurationFileButtonSelectionListener());
	saveConfigurationFileButton.setText("Download configuration file");

	final Button loadConfigurationFileButton = new Button(readerProxyGroup, SWT.NONE);
	loadConfigurationFileButton.addSelectionListener(new LoadConfigurationFileButtonSelectionListener());
	loadConfigurationFileButton.setText("Upload configuration file");
	composite.setSize(504, 352);
	scrolledComposite.setContent(composite);
	initializeToolBar();

	String[] filefilter = { "*.xml", "*.*" };
	saveDialog = new FileDialog(parent.getShell(), SWT.SAVE);
	saveDialog.setFileName("ReaderDevice.xml");
	loadDialog = new FileDialog(parent.getShell(), SWT.SAVE);
	loadDialog.setFilterExtensions(filefilter);
	
	setStatusLabel(Status.DISCONNECTED);
    }

    @Override
    public void setFocus() {

	refreshStatus();

    }

    private void initializeToolBar() {
	IToolBarManager toolBarManager = getViewSite().getActionBars().getToolBarManager();
    }

    private class StartButtonSelectionListener extends SelectionAdapter {
	public void widgetSelected(final SelectionEvent e) {
	    try {
		ConfigurationManager.getInstance().start();
	    } catch (Exception e1) {
		MessageDialog.openError(composite.getShell(), "Error", "Error while starting proxy: "+e1.getMessage());
	    }
	    refreshStatus();
	}
    }

    private class StopButtonSelectionListener extends SelectionAdapter {
	public void widgetSelected(final SelectionEvent e) {
	    try {
		ConfigurationManager.getInstance().stop();
//		CoreManager.restartCore();
	    } catch (Exception e1) {
		MessageDialog.openError(composite.getShell(), "Error", "Error while stopping proxy: "+e1.getMessage());
	    }
	    refreshStatus();
	}
    }

    private class LoadConfigurationButtonSelectionListener extends SelectionAdapter {
	public void widgetSelected(final SelectionEvent e) {
	    setStatusLabel(Status.RESTARTING);
	    try {
		ConfigurationManager.getInstance().loadConfig();
		MessageDialog.openInformation(composite.getShell(), "Completed", "Configuration loaded");
	    } catch (Exception e1) {
		MessageDialog.openError(composite.getShell(), "Error", "Error while loading configuration: "+e1.getMessage());
	    }
	    refreshStatus();

	}
    }

    private class ResetConfigurationButtonSelectionListener extends SelectionAdapter {
	public void widgetSelected(final SelectionEvent e) {
	    setStatusLabel(Status.RESETTING);
	    try {
		ConfigurationManager.getInstance().resetConfig();
		MessageDialog.openInformation(composite.getShell(), "Completed", "Loaded default configuration");
	    } catch (Exception e1) {
		MessageDialog.openError(composite.getShell(), "Error", "Error while reseting: "+e1.getMessage());
	    }
	    refreshStatus();
	}
    }

    private class SaveConfigurationFileButtonSelectionListener extends SelectionAdapter {
	public void widgetSelected(final SelectionEvent e) {
	    String saveAs = saveDialog.open();
	    if (saveAs != null) {
		try {
		    BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(saveAs));
		    out.write(ConfigurationManager.getInstance().saveConfigurationFileAs().getBytes());
		    out.close();
		    MessageDialog.openInformation(composite.getShell(), "Completed", "File saved");
		} catch (Exception e1) {
		    MessageDialog.openError(composite.getShell(), "Error", "Error while saving configuration: "+e1.getMessage());
		}
	    }
	}
    }

    private class LoadConfigurationFileButtonSelectionListener extends SelectionAdapter {
	public void widgetSelected(final SelectionEvent e) {
	    String file = loadDialog.open();
	    if (file != null) {
		try {
		    setStatusLabel(Status.RESTARTING);
		    StringBuffer fileData = new StringBuffer(1000);
		    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
		    char[] buf = new char[1024];
		    int numRead = 0;
		    while ((numRead = bufferedReader.read(buf)) != -1) {
			String readData = String.valueOf(buf, 0, numRead);
			fileData.append(readData);
			buf = new char[1024];
		    }
		    bufferedReader.close();
		    ConfigurationManager.getInstance().loadConfigurationFile(fileData.toString());
		    MessageDialog.openInformation(composite.getShell(), "Completed", "Configuration loaded");
		    refreshStatus();
		    
		} catch (Exception e1) {
		    MessageDialog.openError(composite.getShell(), "Error", "Error while loading configuration: "+e1.getMessage());
		}
	    }
	    refreshStatus();
	}
    }
    
    private class RefreshStatusButtonSelectionListener extends SelectionAdapter {
    	public void widgetSelected(final SelectionEvent e) {
    	    refreshStatus();
    	}
    }

    private void refreshStatus()
    {
	try {
	    if(ConfigurationManager.getInstance().isStarted())
	    	setStatusLabel(Status.STARTED);
	    else
	    	setStatusLabel(Status.STOPPED);
	} catch (Exception e) {
	    setStatusLabel(Status.DISCONNECTED);
	}
    }
    
    private void setStatusLabel(Status status) {
	statusLabel.setText(status.toString());
    }

    private enum Status {
	STARTED, STOPPED, STOPPING, RESTARTING, RESETTING, DISCONNECTED
    }

    FileDialog saveDialog, loadDialog;
}
