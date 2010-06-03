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

package org.ow2.aspirerfid.ide.bpwme.master.views;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.ListViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.custom.CTabItem;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.FocusListener;
import org.eclipse.swt.events.MenuDetectEvent;
import org.eclipse.swt.events.MenuDetectListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.EditorPart;
import org.eclipse.ui.views.properties.IPropertySheetPage;
import org.eclipse.ui.views.properties.tabbed.ITabbedPropertySheetPageContributor;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;
import org.ow2.aspirerfid.commons.epcis.model.VocabularyElementListType;
import org.ow2.aspirerfid.commons.epcis.model.VocabularyElementType;
import org.ow2.aspirerfid.commons.epcis.model.VocabularyType;
import org.ow2.aspirerfid.ide.MasterDataEditorGMF.bpwmeintegration.MasterDataContentsProvider;
import org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.part.MasterDataEditorGMFDiagramEditor;
import org.ow2.aspirerfid.ide.bpwme.diagram.part.BpwmeDiagramEditorPlugin;
import org.ow2.aspirerfid.ide.bpwme.dialog.DispositionDialog;
import org.ow2.aspirerfid.ide.bpwme.ecspec.utils.SelectionProviderWrapper;
import org.ow2.aspirerfid.ide.bpwme.master.model.DispositionItem;
import org.ow2.aspirerfid.ide.bpwme.master.utils.MasterDataBuilder;
import org.ow2.aspirerfid.ide.bpwme.master.utils.MasterDataUtil;
import org.ow2.aspirerfid.ide.bpwme.utils.*;
/**
 * Master Editor edit master data.
 * Should be only one instance in the workspace
 * 
 * @author Yongming Luo <yluo@ait.edu.gr>
 * 
 */

