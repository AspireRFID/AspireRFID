package org.ow2.aspirerfid.programmableengine.client;

import java.util.HashMap;

import org.ow2.aspirerfid.commons.apdl.model.CLCBProc;
import org.ow2.aspirerfid.commons.pe.exceptions.CLCBProcValidationException;
import org.ow2.aspirerfid.commons.pe.exceptions.NoSuchCLCBProcIdException;
import org.ow2.aspirerfid.commons.pe.exceptions.NotCompletedExecutionException;
import org.ow2.aspirerfid.commons.pe.interfaces.ProgrammEngineCLCBProcControlInterface;

public class PeCLCBProcControlClient implements ProgrammEngineCLCBProcControlInterface {

	@Override
	public CLCBProc getCLCBProc(String arg0, HashMap<String, String> arg1) throws NoSuchCLCBProcIdException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public HashMap<String, String> register(CLCBProc arg0) throws CLCBProcValidationException,
			NotCompletedExecutionException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public HashMap<String, String> start(CLCBProc arg0) throws NoSuchCLCBProcIdException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public HashMap<String, String> stop(CLCBProc arg0) throws NoSuchCLCBProcIdException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public HashMap<String, String> unregister(CLCBProc arg0) throws NoSuchCLCBProcIdException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public HashMap<String, String> update(CLCBProc arg0) throws CLCBProcValidationException,
			NotCompletedExecutionException {
		// TODO Auto-generated method stub
		return null;
	}

}
