/*
 * Copyright (C) 2008-2010, Aspire
 * 
 * Aspire is free software; you can redistribute it and/or modify it under the
 * terms of the GNU Lesser General Public License version 2.1 as published by
 * the Free Software Foundation (the "LGPL").
 * 
 * You should have received a copy of the GNU Lesser General Public License
 * along with this library in the file COPYING-LGPL-2.1; if not, write to the
 * Free Software Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA
 * 02110-1301 USA.
 * 
 * This software is distributed on an "AS IS" basis, WITHOUT WARRANTY OF ANY
 * KIND, either express or implied. See the GNU Lesser General Public License
 * for the specific language governing rights and limitations.
 */

package org.ow2.aspirerfid.programmableengine;

import java.util.HashMap;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

import org.ow2.aspirerfid.commons.apdl.model.OLCBProc;
import org.ow2.aspirerfid.programmableengine.exceptions.NoSuchOLCBProcIdException;
import org.ow2.aspirerfid.programmableengine.exceptions.NotCompletedExecutionException;
import org.ow2.aspirerfid.programmableengine.exceptions.OLCBProcValidationException;

/**
 * @author Nikos Kefalakis (nkef) e-mail: nkef@ait.edu.gr
 * 
 */
@WebService(name = "ProgramEngOLCBProcControlInterface", targetNamespace = "http://olcbproccontrol.programmableengine.aspirerfid.ow2.org/")
public interface ProgrammEngineOLCBProcControlInterface {

	@WebMethod()
	@WebResult(name = "registerStatus")
	public HashMap<String, String> register(@WebParam(name = "openLoopCBProc") OLCBProc openLoopCBProc)
			throws OLCBProcValidationException, NotCompletedExecutionException;

	@WebMethod()
	@WebResult(name = "unregisterStatus")
	public HashMap<String, String> unregister(@WebParam(name = "openLoopCBProc") OLCBProc openLoopCBProc)
			throws NoSuchOLCBProcIdException;

	@WebMethod()
	@WebResult(name = "updateStatus")
	public HashMap<String, String> update(@WebParam(name = "openLoopCBProc") OLCBProc openLoopCBProc)
			throws OLCBProcValidationException, NotCompletedExecutionException;

	@WebMethod()
	@WebResult(name = "startStatus")
	public HashMap<String, String> start(@WebParam(name = "openLoopCBProc") OLCBProc openLoopCBProc)
			throws NoSuchOLCBProcIdException;

	@WebMethod()
	@WebResult(name = "stopStatus")
	public HashMap<String, String> stop(@WebParam(name = "openLoopCBProc") OLCBProc openLoopCBProc)
			throws NoSuchOLCBProcIdException;

	@WebMethod()
	@WebResult(name = "OLCBProc")
	public OLCBProc getOLCBProc(@WebParam(name = "openLoopCBProcID") String openLoopCBProcID,
			@WebParam(name = "endPoints") HashMap<String, String> endPoints) throws NoSuchOLCBProcIdException;

}
