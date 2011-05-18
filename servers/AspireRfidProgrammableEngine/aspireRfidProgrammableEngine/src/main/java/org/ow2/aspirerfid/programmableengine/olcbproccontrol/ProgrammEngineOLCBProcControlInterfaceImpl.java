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
		OLCBProcControlGetOLCBProc olcbProcControlGetOLCBProc = new OLCBProcControlGetOLCBProc(openLoopCBProcID, endPoints);
		return olcbProcControlGetOLCBProc.getRetievedOLCBProc();
	}

	@Override
	public HashMap<String, String> register(OLCBProc openLoopCBProc)
			throws OLCBProcValidationException, NotCompletedExecutionException {
		OLCBProcControlRegister olcbProcControlRegister = new OLCBProcControlRegister(openLoopCBProc);
		return olcbProcControlRegister.getOLCBProcControlRegisterStepsFeedback();
	}

	@Override
	public HashMap<String, String> start(OLCBProc openLoopCBProc)
			throws NoSuchOLCBProcIdException {

		OLCBProcControlStart olcbProcControlStart = new OLCBProcControlStart(openLoopCBProc);
				
		return olcbProcControlStart.getOlcbProcControlStartStepsFeedback();
	}

	@Override
	public HashMap<String, String> stop(OLCBProc openLoopCBProc)
			throws NoSuchOLCBProcIdException {
		
		OLCBProcControlStop olcbProcControlStop = new OLCBProcControlStop(openLoopCBProc);
		
		return olcbProcControlStop.getOlcbProcControlStopStepsFeedback();
	}

	@Override
	public HashMap<String, String> unregister(OLCBProc openLoopCBProc)
			throws NoSuchOLCBProcIdException {
		OLCBProcControlUnregister olcbBProcControlUnregister = new OLCBProcControlUnregister(openLoopCBProc);
		
		return olcbBProcControlUnregister.getOlcbProcControlUnregisterStepsFeedback();
	}

	@Override
	public HashMap<String, String> update(OLCBProc openLoopCBProc)
			throws OLCBProcValidationException, NotCompletedExecutionException {
		OLCBProcControlUpdate olcbProcControlUpdate = new OLCBProcControlUpdate(openLoopCBProc);
		
		return olcbProcControlUpdate.getOlcbProcControlUpdateStepsFeedback();
	}

}
