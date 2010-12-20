package org.ow2.aspirerfid.ide.bpwme.diagram.part;


import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.common.ui.URIEditorInput;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gef.palette.PaletteRoot;
import org.eclipse.gmf.runtime.diagram.core.preferences.PreferencesHint;
import org.eclipse.gmf.runtime.diagram.ui.actions.ActionIds;
import org.eclipse.gmf.runtime.diagram.ui.editparts.GraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.resources.editor.document.IDiagramDocument;
import org.eclipse.gmf.runtime.diagram.ui.resources.editor.document.IDocument;
import org.eclipse.gmf.runtime.diagram.ui.resources.editor.document.IDocumentProvider;
import org.eclipse.gmf.runtime.diagram.ui.resources.editor.parts.DiagramDocumentEditor;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IEditorReference;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import  org.eclipse.gmf.runtime.diagram.ui.resources.editor.parts.DiagramDocumentEditor;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.ow2.aspirerfid.commons.apdl.model.CLCBProc;
import org.ow2.aspirerfid.ide.MasterDataEditorGMF.bpwmeintegration.MasterDataContentsProvider;
import org.ow2.aspirerfid.ide.bpwme.actions.PropertyActionImportBusinessLocation;
import org.ow2.aspirerfid.ide.bpwme.diagram.edit.parts.CLCBProcEditPart;
import org.ow2.aspirerfid.ide.bpwme.diagram.edit.parts.EBProcEditPart;
import org.ow2.aspirerfid.ide.bpwme.diagram.preferences.PreferenceConstants;
import org.ow2.aspirerfid.ide.bpwme.ecspec.views.ECSpecEditor;
import org.ow2.aspirerfid.ide.bpwme.impl.CLCBProcImpl;
import org.ow2.aspirerfid.ide.bpwme.master.utils.MasterDataBuilder;
import org.ow2.aspirerfid.ide.bpwme.utils.EditorListener;
import org.ow2.aspirerfid.ide.bpwme.utils.MainControl;
import org.ow2.aspirerfid.ide.bpwme.utils.MainUtil;

/**
 * @generated
 */
public class BpwmeDiagramEditor extends DiagramDocumentEditor {

	private ArrayList<ISelectionChangedListener> selectionListeners;
	
	/**
	 * @generated
	 */
	public static final String ID = "org.ow2.aspirerfid.ide.bpwme.diagram.part.BpwmeDiagramEditorID"; //$NON-NLS-1$

	/**
	 * @generated
	 */
	public static final String CONTEXT_ID = "bpwme.diagram.ui.diagramContext"; //$NON-NLS-1$

	
	//private static boolean dirty = false;
	/**
	 * @generated
	 */
	public BpwmeDiagramEditor() {
		super(true);
		selectionListeners = new ArrayList<ISelectionChangedListener>();
	}

	/**
	 * @generated
	 */
	protected String getContextID() {
		return CONTEXT_ID;
	}

	/**
	 * @generated
	 */
	protected PaletteRoot createPaletteRoot(PaletteRoot existingPaletteRoot) {
		PaletteRoot root = super.createPaletteRoot(existingPaletteRoot);
		new BpwmePaletteFactory().fillPalette(root);
		return root;
	}

	/**
	 * @generated
	 */
	protected PreferencesHint getPreferencesHint() {
		return BpwmeDiagramEditorPlugin.DIAGRAM_PREFERENCES_HINT;
	}

	/**
	 * @generated
	 */
	public String getContributorId() {
		return BpwmeDiagramEditorPlugin.ID;
	}

	/**
	 * @generated
	 */
	protected IDocumentProvider getDocumentProvider(IEditorInput input) {
		if (input instanceof URIEditorInput) {
			return BpwmeDiagramEditorPlugin.getInstance().getDocumentProvider();
		}
		return super.getDocumentProvider(input);
	}

	/**
	 * @generated
	 */
	public TransactionalEditingDomain getEditingDomain() {
		IDocument document = getEditorInput() != null ? getDocumentProvider()
				.getDocument(getEditorInput()) : null;
		if (document instanceof IDiagramDocument) {
			return ((IDiagramDocument) document).getEditingDomain();
		}
		return super.getEditingDomain();
	}

	/**
	 * @generated
	 */
	protected void setDocumentProvider(IEditorInput input) {
		if (input instanceof URIEditorInput) {
			setDocumentProvider(BpwmeDiagramEditorPlugin.getInstance()
					.getDocumentProvider());
		} else {
			super.setDocumentProvider(input);
		}
	}

	/**
	 * @generated
	 */
	protected void configureGraphicalViewer() {
		super.configureGraphicalViewer();
		DiagramEditorContextMenuProvider provider = new DiagramEditorContextMenuProvider(
				this, getDiagramGraphicalViewer());
		getDiagramGraphicalViewer().setContextMenu(provider);
		getSite().registerContextMenu(ActionIds.DIAGRAM_EDITOR_CONTEXT_MENU,
				provider, getDiagramGraphicalViewer());
	}
	
