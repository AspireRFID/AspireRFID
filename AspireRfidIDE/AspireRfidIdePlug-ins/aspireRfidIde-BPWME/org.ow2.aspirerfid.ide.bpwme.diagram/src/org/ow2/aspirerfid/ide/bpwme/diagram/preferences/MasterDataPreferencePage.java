package org.ow2.aspirerfid.ide.bpwme.diagram.preferences;

import org.eclipse.jface.preference.FieldEditorPreferencePage;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;
import org.ow2.aspirerfid.ide.bpwme.diagram.part.BpwmeDiagramEditorPlugin;

public class MasterDataPreferencePage extends FieldEditorPreferencePage implements
		IWorkbenchPreferencePage {

	public MasterDataPreferencePage() {
		super(GRID);
		setPreferenceStore(BpwmeDiagramEditorPlugin.getInstance().getPreferenceStore());
	}
	
	@Override
	protected void createFieldEditors() {
		addField(
				new StringListEditor(PreferenceConstants.P_BS,"Business Step Attribute","Input Dialog","Input the attribute name",getFieldEditorParent()));
		addField(
				new StringListEditor(PreferenceConstants.P_DI,"Disposition Attribute","Input Dialog","Input the attribute name",getFieldEditorParent()));
		addField(
				new StringListEditor(PreferenceConstants.P_BT,"Business Transaction Type Attribute","Input Dialog","Input the attribute name",getFieldEditorParent()));		
	}

	@Override
	public void init(IWorkbench workbench) {
		// TODO Auto-generated method stub
		
	}


}
