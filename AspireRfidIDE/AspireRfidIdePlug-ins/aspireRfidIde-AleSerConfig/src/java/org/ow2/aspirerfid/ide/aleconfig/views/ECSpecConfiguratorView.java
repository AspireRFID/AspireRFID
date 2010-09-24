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

import java.io.CharArrayWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import org.accada.ale.util.DeserializerUtil;
import org.accada.ale.util.SerializerUtil;
import org.accada.ale.wsdl.ale.epcglobal.ALEServicePortType;
import org.accada.ale.wsdl.ale.epcglobal.ArrayOfString;
import org.accada.ale.wsdl.ale.epcglobal.Define;
import org.accada.ale.wsdl.ale.epcglobal.DuplicateNameExceptionResponse;
import org.accada.ale.wsdl.ale.epcglobal.DuplicateSubscriptionExceptionResponse;
import org.accada.ale.wsdl.ale.epcglobal.ECSpecValidationExceptionResponse;
import org.accada.ale.wsdl.ale.epcglobal.EmptyParms;
import org.accada.ale.wsdl.ale.epcglobal.GetECSpec;
import org.accada.ale.wsdl.ale.epcglobal.GetSubscribers;
import org.accada.ale.wsdl.ale.epcglobal.Immediate;
import org.accada.ale.wsdl.ale.epcglobal.ImplementationExceptionResponse;
import org.accada.ale.wsdl.ale.epcglobal.InvalidURIExceptionResponse;
import org.accada.ale.wsdl.ale.epcglobal.NoSuchNameExceptionResponse;
import org.accada.ale.wsdl.ale.epcglobal.NoSuchSubscriberExceptionResponse;
import org.accada.ale.wsdl.ale.epcglobal.Poll;
import org.accada.ale.wsdl.ale.epcglobal.SecurityExceptionResponse;
import org.accada.ale.wsdl.ale.epcglobal.Subscribe;
import org.accada.ale.wsdl.ale.epcglobal.Undefine;
import org.accada.ale.wsdl.ale.epcglobal.Unsubscribe;
import org.accada.ale.xsd.ale.epcglobal.ECReports;
import org.accada.ale.xsd.ale.epcglobal.ECSpec;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;
import org.eclipse.ui.console.ConsolePlugin;
import org.eclipse.ui.console.IConsole;
import org.eclipse.ui.console.IConsoleManager;
import org.eclipse.ui.console.IOConsole;
import org.eclipse.ui.console.IOConsoleOutputStream;
import org.eclipse.ui.part.ViewPart;
import org.ow2.aspirerfid.ide.aleconfig.Activator;
import org.ow2.aspirerfid.ide.aleconfig.preferences.PreferenceConstants;
import org.ow2.aspirerfid.ide.aleconfig.utils.swtdesigner.ResourceManager;
import org.ow2.aspirerfid.ide.aleconfig.utils.swtdesigner.SWTResourceManager;

/**
 * @author Vasso Koletti e-mail: vkol@ait.edu.gr
 * @author Nikos Kefalakis (nkef) e-mail: nkef@ait.edu.gr
 * 
 */
public class ECSpecConfiguratorView extends ViewPart {
	private Combo ecSpecNameCombo;
	private Combo unsubscribeECSpecNotificationURIListCombo;
	private Combo subscribeECSpecNotificationURIListCombo;
	private Combo immediateECSpecCombo;
	private Composite immediateECSpecComposite;
	private Combo pollSpecNameCombo;
	private Composite pollECSpecComposite;
	private Combo unsubscribeECSpecListcombo;
	private Composite unsubscribeECSpecComposite;
	private Combo subscribeECSpecListcombo;
	private Composite subscribeECSpecComposite;
	private Combo getECSpecECSpecListcombo;
	private Composite getECSpecECSpecComposite;
	private Combo undefineECSpecListcombo;
	private Composite undefineECSpecComposite;
	private Composite ecspecConfiguratorComposite;
	private Combo ecSpecListcombo;
	private Composite defineECSpecComposite;
	private Combo getSubscribersSpecNameCombo;
	private Composite getSubscribersECSpecComposite;
	private Group aleMethodGroup;
	private static String fileSeparator = System.getProperty("file.separator");

	Composite container;

	public static final String ID = "org.ow2.aspirerfid.ide.aleserverconfigurator.views.ECSpecConfiguratorView"; //$NON-NLS-1$

	/** ale proxy */
	private ALEServicePortType aleProxy = null;

	/** Returns the preference store for this UI plug-in */
	IPreferenceStore aleConfigPreferences = Activator.getDefault()
			.getPreferenceStore();

	/**
	 * ECSpec Configurator Result
	 */
	Object ecSpecConfiguratorResult = null;

	// ----------Console Initialization and Use----------------
	IOConsoleOutputStream aleServerConfiguratorOutputConsole;
	IOConsole aleServerConfiguratorConsole;
	IConsoleManager manager;
	IConsole[] consolesx;

