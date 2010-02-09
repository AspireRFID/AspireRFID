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


package org.ow2.aspirerfid.ide.masterdata.views;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;

import java.util.Iterator;

import java.util.Properties;
import java.util.StringTokenizer;
import java.util.TreeMap;

import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.preference.IPreferenceStore;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.events.FocusAdapter;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.VerifyEvent;
import org.eclipse.swt.events.VerifyListener;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;
import org.eclipse.ui.part.ViewPart;
import org.ow2.aspirerfid.commons.epcis.model.QueryParam;
import org.ow2.aspirerfid.ide.masterdata.Activator;


import org.ow2.aspirerfid.ide.masterdata.classes.Attribute;
import org.ow2.aspirerfid.ide.masterdata.classes.AttributesCollector;
import org.ow2.aspirerfid.ide.masterdata.classes.DataManagerModule;
import org.ow2.aspirerfid.ide.masterdata.classes.EditReadPoint;
import org.ow2.aspirerfid.ide.masterdata.classes.EpcisConstants;
import org.ow2.aspirerfid.ide.masterdata.classes.Key_Map;

import org.ow2.aspirerfid.ide.masterdata.preferences.PreferenceConstants;
import org.ow2.aspirerfid.ide.masterdata.swtdesigner.ResourceManager;
import org.ow2.aspirerfid.ide.masterdata.swtdesigner.SWTResourceManager;
import org.ow2.aspirerfid.ide.masterdata.tools.MasterDataCaptureClient;
import org.ow2.aspirerfid.ide.masterdata.tools.QueryClientGuiHelper;
//import com.swtdesigner.SWTResourceManager;


/**
 * @author Eystathios Mertikas e-mail: efme@ait.edu.gr
 * 
 */

public class MasterDataEditorView extends ViewPart {
	private Tree BizLoc_depr_tree_v2;
	private Tree BizLoc_tree_v2;
	private Table bizLoc_attrVal_table;
	private Text BizLoc_Val_text;
	private Combo BizLocAttr_combo_v2;
	private Text BizLoc_name_v2;
	private Text BizLoc_v2_text;
	private Text text_4_Value_trans;
	private Text text_3_attribute;
	private Table table_1_transactions;
	private List listOfReports;
	private Combo combo_4_TransaType;
	private Combo combo_3_Action;
	private Combo combo_2;
	private Combo combo_1_Disposition;
	private Combo combo_1Location;
	private Combo combo_2_businessStep;
	private Combo combo_1_eventType;
	private Text NameTrans;
	private Text text_EPCTrans;
	private Tree treeTransaction;
	private Text specificURI_TransType_txt;
	private Text specificURI_RPoint_txt;
	private Text specificURI_BStep_txt;
	private Text regExpression;
	private Table AttrVal_TransType_Table;
	private Table ReadPointsAttrValTable;
	private Table Bstep_attrVal_table;
	private Table dispo_attrVal_table;
	private Combo combo;
	private Combo BizReadPoints_combo;
	private Combo BizStepMod_combo;
	private Table table;
	private Combo BizDispAvail_combo;
	private Text BizTransType_LName;
	private Text BizTransType_EPCTag;
	private Text BizTransType_val;
	private Text BizTransType_attr;
	private Text BizReaderValue_txt;
	private Text BizReader_Attr_txt;
	private Text BizReaderName_txt;
	private Text BizReaderTag_txt;
	private Text BizStepName_txt;
	private Text Dispo_Lname_txt;
	private Text bizDispoTag_txt;
	private Text BizStepValueText;
	private Text BizStepAttributeText;
	private StyledText inThisAreaText;
	private Text BizStepText;
	private Text bizDispoValue_txt;
	private Text bizDispo_Attribute_txt;
	Button attributesButton_2;
	Button cancelButton;
	Button saveButton_1;
	
	public static final String ID = "org.ow2.aspirerfid.ide.masterdata.views.MasterDataEditorView"; //$NON-NLS-1$
	private QueryClientGuiHelper client = null;
	private static final String PROPERTY_FILE = "/queryclient.properties";
	
	/** Returns the preference store for this UI plug-in */
	IPreferenceStore preferences = Activator.getDefault().getPreferenceStore();

	private static final String PROPERTY_QUERY_URL = "default.url";
	
	Menu menuBar = null;
	Menu BizLoc_menuBar = null;
	Menu BizLoc_menuBar_Undeprecate = null;
	Menu BizTransMenu = null;
	Menu BizDispositionMenu = null;
	Menu genModificationMenu = null;
	Menu ECReportsDel = null;
	Menu ReadPointsModificationMenu = null;
	Menu reportMenu = null;
	Menu TransTypeModificationMenu = null;
	Menu removeAttrTableMenu = null;
	Menu removeAttrTableBStepMenu = null;
	Menu removeAttrTableReadPointMenu = null;
	Menu removeAttrTableTransTypeMenu = null;
	Menu undeprecateMenu = null;
	Menu BizTransMenu2 = null;
	Menu BizTransactionMenu = null;
	Menu BizRootTransactionMenu = null;
	Menu BizEventMenu = null;
	Menu removeAttrTableMenuTrans = null;

	Menu removeAttrTableMenuTransactions = null;
	Menu removeAttrTableMenuLocations2 = null;
	private Label label_4;
	private Group elementDataGroup;
	private Label eventTypeLabel_2;
	private Label stepLabel;
	private Label locationLabel;
	private Label dispositionLabel_2;
	private Label readerLabel;
	private Label actionLabel_2;
	private Label transTypeLabel_1;
	private Label reportsLabel_1;
	java.util.List<String> LocationList = new ArrayList<String>();

	private static final String DEFAULT_URL = "http://demo.accada.org/EPCIS-Query-v0.2.0";
	 TabFolder tabFolder=null;
	/**
	 * The query client instance. Has methods to actually execute a query.
	 */

	 Label youMustFillLabel_1;
	 boolean displayEventTypes = false;
	
	 Label youMustAddLabel;
	 TreeItem[] transTree_Selection; 
	 TreeItem[] curentBizLoc = null;
	 TreeItem currentBizLocBranch = null;
	 String current_parent = null;
	
	
	 private Button saveBizTransTypeMod_button;
	
	 public TreeMap<Key_Map,String> info;
	 TreeMap<Key_Map,String> biz_step_info;
	 TreeMap<Key_Map,String> biz_loc_info;
	 TreeMap<Key_Map,String> biz_disp_info;
	 TreeMap<Key_Map,String> biz_rp_info;
	 TreeMap<Key_Map,String> biz_tt_info;
	 TreeMap<Key_Map,String> nameToUri;
	 
	 String my_state = "idle";
	 String initialValue = "";
	 private DataManagerModule dataManager;

	 private boolean transactionBranch = false;
	 private TreeItem insertNode;

	 Label bizLoc_info_label;

	 public boolean editing = false;
	 String BizLoc_edit_name;
	 String BizLoc_edit_epc;
	 public org.ow2.aspirerfid.ide.masterdata.classes.BusinessLocation underEdit = null;
	 public org.ow2.aspirerfid.ide.masterdata.classes.UpdateDB modifications_pool = new org.ow2.aspirerfid.ide.masterdata.classes.UpdateDB();
	 public boolean isModified = false;
	 java.util.List<org.ow2.aspirerfid.ide.masterdata.classes.BusinessLocation> locationsChanged = null;
	 
	 MenuItem sub_attrs;
	 public java.util.List<String>ECReportName = new java.util.ArrayList<String>();
	 TreeItem transactionParent = null;
	 String Transaction_Modified_Id = "";
	 
	 StringBuilder bizloc_readpoints = new StringBuilder();
	 
	 private Button saveDispoModAddition_button;
	 private Group groupDispoMod;
	 Group attributevaluesGroup_1;
	 Label Reader_Error_lbl;
	 TabItem readersTabItem; 
	 boolean fromBizLoc = false;
	 private Label requiredDataAreLabel;
	 public boolean treeCreated = false;
	/**
	 * The endpoint URL for the query service.
	 */
	private String queryUrl = null;

	/**
	 * Contains the data for the result table.
	 */
	private Object[][] data = {};

	/**
	 * Create contents of the view part
	 * @param parent
	 */
	