public class MasterEditor extends EditorPart implements 
ITabbedPropertySheetPageContributor{
	
	private enum Type{
		BusinessStep,
		Disposition,
		TransactionType
	}
	
	public static final String ID = "org.ow2.aspirerfid.ide.bpwme.master.views.MasterEditor";
	protected boolean isdirty = false;
	private ComboViewer businessStepComboViewer;
	private ComboViewer dispositionComboViewer;
	private ComboViewer transactionComboViewer;
	
	private ComboViewer readPointComboViewer;
	private Text businessLocationText;
	private Combo actionCombo;
	private Text eventTypeText;
	private Text eventNameText;
	//private MasterEditorInput mei;	
	
	private Button newReadPointButton;
	private Button newBizStepButton;
	private Button newDispositionButton;
	private Button newTransactionButton;
	private Button refreshReadPointButton;
	
	private CTabFolder tabFolder;
	
	private SelectionProviderWrapper spw;
	
	//for business step part
	private Button buttonBizNew;
	private Button buttonBizDuplicate;
	private Button buttonBizRemove;
	private ListViewer bizListViewer;
	
	//for disposition part
	private Button buttonDispoNew;
	private Button buttonDispoDuplicate;
	private Button buttonDispoRemove;
	private ListViewer dispoListViewer;
	
	//for transaction part
	private Button buttonTranzNew;
	private Button buttonTranzDuplicate;
	private Button buttonTranzRemove;
	private ListViewer tranzListViewer;
	
	@Override
	public void doSave(IProgressMonitor monitor) {
		setDirty(false);
		MainControl mc = MainControl.getMainControl();
		mc.saveObject();
	}

	@Override
	public void doSaveAs() {
		doSave(null);
	}

	@Override
	public void init(IEditorSite site, IEditorInput input)
			throws PartInitException {
        setSite(site);       
        setInput(input);
        //MasterEditorInput mei = (MasterEditorInput)input;
        
        spw = new SelectionProviderWrapper();
		MasterDataBuilder mdb = MasterDataBuilder.getInstance();
		mdb.setMasterEditor(this);
	}

	@Override
	public boolean isDirty() {
		return isdirty;
	}

	@Override
	public boolean isSaveAsAllowed() {
		return false;
	}

	@Override
	public void createPartControl(Composite parent) {
		final ScrolledComposite scrolledComposite = new ScrolledComposite(parent, SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL);

		final Composite composite = new Composite(scrolledComposite, SWT.NONE);
		composite.setLayout(new GridLayout());
		scrolledComposite.setContent(composite);
		
		tabFolder = new CTabFolder(composite, SWT.TOP);
		tabFolder.setMaximized(true);
		tabFolder.setBorderVisible(true);
		tabFolder.setLayoutData(new GridData(GridData.FILL_BOTH));
		tabFolder.setBackground(null);
		
		CTabItem ctiGeneral = new CTabItem(tabFolder, SWT.NONE);
		ctiGeneral.setText("General");
		Composite compositeGeneral = new Composite(tabFolder, SWT.NONE);
		compositeGeneral.setLayout(new GridLayout());
		compositeGeneral.setLayoutData(new GridData(GridData.FILL_BOTH));
		ctiGeneral.setControl(compositeGeneral);
		
		
		CTabItem ctiBizStep = new CTabItem(tabFolder, SWT.NONE);
		ctiBizStep.setText("BusinessStep");
		Composite compositeBizStep = new Composite(tabFolder, SWT.NONE);
		compositeBizStep.setLayout(new GridLayout());
		compositeBizStep.setLayoutData(new GridData(GridData.FILL_BOTH));
		ctiBizStep.setControl(compositeBizStep);

		
		CTabItem ctiDisposition = new CTabItem(tabFolder, SWT.NONE);
		ctiDisposition.setText("Disposition");
		Composite compositeDisposition = new Composite(tabFolder, SWT.NONE);
		compositeDisposition.setLayout(new GridLayout());
		compositeDisposition.setLayoutData(new GridData(GridData.FILL_BOTH));
		ctiDisposition.setControl(compositeDisposition);

		
		CTabItem ctiTransType = new CTabItem(tabFolder, SWT.NONE);
		ctiTransType.setText("BusinessTransactionType");
		Composite compositeTransType = new Composite(tabFolder, SWT.NONE);
		compositeTransType.setLayout(new GridLayout());
		compositeTransType.setLayoutData(new GridData(GridData.FILL_BOTH));
		ctiTransType.setControl(compositeTransType);

		
		//select the first tab at the beginning
		tabFolder.setSelection(ctiGeneral);	
				
		createGeneralPart(compositeGeneral);
		createGeneralPartLogic();
		
		createBusinessStepPart(compositeBizStep);
		createBusinessStepPartLogic(compositeBizStep);
		
		createDispositionPart(compositeDisposition);
		createDispositionPartLogic(compositeDisposition);		
		
		createTransactionPart(compositeTransType);
		createTransactionPartLogic(compositeTransType);
		
		getSite().setSelectionProvider(spw);
		composite.setSize(400, 500);
	}
	
	private void createGeneralPart(final Composite parent) {
		final Group generalInfoGroup = new Group(parent, SWT.NONE);
		final GridLayout gridLayout = new GridLayout();
		gridLayout.numColumns = 4;
		gridLayout.makeColumnsEqualWidth = false;
		generalInfoGroup.setLayout(gridLayout);
		generalInfoGroup.setText("General Info");

		final Label eventNameLabel = new Label(generalInfoGroup, SWT.NONE);
		eventNameLabel.setText("Event Name");

		eventNameText = new Text(generalInfoGroup, SWT.BORDER);
		eventNameText.setEditable(false);
		eventNameText.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
		
		//fill space
		new Label(generalInfoGroup, SWT.NONE);
		new Label(generalInfoGroup, SWT.NONE);
		
		final Label eventTypeLabel = new Label(generalInfoGroup, SWT.NONE);
		eventTypeLabel.setText("Event Type");

		eventTypeText = new Text(generalInfoGroup, SWT.BORDER);
		eventTypeText.setEditable(false);
		eventTypeText.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));

		//fill space
		new Label(generalInfoGroup, SWT.NONE);
		new Label(generalInfoGroup, SWT.NONE);
		
		final Label actionLabel = new Label(generalInfoGroup, SWT.NONE);
		actionLabel.setText("Action");

		actionCombo = new Combo(generalInfoGroup, SWT.DROP_DOWN | SWT.READ_ONLY);
		actionCombo.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));

		//fill space
		new Label(generalInfoGroup, SWT.NONE);
		new Label(generalInfoGroup, SWT.NONE);
		
		final Label businessLocationLabel = new Label(generalInfoGroup, SWT.NONE);
		businessLocationLabel.setText("Business Location");

		businessLocationText = new Text(generalInfoGroup, SWT.BORDER);
		businessLocationText.setEditable(false);
		businessLocationText.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));

		//fill space
		new Label(generalInfoGroup, SWT.NONE);
		new Label(generalInfoGroup, SWT.NONE);
		
		final Label readPointLabel = new Label(generalInfoGroup, SWT.NONE);
		readPointLabel.setText("Read Point");
		
		readPointComboViewer = new ComboViewer(generalInfoGroup, SWT.DROP_DOWN | SWT.READ_ONLY);
		readPointComboViewer.getControl().setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
		
		newReadPointButton = new Button(generalInfoGroup, SWT.NONE);
		newReadPointButton.setText("+");

		refreshReadPointButton = new Button(generalInfoGroup, SWT.NONE);
		refreshReadPointButton.setToolTipText("Refresh");
		refreshReadPointButton.setImage(ResourceManager.getPluginImage(BpwmeDiagramEditorPlugin.getInstance(),"icons/s_reload.png"));
		
		final Label businessStepLabel = new Label(generalInfoGroup, SWT.NONE);
		businessStepLabel.setText("Business Step");

		
		businessStepComboViewer = new ComboViewer(generalInfoGroup, SWT.DROP_DOWN | SWT.READ_ONLY);
		businessStepComboViewer.getControl().setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));

		newBizStepButton = new Button(generalInfoGroup, SWT.NONE);
		newBizStepButton.setText("+");
		
		new Label(generalInfoGroup, SWT.NONE);
		
		final Label dispositionLabel = new Label(generalInfoGroup, SWT.NONE);
		dispositionLabel.setText("Disposition");
		
		dispositionComboViewer = new ComboViewer(generalInfoGroup, SWT.DROP_DOWN | SWT.READ_ONLY);
		dispositionComboViewer.getControl().setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));

		newDispositionButton = new Button(generalInfoGroup, SWT.NONE);
		newDispositionButton.setText("+");
		
		new Label(generalInfoGroup, SWT.NONE);
		
		final Label transactionTypeLabel = new Label(generalInfoGroup, SWT.NONE);
		transactionTypeLabel.setText("Transaction Type");

		transactionComboViewer = new ComboViewer(generalInfoGroup, SWT.DROP_DOWN | SWT.READ_ONLY);
		transactionComboViewer.getControl().setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
		
		newTransactionButton = new Button(generalInfoGroup, SWT.NONE);
		newTransactionButton.setText("+");
		
		new Label(generalInfoGroup, SWT.NONE);
		
		generalInfoGroup.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
	}
	
	private void createGeneralPartLogic() {
		final MasterDataBuilder mdb = MasterDataBuilder.getInstance();
		String text = mdb.getAttribute("urn:epcglobal:epcis:mda:event_name");
		eventNameText.setText(text);
		
		text = mdb.getAttribute("urn:epcglobal:epcis:mda:event_type");
		eventTypeText.setText(text);
		
		text = mdb.getAttribute("urn:epcglobal:epcis:mda:action");		
		actionCombo.add("ADD");
		actionCombo.add("DROP");
		actionCombo.add("DELETE");		
		actionCombo.setText(text);//set text to the chosen one
		
		actionCombo.addSelectionListener(new SelectionAdapter(){					
			@Override
			public void widgetSelected(SelectionEvent e) {
				String text = actionCombo.getText();
				if(text == null) {
					return;
				}else {
					mdb.setAttribute("urn:epcglobal:epcis:mda:action", text);
				}
			}
		});
		

		
		readPointComboViewer.setContentProvider(new IStructuredContentProvider(){
			@Override
			public Object[] getElements(Object inputElement) {
				VocabularyElementListType list = 
					MasterDataUtil.getVocabularyElementList((VocabularyType)inputElement);
				return list.getVocabularyElement().toArray();
			}

			@Override
			public void dispose() {
			}

			@Override
			public void inputChanged(Viewer viewer, Object oldInput,
					Object newInput) {
			}			
		});
		readPointComboViewer.setLabelProvider(new LabelProvider(){
			@Override
			public String getText(Object element) {
				return ((VocabularyElementType)element).getId();
			}
		});
		readPointComboViewer.setInput(mdb.getReadPointVocabulary());
		mdb.addListener(readPointComboViewer);
		
		readPointComboViewer.getCombo().addSelectionListener(new SelectionAdapter(){
			@Override
			public void widgetSelected(SelectionEvent e) {
				String text = readPointComboViewer.getCombo().getText();
				if(text == null) {
					return;
				}else {
					mdb.setAttribute("urn:epcglobal:epcis:mda:read_point", text);
					String bizLocation = mdb.getBusinessLocation(text);
					businessLocationText.setText(bizLocation);
					mdb.setAttribute("urn:epcglobal:epcis:mda:business_location", bizLocation);
					setDirty(true);
				}
			}
		});
		
		text = mdb.getAttribute("urn:epcglobal:epcis:mda:read_point");
		readPointComboViewer.getCombo().setText(text);
		
		text = mdb.getAttribute("urn:epcglobal:epcis:mda:business_location");
		businessLocationText.setText(text);
		
		createComboViewerLogic(businessStepComboViewer, Type.BusinessStep);		
		text = mdb.getAttribute("urn:epcglobal:epcis:mda:business_step");
		businessStepComboViewer.getCombo().setText(text);

		
		createComboViewerLogic(dispositionComboViewer, Type.Disposition);
		text = mdb.getAttribute("urn:epcglobal:epcis:mda:disposition");
		dispositionComboViewer.getCombo().setText(text);

		
		createComboViewerLogic(transactionComboViewer, Type.TransactionType);
		text = mdb.getAttribute("urn:epcglobal:epcis:mda:transaction_type");
		transactionComboViewer.getCombo().setText(text);
		
		//set actions for the '+' buttons
		//new read point button should jump to the existing master data diagram
		//editor or invoke the method to create a new diagram
		newReadPointButton.addSelectionListener(new SelectionAdapter(){
			@Override
			public void widgetSelected(SelectionEvent e) {
				if(MainUtil.isEditorOpened(MasterDataEditorGMFDiagramEditor.ID)) {
					//activate it
					MainUtil.activateEditor(MasterDataEditorGMFDiagramEditor.ID);					
				}else {
					MessageBox messageBox = new MessageBox(
							PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell());
					messageBox.setMessage("No masterdata diagram editor is in workspace.\n" +
							"Please create or open a masterdata diagram.");
					messageBox.open();
					return;
				}
			}
		});		
		refreshReadPointButton.addSelectionListener(new SelectionAdapter(){
			@Override
			public void widgetSelected(SelectionEvent e) {
				MainUtil.activateEditor(MasterDataEditorGMFDiagramEditor.ID);
				
				MasterDataBuilder mdb = MasterDataBuilder.getInstance();					
				HashMap<String, HashMap<String, String>> companyMap = 
					MasterDataContentsProvider.getCompanyUriAttributesValues();
				HashMap<String, HashMap<String, String>> warehouseMap = 
					MasterDataContentsProvider.getWarehouseUriAttributesValues();
				HashMap<String, HashMap<String, String>> readpointMap = 
					MasterDataContentsProvider.getReadPointUriAttributesValues();
				
				mdb.setBusinessStepReadPoint(companyMap, warehouseMap, readpointMap);
				
				MainUtil.activateEditor(MasterEditor.ID);
			}


		});
		newBizStepButton.addSelectionListener(new SelectionAdapter(){
			@Override
			public void widgetSelected(SelectionEvent e) {
				tabFolder.setSelection(1);
			}
		});
		
		newDispositionButton.addSelectionListener(new SelectionAdapter(){
			@Override
			public void widgetSelected(SelectionEvent e) {
				tabFolder.setSelection(2);
			}
		});
		
		newTransactionButton.addSelectionListener(new SelectionAdapter(){
			@Override
			public void widgetSelected(SelectionEvent e) {
				tabFolder.setSelection(3);
			}
		});
	}
	
	private void createComboViewerLogic(final ComboViewer comboViewer, final Type type) {
		final MasterDataBuilder mdb = MasterDataBuilder.getInstance();

		comboViewer.setContentProvider(new IStructuredContentProvider(){
			@Override
			public Object[] getElements(Object inputElement) {
				VocabularyElementListType list = 
					MasterDataUtil.getVocabularyElementList((VocabularyType)inputElement);
				return list.getVocabularyElement().toArray();
			}
			@Override
			public void dispose() {
			}

			@Override
			public void inputChanged(Viewer viewer, Object oldInput,
					Object newInput) {
			}			
		});
		comboViewer.setLabelProvider(new LabelProvider(){
			@Override
			public String getText(Object element) {
				return ((VocabularyElementType)element).getId();
			}
		});
		switch(type) {
		case BusinessStep:
			comboViewer.setInput(mdb.getBusinessStepVocabulary());
			break;
		case Disposition:
			comboViewer.setInput(mdb.getDispositionVocabulary());
			break;
		case TransactionType:
			comboViewer.setInput(mdb.getBusinessTransactionVocabulary());
			break;
		}
		
		mdb.addListener(comboViewer);
		
		comboViewer.getCombo().addSelectionListener(new SelectionAdapter(){
			@Override
			public void widgetSelected(SelectionEvent e) {
				String text = comboViewer.getCombo().getText();
				if(text == null) {
					return;
				}else {
					switch(type) {
					case BusinessStep:
						mdb.setAttribute("urn:epcglobal:epcis:mda:business_step", text);
						break;
					case Disposition:
						mdb.setAttribute("urn:epcglobal:epcis:mda:disposition", text);
						break;
					case TransactionType:
						mdb.setAttribute("urn:epcglobal:epcis:mda:transaction_type", text);
						break;
					}
					setDirty(true);
				}
			}
		});
	}
	
	
	private void createTabPart(final Composite parent, final Type type, String groupText) {

		Button buttonNew;
		Button buttonDuplicate;
		Button buttonRemove;
		ListViewer listViewer;
		
		Composite buttonComposite = new Composite(parent,0);
		buttonComposite.setLayout(new GridLayout());
		buttonComposite.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		Group buttonGroup = new Group(buttonComposite, SWT.SHADOW_NONE);
		buttonGroup.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
		buttonGroup.setText("Button");
		buttonGroup.setLayout(new GridLayout(3, false));
		//add buttons
		buttonNew = new Button(buttonGroup, SWT.PUSH);
		buttonNew.setText("New");
		
		buttonDuplicate = new Button(buttonGroup, SWT.PUSH);
		buttonDuplicate.setText("Duplicate");
		
		buttonRemove = new Button(buttonGroup, SWT.PUSH);
		buttonRemove.setText("Remove");
		
		Composite listComposite = new Composite(parent,0);
		listComposite.setLayout(new GridLayout());
		listComposite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
		
		Group dispositionGrp = new Group(listComposite, SWT.SHADOW_NONE);
		GridData dispositionGd = new GridData(SWT.FILL, SWT.FILL, true, true);
		dispositionGd.widthHint = 100;
		dispositionGrp.setLayoutData(dispositionGd);
		dispositionGrp.setText(groupText);
		dispositionGrp.setLayout(new GridLayout());
		
		listViewer = new ListViewer(dispositionGrp);
		listViewer.getControl().setLayoutData(
				new GridData(SWT.FILL, SWT.FILL, true, true));
		
		//assign local variables to global
		switch(type) {
		case BusinessStep:
			buttonBizNew = buttonNew;
			buttonBizDuplicate = buttonDuplicate;
			buttonBizRemove = buttonRemove;
			bizListViewer = listViewer;
			break;
		case TransactionType:
			buttonTranzNew = buttonNew;
			buttonTranzDuplicate = buttonDuplicate;
			buttonTranzRemove = buttonRemove;
			tranzListViewer = listViewer;
			break;
		case Disposition:
			buttonDispoNew = buttonNew;
			buttonDispoDuplicate = buttonDuplicate;
			buttonDispoRemove = buttonRemove;
			dispoListViewer = listViewer;
			break;
		}
		
	}	
	
	private void createTabPartLogic(final Composite parent, 
			Button buttonNew, Button buttonDuplicate, Button buttonRemove,
			final ListViewer listViewer, final Type type, final String defaultURI) {
		final MasterDataBuilder mdb = MasterDataBuilder.getInstance();
		
		buttonNew.addSelectionListener(new SelectionAdapter(){
			@Override
			public void widgetSelected(SelectionEvent e) {
				DispositionDialog dd = new DispositionDialog(parent.getShell());
				dd.setMessage("Input URI and Name");
				dd.setDefaultURI(defaultURI);
				DispositionItem di = dd.open();
				if(di == null) {
					return;
				}else {
					switch(type) {
					case BusinessStep:
						mdb.addBizStepItem(di);
						break;
					case Disposition:
						mdb.addDispositionItem(di);
						break;
					case TransactionType:
						mdb.addTranzItem(di);
						break;
					}	
					listViewer.refresh(false);
					setDirty(true);
				}
			}
		});
		
		buttonDuplicate.addSelectionListener(new SelectionAdapter(){
			@Override
			public void widgetSelected(SelectionEvent e) {
				DispositionItem di = (DispositionItem)((IStructuredSelection)listViewer.getSelection()).getFirstElement();
				if(di == null) {
					return;
				}else {
					DispositionItem newDi = di.getClone();
					switch(type) {
					case BusinessStep:
						mdb.addBizStepItem(newDi);
						break;
					case Disposition:
						mdb.addDispositionItem(newDi);
						break;
					case TransactionType:
						mdb.addTranzItem(newDi);
						break;
					}	
					listViewer.refresh(false);
					setDirty(true);
				}
			}
		});
		
		buttonRemove.addSelectionListener(new SelectionAdapter(){
			@Override
			public void widgetSelected(SelectionEvent e) {
				DispositionItem di = (DispositionItem)((IStructuredSelection)listViewer.getSelection()).getFirstElement();
				if(di == null) {
					return;
				}else {
					switch(type) {
					case BusinessStep:
						mdb.removeBizStepItem(di);
						break;
					case Disposition:
						mdb.removeDispositionItem(di);
						break;
					case TransactionType:
						mdb.removeTranzItem(di);
						break;
					}	
					listViewer.refresh(false);
					setDirty(true);
				}
			}
		});
		
		listViewer.setContentProvider(new IStructuredContentProvider(){
			@SuppressWarnings("unchecked")
			@Override
			public Object[] getElements(Object inputElement) {
				return ((ArrayList<DispositionItem>)inputElement).toArray();
			}
			@Override
			public void dispose() {	
			}
			@Override
			public void inputChanged(Viewer viewer, Object oldInput,
					Object newInput) {				
			}
		});
		
		listViewer.setLabelProvider(new LabelProvider(){
			@Override
			public String getText(Object element) {
				return ((DispositionItem)element).getLabel();
			}
		});
		
		switch(type) {
		case BusinessStep:
			listViewer.setInput(mdb.getBizStepList());
			break;
		case Disposition:
			listViewer.setInput(mdb.getDispositionList());
			break;
		case TransactionType:
			listViewer.setInput(mdb.getTranzList());
		}
		
		listViewer.getControl().addFocusListener(new FocusListener() {
			@Override
			public void focusGained(FocusEvent e) {
				spw.setSelectionProvider(listViewer);
			}
			@Override
			public void focusLost(FocusEvent e) {
			}
		});
		
		mdb.addListener(listViewer);
	}
	
	private void createBusinessStepPart(final Composite parent) {
		createTabPart(parent, Type.BusinessStep, "Business Step");
	}
	
	private void createBusinessStepPartLogic(final Composite parent) {
		createTabPartLogic(parent, buttonBizNew, buttonBizDuplicate, 
				buttonBizRemove, bizListViewer, Type.BusinessStep, "urn:epcglobal:fmcg:bizstep:*");
	}
	
	private void createDispositionPart(final Composite parent) {
		createTabPart(parent, Type.Disposition, "Disposition");
	}
	
	private void createDispositionPartLogic(final Composite parent) {
		createTabPartLogic(parent, buttonDispoNew, buttonDispoDuplicate, 
				buttonDispoRemove, dispoListViewer, Type.Disposition, "urn:epcglobal:fmcg:disp:*");
	}
	
	private void createTransactionPart(final Composite parent) {
		createTabPart(parent, Type.TransactionType, "Business Transaction Type");
	}
	
	private void createTransactionPartLogic(final Composite parent) {
		createTabPartLogic(parent, buttonTranzNew, buttonTranzDuplicate, 
				buttonTranzRemove, tranzListViewer, Type.TransactionType, "urn:epcglobal:fmcg:btt:*");
	}
	
	@Override
	public void setFocus() {
		//set the current master editor to this
		MasterDataBuilder mdb = MasterDataBuilder.getInstance();
		mdb.setMasterEditor(this);
		//reload eventName
		String text = mdb.getAttribute("urn:epcglobal:epcis:mda:event_name");
		eventNameText.setText(text);
		//MainControl mc = MainControl.getMainControl();
	    //mc.setMasterEditor(this);
	    //reload some data
	    
	    //TODO should change the MasterDataBuilder instance also?
	    
	    //System.out.println("Focus Changed");
	}
	
	
	@Override
	public Object getAdapter(Class adapter) {
        if (adapter == IPropertySheetPage.class)
            return new TabbedPropertySheetPage(this);
        return super.getAdapter(adapter);
	}

	@Override
	public String getContributorId() {
		return getSite().getId();
	}
	
    public void setDirty(boolean value) {
        isdirty = value;
        firePropertyChange(PROP_DIRTY);
     }
}
