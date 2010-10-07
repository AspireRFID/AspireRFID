package org.ow2.aspirerfid.ide.bpwme.diagram.preferences;

import java.util.Iterator;

import org.eclipse.jface.preference.FieldEditorPreferencePage;
import org.eclipse.jface.preference.PreferencePage;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;
import org.ow2.aspirerfid.ide.bpwme.diagram.part.BpwmeDiagramEditorPlugin;

public class ExtendedDataPreferencePage extends FieldEditorPreferencePage implements
		IWorkbenchPreferencePage {

	public ExtendedDataPreferencePage() {
		super(GRID);
		setPreferenceStore(BpwmeDiagramEditorPlugin.getInstance().getPreferenceStore());
	}
	
	@Override
	protected void createFieldEditors() {

		addField(new TwoListEditor(PreferenceConstants.P_EXTEND.keySet(),getFieldEditorParent()));
//		Iterator<String> iter = PreferenceConstants.P_EXTEND.keySet().iterator();
//		while(iter.hasNext()) {
//			String key = iter.next();
//			addField(
//					new StringListEditor(key,key,"Input Dialog","Input the attribute value",getFieldEditorParent())
//			);
//		}
	}

	@Override
	public void init(IWorkbench workbench) {		
	}
}

