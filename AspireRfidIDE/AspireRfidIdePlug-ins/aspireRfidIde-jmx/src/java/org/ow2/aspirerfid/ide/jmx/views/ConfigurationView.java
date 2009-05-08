/*
 * Copyright � 2008-2010, Aspire
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

import java.util.HashSet;

import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.ExpandBar;
import org.eclipse.swt.widgets.ExpandItem;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.forms.widgets.ColumnLayout;
import org.eclipse.ui.forms.widgets.TableWrapData;
import org.eclipse.ui.forms.widgets.TableWrapLayout;
import org.eclipse.ui.part.ViewPart;
import org.ow2.aspirerfid.ide.jmx.Activator;
import org.ow2.aspirerfid.ide.jmx.ConfigurationManager;
import org.ow2.aspirerfid.ide.jmx.preferences.PreferenceConstants;



/**
 * @author Nektarios Leontiadis (nele@ait.edu.gr)
 *
 */
public class ConfigurationView extends ViewPart {

    private Combo readerClassCb;
    private Combo existingEdgeTriggersCb;
    private Text ioValueTriggerTf;
    private Combo existingValueTriggersCb;
    private Text ioEdgeTriggerTf;
    private Text selectedSourceReadpointTf;
    private Combo availableReadpointsCb;
    private Text newSourceNameTf;
    private Combo sourcesCb;
    private Text newReadPointTf;
    private Combo readPointsCb;
    private Combo readersCb;
    private Text readerPropertiesTf;
    private Text readerNameTf;
    private Button httpConnectionCk;
    private Button tcpConnectionCk;
    private Text readTimeoutTf;
    private Text maxReadDutyCyclesTf;
    private Text readCyclesPertriggerTf;
    private Text lostTimeoutTf;
    private Text observedTimeoutTf;
    private Text observedThresholdTf;
    private Text glimpsedTimeoutTf;
    private Text threadPoolSizeTf;
    private Text notificationTimeoutTf;
    private Text httpPortTf;
    private Text tcpPortTf;
    private Text maxTriggersTf;
    private Text maxTagSelectorTf;
    private Text maxSourcesTf;
    private Text roleTf;
    private Text modelTf;
    private Text manufacturerDescTf;
    private Text manufacturerTf;
    private Text epcTf;
    private Text nameTf;
    public static final String ID = "org.ow2.aspirerfid.ide.jmx.views.ConfigurationView"; //$NON-NLS-1$
    private Button selectedSourceFixed;
    private Button isFixedCk;
    Composite composite;

    public ConfigurationView() {
    }

