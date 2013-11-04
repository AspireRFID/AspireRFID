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

package org.ow2.aspirerfid.ide.lrspec.views;

import org.ow2.aspirerfid.ide.aleconfig.views.LRSpecConfiguratorView;
import org.ow2.aspirerfid.ide.lrspec.preferences.*;

import java.awt.Window;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.swing.JOptionPane;
import javax.xml.bind.JAXBElement;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.dialogs.IInputValidator;
import org.eclipse.jface.dialogs.InputDialog;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.VerifyEvent;
import org.eclipse.swt.events.VerifyListener;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.ViewPart;
import org.eclipse.swt.widgets.TreeItem;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.List;

import org.w3c.dom.*;

import org.ow2.aspirerfid.ide.lrspec.utils.LRSpecBuilder;
import org.ow2.aspirerfid.ide.lrspec.Activator;
import org.ow2.aspirerfid.ide.lrspec.preferences.StringListEditor;
import org.ow2.aspirerfid.ide.lrspec.dialogs.*;
import org.accada.ale.xsd.ale.epcglobal.LRSpec;
import org.ow2.aspirerfid.ide.lrspec.utils.swtdesigner.*;

/**
 * @author Vasso Koletti e-mail: vkol@ait.edu.gr
 * 
 */
@SuppressWarnings("unused")
public class LogicalReaderEditorView extends ViewPart {

	private Text CompReaderNameText;
	private Combo RPNotificationPointCombo;
	private Combo RPConnectionPointCombo;
	private Combo compFilterByCombo;
	private Text LLRPAdaptorClassText;
	private Combo LLRPReaderTypeText;
	private Text RPAdaptorClassText;
	private Combo RPReaderTypeText;
	private Text HALAdaptorClassText;
	private Combo HALReaderTypeText;
	private Combo CompReaderTypeText;
	private Text LLRPReaderOperationSpecIDText;
	private Text LLRPDescriptiveReaderSourceText;
	private Text LLRPPhysicalReaderSourceText;
	private Text LLRPImplClassText;
	private Text LLRPReadTimeIntervalText;
	private Text LLRPEncryptedConnectionPointPortText;
	private Text LLRPEncryptedConnectionPointAddressText;
	private Text LLRPConnectionPointPortText;
	private Text LLRPConnectionPointAddressText;
	private Text LLRPDescriptionText;
	private Text LLRPReaderNameText;
	private Text HALPropertiesFileText;
	private Text HALReadPointsText;
	private Text HALReadTimeIntervalText;
	private Text HALDescriptionText;
	private Text HALPhysicalReaderNameText;
	private Text HALReaderNameText;
	private Text RPPhysicalReaderSourceText;
	private Text RPPhysicalReaderNameText;
	private Text RPImplClassText;
	private Text RPReadTimeIntervalText;
	private Text RPDescriptionText;
	private Text RPReaderNameText;
	private List lrspecLogicalReadersList;
	private List lrspecIncludedReadersList;
	private Text CompDescriptionText;
	private Tree lrspecTree;
	private Label HALDescriptionLabel;
	private Label HALAdaptorClassLabel;
	private Label HALPhysicalReaderNameLabel;
	private Label HALReadTimeIntervalLabel;
	private Label HALReadPointsLabel;
	private Label HALPropertiesFileLabel;
	private Composite compositeHAL;
	private Composite compositeRP;
	private Composite compositeLLRP;
	private Composite compositeComposite;
	private Composite compositeOpenFile;
	private Label LLRPDescriptionLabel;
	private Label RPDescriptionLabel;
	private Label RPConnectionPointLabel;
	private Label RPNotificationPointLabel;
	private Label RPReadTimeIntervalLabel;
	private Label RPAdaptorClassLabel;
	private Label RPImplClassLabel;
	private Label RPPhysicalReaderNameLabel;
	private Label RPPhysicalReaderSourceLabel;
	private Label LLRPReaderOperationSpecIDLabel;
	private Label LLRPDescriptiveReaderSourceLabel;
	private Label LLRPPhysicalReaderSourceLabel;
	private Label LLRPImplClassLabel;
	private Label LLRPAdaptorClassLabel;
	private Label LLRPReadTimeIntervalLabel;
	private Label LLRPEncryptedConnectionPointPortLabel;
	private Label LLRPEncryptedConnectionPointAddressLabel;
	private Label LLRPConnectionPointPortLabel;
	private Label LLRPConnectionPointAddressLabel;
	private Label CompDescriptionLabel;
	private Label CompReaderTypeLabel;
	private Label HALReaderTypeLabel;
	private Label RPReaderTypeLabel;
	private Label LLRPReaderTypeLabel;
	private TreeItem HALReadersNode;
	private TreeItem RPReadersNode;
	private TreeItem LLRPReadersNode;
	private TreeItem CompositeReadersNode;
	private TreeItem halNodeChild;
	private TreeItem rpNodeChild;
	private TreeItem llrpNodeChild;
	private TreeItem compNodeChild;
	private TabFolder tabFolder;
	private TabItem lrspecHALTabItem;
	private TabItem lrspecRPTabItem;
	private TabItem lrspecLLRPTabItem;
	private TabItem lrspecCompositeTabItem;
	private Button HALStaticDefinitionCheck;
	private Button RPStaticDefinitionCheck;
	private Button LLRPStaticDefinitionCheck;
	private Button HALReaderSaveButton;
	private Button RPReaderSaveButton;
	private Button LLRPReaderSaveButton;
	private Button CompReaderSaveButton;
	private Button newReaderButton;

	public static final String ID = "org.ow2.aspirerfid.ide.lrspec.views.LogicalReaderEditorView"; //$NON-NLS-1$

	private LRSpecBuilder halLrSpecBuilder;
	private LRSpecBuilder openLrSpecBuilder;
	private LRSpecBuilder rpLrSpecBuilder;
	private LRSpecBuilder llrpLrSpecBuilder;
	private LRSpecBuilder lrSpecBuilder;
	private LRSpecBuilder compositeLrSpecBuilder;

	private String[] availableToIncludedReaders = { "" };
	private String[] includedToAvailableReaders = { "" };

	private String existingHALReaderFileToBeSaved;
	private String existingRPReaderFileToBeSaved;
	private String existingLLRPReaderFileToBeSaved;
	private String existingCompositeReaderFileToBeSaved;

	private String filterByComboSelectedItem = "";

	@SuppressWarnings("unchecked")
	HashMap staticReadersHashMap = new HashMap();

	Composite container;

	/** Returns the preference store for this UI plug-in */
	IPreferenceStore preferences = Activator.getDefault().getPreferenceStore();

	private LRSpecPreferencePage lrSpecPreferencePage;
	
	private static String fileSeparator = System.getProperty("file.separator");

	public LogicalReaderEditorView() {
		halLrSpecBuilder = new LRSpecBuilder();
		openLrSpecBuilder = new LRSpecBuilder();
		rpLrSpecBuilder = new LRSpecBuilder();
		llrpLrSpecBuilder = new LRSpecBuilder();
		lrSpecBuilder = new LRSpecBuilder();
		compositeLrSpecBuilder = new LRSpecBuilder();
	}

