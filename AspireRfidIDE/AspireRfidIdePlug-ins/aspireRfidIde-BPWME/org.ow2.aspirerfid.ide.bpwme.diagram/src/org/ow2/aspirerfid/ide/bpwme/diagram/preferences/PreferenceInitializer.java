package org.ow2.aspirerfid.ide.bpwme.diagram.preferences;

import org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer;
import org.eclipse.jface.preference.IPreferenceStore;

import org.ow2.aspirerfid.ide.bpwme.diagram.part.BpwmeDiagramEditorPlugin;

/**
 * Class used to initialize default preference values.
 */
public class PreferenceInitializer extends AbstractPreferenceInitializer {

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer#initializeDefaultPreferences()
	 */
	public void initializeDefaultPreferences() {
		IPreferenceStore store = BpwmeDiagramEditorPlugin.getInstance().getPreferenceStore();
//		store.setDefault(PreferenceConstants.P_BOOLEAN, true);
//		store.setDefault(PreferenceConstants.P_CHOICE, "choice2");
//		store.setDefault(PreferenceConstants.P_STRING,
//				"Default value");
		//EC Spec data initialize
		store.setDefault(PreferenceConstants.P_LLRP, "ConnectionPointAddress,ConnectionPointPort,PhysicalReaderSource,RoSpecID");
		store.setDefault(PreferenceConstants.P_RP, "ConnectionPointAddress,ConnectionPointPort,PhysicalReaderSource,RoSpecID");
		store.setDefault(PreferenceConstants.P_HAL, "ConnectionPointAddress,ConnectionPointPort,PhysicalReaderSource,RoSpecID");
		//Master data initialize
		store.setDefault(PreferenceConstants.P_BS, "Attr1,Attr2,Attr3");
		store.setDefault(PreferenceConstants.P_DI, "Attr1,Attr2,Attr3");
		store.setDefault(PreferenceConstants.P_BT, "Attr1,Attr2,Attr3");
	}
}