	public ECSpecConfiguratorView() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void createPartControl(Composite parent) {
		container = new Composite(parent, SWT.NONE);
		container.setLayout(new FillLayout());

		final ScrolledComposite ecspecConfiguratorScrolledComposite = new ScrolledComposite(
				container, SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL);

		ecspecConfiguratorComposite = new Composite(
				ecspecConfiguratorScrolledComposite, SWT.NONE);
		ecspecConfiguratorComposite.setLocation(0, 0);
		ecspecConfiguratorComposite.setSize(674, 329);

		ecspecConfiguratorScrolledComposite
				.setContent(ecspecConfiguratorComposite);

		final Group aleMethodsGroup_1 = new Group(ecspecConfiguratorComposite,
				SWT.NONE);
		aleMethodsGroup_1.setText("ALE Methods");
		aleMethodsGroup_1.setBounds(10, 0, 614, 316);

		final ToolBar ecspecAleServerConfiguratorToolBar = new ToolBar(
				aleMethodsGroup_1, SWT.BORDER);
		ecspecAleServerConfiguratorToolBar.setBounds(100, 20, 511, 50);
		ecspecAleServerConfiguratorToolBar.setBackground(SWTResourceManager
				.getColor(179, 213, 186));

		final ToolItem getECSpecToolItem = new ToolItem(
				ecspecAleServerConfiguratorToolBar, SWT.PUSH);
		getECSpecToolItem
				.addSelectionListener(new GetECSpecToolItemSelectionListener());
		getECSpecToolItem.setImage(ResourceManager.getPluginImage(Activator
				.getDefault(), "icons/img/s_tbl.png"));
		getECSpecToolItem.setText("GetECSpec");

		final ToolItem getSubscribersToolItem = new ToolItem(
				ecspecAleServerConfiguratorToolBar, SWT.PUSH);
		getSubscribersToolItem
				.addSelectionListener(new GetSubscribersToolItemSelectionListener());
		getSubscribersToolItem.setImage(ResourceManager.getPluginImage(
				Activator.getDefault(), "icons/img/b_browse.png"));
		getSubscribersToolItem.setText("GetSubscribers");

		final ToolItem getECSpecNamesToolItem = new ToolItem(
				ecspecAleServerConfiguratorToolBar, SWT.PUSH);
		getECSpecNamesToolItem
				.addSelectionListener(new GetECSpecNamesToolItemSelectionListener());
		getECSpecNamesToolItem.setImage(ResourceManager.getPluginImage(
				Activator.getDefault(), "icons/img/b_newtbl.png"));
		getECSpecNamesToolItem.setText("GetECSpecNames");

		final ToolItem getVendorVersionToolItem = new ToolItem(
				ecspecAleServerConfiguratorToolBar, SWT.PUSH);
		getVendorVersionToolItem
				.addSelectionListener(new GetVendorVersionToolItemSelectionListener());
		getVendorVersionToolItem.setImage(ResourceManager.getPluginImage(
				Activator.getDefault(), "icons/img/b_help.png"));
		getVendorVersionToolItem.setText("GetVendorVersion");

		final ToolItem getStandardVersionToolItem = new ToolItem(
				ecspecAleServerConfiguratorToolBar, SWT.PUSH);
		getStandardVersionToolItem
				.addSelectionListener(new GetStandardVersionToolItemSelectionListener());
		getStandardVersionToolItem.setImage(ResourceManager.getPluginImage(
				Activator.getDefault(), "icons/img/s_really.png"));
		getStandardVersionToolItem.setText("GetStandardVersion");

		final ToolBar toolBar = new ToolBar(aleMethodsGroup_1, SWT.VERTICAL
				| SWT.BORDER);
		toolBar.setBounds(10, 65, 93, 238);
		toolBar.setBackground(SWTResourceManager.getColor(179, 213, 186));

		final ToolItem defineToolItem = new ToolItem(toolBar, SWT.PUSH);
		defineToolItem
				.addSelectionListener(new DefineToolItemSelectionListener());
		defineToolItem.setImage(ResourceManager.getPluginImage(Activator
				.getDefault(), "icons/pencil_16.gif"));
		defineToolItem.setText("Define");

		final ToolItem undefineToolItem = new ToolItem(toolBar, SWT.PUSH);
		undefineToolItem
				.addSelectionListener(new UndefineToolItemSelectionListener());
		undefineToolItem.setImage(ResourceManager.getPluginImage(Activator
				.getDefault(), "icons/img/b_tblops.png"));
		undefineToolItem.setText("Undefine");

		final ToolItem subscribeToolItem = new ToolItem(toolBar, SWT.PUSH);
		subscribeToolItem
				.addSelectionListener(new SubscribeToolItemSelectionListener());
		subscribeToolItem.setImage(ResourceManager.getPluginImage(Activator
				.getDefault(), "icons/img/b_edit.png"));
		subscribeToolItem.setText("Subscribe");

		final ToolItem unsubscribeToolItem = new ToolItem(toolBar, SWT.PUSH);
		unsubscribeToolItem
				.addSelectionListener(new UnsubscribeToolItemSelectionListener());
		unsubscribeToolItem.setImage(ResourceManager.getPluginImage(Activator
				.getDefault(), "icons/img/bd_drop.png"));
		unsubscribeToolItem.setText("Unsubscribe");

		final ToolItem immediateToolItem = new ToolItem(toolBar, SWT.PUSH);
		immediateToolItem
				.addSelectionListener(new ImmediateToolItemSelectionListener());
		immediateToolItem.setImage(ResourceManager.getPluginImage(Activator
				.getDefault(), "icons/img/b_tipp.png"));
		immediateToolItem.setText("Immediate");

		final ToolItem pollToolItem = new ToolItem(toolBar, SWT.PUSH);
		pollToolItem.addSelectionListener(new PollToolItemSelectionListener());
		pollToolItem.setImage(ResourceManager.getPluginImage(Activator
				.getDefault(), "icons/img/b_view.png"));
		pollToolItem.setText("Poll");

		aleMethodGroup = new Group(aleMethodsGroup_1, SWT.NONE);
		aleMethodGroup.setBounds(110, 85, 501, 130);
		aleMethodGroup.setVisible(false);

		getSubscribersECSpecComposite = new Composite(aleMethodGroup, SWT.NONE);
		getSubscribersECSpecComposite.setBounds(10, 16, 481, 105);
		getSubscribersECSpecComposite.setVisible(false);

		final Label specNameLabel = new Label(getSubscribersECSpecComposite,
				SWT.NONE);
		specNameLabel.setBounds(5, 5, 88, 21);
		specNameLabel.setText("Spec name:");

		getSubscribersSpecNameCombo = new Combo(getSubscribersECSpecComposite,
				SWT.READ_ONLY);
		getSubscribersSpecNameCombo.setBackground(SWTResourceManager.getColor(
				230, 230, 230));
		getSubscribersSpecNameCombo.setBounds(96, 2, 375, 24);

		final Label label_5 = new Label(getSubscribersECSpecComposite,
				SWT.HORIZONTAL | SWT.SEPARATOR);
		label_5.setBounds(5, 60, 466, 10);
		label_5.setText("Label");

		final Button getSubscribersExecuteButton = new Button(
				getSubscribersECSpecComposite, SWT.NONE);
		getSubscribersExecuteButton
				.addSelectionListener(new GetSubscribersExecuteButtonSelectionListener());
		getSubscribersExecuteButton.setImage(ResourceManager.getPluginImage(
				Activator.getDefault(), "icons/services_execute.gif"));
		getSubscribersExecuteButton.setBounds(192, 76, 80, 24);
		getSubscribersExecuteButton.setText("Execute");

		defineECSpecComposite = new Composite(aleMethodGroup, SWT.NONE);
		defineECSpecComposite.setBounds(10, 16, 481, 105);
		defineECSpecComposite.setVisible(false);

		final Label setSpecNameLabel = new Label(defineECSpecComposite,
				SWT.NONE);
		setSpecNameLabel.setText("Spec Name:");
		setSpecNameLabel.setBounds(5, 5, 88, 21);

		final Label setSpecNameLabel_1 = new Label(defineECSpecComposite,
				SWT.NONE);
		setSpecNameLabel_1.setBounds(5, 35, 88, 21);
		setSpecNameLabel_1.setText("Select ECSpec:");

		ecSpecListcombo = new Combo(defineECSpecComposite, SWT.READ_ONLY);
		ecSpecListcombo.setBackground(SWTResourceManager
				.getColor(230, 230, 230));
		ecSpecListcombo.setBounds(96, 32, 375, 24);

		final Label label = new Label(defineECSpecComposite, SWT.SEPARATOR
				| SWT.HORIZONTAL);
		label.setBounds(5, 60, 466, 10);

		final Button defineECSpecButton = new Button(defineECSpecComposite,
				SWT.NONE);
		defineECSpecButton
				.addSelectionListener(new DefineECSpecButtonSelectionListener());
		defineECSpecButton.setImage(ResourceManager.getPluginImage(Activator
				.getDefault(), "icons/services_execute.gif"));
		defineECSpecButton.setText("Execute");
		defineECSpecButton.setBounds(192, 76, 80, 24);

		ecSpecNameCombo = new Combo(defineECSpecComposite, SWT.NONE);
		ecSpecNameCombo.setBackground(SWTResourceManager
				.getColor(230, 230, 230));
		ecSpecNameCombo.setBounds(96, 2, 375, 24);

		undefineECSpecComposite = new Composite(aleMethodGroup, SWT.NONE);
		undefineECSpecComposite.setBounds(10, 16, 481, 105);
		undefineECSpecComposite.setVisible(false);

		final Label setSpecNameLabel_1_1 = new Label(undefineECSpecComposite,
				SWT.NONE);
		setSpecNameLabel_1_1.setBounds(5, 5, 89, 21);
		setSpecNameLabel_1_1.setText("Spec name:");

		final Button undefineECSpecButton = new Button(undefineECSpecComposite,
				SWT.NONE);
		undefineECSpecButton
				.addSelectionListener(new UndefineECSpecButtonSelectionListener());
		undefineECSpecButton.setImage(ResourceManager.getPluginImage(Activator
				.getDefault(), "icons/services_execute.gif"));
		undefineECSpecButton.setBounds(192, 76, 80, 24);
		undefineECSpecButton.setText("Execute");

		final Label label_1 = new Label(undefineECSpecComposite, SWT.SEPARATOR
				| SWT.HORIZONTAL);
		label_1.setBounds(5, 60, 466, 10);

		undefineECSpecListcombo = new Combo(undefineECSpecComposite,
				SWT.READ_ONLY);
		undefineECSpecListcombo.setBackground(SWTResourceManager.getColor(230,
				230, 230));
		undefineECSpecListcombo.setBounds(96, 2, 375, 24);

		getECSpecECSpecComposite = new Composite(aleMethodGroup, SWT.NONE);
		getECSpecECSpecComposite.setBounds(10, 16, 481, 105);
		getECSpecECSpecComposite.setVisible(false);

		final Label setSpecNameLabel_1_1_1 = new Label(
				getECSpecECSpecComposite, SWT.NONE);
		setSpecNameLabel_1_1_1.setBounds(5, 5, 89, 21);
		setSpecNameLabel_1_1_1.setText("Spec name:");

		final Button getECSpecECSpecButton = new Button(
				getECSpecECSpecComposite, SWT.NONE);
		getECSpecECSpecButton
				.addSelectionListener(new GetECSpecECSpecButtonSelectionListener());
		getECSpecECSpecButton.setImage(ResourceManager.getPluginImage(Activator
				.getDefault(), "icons/services_execute.gif"));
		getECSpecECSpecButton.setBounds(192, 76, 80, 24);
		getECSpecECSpecButton.setText("Execute");

		final Label label_2 = new Label(getECSpecECSpecComposite, SWT.SEPARATOR
				| SWT.HORIZONTAL);
		label_2.setBounds(5, 60, 466, 10);

		getECSpecECSpecListcombo = new Combo(getECSpecECSpecComposite,
				SWT.READ_ONLY);
		getECSpecECSpecListcombo.setBackground(SWTResourceManager.getColor(230,
				230, 230));
		getECSpecECSpecListcombo.setBounds(96, 2, 375, 24);

		subscribeECSpecComposite = new Composite(aleMethodGroup, SWT.NONE);
		subscribeECSpecComposite.setBounds(10, 16, 481, 105);
		subscribeECSpecComposite.setVisible(false);

		final Label setSpecNameLabel_2 = new Label(subscribeECSpecComposite,
				SWT.NONE);
		setSpecNameLabel_2.setBounds(5, 5, 88, 21);
		setSpecNameLabel_2.setText("Notification URI:");

		final Label setSpecNameLabel_1_2 = new Label(subscribeECSpecComposite,
				SWT.NONE);
		setSpecNameLabel_1_2.setBounds(5, 35, 88, 21);
		setSpecNameLabel_1_2.setText("Select ECSpec:");

		final Button subscribeECSpecButton = new Button(
				subscribeECSpecComposite, SWT.NONE);
		subscribeECSpecButton
				.addSelectionListener(new SubscribeECSpecButtonSelectionListener());
		subscribeECSpecButton.setImage(ResourceManager.getPluginImage(Activator
				.getDefault(), "icons/services_execute.gif"));
		subscribeECSpecButton.setBounds(192, 76, 80, 24);
		subscribeECSpecButton.setText("Execute");

		subscribeECSpecListcombo = new Combo(subscribeECSpecComposite,
				SWT.READ_ONLY);
		subscribeECSpecListcombo.setBackground(SWTResourceManager.getColor(230,
				230, 230));
		subscribeECSpecListcombo.setBounds(96, 32, 375, 24);

		final Label label_3 = new Label(subscribeECSpecComposite, SWT.SEPARATOR
				| SWT.HORIZONTAL);
		label_3.setBounds(5, 60, 466, 10);

		subscribeECSpecNotificationURIListCombo = new Combo(
				subscribeECSpecComposite, SWT.NONE);
		subscribeECSpecNotificationURIListCombo.setVisibleItemCount(10);
		subscribeECSpecNotificationURIListCombo
				.setBackground(SWTResourceManager.getColor(230, 230, 230));
		subscribeECSpecNotificationURIListCombo.setBounds(96, 2, 375, 24);

		unsubscribeECSpecComposite = new Composite(aleMethodGroup, SWT.NONE);
		unsubscribeECSpecComposite.setBounds(10, 16, 481, 105);
		unsubscribeECSpecComposite.setVisible(false);

		final Label setSpecNameLabel_2_1 = new Label(
				unsubscribeECSpecComposite, SWT.NONE);
		setSpecNameLabel_2_1.setBounds(5, 5, 85, 21);
		setSpecNameLabel_2_1.setText("Notification URI:");

		final Label setSpecNameLabel_1_2_1 = new Label(
				unsubscribeECSpecComposite, SWT.NONE);
		setSpecNameLabel_1_2_1.setBounds(5, 35, 85, 21);
		setSpecNameLabel_1_2_1.setText("Select ECSpec:");

		final Button unsubscribeECSpecButton = new Button(
				unsubscribeECSpecComposite, SWT.NONE);
		unsubscribeECSpecButton
				.addSelectionListener(new UnsubscribeECSpecButtonSelectionListener());
		unsubscribeECSpecButton.setImage(ResourceManager.getPluginImage(
				Activator.getDefault(), "icons/services_execute.gif"));
		unsubscribeECSpecButton.setBounds(192, 76, 80, 24);
		unsubscribeECSpecButton.setText("Execute");

		final Label label_4 = new Label(unsubscribeECSpecComposite,
				SWT.SEPARATOR | SWT.HORIZONTAL);
		label_4.setBounds(5, 60, 466, 10);

		unsubscribeECSpecListcombo = new Combo(unsubscribeECSpecComposite,
				SWT.READ_ONLY);
		unsubscribeECSpecListcombo.setBackground(SWTResourceManager.getColor(
				230, 230, 230));
		unsubscribeECSpecListcombo.setBounds(96, 32, 375, 24);

		unsubscribeECSpecNotificationURIListCombo = new Combo(
				unsubscribeECSpecComposite, SWT.NONE);
		unsubscribeECSpecNotificationURIListCombo.setVisibleItemCount(10);
		unsubscribeECSpecNotificationURIListCombo
				.setBackground(SWTResourceManager.getColor(230, 230, 230));
		unsubscribeECSpecNotificationURIListCombo.setBounds(96, 2, 375, 24);

		pollECSpecComposite = new Composite(aleMethodGroup, SWT.NONE);
		pollECSpecComposite.setBounds(10, 16, 481, 105);
		pollECSpecComposite.setVisible(false);

		final Label specNameLabel_1 = new Label(pollECSpecComposite, SWT.NONE);
		specNameLabel_1.setText("Spec name:");
		specNameLabel_1.setBounds(5, 5, 88, 21);

		pollSpecNameCombo = new Combo(pollECSpecComposite, SWT.READ_ONLY);
		pollSpecNameCombo.setBackground(SWTResourceManager.getColor(230, 230,
				230));
		pollSpecNameCombo.setBounds(96, 2, 375, 24);

		final Label label_6 = new Label(pollECSpecComposite, SWT.SEPARATOR
				| SWT.HORIZONTAL);
		label_6.setBounds(5, 60, 466, 10);

		final Button pollExecuteButton = new Button(pollECSpecComposite,
				SWT.NONE);
		pollExecuteButton
				.addSelectionListener(new PollExecuteButtonSelectionListener());
		pollExecuteButton.setImage(ResourceManager.getPluginImage(Activator
				.getDefault(), "icons/services_execute.gif"));
		pollExecuteButton.setText("Execute");
		pollExecuteButton.setBounds(192, 76, 80, 24);

		immediateECSpecComposite = new Composite(aleMethodGroup, SWT.NONE);
		immediateECSpecComposite.setBounds(10, 16, 481, 105);
		immediateECSpecComposite.setVisible(false);

		final Label selectEcspecLabel = new Label(immediateECSpecComposite,
				SWT.NONE);
		selectEcspecLabel.setText("Select ECSpec:");
		selectEcspecLabel.setBounds(5, 5, 88, 21);

		immediateECSpecCombo = new Combo(immediateECSpecComposite,
				SWT.READ_ONLY);
		immediateECSpecCombo.setBackground(SWTResourceManager.getColor(230,
				230, 230));
		immediateECSpecCombo.setBounds(96, 2, 375, 24);

		final Label label_7 = new Label(immediateECSpecComposite, SWT.SEPARATOR
				| SWT.HORIZONTAL);
		label_7.setBounds(5, 60, 466, 10);

		final Button immediateExecuteButton = new Button(
				immediateECSpecComposite, SWT.NONE);
		immediateExecuteButton
				.addSelectionListener(new ImmediateExecuteButtonSelectionListener());
		immediateExecuteButton.setImage(ResourceManager.getPluginImage(
				Activator.getDefault(), "icons/services_execute.gif"));
		immediateExecuteButton.setText("Execute");
		immediateExecuteButton.setBounds(192, 76, 80, 24);

		final Button button = new Button(aleMethodsGroup_1, SWT.FLAT
				| SWT.BORDER);
		button.setForeground(SWTResourceManager.getColor(255, 255, 255));
		button.setBackground(SWTResourceManager.getColor(255, 255, 255));
		button.setImage(ResourceManager.getPluginImage(Activator.getDefault(),
				"icons/img/desktop_icons_1.gif"));
		button.setBounds(10, 22, 93, 43);

		createActions();
		initializeToolBar();
		initializeMenu();
		initializeAleProxy();
		// TODO Auto-generated method stub

	}