	public void setQueryClient(final QueryClientGuiHelper newclient) {
		client = newclient;
	}
	public QueryClientGuiHelper getQueryClient()
	{
		return client;
	}
	@Override
	public void createPartControl(Composite parent) {
//		setEpcisRepositoryURL();
		this.queryUrl = preferences.getString(PreferenceConstants.P_MdeEpcisRepositoryQueryURL);
		
		
		Composite container = new Composite(parent, SWT.NONE);
		container.setLayout(new FillLayout());

	

//		final TabFolder tabFolder = new TabFolder(container, SWT.NONE);
	    tabFolder = new TabFolder(container, SWT.NONE);

		final TabItem businessLocationsTabItem = new TabItem(tabFolder, SWT.NONE);
		businessLocationsTabItem.setText("Business Dispositions");

	    final TabItem transactionsTabItem = new TabItem(tabFolder, SWT.NONE);
	    transactionsTabItem.setImage(ResourceManager.getPluginImage(Activator.getDefault(), "icons/fromAgilo/gear.png"));
	    transactionsTabItem.setText("Transactions");

	    final ScrolledComposite scrolledComposite = new ScrolledComposite(tabFolder, SWT.V_SCROLL | SWT.BORDER);
	    transactionsTabItem.setControl(scrolledComposite);

	    final Composite composite_2 = new Composite(scrolledComposite, SWT.NONE);
	    composite_2.addMouseListener(new MouseAdapter() {
	    	public void mouseDown(final MouseEvent e) {
	    		label_4.setText("");
	    	}
	    });

	    treeTransaction = new Tree(composite_2, SWT.BORDER);
	    treeTransaction.setToolTipText("Right-click to start creating processes/events!");
	    treeTransaction.addMouseListener(new MouseAdapter() {
	    	public void mouseDown(final MouseEvent e) {
	    		String type;
	    		label_4.setText("");
	    		if(treeTransaction.getItemCount() == 0)
	    			return;
	    		if(e.button == 3)
	    		{
	    			dataManager.clearTransactionSwap();
	    			//BizTransMenu2.setVisible(true);
	    			StringTokenizer elem = new StringTokenizer((String)treeTransaction.getSelection()[0].getData(),"$$");
	    			
					/*if(elem.countTokens() != 2)
					{
						label_4.setVisible(true);
						label_4.setText("");
						return;
						
					}*/
					type = elem.nextToken();
					if(type.equals("Transaction"))
					{
						BizTransactionMenu.setVisible(true);
					}
					else if(type.equals("TopLevelTransaction") || type.equals("Root"))
					{
						BizRootTransactionMenu.setVisible(true);
						
					}
					else
					{
						BizEventMenu.setVisible(true);
					}
	    			
	    		
	    		}
	    	}
	    	public void mouseDoubleClick(final MouseEvent e) 
	    	{
	    		if(!dataManager.changeTransaction())
	    			return;
	    		label_4.setVisible(false);
				StringTokenizer elem = new StringTokenizer((String)treeTransaction.getSelection()[0].getData(),"$$");
				if(elem.countTokens() != 2)
				{
					label_4.setVisible(true);
					label_4.setText("Invalid selection!Please double click on an inner Transaction");
					return;
					
				}
				else if(!elem.nextToken().equals("Transaction"))
				{
					label_4.setVisible(true);
					label_4.setText("Invalid selection!Please double click on an inner Transaction");
					return;
					
				}
				label_4.setVisible(false);
				String transactionId = elem.nextToken();
				dataManager.setVocabularyId(EpcisConstants.BUSINESS_TRANSACTION_ID);
				
				boolean result = dataManager.changeTransaction(transactionId);
				
				
				if(result)
				{
					label_4.setVisible(true);
					label_4.setText("Database have been updated sucesfully");
					
					
					TreeItem oldNode = dataManager.getOldNode();
					//if(treeTransaction.getSelection()[0].getItemCount() == 0)
					//{
						//TreeItem events = new TreeItem(treeTransaction.getSelection()[0],1);
						//events.setText("Events");
					//}
					TreeItem newEvent = new TreeItem(treeTransaction.getSelection()[0],0);//.getItem(0),0);
					 
					newEvent.setText(oldNode.getText());
					String eventId = transactionId + "," +dataManager.getOldTransactionId().split(",")[2];
					newEvent.setData("Event$$"+eventId);
					newEvent.setImage(oldNode.getImage());
					oldNode.dispose();
					newEvent.setExpanded(true);
				}
				else
				{
					label_4.setVisible(true);
					label_4.setText("Database has not been update!");
					
				}
	    		
	    		
	    	}
	    });
	    treeTransaction.setBounds(10, 47, 184, 338);
	    
	    
	    BizTransMenu2 = new Menu(composite_2.getShell(), SWT.POP_UP);
	    BizTransactionMenu = new Menu(composite_2.getShell(), SWT.POP_UP);
	    BizEventMenu = new Menu(composite_2.getShell(), SWT.POP_UP);
	    BizRootTransactionMenu = new Menu(composite_2.getShell(), SWT.POP_UP);
	    //MenuItem insertTransaction2 = new MenuItem(BizTransMenu2, SWT.CASCADE);
	    MenuItem insertTransaction2 = new MenuItem(BizRootTransactionMenu, SWT.CASCADE);
	    		insertTransaction2.setText("Insert Transaction");
	    		insertTransaction2.setImage(ResourceManager.getPluginImage(Activator.getDefault(), "icons/fromAgilo/menu_newprocess.png"));
	    		insertTransaction2.addSelectionListener(new SelectionAdapter(){
	    			public void widgetSelected(final SelectionEvent e){
	    				if(transactionBranch == true)
	    					return;
	    				transactionBranch = true;
	    				attributesButton_2.setEnabled(true);
	    				cancelButton.setEnabled(true);
	    				saveButton_1.setEnabled(true);
	    				//TreeItem newTransaction = new TreeItem(treeTransaction.getSelectionCount() == 0?treeTransaction.getItems()[0]:treeTransaction.getSelection()[0],0);
	    				StringTokenizer elem = new StringTokenizer((String)treeTransaction.getSelection()[0].getData(),"$$");
	    				if(elem.nextToken().equals("Root")){
	    					transactionBranch = false;
	    					return;
	    				}
	    					
	    				
	    				TreeItem newTransaction = new TreeItem(treeTransaction.getSelection()[0],0);
	    				insertNode = newTransaction;
	    				newTransaction.setText("<New Transaction>");
	    				newTransaction.setData("Trans Addition");
	    				newTransaction.setImage(ResourceManager.getPluginImage(Activator.getDefault(), "icons/fromAgilo/runprocess.png"));
	    				transactionParent = treeTransaction.getSelection()[0];
	    				treeTransaction.setSelection(newTransaction);
	    				elementDataGroup.setBounds(200, 40, 514, 82);
    					table_1_transactions.setVisible(true);
    					eventTypeLabel_2.setVisible(false);
    					combo_1_eventType.setVisible(false);
    					stepLabel.setVisible(false);
    					combo_2_businessStep.setVisible(false);
    					locationLabel.setVisible(false);
    					combo_1Location.setVisible(false);
    					dispositionLabel_2.setVisible(false);
    					combo_1_Disposition.setVisible(false);
    					
    					
    					readerLabel.setVisible(false);
    					combo_2.setVisible(false);
    					actionLabel_2.setVisible(false);
    					combo_3_Action.setVisible(false);
    					transTypeLabel_1.setVisible(false);
    					combo_4_TransaType.setVisible(false);
    					reportsLabel_1.setVisible(false);
    					listOfReports.setVisible(false);
    					
    					NameTrans.setText("");
    					text_EPCTrans.setText("");
    					text_EPCTrans.setEnabled(true);
    					NameTrans.setEnabled(true);
    					table_1_transactions.removeAll();
    					text_3_attribute.setText("");
    					text_4_Value_trans.setText("");
    					my_state = "Edit";

	    			}
	    });


	    		MenuItem insertOuterTransaction = new MenuItem(BizRootTransactionMenu, SWT.CASCADE);
	    		insertOuterTransaction.setText("Insert Top Level Transaction");
	    		insertOuterTransaction.setImage(ResourceManager.getPluginImage(Activator.getDefault(), "icons/fromAgilo/menu_newprocess.png"));
	    		insertOuterTransaction.addSelectionListener(new SelectionAdapter(){
	    			public void widgetSelected(final SelectionEvent e){
	    				if(transactionBranch == true)
	    					return;
	    				transactionBranch = true;
	    				attributesButton_2.setEnabled(true);
	    				cancelButton.setEnabled(true);
	    				saveButton_1.setEnabled(true);
	    				TreeItem newTransaction = new TreeItem(treeTransaction.getItems()[0],0);
	    				insertNode = newTransaction;
	    				newTransaction.setText("<New Transaction>");
	    				newTransaction.setData("Trans Addition");
	    				newTransaction.setImage(ResourceManager.getPluginImage(Activator.getDefault(), "icons/fromAgilo/package_obj.gif"));
	    				treeTransaction.setSelection(newTransaction);
	    				elementDataGroup.setBounds(200, 40, 514, 82);
    					table_1_transactions.setVisible(true);
    					eventTypeLabel_2.setVisible(false);
    					combo_1_eventType.setVisible(false);
    					stepLabel.setVisible(false);
    					combo_2_businessStep.setVisible(false);
    					locationLabel.setVisible(false);
    					combo_1Location.setVisible(false);
    					dispositionLabel_2.setVisible(false);
    					combo_1_Disposition.setVisible(false);
    					
    					
    					readerLabel.setVisible(false);
    					combo_2.setVisible(false);
    					actionLabel_2.setVisible(false);
    					combo_3_Action.setVisible(false);
    					transTypeLabel_1.setVisible(false);
    					combo_4_TransaType.setVisible(false);
    					reportsLabel_1.setVisible(false);
    					listOfReports.setVisible(false);
    					
    					NameTrans.setText("");
    					text_EPCTrans.setText("");
    					text_EPCTrans.setEnabled(true);
    					NameTrans.setEnabled(true);
    					table_1_transactions.removeAll();
    					text_3_attribute.setText("");
    					text_4_Value_trans.setText("");
    					my_state = "Edit";

	    			}
	    });
	    //MenuItem removeTransaction2 = new MenuItem(BizTransMenu2, SWT.CASCADE);
	    		MenuItem removeTransaction2 = new MenuItem(BizTransactionMenu, SWT.CASCADE);
	    		removeTransaction2.setText("Remove Transaction");
	    		removeTransaction2.setImage(ResourceManager.getPluginImage(Activator.getDefault(), "icons/fromAgilo/menu_delete_e.gif"));
	    		removeTransaction2.addSelectionListener(new SelectionAdapter(){
	    			public void widgetSelected(final SelectionEvent e){
	    				attributesButton_2.setEnabled(true);
	    				cancelButton.setEnabled(false);
    					label_4.setVisible(false);
    					String type;
	    				StringTokenizer elem = new StringTokenizer((String)treeTransaction.getSelection()[0].getData(),"$$");
	    				if(elem.countTokens() != 2)
	    				{
	    					label_4.setVisible(true);
	    					label_4.setText("Please click on a Transaction");
	    					return;
	    					
	    				}
	    				type = elem.nextToken();
	    				if(!type.equals("Transaction") && !type.equals("TopLevelTransaction"))
	    				{
	    					label_4.setVisible(true);
	    					label_4.setText("Please click on a Transaction");
	    					return;
	    					
	    				}
	    				label_4.setVisible(false);
	    				String transactionId = elem.nextToken();
	    				dataManager.setVocabularyId(EpcisConstants.BUSINESS_TRANSACTION_ID);
	    				boolean result = dataManager.deleteTransaction(transactionId);
	    				if(result)
	    				{
	    					treeTransaction.getSelection()[0].dispose();
	    					label_4.setVisible(true);
	    					label_4.setText("The transaction was sucesfully removed");
	    				}
	    				else
	    				{
	    					label_4.setVisible(true);
	    					label_4.setText("The database update has failed!");

	    				}
	    				
	    				
	    				
	    				
	    				
	    			}
	    });


	    		MenuItem removeRootTransaction2 = new MenuItem(BizRootTransactionMenu, SWT.CASCADE);
	    		removeRootTransaction2.setText("Remove Transaction");
	    		removeRootTransaction2.setImage(ResourceManager.getPluginImage(Activator.getDefault(), "icons/fromAgilo/menu_delete_e.gif"));
	    		removeRootTransaction2.addSelectionListener(new SelectionAdapter(){
	    			public void widgetSelected(final SelectionEvent e){
	    				attributesButton_2.setEnabled(true);
	    				cancelButton.setEnabled(false);
    					label_4.setVisible(false);
    					String type;
	    				StringTokenizer elem = new StringTokenizer((String)treeTransaction.getSelection()[0].getData(),"$$");
	    				if(elem.countTokens() != 2)
	    				{
	    					label_4.setVisible(true);
	    					label_4.setText("Please click on a Transaction");
	    					return;
	    					
	    				}
	    				type = elem.nextToken();
	    				if(!type.equals("Transaction") && !type.equals("TopLevelTransaction"))
	    				{
	    					label_4.setVisible(true);
	    					label_4.setText("Please click on a Transaction");
	    					return;
	    					
	    				}
	    				label_4.setVisible(false);
	    				String transactionId = elem.nextToken();
	    				dataManager.setVocabularyId(EpcisConstants.BUSINESS_TRANSACTION_ID);
	    				boolean result = dataManager.deleteTransaction(transactionId);
	    				if(result)
	    				{
	    					treeTransaction.getSelection()[0].dispose();
	    					label_4.setVisible(true);
	    					label_4.setText("The transaction was sucesfully removed");
	    				}
	    				else
	    				{
	    					label_4.setVisible(true);
	    					label_4.setText("The database update has failed!");

	    				}
	    				
	    				
	    				
	    				
	    				
	    			}
	    });

	    		MenuItem transaction_modification = new MenuItem(BizTransactionMenu,SWT.CASCADE);
	    		transaction_modification.setText("Display/Modify Transaction");
	    		transaction_modification.setImage(ResourceManager.getPluginImage(Activator.getDefault(), "icons/img/s_reload.png"));
	    		transaction_modification.addSelectionListener(new SelectionAdapter(){
	    			public void widgetSelected(final SelectionEvent e){
	    				
	    				cancelButton.setEnabled(true);
	    				saveButton_1.setEnabled(true);
	    				String uri;
	    				if(treeTransaction.getSelectionCount() == 0)
	    					return;
	    				StringTokenizer elem = new StringTokenizer((String)treeTransaction.getSelection()[0].getData(),"$$");
	    				if(elem == null || elem.countTokens() != 2)
	    				{
	    					label_4.setVisible(true);
	    					label_4.setText("Please click on a transaction or an event");
	    					
	    				}
	    				dataManager.setVocabularyId(EpcisConstants.BUSINESS_TRANSACTION_ID);
	    				String type = elem.nextToken();
	    				if(type.equals("Transaction") || type.equals("TopLevelTransaction"))
	    				{
	    					//This is a transaction
	    					elementDataGroup.setBounds(200, 40, 514, 82);
	    					text_3_attribute.setEnabled(true);
	    	    			text_3_attribute.setText("");
	    	    			text_4_Value_trans.setEnabled(true);
	    	    			text_4_Value_trans.setText("");
	    					table_1_transactions.setVisible(true);
	    					eventTypeLabel_2.setVisible(false);
	    					combo_1_eventType.setVisible(false);
	    					stepLabel.setVisible(false);
	    					combo_2_businessStep.setVisible(false);
	    					uri = elem.nextToken();
	    					java.util.List<Attribute> attributes = dataManager.getAttributes(uri);
	    					dataManager.excludeAttributes(attributes, "Children");
	    					if(uri.split(",").length == 2)
	    					{
	    						/*elem = new StringTokenizer((String)treeTransaction.getSelection()[0].getParentItem().getData(),"$$");
	    						if(!elem.nextToken().equals("TopLevelTransaction"))
	    							return;*/
	    						
	    						text_EPCTrans.setText(uri.split(",")[1]);
	    						Transaction_Modified_Id = uri;
	    					}
	    					else if(uri.split(",").length == 1)
	    					{
	    						text_EPCTrans.setText(uri);
	    						Transaction_Modified_Id ="";
	    						
	    					}
	    					
	    					text_EPCTrans.setEnabled(false);
	    					NameTrans.setText(treeTransaction.getSelection()[0].getText());
	    					NameTrans.setEnabled(false);
	    					attributesButton_2.setEnabled(true);
	    					int total = attributes.size();
	    					if( total > 0)
	    					{
	    						showAttributeTable2(table_1_transactions,attributes);
	    					
	    					}
	    					my_state = "Edit";
	    				}
	    				
	    				
	    				
	    			}
	    		});
	    		
	    		MenuItem Roottransaction_modification = new MenuItem(BizRootTransactionMenu,SWT.CASCADE);
	    		Roottransaction_modification.setText("Display/Modify Root Transaction");
	    		Roottransaction_modification.setImage(ResourceManager.getPluginImage(Activator.getDefault(), "icons/img/s_reload.png"));
	    		Roottransaction_modification.addSelectionListener(new SelectionAdapter(){
	    			public void widgetSelected(final SelectionEvent e){
	    				
	    				cancelButton.setEnabled(true);
	    				saveButton_1.setEnabled(true);
	    				String uri;
	    				if(treeTransaction.getSelectionCount() == 0)
	    					return;
	    				StringTokenizer elem = new StringTokenizer((String)treeTransaction.getSelection()[0].getData(),"$$");
	    				if(elem == null || elem.countTokens() != 2)
	    				{
	    					label_4.setVisible(true);
	    					label_4.setText("Please click on a transaction or an event");
	    					
	    				}
	    				dataManager.setVocabularyId(EpcisConstants.BUSINESS_TRANSACTION_ID);
	    				String type = elem.nextToken();
	    				if(type.equals("Transaction") || type.equals("TopLevelTransaction"))
	    				{
	    					//This is a transaction
	    					elementDataGroup.setBounds(200, 40, 514, 82);
	    					text_3_attribute.setEnabled(true);
	    	    			text_3_attribute.setText("");
	    	    			text_4_Value_trans.setEnabled(true);
	    	    			text_4_Value_trans.setText("");
	    					table_1_transactions.setVisible(true);
	    					eventTypeLabel_2.setVisible(false);
	    					combo_1_eventType.setVisible(false);
	    					stepLabel.setVisible(false);
	    					combo_2_businessStep.setVisible(false);
	    					uri = elem.nextToken();
	    					java.util.List<Attribute> attributes = dataManager.getAttributes(uri);
	    					dataManager.excludeAttributes(attributes, "Children");
	    					if(uri.split(",").length == 2)
	    					{
	    						/*elem = new StringTokenizer((String)treeTransaction.getSelection()[0].getParentItem().getData(),"$$");
	    						if(!elem.nextToken().equals("TopLevelTransaction"))
	    							return;*/
	    						
	    						text_EPCTrans.setText(uri.split(",")[1]);
	    						Transaction_Modified_Id = uri;
	    					}
	    					else if(uri.split(",").length == 1)
	    					{
	    						text_EPCTrans.setText(uri);
	    						Transaction_Modified_Id ="";
	    						
	    					}
	    					
	    					text_EPCTrans.setEnabled(false);
	    					NameTrans.setText(treeTransaction.getSelection()[0].getText());
	    					NameTrans.setEnabled(false);
	    					attributesButton_2.setEnabled(true);
	    					int total = attributes.size();
	    					if( total > 0)
	    					{
	    						showAttributeTable2(table_1_transactions,attributes);
	    					
	    					}
	    					my_state = "Edit";
	    				}
	    				
	    				
	    				
	    			}
	    		});	
	    		
	    	MenuItem event_modification = new MenuItem(BizEventMenu,SWT.CASCADE);
	    	event_modification.setText("Display/Modify Event");
	    	event_modification.setImage(ResourceManager.getPluginImage(Activator.getDefault(), "icons/img/s_reload.png"));
	    	event_modification.addSelectionListener(new SelectionAdapter(){
    			public void widgetSelected(final SelectionEvent e){
    				String uri;
    				cancelButton.setEnabled(true);
    				saveButton_1.setEnabled(true);
    				if(treeTransaction.getSelectionCount() == 0)
    					return;
    				StringTokenizer elem = new StringTokenizer((String)treeTransaction.getSelection()[0].getData(),"$$");
    				if(elem == null || elem.countTokens() != 2)
    				{
    					label_4.setVisible(true);
    					label_4.setText("Please click on a transaction or an event");
    					
    				}
    				if(!elem.nextToken().equals("Event"))
    					return;
    				dataManager.setVocabularyId(EpcisConstants.BUSINESS_TRANSACTION_ID);
    				String eventId = elem.nextToken();
					//This is an event
					table_1_transactions.setVisible(false);
					elementDataGroup.setBounds(200, 40, 514, 335);
					
					eventTypeLabel_2.setVisible(true);
					combo_1_eventType.setVisible(true);
					stepLabel.setVisible(true);
					combo_2_businessStep.setVisible(true);
					locationLabel.setVisible(true);
					combo_1Location.setVisible(true);
					dispositionLabel_2.setVisible(true);
					combo_1_Disposition.setVisible(true);
				
					
					readerLabel.setVisible(true);
					combo_2.setVisible(true);
					actionLabel_2.setVisible(true);
					combo_3_Action.setVisible(true);
					transTypeLabel_1.setVisible(true);
					combo_4_TransaType.setVisible(true);
					reportsLabel_1.setVisible(true);
					listOfReports.setVisible(true);
					listOfReports.removeAll();
					
					text_EPCTrans.setText("");
					NameTrans.setText("");
					text_EPCTrans.setEnabled(true);
					NameTrans.setEnabled(true);
					
					dataManager.setVocabularyId(EpcisConstants.BUSINESS_TRANSACTION_ID);
					String report = null;
					if(eventId.split(",").length == 3)
						text_EPCTrans.setText(eventId.split(",")[2]);
					else
						text_EPCTrans.setText(eventId);
					
					dataManager.getEventInfo(eventId, NameTrans, combo_1_eventType, combo_2_businessStep, combo_1Location, combo_1_Disposition, combo_2, combo_3_Action, combo_4_TransaType, listOfReports);
					attributesButton_2.setEnabled(false);
					my_state = "Edit";
					
    			
    			}
	    	});
    		
	    		
	    	MenuItem modEvent2 = new MenuItem(BizTransMenu2, SWT.CASCADE);
	    		modEvent2.setText("Modify Transaction/Event");
	    		modEvent2.setImage(ResourceManager.getPluginImage(Activator.getDefault(), "icons/img/s_reload.png"));
	    		modEvent2.addSelectionListener(new SelectionAdapter(){
	    			public void widgetSelected(final SelectionEvent e){
	    				String uri;
	    				if(treeTransaction.getSelectionCount() == 0)
	    					return;
	    				StringTokenizer elem = new StringTokenizer((String)treeTransaction.getSelection()[0].getData(),"$$");
	    				if(elem == null || elem.countTokens() != 2)
	    				{
	    					label_4.setVisible(true);
	    					label_4.setText("Please click on a transaction or an event");
	    					
	    				}
	    				dataManager.setVocabularyId(EpcisConstants.BUSINESS_TRANSACTION_ID);
	    				if(elem.nextToken().equals("Transaction"))
	    				{
	    					//This is a transaction
	    					elementDataGroup.setBounds(200, 40, 514, 82);
	    					text_3_attribute.setEnabled(true);
	    	    			text_3_attribute.setText("");
	    	    			text_4_Value_trans.setEnabled(true);
	    	    			text_4_Value_trans.setText("");
	    					table_1_transactions.setVisible(true);
	    					eventTypeLabel_2.setVisible(false);
	    					combo_1_eventType.setVisible(false);
	    					stepLabel.setVisible(false);
	    					combo_2_businessStep.setVisible(false);
	    					uri = elem.nextToken();
	    					java.util.List<Attribute> attributes = dataManager.getAttributes(uri);
	    					dataManager.excludeAttributes(attributes, "Children");
	    					text_EPCTrans.setText(uri);
	    					text_EPCTrans.setEnabled(false);
	    					NameTrans.setText(treeTransaction.getSelection()[0].getText());
	    					NameTrans.setEnabled(false);
	    					int total = attributes.size();
	    					if( total > 0)
	    					{
	    						showAttributeTable2(table_1_transactions,attributes);
	    					
	    					}
	    					
	    				}
	    				else
	    				{
	    					String eventId = elem.nextToken();
	    					//This is an event
	    					table_1_transactions.setVisible(false);
	    					elementDataGroup.setBounds(200, 40, 514, 335);
	    					
	    					eventTypeLabel_2.setVisible(true);
	    					combo_1_eventType.setVisible(true);
	    					stepLabel.setVisible(true);
	    					combo_2_businessStep.setVisible(true);
	    					locationLabel.setVisible(true);
	    					combo_1Location.setVisible(true);
	    					dispositionLabel_2.setVisible(true);
	    					combo_1_Disposition.setVisible(true);
	    				
	    					
	    					readerLabel.setVisible(true);
	    					combo_2.setVisible(true);
	    					actionLabel_2.setVisible(true);
	    					combo_3_Action.setVisible(true);
	    					transTypeLabel_1.setVisible(true);
	    					combo_4_TransaType.setVisible(true);
	    					reportsLabel_1.setVisible(true);
	    					listOfReports.setVisible(true);
	    					listOfReports.removeAll();
	    					
	    					text_EPCTrans.setText("");
	    					NameTrans.setText("");
	    					text_EPCTrans.setEnabled(true);
	    					NameTrans.setEnabled(true);
	    					
	    					dataManager.setVocabularyId(EpcisConstants.BUSINESS_TRANSACTION_ID);
	    					String report = null;
	    					text_EPCTrans.setText(eventId);
	    					dataManager.getEventInfo(eventId, NameTrans, combo_1_eventType, combo_2_businessStep, combo_1Location, combo_1_Disposition, combo_2, combo_3_Action, combo_4_TransaType, listOfReports);
	    					

	    				}
	    				
	    			}
	    });


	    //MenuItem remEvent2 = new MenuItem(BizTransMenu2, SWT.CASCADE);
	    		MenuItem remEvent2 = new MenuItem(BizEventMenu, SWT.CASCADE);
	    		remEvent2.setText("Remove Event");
	    		remEvent2.setImage(ResourceManager.getPluginImage(Activator.getDefault(), "icons/fromAgilo/menu_delete_e.gif"));
	    		remEvent2.addSelectionListener(new SelectionAdapter(){
	    			public void widgetSelected(final SelectionEvent e){
	    				
	    				label_4.setVisible(false);
	    				cancelButton.setEnabled(false);
	    				StringTokenizer elem = new StringTokenizer((String)treeTransaction.getSelection()[0].getData(),"$$");
	    				if(elem.countTokens() != 2)
	    				{
	    					label_4.setVisible(true);
	    					label_4.setText("Please click on an event");
	    					return;
	    					
	    				}
	    				else if(!elem.nextToken().equals("Event"))
	    				{
	    					label_4.setVisible(true);
	    					label_4.setText("Please click on an event");
	    					return;
	    					
	    				}
	    				label_4.setVisible(false);
	    				String eventId = elem.nextToken();
	    				StringTokenizer trans = new StringTokenizer((String)treeTransaction.getSelection()[0].getParentItem().getParentItem().getData(),"$$");
	    				if(trans.countTokens() != 2)
	    					return;
	    				trans.nextToken();//Transaction $$
	    				String transactionId = trans.nextToken();
	    				dataManager.setVocabularyId(EpcisConstants.BUSINESS_TRANSACTION_ID);
	    				boolean result = dataManager.deleteEvent(eventId, transactionId);
	    				if(result)
	    				{
	    					label_4.setVisible(true);
	    					label_4.setText("Event has been sucesfully removed");
	    				}
	    				else
	    				{
	    					label_4.setVisible(true);
	    					label_4.setText("Event removal failed!");
	    					
	    				}
	    				treeTransaction.getSelection()[0].dispose();
	    				
	    				
	    			}
	    		});
	    		
	    		
	    		// MenuItem changeTransaction = new MenuItem(BizTransMenu2, SWT.CASCADE);
	    		MenuItem changeTransaction = new MenuItem(BizEventMenu, SWT.CASCADE);
	    		 changeTransaction.setText("Change Transaction");
	    		 changeTransaction.setImage(ResourceManager.getPluginImage(Activator.getDefault(), "icons/fromAgilo/menu_gridsnapnowcentered.png"));
	    		 changeTransaction.addSelectionListener(new SelectionAdapter(){
		    			public void widgetSelected(final SelectionEvent e){
		    				
		    				label_4.setVisible(false);
		    				cancelButton.setEnabled(false);
		    				saveButton_1.setEnabled(false);
		    				StringTokenizer elem = new StringTokenizer((String)treeTransaction.getSelection()[0].getData(),"$$");
		    				if(elem.countTokens() != 2)
		    				{
		    					label_4.setVisible(true);
		    					label_4.setText("Please click on an event");
		    					return;
		    					
		    				}
		    				else if(!elem.nextToken().equals("Event"))
		    				{
		    					label_4.setVisible(true);
		    					label_4.setText("Please click on an event");
		    					return;
		    					
		    				}
		    				label_4.setVisible(false);
		    				String transactionId = elem.nextToken();
		    				//StringTokenizer trans = new StringTokenizer((String)treeTransaction.getSelection()[0].getParentItem().getParentItem().getData(),"$$");
		    				//if(trans.countTokens() != 2)
		    					//return;
		    				//trans.nextToken();//Transaction $$
		    				//String transactionId = trans.nextToken();
		    				if(transactionId.split(",").length!=3)
		    					return;
		    				String eventId = transactionId.split(",")[2];
		    				
		    				 dataManager.storeTransactionSwapInfo(transactionId, eventId,treeTransaction.getSelection()[0]);
		    				label_4.setVisible(true);
		    				label_4.setText("Double click on new parent");
		    				
		    				
		    			}
		    		});


	    //MenuItem insEvent2 = new MenuItem(BizTransMenu2, SWT.CASCADE);
	    		 MenuItem insEvent2 = new MenuItem(BizTransactionMenu, SWT.CASCADE);
	    		insEvent2.setText("Insert Event");
	    		insEvent2.setImage(ResourceManager.getPluginImage(Activator.getDefault(), "icons/img/b_props.png"));
	    		insEvent2.addSelectionListener(new SelectionAdapter(){
	    			public void widgetSelected(final SelectionEvent e){
	    				label_4.setVisible(false);
	    				cancelButton.setEnabled(true);
	    				saveButton_1.setEnabled(true);
	    				if(treeTransaction.getSelectionCount() == 0)
	    				{
	    					label_4.setVisible(true);
	    					label_4.setText("Please click on a transaction");
	    					return;
	    				}
	    				StringTokenizer elem = new StringTokenizer((String)treeTransaction.getSelection()[0].getData(),"$$");
	    				if(elem.countTokens() != 2)
	    				{
	    					label_4.setVisible(true);
	    					label_4.setText("Please click on a transaction");
	    					return;
	    				}
	    				dataManager.clearInternal();
	    				elem.nextToken();
	    			
	    				String uri = elem.nextToken();
	    				
	    				if(uri.split(",").length != 2)//!elem.nextToken().equals("Transaction"))
	    				{
	    					label_4.setVisible(true);
	    					label_4.setText("Please click on a transaction");
	    					return;
	    				}
	    				/*if(treeTransaction.getSelection()[0].getItemCount() == 0)
	    				{
	    					TreeItem ev = new TreeItem(treeTransaction.getSelection()[0],1);
	    					ev.setText("Events");
	    				}*/
	    				if(transactionBranch)
	    					return;
	    				transactionBranch = true;
	    				TreeItem rootevent = treeTransaction.getSelection()[0];//.getItem(0);
	    				TreeItem newEvent = new TreeItem(rootevent,0);
	    				newEvent.setText("<New Event>");
	    				newEvent.setData("Addition");
	    				newEvent.setImage(ResourceManager.getPluginImage(Activator.getDefault(), "icons/fromAgilo/finalNode.gif"));
	    				treeTransaction.setSelection(newEvent);
	    				insertNode = newEvent;
	    				table_1_transactions.setVisible(false);
    					elementDataGroup.setBounds(200, 40, 514, 335);
    					eventTypeLabel_2.setVisible(true);
    					combo_1_eventType.setVisible(true);
    					combo_1_eventType.removeAll();
    					stepLabel.setVisible(true);
    					combo_2_businessStep.setVisible(true);
    					combo_2_businessStep.removeAll();
    					locationLabel.setVisible(true);
    					combo_1Location.setVisible(true);
    					combo_1Location.removeAll();
    					dispositionLabel_2.setVisible(true);
    					combo_1_Disposition.setVisible(true);
    					combo_1_Disposition.removeAll();
    		
    					
    					
    					readerLabel.setVisible(true);
    					combo_2.setVisible(true);
    					combo_2.removeAll();
    					actionLabel_2.setVisible(true);
    					combo_3_Action.setVisible(true);
    					combo_3_Action.removeAll();
    					transTypeLabel_1.setVisible(true);
    					combo_4_TransaType.setVisible(true);
    					combo_4_TransaType.removeAll();
    					reportsLabel_1.setVisible(true);
    					listOfReports.setVisible(true);
    					listOfReports.removeAll();
    					
    					
    					text_EPCTrans.setText("");
    					NameTrans.setText("");
    					text_EPCTrans.setEnabled(true);
    					NameTrans.setEnabled(true);
    					my_state = "Edit";


	    				
	    				
	    			}
	    				
	    		});
	    
	    
	    

	    final Label label_3 = new Label(composite_2, SWT.NONE);
	    label_3.setAlignment(SWT.CENTER);
	    label_3.setImage(ResourceManager.getPluginImage(Activator.getDefault(), "icons/fromAgilo/runprocess.png"));
	    label_3.setBounds(10, 10, 49, 31);

	    final Label processesLabel_1 = new Label(composite_2, SWT.NONE);
	    processesLabel_1.setFont(SWTResourceManager.getFont("Palatino Linotype", 14, SWT.BOLD));
	    processesLabel_1.setText("Processes");
	    processesLabel_1.setBounds(65, 12, 115, 29);

	    elementDataGroup = new Group(composite_2, SWT.NONE);
	    elementDataGroup.setText("Element Data (URI/Alias)");
	    elementDataGroup.setBounds(200, 40, 514, 82);

	    final Label uriLabel_trans = new Label(elementDataGroup, SWT.NONE);
	    uriLabel_trans.setText("Uri:");
	    uriLabel_trans.setBounds(10, 29, 28, 15);

	    text_EPCTrans = new Text(elementDataGroup, SWT.BORDER);
	    text_EPCTrans.addVerifyListener(new VerifyListener() {
	    	public void verifyText(final VerifyEvent e) {
	    		if(!my_state.equals("Edit"))
	    			return;
	    		if (e.end - e.start == 0) 
	    		{
	    			if(e.text.trim().equals(""))
	    			{
	    				label_4.setVisible(true);
	    				label_4.setText("All the fields must be filled");
	    				e.doit = false;
	    				return;
	    			}
	    			if(e.text.equals(","))
	    			{
	    				e.text = ":";
	    			}
	    			
	    		}

	    	}
	    });
	    text_EPCTrans.setBounds(80, 25, 130, 25);

	    final Label nameLabel_trans = new Label(elementDataGroup, SWT.NONE);
	    nameLabel_trans.setText("Name:");
	    nameLabel_trans.setBounds(261, 29, 35, 15);

	    NameTrans = new Text(elementDataGroup, SWT.BORDER);
	    NameTrans.setBounds(330, 25, 142, 25);

	    eventTypeLabel_2 = new Label(elementDataGroup, SWT.NONE);
	    
	    eventTypeLabel_2.setVisible(false);
	    eventTypeLabel_2.setText("Type:");
	    eventTypeLabel_2.setBounds(10, 81, 28, 15);

	    combo_1_eventType = new Combo(elementDataGroup, SWT.NONE);
	    combo_1_eventType.addModifyListener(new ModifyListener() {
	    	public void modifyText(final ModifyEvent e) {
	    		if(!combo_1_eventType.getText().equals(initialValue))
	    		{
	    			my_state = "Edit";
	    			label_4.setText("");
	    		}
	    		else
	    			my_state = "Idle";
	    			
	    	}
	    });
	    combo_1_eventType.addFocusListener(new FocusAdapter() {
	    	public void focusGained(final FocusEvent e) {
	    		initialValue = combo_1_eventType.getText();
	    	}
	    });
	    combo_1_eventType.addMouseListener(new MouseAdapter() {
	    	public void mouseDown(final MouseEvent e) {

	    		if(combo_1_eventType.getItemCount() > 0)
	    			return;
	    	
	    		combo_1_eventType.add("ObjectEvent");
	    		combo_1_eventType.add("AggregationEvent");
	    		combo_1_eventType.add("TransactionEvent");
	    		combo_1_eventType.add("QuantityEvent");
	    		
			
	    	}
	    });
	    combo_1_eventType.setVisible(false);
	    combo_1_eventType.setBounds(80, 77, 130, 23);

	    stepLabel = new Label(elementDataGroup, SWT.NONE);
	    stepLabel.setVisible(false);
	    stepLabel.setText("Step:");
	    stepLabel.setBounds(261, 81, 28, 15);

	    combo_2_businessStep = new Combo(elementDataGroup, SWT.NONE);
	    combo_2_businessStep.addModifyListener(new ModifyListener() {
	    	public void modifyText(final ModifyEvent e) {
	    		if(!combo_2_businessStep.getText().equals(initialValue))
	    		{
	    			my_state = "Edit";
	    			label_4.setText("");
	    		}
	    		else
	    			my_state = "Idle";
	    	}
	    });
	    combo_2_businessStep.addFocusListener(new FocusAdapter() {
	    	public void focusGained(final FocusEvent e) {
	    		initialValue = combo_2_businessStep.getText();
	    	}
	    });
	    combo_2_businessStep.addMouseListener(new MouseAdapter() {
	    	public void mouseDown(final MouseEvent e) {
	    		if(combo_2_businessStep.getItemCount() > 0)
					return;
				dataManager.setVocabularyId(EpcisConstants.BUSINESS_STEP_ID);
				java.util.List<String> names = dataManager.getElementNames("",null);
				if(names.size() == 0)
				{
					combo_2_businessStep.setText("No information available!");
					
					return;
				}
				populateCombo_Names(combo_2_businessStep,names);
	    	}
	    });
	    combo_2_businessStep.setVisible(false);
	    combo_2_businessStep.setBounds(330, 77, 142, 23);

	    locationLabel = new Label(elementDataGroup, SWT.NONE);
	    locationLabel.setVisible(false);
	    locationLabel.setText("Location:");
	    locationLabel.setBounds(10, 127, 49, 15);

	    combo_1Location = new Combo(elementDataGroup, SWT.NONE);
	    combo_1Location.addVerifyListener(new VerifyListener() {
	    	public void verifyText(final VerifyEvent e) {
	    		if(!combo_1Location.getText().equals(initialValue))
	    		{
	    			my_state = "Edit";
	    			label_4.setText("");
	    		}
	    		else
	    			my_state = "Idle";
	    	}
	    });
	    combo_1Location.addFocusListener(new FocusAdapter() {
	    	public void focusGained(final FocusEvent e) {
	    		initialValue = combo_1Location.getText();
	    	}
	    });
	    combo_1Location.addMouseListener(new MouseAdapter() {
	    	public void mouseDown(final MouseEvent e) {
	    		if(combo_1Location.getItemCount() > 0)
					return;
				dataManager.setVocabularyId(EpcisConstants.BUSINESS_LOCATION_ID);
				java.util.List<String> names = dataManager.getElementNames("",null);
				if(names.size() == 0)
				{
					combo_1Location.setText("No information available!");
					
					return;
				}
				populateCombo_Names(combo_1Location,names);
	    	}
	    });
	    combo_1Location.setVisible(false);
	    combo_1Location.setBounds(80, 124, 130, 23);

	    dispositionLabel_2 = new Label(elementDataGroup, SWT.NONE);
	    dispositionLabel_2.setVisible(false);
	    dispositionLabel_2.setText("Disposition:");
	    dispositionLabel_2.setBounds(261, 127, 62, 15);

	    combo_1_Disposition = new Combo(elementDataGroup, SWT.NONE);
	    combo_1_Disposition.addVerifyListener(new VerifyListener() {
	    	public void verifyText(final VerifyEvent e) {
	    		if(!combo_1_Disposition.getText().equals(initialValue))
	    		{
	    			my_state = "Edit";
	    			label_4.setText("");
	    		}
	    		else
	    			my_state = "Idle";
	    	}
	    });
	    combo_1_Disposition.addFocusListener(new FocusAdapter() {
	    	public void focusGained(final FocusEvent e) {
	    		initialValue = combo_1_Disposition.getText();
	    	}
	    });
	    combo_1_Disposition.addMouseListener(new MouseAdapter() {
	    	public void mouseDown(final MouseEvent e) {
	    		if(combo_1_Disposition.getItemCount() > 0)
					return;
				dataManager.setVocabularyId(EpcisConstants.DISPOSITION_ID);
				java.util.List<String> names = dataManager.getElementNames("",null);
				if(names.size() == 0)
				{
					combo_1_Disposition.setText("No information available!");
					
					return;
				}
				populateCombo_Names(combo_1_Disposition,names);
	    	}
	    });
	    combo_1_Disposition.setVisible(false);
	    combo_1_Disposition.setBounds(330, 123, 142, 23);

	    readerLabel = new Label(elementDataGroup, SWT.NONE);
	    readerLabel.setVisible(false);
	    readerLabel.setText("Reader:");
	    readerLabel.setBounds(261, 170, 62, 15);

	    combo_2 = new Combo(elementDataGroup, SWT.NONE);
	    combo_2.addModifyListener(new ModifyListener() {
	    	public void modifyText(final ModifyEvent e) {
	    		if(!combo_2.getText().equals(initialValue))
	    		{
	    			my_state = "Edit";
	    			label_4.setText("");
	    		}
	    		else
	    			my_state = "Idle";
	    		
	    	}
	    });
	    combo_2.addFocusListener(new FocusAdapter() {
	    	public void focusGained(final FocusEvent e) {
	    		initialValue = combo_2.getText();
	    	}
	    });
	    combo_2.addMouseListener(new MouseAdapter() {
	    	public void mouseDown(final MouseEvent e) {
	    		if(combo_2.getItemCount() > 0)
					return;
				dataManager.setVocabularyId(EpcisConstants.READ_POINT_ID);
				java.util.List<String> names = dataManager.getElementNames("",null);
				if(names.size() == 0)
				{
					combo_2.setText("No information available!");
					
					return;
				}
				populateCombo_Names(combo_2,names);
	    	}
	    });
	    combo_2.setVisible(false);
	    combo_2.setBounds(330, 167, 142, 23);

	    actionLabel_2 = new Label(elementDataGroup, SWT.NONE);
	    actionLabel_2.setVisible(false);
	    actionLabel_2.setText("Action:");
	    actionLabel_2.setBounds(10, 173, 49, 15);

	    combo_3_Action = new Combo(elementDataGroup, SWT.NONE);
	    combo_3_Action.addVerifyListener(new VerifyListener() {
	    	public void verifyText(final VerifyEvent e) {
	    		if(!combo_3_Action.getText().equals(initialValue))
	    		{
	    			my_state = "Edit";
	    			label_4.setText("");
	    		}
	    		else
	    			my_state = "Idle";
	    	}
	    });
	    combo_3_Action.addFocusListener(new FocusAdapter() {
	    	public void focusGained(final FocusEvent e) {
	    		initialValue = combo_3_Action.getText();
	    	}
	    });
	    combo_3_Action.addMouseListener(new MouseAdapter() {
	    	public void mouseDown(final MouseEvent e) {
	    		if(combo_3_Action.getItemCount() > 0)
	    			return;
	    		combo_3_Action.add("ADD");
	    		combo_3_Action.add("OBSERVE");
	    		combo_3_Action.add("DELETE");
	    	}
	    });
	    combo_3_Action.setVisible(false);
	    combo_3_Action.setBounds(80, 170, 130, 23);

	    transTypeLabel_1 = new Label(elementDataGroup, SWT.NONE);
	    transTypeLabel_1.setVisible(false);
	    transTypeLabel_1.setText("Trans.Type:");
	    transTypeLabel_1.setBounds(10, 218, 62, 15);

	    combo_4_TransaType = new Combo(elementDataGroup, SWT.NONE);
	    combo_4_TransaType.addModifyListener(new ModifyListener() {
	    	public void modifyText(final ModifyEvent e) {
	    		if(!combo_4_TransaType.getText().equals(initialValue))
	    		{
	    			my_state = "Edit";
	    			label_4.setText("");
	    		}
	    		else
	    			my_state = "Idle";
	    	}
	    });
	    combo_4_TransaType.addFocusListener(new FocusAdapter() {
	    	public void focusGained(final FocusEvent e) {
	    		initialValue = combo_4_TransaType.getText();
	    	}
	    });
	    combo_4_TransaType.addMouseListener(new MouseAdapter() {
	    	public void mouseDown(final MouseEvent e) {
	    		if(combo_4_TransaType.getItemCount() > 0)
					return;
				dataManager.setVocabularyId(EpcisConstants.BUSINESS_TRANSACTION_TYPE_ID);
				java.util.List<String> names = dataManager.getElementNames("",null);
				if(names.size() == 0)
				{
					combo_4_TransaType.setText("No information available!");
					
					return;
				}
				populateCombo_Names(combo_4_TransaType,names);
	    	}
	    });
	    combo_4_TransaType.setVisible(false);
	    combo_4_TransaType.setBounds(80, 215, 130, 23);

	    reportsLabel_1 = new Label(elementDataGroup, SWT.NONE);
	    reportsLabel_1.setVisible(false);
	    reportsLabel_1.setText("Reports:");
	    reportsLabel_1.setBounds(10, 259, 62, 15);

	    listOfReports = new List(elementDataGroup, SWT.BORDER);
	    listOfReports.setEnabled(false);
	    listOfReports.setVisible(false);
	    listOfReports.setBounds(80, 255, 392, 52);

	    table_1_transactions = new Table(composite_2, SWT.FULL_SELECTION | SWT.BORDER);
	    table_1_transactions.addMouseListener(new MouseAdapter() {
	    	public void mouseDown(final MouseEvent e) {
	    		
	    		if(e.button == 3)
				{
	    			removeAttrTableMenuTransactions.setVisible(true);
				}
				else if(e.button == 1)
				{
					text_3_attribute.setText(table_1_transactions.getSelection()[0].getText(0));
					text_3_attribute.setEnabled(false);
					text_4_Value_trans.setText(table_1_transactions.getSelection()[0].getText(1));
					saveButton_1.setEnabled(true);
					
					
				}
	    	}
	    });
	    table_1_transactions.setLinesVisible(true);
	    table_1_transactions.setHeaderVisible(true);
	    table_1_transactions.setBounds(200, 223, 514, 162);

	    final TableColumn newColumnTableColumn_8 = new TableColumn(table_1_transactions, SWT.NONE);
	    newColumnTableColumn_8.setWidth(250);
	    newColumnTableColumn_8.setText("Attribute");

	    final TableColumn newColumnTableColumn_9 = new TableColumn(table_1_transactions, SWT.NONE);
	    newColumnTableColumn_9.setWidth(250);
	    newColumnTableColumn_9.setText("Value");

	    final Group attributevaluesGroup_2 = new Group(composite_2, SWT.NONE);
	    attributevaluesGroup_2.setText("Attribute-Values");
	    attributevaluesGroup_2.setBounds(200, 135, 514, 82);

	    final Label attributeLabel_4 = new Label(attributevaluesGroup_2, SWT.NONE);
	    attributeLabel_4.setText("Attribute:");
	    attributeLabel_4.setBounds(10, 36, 61, 15);

	    text_3_attribute = new Text(attributevaluesGroup_2, SWT.BORDER);
	    text_3_attribute.addFocusListener(new FocusAdapter() {
	    	public void focusGained(final FocusEvent e) {
	    		label_4.setText("");
	    	}
	    });
	    

	    
	    text_3_attribute.addModifyListener(new ModifyListener() {
	    	public void modifyText(final ModifyEvent e) {
	    		
	    		if(text_3_attribute.getText().trim().equals(""))
	    		{
	    			my_state = "Idle";
	    			return;
	    		}
	    		else
	    		{
	    			for(int i = 0; i < table_1_transactions.getItems().length; i++)
	    			{
	    				if(table_1_transactions.getItems()[i].getText(0).equals(text_3_attribute.getText())){
	    					my_state = "Idle";
	    					return;
	    				}
	    					
	    			}
	    		}
	    		my_state = "Edit";
	    	}
	    });
	    
	    text_3_attribute.setBounds(78, 33, 132, 25);

	    final Label valueLabel_5 = new Label(attributevaluesGroup_2, SWT.NONE);
	    valueLabel_5.setText("Value:");
	    valueLabel_5.setBounds(270, 36, 43, 15);

	    text_4_Value_trans = new Text(attributevaluesGroup_2, SWT.BORDER);
	    text_4_Value_trans.addFocusListener(new FocusAdapter() {
	    	public void focusGained(final FocusEvent e) {
	    		label_4.setText("");
	    	}
	    });
	    text_4_Value_trans.addModifyListener(new ModifyListener() {
	    	public void modifyText(final ModifyEvent e) {
	    		
	    		if(text_4_Value_trans.getText().trim().equals(""))
	    		{
	    			my_state = "Idle";
	    			return;
	    		}
	    		else {
	    			for(int i = 0; i < table_1_transactions.getItems().length; i++)
	    			{
	    				if(table_1_transactions.getItems()[i].getText(1).equals(text_4_Value_trans.getText()))
	    				{
	    					my_state = "Idle";
	    					return;
	    				}
	    					
	    			}
	    		}
	    		my_state = "Edit";
	    	}
	    });
	  
	    text_4_Value_trans.setBounds(334, 33, 139, 25);
	    
	    removeAttrTableMenuTransactions = new Menu(composite_2.getShell(), SWT.POP_UP);
	    
	    MenuItem remRowTrans = new MenuItem(removeAttrTableMenuTransactions, SWT.CASCADE);
	    remRowTrans.setText("Remove Attribute");
	    remRowTrans.addSelectionListener(new SelectionAdapter(){
			public void widgetSelected(final SelectionEvent e){
				
				String mode = "3";
				boolean result = false;
				if(table_1_transactions.getItemCount() == 0)
					return;
				if(text_3_attribute.getText().equals("") || text_4_Value_trans.getText().equals("")){
					label_4.setText("Please click on the attribute to remove");
					label_4.setVisible(true);
					return;
				}
				if(text_3_attribute.getText().equals("Name"))
				{
					label_4.setText("Name is required.You can only modify it!");
					label_4.setVisible(true);
					return;
					
				}
				dataManager.setVocabularyId(EpcisConstants.BUSINESS_TRANSACTION_ID);
				String id = (Transaction_Modified_Id.equals("")?text_EPCTrans.getText():Transaction_Modified_Id);
				result = dataManager.masterDataAttributeEdit(id, text_3_attribute.getText(), text_4_Value_trans.getText(), mode);
				//updateAttrValue(bizDispoTag_txt.getText(),"urn:epcglobal:epcis:vtype:Disposition",bizDispo_Attribute_txt.getText(),bizDispoValue_txt.getText(),"3");
				if(result)
				{
					removeAttribute_Table(table_1_transactions);
					label_4.setText("Database was sucessfully updated");
					label_4.setVisible(true);
				}
				else
				{
					label_4.setText("Attribute could not be removed!");
					label_4.setVisible(true);
					
				}
				text_3_attribute.setText("");
				text_4_Value_trans.setText("");
				text_3_attribute.setEditable(true);
				text_4_Value_trans.setEnabled(true);
			
				
				
				
				
			}
		});

	    final Button button_15 = new Button(composite_2, SWT.NONE);
	    button_15.setToolTipText("Start here! Query/Refresh");
	    button_15.addMouseListener(new MouseAdapter() {
	    	public void mouseDown(final MouseEvent e) {
	    		showTransactionHierarchyNEW();
	    	}
	    });
	    button_15.setImage(ResourceManager.getPluginImage(Activator.getDefault(), "icons/img/b_newdb.png"));
	    button_15.setBounds(25, 390, 48, 25);

	    attributesButton_2 = new Button(composite_2, SWT.NONE);
	    attributesButton_2.setEnabled(false);
	    attributesButton_2.addMouseListener(new MouseAdapter() {
	    	public void mouseDown(final MouseEvent e) {
	    		if(table_1_transactions.isVisible())
	    		{
	    			text_3_attribute.setEnabled(true);
	    			text_3_attribute.setText("");
	    			text_4_Value_trans.setEnabled(true);
	    			text_4_Value_trans.setText("");
	    			saveButton_1.setEnabled(true);
	    			//cancelButton.setEnabled(true);
	    			
	    		}
	    		else
	    		{
	    			//AttributesCollector attributeCollector = new AttributesCollector(new Shell(),0);
					//java.util.List<Attribute> theAttributes = (ArrayList<Attribute>)attributeCollector.open();
					//if(theAttributes != null && theAttributes.size() > 0)			
						//BizTransaction.addAll(theAttributes);
					
	    		}
	    	}
	    });
	    attributesButton_2.setText("Attribute");
	    attributesButton_2.setBounds(643, 390, 71, 25);

	    saveButton_1 = new Button(composite_2, SWT.NONE);
	    saveButton_1.setEnabled(false);
	    saveButton_1.addMouseListener(new MouseAdapter() {
	    	public void mouseDown(final MouseEvent e) {
	    		label_4.setVisible(false);
	    		boolean result = false;
	    		if( !my_state.equals("Edit"))
	    			return;
	    		my_state = "Idle"; 
	    		initialValue = "";
	    		if(treeTransaction.getItemCount() == 0)
	    		{
	    			TreeItem root = new TreeItem(treeTransaction, 1);
	    			root.setText("Transactions");
	    			root.setData("Root");
	    			root.setImage(ResourceManager.getPluginImage(Activator.getDefault(), "icons/fromAgilo/package_obj.gif"));
		    	
	    		}
	    		if(NameTrans.getText().equals("") || text_EPCTrans.getText().equals(""))
	    		{
	    			label_4.setVisible(true);
	    			label_4.setText("Uri and Name a" +
	    					"re required!");
	    			return;
	    		}
	    		dataManager.setVocabularyId(EpcisConstants.BUSINESS_TRANSACTION_ID);
	    		if(table_1_transactions.isVisible())
	    		{
	    			//This is a transaction
	    			if(NameTrans.isEnabled())
	    			{
	    				//new addition
	    				String uri = text_EPCTrans.getText();
	    				if(transactionParent != null)//treeTransaction.getSelectionCount() == 0){
	    				{
	    					StringTokenizer parentId = new StringTokenizer((String)transactionParent.getData(),"$$");//(String)treeTransaction.getSelection()[0].getData(),"$$");
	    					String type = parentId.nextToken();
	    					if(!type.equals("Root") && type.equals("TopLevelTransaction"))
	    					{
	    						uri = parentId.nextToken()+","+uri;
	    					}
	    				}
	    				String name = NameTrans.getText();
	    				result = dataManager.masterDataElementEdit(uri, "1");
	    				if(!result)
	    				{
	    					label_4.setVisible(true);
	    	    			label_4.setText("Element could not be added");
	    	    			return;
	    					
	    				}
	    				result = dataManager.masterDataAttributeEdit(uri, "Name", name, "1");
	    				//Put name in table
	    				TableItem nameRow = new TableItem(table_1_transactions,0);
	    				nameRow.setText(new String[]{"Name",NameTrans.getText()});
	    				NameTrans.setEnabled(false);
	    				text_EPCTrans.setEnabled(false);
	    				if(!text_3_attribute.getText().equals("") && !text_4_Value_trans.getText().equals("") )
	    				{
	    					String attribute = text_3_attribute.getText();
	    					String value = text_4_Value_trans.getText();
	    					result = dataManager.masterDataAttributeEdit(uri, attribute, value, "1");
	    					TableItem row = new TableItem(table_1_transactions,0);
		    				row.setText(new String[]{attribute,value});
		    				
		    				text_3_attribute.setText("");
		    				text_4_Value_trans.setText("");
	    				}
	    				
	    	    		if(treeTransaction.getSelectionCount() == 0)
	    	    		{
	    	    			//update tree
	    	    			insertTransactionNode(treeTransaction.getItem(0),uri,name,"Transaction","icons/fromAgilo/package_obj.gif",false);
	    	    		//	insertTransactionNode(treeTransaction.getItem(0),uri,name,"Transaction","icons/fromAgilo/runprocess.png",false);
	    	    		}
	    	    		else{
	    	    			if(transactionParent == null)	    	    			
	    	    				insertTransactionNode(treeTransaction.getSelection()[0],uri,name,"TopLevelTransaction","icons/fromAgilo/package_obj.gif",true);
	    	    			else
	    	    				insertTransactionNode(treeTransaction.getSelection()[0],uri,name,"Transaction","icons/fromAgilo/runprocess.png",true);
	    	    				
	    	    		}
	    	    		transactionParent = null;
	    	    		if(result)
	    	    		{
	    	    			label_4.setVisible(true);
	    	    			label_4.setText("Database was sucessfully updated");
	    	    			transactionBranch = false;
	    	    			
	    	    			
	    	    		}
	    	    		else
	    	    		{
	    	    			label_4.setVisible(true);
	    	    			label_4.setText("Database update failed");
	    	    			
	    	    		}
	    	    		text_3_attribute.setEnabled(true);	

	    				
	    			}
	    			else
	    			{
	    			//Modification
	    				if(text_4_Value_trans.getText().equals(""))
	    					return;
	    				if(text_3_attribute.isEnabled()){
	    					if(text_3_attribute.getText().equals(""))
	    					{
	    						return;
	    					}
	    					for(int i = 0; i < table_1_transactions.getItems().length; i++)
	    					{
	    						if(table_1_transactions.getItem(i).getText(0).equals(text_3_attribute.getText())&&
	    								table_1_transactions.getItem(i).getText(1).equals(text_4_Value_trans.getText()))
	    							return;
	    					}
	    					
	    					TableItem row = new TableItem(table_1_transactions,0);
		    				row.setText(new String[]{text_3_attribute.getText(),text_4_Value_trans.getText()});
	    					
	    				}
	    				else if(table_1_transactions.getSelectionCount() > 0)
	    					table_1_transactions.getSelection()[0].setText(1, text_4_Value_trans.getText());
	    				StringTokenizer parentId = new StringTokenizer((String)treeTransaction.getSelection()[0].getParentItem().getData(),"$$");
	    				if(parentId.nextToken().equals("Root"))
	    					result = dataManager.masterDataAttributeEdit(text_EPCTrans.getText(), text_3_attribute.getText(), text_4_Value_trans.getText(), "2");
	    				else
	    					result = dataManager.masterDataAttributeEdit(parentId.nextToken()+","+text_EPCTrans.getText(), text_3_attribute.getText(), text_4_Value_trans.getText(), "2");

	    				
	    	    		if(result)
	    	    		{
	    	    			label_4.setVisible(true);
	    	    			label_4.setText("Database was sucessfully updated");
	    	    			if(text_3_attribute.getText().equals("Name"))
	    	    			{
	    	    				treeTransaction.getSelection()[0].setText(text_4_Value_trans.getText());
	    	    			}
	    	    			
	    	    			
	    	    		}
	    	    		else
	    	    		{
	    	    			label_4.setVisible(true);
	    	    			label_4.setText("Database update failed");
	    	    			
	    	    		}
	    	    		text_3_attribute.setText("");
	    	    		text_4_Value_trans.setText("");
	    	    		text_3_attribute.setEnabled(true);
	    				
	    			}
	    			
	    		}
	    		else//event save
	    		{
	    			if(NameTrans.getText().equals("") || text_EPCTrans.getText().equals("")|| combo_1_eventType.getText().equals("") || combo_2_businessStep.getText().equals("")||
	    					combo_1Location.getText().equals("") || combo_1_Disposition.getText().equals("") ||  combo_2.getText().equals("") || combo_3_Action.getText().equals("")
	    					|| combo_4_TransaType.getText().equals("") )
	    			{
	    				label_4.setVisible(true);
	    				label_4.setText("All the fields must be filled");
	    				return;
	    			}
	    			if(!dataManager.verifySelection(combo_1_eventType, combo_1_eventType.getText()))
	    			{
	    				label_4.setVisible(true);
	    				label_4.setText("Please choose one of the appropriate types");
	    				return;
	    				
	    			}
	    			if(!dataManager.verifySelection(combo_2_businessStep, combo_2_businessStep.getText()))
	    			{
	    				label_4.setVisible(true);
	    				label_4.setText("Please choose one of the available steps");
	    				return;
	    				
	    			}
	    			if(!dataManager.verifySelection(combo_1Location, combo_1Location.getText()))
	    			{
	    				label_4.setVisible(true);
	    				label_4.setText("Please choose one of the available locations");
	    				return;
	    				
	    			}
	    			if(!dataManager.verifySelection(combo_1_Disposition, combo_1_Disposition.getText()))
	    			{
	    				label_4.setVisible(true);
	    				label_4.setText("Please choose one of the available dispositions");
	    				return;
	    				
	    			}
	    			if(!dataManager.verifySelection(combo_2, combo_2.getText()))
	    			{
	    				label_4.setVisible(true);
	    				label_4.setText("Please choose one of the available read-points");
	    				return;
	    				
	    			}
	    			if(!dataManager.verifySelection(combo_3_Action, combo_3_Action.getText()))
	    			{
	    				label_4.setVisible(true);
	    				label_4.setText("Please choose one of the appropriate actions");
	    				return;
	    				
	    			}
	    			
	    			if(!dataManager.verifySelection(combo_4_TransaType, combo_4_TransaType.getText()))
	    			{
	    				label_4.setVisible(true);
	    				label_4.setText("Please choose one of the appropriate types");
	    				return;
	    				
	    			}
	    			
	    			
	    			
	    			//StringTokenizer transactionUri = new StringTokenizer((String)treeTransaction.getSelection()[0].getParentItem().getParentItem().getData(),"$$");
	    			StringTokenizer transactionUri = new StringTokenizer((String)treeTransaction.getSelection()[0].getParentItem().getData(),"$$");
	    			String eventUri = text_EPCTrans.getText();
	    			transactionUri.nextToken();
	    			String transId = transactionUri.nextToken();
	    			if(transId.split(",").length!=2)//transactionUri.countTokens() != 2 || !transactionUri.nextToken().equals("Transaction"))
	    			{
	    				label_4.setVisible(true);
	    				label_4.setText("Please click on the transaction");
	    				return;
	    			}
	    			
	    			
	    			StringBuffer reports = new StringBuffer();
	    			for(int i = 0; i < listOfReports.getItemCount(); i++)
	    			{
	    				reports.append(listOfReports.getItem(i));
	    				if(i < listOfReports.getItemCount() - 1)
	    					reports.append(",");
	    			}
	    			dataManager.setVocabularyId(EpcisConstants.BUSINESS_TRANSACTION_ID);
	    			result = dataManager.insertEvent(transId, eventUri, NameTrans.getText(),combo_1_eventType.getText(), combo_2_businessStep, combo_1Location, combo_1_Disposition, combo_2, combo_3_Action.getText(), combo_4_TransaType);//, reports.toString());
	    			treeTransaction.getSelection()[0].setText(NameTrans.getText());
	    			treeTransaction.getSelection()[0].setData("Event$$"+transId+","+eventUri);
	    			if(result)
	    			{
	    				label_4.setVisible(true);
	    				label_4.setText("Database has been successfully updated");
	    				transactionBranch = false;
	    			}
	    		}
	    		
	    		//saveButton_1.setEnabled(false);
	    		cancelButton.setEnabled(false);
	    	}
	    });
	    saveButton_1.setText("Save");
	    saveButton_1.setBounds(532, 390, 48, 25);

	    label_4 = new Label(composite_2, SWT.NONE);
	    label_4.setVisible(false);
	    label_4.setForeground(SWTResourceManager.getColor(255, 0, 0));
	    label_4.setFont(SWTResourceManager.getFont("", 10, SWT.BOLD));
	    label_4.setText("Label");
	    label_4.setBounds(79, 393, 447, 25);

	     cancelButton = new Button(composite_2, SWT.NONE);
	    cancelButton.setEnabled(false);
	    cancelButton.addMouseListener(new MouseAdapter() {
	    	public void mouseDown(final MouseEvent e) {
	    		transactionBranch = false;
	    		insertNode.dispose();
	    		label_4.setVisible(false);
	    		saveButton_1.setEnabled(false);
	    		cancelButton.setEnabled(false);
	    	}
	    });
	    cancelButton.setText("Cancel");
	    cancelButton.setBounds(590, 390, 48, 25);
	    composite_2.setSize(787, 466);
	    scrolledComposite.setContent(composite_2);

	    final TabItem locationsTabItem = new TabItem(tabFolder, SWT.NONE);
	    locationsTabItem.setText("Locations");
	    locationsTabItem.setImage(ResourceManager.getPluginImage(Activator.getDefault(), "icons/house_32.gif"));

	    final ScrolledComposite scrolledComposite_1 = new ScrolledComposite(tabFolder, SWT.BORDER | SWT.V_SCROLL);
	    locationsTabItem.setControl(scrolledComposite_1);

	    final Composite composite_9 = new Composite(scrolledComposite_1, SWT.NONE);
	    composite_9.addMouseListener(new MouseAdapter() {
	    	public void mouseDown(final MouseEvent e) {
	    		if(e.button == 1)
	    		{
	    			BizLoc_tree_v2.deselectAll();
	    			
	    		}
	    	}
	    });

	    final Group elementDataGroup_1 = new Group(composite_9, SWT.NONE);
	    elementDataGroup_1.setText("Element Data (URI/Alias)");
	    elementDataGroup_1.setBounds(230, 20, 500, 82);

	    final Label uriLabel = new Label(elementDataGroup_1, SWT.NONE);
	    uriLabel.setText("Uri:");
	    uriLabel.setBounds(10, 35, 28, 15);

	    BizLoc_v2_text = new Text(elementDataGroup_1, SWT.BORDER);
	    BizLoc_v2_text.addVerifyListener(new VerifyListener() {
	    	public void verifyText(final VerifyEvent e) {
	    		if (e.end - e.start == 0) 
	    		{
	    			
	    			if(e.text.equals(","))
	    			{
	    				e.text = ":";
	    			}
	    			
	    		}
	    	}
	    });
	    BizLoc_v2_text.setBounds(54, 32, 184, 25);

	    final Label nameLabel_4 = new Label(elementDataGroup_1, SWT.NONE);
	    nameLabel_4.setText("Name:");
	    nameLabel_4.setBounds(258, 35, 44, 15);

	    BizLoc_name_v2 = new Text(elementDataGroup_1, SWT.BORDER);
	    BizLoc_name_v2.setBounds(310, 32, 180, 25);

	    final Group attributevaluesGroup_3 = new Group(composite_9, SWT.NONE);
	    attributevaluesGroup_3.setText("Attribute-Values");
	    attributevaluesGroup_3.setBounds(230, 110, 500, 89);

	    BizLocAttr_combo_v2 = new Combo(attributevaluesGroup_3, SWT.NONE);
	    BizLocAttr_combo_v2.setToolTipText("Choose an attribute or add your own!");
	    BizLocAttr_combo_v2.addMouseListener(new MouseAdapter() {
	    	public void mouseDown(final MouseEvent e) {
	    		if(BizLocAttr_combo_v2.getItemCount() > 0)
	    			return;
	    		BizLocAttr_combo_v2.add("Address");
	    		BizLocAttr_combo_v2.add("Country");
	    		BizLocAttr_combo_v2.add("City");
	    		BizLocAttr_combo_v2.add("Address2");
	    		BizLocAttr_combo_v2.add("Address3");
	    		BizLocAttr_combo_v2.add("Address4");
	    		BizLocAttr_combo_v2.add("Short_Description");
	    		BizLocAttr_combo_v2.add("Phone_Number");
	    	}
	    });
	    BizLocAttr_combo_v2.setBounds(69, 35, 172, 23);

	    final Label attributeLabel_5 = new Label(attributevaluesGroup_3, SWT.NONE);
	    attributeLabel_5.setText("Attribute:");
	    attributeLabel_5.setBounds(10, 38, 50, 15);

	    final Label valueLabel_6 = new Label(attributevaluesGroup_3, SWT.NONE);
	    valueLabel_6.setText("Value:");
	    valueLabel_6.setBounds(258, 38, 48, 15);

	    BizLoc_Val_text = new Text(attributevaluesGroup_3, SWT.BORDER);
	    BizLoc_Val_text.setBounds(312, 35, 178, 25);

	    bizLoc_attrVal_table = new Table(composite_9, SWT.FULL_SELECTION | SWT.BORDER);
	    bizLoc_attrVal_table.addMouseListener(new MouseAdapter() {
	    	public void mouseDown(final MouseEvent e) {
	    		
	    		if(e.button == 3 && bizLoc_attrVal_table.getItemCount() > 0)
	    		{
	    			removeAttrTableMenuLocations2.setVisible(true);
	    		}

	    		if(e.button == 1 && bizLoc_attrVal_table.getItemCount() > 0)//Value Modification
				{
	    			BizLocAttr_combo_v2.setText(bizLoc_attrVal_table.getSelection()[0].getText(0));
	    			BizLoc_Val_text.setText(bizLoc_attrVal_table.getSelection()[0].getText(1));
	    			BizLocAttr_combo_v2.setEnabled(false);
					
				}
	    		
	    		
	    		
	    	}
	    });
	    bizLoc_attrVal_table.setLinesVisible(true);
	    bizLoc_attrVal_table.setHeaderVisible(true);
	    bizLoc_attrVal_table.setBounds(230, 205, 500, 137);
	    
	    
	    
	    
	    
	    
	   
	    removeAttrTableMenuLocations2 = new Menu(composite_9.getShell(), SWT.POP_UP);
	    
	    MenuItem remRowLocations = new MenuItem(removeAttrTableMenuLocations2, SWT.CASCADE);
	    remRowLocations.setText("Remove Attribute");
	    remRowLocations.setImage(ResourceManager.getPluginImage(Activator.getDefault(), "icons/fromAgilo/menu_delete_e.gif"));
	    remRowLocations.addSelectionListener(new SelectionAdapter(){
			public void widgetSelected(final SelectionEvent e){
				
				String mode = "3";
				boolean result = false;
				if(bizLoc_attrVal_table.getItemCount() == 0)
					return;
				if(BizLocAttr_combo_v2.getText().equals("") || BizLoc_Val_text.getText().equals("")){
					bizLoc_info_label.setText("Please click on the attribute to remove");
					bizLoc_info_label.setVisible(true);
					return;
				}
				if(BizLocAttr_combo_v2.getText().equals("Name"))
				{
					bizLoc_info_label.setText("Name is required.You can only modify it!");
					bizLoc_info_label.setVisible(true);
					return;
					
				}
				dataManager.setVocabularyId(EpcisConstants.BUSINESS_LOCATION_ID);
				result = dataManager.masterDataAttributeEdit(dataManager.getLocationDisplayedId(), BizLocAttr_combo_v2.getText(), BizLoc_Val_text.getText(), mode);

				if(result)
				{
					removeAttribute_Table(bizLoc_attrVal_table);
					bizLoc_info_label.setText("Database was sucessfully updated");
					bizLoc_info_label.setVisible(true);
				}
				else
				{
					bizLoc_info_label.setText("Attribute could not be removed!");
					bizLoc_info_label.setVisible(true);
					
				}
				BizLocAttr_combo_v2.setText("");
				BizLoc_Val_text.setText("");
				BizLoc_Val_text.setEnabled(true);
			
				
				
				
				
			}
		});
	    

	    
	    
	    

	    final TableColumn newColumnTableColumn_10 = new TableColumn(bizLoc_attrVal_table, SWT.NONE);
	    newColumnTableColumn_10.setWidth(250);
	    newColumnTableColumn_10.setText("Attribute");

	    final TableColumn newColumnTableColumn_11 = new TableColumn(bizLoc_attrVal_table, SWT.NONE);
	    newColumnTableColumn_11.setWidth(250);
	    newColumnTableColumn_11.setText("Value");

	    BizLoc_tree_v2 = new Tree(composite_9, SWT.BORDER);
	    BizLoc_tree_v2.addMouseListener(new MouseAdapter() {
	    	public void mouseDown(final MouseEvent e) {
	    		if(e.button == 3)
	    		{
	    			
	    			BizLoc_menuBar.setVisible(true);
	    		
	    		
	    		}
	    		
	    	}
	    	public void mouseDoubleClick(final MouseEvent e) {
	    		if(BizLoc_tree_v2.getSelection()[0].getData() == null || BizLoc_tree_v2.getSelection()[0].getData().equals(""))
				{
					//bizLoc_info_label.setText("Please click on a business location");
					bizLoc_info_label.setVisible(true);
					return;
				}
	    		dataManager.setVocabularyId(EpcisConstants.BUSINESS_LOCATION_ID);
	    		//dataManager.changeLocationParent((String)BizLoc_tree_v2.getSelection()[0].getData());
	    		boolean moved = dataManager.moveLocation((String)BizLoc_tree_v2.getSelection()[0].getData(),BizLoc_tree_v2.getSelection()[0]);
	    		if(moved){
	    			//dataManager.rearrange(BizLoc_tree_v2.getSelection()[0]);
	    			populateBizLocTree(BizLoc_tree_v2,"no","icons/house_16.gif");
	    			BizLoc_tree_v2.getItems()[0].setExpanded(true);
	    			//BizLoc_tree_v2.getSelection()[0].setExpanded(true);
		    		//populateBizLocTree(BizLoc_depr_tree_v2,"yes","icons/img/s_cancel.png");
		    		dataManager.setLocationDisplayedId(null);
	    		}
	    		
	    		
	    	}
	    });
	    BizLoc_tree_v2.setBounds(10, 27, 216, 200);

	    BizLoc_depr_tree_v2 = new Tree(composite_9, SWT.BORDER);
	    BizLoc_depr_tree_v2.addMouseListener(new MouseAdapter() {
	    	public void mouseDown(final MouseEvent e) {
	    		if(e.button == 3)
	    		{
	    			
	    			BizLoc_menuBar_Undeprecate.setVisible(true);
	    		
	    		
	    		}
	    	}
	    });
	    BizLoc_depr_tree_v2.setBounds(10, 233, 216, 203);
	    
	    
	    
	    BizLoc_menuBar = new Menu(composite_9.getShell(), SWT.POP_UP);
		MenuItem chParent = new MenuItem(BizLoc_menuBar, SWT.CASCADE);
		chParent.setText("Change Parent");
		chParent.addSelectionListener(new SelectionAdapter(){
			public void widgetSelected(final SelectionEvent e){
				if(BizLoc_tree_v2.getSelection()[0].getData() == null || BizLoc_tree_v2.getSelection()[0].getData().equals(""))
				{
					bizLoc_info_label.setText("Please click on a business location");
					bizLoc_info_label.setVisible(true);
					return;
				}
				bizLoc_info_label.setVisible(false);
				dataManager.saveLocationHierarchy(BizLoc_tree_v2.getSelection()[0]);
				bizLoc_info_label.setVisible(true);
				bizLoc_info_label.setText("Please double-click on the new parent");
					
			//	if(changeBizLocParent())
			//	{
					//bizLoc_info_label.setVisible(true);
				///	bizLoc_info_label.setText("Please double-click on the new parent");
					//oldPosition = BizLoc_Tree_Root.getSelection()[0];
					
				//}
				
				
			}
		});
			
		
		MenuItem insertLocation = new MenuItem(BizLoc_menuBar, SWT.CASCADE);
		insertLocation.setText("Insert");
		insertLocation.addSelectionListener(new SelectionAdapter(){
			public void widgetSelected(final SelectionEvent e){
				
				
			}
		});
		
		

		MenuItem displayLocation = new MenuItem(BizLoc_menuBar, SWT.CASCADE);
		displayLocation.setText("Show information");
		displayLocation.addSelectionListener(new SelectionAdapter(){
			public void widgetSelected(final SelectionEvent e){
				String msg;
				bizLoc_info_label.setVisible(false);
				if(BizLoc_tree_v2.getSelectionCount() == 0 || BizLoc_tree_v2.getSelection()[0].getData()== null || BizLoc_tree_v2.getSelection()[0].getData().equals(""))
				{
					msg = "You must select a business location";
					bizLoc_info_label.setText(msg);
					bizLoc_info_label.setVisible(true);
					
				}
				else
					display_businessLocation_data((String)BizLoc_tree_v2.getSelection()[0].getData());
				dataManager.setLocationDisplayedId((String)BizLoc_tree_v2.getSelection()[0].getData());
				dataManager.getnewList().clear();
				BizLoc_tree_v2.setToolTipText((String)BizLoc_tree_v2.getSelection()[0].getData());
				BizLoc_name_v2.setToolTipText(BizLoc_name_v2.getText());
			}
		});
		
		
		
		Menu insertLocMenu = new Menu(insertLocation);
		insertLocation.setMenu(insertLocMenu);
		MenuItem loc_children = new MenuItem(insertLocMenu,SWT.PUSH);
		loc_children.setText("Children");
		loc_children.addSelectionListener(new SelectionAdapter(){
			public void widgetSelected(final SelectionEvent e){
				
				if(BizLoc_tree_v2.getSelection()[0].getData()== null || BizLoc_tree_v2.getSelection()[0].getData().equals(""))
				{
					bizLoc_info_label.setText("Please choose a business location");
					bizLoc_info_label.setVisible(true);
					return;
				}
				TreeItem child_node = new TreeItem(BizLoc_tree_v2.getSelection()[0],0);
				child_node.setText("<...>");
				child_node.setData("addition");
				child_node.setImage(ResourceManager.getPluginImage(Activator.getDefault(), "icons/house_16.gif"));
				BizLoc_tree_v2.setSelection(child_node);
				BizLocAttr_combo_v2.setText("");
				BizLoc_Val_text.setText("");
				BizLoc_v2_text.setEnabled(true);
				BizLoc_v2_text.setText("");
				BizLoc_name_v2.setEnabled(true);
				BizLoc_name_v2.setText("");
				bizLoc_attrVal_table.removeAll();
				dataManager.getnewList().clear();
				
			}
		});
		
		
		
		
	
		
		MenuItem deprecate1 = new MenuItem(BizLoc_menuBar, SWT.CASCADE);
		deprecate1.setText("Deprecate");
		deprecate1.addSelectionListener(new SelectionAdapter(){
			public void widgetSelected(final SelectionEvent e){
	
				if(BizLoc_tree_v2.getSelectionCount() == 0 || BizLoc_tree_v2.getSelection()[0].getData() == null || BizLoc_tree_v2.getSelection()[0].getData().equals(""))
				{
					bizLoc_info_label.setText("Please click on a business location");
					bizLoc_info_label.setVisible(true);
					return;
			
				}
				String uri = (String)BizLoc_tree_v2.getSelection()[0].getData();
				dataManager.setVocabularyId(EpcisConstants.BUSINESS_LOCATION_ID);
				dataManager.deprecate(BizLoc_tree_v2.getSelection()[0],"yes");
				populateBizLocTree(BizLoc_tree_v2,"no","icons/house_16.gif");
	    		populateBizLocTree(BizLoc_depr_tree_v2,"yes","icons/img/s_cancel.png");
	    		BizLoc_tree_v2.getItem(0).setExpanded(true);
	    		BizLoc_depr_tree_v2.getItem(0).setExpanded(true);
				
				
				
			}
		});


	    
	    
	    
		BizLoc_menuBar_Undeprecate = new Menu(composite_9.getShell(), SWT.POP_UP);
			MenuItem depr = new MenuItem(BizLoc_menuBar_Undeprecate, SWT.CASCADE);
			depr.setText("Undeprecate");
			depr.addSelectionListener(new SelectionAdapter(){
				public void widgetSelected(final SelectionEvent e){
					
					
					if(BizLoc_depr_tree_v2.getSelectionCount() == 0 || BizLoc_depr_tree_v2.getSelection()[0].getData() == null || BizLoc_depr_tree_v2.getSelection()[0].getData().equals(""))
					{
						bizLoc_info_label.setText("Please click on a business location");
						bizLoc_info_label.setVisible(true);
						return;
					}
					bizLoc_info_label.setVisible(false);
					
					if(BizLoc_depr_tree_v2.getSelection()[0].getParentItem().getData() != null)
					{
						bizLoc_info_label.setText("You can not set active a child node and leave the parent node inactive!");
						bizLoc_info_label.setVisible(true);
						return;
						
					}
					dataManager.setVocabularyId(EpcisConstants.BUSINESS_LOCATION_ID);
					dataManager.deprecate(BizLoc_depr_tree_v2.getSelection()[0],"no");
					populateBizLocTree(BizLoc_tree_v2,"no","icons/house_16.gif");
		    		populateBizLocTree(BizLoc_depr_tree_v2,"yes","icons/img/s_cancel.png");
		    		BizLoc_tree_v2.getItem(0).setExpanded(true);
		    		BizLoc_depr_tree_v2.getItem(0).setExpanded(true);
					
				}
			});
	    
	    
			
			
			
			
		
			
	    

	    final Button button_16 = new Button(composite_9, SWT.NONE);
	    button_16.addMouseListener(new MouseAdapter() {
	    	public void mouseDown(final MouseEvent e) {
	    		
	    		populateBizLocTree(BizLoc_tree_v2,"no","icons/house_16.gif");
	    		populateBizLocTree(BizLoc_depr_tree_v2,"yes","icons/img/s_cancel.png");
	    		dataManager.setLocationDisplayedId(null);
	    	}
	    });
	    button_16.setImage(ResourceManager.getPluginImage(Activator.getDefault(), "icons/img/b_newdb.png"));
	    button_16.setBounds(230, 380, 48, 25);

	    final Button saveButton_2 = new Button(composite_9, SWT.NONE);
	    saveButton_2.addMouseListener(new MouseAdapter() {
	    	public void mouseDown(final MouseEvent e) {
	    		boolean result = false;
	    		boolean newRow = false;
	    		if(BizLoc_v2_text.getText().equals("") || BizLoc_name_v2.getText().equals(""))
	    		{
	    			bizLoc_info_label.setText("Name and Uri are required");
	    			bizLoc_info_label.setVisible(true);
	    			return;	    			
	    		}
	    		
	    		if(BizLoc_v2_text.isEnabled())
	    		{
	    			//New Addition
	    			if(dataManager.getnewList().size() == 0)
	    			{
	    				bizLoc_info_label.setText("Read Points are required!Press button to add");
		    			bizLoc_info_label.setVisible(true);
		    			return;	 
	    				
	    			}
	    			dataManager.setVocabularyId(EpcisConstants.BUSINESS_LOCATION_ID);
	    			String uri = dataManager.createFQBizLocationId(BizLoc_v2_text.getText(), BizLoc_tree_v2);
	    			result = dataManager.masterDataElementEdit(uri, "1");
	    			if(!result)
	    			{
	    				bizLoc_info_label.setText("Database insertion failed");
		    			bizLoc_info_label.setVisible(true);
		    			return;	    				
	    			}
	    			result = dataManager.masterDataAttributeEdit(uri, "Name", BizLoc_name_v2.getText(), "1");
	    			if(!result)
	    			{
	    				bizLoc_info_label.setText("Name could not be inserted!");
		    			bizLoc_info_label.setVisible(true);
		    			return;	    				
	    				
	    			}
	    			result = dataManager.editReadPoints(uri);
	    			TableItem row = new TableItem(bizLoc_attrVal_table,1);
					row.setText(new String[]{"Name",BizLoc_name_v2.getText()});
					if(!BizLocAttr_combo_v2.getText().equals("") && !BizLoc_Val_text.equals(""))
					{
						row = new TableItem(bizLoc_attrVal_table,1);
						row.setText(new String[]{BizLocAttr_combo_v2.getText(),BizLoc_Val_text.getText()});
						result = dataManager.masterDataAttributeEdit(uri, BizLocAttr_combo_v2.getText(), BizLoc_Val_text.getText(), "1");
						if(!result)
		    			{
		    				bizLoc_info_label.setText("Attribute could not be inserted!");
			    			bizLoc_info_label.setVisible(true);
			    			return;	    				
		    				
		    			}
						
					}
					BizLocAttr_combo_v2.setText("");
					BizLoc_Val_text.setText("");
					BizLoc_v2_text.setEnabled(false);
					BizLoc_name_v2.setEnabled(false);

					TreeItem node;
					if(BizLoc_tree_v2.getSelectionCount() == 0)
					{
						
						node = new TreeItem(BizLoc_tree_v2.getItem(0),0);
						
					}
					else
						node = BizLoc_tree_v2.getSelection()[0];
						
					
					
				
					node.setData(uri);
					node.setText(BizLoc_name_v2.getText());
					node.setImage(ResourceManager.getPluginImage(Activator.getDefault(), "icons/house_16.gif"));
					
					BizLoc_tree_v2.deselectAll();
					if(result)
	    			{
	    				bizLoc_info_label.setText("Database was sucessfully updated!");
		    			bizLoc_info_label.setVisible(true);
		    			dataManager.setVocabularyId(uri);
		    			return;	    				
	    				
	    			}
					
					
	
	    		}
	    		else
	    		{
	    			String id = dataManager.getLocationDisplayedId();
	    			//Modification
	    			if(BizLocAttr_combo_v2.isEnabled() && !BizLocAttr_combo_v2.getText().equals(""))
	    			{
	    				//New attribute
	    				 newRow = insertNewRow(BizLocAttr_combo_v2.getText(),BizLoc_Val_text.getText(),bizLoc_attrVal_table);
	    				if(newRow)
	    				{
	    					TableItem row = new TableItem(bizLoc_attrVal_table,1);
	    					row.setText(new String[]{BizLocAttr_combo_v2.getText(),BizLoc_Val_text.getText()});
	    					
	    				}
	    				result = dataManager.masterDataAttributeEdit(id, BizLocAttr_combo_v2.getText(), BizLoc_Val_text.getText(), "2");
	    				String msg;
	    				if(!result)
		    				msg = "Attribute could not be inserted!";
	    				else
	    					msg = "Database was sucesfully updated";
	    				bizLoc_info_label.setText(msg);
		    			bizLoc_info_label.setVisible(true);
		    			BizLocAttr_combo_v2.setEnabled(true);
		    			BizLocAttr_combo_v2.setText("");
		    			BizLoc_Val_text.setText("");
		    			BizLoc_tree_v2.deselectAll();
		    			return;	
	 
	    			}
	    			else if(!BizLoc_Val_text.getText().equals(""))
	    			{
	    				result = dataManager.masterDataAttributeEdit(id, BizLocAttr_combo_v2.getText(), BizLoc_Val_text.getText(), "2");
	    				bizLoc_attrVal_table.getSelection()[0].setText(1, BizLoc_Val_text.getText());
	    				String msg;
	    				if(!result)
		    				msg = "Attribute could not be inserted!";
	    				else
	    					msg = "Database was sucesfully updated";
	    				
	    				if(BizLocAttr_combo_v2.getText().equals("Name"))
	    				{
	    					BizLoc_name_v2.setText(BizLoc_Val_text.getText());
	    					TreeItem selected = BizLoc_tree_v2.getSelection()[0];
	    					if(selected != null)
	    						selected.setText(BizLoc_Val_text.getText());
	    					
	    				}
	    				bizLoc_info_label.setText(msg);
		    			bizLoc_info_label.setVisible(true);
		    			BizLocAttr_combo_v2.setEnabled(true);
		    			BizLocAttr_combo_v2.setText("");
		    			BizLoc_Val_text.setText("");
		    			BizLoc_tree_v2.deselectAll();
		    			
		    			
	    				
	    			}
	    			if(dataManager.getnewList().size() > 0)
	    				dataManager.editReadPoints(id);
	    			
	    			bizLoc_info_label.setText("Database was sucesfully updated");
	    			bizLoc_info_label.setVisible(true);
	    			
	    			
	    			
	    		}
	    		
	    		
	    		
	    	}
	    });
	    saveButton_2.setText("Save");
	    saveButton_2.setBounds(285, 380, 48, 25);

	    final Button cancelButton_1 = new Button(composite_9, SWT.NONE);
	    cancelButton_1.addMouseListener(new MouseAdapter() {
	    	public void mouseDown(final MouseEvent e) {
	    		bizLoc_info_label.setVisible(false);
	    		if(BizLoc_tree_v2.getSelectionCount() == 0)
	    			return;
	    		if(BizLoc_tree_v2.getSelection()[0].getData().equals("addition"))
	    			BizLoc_tree_v2.getSelection()[0].dispose();
	    		
	    	}
	    });
	    cancelButton_1.setText("Cancel");
	    cancelButton_1.setBounds(335, 380, 48, 25);

	    final Button attributeButton = new Button(composite_9, SWT.NONE);
	    attributeButton.addMouseListener(new MouseAdapter() {
	    	public void mouseDown(final MouseEvent e) {
	    		BizLocAttr_combo_v2.setEnabled(true);
	    		BizLocAttr_combo_v2.setText("");
	    		BizLoc_Val_text.setText("");
	    		dataManager.setLocationDisplayedId(null);
	    	}
	    });
	    attributeButton.setText("Attribute");
	    attributeButton.setBounds(673, 382, 59, 25);

	    final Button readpointsButton = new Button(composite_9, SWT.NONE);
	    readpointsButton.setToolTipText("Add Readers");
	    readpointsButton.addMouseListener(new MouseAdapter() {
	    	public void mouseDown(final MouseEvent e) {
	    		String location_id = dataManager.getLocationDisplayedId();
	    		EditReadPoint readers = new EditReadPoint(new Shell(),(location_id == null)? BizLoc_v2_text.getText():location_id,dataManager);
	    		Boolean res = (Boolean)readers.open();
	    		if(dataManager.getOrigList().size() != dataManager.getnewList().size() || !dataManager.getOrigList().containsAll(dataManager.getnewList()))//res && res.booleanValue()){
	    		{
	    		bizLoc_info_label.setText("Press save to update database");
	    		bizLoc_info_label.setVisible(true);
	    		}
	    	}
	    });
	    readpointsButton.setText("RPs");
	    readpointsButton.setBounds(619, 382, 48, 25);

	    bizLoc_info_label = new Label(composite_9, SWT.NONE);
	    bizLoc_info_label.setForeground(SWTResourceManager.getColor(255, 0, 0));
	    bizLoc_info_label.setFont(SWTResourceManager.getFont("", 11, SWT.BOLD));
	    bizLoc_info_label.setBounds(230, 345, 498, 31);
	    composite_9.setSize(787, 466);
	    scrolledComposite_1.setContent(composite_9);

		
 	    
	    //final ScrolledComposite scrolledComposite2 = new ScrolledComposite(tabFolder, SWT.V_SCROLL | SWT.BORDER);
	   // businessLocationsTabItem.setControl(scrolledComposite2);

	    

//akis
	    final Composite composite_3 = new Composite(tabFolder, SWT.BORDER);
	    
		composite_3.setBackground(SWTResourceManager.getColor(255, 255, 255));
		businessLocationsTabItem.setControl(composite_3);

		final Group attributesvaluesGroup = new Group(composite_3, SWT.NONE);
		attributesvaluesGroup.setBackground(SWTResourceManager.getColor(255, 255, 255));
		attributesvaluesGroup.setText("Attributes-Values");
		attributesvaluesGroup.setBounds(10, 130, 501, 69);

		final Label nameLabel = new Label(attributesvaluesGroup, SWT.NONE);
		nameLabel.setBackground(SWTResourceManager.getColor(255, 255, 255));
		nameLabel.setText("Attribute:");
		nameLabel.setBounds(10, 27, 60, 15);

		final Label valueLabel = new Label(attributesvaluesGroup, SWT.NONE);
		valueLabel.setBackground(SWTResourceManager.getColor(255, 255, 255));
		valueLabel.setText("Value:");
		valueLabel.setBounds(269, 27, 48, 15);

		bizDispo_Attribute_txt = new Text(attributesvaluesGroup, SWT.BORDER);
		bizDispo_Attribute_txt.setBounds(88, 24, 151, 25);

		bizDispoValue_txt = new Text(attributesvaluesGroup, SWT.BORDER);
		bizDispoValue_txt.setBounds(323, 24, 151, 25);

		final Group pleaseInsertTheGroup = new Group(composite_3, SWT.NONE);
		pleaseInsertTheGroup.setBackground(SWTResourceManager.getColor(255, 255, 255));
		pleaseInsertTheGroup.setText("Element Data (URI/Alias)");
		pleaseInsertTheGroup.setBounds(10, 25, 501, 99);

		final Label businessLocationLabel = new Label(pleaseInsertTheGroup, SWT.NONE);
		businessLocationLabel.setBackground(SWTResourceManager.getColor(255, 255, 255));
		businessLocationLabel.setBounds(20, 25, 163, 15);
		businessLocationLabel.setText("Business Disposition Tag:");

		bizDispoTag_txt = new Text(pleaseInsertTheGroup, SWT.BORDER);
		bizDispoTag_txt.setBounds(220, 20, 174, 25);

		final Label orSelectFromLabel = new Label(pleaseInsertTheGroup, SWT.NONE);
		orSelectFromLabel.setBackground(SWTResourceManager.getColor(255, 255, 255));
		orSelectFromLabel.setBounds(25, 53, 48, 15);
		orSelectFromLabel.setText("Name:");

		Dispo_Lname_txt = new Text(pleaseInsertTheGroup, SWT.BORDER);
		Dispo_Lname_txt.setBounds(220, 51, 174, 25);

		final Label inThisAreaLabel = new Label(composite_3, SWT.SHADOW_NONE);
		inThisAreaLabel.setFont(SWTResourceManager.getFont("Palatino Linotype", 10, SWT.BOLD));
		inThisAreaLabel.setBackground(SWTResourceManager.getColor(255, 255, 255));
		inThisAreaLabel.setText("Enter all the posible states that the products can enter after a business transaction");
		inThisAreaLabel.setBounds(10, 5, 494, 25);

		
		

		/*final Label*/ youMustFillLabel_1 = new Label(composite_3, SWT.NONE);
		youMustFillLabel_1.setForeground(SWTResourceManager.getColor(255, 0, 0));
		youMustFillLabel_1.setFont(SWTResourceManager.getFont("", 11, SWT.BOLD));
		youMustFillLabel_1.setVisible(false);
		youMustFillLabel_1.setText("You must fill in the form!");
		youMustFillLabel_1.setBounds(10, 390, 356, 25);

		

		
		groupDispoMod = new Group(composite_3, SWT.NONE);
		groupDispoMod.setBackground(SWTResourceManager.getColor(255, 255, 255));
		groupDispoMod.setText("Existing Element List");
		groupDispoMod.setBounds(520, 25, 228, 148);

		BizDispAvail_combo = new Combo(groupDispoMod, SWT.NONE);
		BizDispAvail_combo.setBackground(SWTResourceManager.getColor(255, 255, 255));
		BizDispAvail_combo.setBounds(20, 85,175, 23);
		BizDispAvail_combo.addMouseListener(new MouseAdapter() {
			public void mouseDown(final MouseEvent e) {
				
				if(BizDispAvail_combo.getItemCount() > 0)
					return;
				dataManager.setVocabularyId(EpcisConstants.DISPOSITION_ID);
				java.util.List<String> names = dataManager.getElementNames(regExpression.getText(),null);
				if(names.size() == 0)
				{
					youMustFillLabel_1.setText("No information available!");
					youMustFillLabel_1.setVisible(true);
					return;
				}
				populateCombo_Names(BizDispAvail_combo,names);
	
			}
			
		});

		final Button attributesButton_1 = new Button(groupDispoMod, SWT.NONE);
		attributesButton_1.setBounds(87, 114,36, 25);
		attributesButton_1.addMouseListener(new MouseAdapter() {
			public void mouseDown(final MouseEvent e) {
				
				if(BizDispAvail_combo.getText().equals(""))
					return;
				
				youMustFillLabel_1.setVisible(false);
				dataManager.setVocabularyId(EpcisConstants.DISPOSITION_ID);
				String uri = dataManager.getUri(BizDispAvail_combo.getSelectionIndex(), BizDispAvail_combo.getText());
				if(uri == null)
					return;
				
				
				java.util.List<Attribute> attributes = dataManager.getAttributes(uri);
				bizDispoTag_txt.setText(uri);
				Dispo_Lname_txt.setText(BizDispAvail_combo.getText());
				bizDispoTag_txt.setEditable(false);
				bizDispoTag_txt.setEnabled(false);
				Dispo_Lname_txt.setEditable(false);
				Dispo_Lname_txt.setEnabled(false);
				showAttributeTable2(dispo_attrVal_table,attributes);
				

				
			}
		});
		attributesButton_1.setImage(ResourceManager.getPluginImage(Activator.getDefault(), "icons/fromAgilo/find.png"));

		final Button button_8 = new Button(groupDispoMod, SWT.NONE);
		button_8.setToolTipText("Refresh");
		button_8.addMouseListener(new MouseAdapter() {
			public void mouseDown(final MouseEvent e) {
				BizDispAvail_combo.removeAll();
				
				bizDispoTag_txt.setText("");
				bizDispoTag_txt.setEditable(true);
				bizDispoTag_txt.setEnabled(true);
				Dispo_Lname_txt.setText("");
				Dispo_Lname_txt.setEditable(true);
				Dispo_Lname_txt.setEnabled(true);
				bizDispo_Attribute_txt.setText("");
				bizDispo_Attribute_txt.setEnabled(true);
				bizDispo_Attribute_txt.setEditable(true);
				bizDispoValue_txt.setText("");
				dispo_attrVal_table.removeAll();
				youMustFillLabel_1.setVisible(false);
			}
		});
		button_8.setImage(ResourceManager.getPluginImage(Activator.getDefault(), "icons/img/s_reload.png"));
		button_8.setBounds(201, 83, 25, 25);

		regExpression = new Text(groupDispoMod, SWT.BORDER);
		regExpression.setBounds(20, 45, 175, 25);

		final Label advancedSearchLabel = new Label(groupDispoMod, SWT.NONE);
		advancedSearchLabel.setBackground(SWTResourceManager.getColor(255, 255, 255));
		advancedSearchLabel.setFont(SWTResourceManager.getFont("", 10, SWT.BOLD | SWT.ITALIC));
		advancedSearchLabel.setText("Search");
		advancedSearchLabel.setBounds(20, 20, 175, 15);

		dispo_attrVal_table = new Table(composite_3, SWT.BORDER | SWT.FULL_SELECTION);
		dispo_attrVal_table.addMouseListener(new MouseAdapter() {
			public void mouseDown(final MouseEvent e) {
				if(e.button == 3)
				{
					removeAttrTableMenu.setVisible(true);
				}
				else if(e.button == 1)
				{
					bizDispo_Attribute_txt.setText(dispo_attrVal_table.getSelection()[0].getText(0));
					bizDispo_Attribute_txt.setEditable(false);
					bizDispoValue_txt.setText(dispo_attrVal_table.getSelection()[0].getText(1));
					
				}
			}
		});
		dispo_attrVal_table.setLinesVisible(true);
		dispo_attrVal_table.setHeaderVisible(true);
		dispo_attrVal_table.setBounds(10, 216, 501, 167);
		removeAttrTableMenu = new Menu(composite_3.getShell(), SWT.POP_UP);
		
		MenuItem remRow = new MenuItem(removeAttrTableMenu, SWT.CASCADE);
		remRow.setText("Remove Attribute");
		remRow.addSelectionListener(new SelectionAdapter(){
			public void widgetSelected(final SelectionEvent e){
				String mode = "3";
				boolean result = false;
				if(dispo_attrVal_table.getItemCount() == 0)
					return;
				if(bizDispo_Attribute_txt.getText().equals("") || bizDispoValue_txt.getText().equals("")){
					youMustFillLabel_1.setText("Please click on the attribute to remove");
					youMustFillLabel_1.setVisible(true);
					return;
				}
				if(bizDispo_Attribute_txt.getText().equals("Name"))
				{
					youMustFillLabel_1.setText("Name is required.You can only modify it!");
					youMustFillLabel_1.setVisible(true);
					return;
					
				}
				dataManager.setVocabularyId(EpcisConstants.DISPOSITION_ID);
				result = dataManager.masterDataAttributeEdit(bizDispoTag_txt.getText(), bizDispo_Attribute_txt.getText(), bizDispoValue_txt.getText(), mode);
				//updateAttrValue(bizDispoTag_txt.getText(),"urn:epcglobal:epcis:vtype:Disposition",bizDispo_Attribute_txt.getText(),bizDispoValue_txt.getText(),"3");
				if(result)
				{
					removeAttribute_Table(dispo_attrVal_table);
					youMustFillLabel_1.setText("Database was sucessfully updated");
					youMustFillLabel_1.setVisible(true);
				}
				else
				{
					youMustFillLabel_1.setText("Attribute could not be removed!");
					youMustFillLabel_1.setVisible(true);
					
				}
				bizDispo_Attribute_txt.setText("");
				bizDispoValue_txt.setText("");
				bizDispo_Attribute_txt.setEditable(true);
				bizDispo_Attribute_txt.setEnabled(true);
			
			}
		});
		
		

		final TableColumn newColumnTableColumn = new TableColumn(dispo_attrVal_table, SWT.NONE);
		newColumnTableColumn.setWidth(250);
		newColumnTableColumn.setText("Attribute");

		final TableColumn newColumnTableColumn_1 = new TableColumn(dispo_attrVal_table, SWT.NONE);
		newColumnTableColumn_1.setWidth(250);
		newColumnTableColumn_1.setText("Value");

		final Button clearButton = new Button(composite_3, SWT.NONE);
		clearButton.addMouseListener(new MouseAdapter() {
			public void mouseDown(final MouseEvent e) {
				
				bizDispo_Attribute_txt.setText("");
				bizDispo_Attribute_txt.setEditable(true);
				bizDispo_Attribute_txt.setEnabled(true);
				bizDispoValue_txt.setText("");
				dispo_attrVal_table.removeAll();
				
				Dispo_Lname_txt.setText("");
				Dispo_Lname_txt.setEditable(true);
				Dispo_Lname_txt.setEnabled(true);
				bizDispoTag_txt.setText("");
				bizDispoTag_txt.setEditable(true);
				bizDispoTag_txt.setEnabled(true);
		
			}
		});
		clearButton.setText("New Element");
		clearButton.setBounds(515, 355, 82, 25);

		final Button keepButton = new Button(composite_3, SWT.NONE);
		keepButton.addMouseListener(new MouseAdapter() {
			public void mouseDown(final MouseEvent e) {
				youMustFillLabel_1.setVisible(false);
				String mode = "1";
				boolean newRow = false;
				Attribute attr = null;
				boolean result = false;
				if(bizDispoTag_txt.getText().equals("") || Dispo_Lname_txt.getText().equals(""))
				{
					youMustFillLabel_1.setText("EPC id and Alias(Name) are mandatory");
					youMustFillLabel_1.setVisible(true);
					return;
				}
				if(!bizDispo_Attribute_txt.getText().equals("") && !bizDispoValue_txt.getText().equals(""))
				{
					newRow = insertNewRow(bizDispo_Attribute_txt.getText(),bizDispoValue_txt.getText(),dispo_attrVal_table);
					if(newRow)
					{
						TableItem row = new TableItem(dispo_attrVal_table,1);
						row.setText(new String[]{bizDispo_Attribute_txt.getText(),bizDispoValue_txt.getText()});
						
					}
					else
					{
						dispo_attrVal_table.getSelection()[0].setText(1,bizDispoValue_txt.getText());
						
					}
					attr = new Attribute(bizDispo_Attribute_txt.getText(),bizDispoValue_txt.getText());
				}
				dataManager.setVocabularyId(EpcisConstants.DISPOSITION_ID);
				if(bizDispoTag_txt.isEnabled())//new element
				{
										
					result = dataManager.masterDataElementEdit(bizDispoTag_txt.getText(), mode);
					if(!result)
					{
						youMustFillLabel_1.setVisible(true);
						youMustFillLabel_1.setText("Database update failed");
						return;
					}
					dataManager.masterDataAttributeEdit(bizDispoTag_txt.getText(), "Name", Dispo_Lname_txt.getText(), mode);
					if(attr != null)
						dataManager.masterDataAttributeEdit(bizDispoTag_txt.getText(), attr.getAttribute(), attr.getValue(), mode);
					bizDispo_Attribute_txt.setText("");
					bizDispoValue_txt.setText("");
					
				}
				else
				{
					mode = "2";
					result = dataManager.masterDataAttributeEdit(bizDispoTag_txt.getText(), bizDispo_Attribute_txt.getText(), bizDispoValue_txt.getText(), mode);
				}
				

				if(!result)
				{
					youMustFillLabel_1.setVisible(true);
					youMustFillLabel_1.setText("Database update failed");
					
				}	
				else
				{
					youMustFillLabel_1.setVisible(true);
					youMustFillLabel_1.setText("Database was sucessfully updated!");
					
				}
				bizDispo_Attribute_txt.setText("");
				bizDispo_Attribute_txt.setEditable(true);
				bizDispo_Attribute_txt.setEnabled(true);
				bizDispoValue_txt.setText("");	
				
				
				
				
				
				
			
			}
		});
		keepButton.setText("Save");
		keepButton.setBounds(600, 355, 48, 25);

		
	
		

		final TabItem busimTabItem = new TabItem(tabFolder, SWT.NONE);
		busimTabItem.setText("Business Steps");

		final Composite composite_1 = new Composite(tabFolder, SWT.NONE);
		busimTabItem.setControl(composite_1);

		final Group businessStepGroup = new Group(composite_1, SWT.NONE);
		businessStepGroup.setText("Element Data(URI/Alias)");
		businessStepGroup.setBackground(SWTResourceManager.getColor(255, 255, 255));
		businessStepGroup.setBounds(10, 30, 483, 104);

		final Label orYouCanLabel = new Label(businessStepGroup, SWT.WRAP);
		orYouCanLabel.setBackground(SWTResourceManager.getColor(255, 255, 255));
		orYouCanLabel.setBounds(30, 30, 97, 25);
		orYouCanLabel.setText("Business Step Tag:");

		BizStepText = new Text(businessStepGroup, SWT.BORDER);
		BizStepText.setBounds(210, 25, 172, 25);

		final Label nameLabel_1 = new Label(businessStepGroup, SWT.NONE);
		nameLabel_1.setBackground(SWTResourceManager.getColor(255, 255, 255));
		nameLabel_1.setText("Name:");
		nameLabel_1.setBounds(30, 60, 63, 15);

		BizStepName_txt = new Text(businessStepGroup, SWT.BORDER);
		BizStepName_txt.setBounds(210, 60, 172, 25);

		inThisAreaText = new StyledText(composite_1, SWT.NONE);
		inThisAreaText.setFont(SWTResourceManager.getFont("Palatino Linotype", 10, SWT.BOLD));
		inThisAreaText.setBackground(SWTResourceManager.getColor(255, 255, 255));
		inThisAreaText.setText("Please enter the various business steps");
		inThisAreaText.setBounds(10, 5, 483, 16);

		final Group attributesvaluesGroup_1 = new Group(composite_1, SWT.NONE);
		attributesvaluesGroup_1.setBackground(SWTResourceManager.getColor(255, 255, 255));
		attributesvaluesGroup_1.setText("Attributes-Values");
		attributesvaluesGroup_1.setBounds(10, 140, 483, 85);

		final Label attributeLabel = new Label(attributesvaluesGroup_1, SWT.NONE);
		attributeLabel.setBackground(SWTResourceManager.getColor(255, 255, 255));
		attributeLabel.setText("Attribute");
		attributeLabel.setBounds(20, 40, 68, 15);

		BizStepAttributeText = new Text(attributesvaluesGroup_1, SWT.BORDER);
		BizStepAttributeText.setBounds(94, 37, 155, 25);

		final Label valueLabel_1 = new Label(attributesvaluesGroup_1, SWT.NONE);
		valueLabel_1.setBackground(SWTResourceManager.getColor(255, 255, 255));
		valueLabel_1.setText("Value");
		valueLabel_1.setBounds(257, 40, 39, 15);

		BizStepValueText = new Text(attributesvaluesGroup_1, SWT.BORDER);
		BizStepValueText.setBounds(302, 37, 155, 25);


		/*final Label*/ youMustAddLabel = new Label(composite_1, SWT.NONE);
 youMustAddLabel.setVisible(false);
		youMustAddLabel.setForeground(SWTResourceManager.getColor(255, 0, 0));
		youMustAddLabel.setFont(SWTResourceManager.getFont("", 11, SWT.BOLD));
		youMustAddLabel.setText("You must add attributes!");
		youMustAddLabel.setBounds(10, 405, 330, 25);

		final Group BizStep_group = new Group(composite_1, SWT.NONE);
		BizStep_group.setText("Existing Element List");
		BizStep_group.setBounds(500, 30, 229, 155);

		BizStepMod_combo = new Combo(BizStep_group, SWT.NONE);
		BizStepMod_combo.addMouseListener(new MouseAdapter() {
			public void mouseDown(final MouseEvent e) {
				
				if(BizStepMod_combo.getItemCount() > 0)
					return;
				dataManager.setVocabularyId(EpcisConstants.BUSINESS_STEP_ID);
				java.util.List<String> names = dataManager.getElementNames(specificURI_BStep_txt.getText(),null);
				if(names.size() == 0)
				{
					youMustAddLabel.setText("No information available!");
					youMustAddLabel.setVisible(true);
					return;
				}
				populateCombo_Names(BizStepMod_combo,names);
				
				
				
				
			}
		});
		BizStepMod_combo.setBounds(10, 92, 175, 23);
		
		
		
		
		
		

		
		
		
		

		final Button button_1 = new Button(BizStep_group, SWT.NONE);
		button_1.addMouseListener(new MouseAdapter() {
			public void mouseDown(final MouseEvent e) {
				
				
				if(BizStepMod_combo.getText().equals(""))
					return;
				
				youMustAddLabel.setVisible(false);
				dataManager.setVocabularyId(EpcisConstants.BUSINESS_STEP_ID);
				String uri = dataManager.getUri(BizStepMod_combo.getSelectionIndex(), BizStepMod_combo.getText());
				if(uri == null)
					return;
				
				
				java.util.List<Attribute> attributes = dataManager.getAttributes(uri);
				BizStepText.setText(uri);
				BizStepName_txt.setText(BizStepMod_combo.getText());
				BizStepText.setEditable(false);
				BizStepText.setEnabled(false);
				BizStepName_txt.setEditable(false);
				BizStepName_txt.setEnabled(false);
				showAttributeTable2(Bstep_attrVal_table,attributes);
				
			
			}
		});
		button_1.setImage(ResourceManager.getPluginImage(Activator.getDefault(), "icons/fromAgilo/find.png"));
		button_1.setBounds(81, 121, 36, 25);

		final Button button_9 = new Button(BizStep_group, SWT.NONE);
		button_9.setToolTipText("Refresh");
		button_9.addMouseListener(new MouseAdapter() {
			public void mouseDown(final MouseEvent e) {
				
				BizStepMod_combo.removeAll();
				BizStepText.setText("");
				BizStepText.setEditable(true);
				BizStepText.setEnabled(true);
				BizStepName_txt.setText("");
				BizStepName_txt.setEditable(true);
				BizStepName_txt.setEnabled(true);
				BizStepAttributeText.setText("");
				BizStepValueText.setText("");
				Bstep_attrVal_table.removeAll();
				youMustAddLabel.setVisible(false);
			}
		});
		button_9.setImage(ResourceManager.getPluginImage(Activator.getDefault(), "icons/img/s_reload.png"));
		button_9.setBounds(191, 90, 28, 25);

		final Label advancedSearchLabel_1 = new Label(BizStep_group, SWT.NONE);
		advancedSearchLabel_1.setFont(SWTResourceManager.getFont("", 10, SWT.BOLD | SWT.ITALIC));
		advancedSearchLabel_1.setText("Search");
		advancedSearchLabel_1.setBounds(10, 27, 175, 15);

		specificURI_BStep_txt = new Text(BizStep_group, SWT.BORDER);
		specificURI_BStep_txt.setBounds(10, 55, 175, 25);

		
		genModificationMenu = new Menu(composite_1.getShell(), SWT.POP_UP);
		
		

		

		

		

		Bstep_attrVal_table = new Table(composite_1, SWT.BORDER | SWT.FULL_SELECTION);
		Bstep_attrVal_table.addMouseListener(new MouseAdapter() {
			public void mouseDown(final MouseEvent e) {
				if(e.button == 3)
				{
					removeAttrTableBStepMenu.setVisible(true);
				}
				else if(e.button == 1)
				{
					BizStepAttributeText.setText(Bstep_attrVal_table.getSelection()[0].getText(0));
					BizStepValueText.setText(Bstep_attrVal_table.getSelection()[0].getText(1));
					
				}
			}
		});
		Bstep_attrVal_table.setLinesVisible(true);
		Bstep_attrVal_table.setHeaderVisible(true);
		Bstep_attrVal_table.setBounds(10, 238, 483, 161);

		
		//removeAttrTableBStepMenu = new Menu()
		final TableColumn newColumnTableColumn_2 = new TableColumn(Bstep_attrVal_table, SWT.NONE);
		newColumnTableColumn_2.setWidth(250);
		newColumnTableColumn_2.setText("Attribute");

		final TableColumn newColumnTableColumn_3 = new TableColumn(Bstep_attrVal_table, SWT.NONE);
		newColumnTableColumn_3.setWidth(250);
		newColumnTableColumn_3.setText("Value");
		
		
		removeAttrTableBStepMenu = new Menu(composite_1.getShell(), SWT.POP_UP);
		
		MenuItem remBStepRow = new MenuItem(removeAttrTableBStepMenu, SWT.CASCADE);
		remBStepRow.setText("Remove Attribute");
		remBStepRow.addSelectionListener(new SelectionAdapter(){
			public void widgetSelected(final SelectionEvent e){
				String mode = "3";
				boolean result = false;
				if(Bstep_attrVal_table.getItemCount() == 0)
					return;
				if(BizStepAttributeText.getText().equals("") || BizStepValueText.getText().equals("")){
					youMustAddLabel.setText("Please click on the attribute to remove");
					youMustAddLabel.setVisible(true);
					return;
				}
				if(BizStepAttributeText.getText().equals("Name"))
				{
					youMustAddLabel.setText("Name is required;you can only modify it!");
					youMustAddLabel.setVisible(true);
					return;
					
				}
				dataManager.setVocabularyId(EpcisConstants.BUSINESS_STEP_ID);
				result = dataManager.masterDataAttributeEdit(BizStepText.getText(), BizStepAttributeText.getText(), BizStepValueText.getText(), mode);
				
				
				if(result)
				{
					removeAttribute_Table(Bstep_attrVal_table);
					youMustAddLabel.setText("Database was sucessfully updated");
					youMustAddLabel.setVisible(true);
				}
				else
				{
					youMustAddLabel.setText("Attribute could not be removed!");
					youMustAddLabel.setVisible(true);
					
				}
				
				
				
				
				BizStepAttributeText.setText("");
				BizStepAttributeText.setEditable(true);
				BizStepAttributeText.setEnabled(true);
				BizStepValueText.setText("");
			
			}
		});

		final Button newButton = new Button(composite_1, SWT.NONE);
		newButton.addMouseListener(new MouseAdapter() {
			public void mouseDown(final MouseEvent e) {
				
				BizStepAttributeText.setText("");
				BizStepValueText.setText("");
				BizStepAttributeText.setEnabled(true);
				BizStepAttributeText.setEditable(true);
				Bstep_attrVal_table.removeAll();
			
				BizStepText.setText("");
				BizStepText.setEditable(true);
				BizStepText.setEnabled(true);
				BizStepName_txt.setText("");
				BizStepName_txt.setEditable(true);
				BizStepName_txt.setEnabled(true);
			}
		});
		newButton.setText("New Element");
		newButton.setBounds(495, 375, 75, 25);

		final Button keepButton_1 = new Button(composite_1, SWT.NONE);
		keepButton_1.addMouseListener(new MouseAdapter() {
			public void mouseDown(final MouseEvent e) {
				
				String mode = "1";
				boolean result = false;
				youMustAddLabel.setVisible(false);
				boolean newRow = false;
				Attribute attr = null;
				if(BizStepText.getText().equals("") || BizStepName_txt.getText().equals(""))
				{
					youMustAddLabel.setText("EPC id and Alias(Name) are mandatory");
					youMustAddLabel.setVisible(true);
					return;
				}
				if(!BizStepAttributeText.getText().equals("") && !BizStepValueText.getText().equals(""))
				{
					newRow = insertNewRow(BizStepAttributeText.getText(),BizStepValueText.getText(),Bstep_attrVal_table);
					if(newRow)
					{
						TableItem row = new TableItem(Bstep_attrVal_table,1);
						row.setText(new String[]{BizStepAttributeText.getText(),BizStepValueText.getText()});
						
					}
					else
					{
						Bstep_attrVal_table.getSelection()[0].setText(1,BizStepValueText.getText());
						
					}
					attr = new Attribute(BizStepAttributeText.getText(),BizStepValueText.getText());
				}
				dataManager.setVocabularyId(EpcisConstants.BUSINESS_STEP_ID);
				if(BizStepText.isEnabled())//new element
				{
				
					
					result = dataManager.masterDataElementEdit(BizStepText.getText(), mode);
					if(!result)
					{
						youMustAddLabel.setVisible(true);
						youMustAddLabel.setText("Database update failed");
						return;
					}
					dataManager.masterDataAttributeEdit(BizStepText.getText(), "Name", BizStepName_txt.getText(), mode);
					if(attr != null)
						dataManager.masterDataAttributeEdit(BizStepText.getText(), attr.getAttribute(), attr.getValue(), mode);
				
					
					
					BizStepAttributeText.setText("");
					BizStepValueText.setText("");
					
				}
				else
				{
					mode = "2";
					result = dataManager.masterDataAttributeEdit(BizStepText.getText(), BizStepAttributeText.getText(), BizStepValueText.getText(), mode);
				}
					
					
				if(!result)
				{
					youMustAddLabel.setVisible(true);
					youMustAddLabel.setText("Database update failed");
					
				}	
				else
				{
					youMustAddLabel.setVisible(true);
					youMustAddLabel.setText("Database was sucessfully updated!");
					
				}
				BizStepAttributeText.setText("");
				BizStepAttributeText.setEditable(true);
				BizStepAttributeText.setEnabled(true);
				BizStepValueText.setText("");	
				
			
			}
				
				
				
				
			
		});
		keepButton_1.setText("Save");
		keepButton_1.setBounds(575, 375, 48, 25);
		/*final Label*/
		
		
				
		
		
		

		//final Button 

		//final Button 

		//final Button 
	    /*final Composite*/	    /*final Button*/
	    /*final Button*/
	    /*final Button*/
	    /*final Button*/
 /*final Button*/
 /*final Label*/
 /*final Button*/
	

	    /*final TabItem*/ readersTabItem = new TabItem(tabFolder, SWT.NONE);
	    readersTabItem.setText("Read Points");

	    final Composite composite_8 = new Composite(tabFolder, SWT.NONE);
	    readersTabItem.setControl(composite_8);

	    /*final Group*/ attributevaluesGroup_1 = new Group(composite_8, SWT.NONE);
	    attributevaluesGroup_1.setText("Attribute-Values");
	    attributevaluesGroup_1.setBounds(10, 141, 501, 82);

	    final Label attributeLabel_3 = new Label(attributevaluesGroup_1, SWT.NONE);
	    attributeLabel_3.setText("Attribute:");
	    attributeLabel_3.setBounds(15, 33, 48, 15);

	    final Label valueLabel_4 = new Label(attributevaluesGroup_1, SWT.NONE);
	    valueLabel_4.setText("Value:");
	    valueLabel_4.setBounds(254, 36, 60, 15);

	    BizReader_Attr_txt = new Text(attributevaluesGroup_1, SWT.BORDER);
	    BizReader_Attr_txt.setBounds(69, 30, 161, 25);

	    BizReaderValue_txt = new Text(attributevaluesGroup_1, SWT.BORDER);
	    BizReaderValue_txt.setBounds(324, 33, 155, 25);

	    /*final Button*/
	    final Label pleaseInsertTheLabel_2 = new Label(composite_8, SWT.NONE);
	    pleaseInsertTheLabel_2.setFont(SWTResourceManager.getFont("Palatino Linotype", 10, SWT.BOLD));
	    pleaseInsertTheLabel_2.setText("Please insert the information for your readers (tags, attributes)");
	    pleaseInsertTheLabel_2.setBounds(10, 0, 476, 26);

	    

	    /*final Label*/ Reader_Error_lbl = new Label(composite_8, SWT.NONE);
	    Reader_Error_lbl.setVisible(false);
	    Reader_Error_lbl.setForeground(SWTResourceManager.getColor(255, 0, 0));
	    Reader_Error_lbl.setFont(SWTResourceManager.getFont("", 11, SWT.BOLD));
	    Reader_Error_lbl.setText("Required data are missing!");
	    Reader_Error_lbl.setBounds(20, 415, 354, 25);
	    /*final Button*/
	    final Group modifyReadPoint_group = new Group(composite_8, SWT.NONE);
	    modifyReadPoint_group.setText("Existing Element List");
	    modifyReadPoint_group.setBounds(517, 25, 242, 157);

	    BizReadPoints_combo = new Combo(modifyReadPoint_group, SWT.NONE);
	    BizReadPoints_combo.addMouseListener(new MouseAdapter() {
	    	public void mouseDown(final MouseEvent e) {
	    			    	
	    		if(BizReadPoints_combo.getItemCount() > 0)
					return;
				dataManager.setVocabularyId(EpcisConstants.READ_POINT_ID);
				java.util.List<String> names = dataManager.getElementNames(specificURI_RPoint_txt.getText(),null);
				if(names.size() == 0)
				{
					Reader_Error_lbl.setText("No information available!");
					Reader_Error_lbl.setVisible(true);
					return;
				}
				populateCombo_Names(BizReadPoints_combo,names);
	    	
	    	
	    	}
	    });
	    BizReadPoints_combo.setBounds(10, 87, 192, 23);

	    final Button searchReadPoints_button = new Button(modifyReadPoint_group, SWT.NONE);
	    searchReadPoints_button.addMouseListener(new MouseAdapter() {
	    	public void mouseDown(final MouseEvent e) {
	    		
	    		
	    		
				if(BizReadPoints_combo.getText().equals(""))
					return;
				
				Reader_Error_lbl.setVisible(false);
				dataManager.setVocabularyId(EpcisConstants.READ_POINT_ID);
				String uri = dataManager.getUri(BizReadPoints_combo.getSelectionIndex(), BizReadPoints_combo.getText());
				if(uri == null)
					return;
				
				
				java.util.List<Attribute> attributes = dataManager.getAttributes(uri);
				BizReaderTag_txt.setText(uri);
				BizReaderName_txt.setText(BizReadPoints_combo.getText());
				BizReaderTag_txt.setEditable(false);
				BizReaderTag_txt.setEnabled(false);
				BizReaderName_txt.setEditable(false);
				BizReaderName_txt.setEnabled(false);
				showAttributeTable2(ReadPointsAttrValTable,attributes);


	    		
	    		
	    		
	    		
	    		
	    		
	    		
	    		
	    

	    	}
	    });
	    searchReadPoints_button.setImage(ResourceManager.getPluginImage(Activator.getDefault(), "icons/fromAgilo/find.png"));
	    searchReadPoints_button.setBounds(90, 122, 48, 25);

	    final Button button_10 = new Button(modifyReadPoint_group, SWT.NONE);
	    button_10.addMouseListener(new MouseAdapter() {
	    	public void mouseDown(final MouseEvent e) {
	    		BizReadPoints_combo.removeAll();
	    		
	    		
	    		BizReaderTag_txt.setEditable(true);
	    		BizReaderTag_txt.setText("");
	    		BizReaderTag_txt.setEnabled(true);
	    		BizReaderName_txt.setText("");
	    		BizReaderName_txt.setEditable(true);
	    		BizReaderName_txt.setEnabled(true);
	    		BizReader_Attr_txt.setEnabled(true);
	    		BizReader_Attr_txt.setText("");
	    		BizReader_Attr_txt.setEditable(true);
	    		BizReaderValue_txt.setText("");
	    		Reader_Error_lbl.setVisible(false);

	    		
	    		ReadPointsAttrValTable.removeAll();





	    		
	    	}
	    });
	    button_10.setImage(ResourceManager.getPluginImage(Activator.getDefault(), "icons/img/s_reload.png"));
	    button_10.setBounds(205, 85, 26, 25);

	    final Label existingElementListLabel = new Label(modifyReadPoint_group, SWT.NONE);
	    existingElementListLabel.setFont(SWTResourceManager.getFont("", 10, SWT.BOLD | SWT.ITALIC));
	    existingElementListLabel.setText("Search");
	    existingElementListLabel.setBounds(10, 23, 192, 15);

	    specificURI_RPoint_txt = new Text(modifyReadPoint_group, SWT.BORDER);
	    specificURI_RPoint_txt.setBounds(10, 55, 192, 25);
	    
	    ReadPointsModificationMenu = new Menu(composite_8.getShell(), SWT.POP_UP);

	    final Group elementDataurialiasGroup = new Group(composite_8, SWT.NONE);
	    elementDataurialiasGroup.setText("Element Data(URI/Alias)");
	    elementDataurialiasGroup.setBounds(10, 23, 501, 112);

	    final Label readerTagLabel = new Label(elementDataurialiasGroup, SWT.NONE);
	    readerTagLabel.setBounds(35, 28,69, 15);
	    readerTagLabel.setText("Reader Tag:");

	    BizReaderTag_txt = new Text(elementDataurialiasGroup, SWT.BORDER);
	    BizReaderTag_txt.setBounds(129, 25,148, 25);

	    final Label logicalNameLabel_2 = new Label(elementDataurialiasGroup, SWT.NONE);
	    logicalNameLabel_2.setBounds(35, 68,83, 15);
	    logicalNameLabel_2.setText("Logical Name:");

	    BizReaderName_txt = new Text(elementDataurialiasGroup, SWT.BORDER);
	    BizReaderName_txt.setBounds(129, 65,148, 25);

	   
	   

	    ReadPointsAttrValTable = new Table(composite_8, SWT.FULL_SELECTION | SWT.BORDER);
	    ReadPointsAttrValTable.addMouseListener(new MouseAdapter() {
	    	public void mouseDown(final MouseEvent e) {
	    		if(e.button == 3)
				{
					removeAttrTableReadPointMenu.setVisible(true);
				}
				else if(e.button == 1)
				{
					BizReader_Attr_txt.setText(ReadPointsAttrValTable.getSelection()[0].getText(0));
					BizReaderValue_txt.setText(ReadPointsAttrValTable.getSelection()[0].getText(1));
					
				}
	    	}
	    });
	    ReadPointsAttrValTable.setLinesVisible(true);
	    ReadPointsAttrValTable.setHeaderVisible(true);
	    ReadPointsAttrValTable.setBounds(10, 229, 501, 183);

	    final TableColumn newColumnTableColumn_4 = new TableColumn(ReadPointsAttrValTable, SWT.NONE);
	    newColumnTableColumn_4.setWidth(250);
	    newColumnTableColumn_4.setText("Attribute");

	    final TableColumn newColumnTableColumn_5 = new TableColumn(ReadPointsAttrValTable, SWT.NONE);
	    newColumnTableColumn_5.setWidth(250);
	    newColumnTableColumn_5.setText("Value");
	    
	    
	    removeAttrTableReadPointMenu = new Menu(composite_8.getShell(), SWT.POP_UP);
		
		MenuItem remBReaderRow = new MenuItem(removeAttrTableReadPointMenu, SWT.CASCADE);
		remBReaderRow.setText("Remove Attribute");
		remBReaderRow.addSelectionListener(new SelectionAdapter(){
			public void widgetSelected(final SelectionEvent e){
				boolean result = false;
				String mode = "3";
				if(ReadPointsAttrValTable.getItemCount() == 0)
					return;
				if(BizReader_Attr_txt.getText().equals("") || BizReaderValue_txt.getText().equals("")){
					Reader_Error_lbl.setText("Please click on the attribute to remove");
					Reader_Error_lbl.setVisible(true);
					return;
				}
				if(BizReader_Attr_txt.getText().equals("Name"))
				{
					Reader_Error_lbl.setVisible(true);
					Reader_Error_lbl.setText("Can not remove name! It is required");
					return;
				}
				
				
				dataManager.setVocabularyId(EpcisConstants.READ_POINT_ID);
				result = dataManager.masterDataAttributeEdit(BizReaderTag_txt.getText(), BizReader_Attr_txt.getText(), BizReaderValue_txt.getText(), mode);
				
				
				if(result)
				{
					removeAttribute_Table(ReadPointsAttrValTable);
					Reader_Error_lbl.setText("Database was sucessfully updated");
					Reader_Error_lbl.setVisible(true);
				}
				else
				{
					Reader_Error_lbl.setText("Attribute could not be removed!");
					Reader_Error_lbl.setVisible(true);
					
				}
				
				
				
				
				
				BizReader_Attr_txt.setText("");
				BizReader_Attr_txt.setEditable(true);
				BizReader_Attr_txt.setEnabled(true);
				BizReaderValue_txt.setText("");
			
			}
		});

	    final Button newButton_1 = new Button(composite_8, SWT.NONE);
	    newButton_1.addMouseListener(new MouseAdapter() {
	    	public void mouseDown(final MouseEvent e) {
	    		Reader_Error_lbl.setVisible(false);
	    		BizReader_Attr_txt.setText("");
	    		BizReader_Attr_txt.setEditable(true);
	    		BizReader_Attr_txt.setEnabled(true);
				BizReaderValue_txt.setText("");
				ReadPointsAttrValTable.removeAll();
			
				BizReaderTag_txt.setText("");
				BizReaderTag_txt.setEditable(true);
				BizReaderTag_txt.setEnabled(true);
				BizReaderName_txt.setText("");
				BizReaderName_txt.setEditable(true);
				BizReaderName_txt.setEnabled(true);
	    	}
	    });
	    newButton_1.setText("New Element");
	    newButton_1.setBounds(515, 385, 82, 25);

	    final Button keepButton_2 = new Button(composite_8, SWT.NONE);
	    keepButton_2.addMouseListener(new MouseAdapter() {
	    	public void mouseDown(final MouseEvent e) {

	    		String mode = "1";
				boolean result = false;
	    		Reader_Error_lbl.setVisible(false);
				boolean newRow = false;
				Attribute attr = null;
				if(BizReaderTag_txt.getText().equals("") || BizReaderName_txt.getText().equals(""))
				{
					Reader_Error_lbl.setText("EPC id and Alias(Name) are mandatory");
					Reader_Error_lbl.setVisible(true);
					return;
				}
				if(!BizReader_Attr_txt.getText().equals("") && !BizReaderValue_txt.getText().equals(""))
				{
					newRow = insertNewRow(BizReader_Attr_txt.getText(),BizReaderValue_txt.getText(),ReadPointsAttrValTable);
					if(newRow)
					{
						TableItem row = new TableItem(ReadPointsAttrValTable,1);
						row.setText(new String[]{BizReader_Attr_txt.getText(),BizReaderValue_txt.getText()});
						
					}
					else
					{
						ReadPointsAttrValTable.getSelection()[0].setText(1,BizReaderValue_txt.getText());
						
					}
					attr = new Attribute(BizReader_Attr_txt.getText(),BizReaderValue_txt.getText());
				}
				dataManager.setVocabularyId(EpcisConstants.READ_POINT_ID);
				if(BizReaderTag_txt.isEnabled())//new element
				{
					
					
					result = dataManager.masterDataElementEdit(BizReaderTag_txt.getText(), mode);
					if(!result)
					{
						Reader_Error_lbl.setVisible(true);
						Reader_Error_lbl.setText("Database update failed");
						return;
					}
					dataManager.masterDataAttributeEdit(BizReaderTag_txt.getText(), "Name", BizReaderName_txt.getText(), mode);
					if(attr != null)
						dataManager.masterDataAttributeEdit(BizReaderTag_txt.getText(), attr.getAttribute(), attr.getValue(), mode);
				
					
					
					
					BizReader_Attr_txt.setText("");
					BizReaderValue_txt.setText("");
					
				}
				else
				{
					mode = "2";
					result = dataManager.masterDataAttributeEdit(BizReaderTag_txt.getText(), BizReader_Attr_txt.getText(), BizReaderValue_txt.getText(), mode);
				}
					
				if(!result)
				{
					Reader_Error_lbl.setVisible(true);
					Reader_Error_lbl.setText("Database update failed");
					
				}	
				else
				{
					Reader_Error_lbl.setVisible(true);
					Reader_Error_lbl.setText("Database was sucessfully updated!");
					
				}	
				
				BizReader_Attr_txt.setText("");
				BizReader_Attr_txt.setEditable(true);
				BizReader_Attr_txt.setEnabled(true);
				BizReaderValue_txt.setText("");	
			
	    	}
	    });
	    keepButton_2.setText("Save");
	    keepButton_2.setBounds(608, 385, 48, 25);
		
		

		

	    

	    final TabItem businessTransactionTypeTabItem = new TabItem(tabFolder, SWT.NONE);
	    businessTransactionTypeTabItem.setText("Business Transaction Type");

	    final Composite composite = new Composite(tabFolder, SWT.NONE);
	    businessTransactionTypeTabItem.setControl(composite);

	    final Group attributevaluesGroup = new Group(composite, SWT.NONE);
	    attributevaluesGroup.setText("Attribute-Values:");
	    attributevaluesGroup.setBounds(10, 125, 478, 82);

	    final Label attributeLabel_1 = new Label(attributevaluesGroup, SWT.NONE);
	    attributeLabel_1.setText("Attribute:");
	    attributeLabel_1.setBounds(10, 33, 55, 15);

	    final Label valueLabel_2 = new Label(attributevaluesGroup, SWT.NONE);
	    valueLabel_2.setText("Value:");
	    valueLabel_2.setBounds(238, 33, 55, 15);

	    BizTransType_attr = new Text(attributevaluesGroup, SWT.BORDER);
	    BizTransType_attr.setBounds(70, 30, 149, 25);

	    BizTransType_val = new Text(attributevaluesGroup, SWT.BORDER);
	    BizTransType_val.setBounds(299, 30, 163, 25);

	    /*final Button*/

	  

	    /*final Label*/ requiredDataAreLabel = new Label(composite, SWT.NONE);
	    requiredDataAreLabel.setVisible(false);
	    requiredDataAreLabel.setForeground(SWTResourceManager.getColor(255, 0, 0));
	    requiredDataAreLabel.setFont(SWTResourceManager.getFont("", 11, SWT.BOLD));
	    requiredDataAreLabel.setText("Required data are missing!");
	    requiredDataAreLabel.setBounds(10, 405, 314, 15);

	    final Label pleaseDescribeTheLabel = new Label(composite, SWT.NONE);
	    pleaseDescribeTheLabel.setFont(SWTResourceManager.getFont("Palatino Linotype", 10, SWT.BOLD));
	    pleaseDescribeTheLabel.setText("Please enter the business transaction types");
	    pleaseDescribeTheLabel.setBounds(15, 5, 355, 14);

	    final Group BizTransactionType_group = new Group(composite, SWT.NONE);
	    BizTransactionType_group.setText("Existing Element List");
	    BizTransactionType_group.setBounds(490, 25, 249, 167);

	    combo = new Combo(BizTransactionType_group, SWT.NONE);
	    combo.addMouseListener(new MouseAdapter() {
	    	public void mouseDown(final MouseEvent e) {

	    		if(combo.getItemCount() > 0)
					return;
				dataManager.setVocabularyId(EpcisConstants.BUSINESS_TRANSACTION_TYPE_ID);
				java.util.List<String> names = dataManager.getElementNames(specificURI_TransType_txt.getText(),null);
				if(names.size() == 0)
				{
					requiredDataAreLabel.setText("No information available!");
					requiredDataAreLabel.setVisible(true);
					return;
				}
				populateCombo_Names(combo,names);
	    	
	    	}
	    });
	    combo.setBounds(10, 85, 192, 23);
	    
	    
	    
	    
	    TransTypeModificationMenu = new Menu(composite.getShell(), SWT.POP_UP);
		
		

	

	    final Button searchBizTransType = new Button(BizTransactionType_group, SWT.NONE);
	    searchBizTransType.addMouseListener(new MouseAdapter() {
	    	public void mouseDown(final MouseEvent e) {
	    		
	    		if(combo.getText().equals(""))
					return;
	    		
				youMustFillLabel_1.setVisible(false);
				dataManager.setVocabularyId(EpcisConstants.BUSINESS_TRANSACTION_TYPE_ID);
				String uri = dataManager.getUri(combo.getSelectionIndex(), combo.getText());
				if(uri == null)
					return;
				
				
				java.util.List<Attribute> attributes = dataManager.getAttributes(uri);
				BizTransType_EPCTag.setText(uri);
				BizTransType_LName.setText(combo.getText());
				BizTransType_EPCTag.setEditable(false);
				BizTransType_EPCTag.setEnabled(false);
				BizTransType_LName.setEditable(false);
				BizTransType_LName.setEnabled(false);
				showAttributeTable2(AttrVal_TransType_Table,attributes);
				
	    	}
	    });
	    searchBizTransType.setImage(ResourceManager.getPluginImage(Activator.getDefault(), "icons/fromAgilo/find.png"));
	    searchBizTransType.setBounds(87, 124, 48, 25);

	    final Button button_11 = new Button(BizTransactionType_group, SWT.NONE);
	    button_11.addMouseListener(new MouseAdapter() {
	    	public void mouseDown(final MouseEvent e) {
	    		
	    		combo.removeAll();
	    		BizTransType_EPCTag.setEditable(true);
	    		BizTransType_EPCTag.setText("");
	    		BizTransType_EPCTag.setEnabled(true);
	    		BizTransType_LName.setText("");
	    		BizTransType_LName.setEditable(true);
	    		BizTransType_LName.setEnabled(true);
	    		BizTransType_attr.setEnabled(true);
	    		BizTransType_attr.setText("");
	    		BizTransType_attr.setEditable(true);
	    		BizTransType_val.setText("");
	    		requiredDataAreLabel.setVisible(false);


	    		AttrVal_TransType_Table.removeAll();





	    	}
	    });
	    button_11.setImage(ResourceManager.getPluginImage(Activator.getDefault(), "icons/img/s_reload.png"));
	    button_11.setBounds(208, 85, 28, 25);

	    final Label advancedSearchLabel_2 = new Label(BizTransactionType_group, SWT.NONE);
	    advancedSearchLabel_2.setFont(SWTResourceManager.getFont("", 10, SWT.BOLD | SWT.ITALIC));
	    advancedSearchLabel_2.setText("Search");
	    advancedSearchLabel_2.setBounds(10, 20, 107, 15);

	    specificURI_TransType_txt = new Text(BizTransactionType_group, SWT.BORDER);
	    specificURI_TransType_txt.setBounds(10, 45, 192, 25);


	    final Group elementDataurialiasGroup_1 = new Group(composite, SWT.NONE);
	    elementDataurialiasGroup_1.setText("Element Data (URI/Alias)");
	    elementDataurialiasGroup_1.setBounds(10, 25, 478, 100);
	    final Label typeUriLabel = new Label(elementDataurialiasGroup_1, SWT.NONE);
	    typeUriLabel.setBounds(10, 31,122, 15);
	    typeUriLabel.setText("Business Type Tag:");

	    BizTransType_EPCTag = new Text(elementDataurialiasGroup_1, SWT.BORDER);
	    BizTransType_EPCTag.setBounds(150, 20,171, 25);

	    final Label nameLabel_2 = new Label(elementDataurialiasGroup_1, SWT.NONE);
	    nameLabel_2.setBounds(10, 69,59, 15);
	    nameLabel_2.setText("Name:");

	    BizTransType_LName = new Text(elementDataurialiasGroup_1, SWT.BORDER);
	    BizTransType_LName.setBounds(150, 55,170, 25);

	    AttrVal_TransType_Table = new Table(composite, SWT.BORDER | SWT.FULL_SELECTION);
	    AttrVal_TransType_Table.addMouseListener(new MouseAdapter() {
	    	public void mouseDown(final MouseEvent e) {
	    		if(e.button == 3)
				{
	    			removeAttrTableTransTypeMenu.setVisible(true);
				}
				else if(e.button == 1)
				{
					BizTransType_attr.setText(AttrVal_TransType_Table.getSelection()[0].getText(0));
					BizTransType_val.setText(AttrVal_TransType_Table.getSelection()[0].getText(1));
					
				}
	    	}
	    });
	    AttrVal_TransType_Table.setLinesVisible(true);
	    AttrVal_TransType_Table.setHeaderVisible(true);
	    AttrVal_TransType_Table.setBounds(10, 213, 478, 185);

	    final TableColumn newColumnTableColumn_6 = new TableColumn(AttrVal_TransType_Table, SWT.NONE);
	    newColumnTableColumn_6.setWidth(250);
	    newColumnTableColumn_6.setText("Attribute");

	    final TableColumn newColumnTableColumn_7 = new TableColumn(AttrVal_TransType_Table, SWT.NONE);
	    newColumnTableColumn_7.setWidth(250);
	    newColumnTableColumn_7.setText("Value");
	    
	    
	    removeAttrTableTransTypeMenu = new Menu(composite_1.getShell(), SWT.POP_UP);
		
		MenuItem remTransTypeRow = new MenuItem(removeAttrTableTransTypeMenu, SWT.CASCADE);
		remTransTypeRow.setText("Remove Attribute");
		remTransTypeRow.addSelectionListener(new SelectionAdapter(){
			public void widgetSelected(final SelectionEvent e){
				boolean result = false;
				String mode = "3";
				if(AttrVal_TransType_Table.getItemCount() == 0)
					return;
				if(BizTransType_attr.getText().equals("Name"))
				{
					requiredDataAreLabel.setVisible(true);
					requiredDataAreLabel.setText("Can not remove name. It is required");
					return;
				}
				
				dataManager.setVocabularyId(EpcisConstants.BUSINESS_TRANSACTION_TYPE_ID);
				result = dataManager.masterDataAttributeEdit(BizTransType_EPCTag.getText(), BizTransType_attr.getText(), BizTransType_val.getText(), mode);
				
				
				if(result)
				{
					removeAttribute_Table(AttrVal_TransType_Table);
					requiredDataAreLabel.setText("Database was sucessfully updated");
					requiredDataAreLabel.setVisible(true);
				}
				else
				{
					requiredDataAreLabel.setText("Attribute could not be removed!");
					requiredDataAreLabel.setVisible(true);
					
				}
				
				
				
				
				
				//updateAttrValue(BizTransType_EPCTag.getText(),"urn:epcglobal:epcis:vtype:BusinessTransactionType",BizTransType_attr.getText(),BizTransType_val.getText(),"3");
				
				BizTransType_attr.setText("");
				BizTransType_attr.setEditable(true);
				BizTransType_attr.setEnabled(true);
				BizTransType_val.setText("");
			
			}
		});


	    final Button keepButton_3 = new Button(composite, SWT.NONE);
	    keepButton_3.addMouseListener(new MouseAdapter() {
	    	public void mouseDown(final MouseEvent e) {

	    		String mode = "1";
				boolean result = false;
	    		requiredDataAreLabel.setVisible(false);
				boolean newRow = false;
				Attribute attr = null;
				if(BizTransType_EPCTag.getText().equals("") || BizTransType_LName.getText().equals(""))
				{
					requiredDataAreLabel.setText("EPC id and Alias(Name) are mandatory");
					requiredDataAreLabel.setVisible(true);
					return;
				}
				if(!BizTransType_attr.getText().equals("") && !BizTransType_val.getText().equals(""))
				{
					newRow = insertNewRow(BizTransType_attr.getText(),BizTransType_val.getText(),AttrVal_TransType_Table);
					if(newRow)
					{
						TableItem row = new TableItem(AttrVal_TransType_Table,1);
						row.setText(new String[]{BizTransType_attr.getText(),BizTransType_val.getText()});
						
					}
					else
					{
						AttrVal_TransType_Table.getSelection()[0].setText(1,BizTransType_val.getText());
						
					}
					attr = new Attribute(BizTransType_attr.getText(),BizTransType_val.getText());
				}
				dataManager.setVocabularyId(EpcisConstants.BUSINESS_TRANSACTION_TYPE_ID);
				if(BizTransType_EPCTag.isEnabled())//new element
				{
					result = dataManager.masterDataElementEdit(BizTransType_EPCTag.getText(), mode);
					if(!result)
					{
						requiredDataAreLabel.setVisible(true);
						requiredDataAreLabel.setText("Database update failed");
						return;
					}
					dataManager.masterDataAttributeEdit(BizTransType_EPCTag.getText(), "Name", BizTransType_LName.getText(), mode);
					if(attr != null)
						dataManager.masterDataAttributeEdit(BizTransType_EPCTag.getText(), attr.getAttribute(), attr.getValue(), mode);
				
					
					
					BizTransType_attr.setText("");
					BizTransType_val.setText("");
					
				}
				else
				{
					mode = "2";
					result = dataManager.masterDataAttributeEdit(BizTransType_EPCTag.getText(), BizTransType_attr.getText(), BizTransType_val.getText(), mode);
				}
				if(!result)
				{
					requiredDataAreLabel.setVisible(true);
					requiredDataAreLabel.setText("Database update failed");
					
				}	
				else
				{
					requiredDataAreLabel.setVisible(true);
					requiredDataAreLabel.setText("Database was sucessfully updated!");
					
				}	
					//updateAttrValue(BizTransType_EPCTag.getText(),"urn:epcglobal:epcis:vtype:BusinessTransactionType",BizTransType_attr.getText(),BizTransType_val.getText(),"2");
					
				
				BizTransType_attr.setText("");
				BizTransType_attr.setEditable(true);
				BizTransType_attr.setEnabled(true);
				BizTransType_val.setText("");	
			
	    	}
	    });
	    keepButton_3.setText("Save");
	    keepButton_3.setBounds(598, 373, 48, 25);

	    final Button newButton_2 = new Button(composite, SWT.NONE);
	    newButton_2.addMouseListener(new MouseAdapter() {
	    	public void mouseDown(final MouseEvent e) {
	    		BizTransType_attr.setText("");
				BizTransType_val.setText("");
				BizTransType_attr.setEditable(true);
				BizTransType_attr.setEnabled(true);
				AttrVal_TransType_Table.removeAll();
			
				BizTransType_EPCTag.setText("");
				BizTransType_EPCTag.setEditable(true);
				BizTransType_EPCTag.setEnabled(true);
				BizTransType_LName.setText("");
				BizTransType_LName.setEditable(true);
				BizTransType_LName.setEnabled(true);
	    	}
	    });
	    newButton_2.setText("New Element");
	    newButton_2.setBounds(494, 373, 98, 25);


	   
 /*final TableViewer*/		//
		createActions();
		initializeToolBar();
		initializeVariusParameters();
		initializeMenu();
		//initEPC();
		this.dataManager = new DataManagerModule(preferences.getString(PreferenceConstants.P_MdeEpcisRepositoryCaptureURL),queryUrl);

	//	populateBizLocTree(BizLoc_tree_v2,"no","icons/house_16.gif");
	//	populateBizLocTree(BizLoc_depr_tree_v2,"yes","icons/img/s_cancel.png");
	//	BizLoc_tree_v2.getItem(0).setExpanded(true);
	//	BizLoc_depr_tree_v2.getItem(0).setExpanded(true);

	    final Button newButton_3 = new Button(composite_9, SWT.NONE);
	    newButton_3.setToolTipText("Add a location");
	    newButton_3.addMouseListener(new MouseAdapter() {
	    	public void mouseDown(final MouseEvent e) {
	    		if(BizLoc_tree_v2.getSelectionCount() > 0)
	    			if(BizLoc_tree_v2.getSelection()[0].getData() != null && BizLoc_tree_v2.getSelection()[0].getData().equals("addition"))
	    				return;
	    		
	    		TreeItem child_node = new TreeItem(BizLoc_tree_v2.getItem(0),0);
				child_node.setText("<...>");
				child_node.setData("addition");
				child_node.setImage(ResourceManager.getPluginImage(Activator.getDefault(), "icons/house_16.gif"));
				BizLoc_tree_v2.setSelection(child_node);
				BizLocAttr_combo_v2.setText("");
				BizLocAttr_combo_v2.setEnabled(true);
				BizLoc_Val_text.setText("");
				BizLoc_v2_text.setEnabled(true);
				BizLoc_v2_text.setText("");
				BizLoc_name_v2.setEnabled(true);
				BizLoc_name_v2.setText("");
				bizLoc_attrVal_table.removeAll();
				bizLoc_info_label.setVisible(false);
				dataManager.getnewList().clear();
				dataManager.setLocationDisplayedId(null);
	    	}
	    });
	    newButton_3.setText("Location");
	    newButton_3.setBounds(542, 382, 71, 25);
	//	showTransactionHierarchyNEW();
	//	if(treeTransaction.getItemCount() > 0)
	//		treeTransaction.getItem(0).setExpanded(true);





	}