	/**
	 * Create contents of the view part
	 * 
	 * @param parent
	 */
	@Override
	public void createPartControl(Composite parent) {
		final FillLayout fillLayout = new FillLayout();
		parent.setLayout(fillLayout);
		container = new Composite(parent, SWT.NONE);
		container.setLayout(new FillLayout());

		final ScrolledComposite scrolledComposite = new ScrolledComposite(
				container, SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL);
		scrolledComposite.setAlwaysShowScrollBars(false);

		final Composite composite_2 = new Composite(scrolledComposite, SWT.NONE);
		composite_2.setLocation(0, 0);

		final Composite composite = new Composite(composite_2, SWT.BORDER);
		composite.setBounds(0, 0, 182, 82);
		composite.setBackground(SWTResourceManager.getColor(255, 255, 255));

		final Button button = new Button(composite, SWT.FLAT);
		button.setImage(ResourceManager.getPluginImage(Activator.getDefault(),
				"icons/RFID-logo2.gif"));
		button.setImage(ResourceManager.getPluginImage(Activator.getDefault(),
				"icons/RFID-logo2.gif"));
		button.setBackground(SWTResourceManager.getColor(255, 255, 255));
		button.setBounds(5, 6, 144, 67);

		lrspecTree = new Tree(composite_2, SWT.BORDER);
		lrspecTree.setBounds(0, 82, 182, 265);
		lrspecTree.addMouseListener(new LrspecTreeMouseListener());

		HALReadersNode = new TreeItem(lrspecTree, SWT.NONE, 0);
		HALReadersNode.setImage(ResourceManager.getPluginImage(Activator
				.getDefault(), "icons/HAL.jpg"));
		HALReadersNode.setText("HAL Readers");

		RPReadersNode = new TreeItem(lrspecTree, SWT.NONE);
		RPReadersNode.setImage(ResourceManager.getPluginImage(Activator
				.getDefault(), "icons/Red_R_icon.gif"));
		RPReadersNode.setText("RP Readers");

		LLRPReadersNode = new TreeItem(lrspecTree, SWT.NONE);
		LLRPReadersNode.setImage(ResourceManager.getPluginImage(Activator
				.getDefault(), "icons/icon-l.jpg"));
		LLRPReadersNode.setText("LLRP Readers");

		CompositeReadersNode = new TreeItem(lrspecTree, SWT.NONE);
		CompositeReadersNode.setImage(ResourceManager.getPluginImage(Activator
				.getDefault(), "icons/icon-c.jpg"));
		CompositeReadersNode.setText("Composite Readers");

		final Composite composite_1 = new Composite(composite_2, SWT.BORDER);
		composite_1.setBounds(0, 347, 182, 45);
		composite_1.setBackground(SWTResourceManager.getColor(255, 255, 255));

		final Button lrspecTreeRefreshButton = new Button(composite_1, SWT.NONE);
		lrspecTreeRefreshButton
				.addMouseListener(new LrspecTreeRefreshButtonMouseListener());
		lrspecTreeRefreshButton.setImage(ResourceManager.getPluginImage(
				Activator.getDefault(), "icons/icon_refresh3.png"));
		lrspecTreeRefreshButton.setText("Refresh");
		lrspecTreeRefreshButton.setBounds(49, 10, 85, 26);

		compositeOpenFile = new Composite(composite_2, SWT.BORDER);
		compositeOpenFile.setBounds(0, 392, 182, 107);
		compositeOpenFile.setBackground(SWTResourceManager.getColor(255, 255,
				255));

		final Group openExistingFileGroup = new Group(compositeOpenFile,
				SWT.NONE);
		openExistingFileGroup.setText("Open existing file");
		openExistingFileGroup.setBackground(SWTResourceManager.getColor(255,
				255, 255));
		openExistingFileGroup.setBounds(10, 9, 158, 83);

		final Button openFileButton = new Button(openExistingFileGroup,
				SWT.NONE);
		openFileButton.setImage(ResourceManager.getPluginImage(Activator
				.getDefault(), "icons/secure_file_transfer_icon.jpg"));
		openFileButton.setBounds(38, 34, 85, 26);
		openFileButton.addMouseListener(new OpenFileButtonMouseListener());
		openFileButton.setText("Open file");

		tabFolder = new TabFolder(composite_2, SWT.NONE);
		tabFolder.setBounds(182, 0, 555, 500);

		lrspecHALTabItem = new TabItem(tabFolder, SWT.NONE);
		lrspecHALTabItem.setImage(ResourceManager.getPluginImage(Activator
				.getDefault(), "icons/HAL.jpg"));
		lrspecHALTabItem.setText("HAL Protocol Logical Reader");

		compositeHAL = new Composite(tabFolder, SWT.NONE);
		lrspecHALTabItem.setControl(compositeHAL);

		final Group HALGroup = new Group(compositeHAL, SWT.NONE);
		HALGroup.setBounds(10, 0, 527, 328);

		final Label HALReaderNameLabel = new Label(HALGroup, SWT.NONE);
		HALReaderNameLabel.setBounds(10, 24, 80, 16);
		HALReaderNameLabel.setText("Reader Name:");

		HALReaderNameText = new Text(HALGroup, SWT.BORDER);
		HALReaderNameText.setBackground(SWTResourceManager.getColor(230, 230,
				230));
		HALReaderNameText.setBounds(96, 21, 421, 22);
		HALReaderNameText.setEnabled(false);

		final Group HALPropertiesGroup = new Group(HALGroup, SWT.NONE);
		HALPropertiesGroup.setText("Properties");
		HALPropertiesGroup.setBounds(10, 55, 507, 229);

		HALPhysicalReaderNameLabel = new Label(HALPropertiesGroup, SWT.NONE);
		HALPhysicalReaderNameLabel.setBounds(10, 109, 128, 16);
		HALPhysicalReaderNameLabel.setText("Physical Reader Name:");

		HALPhysicalReaderNameText = new Text(HALPropertiesGroup, SWT.BORDER);
		HALPhysicalReaderNameText.setBackground(SWTResourceManager.getColor(
				230, 230, 230));
		HALPhysicalReaderNameText.setBounds(146, 106, 351, 22);

		HALDescriptionLabel = new Label(HALPropertiesGroup, SWT.NONE);
		HALDescriptionLabel.setBounds(10, 53, 66, 16);
		HALDescriptionLabel.setText("Description:");

		HALDescriptionText = new Text(HALPropertiesGroup, SWT.BORDER);
		HALDescriptionText.setBackground(SWTResourceManager.getColor(230, 230,
				230));
		HALDescriptionText.setBounds(146, 50, 351, 22);

		HALReadTimeIntervalLabel = new Label(HALPropertiesGroup, SWT.NONE);
		HALReadTimeIntervalLabel.setBounds(10, 137, 128, 16);
		HALReadTimeIntervalLabel.setText("Read Time Interval:");

		HALReadTimeIntervalText = new Text(HALPropertiesGroup, SWT.BORDER);
		HALReadTimeIntervalText
				.addVerifyListener(new HALReadTimeIntervalTextVerifyListener());
		HALReadTimeIntervalText.setBackground(SWTResourceManager.getColor(230,
				230, 230));
		HALReadTimeIntervalText.setBounds(146, 134, 351, 22);

		HALReadPointsLabel = new Label(HALPropertiesGroup, SWT.NONE);
		HALReadPointsLabel.setBounds(10, 165, 68, 16);
		HALReadPointsLabel.setText("Read Points:");

		HALReadPointsText = new Text(HALPropertiesGroup, SWT.BORDER);
		HALReadPointsText.setBackground(SWTResourceManager.getColor(230, 230,
				230));
		HALReadPointsText.setBounds(146, 162, 351, 22);

		HALPropertiesFileLabel = new Label(HALPropertiesGroup, SWT.NONE);
		HALPropertiesFileLabel.setBounds(10, 193, 78, 16);
		HALPropertiesFileLabel.setText("Properties File:");

		HALPropertiesFileText = new Text(HALPropertiesGroup, SWT.BORDER);
		HALPropertiesFileText.setEditable(false);
		HALPropertiesFileText.setBackground(SWTResourceManager.getColor(230,
				230, 230));
		HALPropertiesFileText.setBounds(146, 190, 324, 22);

		HALAdaptorClassLabel = new Label(HALPropertiesGroup, SWT.NONE);
		HALAdaptorClassLabel.setBounds(10, 81, 78, 16);
		HALAdaptorClassLabel.setText("Adaptor Class:");

		HALReaderTypeLabel = new Label(HALPropertiesGroup, SWT.NONE);
		HALReaderTypeLabel.setText("Reader Type:");
		HALReaderTypeLabel.setBounds(10, 25, 78, 16);

		HALReaderTypeText = new Combo(HALPropertiesGroup, SWT.READ_ONLY);
		HALReaderTypeText
				.setItems(new String[] { "org.ow2.aspirerfid.ale.server.readers.hal.HALAdaptor" });
		HALReaderTypeText.setBackground(SWTResourceManager.getColor(230, 230,
				230));
		HALReaderTypeText.setBounds(146, 20, 351, 22);

		HALAdaptorClassText = new Text(HALPropertiesGroup, SWT.BORDER);
		HALAdaptorClassText.setBackground(SWTResourceManager.getColor(230, 230,
				230));
		HALAdaptorClassText.setBounds(146, 78, 351, 22);

		final Button HALBrowsePropertiesFileButton = new Button(
				HALPropertiesGroup, SWT.NONE);
		HALBrowsePropertiesFileButton
				.addMouseListener(new HALBrowsePropertiesFileButtonMouseListener());
		HALBrowsePropertiesFileButton.setText("...");
		HALBrowsePropertiesFileButton.setBounds(476, 190, 21, 22);

		final Button HALGenerateXMLButton = new Button(HALGroup, SWT.NONE);
		HALGenerateXMLButton.setImage(ResourceManager.getPluginImage(Activator
				.getDefault(), "icons/recording-launch.gif"));
		HALGenerateXMLButton
				.addMouseListener(new HALGenerateXMLButtonMouseListener());
		HALGenerateXMLButton.setBounds(10, 290, 89, 26);
		HALGenerateXMLButton.setText("Save as...");

		final Button HALResetButton = new Button(HALGroup, SWT.NONE);
		HALResetButton.setImage(ResourceManager.getPluginImage(Activator
				.getDefault(), "icons/icon_delete_tag.gif"));
		HALResetButton.addMouseListener(new HALResetButtonMouseListener());
		HALResetButton.setBounds(178, 290, 65, 26);
		HALResetButton.setText("Reset");

		HALStaticDefinitionCheck = new Button(HALGroup, SWT.CHECK);
		HALStaticDefinitionCheck
				.addSelectionListener(new HALStaticDefinitionCheckSelectionListener());
		HALStaticDefinitionCheck.setText("Static Definition");
		HALStaticDefinitionCheck.setBounds(413, 295, 104, 16);

		HALReaderSaveButton = new Button(HALGroup, SWT.NONE);
		HALReaderSaveButton
				.addMouseListener(new HALReaderSaveButtonMouseListener());
		HALReaderSaveButton.setImage(ResourceManager.getPluginImage(Activator
				.getDefault(), "icons/img/b_save.png"));
		HALReaderSaveButton.setText("Save");
		HALReaderSaveButton.setBounds(105, 290, 67, 26);

		lrspecRPTabItem = new TabItem(tabFolder, SWT.NONE);
		lrspecRPTabItem.setImage(ResourceManager.getPluginImage(Activator
				.getDefault(), "icons/Red_R_icon.gif"));
		lrspecRPTabItem.setText("RP Logical Reader");

		compositeRP = new Composite(tabFolder, SWT.NONE);
		lrspecRPTabItem.setControl(compositeRP);

		final Group RPGroup = new Group(compositeRP, SWT.NONE);
		RPGroup.setBounds(10, 0, 527, 386);

		final Label RPReaderNameLabel = new Label(RPGroup, SWT.NONE);
		RPReaderNameLabel.setBounds(10, 24, 80, 16);
		RPReaderNameLabel.setText("Reader Name:");

		RPReaderNameText = new Text(RPGroup, SWT.BORDER);
		RPReaderNameText.setBackground(SWTResourceManager.getColor(230, 230,
				230));
		RPReaderNameText.setBounds(96, 21, 421, 22);
		RPReaderNameText.setEnabled(false);

		final Group RPPropertiesGroup = new Group(RPGroup, SWT.NONE);
		RPPropertiesGroup.setText("Properties");
		RPPropertiesGroup.setBounds(10, 58, 507, 282);

		RPDescriptionLabel = new Label(RPPropertiesGroup, SWT.NONE);
		RPDescriptionLabel.setBounds(10, 53, 66, 16);
		RPDescriptionLabel.setText("Description:");

		RPDescriptionText = new Text(RPPropertiesGroup, SWT.BORDER);
		RPDescriptionText.setBackground(SWTResourceManager.getColor(230, 230,
				230));
		RPDescriptionText.setBounds(144, 50, 353, 22);

		RPConnectionPointLabel = new Label(RPPropertiesGroup, SWT.NONE);
		RPConnectionPointLabel.setBounds(10, 81, 98, 16);
		RPConnectionPointLabel.setText("Connection Point:");

		RPNotificationPointLabel = new Label(RPPropertiesGroup, SWT.NONE);
		RPNotificationPointLabel.setBounds(10, 109, 98, 16);
		RPNotificationPointLabel.setText("Notification Point:");

		RPReadTimeIntervalLabel = new Label(RPPropertiesGroup, SWT.NONE);
		RPReadTimeIntervalLabel.setBounds(10, 137, 128, 16);
		RPReadTimeIntervalLabel.setText("Read Time Interval:");

		RPReadTimeIntervalText = new Text(RPPropertiesGroup, SWT.BORDER);
		RPReadTimeIntervalText
				.addVerifyListener(new RPReadTimeIntervalTextVerifyListener());
		RPReadTimeIntervalText.setBackground(SWTResourceManager.getColor(230,
				230, 230));
		RPReadTimeIntervalText.setBounds(144, 134, 353, 22);

		RPAdaptorClassLabel = new Label(RPPropertiesGroup, SWT.NONE);
		RPAdaptorClassLabel.setBounds(10, 165, 84, 16);
		RPAdaptorClassLabel.setText("Adaptor Class:");

		RPImplClassLabel = new Label(RPPropertiesGroup, SWT.NONE);
		RPImplClassLabel.setBounds(10, 193, 128, 16);
		RPImplClassLabel.setText("Implementation Class:");

		RPImplClassText = new Text(RPPropertiesGroup, SWT.BORDER);
		RPImplClassText.setBackground(SWTResourceManager
				.getColor(230, 230, 230));
		RPImplClassText.setBounds(144, 190, 353, 22);

		RPPhysicalReaderNameLabel = new Label(RPPropertiesGroup, SWT.NONE);
		RPPhysicalReaderNameLabel.setBounds(10, 221, 128, 16);
		RPPhysicalReaderNameLabel.setText("Physical Reader Name:");

		RPPhysicalReaderSourceLabel = new Label(RPPropertiesGroup, SWT.NONE);
		RPPhysicalReaderSourceLabel.setBounds(10, 249, 128, 16);
		RPPhysicalReaderSourceLabel.setText("Physical Reader Source:");

		RPPhysicalReaderNameText = new Text(RPPropertiesGroup, SWT.BORDER);
		RPPhysicalReaderNameText.setBackground(SWTResourceManager.getColor(230,
				230, 230));
		RPPhysicalReaderNameText.setBounds(144, 218, 353, 22);

		RPPhysicalReaderSourceText = new Text(RPPropertiesGroup, SWT.BORDER);
		RPPhysicalReaderSourceText.setBackground(SWTResourceManager.getColor(
				230, 230, 230));
		RPPhysicalReaderSourceText.setBounds(144, 246, 353, 22);

		RPReaderTypeLabel = new Label(RPPropertiesGroup, SWT.NONE);
		RPReaderTypeLabel.setText("Reader Type:");
		RPReaderTypeLabel.setBounds(10, 25, 98, 16);

		RPReaderTypeText = new Combo(RPPropertiesGroup, SWT.READ_ONLY);
		RPReaderTypeText
				.setItems(new String[] { "org.ow2.aspirerfid.ale.server.readers.rp.RPAdaptor" });
		RPReaderTypeText.setBackground(SWTResourceManager.getColor(230, 230,
				230));
		RPReaderTypeText.setBounds(144, 20, 353, 22);

		RPAdaptorClassText = new Text(RPPropertiesGroup, SWT.BORDER);
		RPAdaptorClassText.setBackground(SWTResourceManager.getColor(230, 230,
				230));
		RPAdaptorClassText.setBounds(144, 162, 353, 22);

		RPConnectionPointCombo = new Combo(RPPropertiesGroup, SWT.NONE);
		RPConnectionPointCombo.setVisibleItemCount(10);
		RPConnectionPointCombo.setBackground(SWTResourceManager.getColor(230,
				230, 230));
		RPConnectionPointCombo.setBounds(144, 78, 353, 24);

		RPNotificationPointCombo = new Combo(RPPropertiesGroup, SWT.NONE);
		RPNotificationPointCombo.setVisibleItemCount(10);
		RPNotificationPointCombo.setBackground(SWTResourceManager.getColor(230,
				230, 230));
		RPNotificationPointCombo.setBounds(144, 106, 353, 24);

		final Button RPGenerateXMLButton = new Button(RPGroup, SWT.NONE);
		RPGenerateXMLButton.setImage(ResourceManager.getPluginImage(Activator
				.getDefault(), "icons/recording-launch.gif"));
		RPGenerateXMLButton
				.addMouseListener(new RPGenerateXMLButtonMouseListener());
		RPGenerateXMLButton.setBounds(10, 346, 89, 26);
		RPGenerateXMLButton.setText("Save as...");

		final Button RPResetButton = new Button(RPGroup, SWT.NONE);
		RPResetButton.setImage(ResourceManager.getPluginImage(Activator
				.getDefault(), "icons/icon_delete_tag.gif"));
		RPResetButton.addMouseListener(new RPResetButtonMouseListener());
		RPResetButton.setBounds(178, 346, 65, 26);
		RPResetButton.setText("Reset");

		RPStaticDefinitionCheck = new Button(RPGroup, SWT.CHECK);
		RPStaticDefinitionCheck
				.addSelectionListener(new RPStaticDefinitionCheckSelectionListener());
		RPStaticDefinitionCheck.setText("Static definition");
		RPStaticDefinitionCheck.setBounds(415, 351, 102, 16);

		RPReaderSaveButton = new Button(RPGroup, SWT.NONE);
		RPReaderSaveButton
				.addMouseListener(new RPReaderSaveButtonMouseListener());
		RPReaderSaveButton.setImage(ResourceManager.getPluginImage(Activator
				.getDefault(), "icons/img/b_save.png"));
		RPReaderSaveButton.setText("Save");
		RPReaderSaveButton.setBounds(105, 346, 67, 26);

		lrspecLLRPTabItem = new TabItem(tabFolder, SWT.NONE);
		lrspecLLRPTabItem.setImage(ResourceManager.getPluginImage(Activator
				.getDefault(), "icons/icon-l.jpg"));
		lrspecLLRPTabItem.setText("LLRP Logical Reader");

		compositeLLRP = new Composite(tabFolder, SWT.NONE);
		lrspecLLRPTabItem.setControl(compositeLLRP);

		final Group LLRPGroup = new Group(compositeLLRP, SWT.NONE);
		LLRPGroup.setBounds(10, 0, 527, 464);

		final Label LLRPReaderNameLabel = new Label(LLRPGroup, SWT.NONE);
		LLRPReaderNameLabel.setBounds(10, 24, 80, 16);
		LLRPReaderNameLabel.setText("Reader Name:");

		LLRPReaderNameText = new Text(LLRPGroup, SWT.BORDER);
		LLRPReaderNameText.setBackground(SWTResourceManager.getColor(230, 230,
				230));
		LLRPReaderNameText.setBounds(96, 21, 421, 22);
		LLRPReaderNameText.setEnabled(false);

		final Button LLRPGenerateXMLButton = new Button(LLRPGroup, SWT.NONE);
		LLRPGenerateXMLButton.setImage(ResourceManager.getPluginImage(Activator
				.getDefault(), "icons/recording-launch.gif"));
		LLRPGenerateXMLButton
				.addMouseListener(new LLRPGenerateXMLButtonMouseListener());
		LLRPGenerateXMLButton.setBounds(10, 430, 89, 26);
		LLRPGenerateXMLButton.setText("Save as...");

		final Button LLRPResetButton = new Button(LLRPGroup, SWT.NONE);
		LLRPResetButton.setImage(ResourceManager.getPluginImage(Activator
				.getDefault(), "icons/icon_delete_tag.gif"));
		LLRPResetButton.addMouseListener(new LLRPResetButtonMouseListener());
		LLRPResetButton.setBounds(178, 430, 65, 26);
		LLRPResetButton.setText("Reset");

		final Group LLRPPropertiesGroup = new Group(LLRPGroup, SWT.NONE);
		LLRPPropertiesGroup.setText("Properties");
		LLRPPropertiesGroup.setBounds(10, 57, 507, 367);

		LLRPDescriptionLabel = new Label(LLRPPropertiesGroup, SWT.NONE);
		LLRPDescriptionLabel.setBounds(10, 55, 66, 16);
		LLRPDescriptionLabel.setText("Description:");

		LLRPDescriptionText = new Text(LLRPPropertiesGroup, SWT.BORDER);
		LLRPDescriptionText.setBackground(SWTResourceManager.getColor(230, 230,
				230));
		LLRPDescriptionText.setBounds(216, 52, 281, 22);

		LLRPConnectionPointAddressLabel = new Label(LLRPPropertiesGroup,
				SWT.NONE);
		LLRPConnectionPointAddressLabel.setBounds(10, 83, 143, 16);
		LLRPConnectionPointAddressLabel.setText("Connection Point Address:");

		LLRPConnectionPointAddressText = new Text(LLRPPropertiesGroup,
				SWT.BORDER);
		LLRPConnectionPointAddressText.setBackground(SWTResourceManager
				.getColor(230, 230, 230));
		LLRPConnectionPointAddressText.setBounds(216, 80, 281, 22);

		LLRPConnectionPointPortLabel = new Label(LLRPPropertiesGroup, SWT.NONE);
		LLRPConnectionPointPortLabel.setBounds(10, 111, 143, 16);
		LLRPConnectionPointPortLabel.setText("Connection Point Port:");

		LLRPConnectionPointPortText = new Text(LLRPPropertiesGroup, SWT.BORDER);
		LLRPConnectionPointPortText
				.addVerifyListener(new LLRPConnectionPointPortTextVerifyListener());
		LLRPConnectionPointPortText.setBackground(SWTResourceManager.getColor(
				230, 230, 230));
		LLRPConnectionPointPortText.setBounds(216, 108, 281, 22);

		LLRPEncryptedConnectionPointAddressLabel = new Label(
				LLRPPropertiesGroup, SWT.NONE);
		LLRPEncryptedConnectionPointAddressLabel.setBounds(10, 139, 200, 16);
		LLRPEncryptedConnectionPointAddressLabel
				.setText("Encrypted Connection Point Address:");

		LLRPEncryptedConnectionPointAddressText = new Text(LLRPPropertiesGroup,
				SWT.BORDER);
		LLRPEncryptedConnectionPointAddressText
				.setBackground(SWTResourceManager.getColor(230, 230, 230));
		LLRPEncryptedConnectionPointAddressText.setBounds(216, 136, 281, 22);

		LLRPEncryptedConnectionPointPortLabel = new Label(LLRPPropertiesGroup,
				SWT.NONE);
		LLRPEncryptedConnectionPointPortLabel.setBounds(10, 167, 200, 16);
		LLRPEncryptedConnectionPointPortLabel
				.setText("Encrypted Connection Point Port:");

		LLRPEncryptedConnectionPointPortText = new Text(LLRPPropertiesGroup,
				SWT.BORDER);
		LLRPEncryptedConnectionPointPortText
				.addVerifyListener(new LLRPEncryptedConnectionPointPortTextVerifyListener());
		LLRPEncryptedConnectionPointPortText.setBackground(SWTResourceManager
				.getColor(230, 230, 230));
		LLRPEncryptedConnectionPointPortText.setBounds(216, 164, 281, 22);

		LLRPReadTimeIntervalLabel = new Label(LLRPPropertiesGroup, SWT.NONE);
		LLRPReadTimeIntervalLabel.setBounds(10, 195, 143, 16);
		LLRPReadTimeIntervalLabel.setText("Read Time Interval:");

		LLRPReadTimeIntervalText = new Text(LLRPPropertiesGroup, SWT.BORDER);
		LLRPReadTimeIntervalText
				.addVerifyListener(new LLRPReadTimeIntervalTextVerifyListener());
		LLRPReadTimeIntervalText.setBackground(SWTResourceManager.getColor(230,
				230, 230));
		LLRPReadTimeIntervalText.setBounds(216, 192, 281, 22);

		LLRPAdaptorClassLabel = new Label(LLRPPropertiesGroup, SWT.NONE);
		LLRPAdaptorClassLabel.setBounds(10, 223, 98, 16);
		LLRPAdaptorClassLabel.setText("Adaptor Class:");

		LLRPImplClassLabel = new Label(LLRPPropertiesGroup, SWT.NONE);
		LLRPImplClassLabel.setBounds(10, 251, 119, 16);
		LLRPImplClassLabel.setText("Implementation Class:");

		LLRPImplClassText = new Text(LLRPPropertiesGroup, SWT.BORDER);
		LLRPImplClassText.setBackground(SWTResourceManager.getColor(230, 230,
				230));
		LLRPImplClassText.setBounds(216, 248, 281, 22);

		LLRPPhysicalReaderSourceLabel = new Label(LLRPPropertiesGroup, SWT.NONE);
		LLRPPhysicalReaderSourceLabel.setBounds(10, 279, 129, 16);
		LLRPPhysicalReaderSourceLabel.setText("Physical Reader Source:");

		LLRPPhysicalReaderSourceText = new Text(LLRPPropertiesGroup, SWT.BORDER);
		LLRPPhysicalReaderSourceText
				.addVerifyListener(new LLRPPhysicalReaderSourceTextVerifyListener());
		LLRPPhysicalReaderSourceText.setBackground(SWTResourceManager.getColor(
				230, 230, 230));
		LLRPPhysicalReaderSourceText.setBounds(216, 276, 281, 22);

		LLRPDescriptiveReaderSourceLabel = new Label(LLRPPropertiesGroup,
				SWT.NONE);
		LLRPDescriptiveReaderSourceLabel.setBounds(10, 307, 154, 16);
		LLRPDescriptiveReaderSourceLabel.setText("Descriptive Reader Source:");

		LLRPDescriptiveReaderSourceText = new Text(LLRPPropertiesGroup,
				SWT.BORDER);
		LLRPDescriptiveReaderSourceText.setBackground(SWTResourceManager
				.getColor(230, 230, 230));
		LLRPDescriptiveReaderSourceText.setBounds(216, 304, 281, 22);

		LLRPReaderOperationSpecIDLabel = new Label(LLRPPropertiesGroup,
				SWT.NONE);
		LLRPReaderOperationSpecIDLabel.setBounds(10, 335, 154, 16);
		LLRPReaderOperationSpecIDLabel.setText("Reader Operation Spec ID:");

		LLRPReaderOperationSpecIDText = new Text(LLRPPropertiesGroup,
				SWT.BORDER);
		LLRPReaderOperationSpecIDText
				.addVerifyListener(new LLRPReaderOperationSpecIDTextVerifyListener());
		LLRPReaderOperationSpecIDText.setBackground(SWTResourceManager
				.getColor(230, 230, 230));
		LLRPReaderOperationSpecIDText.setBounds(216, 332, 281, 22);

		LLRPReaderTypeLabel = new Label(LLRPPropertiesGroup, SWT.NONE);
		LLRPReaderTypeLabel.setText("Reader Type:");
		LLRPReaderTypeLabel.setBounds(10, 25, 79, 16);

		LLRPReaderTypeText = new Combo(LLRPPropertiesGroup, SWT.READ_ONLY);
		LLRPReaderTypeText
				.setItems(new String[] { "org.ow2.aspirerfid.ale.server.readers.llrp.LLRPAdaptor" });
		LLRPReaderTypeText.setBackground(SWTResourceManager.getColor(230, 230,
				230));
		LLRPReaderTypeText.setBounds(216, 22, 281, 22);

		LLRPAdaptorClassText = new Text(LLRPPropertiesGroup, SWT.BORDER);
		LLRPAdaptorClassText.setBackground(SWTResourceManager.getColor(230,
				230, 230));
		LLRPAdaptorClassText.setBounds(216, 220, 281, 22);

		LLRPStaticDefinitionCheck = new Button(LLRPGroup, SWT.CHECK);
		LLRPStaticDefinitionCheck
				.addSelectionListener(new LLRPStaticDefinitionCheckSelectionListener());
		LLRPStaticDefinitionCheck.setText("Static Definition");
		LLRPStaticDefinitionCheck.setBounds(413, 435, 104, 16);

		LLRPReaderSaveButton = new Button(LLRPGroup, SWT.NONE);
		LLRPReaderSaveButton
				.addMouseListener(new LLRPReaderSaveButtonMouseListener());
		LLRPReaderSaveButton.setImage(ResourceManager.getPluginImage(Activator
				.getDefault(), "icons/img/b_save.png"));
		LLRPReaderSaveButton.setText("Save");
		LLRPReaderSaveButton.setBounds(105, 430, 67, 26);

		lrspecCompositeTabItem = new TabItem(tabFolder, SWT.NONE);
		lrspecCompositeTabItem.setImage(ResourceManager.getPluginImage(
				Activator.getDefault(), "icons/icon-c.jpg"));
		lrspecCompositeTabItem.setText("Composite Logical Reader");

		compositeComposite = new Composite(tabFolder, SWT.NONE);
		lrspecCompositeTabItem.setControl(compositeComposite);

		final Group CompositeGroup = new Group(compositeComposite, SWT.NONE);
		CompositeGroup.setBounds(10, 0, 527, 415);

		final Button CompGenerateXMLButton = new Button(CompositeGroup,
				SWT.NONE);
		CompGenerateXMLButton.setImage(ResourceManager.getPluginImage(Activator
				.getDefault(), "icons/recording-launch.gif"));
		CompGenerateXMLButton
				.addMouseListener(new CompGenerateXMLButtonMouseListener());
		CompGenerateXMLButton.setBounds(10, 376, 89, 26);
		CompGenerateXMLButton.setText("Save as...");

		final Group lrspecLogicalReadersGroup = new Group(CompositeGroup,
				SWT.NONE);
		lrspecLogicalReadersGroup.setText("Logical Readers");
		lrspecLogicalReadersGroup.setBounds(10, 159, 507, 211);

		lrspecIncludedReadersList = new List(lrspecLogicalReadersGroup,
				SWT.V_SCROLL | SWT.H_SCROLL | SWT.BORDER);
		lrspecIncludedReadersList.setBackground(SWTResourceManager.getColor(
				230, 230, 230));
		lrspecIncludedReadersList.setBounds(318, 82, 179, 85);

		lrspecIncludedReadersList.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				List list = (List) e.getSource();
				includedToAvailableReaders = list.getSelection();
			}
		});

		lrspecLogicalReadersList = new List(lrspecLogicalReadersGroup,
				SWT.V_SCROLL | SWT.H_SCROLL | SWT.BORDER);
		lrspecLogicalReadersList.setBackground(SWTResourceManager.getColor(230,
				230, 230));
		lrspecLogicalReadersList.setBounds(12, 82, 179, 85);

		lrspecLogicalReadersList.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				List list = (List) e.getSource();
				availableToIncludedReaders = list.getSelection();
			}
		});

		final Button lrspecAddReaderButton = new Button(
				lrspecLogicalReadersGroup, SWT.NONE);
		lrspecAddReaderButton
				.addMouseListener(new LrspecAddReaderButtonMouseListener());
		lrspecAddReaderButton.setBounds(75, 173, 49, 26);
		lrspecAddReaderButton.setText(">>");

		final Button lrspecTruncateReaderButton = new Button(
				lrspecLogicalReadersGroup, SWT.NONE);
		lrspecTruncateReaderButton
				.addMouseListener(new LrspecTruncateReaderButtonMouseListener());
		lrspecTruncateReaderButton.setText("<<");
		lrspecTruncateReaderButton.setBounds(386, 173, 49, 26);

		final Label availableLabel = new Label(lrspecLogicalReadersGroup,
				SWT.NONE);
		availableLabel.setText("Available:");
		availableLabel.setBounds(12, 60, 55, 16);

		final Label includedLabel = new Label(lrspecLogicalReadersGroup,
				SWT.NONE);
		includedLabel.setText("Included:");
		includedLabel.setBounds(318, 60, 55, 16);

		final Label filterByLabel = new Label(lrspecLogicalReadersGroup,
				SWT.NONE);
		filterByLabel.setText("Filter by:");
		filterByLabel.setBounds(188, 27, 49, 16);

		compFilterByCombo = new Combo(lrspecLogicalReadersGroup, SWT.READ_ONLY);
		compFilterByCombo.setBackground(SWTResourceManager.getColor(230, 230,
				230));
		compFilterByCombo
				.addSelectionListener(new CompFilterByComboSelectionListener());
		compFilterByCombo.setVisibleItemCount(2);
		compFilterByCombo.select(0);
		compFilterByCombo.setItems(new String[] { "Static", "Dynamic" });
		compFilterByCombo.setBounds(243, 24, 78, 24);

		final Label label = new Label(lrspecLogicalReadersGroup, SWT.SEPARATOR
				| SWT.HORIZONTAL);
		label.setBounds(12, 54, 485, 3);

		newReaderButton = new Button(lrspecLogicalReadersGroup, SWT.NONE);
		newReaderButton.addMouseListener(new NewReaderButtonMouseListener());
		newReaderButton.setImage(ResourceManager.getPluginImage(Activator
				.getDefault(), "icons/iconAddOnPackage.gif"));
		newReaderButton.setText("New reader");
		newReaderButton.setBounds(204, 173, 100, 26);
		newReaderButton.setEnabled(false);

		final Button lrspecCompResetButton = new Button(CompositeGroup,
				SWT.NONE);
		lrspecCompResetButton.setImage(ResourceManager.getPluginImage(Activator
				.getDefault(), "icons/icon_delete_tag.gif"));
		lrspecCompResetButton
				.addMouseListener(new LrspecCompResetButtonMouseListener());
		lrspecCompResetButton.setText("Reset");
		lrspecCompResetButton.setBounds(178, 376, 65, 26);

		CompReaderSaveButton = new Button(CompositeGroup, SWT.NONE);
		CompReaderSaveButton
				.addMouseListener(new CompReaderSaveButtonMouseListener());
		CompReaderSaveButton.setImage(ResourceManager.getPluginImage(Activator
				.getDefault(), "icons/img/b_save.png"));
		CompReaderSaveButton.setText("Save");
		CompReaderSaveButton.setBounds(105, 376, 67, 26);

		final Label readerNameLabel = new Label(CompositeGroup, SWT.NONE);
		readerNameLabel.setText("Reader Name:");
		readerNameLabel.setBounds(10, 27, 80, 16);

		CompReaderNameText = new Text(CompositeGroup, SWT.BORDER);
		CompReaderNameText.setBackground(SWTResourceManager.getColor(230, 230,
				230));
		CompReaderNameText.setBounds(105, 24, 412, 22);
		CompReaderNameText.setEnabled(false);

		final Group propertiesGroup = new Group(CompositeGroup, SWT.NONE);
		propertiesGroup.setText("Properties");
		propertiesGroup.setBounds(10, 58, 507, 90);

		CompReaderTypeLabel = new Label(propertiesGroup, SWT.NONE);
		CompReaderTypeLabel.setBounds(10, 26, 80, 16);
		CompReaderTypeLabel.setText("Reader Type:");

		CompReaderTypeText = new Combo(propertiesGroup, SWT.READ_ONLY);
		CompReaderTypeText.setBounds(96, 23, 401, 24);
		CompReaderTypeText
				.setItems(new String[] { "org.ow2.aspirerfid.ale.server.readers.CompositeReader" });
		CompReaderTypeText.setBackground(SWTResourceManager.getColor(230, 230,
				230));

		CompDescriptionLabel = new Label(propertiesGroup, SWT.NONE);
		CompDescriptionLabel.setBounds(10, 56, 80, 16);
		CompDescriptionLabel.setText("Description:");

		CompDescriptionText = new Text(propertiesGroup, SWT.BORDER);
		CompDescriptionText.setBounds(96, 53, 401, 22);
		CompDescriptionText.setBackground(SWTResourceManager.getColor(230, 230,
				230));
		composite_2.setSize(757, 499);
		scrolledComposite.setContent(composite_2);

			
		// initialize contents of lrspectree
		initializeToolBar();
		initializeMenu();
		createActions();
				
	}

	private void createActions() {
		// Create the actions
		lrSpecPreferencePage = new LRSpecPreferencePage();
		HALReaderSaveButton.setEnabled(false);
		RPReaderSaveButton.setEnabled(false);
		LLRPReaderSaveButton.setEnabled(false);
		CompReaderSaveButton.setEnabled(false);

		// load values to RPConnectionPointCombo
		String connectionPointArray[] = {};

		connectionPointArray = preferences.getString(
				PreferenceConstants.P_ConnectionPoints).split(",");

		RPConnectionPointCombo.removeAll();
		for (int i = 0; i < connectionPointArray.length; i++) {
			if (!(connectionPointArray[i].equals(""))) {
				RPConnectionPointCombo.add(connectionPointArray[i]);
			}
		}

		// load values to RPNotificationPointCombo
		String notificationPointArray[] = {};

		notificationPointArray = preferences.getString(
				PreferenceConstants.P_NotificationPoints).split(",");

		RPNotificationPointCombo.removeAll();
		for (int i = 0; i < notificationPointArray.length; i++) {
			if (!(notificationPointArray[i].equals(""))) {
				RPNotificationPointCombo.add(notificationPointArray[i]);
			}
		}
	}

	private File[] getHALLRSpecsPathFolderContents() {

		String folderPath = preferences
				.getString(PreferenceConstants.P_HAL_LRSpecsPATH);
		File f = new File(folderPath);

		if (f.exists() == false) {
			f.mkdirs();
		}

		File[] contents = f.listFiles();
		return contents;
	}

	private File[] getRPLRSpecsPathFolderContents() {

		String folderPath = preferences
				.getString(PreferenceConstants.P_RP_LRSpecsPATH);
		File f = new File(folderPath);

		if (f.exists() == false) {
			f.mkdirs();
		}

		File[] contents = f.listFiles();
		return contents;
	}

	private File[] getLLRPLRSpecsPathFolderContents() {

		String folderPath = preferences
				.getString(PreferenceConstants.P_LLRP_LRSpecsPATH);
		File f = new File(folderPath);

		if (f.exists() == false) {
			f.mkdirs();
		}

		File[] contents = f.listFiles();
		return contents;
	}

	private File[] getCompositeLRSpecsPathFolderContents() {

		String folderPath = preferences
				.getString(PreferenceConstants.P_Composite_LRSpecsPATH);
		File f = new File(folderPath);

		if (f.exists() == false) {
			f.mkdirs();
		}

		File[] contents = f.listFiles();
		return contents;
	}

	/**
	 * Create the actions
	 */
	private void initializeLrSpecTree() {
		HALReadersNode.removeAll();
		RPReadersNode.removeAll();
		LLRPReadersNode.removeAll();
		CompositeReadersNode.removeAll();

		File[] HALDirectoryContents = getHALLRSpecsPathFolderContents();
		File[] RPDirectoryContents = getRPLRSpecsPathFolderContents();
		File[] LLRPDirectoryContents = getLLRPLRSpecsPathFolderContents();
		File[] CompositeDirectoryContents = getCompositeLRSpecsPathFolderContents();

		if (HALDirectoryContents.length != 0) {
			for (int i = 0; i < HALDirectoryContents.length; i++) {
				if (HALDirectoryContents[i].getName().endsWith(".xml")) {
					halNodeChild = new TreeItem(HALReadersNode, SWT.NULL);
					halNodeChild.setText(HALDirectoryContents[i].getName());
				}
			}
		}

		if (RPDirectoryContents.length != 0) {
			for (int i = 0; i < RPDirectoryContents.length; i++) {
				if (RPDirectoryContents[i].getName().endsWith(".xml")) {
					rpNodeChild = new TreeItem(RPReadersNode, SWT.NULL);
					rpNodeChild.setText(RPDirectoryContents[i].getName());
				}
			}
		}

		if (LLRPDirectoryContents.length != 0) {
			for (int i = 0; i < LLRPDirectoryContents.length; i++) {
				if (LLRPDirectoryContents[i].getName().endsWith(".xml")) {
					llrpNodeChild = new TreeItem(LLRPReadersNode, SWT.NULL);
					llrpNodeChild.setText(LLRPDirectoryContents[i].getName());
				}
			}
		}

		if (CompositeDirectoryContents.length != 0) {
			for (int i = 0; i < CompositeDirectoryContents.length; i++) {
				if (CompositeDirectoryContents[i].getName().endsWith(".xml")) {
					compNodeChild = new TreeItem(CompositeReadersNode, SWT.NULL);
					compNodeChild.setText(CompositeDirectoryContents[i]
							.getName());
				}
			}
		}
	}

	/**
	 * Initialize the toolbar
	 */
	private void initializeToolBar() {
		IToolBarManager toolbarManager = getViewSite().getActionBars()
				.getToolBarManager();
	}

	/**
	 * Initialize the menu
	 */
	private void initializeMenu() {
		IMenuManager menuManager = getViewSite().getActionBars()
				.getMenuManager();
	}

	@Override
	public void setFocus() {
		// Set the focus
	}

	private void resetHALReaderFields() {
		HALReaderNameText.setText("");
		HALReaderTypeText.deselectAll();
		HALDescriptionText.setText("");
		HALAdaptorClassText.setText("");
		HALPhysicalReaderNameText.setText("");
		HALReadTimeIntervalText.setText("");
		HALReadPointsText.setText("");
		HALPropertiesFileText.setText("");
	}

	private class HALResetButtonMouseListener extends MouseAdapter {
		public void mouseDown(final MouseEvent e) {
			HALReaderNameText.setText("");
			HALReaderTypeText.deselectAll();
			HALDescriptionText.setText("");
			HALAdaptorClassText.setText("");
			HALPhysicalReaderNameText.setText("");
			HALReadTimeIntervalText.setText("");
			HALReadPointsText.setText("");
			HALPropertiesFileText.setText("");
			HALStaticDefinitionCheck.setSelection(false);

			HALReaderSaveButton.setEnabled(false);
			HALReaderNameText.setEnabled(false);
		}
	}

	private class HALGenerateXMLButtonMouseListener extends MouseAdapter {
		public void mouseDown(final MouseEvent e) {

			// variables
			int index = 0;
			String propertyName = "";
			String propertyValue = "";
			halLrSpecBuilder = new LRSpecBuilder();

			// Dynamic definition
			if (HALStaticDefinitionCheck.getSelection() == false) {
				if (HALReaderTypeText.getText().equals("")
						|| HALDescriptionText.getText().equals("")
						|| HALAdaptorClassText.getText().equals("")
						|| HALPhysicalReaderNameText.getText().equals("")
						|| HALReadTimeIntervalText.getText().equals("")
						|| HALReadPointsText.getText().equals("")
						|| HALPropertiesFileText.getText().equals("")) {
					MessageDialog.openError(container.getShell(), "Error",
							"All property fields are mandatory!");
				} else {
					// set isComposite to false (HAL Reader)
					halLrSpecBuilder.setIsComposite(false);

					// set HAL properties

					// set Reader type property
					propertyName = "ReaderType";
					propertyValue = HALReaderTypeText.getText();

					halLrSpecBuilder.setLRProperty(index, propertyName,
							propertyValue);

					// set Description property
					propertyName = "Description";
					propertyValue = HALDescriptionText.getText();

					halLrSpecBuilder.setLRProperty(++index, propertyName,
							propertyValue);

					// set Adaptor class property
					propertyName = "AdaptorClass";
					propertyValue = HALAdaptorClassText.getText();

					halLrSpecBuilder.setLRProperty(++index, propertyName,
							propertyValue);

					// set Physical reader name property
					propertyName = "PhysicalReaderName";
					propertyValue = HALPhysicalReaderNameText.getText();

					halLrSpecBuilder.setLRProperty(++index, propertyName,
							propertyValue);

					// set Read time interval property
					propertyName = "ReadTimeInterval";
					propertyValue = HALReadTimeIntervalText.getText();

					halLrSpecBuilder.setLRProperty(++index, propertyName,
							propertyValue);

					// set Read points property
					propertyName = "ReadPoints";
					propertyValue = HALReadPointsText.getText();

					halLrSpecBuilder.setLRProperty(++index, propertyName,
							propertyValue);

					// set Properties file property
					propertyName = "PropertiesFile";
					propertyValue = HALPropertiesFileText.getText();

					halLrSpecBuilder.setLRProperty(++index, propertyName,
							propertyValue);

					FileDialog fd;

					fd = new FileDialog(compositeHAL.getShell(), SWT.SAVE);
					fd.setText("Save");
					fd.setFileName(HALReaderNameText.getText() + ".xml");
					fd.setFilterExtensions(new String[] { ".xml" });
					String selection = fd.open();
					if (!selection.endsWith(".xml")) {
						selection += ".xml";
					}

					try {
						halLrSpecBuilder.generateXml(selection);

						// add reader to general List of readers
						lrSpecBuilder.setLogicalReaders(HALReaderNameText
								.getText());

						// add reader to lrspectree -> HALReadersNode
						halNodeChild = new TreeItem(HALReadersNode, SWT.NULL);
						halNodeChild.setText(selection.substring(selection
								.lastIndexOf(fileSeparator) + 1));

						HALReaderSaveButton.setEnabled(false);

						resetHALReaderFields();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

				}
			}// Static definition
			else {
				if (HALReaderNameText.getText().equals("")
						|| HALReaderTypeText.getText().equals("")
						|| HALDescriptionText.getText().equals("")
						|| HALAdaptorClassText.getText().equals("")
						|| HALPhysicalReaderNameText.getText().equals("")
						|| HALReadTimeIntervalText.getText().equals("")
						|| HALReadPointsText.getText().equals("")
						|| HALPropertiesFileText.getText().equals("")) {
					MessageDialog.openError(container.getShell(), "Error",
							"All fields are mandatory!");

				} else {
					// set isComposite to false (HAL Reader)
					halLrSpecBuilder.setIsComposite(false);

					// set Logical Reader Name
					halLrSpecBuilder.setLogicalReaders(HALReaderNameText
							.getText());

					// set HAL properties

					// set Reader type property
					propertyName = "ReaderType";
					propertyValue = HALReaderTypeText.getText();

					halLrSpecBuilder.setLRProperty(index, propertyName,
							propertyValue);

					// set Description property
					propertyName = "Description";
					propertyValue = HALDescriptionText.getText();

					halLrSpecBuilder.setLRProperty(++index, propertyName,
							propertyValue);

					// set Adaptor class property
					propertyName = "AdaptorClass";
					propertyValue = HALAdaptorClassText.getText();

					halLrSpecBuilder.setLRProperty(++index, propertyName,
							propertyValue);

					// set Physical reader name property
					propertyName = "PhysicalReaderName";
					propertyValue = HALPhysicalReaderNameText.getText();

					halLrSpecBuilder.setLRProperty(++index, propertyName,
							propertyValue);

					// set Read time interval property
					propertyName = "ReadTimeInterval";
					propertyValue = HALReadTimeIntervalText.getText();

					halLrSpecBuilder.setLRProperty(++index, propertyName,
							propertyValue);

					// set Read points property
					propertyName = "ReadPoints";
					propertyValue = HALReadPointsText.getText();

					halLrSpecBuilder.setLRProperty(++index, propertyName,
							propertyValue);

					// set Properties file property
					propertyName = "PropertiesFile";
					propertyValue = HALPropertiesFileText.getText();

					halLrSpecBuilder.setLRProperty(++index, propertyName,
							propertyValue);

					FileDialog fd;

					fd = new FileDialog(compositeHAL.getShell(), SWT.SAVE);
					fd.setText("Save");
					fd.setFileName(HALReaderNameText.getText() + ".xml");
					fd.setFilterExtensions(new String[] { ".xml" });
					String selection = fd.open();
					if (!selection.endsWith(".xml")) {
						selection += ".xml";
					}

					// generate HAL Reader Static Definition XML
					generateHALReaderStaticDefinitionXML(halLrSpecBuilder,
							selection);

					// add reader to lrspectree -> HALReadersNode
					halNodeChild = new TreeItem(HALReadersNode, SWT.NULL);
					halNodeChild.setText(selection.substring(selection
							.lastIndexOf(fileSeparator) + 1));

					HALReaderSaveButton.setEnabled(false);

					resetHALReaderFields();
				}
			}

		}
	}

	public void generateHALReaderStaticDefinitionXML(
			LRSpecBuilder halLrSpecBuilder, String selection) {
		try {
			// ///////////////////////////
			// Creating an empty XML Document

			// We need a Document
			DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory
					.newInstance();
			DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
			Document document = docBuilder.newDocument();

			// //////////////////////
			// Creating the XML tree

			// create the root element and add it to the document
			Element logicalReadersRoot = document
					.createElement("LogicalReaders");
			logicalReadersRoot.setAttribute("xmlns:xsi",
					"http://www.w3.org/2001/XMLSchema-instance");
			logicalReadersRoot.setAttribute("xsi:noNamespaceSchemaLocation",
					"/resources/LogicalReaders.xsd");
			document.appendChild(logicalReadersRoot);

			Element logicalReaderChild = document
					.createElement("LogicalReader");
			logicalReaderChild.setAttribute("name", halLrSpecBuilder
					.getLogicalReaders().get(0));
			logicalReadersRoot.appendChild(logicalReaderChild);

			Element lrSpecChild = document.createElement("LRSpec");
			lrSpecChild.setAttribute("isComposite", halLrSpecBuilder
					.getIsComposite().toString());
			lrSpecChild.setAttribute("readerType", halLrSpecBuilder
					.getProperties().get(0).getValue());
			logicalReaderChild.appendChild(lrSpecChild);

			for (int i = 1; i < halLrSpecBuilder.getProperties().size(); i++) {
				Element lrPropertyChild = document.createElement("LRProperty");
				lrPropertyChild.setAttribute("name", halLrSpecBuilder
						.getProperties().get(i).getName());
				lrPropertyChild.setAttribute("value", halLrSpecBuilder
						.getProperties().get(i).getValue());
				lrSpecChild.appendChild(lrPropertyChild);
			}

			// ///////////////
			// Output the XML

			// set up a transformer
			TransformerFactory transformerFactory = TransformerFactory
					.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			transformer.setOutputProperty(OutputKeys.STANDALONE, "yes");
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");

			// create string from xml tree
			StringWriter sw = new StringWriter();
			StreamResult result = new StreamResult(sw);
			DOMSource source = new DOMSource(document);
			transformer.transform(source, result);
			String xmlString = sw.toString();

			try {
				BufferedWriter out = new BufferedWriter(new FileWriter(
						selection));
				out.write(xmlString);
				out.close();
			} catch (IOException e) {
				MessageDialog.openError(container.getShell(), "Error",
						"Error in writing the file!");
			}
		} catch (Exception e) {
			MessageDialog.openError(container.getShell(), "Error",
					"DOM parser error!");
		}
	}

	private void resetRPReaderFields() {
		RPReaderNameText.setText("");
		RPReaderTypeText.deselectAll();
		RPDescriptionText.setText("");
		RPConnectionPointCombo.deselectAll();
		RPNotificationPointCombo.deselectAll();
		RPReadTimeIntervalText.setText("");
		RPAdaptorClassText.setText("");
		RPImplClassText.setText("");
		RPPhysicalReaderNameText.setText("");
		RPPhysicalReaderSourceText.setText("");
	}

	private class RPResetButtonMouseListener extends MouseAdapter {
		public void mouseDown(final MouseEvent e) {
			RPReaderNameText.setText("");
			RPReaderTypeText.deselectAll();
			RPDescriptionText.setText("");
			RPConnectionPointCombo.deselectAll();
			RPNotificationPointCombo.deselectAll();
			RPReadTimeIntervalText.setText("");
			RPAdaptorClassText.setText("");
			RPImplClassText.setText("");
			RPPhysicalReaderNameText.setText("");
			RPPhysicalReaderSourceText.setText("");
			RPStaticDefinitionCheck.setSelection(false);

			RPReaderSaveButton.setEnabled(false);
			RPReaderNameText.setEnabled(false);
		}
	}

	private class RPGenerateXMLButtonMouseListener extends MouseAdapter {
		public void mouseDown(final MouseEvent e) {

			// variables
			int index = 0;
			String propertyName = "";
			String propertyValue = "";
			rpLrSpecBuilder = new LRSpecBuilder();

			// Dynamic definition
			if (RPStaticDefinitionCheck.getSelection() == false) {
				if (RPReaderTypeText.getText().equals("")
						|| RPDescriptionText.getText().equals("")
						|| RPConnectionPointCombo.getText().equals("")
						|| RPNotificationPointCombo.getText().equals("")
						|| RPReadTimeIntervalText.getText().equals("")
						|| RPAdaptorClassText.getText().equals("")
						|| RPImplClassText.getText().equals("")
						|| RPPhysicalReaderNameText.getText().equals("")
						|| RPPhysicalReaderSourceText.getText().equals("")) {
					MessageDialog.openError(container.getShell(), "Error",
							"All property fields are mandatory!");

				} else {
					// set isComposite to false (RP Reader)
					rpLrSpecBuilder.setIsComposite(false);

					// set RP properties

					// set Reader type property
					propertyName = "ReaderType";
					propertyValue = RPReaderTypeText.getText();

					rpLrSpecBuilder.setLRProperty(index, propertyName,
							propertyValue);

					// set Description property
					propertyName = "Description";
					propertyValue = RPDescriptionText.getText();

					rpLrSpecBuilder.setLRProperty(++index, propertyName,
							propertyValue);

					// set Connection point property
					propertyName = "ConnectionPoint";
					propertyValue = RPConnectionPointCombo.getText();

					rpLrSpecBuilder.setLRProperty(++index, propertyName,
							propertyValue);

					// set Notification point property
					propertyName = "NotificationPoint";
					propertyValue = RPNotificationPointCombo.getText();

					rpLrSpecBuilder.setLRProperty(++index, propertyName,
							propertyValue);

					// set Read time interval property
					propertyName = "ReadTimeInterval";
					propertyValue = RPReadTimeIntervalText.getText();

					rpLrSpecBuilder.setLRProperty(++index, propertyName,
							propertyValue);

					// set Adaptor class property
					propertyName = "AdaptorClass";
					propertyValue = RPAdaptorClassText.getText();

					rpLrSpecBuilder.setLRProperty(++index, propertyName,
							propertyValue);

					// set Implementation class property
					propertyName = "ImplementationClass";
					propertyValue = RPImplClassText.getText();

					rpLrSpecBuilder.setLRProperty(++index, propertyName,
							propertyValue);

					// set Physical reader name property
					propertyName = "PhysicalReaderName";
					propertyValue = RPPhysicalReaderNameText.getText();

					rpLrSpecBuilder.setLRProperty(++index, propertyName,
							propertyValue);

					// set Physical reader source property
					propertyName = "PhysicalReaderSource";
					propertyValue = RPPhysicalReaderSourceText.getText();

					rpLrSpecBuilder.setLRProperty(++index, propertyName,
							propertyValue);

					FileDialog fd;

					fd = new FileDialog(compositeRP.getShell(), SWT.SAVE);
					fd.setText("Save");
					fd.setFileName(RPReaderNameText.getText() + ".xml");
					fd.setFilterExtensions(new String[] { ".xml" });
					String selection = fd.open();
					if (!selection.endsWith(".xml")) {
						selection += ".xml";
					}

					try {
						rpLrSpecBuilder.generateXml(selection);

						// add reader to lrspectree -> RPReadersNode
						rpNodeChild = new TreeItem(RPReadersNode, SWT.NULL);
						rpNodeChild.setText(selection.substring(selection
								.lastIndexOf(fileSeparator) + 1));

						RPReaderSaveButton.setEnabled(false);

						addNewConnectionPointValueIfNotPresent();
						addNewNotificationPointValueIfNotPresent();

						resetRPReaderFields();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			} // Static definition
			else {
				if (RPReaderNameText.getText().equals("")
						|| RPReaderTypeText.getText().equals("")
						|| RPDescriptionText.getText().equals("")
						|| RPConnectionPointCombo.getText().equals("")
						|| RPNotificationPointCombo.getText().equals("")
						|| RPReadTimeIntervalText.getText().equals("")
						|| RPAdaptorClassText.getText().equals("")
						|| RPImplClassText.getText().equals("")
						|| RPPhysicalReaderNameText.getText().equals("")
						|| RPPhysicalReaderSourceText.getText().equals("")) {
					MessageDialog.openError(container.getShell(), "Error",
							"All fields are mandatory!");

				} else {
					// set isComposite to false (RP Reader)
					rpLrSpecBuilder.setIsComposite(false);

					// set Logical Reader Name
					rpLrSpecBuilder.setLogicalReaders(RPReaderNameText
							.getText());

					// set RP properties

					// set Reader type property
					propertyName = "ReaderType";
					propertyValue = RPReaderTypeText.getText();

					rpLrSpecBuilder.setLRProperty(index, propertyName,
							propertyValue);

					// set Description property
					propertyName = "Description";
					propertyValue = RPDescriptionText.getText();

					rpLrSpecBuilder.setLRProperty(++index, propertyName,
							propertyValue);

					// set Connection point property
					propertyName = "ConnectionPoint";
					propertyValue = RPConnectionPointCombo.getText();

					rpLrSpecBuilder.setLRProperty(++index, propertyName,
							propertyValue);

					// set Notification point property
					propertyName = "NotificationPoint";
					propertyValue = RPNotificationPointCombo.getText();

					rpLrSpecBuilder.setLRProperty(++index, propertyName,
							propertyValue);

					// set Read time interval property
					propertyName = "ReadTimeInterval";
					propertyValue = RPReadTimeIntervalText.getText();

					rpLrSpecBuilder.setLRProperty(++index, propertyName,
							propertyValue);

					// set Adaptor class property
					propertyName = "AdaptorClass";
					propertyValue = RPAdaptorClassText.getText();

					rpLrSpecBuilder.setLRProperty(++index, propertyName,
							propertyValue);

					// set Implementation class property
					propertyName = "ImplementationClass";
					propertyValue = RPImplClassText.getText();

					rpLrSpecBuilder.setLRProperty(++index, propertyName,
							propertyValue);

					// set Physical reader name property
					propertyName = "PhysicalReaderName";
					propertyValue = RPPhysicalReaderNameText.getText();

					rpLrSpecBuilder.setLRProperty(++index, propertyName,
							propertyValue);

					// set Physical reader source property
					propertyName = "PhysicalReaderSource";
					propertyValue = RPPhysicalReaderSourceText.getText();

					rpLrSpecBuilder.setLRProperty(++index, propertyName,
							propertyValue);

					FileDialog fd;

					fd = new FileDialog(compositeRP.getShell(), SWT.SAVE);
					fd.setText("Save");
					fd.setFileName(RPReaderNameText.getText() + ".xml");
					fd.setFilterExtensions(new String[] { ".xml" });
					String selection = fd.open();
					if (!selection.endsWith(".xml")) {
						selection += ".xml";
					}

					// generate HAL Reader Static Definition XML
					generateRPReaderStaticDefinitionXML(rpLrSpecBuilder,
							selection);

					// add reader to lrspectree -> RPReadersNode
					rpNodeChild = new TreeItem(RPReadersNode, SWT.NULL);
					rpNodeChild.setText(selection.substring(selection
							.lastIndexOf(fileSeparator) + 1));

					RPReaderSaveButton.setEnabled(false);

					addNewConnectionPointValueIfNotPresent();
					addNewNotificationPointValueIfNotPresent();

					resetRPReaderFields();
				}
			}

		}
	}

	private void addNewConnectionPointValueIfNotPresent() {
		// add a new value to PreferenceConstants.P_ConnectionPoints
		String connectionPointArray[] = {};
		String connectionPoints = "";
		boolean found = false;

		connectionPointArray = preferences.getString(
				PreferenceConstants.P_ConnectionPoints).split(",");

		for (int i = 0; i < connectionPointArray.length; i++) {
			if (RPConnectionPointCombo.getText()
					.equals(connectionPointArray[i])) {
				found = true;
			}
		}

		if (found == false) {

			connectionPoints = preferences
					.getString(PreferenceConstants.P_ConnectionPoints);

			connectionPoints = connectionPoints + ","
					+ RPConnectionPointCombo.getText();

			preferences.setValue(PreferenceConstants.P_ConnectionPoints,
					connectionPoints);

			connectionPointArray = preferences.getString(
					PreferenceConstants.P_ConnectionPoints).split(",");

			RPConnectionPointCombo.removeAll();
			for (int i = 0; i < connectionPointArray.length; i++) {
				if (!(connectionPointArray[i].equals(""))) {
					RPConnectionPointCombo.add(connectionPointArray[i]);
				}
			}
		}
	}

	private void addNewNotificationPointValueIfNotPresent() {
		// add a new value to PreferenceConstants.P_ConnectionPoints
		String notificationPointArray[] = {};
		String notificationPoints = "";
		boolean found = false;

		notificationPointArray = preferences.getString(
				PreferenceConstants.P_NotificationPoints).split(",");

		for (int i = 0; i < notificationPointArray.length; i++) {
			if (RPNotificationPointCombo.getText().equals(
					notificationPointArray[i])) {
				found = true;
			}
		}

		if (found == false) {

			notificationPoints = preferences
					.getString(PreferenceConstants.P_NotificationPoints);

			notificationPoints = notificationPoints + ","
					+ RPNotificationPointCombo.getText();

			preferences.setValue(PreferenceConstants.P_NotificationPoints,
					notificationPoints);

			notificationPointArray = preferences.getString(
					PreferenceConstants.P_NotificationPoints).split(",");

			RPNotificationPointCombo.removeAll();
			for (int i = 0; i < notificationPointArray.length; i++) {
				if (!(notificationPointArray[i].equals(""))) {
					RPNotificationPointCombo.add(notificationPointArray[i]);
				}
			}
		}
	}

	public void generateRPReaderStaticDefinitionXML(
			LRSpecBuilder rpLrSpecBuilder, String selection) {
		try {
			// ///////////////////////////
			// Creating an empty XML Document

			// We need a Document
			DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory
					.newInstance();
			DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
			Document document = docBuilder.newDocument();

			// //////////////////////
			// Creating the XML tree

			// create the root element and add it to the document
			Element logicalReadersRoot = document
					.createElement("LogicalReaders");
			logicalReadersRoot.setAttribute("xmlns:xsi",
					"http://www.w3.org/2001/XMLSchema-instance");
			logicalReadersRoot.setAttribute("xsi:noNamespaceSchemaLocation",
					"/resources/LogicalReaders.xsd");
			document.appendChild(logicalReadersRoot);

			Element logicalReaderChild = document
					.createElement("LogicalReader");
			logicalReaderChild.setAttribute("name", rpLrSpecBuilder
					.getLogicalReaders().get(0));
			logicalReadersRoot.appendChild(logicalReaderChild);

			Element lrSpecChild = document.createElement("LRSpec");
			lrSpecChild.setAttribute("isComposite", rpLrSpecBuilder
					.getIsComposite().toString());
			lrSpecChild.setAttribute("readerType", rpLrSpecBuilder
					.getProperties().get(0).getValue());
			logicalReaderChild.appendChild(lrSpecChild);

			for (int i = 1; i < rpLrSpecBuilder.getProperties().size(); i++) {
				Element lrPropertyChild = document.createElement("LRProperty");
				lrPropertyChild.setAttribute("name", rpLrSpecBuilder
						.getProperties().get(i).getName());
				lrPropertyChild.setAttribute("value", rpLrSpecBuilder
						.getProperties().get(i).getValue());
				lrSpecChild.appendChild(lrPropertyChild);
			}

			// ///////////////
			// Output the XML

			// set up a transformer
			TransformerFactory transformerFactory = TransformerFactory
					.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			transformer.setOutputProperty(OutputKeys.STANDALONE, "yes");
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");

			// create string from xml tree
			StringWriter sw = new StringWriter();
			StreamResult result = new StreamResult(sw);
			DOMSource source = new DOMSource(document);
			transformer.transform(source, result);
			String xmlString = sw.toString();

			try {
				BufferedWriter out = new BufferedWriter(new FileWriter(
						selection));
				out.write(xmlString);
				out.close();
			} catch (IOException e) {
				MessageDialog.openError(container.getShell(), "Error",
						"Error in writing the file!");
			}
		} catch (Exception e) {
			MessageDialog.openError(container.getShell(), "Error",
					"DOM parser error!");
		}
	}

	private void resetLLRPReaderFields() {
		LLRPReaderNameText.setText("");
		LLRPReaderTypeText.deselectAll();
		LLRPDescriptionText.setText("");
		LLRPConnectionPointAddressText.setText("");
		LLRPConnectionPointPortText.setText("");
		LLRPEncryptedConnectionPointAddressText.setText("");
		LLRPEncryptedConnectionPointPortText.setText("");
		LLRPReadTimeIntervalText.setText("");
		LLRPAdaptorClassText.setText("");
		LLRPImplClassText.setText("");
		LLRPPhysicalReaderSourceText.setText("");
		LLRPDescriptiveReaderSourceText.setText("");
		LLRPReaderOperationSpecIDText.setText("");
	}

	private class LLRPResetButtonMouseListener extends MouseAdapter {
		public void mouseDown(final MouseEvent e) {
			LLRPReaderNameText.setText("");
			LLRPReaderTypeText.deselectAll();
			LLRPDescriptionText.setText("");
			LLRPConnectionPointAddressText.setText("");
			LLRPConnectionPointPortText.setText("");
			LLRPEncryptedConnectionPointAddressText.setText("");
			LLRPEncryptedConnectionPointPortText.setText("");
			LLRPReadTimeIntervalText.setText("");
			LLRPAdaptorClassText.setText("");
			LLRPImplClassText.setText("");
			LLRPPhysicalReaderSourceText.setText("");
			LLRPDescriptiveReaderSourceText.setText("");
			LLRPReaderOperationSpecIDText.setText("");
			LLRPStaticDefinitionCheck.setSelection(false);

			LLRPReaderSaveButton.setEnabled(false);
			LLRPReaderNameText.setEnabled(false);
		}
	}

	private class LLRPGenerateXMLButtonMouseListener extends MouseAdapter {
		public void mouseDown(final MouseEvent e) {
			// variables
			int index = 0;
			String propertyName = "";
			String propertyValue = "";
			llrpLrSpecBuilder = new LRSpecBuilder();

			if (LLRPStaticDefinitionCheck.getSelection() == false) {
				if (LLRPReaderTypeText.getText().equals("")
						|| LLRPDescriptionText.getText().equals("")
						|| LLRPConnectionPointAddressText.getText().equals("")
						|| LLRPConnectionPointPortText.getText().equals("")
						|| LLRPEncryptedConnectionPointAddressText.getText()
								.equals("")
						|| LLRPEncryptedConnectionPointPortText.getText()
								.equals("")
						|| LLRPReadTimeIntervalText.getText().equals("")
						|| LLRPAdaptorClassText.getText().equals("")
						|| LLRPImplClassText.getText().equals("")
						|| LLRPPhysicalReaderSourceText.getText().equals("")
						|| LLRPDescriptiveReaderSourceText.getText().equals("")
						|| LLRPReaderOperationSpecIDText.getText().equals("")) {
					MessageDialog.openError(container.getShell(), "Error",
							"All property fields are mandatory!");

				} else {
					// set isComposite to false (RP Reader)
					llrpLrSpecBuilder.setIsComposite(false);

					// set LLRP properties

					// set Description property
					propertyName = "ReaderType";
					propertyValue = LLRPReaderTypeText.getText();

					llrpLrSpecBuilder.setLRProperty(index, propertyName,
							propertyValue);

					// set Description property
					propertyName = "Description";
					propertyValue = LLRPDescriptionText.getText();

					llrpLrSpecBuilder.setLRProperty(++index, propertyName,
							propertyValue);

					// set Connection point address property
					propertyName = "ConnectionPointAddress";
					propertyValue = LLRPConnectionPointAddressText.getText();

					llrpLrSpecBuilder.setLRProperty(++index, propertyName,
							propertyValue);

					// set Connection point port property
					propertyName = "ConnectionPointPort";
					propertyValue = LLRPConnectionPointPortText.getText();

					llrpLrSpecBuilder.setLRProperty(++index, propertyName,
							propertyValue);

					// set Encrypted connection point address property
					propertyName = "EncryptedConnectionPointAddress";
					propertyValue = LLRPEncryptedConnectionPointAddressText
							.getText();

					llrpLrSpecBuilder.setLRProperty(++index, propertyName,
							propertyValue);

					// set Encrypted connection point port property
					propertyName = "EncryptedConnectionPointPort";
					propertyValue = LLRPEncryptedConnectionPointPortText
							.getText();

					llrpLrSpecBuilder.setLRProperty(++index, propertyName,
							propertyValue);

					// set Read time interval property
					propertyName = "ReadTimeInterval";
					propertyValue = LLRPReadTimeIntervalText.getText();

					llrpLrSpecBuilder.setLRProperty(++index, propertyName,
							propertyValue);

					// set Adaptor class property
					propertyName = "AdaptorClass";
					propertyValue = LLRPAdaptorClassText.getText();

					llrpLrSpecBuilder.setLRProperty(++index, propertyName,
							propertyValue);

					// set Implementation class property
					propertyName = "ImplementationClass";
					propertyValue = LLRPImplClassText.getText();

					llrpLrSpecBuilder.setLRProperty(++index, propertyName,
							propertyValue);

					// set Physical reader source property
					propertyName = "PhysicalReaderSource";
					propertyValue = LLRPPhysicalReaderSourceText.getText();

					llrpLrSpecBuilder.setLRProperty(++index, propertyName,
							propertyValue);

					// set Descriptive Reader source property
					propertyName = "DescriptiveReaderSource";
					propertyValue = LLRPDescriptiveReaderSourceText.getText();

					llrpLrSpecBuilder.setLRProperty(++index, propertyName,
							propertyValue);

					// set Reader Operation Spec ID property
					propertyName = "RoSpecID";
					propertyValue = LLRPReaderOperationSpecIDText.getText();

					llrpLrSpecBuilder.setLRProperty(++index, propertyName,
							propertyValue);

					FileDialog fd;

					fd = new FileDialog(compositeLLRP.getShell(), SWT.SAVE);
					fd.setText("Save");
					fd.setFileName(LLRPReaderNameText.getText() + ".xml");
					fd.setFilterExtensions(new String[] { ".xml" });
					String selection = fd.open();
					if (!selection.endsWith(".xml")) {
						selection += ".xml";
					}

					try {
						llrpLrSpecBuilder.generateXml(selection);

						// add reader to lrspectree -> LLRPReadersNode
						llrpNodeChild = new TreeItem(LLRPReadersNode, SWT.NULL);
						llrpNodeChild.setText(selection.substring(selection
								.lastIndexOf(fileSeparator) + 1));

						LLRPReaderSaveButton.setEnabled(false);

						resetLLRPReaderFields();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			} else {
				if (LLRPReaderNameText.getText().equals("")
						|| LLRPReaderTypeText.getText().equals("")
						|| LLRPDescriptionText.getText().equals("")
						|| LLRPConnectionPointAddressText.getText().equals("")
						|| LLRPConnectionPointPortText.getText().equals("")
						|| LLRPEncryptedConnectionPointAddressText.getText()
								.equals("")
						|| LLRPEncryptedConnectionPointPortText.getText()
								.equals("")
						|| LLRPReadTimeIntervalText.getText().equals("")
						|| LLRPAdaptorClassText.getText().equals("")
						|| LLRPImplClassText.getText().equals("")
						|| LLRPPhysicalReaderSourceText.getText().equals("")
						|| LLRPDescriptiveReaderSourceText.getText().equals("")
						|| LLRPReaderOperationSpecIDText.getText().equals("")) {
					MessageDialog.openError(container.getShell(), "Error",
							"All fields are mandatory!");

				} else {
					// set isComposite to false (RP Reader)
					llrpLrSpecBuilder.setIsComposite(false);

					// set Logical Reader Name
					llrpLrSpecBuilder.setLogicalReaders(LLRPReaderNameText
							.getText());

					// set LLRP properties

					// set Description property
					propertyName = "ReaderType";
					propertyValue = LLRPReaderTypeText.getText();

					llrpLrSpecBuilder.setLRProperty(index, propertyName,
							propertyValue);

					// set Description property
					propertyName = "Description";
					propertyValue = LLRPDescriptionText.getText();

					llrpLrSpecBuilder.setLRProperty(++index, propertyName,
							propertyValue);

					// set Connection point address property
					propertyName = "ConnectionPointAddress";
					propertyValue = LLRPConnectionPointAddressText.getText();

					llrpLrSpecBuilder.setLRProperty(++index, propertyName,
							propertyValue);

					// set Connection point port property
					propertyName = "ConnectionPointPort";
					propertyValue = LLRPConnectionPointPortText.getText();

					llrpLrSpecBuilder.setLRProperty(++index, propertyName,
							propertyValue);

					// set Encrypted connection point address property
					propertyName = "EncryptedConnectionPointAddress";
					propertyValue = LLRPEncryptedConnectionPointAddressText
							.getText();

					llrpLrSpecBuilder.setLRProperty(++index, propertyName,
							propertyValue);

					// set Encrypted connection point port property
					propertyName = "EncryptedConnectionPointPort";
					propertyValue = LLRPEncryptedConnectionPointPortText
							.getText();

					llrpLrSpecBuilder.setLRProperty(++index, propertyName,
							propertyValue);

					// set Read time interval property
					propertyName = "ReadTimeInterval";
					propertyValue = LLRPReadTimeIntervalText.getText();

					llrpLrSpecBuilder.setLRProperty(++index, propertyName,
							propertyValue);

					// set Adaptor class property
					propertyName = "AdaptorClass";
					propertyValue = LLRPAdaptorClassText.getText();

					llrpLrSpecBuilder.setLRProperty(++index, propertyName,
							propertyValue);

					// set Implementation class property
					propertyName = "ImplementationClass";
					propertyValue = LLRPImplClassText.getText();

					llrpLrSpecBuilder.setLRProperty(++index, propertyName,
							propertyValue);

					// set Physical reader source property
					propertyName = "PhysicalReaderSource";
					propertyValue = LLRPPhysicalReaderSourceText.getText();

					llrpLrSpecBuilder.setLRProperty(++index, propertyName,
							propertyValue);

					// set Descriptive Reader source property
					propertyName = "DescriptiveReaderSource";
					propertyValue = LLRPDescriptiveReaderSourceText.getText();

					llrpLrSpecBuilder.setLRProperty(++index, propertyName,
							propertyValue);

					// set Reader Operation Spec ID property
					propertyName = "RoSpecID";
					propertyValue = LLRPReaderOperationSpecIDText.getText();

					llrpLrSpecBuilder.setLRProperty(++index, propertyName,
							propertyValue);

					FileDialog fd;

					fd = new FileDialog(compositeLLRP.getShell(), SWT.SAVE);
					fd.setText("Save");
					fd.setFileName(LLRPReaderNameText.getText() + ".xml");
					fd.setFilterExtensions(new String[] { ".xml" });
					String selection = fd.open();
					if (!selection.endsWith(".xml")) {
						selection += ".xml";
					}

					// generate LLRP Reader Static Definition XML
					generateLLRPReaderStaticDefinitionXML(llrpLrSpecBuilder,
							selection);

					// add reader to lrspectree -> LLRPReadersNode
					llrpNodeChild = new TreeItem(LLRPReadersNode, SWT.NULL);
					llrpNodeChild.setText(selection.substring(selection
							.lastIndexOf(fileSeparator) + 1));

					LLRPReaderSaveButton.setEnabled(false);

					resetLLRPReaderFields();
				}
			}

		}
	}

	public void generateLLRPReaderStaticDefinitionXML(
			LRSpecBuilder llrpLrSpecBuilder, String selection) {
		try {
			// ///////////////////////////
			// Creating an empty XML Document

			// We need a Document
			DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory
					.newInstance();
			DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
			Document document = docBuilder.newDocument();

			// //////////////////////
			// Creating the XML tree

			// create the root element and add it to the document
			Element logicalReadersRoot = document
					.createElement("LogicalReaders");
			logicalReadersRoot.setAttribute("xmlns:xsi",
					"http://www.w3.org/2001/XMLSchema-instance");
			logicalReadersRoot.setAttribute("xsi:noNamespaceSchemaLocation",
					"/resources/LogicalReaders.xsd");
			document.appendChild(logicalReadersRoot);

			Element logicalReaderChild = document
					.createElement("LogicalReader");
			logicalReaderChild.setAttribute("name", llrpLrSpecBuilder
					.getLogicalReaders().get(0));
			logicalReadersRoot.appendChild(logicalReaderChild);

			Element lrSpecChild = document.createElement("LRSpec");
			lrSpecChild.setAttribute("isComposite", llrpLrSpecBuilder
					.getIsComposite().toString());
			lrSpecChild.setAttribute("readerType", llrpLrSpecBuilder
					.getProperties().get(0).getValue());
			logicalReaderChild.appendChild(lrSpecChild);

			for (int i = 1; i < llrpLrSpecBuilder.getProperties().size(); i++) {
				Element lrPropertyChild = document.createElement("LRProperty");
				lrPropertyChild.setAttribute("name", llrpLrSpecBuilder
						.getProperties().get(i).getName());
				lrPropertyChild.setAttribute("value", llrpLrSpecBuilder
						.getProperties().get(i).getValue());
				lrSpecChild.appendChild(lrPropertyChild);
			}

			// ///////////////
			// Output the XML

			// set up a transformer
			TransformerFactory transformerFactory = TransformerFactory
					.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			transformer.setOutputProperty(OutputKeys.STANDALONE, "yes");
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");

			// create string from xml tree
			StringWriter sw = new StringWriter();
			StreamResult result = new StreamResult(sw);
			DOMSource source = new DOMSource(document);
			transformer.transform(source, result);
			String xmlString = sw.toString();

			try {
				BufferedWriter out = new BufferedWriter(new FileWriter(
						selection));
				out.write(xmlString);
				out.close();
			} catch (IOException e) {
				MessageDialog.openError(container.getShell(), "Error",
						"Error in writing the file!");
			}
		} catch (Exception e) {
			MessageDialog.openError(container.getShell(), "Error",
					"DOM parser error!");
		}
	}

	private class LrspecAddReaderButtonMouseListener extends MouseAdapter {
		public void mouseDown(final MouseEvent e) {
			if (availableToIncludedReaders[0] == "") {
				MessageDialog.openError(container.getShell(), "Error",
						"No reader has been selected!");

			} else {

				// add selected reader to included readers
				lrspecIncludedReadersList.add(availableToIncludedReaders[0]);

				// remove reader from logical readers list (available readers)
				lrspecLogicalReadersList.remove(availableToIncludedReaders[0]);

				// set selected reader to null
				availableToIncludedReaders[0] = "";
			}
		}
	}

	private class LrspecTruncateReaderButtonMouseListener extends MouseAdapter {
		public void mouseDown(final MouseEvent e) {
			if (includedToAvailableReaders[0] == "") {
				MessageDialog.openError(container.getShell(), "Error",
						"No reader has been selected!");

			} else {

				// add selected reader to available readers
				lrspecLogicalReadersList.add(includedToAvailableReaders[0]);

				// remove reader from included readers list (available readers)
				lrspecIncludedReadersList.remove(includedToAvailableReaders[0]);

				// set selected reader to null
				includedToAvailableReaders[0] = "";
			}
		}
	}

	private class CompGenerateXMLButtonMouseListener extends MouseAdapter {
		public void mouseDown(final MouseEvent e) {

			// variables
			int index = 0;
			String propertyName = "";
			String propertyValue = "";
			compositeLrSpecBuilder = new LRSpecBuilder();

			if (CompDescriptionText.getText().equals("")
					|| CompReaderTypeText.getText().equals("")) {
				MessageDialog.openError(container.getShell(), "Error",
						"All fields are mandatory!");

			} else if (lrspecIncludedReadersList.getItemCount() == 0) {
				MessageDialog.openError(container.getShell(), "Error",
						"No reader has been selected!");

			} else if (lrspecIncludedReadersList.getItemCount() < 2) {
				MessageDialog.openError(container.getShell(), "Error",
						"At least two readers should be selected!");

			} else {
				// set isComposite to true (Composite Reader)
				compositeLrSpecBuilder.setIsComposite(true);

				// set Composite properties

				// set Reader type property
				propertyName = "ReaderType";
				propertyValue = CompReaderTypeText.getText();

				compositeLrSpecBuilder.setLRProperty(index, propertyName,
						propertyValue);

				// set Description property
				propertyName = "Description";
				propertyValue = CompDescriptionText.getText();

				compositeLrSpecBuilder.setLRProperty(++index, propertyName,
						propertyValue);

				// set list of logical readers to compositeLrSpecBuilder
				for (int i = 0; i < lrspecIncludedReadersList.getItemCount(); i++) {
					compositeLrSpecBuilder
							.setLogicalReaders(lrspecIncludedReadersList
									.getItem(i));
				}

				FileDialog fd;

				if (filterByComboSelectedItem.equals("Dynamic")) {

					fd = new FileDialog(compositeComposite.getShell(), SWT.SAVE);
					fd.setText("Save");
					fd.setFileName(".xml");
					fd.setFilterExtensions(new String[] { ".xml" });
					String selection = fd.open();
					if (!selection.endsWith(".xml")) {
						selection += ".xml";
					}

					try {
						compositeLrSpecBuilder.generateXml(selection);

						// add reader to lrspectree -> LLRPReadersNode
						compNodeChild = new TreeItem(CompositeReadersNode,
								SWT.NULL);
						compNodeChild.setText(selection.substring(selection
								.lastIndexOf(fileSeparator) + 1));

						CompReaderSaveButton.setEnabled(false);

						resetCompositeReaderFields();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				} else if (filterByComboSelectedItem.equals("Static")) {

					fd = new FileDialog(compositeComposite.getShell(), SWT.SAVE);
					fd.setText("Save");
					fd.setFileName(".xml");
					fd.setFilterExtensions(new String[] { ".xml" });
					String selection = fd.open();
					if (!selection.endsWith(".xml")) {
						selection += ".xml";
					}

					generateCompositeReaderStaticDefinitionXML(
							compositeLrSpecBuilder, selection);

					// add reader to lrspectree -> Composite Readers Node
					compNodeChild = new TreeItem(CompositeReadersNode, SWT.NULL);
					compNodeChild.setText(selection.substring(selection
							.lastIndexOf(fileSeparator) + 1));

					CompReaderSaveButton.setEnabled(false);

					resetCompositeReaderFields();
				}

			}
		}
	}

	public void generateCompositeReaderStaticDefinitionXML(
			LRSpecBuilder compositeLrSpecBuilder, String selection) {
		File[] HALDirectoryContents = getHALLRSpecsPathFolderContents();
		File[] RPDirectoryContents = getRPLRSpecsPathFolderContents();
		File[] LLRPDirectoryContents = getLLRPLRSpecsPathFolderContents();
		ArrayList<Node> nodeList = new ArrayList<Node>();
		ArrayList<String> readerFileNames = new ArrayList<String>();
		String temp = "";

		for (int i = 0; i < compositeLrSpecBuilder.getLogicalReaders().size(); i++) {
			temp = (String) staticReadersHashMap.get(compositeLrSpecBuilder
					.getLogicalReaders().get(i));
			readerFileNames.add(temp);

		}

		try {
			// ///////////////////////////
			// Creating an empty XML Document

			// We need a Document
			DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory
					.newInstance();
			DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
			Document document = docBuilder.newDocument();

			// We need a Document
			DocumentBuilderFactory dBFactory = DocumentBuilderFactory
					.newInstance();
			DocumentBuilder dBuilder = dBFactory.newDocumentBuilder();
			Document doc = dBuilder.newDocument();

			for (int i = 0; i < readerFileNames.size(); i++) {

				// search where each reader is located (if exists) and retrieve
				// appropriate node
				if (HALDirectoryContents.length != 0) {
					for (int j = 0; j < HALDirectoryContents.length; j++) {
						if (HALDirectoryContents[j].getName().equals(
								readerFileNames.get(i))) {

							try {
								File selectedFile = new File(
										PreferenceConstants.P_HAL_LRSpecsPATH
												+ HALDirectoryContents[j]
														.getName());

								if (selectedFile.exists()) {
									document = docBuilder.parse(selectedFile);

									// Get nodes list of all elements
									NodeList list = document
											.getElementsByTagName("LogicalReader");

									for (int x = 0; x < list.getLength(); x++) {
										// Get element
										nodeList.add(list.item(x));
									}
								} else {
									MessageDialog.openError(container
											.getShell(), "Error",
											"File not found!");
								}
							} catch (Exception exc) {
								MessageDialog.openError(container.getShell(),
										"Error", "HAL DOM parser error!");
							}

						}
					}
				}

				if (RPDirectoryContents.length != 0) {
					for (int j = 0; j < RPDirectoryContents.length; j++) {
						if (RPDirectoryContents[j].getName().equals(
								readerFileNames.get(i))) {

							try {
								File selectedFile = new File(
										PreferenceConstants.P_RP_LRSpecsPATH
												+ RPDirectoryContents[j]
														.getName());

								if (selectedFile.exists()) {
									document = docBuilder.parse(selectedFile);

									// Get nodes list of all elements
									NodeList list = document
											.getElementsByTagName("LogicalReader");

									for (int x = 0; x < list.getLength(); x++) {
										// Get element
										nodeList.add(list.item(x));
									}
								} else {
									MessageDialog.openError(container
											.getShell(), "Error",
											"File not found!");
								}
							} catch (Exception exc) {
								MessageDialog.openError(container.getShell(),
										"Error", "RP DOM parser error!");
							}

						}
					}
				}

				if (LLRPDirectoryContents.length != 0) {
					for (int j = 0; j < LLRPDirectoryContents.length; j++) {
						if (LLRPDirectoryContents[j].getName().equals(
								readerFileNames.get(i))) {

							try {
								File selectedFile = new File(
										PreferenceConstants.P_LLRP_LRSpecsPATH
												+ LLRPDirectoryContents[j]
														.getName());

								if (selectedFile.exists()) {
									document = docBuilder.parse(selectedFile);

									// Get nodes list of all elements
									NodeList list = document
											.getElementsByTagName("LogicalReader");

									for (int x = 0; x < list.getLength(); x++) {
										// Get element
										nodeList.add(list.item(x));
									}
								} else {
									MessageDialog.openError(container
											.getShell(), "Error",
											"File not found!");
								}
							} catch (Exception exc) {
								MessageDialog.openError(container.getShell(),
										"Error", "LLRP DOM parser error!");
							}

						}
					}
				}
			}

			// //////////////////////
			// Creating the XML tree

			// create the root element and add it to the document
			Element logicalReadersRoot = doc.createElement("LogicalReaders");
			logicalReadersRoot.setAttribute("xmlns:xsi",
					"http://www.w3.org/2001/XMLSchema-instance");
			logicalReadersRoot.setAttribute("xsi:noNamespaceSchemaLocation",
					"/resources/LogicalReaders.xsd");
			doc.appendChild(logicalReadersRoot);

			for (int j = 0; j < nodeList.size(); j++) {
				logicalReadersRoot.appendChild(doc.adoptNode(nodeList.get(j)));
			}

			Element logicalReaderChild = doc.createElement("LogicalReader");
			logicalReaderChild.setAttribute("name", CompReaderNameText
					.getText());
			logicalReadersRoot.appendChild(logicalReaderChild);

			Element lrSpecChild = doc.createElement("LRSpec");
			lrSpecChild.setAttribute("isComposite", compositeLrSpecBuilder
					.getIsComposite().toString());
			lrSpecChild.setAttribute("readerType", compositeLrSpecBuilder
					.getProperties().get(0).getValue());
			logicalReaderChild.appendChild(lrSpecChild);

			Element lrPropertyDescriptionChild = doc
					.createElement("LRProperty");
			lrPropertyDescriptionChild.setAttribute("name",
					compositeLrSpecBuilder.getProperties().get(1).getName());
			lrPropertyDescriptionChild.setAttribute("value",
					compositeLrSpecBuilder.getProperties().get(1).getValue());
			lrSpecChild.appendChild(lrPropertyDescriptionChild);

			for (int j = 0; j < compositeLrSpecBuilder.getLogicalReaders()
					.size(); j++) {
				Element lrPropertyReaderChild = doc.createElement("readers");
				lrPropertyReaderChild.setTextContent(compositeLrSpecBuilder
						.getLogicalReaders().get(j));
				lrSpecChild.appendChild(lrPropertyReaderChild);
			}

			// ///////////////
			// Output the XML

			// set up a transformer
			TransformerFactory transformerFactory = TransformerFactory
					.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			transformer.setOutputProperty(OutputKeys.STANDALONE, "yes");
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");

			// create string from xml tree
			StringWriter sw = new StringWriter();
			StreamResult result = new StreamResult(sw);
			DOMSource source = new DOMSource(doc);
			transformer.transform(source, result);
			String xmlFinalString = sw.toString();

			try {
				BufferedWriter out = new BufferedWriter(new FileWriter(
						selection));
				out.write(xmlFinalString);
				out.close();
			} catch (IOException e) {
				MessageDialog.openError(container.getShell(), "Error",
						"Error in writing the file!");
			}
		} catch (Exception e) {
			MessageDialog.openError(container.getShell(), "Error",
					"DOM parser error!");
		}

	}

	private void resetCompositeReaderFields() {
		CompReaderTypeText.deselectAll();
		CompDescriptionText.setText("");
		CompReaderNameText.setText("");
		compFilterByCombo.deselectAll();

		for (int i = 0; i < lrspecIncludedReadersList.getItemCount(); i++) {
			lrspecLogicalReadersList.add(lrspecIncludedReadersList.getItem(i));
		}

		lrspecIncludedReadersList.removeAll();
		lrspecLogicalReadersList.removeAll();
	}

	private class LrspecCompResetButtonMouseListener extends MouseAdapter {
		public void mouseDown(final MouseEvent e) {
			CompReaderTypeText.deselectAll();
			CompDescriptionText.setText("");
			CompReaderNameText.setText("");
			compFilterByCombo.deselectAll();

			for (int i = 0; i < lrspecIncludedReadersList.getItemCount(); i++) {
				lrspecLogicalReadersList.add(lrspecIncludedReadersList
						.getItem(i));
			}

			lrspecIncludedReadersList.removeAll();
			lrspecLogicalReadersList.removeAll();

			CompReaderSaveButton.setEnabled(false);
			CompReaderNameText.setEnabled(false);
			newReaderButton.setEnabled(false);
		}
	}

	private class OpenFileButtonMouseListener extends MouseAdapter {
		public void mouseDown(final MouseEvent e) {
			int readerTypePropertyIndex = 0;
			DocumentBuilderFactory documentBuilderFactory;
			DocumentBuilder documentBuilder;
			Document document = null;
			String root = "";
			lrspecIncludedReadersList.removeAll();

			FileDialog fd;

			fd = new FileDialog(compositeOpenFile.getShell(), SWT.OPEN);
			fd.setText("Open");
			fd.setFilterExtensions(new String[] { "*.xml" });
			String selection = fd.open();

			// check if the file to be opened contains a static reader or a
			// dynamic reader
			try {
				File selectedFile = new File(selection);

				if (selectedFile.exists()) {
					documentBuilderFactory = DocumentBuilderFactory
							.newInstance();
					documentBuilder = documentBuilderFactory
							.newDocumentBuilder();
					document = documentBuilder.parse(selectedFile);
					Node node = document.getDocumentElement();
					root = node.getNodeName();
				} else {
					MessageDialog.openError(container.getShell(), "Error",
							"File not found!");
				}
			} catch (Exception exc) {
				MessageDialog.openError(container.getShell(), "Error",
						"DOM parser error!");
			}

			// if the reader is dynamic
			if (root.equals("ns3:LRSpec")) {
				try {
					LRSpec lRSpec = new LRSpec();
					lRSpec = openLrSpecBuilder.getLRSpecFromFile(selection);

					// retrieve Reader Type property index
					for (int i = 0; i < lRSpec.getProperties().getProperty()
							.size(); i++) {
						if (lRSpec.getProperties().getProperty().get(i)
								.getName().equals("ReaderType")) {
							readerTypePropertyIndex = i;
						}
					}

					// if the xml file refers to a HAL reader
					if (lRSpec
							.getProperties()
							.getProperty()
							.get(readerTypePropertyIndex)
							.getValue()
							.equals(
									"org.ow2.aspirerfid.ale.server.readers.hal.HALAdaptor")) {
						tabFolder.setSelection(lrspecHALTabItem);

						resetHALReaderFields();

						HALReaderNameText.setEnabled(false);

						HALReaderNameText.setText("");

						for (int i = 0; i < lRSpec.getProperties()
								.getProperty().size(); i++) {
							if (lRSpec.getProperties().getProperty().get(i)
									.getName().equals("ReaderType")) {
								HALReaderTypeText.setText(lRSpec
										.getProperties().getProperty().get(i)
										.getValue());
							} else if (lRSpec.getProperties().getProperty()
									.get(i).getName().equals("Description")) {
								HALDescriptionText.setText(lRSpec
										.getProperties().getProperty().get(i)
										.getValue());
							} else if (lRSpec.getProperties().getProperty()
									.get(i).getName().equals("AdaptorClass")) {
								HALAdaptorClassText.setText(lRSpec
										.getProperties().getProperty().get(i)
										.getValue());
							} else if (lRSpec.getProperties().getProperty()
									.get(i).getName().equals(
											"PhysicalReaderName")) {
								HALPhysicalReaderNameText.setText(lRSpec
										.getProperties().getProperty().get(i)
										.getValue());
							} else if (lRSpec.getProperties().getProperty()
									.get(i).getName()
									.equals("ReadTimeInterval")) {
								HALReadTimeIntervalText.setText(lRSpec
										.getProperties().getProperty().get(i)
										.getValue());
							} else if (lRSpec.getProperties().getProperty()
									.get(i).getName().equals("ReadPoints")) {
								HALReadPointsText.setText(lRSpec
										.getProperties().getProperty().get(i)
										.getValue());
							} else if (lRSpec.getProperties().getProperty()
									.get(i).getName().equals("PropertiesFile")) {
								HALPropertiesFileText.setText(lRSpec
										.getProperties().getProperty().get(i)
										.getValue());
							}
						}

						HALStaticDefinitionCheck.setSelection(false);

						HALReaderSaveButton.setEnabled(true);

						existingHALReaderFileToBeSaved = selection;
					}
					// if the xml file refers to a RP reader
					else if (lRSpec
							.getProperties()
							.getProperty()
							.get(readerTypePropertyIndex)
							.getValue()
							.equals(
									"org.ow2.aspirerfid.ale.server.readers.rp.RPAdaptor")) {
						tabFolder.setSelection(lrspecRPTabItem);

						resetRPReaderFields();

						RPReaderNameText.setEnabled(false);

						RPReaderNameText.setText("");

						for (int i = 0; i < lRSpec.getProperties()
								.getProperty().size(); i++) {
							if (lRSpec.getProperties().getProperty().get(i)
									.getName().equals("ReaderType")) {
								RPReaderTypeText.setText(lRSpec.getProperties()
										.getProperty().get(i).getValue());
							} else if (lRSpec.getProperties().getProperty()
									.get(i).getName().equals("Description")) {
								RPDescriptionText.setText(lRSpec
										.getProperties().getProperty().get(i)
										.getValue());
							} else if (lRSpec.getProperties().getProperty()
									.get(i).getName().equals("ConnectionPoint")) {
								RPConnectionPointCombo.setText(lRSpec
										.getProperties().getProperty().get(i)
										.getValue());
							} else if (lRSpec.getProperties().getProperty()
									.get(i).getName().equals(
											"NotificationPoint")) {
								RPNotificationPointCombo.setText(lRSpec
										.getProperties().getProperty().get(i)
										.getValue());
							} else if (lRSpec.getProperties().getProperty()
									.get(i).getName()
									.equals("ReadTimeInterval")) {
								RPReadTimeIntervalText.setText(lRSpec
										.getProperties().getProperty().get(i)
										.getValue());
							} else if (lRSpec.getProperties().getProperty()
									.get(i).getName().equals("AdaptorClass")) {
								RPAdaptorClassText.setText(lRSpec
										.getProperties().getProperty().get(i)
										.getValue());
							} else if (lRSpec.getProperties().getProperty()
									.get(i).getName().equals(
											"ImplementationClass")) {
								RPImplClassText.setText(lRSpec.getProperties()
										.getProperty().get(i).getValue());
							} else if (lRSpec.getProperties().getProperty()
									.get(i).getName().equals(
											"PhysicalReaderName")) {
								RPPhysicalReaderNameText.setText(lRSpec
										.getProperties().getProperty().get(i)
										.getValue());
							} else if (lRSpec.getProperties().getProperty()
									.get(i).getName().equals(
											"PhysicalReaderSource")) {
								RPPhysicalReaderSourceText.setText(lRSpec
										.getProperties().getProperty().get(i)
										.getValue());
							}
						}

						RPStaticDefinitionCheck.setSelection(false);

						RPReaderSaveButton.setEnabled(true);

						existingRPReaderFileToBeSaved = selection;
					}
					// if the xml file refers to a LLRP reader
					else if (lRSpec
							.getProperties()
							.getProperty()
							.get(readerTypePropertyIndex)
							.getValue()
							.equals(
									"org.ow2.aspirerfid.ale.server.readers.llrp.LLRPAdaptor")) {
						tabFolder.setSelection(lrspecLLRPTabItem);

						resetLLRPReaderFields();

						LLRPReaderNameText.setEnabled(false);

						LLRPReaderNameText.setText("");

						for (int i = 0; i < lRSpec.getProperties()
								.getProperty().size(); i++) {
							if (lRSpec.getProperties().getProperty().get(i)
									.getName().equals("ReaderType")) {
								LLRPReaderTypeText.setText(lRSpec
										.getProperties().getProperty().get(i)
										.getValue());
							} else if (lRSpec.getProperties().getProperty()
									.get(i).getName().equals("Description")) {
								LLRPDescriptionText.setText(lRSpec
										.getProperties().getProperty().get(i)
										.getValue());
							} else if (lRSpec.getProperties().getProperty()
									.get(i).getName().equals(
											"ConnectionPointAddress")) {
								LLRPConnectionPointAddressText.setText(lRSpec
										.getProperties().getProperty().get(i)
										.getValue());
							} else if (lRSpec.getProperties().getProperty()
									.get(i).getName().equals(
											"ConnectionPointPort")) {
								LLRPConnectionPointPortText.setText(lRSpec
										.getProperties().getProperty().get(i)
										.getValue());
							} else if (lRSpec.getProperties().getProperty()
									.get(i).getName().equals(
											"EncryptedConnectionPointAddress")) {
								LLRPEncryptedConnectionPointAddressText
										.setText(lRSpec.getProperties()
												.getProperty().get(i)
												.getValue());
							} else if (lRSpec.getProperties().getProperty()
									.get(i).getName().equals(
											"EncryptedConnectionPointPort")) {
								LLRPEncryptedConnectionPointPortText
										.setText(lRSpec.getProperties()
												.getProperty().get(i)
												.getValue());
							} else if (lRSpec.getProperties().getProperty()
									.get(i).getName()
									.equals("ReadTimeInterval")) {
								LLRPReadTimeIntervalText.setText(lRSpec
										.getProperties().getProperty().get(i)
										.getValue());
							} else if (lRSpec.getProperties().getProperty()
									.get(i).getName().equals("AdaptorClass")) {
								LLRPAdaptorClassText.setText(lRSpec
										.getProperties().getProperty().get(i)
										.getValue());
							} else if (lRSpec.getProperties().getProperty()
									.get(i).getName().equals(
											"ImplementationClass")) {
								LLRPImplClassText.setText(lRSpec
										.getProperties().getProperty().get(i)
										.getValue());
							} else if (lRSpec.getProperties().getProperty()
									.get(i).getName().equals(
											"PhysicalReaderSource")) {
								LLRPPhysicalReaderSourceText.setText(lRSpec
										.getProperties().getProperty().get(i)
										.getValue());
							} else if (lRSpec.getProperties().getProperty()
									.get(i).getName().equals(
											"DescriptiveReaderSource")) {
								LLRPDescriptiveReaderSourceText.setText(lRSpec
										.getProperties().getProperty().get(i)
										.getValue());
							} else if (lRSpec.getProperties().getProperty()
									.get(i).getName().equals("RoSpecID")) {
								LLRPReaderOperationSpecIDText.setText(lRSpec
										.getProperties().getProperty().get(i)
										.getValue());
							}
						}

						LLRPStaticDefinitionCheck.setSelection(false);

						LLRPReaderSaveButton.setEnabled(true);

						existingLLRPReaderFileToBeSaved = selection;
					}
					// if the xml file refers to a Composite reader
					else if (lRSpec
							.getProperties()
							.getProperty()
							.get(readerTypePropertyIndex)
							.getValue()
							.equals(
									"org.ow2.aspirerfid.ale.server.readers.CompositeReader")) {
						tabFolder.setSelection(lrspecCompositeTabItem);

						resetCompositeReaderFields();

						CompReaderNameText.setEnabled(false);
						newReaderButton.setEnabled(true);

						for (int i = 0; i < lRSpec.getReaders().getReader()
								.size(); i++) {
							lrspecIncludedReadersList.add(lRSpec.getReaders()
									.getReader().get(i));
						}

						for (int i = 0; i < lRSpec.getProperties()
								.getProperty().size(); i++) {
							if (lRSpec.getProperties().getProperty().get(i)
									.getName().equals("ReaderType")) {
								CompReaderTypeText.setText(lRSpec
										.getProperties().getProperty().get(i)
										.getValue());
							} else if (lRSpec.getProperties().getProperty()
									.get(i).getName().equals("Description")) {
								CompDescriptionText.setText(lRSpec
										.getProperties().getProperty().get(i)
										.getValue());
							}
						}

						compFilterByCombo.setText("Dynamic");

						CompReaderSaveButton.setEnabled(true);

						existingCompositeReaderFileToBeSaved = selection;
					}

				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				// if the reader is static
			} else if (root.equals("LogicalReaders")) {
				NodeList lrSpecNodeList = document
						.getElementsByTagName("LRSpec");

				if (lrSpecNodeList.getLength() == 1) {
					if (lrSpecNodeList
							.item(0)
							.getAttributes()
							.item(1)
							.getNodeValue()
							.equals(
									"org.ow2.aspirerfid.ale.server.readers.hal.HALAdaptor")) {
						tabFolder.setSelection(lrspecHALTabItem);

						resetHALReaderFields();

						HALReaderNameText.setEnabled(true);

						// set reader name text
						NodeList logicalReaderNodeList = document
								.getElementsByTagName("LogicalReader");
						HALReaderNameText.setText(logicalReaderNodeList.item(0)
								.getAttributes().item(0).getNodeValue());

						HALReaderTypeText.setText(lrSpecNodeList.item(0)
								.getAttributes().item(1).getNodeValue());

						NodeList lrPropertyNodeList = document
								.getElementsByTagName("LRProperty");

						for (int i = 0; i < lrPropertyNodeList.getLength(); i++) {
							if (lrPropertyNodeList.item(i).getAttributes()
									.item(0).getTextContent().equals(
											"Description")) {
								HALDescriptionText.setText(lrPropertyNodeList
										.item(i).getAttributes().item(1)
										.getNodeValue());
							} else if (lrPropertyNodeList.item(i)
									.getAttributes().item(0).getTextContent()
									.equals("AdaptorClass")) {
								HALAdaptorClassText.setText(lrPropertyNodeList
										.item(i).getAttributes().item(1)
										.getNodeValue());
							} else if (lrPropertyNodeList.item(i)
									.getAttributes().item(0).getTextContent()
									.equals("PhysicalReaderName")) {
								HALPhysicalReaderNameText
										.setText(lrPropertyNodeList.item(i)
												.getAttributes().item(1)
												.getNodeValue());
							} else if (lrPropertyNodeList.item(i)
									.getAttributes().item(0).getTextContent()
									.equals("ReadTimeInterval")) {
								HALReadTimeIntervalText
										.setText(lrPropertyNodeList.item(i)
												.getAttributes().item(1)
												.getNodeValue());
							} else if (lrPropertyNodeList.item(i)
									.getAttributes().item(0).getTextContent()
									.equals("ReadPoints")) {
								HALReadPointsText.setText(lrPropertyNodeList
										.item(i).getAttributes().item(1)
										.getNodeValue());
							} else if (lrPropertyNodeList.item(i)
									.getAttributes().item(0).getTextContent()
									.equals("PropertiesFile")) {
								HALPropertiesFileText
										.setText(lrPropertyNodeList.item(i)
												.getAttributes().item(1)
												.getNodeValue());
							}
						}

						HALStaticDefinitionCheck.setSelection(true);

						HALReaderSaveButton.setEnabled(true);

						existingHALReaderFileToBeSaved = selection;
					} else if (lrSpecNodeList
							.item(0)
							.getAttributes()
							.item(1)
							.getNodeValue()
							.equals(
									"org.ow2.aspirerfid.ale.server.readers.rp.RPAdaptor")) {
						tabFolder.setSelection(lrspecRPTabItem);

						resetRPReaderFields();

						RPReaderNameText.setEnabled(true);

						// set reader name text
						NodeList logicalReaderNodeList = document
								.getElementsByTagName("LogicalReader");
						RPReaderNameText.setText(logicalReaderNodeList.item(0)
								.getAttributes().item(0).getNodeValue());

						RPReaderTypeText.setText(lrSpecNodeList.item(0)
								.getAttributes().item(1).getNodeValue());

						NodeList lrPropertyNodeList = document
								.getElementsByTagName("LRProperty");

						for (int i = 0; i < lrPropertyNodeList.getLength(); i++) {
							if (lrPropertyNodeList.item(i).getAttributes()
									.item(0).getTextContent().equals(
											"Description")) {
								RPDescriptionText.setText(lrPropertyNodeList
										.item(i).getAttributes().item(1)
										.getNodeValue());
							} else if (lrPropertyNodeList.item(i)
									.getAttributes().item(0).getTextContent()
									.equals("ConnectionPoint")) {
								RPConnectionPointCombo
										.setText(lrPropertyNodeList.item(i)
												.getAttributes().item(1)
												.getNodeValue());
							} else if (lrPropertyNodeList.item(i)
									.getAttributes().item(0).getTextContent()
									.equals("NotificationPoint")) {
								RPNotificationPointCombo
										.setText(lrPropertyNodeList.item(i)
												.getAttributes().item(1)
												.getNodeValue());
							} else if (lrPropertyNodeList.item(i)
									.getAttributes().item(0).getTextContent()
									.equals("ReadTimeInterval")) {
								RPReadTimeIntervalText
										.setText(lrPropertyNodeList.item(i)
												.getAttributes().item(1)
												.getNodeValue());
							} else if (lrPropertyNodeList.item(i)
									.getAttributes().item(0).getTextContent()
									.equals("AdaptorClass")) {
								RPAdaptorClassText.setText(lrPropertyNodeList
										.item(i).getAttributes().item(1)
										.getNodeValue());
							} else if (lrPropertyNodeList.item(i)
									.getAttributes().item(0).getTextContent()
									.equals("ImplementationClass")) {
								RPImplClassText.setText(lrPropertyNodeList
										.item(i).getAttributes().item(1)
										.getNodeValue());
							} else if (lrPropertyNodeList.item(i)
									.getAttributes().item(0).getTextContent()
									.equals("PhysicalReaderName")) {
								RPPhysicalReaderNameText
										.setText(lrPropertyNodeList.item(i)
												.getAttributes().item(1)
												.getNodeValue());
							} else if (lrPropertyNodeList.item(i)
									.getAttributes().item(0).getTextContent()
									.equals("PhysicalReaderSource")) {
								RPPhysicalReaderSourceText
										.setText(lrPropertyNodeList.item(i)
												.getAttributes().item(1)
												.getNodeValue());
							}
						}

						RPStaticDefinitionCheck.setSelection(true);

						RPReaderSaveButton.setEnabled(true);

						existingRPReaderFileToBeSaved = selection;
					} else if (lrSpecNodeList
							.item(0)
							.getAttributes()
							.item(1)
							.getNodeValue()
							.equals(
									"org.ow2.aspirerfid.ale.server.readers.llrp.LLRPAdaptor")) {
						tabFolder.setSelection(lrspecLLRPTabItem);

						resetLLRPReaderFields();

						LLRPReaderNameText.setEnabled(true);

						// set reader name text
						NodeList logicalReaderNodeList = document
								.getElementsByTagName("LogicalReader");
						LLRPReaderNameText
								.setText(logicalReaderNodeList.item(0)
										.getAttributes().item(0).getNodeValue());

						LLRPReaderTypeText.setText(lrSpecNodeList.item(0)
								.getAttributes().item(1).getNodeValue());

						NodeList lrPropertyNodeList = document
								.getElementsByTagName("LRProperty");

						for (int i = 0; i < lrPropertyNodeList.getLength(); i++) {
							if (lrPropertyNodeList.item(i).getAttributes()
									.item(0).getTextContent().equals(
											"Description")) {
								LLRPDescriptionText.setText(lrPropertyNodeList
										.item(i).getAttributes().item(1)
										.getNodeValue());
							} else if (lrPropertyNodeList.item(i)
									.getAttributes().item(0).getTextContent()
									.equals("ConnectionPointAddress")) {
								LLRPConnectionPointAddressText
										.setText(lrPropertyNodeList.item(i)
												.getAttributes().item(1)
												.getNodeValue());
							} else if (lrPropertyNodeList.item(i)
									.getAttributes().item(0).getTextContent()
									.equals("ConnectionPointPort")) {
								LLRPConnectionPointPortText
										.setText(lrPropertyNodeList.item(i)
												.getAttributes().item(1)
												.getNodeValue());
							} else if (lrPropertyNodeList.item(i)
									.getAttributes().item(0).getTextContent()
									.equals("EncryptedConnectionPointAddress")) {
								LLRPEncryptedConnectionPointAddressText
										.setText(lrPropertyNodeList.item(i)
												.getAttributes().item(1)
												.getNodeValue());
							} else if (lrPropertyNodeList.item(i)
									.getAttributes().item(0).getTextContent()
									.equals("EncryptedConnectionPointPort")) {
								LLRPEncryptedConnectionPointPortText
										.setText(lrPropertyNodeList.item(i)
												.getAttributes().item(1)
												.getNodeValue());
							} else if (lrPropertyNodeList.item(i)
									.getAttributes().item(0).getTextContent()
									.equals("ReadTimeInterval")) {
								LLRPReadTimeIntervalText
										.setText(lrPropertyNodeList.item(i)
												.getAttributes().item(1)
												.getNodeValue());
							} else if (lrPropertyNodeList.item(i)
									.getAttributes().item(0).getTextContent()
									.equals("AdaptorClass")) {
								LLRPAdaptorClassText.setText(lrPropertyNodeList
										.item(i).getAttributes().item(1)
										.getNodeValue());
							} else if (lrPropertyNodeList.item(i)
									.getAttributes().item(0).getTextContent()
									.equals("ImplementationClass")) {
								LLRPImplClassText.setText(lrPropertyNodeList
										.item(i).getAttributes().item(1)
										.getNodeValue());
							} else if (lrPropertyNodeList.item(i)
									.getAttributes().item(0).getTextContent()
									.equals("PhysicalReaderSource")) {
								LLRPPhysicalReaderSourceText
										.setText(lrPropertyNodeList.item(i)
												.getAttributes().item(1)
												.getNodeValue());
							} else if (lrPropertyNodeList.item(i)
									.getAttributes().item(0).getTextContent()
									.equals("DescriptiveReaderSource")) {
								LLRPDescriptiveReaderSourceText
										.setText(lrPropertyNodeList.item(i)
												.getAttributes().item(1)
												.getNodeValue());
							} else if (lrPropertyNodeList.item(i)
									.getAttributes().item(0).getTextContent()
									.equals("RoSpecID")) {
								LLRPReaderOperationSpecIDText
										.setText(lrPropertyNodeList.item(i)
												.getAttributes().item(1)
												.getNodeValue());
							}
						}

						LLRPStaticDefinitionCheck.setSelection(true);

						LLRPReaderSaveButton.setEnabled(true);

						existingLLRPReaderFileToBeSaved = selection;
					}
				} else {
					tabFolder.setSelection(lrspecCompositeTabItem);

					CompReaderNameText.setEnabled(true);
					newReaderButton.setEnabled(false);

					resetCompositeReaderFields();

					NodeList logicalReaderNodeList = document
							.getElementsByTagName("LogicalReader");
					CompReaderNameText.setText(logicalReaderNodeList.item(
							logicalReaderNodeList.getLength() - 1)
							.getAttributes().item(0).getNodeValue());

					CompReaderTypeText.setText(lrSpecNodeList.item(
							lrSpecNodeList.getLength() - 1).getAttributes()
							.item(1).getNodeValue());

					NodeList lrPropertyNodeList = document
							.getElementsByTagName("LRProperty");
					CompDescriptionText.setText((lrPropertyNodeList.item(
							lrPropertyNodeList.getLength() - 1).getAttributes()
							.item(1).getNodeValue()));

					NodeList readersNodesList = document
							.getElementsByTagName("readers");
					for (int i = 0; i < readersNodesList.getLength(); i++) {
						lrspecIncludedReadersList.add(readersNodesList.item(i)
								.getTextContent());
					}

					compFilterByCombo.setText("Static");

					CompReaderSaveButton.setEnabled(true);

					existingCompositeReaderFileToBeSaved = selection;
				}
			}
		}
	}

	private class LrspecTreeMouseListener extends MouseAdapter {
		public void mouseDown(final MouseEvent e) {
			DocumentBuilderFactory documentBuilderFactory;
			DocumentBuilder documentBuilder;
			Document document = null;
			String root = "";
			String lrspectreeSelectedItem = "";
			String lrspectreeSelectedItemParent = "";

			Point point = new Point(e.x, e.y);

			TreeItem item = lrspecTree.getItem(point);

			if (item != null) {
				lrspectreeSelectedItem = item.getText();

				if (lrspectreeSelectedItem.equals("HAL Readers")
						|| lrspectreeSelectedItem.equals("RP Readers")
						|| lrspectreeSelectedItem.equals("LLRP Readers")
						|| lrspectreeSelectedItem.equals("Composite Readers")) {
					return;
				} else {
					lrspectreeSelectedItemParent = item.getParentItem()
							.getText();
				}
			}

			if (lrspectreeSelectedItemParent.equals("HAL Readers")) {

				// check if the file to be opened contains a static reader or a
				// dynamic reader
				try {
					File selectedFile = new File(
							PreferenceConstants.P_HAL_LRSpecsPATH
									+ lrspectreeSelectedItem);

					if (selectedFile.exists()) {
						documentBuilderFactory = DocumentBuilderFactory
								.newInstance();
						documentBuilder = documentBuilderFactory
								.newDocumentBuilder();
						document = documentBuilder.parse(selectedFile);
						Node node = document.getDocumentElement();
						root = node.getNodeName();
					} else {
						MessageDialog.openError(container.getShell(), "Error",
								"File not found!");
					}
				} catch (Exception exc) {
					MessageDialog.openError(container.getShell(), "Error",
							"DOM parser error!");
				}

				// Dynamic definition
				if (root.equals("ns3:LRSpec")) {
					try {
						LRSpec lRSpec = new LRSpec();
						lRSpec = openLrSpecBuilder
								.getLRSpecFromFile(PreferenceConstants.P_HAL_LRSpecsPATH
										+ lrspectreeSelectedItem);

						tabFolder.setSelection(lrspecHALTabItem);

						resetHALReaderFields();

						HALReaderNameText.setEnabled(false);

						HALReaderNameText.setText("");

						for (int i = 0; i < lRSpec.getProperties()
								.getProperty().size(); i++) {
							if (lRSpec.getProperties().getProperty().get(i)
									.getName().equals("ReaderType")) {
								HALReaderTypeText.setText(lRSpec
										.getProperties().getProperty().get(i)
										.getValue());
							} else if (lRSpec.getProperties().getProperty()
									.get(i).getName().equals("Description")) {
								HALDescriptionText.setText(lRSpec
										.getProperties().getProperty().get(i)
										.getValue());
							} else if (lRSpec.getProperties().getProperty()
									.get(i).getName().equals("AdaptorClass")) {
								HALAdaptorClassText.setText(lRSpec
										.getProperties().getProperty().get(i)
										.getValue());
							} else if (lRSpec.getProperties().getProperty()
									.get(i).getName().equals(
											"PhysicalReaderName")) {
								HALPhysicalReaderNameText.setText(lRSpec
										.getProperties().getProperty().get(i)
										.getValue());
							} else if (lRSpec.getProperties().getProperty()
									.get(i).getName()
									.equals("ReadTimeInterval")) {
								HALReadTimeIntervalText.setText(lRSpec
										.getProperties().getProperty().get(i)
										.getValue());
							} else if (lRSpec.getProperties().getProperty()
									.get(i).getName().equals("ReadPoints")) {
								HALReadPointsText.setText(lRSpec
										.getProperties().getProperty().get(i)
										.getValue());
							} else if (lRSpec.getProperties().getProperty()
									.get(i).getName().equals("PropertiesFile")) {
								HALPropertiesFileText.setText(lRSpec
										.getProperties().getProperty().get(i)
										.getValue());
							}
						}

						HALStaticDefinitionCheck.setSelection(false);

						HALReaderSaveButton.setEnabled(true);

						existingHALReaderFileToBeSaved = PreferenceConstants.P_HAL_LRSpecsPATH
								+ lrspectreeSelectedItem;
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				} // Static definition
				else if (root.equals("LogicalReaders")) {

					tabFolder.setSelection(lrspecHALTabItem);

					resetHALReaderFields();

					HALReaderNameText.setEnabled(true);

					NodeList lrSpecNodeList = document
							.getElementsByTagName("LRSpec");

					// set reader name text
					NodeList logicalReaderNodeList = document
							.getElementsByTagName("LogicalReader");
					HALReaderNameText.setText(logicalReaderNodeList.item(0)
							.getAttributes().item(0).getNodeValue());

					HALReaderTypeText.setText(lrSpecNodeList.item(0)
							.getAttributes().item(1).getNodeValue());

					NodeList lrPropertyNodeList = document
							.getElementsByTagName("LRProperty");

					for (int i = 0; i < lrPropertyNodeList.getLength(); i++) {
						if (lrPropertyNodeList.item(i).getAttributes().item(0)
								.getTextContent().equals("Description")) {
							HALDescriptionText.setText(lrPropertyNodeList.item(
									i).getAttributes().item(1).getNodeValue());
						} else if (lrPropertyNodeList.item(i).getAttributes()
								.item(0).getTextContent()
								.equals("AdaptorClass")) {
							HALAdaptorClassText.setText(lrPropertyNodeList
									.item(i).getAttributes().item(1)
									.getNodeValue());
						} else if (lrPropertyNodeList.item(i).getAttributes()
								.item(0).getTextContent().equals(
										"PhysicalReaderName")) {
							HALPhysicalReaderNameText
									.setText(lrPropertyNodeList.item(i)
											.getAttributes().item(1)
											.getNodeValue());
						} else if (lrPropertyNodeList.item(i).getAttributes()
								.item(0).getTextContent().equals(
										"ReadTimeInterval")) {
							HALReadTimeIntervalText.setText(lrPropertyNodeList
									.item(i).getAttributes().item(1)
									.getNodeValue());
						} else if (lrPropertyNodeList.item(i).getAttributes()
								.item(0).getTextContent().equals("ReadPoints")) {
							HALReadPointsText.setText(lrPropertyNodeList
									.item(i).getAttributes().item(1)
									.getNodeValue());
						} else if (lrPropertyNodeList.item(i).getAttributes()
								.item(0).getTextContent().equals(
										"PropertiesFile")) {
							HALPropertiesFileText.setText(lrPropertyNodeList
									.item(i).getAttributes().item(1)
									.getNodeValue());
						}
					}

					HALStaticDefinitionCheck.setSelection(true);

					HALReaderSaveButton.setEnabled(true);

					existingHALReaderFileToBeSaved = PreferenceConstants.P_HAL_LRSpecsPATH
							+ lrspectreeSelectedItem;
				}
			} else if (lrspectreeSelectedItemParent.equals("RP Readers")) {

				// check if the file to be opened contains a static reader or a
				// dynamic reader
				try {
					File selectedFile = new File(
							PreferenceConstants.P_RP_LRSpecsPATH
									+ lrspectreeSelectedItem);

					if (selectedFile.exists()) {
						documentBuilderFactory = DocumentBuilderFactory
								.newInstance();
						documentBuilder = documentBuilderFactory
								.newDocumentBuilder();
						document = documentBuilder.parse(selectedFile);
						Node node = document.getDocumentElement();
						root = node.getNodeName();
					} else {
						MessageDialog.openError(container.getShell(), "Error",
								"File not found!");
					}
				} catch (Exception exc) {
					MessageDialog.openError(container.getShell(), "Error",
							"DOM parser error!");
				}

				if (root.equals("ns3:LRSpec")) {
					try {
						LRSpec lRSpec = new LRSpec();
						lRSpec = openLrSpecBuilder
								.getLRSpecFromFile(PreferenceConstants.P_RP_LRSpecsPATH
										+ lrspectreeSelectedItem);

						tabFolder.setSelection(lrspecRPTabItem);

						resetRPReaderFields();

						RPReaderNameText.setEnabled(false);

						RPReaderNameText.setText("");

						for (int i = 0; i < lRSpec.getProperties()
								.getProperty().size(); i++) {
							if (lRSpec.getProperties().getProperty().get(i)
									.getName().equals("ReaderType")) {
								RPReaderTypeText.setText(lRSpec.getProperties()
										.getProperty().get(i).getValue());
							} else if (lRSpec.getProperties().getProperty()
									.get(i).getName().equals("Description")) {
								RPDescriptionText.setText(lRSpec
										.getProperties().getProperty().get(i)
										.getValue());
							} else if (lRSpec.getProperties().getProperty()
									.get(i).getName().equals("ConnectionPoint")) {
								RPConnectionPointCombo.setText(lRSpec
										.getProperties().getProperty().get(i)
										.getValue());
							} else if (lRSpec.getProperties().getProperty()
									.get(i).getName().equals(
											"NotificationPoint")) {
								RPNotificationPointCombo.setText(lRSpec
										.getProperties().getProperty().get(i)
										.getValue());
							} else if (lRSpec.getProperties().getProperty()
									.get(i).getName()
									.equals("ReadTimeInterval")) {
								RPReadTimeIntervalText.setText(lRSpec
										.getProperties().getProperty().get(i)
										.getValue());
							} else if (lRSpec.getProperties().getProperty()
									.get(i).getName().equals("AdaptorClass")) {
								RPAdaptorClassText.setText(lRSpec
										.getProperties().getProperty().get(i)
										.getValue());
							} else if (lRSpec.getProperties().getProperty()
									.get(i).getName().equals(
											"ImplementationClass")) {
								RPImplClassText.setText(lRSpec.getProperties()
										.getProperty().get(i).getValue());
							} else if (lRSpec.getProperties().getProperty()
									.get(i).getName().equals(
											"PhysicalReaderName")) {
								RPPhysicalReaderNameText.setText(lRSpec
										.getProperties().getProperty().get(i)
										.getValue());
							} else if (lRSpec.getProperties().getProperty()
									.get(i).getName().equals(
											"PhysicalReaderSource")) {
								RPPhysicalReaderSourceText.setText(lRSpec
										.getProperties().getProperty().get(i)
										.getValue());
							}
						}

						RPStaticDefinitionCheck.setSelection(false);

						RPReaderSaveButton.setEnabled(true);

						existingRPReaderFileToBeSaved = PreferenceConstants.P_RP_LRSpecsPATH
								+ lrspectreeSelectedItem;
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				} else if (root.equals("LogicalReaders")) {
					tabFolder.setSelection(lrspecRPTabItem);

					resetRPReaderFields();

					RPReaderNameText.setEnabled(true);

					NodeList lrSpecNodeList = document
							.getElementsByTagName("LRSpec");

					// set reader name text
					NodeList logicalReaderNodeList = document
							.getElementsByTagName("LogicalReader");
					RPReaderNameText.setText(logicalReaderNodeList.item(0)
							.getAttributes().item(0).getNodeValue());

					RPReaderTypeText.setText(lrSpecNodeList.item(0)
							.getAttributes().item(1).getNodeValue());

					NodeList lrPropertyNodeList = document
							.getElementsByTagName("LRProperty");

					for (int i = 0; i < lrPropertyNodeList.getLength(); i++) {
						if (lrPropertyNodeList.item(i).getAttributes().item(0)
								.getTextContent().equals("Description")) {
							RPDescriptionText.setText(lrPropertyNodeList
									.item(i).getAttributes().item(1)
									.getNodeValue());
						} else if (lrPropertyNodeList.item(i).getAttributes()
								.item(0).getTextContent().equals(
										"ConnectionPoint")) {
							RPConnectionPointCombo.setText(lrPropertyNodeList
									.item(i).getAttributes().item(1)
									.getNodeValue());
						} else if (lrPropertyNodeList.item(i).getAttributes()
								.item(0).getTextContent().equals(
										"NotificationPoint")) {
							RPNotificationPointCombo.setText(lrPropertyNodeList
									.item(i).getAttributes().item(1)
									.getNodeValue());
						} else if (lrPropertyNodeList.item(i).getAttributes()
								.item(0).getTextContent().equals(
										"ReadTimeInterval")) {
							RPReadTimeIntervalText.setText(lrPropertyNodeList
									.item(i).getAttributes().item(1)
									.getNodeValue());
						} else if (lrPropertyNodeList.item(i).getAttributes()
								.item(0).getTextContent()
								.equals("AdaptorClass")) {
							RPAdaptorClassText.setText(lrPropertyNodeList.item(
									i).getAttributes().item(1).getNodeValue());
						} else if (lrPropertyNodeList.item(i).getAttributes()
								.item(0).getTextContent().equals(
										"ImplementationClass")) {
							RPImplClassText.setText(lrPropertyNodeList.item(i)
									.getAttributes().item(1).getNodeValue());
						} else if (lrPropertyNodeList.item(i).getAttributes()
								.item(0).getTextContent().equals(
										"PhysicalReaderName")) {
							RPPhysicalReaderNameText.setText(lrPropertyNodeList
									.item(i).getAttributes().item(1)
									.getNodeValue());
						} else if (lrPropertyNodeList.item(i).getAttributes()
								.item(0).getTextContent().equals(
										"PhysicalReaderSource")) {
							RPPhysicalReaderSourceText
									.setText(lrPropertyNodeList.item(i)
											.getAttributes().item(1)
											.getNodeValue());
						}
					}

					RPStaticDefinitionCheck.setSelection(true);

					RPReaderSaveButton.setEnabled(true);

					existingRPReaderFileToBeSaved = PreferenceConstants.P_RP_LRSpecsPATH
							+ lrspectreeSelectedItem;
				}
			} else if (lrspectreeSelectedItemParent.equals("LLRP Readers")) {

				// check if the file to be opened contains a static reader or a
				// dynamic reader
				try {
					File selectedFile = new File(
							PreferenceConstants.P_LLRP_LRSpecsPATH
									+ lrspectreeSelectedItem);

					if (selectedFile.exists()) {
						documentBuilderFactory = DocumentBuilderFactory
								.newInstance();
						documentBuilder = documentBuilderFactory
								.newDocumentBuilder();
						document = documentBuilder.parse(selectedFile);
						Node node = document.getDocumentElement();
						root = node.getNodeName();
					} else {
						MessageDialog.openError(container.getShell(), "Error",
								"File not found!");
					}
				} catch (Exception exc) {
					MessageDialog.openError(container.getShell(), "Error",
							"DOM parser error!");
				}

				if (root.equals("ns3:LRSpec")) {
					try {
						LRSpec lRSpec = new LRSpec();
						lRSpec = openLrSpecBuilder
								.getLRSpecFromFile(PreferenceConstants.P_LLRP_LRSpecsPATH
										+ lrspectreeSelectedItem);

						tabFolder.setSelection(lrspecLLRPTabItem);

						resetLLRPReaderFields();

						LLRPReaderNameText.setEnabled(false);

						LLRPReaderNameText.setText("");

						for (int i = 0; i < lRSpec.getProperties()
								.getProperty().size(); i++) {
							if (lRSpec.getProperties().getProperty().get(i)
									.getName().equals("ReaderType")) {
								LLRPReaderTypeText.setText(lRSpec
										.getProperties().getProperty().get(i)
										.getValue());
							} else if (lRSpec.getProperties().getProperty()
									.get(i).getName().equals("Description")) {
								LLRPDescriptionText.setText(lRSpec
										.getProperties().getProperty().get(i)
										.getValue());
							} else if (lRSpec.getProperties().getProperty()
									.get(i).getName().equals(
											"ConnectionPointAddress")) {
								LLRPConnectionPointAddressText.setText(lRSpec
										.getProperties().getProperty().get(i)
										.getValue());
							} else if (lRSpec.getProperties().getProperty()
									.get(i).getName().equals(
											"ConnectionPointPort")) {
								LLRPConnectionPointPortText.setText(lRSpec
										.getProperties().getProperty().get(i)
										.getValue());
							} else if (lRSpec.getProperties().getProperty()
									.get(i).getName().equals(
											"EncryptedConnectionPointAddress")) {
								LLRPEncryptedConnectionPointAddressText
										.setText(lRSpec.getProperties()
												.getProperty().get(i)
												.getValue());
							} else if (lRSpec.getProperties().getProperty()
									.get(i).getName().equals(
											"EncryptedConnectionPointPort")) {
								LLRPEncryptedConnectionPointPortText
										.setText(lRSpec.getProperties()
												.getProperty().get(i)
												.getValue());
							} else if (lRSpec.getProperties().getProperty()
									.get(i).getName()
									.equals("ReadTimeInterval")) {
								LLRPReadTimeIntervalText.setText(lRSpec
										.getProperties().getProperty().get(i)
										.getValue());
							} else if (lRSpec.getProperties().getProperty()
									.get(i).getName().equals("AdaptorClass")) {
								LLRPAdaptorClassText.setText(lRSpec
										.getProperties().getProperty().get(i)
										.getValue());
							} else if (lRSpec.getProperties().getProperty()
									.get(i).getName().equals(
											"ImplementationClass")) {
								LLRPImplClassText.setText(lRSpec
										.getProperties().getProperty().get(i)
										.getValue());
							} else if (lRSpec.getProperties().getProperty()
									.get(i).getName().equals(
											"PhysicalReaderSource")) {
								LLRPPhysicalReaderSourceText.setText(lRSpec
										.getProperties().getProperty().get(i)
										.getValue());
							} else if (lRSpec.getProperties().getProperty()
									.get(i).getName().equals(
											"DescriptiveReaderSource")) {
								LLRPDescriptiveReaderSourceText.setText(lRSpec
										.getProperties().getProperty().get(i)
										.getValue());
							} else if (lRSpec.getProperties().getProperty()
									.get(i).getName().equals("RoSpecID")) {
								LLRPReaderOperationSpecIDText.setText(lRSpec
										.getProperties().getProperty().get(i)
										.getValue());
							}
						}

						LLRPStaticDefinitionCheck.setSelection(false);

						LLRPReaderSaveButton.setEnabled(true);

						existingLLRPReaderFileToBeSaved = PreferenceConstants.P_LLRP_LRSpecsPATH
								+ lrspectreeSelectedItem;
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				} else if (root.equals("LogicalReaders")) {
					tabFolder.setSelection(lrspecLLRPTabItem);

					resetLLRPReaderFields();

					LLRPReaderNameText.setEnabled(true);

					NodeList lrSpecNodeList = document
							.getElementsByTagName("LRSpec");

					// set reader name text
					NodeList logicalReaderNodeList = document
							.getElementsByTagName("LogicalReader");
					LLRPReaderNameText.setText(logicalReaderNodeList.item(0)
							.getAttributes().item(0).getNodeValue());

					LLRPReaderTypeText.setText(lrSpecNodeList.item(0)
							.getAttributes().item(1).getNodeValue());

					NodeList lrPropertyNodeList = document
							.getElementsByTagName("LRProperty");

					for (int i = 0; i < lrPropertyNodeList.getLength(); i++) {
						if (lrPropertyNodeList.item(i).getAttributes().item(0)
								.getTextContent().equals("Description")) {
							LLRPDescriptionText.setText(lrPropertyNodeList
									.item(i).getAttributes().item(1)
									.getNodeValue());
						} else if (lrPropertyNodeList.item(i).getAttributes()
								.item(0).getTextContent().equals(
										"ConnectionPointAddress")) {
							LLRPConnectionPointAddressText
									.setText(lrPropertyNodeList.item(i)
											.getAttributes().item(1)
											.getNodeValue());
						} else if (lrPropertyNodeList.item(i).getAttributes()
								.item(0).getTextContent().equals(
										"ConnectionPointPort")) {
							LLRPConnectionPointPortText
									.setText(lrPropertyNodeList.item(i)
											.getAttributes().item(1)
											.getNodeValue());
						} else if (lrPropertyNodeList.item(i).getAttributes()
								.item(0).getTextContent().equals(
										"EncryptedConnectionPointAddress")) {
							LLRPEncryptedConnectionPointAddressText
									.setText(lrPropertyNodeList.item(i)
											.getAttributes().item(1)
											.getNodeValue());
						} else if (lrPropertyNodeList.item(i).getAttributes()
								.item(0).getTextContent().equals(
										"EncryptedConnectionPointPort")) {
							LLRPEncryptedConnectionPointPortText
									.setText(lrPropertyNodeList.item(i)
											.getAttributes().item(1)
											.getNodeValue());
						} else if (lrPropertyNodeList.item(i).getAttributes()
								.item(0).getTextContent().equals(
										"ReadTimeInterval")) {
							LLRPReadTimeIntervalText.setText(lrPropertyNodeList
									.item(i).getAttributes().item(1)
									.getNodeValue());
						} else if (lrPropertyNodeList.item(i).getAttributes()
								.item(0).getTextContent()
								.equals("AdaptorClass")) {
							LLRPAdaptorClassText.setText(lrPropertyNodeList
									.item(i).getAttributes().item(1)
									.getNodeValue());
						} else if (lrPropertyNodeList.item(i).getAttributes()
								.item(0).getTextContent().equals(
										"ImplementationClass")) {
							LLRPImplClassText.setText(lrPropertyNodeList
									.item(i).getAttributes().item(1)
									.getNodeValue());
						} else if (lrPropertyNodeList.item(i).getAttributes()
								.item(0).getTextContent().equals(
										"PhysicalReaderSource")) {
							LLRPPhysicalReaderSourceText
									.setText(lrPropertyNodeList.item(i)
											.getAttributes().item(1)
											.getNodeValue());
						} else if (lrPropertyNodeList.item(i).getAttributes()
								.item(0).getTextContent().equals(
										"DescriptiveReaderSource")) {
							LLRPDescriptiveReaderSourceText
									.setText(lrPropertyNodeList.item(i)
											.getAttributes().item(1)
											.getNodeValue());
						} else if (lrPropertyNodeList.item(i).getAttributes()
								.item(0).getTextContent().equals("RoSpecID")) {
							LLRPReaderOperationSpecIDText
									.setText(lrPropertyNodeList.item(i)
											.getAttributes().item(1)
											.getNodeValue());
						}
					}

					LLRPStaticDefinitionCheck.setSelection(true);

					LLRPReaderSaveButton.setEnabled(true);

					existingLLRPReaderFileToBeSaved = PreferenceConstants.P_LLRP_LRSpecsPATH
							+ lrspectreeSelectedItem;
				}
			} else if (lrspectreeSelectedItemParent.equals("Composite Readers")) {
				// reset fields
				CompReaderNameText.setText("");
				CompReaderTypeText.deselectAll();
				CompDescriptionText.setText("");

				for (int i = 0; i < lrspecIncludedReadersList.getItemCount(); i++) {
					lrspecLogicalReadersList.add(lrspecIncludedReadersList
							.getItem(i));
				}

				lrspecIncludedReadersList.removeAll();
				lrspecLogicalReadersList.removeAll();

				// check if the file to be opened contains a static reader or a
				// dynamic reader
				try {
					File selectedFile = new File(
							PreferenceConstants.P_Composite_LRSpecsPATH
									+ lrspectreeSelectedItem);

					if (selectedFile.exists()) {
						documentBuilderFactory = DocumentBuilderFactory
								.newInstance();
						documentBuilder = documentBuilderFactory
								.newDocumentBuilder();
						document = documentBuilder.parse(selectedFile);
						Node node = document.getDocumentElement();
						root = node.getNodeName();
					} else {
						MessageDialog.openError(container.getShell(), "Error",
								"File not found!");
					}
				} catch (Exception exc) {
					MessageDialog.openError(container.getShell(), "Error",
							"DOM parser error!");
				}

				if (root.equals("ns3:LRSpec")) {

					try {
						LRSpec lRSpec = new LRSpec();
						lRSpec = openLrSpecBuilder
								.getLRSpecFromFile(PreferenceConstants.P_Composite_LRSpecsPATH
										+ lrspectreeSelectedItem);

						tabFolder.setSelection(lrspecCompositeTabItem);

						CompReaderNameText.setEnabled(false);
						newReaderButton.setEnabled(true);

						resetCompositeReaderFields();

						for (int i = 0; i < lRSpec.getReaders().getReader()
								.size(); i++) {
							lrspecIncludedReadersList.add(lRSpec.getReaders()
									.getReader().get(i));
						}

						for (int i = 0; i < lRSpec.getProperties()
								.getProperty().size(); i++) {
							if (lRSpec.getProperties().getProperty().get(i)
									.getName().equals("ReaderType")) {
								CompReaderTypeText.setText(lRSpec
										.getProperties().getProperty().get(i)
										.getValue());
							} else if (lRSpec.getProperties().getProperty()
									.get(i).getName().equals("Description")) {
								CompDescriptionText.setText(lRSpec
										.getProperties().getProperty().get(i)
										.getValue());
							}
						}

						compFilterByCombo.setText("Dynamic");

						CompReaderSaveButton.setEnabled(true);

						existingCompositeReaderFileToBeSaved = PreferenceConstants.P_Composite_LRSpecsPATH
								+ lrspectreeSelectedItem;
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				} else if (root.equals("LogicalReaders")) {

					tabFolder.setSelection(lrspecCompositeTabItem);

					CompReaderNameText.setEnabled(true);
					newReaderButton.setEnabled(false);

					resetCompositeReaderFields();

					NodeList logicalReaderNodeList = document
							.getElementsByTagName("LogicalReader");
					CompReaderNameText.setText(logicalReaderNodeList.item(
							logicalReaderNodeList.getLength() - 1)
							.getAttributes().item(0).getNodeValue());

					NodeList lrSpecNodeList = document
							.getElementsByTagName("LRSpec");
					CompReaderTypeText.setText(lrSpecNodeList.item(
							lrSpecNodeList.getLength() - 1).getAttributes()
							.item(1).getNodeValue());

					NodeList lrPropertyNodeList = document
							.getElementsByTagName("LRProperty");
					CompDescriptionText.setText((lrPropertyNodeList.item(
							lrPropertyNodeList.getLength() - 1).getAttributes()
							.item(1).getNodeValue()));

					NodeList readersNodesList = document
							.getElementsByTagName("readers");

					for (int i = 0; i < readersNodesList.getLength(); i++) {
						lrspecIncludedReadersList.add(readersNodesList.item(i)
								.getTextContent());
					}

					compFilterByCombo.setText("Static");

					CompReaderSaveButton.setEnabled(true);

					existingCompositeReaderFileToBeSaved = PreferenceConstants.P_Composite_LRSpecsPATH
							+ lrspectreeSelectedItem;
				}
			}

		}
	}

	private class CompFilterByComboSelectionListener extends SelectionAdapter {
		public void widgetSelected(final SelectionEvent e) {
			File[] HALDirectoryContents = getHALLRSpecsPathFolderContents();
			File[] RPDirectoryContents = getRPLRSpecsPathFolderContents();
			File[] LLRPDirectoryContents = getLLRPLRSpecsPathFolderContents();

			lrspecLogicalReadersList.removeAll();
			lrspecIncludedReadersList.removeAll();

			Combo combo = (Combo) e.getSource();
			filterByComboSelectedItem = combo.getText();

			// if Filter by is Static
			if (filterByComboSelectedItem.equals("Static")) {

				CompReaderNameText.setEnabled(true);
				newReaderButton.setEnabled(false);

				// HAL static readers
				getStaticReaders(HALDirectoryContents,
						PreferenceConstants.P_HAL_LRSpecsPATH);

				// RP static readers
				getStaticReaders(RPDirectoryContents,
						PreferenceConstants.P_RP_LRSpecsPATH);

				// LLRP static readers
				getStaticReaders(LLRPDirectoryContents,
						PreferenceConstants.P_LLRP_LRSpecsPATH);

				if (lrspecIncludedReadersList.getItemCount() != 0) {
					for (int i = 0; i < lrspecIncludedReadersList
							.getItemCount(); i++) {
						for (int j = 0; j < lrspecLogicalReadersList
								.getItemCount(); j++) {
							if (lrspecIncludedReadersList.getItem(i).equals(
									lrspecLogicalReadersList.getItem(j))) {
								lrspecLogicalReadersList.remove(j);
							}
						}
					}
				}

			} else if (filterByComboSelectedItem.equals("Dynamic")) {

				CompReaderNameText.setEnabled(false);
				newReaderButton.setEnabled(true);

				// load values to available readers list
				String dynamicReadersArray[] = {};

				dynamicReadersArray = preferences.getString(
						PreferenceConstants.P_DynamicReaders).split(",");

				lrspecLogicalReadersList.removeAll();
				for (int i = 0; i < dynamicReadersArray.length; i++) {
					lrspecLogicalReadersList.add(dynamicReadersArray[i]);
				}

				// load dynamic readers from LRSpecConfiguratorView
				if (exists("org.ow2.aspirerfid.ide.aleconfig.Activator")) {// added
					// By
					// nkef
					IPreferenceStore lrspecConfigPreferences = org.ow2.aspirerfid.ide.aleconfig.Activator
							.getDefault().getPreferenceStore();

					String readerNamesArray[] = {};

					readerNamesArray = lrspecConfigPreferences
							.getString(
									org.ow2.aspirerfid.ide.aleconfig.preferences.PreferenceConstants.P_ReaderNames)
							.split(",");

					for (int j = 0; j < readerNamesArray.length; j++) {
						lrspecLogicalReadersList.add(readerNamesArray[j]);
					}
				}
			}

		}
	}

	public boolean exists(String className) // added by nkef
	{
		try {
			Class.forName(className);
			// Class.forName(className,false, null);
			return true;
		} catch (ClassNotFoundException exception) {
			return false;
		}
	}

	@SuppressWarnings("unchecked")
	public void getStaticReaders(File[] directoryContents, String readersPath) {
		DocumentBuilderFactory documentBuilderFactory;
		DocumentBuilder documentBuilder;
		Document document = null;
		String root = "";

		if (directoryContents.length != 0) {
			for (int i = 0; i < directoryContents.length; i++) {
				if (directoryContents[i].getName().endsWith(".xml")) {

					try {
						File selectedFile = new File(readersPath
								+ directoryContents[i].getName());

						if (selectedFile.exists()) {
							documentBuilderFactory = DocumentBuilderFactory
									.newInstance();
							documentBuilder = documentBuilderFactory
									.newDocumentBuilder();
							document = documentBuilder.parse(selectedFile);
							Node node = document.getDocumentElement();
							root = node.getNodeName();
						} else {
							MessageDialog.openError(container.getShell(),
									"Error", "File not found!");
						}
					} catch (Exception exc) {
						MessageDialog.openError(container.getShell(), "Error",
								"DOM parser error!");
					}

					if (root.equals("LogicalReaders")) {
						NodeList lrSpecNodeList = document
								.getElementsByTagName("LogicalReader");

						lrspecLogicalReadersList.add(lrSpecNodeList.item(0)
								.getAttributes().item(0).getNodeValue());

						staticReadersHashMap.put(lrSpecNodeList.item(0)
								.getAttributes().item(0).getNodeValue(),
								directoryContents[i].getName());

					}

				}
			}
		}

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
						File selectedFile = new File(readersPath
								+ directoryContents[i].getName());

						if (selectedFile.exists()) {
							documentBuilderFactory = DocumentBuilderFactory
									.newInstance();
							documentBuilder = documentBuilderFactory
									.newDocumentBuilder();
							document = documentBuilder.parse(selectedFile);
							Node node = document.getDocumentElement();
							root = node.getNodeName();
						} else {
							MessageDialog.openError(container.getShell(),
									"Error", "File not found!");
						}
					} catch (Exception exc) {
						MessageDialog.openError(container.getShell(), "Error",
								"DOM parser error!");
					}

					if (root.equals("ns3:LRSpec")) {
						lrspecLogicalReadersList.add(directoryContents[i]
								.getName().substring(
										0,
										directoryContents[i].getName().indexOf(
												".")));
					}

				}
			}
		}
	}

	private class HALReadTimeIntervalTextVerifyListener implements
			VerifyListener {
		public void verifyText(final VerifyEvent arg0) {
			String string = arg0.text;
			char[] chars = new char[string.length()];
			string.getChars(0, chars.length, chars, 0);
			for (int i = 0; i < chars.length; i++) {
				if (!('0' <= chars[i] && chars[i] <= '9')) {
					arg0.doit = false;
					return;
				}
			}
		}
	}

	private class RPReadTimeIntervalTextVerifyListener implements
			VerifyListener {
		public void verifyText(final VerifyEvent arg0) {
			String string = arg0.text;
			char[] chars = new char[string.length()];
			string.getChars(0, chars.length, chars, 0);
			for (int i = 0; i < chars.length; i++) {
				if (!('0' <= chars[i] && chars[i] <= '9')) {
					arg0.doit = false;
					return;
				}
			}
		}
	}

	private class LLRPConnectionPointPortTextVerifyListener implements
			VerifyListener {
		public void verifyText(final VerifyEvent arg0) {
			String string = arg0.text;
			char[] chars = new char[string.length()];
			string.getChars(0, chars.length, chars, 0);
			for (int i = 0; i < chars.length; i++) {
				if (!('0' <= chars[i] && chars[i] <= '9')) {
					arg0.doit = false;
					return;
				}
			}
		}
	}

	private class LLRPEncryptedConnectionPointPortTextVerifyListener implements
			VerifyListener {
		public void verifyText(final VerifyEvent arg0) {
			String string = arg0.text;
			char[] chars = new char[string.length()];
			string.getChars(0, chars.length, chars, 0);
			for (int i = 0; i < chars.length; i++) {
				if (!('0' <= chars[i] && chars[i] <= '9')) {
					arg0.doit = false;
					return;
				}
			}
		}
	}

	private class LLRPReadTimeIntervalTextVerifyListener implements
			VerifyListener {
		public void verifyText(final VerifyEvent arg0) {
			String string = arg0.text;
			char[] chars = new char[string.length()];
			string.getChars(0, chars.length, chars, 0);
			for (int i = 0; i < chars.length; i++) {
				if (!('0' <= chars[i] && chars[i] <= '9')) {
					arg0.doit = false;
					return;
				}
			}
		}
	}

	private class LLRPPhysicalReaderSourceTextVerifyListener implements
			VerifyListener {
		public void verifyText(final VerifyEvent arg0) {
			String string = arg0.text;
			char[] chars = new char[string.length()];
			string.getChars(0, chars.length, chars, 0);
			for (int i = 0; i < chars.length; i++) {
				if (!('0' <= chars[i] && chars[i] <= '9')) {
					arg0.doit = false;
					return;
				}
			}
		}
	}

	private class LLRPReaderOperationSpecIDTextVerifyListener implements
			VerifyListener {
		public void verifyText(final VerifyEvent arg0) {
			String string = arg0.text;
			char[] chars = new char[string.length()];
			string.getChars(0, chars.length, chars, 0);
			for (int i = 0; i < chars.length; i++) {
				if (!('0' <= chars[i] && chars[i] <= '9')) {
					arg0.doit = false;
					return;
				}
			}
		}
	}

	private class LrspecTreeRefreshButtonMouseListener extends MouseAdapter {
		public void mouseDown(final MouseEvent e) {
			initializeLrSpecTree();
		}
	}

	private class HALBrowsePropertiesFileButtonMouseListener extends
			MouseAdapter {
		public void mouseDown(final MouseEvent e) {
			FileDialog fd;

			fd = new FileDialog(compositeOpenFile.getShell(), SWT.OPEN);
			fd.setText("Open");
			fd.setFilterExtensions(new String[] { "*.xml" });
			String selection = fd.open();

			HALPropertiesFileText.setText(selection);
		}
	}

	private class HALReaderSaveButtonMouseListener extends MouseAdapter {
		public void mouseDown(final MouseEvent e) {
			// variables
			int index = 0;
			String propertyName = "";
			String propertyValue = "";
			halLrSpecBuilder = new LRSpecBuilder();

			// Dynamic definition
			if (HALStaticDefinitionCheck.getSelection() == false) {
				if (HALReaderTypeText.getText().equals("")
						|| HALDescriptionText.getText().equals("")
						|| HALAdaptorClassText.getText().equals("")
						|| HALPhysicalReaderNameText.getText().equals("")
						|| HALReadTimeIntervalText.getText().equals("")
						|| HALReadPointsText.getText().equals("")
						|| HALPropertiesFileText.getText().equals("")) {
					MessageDialog.openError(container.getShell(), "Error",
							"All property fields are mandatory!");

				} else {
					// set isComposite to false (HAL Reader)
					halLrSpecBuilder.setIsComposite(false);

					// set HAL properties

					// set Reader type property
					propertyName = "ReaderType";
					propertyValue = HALReaderTypeText.getText();

					halLrSpecBuilder.setLRProperty(index, propertyName,
							propertyValue);

					// set Description property
					propertyName = "Description";
					propertyValue = HALDescriptionText.getText();

					halLrSpecBuilder.setLRProperty(++index, propertyName,
							propertyValue);

					// set Adaptor class property
					propertyName = "AdaptorClass";
					propertyValue = HALAdaptorClassText.getText();

					halLrSpecBuilder.setLRProperty(++index, propertyName,
							propertyValue);

					// set Physical reader name property
					propertyName = "PhysicalReaderName";
					propertyValue = HALPhysicalReaderNameText.getText();

					halLrSpecBuilder.setLRProperty(++index, propertyName,
							propertyValue);

					// set Read time interval property
					propertyName = "ReadTimeInterval";
					propertyValue = HALReadTimeIntervalText.getText();

					halLrSpecBuilder.setLRProperty(++index, propertyName,
							propertyValue);

					// set Read points property
					propertyName = "ReadPoints";
					propertyValue = HALReadPointsText.getText();

					halLrSpecBuilder.setLRProperty(++index, propertyName,
							propertyValue);

					// set Properties file property
					propertyName = "PropertiesFile";
					propertyValue = HALPropertiesFileText.getText();

					halLrSpecBuilder.setLRProperty(++index, propertyName,
							propertyValue);

					try {
						halLrSpecBuilder
								.generateXml(existingHALReaderFileToBeSaved);

						resetHALReaderFields();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
			// Static definition
			else {
				if (HALReaderNameText.getText().equals("")
						|| HALReaderTypeText.getText().equals("")
						|| HALDescriptionText.getText().equals("")
						|| HALAdaptorClassText.getText().equals("")
						|| HALPhysicalReaderNameText.getText().equals("")
						|| HALReadTimeIntervalText.getText().equals("")
						|| HALReadPointsText.getText().equals("")
						|| HALPropertiesFileText.getText().equals("")) {
					MessageDialog.openError(container.getShell(), "Error",
							"All fields are mandatory!");

				} else {
					// set isComposite to false (HAL Reader)
					halLrSpecBuilder.setIsComposite(false);

					// set Logical Reader Name
					halLrSpecBuilder.setLogicalReaders(HALReaderNameText
							.getText());

					// set HAL properties

					// set Reader type property
					propertyName = "ReaderType";
					propertyValue = HALReaderTypeText.getText();

					halLrSpecBuilder.setLRProperty(index, propertyName,
							propertyValue);

					// set Description property
					propertyName = "Description";
					propertyValue = HALDescriptionText.getText();

					halLrSpecBuilder.setLRProperty(++index, propertyName,
							propertyValue);

					// set Adaptor class property
					propertyName = "AdaptorClass";
					propertyValue = HALAdaptorClassText.getText();

					halLrSpecBuilder.setLRProperty(++index, propertyName,
							propertyValue);

					// set Physical reader name property
					propertyName = "PhysicalReaderName";
					propertyValue = HALPhysicalReaderNameText.getText();

					halLrSpecBuilder.setLRProperty(++index, propertyName,
							propertyValue);

					// set Read time interval property
					propertyName = "ReadTimeInterval";
					propertyValue = HALReadTimeIntervalText.getText();

					halLrSpecBuilder.setLRProperty(++index, propertyName,
							propertyValue);

					// set Read points property
					propertyName = "ReadPoints";
					propertyValue = HALReadPointsText.getText();

					halLrSpecBuilder.setLRProperty(++index, propertyName,
							propertyValue);

					// set Properties file property
					propertyName = "PropertiesFile";
					propertyValue = HALPropertiesFileText.getText();

					halLrSpecBuilder.setLRProperty(++index, propertyName,
							propertyValue);

					// generate HAL Reader Static Definition XML
					generateHALReaderStaticDefinitionXML(halLrSpecBuilder,
							existingHALReaderFileToBeSaved);

					resetHALReaderFields();
				}
			}
		}
	}

	private class RPReaderSaveButtonMouseListener extends MouseAdapter {
		public void mouseDown(final MouseEvent e) {
			// variables
			int index = 0;
			String propertyName = "";
			String propertyValue = "";
			rpLrSpecBuilder = new LRSpecBuilder();

			// Dynamic definition
			if (RPStaticDefinitionCheck.getSelection() == false) {
				if (RPReaderTypeText.getText().equals("")
						|| RPDescriptionText.getText().equals("")
						|| RPConnectionPointCombo.getText().equals("")
						|| RPNotificationPointCombo.getText().equals("")
						|| RPReadTimeIntervalText.getText().equals("")
						|| RPAdaptorClassText.getText().equals("")
						|| RPImplClassText.getText().equals("")
						|| RPPhysicalReaderNameText.getText().equals("")
						|| RPPhysicalReaderSourceText.getText().equals("")) {
					MessageDialog.openError(container.getShell(), "Error",
							"All property fields are mandatory!");

				} else {
					// set isComposite to false (RP Reader)
					rpLrSpecBuilder.setIsComposite(false);

					// set RP properties

					// set Reader type property
					propertyName = "ReaderType";
					propertyValue = RPReaderTypeText.getText();

					rpLrSpecBuilder.setLRProperty(index, propertyName,
							propertyValue);

					// set Description property
					propertyName = "Description";
					propertyValue = RPDescriptionText.getText();

					rpLrSpecBuilder.setLRProperty(++index, propertyName,
							propertyValue);

					// set Connection point property
					propertyName = "ConnectionPoint";
					propertyValue = RPConnectionPointCombo.getText();

					rpLrSpecBuilder.setLRProperty(++index, propertyName,
							propertyValue);

					// set Notification point property
					propertyName = "NotificationPoint";
					propertyValue = RPNotificationPointCombo.getText();

					rpLrSpecBuilder.setLRProperty(++index, propertyName,
							propertyValue);

					// set Read time interval property
					propertyName = "ReadTimeInterval";
					propertyValue = RPReadTimeIntervalText.getText();

					rpLrSpecBuilder.setLRProperty(++index, propertyName,
							propertyValue);

					// set Adaptor class property
					propertyName = "AdaptorClass";
					propertyValue = RPAdaptorClassText.getText();

					rpLrSpecBuilder.setLRProperty(++index, propertyName,
							propertyValue);

					// set Implementation class property
					propertyName = "ImplementationClass";
					propertyValue = RPImplClassText.getText();

					rpLrSpecBuilder.setLRProperty(++index, propertyName,
							propertyValue);

					// set Physical reader name property
					propertyName = "PhysicalReaderName";
					propertyValue = RPPhysicalReaderNameText.getText();

					rpLrSpecBuilder.setLRProperty(++index, propertyName,
							propertyValue);

					// set Physical reader source property
					propertyName = "PhysicalReaderSource";
					propertyValue = RPPhysicalReaderSourceText.getText();

					rpLrSpecBuilder.setLRProperty(++index, propertyName,
							propertyValue);

					try {
						rpLrSpecBuilder
								.generateXml(existingRPReaderFileToBeSaved);

						addNewConnectionPointValueIfNotPresent();
						addNewNotificationPointValueIfNotPresent();

						resetRPReaderFields();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			} // Static definition
			else {
				if (RPReaderNameText.getText().equals("")
						|| RPReaderTypeText.getText().equals("")
						|| RPDescriptionText.getText().equals("")
						|| RPConnectionPointCombo.getText().equals("")
						|| RPNotificationPointCombo.getText().equals("")
						|| RPReadTimeIntervalText.getText().equals("")
						|| RPAdaptorClassText.getText().equals("")
						|| RPImplClassText.getText().equals("")
						|| RPPhysicalReaderNameText.getText().equals("")
						|| RPPhysicalReaderSourceText.getText().equals("")) {
					MessageDialog.openError(container.getShell(), "Error",
							"All fields are mandatory!");

				} else {
					// set isComposite to false (RP Reader)
					rpLrSpecBuilder.setIsComposite(false);

					// set Logical Reader Name
					rpLrSpecBuilder.setLogicalReaders(RPReaderNameText
							.getText());

					// set RP properties

					// set Reader type property
					propertyName = "ReaderType";
					propertyValue = RPReaderTypeText.getText();

					rpLrSpecBuilder.setLRProperty(index, propertyName,
							propertyValue);

					// set Description property
					propertyName = "Description";
					propertyValue = RPDescriptionText.getText();

					rpLrSpecBuilder.setLRProperty(++index, propertyName,
							propertyValue);

					// set Connection point property
					propertyName = "ConnectionPoint";
					propertyValue = RPConnectionPointCombo.getText();

					rpLrSpecBuilder.setLRProperty(++index, propertyName,
							propertyValue);

					// set Notification point property
					propertyName = "NotificationPoint";
					propertyValue = RPNotificationPointCombo.getText();

					rpLrSpecBuilder.setLRProperty(++index, propertyName,
							propertyValue);

					// set Read time interval property
					propertyName = "ReadTimeInterval";
					propertyValue = RPReadTimeIntervalText.getText();

					rpLrSpecBuilder.setLRProperty(++index, propertyName,
							propertyValue);

					// set Adaptor class property
					propertyName = "AdaptorClass";
					propertyValue = RPAdaptorClassText.getText();

					rpLrSpecBuilder.setLRProperty(++index, propertyName,
							propertyValue);

					// set Implementation class property
					propertyName = "ImplementationClass";
					propertyValue = RPImplClassText.getText();

					rpLrSpecBuilder.setLRProperty(++index, propertyName,
							propertyValue);

					// set Physical reader name property
					propertyName = "PhysicalReaderName";
					propertyValue = RPPhysicalReaderNameText.getText();

					rpLrSpecBuilder.setLRProperty(++index, propertyName,
							propertyValue);

					// set Physical reader source property
					propertyName = "PhysicalReaderSource";
					propertyValue = RPPhysicalReaderSourceText.getText();

					rpLrSpecBuilder.setLRProperty(++index, propertyName,
							propertyValue);

					// generate RP Reader Static Definition XML
					generateRPReaderStaticDefinitionXML(rpLrSpecBuilder,
							existingRPReaderFileToBeSaved);

					addNewConnectionPointValueIfNotPresent();
					addNewNotificationPointValueIfNotPresent();

					resetRPReaderFields();
				}
			}

		}
	}

	private class LLRPReaderSaveButtonMouseListener extends MouseAdapter {
		public void mouseDown(final MouseEvent e) {
			// variables
			int index = 0;
			String propertyName = "";
			String propertyValue = "";
			llrpLrSpecBuilder = new LRSpecBuilder();

			if (LLRPStaticDefinitionCheck.getSelection() == false) {
				if (LLRPReaderTypeText.getText().equals("")
						|| LLRPDescriptionText.getText().equals("")
						|| LLRPConnectionPointAddressText.getText().equals("")
						|| LLRPConnectionPointPortText.getText().equals("")
						|| LLRPEncryptedConnectionPointAddressText.getText()
								.equals("")
						|| LLRPEncryptedConnectionPointPortText.getText()
								.equals("")
						|| LLRPReadTimeIntervalText.getText().equals("")
						|| LLRPAdaptorClassText.getText().equals("")
						|| LLRPImplClassText.getText().equals("")
						|| LLRPPhysicalReaderSourceText.getText().equals("")
						|| LLRPDescriptiveReaderSourceText.getText().equals("")
						|| LLRPReaderOperationSpecIDText.getText().equals("")) {
					MessageDialog.openError(container.getShell(), "Error",
							"All property fields are mandatory!");

				} else {
					// set isComposite to false (RP Reader)
					llrpLrSpecBuilder.setIsComposite(false);

					// set LLRP properties

					// set Description property
					propertyName = "ReaderType";
					propertyValue = LLRPReaderTypeText.getText();

					llrpLrSpecBuilder.setLRProperty(index, propertyName,
							propertyValue);

					// set Description property
					propertyName = "Description";
					propertyValue = LLRPDescriptionText.getText();

					llrpLrSpecBuilder.setLRProperty(++index, propertyName,
							propertyValue);

					// set Connection point address property
					propertyName = "ConnectionPointAddress";
					propertyValue = LLRPConnectionPointAddressText.getText();

					llrpLrSpecBuilder.setLRProperty(++index, propertyName,
							propertyValue);

					// set Connection point port property
					propertyName = "ConnectionPointPort";
					propertyValue = LLRPConnectionPointPortText.getText();

					llrpLrSpecBuilder.setLRProperty(++index, propertyName,
							propertyValue);

					// set Encrypted connection point address property
					propertyName = "EncryptedConnectionPointAddress";
					propertyValue = LLRPEncryptedConnectionPointAddressText
							.getText();

					llrpLrSpecBuilder.setLRProperty(++index, propertyName,
							propertyValue);

					// set Encrypted connection point port property
					propertyName = "EncryptedConnectionPointPort";
					propertyValue = LLRPEncryptedConnectionPointPortText
							.getText();

					llrpLrSpecBuilder.setLRProperty(++index, propertyName,
							propertyValue);

					// set Read time interval property
					propertyName = "ReadTimeInterval";
					propertyValue = LLRPReadTimeIntervalText.getText();

					llrpLrSpecBuilder.setLRProperty(++index, propertyName,
							propertyValue);

					// set Adaptor class property
					propertyName = "AdaptorClass";
					propertyValue = LLRPAdaptorClassText.getText();

					llrpLrSpecBuilder.setLRProperty(++index, propertyName,
							propertyValue);

					// set Implementation class property
					propertyName = "ImplementationClass";
					propertyValue = LLRPImplClassText.getText();

					llrpLrSpecBuilder.setLRProperty(++index, propertyName,
							propertyValue);

					// set Physical reader source property
					propertyName = "PhysicalReaderSource";
					propertyValue = LLRPPhysicalReaderSourceText.getText();

					llrpLrSpecBuilder.setLRProperty(++index, propertyName,
							propertyValue);

					// set Descriptive Reader source property
					propertyName = "DescriptiveReaderSource";
					propertyValue = LLRPDescriptiveReaderSourceText.getText();

					llrpLrSpecBuilder.setLRProperty(++index, propertyName,
							propertyValue);

					// set Reader Operation Spec ID property
					propertyName = "RoSpecID";
					propertyValue = LLRPReaderOperationSpecIDText.getText();

					llrpLrSpecBuilder.setLRProperty(++index, propertyName,
							propertyValue);

					try {
						llrpLrSpecBuilder
								.generateXml(existingLLRPReaderFileToBeSaved);

						resetLLRPReaderFields();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			} else {
				if (LLRPReaderNameText.getText().equals("")
						|| LLRPReaderTypeText.getText().equals("")
						|| LLRPDescriptionText.getText().equals("")
						|| LLRPConnectionPointAddressText.getText().equals("")
						|| LLRPConnectionPointPortText.getText().equals("")
						|| LLRPEncryptedConnectionPointAddressText.getText()
								.equals("")
						|| LLRPEncryptedConnectionPointPortText.getText()
								.equals("")
						|| LLRPReadTimeIntervalText.getText().equals("")
						|| LLRPAdaptorClassText.getText().equals("")
						|| LLRPImplClassText.getText().equals("")
						|| LLRPPhysicalReaderSourceText.getText().equals("")
						|| LLRPDescriptiveReaderSourceText.getText().equals("")
						|| LLRPReaderOperationSpecIDText.getText().equals("")) {
					MessageDialog.openError(container.getShell(), "Error",
							"All fields are mandatory!");

				} else {
					// set isComposite to false (RP Reader)
					llrpLrSpecBuilder.setIsComposite(false);

					// set Logical Reader Name
					llrpLrSpecBuilder.setLogicalReaders(LLRPReaderNameText
							.getText());

					// set LLRP properties

					// set Description property
					propertyName = "ReaderType";
					propertyValue = LLRPReaderTypeText.getText();

					llrpLrSpecBuilder.setLRProperty(index, propertyName,
							propertyValue);

					// set Description property
					propertyName = "Description";
					propertyValue = LLRPDescriptionText.getText();

					llrpLrSpecBuilder.setLRProperty(++index, propertyName,
							propertyValue);

					// set Connection point address property
					propertyName = "ConnectionPointAddress";
					propertyValue = LLRPConnectionPointAddressText.getText();

					llrpLrSpecBuilder.setLRProperty(++index, propertyName,
							propertyValue);

					// set Connection point port property
					propertyName = "ConnectionPointPort";
					propertyValue = LLRPConnectionPointPortText.getText();

					llrpLrSpecBuilder.setLRProperty(++index, propertyName,
							propertyValue);

					// set Encrypted connection point address property
					propertyName = "EncryptedConnectionPointAddress";
					propertyValue = LLRPEncryptedConnectionPointAddressText
							.getText();

					llrpLrSpecBuilder.setLRProperty(++index, propertyName,
							propertyValue);

					// set Encrypted connection point port property
					propertyName = "EncryptedConnectionPointPort";
					propertyValue = LLRPEncryptedConnectionPointPortText
							.getText();

					llrpLrSpecBuilder.setLRProperty(++index, propertyName,
							propertyValue);

					// set Read time interval property
					propertyName = "ReadTimeInterval";
					propertyValue = LLRPReadTimeIntervalText.getText();

					llrpLrSpecBuilder.setLRProperty(++index, propertyName,
							propertyValue);

					// set Adaptor class property
					propertyName = "AdaptorClass";
					propertyValue = LLRPAdaptorClassText.getText();

					llrpLrSpecBuilder.setLRProperty(++index, propertyName,
							propertyValue);

					// set Implementation class property
					propertyName = "ImplementationClass";
					propertyValue = LLRPImplClassText.getText();

					llrpLrSpecBuilder.setLRProperty(++index, propertyName,
							propertyValue);

					// set Physical reader source property
					propertyName = "PhysicalReaderSource";
					propertyValue = LLRPPhysicalReaderSourceText.getText();

					llrpLrSpecBuilder.setLRProperty(++index, propertyName,
							propertyValue);

					// set Descriptive Reader source property
					propertyName = "DescriptiveReaderSource";
					propertyValue = LLRPDescriptiveReaderSourceText.getText();

					llrpLrSpecBuilder.setLRProperty(++index, propertyName,
							propertyValue);

					// set Reader Operation Spec ID property
					propertyName = "RoSpecID";
					propertyValue = LLRPReaderOperationSpecIDText.getText();

					llrpLrSpecBuilder.setLRProperty(++index, propertyName,
							propertyValue);

					// generate LLRP Reader Static Definition XML
					generateLLRPReaderStaticDefinitionXML(llrpLrSpecBuilder,
							existingLLRPReaderFileToBeSaved);

					resetLLRPReaderFields();
				}
			}

		}
	}

	private class CompReaderSaveButtonMouseListener extends MouseAdapter {
		public void mouseDown(final MouseEvent e) {
			// variables
			int index = 0;
			String propertyName = "";
			String propertyValue = "";
			compositeLrSpecBuilder = new LRSpecBuilder();

			if (CompDescriptionText.getText().equals("")
					|| CompReaderTypeText.getText().equals("")) {
				MessageDialog.openError(container.getShell(), "Error",
						"All fields are mandatory!");

			} else if (lrspecIncludedReadersList.getItemCount() == 0) {
				MessageDialog.openError(container.getShell(), "Error",
						"No reader has been selected!");

			} else if (lrspecIncludedReadersList.getItemCount() < 2) {
				MessageDialog.openError(container.getShell(), "Error",
						"At least two readers should be selected!");

			} else {
				// set isComposite to true (Composite Reader)
				compositeLrSpecBuilder.setIsComposite(true);

				// set Composite properties

				// set Reader type property
				propertyName = "ReaderType";
				propertyValue = CompReaderTypeText.getText();

				compositeLrSpecBuilder.setLRProperty(index, propertyName,
						propertyValue);

				// set Description property
				propertyName = "Description";
				propertyValue = CompDescriptionText.getText();

				compositeLrSpecBuilder.setLRProperty(++index, propertyName,
						propertyValue);

				// set list of logical readers to compositeLrSpecBuilder
				for (int i = 0; i < lrspecIncludedReadersList.getItemCount(); i++) {
					compositeLrSpecBuilder
							.setLogicalReaders(lrspecIncludedReadersList
									.getItem(i));
				}

				if (filterByComboSelectedItem.equals("Dynamic")) {

					try {
						compositeLrSpecBuilder
								.generateXml(existingCompositeReaderFileToBeSaved);

						resetCompositeReaderFields();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				} else if (filterByComboSelectedItem.equals("Static")) {

					generateCompositeReaderStaticDefinitionXML(
							compositeLrSpecBuilder,
							existingCompositeReaderFileToBeSaved);

					resetCompositeReaderFields();
				}

			}
		}
	}

	private class HALStaticDefinitionCheckSelectionListener extends
			SelectionAdapter {
		public void widgetSelected(final SelectionEvent e) {
			if (HALStaticDefinitionCheck.getSelection() == true) {
				HALReaderNameText.setEnabled(true);
			} else {
				HALReaderNameText.setEnabled(false);
			}
		}
	}

	private class RPStaticDefinitionCheckSelectionListener extends
			SelectionAdapter {
		public void widgetSelected(final SelectionEvent e) {
			if (RPStaticDefinitionCheck.getSelection() == true) {
				RPReaderNameText.setEnabled(true);
			} else {
				RPReaderNameText.setEnabled(false);
			}
		}
	}

	private class LLRPStaticDefinitionCheckSelectionListener extends
			SelectionAdapter {
		public void widgetSelected(final SelectionEvent e) {
			if (LLRPStaticDefinitionCheck.getSelection() == true) {
				LLRPReaderNameText.setEnabled(true);
			} else {
				LLRPReaderNameText.setEnabled(false);
			}
		}
	}

	private class NewReaderButtonMouseListener extends MouseAdapter {
		public void mouseDown(final MouseEvent e) {
			boolean found = false;
			org.ow2.aspirerfid.ide.lrspec.dialogs.InputDialogAddNewReader dlg = new org.ow2.aspirerfid.ide.lrspec.dialogs.InputDialogAddNewReader(
					new Shell());
			String input = dlg.open();

			if (input != null) {

				for (int i = 0; i < lrspecLogicalReadersList.getItemCount(); i++) {
					if (input.equals(lrspecLogicalReadersList.getItem(i))) {
						found = true;
					}
				}

				if (found == false) {
					lrspecLogicalReadersList.add(input);

				} else {
					MessageDialog.openError(container.getShell(), "Error",
							"Reader already exists! Please try again!");
				}

				// check if plug-in exists and if so, add dynamic reader to
				// LRSpecConfiguratorView if does not
				// exist
				if (exists("org.ow2.aspirerfid.ide.aleconfig.Activator")) {
					if (findLRSpecConfiguratorView() != null) {
						IPreferenceStore lrspecConfigPreferences = org.ow2.aspirerfid.ide.aleconfig.Activator
								.getDefault().getPreferenceStore();
						String readerNamesArray[] = {};
						String readerNames = "";
						boolean exists = false;

						readerNamesArray = lrspecConfigPreferences
								.getString(
										org.ow2.aspirerfid.ide.aleconfig.preferences.PreferenceConstants.P_ReaderNames)
								.split(",");

						for (int i = 0; i < readerNamesArray.length; i++) {
							if (input.equals(readerNamesArray[i])) {
								exists = true;
							}
						}

						if (exists == false) {

							readerNames = lrspecConfigPreferences
									.getString(org.ow2.aspirerfid.ide.aleconfig.preferences.PreferenceConstants.P_ReaderNames);

							readerNames = readerNames + "," + input;

							lrspecConfigPreferences
									.setValue(
											org.ow2.aspirerfid.ide.aleconfig.preferences.PreferenceConstants.P_ReaderNames,
											readerNames);
						}
					}
				}
			}
		}
	}

	private LRSpecConfiguratorView findLRSpecConfiguratorView() {

		if (PlatformUI.getWorkbench().getWorkbenchWindows().length != 0) {

			for (int i = 0; i < PlatformUI.getWorkbench().getWorkbenchWindows().length; i++) {

				IWorkbenchWindow iWorkbenchWindow = PlatformUI.getWorkbench()
						.getWorkbenchWindows()[i];

				for (int j = 0; j < iWorkbenchWindow.getPages().length; j++) {

					IWorkbenchPage iWorkbenchPage = iWorkbenchWindow.getPages()[j];

					for (int k = 0; k < iWorkbenchPage.getViewReferences().length; k++) {

						if (iWorkbenchPage.getViewReferences()[k].getId()
								.equals(LRSpecConfiguratorView.ID)) {

							return (LRSpecConfiguratorView) iWorkbenchPage
									.getViewReferences()[k].getView(false);
						}

					}
				}
			}
		}

		return null;

	}

}
