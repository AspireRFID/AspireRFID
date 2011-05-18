package org.ow2.aspirerfid.programmableengine.olcbproccontrol;

import java.util.HashMap;

import org.ow2.aspirerfid.commons.apdl.model.OLCBProc;
import org.ow2.aspirerfid.commons.pe.exceptions.NoSuchOLCBProcIdException;

public class OLCBProcControlUnregister {

	HashMap<String, String> olcbProcControlUnregisterStepsFeedback;
	
	
	
	
	public OLCBProcControlUnregister(OLCBProc openLoopCBProc)
	throws NoSuchOLCBProcIdException {
		
	}



	public HashMap<String, String> getOlcbProcControlUnregisterStepsFeedback() {
		return olcbProcControlUnregisterStepsFeedback;
	}

	
	
}
