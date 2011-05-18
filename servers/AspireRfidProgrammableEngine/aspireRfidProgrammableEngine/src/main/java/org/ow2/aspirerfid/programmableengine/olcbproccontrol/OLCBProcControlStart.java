package org.ow2.aspirerfid.programmableengine.olcbproccontrol;

import java.util.HashMap;

import org.ow2.aspirerfid.commons.apdl.model.OLCBProc;
import org.ow2.aspirerfid.commons.pe.exceptions.NoSuchOLCBProcIdException;

public class OLCBProcControlStart {

	HashMap<String, String> olcbProcControlStartStepsFeedback;
	
	
	
	public OLCBProcControlStart(OLCBProc openLoopCBProc)
	throws NoSuchOLCBProcIdException {
		
	}



	public HashMap<String, String> getOlcbProcControlStartStepsFeedback() {
		return olcbProcControlStartStepsFeedback;
	}
	
	
	
	
	
	

	
}
