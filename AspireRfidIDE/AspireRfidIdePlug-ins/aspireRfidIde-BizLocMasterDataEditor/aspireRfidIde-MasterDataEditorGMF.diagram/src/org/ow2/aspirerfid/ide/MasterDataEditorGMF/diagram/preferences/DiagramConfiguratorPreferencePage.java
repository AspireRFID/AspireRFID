package org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.preferences;

import org.eclipse.emf.common.ui.URIEditorInput;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.preference.*;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IEditorReference;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPreferencePage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.part.MasterDataEditorGMFDiagramEditor;
import org.ow2.aspirerfid.ide.MasterDataEditorGMF.querycapture.MasterDataEditParts;

/**
 * @author Eleftherios Karageorgiou (elka) e-mail: elka@ait.edu.gr
 *
 */
public class DiagramConfiguratorPreferencePage extends FieldEditorPreferencePage implements
		IWorkbenchPreferencePage {

	public DiagramConfiguratorPreferencePage() {
		super(GRID);
		setPreferenceStore(org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.part.MasterDataEditorGMFDiagramEditorPlugin
				.getInstance().getPreferenceStore());
		setDescription("Master Data Editor GMF Configurator Preferences");
	}
	
	@Override
	protected void createFieldEditors() {
		{
			addField(new StringFieldEditor(DiagramConfiguratorPreferenceConstants.P_CaptureURL, "EPCIS Repository Capture URL:", getFieldEditorParent()));
		}
		
		{
			addField(new StringFieldEditor(DiagramConfiguratorPreferenceConstants.P_QueryURL, "EPCIS Repository Query URL:", getFieldEditorParent()));
		}
		
		{
			final StringListEditor listOfNewCompanyAttributes = new StringListEditor(DiagramConfiguratorPreferenceConstants.newCompanyAttributes, "New Company Attributes:",
					"Company Attribute Dialog", "Insert Company Attribute:", getFieldEditorParent());
			addField(listOfNewCompanyAttributes);
		}
		
		{
			final StringListEditor listOfNewWarehouseAttributes = new StringListEditor(DiagramConfiguratorPreferenceConstants.newWarehouseAttributes, "New Warehouse Attributes:",
					"Warehouse Attribute Dialog", "Insert Warehouse Attribute:", getFieldEditorParent());
			addField(listOfNewWarehouseAttributes);
		}
		
		{
			final StringListEditor listOfNewReadPointAttributes = new StringListEditor(DiagramConfiguratorPreferenceConstants.newReadPointAttributes, "New ReadPoint Attributes:",
					"ReadPoint Attribute Dialog", "Insert ReadPoint Attribute:", getFieldEditorParent());
			addField(listOfNewReadPointAttributes);
		}
		
		

	}

	@Override
	public void init(IWorkbench workbench) {
		// TODO Auto-generated method stub
	}
	
	/**
	 * Default store values
	 */
	public static void initDefaults(IPreferenceStore preferenceStore) {
		preferenceStore.setDefault(DiagramConfiguratorPreferenceConstants.P_CaptureURL,"http://localhost:8080/aspireRfidEpcisRepository/capture");
		preferenceStore.setDefault(DiagramConfiguratorPreferenceConstants.P_QueryURL,"http://localhost:8080/aspireRfidEpcisRepository/query");
		
		MasterDataEditParts.setNewCompanyAttr(new String[splitString(preferenceStore.getString(DiagramConfiguratorPreferenceConstants.newCompanyAttributes)).length]);
		MasterDataEditParts.setNewCompanyAttr(splitString(preferenceStore.getString(DiagramConfiguratorPreferenceConstants.newCompanyAttributes)));
		
		MasterDataEditParts.setNewWarehouseAttr(new String[splitString(preferenceStore.getString(DiagramConfiguratorPreferenceConstants.newWarehouseAttributes)).length]);
		MasterDataEditParts.setNewWarehouseAttr(splitString(preferenceStore.getString(DiagramConfiguratorPreferenceConstants.newWarehouseAttributes)));
	
		MasterDataEditParts.setNewReadPointAttr(new String[splitString(preferenceStore.getString(DiagramConfiguratorPreferenceConstants.newReadPointAttributes)).length]);
		MasterDataEditParts.setNewReadPointAttr(splitString(preferenceStore.getString(DiagramConfiguratorPreferenceConstants.newReadPointAttributes)));
	}
	
	/**
	 * Split a String
	 */
	private static String[] splitString(String s) {
		String[] temp = null;
		temp = s.split(",");

		return temp;
	}
	
	@Override
	public boolean performOk() {
		MessageDialog.openWarning(getShell(), "Warning", 
				"The application needs to be restarted in order to update any new custom property.");
//		IWorkbenchWindow window = PlatformUI.getWorkbench().getActiveWorkbenchWindow();
//		IWorkbenchPage page = window.getActivePage();
//		IEditorReference[] refs = page.getEditorReferences();
//		IEditorPart[] editors = page.getEditors();
//		page.get
//		
//		try {
//			page.closeAllEditors(true);
//			page.op
//		} catch (PartInitException e) {
//			e.printStackTrace();
//		}
		
		
		return super.performOk();
	}

}