	/**
	 * Create the actions
	 */
	private void createActions() {
		// Create the actions
	}

	@Override
	public void setFocus() {
		// TODO Auto-generated method stub

	}

	private void initializeToolBar() {
		@SuppressWarnings("unused")
		IToolBarManager toolBarManager = getViewSite().getActionBars()
				.getToolBarManager();
	}

	/**
	 * Initialize the menu
	 */
	private void initializeMenu() {
		@SuppressWarnings("unused")
		IMenuManager menuManager = getViewSite().getActionBars()
				.getMenuManager();
	}

	/**
	 * Initialize the ALE Server Proxy
	 */
	private void initializeAleProxy() {
		JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();
		factory.setServiceClass(ALEServicePortType.class);
		String ecSpecEndPoint = aleConfigPreferences
				.getString(PreferenceConstants.P_ALEClientEndPointSTRING);
		if (ecSpecEndPoint.equals(null) || ecSpecEndPoint.equals("")) {
			MessageDialog.openWarning(container.getShell(), "Error",
					"No end point has been defined!");
			return;
		}
		factory.setAddress(ecSpecEndPoint);
		aleProxy = (ALEServicePortType) factory.create();
	}

	// =========================Getters & Setters=============================

	public void setDefineECSpecCompositeVisible(boolean visible) {
		defineECSpecComposite.setVisible(visible);
	}

