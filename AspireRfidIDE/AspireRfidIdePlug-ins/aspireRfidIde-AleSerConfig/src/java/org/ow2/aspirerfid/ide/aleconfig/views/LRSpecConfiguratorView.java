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

package org.ow2.aspirerfid.ide.aleconfig.views;

import java.io.BufferedWriter;
import java.io.CharArrayWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.accada.ale.util.DeserializerUtil;
import org.accada.ale.util.SerializerUtil;
import org.accada.ale.wsdl.alelr.epcglobal.ALELRServicePortType;
import org.accada.ale.wsdl.alelr.epcglobal.AddReaders;
import org.accada.ale.wsdl.alelr.epcglobal.GetLRSpec;
import org.accada.ale.wsdl.alelr.epcglobal.GetPropertyValue;
import org.accada.ale.wsdl.alelr.epcglobal.ImmutableReaderExceptionResponse;
import org.accada.ale.wsdl.alelr.epcglobal.InUseExceptionResponse;
import org.accada.ale.wsdl.alelr.epcglobal.NonCompositeReaderExceptionResponse;
import org.accada.ale.wsdl.alelr.epcglobal.ReaderLoopExceptionResponse;
import org.accada.ale.wsdl.alelr.epcglobal.RemoveReaders;
import org.accada.ale.wsdl.alelr.epcglobal.SetProperties;
import org.accada.ale.wsdl.alelr.epcglobal.SetReaders;
import org.accada.ale.wsdl.alelr.epcglobal.ValidationExceptionResponse;
import org.accada.ale.xsd.ale.epcglobal.ECSpec;
import org.accada.ale.xsd.ale.epcglobal.LRProperty;
import org.accada.ale.xsd.ale.epcglobal.LRSpec;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.preference.IPreferenceStore;
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
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;
import org.eclipse.swt.widgets.TreeItem;
import org.eclipse.ui.console.ConsolePlugin;
import org.eclipse.ui.console.IConsole;
import org.eclipse.ui.console.IConsoleManager;
import org.eclipse.ui.console.IOConsole;
import org.eclipse.ui.console.IOConsoleOutputStream;
import org.eclipse.ui.part.ViewPart;
import org.ow2.aspirerfid.ide.aleconfig.Activator;
import org.ow2.aspirerfid.ide.aleconfig.preferences.PreferenceConstants;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.ow2.aspirerfid.ide.aleconfig.dialogs.*;
import org.ow2.aspirerfid.ide.aleconfig.utils.*;
import org.ow2.aspirerfid.ide.aleconfig.utils.swtdesigner.ResourceManager;
import org.ow2.aspirerfid.ide.aleconfig.utils.swtdesigner.SWTResourceManager;

/**
 * @author Vasso Koletti e-mail: vkol@ait.edu.gr
 * @author Nikos Kefalakis (nkef) e-mail: nkef@ait.edu.gr
 * 
 */
public class LRSpecConfiguratorView extends ViewPart {
	private Combo lrDefineReaderNameCombo;
	private Composite lrspecConfiguratorComposite;
	private Combo setPropertiesReaderNameCombo;
	private Combo getPropertyValuePropertyNameCombo;
	private Combo getPropertyValueReaderNameCombo;
	private org.eclipse.swt.widgets.List removeReadersReadersToBeRemovedList;
	private Combo removeReadersSelectReaderCombo;
	private Combo removeReadersReaderNameCombo;
	private org.eclipse.swt.widgets.List setReadersNewReadersList;
	private Combo setReadersSelectReaderCombo;
	private Combo setReadersReaderNameCombo;
	private org.eclipse.swt.widgets.List addReadersReadersToBeAddedList;
	private Combo addReadersSelectReaderCombo;
	private Combo addReadersReaderNameCombo;
	private Combo lrGetLRSpecReaderNameCombo;
	private Combo lrUpdateSelectLRSpecCombo;
	private Combo lrUpdateReaderNameCombo;
	private Combo lrUndefineReaderNameCombo;
	private Combo lrDefineSelectLRSpecCombo;
	private Composite defineLRSpecComposite;
	private Composite undefineLRSpecComposite;
	private Composite updateLRSpecComposite;
	private Composite getLRSpecComposite;
	private Composite addReadersComposite;
	private Composite setReadersComposite;
	private Composite removeReadersComposite;
	private Composite getPropertyValueComposite;
	private Composite setPropertiesComposite;
	private Group alelrMethodGroup;

	private int addReadersReadersToBeAddedListSelectedIndex;
	private int setReadersNewReadersListSelectedIndex;
	private int removeReadersReadersToBeRemovedListSelectedIndex;
	private int getPropertyValueReaderNameComboSelectedIndex;
	private String getPropertyValueReaderNameComboSelectedIndexName;
	private int setPropertiesReaderNameComboSelectedIndex;
	private String setPropertiesReaderNameComboSelectedIndexName;

	private ArrayList<String> halReaderSetpropertiesInputValues;
	private ArrayList<String> rpReaderSetpropertiesInputValues;
	private ArrayList<String> llrpReaderSetpropertiesInputValues;

	private ECSpecBuilder openEcSpecBuilder;

	public static final String ID = "org.ow2.aspirerfid.ide.aleserverconfigurator.views.LRSpecConfiguratorView"; //$NON-NLS-1$

	Composite container;

	/**
	 * ECSpec Configurator Result
	 */
	Object lrSpecConfiguratorResult = null;

	/** ale proxy */
	private ALELRServicePortType alelrProxy = null;

	/** Returns the preference store for this UI plug-in */
	IPreferenceStore lrspecConfigPreferences = Activator.getDefault().getPreferenceStore();
	IPreferenceStore ecspecPreferences = Activator.getDefault().getPreferenceStore();

	// ----------Console Initialization and Use----------------
	IOConsoleOutputStream aleServerConfiguratorOutputConsole;
	IOConsole aleServerConfiguratorConsole;
	IConsoleManager manager;
	IConsole[] consolesx;

	public LRSpecConfiguratorView() {
		// TODO Auto-generated constructor stub

	}