	/**
	 * Create the actions
	 */
	private void createActions() {
		// Create the actions
	}

	/**
	 * Initialize the tool bar
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
	private void setEpcisRepositoryURL() {
		String url = DEFAULT_URL;
		// read properties
		Properties props = new Properties();
		InputStream is = this.getClass().getResourceAsStream(PROPERTY_FILE);
		if (is != null) {
			try {
				props.load(is);
			} catch (IOException e) {
				// unable to load properties
				// use default address
			}
			url = props.getProperty(PROPERTY_QUERY_URL);
			if (url == null) {
				url = DEFAULT_URL;
			}
		}
		this.queryUrl = url;
	}

	/**
	 * Initialize the varius parameters
	 */
	private void initializeVariusParameters() {
		/*
		 * set up query client.
		 */
		client = new QueryClientGuiHelper(queryUrl);
	}


		
	public void insertBusinessLocation(String EPCTag)
	{
		try
		{
			client.clearParameters();
			QueryParam queryParam = new QueryParam();
			queryParam.setName("eventType");
			queryParam.setValue("SimpleMasterDataInsertion");
			client.addParameter(queryParam);
			QueryParam param = new QueryParam();
			param.setName("bizLocation");
			param.setValue(EPCTag);
			client.addParameter(param);
			client.runMasterDataInsertionQuery();
		}
		catch(Exception e){}
	
	}
	
	
//end insertBizLoc
	

	
	