    @Override
    public void createPartControl(Composite parent) {
	composite = parent;
	final FillLayout fillLayout = new FillLayout();

	fillLayout.type = SWT.VERTICAL;
	parent.setLayout(fillLayout);
	final TableWrapLayout tableWrapLayout = new TableWrapLayout();
	tableWrapLayout.numColumns = 3;
	final ExpandBar expandBar = new ExpandBar(parent, SWT.V_SCROLL);
	
	final Composite sourcesComposite = new Composite(expandBar, SWT.NONE);
	final Composite triggersComposite = new Composite(expandBar, SWT.NONE);
	final Group addSourceGroup = new Group(sourcesComposite, SWT.NONE);
	addSourceGroup.setText("Add source");
	addSourceGroup.setBounds(15, 120, 701, 146);

	final Label sourceNameLabel = new Label(addSourceGroup, SWT.NONE);
	sourceNameLabel.setText("Source name");
	sourceNameLabel.setBounds(10, 34, 90, 20);

	newSourceNameTf = new Text(addSourceGroup, SWT.BORDER);
	newSourceNameTf.setBounds(10, 67, 213, 25);

	final Label availableReadPointsLabel = new Label(addSourceGroup, SWT.NONE);
	availableReadPointsLabel.setText("Available read points");
	availableReadPointsLabel.setBounds(250, 34, 135, 20);

	availableReadpointsCb = new Combo(addSourceGroup, SWT.READ_ONLY);
	availableReadpointsCb.setBounds(250, 65, 244, 29);

	isFixedCk = new Button(addSourceGroup, SWT.CHECK);
	isFixedCk.setText("Is fixed?");
	isFixedCk.setBounds(528, 67, 75, 25);

	final Button addSourceButton = new Button(addSourceGroup, SWT.NONE);
	addSourceButton.addSelectionListener(new AddSourceButtonSelectionListener());
	addSourceButton.setText("Add");
	addSourceButton.setBounds(275, 105, 54, 29);

	final Group currentSourceGroup = new Group(sourcesComposite, SWT.NONE);
	currentSourceGroup.setText("Current source");
	currentSourceGroup.setBounds(15, 0, 701, 100);

	final Label currentSourceLabel = new Label(currentSourceGroup, SWT.NONE);
	currentSourceLabel.setBounds(10, 27,95, 20);
	currentSourceLabel.setText("Active source");

	sourcesCb = new Combo(currentSourceGroup, SWT.READ_ONLY);
	sourcesCb.addSelectionListener(new SourcesCbSelectionListener());
	sourcesCb.setBounds(130, 23,301, 29);

	final Label selectedSourceReadLabel = new Label(currentSourceGroup, SWT.NONE);
	selectedSourceReadLabel.setBounds(10, 70,180, 20);
	selectedSourceReadLabel.setText("Selected source read point:");

	selectedSourceReadpointTf = new Text(currentSourceGroup, SWT.BORDER);
	selectedSourceReadpointTf.setBounds(196, 65,235, 25);
	selectedSourceReadpointTf.setEnabled(false);

	final Button updateActiveSourceButton = new Button(currentSourceGroup, SWT.NONE);
	updateActiveSourceButton.addSelectionListener(new UpdateActiveSourceButtonSelectionListener());
	updateActiveSourceButton.setText("Save selection as active");
	updateActiveSourceButton.setBounds(480, 20, 175, 30);

	final Button reloadActiveSourceButton = new Button(currentSourceGroup, SWT.NONE);
	reloadActiveSourceButton.addSelectionListener(new ReloadActiveSourceButtonSelectionListener());
	reloadActiveSourceButton.setText("Reload");
	reloadActiveSourceButton.setBounds(600, 60, 54, 29);

	selectedSourceFixed = new Button(currentSourceGroup, SWT.CHECK);
	selectedSourceFixed.setEnabled(false);
	selectedSourceFixed.setText("is Fixed?");
	selectedSourceFixed.setBounds(437, 68, 110, 22);
	
	
	expandBar.setSpacing(1);
	final ExpandItem readerInfoBar = new ExpandItem(expandBar, SWT.NONE, 0);
	readerInfoBar.setExpanded(true);
	final ExpandItem mainConfigBar = new ExpandItem(expandBar, SWT.NONE, 1);
	mainConfigBar.setText("Main configuration");

	final ExpandItem SourcesConfigBar = new ExpandItem(expandBar, SWT.NONE);
	SourcesConfigBar.setText("Sources");
	final ExpandItem readerConfigBar = new ExpandItem(expandBar, SWT.NONE);
	readerConfigBar.setText("Readers configuration");
	
	final Composite readerConfigComposite = new Composite(expandBar, SWT.NONE);

	final Label readerNameLabel = new Label(readerConfigComposite, SWT.NONE);
	readerNameLabel.setText("Reader name");
	readerNameLabel.setBounds(10, 50, 85, 20);

	final Label readerControllerClassLabel = new Label(readerConfigComposite, SWT.NONE);
	readerControllerClassLabel.setText("Reader controller class name");
	readerControllerClassLabel.setBounds(10, 85, 185, 20);

	final Label readerPropertiesFileLabel = new Label(readerConfigComposite, SWT.NONE);
	readerPropertiesFileLabel.setText("Reader properties file");
	readerPropertiesFileLabel.setBounds(10, 120, 135, 20);

	readerNameTf = new Text(readerConfigComposite, SWT.BORDER);
	readerNameTf.setBounds(229, 45, 446, 25);

	readerClassCb = new Combo(readerConfigComposite, SWT.READ_ONLY);
	readerClassCb.setBounds(229, 80, 446, 30);

	readerPropertiesTf = new Text(readerConfigComposite, SWT.BORDER);
	readerPropertiesTf.setBounds(229, 115, 446, 25);

	final Label existingReadersLabel = new Label(readerConfigComposite, SWT.NONE);
	existingReadersLabel.setText("Existing readers");
	existingReadersLabel.setBounds(10, 15, 105, 20);

	readersCb = new Combo(readerConfigComposite, SWT.READ_ONLY);
	readersCb.addSelectionListener(new ReadersCbSelectionListener());
	readersCb.setBounds(230, 10, 270, 29);

	final Label readPointsLabel = new Label(readerConfigComposite, SWT.NONE);
	readPointsLabel.setText("Read points");
	readPointsLabel.setBounds(10, 155, 75, 20);

	readPointsCb = new Combo(readerConfigComposite, SWT.READ_ONLY);
	readPointsCb.setBounds(230, 150, 270, 29);

	final Button addReadPointButton = new Button(readerConfigComposite, SWT.NONE);
	addReadPointButton.addSelectionListener(new AddReadPointButtonSelectionListener());
	addReadPointButton.setText("Add read point:");
	addReadPointButton.setBounds(40, 190, 110, 30);

	newReadPointTf = new Text(readerConfigComposite, SWT.BORDER);
	newReadPointTf.setBounds(155, 193, 174, 25);

	final Button addReaderButton = new Button(readerConfigComposite, SWT.NONE);
	addReaderButton.addSelectionListener(new AddReaderButtonSelectionListener());
	addReaderButton.setText("Add reader");
	addReaderButton.setBounds(229, 234, 85, 30);

	final Button clearFieldsButton = new Button(readerConfigComposite, SWT.NONE);
	clearFieldsButton.addSelectionListener(new ClearFieldsButtonSelectionListener());
	clearFieldsButton.setText("Clear fields");
	clearFieldsButton.setBounds(351, 234, 85, 30);
	


	final Composite mainConfigComposite = new Composite(expandBar, SWT.NONE);
	mainConfigComposite.setLayout(new FormLayout());

	final Composite mainConfigFormCompo = new Composite(mainConfigComposite, SWT.NONE);
	final TableWrapLayout tableWrapLayout_4 = new TableWrapLayout();
	tableWrapLayout_4.numColumns = 3;
	mainConfigFormCompo.setLayout(tableWrapLayout_4);
	final FormData fd_mainConfigFormCompo = new FormData();
	fd_mainConfigFormCompo.left = new FormAttachment(0, 5);
	fd_mainConfigFormCompo.right = new FormAttachment(100, -5);
	fd_mainConfigFormCompo.bottom = new FormAttachment(0, 200);
	fd_mainConfigFormCompo.top = new FormAttachment(0, 3);
	mainConfigFormCompo.setLayoutData(fd_mainConfigFormCompo);
	final Composite mainConfig1Compo = new Composite(mainConfigFormCompo, SWT.NONE);

	final Composite mainConfig2Compo = new Composite(mainConfigFormCompo, SWT.NONE);
	final TableWrapLayout tableWrapLayout_1 = new TableWrapLayout();
	tableWrapLayout_1.numColumns = 2;
	mainConfig2Compo.setLayout(tableWrapLayout_1);

	final Label readCiclesPerLabel = new Label(mainConfig2Compo, SWT.NONE);
	readCiclesPerLabel.setText("Read cicles per trigger");

	readCyclesPertriggerTf = new Text(mainConfig2Compo, SWT.BORDER);

	final Label maxReadDutyLabel = new Label(mainConfig2Compo, SWT.NONE);
	maxReadDutyLabel.setText("Max read duty cycles");

	maxReadDutyCyclesTf = new Text(mainConfig2Compo, SWT.BORDER);

	final Label maxSourceNumberLabel = new Label(mainConfig2Compo, SWT.NONE);
	maxSourceNumberLabel.setText("Max sources");

	maxSourcesTf = new Text(mainConfig2Compo, SWT.BORDER);

	final Label maxTagSelectorLabel = new Label(mainConfig2Compo, SWT.NONE);
	maxTagSelectorLabel.setText("Max tag selector");

	maxTagSelectorTf = new Text(mainConfig2Compo, SWT.BORDER);

	final Label threadPoolSizeLabel = new Label(mainConfig2Compo, SWT.NONE);
	threadPoolSizeLabel.setText("Thread pool size");

	threadPoolSizeTf = new Text(mainConfig2Compo, SWT.BORDER);

	final Label maxTriggersLabel = new Label(mainConfig2Compo, SWT.NONE);
	maxTriggersLabel.setText("Max triggers");

	maxTriggersTf = new Text(mainConfig2Compo, SWT.BORDER);
	mainConfig2Compo.setTabList(new Control[] { readCyclesPertriggerTf, maxReadDutyCyclesTf, maxSourcesTf, maxTagSelectorTf, threadPoolSizeTf,
		maxTriggersTf });

	final Composite mainConfig3Compo = new Composite(mainConfigFormCompo, SWT.NONE);
	final TableWrapLayout tableWrapLayout_2 = new TableWrapLayout();
	tableWrapLayout_2.numColumns = 2;
	mainConfig3Compo.setLayout(tableWrapLayout_2);

	final Label glimpsedTimeoutLabel = new Label(mainConfig3Compo, SWT.NONE);
	glimpsedTimeoutLabel.setText("Glimpsed timeout");

	glimpsedTimeoutTf = new Text(mainConfig3Compo, SWT.BORDER);

	final Label notificationTimeoutLabel = new Label(mainConfig3Compo, SWT.NONE);
	notificationTimeoutLabel.setText("Notification timeout");

	notificationTimeoutTf = new Text(mainConfig3Compo, SWT.BORDER);

	final Label observedTimeoutLabel = new Label(mainConfig3Compo, SWT.NONE);
	observedTimeoutLabel.setText("Observed timeout");

	observedTimeoutTf = new Text(mainConfig3Compo, SWT.BORDER);

	final Label lostTimeoutLabel = new Label(mainConfig3Compo, SWT.NONE);
	lostTimeoutLabel.setText("Lost timeout");

	lostTimeoutTf = new Text(mainConfig3Compo, SWT.BORDER);

	final Label readTimeoutLabel = new Label(mainConfig3Compo, SWT.NONE);
	readTimeoutLabel.setText("Read timeout");

	readTimeoutTf = new Text(mainConfig3Compo, SWT.BORDER);

	final Label observedThresholdLabel = new Label(mainConfig3Compo, SWT.NONE);
	observedThresholdLabel.setText("Observed threshold");

	observedThresholdTf = new Text(mainConfig3Compo, SWT.BORDER);

	final TableWrapLayout tableWrapLayout_3 = new TableWrapLayout();
	tableWrapLayout_3.numColumns = 2;
	mainConfig1Compo.setLayout(tableWrapLayout_3);

	final Label tcpConectionEnabledLabel = new Label(mainConfig1Compo, SWT.NONE);
	tcpConectionEnabledLabel.setText("TCP connection");

	tcpConnectionCk = new Button(mainConfig1Compo, SWT.CHECK);
	tcpConnectionCk.setText("Is enabled?");

	final Label tcpPortLabel = new Label(mainConfig1Compo, SWT.NONE);
	tcpPortLabel.setText("TCP port");

	tcpPortTf = new Text(mainConfig1Compo, SWT.BORDER);

	final Label httpConnectionEnabledLabel = new Label(mainConfig1Compo, SWT.NONE);
	httpConnectionEnabledLabel.setText("HTTP connection");

	httpConnectionCk = new Button(mainConfig1Compo, SWT.CHECK);
	httpConnectionCk.setText("Is enabled?");

	final Label httpPortLabel = new Label(mainConfig1Compo, SWT.NONE);
	httpPortLabel.setText("HTTP port");

	httpPortTf = new Text(mainConfig1Compo, SWT.BORDER);
	final TableWrapData twd_httpPortTf = new TableWrapData(TableWrapData.LEFT, TableWrapData.TOP);
	twd_httpPortTf.maxWidth = 90;
	httpPortTf.setLayoutData(twd_httpPortTf);

	final Composite mainConfigButtonsCompo = new Composite(mainConfigComposite, SWT.NONE);
	final FormData fd_mainConfigButtonsCompo = new FormData();
	fd_mainConfigButtonsCompo.bottom = new FormAttachment(0, 238);
	fd_mainConfigButtonsCompo.top = new FormAttachment(0, 203);
	fd_mainConfigButtonsCompo.right = new FormAttachment(0, 692);
	fd_mainConfigButtonsCompo.left = new FormAttachment(0, 34);
	mainConfigButtonsCompo.setLayoutData(fd_mainConfigButtonsCompo);
	final RowLayout rowLayout_1 = new RowLayout();
	rowLayout_1.justify = true;
	mainConfigButtonsCompo.setLayout(rowLayout_1);

	final Button saveMainConfigBt = new Button(mainConfigButtonsCompo, SWT.NONE);
	saveMainConfigBt.addSelectionListener(new SaveMainConfigBtSelectionListener());
	saveMainConfigBt.setText("Save");

	final Button resetMainConfigBt = new Button(mainConfigButtonsCompo, SWT.NONE);
	resetMainConfigBt.addSelectionListener(new ResetMainConfigBtSelectionListener());
	resetMainConfigBt.setText("Reset");

	final Composite readerSettingsComposite = new Composite(expandBar, SWT.NONE);

	readerSettingsComposite.setBounds(10,10,706,263);

	final Label label = new Label(readerSettingsComposite, SWT.NONE);
	label.setBounds(10, 10,0, 17);

	final Label label_1 = new Label(readerSettingsComposite, SWT.NONE);
	label_1.setBounds(224, 10,0, 17);

	final Label readerEpcLabel = new Label(readerSettingsComposite, SWT.NONE);
	readerEpcLabel.setBounds(10, 38,87, 17);
	readerEpcLabel.setText("Reader Name");

	nameTf = new Text(readerSettingsComposite, SWT.BORDER);
	nameTf.setBounds(224, 33,370, 27);
	nameTf.setRedraw(true);

	final Label readerEpcLabel_1 = new Label(readerSettingsComposite, SWT.NONE);
	readerEpcLabel_1.setBounds(10, 70,73, 17);
	readerEpcLabel_1.setText("Reader EPC");

	epcTf = new Text(readerSettingsComposite, SWT.BORDER);
	epcTf.setBounds(224, 65,370, 27);
	epcTf.setRedraw(true);

	final Label readerEpcLabel_2 = new Label(readerSettingsComposite, SWT.NONE);
	readerEpcLabel_2.setBounds(10, 102,135, 17);
	readerEpcLabel_2.setText("Reader manufacturer");

	manufacturerTf = new Text(readerSettingsComposite, SWT.BORDER);
	manufacturerTf.setBounds(224, 97,370, 27);
	manufacturerTf.setRedraw(true);

	final Label readerEpcLabel_2_1 = new Label(readerSettingsComposite, SWT.NONE);
	readerEpcLabel_2_1.setBounds(10, 134,209, 17);
	readerEpcLabel_2_1.setText("Reader manufacturer description");

	manufacturerDescTf = new Text(readerSettingsComposite, SWT.BORDER);
	manufacturerDescTf.setBounds(224, 129,370, 27);
	manufacturerDescTf.setRedraw(true);

	final Label readerEpcLabel_2_2 = new Label(readerSettingsComposite, SWT.NONE);
	readerEpcLabel_2_2.setBounds(10, 166,88, 17);
	readerEpcLabel_2_2.setText("Reader model");

	modelTf = new Text(readerSettingsComposite, SWT.BORDER);
	modelTf.setBounds(224, 161,370, 27);
	modelTf.setRedraw(true);

	final Label readerEpcLabel_2_3 = new Label(readerSettingsComposite, SWT.NONE);
	readerEpcLabel_2_3.setBounds(10, 198,72, 17);
	readerEpcLabel_2_3.setText("Reader role");

	roleTf = new Text(readerSettingsComposite, SWT.BORDER);
	roleTf.setBounds(224, 193,370, 27);
	roleTf.setRedraw(true);

	final Button updateMainBt = new Button(readerSettingsComposite, SWT.NONE);
	updateMainBt.setBounds(304, 226,117, 29);
	updateMainBt.addSelectionListener(new UpdateMainBtSelectionListener());
	updateMainBt.setText("Update selected");
	readerSettingsComposite.setSize(706, 544);
	// readerInfoScroll.setContent(readerSettingsComposite);

	triggersComposite.setLayout(new ColumnLayout());

	final Group ioEdgeTriggersGroup = new Group(triggersComposite, SWT.NONE);
	ioEdgeTriggersGroup.setText("IO Edge triggers");

	final Label existingTriggersLabel = new Label(ioEdgeTriggersGroup, SWT.NONE);
	existingTriggersLabel.setBounds(7, 22, 102, 17);
	existingTriggersLabel.setText("Existing triggers");

	existingEdgeTriggersCb = new Combo(ioEdgeTriggersGroup, SWT.READ_ONLY);
	existingEdgeTriggersCb.setBounds(114, 22, 231, 29);

	final Button addIOEdgeTriggerButton = new Button(ioEdgeTriggersGroup, SWT.NONE);
	addIOEdgeTriggerButton.addSelectionListener(new AddIOEdgeTriggerButtonSelectionListener());
	addIOEdgeTriggerButton.setBounds(10, 55, 120, 30);
	addIOEdgeTriggerButton.setText("Add trigger class");

	ioEdgeTriggerTf = new Text(ioEdgeTriggersGroup, SWT.BORDER);
	ioEdgeTriggerTf.setBounds(114, 56, 231, 27);
	ioEdgeTriggerTf.setText("                                               ");

	final Group ioValueTriggersGroup = new Group(triggersComposite, SWT.NONE);
	ioValueTriggersGroup.setText("IO Value triggers");

	final Label existingTriggersLabel_1 = new Label(ioValueTriggersGroup, SWT.NONE);
	existingTriggersLabel_1.setBounds(7, 22, 102, 17);
	existingTriggersLabel_1.setText("Existing triggers");

	existingValueTriggersCb = new Combo(ioValueTriggersGroup, SWT.READ_ONLY);
	existingValueTriggersCb.setBounds(114, 22, 231, 27);

	final Button addIOValueTriggerButton = new Button(ioValueTriggersGroup, SWT.NONE);
	addIOValueTriggerButton.addSelectionListener(new AddIOValueTriggerButtonSelectionListener());
	addIOValueTriggerButton.setBounds(40, 50, 37, 29);
	addIOValueTriggerButton.setText("Add");

	ioValueTriggerTf = new Text(ioValueTriggersGroup, SWT.BORDER);
	ioValueTriggerTf.setBounds(114, 54, 231, 27);
	
	readerInfoBar.setText("Reader information");
	readerInfoBar.setControl(readerSettingsComposite);

	readerInfoBar.setHeight(readerSettingsComposite.computeSize(SWT.DEFAULT, SWT.DEFAULT).y + 10);

	mainConfigBar.setControl(mainConfigComposite);
	mainConfigBar.setHeight(mainConfigComposite.computeSize(SWT.DEFAULT, SWT.DEFAULT).y);

	readerConfigBar.setControl(readerConfigComposite);
	readerConfigBar.setHeight(readerConfigComposite.computeSize(SWT.DEFAULT, SWT.DEFAULT).y + 10);
	
	SourcesConfigBar.setControl(sourcesComposite);
	SourcesConfigBar.setHeight(sourcesComposite.computeSize(SWT.DEFAULT, SWT.DEFAULT).y + 10);
	
//	final ExpandItem triggersConfigBar = new ExpandItem(expandBar, SWT.NONE);
//	triggersConfigBar.setExpanded(false);
//	triggersConfigBar.setText("Triggers");
//	triggersConfigBar.setControl(triggersComposite);
//	triggersConfigBar.setHeight(triggersComposite.computeSize(SWT.DEFAULT, SWT.DEFAULT).y + 10);
	
	
	
	initializeToolBar();
	// TODO Auto-generated method stub

    }

