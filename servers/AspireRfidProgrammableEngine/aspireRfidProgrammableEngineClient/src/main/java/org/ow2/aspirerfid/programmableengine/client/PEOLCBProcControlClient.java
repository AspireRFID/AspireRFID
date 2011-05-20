package org.ow2.aspirerfid.programmableengine.client;

import java.util.HashMap;

import org.ow2.aspirerfid.commons.apdl.model.OLCBProc;
import org.ow2.aspirerfid.commons.pe.exceptions.NoSuchOLCBProcIdException;
import org.ow2.aspirerfid.commons.pe.exceptions.NotCompletedExecutionException;
import org.ow2.aspirerfid.commons.pe.exceptions.OLCBProcValidationException;
import org.ow2.aspirerfid.commons.pe.interfaces.ProgrammEngineOLCBProcControlInterface;

public class PEOLCBProcControlClient implements ProgrammEngineOLCBProcControlInterface {

	@Override
	public OLCBProc getOLCBProc(String arg0, HashMap<String, String> arg1) throws NoSuchOLCBProcIdException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public HashMap<String, String> register(OLCBProc arg0) throws OLCBProcValidationException,
			NotCompletedExecutionException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public HashMap<String, String> start(OLCBProc arg0) throws NoSuchOLCBProcIdException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public HashMap<String, String> stop(OLCBProc arg0) throws NoSuchOLCBProcIdException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public HashMap<String, String> unregister(OLCBProc arg0) throws NoSuchOLCBProcIdException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public HashMap<String, String> update(OLCBProc arg0) throws OLCBProcValidationException,
			NotCompletedExecutionException {
		// TODO Auto-generated method stub
		return null;
	}

}