	public void insertAttValBizLoc(String EPCLocTag, String Attribute, String Values)
	{
		try
		{
			client.clearParameters();
			QueryParam queryParam = new QueryParam();
			queryParam.setName("eventType");
			queryParam.setValue("SimpleMasterDataInsertion");
			client.addParameter(queryParam);
			QueryParam param = new QueryParam();
			param.setName("bizLocationAttribute");
			param.setValue(EPCLocTag + "@" + Attribute + "@"
						+ Values);
			client.addParameter(param);
			client.runMasterDataInsertionQuery();
				
			
			

		
		}
		catch(Exception e){}
		
	}
	
	
	
	
	
	
	
	

		
	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public void insertDB_BizTransaction(String EPCTag)
	{
		try
		{
			client.clearParameters();
			QueryParam queryParam = new QueryParam();
			queryParam.setName("eventType");
			queryParam.setValue("SimpleMasterDataInsertion");
			client.addParameter(queryParam);
			QueryParam param = new QueryParam();
			param.setName("bizTransaction");
			param.setValue(EPCTag);
			client.addParameter(param);
			client.runMasterDataInsertionQuery();
						
		}
		catch(Exception e){}
		
	}
	
	
	public void insertDB_BizTransaction_AttrVal(String EPCTag, String attribute, String value)
	{
		try
		{
			client.clearParameters();
			QueryParam queryParam = new QueryParam();
			queryParam.setName("eventType");
			queryParam.setValue("SimpleMasterDataInsertion");
			client.addParameter(queryParam);
			QueryParam param = new QueryParam();
			param.setName("bizTransactionAttribute");
			param.setValue(EPCTag + "@" + attribute + "@"
						+ value);
			client.addParameter(param);
			client.runMasterDataInsertionQuery();
				
			
			
			
		}
		catch(Exception e){}
		
	}
	public boolean isTransaction(org.ow2.aspirerfid.ide.masterdata.classes.EPCIS_Class t)
	{
		if(t.getValueByAttribute("Children") == null && t.getValueByAttribute("event_type") != null)
		{
			return false;
		}
		return true;
	}
	public org.ow2.aspirerfid.ide.masterdata.classes.EPCIS_Event makeEvent(org.ow2.aspirerfid.ide.masterdata.classes.EPCIS_Class tr)
	{
		org.ow2.aspirerfid.ide.masterdata.classes.epcisQueryModule query = new org.ow2.aspirerfid.ide.masterdata.classes.epcisQueryModule();
		query.setClient(client);
		org.ow2.aspirerfid.ide.masterdata.classes.EPCIS_Event ev = new org.ow2.aspirerfid.ide.masterdata.classes.EPCIS_Event();
		ev.setEPC(tr.getEPC());
		
	
		java.util.List<Attribute> attr = tr.getAttributes();
		Iterator<Attribute> itAt = attr.iterator();
		while(itAt.hasNext())
		{
			Attribute a = itAt.next();
			
			if(a.getAttribute().endsWith("business_location"))
			{
				String bizLoc_id;
				StringTokenizer bl = new StringTokenizer(a.getValue(),",");
				int size = bl.countTokens();
				for(int i = 0; i < size -1; i++)
					bl.nextToken();
				if(size == 1)
					bizLoc_id = a.getValue();//ev.setBizLoc(a.getValue());
				else
					bizLoc_id = bl.nextToken();//ev.setBizLoc(bl.nextToken());
				java.util.List<String> v = query.getValues("urn:epcglobal:epcis:vtype:BusinessLocation", bizLoc_id, "Name");
				if(v != null && v.size()>0)
					ev.setBizLoc(v.get(0));			
			
			}
			else if(a.getAttribute().endsWith("business_step"))
			{
				java.util.List<String> v = query.getValues("urn:epcglobal:epcis:vtype:BusinessStep",a.getValue(),"Name");
				if(v != null && v.size()>0)
					ev.setBizStep(v.get(0));
			}
			else if(a.getAttribute().endsWith("disposition"))
			{
				java.util.List<String> v = query.getValues("urn:epcglobal:epcis:vtype:Disposition",a.getValue(),"Name");
				if(v != null && v.size()>0)
					ev.setDisposition(v.get(0));
			}
			else if(a.getAttribute().endsWith("ecspec_name"))
				ev.setECSpec(a.getValue());
			else if(a.getAttribute().endsWith("event_type"))
				ev.setEventType(a.getValue());
			else if(a.getAttribute().endsWith("event_name"))
				ev.setName(a.getValue());
			else if(a.getAttribute().endsWith("read_point"))
			{
				java.util.List<String> v = query.getValues("urn:epcglobal:epcis:vtype:ReadPoint",a.getValue(),"Name");
				if(v != null && v.size()>0)
					ev.setReadPoint(v.get(0));
			}
			else if(a.getAttribute().endsWith("action"))
				ev.setAction(a.getValue());
			else if(a.getAttribute().endsWith("transaction_type"))
			{
				java.util.List<String> v = query.getValues("urn:epcglobal:epcis:vtype:BusinessTransactionType",a.getValue(),"Name");
				if(v != null && v.size()>0)
					ev.setTransactionType(v.get(0));
			}
				
			
		}
		return ev;
		
	}
	
