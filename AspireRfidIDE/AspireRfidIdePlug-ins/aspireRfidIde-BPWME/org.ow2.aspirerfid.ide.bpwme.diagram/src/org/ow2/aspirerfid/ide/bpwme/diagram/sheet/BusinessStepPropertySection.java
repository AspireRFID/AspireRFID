package org.ow2.aspirerfid.ide.bpwme.diagram.sheet;
import java.util.ArrayList;
import java.util.Iterator;

import javax.xml.namespace.QName;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.transaction.util.TransactionUtil;
import org.eclipse.gef.EditPart;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.ListViewer;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.layout.GridData;

import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.views.properties.IPropertySource;
import org.eclipse.ui.views.properties.IPropertySourceProvider;
import org.eclipse.ui.views.properties.tabbed.AbstractPropertySection;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;
import org.ow2.aspirerfid.commons.epcis.model.AttributeType;
import org.ow2.aspirerfid.ide.bpwme.diagram.preferences.PreferenceConstants;
import org.ow2.aspirerfid.ide.bpwme.diagram.preferences.PreferenceUtil;
import org.ow2.aspirerfid.ide.bpwme.dialog.ComboDialog;
import org.ow2.aspirerfid.ide.bpwme.dialog.EditVocabularyAttributeDialog;
import org.ow2.aspirerfid.ide.bpwme.dialog.InputDialog;
import org.ow2.aspirerfid.ide.bpwme.dialog.PrefixValidator;
import org.ow2.aspirerfid.ide.bpwme.dialog.NewDispoDialog;
import org.ow2.aspirerfid.ide.bpwme.master.model.DispositionItem;
import org.ow2.aspirerfid.ide.bpwme.master.utils.MasterDataBuilder;
import org.ow2.aspirerfid.ide.bpwme.utils.MainControl;


/**
* This code was edited or generated using CloudGarden's Jigloo
* SWT/Swing GUI Builder, which is free for non-commercial
* use. If Jigloo is being used commercially (ie, by a corporation,
* company or business for any purpose whatever) then you
* should purchase a license for each developer using Jigloo.
* Please visit www.cloudgarden.com for details.
* Use of Jigloo implies acceptance of these licensing terms.
* A COMMERCIAL LICENSE HAS NOT BEEN PURCHASED FOR
* THIS MACHINE, SO JIGLOO OR THIS CODE CANNOT BE USED
* LEGALLY FOR ANY CORPORATE OR COMMERCIAL PURPOSE.
*/
public class BusinessStepPropertySection extends AbstractPropertySection{
	private ListViewer listViewerMain;
	private Group listButtonGroup;
	private Button propertyButtonRemove;
	private Button propertyButtonEdit;
	private Button propertyButtonNew;
	private Group propertyButtonGroup;
	private TableViewer propertyViewer;
	private Button buttonRemove;
	private Button buttonEdit;
	private Button buttonNew;

	public BusinessStepPropertySection() {
	}	
	
