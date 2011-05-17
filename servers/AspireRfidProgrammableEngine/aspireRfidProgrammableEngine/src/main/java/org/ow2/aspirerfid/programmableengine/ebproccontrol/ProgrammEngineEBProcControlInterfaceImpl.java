package org.ow2.aspirerfid.programmableengine.ebproccontrol;

import java.util.HashMap;

import org.ow2.aspirerfid.commons.apdl.model.EBProc;
import org.ow2.aspirerfid.commons.pe.interfaces.ProgrammEngineEBProcControlInterface;
import org.ow2.aspirerfid.commons.pe.exceptions.EBProcValidationException;
import org.ow2.aspirerfid.commons.pe.exceptions.NoSuchEBProcIdException;
import org.ow2.aspirerfid.commons.pe.exceptions.NotCompletedExecutionException;

public class ProgrammEngineEBProcControlInterfaceImpl implements
		ProgrammEngineEBProcControlInterface {

	@Override
	public EBProc getEBProc(String elementaryBProcID,
			HashMap<String, String> endPoints) throws NoSuchEBProcIdException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public HashMap<String, String> register(EBProc elementaryBProc)
			throws EBProcValidationException, NotCompletedExecutionException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public HashMap<String, String> start(EBProc elementaryBProc)
			throws NoSuchEBProcIdException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public HashMap<String, String> stop(EBProc elementaryBProcD)
			throws NoSuchEBProcIdException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public HashMap<String, String> unregister(EBProc elementaryBProc)
			throws NoSuchEBProcIdException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public HashMap<String, String> update(EBProc elementaryBProc)
			throws EBProcValidationException, NotCompletedExecutionException {
		// TODO Auto-generated method stub
		return null;
	}

}