	public void updateTransactions(java.util.List<org.ow2.aspirerfid.ide.masterdata.classes.Transaction>theTransactions,java.util.List<org.ow2.aspirerfid.ide.masterdata.classes.EPCIS_Event>events)
	{
		Iterator<org.ow2.aspirerfid.ide.masterdata.classes.Transaction>iter = theTransactions.iterator();
		//List<org.ow2.aspirerfid.ide.masterdata.classes.EPCIS_Event> children = new ArrayList<org.ow2.aspirerfid.ide.masterdata.classes.EPCIS_Event>();
		if(events.size() == 0)
			return;
		while(iter.hasNext())
		{
			org.ow2.aspirerfid.ide.masterdata.classes.Transaction temp = iter.next();
			System.out.println("Transaction = "+temp.getEPC()+" \n\tChildren"+temp.getValueByAttribute("Children"));
			if(temp.getValueByAttribute("Children") == null)
				continue;
			StringTokenizer ev = new StringTokenizer(temp.getValueByAttribute("Children"), ",");
			int total = ev.countTokens();
			
			for(int i = 0 ; i < total; i++)
			{
				String event = ev.nextToken();
				Iterator<org.ow2.aspirerfid.ide.masterdata.classes.EPCIS_Event>itEv = events.iterator();
				while(itEv.hasNext())
				{
					org.ow2.aspirerfid.ide.masterdata.classes.EPCIS_Event t = itEv.next();
					if(t.getEPC().equals(event))
					{
						//children.add(t);
						temp.addEvent(t);
						break;
					}
					
				}
			}
	
		}
		
		
	}
	public void place_on_tree(String id, String name, String type)
	{
		
		if(type.equals("TopLevelTransaction"))
		{
			TreeItem trans = new TreeItem(treeTransaction.getItems()[0],1);
			trans.setText(name);
			trans.setData(type+"$$"+id);
			trans.setImage(ResourceManager.getPluginImage(Activator.getDefault(), "icons/fromAgilo/package_obj.gif"));
			return;
			
		}
		else if(type.equals("Transaction"))
		{
			int transactions = treeTransaction.getItems()[0].getItemCount();
			String []uris = id.split(",");
			if(transactions == 0)
			{
				TreeItem trans = new TreeItem(treeTransaction.getItems()[0],1);
				trans.setText(name);
				trans.setData(type+"$$"+id);
				trans.setImage(ResourceManager.getPluginImage(Activator.getDefault(), "icons/fromAgilo/runprocess.png"));
				return;
				
			}
			for(int i = 0; i < transactions; i++)
			{
				StringTokenizer parts = new StringTokenizer(((String)treeTransaction.getItems()[0].getItem(i).getData()),"$$");//((String)treeTransaction.getItems()[0].getItem(i).getData()).split("$$");
				parts.nextToken();
				if(parts.nextToken().equals(uris[0]))
				{
					TreeItem trans = new TreeItem(treeTransaction.getItems()[0].getItem(i),1);
					trans.setText(name);
					trans.setData(type+"$$"+id);
					trans.setImage(ResourceManager.getPluginImage(Activator.getDefault(), "icons/fromAgilo/runprocess.png"));
					return;
					
				}
			}
		}
		else if(type.equals("Event"))
		{
			String[] uris =id.split(",");
			
			
			//String[] parts id= ((String)treeTransaction.getItems()[0].getData()).split("$$");
			int topLevel = treeTransaction.getItems()[0].getItemCount();
			for(int i = 0; i < topLevel; i++)
			{
				StringTokenizer tokens = new StringTokenizer((String)treeTransaction.getItems()[0].getItem(i).getData(),"$$");
				tokens.nextToken();//Ignore Transaction and get id after $$
				String parent_id = tokens.nextToken();
				//String[] tokens = ((String)treeTransaction.getItems()[0].getItem(i).getData()).split("$$");
				if(uris[0].equals(parent_id))
				{
					int transactions = treeTransaction.getItems()[0].getItem(i).getItemCount();
					for(int j = 0; j < transactions; j++)
					{
						tokens = new StringTokenizer((String)treeTransaction.getItems()[0].getItem(i).getItem(j).getData(),"$$");
						tokens.nextToken();//Ignore Transaction and get id after $$
						parent_id  = tokens.nextToken();
						//tokens = ((String)treeTransaction.getItems()[0].getItem(i).getItem(j).getData()).split("$$");
						if((uris[0]+","+uris[1]).equals(parent_id))
						{
							TreeItem trans = new TreeItem(treeTransaction.getItems()[0].getItem(i).getItem(j),1);
							trans.setText(name);
							trans.setData(type+"$$"+id);
							trans.setImage(ResourceManager.getPluginImage(Activator.getDefault(), "icons/fromAgilo/finalNode.gif"));
							return;

						}
					}
				}
			}
			
			
		}
		
		
	}
	
