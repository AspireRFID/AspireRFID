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

package org.ow2.aspirerfid.ide.bpwme.ecspec.views;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.ListViewer;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.custom.CTabItem;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.FocusListener;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
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
import org.ow2.aspirerfid.commons.apdl.model.EBProc;
import org.ow2.aspirerfid.ide.bpwme.diagram.edit.parts.EBProcEditPart;
import org.ow2.aspirerfid.ide.bpwme.diagram.part.BpwmeDiagramEditor;
import org.ow2.aspirerfid.ide.bpwme.dialog.ComplexDialog;
import org.ow2.aspirerfid.ide.bpwme.dialog.InputDialog;
import org.ow2.aspirerfid.ide.bpwme.ecspec.model.*;
import org.ow2.aspirerfid.ide.bpwme.ecspec.provider.BoundaryContentProvider;
import org.ow2.aspirerfid.ide.bpwme.ecspec.provider.BoundaryEditingSupport;
import org.ow2.aspirerfid.ide.bpwme.ecspec.provider.BoundaryLabelProvider;
import org.ow2.aspirerfid.ide.bpwme.ecspec.provider.ReportContentProvider;
import org.ow2.aspirerfid.ide.bpwme.ecspec.provider.ReportLabelProvider;
import org.ow2.aspirerfid.ide.bpwme.ecspec.utils.ECSpecBuilder;
import org.ow2.aspirerfid.ide.bpwme.ecspec.utils.LRSpecBuilder;
import org.ow2.aspirerfid.ide.bpwme.ecspec.utils.SelectionProviderWrapper;
import org.ow2.aspirerfid.ide.bpwme.impl.EBProcImpl;

import org.ow2.aspirerfid.ide.bpwme.utils.MainControl;
import org.ow2.aspirerfid.ide.bpwme.utils.MainUtil;


/**
 * The ECSpecEditor has the following structure:
 * parent-
 * 	-ctab composite
 * 		-Logical Part
 * 			-Candidate Viewer
 * 			-Select Viewer
 * 		-Boundary Part
 * 	-report tree
 * 
 * @author Yongming Luo <yluo@ait.edu.gr>
 *
 */

