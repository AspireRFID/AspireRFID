package org.ow2.aspirerfid.ide.bpwme.diagram.preferences;

import org.eclipse.jface.preference.FieldEditorPreferencePage;
import org.eclipse.jface.preference.PreferencePage;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;
import org.ow2.aspirerfid.ide.bpwme.diagram.part.BpwmeDiagramEditorPlugin;

public class ECDataPreferencePage extends FieldEditorPreferencePage implements
		IWorkbenchPreferencePage {

	public ECDataPreferencePage() {
		super(GRID);
		setPreferenceStore(BpwmeDiagramEditorPlugin.getInstance().getPreferenceStore());
	}
	
	@Override
	protected void createFieldEditors() {
		addField(
				new StringListEditor(PreferenceConstants.P_LLRP,"LLRP Attribute","Input Dialog","Input the attribute name",getFieldEditorParent()));
		addField(
				new StringListEditor(PreferenceConstants.P_RP,"RP Attribute","Input Dialog","Input the attribute name",getFieldEditorParent()));
		addField(
				new StringListEditor(PreferenceConstants.P_HAL,"HAL Attribute","Input Dialog","Input the attribute name",getFieldEditorParent()));		
	}

	@Override
	public void init(IWorkbench workbench) {		
	}


}