	public void add_to_tree(String id,String type)
	{
		//java.util.List<String> name = dataManager.getElementNames(id,null);
		java.util.List<String>attr = new ArrayList<String>();
		attr.add("urn:epcglobal:epcis:mda:Name");
		String name = dataManager.getSpecificAttributeValue(id, attr);
		place_on_tree(id,name == null?"":name,type);
		
	}
	public void showTransactionHierarchyNEW()
	{
		
		java.util.List<String> attributes = new ArrayList<String>();
		TreeItem root = null;
		
		treeTransaction.removeAll();
		

		label_4.setVisible(false);

		dataManager.setVocabularyId(EpcisConstants.BUSINESS_TRANSACTION_ID);
		//attributes.add("urn:epcglobal:epcis:mda:Name");

		java.util.List<String> transactionIDs = dataManager.getElementUris(attributes);
		attributes.clear();
		if(transactionIDs == null)
		{
			label_4.setVisible(true);
			label_4.setText("No processes in the DB!");
			return;
			
		}
		if(transactionIDs.size() == 0)
		{
			label_4.setVisible(true);
			label_4.setText("No processes in the DB!");
		}
		if(root == null)
			root = new TreeItem(treeTransaction, 1);
		root.setText("Transactions");
		root.setData("Root");
		root.setImage(ResourceManager.getPluginImage(Activator.getDefault(), "icons/fromAgilo/semBPM.gif"));
		
		
		int total = transactionIDs.size();
		java.util.List<String> exists_in_tree = new java.util.ArrayList<String>();
		for(int i = 0; i < total; i++)
		{
			//For each uri create the tree by parsing
			String[] uri_parts = transactionIDs.get(i).split(",");
			String temp = new String();
			for(int j = 0; j < uri_parts.length; j++)
			{
				temp += uri_parts[j];
				if(!exists_in_tree.contains(temp))
				{
					String type;
					if(j == 0)
						type = "TopLevelTransaction";
					else if(j == 1)
						type = "Transaction";
					else
						type = "Event";
					add_to_tree(temp,type);
					exists_in_tree.add(temp);
					
					
				}
				
				temp += ",";
				
			}
			
		}
		text_EPCTrans.setEnabled(true);
		text_EPCTrans.setText("");
		NameTrans.setEnabled(true);
		NameTrans.setText("");
		elementDataGroup.setBounds(200, 40, 514, 82);
		table_1_transactions.removeAll();
		table_1_transactions.setVisible(true);
		eventTypeLabel_2.setVisible(false);
		combo_1_eventType.setVisible(false);
		stepLabel.setVisible(false);
		combo_2_businessStep.setVisible(false);
		combo_2_businessStep.removeAll();
		locationLabel.setVisible(false);
		combo_1Location.setVisible(false);
		combo_1Location.removeAll();
		dispositionLabel_2.setVisible(false);
		combo_1_Disposition.setVisible(false);
		combo_1_Disposition.removeAll();


		readerLabel.setVisible(false);
		combo_2.setVisible(false);
		combo_2.removeAll();
		actionLabel_2.setVisible(false);
		combo_3_Action.setVisible(false);
		combo_3_Action.removeAll();
		transTypeLabel_1.setVisible(false);
		combo_4_TransaType.setVisible(false);
		combo_4_TransaType.removeAll();
		reportsLabel_1.setVisible(false);
		listOfReports.setVisible(false);
		listOfReports.removeAll();
		
		text_3_attribute.setText("");
		text_3_attribute.setEnabled(true);
		text_4_Value_trans.setText("");
		text_4_Value_trans.setEnabled(true);
		
		attributesButton_2.setEnabled(false);
		saveButton_1.setEnabled(false);


		
		/*java.util.List<String> attributes = new ArrayList<String>();
		TreeItem root = null;
		
		treeTransaction.removeAll();
		

		label_4.setVisible(false);

		dataManager.setVocabularyId(EpcisConstants.BUSINESS_TRANSACTION_ID);
		attributes.add("urn:epcglobal:epcis:mda:Name");

		java.util.List<String> transactionIDs = dataManager.getElementUris(attributes);
		attributes.clear();
		if(transactionIDs == null)
		{
			label_4.setVisible(true);
			label_4.setText("No processes in the DB!");
			return;
			
		}
		if(transactionIDs.size() == 0)
		{
			label_4.setVisible(true);
			label_4.setText("No processes in the DB!");
		}
		if(root == null)
			root = new TreeItem(treeTransaction, 1);
		root.setText("Transactions");
		root.setData("Root");
		root.setImage(ResourceManager.getPluginImage(Activator.getDefault(), "icons/fromAgilo/package_obj.gif"));
		
		
		int total = transactionIDs.size();
		for(int i = 0; i < total; i++)
		{
			java.util.List<String> name = dataManager.getElementNames(transactionIDs.get(i),null);
			TreeItem trans = new TreeItem(root,1);
			trans.setText(name.get(0));
			trans.setData("Transaction$$"+transactionIDs.get(i));
			trans.setImage(ResourceManager.getPluginImage(Activator.getDefault(), "icons/fromAgilo/runprocess.png"));
			
			attributes.add("urn:epcglobal:epcis:mda:Children");
			String children = dataManager.getSpecificAttributeValue(transactionIDs.get(i), attributes);
			if(children != null)
			{
				TreeItem eventTree = new TreeItem(trans,1);
				eventTree.setText("Events");
				eventTree.setData("Events");
				StringTokenizer events = new StringTokenizer(children,",");
				int child_number = events.countTokens();
				for(int j = 0; j < child_number; j++)
				{
					String eventUri = events.nextToken();
					attributes.clear();
					attributes.add("urn:epcglobal:epcis:mda:event_name");
					String eventName = dataManager.getSpecificAttributeValue(eventUri, attributes);
					if(eventName == null)
						continue;
					TreeItem ev = new TreeItem(eventTree,1);
					ev.setText(eventName);
					ev.setData("Event$$"+eventUri);
					ev.setImage(ResourceManager.getPluginImage(Activator.getDefault(), "icons/fromAgilo/finalNode.gif"));
					
				}
				
				
			}
			
			
			text_EPCTrans.setEnabled(true);
			text_EPCTrans.setText("");
			NameTrans.setEnabled(true);
			NameTrans.setText("");
			elementDataGroup.setBounds(200, 40, 514, 82);
			table_1_transactions.removeAll();
			table_1_transactions.setVisible(true);
			eventTypeLabel_2.setVisible(false);
			combo_1_eventType.setVisible(false);
			stepLabel.setVisible(false);
			combo_2_businessStep.setVisible(false);
			combo_2_businessStep.removeAll();
			locationLabel.setVisible(false);
			combo_1Location.setVisible(false);
			combo_1Location.removeAll();
			dispositionLabel_2.setVisible(false);
			combo_1_Disposition.setVisible(false);
			combo_1_Disposition.removeAll();


			readerLabel.setVisible(false);
			combo_2.setVisible(false);
			combo_2.removeAll();
			actionLabel_2.setVisible(false);
			combo_3_Action.setVisible(false);
			combo_3_Action.removeAll();
			transTypeLabel_1.setVisible(false);
			combo_4_TransaType.setVisible(false);
			combo_4_TransaType.removeAll();
			reportsLabel_1.setVisible(false);
			listOfReports.setVisible(false);
			listOfReports.removeAll();
			
			text_3_attribute.setText("");
			text_3_attribute.setEnabled(true);
			text_4_Value_trans.setText("");
			text_4_Value_trans.setEnabled(true);
			
			attributesButton_2.setEnabled(true);

		}
		
		
		
		
		
			*/	
		
		
	}
	public void insertTransactionNode(TreeItem parent,String uri, String name,String type,String img,boolean override)
	{
		TreeItem child;
		if(override)
			child = parent;
		else
			child = new TreeItem(parent,0);
		child.setText(name);
		child.setData(type+"$$"+uri);
		child.setImage(ResourceManager.getPluginImage(Activator.getDefault(), img));
		
	}

	
	
	
	
	
	
	
		
	
	
	
	
	
	
	
		
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
		
	
	
	
	
	
	
	
	
	
	public int relationship(String uri, TreeItem[] nodes, String relation)
	{
		for(int i = 0; i < nodes.length; i++)
		{
			String treeElem = (String)nodes[i].getData();
			if(uri.equals(dataManager.removeToken(treeElem, ",", -1)))
			{
				//Parent
				relation = "parent";
				return i;
				
			}
			else 
			{
				if(treeElem.equals(dataManager.removeToken(uri, ",", -1)))
				{
					relation = "child";
					return i;
				}
			}
			
			
			
		}
		return -1;
	}
	public java.util.List<String> findLevelLocations(java.util.List<String> uris, int level)
	{
		java.util.List<String> roots = new ArrayList<String>();
		
		for(int i = 0; i < uris.size(); i++)
		{
			StringTokenizer id = new StringTokenizer(uris.get(i),",");
			if(id.countTokens() == level){
				String temp = new String(uris.get(i));
				roots.add(temp);
				roots.remove(i);
				
			}
		}
		return roots;
	}
	public void addToTree(TreeItem theTree,java.util.List<String> elems,java.util.List<String>attr)
	{
		for(int i = 0; i < elems.size(); i++)
		{
			TreeItem temp = new TreeItem(theTree,0);
			temp.setData(elems.get(i));
			String name = dataManager.getSpecificAttributeValue(elems.get(i), attr);
			temp.setText(name);
			
		}
		
	}
	