	public void setSubscribeECSpecCompositeVisible(boolean visible) {
		subscribeECSpecComposite.setVisible(visible);
	}

	public void setUndefineECSpecCompositeVisible(boolean visible) {
		undefineECSpecComposite.setVisible(visible);
	}

	public void setGetECSpecECSpecCompositeVisible(boolean visible) {
		getECSpecECSpecComposite.setVisible(visible);
	}

	public void setUnsubscribeECSpecCompositeVisible(boolean visible) {
		unsubscribeECSpecComposite.setVisible(visible);
	}

	public void setECSpecConfiguratorCompositeVisible(boolean visible) {
		ecspecConfiguratorComposite.setVisible(visible);
	}

	public void setGetSubscribersECSpecCompositeVisible(boolean visible) {
		getSubscribersECSpecComposite.setVisible(visible);
	}

	public void setPollECSpecCompositeVisible(boolean visible) {
		pollECSpecComposite.setVisible(visible);
	}

	public void setImmediateECSpecCompositeVisible(boolean visible) {
		immediateECSpecComposite.setVisible(visible);
	}

	// ======================================================================

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
			aleServerConfiguratorConsole = new IOConsole(
					"ALE Server Configurator", null);
			manager
					.addConsoles(new IConsole[] { aleServerConfiguratorConsole });
		}
		manager.showConsoleView(aleServerConfiguratorConsole);
		aleServerConfiguratorConsole.clearConsole();
		aleServerConfiguratorOutputConsole = aleServerConfiguratorConsole
				.newOutputStream();
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
			aleServerConfiguratorConsole = new IOConsole(
					"ALE Server Configurator", null);
			manager
					.addConsoles(new IConsole[] { aleServerConfiguratorConsole });
		}

		manager.removeConsoles(new IConsole[] { aleServerConfiguratorConsole });
		aleServerConfiguratorConsole.destroy();
	}

	/**
	 * Write to the IDE Console
	 * 
	 * @param message
	 */
	private void writeToConsole(String message) {

		try {
			aleServerConfiguratorOutputConsole.write(message + "\n");
		} catch (IOException e) {
			MessageDialog.openWarning(container.getShell(),
					"Console writing error!", e.getMessage());
		}
	}

	/**
	 * This method displays the result in the result text area.
	 * 
	 * @param result
	 *            to display
	 */
	private void showECSpecConfiguratorResult(Object result) {

		if (result instanceof String) {
			aleServerConfiguratorConsole.clearConsole();
			writeToConsole((String) result);

		} else if (result instanceof ArrayOfString) {
			aleServerConfiguratorConsole.clearConsole();
			ArrayOfString resultStringArray = (ArrayOfString) result;
			if (resultStringArray.getString().size() == 0) {
				writeToConsole("No data found!");
			} else {
				for (String s : resultStringArray.getString()) {
					writeToConsole(s);
				}
			}
		} else if (result instanceof ECSpec) {
			aleServerConfiguratorConsole.clearConsole();
			CharArrayWriter writer = new CharArrayWriter();
			try {
				SerializerUtil.serializeECSpec((ECSpec) result, writer);
			} catch (IOException e) {
				MessageDialog.openWarning(container.getShell(),
						"SerializationException was thrown!", e.getMessage());
			}
			writeToConsole(writer.toString());

		} else if (result instanceof ECReports) {
			aleServerConfiguratorConsole.clearConsole();
			CharArrayWriter writer = new CharArrayWriter();
			try {
				SerializerUtil.serializeECReports((ECReports) result, writer);
			} catch (IOException e) {
				MessageDialog.openWarning(container.getShell(),
						"SerializationException was thrown!", e.getMessage());
			}
			writeToConsole(writer.toString());

		}
	}

	/**
	 * Gets the Defined ECSpec Names
	 * 
	 * @return List<String> of ECSpec Names or null if empty
	 */
	private List<String> getDefinedECSpecNames() {

		List<String> ecSpecNames = null;

		try {
			ecSpecNames = aleProxy.getECSpecNames(new EmptyParms()).getString();
		} catch (Exception e) {
			MessageDialog.openWarning(container.getShell(), "Error", e
					.getMessage());
		}
		if (ecSpecNames != null && ecSpecNames.size() > 0) {
			return ecSpecNames;
		} else {
			return null;

		}
	}

	/**
	 * This method loads the ec specification from a file.
	 * 
	 * @param filename
	 *            of ec specification file
	 * @return ec specification
	 * @throws Exception
	 *             if specification could not be loaded
	 */
	private ECSpec getECSpecFromFile(Combo combo) {
		FileInputStream inputStream;
		ECSpec eCSpec = null;
		String ecSpecsPath = null;
		ecSpecsPath = aleConfigPreferences
				.getString(PreferenceConstants.P_ECSpecsPATH);
		try {
			if (ecSpecsPath.endsWith(fileSeparator)) {
				ecSpecsPath = aleConfigPreferences
						.getString(PreferenceConstants.P_ECSpecsPATH);
			} else {
				ecSpecsPath = ecSpecsPath + fileSeparator;
			}

			inputStream = new FileInputStream(ecSpecsPath + combo.getText());
			eCSpec = DeserializerUtil.deserializeECSpec(inputStream);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			MessageDialog.openWarning(container.getShell(), "Error",
					"File not found!");
			e.printStackTrace();
		} catch (Exception e) {
			MessageDialog.openWarning(container.getShell(), "Error", e
					.getMessage());

		}

		return eCSpec;

	}

	private File[] getECSpecsPathFolderContents() {

		String folderPath = aleConfigPreferences
				.getString(PreferenceConstants.P_ECSpecsPATH);
		File f = new File(folderPath);

		if (f.exists() == false) {
			f.mkdirs();
		}

		File[] contents = f.listFiles();
		return contents;
	}

	private void loadDefineECSpecComposite() {
		setDefineECSpecCompositeVisible(true);
		setUndefineECSpecCompositeVisible(false);
		setSubscribeECSpecCompositeVisible(false);
		setUnsubscribeECSpecCompositeVisible(false);
		setGetECSpecECSpecCompositeVisible(false);
		setGetSubscribersECSpecCompositeVisible(false);
		setPollECSpecCompositeVisible(false);
		setImmediateECSpecCompositeVisible(false);

		ecSpecListcombo.removeAll();
		File[] contents = getECSpecsPathFolderContents();
		for (int i = 0; i < contents.length; i++) {
			if (contents[i].getName().endsWith(".xml")) {
				ecSpecListcombo.add(contents[i].getName());
			}
		}

		ecSpecNameCombo.removeAll();

		String specNamesArray[] = {};

		specNamesArray = aleConfigPreferences.getString(
				PreferenceConstants.P_ECSpecNames).split(",");

		for (int i = 0; i < specNamesArray.length; i++) {
			if (!(specNamesArray[i].equals(""))) {
				ecSpecNameCombo.add(specNamesArray[i]);
			}
		}
	}

	private void loadUndefineECSpecComposite() {
		setDefineECSpecCompositeVisible(false);
		setUndefineECSpecCompositeVisible(true);
		setSubscribeECSpecCompositeVisible(false);
		setUnsubscribeECSpecCompositeVisible(false);
		setGetECSpecECSpecCompositeVisible(false);
		setGetSubscribersECSpecCompositeVisible(false);
		setPollECSpecCompositeVisible(false);
		setImmediateECSpecCompositeVisible(false);

		undefineECSpecListcombo.removeAll();
		List<String> ecSpecNames = getDefinedECSpecNames();
		if (ecSpecNames != null) {
			for (String specName : ecSpecNames) {
				undefineECSpecListcombo.add(specName);

			}
		}
	}

	private void loadGetECSpecECSpecComposite() {
		setDefineECSpecCompositeVisible(false);
		setUndefineECSpecCompositeVisible(false);
		setSubscribeECSpecCompositeVisible(false);
		setUnsubscribeECSpecCompositeVisible(false);
		setGetECSpecECSpecCompositeVisible(true);
		setGetSubscribersECSpecCompositeVisible(false);
		setPollECSpecCompositeVisible(false);
		setImmediateECSpecCompositeVisible(false);

		getECSpecECSpecListcombo.removeAll();
		List<String> ecSpecNames = getDefinedECSpecNames();
		if (ecSpecNames != null) {
			for (String specName : ecSpecNames) {
				getECSpecECSpecListcombo.add(specName);
			}
		}
	}

	private void loadGetECSpecNamesECSpecComposite() {
		setDefineECSpecCompositeVisible(false);
		setUndefineECSpecCompositeVisible(false);
		setSubscribeECSpecCompositeVisible(false);
		setUnsubscribeECSpecCompositeVisible(false);
		setGetECSpecECSpecCompositeVisible(false);
		setGetSubscribersECSpecCompositeVisible(false);
		setPollECSpecCompositeVisible(false);
		setImmediateECSpecCompositeVisible(false);

		ecSpecConfiguratorResult = null;

		try {
			ecSpecConfiguratorResult = aleProxy
					.getECSpecNames(new EmptyParms());
		} catch (ImplementationExceptionResponse e1) {
			// TODO Auto-generated catch block
			MessageDialog.openWarning(container.getShell(),
					"[Get ECSpec Names]: Implementation Exception was thrown.",
					e1.getMessage());
			e1.printStackTrace();

		} catch (SecurityExceptionResponse e1) {
			// TODO Auto-generated catch block
			MessageDialog.openWarning(container.getShell(),
					"[Get ECSpec Names]: Security Exception was thrown.", e1
							.getMessage());
			e1.printStackTrace();
		}

		writeToConsole("ECSpec Names: ");
		showECSpecConfiguratorResult(ecSpecConfiguratorResult);
	}

	private void loadSubscribeECSpecComposite() {
		setDefineECSpecCompositeVisible(false);
		setUndefineECSpecCompositeVisible(false);
		setSubscribeECSpecCompositeVisible(true);
		setUnsubscribeECSpecCompositeVisible(false);
		setGetECSpecECSpecCompositeVisible(false);
		setGetSubscribersECSpecCompositeVisible(false);
		setPollECSpecCompositeVisible(false);
		setImmediateECSpecCompositeVisible(false);

		subscribeECSpecListcombo.removeAll();
		List<String> ecSpecNames = getDefinedECSpecNames();
		if (ecSpecNames != null) {
			for (String specName : ecSpecNames) {
				subscribeECSpecListcombo.add(specName);

			}
		}

		subscribeECSpecNotificationURIListCombo.removeAll();

		String subscribeNotificationURIArray[] = {};

		subscribeNotificationURIArray = aleConfigPreferences.getString(
				PreferenceConstants.P_NotificationURI).split(",");

		for (int i = 0; i < subscribeNotificationURIArray.length; i++) {
			if (!(subscribeNotificationURIArray[i].equals(""))) {
				subscribeECSpecNotificationURIListCombo
						.add(subscribeNotificationURIArray[i]);
			}
		}

	}

	private void loadUnsubscribeECSpecComposite() {
		setDefineECSpecCompositeVisible(false);
		setUndefineECSpecCompositeVisible(false);
		setSubscribeECSpecCompositeVisible(false);
		setUnsubscribeECSpecCompositeVisible(true);
		setGetECSpecECSpecCompositeVisible(false);
		setGetSubscribersECSpecCompositeVisible(false);
		setPollECSpecCompositeVisible(false);
		setImmediateECSpecCompositeVisible(false);

		unsubscribeECSpecListcombo.removeAll();
		List<String> ecSpecNames = getDefinedECSpecNames();
		if (ecSpecNames != null) {
			for (String specName : ecSpecNames) {
				unsubscribeECSpecListcombo.add(specName);

			}
		}

		unsubscribeECSpecNotificationURIListCombo.removeAll();

		String unsubscribeNotificationURIArray[] = {};

		unsubscribeNotificationURIArray = aleConfigPreferences.getString(
				PreferenceConstants.P_NotificationURI).split(",");

		for (int i = 0; i < unsubscribeNotificationURIArray.length; i++) {
			if (!(unsubscribeNotificationURIArray[i].equals(""))) {
				unsubscribeECSpecNotificationURIListCombo
						.add(unsubscribeNotificationURIArray[i]);
			}
		}
	}

	private void loadPollECSpecComposite() {
		setDefineECSpecCompositeVisible(false);
		setUndefineECSpecCompositeVisible(false);
		setSubscribeECSpecCompositeVisible(false);
		setUnsubscribeECSpecCompositeVisible(false);
		setGetECSpecECSpecCompositeVisible(false);
		setGetSubscribersECSpecCompositeVisible(false);
		setPollECSpecCompositeVisible(true);
		setImmediateECSpecCompositeVisible(false);

		pollSpecNameCombo.removeAll();
		List<String> ecSpecNames = getDefinedECSpecNames();
		if (ecSpecNames != null) {
			for (String specName : ecSpecNames) {
				pollSpecNameCombo.add(specName);

			}
		}
	}

	private void loadImmediateECSpecComposite() {
		setDefineECSpecCompositeVisible(false);
		setUndefineECSpecCompositeVisible(false);
		setSubscribeECSpecCompositeVisible(false);
		setUnsubscribeECSpecCompositeVisible(false);
		setGetECSpecECSpecCompositeVisible(false);
		setGetSubscribersECSpecCompositeVisible(false);
		setPollECSpecCompositeVisible(false);
		setImmediateECSpecCompositeVisible(true);

		immediateECSpecCombo.removeAll();
		File[] contents = getECSpecsPathFolderContents();
		for (int i = 0; i < contents.length; i++) {
			if (contents[i].getName().endsWith(".xml")) {
				immediateECSpecCombo.add(contents[i].getName());
			}
		}
	}

	private void loadGetSubscribersECSpecComposite() {
		setDefineECSpecCompositeVisible(false);
		setUndefineECSpecCompositeVisible(false);
		setSubscribeECSpecCompositeVisible(false);
		setUnsubscribeECSpecCompositeVisible(false);
		setGetECSpecECSpecCompositeVisible(false);
		setGetSubscribersECSpecCompositeVisible(true);
		setPollECSpecCompositeVisible(false);
		setImmediateECSpecCompositeVisible(false);

		getSubscribersSpecNameCombo.removeAll();
		List<String> ecSpecNames = getDefinedECSpecNames();
		if (ecSpecNames != null) {
			for (String specName : ecSpecNames) {
				getSubscribersSpecNameCombo.add(specName);

			}
		}
	}

	private void getVendorVersion() {
		setDefineECSpecCompositeVisible(false);
		setUndefineECSpecCompositeVisible(false);
		setSubscribeECSpecCompositeVisible(false);
		setUnsubscribeECSpecCompositeVisible(false);
		setGetECSpecECSpecCompositeVisible(false);
		setGetSubscribersECSpecCompositeVisible(false);
		setPollECSpecCompositeVisible(false);
		setImmediateECSpecCompositeVisible(false);

		try {
			ecSpecConfiguratorResult = aleProxy
					.getVendorVersion(new EmptyParms());
		} catch (ImplementationExceptionResponse e1) {
			MessageDialog
					.openWarning(container.getShell(), "Error",
							"[getVendorVersion method]: Implementation Exception was thrown.");
			e1.printStackTrace();
		}

		writeToConsole("Vendor version: ");
		showECSpecConfiguratorResult(ecSpecConfiguratorResult);
	}

	private void getStandardVersion() {
		setDefineECSpecCompositeVisible(false);
		setUndefineECSpecCompositeVisible(false);
		setSubscribeECSpecCompositeVisible(false);
		setUnsubscribeECSpecCompositeVisible(false);
		setGetECSpecECSpecCompositeVisible(false);
		setGetSubscribersECSpecCompositeVisible(false);
		setPollECSpecCompositeVisible(false);
		setImmediateECSpecCompositeVisible(false);

		try {
			ecSpecConfiguratorResult = aleProxy
					.getStandardVersion(new EmptyParms());
		} catch (ImplementationExceptionResponse e1) {
			MessageDialog
					.openWarning(container.getShell(), "Error",
							"[getStandardVersion method]: Implementation Exception was thrown.");
			e1.printStackTrace();
		}

		writeToConsole("Standard version: ");
		showECSpecConfiguratorResult(ecSpecConfiguratorResult);
	}

	private void addNewSpecNameIfNotPresent() {
		// add a new value to PreferenceConstants.P_ConnectionPoints
		String specNameArray[] = {};
		String specNames = "";
		boolean found = false;

		specNameArray = aleConfigPreferences.getString(
				PreferenceConstants.P_ECSpecNames).split(",");

		for (int i = 0; i < specNameArray.length; i++) {
			if (ecSpecNameCombo.getText().equals(specNameArray[i])) {
				found = true;
			}
		}

		if (found == false) {

			specNames = aleConfigPreferences
					.getString(PreferenceConstants.P_ECSpecNames);

			specNames = specNames + "," + ecSpecNameCombo.getText();

			aleConfigPreferences.setValue(PreferenceConstants.P_ECSpecNames,
					specNames);

			specNameArray = aleConfigPreferences.getString(
					PreferenceConstants.P_ECSpecNames).split(",");

			ecSpecNameCombo.removeAll();
			for (int i = 0; i < specNameArray.length; i++) {
				if (!(specNameArray[i].equals(""))) {
					ecSpecNameCombo.add(specNameArray[i]);
				}
			}
		}
	}

	private class DefineECSpecButtonSelectionListener extends SelectionAdapter {
		public void widgetSelected(final SelectionEvent e) {
			ecSpecConfiguratorResult = null;

			List<String> ecSpecNames = getDefinedECSpecNames();

			if (ecSpecNames != null) {
				for (String specName : ecSpecNames) {
					if (ecSpecNameCombo.equals(specName)) {
						MessageDialog
								.openWarning(container.getShell(), "Error",
										"This ECSpec name already exists! \n Please choose a diferent one!");
						return;
					}
				}
			}

			// ==================================

			if (ecSpecNameCombo.getText().equals("")) {
				MessageDialog.openWarning(container.getShell(), "Error",
						"Please spesify an ECSpec Name!");
			}

			// get ecSpec
			ECSpec ecSpec = getECSpecFromFile(ecSpecListcombo);

			Define defineParms = new Define();
			defineParms.setSpecName(ecSpecNameCombo.getText());
			defineParms.setSpec(ecSpec);

			try {
				ecSpecConfiguratorResult = aleProxy.define(defineParms);
				MessageDialog
						.openConfirm(container.getShell(), "Confirmation",
								"[Define method]: The ECSpec was successfully defined!");
			} catch (ImplementationExceptionResponse e1) {
				MessageDialog
						.openWarning(
								container.getShell(),
								"[Define method]: Implementation Exception was thrown.",
								e1.getMessage());
				e1.printStackTrace();
				return;
			} catch (SecurityExceptionResponse e1) {
				MessageDialog.openWarning(container.getShell(),
						"[Define method]: Security Exception was thrown.", e1
								.getMessage());
				e1.printStackTrace();
				return;
			} catch (ECSpecValidationExceptionResponse e1) {
				MessageDialog
						.openWarning(
								container.getShell(),
								"[Define method]: ECSpec Validation Exception was thrown.",
								e1.getMessage());
				e1.printStackTrace();
				return;
			} catch (DuplicateNameExceptionResponse e1) {
				MessageDialog
						.openWarning(
								container.getShell(),
								"[Define method]: Duplicate Name Exception was thrown.",
								e1.getMessage());
				e1.printStackTrace();
				return;
			}

			addNewSpecNameIfNotPresent();

			ecSpecNameCombo.deselectAll();
			ecSpecListcombo.deselectAll();
		}
	}

	private class UndefineECSpecButtonSelectionListener extends
			SelectionAdapter {
		public void widgetSelected(final SelectionEvent e) {
			String specName = null;
			ecSpecConfiguratorResult = null;

			specName = undefineECSpecListcombo.getText();

			if (specName == null || "".equals(specName)) {
				MessageDialog.openWarning(container.getShell(), "Error",
						"Spec name field should be specified!");
				return;
			}

			Undefine undefineParms = new Undefine();
			undefineParms.setSpecName(specName);

			try {
				ecSpecConfiguratorResult = aleProxy.undefine(undefineParms);
				MessageDialog
						.openConfirm(container.getShell(), "Confirmation",
								"[Undefine method]: The ECSpec was successfully undefined!");
			} catch (NoSuchNameExceptionResponse e1) {
				// TODO Auto-generated catch block
				MessageDialog
						.openWarning(
								container.getShell(),
								"[Undefine method]: No Such Name Exception was thrown.",
								e1.getMessage());
				e1.printStackTrace();
			} catch (ImplementationExceptionResponse e1) {
				// TODO Auto-generated catch block
				MessageDialog
						.openWarning(
								container.getShell(),
								"[Undefine method]: Implementation Exception was thrown.",
								e1.getMessage());
				e1.printStackTrace();
			} catch (SecurityExceptionResponse e1) {
				// TODO Auto-generated catch block
				MessageDialog.openWarning(container.getShell(),
						"[Undefine method]: Security Exception was thrown.", e1
								.getMessage());
				e1.printStackTrace();
			}

			undefineECSpecListcombo.removeAll();

			List<String> ecSpecNames = getDefinedECSpecNames();

			if (ecSpecNames != null) {
				for (String curentSpecName : ecSpecNames) {
					undefineECSpecListcombo.add(curentSpecName);

				}
			}
		}
	}

	private class GetECSpecECSpecButtonSelectionListener extends
			SelectionAdapter {
		public void widgetSelected(final SelectionEvent e) {
			String specName = null;
			ecSpecConfiguratorResult = null;

			specName = getECSpecECSpecListcombo.getText();

			if (specName == null || "".equals(specName)) {
				MessageDialog.openWarning(container.getShell(), "Error",
						"Spec name field should be specified!");
				return;
			}

			GetECSpec getECSpecParms = new GetECSpec();
			getECSpecParms.setSpecName(specName);

			try {
				ecSpecConfiguratorResult = aleProxy.getECSpec(getECSpecParms);
			} catch (NoSuchNameExceptionResponse e1) {
				// TODO Auto-generated catch block
				MessageDialog
						.openWarning(
								container.getShell(),
								"[getECSpec method]: No Such Name Exception was thrown.",
								e1.getMessage());
				e1.printStackTrace();
			} catch (ImplementationExceptionResponse e1) {
				// TODO Auto-generated catch block
				MessageDialog
						.openWarning(
								container.getShell(),
								"[getECSpec method]: Implementation Exception was thrown.",
								e1.getMessage());
				e1.printStackTrace();
			} catch (SecurityExceptionResponse e1) {
				// TODO Auto-generated catch block
				MessageDialog.openWarning(container.getShell(),
						"[getECSpec method]: Security Exception was thrown.",
						e1.getMessage());
				e1.printStackTrace();
			}

			showECSpecConfiguratorResult(ecSpecConfiguratorResult);

			getECSpecECSpecListcombo.deselectAll();
		}
	}

	private void addNewNotificationURIIfNotPresent() {
		// add a new value to PreferenceConstants.P_ConnectionPoints
		String notificationURIArray[] = {};
		String notificationURIs = "";
		boolean found = false;

		notificationURIArray = aleConfigPreferences.getString(
				PreferenceConstants.P_NotificationURI).split(",");

		for (int i = 0; i < notificationURIArray.length; i++) {
			if (subscribeECSpecNotificationURIListCombo.getText().equals(
					notificationURIArray[i])) {
				found = true;
			}
		}

		if (found == false) {

			notificationURIs = aleConfigPreferences
					.getString(PreferenceConstants.P_NotificationURI);

			notificationURIs = notificationURIs + ","
					+ subscribeECSpecNotificationURIListCombo.getText();

			aleConfigPreferences.setValue(
					PreferenceConstants.P_NotificationURI, notificationURIs);

			notificationURIArray = aleConfigPreferences.getString(
					PreferenceConstants.P_NotificationURI).split(",");

			subscribeECSpecNotificationURIListCombo.removeAll();
			for (int i = 0; i < notificationURIArray.length; i++) {
				if (!(notificationURIArray[i].equals(""))) {
					subscribeECSpecNotificationURIListCombo
							.add(notificationURIArray[i]);
				}
			}
		}
	}

	private class SubscribeECSpecButtonSelectionListener extends
			SelectionAdapter {
		public void widgetSelected(final SelectionEvent e) {
			String notificationURI = null;
			String specName = null;
			ecSpecConfiguratorResult = null;

			specName = subscribeECSpecListcombo.getText();
			if (specName == null || "".equals(specName)) {
				MessageDialog.openWarning(container.getShell(), "Error",
						"Spec name field should be specified!");
				return;
			}

			// get notificationURI
			notificationURI = subscribeECSpecNotificationURIListCombo.getText();
			if (notificationURI == null || "".equals(notificationURI)) {
				MessageDialog.openWarning(container.getShell(), "Error",
						"Notification URI field should be specified!");
				return;
			}

			Subscribe subscribeParms = new Subscribe();
			subscribeParms.setSpecName(specName);
			subscribeParms.setNotificationURI(notificationURI);

			try {

				ecSpecConfiguratorResult = aleProxy.subscribe(subscribeParms);

				MessageDialog
						.openConfirm(container.getShell(), "Confirmation",
								"[Subscribe method]: The ECSpec was successfully subscribed!");

			} catch (DuplicateSubscriptionExceptionResponse e1) {
				// TODO Auto-generated catch block
				MessageDialog
						.openWarning(
								container.getShell(),
								"[Subscribe method]: Duplicate Subscription Exception was thrown.",
								e1.getMessage());
				e1.printStackTrace();
			} catch (InvalidURIExceptionResponse e1) {
				// TODO Auto-generated catch block
				MessageDialog
						.openWarning(
								container.getShell(),
								"[Subscribe method]: Invalid URI Exception was thrown.",
								e1.getMessage());
				e1.printStackTrace();
			} catch (NoSuchNameExceptionResponse e1) {
				// TODO Auto-generated catch block
				MessageDialog
						.openWarning(
								container.getShell(),
								"[Subscribe method]: No Such Name Exception was thrown.",
								e1.getMessage());
				e1.printStackTrace();
			} catch (ImplementationExceptionResponse e1) {
				// TODO Auto-generated catch block
				MessageDialog
						.openWarning(
								container.getShell(),
								"[Subscribe method]: Implementation Exception was thrown.",
								e1.getMessage());
				e1.printStackTrace();
			} catch (SecurityExceptionResponse e1) {
				// TODO Auto-generated catch block
				MessageDialog.openWarning(container.getShell(),
						"[Subscribe method]: Security Exception was thrown.",
						e1.getMessage());
				e1.printStackTrace();
			}

			addNewNotificationURIIfNotPresent();

			subscribeECSpecListcombo.deselectAll();
			subscribeECSpecNotificationURIListCombo.deselectAll();
		}
	}

	private void unsubscribeAddNewNotificationURIIfNotPresent() {
		// add a new value to PreferenceConstants.P_ConnectionPoints
		String notificationURIArray[] = {};
		String notificationURIs = "";
		boolean found = false;

		notificationURIArray = aleConfigPreferences.getString(
				PreferenceConstants.P_NotificationURI).split(",");

		for (int i = 0; i < notificationURIArray.length; i++) {
			if (unsubscribeECSpecNotificationURIListCombo.getText().equals(
					notificationURIArray[i])) {
				found = true;
			}
		}

		if (found == false) {

			notificationURIs = aleConfigPreferences
					.getString(PreferenceConstants.P_NotificationURI);

			notificationURIs = notificationURIs + ","
					+ unsubscribeECSpecNotificationURIListCombo.getText();

			aleConfigPreferences.setValue(
					PreferenceConstants.P_NotificationURI, notificationURIs);

			notificationURIArray = aleConfigPreferences.getString(
					PreferenceConstants.P_NotificationURI).split(",");

			unsubscribeECSpecNotificationURIListCombo.removeAll();
			for (int i = 0; i < notificationURIArray.length; i++) {
				if (!(notificationURIArray[i].equals(""))) {
					unsubscribeECSpecNotificationURIListCombo
							.add(notificationURIArray[i]);
				}
			}
		}
	}

	private class UnsubscribeECSpecButtonSelectionListener extends
			SelectionAdapter {
		public void widgetSelected(final SelectionEvent e) {
			String notificationURI = null;
			String specName = null;
			ecSpecConfiguratorResult = null;

			specName = unsubscribeECSpecListcombo.getText();

			if (specName == null || "".equals(specName)) {
				MessageDialog.openWarning(container.getShell(), "Error",
						"Spec name field should be specified!");
				return;
			}

			// get notificationURI
			notificationURI = unsubscribeECSpecNotificationURIListCombo
					.getText();
			if (notificationURI == null || "".equals(notificationURI)) {
				MessageDialog.openWarning(container.getShell(), "Error",
						"Notification URI field should be specified!");
				return;
			}

			unsubscribeAddNewNotificationURIIfNotPresent();

			Unsubscribe unsubscribeParms = new Unsubscribe();
			unsubscribeParms.setSpecName(specName);
			unsubscribeParms.setNotificationURI(notificationURI);

			try {

				ecSpecConfiguratorResult = aleProxy
						.unsubscribe(unsubscribeParms);
				MessageDialog
						.openConfirm(container.getShell(), "Confirmation",
								"[Unsubscribe method]: The ECSpec was successfully unsubscribed!");
			} catch (NoSuchSubscriberExceptionResponse e1) {
				// TODO Auto-generated catch block
				MessageDialog
						.openWarning(
								container.getShell(),
								"[Unsubscribe method]: No Such Subscriber Exception was thrown.",
								e1.getMessage());
				e1.printStackTrace();
			} catch (InvalidURIExceptionResponse e1) {
				// TODO Auto-generated catch block
				MessageDialog
						.openWarning(
								container.getShell(),
								"[Unsubscribe method]: Invalid URI Exception was thrown.",
								e1.getMessage());
				e1.printStackTrace();
			} catch (NoSuchNameExceptionResponse e1) {
				// TODO Auto-generated catch block
				MessageDialog
						.openWarning(
								container.getShell(),
								"[Unsubscribe method]: No Such Name Exception was thrown.",
								e1.getMessage());
				e1.printStackTrace();
			} catch (ImplementationExceptionResponse e1) {
				// TODO Auto-generated catch block
				MessageDialog
						.openWarning(
								container.getShell(),
								"[Unsubscribe method]: Implementation Exception was thrown.",
								e1.getMessage());
				e1.printStackTrace();
			} catch (SecurityExceptionResponse e1) {
				// TODO Auto-generated catch block
				MessageDialog.openWarning(container.getShell(),
						"[Unsubscribe method]: Security Exception was thrown.",
						e1.getMessage());
				e1.printStackTrace();
			}

			unsubscribeECSpecListcombo.deselectAll();
			unsubscribeECSpecNotificationURIListCombo.deselectAll();
		}
	}

	private class PollExecuteButtonSelectionListener extends SelectionAdapter {
		public void widgetSelected(final SelectionEvent e) {
			String specName = null;
			ecSpecConfiguratorResult = null;

			specName = pollSpecNameCombo.getText();

			if (specName == null || "".equals(specName)) {
				MessageDialog.openWarning(container.getShell(), "Error",
						"Spec name field should be specified!");
				return;
			}

			Poll pollParms = new Poll();
			pollParms.setSpecName(specName);

			try {
				ecSpecConfiguratorResult = aleProxy.poll(pollParms);
				MessageDialog.openConfirm(container.getShell(), "Confirmation",
						"[Poll method]: The spec was successfully activated!");
			} catch (ImplementationExceptionResponse e1) {
				MessageDialog.openWarning(container.getShell(), "Error",
						"[Poll method]: Implementation Exception was thrown.");
				e1.printStackTrace();
			} catch (NoSuchNameExceptionResponse e1) {
				// TODO Auto-generated catch block
				MessageDialog.openWarning(container.getShell(),
						"[Poll method]: No Such Name Exception was thrown.", e1
								.getMessage());
				e1.printStackTrace();
			} catch (SecurityExceptionResponse e1) {
				// TODO Auto-generated catch block
				MessageDialog.openWarning(container.getShell(),
						"[Poll method]: Security Exception was thrown.", e1
								.getMessage());
				e1.printStackTrace();
			}

			pollSpecNameCombo.deselectAll();
		}
	}

	private class ImmediateExecuteButtonSelectionListener extends
			SelectionAdapter {
		public void widgetSelected(final SelectionEvent e) {
			ecSpecConfiguratorResult = null;

			// get ecSpec
			ECSpec ecSpec = getECSpecFromFile(immediateECSpecCombo);

			Immediate immediateParms = new Immediate();
			immediateParms.setSpec(ecSpec);

			try {
				ecSpecConfiguratorResult = aleProxy.immediate(immediateParms);
				MessageDialog
						.openConfirm(container.getShell(), "Confirmation",
								"[Immediate method]: The ECSpec was successfully activated!");
			} catch (ImplementationExceptionResponse e1) {
				MessageDialog
						.openWarning(
								container.getShell(),
								"[Immediate method]: Implementation Exception was thrown.",
								e1.getMessage());
				e1.printStackTrace();
				return;
			} catch (SecurityExceptionResponse e1) {
				MessageDialog.openWarning(container.getShell(),
						"[Immediate method]: Security Exception was thrown.",
						e1.getMessage());
				e1.printStackTrace();
				return;
			} catch (ECSpecValidationExceptionResponse e1) {
				MessageDialog
						.openWarning(
								container.getShell(),
								"[Immediate method]: ECSpec Validation Exception was thrown.",
								e1.getMessage());
				e1.printStackTrace();
				return;
			}

			immediateECSpecCombo.deselectAll();
		}
	}

	private class GetSubscribersExecuteButtonSelectionListener extends
			SelectionAdapter {
		public void widgetSelected(final SelectionEvent e) {
			String specName = null;
			ecSpecConfiguratorResult = null;

			specName = getSubscribersSpecNameCombo.getText();

			if (specName == null || "".equals(specName)) {
				MessageDialog.openWarning(container.getShell(), "Error",
						"Spec name field should be specified!");
				return;
			}

			GetSubscribers getSubscribersParms = new GetSubscribers();
			getSubscribersParms.setSpecName(specName);

			try {
				ecSpecConfiguratorResult = aleProxy
						.getSubscribers(getSubscribersParms);
			} catch (ImplementationExceptionResponse e1) {
				MessageDialog
						.openWarning(container.getShell(), "Error",
								"[getSubscribers method]: Implementation Exception was thrown.");
				e1.printStackTrace();
			} catch (NoSuchNameExceptionResponse e1) {
				// TODO Auto-generated catch block
				MessageDialog
						.openWarning(
								container.getShell(),
								"[getSubscribers method]: No Such Name Exception was thrown.",
								e1.getMessage());
				e1.printStackTrace();
			} catch (SecurityExceptionResponse e1) {
				// TODO Auto-generated catch block
				MessageDialog
						.openWarning(
								container.getShell(),
								"[getSubscribers method]: Security Exception was thrown.",
								e1.getMessage());
				e1.printStackTrace();
			}

			showECSpecConfiguratorResult(ecSpecConfiguratorResult);

			getSubscribersSpecNameCombo.deselectAll();
		}
	}

	private class DefineToolItemSelectionListener extends SelectionAdapter {
		public void widgetSelected(final SelectionEvent e) {
			initializeAleProxy();
			//removeConsole();

			aleMethodGroup.setVisible(true);
			aleMethodGroup.setText("Define");
			loadDefineECSpecComposite();
		}
	}

	private class UndefineToolItemSelectionListener extends SelectionAdapter {
		public void widgetSelected(final SelectionEvent e) {
			initializeAleProxy();
			//removeConsole();

			aleMethodGroup.setVisible(true);
			aleMethodGroup.setText("Undefine");
			loadUndefineECSpecComposite();
		}
	}

	private class SubscribeToolItemSelectionListener extends SelectionAdapter {
		public void widgetSelected(final SelectionEvent e) {
			initializeAleProxy();
			//removeConsole();

			aleMethodGroup.setVisible(true);
			aleMethodGroup.setText("Subscribe");
			loadSubscribeECSpecComposite();
		}
	}

	private class UnsubscribeToolItemSelectionListener extends SelectionAdapter {
		public void widgetSelected(final SelectionEvent e) {
			initializeAleProxy();
			//removeConsole();

			aleMethodGroup.setVisible(true);
			aleMethodGroup.setText("Unsubscribe");
			loadUnsubscribeECSpecComposite();
		}
	}

	private class ImmediateToolItemSelectionListener extends SelectionAdapter {
		public void widgetSelected(final SelectionEvent e) {
			initializeAleProxy();
			//removeConsole();

			aleMethodGroup.setVisible(true);
			aleMethodGroup.setText("Immediate");
			loadImmediateECSpecComposite();
		}
	}

	private class PollToolItemSelectionListener extends SelectionAdapter {
		public void widgetSelected(final SelectionEvent e) {
			initializeAleProxy();
			//removeConsole();

			aleMethodGroup.setVisible(true);
			aleMethodGroup.setText("Poll");
			loadPollECSpecComposite();
		}
	}

	private class GetECSpecToolItemSelectionListener extends SelectionAdapter {
		public void widgetSelected(final SelectionEvent e) {
			initializeAleProxy();
			initiateConsole();

			aleMethodGroup.setVisible(true);
			aleMethodGroup.setText("GetECSpec");
			loadGetECSpecECSpecComposite();
		}
	}

	private class GetSubscribersToolItemSelectionListener extends
			SelectionAdapter {
		public void widgetSelected(final SelectionEvent e) {
			initializeAleProxy();
			initiateConsole();

			aleMethodGroup.setVisible(true);
			aleMethodGroup.setText("GetSubscribers");
			loadGetSubscribersECSpecComposite();
		}
	}

	private class GetECSpecNamesToolItemSelectionListener extends
			SelectionAdapter {
		public void widgetSelected(final SelectionEvent e) {
			initializeAleProxy();
			initiateConsole();

			aleMethodGroup.setVisible(false);
			loadGetECSpecNamesECSpecComposite();
		}
	}

	private class GetVendorVersionToolItemSelectionListener extends
			SelectionAdapter {
		public void widgetSelected(final SelectionEvent e) {
			initializeAleProxy();
			initiateConsole();

			aleMethodGroup.setVisible(false);
			getVendorVersion();
		}
	}

	private class GetStandardVersionToolItemSelectionListener extends
			SelectionAdapter {
		public void widgetSelected(final SelectionEvent e) {
			initializeAleProxy();
			initiateConsole();

			aleMethodGroup.setVisible(false);
			getStandardVersion();
		}
	}

}
