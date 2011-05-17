package org.ow2.aspirerfid.programmableengine.clcbproccontrol;

import java.util.HashMap;

import org.ow2.aspirerfid.commons.apdl.model.CLCBProc;
import org.ow2.aspirerfid.commons.pe.interfaces.ProgrammEngineCLCBProcControlInterface;
import org.ow2.aspirerfid.commons.pe.exceptions.CLCBProcValidationException;
import org.ow2.aspirerfid.commons.pe.exceptions.NoSuchCLCBProcIdException;
import org.ow2.aspirerfid.commons.pe.exceptions.NotCompletedExecutionException;

public class ProgrammEngineCLCBProcControlInterfaceImpl implements
		ProgrammEngineCLCBProcControlInterface {

	@Override
	public CLCBProc getCLCBProc(String closeLoopCBProcID,
			HashMap<String, String> endPoints) throws NoSuchCLCBProcIdException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public HashMap<String, String> register(CLCBProc closeLoopCBProc)
			throws CLCBProcValidationException, NotCompletedExecutionException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public HashMap<String, String> start(CLCBProc closeLoopCBProc)
			throws NoSuchCLCBProcIdException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public HashMap<String, String> stop(CLCBProc closeLoopCBProc)
			throws NoSuchCLCBProcIdException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public HashMap<String, String> unregister(CLCBProc closeLoopCBProc)
			throws NoSuchCLCBProcIdException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public HashMap<String, String> update(CLCBProc closeLoopCBProc)
			throws CLCBProcValidationException, NotCompletedExecutionException {
		// TODO Auto-generated method stub
		return null;
	}

}
