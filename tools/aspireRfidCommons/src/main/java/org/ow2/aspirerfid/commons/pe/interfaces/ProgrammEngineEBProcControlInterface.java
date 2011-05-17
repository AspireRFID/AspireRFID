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

package org.ow2.aspirerfid.commons.pe.interfaces;

import java.util.HashMap;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

import org.ow2.aspirerfid.commons.apdl.model.EBProc;
import org.ow2.aspirerfid.commons.pe.exceptions.EBProcValidationException;
import org.ow2.aspirerfid.commons.pe.exceptions.NoSuchEBProcIdException;
import org.ow2.aspirerfid.commons.pe.exceptions.NotCompletedExecutionException;

/**
 * @author Nikos Kefalakis (nkef) e-mail: nkef@ait.edu.gr
 * 
 */
@WebService(name = "ProgrammEngineEBProcControlInterface", targetNamespace = "http://ebproccontrol.programmableengine.aspirerfid.ow2.org/")
public interface ProgrammEngineEBProcControlInterface {

	@WebMethod()
	@WebResult(name = "registerStatus")
	public HashMap<String, String> register(@WebParam(name = "elementaryBProc") EBProc elementaryBProc)
			throws EBProcValidationException, NotCompletedExecutionException;

	@WebMethod()
	@WebResult(name = "unregisterStatus")
	public HashMap<String, String> unregister(@WebParam(name = "elementaryBProc") EBProc elementaryBProc)
			throws NoSuchEBProcIdException;

	@WebMethod()
	@WebResult(name = "updateStatus")
	public HashMap<String, String> update(@WebParam(name = "elementaryBProc") EBProc elementaryBProc)
			throws EBProcValidationException, NotCompletedExecutionException;

	@WebMethod()
	@WebResult(name = "startStatus")
	public HashMap<String, String> start(@WebParam(name = "elementaryBProc") EBProc elementaryBProc)
			throws NoSuchEBProcIdException;

	@WebMethod()
	@WebResult(name = "stopStatus")
	public HashMap<String, String> stop(@WebParam(name = "elementaryBProc") EBProc elementaryBProcD)
			throws NoSuchEBProcIdException;

	@WebMethod()
	@WebResult(name = "EBProc")
	public EBProc getEBProc(@WebParam(name = "elementaryBProcID") String elementaryBProcID,
			@WebParam(name = "endPoints") HashMap<String, String> endPoints) throws NoSuchEBProcIdException;

}