	/*
	 * get an editor instance from the workbench
	 */
	public static BpwmeDiagramEditor getInstance() {
		IWorkbenchPage[] pages = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getPages();
		for(int i = 0; i < pages.length; i++) {
			IEditorReference[] ier = pages[i].getEditorReferences();
			for(int j = 0; j < ier.length; j++) {
				if(ier[j].getId().equals(ID)) {
					return (BpwmeDiagramEditor) ier[j].getEditor(false);
				}
			}
		}
		return null;
	}
	
	public void addSelectionListener(ISelectionChangedListener listener) {
		getEditorSite().getSelectionProvider().addSelectionChangedListener(listener);
		selectionListeners.add(listener);
	}
	
	
	@Override
	public void dispose() {
		for (ISelectionChangedListener listener:selectionListeners) {
			getEditorSite().getSelectionProvider().removeSelectionChangedListener(listener);
		}
		selectionListeners.clear();
		super.dispose();
	}
	
	@Override
	public void init(IEditorSite site, IEditorInput input)
			throws PartInitException {
		super.init(site, input);
		EditorListener listener = EditorListener.getInstance();		
		getSite().getPage().addPartListener(listener);
	}
	
	@Override
	public void doSave(IProgressMonitor progressMonitor) {
		super.doSave(progressMonitor);
	}
	
	
	/**
	 * Whenever change focus back to bpwme editor,
	 * try to import the data from business location editor
	 * to the selected clcb object
	 * TODO reload all clcb data from the diagrams.
	 */
//	@Override
//	public void setFocus() {
//		ISelection selection = getSite().getSelectionProvider().getSelection();
//
//		GraphicalEditPart selectedEditPart = null;
//		if (selection instanceof IStructuredSelection) {
//			IStructuredSelection structuredSelection = (IStructuredSelection) selection;
//			if (structuredSelection.size() != 1) {
//				return;
//			}
//			selectedEditPart = (GraphicalEditPart) structuredSelection.getFirstElement();
//		}
//		
//		CLCBProcEditPart clcbPart = null;
//		String clcbName;
//		
//		if(selectedEditPart instanceof CLCBProcEditPart) {
//			clcbPart = ((CLCBProcEditPart)selectedEditPart);
//			//if it is ebproc, get the parent	
//		}else if(selectedEditPart instanceof EBProcEditPart){
//			clcbPart = (CLCBProcEditPart)((EBProcEditPart)selectedEditPart).getParent().getParent();
//		}else {
//			return;
//		}
//		
//		if(clcbPart == null) {
//			System.out.println("CLCBProcPart is null, no selection found");
//			return;
//		}
//		
//		MainControl mc = MainControl.getMainControl();
//		
//		CLCBProcImpl clcbi = (CLCBProcImpl)((View)clcbPart.getModel()).getElement();
//		CLCBProc clcb = (CLCBProc) mc.getMapObject(clcbi.hashCode());
//
//		clcbName = clcb.getName();
//
//		URI uri = mc.getApdlURI();
//		
//		String projectName = MainUtil.getProjectName(uri.toFileString());
//
//		IPreferenceStore store = BpwmeDiagramEditorPlugin.getInstance().getPreferenceStore();
//		String dir = store.getString(PreferenceConstants.P_BPWME_DIR);
//				
//		String newDir = dir +  File.separator + 
//			projectName + File.separator + 
//			clcbName + File.separator;
//		//check if the corresponding file exists
//		
//		
//		String lFileName = PropertyActionImportBusinessLocation.getLocationFile(newDir);
//		//if it is, open the file
//		if(lFileName != null) {
//			MasterDataBuilder mdb = MasterDataBuilder.getInstance();			
//			mdb.setCLCBProc(clcb);
//			
//			MasterDataContentsProvider mcp = new MasterDataContentsProvider();
//			mcp.setCompany(lFileName);
//						
//			HashMap<String, HashMap<String, String>> companyMap = 
//				mcp.getCompanyModelUriAttributesValues();
//			HashMap<String, HashMap<String, String>> warehouseMap = 
//				mcp.getWarehouseModelUriAttributesValuesBpwme();
//			HashMap<String, HashMap<String, String>> readpointMap = 
//				mcp.getReadPointModelUriAttributesValuesBpwme();			
//			
//			mdb.setBusinessStepReadPoint(companyMap, warehouseMap, readpointMap);
//			
//		}else {//else create a new one			
//			MainUtil.executeCommand("org.ow2.aspirerfid.ide.MasterDataEditorGMF.newMasterDataEditorGMF.command");
//		}
//		super.setFocus();
//	}
	
	
}
