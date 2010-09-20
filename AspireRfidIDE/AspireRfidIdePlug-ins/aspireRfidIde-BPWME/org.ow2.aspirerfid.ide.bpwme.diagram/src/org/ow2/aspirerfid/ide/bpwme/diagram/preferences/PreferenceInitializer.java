package org.ow2.aspirerfid.ide.bpwme.diagram.preferences;

import java.io.File;
import java.util.HashMap;
import java.util.Iterator;

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
		
		//file paths
		store.setDefault(PreferenceConstants.P_BPWME_DIR, System.getProperty("user.home") + 
				File.separator + "AspireRFID" +
				File.separator + "IDE" +				
				File.separator + "BPWME" + File.separator);
		
		store.setDefault(PreferenceConstants.P_APDL_DIR, System.getProperty("user.home") + 
				File.separator + "AspireRFID" +
				File.separator + "IDE" +				
				File.separator + "APDLs" + File.separator);
		
		//Extended Attribute
		PreferenceConstants.P_EXTEND.put("ECSpecSubscriptionURI","http://localhost:9999");
		PreferenceConstants.P_EXTEND.put("AleClientEndPoint","http://localhost:8080/aspireRfidALE/services/ALEService");
		PreferenceConstants.P_EXTEND.put("AleLrClientEndPoint","http://localhost:8080/aspireRfidALE/services/ALELRService");
		PreferenceConstants.P_EXTEND.put("EpcisClientCaptureEndPoint","http://localhost:8080/aspireRfidEpcisRepository/capture");
		PreferenceConstants.P_EXTEND.put("EpcisClientQueryEndPoint","http://localhost:8080/aspireRfidEpcisRepository/query");
		PreferenceConstants.P_EXTEND.put("BegEngineEndpoint","http://localhost:8080/aspireRfidBEG/begengine");

		
		Iterator<String> iter = PreferenceConstants.P_EXTEND.keySet().iterator();
		
		while(iter.hasNext()) {
			String key = iter.next();
			String value = PreferenceConstants.P_EXTEND.get(key);
			store.setDefault(key,value);
		}

	}
}
