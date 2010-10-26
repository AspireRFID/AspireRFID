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
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.ListViewer;
import org.eclipse.jface.viewers.SelectionChangedEvent;
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
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.EditorPart;
import org.eclipse.ui.views.properties.IPropertySheetPage;
import org.eclipse.ui.views.properties.tabbed.ITabbedPropertySheetPageContributor;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;
import org.ow2.aspirerfid.commons.ale.model.ale.ECReportSpec;
import org.ow2.aspirerfid.commons.apdl.model.CLCBProc;
import org.ow2.aspirerfid.commons.apdl.model.EBProc;
import org.ow2.aspirerfid.commons.epcis.model.VocabularyElementListType;
import org.ow2.aspirerfid.commons.epcis.model.VocabularyElementType;
import org.ow2.aspirerfid.commons.epcis.model.VocabularyType;
import org.ow2.aspirerfid.ide.bpwme.diagram.edit.parts.CLCBProcEditPart;
import org.ow2.aspirerfid.ide.bpwme.diagram.edit.parts.EBProcEditPart;
import org.ow2.aspirerfid.ide.bpwme.diagram.part.BpwmeDiagramEditor;
import org.ow2.aspirerfid.ide.bpwme.diagram.part.BpwmeDiagramEditorPlugin;
import org.ow2.aspirerfid.ide.bpwme.dialog.DispositionDialog;
import org.ow2.aspirerfid.ide.bpwme.dialog.NewDialog;
import org.ow2.aspirerfid.ide.bpwme.dialog.PrefixValidator;
import org.ow2.aspirerfid.ide.bpwme.dialog.NewDispoDialog;
import org.ow2.aspirerfid.ide.bpwme.ecspec.model.ReportSpec;
import org.ow2.aspirerfid.ide.bpwme.ecspec.utils.SelectionProviderWrapper;
import org.ow2.aspirerfid.ide.bpwme.ecspec.views.ECLRInput;
import org.ow2.aspirerfid.ide.bpwme.impl.CLCBProcImpl;
import org.ow2.aspirerfid.ide.bpwme.impl.EBProcImpl;
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
ITabbedPropertySheetPageContributor,ISelectionChangedListener{
	
	private enum Type{
		BusinessStep,
		Disposition,
		TransactionType
	}
	
	public static final String ID = "org.ow2.aspirerfid.ide.bpwme.master.views.MasterEditor";
	protected boolean isdirty = false;
	
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
		
		
		//add selection listener to bpwme editor
		IEditorPart editor = MainUtil.getEditor(BpwmeDiagramEditor.ID);
		if(editor != null) {
			((BpwmeDiagramEditor)editor).addSelectionListener(this);
		}
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
		tabFolder.setSelection(ctiBizStep);	
//				
//		createGeneralPart(compositeGeneral);
//		createGeneralPartLogic();
		
		createBusinessStepPart(compositeBizStep);
		createBusinessStepPartLogic(compositeBizStep);
		
		createDispositionPart(compositeDisposition);
		createDispositionPartLogic(compositeDisposition);		
		
		createTransactionPart(compositeTransType);
		createTransactionPartLogic(compositeTransType);
		
		getSite().setSelectionProvider(spw);
		composite.setSize(400, 500);
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
//				DispositionDialog dd = new DispositionDialog(parent.getShell());
//				dd.setMessage("Input URI and Name");
//				dd.setDefaultURI(defaultURI);
//				DispositionItem di = dd.open();
				PrefixValidator pv = new PrefixValidator(defaultURI);
				
				NewDispoDialog sd = new NewDispoDialog
				(parent.getShell(), "", "Input URI", "Input Name", defaultURI, "Default Name", pv);
				DispositionItem di = sd.start();
				
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
				buttonBizRemove, bizListViewer, Type.BusinessStep, "urn:epcglobal:fmcg:bizstep:");
	}
	
	private void createDispositionPart(final Composite parent) {
		createTabPart(parent, Type.Disposition, "Disposition");
	}
	
	private void createDispositionPartLogic(final Composite parent) {
		createTabPartLogic(parent, buttonDispoNew, buttonDispoDuplicate, 
				buttonDispoRemove, dispoListViewer, Type.Disposition, "urn:epcglobal:fmcg:disp:");
	}
	
	private void createTransactionPart(final Composite parent) {
		createTabPart(parent, Type.TransactionType, "Business Transaction Type");
	}
	
	private void createTransactionPartLogic(final Composite parent) {
		createTabPartLogic(parent, buttonTranzNew, buttonTranzDuplicate, 
				buttonTranzRemove, tranzListViewer, Type.TransactionType, "urn:epcglobal:fmcg:btt:");
	}
	
	@Override
	public void setFocus() {
		//set the current master editor to this
		MasterDataBuilder mdb = MasterDataBuilder.getInstance();
		mdb.setMasterEditor(this);
		//reload eventName
//		String text = mdb.getAttribute("urn:epcglobal:epcis:mda:event_name");
//		eventNameText.setText(text);
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

	@Override
	public void selectionChanged(SelectionChangedEvent event) {
		ISelection selection = event.getSelection();
		MainControl mc = MainControl.getMainControl();
		if(selection instanceof IStructuredSelection) {
			IStructuredSelection sselection = (IStructuredSelection)selection;
			if(sselection.size() > 0) {
				Object selectObject = sselection.getFirstElement();
				if(selectObject instanceof CLCBProcEditPart) {
					
					CLCBProcEditPart clcbPart = (CLCBProcEditPart)selectObject;
					
					CLCBProcImpl clcbi = (CLCBProcImpl)((View)clcbPart.getModel()).getElement();
					CLCBProc clcbp =  (CLCBProc)mc.getMapObject(clcbi.hashCode());
					
					if(clcbp != null){
						System.out.println("Selected");
					}
				}
			} 
		}	
		
	}
}
