package org.ow2.aspirerfid.programmableengine.olcbproccontrol;

import java.util.HashMap;

import org.ow2.aspirerfid.commons.apdl.model.OLCBProc;
import org.ow2.aspirerfid.commons.pe.exceptions.NoSuchOLCBProcIdException;

public class OLCBProcControlGetOLCBProc {

	OLCBProc retievedOLCBProc;

	public OLCBProcControlGetOLCBProc (String openLoopCBProcID,
			HashMap<String, String> endPoints) throws NoSuchOLCBProcIdException {
					
	}
	
	
	public OLCBProc getRetievedOLCBProc() {
		return retievedOLCBProc;
	}

	
}
