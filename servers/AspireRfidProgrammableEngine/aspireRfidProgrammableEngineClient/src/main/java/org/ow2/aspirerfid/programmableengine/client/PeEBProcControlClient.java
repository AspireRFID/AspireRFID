package org.ow2.aspirerfid.programmableengine.client;

import java.util.HashMap;

import org.ow2.aspirerfid.commons.apdl.model.EBProc;
import org.ow2.aspirerfid.commons.pe.exceptions.EBProcValidationException;
import org.ow2.aspirerfid.commons.pe.exceptions.NoSuchEBProcIdException;
import org.ow2.aspirerfid.commons.pe.exceptions.NotCompletedExecutionException;
import org.ow2.aspirerfid.commons.pe.interfaces.ProgrammEngineEBProcControlInterface;

public class PeEBProcControlClient implements ProgrammEngineEBProcControlInterface {

	@Override
	public EBProc getEBProc(String arg0, HashMap<String, String> arg1) throws NoSuchEBProcIdException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public HashMap<String, String> register(EBProc arg0) throws EBProcValidationException,
			NotCompletedExecutionException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public HashMap<String, String> start(EBProc arg0) throws NoSuchEBProcIdException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public HashMap<String, String> stop(EBProc arg0) throws NoSuchEBProcIdException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public HashMap<String, String> unregister(EBProc arg0) throws NoSuchEBProcIdException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public HashMap<String, String> update(EBProc arg0) throws EBProcValidationException, NotCompletedExecutionException {
		// TODO Auto-generated method stub
		return null;
	}

}