public class ECSpecEditor extends EditorPart implements 
ITabbedPropertySheetPageContributor,ISelectionChangedListener{
	private ECSpecBuilder ecsb;
	private LRSpecBuilder lrsb;
	private EBProc ebproc;
	
	private TreeViewer reportViewer;
	private ListViewer startListViewer;
	private ListViewer stopListViewer;
	private ListViewer candidateViewer;
	private ListViewer selectViewer;
	private TableViewer attributeTableViewer;
	private Button changeStart;
	private Button newStart;
	private Button removeStart;
	private Button newStop;
	private Button changeStop;
	private Button removeStop;
	private Button availableButton;
	private SelectionProviderWrapper spw;
	
	protected boolean isdirty = false;
	
	public static final String ID = "org.ow2.aspirerfid.ide.bpwme.ecspec.views.ECSpecEditor";
	//private ListViewer logicalListViewer;
	@Override
	public void createPartControl(Composite parent) {
		
		final ScrolledComposite scrolledComposite = new ScrolledComposite(parent, SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL);

		final Composite composite = new Composite(scrolledComposite, SWT.NONE);
		composite.setLayout(new GridLayout());
		
		CTabFolder tabFolder = new CTabFolder(composite, SWT.TOP);
		tabFolder.setMaximized(true);
		tabFolder.setBorderVisible(true);
		tabFolder.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		tabFolder.setBackground(null);
		
		CTabItem ctiLogic = new CTabItem(tabFolder, 0);
		ctiLogic.setText("Logical");
		Composite compositeLogic = new Composite(tabFolder, SWT.NONE);
		compositeLogic.setLayout(new GridLayout());
		ctiLogic.setControl(compositeLogic);
		
		CTabItem ctiBound = new CTabItem(tabFolder, 0);
		ctiBound.setText("Boundary");
		Composite compositeBound = new Composite(tabFolder, SWT.NONE);
		compositeBound.setLayout(new GridLayout());
		ctiBound.setControl(compositeBound);
		
		//set default selection
		tabFolder.setSelection(ctiLogic);
		
		createLogicalPart(compositeLogic, ecsb);
		createBoundaryPart(compositeBound, ecsb);
		createBoundaryLogic(compositeBound, ecsb);
		createReportPart(composite, ecsb);
		
		getSite().setSelectionProvider(spw);
		composite.setSize(420, 650);
		scrolledComposite.setContent(composite);
		
	}

	public static ECSpecEditor getECSpecView() {
		try {
			return (ECSpecEditor)PlatformUI.getWorkbench().getActiveWorkbenchWindow().
			getActivePage().showView(ECSpecEditor.ID);
		} catch (PartInitException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public LRSpecBuilder getLrsb() {
		return lrsb;
	}
	
	@Override
	public void setFocus() {
		//treeViewer.getControl().setFocus();
		// TODO Auto-generated method stub
		//lll.getControl().setFocus();
	}

	
	public void refresh() {
		//logicalListViewer.refresh();
		candidateViewer.refresh();
		selectViewer.refresh();
	}
	
	
	public void changeName(Spec spec, String oldName, String newName) {
		spec.setName(newName);
		//delete the ecsb part if exists
		ecsb.changeLogicalReaderName(oldName, newName);
		
		candidateViewer.refresh(false);
		selectViewer.refresh(false);		
		setDirty(true);
	}
	
	
	private void createCandidateButtons(final Composite parent) {
		Button buttonNew = new Button(parent, SWT.PUSH);
		buttonNew.setText("New");

		Button buttonDuplicate = new Button(parent, SWT.PUSH);
		buttonDuplicate.setText("Duplicate");
		
		Button buttonRemove = new Button(parent, SWT.PUSH);
		buttonRemove.setText("Remove");
		
		buttonNew.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				ComplexDialog cd = new ComplexDialog(parent.getShell());
				cd.setMessage("Choose Reader Type");
				
				Spec item = cd.open();
				item.setBelongTo(ebproc);
				
				if(item instanceof LLRPSpec) {
					LLRPSpec newItem = ((LLRPSpec)item);
					lrsb.getLeftSpecList().add(newItem);
				} else if(item instanceof RPSpec) {
					RPSpec newItem = ((RPSpec)item);
					lrsb.getLeftSpecList().add(newItem);
				} else if(item instanceof HALSpec) {
					HALSpec newItem = ((HALSpec)item);
					lrsb.getLeftSpecList().add(newItem);
				}
				
				candidateViewer.refresh(false);
				
				lrsb.addLRSpec(item.getApdl());
				setDirty(true);
			}
		});
		
		buttonDuplicate.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				Spec item = (Spec)((IStructuredSelection)candidateViewer.getSelection()).getFirstElement();

				if(item instanceof LLRPSpec) {
					LLRPSpec newItem = ((LLRPSpec)item).getClone();
					lrsb.getLeftSpecList().add(newItem);
					lrsb.addLRSpec(newItem.getApdl());

				} else if(item instanceof RPSpec) {
					RPSpec newItem = ((RPSpec)item).getClone();
					lrsb.getLeftSpecList().add(newItem);
					lrsb.addLRSpec(newItem.getApdl());
					
				} else if(item instanceof HALSpec) {
					HALSpec newItem = ((HALSpec)item).getClone();
					lrsb.getLeftSpecList().add(newItem);
					lrsb.addLRSpec(newItem.getApdl());
				}
				candidateViewer.refresh(false);
				setDirty(true);
			}
		});


		buttonRemove.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				Spec s = (Spec)((IStructuredSelection)candidateViewer.getSelection()).getFirstElement();
				if(lrsb.getLeftSpecList().remove(s) == false) {
					System.out.println("Remove Nothing");
				}
				lrsb.removeLRSpec(s.getApdl());
				
				lrsb.getRightSpecList().remove(s);
				ecsb.removeLogicalReader(s.getName());
				
				candidateViewer.refresh(false);
				selectViewer.refresh(false);
				setDirty(true);
			}
		});
	}
	
	private void createCandidateView(Composite parent) {
		candidateViewer = new ListViewer(parent);
		candidateViewer.getControl().setLayoutData(
				new GridData(SWT.FILL, SWT.FILL, true, true));
		candidateViewer.setContentProvider(new IStructuredContentProvider() {
			@Override
			public Object[] getElements(Object inputElement) {
				return ((ArrayList<Spec>)inputElement).toArray();
			}
			@Override
			public void dispose() {
			}

			@Override
			public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
				
			}
		});
		
		candidateViewer.setLabelProvider(new LabelProvider(){
			@Override
			public String getText(Object element) {
				return ((Spec)element).getName()+"@"+((Spec)element).getBelongTo().getId();
			}
		});
		
		candidateViewer.setInput(lrsb.getLeftSpecList());
		
		candidateViewer.getControl().addFocusListener(new FocusListener() {

			@Override
			public void focusGained(FocusEvent e) {
				spw.setSelectionProvider(candidateViewer);
			}
			@Override
			public void focusLost(FocusEvent e) {				
			}			
		});
		lrsb.addListener(candidateViewer);
	}
	
	private void createFromToButtons(final Composite parent) {
		Button buttonTo = new Button(parent, SWT.ARROW|SWT.RIGHT);
		Button buttonFrom = new Button(parent, SWT.ARROW|SWT.LEFT);
		buttonTo.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, true));
		buttonFrom.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, true));
		
		
		//buttonTo.setLayoutData(new RowData(30, 30));
		buttonTo.addSelectionListener(new SelectionAdapter(){
			@Override
			public void widgetSelected(SelectionEvent e) {
				IStructuredSelection lselection = (IStructuredSelection)candidateViewer.getSelection();
								
				Iterator<?> liter = lselection.iterator();
				while(liter.hasNext()) {
					Spec temp = (Spec)liter.next();
					//if not belongs to the current selection
					//copy it to the current ebproc
					if(temp.getBelongTo() != ebproc) {
						Spec newItem = temp.getClone();
						newItem.setBelongTo(ebproc);
						lrsb.getLeftSpecList().add(newItem);
						lrsb.addLRSpec(newItem.getApdl());
						temp = newItem;
					}
					//lrsb.getLeftSpecList().remove(temp);
					lrsb.getRightSpecList().add(temp);
					ecsb.addLogicalReader(temp.getName());
				}
				candidateViewer.refresh(false);
				selectViewer.refresh(false);
				setDirty(true);
			}
		});
		
		buttonFrom.addSelectionListener(new SelectionAdapter(){
			@Override
			public void widgetSelected(SelectionEvent e) {
				IStructuredSelection lselection = (IStructuredSelection)selectViewer.getSelection();
				Iterator liter = lselection.iterator();    		
				while(liter.hasNext()) {
					Spec temp = (Spec)liter.next();
					lrsb.getRightSpecList().remove(temp);
					//lrsb.getLeftSpecList().add(temp);
					ecsb.removeLogicalReader(temp.getName());
				}
				candidateViewer.refresh(false);
				selectViewer.refresh(false);
				setDirty(true);
			}
		});
	}
	private void createLogicalPart(Composite parent, final ECSpecBuilder ecsb) {
//		final Group logicalReadersGroup = new Group(parent, SWT.NONE);
//		logicalReadersGroup.setText("logical readers");
//		final GridData gd_logicalReadersGroup = new GridData(SWT.FILL, SWT.FILL, true, true);
//		logicalReadersGroup.setLayoutData(gd_logicalReadersGroup);
//		final GridLayout gridLayout = new GridLayout();
//		//gridLayout.numColumns = 2;
//		logicalReadersGroup.setLayout(gridLayout);
		
		Composite cbComposite = new Composite(parent,0);
		cbComposite.setLayout(new GridLayout(6, false));
		cbComposite.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		
		createCandidateButtons(cbComposite);

		Composite viewComposite = new Composite(parent,0);
		viewComposite.setLayout(new GridLayout(3, false));
		viewComposite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
		
		Group candidateGrp = new Group(viewComposite, SWT.SHADOW_NONE);
		GridData candidateGd = new GridData(SWT.FILL, SWT.FILL, true, true);
		candidateGd.widthHint = 100;
		candidateGrp.setLayoutData(candidateGd);
		candidateGrp.setText("Candidate Readers");
		candidateGrp.setLayout(new GridLayout());
		
		createCandidateView(candidateGrp);
		
		Composite toFromComposite = new Composite(viewComposite, 0);
		toFromComposite.setLayout(new GridLayout());
		GridData toFromData = new GridData();
		toFromData.widthHint = 40;
		toFromComposite.setLayoutData(toFromData);
		
		createFromToButtons(toFromComposite);
		
		
		Group selectGrp = new Group(viewComposite, SWT.SHADOW_NONE);
		GridData selectGd = new GridData(SWT.FILL, SWT.FILL, true, true);
		selectGd.widthHint = 100;
		selectGrp.setLayoutData(selectGd);
		selectGrp.setText("Select Readers");
		selectGrp.setLayout(new GridLayout());

		createSelectView(selectGrp);

		
//		//logical list viewer configuration
//		logicalListViewer = new ListViewer(logicalReadersGroup, SWT.BORDER);
//		//GridData gd_logicalData = new GridData(300,50);
//		logicalListViewer.getList().setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
//		ecsb.addListener(logicalListViewer);
//		logicalListViewer.setContentProvider(new IStructuredContentProvider() {
//			@Override
//			public Object[] getElements(Object inputElement) {
//				// TODO Auto-generated method stub
//				return ((List<String>)inputElement).toArray();
//			}
//			@Override
//			public void dispose() {
//				// TODO Auto-generated method stub
//			}
//
//			@Override
//			public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
//				// TODO Auto-generated method stub
//				//viewer.refresh();
//			}
//		});
//		logicalListViewer.setInput(ecsb.getLogicalReaders());
//		
//		logicalListViewer.setLabelProvider(new LabelProvider(){
//			@Override
//			public String getText(Object element) {
//				return (String)element;
//				//return super.getText(element);
//			}
//		});
//				
//		final Button changeButton = new Button(logicalReadersGroup, SWT.NONE);
//		changeButton.setText("change");
//		//TODO add button action
//		changeButton.addMouseListener(new MouseAdapter(){
//			@Override
//			public void mouseDown(MouseEvent e) {
//				super.mouseDown(e);
//				MainControl mc = MainControl.getMainControl();
//				IWorkbenchPage page = PlatformUI.getWorkbench().
//				getActiveWorkbenchWindow().getActivePage();
//				
//				if(mc.lrEditor != null) {
//					LRSpecEditorInput oldInput = (LRSpecEditorInput)mc.lrEditor.getEditorInput();
//					if(oldInput.getLRSpecBuilder() == mc.lrsb) {
//						try {
//							mc.lrEditor = (LRSpecEditor)page.openEditor(mc.lrEditor.getEditorInput(), LRSpecEditor.ID);
//						} catch (PartInitException e1) {
//							// TODO Auto-generated catch block
//							e1.printStackTrace();
//						}
//						return;
//					}
//				}
//				
//				LRSpecEditorInput lei = new LRSpecEditorInput(lrsb, ecsb);								
//				try {
//					LRSpecEditor lre = (LRSpecEditor)page.openEditor(lei, LRSpecEditor.ID);			
//					mc.lrEditor = lre;
//					//System.out.println(lrse);
//					//lrse.setDirty(true);
//				} catch (PartInitException e1) {
//					// TODO Auto-generated catch block
//					e1.printStackTrace();
//				}
//				//ecsb.removeLogicalReader(ECSpecView.this.id); 
//				//ECSpecView.this.refresh();
//				//ECSpecView.this.sv = SampleView.getSampleView();
//			}
//		});
	}
	
	private void createSelectView(Composite parent) {
		selectViewer = new ListViewer(parent);
		selectViewer.getControl().setLayoutData(
				new GridData(SWT.FILL, SWT.FILL, true, true));
		selectViewer.setContentProvider(new IStructuredContentProvider() {
			@Override
			public Object[] getElements(Object inputElement) {
				// TODO Auto-generated method stub
				return ((ArrayList<Spec>)inputElement).toArray();
			}
			@Override
			public void dispose() {
				// TODO Auto-generated method stub
			}

			@Override
			public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
				// TODO Auto-generated method stub
			}
		});
		
		selectViewer.setLabelProvider(new LabelProvider(){
			@Override
			public String getText(Object element) {
				// TODO Auto-generated method stub
				return ((Spec)element).getName();

				//return super.getText(element);
			}
		});
			
		selectViewer.setInput(lrsb.getRightSpecList());
		
		selectViewer.getControl().addFocusListener(new FocusListener() {

			@Override
			public void focusGained(FocusEvent e) {
				spw.setSelectionProvider(selectViewer);
			}
			@Override
			public void focusLost(FocusEvent e) {
			}
		});
		lrsb.addListener(selectViewer);
	}
	
	private void createBoundaryLogic(final Composite parent, final ECSpecBuilder ecsb) {
		
		startListViewer.setContentProvider(new IStructuredContentProvider() {
			@Override
			public Object[] getElements(Object inputElement) {
				return ((List<String>)inputElement).toArray();
			}
			@Override
			public void dispose() {
			}

			@Override
			public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
			}
		});
		
		startListViewer.setLabelProvider(new LabelProvider(){
			@Override
			public String getText(Object element) {
				return (String)element;
			}
		});
		
		startListViewer.setInput(ecsb.getStartTriggerList());
		ecsb.addListener(startListViewer);

		stopListViewer.setContentProvider(new IStructuredContentProvider() {
			@Override
			public Object[] getElements(Object inputElement) {
				return ((List<String>)inputElement).toArray();
			}
			@Override
			public void dispose() {
			}

			@Override
			public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
			}
		});
		stopListViewer.setLabelProvider(new LabelProvider(){
			@Override
			public String getText(Object element) {
				return (String)element;
			}
		});
		stopListViewer.setInput(ecsb.getStopTriggerList());
		ecsb.addListener(stopListViewer);
		
		newStart.addMouseListener(new MouseAdapter(){
			@Override
			public void mouseDown(MouseEvent e) {
				super.mouseDown(e);
				InputDialog id = new InputDialog(parent.getShell());
				String input = id.open();
				if((input != null) && (!input.equals(""))) {
					ecsb.addStartTrigger(input);
					startListViewer.refresh();
					setDirty(true);
				}
			}
		});
		changeStart.addMouseListener(new MouseAdapter(){
			@Override
			public void mouseDown(MouseEvent e) {
				super.mouseDown(e);
				InputDialog id = new InputDialog(parent.getShell());
				String oldStart = (String)((IStructuredSelection)startListViewer.getSelection()).getFirstElement();
				id.setInput(oldStart);
				String newStart = id.open();
				ecsb.changeStartTrigger(oldStart, newStart);
				startListViewer.refresh();
				setDirty(true);
			}
		});
		removeStart.addMouseListener(new MouseAdapter(){
			@Override
			public void mouseDown(MouseEvent e) {
				super.mouseDown(e);
				String oldStart = (String)((IStructuredSelection)startListViewer.getSelection()).getFirstElement();
				ecsb.removeStartTrigger(oldStart);
				startListViewer.refresh();
				setDirty(true);
			}

		});
		
		newStop.addMouseListener(new MouseAdapter(){
			@Override
			public void mouseDown(MouseEvent e) {
				super.mouseDown(e);
				InputDialog id = new InputDialog(parent.getShell());
				String input = id.open();
				if((input != null) && (!input.equals(""))) {
					ecsb.addStopTrigger(input);
					stopListViewer.refresh();
					setDirty(true);
				}				
			}
		});
		changeStop.addMouseListener(new MouseAdapter(){
			@Override
			public void mouseDown(MouseEvent e) {
				super.mouseDown(e);
				InputDialog id = new InputDialog(parent.getShell());
				String oldStop = (String)((IStructuredSelection)stopListViewer.getSelection()).getFirstElement();
				id.setInput(oldStop);
				String newStop = id.open();
				ecsb.changeStopTrigger(oldStop, newStop);
				stopListViewer.refresh();
				setDirty(true);
			}
		});
		removeStop.addMouseListener(new MouseAdapter(){
			@Override
			public void mouseDown(MouseEvent e) {
				super.mouseDown(e);
				String oldStop = (String)((IStructuredSelection)stopListViewer.getSelection()).getFirstElement();
				ecsb.removeStopTrigger(oldStop);
				stopListViewer.refresh();
				setDirty(true);
			}
		});
		
		availableButton.addSelectionListener(new SelectionAdapter(){
			@Override
			public void widgetSelected(SelectionEvent e) {			
				super.widgetSelected(e);
				ecsb.setWhenDataAvailiable(availableButton.getSelection());
				setDirty(true);
			}
		});
	}
	
	private void createBoundaryPart(Composite parent, final ECSpecBuilder ecsb) {
		//boundary spec
		final Group boundaryGroup = new Group(parent, SWT.NONE);
		boundaryGroup.setText("boundary spec");
		final GridData gd_boundaryGroup = new GridData(SWT.FILL, SWT.FILL, true, true);
		//GridData(350, 200);
		boundaryGroup.setLayoutData(gd_boundaryGroup);
		final GridLayout bgridLayout = new GridLayout();
		//bgridLayout.numColumns = 4;
		boundaryGroup.setLayout(bgridLayout);
		
		
		
		attributeTableViewer  = new TableViewer(boundaryGroup,
				SWT.BORDER | SWT.FULL_SELECTION);
		TableViewerColumn column = new TableViewerColumn(attributeTableViewer,SWT.NONE);
		//column.setLabelProvider(new BoundaryNameLabelProvider());
		column.getColumn().setWidth(100);
		column.getColumn().setText("Name");
		
		column = new TableViewerColumn(attributeTableViewer,SWT.NONE);
		//column.setLabelProvider(new BoundaryValueLabelProvider());
		column.getColumn().setWidth(100);
		column.getColumn().setText("Value");
		column.setEditingSupport(new BoundaryEditingSupport(attributeTableViewer));
		
		attributeTableViewer.setContentProvider(new BoundaryContentProvider());
		attributeTableViewer.setLabelProvider(new BoundaryLabelProvider());
		attributeTableViewer.setInput(ecsb);
		

		attributeTableViewer.getTable().setLinesVisible(true);
		attributeTableViewer.getTable().setHeaderVisible(true);
		
		GridData tgd = new GridData(SWT.FILL, SWT.FILL, true, true);
		attributeTableViewer.getTable().setLayoutData(tgd);
		//ecsb.addListener(attributeTableViewer);
		
		
		//Table table = tableViewer.getTable();
		//new TableColumn(table, SWT.LEFT).setText("Name");
		//new TableColumn(table, SWT.LEFT).setText("Value");
		/*
		final TableViewer triggerViewer = new TableViewer(boundaryGroup,
				SWT.BORDER | SWT.FULL_SELECTION);
		column = new TableViewerColumn(triggerViewer,SWT.NONE);
		column.setLabelProvider(new BoundaryTriggerLabelProvider());
		column.getColumn().setWidth(100);
		column.getColumn().setText("Start");
		*/
		
		Composite startstopText = new Composite(boundaryGroup, 0);
		
		startstopText.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
		GridLayout textLayout = new GridLayout(2, true);
		startstopText.setLayout(textLayout);
		Label startLabel = new Label(startstopText, SWT.LEFT);
		startLabel.setText("Start Trigger List");
		startLabel.setLayoutData(new GridData(SWT.FILL, SWT.TOP, true, true));
		Label stopLabel = new Label(startstopText, SWT.LEFT);
		stopLabel.setText("Stop Trigger List");
		stopLabel.setLayoutData(new GridData(SWT.FILL, SWT.TOP, true, true));
		
		Composite startstop = new Composite(boundaryGroup, 0);
		GridLayout glss = new GridLayout(5, false);
		startstop.setLayout(glss);
		startListViewer = new ListViewer(startstop, SWT.BORDER);
		GridData startGD = new GridData(SWT.FILL, SWT.FILL, true, true);
		startGD.widthHint = 100;
		startListViewer.getControl().setLayoutData(startGD);
		
		
		Composite startbComposite = new Composite(startstop, 0);
		startbComposite.setLayout(new GridLayout());
		
		GridData data = new GridData(GridData.FILL_BOTH);
		newStart = new Button(startbComposite, SWT.NONE);
		newStart.setText("New");
		newStart.setLayoutData(data);
		
		data = new GridData(GridData.FILL_BOTH);
		changeStart = new Button(startbComposite, SWT.NONE);
		changeStart.setLayoutData(data);
		changeStart.setText("Change");
		
		data = new GridData(GridData.FILL_BOTH);
		removeStart = new Button(startbComposite, SWT.NONE);
		removeStart.setLayoutData(data);
		removeStart.setText("Remove");
				
		new Label(startstop, SWT.SEPARATOR | SWT.VERTICAL);
		
		stopListViewer = new ListViewer(startstop, SWT.BORDER);
		GridData stopGD = new GridData(SWT.FILL, SWT.FILL, true, true);
		stopGD.widthHint = 100;
		stopListViewer.getControl().setLayoutData(stopGD);
		
		Composite stopbComposite = new Composite(startstop, 0);
		stopbComposite.setLayout(new GridLayout());
		
		data = new GridData(GridData.FILL_BOTH);
		newStop = new Button(stopbComposite, SWT.NONE);
		newStop.setText("New");
		newStop.setLayoutData(data);
		
		data = new GridData(GridData.FILL_BOTH);
		changeStop = new Button(stopbComposite, SWT.NONE);
		changeStop.setLayoutData(data);
		changeStop.setText("Change");
		
		data = new GridData(GridData.FILL_BOTH);
		removeStop = new Button(stopbComposite, SWT.NONE);
		removeStop.setLayoutData(data);
		removeStop.setText("Remove");
		
		//next line
		new Label(boundaryGroup, SWT.SEPARATOR | SWT.HORIZONTAL);
		
		availableButton = new Button(boundaryGroup, SWT.CHECK);
		availableButton.setText("When Data Available");

	}
	
	private void createReportPart(Composite parent, ECSpecBuilder ecsb) {
		final Group reportGroup = new Group(parent, SWT.NONE);
		reportGroup.setText("report spec");
		final GridData gd_reportGroup = new GridData(GridData.FILL_BOTH);
		reportGroup.setLayoutData(gd_reportGroup);
		final GridLayout rgridLayout = new GridLayout();
		reportGroup.setLayout(rgridLayout);
		
		reportViewer = new TreeViewer(reportGroup);
		reportViewer.setContentProvider(new ReportContentProvider(ecsb.getECReportSpecs(), ecsb));
		reportViewer.setLabelProvider(new ReportLabelProvider());
		ArrayList<ReportSpec> lista = new ArrayList<ReportSpec>();
		for(ECReportSpec ecrs:ecsb.getECReportSpecs()) {
			lista.add(new ReportSpec(ecrs, ecsb));
		}
		reportViewer.setInput(lista);
		reportViewer.getControl().setLayoutData(new GridData(GridData.FILL_BOTH));
		reportViewer.getControl().addFocusListener(new FocusListener() {

			@Override
			public void focusGained(FocusEvent e) {
				spw.setSelectionProvider(reportViewer);
			}
			@Override
			public void focusLost(FocusEvent e) {
			}			
		});
		ecsb.addListener(reportViewer);
		
//		treeViewer.addSelectionChangedListener(new ISelectionChangedListener(){
//
//			@Override
//			public void selectionChanged(SelectionChangedEvent event) {
//				// TODO Auto-generated method stub
//				
//				System.out.println(event.getSelection());				
//			}			
//		});
		/*
		lll = new ListViewer(reportGroup);
		lll.setContentProvider(new ReportContentProvider(ecsb.getECReportSpecs()));
		lll.setLabelProvider(new ReportLabelProvider());
		lll.setInput(ecsb.getECReportSpecs());
		*/
		
	}


	@Override
	public String getContributorId() {
		return getSite().getId();
	}
	
	@Override
	public Object getAdapter(Class adapter) {
        if (adapter == IPropertySheetPage.class)
            return new TabbedPropertySheetPage(this);
        return super.getAdapter(adapter);
	}

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
        
        lrsb = ((ECLRInput)input).getLRSpecBuilder();
        ecsb = ((ECLRInput)input).getECSpecBuilder();
        spw = new SelectionProviderWrapper();
        ebproc = ((ECLRInput)input).getEbproc();
        
		//add selection listener to bpwme editor
        BpwmeDiagramEditor editor = MainUtil.getBPWMEEditor();
		if(editor != null) {
			editor.addSelectionListener(this);
		}
	}

	@Override
	public boolean isDirty() {
		return isdirty;
	}
	
	
    public void setDirty(boolean value) {
        isdirty = value;
        firePropertyChange(PROP_DIRTY);
     }

	@Override
	public boolean isSaveAsAllowed() {
		return false;
	}

	@Override
	public void selectionChanged(SelectionChangedEvent event) {
		
		ISelection selection = event.getSelection();
		MainControl mc = MainControl.getMainControl();
		if(selection instanceof IStructuredSelection) {
			IStructuredSelection sselection = (IStructuredSelection)selection;
			if(sselection.size() > 0) {
				Object selectObject = sselection.getFirstElement();
				
				if(selectObject instanceof EBProcEditPart) {
					
					EBProcEditPart ebPart = (EBProcEditPart)selectObject;
					
					EBProcImpl ebpi = (EBProcImpl)((View)ebPart.getModel()).getElement();
					EBProc ebp =  (EBProc)mc.getMapObject(ebpi.hashCode());
					
					if(ebp != null){
						//((ECLRInput)getEditorInput()).setEBProc(ebp);
						ebproc = ebp;
						ecsb.setEBProc(ebp);// = ((ECLRInput)getEditorInput()).getECSpecBuilder();
						lrsb.setEBProc(ebp);// = ((ECLRInput)getEditorInput()).getLRSpecBuilder();
										
						startListViewer.setInput(ecsb.getStartTriggerList());
						stopListViewer.setInput(ecsb.getStopTriggerList());
						
						attributeTableViewer.setInput(ecsb);
						ArrayList<ReportSpec> lista = new ArrayList<ReportSpec>();
						
						for(ECReportSpec ecrs:ecsb.getECReportSpecs()) {
							lista.add(new ReportSpec(ecrs, ecsb));
						}
						reportViewer.setInput(lista);
						availableButton.setSelection(ecsb.getWhenDataAvailable());
						
						startListViewer.refresh();
						stopListViewer.refresh();
						attributeTableViewer.refresh();
						reportViewer.refresh();
					}
				}
			} 
		}	
	}
	
	@Override
	public void dispose() {
		//remove listeners
		
		BpwmeDiagramEditor editor = MainUtil.getBPWMEEditor();
		if(editor != null) {
			editor.removeSelectionListener(this);
		}		
		lrsb.clearListeners();
		ecsb.clearListeners();
		super.dispose();
	}
	
	
}

