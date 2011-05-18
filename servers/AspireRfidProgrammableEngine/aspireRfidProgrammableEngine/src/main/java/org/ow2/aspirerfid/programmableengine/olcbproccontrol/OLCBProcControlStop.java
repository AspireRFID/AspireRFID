package org.ow2.aspirerfid.programmableengine.olcbproccontrol;

import java.util.HashMap;

import org.ow2.aspirerfid.commons.apdl.model.OLCBProc;
import org.ow2.aspirerfid.commons.pe.exceptions.NoSuchOLCBProcIdException;

public class OLCBProcControlStop {
	
	
	
	
HashMap<String, String> olcbProcControlStopStepsFeedback;
	
	
	
	public OLCBProcControlStop(OLCBProc openLoopCBProc)
	throws NoSuchOLCBProcIdException {
		
	}



	public HashMap<String, String> getOlcbProcControlStopStepsFeedback() {
		return olcbProcControlStopStepsFeedback;
	}
	
	
	

}