	@Override
	public void createControls(final Composite parent,
			TabbedPropertySheetPage aTabbedPropertySheetPage) {
		super.createControls(parent, aTabbedPropertySheetPage);
		final MasterDataBuilder mdb = MasterDataBuilder.getInstance();
		final MainControl mc = MainControl.getMainControl();
		Composite composite = getWidgetFactory().createFlatFormComposite(parent);
		FormLayout compositeLayout = new FormLayout();
		composite.setLayout(compositeLayout);
		{
			listButtonGroup = getWidgetFactory().createGroup(composite, "Edit List");
			GridLayout group1Layout = new GridLayout();
			group1Layout.makeColumnsEqualWidth = true;
			listButtonGroup.setLayout(group1Layout);
			FormData group1LData = new FormData();
			group1LData.left =  new FormAttachment(0, 220);
			group1LData.top =  new FormAttachment(0, 5);
			group1LData.bottom = new FormAttachment(100, -5);
			group1LData.width = 60;
			listButtonGroup.setLayoutData(group1LData);
			{
				buttonNew = new Button(listButtonGroup, SWT.PUSH | SWT.CENTER);
				GridData buttonNewLData = new GridData();
				buttonNewLData.horizontalAlignment = GridData.FILL;
				buttonNew.setLayoutData(buttonNewLData);
				buttonNew.setText("New");
				
				buttonNew.addSelectionListener(new SelectionAdapter(){
					@Override
					public void widgetSelected(SelectionEvent e) {
						String defaultURI = "urn:epcglobal:fmcg:bizstep:";
						PrefixValidator pv = new PrefixValidator(defaultURI);						
						NewDispoDialog sd = new NewDispoDialog
						(parent.getShell(), "", "Input URI", "Input Name", defaultURI, "Default Name", pv);
						DispositionItem di = sd.start();
						
						if(di == null) {
							return;
						}
						mdb.addBizStepItem(di);
						listViewerMain.refresh(false);
						mc.saveObject();
						//setDirty();
					}
				});

			}
			{
				buttonEdit = new Button(listButtonGroup, SWT.PUSH | SWT.CENTER);
				GridData buttonEditLData = new GridData();
				buttonEditLData.horizontalAlignment = GridData.FILL;
				buttonEdit.setLayoutData(buttonEditLData);
				buttonEdit.setText("Edit");
				
				buttonEdit.addSelectionListener(new SelectionAdapter(){
					@Override
					public void widgetSelected(SelectionEvent e) {
						DispositionItem di = (DispositionItem)((IStructuredSelection)listViewerMain.getSelection()).getFirstElement();
						if(di == null) {
							return;
						}else {
							InputDialog id = new InputDialog(parent.getShell());
							id.setInput(di.getID());
							String newId = id.open();
							if(!newId.equals("")) {
								di.setID(newId);
							}
							listViewerMain.refresh(false);					
							mc.saveObject();
						}
					}
				});
				
			}
			{
				buttonRemove = new Button(listButtonGroup, SWT.PUSH | SWT.CENTER);
				GridData buttonRemoveLData = new GridData();
				buttonRemoveLData.horizontalAlignment = GridData.FILL;
				buttonRemove.setLayoutData(buttonRemoveLData);
				buttonRemove.setText("Remove");
								
				buttonRemove.addSelectionListener(new SelectionAdapter(){
					@Override
					public void widgetSelected(SelectionEvent e) {
						DispositionItem di = (DispositionItem)((IStructuredSelection)listViewerMain.getSelection()).getFirstElement();
						if(di == null) {
							return;
						}else {
							mdb.removeBizStepItem(di);
							listViewerMain.refresh(false);					
							mc.saveObject();
						}
					}
				});
			}
		}
		{
			
			propertyButtonGroup = getWidgetFactory().createGroup(composite, "Edit Properties");
//			groupButtons = new Group(composite, SWT.NONE);
			GridLayout groupButtonsLayout = new GridLayout();
			groupButtonsLayout.makeColumnsEqualWidth = true;
			propertyButtonGroup.setLayout(groupButtonsLayout);
			FormData groupButtonsLData = new FormData();
			groupButtonsLData.left =  new FormAttachment(0, 625);
			groupButtonsLData.top =  new FormAttachment(0, 5);
			groupButtonsLData.bottom =  new FormAttachment(100, -5);

			groupButtonsLData.width = 60;
			//groupButtonsLData.height = 140;
			propertyButtonGroup.setLayoutData(groupButtonsLData);
			{
				propertyButtonNew = new Button(propertyButtonGroup, SWT.PUSH | SWT.CENTER);
				GridData button1LData = new GridData();
				button1LData.horizontalAlignment = SWT.FILL;
				//button1LData.grabExcessHorizontalSpace = true;
				propertyButtonNew.setLayoutData(button1LData);
				propertyButtonNew.setText("New");
				
				propertyButtonNew.addSelectionListener(new SelectionAdapter(){
					@Override
					public void widgetSelected(SelectionEvent e) {
						
						//create new attribute item
						AttributeType attr = new AttributeType();
						attr.setId("urn:epcglobal:epcis:mda:");
						attr.getOtherAttributes().put(new QName("value"), "Default Value");
						//set the dialog and open
						EditVocabularyAttributeDialog ed = new EditVocabularyAttributeDialog(parent.getShell(),attr);						
						
						DispositionItem input = (DispositionItem)propertyViewer.getInput();
						if(input == null) {
							return;
						}
						
						String id = input.getID();
						
						if(id.startsWith("urn:epcglobal:fmcg:bizstep:")) {
							ed.setOptions(PreferenceUtil.getAttributes(PreferenceConstants.P_BS));
						}else if(id.startsWith("urn:epcglobal:fmcg:disp:")) {
							ed.setOptions(PreferenceUtil.getAttributes(PreferenceConstants.P_DI));
						}else if(id.startsWith("urn:epcglobal:fmcg:btt:")) {
							ed.setOptions(PreferenceUtil.getAttributes(PreferenceConstants.P_BT));
						}
						
						attr = ed.start();
						//if return a null, means user press cancel, do nothing
						if(attr == null) {							
							
						}else {//if not, means to add a new attribute item
							input.getVocabularyElement().getAttribute().add(attr);
							MainControl mc = MainControl.getMainControl();
							propertyViewer.refresh(false);
							mc.saveObject();

							if(id.startsWith("urn:epcglobal:fmcg:bizstep:")) {
								PreferenceUtil.addAttribute(PreferenceConstants.P_BS, attr.getId());
							}else if(id.startsWith("urn:epcglobal:fmcg:disp:")) {
								PreferenceUtil.addAttribute(PreferenceConstants.P_DI, attr.getId());
							}else if(id.startsWith("urn:epcglobal:fmcg:btt:")) {
								PreferenceUtil.addAttribute(PreferenceConstants.P_BT, attr.getId());
							}
						}
					}
				});
				
			}
			{
				propertyButtonEdit = new Button(propertyButtonGroup, SWT.PUSH | SWT.CENTER);
				GridData button2LData = new GridData();
				button2LData.horizontalAlignment = SWT.FILL;
				//button2LData.grabExcessHorizontalSpace = true;
				propertyButtonEdit.setLayoutData(button2LData);
				propertyButtonEdit.setText("Edit");
				
				propertyButtonEdit.addSelectionListener(new SelectionAdapter(){
					@Override
					public void widgetSelected(SelectionEvent e) {
						StructuredSelection selection = (StructuredSelection)propertyViewer.getSelection();
						if(selection == null) {
							return;
						}
						AttributeType attr = (AttributeType) selection.getFirstElement();
						EditVocabularyAttributeDialog ed = new EditVocabularyAttributeDialog(parent.getShell(),attr);
						
						DispositionItem input = (DispositionItem)propertyViewer.getInput();
						if(input == null) {
							return;
						}
						
						String id = input.getID();
						if(id.startsWith("urn:epcglobal:fmcg:bizstep:")) {
							ed.setOptions(PreferenceUtil.getAttributes(PreferenceConstants.P_BS));
						}else if(id.startsWith("urn:epcglobal:fmcg:disp:")) {
							ed.setOptions(PreferenceUtil.getAttributes(PreferenceConstants.P_DI));
						}else if(id.startsWith("urn:epcglobal:fmcg:btt:")) {
							ed.setOptions(PreferenceUtil.getAttributes(PreferenceConstants.P_BT));
						}
						
						ed.start();
						propertyViewer.refresh();
						MainControl mc = MainControl.getMainControl();
						mc.saveObject();
						
					}
				});
			}
			{
				propertyButtonRemove = new Button(propertyButtonGroup, SWT.PUSH | SWT.CENTER);
				GridData button3LData = new GridData();
				button3LData.horizontalAlignment = SWT.FILL;
				//button3LData.grabExcessHorizontalSpace = true;
				propertyButtonRemove.setLayoutData(button3LData);
				propertyButtonRemove.setText("Remove");
				
				propertyButtonRemove.addSelectionListener(new SelectionAdapter(){
					@Override
					public void widgetSelected(SelectionEvent e) {
						DispositionItem input = (DispositionItem)propertyViewer.getInput();
						if(input == null) {
							return;
						}
						StructuredSelection selection = (StructuredSelection)propertyViewer.getSelection();
						if(selection == null) {
							return;
						}
						AttributeType attr = (AttributeType) selection.getFirstElement();
						MasterDataBuilder mdb = MasterDataBuilder.getInstance();
						mdb.removeItemAttribute(input, attr.getId());
						MainControl mc = MainControl.getMainControl();
						mc.saveObject();
						propertyViewer.refresh(false);
					}
				});
			}
		}
		{
			FormData tableViewerLData = new FormData();
			tableViewerLData.left =  new FormAttachment(0, 295);
			tableViewerLData.top =  new FormAttachment(0, 5);
			tableViewerLData.bottom =  new FormAttachment(100, -5);
			tableViewerLData.width = 300;
			

			//tableViewerLData.height = 100;
			propertyViewer = new TableViewer(composite, SWT.BORDER|SWT.MULTI| 
					SWT.H_SCROLL|SWT.V_SCROLL );
			propertyViewer.getControl().setLayoutData(tableViewerLData);
			
//			Table table = propertyViewer.getTable();
//			TableColumn tc = new TableColumn(table, SWT.LEFT);
//			tc.setText("Name");
//			tc = new TableColumn(table, SWT.LEFT);
//			tc.setText("Attribute");
			TableViewerColumn tvc = new TableViewerColumn(propertyViewer,SWT.NONE);
			tvc.getColumn().setText("Name");
			tvc.getColumn().setWidth(200);
			
			tvc = new TableViewerColumn(propertyViewer,SWT.NONE);
			tvc.getColumn().setText("Attribute");
			tvc.getColumn().setWidth(100);
			
			Table table = propertyViewer.getTable();
			table.setLinesVisible(true);
			
			propertyViewer.setContentProvider(new IStructuredContentProvider(){

				@Override
				public Object[] getElements(Object inputElement) {
					DispositionItem di = (DispositionItem)inputElement;
					return di.getVocabularyElement().getAttribute().toArray();
				}

				@Override
				public void dispose() {
				}

				@Override
				public void inputChanged(Viewer viewer, Object oldInput,
						Object newInput) {
				}
				
			});
			
			propertyViewer.setLabelProvider(new ITableLabelProvider(){

				@Override
				public Image getColumnImage(Object element, int columnIndex) {
					return null;
				}

				@Override
				public String getColumnText(Object element, int columnIndex) {
					AttributeType attr = (AttributeType)element;
					
					switch(columnIndex) {
					case 0:
						return attr.getId();
						
					case 1:
						return attr.getOtherAttributes().get(new QName("value"));
						
					default:
						System.out.println("Not possible");
					}
					return null;
				}

				@Override
				public void addListener(ILabelProviderListener listener) {
				}

				@Override
				public void dispose() {
				}

				@Override
				public boolean isLabelProperty(Object element, String property) {
					return false;
				}

				@Override
				public void removeListener(ILabelProviderListener listener) {
				}
				
			});
			
			//propertyViewer.setInput(((IStructuredSelection)listViewerMain.getSelection()).getFirstElement());
			
		}
		{
			FormData listViewerMainLData = new FormData();
			listViewerMainLData.left =  new FormAttachment(0, 5);
			listViewerMainLData.top =  new FormAttachment(0, 5);
			listViewerMainLData.bottom = new FormAttachment(100, -5);
			listViewerMainLData.width = 180;
			listViewerMainLData.height = 100;
			listViewerMain = new ListViewer(composite, SWT.BORDER|SWT.H_SCROLL|SWT.V_SCROLL);
			listViewerMain.getControl().setLayoutData(listViewerMainLData);
			
			
			
			listViewerMain.setContentProvider(new IStructuredContentProvider(){
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
			
			listViewerMain.setLabelProvider(new LabelProvider(){
				@Override
				public String getText(Object element) {
					return ((DispositionItem)element).getLabel();
				}
			});
			listViewerMain.setInput(mdb.getBizStepList());
			
			listViewerMain.addSelectionChangedListener(new ISelectionChangedListener(){

				@Override
				public void selectionChanged(SelectionChangedEvent event) {
					propertyViewer.setInput(((IStructuredSelection)listViewerMain.getSelection()).getFirstElement());
				}});
			
		}

		
	}

	protected Object transformSelection(Object selected) {

		if (selected instanceof EditPart) {
			Object model = ((EditPart) selected).getModel();
			return model instanceof View ? ((View) model).getElement() : null;
		}
		if (selected instanceof View) {
			return ((View) selected).getElement();
		}
		if (selected instanceof IAdaptable) {
			View view = (View) ((IAdaptable) selected).getAdapter(View.class);
			if (view != null) {
				return view.getElement();
			}
		}
		return selected;
	}

	public void setInput(IWorkbenchPart part, ISelection selection) {
		if (selection.isEmpty()
				|| false == selection instanceof StructuredSelection) {
			super.setInput(part, selection);
			return;
		}
		final StructuredSelection structuredSelection = ((StructuredSelection) selection);
		ArrayList transformedSelection = new ArrayList(structuredSelection
				.size());
		for (Iterator it = structuredSelection.iterator(); it.hasNext();) {
			Object r = transformSelection(it.next());
			if (r != null) {
				transformedSelection.add(r);
			}
		}
		super.setInput(part, new StructuredSelection(transformedSelection));
		
		
		MasterDataBuilder mdb = MasterDataBuilder.getInstance();
		MainControl mc = MainControl.getMainControl();
		
		IStructuredSelection selection1 = (IStructuredSelection)getSelection();
		if(selection1.getFirstElement() instanceof org.ow2.aspirerfid.ide.bpwme.impl.OLCBProcImpl) {
			mdb.setOLCBProc(mc.getOLCBProc());
		}
		
	}

	
	private void setDirty() {
		IStructuredSelection selection = (IStructuredSelection)getSelection();
		MainControl mc = MainControl.getMainControl();
		if(selection.getFirstElement() instanceof org.ow2.aspirerfid.ide.bpwme.impl.OLCBProcImpl) {
			org.ow2.aspirerfid.ide.bpwme.impl.OLCBProcImpl olcbi = 
				(org.ow2.aspirerfid.ide.bpwme.impl.OLCBProcImpl)selection.getFirstElement();
			olcbi.setFake();
			mc.saveObject();
		}
	}

}