	private java.util.List<String> findChildren(String parent,java.util.List<String> loc_uris)
	{
		java.util.List<String> children = new ArrayList<String>();
		int total = loc_uris.size();
		StringTokenizer parent_node = new StringTokenizer(parent,",");
		int tokens = parent_node.countTokens();
		tokens++;
		StringTokenizer elem;
		for(int i = 0; i < total; i++)
		{
			elem = new StringTokenizer(loc_uris.get(i),",");
			if(elem.countTokens() == tokens)
			{
				StringBuffer temp = new StringBuffer();
				for(int j = 0; j < tokens - 1; j++)
				{
					temp.append(elem.nextToken());
					if(j < tokens -1)
						temp.append(",");	
				}
				if(temp.toString().equals(parent))
				{
					children.add(elem.toString());
					loc_uris.remove(i);
				}
			}
				
		}
		return children;
		
		
	}
	private TreeItem getSpot(TreeItem node, String parent)
	{
		if(node.getItemCount() == 0)
			return null;
		for(int i = 0; i < node.getItemCount(); i++)
		{
			if(node.getItem(i).equals(parent))
				return node.getItem(i);
		}
		return null;
	}
	
	
	public void display_businessLocation_data(String uri)
	{
		dataManager.setVocabularyId(EpcisConstants.BUSINESS_LOCATION_ID);
		java.util.List<String>ex = new ArrayList<String>();
		ex.add("urn:epcglobal:epcis:mda:ReadPoints");
		ex.add("urn:epcglobal:epcis:mda:deprecated");
		
		java.util.List<Attribute> attributes = dataManager.getAttributes(uri, ex);//dataManager.getAttributes(uri);
		BizLoc_v2_text.setText(dataManager.parseUri(uri, ",", -1));
		BizLoc_v2_text.setEnabled(false);
		int total = attributes.size();
		
		showAttributeTable2(bizLoc_attrVal_table,attributes);
		for(int i = 0; i < total; i++)
		{
			if(attributes.get(i).getAttribute().endsWith("Name"))
			{
				BizLoc_name_v2.setText(attributes.get(i).getValue());
				break;
			}
		}
		
	}
	
