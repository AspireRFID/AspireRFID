package org.ow2.aspirerfid.programmableengine.olcbproccontrol;

import java.util.HashMap;

import javax.jws.WebService;

import org.ow2.aspirerfid.commons.apdl.model.OLCBProc;
import org.ow2.aspirerfid.commons.pe.interfaces.ProgrammEngineOLCBProcControlInterface;
import org.ow2.aspirerfid.commons.pe.exceptions.NoSuchOLCBProcIdException;
import org.ow2.aspirerfid.commons.pe.exceptions.NotCompletedExecutionException;
import org.ow2.aspirerfid.commons.pe.exceptions.OLCBProcValidationException;

@WebService(endpointInterface="org.ow2.aspirerfid.commons.pe.interfaces.ProgrammEngineOLCBProcControlInterface")
public class ProgrammEngineOLCBProcControlInterfaceImpl implements
		ProgrammEngineOLCBProcControlInterface {

	@Override
	public OLCBProc getOLCBProc(String openLoopCBProcID,
			HashMap<String, String> endPoints) throws NoSuchOLCBProcIdException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public HashMap<String, String> register(OLCBProc openLoopCBProc)
			throws OLCBProcValidationException, NotCompletedExecutionException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public HashMap<String, String> start(OLCBProc openLoopCBProc)
			throws NoSuchOLCBProcIdException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public HashMap<String, String> stop(OLCBProc openLoopCBProc)
			throws NoSuchOLCBProcIdException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public HashMap<String, String> unregister(OLCBProc openLoopCBProc)
			throws NoSuchOLCBProcIdException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public HashMap<String, String> update(OLCBProc openLoopCBProc)
			throws OLCBProcValidationException, NotCompletedExecutionException {
		// TODO Auto-generated method stub
		return null;
	}

}