	@Override
	public void createPartControl(Composite parent) {
		container = new Composite(parent, SWT.NONE);
		container.setLayout(new FillLayout());

		final ScrolledComposite lrspecConfiguratorScrolledComposite = new ScrolledComposite(container, SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL);

		lrspecConfiguratorComposite = new Composite(lrspecConfiguratorScrolledComposite, SWT.NONE);
		lrspecConfiguratorComposite.setLocation(0, 0);
		lrspecConfiguratorComposite.setSize(709, 389);

		lrspecConfiguratorScrolledComposite.setContent(lrspecConfiguratorComposite);

		final Group alelrMethodsGroup_1 = new Group(lrspecConfiguratorComposite, SWT.NONE);
		alelrMethodsGroup_1.setText("ALELR Methods");
		alelrMethodsGroup_1.setBounds(10, 0, 680, 354);

		final ToolBar toolBar = new ToolBar(alelrMethodsGroup_1, SWT.VERTICAL | SWT.BORDER);
		toolBar.setBackground(SWTResourceManager.getColor(179, 213, 186));
		toolBar.setBounds(10, 65, 101, 277); // If it doesnt apear set it to
												// (10, 65, 120, 277)

		final ToolItem defineToolItem = new ToolItem(toolBar, SWT.PUSH);
		defineToolItem.addSelectionListener(new DefineToolItemSelectionListener());
		defineToolItem.setImage(ResourceManager.getPluginImage(Activator.getDefault(), "icons/pencil_16.gif"));
		defineToolItem.setText("Define");

		final ToolItem updateToolItem = new ToolItem(toolBar, SWT.PUSH);
		updateToolItem.addSelectionListener(new UpdateToolItemSelectionListener());
		updateToolItem.setImage(ResourceManager.getPluginImage(Activator.getDefault(), "icons/img/b_edit.png"));
		updateToolItem.setText("Update");

		final ToolItem undefineToolItem = new ToolItem(toolBar, SWT.PUSH);
		undefineToolItem.addSelectionListener(new UndefineToolItemSelectionListener());
		undefineToolItem.setImage(ResourceManager.getPluginImage(Activator.getDefault(), "icons/img/b_tblops.png"));
		undefineToolItem.setText("Undefine");

		final ToolItem addReadersToolItem = new ToolItem(toolBar, SWT.PUSH);
		addReadersToolItem.addSelectionListener(new AddReadersToolItemSelectionListener());
		addReadersToolItem.setImage(ResourceManager.getPluginImage(Activator.getDefault(), "icons/img/b_insrow.png"));
		addReadersToolItem.setText("AddReaders");

		final ToolItem setReadersToolItem = new ToolItem(toolBar, SWT.PUSH);
		setReadersToolItem.addSelectionListener(new SetReadersToolItemSelectionListener());
		setReadersToolItem.setImage(ResourceManager.getPluginImage(Activator.getDefault(), "icons/img/b_newdb.png"));
		setReadersToolItem.setText("SetReaders");

		final ToolItem removeReadersToolItem = new ToolItem(toolBar, SWT.PUSH);
		removeReadersToolItem.addSelectionListener(new RemoveReadersToolItemSelectionListener());
		removeReadersToolItem.setImage(ResourceManager.getPluginImage(Activator.getDefault(), "icons/img/bd_drop.png"));
		removeReadersToolItem.setText("RemoveReaders");

		final ToolItem setPropertiesToolItem = new ToolItem(toolBar, SWT.PUSH);
		setPropertiesToolItem.addSelectionListener(new SetPropertiesToolItemSelectionListener());
		setPropertiesToolItem.setImage(ResourceManager.getPluginImage(Activator.getDefault(), "icons/img/b_sqldoc.png"));
		setPropertiesToolItem.setText("SetProperties");

		alelrMethodGroup = new Group(alelrMethodsGroup_1, SWT.NONE);
		alelrMethodGroup.setBounds(121, 71, 550, 271);
		alelrMethodGroup.setVisible(false);

		defineLRSpecComposite = new Composite(alelrMethodGroup, SWT.NONE);
		defineLRSpecComposite.setBounds(10, 16, 530, 105);

		final Label readerNameLabel = new Label(defineLRSpecComposite, SWT.NONE);
		readerNameLabel.setText("Reader name:");
		readerNameLabel.setBounds(5, 5, 88, 21);

		final Label selectLrspecLabel = new Label(defineLRSpecComposite, SWT.NONE);
		selectLrspecLabel.setText("Select LRSpec:");
		selectLrspecLabel.setBounds(5, 35, 88, 21);

		lrDefineSelectLRSpecCombo = new Combo(defineLRSpecComposite, SWT.NONE);
		lrDefineSelectLRSpecCombo.setBackground(SWTResourceManager.getColor(230, 230, 230));
		lrDefineSelectLRSpecCombo.setBounds(96, 32, 424, 24);

		final Label label_8 = new Label(defineLRSpecComposite, SWT.SEPARATOR | SWT.HORIZONTAL);
		label_8.setBounds(5, 60, 515, 10);

		final Button lrDefineExecuteButton = new Button(defineLRSpecComposite, SWT.NONE);
		lrDefineExecuteButton.addMouseListener(new LrDefineExecuteButtonMouseListener());
		lrDefineExecuteButton.setImage(ResourceManager.getPluginImage(Activator.getDefault(), "icons/services_execute.gif"));
		lrDefineExecuteButton.setText("Execute");
		lrDefineExecuteButton.setBounds(215, 75, 80, 24);

		lrDefineReaderNameCombo = new Combo(defineLRSpecComposite, SWT.NONE);
		lrDefineReaderNameCombo.setBackground(SWTResourceManager.getColor(230, 230, 230));
		lrDefineReaderNameCombo.setBounds(96, 2, 424, 24);

		undefineLRSpecComposite = new Composite(alelrMethodGroup, SWT.NONE);
		undefineLRSpecComposite.setBounds(10, 16, 530, 105);
		undefineLRSpecComposite.setVisible(false);

		final Label readerNameLabel_1 = new Label(undefineLRSpecComposite, SWT.NONE);
		readerNameLabel_1.setText("Reader name:");
		readerNameLabel_1.setBounds(5, 5, 88, 21);

		lrUndefineReaderNameCombo = new Combo(undefineLRSpecComposite, SWT.READ_ONLY);
		lrUndefineReaderNameCombo.setBackground(SWTResourceManager.getColor(230, 230, 230));
		lrUndefineReaderNameCombo.setBounds(96, 2, 424, 24);

		final Label label_9 = new Label(undefineLRSpecComposite, SWT.SEPARATOR | SWT.HORIZONTAL);
		label_9.setBounds(5, 60, 515, 10);

		final Button lrUndefineExecuteButton = new Button(undefineLRSpecComposite, SWT.NONE);
		lrUndefineExecuteButton.addMouseListener(new LrUndefineExecuteButtonMouseListener());
		lrUndefineExecuteButton.setImage(ResourceManager.getPluginImage(Activator.getDefault(), "icons/services_execute.gif"));
		lrUndefineExecuteButton.setText("Execute");
		lrUndefineExecuteButton.setBounds(215, 75, 80, 24);

		updateLRSpecComposite = new Composite(alelrMethodGroup, SWT.NONE);
		updateLRSpecComposite.setBounds(10, 16, 530, 105);
		updateLRSpecComposite.setVisible(false);

		final Label readerNameLabel_2 = new Label(updateLRSpecComposite, SWT.NONE);
		readerNameLabel_2.setText("Reader Name:");
		readerNameLabel_2.setBounds(5, 5, 88, 21);

		lrUpdateReaderNameCombo = new Combo(updateLRSpecComposite, SWT.READ_ONLY);
		lrUpdateReaderNameCombo.setBackground(SWTResourceManager.getColor(230, 230, 230));
		lrUpdateReaderNameCombo.setBounds(96, 2, 424, 24);

		final Label label_10 = new Label(updateLRSpecComposite, SWT.NONE);
		label_10.setText("Select LRSpec:");
		label_10.setBounds(5, 35, 88, 21);

		lrUpdateSelectLRSpecCombo = new Combo(updateLRSpecComposite, SWT.READ_ONLY);
		lrUpdateSelectLRSpecCombo.setBackground(SWTResourceManager.getColor(230, 230, 230));
		lrUpdateSelectLRSpecCombo.setBounds(96, 32, 424, 24);

		final Label label_11 = new Label(updateLRSpecComposite, SWT.SEPARATOR | SWT.HORIZONTAL);
		label_11.setBounds(5, 60, 515, 10);

		final Button lrUpdateExecuteButton = new Button(updateLRSpecComposite, SWT.NONE);
		lrUpdateExecuteButton.addMouseListener(new LrUpdateExecuteButtonMouseListener());
		lrUpdateExecuteButton.setImage(ResourceManager.getPluginImage(Activator.getDefault(), "icons/services_execute.gif"));
		lrUpdateExecuteButton.setText("Execute");
		lrUpdateExecuteButton.setBounds(215, 75, 80, 24);

		getLRSpecComposite = new Composite(alelrMethodGroup, SWT.NONE);
		getLRSpecComposite.setBounds(10, 16, 530, 105);

		final Label lrGetLRSpecReaderNameLabel = new Label(getLRSpecComposite, SWT.NONE);
		lrGetLRSpecReaderNameLabel.setText("Reader name:");
		lrGetLRSpecReaderNameLabel.setBounds(5, 5, 88, 21);

		lrGetLRSpecReaderNameCombo = new Combo(getLRSpecComposite, SWT.READ_ONLY);
		lrGetLRSpecReaderNameCombo.setBackground(SWTResourceManager.getColor(230, 230, 230));
		lrGetLRSpecReaderNameCombo.setBounds(96, 2, 424, 24);

		final Label label_12 = new Label(getLRSpecComposite, SWT.SEPARATOR | SWT.HORIZONTAL);
		label_12.setBounds(5, 60, 515, 10);

		final Button lrGetLRSpecExecuteButton = new Button(getLRSpecComposite, SWT.NONE);
		lrGetLRSpecExecuteButton.addMouseListener(new LrGetLRSpecExecuteButtonMouseListener());
		lrGetLRSpecExecuteButton.setImage(ResourceManager.getPluginImage(Activator.getDefault(), "icons/services_execute.gif"));
		lrGetLRSpecExecuteButton.setText("Execute");
		lrGetLRSpecExecuteButton.setBounds(215, 75, 80, 24);

		addReadersComposite = new Composite(alelrMethodGroup, SWT.NONE);
		addReadersComposite.setBounds(10, 16, 530, 206);

		final Label addReadersReaderNameText = new Label(addReadersComposite, SWT.NONE);
		addReadersReaderNameText.setText("Reader name:");
		addReadersReaderNameText.setBounds(5, 5, 88, 21);

		addReadersReaderNameCombo = new Combo(addReadersComposite, SWT.READ_ONLY);
		addReadersReaderNameCombo.setBackground(SWTResourceManager.getColor(230, 230, 230));
		addReadersReaderNameCombo.setBounds(96, 2, 424, 24);

		addReadersSelectReaderCombo = new Combo(addReadersComposite, SWT.READ_ONLY);
		addReadersSelectReaderCombo.setBackground(SWTResourceManager.getColor(230, 230, 230));
		addReadersSelectReaderCombo.setBounds(96, 32, 370, 24);

		final Label selectReaderLabel = new Label(addReadersComposite, SWT.NONE);
		selectReaderLabel.setText("Select reader:");
		selectReaderLabel.setBounds(5, 35, 88, 16);

		final Button addReadersAddReaderButton = new Button(addReadersComposite, SWT.NONE);
		addReadersAddReaderButton.addMouseListener(new AddReadersAddReaderButtonMouseListener());
		addReadersAddReaderButton.setImage(ResourceManager.getPluginImage(Activator.getDefault(), "icons/img/b_plus.png"));
		addReadersAddReaderButton.setText("Add");
		addReadersAddReaderButton.setBounds(472, 32, 49, 26);

		final Label readersToBeLabel = new Label(addReadersComposite, SWT.NONE);
		readersToBeLabel.setForeground(SWTResourceManager.getColor(0, 0, 255));
		readersToBeLabel.setText("Readers to be added");
		readersToBeLabel.setBounds(5, 71, 515, 16);

		final Label label_13 = new Label(addReadersComposite, SWT.SEPARATOR | SWT.HORIZONTAL);
		label_13.setBounds(5, 85, 515, 10);

		addReadersReadersToBeAddedList = new org.eclipse.swt.widgets.List(addReadersComposite, SWT.V_SCROLL | SWT.H_SCROLL | SWT.BORDER);
		addReadersReadersToBeAddedList.setBackground(SWTResourceManager.getColor(230, 230, 230));
		addReadersReadersToBeAddedList.setBounds(5, 100, 120, 47);

		addReadersReadersToBeAddedList.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				org.eclipse.swt.widgets.List list = (org.eclipse.swt.widgets.List) e.getSource();
				addReadersReadersToBeAddedListSelectedIndex = list.getSelectionIndex();
			}
		});

		final Button addReadersDeleteReaderButton = new Button(addReadersComposite, SWT.NONE);
		addReadersDeleteReaderButton.addMouseListener(new AddReadersDeleteReaderButtonMouseListener());
		addReadersDeleteReaderButton.setSelection(true);
		addReadersDeleteReaderButton.setImage(ResourceManager.getPluginImage(Activator.getDefault(), "icons/img/b_minus.png"));
		addReadersDeleteReaderButton.setText("Delete");
		addReadersDeleteReaderButton.setBounds(131, 121, 64, 26);

		final Label label_14 = new Label(addReadersComposite, SWT.SEPARATOR | SWT.HORIZONTAL);
		label_14.setBounds(5, 160, 515, 10);

		final Button addReadersExecuteButton = new Button(addReadersComposite, SWT.NONE);
		addReadersExecuteButton.addMouseListener(new AddReadersExecuteButtonMouseListener());
		addReadersExecuteButton.setImage(ResourceManager.getPluginImage(Activator.getDefault(), "icons/services_execute.gif"));
		addReadersExecuteButton.setText("Execute");
		addReadersExecuteButton.setBounds(215, 175, 80, 24);

		setReadersComposite = new Composite(alelrMethodGroup, SWT.NONE);
		setReadersComposite.setBounds(10, 16, 530, 206);

		final Label label_15 = new Label(setReadersComposite, SWT.NONE);
		label_15.setText("Reader name:");
		label_15.setBounds(5, 5, 88, 21);

		setReadersReaderNameCombo = new Combo(setReadersComposite, SWT.READ_ONLY);
		setReadersReaderNameCombo.setBackground(SWTResourceManager.getColor(230, 230, 230));
		setReadersReaderNameCombo.setBounds(96, 2, 424, 24);

		final Label label_16 = new Label(setReadersComposite, SWT.NONE);
		label_16.setText("Select reader:");
		label_16.setBounds(5, 35, 88, 21);

		setReadersSelectReaderCombo = new Combo(setReadersComposite, SWT.READ_ONLY);
		setReadersSelectReaderCombo.setBackground(SWTResourceManager.getColor(230, 230, 230));
		setReadersSelectReaderCombo.setBounds(96, 32, 370, 24);

		final Button setReadersAddReaderButton = new Button(setReadersComposite, SWT.NONE);
		setReadersAddReaderButton.addMouseListener(new SetReadersAddReaderButtonMouseListener());
		setReadersAddReaderButton.setText("Add");
		setReadersAddReaderButton.setImage(ResourceManager.getPluginImage(Activator.getDefault(), "icons/img/b_plus.png"));
		setReadersAddReaderButton.setBounds(472, 32, 49, 26);

		final Label newReadersLabel = new Label(setReadersComposite, SWT.NONE);
		newReadersLabel.setForeground(SWTResourceManager.getColor(0, 0, 255));
		newReadersLabel.setText("New readers");
		newReadersLabel.setBounds(5, 71, 118, 16);

		final Label label_18 = new Label(setReadersComposite, SWT.SEPARATOR | SWT.HORIZONTAL);
		label_18.setBounds(5, 85, 515, 10);

		setReadersNewReadersList = new org.eclipse.swt.widgets.List(setReadersComposite, SWT.V_SCROLL | SWT.H_SCROLL | SWT.BORDER);
		setReadersNewReadersList.setBackground(SWTResourceManager.getColor(230, 230, 230));
		setReadersNewReadersList.setBounds(5, 100, 120, 47);

		setReadersNewReadersList.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				org.eclipse.swt.widgets.List list = (org.eclipse.swt.widgets.List) e.getSource();
				setReadersNewReadersListSelectedIndex = list.getSelectionIndex();
			}
		});

		final Button setReadersDeleteReaderButton = new Button(setReadersComposite, SWT.NONE);
		setReadersDeleteReaderButton.addMouseListener(new SetReadersDeleteReaderButtonMouseListener());
		setReadersDeleteReaderButton.setImage(ResourceManager.getPluginImage(Activator.getDefault(), "icons/img/b_minus.png"));
		setReadersDeleteReaderButton.setText("Delete");
		setReadersDeleteReaderButton.setBounds(131, 121, 64, 26);

		final Label label_19 = new Label(setReadersComposite, SWT.SEPARATOR | SWT.HORIZONTAL);
		label_19.setBounds(5, 160, 515, 10);

		final Button setReadersExecuteButton = new Button(setReadersComposite, SWT.NONE);
		setReadersExecuteButton.addMouseListener(new SetReadersExecuteButtonMouseListener());
		setReadersExecuteButton.setImage(ResourceManager.getPluginImage(Activator.getDefault(), "icons/services_execute.gif"));
		setReadersExecuteButton.setText("Execute");
		setReadersExecuteButton.setBounds(215, 175, 80, 24);

		removeReadersComposite = new Composite(alelrMethodGroup, SWT.NONE);
		removeReadersComposite.setBounds(10, 16, 530, 206);

		final Label readerNameLabel_3 = new Label(removeReadersComposite, SWT.NONE);
		readerNameLabel_3.setText("Reader name:");
		readerNameLabel_3.setBounds(5, 5, 88, 21);

		removeReadersReaderNameCombo = new Combo(removeReadersComposite, SWT.READ_ONLY);
		removeReadersReaderNameCombo.setBackground(SWTResourceManager.getColor(230, 230, 230));
		removeReadersReaderNameCombo.setBounds(96, 2, 424, 24);

		final Label selectReaderLabel_1 = new Label(removeReadersComposite, SWT.NONE);
		selectReaderLabel_1.setText("Select reader:");
		selectReaderLabel_1.setBounds(5, 35, 88, 21);

		removeReadersSelectReaderCombo = new Combo(removeReadersComposite, SWT.READ_ONLY);
		removeReadersSelectReaderCombo.setBackground(SWTResourceManager.getColor(230, 230, 230));
		removeReadersSelectReaderCombo.setBounds(96, 32, 370, 24);

		final Button removeReadersAddReaderButton = new Button(removeReadersComposite, SWT.NONE);
		removeReadersAddReaderButton.addMouseListener(new RemoveReadersAddReaderButtonMouseListener());
		removeReadersAddReaderButton.setImage(ResourceManager.getPluginImage(Activator.getDefault(), "icons/img/b_plus.png"));
		removeReadersAddReaderButton.setText("Add");
		removeReadersAddReaderButton.setBounds(472, 32, 49, 26);

		final Label readersToBeLabel_1 = new Label(removeReadersComposite, SWT.NONE);
		readersToBeLabel_1.setForeground(SWTResourceManager.getColor(0, 0, 255));
		readersToBeLabel_1.setText("Readers to be removed");
		readersToBeLabel_1.setBounds(5, 71, 129, 16);

		final Label label_20 = new Label(removeReadersComposite, SWT.SEPARATOR | SWT.HORIZONTAL);
		label_20.setBounds(5, 85, 515, 10);

		removeReadersReadersToBeRemovedList = new org.eclipse.swt.widgets.List(removeReadersComposite, SWT.V_SCROLL | SWT.H_SCROLL | SWT.BORDER);
		removeReadersReadersToBeRemovedList.setBackground(SWTResourceManager.getColor(230, 230, 230));
		removeReadersReadersToBeRemovedList.setBounds(5, 100, 120, 47);

		removeReadersReadersToBeRemovedList.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				org.eclipse.swt.widgets.List list = (org.eclipse.swt.widgets.List) e.getSource();
				removeReadersReadersToBeRemovedListSelectedIndex = list.getSelectionIndex();
			}
		});

		final Button removeReadersDeleteReaderButton = new Button(removeReadersComposite, SWT.NONE);
		removeReadersDeleteReaderButton.addMouseListener(new RemoveReadersDeleteReaderButtonMouseListener());
		removeReadersDeleteReaderButton.setImage(ResourceManager.getPluginImage(Activator.getDefault(), "icons/img/b_minus.png"));
		removeReadersDeleteReaderButton.setText("Delete");
		removeReadersDeleteReaderButton.setBounds(131, 121, 64, 26);

		final Label label_21 = new Label(removeReadersComposite, SWT.SEPARATOR | SWT.HORIZONTAL);
		label_21.setBounds(5, 160, 515, 10);

		final Button removeReadersExecuteButton = new Button(removeReadersComposite, SWT.NONE);
		removeReadersExecuteButton.addMouseListener(new RemoveReadersExecuteButtonMouseListener());
		removeReadersExecuteButton.setImage(ResourceManager.getPluginImage(Activator.getDefault(), "icons/services_execute.gif"));
		removeReadersExecuteButton.setText("Execute");
		removeReadersExecuteButton.setBounds(215, 175, 80, 24);

		getPropertyValueComposite = new Composite(alelrMethodGroup, SWT.NONE);
		getPropertyValueComposite.setBounds(10, 16, 530, 105);

		final Label readerNameLabel_4 = new Label(getPropertyValueComposite, SWT.NONE);
		readerNameLabel_4.setText("Reader name:");
		readerNameLabel_4.setBounds(5, 5, 88, 21);

		getPropertyValueReaderNameCombo = new Combo(getPropertyValueComposite, SWT.NONE);
		getPropertyValueReaderNameCombo.setBackground(SWTResourceManager.getColor(230, 230, 230));
		getPropertyValueReaderNameCombo.setBounds(96, 2, 424, 24);

		getPropertyValueReaderNameCombo.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				lrSpecConfiguratorResult = null;

				getPropertyValuePropertyNameCombo.removeAll();

				org.eclipse.swt.widgets.Combo combo = (org.eclipse.swt.widgets.Combo) e.getSource();
				getPropertyValueReaderNameComboSelectedIndex = combo.getSelectionIndex();
				getPropertyValueReaderNameComboSelectedIndexName = combo.getItem(getPropertyValueReaderNameComboSelectedIndex);

				GetLRSpec getLRSpecParms = new GetLRSpec();
				getLRSpecParms.setName(getPropertyValueReaderNameComboSelectedIndexName);

				try {
					lrSpecConfiguratorResult = alelrProxy.getLRSpec(getLRSpecParms);
				}
				catch (org.accada.ale.wsdl.alelr.epcglobal.SecurityExceptionResponse e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				catch (org.accada.ale.wsdl.alelr.epcglobal.ImplementationExceptionResponse e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				catch (org.accada.ale.wsdl.alelr.epcglobal.NoSuchNameExceptionResponse e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				if (lrSpecConfiguratorResult instanceof LRSpec) {

					CharArrayWriter writer = new CharArrayWriter();

					try {
						SerializerUtil.serializeLRSpec((LRSpec) lrSpecConfiguratorResult, writer);
					}
					catch (IOException e1) {
						MessageDialog.openWarning(container.getShell(), "SerializationException was thrown.", e1.getMessage());
					}

					try {
						// Create file
						FileWriter fstream = new FileWriter("out.txt");
						BufferedWriter out = new BufferedWriter(fstream);
						out.write(writer.toString());
						// Close the output stream
						out.close();
					}
					catch (Exception e1) {// Catch exception if any
						System.err.println("Error: " + e1.getMessage());
					}

					try {
						DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
						DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
						Document document = documentBuilder.parse("out.txt");
						NodeList readersNodesList = document.getElementsByTagName("value");

						for (int i = 0; i < readersNodesList.getLength(); i++) {
							if (readersNodesList.item(i).getTextContent().equals("org.ow2.aspirerfid.ale.server.readers.hal.HALAdaptor")) {
								getPropertyValuePropertyNameCombo.add("ReaderType");
								getPropertyValuePropertyNameCombo.add("Description");
								getPropertyValuePropertyNameCombo.add("AdaptorClass");
								getPropertyValuePropertyNameCombo.add("PhysicalReaderName");
								getPropertyValuePropertyNameCombo.add("ReadTimeInterval");
								getPropertyValuePropertyNameCombo.add("ReadPoints");
								getPropertyValuePropertyNameCombo.add("PropertiesFile");

								return;
							}
							else if (readersNodesList.item(i).getTextContent().equals("org.ow2.aspirerfid.ale.server.readers.rp.RPAdaptor")) {
								getPropertyValuePropertyNameCombo.add("ReaderType");
								getPropertyValuePropertyNameCombo.add("Description");
								getPropertyValuePropertyNameCombo.add("ConnectionPoint");
								getPropertyValuePropertyNameCombo.add("NotificationPoint");
								getPropertyValuePropertyNameCombo.add("ReadTimeInterval");
								getPropertyValuePropertyNameCombo.add("AdaptorClass");
								getPropertyValuePropertyNameCombo.add("ImplementationClass");
								getPropertyValuePropertyNameCombo.add("PhysicalReaderName");
								getPropertyValuePropertyNameCombo.add("PhysicalReaderSource");

								return;
							}
							else if (readersNodesList.item(i).getTextContent().equals("org.ow2.aspirerfid.ale.server.readers.llrp.LLRPAdaptor")) {
								getPropertyValuePropertyNameCombo.add("ReaderType");
								getPropertyValuePropertyNameCombo.add("Description");
								getPropertyValuePropertyNameCombo.add("ConnectionPointAddress");
								getPropertyValuePropertyNameCombo.add("ConnectionPointPort");
								getPropertyValuePropertyNameCombo.add("EncryptedConnectionPointAddress");
								getPropertyValuePropertyNameCombo.add("EncryptedConnectionPointPort");
								getPropertyValuePropertyNameCombo.add("ReadTimeInterval");
								getPropertyValuePropertyNameCombo.add("AdaptorClass");
								getPropertyValuePropertyNameCombo.add("ImplementationClass");
								getPropertyValuePropertyNameCombo.add("PhysicalReaderSource");
								getPropertyValuePropertyNameCombo.add("DescriptiveReaderSource");
								getPropertyValuePropertyNameCombo.add("RoSpecID");

								return;
							}
							else if (readersNodesList.item(i).getTextContent().equals("org.ow2.aspirerfid.ale.server.readers.CompositeReader")) {
								getPropertyValuePropertyNameCombo.add("ReaderType");
								getPropertyValuePropertyNameCombo.add("Description");

								return;
							}
						}

					}
					catch (Exception exc) {
						exc.printStackTrace();
					}
				}
			}
		});

		final Label label_22 = new Label(getPropertyValueComposite, SWT.NONE);
		label_22.setText("Property name:");
		label_22.setBounds(5, 35, 88, 21);

		final Label label_23 = new Label(getPropertyValueComposite, SWT.SEPARATOR | SWT.HORIZONTAL);
		label_23.setBounds(5, 60, 515, 10);

		final Button getPropertyValueExecuteButton = new Button(getPropertyValueComposite, SWT.NONE);
		getPropertyValueExecuteButton.addMouseListener(new GetPropertyValueExecuteButtonMouseListener());
		getPropertyValueExecuteButton.setImage(ResourceManager.getPluginImage(Activator.getDefault(), "icons/services_execute.gif"));
		getPropertyValueExecuteButton.setText("Execute");
		getPropertyValueExecuteButton.setBounds(215, 75, 80, 24);

		getPropertyValuePropertyNameCombo = new Combo(getPropertyValueComposite, SWT.NONE);
		getPropertyValuePropertyNameCombo.setVisibleItemCount(15);
		getPropertyValuePropertyNameCombo.setBackground(SWTResourceManager.getColor(230, 230, 230));
		getPropertyValuePropertyNameCombo.setBounds(96, 32, 424, 24);

		setPropertiesComposite = new Composite(alelrMethodGroup, SWT.NONE);
		setPropertiesComposite.setBounds(10, 16, 530, 105);

		setPropertiesReaderNameCombo = new Combo(setPropertiesComposite, SWT.READ_ONLY);
		setPropertiesReaderNameCombo.setBackground(SWTResourceManager.getColor(230, 230, 230));
		setPropertiesReaderNameCombo.setBounds(96, 2, 424, 24);

		setPropertiesReaderNameCombo.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				lrSpecConfiguratorResult = null;

				org.eclipse.swt.widgets.Combo combo = (org.eclipse.swt.widgets.Combo) e.getSource();
				setPropertiesReaderNameComboSelectedIndex = combo.getSelectionIndex();
				setPropertiesReaderNameComboSelectedIndexName = combo.getItem(setPropertiesReaderNameComboSelectedIndex);

				GetLRSpec getLRSpecParms = new GetLRSpec();
				getLRSpecParms.setName(setPropertiesReaderNameComboSelectedIndexName);

				try {
					lrSpecConfiguratorResult = alelrProxy.getLRSpec(getLRSpecParms);
				}
				catch (org.accada.ale.wsdl.alelr.epcglobal.SecurityExceptionResponse e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				catch (org.accada.ale.wsdl.alelr.epcglobal.ImplementationExceptionResponse e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				catch (org.accada.ale.wsdl.alelr.epcglobal.NoSuchNameExceptionResponse e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				if (lrSpecConfiguratorResult instanceof LRSpec) {

					CharArrayWriter writer = new CharArrayWriter();

					try {
						SerializerUtil.serializeLRSpec((LRSpec) lrSpecConfiguratorResult, writer);
					}
					catch (IOException e1) {
						MessageDialog.openWarning(container.getShell(), "SerializationException was thrown.", e1.getMessage());
					}

					try {
						// Create file
						FileWriter fstream = new FileWriter("out.txt");
						BufferedWriter out = new BufferedWriter(fstream);
						out.write(writer.toString());
						// Close the output stream
						out.close();
					}
					catch (Exception e1) {// Catch exception if any
						System.err.println("Error: " + e1.getMessage());
					}

					try {
						DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
						DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
						Document document = documentBuilder.parse("out.txt");
						NodeList propertiesNodesList = document.getElementsByTagName("value");

						for (int i = 0; i < propertiesNodesList.getLength(); i++) {
							if (propertiesNodesList.item(i).getTextContent().equals("org.ow2.aspirerfid.ale.server.readers.hal.HALAdaptor")) {

								halReaderSetpropertiesInputValues = new ArrayList<String>();

								NodeList propertiesNamesNodeList = document.getElementsByTagName("name");

								NodeList propertiesValuesNodesList = document.getElementsByTagName("value");

								halReaderSetPropertiesInputDialog dlg = new halReaderSetPropertiesInputDialog(new Shell(), propertiesNamesNodeList,
										propertiesValuesNodesList);
								halReaderSetpropertiesInputValues = dlg.open();

								if (halReaderSetpropertiesInputValues != null) {
									aleServerConfiguratorConsole.clearConsole();

									SetProperties.Properties props = new SetProperties.Properties();
									LRProperty property = new LRProperty();

									property.setName("ReaderType");
									property.setValue(halReaderSetpropertiesInputValues.get(0));
									props.getProperty().add(property);

									property = new LRProperty();
									property.setName("Description");
									property.setValue(halReaderSetpropertiesInputValues.get(1));
									props.getProperty().add(property);

									property = new LRProperty();
									property.setName("AdaptorClass");
									property.setValue(halReaderSetpropertiesInputValues.get(2));
									props.getProperty().add(property);

									property = new LRProperty();
									property.setName("PhysicalReaderName");
									property.setValue(halReaderSetpropertiesInputValues.get(3));
									props.getProperty().add(property);

									property = new LRProperty();
									property.setName("ReadTimeInterval");
									property.setValue(halReaderSetpropertiesInputValues.get(4));
									props.getProperty().add(property);

									property = new LRProperty();
									property.setName("ReadPoints");
									property.setValue(halReaderSetpropertiesInputValues.get(5));
									props.getProperty().add(property);

									property = new LRProperty();
									property.setName("PropertiesFile");
									property.setValue(halReaderSetpropertiesInputValues.get(6));
									props.getProperty().add(property);

									SetProperties setPropertiesParms = new SetProperties();
									setPropertiesParms.setName(setPropertiesReaderNameCombo.getText());
									setPropertiesParms.setProperties(props);

									try {
										alelrProxy.setProperties(setPropertiesParms);
										MessageDialog.openConfirm(container.getShell(), "Confirmation",
												"[setProperties method]: Properties were successfully set!");
									}
									catch (org.accada.ale.wsdl.alelr.epcglobal.SecurityExceptionResponse e1) {
										// TODO Auto-generated catch
										// block
										MessageDialog.openWarning(container.getShell(), "[setProperties method]: Security Exception was thrown.", e1
												.getMessage());
										e1.printStackTrace();
									}
									catch (org.accada.ale.wsdl.alelr.epcglobal.InUseExceptionResponse e1) {
										// TODO Auto-generated catch
										// block
										MessageDialog.openWarning(container.getShell(), "[setProperties method]: In Use Exception was thrown.", e1
												.getMessage());
										e1.printStackTrace();
									}
									catch (org.accada.ale.wsdl.alelr.epcglobal.ImplementationExceptionResponse e1) {
										// TODO Auto-generated catch
										// block
										MessageDialog.openWarning(container.getShell(),
												"[setProperties method]: Implementation Exception was thrown.", e1.getMessage());
										e1.printStackTrace();
									}
									catch (org.accada.ale.wsdl.alelr.epcglobal.ImmutableReaderExceptionResponse e1) {
										// TODO Auto-generated catch
										// block
										MessageDialog.openWarning(container.getShell(),
												"[setProperties method]: Immutable Reader Exception was thrown.", e1.getMessage());
										e1.printStackTrace();
									}
									catch (org.accada.ale.wsdl.alelr.epcglobal.NoSuchNameExceptionResponse e1) {
										// TODO Auto-generated catch
										// block
										MessageDialog.openWarning(container.getShell(), "[setProperties method]: No Such Name Exception was thrown.",
												e1.getMessage());
										e1.printStackTrace();
									}
									catch (org.accada.ale.wsdl.alelr.epcglobal.ValidationExceptionResponse e1) {
										// TODO Auto-generated catch
										// block
										MessageDialog.openWarning(container.getShell(), "[setProperties method]: Validation Exception was thrown.",
												e1.getMessage());
										e1.printStackTrace();
									}

									setPropertiesReaderNameCombo.deselectAll();
								}
								else {
									setPropertiesReaderNameCombo.deselectAll();
								}

								return;

							}
							else if (propertiesNodesList.item(i).getTextContent().equals("org.ow2.aspirerfid.ale.server.readers.rp.RPAdaptor")) {

								rpReaderSetpropertiesInputValues = new ArrayList<String>();

								NodeList propertiesNamesNodeList = document.getElementsByTagName("name");

								NodeList propertiesValuesNodesList = document.getElementsByTagName("value");

								rpReaderSetPropertiesInputDialog dlg = new rpReaderSetPropertiesInputDialog(new Shell(), propertiesNamesNodeList,
										propertiesValuesNodesList);
								rpReaderSetpropertiesInputValues = dlg.open();

								if (rpReaderSetpropertiesInputValues != null) {
									aleServerConfiguratorConsole.clearConsole();

									SetProperties.Properties props = new SetProperties.Properties();
									LRProperty property = new LRProperty();

									property.setName("ReaderType");
									property.setValue(rpReaderSetpropertiesInputValues.get(0));
									props.getProperty().add(property);

									property = new LRProperty();
									property.setName("Description");
									property.setValue(rpReaderSetpropertiesInputValues.get(1));
									props.getProperty().add(property);

									property = new LRProperty();
									property.setName("ConnectionPoint");
									property.setValue(rpReaderSetpropertiesInputValues.get(2));
									props.getProperty().add(property);

									property = new LRProperty();
									property.setName("NotificationPoint");
									property.setValue(rpReaderSetpropertiesInputValues.get(3));
									props.getProperty().add(property);

									property = new LRProperty();
									property.setName("ReadTimeInterval");
									property.setValue(rpReaderSetpropertiesInputValues.get(4));
									props.getProperty().add(property);

									property = new LRProperty();
									property.setName("AdaptorClass");
									property.setValue(rpReaderSetpropertiesInputValues.get(5));
									props.getProperty().add(property);

									property = new LRProperty();
									property.setName("ImplementationClass");
									property.setValue(rpReaderSetpropertiesInputValues.get(6));
									props.getProperty().add(property);

									property = new LRProperty();
									property.setName("PhysicalReaderName");
									property.setValue(rpReaderSetpropertiesInputValues.get(7));
									props.getProperty().add(property);

									property = new LRProperty();
									property.setName("PhysicalReaderSource");
									property.setValue(rpReaderSetpropertiesInputValues.get(8));
									props.getProperty().add(property);

									SetProperties setPropertiesParms = new SetProperties();
									setPropertiesParms.setName(setPropertiesReaderNameCombo.getText());
									setPropertiesParms.setProperties(props);

									try {
										alelrProxy.setProperties(setPropertiesParms);
										MessageDialog.openConfirm(container.getShell(), "Confirmation",
												"[setProperties method]: Properties were successfully set!");
									}
									catch (org.accada.ale.wsdl.alelr.epcglobal.SecurityExceptionResponse e1) {
										// TODO Auto-generated catch
										// block
										MessageDialog.openWarning(container.getShell(), "[setProperties method]: Security Exception was thrown.", e1
												.getMessage());
										e1.printStackTrace();
									}
									catch (org.accada.ale.wsdl.alelr.epcglobal.InUseExceptionResponse e1) {
										// TODO Auto-generated catch
										// block
										MessageDialog.openWarning(container.getShell(), "[setProperties method]: In Use Exception was thrown.", e1
												.getMessage());
										e1.printStackTrace();
									}
									catch (org.accada.ale.wsdl.alelr.epcglobal.ImplementationExceptionResponse e1) {
										// TODO Auto-generated catch
										// block
										MessageDialog.openWarning(container.getShell(),
												"[setProperties method]: Implementation Exception was thrown.", e1.getMessage());
										e1.printStackTrace();
									}
									catch (org.accada.ale.wsdl.alelr.epcglobal.ImmutableReaderExceptionResponse e1) {
										// TODO Auto-generated catch
										// block
										MessageDialog.openWarning(container.getShell(),
												"[setProperties method]: Immutable Reader Exception was thrown.", e1.getMessage());
										e1.printStackTrace();
									}
									catch (org.accada.ale.wsdl.alelr.epcglobal.NoSuchNameExceptionResponse e1) {
										// TODO Auto-generated catch
										// block
										MessageDialog.openWarning(container.getShell(), "[setProperties method]: No Such Name Exception was thrown.",
												e1.getMessage());
										e1.printStackTrace();
									}
									catch (org.accada.ale.wsdl.alelr.epcglobal.ValidationExceptionResponse e1) {
										// TODO Auto-generated catch
										// block
										MessageDialog.openWarning(container.getShell(), "[setProperties method]: Validation Exception was thrown.",
												e1.getMessage());
										e1.printStackTrace();
									}

									setPropertiesReaderNameCombo.deselectAll();
								}
								else {
									setPropertiesReaderNameCombo.deselectAll();
								}

								return;

							}
							else if (propertiesNodesList.item(i).getTextContent().equals("org.ow2.aspirerfid.ale.server.readers.llrp.LLRPAdaptor")) {

								llrpReaderSetpropertiesInputValues = new ArrayList<String>();

								NodeList propertiesNamesNodeList = document.getElementsByTagName("name");

								NodeList propertiesValuesNodesList = document.getElementsByTagName("value");

								llrpReaderSetPropertiesInputDialog dlg = new llrpReaderSetPropertiesInputDialog(new Shell(), propertiesNamesNodeList,
										propertiesValuesNodesList);
								llrpReaderSetpropertiesInputValues = dlg.open();

								if (llrpReaderSetpropertiesInputValues != null) {
									aleServerConfiguratorConsole.clearConsole();

									SetProperties.Properties props = new SetProperties.Properties();
									LRProperty property = new LRProperty();

									property.setName("ReaderType");
									property.setValue(llrpReaderSetpropertiesInputValues.get(0));
									props.getProperty().add(property);

									property = new LRProperty();
									property.setName("Description");
									property.setValue(llrpReaderSetpropertiesInputValues.get(1));
									props.getProperty().add(property);

									property = new LRProperty();
									property.setName("ConnectionPointAddress");
									property.setValue(llrpReaderSetpropertiesInputValues.get(2));
									props.getProperty().add(property);

									property = new LRProperty();
									property.setName("ConnectionPointPort");
									property.setValue(llrpReaderSetpropertiesInputValues.get(3));
									props.getProperty().add(property);

									property = new LRProperty();
									property.setName("EncryptedConnectionPointAddress");
									property.setValue(llrpReaderSetpropertiesInputValues.get(4));
									props.getProperty().add(property);

									property = new LRProperty();
									property.setName("EncryptedConnectionPointPort");
									property.setValue(llrpReaderSetpropertiesInputValues.get(5));
									props.getProperty().add(property);

									property = new LRProperty();
									property.setName("ReadTimeInterval");
									property.setValue(llrpReaderSetpropertiesInputValues.get(6));
									props.getProperty().add(property);

									property = new LRProperty();
									property.setName("AdaptorClass");
									property.setValue(llrpReaderSetpropertiesInputValues.get(7));
									props.getProperty().add(property);

									property = new LRProperty();
									property.setName("ImplementationClass");
									property.setValue(llrpReaderSetpropertiesInputValues.get(8));
									props.getProperty().add(property);

									property = new LRProperty();
									property.setName("PhysicalReaderSource");
									property.setValue(llrpReaderSetpropertiesInputValues.get(9));
									props.getProperty().add(property);

									property = new LRProperty();
									property.setName("DescriptiveReaderSource");
									property.setValue(llrpReaderSetpropertiesInputValues.get(10));
									props.getProperty().add(property);

									property = new LRProperty();
									property.setName("RoSpecID");
									property.setValue(llrpReaderSetpropertiesInputValues.get(11));
									props.getProperty().add(property);

									SetProperties setPropertiesParms = new SetProperties();
									setPropertiesParms.setName(setPropertiesReaderNameCombo.getText());
									setPropertiesParms.setProperties(props);

									try {
										alelrProxy.setProperties(setPropertiesParms);
										MessageDialog.openConfirm(container.getShell(), "Confirmation",
												"[setProperties method]: Properties were successfully set!");
									}
									catch (org.accada.ale.wsdl.alelr.epcglobal.SecurityExceptionResponse e1) {
										// TODO Auto-generated catch
										// block
										MessageDialog.openWarning(container.getShell(), "[setProperties method]: Security Exception was thrown.", e1
												.getMessage());
										e1.printStackTrace();
									}
									catch (org.accada.ale.wsdl.alelr.epcglobal.InUseExceptionResponse e1) {
										// TODO Auto-generated catch
										// block
										MessageDialog.openWarning(container.getShell(), "[setProperties method]: In Use Exception was thrown.", e1
												.getMessage());
										e1.printStackTrace();
									}
									catch (org.accada.ale.wsdl.alelr.epcglobal.ImplementationExceptionResponse e1) {
										// TODO Auto-generated catch
										// block
										MessageDialog.openWarning(container.getShell(),
												"[setProperties method]: Implementation Exception was thrown.", e1.getMessage());
										e1.printStackTrace();
									}
									catch (org.accada.ale.wsdl.alelr.epcglobal.ImmutableReaderExceptionResponse e1) {
										// TODO Auto-generated catch
										// block
										MessageDialog.openWarning(container.getShell(),
												"[setProperties method]: Immutable Reader Exception was thrown.", e1.getMessage());
										e1.printStackTrace();
									}
									catch (org.accada.ale.wsdl.alelr.epcglobal.NoSuchNameExceptionResponse e1) {
										// TODO Auto-generated catch
										// block
										MessageDialog.openWarning(container.getShell(), "[setProperties method]: No Such Name Exception was thrown.",
												e1.getMessage());
										e1.printStackTrace();
									}
									catch (org.accada.ale.wsdl.alelr.epcglobal.ValidationExceptionResponse e1) {
										// TODO Auto-generated catch
										// block
										MessageDialog.openWarning(container.getShell(), "[setProperties method]: Validation Exception was thrown.",
												e1.getMessage());
										e1.printStackTrace();
									}

									setPropertiesReaderNameCombo.deselectAll();
								}
								else {
									setPropertiesReaderNameCombo.deselectAll();
								}

								return;
							}
						}
					}
					catch (Exception exc) {
						exc.printStackTrace();
					}

					// ====================================
				}
			}
		});

		final Label readerNameLabel_5 = new Label(setPropertiesComposite, SWT.NONE);
		readerNameLabel_5.setText("Reader name:");
		readerNameLabel_5.setBounds(5, 5, 88, 21);

		final Button button = new Button(alelrMethodsGroup_1, SWT.FLAT | SWT.BORDER);
		button.setImage(ResourceManager.getPluginImage(Activator.getDefault(), "icons/img/desktop_icons_1.gif"));
		button.setBounds(10, 22, 101, 43);

		final ToolBar toolBar_1 = new ToolBar(alelrMethodsGroup_1, SWT.BORDER);
		toolBar_1.setBackground(SWTResourceManager.getColor(179, 213, 186));
		toolBar_1.setBounds(111, 22, 560, 43);

		final ToolItem getLogicalReaderNamesToolItem = new ToolItem(toolBar_1, SWT.PUSH);
		getLogicalReaderNamesToolItem.addSelectionListener(new GetLogicalReaderNamesToolItemSelectionListener());
		getLogicalReaderNamesToolItem.setImage(ResourceManager.getPluginImage(Activator.getDefault(), "icons/img/b_browse.png"));
		getLogicalReaderNamesToolItem.setText("GetLogicalReaderNames");

		final ToolItem getLRSpecToolItem = new ToolItem(toolBar_1, SWT.PUSH);
		getLRSpecToolItem.addSelectionListener(new GetLRSpecToolItemSelectionListener());
		getLRSpecToolItem.setImage(ResourceManager.getPluginImage(Activator.getDefault(), "icons/img/b_views.png"));
		getLRSpecToolItem.setText("GetLRSpec");

		final ToolItem getPropertyValueToolItem = new ToolItem(toolBar_1, SWT.PUSH);
		getPropertyValueToolItem.addSelectionListener(new GetPropertyValueToolItemSelectionListener());
		getPropertyValueToolItem.setImage(ResourceManager.getPluginImage(Activator.getDefault(), "icons/img/b_index.png"));
		getPropertyValueToolItem.setText("GetPropertyValue");

		final ToolItem getStandardVersionToolItem = new ToolItem(toolBar_1, SWT.PUSH);
		getStandardVersionToolItem.addSelectionListener(new GetStandardVersionToolItemSelectionListener());
		getStandardVersionToolItem.setImage(ResourceManager.getPluginImage(Activator.getDefault(), "icons/img/s_really.png"));
		getStandardVersionToolItem.setText("GetStandardVersion");

		final ToolItem getVendorVersionToolItem = new ToolItem(toolBar_1, SWT.PUSH);
		getVendorVersionToolItem.addSelectionListener(new GetVendorVersionToolItemSelectionListener());
		getVendorVersionToolItem.setImage(ResourceManager.getPluginImage(Activator.getDefault(), "icons/img/b_help.png"));
		getVendorVersionToolItem.setText("GetVendorVersion");

		createActions();
		initializeToolBar();
		initializeMenu();
		initializeAleLrProxy();
		loadReaderNamesFromECSpecs();

	}

	private File[] getECSpecsPathFolderContents() {

		String folderPath = ecspecPreferences.getString(PreferenceConstants.P_ECSpecsPATH);
		File f = new File(folderPath);

		if (f.exists() == false) {
			f.mkdirs();
		}

		File[] contents = f.listFiles();
		return contents;
	}

	// initialize dynamic reader names
	private void loadReaderNamesFromECSpecs() {
		File[] ECSpecDirectoryContents = getECSpecsPathFolderContents();

		if (ECSpecDirectoryContents.length != 0) {
			for (int i = 0; i < ECSpecDirectoryContents.length; i++) {
				if (ECSpecDirectoryContents[i].getName().endsWith(".xml")) {
					openEcSpecBuilder = new ECSpecBuilder();

					try {
						ECSpec ecSpec = new ECSpec();
						ecSpec = openEcSpecBuilder.getECSpecFromFile(PreferenceConstants.P_ECSpecsPATH + ECSpecDirectoryContents[i].getName());

						for (int j = 0; j < ecSpec.getLogicalReaders().getLogicalReader().size(); j++) {
							// add a new value to
							// PreferenceConstants.P_ReaderNames
							String readerNamesArray[] = {};
							String readerNames = "";
							boolean found = false;

							readerNamesArray = lrspecConfigPreferences.getString(PreferenceConstants.P_ReaderNames).split(",");

							for (int k = 0; k < readerNamesArray.length; k++) {
								if (ecSpec.getLogicalReaders().getLogicalReader().get(j).equals(readerNamesArray[k])) {
									found = true;
								}
							}

							if (found == false) {

								readerNames = lrspecConfigPreferences.getString(PreferenceConstants.P_ReaderNames);

								readerNames = readerNames + "," + ecSpec.getLogicalReaders().getLogicalReader().get(j);

								lrspecConfigPreferences.setValue(PreferenceConstants.P_ReaderNames, readerNames);
							}
						}
					}
					catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}
	}

	/**
	 * Create the actions
	 */
	private void createActions() {
		// Create the actions
		setSetPropertiesHALReaderComposite(false);
		setSetPropertiesRPReaderComposite(false);
		setSetPropertiesLLRPReaderComposite(false);
	}

	@Override
	public void setFocus() {
		// TODO Auto-generated method stub

	}

	/**
	 * Initialize the menu
	 */
	private void initializeMenu() {
		@SuppressWarnings("unused")
		IMenuManager menuManager = getViewSite().getActionBars().getMenuManager();
	}

	private void initializeToolBar() {
		@SuppressWarnings("unused")
		IToolBarManager toolBarManager = getViewSite().getActionBars().getToolBarManager();
	}

	private void initializeAleLrProxy() {
		JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();
		factory.setServiceClass(ALELRServicePortType.class);
		String lrSpecEndPoint = lrspecConfigPreferences.getString(PreferenceConstants.P_ALELRClientEndPointSTRING);
		if (lrSpecEndPoint.equals(null) || lrSpecEndPoint.equals("")) {
			MessageDialog.openWarning(container.getShell(), "Error", "No end point has been defined!");
			return;
		}
		factory.setAddress(lrSpecEndPoint);
		alelrProxy = (ALELRServicePortType) factory.create();
	}

	/**
	 * Initiate the Ale Server Configurator Console
	 */
	private void initiateConsole() {
		manager = ConsolePlugin.getDefault().getConsoleManager();
		consolesx = manager.getConsoles();
		boolean exist = false;
		for (int i = 0; i < consolesx.length; i++) {
			if (consolesx[i].getName().equals("ALE Server Configurator"))
				aleServerConfiguratorConsole = (IOConsole) consolesx[i];
			exist = true;
		}
		if (!exist) {
			aleServerConfiguratorConsole = new IOConsole("ALE Server Configurator", null);
			manager.addConsoles(new IConsole[] { aleServerConfiguratorConsole });
		}
		manager.showConsoleView(aleServerConfiguratorConsole);
		aleServerConfiguratorConsole.clearConsole();
		aleServerConfiguratorOutputConsole = aleServerConfiguratorConsole.newOutputStream();
	}

	/**
	 * Remove the Ale Server Configurator Console
	 */
	private void removeConsole() {
		manager = ConsolePlugin.getDefault().getConsoleManager();
		consolesx = manager.getConsoles();
		boolean exist = false;
		for (int i = 0; i < consolesx.length; i++) {
			if (consolesx[i].getName().equals("ALE Server Configurator"))
				aleServerConfiguratorConsole = (IOConsole) consolesx[i];
			exist = true;
		}
		if (!exist) {
			aleServerConfiguratorConsole = new IOConsole("ALE Server Configurator", null);
			manager.addConsoles(new IConsole[] { aleServerConfiguratorConsole });
		}

		manager.removeConsoles(new IConsole[] { aleServerConfiguratorConsole });
		aleServerConfiguratorConsole.destroy();
	}

	// =========================Getters & Setters=============================

	public void setLRSpecConfiguratorCompositeVisible(boolean visible) {
		lrspecConfiguratorComposite.setVisible(visible);
	}

	public void setDefineLRSpecCompositeVisible(boolean visible) {
		defineLRSpecComposite.setVisible(visible);
	}

	public void setUndefineLRSpecCompositeVisible(boolean visible) {
		undefineLRSpecComposite.setVisible(visible);
	}

	public void setUpdateLRSpecCompositeVisible(boolean visible) {
		updateLRSpecComposite.setVisible(visible);
	}

	public void setGetLRSpecCompositeVisible(boolean visible) {
		getLRSpecComposite.setVisible(visible);
	}

	public void setAddReadersComposite(boolean visible) {
		addReadersComposite.setVisible(visible);
	}

	public void setSetReadersComposite(boolean visible) {
		setReadersComposite.setVisible(visible);
	}

	public void setRemoveReadersComposite(boolean visible) {
		removeReadersComposite.setVisible(visible);
	}

	public void setGetPropertyValueComposite(boolean visible) {
		getPropertyValueComposite.setVisible(visible);
	}

	public void setSetPropertiesComposite(boolean visible) {
		setPropertiesComposite.setVisible(visible);
	}

	public void setSetPropertiesHALReaderComposite(boolean visible) {
	}

	public void setSetPropertiesRPReaderComposite(boolean visible) {
	}

	public void setSetPropertiesLLRPReaderComposite(boolean visible) {
	}

	/**
	 * Write to the IDE Console
	 * 
	 * @param message
	 */
	private void writeToConsole(String message) {

		try {
			aleServerConfiguratorOutputConsole.write(message + "\n");
		}
		catch (IOException e) {
			MessageDialog.openWarning(container.getShell(), "Console writing error!", e.getMessage());
		}
	}

	private void showLRSpecConfiguratorResult(Object result) {

		if (result instanceof String) {
			aleServerConfiguratorConsole.clearConsole();
			writeToConsole((String) result);

		}
		else if (result instanceof org.accada.ale.wsdl.alelr.epcglobal.ArrayOfString) {
			aleServerConfiguratorConsole.clearConsole();
			org.accada.ale.wsdl.alelr.epcglobal.ArrayOfString resultStringArray = (org.accada.ale.wsdl.alelr.epcglobal.ArrayOfString) result;
			if (resultStringArray.getString().size() == 0) {
				writeToConsole("No data found!");
			}
			else {
				for (String s : resultStringArray.getString()) {
					writeToConsole(s);
				}
			}
		}
		else if (result instanceof LRSpec) {
			aleServerConfiguratorConsole.clearConsole();
			CharArrayWriter writer = new CharArrayWriter();
			try {
				SerializerUtil.serializeLRSpec((LRSpec) result, writer);
			}
			catch (IOException e) {
				MessageDialog.openWarning(container.getShell(), "SerializationException was thrown.", e.getMessage());
			}
			writeToConsole(writer.toString());

		}
	}

	private File[] getHALLRSpecsPathFolderContents() {

		String folderPath = lrspecConfigPreferences.getString(PreferenceConstants.P_HAL_LRSpecsPATH);
		File f = new File(folderPath);

		if (f.exists() == false) {
			f.mkdirs();
		}

		File[] contents = f.listFiles();
		return contents;
	}

	private File[] getRPLRSpecsPathFolderContents() {

		String folderPath = lrspecConfigPreferences.getString(PreferenceConstants.P_RP_LRSpecsPATH);
		File f = new File(folderPath);

		if (f.exists() == false) {
			f.mkdirs();
		}

		File[] contents = f.listFiles();
		return contents;
	}

	private File[] getLLRPLRSpecsPathFolderContents() {

		String folderPath = lrspecConfigPreferences.getString(PreferenceConstants.P_LLRP_LRSpecsPATH);
		File f = new File(folderPath);

		if (f.exists() == false) {
			f.mkdirs();
		}

		File[] contents = f.listFiles();
		return contents;
	}

	private File[] getCompositeLRSpecsPathFolderContents() {

		String folderPath = lrspecConfigPreferences.getString(PreferenceConstants.P_Composite_LRSpecsPATH);
		File f = new File(folderPath);

		if (f.exists() == false) {
			f.mkdirs();
		}

		File[] contents = f.listFiles();
		return contents;
	}

	private void getVendorVersionLr() {
		setDefineLRSpecCompositeVisible(false);
		setUpdateLRSpecCompositeVisible(false);
		setUndefineLRSpecCompositeVisible(false);
		setGetLRSpecCompositeVisible(false);
		setAddReadersComposite(false);
		setSetReadersComposite(false);
		setRemoveReadersComposite(false);
		setGetPropertyValueComposite(false);
		setSetPropertiesComposite(false);
		setSetPropertiesHALReaderComposite(false);

		try {
			lrSpecConfiguratorResult = alelrProxy.getVendorVersion(new org.accada.ale.wsdl.alelr.epcglobal.EmptyParms());
		}
		catch (org.accada.ale.wsdl.alelr.epcglobal.ImplementationExceptionResponse e1) {
			MessageDialog.openWarning(container.getShell(), "Error", "[getVendorVersion method]: Implementation Exception was thrown.");
			e1.printStackTrace();
		}

		writeToConsole("Vendor version: ");
		showLRSpecConfiguratorResult(lrSpecConfiguratorResult);
	}

	private void getStandardVersionLr() {
		setDefineLRSpecCompositeVisible(false);
		setUpdateLRSpecCompositeVisible(false);
		setUndefineLRSpecCompositeVisible(false);
		setGetLRSpecCompositeVisible(false);
		setAddReadersComposite(false);
		setSetReadersComposite(false);
		setRemoveReadersComposite(false);
		setGetPropertyValueComposite(false);
		setSetPropertiesComposite(false);
		setSetPropertiesHALReaderComposite(false);

		try {
			lrSpecConfiguratorResult = alelrProxy.getStandardVersion(new org.accada.ale.wsdl.alelr.epcglobal.EmptyParms());
		}
		catch (org.accada.ale.wsdl.alelr.epcglobal.ImplementationExceptionResponse e1) {
			MessageDialog.openWarning(container.getShell(), "Error", "[getStandardVersion method]: Implementation Exception was thrown.");
			e1.printStackTrace();
		}

		writeToConsole("Standard version: ");
		showLRSpecConfiguratorResult(lrSpecConfiguratorResult);
	}

	public void getDynamicReaders(File[] directoryContents, String readersPath) {
		DocumentBuilderFactory documentBuilderFactory;
		DocumentBuilder documentBuilder;
		Document document = null;
		String root = "";

		if (directoryContents.length != 0) {
			for (int i = 0; i < directoryContents.length; i++) {
				if (directoryContents[i].getName().endsWith(".xml")) {

					try {
						File selectedFile = new File(readersPath + directoryContents[i].getName());

						if (selectedFile.exists()) {
							documentBuilderFactory = DocumentBuilderFactory.newInstance();
							documentBuilder = documentBuilderFactory.newDocumentBuilder();
							document = documentBuilder.parse(selectedFile);
							Node node = document.getDocumentElement();
							root = node.getNodeName();
						}
						else {
							MessageDialog.openError(container.getShell(), "Error", "File not found!");
						}
					}
					catch (Exception exc) {
						MessageDialog.openError(container.getShell(), "Error", "DOM parser error!");
					}

					if (root.equals("ns3:LRSpec")) {
						lrDefineSelectLRSpecCombo.add(directoryContents[i].getName());
					}

				}
			}
		}
	}

	private void loadDefineLRSpecComposite() {
		setUndefineLRSpecCompositeVisible(false);
		setUpdateLRSpecCompositeVisible(false);
		setGetLRSpecCompositeVisible(false);
		setAddReadersComposite(false);
		setDefineLRSpecCompositeVisible(true);
		setSetReadersComposite(false);
		setRemoveReadersComposite(false);
		setGetPropertyValueComposite(false);
		setSetPropertiesComposite(false);
		setSetPropertiesHALReaderComposite(false);
		setSetPropertiesRPReaderComposite(false);
		setSetPropertiesLLRPReaderComposite(false);

		lrDefineSelectLRSpecCombo.removeAll();
		File[] halContents = getHALLRSpecsPathFolderContents();
		File[] rpContents = getRPLRSpecsPathFolderContents();
		File[] llrpContents = getLLRPLRSpecsPathFolderContents();
		File[] compositeContents = getCompositeLRSpecsPathFolderContents();

		// HAL Dynamic readers
		getDynamicReaders(halContents, PreferenceConstants.P_HAL_LRSpecsPATH);

		// RP Dynamic readers
		getDynamicReaders(rpContents, PreferenceConstants.P_RP_LRSpecsPATH);

		// LLRP Dynamic readers
		getDynamicReaders(llrpContents, PreferenceConstants.P_LLRP_LRSpecsPATH);

		// Composite Dynamic readers
		getDynamicReaders(compositeContents, PreferenceConstants.P_Composite_LRSpecsPATH);

		lrDefineReaderNameCombo.removeAll();

		String readerNamesArray[] = {};

		readerNamesArray = lrspecConfigPreferences.getString(PreferenceConstants.P_ReaderNames).split(",");

		for (int i = 0; i < readerNamesArray.length; i++) {
			if (!(readerNamesArray[i].equals(""))) {
				lrDefineReaderNameCombo.add(readerNamesArray[i]);
			}
		}

	}

	private List<String> getDefinedLRSpecNames() {

		List<String> lrSpecNames = null;

		try {
			lrSpecNames = alelrProxy.getLogicalReaderNames(new org.accada.ale.wsdl.alelr.epcglobal.EmptyParms()).getString();
		}
		catch (Exception e) {
			MessageDialog.openWarning(container.getShell(), "Error", e.getMessage());
		}
		if (lrSpecNames != null && lrSpecNames.size() > 0) {
			return lrSpecNames;
		}
		else {
			return null;

		}
	}

	private LRSpec getLRSpecFromFile(Combo combo) {
		FileInputStream inputStream;
		LRSpec lrSpec = null;
		String lrSpecsPath = null;
		// lrSpecsPath =
		// lrspecConfigPreferences.getString(PreferenceConstants.P_LRSpecsPATH);

		try {
			File selectedFile = new File(PreferenceConstants.P_HAL_LRSpecsPATH + combo.getText());

			if (selectedFile.exists()) {
				lrSpecsPath = PreferenceConstants.P_HAL_LRSpecsPATH + combo.getText();
			}
			else {
				System.out.println("File not found!");
			}
		}
		catch (Exception exc) {
			exc.printStackTrace();
		}

		try {
			File selectedFile = new File(PreferenceConstants.P_RP_LRSpecsPATH + combo.getText());

			if (selectedFile.exists()) {
				lrSpecsPath = PreferenceConstants.P_RP_LRSpecsPATH + combo.getText();
			}
			else {
				System.out.println("File not found!");
			}
		}
		catch (Exception exc) {
			exc.printStackTrace();
		}

		try {
			File selectedFile = new File(PreferenceConstants.P_LLRP_LRSpecsPATH + combo.getText());

			if (selectedFile.exists()) {
				lrSpecsPath = PreferenceConstants.P_LLRP_LRSpecsPATH + combo.getText();
			}
			else {
				System.out.println("File not found!");
			}
		}
		catch (Exception exc) {
			exc.printStackTrace();
		}

		try {
			File selectedFile = new File(PreferenceConstants.P_Composite_LRSpecsPATH + combo.getText());

			if (selectedFile.exists()) {
				lrSpecsPath = PreferenceConstants.P_Composite_LRSpecsPATH + combo.getText();
			}
			else {
				System.out.println("File not found!");
			}
		}
		catch (Exception exc) {
			exc.printStackTrace();
		}

		try {
			inputStream = new FileInputStream(lrSpecsPath);
			lrSpec = DeserializerUtil.deserializeLRSpec(inputStream);
		}
		catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			MessageDialog.openWarning(container.getShell(), "Error", "File not found!");
			e.printStackTrace();
		}
		catch (Exception e) {
			MessageDialog.openWarning(container.getShell(), "Error", e.getMessage());

		}

		return lrSpec;

	}

	private void loadUpdateLRSpecComposite() {
		setUndefineLRSpecCompositeVisible(false);
		setDefineLRSpecCompositeVisible(false);
		setGetLRSpecCompositeVisible(false);
		setAddReadersComposite(false);
		setUpdateLRSpecCompositeVisible(true);
		setSetReadersComposite(false);
		setRemoveReadersComposite(false);
		setGetPropertyValueComposite(false);
		setSetPropertiesComposite(false);
		setSetPropertiesHALReaderComposite(false);
		setSetPropertiesRPReaderComposite(false);
		setSetPropertiesLLRPReaderComposite(false);

		lrUpdateReaderNameCombo.removeAll();
		List<String> lrSpecNames = getDefinedLRSpecNames();
		if (lrSpecNames != null) {
			for (String lrspecName : lrSpecNames) {
				lrUpdateReaderNameCombo.add(lrspecName);

			}
		}

		lrUpdateSelectLRSpecCombo.removeAll();
		File[] halContents = getHALLRSpecsPathFolderContents();
		File[] rpContents = getRPLRSpecsPathFolderContents();
		File[] llrpContents = getLLRPLRSpecsPathFolderContents();
		File[] compositeContents = getCompositeLRSpecsPathFolderContents();

		for (int i = 0; i < halContents.length; i++) {
			if (halContents[i].getName().endsWith(".xml")) {
				lrUpdateSelectLRSpecCombo.add(halContents[i].getName());
			}
		}

		for (int i = 0; i < rpContents.length; i++) {
			if (rpContents[i].getName().endsWith(".xml")) {
				lrUpdateSelectLRSpecCombo.add(rpContents[i].getName());
			}
		}

		for (int i = 0; i < llrpContents.length; i++) {
			if (llrpContents[i].getName().endsWith(".xml")) {
				lrUpdateSelectLRSpecCombo.add(llrpContents[i].getName());
			}
		}

		for (int i = 0; i < compositeContents.length; i++) {
			if (compositeContents[i].getName().endsWith(".xml")) {
				lrUpdateSelectLRSpecCombo.add(compositeContents[i].getName());
			}
		}
	}

	private void loadUndefineLRSpecComposite() {
		setDefineLRSpecCompositeVisible(false);
		setUpdateLRSpecCompositeVisible(false);
		setGetLRSpecCompositeVisible(false);
		setAddReadersComposite(false);
		setUndefineLRSpecCompositeVisible(true);
		setSetReadersComposite(false);
		setRemoveReadersComposite(false);
		setGetPropertyValueComposite(false);
		setSetPropertiesComposite(false);
		setSetPropertiesHALReaderComposite(false);
		setSetPropertiesRPReaderComposite(false);
		setSetPropertiesLLRPReaderComposite(false);

		lrUndefineReaderNameCombo.removeAll();
		List<String> lrSpecNames = getDefinedLRSpecNames();
		if (lrSpecNames != null) {
			for (String lrspecName : lrSpecNames) {
				lrUndefineReaderNameCombo.add(lrspecName);

			}
		}
	}

	private void getLogicalReaderNames() {
		setUndefineLRSpecCompositeVisible(false);
		setDefineLRSpecCompositeVisible(false);
		setUpdateLRSpecCompositeVisible(false);
		setGetLRSpecCompositeVisible(false);
		setAddReadersComposite(false);
		setSetReadersComposite(false);
		setRemoveReadersComposite(false);
		setGetPropertyValueComposite(false);
		setSetPropertiesComposite(false);
		setSetPropertiesHALReaderComposite(false);
		setSetPropertiesRPReaderComposite(false);
		setSetPropertiesLLRPReaderComposite(false);

		try {
			lrSpecConfiguratorResult = alelrProxy.getLogicalReaderNames(new org.accada.ale.wsdl.alelr.epcglobal.EmptyParms());
		}
		catch (org.accada.ale.wsdl.alelr.epcglobal.SecurityExceptionResponse e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (org.accada.ale.wsdl.alelr.epcglobal.ImplementationExceptionResponse e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		writeToConsole("Logical reader names: ");
		showLRSpecConfiguratorResult(lrSpecConfiguratorResult);

	}

	private void loadGetLRSpecComposite() {
		setUndefineLRSpecCompositeVisible(false);
		setDefineLRSpecCompositeVisible(false);
		setUpdateLRSpecCompositeVisible(false);
		setAddReadersComposite(false);
		setGetLRSpecCompositeVisible(true);
		setSetReadersComposite(false);
		setRemoveReadersComposite(false);
		setGetPropertyValueComposite(false);
		setSetPropertiesComposite(false);
		setSetPropertiesHALReaderComposite(false);
		setSetPropertiesRPReaderComposite(false);
		setSetPropertiesLLRPReaderComposite(false);

		lrGetLRSpecReaderNameCombo.removeAll();
		List<String> lrSpecNames = getDefinedLRSpecNames();
		if (lrSpecNames != null) {
			for (String lrspecName : lrSpecNames) {
				lrGetLRSpecReaderNameCombo.add(lrspecName);

			}
		}
	}

	private void loadAddReadersComposite() {
		setUndefineLRSpecCompositeVisible(false);
		setDefineLRSpecCompositeVisible(false);
		setUpdateLRSpecCompositeVisible(false);
		setGetLRSpecCompositeVisible(false);
		setAddReadersComposite(true);
		setSetReadersComposite(false);
		setRemoveReadersComposite(false);
		setGetPropertyValueComposite(false);
		setSetPropertiesComposite(false);
		setSetPropertiesHALReaderComposite(false);
		setSetPropertiesRPReaderComposite(false);
		setSetPropertiesLLRPReaderComposite(false);

		lrSpecConfiguratorResult = null;

		addReadersReaderNameCombo.removeAll();
		addReadersSelectReaderCombo.removeAll();

		List<String> lrSpecNames = getDefinedLRSpecNames();
		if (lrSpecNames != null) {
			for (String lrspecName : lrSpecNames) {
				GetLRSpec getLRSpecParms = new GetLRSpec();
				getLRSpecParms.setName(lrspecName);

				try {
					lrSpecConfiguratorResult = alelrProxy.getLRSpec(getLRSpecParms);
				}
				catch (org.accada.ale.wsdl.alelr.epcglobal.SecurityExceptionResponse e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				catch (org.accada.ale.wsdl.alelr.epcglobal.ImplementationExceptionResponse e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				catch (org.accada.ale.wsdl.alelr.epcglobal.NoSuchNameExceptionResponse e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				if (lrSpecConfiguratorResult instanceof LRSpec) {

					CharArrayWriter writer = new CharArrayWriter();

					try {
						SerializerUtil.serializeLRSpec((LRSpec) lrSpecConfiguratorResult, writer);
					}
					catch (IOException e) {
						MessageDialog.openWarning(container.getShell(), "SerializationException was thrown.", e.getMessage());
					}

					try {
						// Create file
						FileWriter fstream = new FileWriter("out.txt");
						BufferedWriter out = new BufferedWriter(fstream);
						out.write(writer.toString());
						// Close the output stream
						out.close();
					}
					catch (Exception e) {// Catch exception if any
						System.err.println("Error: " + e.getMessage());
					}

					try {
						DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
						DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
						Document document = documentBuilder.parse("out.txt");
						NodeList readersNodesList = document.getElementsByTagName("isComposite");

						if (readersNodesList.item(0).getTextContent().equals("true")) {
							addReadersReaderNameCombo.add(lrspecName);
						}
						else {
							addReadersSelectReaderCombo.add(lrspecName);
						}
					}
					catch (Exception exc) {
						exc.printStackTrace();
					}

				}

			}
		}
	}

	private void loadSetReadersComposite() {
		setUndefineLRSpecCompositeVisible(false);
		setDefineLRSpecCompositeVisible(false);
		setUpdateLRSpecCompositeVisible(false);
		setGetLRSpecCompositeVisible(false);
		setAddReadersComposite(false);
		setSetReadersComposite(true);
		setRemoveReadersComposite(false);
		setGetPropertyValueComposite(false);
		setSetPropertiesComposite(false);
		setSetPropertiesHALReaderComposite(false);
		setSetPropertiesRPReaderComposite(false);
		setSetPropertiesLLRPReaderComposite(false);

		lrSpecConfiguratorResult = null;

		setReadersReaderNameCombo.removeAll();
		setReadersSelectReaderCombo.removeAll();

		List<String> lrSpecNames = getDefinedLRSpecNames();
		if (lrSpecNames != null) {
			for (String lrspecName : lrSpecNames) {
				GetLRSpec getLRSpecParms = new GetLRSpec();
				getLRSpecParms.setName(lrspecName);

				try {
					lrSpecConfiguratorResult = alelrProxy.getLRSpec(getLRSpecParms);
				}
				catch (org.accada.ale.wsdl.alelr.epcglobal.SecurityExceptionResponse e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				catch (org.accada.ale.wsdl.alelr.epcglobal.ImplementationExceptionResponse e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				catch (org.accada.ale.wsdl.alelr.epcglobal.NoSuchNameExceptionResponse e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				if (lrSpecConfiguratorResult instanceof LRSpec) {

					CharArrayWriter writer = new CharArrayWriter();

					try {
						SerializerUtil.serializeLRSpec((LRSpec) lrSpecConfiguratorResult, writer);
					}
					catch (IOException e) {
						MessageDialog.openWarning(container.getShell(), "SerializationException was thrown.", e.getMessage());
					}

					try {
						// Create file
						FileWriter fstream = new FileWriter("out.txt");
						BufferedWriter out = new BufferedWriter(fstream);
						out.write(writer.toString());
						// Close the output stream
						out.close();
					}
					catch (Exception e) {// Catch exception if any
						System.err.println("Error: " + e.getMessage());
					}

					try {
						DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
						DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
						Document document = documentBuilder.parse("out.txt");
						NodeList readersNodesList = document.getElementsByTagName("isComposite");

						if (readersNodesList.item(0).getTextContent().equals("true")) {
							setReadersReaderNameCombo.add(lrspecName);
						}
						else {
							setReadersSelectReaderCombo.add(lrspecName);
						}
					}
					catch (Exception exc) {
						exc.printStackTrace();
					}

				}

			}
		}

	}

	private void loadRemoveReadersComposite() {
		setUndefineLRSpecCompositeVisible(false);
		setDefineLRSpecCompositeVisible(false);
		setUpdateLRSpecCompositeVisible(false);
		setGetLRSpecCompositeVisible(false);
		setAddReadersComposite(false);
		setSetReadersComposite(false);
		setRemoveReadersComposite(true);
		setGetPropertyValueComposite(false);
		setSetPropertiesComposite(false);
		setSetPropertiesHALReaderComposite(false);
		setSetPropertiesRPReaderComposite(false);
		setSetPropertiesLLRPReaderComposite(false);

		lrSpecConfiguratorResult = null;

		removeReadersReaderNameCombo.removeAll();
		removeReadersSelectReaderCombo.removeAll();

		List<String> lrSpecNames = getDefinedLRSpecNames();
		if (lrSpecNames != null) {
			for (String lrspecName : lrSpecNames) {
				GetLRSpec getLRSpecParms = new GetLRSpec();
				getLRSpecParms.setName(lrspecName);

				try {
					lrSpecConfiguratorResult = alelrProxy.getLRSpec(getLRSpecParms);
				}
				catch (org.accada.ale.wsdl.alelr.epcglobal.SecurityExceptionResponse e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				catch (org.accada.ale.wsdl.alelr.epcglobal.ImplementationExceptionResponse e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				catch (org.accada.ale.wsdl.alelr.epcglobal.NoSuchNameExceptionResponse e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				if (lrSpecConfiguratorResult instanceof LRSpec) {

					CharArrayWriter writer = new CharArrayWriter();

					try {
						SerializerUtil.serializeLRSpec((LRSpec) lrSpecConfiguratorResult, writer);
					}
					catch (IOException e) {
						MessageDialog.openWarning(container.getShell(), "SerializationException was thrown.", e.getMessage());
					}

					try {
						// Create file
						FileWriter fstream = new FileWriter("out.txt");
						BufferedWriter out = new BufferedWriter(fstream);
						out.write(writer.toString());
						// Close the output stream
						out.close();
					}
					catch (Exception e) {// Catch exception if any
						System.err.println("Error: " + e.getMessage());
					}

					try {
						DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
						DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
						Document document = documentBuilder.parse("out.txt");
						NodeList readersNodesList = document.getElementsByTagName("isComposite");

						if (readersNodesList.item(0).getTextContent().equals("true")) {
							removeReadersReaderNameCombo.add(lrspecName);
						}
						else {
							removeReadersSelectReaderCombo.add(lrspecName);
						}
					}
					catch (Exception exc) {
						exc.printStackTrace();
					}

				}

			}
		}
	}

	private void loadSetPropertiesComposite() {
		setUndefineLRSpecCompositeVisible(false);
		setDefineLRSpecCompositeVisible(false);
		setUpdateLRSpecCompositeVisible(false);
		setGetLRSpecCompositeVisible(false);
		setAddReadersComposite(false);
		setSetReadersComposite(false);
		setRemoveReadersComposite(false);
		setGetPropertyValueComposite(false);
		setSetPropertiesComposite(true);
		setSetPropertiesHALReaderComposite(false);
		setSetPropertiesRPReaderComposite(false);
		setSetPropertiesLLRPReaderComposite(false);

		setPropertiesReaderNameCombo.removeAll();
		List<String> lrSpecNames = getDefinedLRSpecNames();
		if (lrSpecNames != null) {
			for (String lrspecName : lrSpecNames) {
				GetLRSpec getLRSpecParms = new GetLRSpec();
				getLRSpecParms.setName(lrspecName);

				try {
					lrSpecConfiguratorResult = alelrProxy.getLRSpec(getLRSpecParms);
				}
				catch (org.accada.ale.wsdl.alelr.epcglobal.SecurityExceptionResponse e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				catch (org.accada.ale.wsdl.alelr.epcglobal.ImplementationExceptionResponse e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				catch (org.accada.ale.wsdl.alelr.epcglobal.NoSuchNameExceptionResponse e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				if (lrSpecConfiguratorResult instanceof LRSpec) {

					CharArrayWriter writer = new CharArrayWriter();

					try {
						SerializerUtil.serializeLRSpec((LRSpec) lrSpecConfiguratorResult, writer);
					}
					catch (IOException e) {
						MessageDialog.openWarning(container.getShell(), "SerializationException was thrown.", e.getMessage());
					}

					try {
						// Create file
						FileWriter fstream = new FileWriter("out.txt");
						BufferedWriter out = new BufferedWriter(fstream);
						out.write(writer.toString());
						// Close the output stream
						out.close();
					}
					catch (Exception e) {// Catch exception if any
						System.err.println("Error: " + e.getMessage());
					}

					try {
						DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
						DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
						Document document = documentBuilder.parse("out.txt");
						NodeList readersNodesList = document.getElementsByTagName("isComposite");

						if (!readersNodesList.item(0).getTextContent().equals("true")) {
							setPropertiesReaderNameCombo.add(lrspecName);
						}
					}
					catch (Exception exc) {
						exc.printStackTrace();
					}

				}

			}
		}
	}

	private void loadGetPropertyValueComposite() {
		setUndefineLRSpecCompositeVisible(false);
		setDefineLRSpecCompositeVisible(false);
		setUpdateLRSpecCompositeVisible(false);
		setGetLRSpecCompositeVisible(false);
		setAddReadersComposite(false);
		setSetReadersComposite(false);
		setRemoveReadersComposite(false);
		setGetPropertyValueComposite(true);
		setSetPropertiesComposite(false);
		setSetPropertiesHALReaderComposite(false);
		setSetPropertiesRPReaderComposite(false);
		setSetPropertiesLLRPReaderComposite(false);

		getPropertyValueReaderNameCombo.removeAll();
		List<String> lrSpecNames = getDefinedLRSpecNames();
		if (lrSpecNames != null) {
			for (String lrspecName : lrSpecNames) {
				getPropertyValueReaderNameCombo.add(lrspecName);

			}
		}
	}

	private void addNewReaderNameIfNotPresent() {
		// add a new value to PreferenceConstants.P_ReaderNames
		String readerNamesArray[] = {};
		String readerNames = "";
		boolean found = false;

		readerNamesArray = lrspecConfigPreferences.getString(PreferenceConstants.P_ReaderNames).split(",");

		for (int i = 0; i < readerNamesArray.length; i++) {
			if (lrDefineReaderNameCombo.getText().equals(readerNamesArray[i])) {
				found = true;
			}
		}

		if (found == false) {

			readerNames = lrspecConfigPreferences.getString(PreferenceConstants.P_ReaderNames);

			readerNames = readerNames + "," + lrDefineReaderNameCombo.getText();

			lrspecConfigPreferences.setValue(PreferenceConstants.P_ReaderNames, readerNames);

			readerNamesArray = lrspecConfigPreferences.getString(PreferenceConstants.P_ReaderNames).split(",");

			lrDefineReaderNameCombo.removeAll();
			for (int i = 0; i < readerNamesArray.length; i++) {
				if (!(readerNamesArray[i].equals(""))) {
					lrDefineReaderNameCombo.add(readerNamesArray[i]);
				}
			}
		}
	}

	private class LrDefineExecuteButtonMouseListener extends MouseAdapter {
		public void mouseDown(final MouseEvent e) {
			lrSpecConfiguratorResult = null;

			List<String> lrSpecNames = getDefinedLRSpecNames();

			if (lrSpecNames != null) {
				for (String lrspecName : lrSpecNames) {
					if (lrDefineReaderNameCombo.getText().equals(lrspecName)) {
						MessageDialog.openWarning(container.getShell(), "Error", "This LRSpec name already exists! \n Please choose a diferent one!");
						return;
					}
				}
			}

			// ==================================

			if (lrDefineReaderNameCombo.getText().equals("")) {
				MessageDialog.openWarning(container.getShell(), "Error", "Please spesify an LRSpec Name!");
				return;
			}

			// get lrSpec
			LRSpec lrSpec = getLRSpecFromFile(lrDefineSelectLRSpecCombo);

			org.accada.ale.wsdl.alelr.epcglobal.Define defineParms = new org.accada.ale.wsdl.alelr.epcglobal.Define();
			defineParms.setName(lrDefineReaderNameCombo.getText());
			defineParms.setSpec(lrSpec);

			try {
				lrSpecConfiguratorResult = alelrProxy.define(defineParms);
				MessageDialog.openConfirm(container.getShell(), "Confirmation", "[Define method]: The LRSpec was successfully defined!");
			}
			catch (org.accada.ale.wsdl.alelr.epcglobal.SecurityExceptionResponse e1) {
				// TODO Auto-generated catch block
				MessageDialog.openWarning(container.getShell(), "Error", "[Define method]: Security Exception was thrown.");
				e1.printStackTrace();
			}
			catch (org.accada.ale.wsdl.alelr.epcglobal.ImplementationExceptionResponse e1) {
				// TODO Auto-generated catch block
				MessageDialog.openWarning(container.getShell(), "Error", "[Define method]: Implementation Exception was thrown.");
				e1.printStackTrace();
			}
			catch (org.accada.ale.wsdl.alelr.epcglobal.DuplicateNameExceptionResponse e1) {
				// TODO Auto-generated catch block
				MessageDialog.openWarning(container.getShell(), "Error", "[Define method]: Duplicate Name Exception was thrown.");
				e1.printStackTrace();
			}
			catch (ValidationExceptionResponse e1) {
				// TODO Auto-generated catch block
				MessageDialog.openWarning(container.getShell(), "Error", "[Define method]: Validation Exception was thrown.");
				e1.printStackTrace();
			}

			addNewReaderNameIfNotPresent();

			lrDefineReaderNameCombo.deselectAll();
			lrDefineSelectLRSpecCombo.deselectAll();
		}
	}

	private class LrUpdateExecuteButtonMouseListener extends MouseAdapter {
		public void mouseDown(final MouseEvent e) {
			lrSpecConfiguratorResult = null;

			if (lrUpdateReaderNameCombo.getText().equals("")) {
				MessageDialog.openWarning(container.getShell(), "Error", "Please select a Reader name!");
				return;
			}

			// get lrSpec
			LRSpec lrSpec = getLRSpecFromFile(lrUpdateSelectLRSpecCombo);

			org.accada.ale.wsdl.alelr.epcglobal.Update updateParms = new org.accada.ale.wsdl.alelr.epcglobal.Update();
			updateParms.setName(lrUpdateReaderNameCombo.getText());
			updateParms.setSpec(lrSpec);

			try {
				lrSpecConfiguratorResult = alelrProxy.update(updateParms);

				MessageDialog.openConfirm(container.getShell(), "Confirmation", "[Update method]: The LRSpec was successfully updated!");
			}
			catch (org.accada.ale.wsdl.alelr.epcglobal.ReaderLoopExceptionResponse e1) {
				// TODO Auto-generated catch block
				MessageDialog.openWarning(container.getShell(), "[Update method]: Reader Loop Exception was thrown.", e1.getMessage());
				e1.printStackTrace();
			}
			catch (org.accada.ale.wsdl.alelr.epcglobal.SecurityExceptionResponse e1) {
				// TODO Auto-generated catch block
				MessageDialog.openWarning(container.getShell(), "[Update method]: Security Exception was thrown.", e1.getMessage());
				e1.printStackTrace();
			}
			catch (org.accada.ale.wsdl.alelr.epcglobal.InUseExceptionResponse e1) {
				// TODO Auto-generated catch block
				MessageDialog.openWarning(container.getShell(), "[Update method]: In Use Exception was thrown.", e1.getMessage());
				e1.printStackTrace();
			}
			catch (org.accada.ale.wsdl.alelr.epcglobal.ImplementationExceptionResponse e1) {
				// TODO Auto-generated catch block
				MessageDialog.openWarning(container.getShell(), "[Update method]: Implementation Exception was thrown.", e1.getMessage());
				e1.printStackTrace();
			}
			catch (org.accada.ale.wsdl.alelr.epcglobal.ImmutableReaderExceptionResponse e1) {
				// TODO Auto-generated catch block
				MessageDialog.openWarning(container.getShell(), "[Update method]: Immutable Reader Exception was thrown.", e1.getMessage());
				e1.printStackTrace();
			}
			catch (org.accada.ale.wsdl.alelr.epcglobal.NoSuchNameExceptionResponse e1) {
				// TODO Auto-generated catch block
				MessageDialog.openWarning(container.getShell(), "[Update method]: No Such Name Exception was thrown.", e1.getMessage());
				e1.printStackTrace();
			}
			catch (org.accada.ale.wsdl.alelr.epcglobal.ValidationExceptionResponse e1) {
				// TODO Auto-generated catch block
				MessageDialog.openWarning(container.getShell(), "[Update method]: Validation Exception was thrown.", e1.getMessage());
				e1.printStackTrace();
			}

			lrUpdateReaderNameCombo.deselectAll();
			lrUpdateSelectLRSpecCombo.deselectAll();

		}
	}

	private class LrUndefineExecuteButtonMouseListener extends MouseAdapter {
		public void mouseDown(final MouseEvent e) {
			String lrspecName = null;
			lrSpecConfiguratorResult = null;

			lrspecName = lrUndefineReaderNameCombo.getText();

			if (lrspecName == null || "".equals(lrspecName)) {
				MessageDialog.openWarning(container.getShell(), "Error", "Reader name field should be specified!");
				return;
			}

			org.accada.ale.wsdl.alelr.epcglobal.Undefine undefineParms = new org.accada.ale.wsdl.alelr.epcglobal.Undefine();
			undefineParms.setName(lrspecName);

			try {
				lrSpecConfiguratorResult = alelrProxy.undefine(undefineParms);

				MessageDialog.openConfirm(container.getShell(), "Confirmation", "[Undefine method]: The LRSpec was successfully undefined!");
			}
			catch (org.accada.ale.wsdl.alelr.epcglobal.NoSuchNameExceptionResponse e1) {
				// TODO Auto-generated catch block
				MessageDialog.openWarning(container.getShell(), "[Undefine method]: No Such Name Exception was thrown.", e1.getMessage());
				e1.printStackTrace();
			}
			catch (org.accada.ale.wsdl.alelr.epcglobal.ImplementationExceptionResponse e1) {
				// TODO Auto-generated catch block
				MessageDialog.openWarning(container.getShell(), "[Undefine method]: Implementation Exception was thrown.", e1.getMessage());
				e1.printStackTrace();
			}
			catch (org.accada.ale.wsdl.alelr.epcglobal.SecurityExceptionResponse e1) {
				// TODO Auto-generated catch block
				MessageDialog.openWarning(container.getShell(), "[Undefine method]: Security Exception was thrown.", e1.getMessage());
				e1.printStackTrace();
			}
			catch (org.accada.ale.wsdl.alelr.epcglobal.InUseExceptionResponse e1) {
				// TODO Auto-generated catch block
				MessageDialog.openWarning(container.getShell(), "[Undefine method]: In Use Exception was thrown.", e1.getMessage());
				e1.printStackTrace();
			}
			catch (org.accada.ale.wsdl.alelr.epcglobal.ImmutableReaderExceptionResponse e1) {
				// TODO Auto-generated catch block
				MessageDialog.openWarning(container.getShell(), "[Undefine method]: Immutable Reader Exception was thrown.", e1.getMessage());
				e1.printStackTrace();
			}

			lrUndefineReaderNameCombo.removeAll();

			List<String> lrSpecNames = getDefinedLRSpecNames();

			if (lrSpecNames != null) {
				for (String curentLrSpecName : lrSpecNames) {
					lrUndefineReaderNameCombo.add(curentLrSpecName);

				}
			}
		}
	}

	private class LrGetLRSpecExecuteButtonMouseListener extends MouseAdapter {
		public void mouseDown(final MouseEvent e) {
			String lrspecName = null;
			lrSpecConfiguratorResult = null;

			lrspecName = lrGetLRSpecReaderNameCombo.getText();

			if (lrspecName == null || "".equals(lrspecName)) {
				MessageDialog.openWarning(container.getShell(), "Error", "Reader name field should be specified!");
				return;
			}

			GetLRSpec getLRSpecParms = new GetLRSpec();
			getLRSpecParms.setName(lrspecName);

			try {
				lrSpecConfiguratorResult = alelrProxy.getLRSpec(getLRSpecParms);
			}
			catch (org.accada.ale.wsdl.alelr.epcglobal.SecurityExceptionResponse e1) {
				// TODO Auto-generated catch block
				MessageDialog.openWarning(container.getShell(), "[getLRSpec method]: Security Exception was thrown.", e1.getMessage());
				e1.printStackTrace();
			}
			catch (org.accada.ale.wsdl.alelr.epcglobal.ImplementationExceptionResponse e1) {
				// TODO Auto-generated catch block
				MessageDialog.openWarning(container.getShell(), "[getLRSpec method]: Implementation Exception was thrown.", e1.getMessage());
				e1.printStackTrace();
			}
			catch (org.accada.ale.wsdl.alelr.epcglobal.NoSuchNameExceptionResponse e1) {
				// TODO Auto-generated catch block
				MessageDialog.openWarning(container.getShell(), "[getLRSpec method]: No Such Name Exception was thrown.", e1.getMessage());
				e1.printStackTrace();
			}

			showLRSpecConfiguratorResult(lrSpecConfiguratorResult);

			lrGetLRSpecReaderNameCombo.deselectAll();
		}
	}

	private class AddReadersExecuteButtonMouseListener extends MouseAdapter {
		public void mouseDown(final MouseEvent e) {
			lrSpecConfiguratorResult = null;

			if (addReadersReaderNameCombo.getText().equals("") || addReadersReadersToBeAddedList.getItemCount() == 0) {
				MessageDialog.openWarning(container.getShell(), "Error", "All fields are mandatory!");
			}
			else {
				AddReaders addReaders = new AddReaders();
				addReaders.setName(addReadersReaderNameCombo.getText());

				AddReaders.Readers readers = new AddReaders.Readers();
				for (int i = 0; i < addReadersReadersToBeAddedList.getItemCount(); i++) {
					readers.getReader().add(addReadersReadersToBeAddedList.getItem(i));
				}

				addReaders.setReaders(readers);

				try {
					lrSpecConfiguratorResult = alelrProxy.addReaders(addReaders);
					MessageDialog.openConfirm(container.getShell(), "Confirmation", "[addReaders method]: Readers were successfully added!");
				}
				catch (ReaderLoopExceptionResponse e1) {
					// TODO Auto-generated catch block
					MessageDialog.openWarning(container.getShell(), "[addReaders method]: Reader Loop Exception was thrown.", e1.getMessage());
					e1.printStackTrace();
				}
				catch (NonCompositeReaderExceptionResponse e1) {
					// TODO Auto-generated catch block
					MessageDialog.openWarning(container.getShell(), "[addReaders method]: Non Composite Exception was thrown.", e1.getMessage());
					e1.printStackTrace();
				}
				catch (org.accada.ale.wsdl.alelr.epcglobal.SecurityExceptionResponse e1) {
					// TODO Auto-generated catch block
					MessageDialog.openWarning(container.getShell(), "[addReaders method]: Security Exception was thrown.", e1.getMessage());
					e1.printStackTrace();
				}
				catch (InUseExceptionResponse e1) {
					// TODO Auto-generated catch block
					MessageDialog.openWarning(container.getShell(), "[addReaders method]: In Use Exception was thrown.", e1.getMessage());
					e1.printStackTrace();
				}
				catch (org.accada.ale.wsdl.alelr.epcglobal.ImplementationExceptionResponse e1) {
					// TODO Auto-generated catch block
					MessageDialog.openWarning(container.getShell(), "[addReaders method]: Implementation Exception was thrown.", e1.getMessage());
					e1.printStackTrace();
				}
				catch (ImmutableReaderExceptionResponse e1) {
					// TODO Auto-generated catch block
					MessageDialog.openWarning(container.getShell(), "[addReaders method]: Immutable Reader Exception was thrown.", e1.getMessage());
					e1.printStackTrace();
				}
				catch (org.accada.ale.wsdl.alelr.epcglobal.NoSuchNameExceptionResponse e1) {
					// TODO Auto-generated catch block
					MessageDialog.openWarning(container.getShell(), "[addReaders method]: No Such Name Exception was thrown.", e1.getMessage());
					e1.printStackTrace();
				}
				catch (ValidationExceptionResponse e1) {
					// TODO Auto-generated catch block
					MessageDialog.openWarning(container.getShell(), "[addReaders method]: Validation Exception was thrown.", e1.getMessage());
					e1.printStackTrace();
				}

				addReadersReadersToBeAddedList.removeAll();
				addReadersReaderNameCombo.deselectAll();
				addReadersSelectReaderCombo.deselectAll();
			}
		}
	}

	private class SetReadersExecuteButtonMouseListener extends MouseAdapter {
		public void mouseDown(final MouseEvent e) {
			lrSpecConfiguratorResult = null;

			if (setReadersReaderNameCombo.getText().equals("") || setReadersNewReadersList.getItemCount() == 0) {
				MessageDialog.openWarning(container.getShell(), "Error", "All fields are mandatory!");
			}
			else {
				SetReaders setReaders = new SetReaders();
				setReaders.setName(setReadersReaderNameCombo.getText());

				SetReaders.Readers readers = new SetReaders.Readers();
				for (int i = 0; i < setReadersNewReadersList.getItemCount(); i++) {
					readers.getReader().add(setReadersNewReadersList.getItem(i));
				}

				setReaders.setReaders(readers);

				try {
					lrSpecConfiguratorResult = alelrProxy.setReaders(setReaders);
					MessageDialog.openConfirm(container.getShell(), "Confirmation", "[setReaders method]: Readers were successfully set!");
				}
				catch (ReaderLoopExceptionResponse e1) {
					// TODO Auto-generated catch block
					MessageDialog.openWarning(container.getShell(), "[setReaders method]: Reader Loop Exception was thrown.", e1.getMessage());
					e1.printStackTrace();
				}
				catch (NonCompositeReaderExceptionResponse e1) {
					// TODO Auto-generated catch block
					MessageDialog.openWarning(container.getShell(), "[setReaders method]: Non Composite Exception was thrown.", e1.getMessage());
					e1.printStackTrace();
				}
				catch (org.accada.ale.wsdl.alelr.epcglobal.SecurityExceptionResponse e1) {
					// TODO Auto-generated catch block
					MessageDialog.openWarning(container.getShell(), "[setReaders method]: Security Exception was thrown.", e1.getMessage());
					e1.printStackTrace();
				}
				catch (InUseExceptionResponse e1) {
					// TODO Auto-generated catch block
					MessageDialog.openWarning(container.getShell(), "[setReaders method]: In Use Exception was thrown.", e1.getMessage());
					e1.printStackTrace();
				}
				catch (org.accada.ale.wsdl.alelr.epcglobal.ImplementationExceptionResponse e1) {
					// TODO Auto-generated catch block
					MessageDialog.openWarning(container.getShell(), "[setReaders method]: Implementation Exception was thrown.", e1.getMessage());
					e1.printStackTrace();
				}
				catch (ImmutableReaderExceptionResponse e1) {
					// TODO Auto-generated catch block
					MessageDialog.openWarning(container.getShell(), "[setReaders method]: Immutable Reader Exception was thrown.", e1.getMessage());
					e1.printStackTrace();
				}
				catch (org.accada.ale.wsdl.alelr.epcglobal.NoSuchNameExceptionResponse e1) {
					// TODO Auto-generated catch block
					MessageDialog.openWarning(container.getShell(), "[setReaders method]: No Such Name Exception was thrown.", e1.getMessage());
					e1.printStackTrace();
				}
				catch (ValidationExceptionResponse e1) {
					// TODO Auto-generated catch block
					MessageDialog.openWarning(container.getShell(), "[setReaders method]: Validation Exception was thrown.", e1.getMessage());
					e1.printStackTrace();
				}

				setReadersNewReadersList.removeAll();
				setReadersReaderNameCombo.deselectAll();
				setReadersSelectReaderCombo.deselectAll();
			}
		}
	}

	private class RemoveReadersExecuteButtonMouseListener extends MouseAdapter {
		public void mouseDown(final MouseEvent e) {
			lrSpecConfiguratorResult = null;

			if (removeReadersReaderNameCombo.getText().equals("") || removeReadersReadersToBeRemovedList.getItemCount() == 0) {
				MessageDialog.openWarning(container.getShell(), "Error", "All fields are mandatory!");
			}
			else {
				RemoveReaders removeReaders = new RemoveReaders();
				removeReaders.setName(removeReadersReaderNameCombo.getText());

				RemoveReaders.Readers readers = new RemoveReaders.Readers();
				for (int i = 0; i < removeReadersReadersToBeRemovedList.getItemCount(); i++) {
					readers.getReader().add(removeReadersReadersToBeRemovedList.getItem(i));
				}

				removeReaders.setReaders(readers);

				try {
					lrSpecConfiguratorResult = alelrProxy.removeReaders(removeReaders);
					MessageDialog.openConfirm(container.getShell(), "Confirmation", "[removeReaders method]: Readers were successfully removed!");
				}
				catch (NonCompositeReaderExceptionResponse e1) {
					// TODO Auto-generated catch block
					MessageDialog.openWarning(container.getShell(), "[removeReaders method]: Non Composite Exception was thrown.", e1.getMessage());
					e1.printStackTrace();
				}
				catch (org.accada.ale.wsdl.alelr.epcglobal.SecurityExceptionResponse e1) {
					// TODO Auto-generated catch block
					MessageDialog.openWarning(container.getShell(), "[removeReaders method]: Security Exception was thrown.", e1.getMessage());
					e1.printStackTrace();
				}
				catch (InUseExceptionResponse e1) {
					// TODO Auto-generated catch block
					MessageDialog.openWarning(container.getShell(), "[removeReaders method]: In Use Exception was thrown.", e1.getMessage());
					e1.printStackTrace();
				}
				catch (org.accada.ale.wsdl.alelr.epcglobal.ImplementationExceptionResponse e1) {
					// TODO Auto-generated catch block
					MessageDialog.openWarning(container.getShell(), "[removeReaders method]: Implementation Exception was thrown.", e1.getMessage());
					e1.printStackTrace();
				}
				catch (ImmutableReaderExceptionResponse e1) {
					// TODO Auto-generated catch block
					MessageDialog
							.openWarning(container.getShell(), "[removeReaders method]: Immutable Reader Exception was thrown.", e1.getMessage());
					e1.printStackTrace();
				}
				catch (org.accada.ale.wsdl.alelr.epcglobal.NoSuchNameExceptionResponse e1) {
					// TODO Auto-generated catch block
					MessageDialog.openWarning(container.getShell(), "[removeReaders method]: No Such Name Exception was thrown.", e1.getMessage());
					e1.printStackTrace();
				}

				removeReadersReadersToBeRemovedList.removeAll();
				removeReadersReaderNameCombo.deselectAll();
				removeReadersSelectReaderCombo.deselectAll();

			}
		}
	}

	private class GetPropertyValueExecuteButtonMouseListener extends MouseAdapter {
		public void mouseDown(final MouseEvent e) {
			lrSpecConfiguratorResult = null;

			if (getPropertyValueReaderNameCombo.getText().equals("") || getPropertyValuePropertyNameCombo.getText().equals("")) {
				MessageDialog.openWarning(container.getShell(), "Error", "All fields are mandatory!");
				return;
			}

			GetPropertyValue getPropertyValueParms = new GetPropertyValue();
			getPropertyValueParms.setName(getPropertyValueReaderNameCombo.getText());
			getPropertyValueParms.setPropertyName(getPropertyValuePropertyNameCombo.getText());

			try {
				lrSpecConfiguratorResult = alelrProxy.getPropertyValue(getPropertyValueParms);
			}
			catch (org.accada.ale.wsdl.alelr.epcglobal.SecurityExceptionResponse e1) {
				// TODO Auto-generated catch block
				MessageDialog.openWarning(container.getShell(), "[getPropertyValue method]: Security Exception was thrown.", e1.getMessage());
				e1.printStackTrace();
			}
			catch (org.accada.ale.wsdl.alelr.epcglobal.ImplementationExceptionResponse e1) {
				// TODO Auto-generated catch block
				MessageDialog.openWarning(container.getShell(), "[getPropertyValue method]: Implementation Exception was thrown.", e1.getMessage());
				e1.printStackTrace();
			}
			catch (org.accada.ale.wsdl.alelr.epcglobal.NoSuchNameExceptionResponse e1) {
				// TODO Auto-generated catch block
				MessageDialog.openWarning(container.getShell(), "[getPropertyValue method]: No Such Name Exception was thrown.", e1.getMessage());
				e1.printStackTrace();
			}

			writeToConsole(getPropertyValuePropertyNameCombo.getText() + ": ");
			showLRSpecConfiguratorResult(lrSpecConfiguratorResult);

			getPropertyValueReaderNameCombo.deselectAll();
			getPropertyValuePropertyNameCombo.removeAll();
		}
	}

	private class DefineToolItemSelectionListener extends SelectionAdapter {
		public void widgetSelected(final SelectionEvent e) {
			initializeAleLrProxy();
			// removeConsole();

			alelrMethodGroup.setVisible(true);
			alelrMethodGroup.setText("Define");
			loadDefineLRSpecComposite();
		}
	}

	private class UpdateToolItemSelectionListener extends SelectionAdapter {
		public void widgetSelected(final SelectionEvent e) {
			initializeAleLrProxy();
			// removeConsole();

			alelrMethodGroup.setVisible(true);
			alelrMethodGroup.setText("Update");
			loadUpdateLRSpecComposite();
		}
	}

	private class UndefineToolItemSelectionListener extends SelectionAdapter {
		public void widgetSelected(final SelectionEvent e) {
			initializeAleLrProxy();
			// removeConsole();

			alelrMethodGroup.setVisible(true);
			alelrMethodGroup.setText("Undefine");
			loadUndefineLRSpecComposite();
		}
	}

	private class AddReadersToolItemSelectionListener extends SelectionAdapter {
		public void widgetSelected(final SelectionEvent e) {
			initializeAleLrProxy();
			// removeConsole();

			alelrMethodGroup.setVisible(true);
			alelrMethodGroup.setText("AddReaders");
			loadAddReadersComposite();
		}
	}

	private class SetReadersToolItemSelectionListener extends SelectionAdapter {
		public void widgetSelected(final SelectionEvent e) {
			initializeAleLrProxy();
			// removeConsole();

			alelrMethodGroup.setVisible(true);
			alelrMethodGroup.setText("SetReaders");
			loadSetReadersComposite();
		}
	}

	private class RemoveReadersToolItemSelectionListener extends SelectionAdapter {
		public void widgetSelected(final SelectionEvent e) {
			initializeAleLrProxy();
			// removeConsole();

			alelrMethodGroup.setVisible(true);
			alelrMethodGroup.setText("RemoveReaders");
			loadRemoveReadersComposite();
		}
	}

	private class SetPropertiesToolItemSelectionListener extends SelectionAdapter {
		public void widgetSelected(final SelectionEvent e) {
			initializeAleLrProxy();
			// removeConsole();

			alelrMethodGroup.setVisible(true);
			alelrMethodGroup.setText("SetProperties");
			loadSetPropertiesComposite();
		}
	}

	private class GetLogicalReaderNamesToolItemSelectionListener extends SelectionAdapter {
		public void widgetSelected(final SelectionEvent e) {
			initializeAleLrProxy();
			initiateConsole();

			alelrMethodGroup.setVisible(false);
			getLogicalReaderNames();
		}
	}

	private class GetLRSpecToolItemSelectionListener extends SelectionAdapter {
		public void widgetSelected(final SelectionEvent e) {
			initializeAleLrProxy();
			initiateConsole();

			alelrMethodGroup.setVisible(true);
			alelrMethodGroup.setText("GetLRSpec");
			loadGetLRSpecComposite();
		}
	}

	private class GetPropertyValueToolItemSelectionListener extends SelectionAdapter {
		public void widgetSelected(final SelectionEvent e) {
			initializeAleLrProxy();
			initiateConsole();

			alelrMethodGroup.setVisible(true);
			alelrMethodGroup.setText("GetPropertyValue");
			loadGetPropertyValueComposite();
		}
	}

	private class GetStandardVersionToolItemSelectionListener extends SelectionAdapter {
		public void widgetSelected(final SelectionEvent e) {
			initializeAleLrProxy();
			initiateConsole();

			alelrMethodGroup.setVisible(false);
			getStandardVersionLr();
		}
	}

	private class GetVendorVersionToolItemSelectionListener extends SelectionAdapter {
		public void widgetSelected(final SelectionEvent e) {
			initializeAleLrProxy();
			initiateConsole();

			alelrMethodGroup.setVisible(false);
			getVendorVersionLr();
		}
	}

	private class AddReadersAddReaderButtonMouseListener extends MouseAdapter {
		public void mouseDown(final MouseEvent e) {
			if (addReadersSelectReaderCombo.getText().equals("")) {
				MessageDialog.openWarning(container.getShell(), "Error", "No reader has been entered!");
			}
			else {
				addReadersReadersToBeAddedList.add(addReadersSelectReaderCombo.getText());
				addReadersSelectReaderCombo.deselectAll();
			}
		}
	}

	private class AddReadersDeleteReaderButtonMouseListener extends MouseAdapter {
		public void mouseDown(final MouseEvent e) {
			if (addReadersReadersToBeAddedList.getItemCount() == 0) {
				MessageDialog.openWarning(container.getShell(), "Error", "No readers available to delete!");
			}
			else {
				addReadersReadersToBeAddedList.remove(addReadersReadersToBeAddedListSelectedIndex);
			}
		}
	}

	private class SetReadersAddReaderButtonMouseListener extends MouseAdapter {
		public void mouseDown(final MouseEvent e) {
			if (setReadersSelectReaderCombo.getText().equals("")) {
				MessageDialog.openWarning(container.getShell(), "Error", "No reader has been entered!");
			}
			else {
				setReadersNewReadersList.add(setReadersSelectReaderCombo.getText());
				setReadersSelectReaderCombo.deselectAll();
			}
		}
	}

	private class SetReadersDeleteReaderButtonMouseListener extends MouseAdapter {
		public void mouseDown(final MouseEvent e) {
			if (setReadersNewReadersList.getItemCount() == 0) {
				MessageDialog.openWarning(container.getShell(), "Error", "No readers available to delete!");
			}
			else {
				setReadersNewReadersList.remove(setReadersNewReadersListSelectedIndex);
			}
		}
	}

	private class RemoveReadersAddReaderButtonMouseListener extends MouseAdapter {
		public void mouseDown(final MouseEvent e) {
			if (removeReadersSelectReaderCombo.getText().equals("")) {
				MessageDialog.openWarning(container.getShell(), "Error", "No reader has been entered!");
			}
			else {
				removeReadersReadersToBeRemovedList.add(removeReadersSelectReaderCombo.getText());
				removeReadersSelectReaderCombo.deselectAll();
			}
		}
	}

	private class RemoveReadersDeleteReaderButtonMouseListener extends MouseAdapter {
		public void mouseDown(final MouseEvent e) {
			if (removeReadersReadersToBeRemovedList.getItemCount() == 0) {
				MessageDialog.openWarning(container.getShell(), "Error", "No readers available to delete!");
			}
			else {
				removeReadersReadersToBeRemovedList.remove(removeReadersReadersToBeRemovedListSelectedIndex);
			}
		}
	}

	public IPreferenceStore getLrspecConfigPreferences() {
		return lrspecConfigPreferences;
	}

	public void setLrspecConfigPreferences(IPreferenceStore lrspecConfigPreferences) {
		this.lrspecConfigPreferences = lrspecConfigPreferences;
	}

}