	public void populateBizLocTree(Tree theTree,String deprecated,String imgPath)
	{
		theTree.removeAll();
		BizLoc_v2_text.setText("");
		BizLoc_v2_text.setEnabled(true);
		BizLoc_name_v2.setText("");
		BizLoc_name_v2.setEnabled(true);
		bizLoc_attrVal_table.removeAll();
		bizLoc_info_label.setVisible(false);
		java.util.List<String>attr = new ArrayList<String>();
		attr.add("urn:epcglobal:epcis:mda:Name");
		dataManager.setVocabularyId(EpcisConstants.BUSINESS_LOCATION_ID);
		//java.util.List<String>val = new ArrayList<String>();
	//	val.add("no");
		//java.util.List<String> loc_uris = dataManager.getElementUris(attr, val);//getElementUris(attr);
		java.util.List<String> loc_uris = dataManager.getLocations(deprecated);//dataManager.getElementUris(attr);
		
		TreeItem root = new TreeItem(theTree,0);
		root.setText((deprecated.equals("no")?"Active ":"Inactive ") + "Locations");
		TreeItem currentLevel;
		int total = loc_uris.size();
		
		for(int i = 0; i < total; i++)
		{
			StringTokenizer elem = new StringTokenizer(loc_uris.get(i),",");
			int tokens = elem.countTokens();
			StringBuffer location_id = new StringBuffer();
			currentLevel = root;
			for(int j = 0; j < tokens; j++)
			{
				location_id.append(elem.nextToken());
				if(currentLevel.getItemCount() == 0 && loc_uris.contains(location_id.toString()))
				{
					TreeItem node = new TreeItem(currentLevel,0);
					node.setData(location_id.toString());
					String name = dataManager.getSpecificAttributeValue(location_id.toString(), attr);
					node.setText(name);
					node.setImage(ResourceManager.getPluginImage(Activator.getDefault(), imgPath));
					currentLevel = node;
					location_id.append(",");
					
				}
				else
				{
					TreeItem[] parent_nodes = currentLevel.getItems();
					boolean exists = false;
					for(int j2 = 0; j2 < parent_nodes.length; j2++)
					{
						if(parent_nodes[j2].getData().equals(location_id.toString()))
						{
							currentLevel = parent_nodes[j2];
							exists = true;
							break;
						}
							
					}
					if(!exists &&  loc_uris.contains(location_id.toString()))
					{
						TreeItem node = new TreeItem(currentLevel,0);
						node.setData(location_id.toString());
						String name = dataManager.getSpecificAttributeValue(location_id.toString(), attr);
						node.setText(name);
						node.setImage(ResourceManager.getPluginImage(Activator.getDefault(), imgPath));
						currentLevel = node;
						
					}
					location_id.append(",");
					
					
				}
				
				
			}
			
			
			
			
		}
		
		
	
		
	}
	

	
	
	
	
	
	
	
	
	
	
	public void changeBusinessLocationState(Tree fromTree,Tree toTree,String deprecated)
	{
		String selectionURI = (String)fromTree.getSelection()[0].getData();
		TreeItem[] children = fromTree.getSelection()[0].getItems();
		MasterDataCaptureClient cedit = new MasterDataCaptureClient(preferences.getString(PreferenceConstants.P_MdeEpcisRepositoryCaptureURL));
		cedit.simpleMasterDataAndAttributeEdit("urn:epcglobal:epcis:vtype:BusinessLocation", selectionURI, "deprecated",deprecated, "2");
		if(children != null)
		{
			int total = children.length;
			for(int i = 0; i < total; i++)
			{
				cedit.simpleMasterDataAndAttributeEdit("urn:epcglobal:epcis:vtype:BusinessLocation", (String)children[i].getData(), "deprecated", deprecated, "2");
				
			}
		}
		//Change tree
		
		
		
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
		
	
	
	
	public void insertDispositionAttribute(Tree baseTree)
	{
		TreeItem attr = new TreeItem(baseTree.getItems()[0].getItems()[0],0);
		attr.setText("<EDIT>");
		attr.setData("edit_attribute");
		TreeItem val = new TreeItem(attr,0);
		val.setText("<EDIT>");
		val.setData("edit_value");
		
		
	}
	public void removeAttribute_Table(Table Table_attrs)
	{
		
		Table_attrs.getSelection()[0].dispose();
	}
	public void removeDispositionAttribute(Tree baseTree, String vocabulary)
	{
		boolean attribute_lvl = false;
		
		if(!((String)baseTree.getSelection()[0].getData()).equals("attribute") && !((String)baseTree.getSelection()[0].getData()).equals("value"))
			return;
		//Remove attribute from DB
		MasterDataCaptureClient cedit = new MasterDataCaptureClient(preferences.getString(PreferenceConstants.P_MdeEpcisRepositoryCaptureURL));
//		String epc = (String)disposition_tree.getItems()[0].getData();
		String epc = (String)baseTree.getItems()[0].getData();
		String attribute;
		String value;
//		if(((String)disposition_tree.getSelection()[0].getData()).equals("attribute"))
		if(((String)baseTree.getSelection()[0].getData()).equals("attribute"))
		{
			attribute = (String)baseTree.getSelection()[0].getText();
			value = baseTree.getSelection()[0].getItems()[0].getText();
			attribute_lvl = true;
		}
		else if(((String)baseTree.getSelection()[0].getData()).equals("value"))
		{
			value = (String)baseTree.getSelection()[0].getText();
			attribute = baseTree.getSelection()[0].getParentItem().getText();
		}
		else
			return;
		
		                                   
		
		cedit.simpleMasterDataAndAttributeEdit(vocabulary, epc, attribute, value, "3");
		if(attribute_lvl)
			baseTree.getSelection()[0].dispose();
		else
			baseTree.getSelection()[0].getParentItem().dispose();
		
	}
	
	public void updateAttrValue(String epcID,String vocabulary,String attribute,String value,String mode)
	{
		if(attribute.equals("") || value.equals(""))
			return;
			
		MasterDataCaptureClient cedit = new MasterDataCaptureClient(preferences.getString(PreferenceConstants.P_MdeEpcisRepositoryCaptureURL));
		cedit.simpleMasterDataAndAttributeEdit(vocabulary, epcID, attribute, value, mode);//"1");
	}
	
	//public void saveNewDispositionAttributes(Tree baseTree, Button save, Label lbl, Text input)
	//mode == 1 means insert new attribute
	//mode == 2 means modify an existing attribute
	public void saveNewAttributes(Tree baseTree, Button save, Label lbl, Text input,String vocabulary,String mode)
	{
		StringTokenizer attr_val = null;
		if(mode == "1")
			attr_val = new StringTokenizer(input.getText(),",");
		
		
		if(mode == "1" && attr_val.countTokens() != 2)
		{
			lbl.setText("Error Input");
			return;
		}
		else if(mode == "2")
		{
			baseTree.getSelection()[0].setData("attribute");	
			if(input.getText().equals(""))
			{
				lbl.setText("Please give value");
				return;
			}
			else if(input.getText().equals(baseTree.getSelection()[0].getItems()[0].getText()))
			{
				lbl.setText("Same Value, no change");
				return;
			}
			
		}
		String attribute ="";
		String value ="";
		if(mode == "1")
		{
			attribute = attr_val.nextToken();
			value = attr_val.nextToken();
			
		}
		else if(mode =="2")
		{
			attribute = baseTree.getSelection()[0].getText();
			value = input.getText();
		}
		
		String epc = (String)baseTree.getItems()[0].getData();
		MasterDataCaptureClient cedit = new MasterDataCaptureClient(preferences.getString(PreferenceConstants.P_MdeEpcisRepositoryCaptureURL));
		cedit.simpleMasterDataAndAttributeEdit(vocabulary, epc, attribute, value, mode);//"1");
		if(mode == "2")
		{
			baseTree.getSelection()[0].getItems()[0].setText(value);
			if(attribute.endsWith("Name"))
			{
				baseTree.getSelection()[0].getParentItem().getParentItem().setText(value);
				
			}
		}
		else
		{

			TreeItem [] attribs = baseTree.getItems()[0].getItems()[0].getItems();
			for(int i = 0; i < attribs.length; i++)
			{
				if(attribs[i].getData().equals("edit_attribute"))
				{
					attribs[i].setText(attribute);
					attribs[i].setData("attribute");
					attribs[i].getItems()[0].setText(value);
					attribs[i].getItems()[0].setData("value");
				}
			}
		}
		save.setVisible(false);
		lbl.setVisible(false);
		lbl.setText("Give Attribute,Value");
		input.setText("");
		input.setVisible(false);
	}
	
	
	public void showAttributeTable2(Table Attrtable,java.util.List<Attribute> attributes)
	{
		Attrtable.removeAll();
		
		
		Iterator<org.ow2.aspirerfid.ide.masterdata.classes.Attribute> itD = attributes.iterator();
		while(itD.hasNext())
		{
			org.ow2.aspirerfid.ide.masterdata.classes.Attribute temp = itD.next();
			TableItem row = new TableItem(Attrtable,0);
			row.setText(new String[]{temp.getAttribute().substring(temp.getAttribute().lastIndexOf(":")+1,temp.getAttribute().length()),temp.getValue()});
		}
		
		
		
	}
	
	
	public void showAttributeTable(Table Attrtable,Combo input,TreeMap<Key_Map,String> info,java.util.List<org.ow2.aspirerfid.ide.masterdata.classes.Attribute> attributes,Text epcID,Text el_name)
	{
		Attrtable.removeAll();
		int index = input.getSelectionIndex();
		String name = input.getText();
		if(name.equals(""))
			return;
		Key_Map k = new Key_Map();
		k.setIdx(index);
		k.setName(name);
		String uri = info.get(k);//biz_disp_info.get(k);
		System.out.println("URI="+uri);
		epcID.setText(uri);
		el_name.setText(name);
		epcID.setEditable(false);
		epcID.setEnabled(false);
		el_name.setEditable(false);
		el_name.setEnabled(false);
		Iterator<org.ow2.aspirerfid.ide.masterdata.classes.Attribute> itD = attributes.iterator();
		while(itD.hasNext())
		{
			org.ow2.aspirerfid.ide.masterdata.classes.Attribute temp = itD.next();
			TableItem row = new TableItem(Attrtable,0);
			row.setText(new String[]{temp.getAttribute().substring(temp.getAttribute().lastIndexOf(":")+1,temp.getAttribute().length()),temp.getValue()});
		}
		
		
		
	}
	
	
	public void showAttributeTree(Tree baseTree,Combo input,TreeMap<Key_Map,String> info,java.util.List<org.ow2.aspirerfid.ide.masterdata.classes.Attribute> attributes)
	{
	
		int index = input.getSelectionIndex();//BizDispAvail_combo.getSelectionIndex();
		String name = input.getText();//BizDispAvail_combo.getText();
		if(name.equals(""))
			return;
		Key_Map k = new Key_Map();
		k.setIdx(index);
		k.setName(name);
		String uri = info.get(k);//biz_disp_info.get(k);
		System.out.println("URI="+uri);
		//org.ow2.aspirerfid.ide.masterdata.classes.BusinessDispositionReader readerDIS = new org.ow2.aspirerfid.ide.masterdata.classes.BusinessDispositionReader(client);
		//java.util.List<org.ow2.aspirerfid.ide.masterdata.classes.Attribute> attributes = readerDIS.getAttributes(uri);
		
		TreeItem Element = new TreeItem(baseTree,0);
		Element.setText(name);
		Element.setData(uri);
		TreeItem attributeRoot = new TreeItem(Element,0);
		attributeRoot.setText("Attributes");
		Iterator<org.ow2.aspirerfid.ide.masterdata.classes.Attribute> itD = attributes.iterator();
		while(itD.hasNext())
		{
			org.ow2.aspirerfid.ide.masterdata.classes.Attribute temp = itD.next();
			TreeItem attr = new TreeItem(attributeRoot,0);
			
			attr.setText(temp.getAttribute().substring(temp.getAttribute().lastIndexOf(":")+1,temp.getAttribute().length()));
			attr.setData("attribute");
			TreeItem val = new TreeItem(attr,0);
			val.setText(temp.getValue());
			val.setData("value");
		}
		
		
		
		
		
	}
	
	
	
	
	private void populateCombo_Names(Combo elems,java.util.List<String> names)
	{
		int total = names.size();
		for(int i = 0; i < total; i++)
			elems.add(names.get(i));
		
		
		
		
	}
	
	
	
	public void displayDispositions(Combo availableEPCs,TreeMap<Key_Map,String> info )
	{
	
		if(availableEPCs.getItemCount() != 0)
			return;
	
		java.util.Set<java.util.Map.Entry<Key_Map,String>> se = info.entrySet();
		for(Iterator<java.util.Map.Entry<Key_Map,String>> i = se.iterator();i.hasNext();)
		{
			java.util.Map.Entry<Key_Map,String> temp = i.next();
			Key_Map k = temp.getKey();
			String v = temp.getValue();
			String name = k.getName();
			System.out.println("Key = <"+k.getIdx()+","+name+"> - Value = <"+v+">");
			
			//BizDispAvail_combo.add(k.getName());
			availableEPCs.add(name);
			
			
			
		}
		
		
	}
	
	public java.util.List<org.ow2.aspirerfid.ide.masterdata.classes.Attribute> collectAttributes(Table attrTable)
	{
		java.util.List<org.ow2.aspirerfid.ide.masterdata.classes.Attribute> attribs = new ArrayList<org.ow2.aspirerfid.ide.masterdata.classes.Attribute>();
		int total = attrTable.getItemCount();
		for(int i = 0; i < total; i++)
			attribs.add(new Attribute(attrTable.getItem(i).getText(0),attrTable.getItem(i).getText(1)));
		return attribs;
		
	}
	
	
	
	
	public boolean insertNewRow(String attribute, String value, Table attrTable)
	{
		//Check if it is already there
		if(attrTable.getItemCount() > 0)
		{
			int total = attrTable.getItems().length;
			for(int i = 0; i < total; i++)
			{
				if(attrTable.getItem(i).getText(0).equals(attribute))
					return false;
				
			}
		}
		return true;
	}
	public void addAttributeTable(String attribute, String value, Table attrTable)
	{
		//Check if it is already there
		if(attrTable.getItemCount() > 0)
		{
			int total = attrTable.getItems().length;
			for(int i = 0; i < total; i++)
			{
				if(attrTable.getItem(i).getText(0).equals(attribute))
					return;
				
			}
		}
		TableItem row = new TableItem(attrTable,0);
		row.setText(new String[]{attribute,value});
	}
	
	
	public void insertBizDispo_Attrs(String parent,Attribute at)
	{
		
		try
		{
			client.clearParameters();
			QueryParam queryParam = new QueryParam();
			queryParam.setName("eventType");
			queryParam.setValue("SimpleMasterDataInsertion");
			client.addParameter(queryParam);
			QueryParam param = new QueryParam();
			param.setName("dispositionAttribute");
			param.setValue(parent + "@" + at.getAttribute() + "@"+ at.getValue());
			client.addParameter(param);
			client.runMasterDataInsertionQuery();
		}
		catch(Exception e)
		{
			e.printStackTrace();
			
		}
	}
	
	
	
		
	
	
	
	
	
	
	public void insert_BizReader_attr_Vals(String parent, Attribute at)
	{
		Reader_Error_lbl.setVisible(false);
		try
		{
			client.clearParameters();
			QueryParam queryParam = new QueryParam();
			queryParam.setName("eventType");
			queryParam.setValue("SimpleMasterDataInsertion");
			client.addParameter(queryParam);
			QueryParam param = new QueryParam();
			param.setName("readPointAttribute");
			param.setValue(parent + "@" + at.getAttribute() + "@"+ at.getValue());
			client.addParameter(param);
			client.runMasterDataInsertionQuery();
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	

	
}
