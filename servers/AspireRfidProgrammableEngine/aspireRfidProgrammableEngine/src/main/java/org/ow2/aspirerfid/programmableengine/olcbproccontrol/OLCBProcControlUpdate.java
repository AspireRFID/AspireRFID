package org.ow2.aspirerfid.programmableengine.olcbproccontrol;

import java.util.HashMap;

import org.ow2.aspirerfid.commons.apdl.model.OLCBProc;
import org.ow2.aspirerfid.commons.pe.exceptions.NotCompletedExecutionException;
import org.ow2.aspirerfid.commons.pe.exceptions.OLCBProcValidationException;

public class OLCBProcControlUpdate {


	HashMap<String, String> olcbProcControlUpdateStepsFeedback;
	
	
	
	
	public OLCBProcControlUpdate(OLCBProc openLoopCBProc)
	throws OLCBProcValidationException, NotCompletedExecutionException {
		
	}



	public HashMap<String, String> getOlcbProcControlUpdateStepsFeedback() {
		return olcbProcControlUpdateStepsFeedback;
	}

	
}
