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

package org.ow2.aspirerfid.ide.ecspec.views;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JOptionPane;
import javax.xml.bind.JAXBElement;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseTrackAdapter;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.VerifyEvent;
import org.eclipse.swt.events.VerifyListener;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.IViewReference;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.ViewPart;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Event;

import org.ow2.aspirerfid.ide.ecspec.utils.swtdesigner.SWTResourceManager;
import org.ow2.aspirerfid.ide.ecspec.utils.ECBoundarySpecBuilder;
import org.ow2.aspirerfid.ide.ecspec.utils.ECReportSpecBuilder;
import org.ow2.aspirerfid.ide.ecspec.utils.ECSpecBuilder;
import org.ow2.aspirerfid.ide.ecspec.utils.LRSpecBuilder;
import org.ow2.aspirerfid.ide.ecspec.Activator;
import org.ow2.aspirerfid.ide.ecspec.preferences.PreferenceConstants;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import org.accada.ale.xsd.ale.epcglobal.*;
import org.accada.ale.xsd.ale.epcglobal.ECBoundarySpecExtension.StartTriggerList;
import org.accada.ale.xsd.ale.epcglobal.ECBoundarySpecExtension.StopTriggerList;
import org.accada.ale.xsd.ale.epcglobal.ECFilterListMember.PatList;
import org.accada.ale.xsd.ale.epcglobal.ECFilterSpec.ExcludePatterns;
import org.accada.ale.xsd.ale.epcglobal.ECFilterSpec.IncludePatterns;
import org.accada.ale.xsd.ale.epcglobal.ECFilterSpecExtension.FilterList;
import org.accada.ale.xsd.ale.epcglobal.ECReaderStat.Sightings;
import org.accada.ale.xsd.ale.epcglobal.ECReportGroupListMemberExtension.Stats;
import org.accada.ale.xsd.ale.epcglobal.ECReportSpecExtension.StatProfileNames;
import org.accada.ale.xsd.ale.epcglobal.ECReports.Reports;
import org.accada.ale.xsd.ale.epcglobal.ECSpec.LogicalReaders;
import org.accada.ale.xsd.ale.epcglobal.ECSpec.ReportSpecs;
import org.accada.ale.xsd.ale.epcglobal.ECSpecExtension.PrimaryKeyFields;
import org.accada.ale.xsd.ale.epcglobal.ECTagStat.StatBlocks;
import org.accada.ale.xsd.ale.epcglobal.LRSpec.Properties;
import org.accada.ale.xsd.ale.epcglobal.LRSpec.Readers;
import org.ow2.aspirerfid.ide.ecspec.utils.swtdesigner.*;
import org.ow2.aspirerfid.ide.aleconfig.dialogs.*;
import org.ow2.aspirerfid.ide.aleconfig.views.*;

/**
 * @author Vasso Koletti e-mail: vkol@ait.edu.gr
 * @author Nikos Kefalakis (nkef) e-mail: nkef@ait.edu.gr
 * 
 */
@SuppressWarnings("unused")
public class ECSpecEditorView extends ViewPart {

	private List ecspecStopTriggerList;
	private List ecspecStartTriggerList;
	private Tree ecspectree;
	private TreeItem ecspecNodeChild;
	private List reportsList;
	private List patternList;
	private List excludePatternsList;
	private List includePatternsList;
	private Text ecBoundarySpecDurationValueText;
	private Text ecBoundarySpecStopTriggerText;
	private Text ecBoundarySpecStartTriggerText;
	private List ecspecIncludedReadersList;
	private List ecspecAvailableReadersList;
	private Text groupSpecURIPatternText;
	private Text excludePatternsText;
	private Text includePatternsText;
	private Combo ecReportSpecReportSetCombo;
	private Text reportNameText;
	private Text ecBoundarySpecStableSetIntervalValueText;
	private Text ecBoundarySpecRepeatPeriodValueText;
	private Button includeSpecInReportCheckBox;
	private Composite compositeECSpec;
	private Composite composite;
	private Composite composite_2;
	private Composite composite_1;
	private Composite ecspecEditorComposite;
	private Button reportIfEmptyCheck;
	private Button reportOnlyOnChangeCheck;
	private Button includeCountCheck;
	private Button includeEpcCheck;
	private Button includeRawDecimalCheck;
	private Button includeRawHexCheck;
	private Button includeTagCheck;
	private Button ecspecWhenDataAvailableCheckBox;
	private Button ecspecSaveButton;
	private TreeItem ECSpecsNode;

	Composite container;

	public static final String ID = "org.ow2.aspirerfid.ide.ecspec.views.ECSpecEditorView"; //$NON-NLS-1$

	private String[] availableToIncludedReaders = { "" };
	private String[] includedToAvailableReaders = { "" };

	private String existingECSpecFileToBeSaved;

	private ArrayList<String> ecSpecIncludedLogicalReadersList;

	private int includePatternsListSelectedIndex;
	private int excludePatternsListSelectedIndex;
	private int startTriggerListSelectedIndex;
	private int patternListSelectedIndex;
	private int stopTriggerListSelectedIndex;
	private int reportsListSelectedIndex = -1;

	private ECSpecBuilder ecSpecBuilder;
	private ECSpecBuilder openEcSpecBuilder;
	private ECBoundarySpecBuilder ecBoundarySpecBuilder;
	private ECReportSetSpec ecReportSetSpec;
	private ECReportSpec ecReportSpec;
	private ECFilterSpec ecFilterSpec;
	private IncludePatterns includePatterns;
	private ExcludePatterns excludePatterns;
	private ECGroupSpec ecGroupSpec;
	private ECReportOutputSpec ecReportOutputSpec;
	private ECSpec ecSpec;
	private ECBoundarySpecExtension ecBoundarySpecExtension;
	private StartTriggerList startTriggerList;
	private StopTriggerList stopTriggerList;

	/** Returns the preference store for this UI plug-in */
	IPreferenceStore preferences = Activator.getDefault().getPreferenceStore();

	public ECSpecEditorView() {
		ecSpecBuilder = new ECSpecBuilder();
		openEcSpecBuilder = new ECSpecBuilder();
		ecBoundarySpecBuilder = new ECBoundarySpecBuilder();
		ecReportSetSpec = new ECReportSetSpec();
		ecReportSpec = new ECReportSpec();
		ecFilterSpec = new ECFilterSpec();
		includePatterns = new IncludePatterns();
		excludePatterns = new ExcludePatterns();
		ecGroupSpec = new ECGroupSpec();
		ecReportOutputSpec = new ECReportOutputSpec();
		ecSpecIncludedLogicalReadersList = new ArrayList<String>();
		ecBoundarySpecExtension = new ECBoundarySpecExtension();
		startTriggerList = new StartTriggerList();
		stopTriggerList = new StopTriggerList();
	}