    @Override
    public void setFocus() {
	try {
	    loadReaderInfo();
	    loadMainConfig();
	    loadReaders();
	    loadSources();
	    loadTriggers();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    private void initializeToolBar() {
	IToolBarManager toolBarManager = getViewSite().getActionBars().getToolBarManager();
    }

    private class UpdateMainBtSelectionListener extends SelectionAdapter {
	public void widgetSelected(final SelectionEvent e) {
	    ConfigurationManager m = null;
	    try {
		m = ConfigurationManager.getInstance();
	    
		m.setRole(roleTf.getText().trim());
		m.setModel(modelTf.getText().trim());
		m.setManufacturerDescription(manufacturerDescTf.getText().trim());
		m.setManufacturer(manufacturerTf.getText().trim());
		m.setEPC(epcTf.getText().trim());
		m.setName(nameTf.getText().trim());
	    } catch (Exception ex) {
		MessageDialog.openError(composite.getShell(), "Error", "Could not save changes:"+ex.getLocalizedMessage());
	    }
	}
    }

    private class SaveMainConfigBtSelectionListener extends SelectionAdapter {
	public void widgetSelected(final SelectionEvent e) {
	    try {
		saveMainConfigSettings();
	    } catch (Exception e1) {
		MessageDialog.openError(composite.getShell(), "Error", "Could not save changes:"+e1.getLocalizedMessage());
	    }
	}
    }

    private class ResetMainConfigBtSelectionListener extends SelectionAdapter {
	public void widgetSelected(final SelectionEvent e) {
	    try {
		loadMainConfig();
	    } catch (Exception e1) {
		MessageDialog.openError(composite.getShell(), "Error", "Could not load default values:"+e1.getLocalizedMessage());
	    }
	}
    }

    private class ReadersCbSelectionListener extends SelectionAdapter {
	public void widgetSelected(final SelectionEvent e) {
	    String reader = readersCb.getText();
	    clearReaderFields();
	    if (!reader.equals("NEW")) {
		try {
		    fillReaderParams(reader);
		    manageReaderFieldsEditing(false);
		} catch (Exception e1) {
			MessageDialog.openError(composite.getShell(), "Error", "Could not load reader params:"+e1.getLocalizedMessage());
		}
	    }
	    else
	    {
		manageReaderFieldsEditing(true);
	    }
	}
    }

    private class AddReaderButtonSelectionListener extends SelectionAdapter {
	public void widgetSelected(final SelectionEvent e) {
	    try {
		addReader();
		clearReaderFields();
		loadReaders();
		manageReaderFieldsEditing();
	    } catch (Exception e1) {
		MessageDialog.openError(composite.getShell(), "Error", "Could not add new reader:"+e1.getLocalizedMessage());
	    }
	}
    }

    private class ClearFieldsButtonSelectionListener extends SelectionAdapter {
	public void widgetSelected(final SelectionEvent e) {
	    clearReaderFields();
	    readersCb.select(0);
	    manageReaderFieldsEditing();
	}
    }

    private class AddReadPointButtonSelectionListener extends SelectionAdapter {
	public void widgetSelected(final SelectionEvent e) {
	    String reader = readerNameTf.getText().trim();
	    String readpoint = newReadPointTf.getText().trim();
	    if (!reader.equals("") && !readpoint.equals("")) {
		try {
		    addReadPoint(reader, readpoint);
		    newReadPointTf.setText("");
		    fillReaderParams(reader);
		} catch (Exception e1) {
			MessageDialog.openError(composite.getShell(), "Error", "Could not add new read point:"+e1.getLocalizedMessage());
		}
	    }
	}
    }
    private class UpdateActiveSourceButtonSelectionListener extends SelectionAdapter {
    	public void widgetSelected(final SelectionEvent e) {
    	    try {
		ConfigurationManager.getInstance().setCurrentSource(sourcesCb.getText());
	    } catch (Exception e1) {
		MessageDialog.openError(composite.getShell(), "Error", "Could not update:"+e1.getLocalizedMessage());
	    }
    	}
    }
    private class ReloadActiveSourceButtonSelectionListener extends SelectionAdapter {
    	public void widgetSelected(final SelectionEvent e) {
    	    try {
		loadSources();
	    } catch (Exception e1) {
		MessageDialog.openError(composite.getShell(), "Error", "Could not reload:"+e1.getLocalizedMessage());
	    }
    	}
    }
    private class AddSourceButtonSelectionListener extends SelectionAdapter {
    	public void widgetSelected(final SelectionEvent e) {
    	    String source, readpoint;
    	    boolean fixed;
    	    try {
		ConfigurationManager m = ConfigurationManager.getInstance();
		
		source = newSourceNameTf.getText().trim();
		if(m.getSourceReadpoint(source) == null)
		{
		    readpoint = availableReadpointsCb.getText();
		    fixed = isFixedCk.getSelection();
		    m.addSource(source, fixed, readpoint);
		    
		    newSourceNameTf.setText("");
		    isFixedCk.setSelection(false);
		    loadSources();
		}
	    } catch (Exception e1) {
		MessageDialog.openError(composite.getShell(), "Error", "Could not add new source:"+e1.getLocalizedMessage());
	    }
    	}
    }
    private class SourcesCbSelectionListener extends SelectionAdapter {
    	public void widgetSelected(final SelectionEvent e) {
    	    String selectedSource = sourcesCb.getText(); 
    		try {
		    selectedSourceReadpointTf.setText(ConfigurationManager.getInstance().getSourceReadpoint(selectedSource));
		} catch (Exception e1) {
		    e1.printStackTrace();
		}
    	}
    }
    private class AddIOEdgeTriggerButtonSelectionListener extends SelectionAdapter {
    	public void widgetSelected(final SelectionEvent e) {
    	    
    	    try {
		ConfigurationManager m = ConfigurationManager.getInstance();
		
		String edges[], newEdge;
		
		edges = m.getIOEdgeTriggerPortManager();
		newEdge = ioEdgeTriggerTf.getText().trim();
		
		for(int i=0; i<edges.length; i++)
		{
		    if(edges[i].equals(newEdge))
			return;
		}
		
		m.addIOEdgeTriggerPortManager(newEdge);
	    } catch (Exception e1) {
		MessageDialog.openError(composite.getShell(), "Error", "Could not add the new value:"+e1.getLocalizedMessage());
	    }
    	}
    }
    private class AddIOValueTriggerButtonSelectionListener extends SelectionAdapter {
    	public void widgetSelected(final SelectionEvent e) {
    	try {
		ConfigurationManager m = ConfigurationManager.getInstance();
		
		String values[], newValue;
		
		values = m.getIOEdgeTriggerPortManager();
		newValue = ioEdgeTriggerTf.getText().trim();
		
		for(int i=0; i<values.length; i++)
		{
		    if(values[i].equals(newValue))
			return;
		}
		
		m.addIOValueTriggerPortManager(newValue);
	    } catch (Exception e1) {
		MessageDialog.openError(composite.getShell(), "Error", "Could not add the new value:"+e1.getLocalizedMessage());
	    }
    	}
    }

    private void addReadPoint(String readerName, String readpoint) throws Exception {
	String readPoints[];
	ConfigurationManager m = ConfigurationManager.getInstance();

	readPoints = m.getReadPointNames(readerName);

	// Check if the reader exists
	if (readPoints == null)
	    return;

	// Check is it exists
	for (int i = 0; i < readPoints.length; i++)
	    if (readPoints[i].equalsIgnoreCase(readpoint))
		return;

	m.addReaderReadpoint(readerName, readpoint);
    }

    private void clearReaderFields() {
	final String EMPTY = "";
	readerNameTf.setText(EMPTY);
	readerPropertiesTf.setText(EMPTY);
	readPointsCb.removeAll();
	newReadPointTf.setText(EMPTY);
    }

    private void loadReaderInfo() throws Exception {
	ConfigurationManager m = ConfigurationManager.getInstance();
	nameTf.setText(m.getName());
	epcTf.setText(m.getEPC());
	manufacturerTf.setText(m.getManufacturer());
	manufacturerDescTf.setText(m.getManufacturerDescription());
	modelTf.setText(m.getModel());
	roleTf.setText(m.getRole());
    }
    
    private void loadSources() throws Exception 
    {
	String source, sources[], readers[], readpoint[];
	ConfigurationManager m = ConfigurationManager.getInstance();
	HashSet <String> rps = new HashSet<String>();
	sourcesCb.removeAll();
	
	source  = m.getCurrentSource();
	sources = m.getSources();
	
	for(int i=0; i<sources.length; i++)
	{
	    sourcesCb.add(sources[i]);
	    if(sources[i].equals(source))
		sourcesCb.select(i);
	}
	selectedSourceReadpointTf.setText(m.getSourceReadpoint(source));
	selectedSourceFixed.setSelection(m.getIsSourceFixed(source));
	
	readers = m.getReaders();
	for(int i=0; i<readers.length; i++)
	{
	    readpoint = m.getReadPointNames(readers[i]);
	    for(int j=0; j<readpoint.length; j++)
	    {
		if(!rps.contains(readpoint[j]))
		    rps.add(readpoint[j]);
	    }
	}
	availableReadpointsCb.removeAll();
	for(String rp: rps)
	{
	    availableReadpointsCb.add(rp);
	}
	if(rps.size()>0)
	    availableReadpointsCb.select(0);
    }

    private void saveMainConfigSettings() throws Exception {
	ConfigurationManager m = ConfigurationManager.getInstance();

	m.setMaxSourceNumber(Integer.parseInt(maxSourcesTf.getText()));
	m.setMaxTagSelectorNumber(Integer.parseInt(maxTagSelectorTf.getText()));
	m.setMaxTriggerNumber(Integer.parseInt(maxTriggersTf.getText()));
	m.setReadCyclesPerTrigger(Integer.parseInt(readCyclesPertriggerTf.getText()));
	m.setMaxReadDutyCycles(Integer.parseInt(maxReadDutyCyclesTf.getText()));
	m.setReadTimeout(Long.parseLong(readTimeoutTf.getText()));
	m.setThreadPoolSize(Integer.parseInt(threadPoolSizeTf.getText()));

	m.setHttpServerConnectionEnabled(httpConnectionCk.getSelection());
	m.setHttpPort(Integer.parseInt(httpPortTf.getText()));

	m.setTcpServerConnection(tcpConnectionCk.getSelection());
	m.setTcpPort(Integer.parseInt(tcpPortTf.getText()));

	m.setNotificationListenTimeout(Long.parseLong(notificationTimeoutTf.getText()));
	m.setObservedThreshold(Long.parseLong(observedThresholdTf.getText()));
	m.setObservedTimeout(Long.parseLong(observedTimeoutTf.getText()));
	m.setLostTimeout(Long.parseLong(lostTimeoutTf.getText()));
	m.setGlimpsedTimeout(Long.parseLong(glimpsedTimeoutTf.getText()));
    }

    private void loadMainConfig() throws Exception {
	ConfigurationManager m = ConfigurationManager.getInstance();

	maxSourcesTf.setText(String.valueOf(m.getMaxSourceNumber()));
	maxTagSelectorTf.setText(String.valueOf(m.getMaxTagSelectorNumber()));
	maxTriggersTf.setText(String.valueOf(m.getMaxTriggerNumber()));
	readCyclesPertriggerTf.setText(String.valueOf(m.getReadCyclesPerTrigger()));
	maxReadDutyCyclesTf.setText(String.valueOf(m.getMaxReadDutyCycles()));
	readTimeoutTf.setText(String.valueOf(m.getReadTimeout()));
	threadPoolSizeTf.setText(String.valueOf(m.getThreadPoolSize()));

	httpConnectionCk.setSelection(m.getHttpServerConnectionEnabled());
	httpPortTf.setText(String.valueOf(m.getHttpPort()));

	tcpConnectionCk.setSelection(m.getTcpServerConnection());
	tcpPortTf.setText(String.valueOf(m.getTcpPort()));

	notificationTimeoutTf.setText(String.valueOf(m.getNotificationListenTimeout()));
	observedThresholdTf.setText(String.valueOf(m.getObservedThreshold()));
	observedTimeoutTf.setText(String.valueOf(m.getObservedTimeout()));
	lostTimeoutTf.setText(String.valueOf(m.getLostTimeout()));
	glimpsedTimeoutTf.setText(String.valueOf(m.getGlimpsedTimeout()));

	// sourceFixedCk.setSelection(m.getIsSourceFixed(arg0));

    }

    private void loadReaders() throws Exception {
	readersCb.removeAll();
	readersCb.add("NEW");
	String readers[] = ConfigurationManager.getInstance().getReaders();
	for (int i = 0; i < readers.length; i++) {
	    readersCb.add(readers[i]);
	}
	String classesPrefs = Activator.getDefault().getPreferenceStore().getString(PreferenceConstants.READER_CLASSES);
	String classes [] = classesPrefs.split(",");
	readerClassCb.removeAll();
	for(int i=0; i< classes.length; i++)
	{
	    readerClassCb.add(classes[i]);
	}
    }
    
    private void loadTriggers() throws Exception
    {
	ConfigurationManager m = ConfigurationManager.getInstance();
	
	String [] values, edges;
	
	values = m.getIOValueTriggerPortManager();
	edges = m.getIOEdgeTriggerPortManager();
	
	existingValueTriggersCb.removeAll();
	for(int i=0; i<values.length; i++)
	{
	    existingValueTriggersCb.add(values[i]);
	}
	existingEdgeTriggersCb.removeAll();
	for(int i=0; i<edges.length; i++)
	{
	    existingEdgeTriggersCb.add(edges[i]);
	}
	
	if(values.length>0)
	    existingValueTriggersCb.select(0);
	if(edges.length>0)
	    existingEdgeTriggersCb.select(0);
    }

    private void fillReaderParams(String readerName) throws Exception {
	ConfigurationManager m = ConfigurationManager.getInstance();

	readerNameTf.setText(readerName);
	readerClassCb.setText(m.getReaderClassName(readerName));
	readerPropertiesTf.setText(m.getReaderPropertiesFile(readerName));

	readPointsCb.removeAll();

	String[] rps = m.getReadPointNames(readerName);
	if (rps != null) {
	    for (int i = 0; i < rps.length; i++) {
		readPointsCb.add(rps[i]);
	    }
	}
    }

    private void addReader() throws Exception{
	final String EMPTY="";
	String name, clas, props;
	if(readersCb.getSelectionIndex() == 0)
	{
	    name = readerNameTf.getText().trim();
	    clas = readerClassCb.getText();
	    props = readerPropertiesTf.getText().trim();
	    if(!name.equals(EMPTY) && !clas.equals(EMPTY) && !props.equals(EMPTY))
	    {
		ConfigurationManager.getInstance().addReader(name, clas, props);
	    }
	    loadSources();
	}
    }

    private void manageReaderFieldsEditing(boolean editable) {
	readerNameTf.setEnabled(editable);
	readerClassCb.setEnabled(editable);
	readerPropertiesTf.setEnabled(editable);
	newReadPointTf.setEnabled(!editable);
    }
    
    private void manageReaderFieldsEditing()
    {
	manageReaderFieldsEditing(readersCb.getText().equals("NEW"));
    }
}
