package org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.preferences;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.preference.*;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;
import org.eclipse.ui.PlatformUI;
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
			addField(new BooleanFieldEditor(DiagramConfiguratorPreferenceConstants.P_Size, "Set size of elements on \"MasterDataEditorGMF Square Layout\" action", getFieldEditorParent()));
		}
		
		{
			addField(new IntegerFieldEditor(DiagramConfiguratorPreferenceConstants.P_Size_Height, "Set height of elements:", getFieldEditorParent()));
		}
		
		{
			addField(new IntegerFieldEditor(DiagramConfiguratorPreferenceConstants.P_Size_Width, "Set width of elements:", getFieldEditorParent()));
		}
		
		{
			final CompanyStringListEditor listOfNewCompanyAttributes = new CompanyStringListEditor(DiagramConfiguratorPreferenceConstants.newCompanyAttributes, "New Company Attributes:",
					"Company Attribute Dialog", "Insert Company Attribute:", getFieldEditorParent());
			addField(listOfNewCompanyAttributes);
		}
		
		{
			final WarehouseStringListEditor listOfNewWarehouseAttributes = new WarehouseStringListEditor(DiagramConfiguratorPreferenceConstants.newWarehouseAttributes, "New Warehouse Attributes:",
					"Warehouse Attribute Dialog", "Insert Warehouse Attribute:", getFieldEditorParent());
			addField(listOfNewWarehouseAttributes);
		}
		
		{
			final ReadPointStringListEditor listOfNewReadPointAttributes = new ReadPointStringListEditor(DiagramConfiguratorPreferenceConstants.newReadPointAttributes, "New ReadPoint Attributes:",
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
		preferenceStore.setDefault(DiagramConfiguratorPreferenceConstants.P_Size_Height, "100");
		preferenceStore.setDefault(DiagramConfiguratorPreferenceConstants.P_Size_Width, "100");
		
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
		boolean result = MessageDialog.openConfirm(getShell(), "Warning", 
			"The application needs to be restarted in order to update any new custom property.");

		super.performOk();
		
		if (result)
			PlatformUI.getWorkbench().restart();
		
		return true;
	}

}