	/**
	 * Create contents of the view part
	 * 
	 * @param parent
	 */
	@Override
	public void createPartControl(Composite parent) {
		container = new Composite(parent, SWT.NONE);
		container.setLayout(new FillLayout());

		final ScrolledComposite scrolledComposite = new ScrolledComposite(
				container, SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL);
		scrolledComposite.setAlwaysShowScrollBars(false);

		final Composite composite_3 = new Composite(scrolledComposite, SWT.NONE);
		composite_3.setLocation(0, 0);

		composite = new Composite(composite_3, SWT.BORDER);
		composite.setBounds(0, 582, 182, 107);
		composite.setBackground(SWTResourceManager.getColor(255, 255, 255));

		final Group openExistingFileGroup = new Group(composite, SWT.NONE);
		openExistingFileGroup.setText("Open existing file");
		openExistingFileGroup.setBackground(SWTResourceManager.getColor(255,
				255, 255));
		openExistingFileGroup.setBounds(10, 10, 158, 83);

		final Button ecspecOpenFileButton = new Button(openExistingFileGroup,
				SWT.NONE);
		ecspecOpenFileButton
				.addMouseListener(new EcspecOpenFileButtonMouseListener());
		ecspecOpenFileButton.setBounds(37, 34, 85, 26);
		ecspecOpenFileButton.setImage(ResourceManager.getPluginImage(Activator
				.getDefault(), "icons/secure_file_transfer_icon.jpg"));
		ecspecOpenFileButton.setText("Open file");

		composite_2 = new Composite(composite_3, SWT.BORDER);
		composite_2.setBounds(0, 537, 182, 45);
		composite_2.setBackground(SWTResourceManager.getColor(255, 255, 255));

		final Button ecspecTreeRefreshButton = new Button(composite_2, SWT.NONE);
		ecspecTreeRefreshButton
				.addMouseListener(new EcspecTreeRefreshButtonMouseListener());
		ecspecTreeRefreshButton.setImage(ResourceManager.getPluginImage(
				Activator.getDefault(), "icons/icon_refresh3.png"));
		ecspecTreeRefreshButton.setText("Refresh");
		ecspecTreeRefreshButton.setBounds(48, 10, 85, 26);

		composite_1 = new Composite(composite_3, SWT.BORDER);
		composite_1.setBounds(0, 0, 182, 81);
		composite_1.setBackground(SWTResourceManager.getColor(255, 255, 255));

		final Button button = new Button(composite_1, SWT.FLAT);
		button.setImage(ResourceManager.getPluginImage(Activator.getDefault(),
				"icons/ecspeclogo.gif"));
		button.setImage(ResourceManager.getPluginImage(Activator.getDefault(),
				"icons/ecspeclogo.gif"));
		button.setImage(ResourceManager.getPluginImage(Activator.getDefault(),
				"icons/ecspeclogo.gif"));
		button.setBounds(10, 14, 158, 43);

		ecspectree = new Tree(composite_3, SWT.BORDER);
		ecspectree.setBounds(0, 81, 182, 456);
		ecspectree.addMouseListener(new EcspectreeMouseListener());

		ECSpecsNode = new TreeItem(ecspectree, SWT.NONE, 0);
		ECSpecsNode.setExpanded(true);
		ECSpecsNode.setImage(ResourceManager.getPluginImage(Activator
				.getDefault(), "icons/https.png"));
		ECSpecsNode.setText("ECSpecs");

		compositeECSpec = new Composite(composite_3, SWT.BORDER);
		compositeECSpec.setBounds(182, 0, 621, 689);
		compositeECSpec.setBackground(SWTResourceManager
				.getColor(255, 255, 255));

		final Group ecspecLogicalReadersGroup = new Group(compositeECSpec,
				SWT.NONE);
		ecspecLogicalReadersGroup.setBackground(SWTResourceManager.getColor(
				255, 255, 255));
		ecspecLogicalReadersGroup.setText("Logical readers");
		ecspecLogicalReadersGroup.setBounds(10, 0, 600, 150);

		final Label ecspecAvailableLogicalReadersLabel = new Label(
				ecspecLogicalReadersGroup, SWT.NONE);
		ecspecAvailableLogicalReadersLabel.setBackground(SWTResourceManager
				.getColor(255, 255, 255));
		ecspecAvailableLogicalReadersLabel.setText("Available:");
		ecspecAvailableLogicalReadersLabel.setBounds(10, 23, 55, 16);

		final Label ecspecIncludedLogicalReadersLabel = new Label(
				ecspecLogicalReadersGroup, SWT.NONE);
		ecspecIncludedLogicalReadersLabel.setBackground(SWTResourceManager
				.getColor(255, 255, 255));
		ecspecIncludedLogicalReadersLabel.setText("Included:");
		ecspecIncludedLogicalReadersLabel.setBounds(376, 23, 55, 16);

		final Button ecspecAddReaderButton = new Button(
				ecspecLogicalReadersGroup, SWT.NONE);
		ecspecAddReaderButton
				.addMouseListener(new EcspecAddReaderButtonMouseListener());
		ecspecAddReaderButton.setText(">>");
		ecspecAddReaderButton.setBounds(84, 114, 49, 26);

		final Button ecspecTruncateReaderButton = new Button(
				ecspecLogicalReadersGroup, SWT.NONE);
		ecspecTruncateReaderButton
				.addMouseListener(new EcspecTruncateReaderButtonMouseListener());
		ecspecTruncateReaderButton.setText("<<");
		ecspecTruncateReaderButton.setBounds(462, 114, 49, 26);

		ecspecAvailableReadersList = new List(ecspecLogicalReadersGroup,
				SWT.V_SCROLL | SWT.H_SCROLL | SWT.BORDER);
		ecspecAvailableReadersList.setBackground(SWTResourceManager.getColor(
				230, 230, 230));
		ecspecAvailableReadersList.setBounds(10, 45, 214, 63);

		ecspecAvailableReadersList.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				List list = (List) e.getSource();
				availableToIncludedReaders = list.getSelection();
			}
		});

		ecspecIncludedReadersList = new List(ecspecLogicalReadersGroup,
				SWT.V_SCROLL | SWT.H_SCROLL | SWT.BORDER);
		ecspecIncludedReadersList.setBackground(SWTResourceManager.getColor(
				230, 230, 230));
		ecspecIncludedReadersList.setBounds(376, 45, 214, 63);

		ecspecIncludedReadersList.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				List list = (List) e.getSource();
				includedToAvailableReaders = list.getSelection();
			}
		});

		final Button ecspecAddNewReaderButton = new Button(
				ecspecLogicalReadersGroup, SWT.NONE);
		ecspecAddNewReaderButton.setImage(ResourceManager.getPluginImage(
				Activator.getDefault(), "icons/iconAddOnPackage.gif"));
		ecspecAddNewReaderButton
				.addMouseListener(new EcspecAddNewReaderButtonMouseListener());
		ecspecAddNewReaderButton.setText("New reader");
		ecspecAddNewReaderButton.setBounds(251, 114, 100, 26);

		final TabFolder tabFolder = new TabFolder(compositeECSpec, SWT.NONE);
		tabFolder.setBackground(SWTResourceManager.getColor(255, 255, 255));
		tabFolder.setBounds(10, 156, 600, 454);

		final TabItem ecspecBoundariesTabItem = new TabItem(tabFolder, SWT.NONE);
		ecspecBoundariesTabItem.setText("boundarySpec");

		final TabItem ecspecReportSpecsTabItem = new TabItem(tabFolder,
				SWT.NONE);
		ecspecReportSpecsTabItem.setText("reportSpec");

		final Composite compositeECReportSpec = new Composite(tabFolder,
				SWT.NONE);
		compositeECReportSpec.setBackground(SWTResourceManager.getColor(255,
				255, 255));
		ecspecReportSpecsTabItem.setControl(compositeECReportSpec);

		final Label ecReportSpecReportNameLabel = new Label(
				compositeECReportSpec, SWT.NONE);
		ecReportSpecReportNameLabel.setBackground(SWTResourceManager.getColor(
				255, 255, 255));
		ecReportSpecReportNameLabel.setText("Report name:");
		ecReportSpecReportNameLabel.setBounds(10, 21, 74, 16);

		reportNameText = new Text(compositeECReportSpec, SWT.BORDER);
		reportNameText
				.setBackground(SWTResourceManager.getColor(230, 230, 230));
		reportNameText.setBounds(90, 18, 180, 22);

		final Label ecReportSpecReportSpecLabel = new Label(
				compositeECReportSpec, SWT.NONE);
		ecReportSpecReportSpecLabel.setBackground(SWTResourceManager.getColor(
				255, 255, 255));
		ecReportSpecReportSpecLabel.setText("Report set:");
		ecReportSpecReportSpecLabel.setBounds(338, 21, 57, 16);

		ecReportSpecReportSetCombo = new Combo(compositeECReportSpec,
				SWT.READ_ONLY);
		ecReportSpecReportSetCombo.setBackground(SWTResourceManager.getColor(
				230, 230, 230));
		ecReportSpecReportSetCombo.select(0);
		ecReportSpecReportSetCombo.setItems(new String[] { "CURRENT",
				"ADDITIONS", "DELETIONS" });
		ecReportSpecReportSetCombo.setBounds(401, 18, 180, 22);

		final TabFolder ecReportSpecFilterSpecTabFolder = new TabFolder(
				compositeECReportSpec, SWT.NONE);
		ecReportSpecFilterSpecTabFolder.setBackground(SWTResourceManager
				.getColor(255, 255, 255));
		ecReportSpecFilterSpecTabFolder.setBounds(10, 62, 570, 194);

		final TabItem ecReportSpecFilterSpecTabItem = new TabItem(
				ecReportSpecFilterSpecTabFolder, SWT.NONE);
		ecReportSpecFilterSpecTabItem.setText("filterSpec");

		final TabItem ecReportSpecGroupSpecTabItem = new TabItem(
				ecReportSpecFilterSpecTabFolder, SWT.NONE);
		ecReportSpecGroupSpecTabItem.setText("groupSpec");

		final TabItem ecReportSpecOutputTabItem = new TabItem(
				ecReportSpecFilterSpecTabFolder, SWT.NONE);
		ecReportSpecOutputTabItem.setText("reportOutputSpec");

		final Composite compositeECReportSpecOutput = new Composite(
				ecReportSpecFilterSpecTabFolder, SWT.NONE);
		ecReportSpecOutputTabItem.setControl(compositeECReportSpecOutput);

		includeEpcCheck = new Button(compositeECReportSpecOutput, SWT.CHECK);
		includeEpcCheck.setSelection(true);
		includeEpcCheck.setText("Include EPC");
		includeEpcCheck.setBounds(10, 15, 87, 16);

		includeTagCheck = new Button(compositeECReportSpecOutput, SWT.CHECK);
		includeTagCheck.setText("Include Tag");
		includeTagCheck.setBounds(10, 46, 87, 16);

		includeRawHexCheck = new Button(compositeECReportSpecOutput, SWT.CHECK);
		includeRawHexCheck.setText("Include Raw Hex");
		includeRawHexCheck.setBounds(10, 108, 114, 16);

		includeRawDecimalCheck = new Button(compositeECReportSpecOutput,
				SWT.CHECK);
		includeRawDecimalCheck.setText("Include Raw Decimal");
		includeRawDecimalCheck.setBounds(10, 139, 137, 16);

		includeCountCheck = new Button(compositeECReportSpecOutput, SWT.CHECK);
		includeCountCheck.setText("Include Count");
		includeCountCheck.setBounds(10, 78, 99, 16);

		final Composite compositeECReportSpecGroupSpec = new Composite(
				ecReportSpecFilterSpecTabFolder, SWT.NONE);
		ecReportSpecGroupSpecTabItem.setControl(compositeECReportSpecGroupSpec);

		final Label patternLabel = new Label(compositeECReportSpecGroupSpec,
				SWT.NONE);
		patternLabel.setText("Pattern:");
		patternLabel.setBounds(10, 21, 43, 16);

		groupSpecURIPatternText = new Text(compositeECReportSpecGroupSpec,
				SWT.BORDER);
		groupSpecURIPatternText.setBackground(SWTResourceManager.getColor(230,
				230, 230));
		groupSpecURIPatternText.setBounds(62, 18, 130, 22);

		final Button patternAddButton = new Button(
				compositeECReportSpecGroupSpec, SWT.NONE);
		patternAddButton.setImage(ResourceManager.getPluginImage(Activator
				.getDefault(), "icons/Add_icon.gif"));
		patternAddButton.addMouseListener(new PatternAddButtonMouseListener());
		patternAddButton.setText("Add");
		patternAddButton.setBounds(198, 16, 57, 26);

		final Label label_8 = new Label(compositeECReportSpecGroupSpec,
				SWT.SEPARATOR | SWT.HORIZONTAL);
		label_8.setBounds(10, 74, 245, 8);

		final Label patternListLabel = new Label(
				compositeECReportSpecGroupSpec, SWT.NONE);
		patternListLabel.setForeground(SWTResourceManager.getColor(0, 0, 255));
		patternListLabel.setText("Pattern list");
		patternListLabel.setBounds(10, 59, 111, 16);

		patternList = new List(compositeECReportSpecGroupSpec, SWT.V_SCROLL
				| SWT.H_SCROLL | SWT.BORDER);
		patternList.setBackground(SWTResourceManager.getColor(230, 230, 230));
		patternList.setBounds(10, 88, 163, 58);

		patternList.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				List list = (List) e.getSource();
				patternListSelectedIndex = list.getSelectionIndex();
				groupSpecURIPatternText.setText(patternList
						.getItem(patternListSelectedIndex));
			}
		});

		final Button patternUpdateButton = new Button(
				compositeECReportSpecGroupSpec, SWT.NONE);
		patternUpdateButton.setImage(ResourceManager.getPluginImage(Activator
				.getDefault(), "icons/Automatic_Update_icon.jpg"));
		patternUpdateButton
				.addMouseListener(new PatternUpdateButtonMouseListener());
		patternUpdateButton.setText("Update");
		patternUpdateButton.setBounds(179, 88, 76, 26);

		final Button patternDeleteButton = new Button(
				compositeECReportSpecGroupSpec, SWT.NONE);
		patternDeleteButton.setImage(ResourceManager.getPluginImage(Activator
				.getDefault(), "icons/delete_icon_2.gif"));
		patternDeleteButton
				.addMouseListener(new PatternDeleteButtonMouseListener());
		patternDeleteButton.setText("Delete");
		patternDeleteButton.setBounds(179, 120, 76, 26);

		final Composite compositeECReportSpecFilterSpec = new Composite(
				ecReportSpecFilterSpecTabFolder, SWT.NONE);
		ecReportSpecFilterSpecTabItem
				.setControl(compositeECReportSpecFilterSpec);

		final Label includePatternsLabel = new Label(
				compositeECReportSpecFilterSpec, SWT.NONE);
		includePatternsLabel.setText("Include patterns:");
		includePatternsLabel.setBounds(10, 21, 92, 16);

		includePatternsText = new Text(compositeECReportSpecFilterSpec,
				SWT.BORDER);
		includePatternsText.setBackground(SWTResourceManager.getColor(230, 230,
				230));
		includePatternsText.setBounds(108, 18, 84, 22);

		final Label excludePatternsLabel = new Label(
				compositeECReportSpecFilterSpec, SWT.NONE);
		excludePatternsLabel.setText("Exclude patterns:");
		excludePatternsLabel.setBounds(307, 21, 94, 16);

		excludePatternsText = new Text(compositeECReportSpecFilterSpec,
				SWT.BORDER);
		excludePatternsText.setBackground(SWTResourceManager.getColor(230, 230,
				230));
		excludePatternsText.setBounds(407, 18, 84, 22);

		final Button includePatternsAddButton = new Button(
				compositeECReportSpecFilterSpec, SWT.NONE);
		includePatternsAddButton.setImage(ResourceManager.getPluginImage(
				Activator.getDefault(), "icons/Add_icon.gif"));
		includePatternsAddButton
				.addMouseListener(new IncludePatternsAddButtonMouseListener());
		includePatternsAddButton.setText("Add");
		includePatternsAddButton.setBounds(198, 16, 57, 26);

		final Button excludePatternsAddButton = new Button(
				compositeECReportSpecFilterSpec, SWT.NONE);
		excludePatternsAddButton.setImage(ResourceManager.getPluginImage(
				Activator.getDefault(), "icons/Add_icon.gif"));
		excludePatternsAddButton
				.addMouseListener(new ExcludePatternsAddButtonMouseListener());
		excludePatternsAddButton.setText("Add");
		excludePatternsAddButton.setBounds(497, 16, 57, 26);

		includePatternsList = new List(compositeECReportSpecFilterSpec,
				SWT.V_SCROLL | SWT.H_SCROLL | SWT.BORDER);
		includePatternsList.setBackground(SWTResourceManager.getColor(230, 230,
				230));
		includePatternsList.setBounds(10, 92, 163, 57);

		includePatternsList.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				List list = (List) e.getSource();
				includePatternsListSelectedIndex = list.getSelectionIndex();
				includePatternsText.setText(includePatternsList
						.getItem(includePatternsListSelectedIndex));
			}
		});

		final Button includePatternsUpdateButton = new Button(
				compositeECReportSpecFilterSpec, SWT.NONE);
		includePatternsUpdateButton.setImage(ResourceManager.getPluginImage(
				Activator.getDefault(), "icons/Automatic_Update_icon.jpg"));
		includePatternsUpdateButton
				.addMouseListener(new IncludePatternsUpdateButtonMouseListener());
		includePatternsUpdateButton.setText("Update");
		includePatternsUpdateButton.setBounds(179, 91, 76, 26);

		final Button includePatternsDeleteButton = new Button(
				compositeECReportSpecFilterSpec, SWT.NONE);
		includePatternsDeleteButton.setImage(ResourceManager.getPluginImage(
				Activator.getDefault(), "icons/delete_icon_2.gif"));
		includePatternsDeleteButton
				.addMouseListener(new IncludePatternsDeleteButtonMouseListener());
		includePatternsDeleteButton.setText("Delete");
		includePatternsDeleteButton.setBounds(179, 123, 76, 26);

		final Label label = new Label(compositeECReportSpecFilterSpec,
				SWT.SEPARATOR | SWT.HORIZONTAL);
		label.setBounds(10, 77, 245, 8);

		final Label includePatternsListLabel = new Label(
				compositeECReportSpecFilterSpec, SWT.NONE);
		includePatternsListLabel.setForeground(SWTResourceManager.getColor(0,
				0, 255));
		includePatternsListLabel.setText("Include patterns list");
		includePatternsListLabel.setBounds(10, 59, 111, 16);

		excludePatternsList = new List(compositeECReportSpecFilterSpec,
				SWT.V_SCROLL | SWT.H_SCROLL | SWT.BORDER);
		excludePatternsList.setBackground(SWTResourceManager.getColor(230, 230,
				230));
		excludePatternsList.setBounds(307, 92, 163, 57);

		excludePatternsList.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				List list = (List) e.getSource();
				excludePatternsListSelectedIndex = list.getSelectionIndex();
				excludePatternsText.setText(excludePatternsList
						.getItem(excludePatternsListSelectedIndex));
			}
		});

		final Button excludePatternsUpdateButton = new Button(
				compositeECReportSpecFilterSpec, SWT.NONE);
		excludePatternsUpdateButton.setImage(ResourceManager.getPluginImage(
				Activator.getDefault(), "icons/Automatic_Update_icon.jpg"));
		excludePatternsUpdateButton
				.addMouseListener(new ExcludePatternsUpdateButtonMouseListener());
		excludePatternsUpdateButton.setText("Update");
		excludePatternsUpdateButton.setBounds(476, 91, 78, 26);

		final Button excludePatternsDeleteButton = new Button(
				compositeECReportSpecFilterSpec, SWT.NONE);
		excludePatternsDeleteButton.setImage(ResourceManager.getPluginImage(
				Activator.getDefault(), "icons/delete_icon_2.gif"));
		excludePatternsDeleteButton
				.addMouseListener(new ExcludePatternsDeleteButtonMouseListener());
		excludePatternsDeleteButton.setText("Delete");
		excludePatternsDeleteButton.setBounds(476, 123, 78, 26);

		final Label label_7 = new Label(compositeECReportSpecFilterSpec,
				SWT.SEPARATOR | SWT.HORIZONTAL);
		label_7.setBounds(307, 77, 247, 8);

		final Label excludePatternsListLabel = new Label(
				compositeECReportSpecFilterSpec, SWT.NONE);
		excludePatternsListLabel.setForeground(SWTResourceManager.getColor(0,
				0, 255));
		excludePatternsListLabel.setText("Exclude patterns list");
		excludePatternsListLabel.setBounds(307, 59, 111, 16);

		reportIfEmptyCheck = new Button(compositeECReportSpec, SWT.CHECK);
		reportIfEmptyCheck.setBackground(SWTResourceManager.getColor(255, 255,
				255));
		reportIfEmptyCheck.setText("Report If Empty");
		reportIfEmptyCheck.setBounds(10, 262, 103, 16);

		reportOnlyOnChangeCheck = new Button(compositeECReportSpec, SWT.CHECK);
		reportOnlyOnChangeCheck.setBackground(SWTResourceManager.getColor(255,
				255, 255));
		reportOnlyOnChangeCheck.setText("Report Only On Change");
		reportOnlyOnChangeCheck.setBounds(429, 262, 153, 16);

		final Label label_3 = new Label(compositeECReportSpec, SWT.SEPARATOR
				| SWT.HORIZONTAL);
		label_3.setBackground(SWTResourceManager.getColor(255, 255, 255));
		label_3.setBounds(11, 282, 570, 10);

		final Button reportAddButton = new Button(compositeECReportSpec,
				SWT.NONE);
		reportAddButton.setImage(ResourceManager.getPluginImage(Activator
				.getDefault(), "icons/calAddIcon.gif"));
		reportAddButton.addMouseListener(new ReportAddButtonMouseListener());
		reportAddButton.setText("Add report");
		reportAddButton.setBounds(10, 295, 91, 26);

		reportsList = new List(compositeECReportSpec, SWT.V_SCROLL
				| SWT.H_SCROLL | SWT.BORDER);
		reportsList.setBackground(SWTResourceManager.getColor(230, 230, 230));
		reportsList.setBounds(10, 361, 163, 57);

		reportsList.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				List list = (List) e.getSource();
				reportsListSelectedIndex = list.getSelectionIndex();

				resetReportSpecFields();

				loadReportListItemData(ecSpecBuilder, reportsListSelectedIndex);
			}
		});

		final Button reportSpecsUpdateButton = new Button(
				compositeECReportSpec, SWT.NONE);
		reportSpecsUpdateButton.setImage(ResourceManager.getPluginImage(
				Activator.getDefault(), "icons/Automatic_Update_icon.jpg"));
		reportSpecsUpdateButton
				.addMouseListener(new ReportSpecsUpdateButtonMouseListener());
		reportSpecsUpdateButton.setText("Update");
		reportSpecsUpdateButton.setBounds(179, 360, 76, 26);

		final Button reportSpecsDeleteButton = new Button(
				compositeECReportSpec, SWT.NONE);
		reportSpecsDeleteButton
				.addMouseListener(new ReportSpecsDeleteButtonMouseListener());
		reportSpecsDeleteButton.setImage(ResourceManager.getPluginImage(
				Activator.getDefault(), "icons/delete_icon_2.gif"));
		reportSpecsDeleteButton.setText("Delete");
		reportSpecsDeleteButton.setBounds(179, 392, 76, 26);

		final Label label_1 = new Label(compositeECReportSpec, SWT.SEPARATOR
				| SWT.HORIZONTAL);
		label_1.setBackground(SWTResourceManager.getColor(255, 255, 255));
		label_1.setBounds(10, 347, 571, 10);

		final Label reportsListLabel = new Label(compositeECReportSpec,
				SWT.NONE);
		reportsListLabel.setBackground(SWTResourceManager.getColor(255, 255,
				255));
		reportsListLabel.setForeground(SWTResourceManager.getColor(0, 0, 255));
		reportsListLabel.setText("Reports List");
		reportsListLabel.setBounds(10, 330, 103, 16);

		final Button resetReportButton = new Button(compositeECReportSpec,
				SWT.NONE);
		resetReportButton
				.addMouseListener(new ResetReportButtonMouseListener());
		resetReportButton.setImage(ResourceManager.getPluginImage(Activator
				.getDefault(), "icons/delete_icon.gif"));
		resetReportButton.setText("Reset report");
		resetReportButton.setBounds(107, 295, 96, 26);

		final Composite compositeECBoundarySpec = new Composite(tabFolder,
				SWT.NONE);
		ecspecBoundariesTabItem.setControl(compositeECBoundarySpec);

		final Group group = new Group(compositeECBoundarySpec, SWT.NONE);
		group.setBounds(10, 8, 572, 407);

		final Label ecBoundarySpecStartTriggerLabel = new Label(group, SWT.NONE);
		ecBoundarySpecStartTriggerLabel.setBounds(11, 24, 64, 16);
		ecBoundarySpecStartTriggerLabel.setText("Start trigger:");

		ecBoundarySpecStartTriggerText = new Text(group, SWT.BORDER);
		ecBoundarySpecStartTriggerText.setBounds(81, 21, 130, 22);
		ecBoundarySpecStartTriggerText.setBackground(SWTResourceManager
				.getColor(230, 230, 230));

		final Label ecBoundarySpecStopTriggerLabel = new Label(group, SWT.NONE);
		ecBoundarySpecStopTriggerLabel.setBounds(297, 24, 64, 16);
		ecBoundarySpecStopTriggerLabel.setText("Stop trigger:");

		ecBoundarySpecStopTriggerText = new Text(group, SWT.BORDER);
		ecBoundarySpecStopTriggerText.setBounds(367, 21, 130, 22);
		ecBoundarySpecStopTriggerText.setBackground(SWTResourceManager
				.getColor(230, 230, 230));

		final Label repeatPeriodLabel = new Label(group, SWT.NONE);
		repeatPeriodLabel.setBounds(11, 176, 79, 16);
		repeatPeriodLabel.setForeground(SWTResourceManager.getColor(0, 0, 255));
		repeatPeriodLabel.setText("Repeat period");

		final Label label_4 = new Label(group, SWT.SEPARATOR | SWT.HORIZONTAL);
		label_4.setBounds(11, 190, 551, 8);

		final Label valueLabel = new Label(group, SWT.NONE);
		valueLabel.setBounds(11, 207, 64, 16);
		valueLabel.setText("Value (ms):");

		ecBoundarySpecRepeatPeriodValueText = new Text(group, SWT.BORDER);
		ecBoundarySpecRepeatPeriodValueText
				.addVerifyListener(new EcBoundarySpecRepeatPeriodValueTextVerifyListener());
		ecBoundarySpecRepeatPeriodValueText.setBounds(81, 204, 95, 22);
		ecBoundarySpecRepeatPeriodValueText.setBackground(SWTResourceManager
				.getColor(230, 230, 230));

		final Label durationLabel = new Label(group, SWT.NONE);
		durationLabel.setBounds(11, 239, 79, 16);
		durationLabel.setForeground(SWTResourceManager.getColor(0, 0, 255));
		durationLabel.setText("Duration");

		final Label label_5 = new Label(group, SWT.SEPARATOR | SWT.HORIZONTAL);
		label_5.setBounds(11, 253, 551, 8);

		final Label valueLabel_1 = new Label(group, SWT.NONE);
		valueLabel_1.setBounds(11, 268, 64, 16);
		valueLabel_1.setText("Value (ms):");

		ecBoundarySpecDurationValueText = new Text(group, SWT.BORDER);
		ecBoundarySpecDurationValueText
				.addVerifyListener(new EcBoundarySpecDurationValueTextVerifyListener());
		ecBoundarySpecDurationValueText.setBounds(81, 265, 95, 22);
		ecBoundarySpecDurationValueText.setBackground(SWTResourceManager
				.getColor(230, 230, 230));

		final Label ecBoundarySpecStableSetIntervalLabel = new Label(group,
				SWT.NONE);
		ecBoundarySpecStableSetIntervalLabel.setBounds(11, 301, 105, 16);
		ecBoundarySpecStableSetIntervalLabel.setForeground(SWTResourceManager
				.getColor(0, 0, 255));
		ecBoundarySpecStableSetIntervalLabel.setText("Stable set interval");

		final Label label_6 = new Label(group, SWT.SEPARATOR | SWT.HORIZONTAL);
		label_6.setBounds(11, 315, 551, 8);

		final Label valueLabel_2 = new Label(group, SWT.NONE);
		valueLabel_2.setBounds(11, 332, 64, 16);
		valueLabel_2.setText("Value (ms):");

		ecBoundarySpecStableSetIntervalValueText = new Text(group, SWT.BORDER);
		ecBoundarySpecStableSetIntervalValueText
				.addVerifyListener(new EcBoundarySpecStableSetIntervalValueTextVerifyListener());
		ecBoundarySpecStableSetIntervalValueText.setBounds(81, 329, 95, 22);
		ecBoundarySpecStableSetIntervalValueText
				.setBackground(SWTResourceManager.getColor(230, 230, 230));

		final Label startTriggerListLabel = new Label(group, SWT.NONE);
		startTriggerListLabel.setForeground(SWTResourceManager.getColor(0, 0,
				255));
		startTriggerListLabel.setText("Start trigger list");
		startTriggerListLabel.setBounds(11, 66, 79, 16);

		final Button ecspecStartTriggerAddButton = new Button(group, SWT.NONE);
		ecspecStartTriggerAddButton
				.addMouseListener(new EcspecStartTriggerAddButtonMouseListener());
		ecspecStartTriggerAddButton.setImage(ResourceManager.getPluginImage(
				Activator.getDefault(), "icons/Add_icon.gif"));
		ecspecStartTriggerAddButton.setText("Add");
		ecspecStartTriggerAddButton.setBounds(217, 19, 59, 26);

		final Button ecspecStopTriggerAddButton = new Button(group, SWT.NONE);
		ecspecStopTriggerAddButton
				.addMouseListener(new EcspecStopTriggerAddButtonMouseListener());
		ecspecStopTriggerAddButton.setImage(ResourceManager.getPluginImage(
				Activator.getDefault(), "icons/Add_icon.gif"));
		ecspecStopTriggerAddButton.setText("Add");
		ecspecStopTriggerAddButton.setBounds(503, 19, 59, 26);

		final Label label_9 = new Label(group, SWT.SEPARATOR | SWT.HORIZONTAL);
		label_9.setBounds(11, 82, 265, 8);

		final Label stopTriggerListLabel = new Label(group, SWT.NONE);
		stopTriggerListLabel.setForeground(SWTResourceManager.getColor(0, 0,
				255));
		stopTriggerListLabel.setText("Stop trigger list");
		stopTriggerListLabel.setBounds(297, 66, 79, 16);

		final Label label_10 = new Label(group, SWT.SEPARATOR | SWT.HORIZONTAL);
		label_10.setBounds(297, 82, 265, 8);

		ecspecStartTriggerList = new List(group, SWT.V_SCROLL | SWT.H_SCROLL
				| SWT.BORDER);
		ecspecStartTriggerList.setBackground(SWTResourceManager.getColor(230,
				230, 230));
		ecspecStartTriggerList.setBounds(11, 98, 183, 57);

		ecspecStartTriggerList.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				List list = (List) e.getSource();
				startTriggerListSelectedIndex = list.getSelectionIndex();
				ecBoundarySpecStartTriggerText.setText(ecspecStartTriggerList
						.getItem(startTriggerListSelectedIndex));
			}
		});

		final Button ecspecStartTriggerListUpdateButton = new Button(group,
				SWT.NONE);
		ecspecStartTriggerListUpdateButton
				.addMouseListener(new EcspecStartTriggerListUpdateButtonMouseListener());
		ecspecStartTriggerListUpdateButton.setImage(ResourceManager
				.getPluginImage(Activator.getDefault(),
						"icons/Automatic_Update_icon.jpg"));
		ecspecStartTriggerListUpdateButton.setText("Update");
		ecspecStartTriggerListUpdateButton.setBounds(200, 97, 76, 26);

		final Button ecspecStartTriggerListDeleteButton = new Button(group,
				SWT.NONE);
		ecspecStartTriggerListDeleteButton
				.addMouseListener(new EcspecStartTriggerListDeleteButtonMouseListener());
		ecspecStartTriggerListDeleteButton.setImage(ResourceManager
				.getPluginImage(Activator.getDefault(),
						"icons/delete_icon_2.gif"));
		ecspecStartTriggerListDeleteButton.setText("Delete");
		ecspecStartTriggerListDeleteButton.setBounds(200, 129, 76, 26);

		ecspecStopTriggerList = new List(group, SWT.V_SCROLL | SWT.H_SCROLL
				| SWT.BORDER);
		ecspecStopTriggerList.setBackground(SWTResourceManager.getColor(230,
				230, 230));
		ecspecStopTriggerList.setBounds(297, 98, 183, 57);

		ecspecStopTriggerList.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				List list = (List) e.getSource();
				stopTriggerListSelectedIndex = list.getSelectionIndex();
				ecBoundarySpecStopTriggerText.setText(ecspecStopTriggerList
						.getItem(stopTriggerListSelectedIndex));
			}
		});

		final Button ecspecStopTriggerListUpdateButton = new Button(group,
				SWT.NONE);
		ecspecStopTriggerListUpdateButton
				.addMouseListener(new EcspecStopTriggerListUpdateButtonMouseListener());
		ecspecStopTriggerListUpdateButton.setImage(ResourceManager
				.getPluginImage(Activator.getDefault(),
						"icons/Automatic_Update_icon.jpg"));
		ecspecStopTriggerListUpdateButton.setText("Update");
		ecspecStopTriggerListUpdateButton.setBounds(486, 96, 76, 26);

		final Button ecspecStopTriggerListDeleteButton = new Button(group,
				SWT.NONE);
		ecspecStopTriggerListDeleteButton
				.addMouseListener(new EcspecStopTriggerListDeleteButtonMouseListener());
		ecspecStopTriggerListDeleteButton.setImage(ResourceManager
				.getPluginImage(Activator.getDefault(),
						"icons/delete_icon_2.gif"));
		ecspecStopTriggerListDeleteButton.setText("Delete");
		ecspecStopTriggerListDeleteButton.setBounds(486, 129, 76, 26);

		ecspecWhenDataAvailableCheckBox = new Button(group, SWT.CHECK);
		ecspecWhenDataAvailableCheckBox.setText("When Data Available");
		ecspecWhenDataAvailableCheckBox.setBounds(11, 377, 165, 16);

		final Label label_11 = new Label(group, SWT.SEPARATOR | SWT.HORIZONTAL);
		label_11.setBounds(10, 363, 552, 8);

		final Button ecspecGenerateXMLButton = new Button(compositeECSpec,
				SWT.NONE);
		ecspecGenerateXMLButton
				.addMouseListener(new EcspecGenerateXMLButtonMouseListener());
		ecspecGenerateXMLButton.setImage(ResourceManager.getPluginImage(
				Activator.getDefault(), "icons/recording-launch.gif"));
		ecspecGenerateXMLButton.setText("Save as...");
		ecspecGenerateXMLButton.setBounds(10, 655, 93, 26);

		final Button ecspecResetButton = new Button(compositeECSpec, SWT.NONE);
		ecspecResetButton.setImage(ResourceManager.getPluginImage(Activator
				.getDefault(), "icons/icon_delete_tag.gif"));
		ecspecResetButton
				.addMouseListener(new EcspecResetButtonMouseListener());
		ecspecResetButton.setText("Reset");
		ecspecResetButton.setBounds(179, 655, 64, 26);

		final Label label_2 = new Label(compositeECSpec, SWT.SEPARATOR
				| SWT.HORIZONTAL);
		label_2.setBackground(SWTResourceManager.getColor(255, 255, 255));
		label_2.setBounds(10, 638, 600, 9);

		includeSpecInReportCheckBox = new Button(compositeECSpec, SWT.CHECK);
		includeSpecInReportCheckBox.setBackground(SWTResourceManager.getColor(
				255, 255, 255));
		includeSpecInReportCheckBox.setText("Include Spec In Report");
		includeSpecInReportCheckBox.setBounds(10, 616, 144, 16);

		ecspecSaveButton = new Button(compositeECSpec, SWT.NONE);
		ecspecSaveButton.addMouseListener(new EcspecSaveButtonMouseListener());
		ecspecSaveButton.setImage(ResourceManager.getPluginImage(Activator
				.getDefault(), "icons/img/b_save.png"));
		ecspecSaveButton.setText("Save");
		ecspecSaveButton.setBounds(109, 655, 64, 26);
		composite_3.setSize(796, 689);
		scrolledComposite.setContent(composite_3);

		initializeAvailableReadersList();
		initializeToolBar();
		initializeMenu();
		createActions();
	}

	private File[] getECSpecsPathFolderContents() {

		String folderPath = preferences
				.getString(PreferenceConstants.P_ECSpecsPATH);
		File f = new File(folderPath);

		if (f.exists() == false) {
			f.mkdirs();
		}

		File[] contents = f.listFiles();
		return contents;
	}

	private void initializeEcSpecTree() {
		ECSpecsNode.removeAll();

		File[] ECSpecDirectoryContents = getECSpecsPathFolderContents();

		if (ECSpecDirectoryContents.length != 0) {
			for (int i = 0; i < ECSpecDirectoryContents.length; i++) {
				if (ECSpecDirectoryContents[i].getName().endsWith(".xml")) {
					ecspecNodeChild = new TreeItem(ECSpecsNode, SWT.NULL);
					ecspecNodeChild.setText(ECSpecDirectoryContents[i]
							.getName());
				}
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

	private void initializeAvailableReadersList() {
		DocumentBuilderFactory documentBuilderFactory;
		DocumentBuilder documentBuilder;
		Document document = null;
		String root = "";

		File[] HALDirectoryContents = getHALLRSpecsPathFolderContents();
		File[] RPDirectoryContents = getRPLRSpecsPathFolderContents();
		File[] LLRPDirectoryContents = getLLRPLRSpecsPathFolderContents();

		if (HALDirectoryContents.length != 0) {
			for (int i = 0; i < HALDirectoryContents.length; i++) {
				if (HALDirectoryContents[i].getName().endsWith(".xml")) {

					// check if the file to be opened contains a static reader
					// or a
					// dynamic reader
					try {
						File selectedFile = new File(
								PreferenceConstants.P_HAL_LRSpecsPATH
										+ HALDirectoryContents[i].getName());

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

						ecspecAvailableReadersList.add(lrSpecNodeList.item(0)
								.getAttributes().item(0).getNodeValue());
					}

				}
			}
		}

		if (RPDirectoryContents.length != 0) {
			for (int i = 0; i < RPDirectoryContents.length; i++) {
				if (RPDirectoryContents[i].getName().endsWith(".xml")) {

					// check if the file to be opened contains a static reader
					// or a
					// dynamic reader
					try {
						File selectedFile = new File(
								PreferenceConstants.P_RP_LRSpecsPATH
										+ RPDirectoryContents[i].getName());

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

						ecspecAvailableReadersList.add(lrSpecNodeList.item(0)
								.getAttributes().item(0).getNodeValue());
					}
				}
			}
		}

		if (LLRPDirectoryContents.length != 0) {
			for (int i = 0; i < LLRPDirectoryContents.length; i++) {
				if (LLRPDirectoryContents[i].getName().endsWith(".xml")) {

					// check if the file to be opened contains a static reader
					// or a
					// dynamic reader
					try {
						File selectedFile = new File(
								PreferenceConstants.P_LLRP_LRSpecsPATH
										+ LLRPDirectoryContents[i].getName());

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

						ecspecAvailableReadersList.add(lrSpecNodeList.item(0)
								.getAttributes().item(0).getNodeValue());
					}
				}
			}
		}

		// load dynamic readers from LRSpecConfiguratorView
		if (exists("org.ow2.aspirerfid.ide.aleconfig.Activator")) {// added By
			// nkef

			IPreferenceStore lrspecConfigPreferences = org.ow2.aspirerfid.ide.aleconfig.Activator
					.getDefault().getPreferenceStore();

			String readerNamesArray[] = {};

			readerNamesArray = lrspecConfigPreferences
					.getString(
							org.ow2.aspirerfid.ide.aleconfig.preferences.PreferenceConstants.P_ReaderNames)
					.split(",");

			for (int j = 0; j < readerNamesArray.length; j++) {
				ecspecAvailableReadersList.add(readerNamesArray[j]);
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

	/**
	 * Create the actions
	 */
	private void createActions() {
		// Create the actions
		ecspecSaveButton.setEnabled(false);
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

	private class EcspecResetButtonMouseListener extends MouseAdapter {
		public void mouseDown(final MouseEvent e) {

			ecspecSaveButton.setEnabled(false);

			ecSpecBuilder = new ECSpecBuilder();
			ecBoundarySpecBuilder = new ECBoundarySpecBuilder();
			ecReportSetSpec = new ECReportSetSpec();
			ecReportSpec = new ECReportSpec();
			ecFilterSpec = new ECFilterSpec();
			includePatterns = new IncludePatterns();
			excludePatterns = new ExcludePatterns();
			ecGroupSpec = new ECGroupSpec();
			ecReportOutputSpec = new ECReportOutputSpec();
			ecSpecIncludedLogicalReadersList = new ArrayList<String>();
			ecBoundarySpecExtension = new ECBoundarySpecExtension();
			startTriggerList = new StartTriggerList();
			stopTriggerList = new StopTriggerList();

			for (int i = 0; i < ecspecIncludedReadersList.getItemCount(); i++) {
				ecspecAvailableReadersList.add(ecspecIncludedReadersList
						.getItem(i));
			}

			ecspecIncludedReadersList.removeAll();
			ecspecStartTriggerList.removeAll();
			ecspecStopTriggerList.removeAll();

			ecBoundarySpecStartTriggerText.setText("");
			ecBoundarySpecStopTriggerText.setText("");
			ecBoundarySpecRepeatPeriodValueText.setText("");
			ecBoundarySpecDurationValueText.setText("");
			ecBoundarySpecStableSetIntervalValueText.setText("");
			includeSpecInReportCheckBox.setSelection(false);

			reportNameText.setText("");
			ecReportSpecReportSetCombo.deselectAll();
			includePatternsList.removeAll();
			includePatternsText.setText("");
			excludePatternsList.removeAll();
			excludePatternsText.setText("");
			reportIfEmptyCheck.setSelection(false);
			reportOnlyOnChangeCheck.setSelection(false);
			reportsList.removeAll();
			groupSpecURIPatternText.setText("");
			patternList.removeAll();
			includeEpcCheck.setSelection(true);
			includeTagCheck.setSelection(false);
			includeCountCheck.setSelection(false);
			includeRawHexCheck.setSelection(false);
			includeRawDecimalCheck.setSelection(false);
			ecspecWhenDataAvailableCheckBox.setSelection(false);
		}
	}

	private class EcspecAddReaderButtonMouseListener extends MouseAdapter {
		public void mouseDown(final MouseEvent e) {
			if (availableToIncludedReaders[0] == "") {
				MessageDialog.openError(container.getShell(), "Error",
						"No reader has been selected!");

			} else {
				// add selected reader to included readers
				ecspecIncludedReadersList.add(availableToIncludedReaders[0]);

				// remove reader from logical readers list (available readers)
				ecspecAvailableReadersList
						.remove(availableToIncludedReaders[0]);

				// add reader to ecspec
				ecSpecIncludedLogicalReadersList
						.add(availableToIncludedReaders[0]);

				// set selected reader to null
				availableToIncludedReaders[0] = "";
			}
		}
	}

	private class EcspecTruncateReaderButtonMouseListener extends MouseAdapter {
		public void mouseDown(final MouseEvent e) {
			if (includedToAvailableReaders[0] == "") {
				MessageDialog.openError(container.getShell(), "Error",
						"No reader has been selected!");

			} else {
				// add selected reader to available readers
				ecspecAvailableReadersList.add(includedToAvailableReaders[0]);

				// remove reader from included readers list (available readers)
				ecspecIncludedReadersList.remove(includedToAvailableReaders[0]);

				// remove reader from included ecspec readers list
				ecSpecIncludedLogicalReadersList
						.remove(includedToAvailableReaders[0]);

				// set selected reader to null
				includedToAvailableReaders[0] = "";
			}
		}
	}

	private class EcspecGenerateXMLButtonMouseListener extends MouseAdapter {
		public void mouseDown(final MouseEvent e) {

			if (ecspecIncludedReadersList.getItemCount() == 0) {
				MessageDialog.openError(container.getShell(), "Error",
						"Included readers list should be filled!");

			} else if (reportsList.getItemCount() == 0) {
				MessageDialog.openError(container.getShell(), "Error",
						"Reports list should be filled!");

			} else {
				// set readers
				ecSpecBuilder
						.setLogicalReaders(ecSpecIncludedLogicalReadersList);

				// set Include Spec In reports
				ecSpecBuilder
						.setIncludeSpecInreports(includeSpecInReportCheckBox
								.getSelection());

				/* set ECBoundarySpec */

				// set start trigger
				if (ecBoundarySpecStartTriggerText.getText() != "") {
					Pattern p = Pattern
							.compile("^http[s]?://[-a-zA-Z0-9_.:]+[-a-zA-Z0-9_:@&?=+,.!/~*'%$]*$");
					Matcher m = p.matcher(ecBoundarySpecStartTriggerText
							.getText());
					boolean matched = m.matches();

					if (matched == true) {
						ecBoundarySpecBuilder.getECBoundarySpec()
								.setStartTrigger(
										ecBoundarySpecStartTriggerText
												.getText());
					} else {
						MessageDialog
								.openError(container.getShell(), "Error",
										"Start trigger is not valid! It should start with http://!");

						return;
					}

				}

				// set start trigger list
				if (ecspecStartTriggerList.getItemCount() != 0) {
					for (int i = 0; i < ecspecStartTriggerList.getItemCount(); i++) {
						startTriggerList.getStartTrigger().add(
								ecspecStartTriggerList.getItem(i));
					}
					ecBoundarySpecExtension
							.setStartTriggerList(startTriggerList);
				}

				// set stop trigger
				if (ecBoundarySpecStopTriggerText.getText() != "") {
					Pattern p = Pattern
							.compile("^http[s]?://[-a-zA-Z0-9_.:]+[-a-zA-Z0-9_:@&?=+,.!/~*'%$]*$");
					Matcher m = p.matcher(ecBoundarySpecStopTriggerText
							.getText());
					boolean matched = m.matches();

					if (matched == true) {
						ecBoundarySpecBuilder
								.getECBoundarySpec()
								.setStopTrigger(
										ecBoundarySpecStopTriggerText.getText());
					} else {
						MessageDialog
								.openError(container.getShell(), "Error",
										"Stop trigger is not valid! It should start with http://!");

						return;
					}

				}

				// set stop trigger list
				if (ecspecStopTriggerList.getItemCount() != 0) {
					for (int i = 0; i < ecspecStopTriggerList.getItemCount(); i++) {
						stopTriggerList.getStopTrigger().add(
								ecspecStopTriggerList.getItem(i));
					}
					ecBoundarySpecExtension.setStopTriggerList(stopTriggerList);
				}

				// set repeat period
				if (ecBoundarySpecRepeatPeriodValueText.getText() != "") {
					ecBoundarySpecBuilder.setRepeatPeriod(Integer
							.parseInt(ecBoundarySpecRepeatPeriodValueText
									.getText()));
				}

				// set duration
				if (ecBoundarySpecDurationValueText.getText() != "") {
					ecBoundarySpecBuilder
							.setDuration(Integer
									.parseInt(ecBoundarySpecDurationValueText
											.getText()));
				}

				// set stable set interval
				if (ecBoundarySpecStableSetIntervalValueText.getText() != "") {
					ecBoundarySpecBuilder.setStableSetInterval(Integer
							.parseInt(ecBoundarySpecStableSetIntervalValueText
									.getText()));
				}

				// set when data available
				if (ecspecWhenDataAvailableCheckBox.getSelection() == true) {
					ecBoundarySpecExtension
							.setWhenDataAvailable(ecspecWhenDataAvailableCheckBox
									.getSelection());
				}

				/* end of ECBoundarySpec */

				// set ECBoundarySpec to ECSpec
				ecSpecBuilder.setECBoundarySpec(ecBoundarySpecBuilder);
				ecSpecBuilder.getECBoundarySpec().getECBoundarySpec()
						.setExtension(ecBoundarySpecExtension);

				/* ************************* */

				FileDialog fd;

				fd = new FileDialog(compositeECSpec.getShell(), SWT.SAVE);
				fd.setText("Save");
				fd.setFileName(".xml");
				fd.setFilterExtensions(new String[] { ".xml" });
				String selection = fd.open();
				if (!selection.endsWith(".xml")) {
					selection += ".xml";
				}

				try {
					ecSpecBuilder.generateXml(selection);

					// add reader to ecspectree -> ECSpecsNode
					ecspecNodeChild = new TreeItem(ECSpecsNode, SWT.NULL);
					ecspecNodeChild.setText(selection.substring(selection
							.lastIndexOf("\\") + 1));

					ecspecSaveButton.setEnabled(false);
				} catch (IOException e1) { // TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}
	}

	private class IncludePatternsAddButtonMouseListener extends MouseAdapter {
		public void mouseDown(final MouseEvent e) {
			if (includePatternsText.getText().equals("")) {
				MessageDialog.openError(container.getShell(), "Error",
						"No pattern has been entered!");

			} else {
				Pattern p = Pattern
						.compile("urn:epc:[a-zA-Z0-9\\-]*\\:[a-zA-Z0-9\\-]*\\:[0-9]*\\*?\\.[0-9]*\\*?\\.[0-9]*\\*?");
				Matcher m = p.matcher(includePatternsText.getText());
				boolean matched = m.matches();
				boolean found = false;

				if (matched == true) {
					for (int i = 0; i < includePatternsList.getItemCount(); i++) {
						if (includePatternsText.getText().equals(
								includePatternsList.getItem(i))) {
							found = true;
						}
					}

					if (found == false) {
						includePatternsList.add(includePatternsText.getText());
						includePatternsText.setText("");
					} else {
						MessageDialog.openError(container.getShell(), "Error",
								"Pattern already exists!");
					}

				} else {
					MessageDialog
							.openError(container.getShell(), "Error",
									"Pattern is not valid! It should have the format: urn:epc:...:...:*.*.*!");
				}
			}
		}
	}

	private class ExcludePatternsAddButtonMouseListener extends MouseAdapter {
		public void mouseDown(final MouseEvent e) {
			if (excludePatternsText.getText().equals("")) {
				MessageDialog.openError(container.getShell(), "Error",
						"No pattern has been entered!");

			} else {
				Pattern p = Pattern
						.compile("urn:epc:[a-zA-Z0-9\\-]*\\:[a-zA-Z0-9\\-]*\\:[0-9]*\\*?\\.[0-9]*\\*?\\.[0-9]*\\*?");
				Matcher m = p.matcher(excludePatternsText.getText());
				boolean matched = m.matches();
				boolean found = false;

				if (matched == true) {
					for (int i = 0; i < excludePatternsList.getItemCount(); i++) {
						if (excludePatternsText.getText().equals(
								excludePatternsList.getItem(i))) {
							found = true;
						}
					}

					if (found == false) {
						excludePatternsList.add(excludePatternsText.getText());
						excludePatternsText.setText("");
					} else {
						MessageDialog.openError(container.getShell(), "Error",
								"Pattern already exists!");
					}

				} else {
					MessageDialog
							.openError(container.getShell(), "Error",
									"Pattern is not valid! It should have the format: urn:epc:...:...:*.*.*!");
				}

			}
		}
	}

	private class PatternAddButtonMouseListener extends MouseAdapter {
		public void mouseDown(final MouseEvent e) {
			if (groupSpecURIPatternText.getText().equals("")) {
				MessageDialog.openError(container.getShell(), "Error",
						"No pattern has been entered!");

			} else {
				Pattern p = Pattern
						.compile("urn:epc:[a-zA-Z0-9\\-]*\\:[a-zA-Z0-9\\-]*\\:[0-9]*\\*?\\.[0-9]*\\*?\\.[0-9]*\\*?");
				Matcher m = p.matcher(groupSpecURIPatternText.getText());
				boolean matched = m.matches();
				boolean found = false;

				if (matched == true) {
					for (int i = 0; i < patternList.getItemCount(); i++) {
						if (groupSpecURIPatternText.getText().equals(
								patternList.getItem(i))) {
							found = true;
						}
					}

					if (found == false) {
						patternList.add(groupSpecURIPatternText.getText());
						groupSpecURIPatternText.setText("");
					} else {
						MessageDialog.openError(container.getShell(), "Error",
								"Pattern already exists!");
					}

				} else {
					MessageDialog
							.openError(container.getShell(), "Error",
									"Pattern is not valid! It should have the format: urn:epc:...:...:*.*.*!");
				}

			}
		}
	}

	private class EcBoundarySpecRepeatPeriodValueTextVerifyListener implements
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

	private class EcBoundarySpecDurationValueTextVerifyListener implements
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

	private class EcBoundarySpecStableSetIntervalValueTextVerifyListener
			implements VerifyListener {
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

	private class IncludePatternsUpdateButtonMouseListener extends MouseAdapter {
		public void mouseDown(final MouseEvent e) {
			if (includePatternsText.getText().equals("")) {
				MessageDialog.openError(container.getShell(), "Error",
						"No pattern has been selected!");

			} else if (includePatternsText.getText() != ""
					&& includePatternsList.getItemCount() == 0) {
				MessageDialog.openError(container.getShell(), "Error",
						"No patterns available to update!");

			} else {
				Pattern p = Pattern
						.compile("urn:epc:[a-zA-Z0-9\\-]*\\:[a-zA-Z0-9\\-]*\\:[0-9]*\\*?\\.[0-9]*\\*?\\.[0-9]*\\*?");
				Matcher m = p.matcher(includePatternsText.getText());
				boolean matched = m.matches();
				boolean found = false;

				if (matched == true) {
					for (int i = 0; i < includePatternsList.getItemCount(); i++) {
						if (includePatternsText.getText().equals(
								includePatternsList.getItem(i))) {
							found = true;
						}
					}

					if (found == false) {
						includePatternsList
								.remove(includePatternsListSelectedIndex);
						includePatternsList.add(includePatternsText.getText(),
								includePatternsListSelectedIndex);
						includePatternsText.setText("");
					} else {
						MessageDialog.openError(container.getShell(), "Error",
								"Pattern already exists!");
					}

				} else {
					MessageDialog
							.openError(container.getShell(), "Error",
									"Pattern is not valid! It should have the format: urn:epc:...:...:*.*.*!");
				}

			}
		}
	}

	private class IncludePatternsDeleteButtonMouseListener extends MouseAdapter {
		public void mouseDown(final MouseEvent e) {
			if (includePatternsText.getText().equals("")) {
				MessageDialog.openError(container.getShell(), "Error",
						"No pattern has been selected!");

			} else if (includePatternsText.getText() != ""
					&& includePatternsList.getItemCount() == 0) {
				MessageDialog.openError(container.getShell(), "Error",
						"No patterns available to delete!");

			} else {
				includePatternsList.remove(includePatternsListSelectedIndex);
				includePatternsText.setText("");
			}
		}
	}

	private class ExcludePatternsUpdateButtonMouseListener extends MouseAdapter {
		public void mouseDown(final MouseEvent e) {
			if (excludePatternsText.getText().equals("")) {
				MessageDialog.openError(container.getShell(), "Error",
						"No pattern has been selected!");

			} else if (excludePatternsText.getText() != ""
					&& excludePatternsList.getItemCount() == 0) {
				MessageDialog.openError(container.getShell(), "Error",
						"No patterns available to update!");

			} else {
				Pattern p = Pattern
						.compile("urn:epc:[a-zA-Z0-9\\-]*\\:[a-zA-Z0-9\\-]*\\:[0-9]*\\*?\\.[0-9]*\\*?\\.[0-9]*\\*?");
				Matcher m = p.matcher(excludePatternsText.getText());
				boolean matched = m.matches();
				boolean found = false;

				if (matched == true) {
					for (int i = 0; i < excludePatternsList.getItemCount(); i++) {
						if (excludePatternsText.getText().equals(
								excludePatternsList.getItem(i))) {
							found = true;
						}
					}

					if (found == false) {
						excludePatternsList
								.remove(excludePatternsListSelectedIndex);
						excludePatternsList.add(excludePatternsText.getText(),
								excludePatternsListSelectedIndex);
						excludePatternsText.setText("");
					} else {
						MessageDialog.openError(container.getShell(), "Error",
								"Pattern already exists!");
					}

				} else {
					MessageDialog
							.openError(container.getShell(), "Error",
									"Pattern is not valid! It should have the format: urn:epc:...:...:*.*.*!");
				}

			}
		}
	}

	private class ExcludePatternsDeleteButtonMouseListener extends MouseAdapter {
		public void mouseDown(final MouseEvent e) {
			if (excludePatternsText.getText().equals("")) {
				MessageDialog.openError(container.getShell(), "Error",
						"No pattern has been selected!");

			} else if (excludePatternsText.getText() != ""
					&& excludePatternsList.getItemCount() == 0) {
				MessageDialog.openError(container.getShell(), "Error",
						"No patterns available to delete!");

			} else {
				excludePatternsList.remove(excludePatternsListSelectedIndex);
				excludePatternsText.setText("");
			}
		}
	}

	private class PatternUpdateButtonMouseListener extends MouseAdapter {
		public void mouseDown(final MouseEvent e) {
			if (groupSpecURIPatternText.getText().equals("")) {
				MessageDialog.openError(container.getShell(), "Error",
						"No pattern has been selected!");

			} else if (groupSpecURIPatternText.getText() != ""
					&& patternList.getItemCount() == 0) {
				MessageDialog.openError(container.getShell(), "Error",
						"No patterns available to update!");

			} else {
				Pattern p = Pattern
						.compile("urn:epc:[a-zA-Z0-9\\-]*\\:[a-zA-Z0-9\\-]*\\:[0-9]*\\*?\\.[0-9]*\\*?\\.[0-9]*\\*?");
				Matcher m = p.matcher(groupSpecURIPatternText.getText());
				boolean matched = m.matches();
				boolean found = false;

				if (matched == true) {
					for (int i = 0; i < patternList.getItemCount(); i++) {
						if (groupSpecURIPatternText.getText().equals(
								patternList.getItem(i))) {
							found = true;
						}
					}

					if (found == false) {
						patternList.remove(patternListSelectedIndex);
						patternList.add(groupSpecURIPatternText.getText(),
								patternListSelectedIndex);
						groupSpecURIPatternText.setText("");
					} else {
						MessageDialog.openError(container.getShell(), "Error",
								"Pattern already exists!");
					}

				} else {
					MessageDialog
							.openError(container.getShell(), "Error",
									"Pattern is not valid! It should have the format: urn:epc:...:...:*.*.*!");
				}

			}
		}
	}

	private class PatternDeleteButtonMouseListener extends MouseAdapter {
		public void mouseDown(final MouseEvent e) {
			if (groupSpecURIPatternText.getText().equals("")) {
				MessageDialog.openError(container.getShell(), "Error",
						"No pattern has been selected!");

			} else if (groupSpecURIPatternText.getText() != ""
					&& patternList.getItemCount() == 0) {
				MessageDialog.openError(container.getShell(), "Error",
						"No patterns available to delete!");

			} else {
				patternList.remove(patternListSelectedIndex);
				groupSpecURIPatternText.setText("");
			}
		}
	}

	private class ReportAddButtonMouseListener extends MouseAdapter {
		public void mouseDown(final MouseEvent e) {
			if (reportNameText.getText().equals("")) {
				MessageDialog.openError(container.getShell(), "Error",
						"Report name field should be filled!");

			} else if (ecReportSpecReportSetCombo.getText().equals("")) {
				MessageDialog.openError(container.getShell(), "Error",
						"Report set field should be filled!");

			} else if (includeEpcCheck.getSelection() == false) {
				MessageDialog.openError(container.getShell(), "Error",
						"Include EPC field should be filled!");

			} else {

				/* set ECReportSpec */

				// add to Reports list the report
				boolean found = false;

				for (int i = 0; i < reportsList.getItemCount(); i++) {
					if (reportNameText.getText().equals(reportsList.getItem(i))) {
						found = true;
					}
				}

				if (found == true) {
					MessageDialog.openError(container.getShell(), "Error",
							"Report name field already exists!");
					return;
				} else {
					reportsList.add(reportNameText.getText());
				}

				// set reportIfEmpty
				ecReportSpec
						.setReportIfEmpty(reportIfEmptyCheck.getSelection());

				// set reportOnlyOnChange
				ecReportSpec.setReportOnlyOnChange(reportOnlyOnChangeCheck
						.getSelection());

				// set report name
				ecReportSpec.setReportName(reportNameText.getText());

				/* set ECReportSetSpec */
				if (ecReportSpecReportSetCombo.getText().equals("CURRENT")) {
					ecReportSetSpec
							.setSet(ECReportSpecBuilder.ReportSet.CURRENT
									.toString());
					ecReportSpec.setReportSet(ecReportSetSpec);
				} else if (ecReportSpecReportSetCombo.getText().equals(
						"ADDITIONS")) {
					ecReportSetSpec
							.setSet(ECReportSpecBuilder.ReportSet.ADDITIONS
									.toString());
					ecReportSpec.setReportSet(ecReportSetSpec);
				} else if (ecReportSpecReportSetCombo.getText().equals(
						"DELETIONS")) {
					ecReportSetSpec
							.setSet(ECReportSpecBuilder.ReportSet.DELETIONS
									.toString());
					ecReportSpec.setReportSet(ecReportSetSpec);
				}
				/* end of ECReportSetSpec */

				/* set ECFilterSpec */
				for (int i = 0; i < includePatternsList.getItemCount(); i++) {
					includePatterns.getIncludePattern().add(
							includePatternsList.getItem(i));
				}

				for (int i = 0; i < excludePatternsList.getItemCount(); i++) {
					excludePatterns.getExcludePattern().add(
							excludePatternsList.getItem(i));
				}

				ecFilterSpec.setIncludePatterns(includePatterns);
				ecFilterSpec.setExcludePatterns(excludePatterns);
				/* end of ECFilterSpec */

				// set ECFilterSpec to ECReportSpec
				ecReportSpec.setFilterSpec(ecFilterSpec);

				// set ECGroupSpec to ECReportSpec
				for (int i = 0; i < patternList.getItemCount(); i++) {
					ecGroupSpec.getPattern().add(patternList.getItem(i));
				}

				ecReportSpec.setGroupSpec(ecGroupSpec);

				/* set ECReportOutputSpec */
				ecReportOutputSpec.setIncludeCount(includeCountCheck
						.getSelection());
				ecReportOutputSpec
						.setIncludeEPC(includeEpcCheck.getSelection());
				ecReportOutputSpec.setIncludeRawDecimal(includeRawDecimalCheck
						.getSelection());
				ecReportOutputSpec.setIncludeRawHex(includeRawHexCheck
						.getSelection());
				ecReportOutputSpec
						.setIncludeTag(includeTagCheck.getSelection());
				/* end of ECReportOutputSpec */

				// set ECReportOutputSpec to ECReportSpec
				ecReportSpec.setOutput(ecReportOutputSpec);
				/* end of ECReportSpec */

				// set ECReportSpec to ECSpec
				ecSpecBuilder.addECReportSpec(ecReportSpec);

				// reset all fields
				resetReportSpecFields();

				// new objects
				includePatterns = new IncludePatterns();
				excludePatterns = new ExcludePatterns();
				ecReportSpec = new ECReportSpec();
				ecReportOutputSpec = new ECReportOutputSpec();
				ecReportSetSpec = new ECReportSetSpec();
				ecFilterSpec = new ECFilterSpec();
				ecGroupSpec = new ECGroupSpec();
			}
		}
	}

	private class ReportSpecsUpdateButtonMouseListener extends MouseAdapter {
		public void mouseDown(final MouseEvent e) {

			includePatterns = new IncludePatterns();
			excludePatterns = new ExcludePatterns();
			ecReportSpec = new ECReportSpec();
			ecReportOutputSpec = new ECReportOutputSpec();
			ecReportSetSpec = new ECReportSetSpec();
			ecFilterSpec = new ECFilterSpec();
			ecGroupSpec = new ECGroupSpec();

			if (reportsListSelectedIndex == -1) {
				MessageDialog.openError(container.getShell(), "Error",
						"No report has been selected!");

			} else {
				/* set ECReportSpec */

				// set reportIfEmpty
				ecReportSpec
						.setReportIfEmpty(reportIfEmptyCheck.getSelection());

				// set reportOnlyOnChange
				ecReportSpec.setReportOnlyOnChange(reportOnlyOnChangeCheck
						.getSelection());

				// set report name
				ecReportSpec.setReportName(reportNameText.getText());

				/* set ECReportSetSpec */
				if (ecReportSpecReportSetCombo.getText().equals("CURRENT")) {
					ecReportSetSpec
							.setSet(ECReportSpecBuilder.ReportSet.CURRENT
									.toString());
					ecReportSpec.setReportSet(ecReportSetSpec);
				} else if (ecReportSpecReportSetCombo.getText().equals(
						"ADDITIONS")) {
					ecReportSetSpec
							.setSet(ECReportSpecBuilder.ReportSet.ADDITIONS
									.toString());
					ecReportSpec.setReportSet(ecReportSetSpec);
				} else if (ecReportSpecReportSetCombo.getText().equals(
						"DELETIONS")) {
					ecReportSetSpec
							.setSet(ECReportSpecBuilder.ReportSet.DELETIONS
									.toString());
					ecReportSpec.setReportSet(ecReportSetSpec);
				}
				/* end of ECReportSetSpec */

				/* set ECFilterSpec */
				for (int i = 0; i < includePatternsList.getItemCount(); i++) {
					includePatterns.getIncludePattern().add(
							includePatternsList.getItem(i));
				}

				for (int i = 0; i < excludePatternsList.getItemCount(); i++) {
					excludePatterns.getExcludePattern().add(
							excludePatternsList.getItem(i));
				}

				ecFilterSpec.setIncludePatterns(includePatterns);
				ecFilterSpec.setExcludePatterns(excludePatterns);
				/* end of ECFilterSpec */

				// set ECFilterSpec to ECReportSpec
				ecReportSpec.setFilterSpec(ecFilterSpec);

				// set ECGroupSpec to ECReportSpec
				for (int i = 0; i < patternList.getItemCount(); i++) {
					ecGroupSpec.getPattern().add(patternList.getItem(i));
				}

				ecReportSpec.setGroupSpec(ecGroupSpec);

				/* set ECReportOutputSpec */
				ecReportOutputSpec.setIncludeCount(includeCountCheck
						.getSelection());
				ecReportOutputSpec
						.setIncludeEPC(includeEpcCheck.getSelection());
				ecReportOutputSpec.setIncludeRawDecimal(includeRawDecimalCheck
						.getSelection());
				ecReportOutputSpec.setIncludeRawHex(includeRawHexCheck
						.getSelection());
				ecReportOutputSpec
						.setIncludeTag(includeTagCheck.getSelection());
				/* end of ECReportOutputSpec */

				// set ECReportOutputSpec to ECReportSpec
				ecReportSpec.setOutput(ecReportOutputSpec);
				/* end of ECReportSpec */

				// set ECReportSpec to ECSpec
				ecSpecBuilder.getECReportSpecs().remove(
						reportsListSelectedIndex);
				ecSpecBuilder.getECReportSpecs().add(reportsListSelectedIndex,
						ecReportSpec);

				reportsList.remove(reportsListSelectedIndex);

				// add to Reports list the report
				reportsList.add(ecReportSpec.getReportName(),
						reportsListSelectedIndex);

				// reset all fields
				resetReportSpecFields();
				reportsListSelectedIndex = -1;
			}

		}
	}

	private class ResetReportButtonMouseListener extends MouseAdapter {
		public void mouseDown(final MouseEvent e) {
			resetReportSpecFields();
		}
	}

	private void resetReportSpecFields() {
		reportNameText.setText("");
		ecReportSpecReportSetCombo.deselectAll();
		includePatternsList.removeAll();
		includePatternsText.setText("");
		excludePatternsList.removeAll();
		excludePatternsText.setText("");
		patternList.removeAll();
		groupSpecURIPatternText.setText("");
		includeEpcCheck.setSelection(true);
		includeTagCheck.setSelection(false);
		includeCountCheck.setSelection(false);
		includeRawHexCheck.setSelection(false);
		includeRawDecimalCheck.setSelection(false);
		reportIfEmptyCheck.setSelection(false);
		reportOnlyOnChangeCheck.setSelection(false);
		reportsList.deselectAll();
	}

	// load data when an item of the list is selected
	private void loadReportListItemData(ECSpecBuilder ecSpecBuilder,
			int reportsListSelectedIndex) {
		reportNameText.setText(ecSpecBuilder.getECReportSpecs().get(
				reportsListSelectedIndex).getReportName());

		ecReportSpecReportSetCombo.setText(ecSpecBuilder.getECReportSpecs()
				.get(reportsListSelectedIndex).getReportSet().getSet()
				.toString());

		for (int i = 0; i < ecSpecBuilder.getECReportSpecs().get(
				reportsListSelectedIndex).getFilterSpec().getIncludePatterns()
				.getIncludePattern().size(); i++) {
			includePatternsList.add(ecSpecBuilder.getECReportSpecs().get(
					reportsListSelectedIndex).getFilterSpec()
					.getIncludePatterns().getIncludePattern().get(i));
		}

		for (int i = 0; i < ecSpecBuilder.getECReportSpecs().get(
				reportsListSelectedIndex).getFilterSpec().getExcludePatterns()
				.getExcludePattern().size(); i++) {
			excludePatternsList.add(ecSpecBuilder.getECReportSpecs().get(
					reportsListSelectedIndex).getFilterSpec()
					.getExcludePatterns().getExcludePattern().get(i));
		}

		for (int i = 0; i < ecSpecBuilder.getECReportSpecs().get(
				reportsListSelectedIndex).getGroupSpec().getPattern().size(); i++) {
			patternList.add(ecSpecBuilder.getECReportSpecs().get(
					reportsListSelectedIndex).getGroupSpec().getPattern()
					.get(i));
		}

		includeEpcCheck.setSelection(ecSpecBuilder.getECReportSpecs().get(
				reportsListSelectedIndex).getOutput().isIncludeEPC());
		includeTagCheck.setSelection(ecSpecBuilder.getECReportSpecs().get(
				reportsListSelectedIndex).getOutput().isIncludeTag());
		includeCountCheck.setSelection(ecSpecBuilder.getECReportSpecs().get(
				reportsListSelectedIndex).getOutput().isIncludeCount());
		includeRawHexCheck.setSelection(ecSpecBuilder.getECReportSpecs().get(
				reportsListSelectedIndex).getOutput().isIncludeRawHex());
		includeRawDecimalCheck.setSelection(ecSpecBuilder.getECReportSpecs()
				.get(reportsListSelectedIndex).getOutput()
				.isIncludeRawDecimal());

		reportIfEmptyCheck.setSelection(ecSpecBuilder.getECReportSpecs().get(
				reportsListSelectedIndex).isReportIfEmpty());
		reportOnlyOnChangeCheck.setSelection(ecSpecBuilder.getECReportSpecs()
				.get(reportsListSelectedIndex).isReportOnlyOnChange());
	}

	private class ReportSpecsDeleteButtonMouseListener extends MouseAdapter {
		public void mouseDown(final MouseEvent e) {
			reportsList.remove(reportsListSelectedIndex);
			ecSpecBuilder.getECReportSpecs().remove(reportsListSelectedIndex);
		}
	}

	private class EcspecOpenFileButtonMouseListener extends MouseAdapter {
		public void mouseDown(final MouseEvent e) {

			ecSpecBuilder = new ECSpecBuilder();
			ecBoundarySpecBuilder = new ECBoundarySpecBuilder();
			ecReportSetSpec = new ECReportSetSpec();
			ecReportSpec = new ECReportSpec();
			ecFilterSpec = new ECFilterSpec();
			includePatterns = new IncludePatterns();
			excludePatterns = new ExcludePatterns();
			ecGroupSpec = new ECGroupSpec();
			ecReportOutputSpec = new ECReportOutputSpec();
			ecBoundarySpecExtension = new ECBoundarySpecExtension();
			startTriggerList = new StartTriggerList();
			stopTriggerList = new StopTriggerList();

			resetAllFields();

			FileDialog fd;

			fd = new FileDialog(composite.getShell(), SWT.OPEN);
			fd.setText("Open");
			fd.setFilterExtensions(new String[] { "*.xml" });
			String selection = fd.open();

			existingECSpecFileToBeSaved = selection;

			try {
				ECSpec ecSpec = new ECSpec();
				ecSpec = openEcSpecBuilder.getECSpecFromFile(selection);

				for (int i = 0; i < ecSpec.getLogicalReaders()
						.getLogicalReader().size(); i++) {
					ecspecIncludedReadersList.add(ecSpec.getLogicalReaders()
							.getLogicalReader().get(i));
				}

				for (int i = 0; i < ecspecIncludedReadersList.getItemCount(); i++) {
					for (int j = 0; j < ecspecAvailableReadersList
							.getItemCount(); j++) {
						if (ecspecIncludedReadersList.getItem(i).equals(
								ecspecAvailableReadersList.getItem(j))) {
							ecspecAvailableReadersList.remove(j);
						}
					}
				}

				if (ecSpec.getBoundarySpec().getStartTrigger() != null) {
					ecBoundarySpecStartTriggerText.setText(ecSpec
							.getBoundarySpec().getStartTrigger());
				}

				if (ecSpec.getBoundarySpec().getStopTrigger() != null) {
					ecBoundarySpecStopTriggerText.setText(ecSpec
							.getBoundarySpec().getStopTrigger());
				}

				if (ecSpec.getBoundarySpec().getExtension() != null) {
					if (ecSpec.getBoundarySpec().getExtension()
							.getStartTriggerList() != null) {
						for (int i = 0; i < ecSpec.getBoundarySpec()
								.getExtension().getStartTriggerList()
								.getStartTrigger().size(); i++) {
							ecspecStartTriggerList.add(ecSpec.getBoundarySpec()
									.getExtension().getStartTriggerList()
									.getStartTrigger().get(i));
						}
					}

					if (ecSpec.getBoundarySpec().getExtension()
							.getStopTriggerList() != null) {
						for (int i = 0; i < ecSpec.getBoundarySpec()
								.getExtension().getStopTriggerList()
								.getStopTrigger().size(); i++) {
							ecspecStopTriggerList.add(ecSpec.getBoundarySpec()
									.getExtension().getStopTriggerList()
									.getStopTrigger().get(i));
						}
					}

					if (ecSpec.getBoundarySpec().getExtension()
							.isWhenDataAvailable() != null) {
						ecspecWhenDataAvailableCheckBox.setSelection(ecSpec
								.getBoundarySpec().getExtension()
								.isWhenDataAvailable());
					}
				}

				if (ecSpec.getBoundarySpec().getRepeatPeriod() != null) {
					ecBoundarySpecRepeatPeriodValueText.setText(String
							.valueOf(ecSpec.getBoundarySpec().getRepeatPeriod()
									.getValue()));
				}

				if (ecSpec.getBoundarySpec().getDuration() != null) {
					ecBoundarySpecDurationValueText.setText(String
							.valueOf(ecSpec.getBoundarySpec().getDuration()
									.getValue()));
				}

				if (ecSpec.getBoundarySpec().getStableSetInterval() != null) {
					ecBoundarySpecStableSetIntervalValueText.setText(String
							.valueOf(ecSpec.getBoundarySpec()
									.getStableSetInterval().getValue()));
				}

				includeSpecInReportCheckBox.setSelection(ecSpec
						.isIncludeSpecInReports());

				if (ecSpec.getReportSpecs().getReportSpec() != null) {
					for (int i = 0; i < ecSpec.getReportSpecs().getReportSpec()
							.size(); i++) {
						if (ecSpec.getReportSpecs().getReportSpec().get(i)
								.getReportName() != null) {
							reportsList.add(ecSpec.getReportSpecs()
									.getReportSpec().get(i).getReportName());
						}
					}
				}

				// set object (from jaxbObj)
				for (int i = 0; i < ecSpec.getLogicalReaders()
						.getLogicalReader().size(); i++) {
					ecSpecIncludedLogicalReadersList.add(ecSpec
							.getLogicalReaders().getLogicalReader().get(i));
				}

				ecSpecBuilder
						.setLogicalReaders(ecSpecIncludedLogicalReadersList);

				ecSpecBuilder.setIncludeSpecInreports(ecSpec
						.isIncludeSpecInReports());

				if (ecSpec.getBoundarySpec().getRepeatPeriod() != null) {
					ecBoundarySpecBuilder.setRepeatPeriod(ecSpec
							.getBoundarySpec().getRepeatPeriod().getValue());
				}

				if (ecSpec.getBoundarySpec().getDuration() != null) {
					ecBoundarySpecBuilder.setDuration(ecSpec.getBoundarySpec()
							.getDuration().getValue());
				}

				if (ecSpec.getBoundarySpec().getStableSetInterval() != null) {
					ecBoundarySpecBuilder.setStableSetInterval(ecSpec
							.getBoundarySpec().getStableSetInterval()
							.getValue());
				}

				ecBoundarySpecBuilder.getECBoundarySpec().setStartTrigger(
						ecSpec.getBoundarySpec().getStartTrigger());

				// set start trigger list
				if (ecspecStartTriggerList.getItemCount() != 0) {
					for (int i = 0; i < ecspecStartTriggerList.getItemCount(); i++) {
						startTriggerList.getStartTrigger().add(
								ecspecStartTriggerList.getItem(i));
					}
					ecBoundarySpecExtension
							.setStartTriggerList(startTriggerList);
				}

				ecBoundarySpecBuilder.getECBoundarySpec().setStopTrigger(
						ecSpec.getBoundarySpec().getStopTrigger());

				// set stop trigger list
				if (ecspecStopTriggerList.getItemCount() != 0) {
					for (int i = 0; i < ecspecStopTriggerList.getItemCount(); i++) {
						stopTriggerList.getStopTrigger().add(
								ecspecStopTriggerList.getItem(i));
					}
					ecBoundarySpecExtension.setStopTriggerList(stopTriggerList);
				}

				// set when data available
				if (ecspecWhenDataAvailableCheckBox.getSelection() == true) {
					ecBoundarySpecExtension
							.setWhenDataAvailable(ecspecWhenDataAvailableCheckBox
									.getSelection());
				}

				ecSpecBuilder.setECBoundarySpec(ecBoundarySpecBuilder);
				ecSpecBuilder.getECBoundarySpec().getECBoundarySpec()
						.setExtension(ecBoundarySpecExtension);

				for (int i = 0; i < ecSpec.getReportSpecs().getReportSpec()
						.size(); i++) {
					ecReportSpec.setReportIfEmpty(ecSpec.getReportSpecs()
							.getReportSpec().get(i).isReportIfEmpty());
					ecReportSpec.setReportOnlyOnChange(ecSpec.getReportSpecs()
							.getReportSpec().get(i).isReportOnlyOnChange());
					ecReportSpec.setReportName(ecSpec.getReportSpecs()
							.getReportSpec().get(i).getReportName());

					ecReportSetSpec.setSet(ecSpec.getReportSpecs()
							.getReportSpec().get(i).getReportSet().getSet());
					ecReportSpec.setReportSet(ecReportSetSpec);

					/* set ECFilterSpec */
					if (ecSpec.getReportSpecs().getReportSpec().get(i)
							.getFilterSpec() != null) {
						for (int j = 0; j < ecSpec.getReportSpecs()
								.getReportSpec().get(i).getFilterSpec()
								.getIncludePatterns().getIncludePattern()
								.size(); j++) {

							if (ecSpec.getReportSpecs().getReportSpec().get(i)
									.getFilterSpec().getIncludePatterns()
									.getIncludePattern().get(j) != null) {
								includePatterns.getIncludePattern().add(
										ecSpec.getReportSpecs().getReportSpec()
												.get(i).getFilterSpec()
												.getIncludePatterns()
												.getIncludePattern().get(j));
							}
						}
					}

					if (ecSpec.getReportSpecs().getReportSpec().get(i)
							.getFilterSpec() != null) {
						for (int j = 0; j < ecSpec.getReportSpecs()
								.getReportSpec().get(i).getFilterSpec()
								.getExcludePatterns().getExcludePattern()
								.size(); j++) {
							if (ecSpec.getReportSpecs().getReportSpec().get(i)
									.getFilterSpec().getExcludePatterns()
									.getExcludePattern().get(j) != null) {
								excludePatterns.getExcludePattern().add(
										ecSpec.getReportSpecs().getReportSpec()
												.get(i).getFilterSpec()
												.getExcludePatterns()
												.getExcludePattern().get(j));
							}
						}
					}
					ecFilterSpec.setIncludePatterns(includePatterns);
					ecFilterSpec.setExcludePatterns(excludePatterns);
					/* end of ECFilterSpec */

					// set ECFilterSpec to ECReportSpec
					ecReportSpec.setFilterSpec(ecFilterSpec);

					// set ECGroupSpec to ECReportSpec
					if (ecSpec.getReportSpecs().getReportSpec().get(i)
							.getGroupSpec() != null) {
						for (int j = 0; j < ecSpec.getReportSpecs()
								.getReportSpec().get(i).getGroupSpec()
								.getPattern().size(); j++) {
							if (ecSpec.getReportSpecs().getReportSpec().get(i)
									.getGroupSpec().getPattern().get(j) != null) {
								ecGroupSpec.getPattern().add(
										ecSpec.getReportSpecs().getReportSpec()
												.get(i).getGroupSpec()
												.getPattern().get(j));
							}
						}
					}
					ecReportSpec.setGroupSpec(ecGroupSpec);

					/* set ECReportOutputSpec */
					ecReportOutputSpec.setIncludeCount(ecSpec.getReportSpecs()
							.getReportSpec().get(i).getOutput()
							.isIncludeCount());
					ecReportOutputSpec.setIncludeEPC(ecSpec.getReportSpecs()
							.getReportSpec().get(i).getOutput().isIncludeEPC());
					ecReportOutputSpec.setIncludeRawDecimal(ecSpec
							.getReportSpecs().getReportSpec().get(i)
							.getOutput().isIncludeRawDecimal());
					ecReportOutputSpec.setIncludeRawHex(ecSpec.getReportSpecs()
							.getReportSpec().get(i).getOutput()
							.isIncludeRawHex());
					ecReportOutputSpec.setIncludeTag(ecSpec.getReportSpecs()
							.getReportSpec().get(i).getOutput().isIncludeTag());
					/* end of ECReportOutputSpec */

					// set ECReportOutputSpec to ECReportSpec
					ecReportSpec.setOutput(ecReportOutputSpec);
					/* end of ECReportSpec */

					ecSpecBuilder.addECReportSpec(ecReportSpec);

					ecFilterSpec = new ECFilterSpec();
					ecReportSpec = new ECReportSpec();
					includePatterns = new IncludePatterns();
					excludePatterns = new ExcludePatterns();
					ecReportSetSpec = new ECReportSetSpec();
					ecGroupSpec = new ECGroupSpec();
					ecReportOutputSpec = new ECReportOutputSpec();
					ecBoundarySpecExtension = new ECBoundarySpecExtension();
					startTriggerList = new StartTriggerList();
					stopTriggerList = new StopTriggerList();

				}

				reportsListSelectedIndex = -1;
				ecspecSaveButton.setEnabled(true);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}

	public void resetAllFields() {
		for (int i = 0; i < ecspecIncludedReadersList.getItemCount(); i++) {
			ecspecAvailableReadersList
					.add(ecspecIncludedReadersList.getItem(i));
		}

		ecspecIncludedReadersList.removeAll();

		ecSpecIncludedLogicalReadersList = new ArrayList<String>();

		ecspecStartTriggerList.removeAll();
		ecspecStopTriggerList.removeAll();

		ecBoundarySpecStartTriggerText.setText("");
		ecBoundarySpecStopTriggerText.setText("");
		ecBoundarySpecRepeatPeriodValueText.setText("");
		ecBoundarySpecDurationValueText.setText("");
		ecBoundarySpecStableSetIntervalValueText.setText("");
		includeSpecInReportCheckBox.setSelection(false);

		reportNameText.setText("");
		ecReportSpecReportSetCombo.deselectAll();
		includePatternsList.removeAll();
		includePatternsText.setText("");
		excludePatternsList.removeAll();
		excludePatternsText.setText("");
		reportIfEmptyCheck.setSelection(false);
		reportOnlyOnChangeCheck.setSelection(false);
		reportsList.removeAll();
		groupSpecURIPatternText.setText("");
		patternList.removeAll();
		includeEpcCheck.setSelection(true);
		includeTagCheck.setSelection(false);
		includeCountCheck.setSelection(false);
		includeRawHexCheck.setSelection(false);
		includeRawDecimalCheck.setSelection(false);
		ecspecWhenDataAvailableCheckBox.setSelection(false);
	}

	private class EcspectreeMouseListener extends MouseAdapter {
		public void mouseDown(final MouseEvent e) {

			ecSpecBuilder = new ECSpecBuilder();
			ecBoundarySpecBuilder = new ECBoundarySpecBuilder();
			ecReportSetSpec = new ECReportSetSpec();
			ecReportSpec = new ECReportSpec();
			ecFilterSpec = new ECFilterSpec();
			includePatterns = new IncludePatterns();
			excludePatterns = new ExcludePatterns();
			ecGroupSpec = new ECGroupSpec();
			ecReportOutputSpec = new ECReportOutputSpec();
			ecBoundarySpecExtension = new ECBoundarySpecExtension();
			startTriggerList = new StartTriggerList();
			stopTriggerList = new StopTriggerList();

			resetAllFields();

			String ecspectreeSelectedItem = "";

			Point point = new Point(e.x, e.y);

			TreeItem item = ecspectree.getItem(point);

			if (item != null) {
				ecspectreeSelectedItem = item.getText();

				if (ecspectreeSelectedItem.equals("ECSpecs")) {
					return;
				}
			}

			if (ecspectreeSelectedItem.equals("")) {
				return;
			}

			try {
				ECSpec ecSpec = new ECSpec();
				ecSpec = openEcSpecBuilder
						.getECSpecFromFile(PreferenceConstants.P_ECSpecsPATH
								+ ecspectreeSelectedItem);

				existingECSpecFileToBeSaved = PreferenceConstants.P_ECSpecsPATH
						+ ecspectreeSelectedItem;

				for (int i = 0; i < ecSpec.getLogicalReaders()
						.getLogicalReader().size(); i++) {
					ecspecIncludedReadersList.add(ecSpec.getLogicalReaders()
							.getLogicalReader().get(i));
				}

				for (int i = 0; i < ecspecIncludedReadersList.getItemCount(); i++) {
					for (int j = 0; j < ecspecAvailableReadersList
							.getItemCount(); j++) {
						if (ecspecIncludedReadersList.getItem(i).equals(
								ecspecAvailableReadersList.getItem(j))) {
							ecspecAvailableReadersList.remove(j);
						}
					}
				}

				if (ecSpec.getBoundarySpec().getStartTrigger() != null) {
					ecBoundarySpecStartTriggerText.setText(ecSpec
							.getBoundarySpec().getStartTrigger());
				}

				if (ecSpec.getBoundarySpec().getStopTrigger() != null) {
					ecBoundarySpecStopTriggerText.setText(ecSpec
							.getBoundarySpec().getStopTrigger());
				}

				if (ecSpec.getBoundarySpec().getExtension() != null) {
					if (ecSpec.getBoundarySpec().getExtension()
							.getStartTriggerList() != null) {
						for (int i = 0; i < ecSpec.getBoundarySpec()
								.getExtension().getStartTriggerList()
								.getStartTrigger().size(); i++) {
							ecspecStartTriggerList.add(ecSpec.getBoundarySpec()
									.getExtension().getStartTriggerList()
									.getStartTrigger().get(i));
						}
					}

					if (ecSpec.getBoundarySpec().getExtension()
							.getStopTriggerList() != null) {
						for (int i = 0; i < ecSpec.getBoundarySpec()
								.getExtension().getStopTriggerList()
								.getStopTrigger().size(); i++) {
							ecspecStopTriggerList.add(ecSpec.getBoundarySpec()
									.getExtension().getStopTriggerList()
									.getStopTrigger().get(i));
						}
					}
					if (ecSpec.getBoundarySpec().getExtension()
							.isWhenDataAvailable() != null) {
						ecspecWhenDataAvailableCheckBox.setSelection(ecSpec
								.getBoundarySpec().getExtension()
								.isWhenDataAvailable());
					}
				}

				if (ecSpec.getBoundarySpec().getRepeatPeriod() != null) {
					ecBoundarySpecRepeatPeriodValueText.setText(String
							.valueOf(ecSpec.getBoundarySpec().getRepeatPeriod()
									.getValue()));
				}

				if (ecSpec.getBoundarySpec().getDuration() != null) {
					ecBoundarySpecDurationValueText.setText(String
							.valueOf(ecSpec.getBoundarySpec().getDuration()
									.getValue()));
				}

				if (ecSpec.getBoundarySpec().getStableSetInterval() != null) {
					ecBoundarySpecStableSetIntervalValueText.setText(String
							.valueOf(ecSpec.getBoundarySpec()
									.getStableSetInterval().getValue()));
				}

				includeSpecInReportCheckBox.setSelection(ecSpec
						.isIncludeSpecInReports());

				if (ecSpec.getReportSpecs().getReportSpec() != null) {
					for (int i = 0; i < ecSpec.getReportSpecs().getReportSpec()
							.size(); i++) {
						if (ecSpec.getReportSpecs().getReportSpec().get(i)
								.getReportName() != null) {
							reportsList.add(ecSpec.getReportSpecs()
									.getReportSpec().get(i).getReportName());
						}
					}
				}

				// set object (from jaxbObj)
				for (int i = 0; i < ecSpec.getLogicalReaders()
						.getLogicalReader().size(); i++) {
					ecSpecIncludedLogicalReadersList.add(ecSpec
							.getLogicalReaders().getLogicalReader().get(i));
				}
				ecSpecBuilder
						.setLogicalReaders(ecSpecIncludedLogicalReadersList);

				ecSpecBuilder.setIncludeSpecInreports(ecSpec
						.isIncludeSpecInReports());

				if (ecSpec.getBoundarySpec().getRepeatPeriod() != null) {
					ecBoundarySpecBuilder.setRepeatPeriod(ecSpec
							.getBoundarySpec().getRepeatPeriod().getValue());
				}

				if (ecSpec.getBoundarySpec().getDuration() != null) {
					ecBoundarySpecBuilder.setDuration(ecSpec.getBoundarySpec()
							.getDuration().getValue());
				}

				if (ecSpec.getBoundarySpec().getStableSetInterval() != null) {
					ecBoundarySpecBuilder.setStableSetInterval(ecSpec
							.getBoundarySpec().getStableSetInterval()
							.getValue());
				}

				ecBoundarySpecBuilder.getECBoundarySpec().setStartTrigger(
						ecSpec.getBoundarySpec().getStartTrigger());

				// set start trigger list
				if (ecspecStartTriggerList.getItemCount() != 0) {
					for (int i = 0; i < ecspecStartTriggerList.getItemCount(); i++) {
						startTriggerList.getStartTrigger().add(
								ecspecStartTriggerList.getItem(i));
					}
					ecBoundarySpecExtension
							.setStartTriggerList(startTriggerList);
				}

				ecBoundarySpecBuilder.getECBoundarySpec().setStopTrigger(
						ecSpec.getBoundarySpec().getStopTrigger());

				// set stop trigger list
				if (ecspecStopTriggerList.getItemCount() != 0) {
					for (int i = 0; i < ecspecStopTriggerList.getItemCount(); i++) {
						stopTriggerList.getStopTrigger().add(
								ecspecStopTriggerList.getItem(i));
					}
					ecBoundarySpecExtension.setStopTriggerList(stopTriggerList);
				}

				// set when data available
				if (ecspecWhenDataAvailableCheckBox.getSelection() == true) {
					ecBoundarySpecExtension
							.setWhenDataAvailable(ecspecWhenDataAvailableCheckBox
									.getSelection());
				}

				ecSpecBuilder.setECBoundarySpec(ecBoundarySpecBuilder);
				ecSpecBuilder.getECBoundarySpec().getECBoundarySpec()
						.setExtension(ecBoundarySpecExtension);

				for (int i = 0; i < ecSpec.getReportSpecs().getReportSpec()
						.size(); i++) {
					ecReportSpec.setReportIfEmpty(ecSpec.getReportSpecs()
							.getReportSpec().get(i).isReportIfEmpty());
					ecReportSpec.setReportOnlyOnChange(ecSpec.getReportSpecs()
							.getReportSpec().get(i).isReportOnlyOnChange());
					ecReportSpec.setReportName(ecSpec.getReportSpecs()
							.getReportSpec().get(i).getReportName());

					ecReportSetSpec.setSet(ecSpec.getReportSpecs()
							.getReportSpec().get(i).getReportSet().getSet());
					ecReportSpec.setReportSet(ecReportSetSpec);

					/* set ECFilterSpec */
					if (ecSpec.getReportSpecs().getReportSpec().get(i)
							.getFilterSpec() != null) {
						for (int j = 0; j < ecSpec.getReportSpecs()
								.getReportSpec().get(i).getFilterSpec()
								.getIncludePatterns().getIncludePattern()
								.size(); j++) {

							if (ecSpec.getReportSpecs().getReportSpec().get(i)
									.getFilterSpec().getIncludePatterns()
									.getIncludePattern().get(j) != null) {
								includePatterns.getIncludePattern().add(
										ecSpec.getReportSpecs().getReportSpec()
												.get(i).getFilterSpec()
												.getIncludePatterns()
												.getIncludePattern().get(j));
							}
						}
					}

					if (ecSpec.getReportSpecs().getReportSpec().get(i)
							.getFilterSpec() != null) {
						for (int j = 0; j < ecSpec.getReportSpecs()
								.getReportSpec().get(i).getFilterSpec()
								.getExcludePatterns().getExcludePattern()
								.size(); j++) {
							if (ecSpec.getReportSpecs().getReportSpec().get(i)
									.getFilterSpec().getExcludePatterns()
									.getExcludePattern().get(j) != null) {
								excludePatterns.getExcludePattern().add(
										ecSpec.getReportSpecs().getReportSpec()
												.get(i).getFilterSpec()
												.getExcludePatterns()
												.getExcludePattern().get(j));
							}
						}
					}

					ecFilterSpec.setIncludePatterns(includePatterns);
					ecFilterSpec.setExcludePatterns(excludePatterns);
					/* end of ECFilterSpec */

					// set ECFilterSpec to ECReportSpec
					ecReportSpec.setFilterSpec(ecFilterSpec);

					// set ECGroupSpec to ECReportSpec
					if (ecSpec.getReportSpecs().getReportSpec().get(i)
							.getGroupSpec() != null) {
						for (int j = 0; j < ecSpec.getReportSpecs()
								.getReportSpec().get(i).getGroupSpec()
								.getPattern().size(); j++) {
							if (ecSpec.getReportSpecs().getReportSpec().get(i)
									.getGroupSpec().getPattern().get(j) != null) {
								ecGroupSpec.getPattern().add(
										ecSpec.getReportSpecs().getReportSpec()
												.get(i).getGroupSpec()
												.getPattern().get(j));
							}
						}
					}
					ecReportSpec.setGroupSpec(ecGroupSpec);

					/* set ECReportOutputSpec */
					ecReportOutputSpec.setIncludeCount(ecSpec.getReportSpecs()
							.getReportSpec().get(i).getOutput()
							.isIncludeCount());
					ecReportOutputSpec.setIncludeEPC(ecSpec.getReportSpecs()
							.getReportSpec().get(i).getOutput().isIncludeEPC());
					ecReportOutputSpec.setIncludeRawDecimal(ecSpec
							.getReportSpecs().getReportSpec().get(i)
							.getOutput().isIncludeRawDecimal());
					ecReportOutputSpec.setIncludeRawHex(ecSpec.getReportSpecs()
							.getReportSpec().get(i).getOutput()
							.isIncludeRawHex());
					ecReportOutputSpec.setIncludeTag(ecSpec.getReportSpecs()
							.getReportSpec().get(i).getOutput().isIncludeTag());
					/* end of ECReportOutputSpec */

					// set ECReportOutputSpec to ECReportSpec
					ecReportSpec.setOutput(ecReportOutputSpec);
					/* end of ECReportSpec */

					ecSpecBuilder.addECReportSpec(ecReportSpec);

					ecFilterSpec = new ECFilterSpec();
					ecReportSpec = new ECReportSpec();
					includePatterns = new IncludePatterns();
					excludePatterns = new ExcludePatterns();
					ecReportSetSpec = new ECReportSetSpec();
					ecGroupSpec = new ECGroupSpec();
					ecReportOutputSpec = new ECReportOutputSpec();
					ecBoundarySpecExtension = new ECBoundarySpecExtension();
					startTriggerList = new StartTriggerList();
					stopTriggerList = new StopTriggerList();
				}

				reportsListSelectedIndex = -1;
				ecspecSaveButton.setEnabled(true);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}

	private class EcspecTreeRefreshButtonMouseListener extends MouseAdapter {
		public void mouseDown(final MouseEvent e) {
			initializeEcSpecTree();
		}
	}

	private class EcspecStartTriggerAddButtonMouseListener extends MouseAdapter {
		public void mouseDown(final MouseEvent e) {
			if (ecBoundarySpecStartTriggerText.getText().equals("")) {
				MessageDialog.openError(container.getShell(), "Error",
						"No start trigger has been entered!");

			} else {
				Pattern p = Pattern
						.compile("^http[s]?://[-a-zA-Z0-9_.:]+[-a-zA-Z0-9_:@&?=+,.!/~*'%$]*$");
				Matcher m = p.matcher(ecBoundarySpecStartTriggerText.getText());
				boolean matched = m.matches();
				boolean found = false;

				if (matched == true) {
					for (int i = 0; i < ecspecStartTriggerList.getItemCount(); i++) {
						if (ecBoundarySpecStartTriggerText.getText().equals(
								ecspecStartTriggerList.getItem(i))) {
							found = true;
						}
					}

					if (found == false) {
						ecspecStartTriggerList
								.add(ecBoundarySpecStartTriggerText.getText());
						ecBoundarySpecStartTriggerText.setText("");
					} else {
						MessageDialog.openError(container.getShell(), "Error",
								"Start trigger already exists!");
					}

				} else {
					MessageDialog
							.openError(container.getShell(), "Error",
									"Start trigger is not valid! It should start with http://!");
				}

			}
		}
	}

	private class EcspecStartTriggerListUpdateButtonMouseListener extends
			MouseAdapter {
		public void mouseDown(final MouseEvent e) {
			if (ecBoundarySpecStartTriggerText.getText().equals("")) {
				MessageDialog.openError(container.getShell(), "Error",
						"No start trigger has been selected!");

			} else if (ecBoundarySpecStartTriggerText.getText() != ""
					&& ecspecStartTriggerList.getItemCount() == 0) {
				MessageDialog.openError(container.getShell(), "Error",
						"No start triggers available to update!");

			} else {
				Pattern p = Pattern
						.compile("^http[s]?://[-a-zA-Z0-9_.:]+[-a-zA-Z0-9_:@&?=+,.!/~*'%$]*$");
				Matcher m = p.matcher(ecBoundarySpecStartTriggerText.getText());
				boolean matched = m.matches();
				boolean found = false;

				if (matched == true) {
					for (int i = 0; i < ecspecStartTriggerList.getItemCount(); i++) {
						if (ecBoundarySpecStartTriggerText.getText().equals(
								ecspecStartTriggerList.getItem(i))) {
							found = true;
						}
					}

					if (found == false) {
						ecspecStartTriggerList
								.remove(startTriggerListSelectedIndex);
						ecspecStartTriggerList.add(
								ecBoundarySpecStartTriggerText.getText(),
								startTriggerListSelectedIndex);
						ecBoundarySpecStartTriggerText.setText("");
					} else {
						MessageDialog.openError(container.getShell(), "Error",
								"Start trigger already exists!");
					}

				} else {
					MessageDialog
							.openError(container.getShell(), "Error",
									"Start trigger is not valid! It should start with http://!");
				}

			}
		}
	}

	private class EcspecStartTriggerListDeleteButtonMouseListener extends
			MouseAdapter {
		public void mouseDown(final MouseEvent e) {
			if (ecBoundarySpecStartTriggerText.getText().equals("")) {
				MessageDialog.openError(container.getShell(), "Error",
						"No start trigger has been selected!");

			} else if (ecBoundarySpecStartTriggerText.getText() != ""
					&& ecspecStartTriggerList.getItemCount() == 0) {
				MessageDialog.openError(container.getShell(), "Error",
						"No start triggers available to delete!");

			} else {
				ecspecStartTriggerList.remove(startTriggerListSelectedIndex);
				ecBoundarySpecStartTriggerText.setText("");
			}
		}
	}

	private class EcspecStopTriggerAddButtonMouseListener extends MouseAdapter {
		public void mouseDown(final MouseEvent e) {
			if (ecBoundarySpecStopTriggerText.getText().equals("")) {
				MessageDialog.openError(container.getShell(), "Error",
						"No stop trigger has been entered!");

			} else {
				Pattern p = Pattern
						.compile("^http[s]?://[-a-zA-Z0-9_.:]+[-a-zA-Z0-9_:@&?=+,.!/~*'%$]*$");
				Matcher m = p.matcher(ecBoundarySpecStopTriggerText.getText());
				boolean matched = m.matches();
				boolean found = false;

				if (matched == true) {
					for (int i = 0; i < ecspecStopTriggerList.getItemCount(); i++) {
						if (ecBoundarySpecStopTriggerText.getText().equals(
								ecspecStopTriggerList.getItem(i))) {
							found = true;
						}
					}

					if (found == false) {
						ecspecStopTriggerList.add(ecBoundarySpecStopTriggerText
								.getText());
						ecBoundarySpecStopTriggerText.setText("");

					} else {
						MessageDialog.openError(container.getShell(), "Error",
								"Stop trigger already exists!");
					}

				} else {
					MessageDialog
							.openError(container.getShell(), "Error",
									"Stop trigger is not valid! It should start with http://!");
				}

			}
		}
	}

	private class EcspecStopTriggerListUpdateButtonMouseListener extends
			MouseAdapter {
		public void mouseDown(final MouseEvent e) {
			if (ecBoundarySpecStopTriggerText.getText().equals("")) {
				MessageDialog.openError(container.getShell(), "Error",
						"No stop trigger has been selected!");

			} else if (ecBoundarySpecStopTriggerText.getText() != ""
					&& ecspecStopTriggerList.getItemCount() == 0) {
				MessageDialog.openError(container.getShell(), "Error",
						"No stop trigger available to update!");

			} else {
				Pattern p = Pattern
						.compile("^http[s]?://[-a-zA-Z0-9_.:]+[-a-zA-Z0-9_:@&?=+,.!/~*'%$]*$");
				Matcher m = p.matcher(ecBoundarySpecStopTriggerText.getText());
				boolean matched = m.matches();
				boolean found = false;

				if (matched == true) {
					for (int i = 0; i < ecspecStopTriggerList.getItemCount(); i++) {
						if (ecBoundarySpecStopTriggerText.getText().equals(
								ecspecStopTriggerList.getItem(i))) {
							found = true;
						}
					}

					if (found == false) {
						ecspecStopTriggerList
								.remove(stopTriggerListSelectedIndex);
						ecspecStopTriggerList.add(ecBoundarySpecStopTriggerText
								.getText(), stopTriggerListSelectedIndex);
						ecBoundarySpecStopTriggerText.setText("");

					} else {
						MessageDialog.openError(container.getShell(), "Error",
								"Stop trigger already exists!");
					}

				} else {
					MessageDialog
							.openError(container.getShell(), "Error",
									"Stop trigger is not valid! It should start with http://!");
				}

			}
		}
	}

	private class EcspecStopTriggerListDeleteButtonMouseListener extends
			MouseAdapter {
		public void mouseDown(final MouseEvent e) {
			if (ecBoundarySpecStopTriggerText.getText().equals("")) {
				MessageDialog.openError(container.getShell(), "Error",
						"No stop trigger has been selected!");

			} else if (ecBoundarySpecStopTriggerText.getText() != ""
					&& ecspecStopTriggerList.getItemCount() == 0) {
				MessageDialog.openError(container.getShell(), "Error",
						"No stop trigger available to delete!");

			} else {
				ecspecStopTriggerList.remove(stopTriggerListSelectedIndex);
				ecBoundarySpecStopTriggerText.setText("");
			}
		}
	}

	private class EcspecSaveButtonMouseListener extends MouseAdapter {
		public void mouseDown(final MouseEvent e) {

			if (ecspecIncludedReadersList.getItemCount() == 0) {
				MessageDialog.openError(container.getShell(), "Error",
						"Included readers list should be filled!");

			} else if (reportsList.getItemCount() == 0) {
				MessageDialog.openError(container.getShell(), "Error",
						"Reports list should be filled!");

			} else {
				// set readers
				ecSpecBuilder
						.setLogicalReaders(ecSpecIncludedLogicalReadersList);

				// set Include Spec In reports
				ecSpecBuilder
						.setIncludeSpecInreports(includeSpecInReportCheckBox
								.getSelection());

				/* set ECBoundarySpec */
				// set start trigger
				if (ecBoundarySpecStartTriggerText.getText() != "") {
					Pattern p = Pattern
							.compile("^http[s]?://[-a-zA-Z0-9_.:]+[-a-zA-Z0-9_:@&?=+,.!/~*'%$]*$");
					Matcher m = p.matcher(ecBoundarySpecStartTriggerText
							.getText());
					boolean matched = m.matches();

					if (matched == true) {
						ecBoundarySpecBuilder.getECBoundarySpec()
								.setStartTrigger(
										ecBoundarySpecStartTriggerText
												.getText());
					} else {
						MessageDialog
								.openError(container.getShell(), "Error",
										"Start trigger is not valid! It should start with http://!");

						return;
					}

				}

				// set start trigger list
				if (ecspecStartTriggerList.getItemCount() != 0) {
					for (int i = 0; i < ecspecStartTriggerList.getItemCount(); i++) {
						startTriggerList.getStartTrigger().add(
								ecspecStartTriggerList.getItem(i));
					}
					ecBoundarySpecExtension
							.setStartTriggerList(startTriggerList);
				}

				// set stop trigger
				if (ecBoundarySpecStopTriggerText.getText() != "") {
					Pattern p = Pattern
							.compile("^http[s]?://[-a-zA-Z0-9_.:]+[-a-zA-Z0-9_:@&?=+,.!/~*'%$]*$");
					Matcher m = p.matcher(ecBoundarySpecStopTriggerText
							.getText());
					boolean matched = m.matches();

					if (matched == true) {
						ecBoundarySpecBuilder
								.getECBoundarySpec()
								.setStopTrigger(
										ecBoundarySpecStopTriggerText.getText());
					} else {
						MessageDialog
								.openError(container.getShell(), "Error",
										"Stop trigger is not valid! It should start with http://!");

						return;
					}

				}

				// set stop trigger list
				if (ecspecStopTriggerList.getItemCount() != 0) {
					for (int i = 0; i < ecspecStopTriggerList.getItemCount(); i++) {
						stopTriggerList.getStopTrigger().add(
								ecspecStopTriggerList.getItem(i));
					}
					ecBoundarySpecExtension.setStopTriggerList(stopTriggerList);
				}

				// set repeat period
				if (ecBoundarySpecRepeatPeriodValueText.getText() != "") {
					ecBoundarySpecBuilder.setRepeatPeriod(Integer
							.parseInt(ecBoundarySpecRepeatPeriodValueText
									.getText()));
				} else {
					ecBoundarySpecBuilder
							.setRepeatPeriod(Integer.parseInt("0"));
				}

				// set duration
				if (ecBoundarySpecDurationValueText.getText() != "") {
					ecBoundarySpecBuilder
							.setDuration(Integer
									.parseInt(ecBoundarySpecDurationValueText
											.getText()));
				} else {
					ecBoundarySpecBuilder.setDuration(Integer.parseInt("0"));
				}

				// set stable set interval
				if (ecBoundarySpecStableSetIntervalValueText.getText() != "") {
					ecBoundarySpecBuilder.setStableSetInterval(Integer
							.parseInt(ecBoundarySpecStableSetIntervalValueText
									.getText()));
				} else {
					ecBoundarySpecBuilder.setStableSetInterval(Integer
							.parseInt("0"));
				}

				// set when data available
				if (ecspecWhenDataAvailableCheckBox.getSelection() == true) {
					ecBoundarySpecExtension
							.setWhenDataAvailable(ecspecWhenDataAvailableCheckBox
									.getSelection());
				}

				/* end of ECBoundarySpec */

				// set ECBoundarySpec to ECSpec
				ecSpecBuilder.setECBoundarySpec(ecBoundarySpecBuilder);
				ecSpecBuilder.getECBoundarySpec().getECBoundarySpec()
						.setExtension(ecBoundarySpecExtension);

				/* ************************* */

				try {
					ecSpecBuilder.generateXml(existingECSpecFileToBeSaved);

				} catch (IOException e1) { // TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}
	}

	private class EcspecAddNewReaderButtonMouseListener extends MouseAdapter {
		public void mouseDown(final MouseEvent e) {
			boolean found = false;
			org.ow2.aspirerfid.ide.ecspec.dialogs.InputDialogAddNewReader dlg = new org.ow2.aspirerfid.ide.ecspec.dialogs.InputDialogAddNewReader(
					new Shell());
			String input = dlg.open();

			if (input != null) {

				for (int i = 0; i < ecspecAvailableReadersList.getItemCount(); i++) {
					if (input.equals(ecspecAvailableReadersList.getItem(i))) {
						found = true;
					}
				}

				if (found == false) {
					ecspecAvailableReadersList.add(input);

				} else {
					MessageDialog.openError(container.getShell(), "Error",
							"Reader already exists! Please try again!");
				}

				// check if plug-in exists and if so, add dynamic reader to
				// LRSpecConfiguratorView if does not
				// exist
				if (exists("org.ow2.aspirerfid.ide.aleconfig.Activator")) {// added
					// by
					// nkef
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
